/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025-2026 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.swisstransfer.upload

import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import com.infomaniak.core.common.autoCancelScope
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.transfers.v2.Transfer
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.managers.UploadV2Manager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.network.models.upload.request.v2.ChunkEtag
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Uploading.Status
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import io.ktor.http.content.OutgoingContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import splitties.init.appCtx
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.minusAssign
import kotlin.concurrent.atomics.plusAssign
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

@OptIn(ExperimentalAtomicApi::class)
class TransferUploaderV2(
    override val startRequest: StartUploadRequest,
    private val uploadManager: UploadV2Manager,
    private val transferManager: TransferManager,
    private val fileChunkSizeManager: FileChunkSizeManager,
    private val transfer: Transfer,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
) : TransferUploader {

    private val pickedFiles: List<PickedFile> = startRequest.files

    init {
        require(pickedFiles.size == transfer.files.size)
    }

    private val uploadedBytes = AtomicLong(value = 0)
    private val uploadedBytesUpdates = MutableSharedFlow<Unit>(extraBufferCapacity = 1, replay = 1).also {
        it.tryEmit(Unit)
    }

    private val filesToUploadMetadata = pickedFiles.mapIndexed { index, pickedFile ->
        FileToUploadMetaData(
            pickedFile = pickedFile,
            chunkConfig = fileChunkSizeManager.computeChunkConfig(fileSize = pickedFile.size),
            uuid = transfer.files[index].id,
        )
    }

    private class FileToUploadMetaData(
        val pickedFile: PickedFile,
        val chunkConfig: FileChunkSizeManager.ChunkConfig,
        val uuid: String,
    ) {

        val chunksEtags = Array<ChunkEtag?>(chunkConfig.totalChunks) { null }
        var thumbnailSaved = false
    }

    /**
     * Returns the transfer UUID once the upload completes successfully.
     */
    override suspend fun uploadRemainderOrThrow(updateState: (UploadState.Ongoing) -> Unit) = autoCancelScope {
        launch {
            uploadedBytesUpdates.collect { _ ->
                updateState(newUploadState(uploadedBytes.load()))
            }
        }
        uploadRemainderOrThrow()
    }

    private fun newUploadState(uploadedBytes: Long): UploadState.Ongoing {
        return UploadState.Ongoing.Uploading(
            uploadedBytes = uploadedBytes,
            status = Status.InProgress,
            info = startRequest.info
        )
    }

    private suspend fun uploadRemainderOrThrow(): TransferUploader.UploadResult {
        uploadFiles()
        val linkId = uploadManager.finalizeTransferAndGetLinkUuid(transferId = transfer.id)
        val transferUuid = transfer.id

        thumbnailsLocalStorage.renameOngoingThumbnailsFolderWith(transfer.id)

        transferManager.updateTransferFilesThumbnails(
            transferUUID = transferUuid,
            thumbnailRootPath = thumbnailsLocalStorage.getThumbnailsFolderFor(transferUuid).toString(),
        )

        return TransferUploader.UploadResult(transferId = transferUuid, linkId = linkId)
    }

    private suspend fun uploadFiles() {
        filesToUploadMetadata.forEach {
            saveThumbnailAndUploadFileIfNeeded(it)
        }
    }

    private val contentResolver = appCtx.contentResolver

    private suspend fun saveThumbnailAndUploadFileIfNeeded(metadata: FileToUploadMetaData) {

        val allChunksUploaded = metadata.chunksEtags.none { it == null }
        if (allChunksUploaded) return

        val targetFileUri: Uri = metadata.pickedFile.uri
        val fileUUID: String = metadata.uuid
        SentryLog.i(TAG, "start upload file $fileUUID, with size ${metadata.pickedFile.size}")

        if (metadata.thumbnailSaved.not()) targetFileUri.getMimeType()?.let { mimeType ->
            thumbnailsLocalStorage.generateThumbnailFor(
                fileUri = targetFileUri,
                fileName = fileUUID,
                extension = mimeType.substring(mimeType.indexOfLast { it == '/' }),
            )
            metadata.thumbnailSaved = true
        }
        uploadFileInChunks(metadata = metadata)
    }

    private fun Uri.getMimeType(): String? {
        return when (scheme) {
            ContentResolver.SCHEME_CONTENT -> contentResolver.getType(this)
            ContentResolver.SCHEME_FILE -> MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                MimeTypeMap.getFileExtensionFromUrl(toString()).lowercase()
            )
            else -> null
        }
    }

    private suspend fun uploadFileInChunks(metadata: FileToUploadMetaData) = Dispatchers.IO {

        val chunkConfig = metadata.chunkConfig
        val totalChunks = chunkConfig.totalChunks

        val requestSemaphore = Semaphore(chunkConfig.parallelChunks)

        val lastChunkIndex = totalChunks - 1

        SentryLog.d(TAG, "$chunkConfig")

        // val etags = coroutineScope {
        //     List(totalChunks - 1) { chunkIndex ->
        //         async {
        //             requestSemaphore.withPermit {
        //                 uploadChunkIfNeeded(
        //                     metadata = metadata,
        //                     chunkIndex = chunkIndex,
        //                     isLastChunk = chunkIndex == lastChunkIndex
        //                 )
        //             }
        //         }
        //     }.awaitAll().sortedBy { it.chunkIndex } + uploadChunkIfNeeded(
        //         metadata = metadata,
        //         chunkIndex = lastChunkIndex,
        //         isLastChunk = true
        //     )
        // }

        val etags = coroutineScope {
            List(totalChunks) { chunkIndex ->
                async {
                    requestSemaphore.withPermit {
                        uploadChunkIfNeeded(
                            metadata = metadata,
                            chunkIndex = chunkIndex,
                            isLastChunk = chunkIndex == lastChunkIndex
                        )
                    }
                }
            }.awaitAll().sortedBy { it.chunkIndex }
        }
        uploadManager.finalizeFileUploadedInChunks(transfer.id, metadata.uuid, etags)
    }

    private suspend fun uploadChunkIfNeeded(metadata: FileToUploadMetaData, chunkIndex: Int, isLastChunk: Boolean): ChunkEtag {
        val fileUuid = metadata.uuid
        when (val etag = metadata.chunksEtags[chunkIndex]) {
            null -> {}
            else -> {
                SentryLog.d(TAG, "skipping chunk #$chunkIndex since it's already been uploaded (file $fileUuid)")
                return etag
            }
        }

        val chunkSize = metadata.chunkConfig.fileChunkSize
        val totalFileSize = metadata.pickedFile.size
        val chunkedContent = metadata.pickedFile.uri.toOutgoingContent(
            offset = chunkSize * chunkIndex,
            length = if (isLastChunk) {
                (totalFileSize % chunkSize).let { if (it == 0L) chunkSize else it }
            } else {
                chunkSize
            }
        )
        val etag = withRetries { isRetrying ->
            if (isRetrying) {
                val etag = metadata.chunksEtags[chunkIndex]
                if (etag != null) {
                    SentryLog.d(TAG, "no need to retry")
                    SentryLog.d(TAG, "skipping chunk #$chunkIndex since it's already been uploaded (file $fileUuid)")
                    return etag
                }
            }
            SentryLog.i(TAG, "Uploading chunk #$chunkIndex of $fileUuid") {
                uploadChunk(
                    fileUUID = fileUuid,
                    chunkIndex = chunkIndex,
                    data = chunkedContent
                )
            }
        }
        metadata.chunksEtags[chunkIndex] = etag
        return etag
    }

    private suspend inline fun <R> withRetries(block: (isRetrying: Boolean) -> R): R {
        val maxGiveUpDelay = 40.seconds
        val maxDelayBetweenRetries = 10.seconds
        val minDelayBetweenRetries = 500.milliseconds
        val attemptTimeMark = TimeSource.Monotonic.markNow()
        var attemptNumber = 0
        while (true) {
            try {
                attemptNumber++
                return block(attemptNumber > 1)
            } catch (e: NetworkException) {
                val timeUntilGiveUp = maxGiveUpDelay - attemptTimeMark.elapsedNow()
                val retryIn = (minDelayBetweenRetries * attemptNumber).coerceAtMost(maxDelayBetweenRetries)
                if (retryIn > timeUntilGiveUp) throw e
                delay(retryIn)
            }
        }
    }

    private suspend fun uploadChunk(
        fileUUID: String,
        chunkIndex: Int,
        data: OutgoingContent.WriteChannelContent,
    ): ChunkEtag {
        var oldBytesSentTotal = 0L
        try {
            val chunkNumber = chunkIndex + 1
            val etag = uploadManager.uploadFileChunk(
                transferId = transfer.id,
                fileId = fileUUID,
                chunkIndex = chunkNumber,
                data = data,
                onUpload = { bytesSentTotal, _ ->
                    val bytesJustSent = bytesSentTotal - oldBytesSentTotal
                    oldBytesSentTotal = bytesSentTotal
                    uploadedBytes += bytesJustSent
                    uploadedBytesUpdates.tryEmit(Unit)
                }
            )
            return ChunkEtag(etag, chunkNumber)
        } catch (t: Throwable) {
            uploadedBytes -= oldBytesSentTotal
            uploadedBytesUpdates.tryEmit(Unit)
            throw t
        }
    }

    private companion object {
        private val TAG = TransferUploaderV2::class.java.simpleName
    }
}
