/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
import com.infomaniak.core.autoCancelScope
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.*
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.upload.TransferUploader.ChunkUploadStatus.DefinitelyComplete
import com.infomaniak.swisstransfer.upload.TransferUploader.ChunkUploadStatus.StartedOrComplete
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import io.ktor.http.content.OutgoingContent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
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
class TransferUploader(
    val startRequest: StartUploadRequest,
    private val uploadManager: InMemoryUploadManager,
    private val transferManager: TransferManager,
    private val fileChunkSizeManager: FileChunkSizeManager,
    private val request: UploadSessionRequest,
    private val destination: UploadDestination,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
) {

    private val pickedFiles: List<PickedFile> = startRequest.files

    init {
        require(pickedFiles.size == destination.filesUuid.size)
    }

    private val uploadedBytes = AtomicLong(value = 0)
    private val uploadedBytesUpdates = MutableSharedFlow<Unit>(extraBufferCapacity = 1, replay = 1).also {
        it.tryEmit(Unit)
    }

    private val filesToUploadMetadata = pickedFiles.mapIndexed { index, pickedFile ->
        FileToUploadMetaData(
            pickedFile = pickedFile,
            chunkConfig = fileChunkSizeManager.computeChunkConfig(fileSize = pickedFile.size),
            uuid = destination.filesUuid[index],
        )
    }

    private class FileToUploadMetaData(
        val pickedFile: PickedFile,
        val chunkConfig: FileChunkSizeManager.ChunkConfig,
        val uuid: String,
    ) {

        val chunksUploadStatus = Array<ChunkUploadStatus?>(chunkConfig.totalChunks) { null }
        var thumbnailSaved = false
    }

    private enum class ChunkUploadStatus {

        /** Upload started but might not have come to completion. */
        StartedOrComplete,

        /** Upload completed, because we successfully received the confirmation from the backend. */
        DefinitelyComplete;
    }

    /**
     * Returns the transfer UUID once the upload completes successfully.
     */
    suspend fun uploadRemainderOrThrow(updateState: (UploadState.Ongoing) -> Unit): String = autoCancelScope {
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
            status = UploadState.Ongoing.Uploading.Status.InProgress,
            info = startRequest.info
        )
    }

    private suspend fun uploadRemainderOrThrow(): String {
        uploadFiles()
        val session = NewUploadSession(
            duration = request.validityPeriod,
            authorEmail = request.authorEmail,
            password = request.password,
            message = request.message,
            numberOfDownload = request.downloadCountLimit,
            language = request.languageCode,
            recipientsEmails = request.recipientsEmails,
            files = pickedFiles.mapIndexed { index, pickedFile ->
                pickedFile.toUploadFileSession(uuid = destination.filesUuid[index])
            },
            remoteContainer = destination.container,
            remoteUploadHost = destination.uploadHost
        )
        val transferUuid = uploadManager.finalizeUploadSession(session)

        thumbnailsLocalStorage.renameOngoingThumbnailsFolderWith(transferUuid)
        transferManager.updateTransferFilesThumbnails(
            transferUUID = transferUuid,
            thumbnailRootPath = thumbnailsLocalStorage.getThumbnailsFolderFor(transferUuid).toString(),
        )

        return transferUuid
    }

    private suspend fun uploadFiles() {
        filesToUploadMetadata.forEach {
            saveThumbnailAndUploadFileIfNeeded(it)
        }
    }

    private val contentResolver = appCtx.contentResolver

    private suspend fun saveThumbnailAndUploadFileIfNeeded(metadata: FileToUploadMetaData) {

        val allChunksUploaded = metadata.chunksUploadStatus.all { it == DefinitelyComplete }
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

    private suspend fun syncFileChunksUploadStatuses(metadata: FileToUploadMetaData, totalChunks: Int) = coroutineScope {
        for (chunkIndex in 0..<totalChunks) {
            when (metadata.chunksUploadStatus[chunkIndex]) {
                null, DefinitelyComplete -> continue // Leave as is.
                StartedOrComplete -> launch {
                    syncChunkUploadStatus(metadata, chunkIndex)
                }
            }
        }
    }

    private suspend fun syncChunkUploadStatus(metadata: FileToUploadMetaData, chunkIndex: Int) {
        val exists: Boolean = uploadManager.doesChunkExist(
            remoteContainerUuid = destination.container.uuid,
            fileUUID = metadata.uuid,
            chunkIndex = chunkIndex,
            chunkSize = metadata.chunkConfig.fileChunkSize
        )
        metadata.chunksUploadStatus[chunkIndex] = if (exists) DefinitelyComplete else null
    }

    private suspend fun uploadFileInChunks(metadata: FileToUploadMetaData) = Dispatchers.IO {

        val chunkConfig = metadata.chunkConfig
        val totalChunks = chunkConfig.totalChunks

        syncFileChunksUploadStatuses(metadata, totalChunks)

        val requestSemaphore = Semaphore(chunkConfig.parallelChunks)

        val lastChunkIndex = totalChunks - 1

        SentryLog.d(TAG, "$chunkConfig")

        coroutineScope {
            for (chunkIndex in 0 until lastChunkIndex) launch {
                requestSemaphore.withPermit {
                    uploadChunkIfNeeded(metadata = metadata, chunkIndex = chunkIndex, isLastChunk = false)
                }
            }
        }
        uploadChunkIfNeeded(metadata = metadata, chunkIndex = lastChunkIndex, isLastChunk = true)
    }

    private suspend fun uploadChunkIfNeeded(metadata: FileToUploadMetaData, chunkIndex: Int, isLastChunk: Boolean) {
        val uploadStatus = metadata.chunksUploadStatus[chunkIndex]
        val fileUuid = metadata.uuid
        if (uploadStatus == DefinitelyComplete) {
            SentryLog.d(TAG, "skipping chunk #$chunkIndex since it's already been uploaded (file $fileUuid)")
            return
        }

        val chunkSize = metadata.chunkConfig.fileChunkSize
        val totalFileSize = metadata.pickedFile.size
        val chunkedContent = metadata.pickedFile.uri.toOutgoingContent(
            offset = chunkSize * chunkIndex,
            length = if (isLastChunk) {
                (totalFileSize % chunkSize).let { if (it == 0L) chunkSize.toLong() else it }
            } else {
                chunkSize
            }
        )
        metadata.chunksUploadStatus[chunkIndex] = StartedOrComplete
        withRetries { isRetrying ->
            if (isRetrying) {
                syncChunkUploadStatus(metadata, chunkIndex)
                val uploadStatus = metadata.chunksUploadStatus[chunkIndex]
                if (uploadStatus == DefinitelyComplete) {
                    SentryLog.d(TAG, "no need to retry")
                    SentryLog.d(TAG, "skipping chunk #$chunkIndex since it's already been uploaded (file $fileUuid)")
                    return
                }
            }
            SentryLog.i(TAG, "Uploading chunk #$chunkIndex of $fileUuid") {
                uploadChunk(
                    fileUUID = fileUuid,
                    chunkIndex = chunkIndex,
                    isLastChunk = isLastChunk,
                    data = chunkedContent
                )
            }
        }
        metadata.chunksUploadStatus[chunkIndex] = DefinitelyComplete
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
        isLastChunk: Boolean,
        data: OutgoingContent.WriteChannelContent,
    ) {
        var oldBytesSentTotal = 0L
        try {
            uploadManager.uploadChunk(
                uploadHost = destination.uploadHost,
                remoteContainerUuid = destination.container.uuid,
                fileUUID = fileUUID,
                chunkIndex = chunkIndex,
                isLastChunk = isLastChunk,
                data = data,
                onUpload = { bytesSentTotal, _ ->
                    val bytesJustSent = bytesSentTotal - oldBytesSentTotal
                    oldBytesSentTotal = bytesSentTotal
                    uploadedBytes += bytesJustSent
                    uploadedBytesUpdates.tryEmit(Unit)
                }
            )
        } catch (t: Throwable) {
            uploadedBytes -= oldBytesSentTotal
            uploadedBytesUpdates.tryEmit(Unit)
            throw t
        }
    }

    private companion object {
        private val TAG = TransferUploader::class.java.simpleName
    }
}

private fun PickedFile.toUploadFileSession(uuid: String? = null): UploadFileSession = object : UploadFileSession {
    override val path: String? = null // We currently don't support adding folders to transfers.
    override val localPath: String get() = "" // Unneeded, but this model is reused.
    override val mimeType: String = this@toUploadFileSession.mimeType
    override val name: String = this@toUploadFileSession.name
    override val remoteUploadFile: RemoteUploadFile? = when (uuid) {
        null -> null
        else -> object : RemoteUploadFile {
            override val uploadStatus: UploadStatus get() = UploadStatus.FINISHED
            override val uploadedChunks: List<Int> get() = emptyList()
            override val uuid: String = uuid
        }
    }
    override val size: Long = this@toUploadFileSession.size
}
