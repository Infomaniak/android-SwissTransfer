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
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.upload.TransferUploader.ChunkUploadStatus.*
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import splitties.init.appCtx
import java.io.BufferedInputStream
import java.util.concurrent.ArrayBlockingQueue
import kotlin.concurrent.atomics.*

@OptIn(ExperimentalAtomicApi::class)
class TransferUploader(
    private val uploadManager: InMemoryUploadManager,
    private val transferManager: TransferManager,
    private val fileChunkSizeManager: FileChunkSizeManager,
    private val request: UploadSessionRequest,
    private val destination: UploadDestination,
    private val startRequest: StartUploadRequest,
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
        StartedOrComplete, DefinitelyComplete;
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
        return UploadState.Ongoing(
            uploadedBytes = uploadedBytes,
            status = UploadState.Ongoing.Status.InProgress,
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
        SentryLog.i(TAG, "start upload file ${fileUUID}, with size ${metadata.pickedFile.size}")

        contentResolver.openInputStream(targetFileUri)!!.buffered().use { inputStream ->
            if (metadata.thumbnailSaved.not()) targetFileUri.getMimeType()?.let { mimeType ->
                thumbnailsLocalStorage.generateThumbnailFor(
                    fileUri = targetFileUri,
                    fileName = fileUUID,
                    extension = mimeType.substring(mimeType.indexOfLast { it == '/' }),
                )
                metadata.thumbnailSaved = true
            }

            uploadFileInChunks(
                metadata = metadata,
                fileUUID = fileUUID,
                inputStream = inputStream,
            )
        }
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
                    val exists: Boolean = uploadManager.doesChunkExist(
                        remoteContainerUuid = destination.container.uuid,
                        fileUUID = metadata.uuid,
                        chunkIndex = chunkIndex,
                        chunkSize = metadata.chunkConfig.fileChunkSize
                    )
                    metadata.chunksUploadStatus[chunkIndex] = if (exists) DefinitelyComplete else null
                }
            }
        }
    }

    private suspend fun uploadFileInChunks(
        metadata: FileToUploadMetaData,
        fileUUID: String,
        inputStream: BufferedInputStream,
    ) {

        val chunkConfig = metadata.chunkConfig
        val chunkSize = chunkConfig.fileChunkSize
        val totalChunks = chunkConfig.totalChunks
        val parallelChunks = chunkConfig.parallelChunks

        syncFileChunksUploadStatuses(metadata, totalChunks)
        val completedChunks = AtomicInt(value = 0)

        val requestSemaphore = Semaphore(parallelChunks.coerceAtLeast(if (totalChunks > 1) 2 else 1))
        val byteArrayPool = ArrayBlockingQueue<ByteArray>(parallelChunks)

        val allChunksButLastUploadedSignal: CompletableJob = Job()
        val lastChunkIndex = totalChunks - 1

        SentryLog.d(TAG, "chunkSize:$chunkSize | totalChunks:$totalChunks | parallelChunks:$parallelChunks")

        coroutineScope {

            for (chunkIndex in 0..lastChunkIndex)  {
                val uploadStatus = metadata.chunksUploadStatus[chunkIndex]
                if (uploadStatus == DefinitelyComplete) {
                    SentryLog.d(TAG, "skipping chunk #$chunkIndex since it's already been uploaded")
                    inputStream.skip(chunkSize)
                    if (totalChunks > 1 && completedChunks.incrementAndFetch() == lastChunkIndex) {
                        allChunksButLastUploadedSignal.complete()
                    }
                    continue
                }

                requestSemaphore.acquire()

                val isLastChunk = chunkIndex == lastChunkIndex
                val dataByteArray = getReusableByteArray(byteArrayPool, inputStream, chunkSize, isLastChunk)

                val isEndOfFile = inputStream.read(dataByteArray, 0, dataByteArray.size) == -1
                if (isEndOfFile) {
                    SentryLog.w(TAG, "endOfFile for chunk #$chunkIndex of $fileUUID")
                    continue
                }
                launch {
                    try {

                        // Wait for all the other jobs to complete
                        if (totalChunks > 1 && isLastChunk) {
                            allChunksButLastUploadedSignal.join()
                        }
                        metadata.chunksUploadStatus[chunkIndex] = StartedOrComplete
                        SentryLog.d(TAG, "Uploading chunk #$chunkIndex of $fileUUID")
                        startUploadChunk(
                            fileUUID = fileUUID,
                            chunkIndex = chunkIndex,
                            isLastChunk = isLastChunk,
                            data = dataByteArray
                        )
                        SentryLog.d(TAG, "Uploaded chunk #$chunkIndex of $fileUUID")
                        metadata.chunksUploadStatus[chunkIndex] = DefinitelyComplete
                        // Allow the last chunk to start being uploaded
                        if (totalChunks > 1 && completedChunks.incrementAndFetch() == lastChunkIndex) {
                            allChunksButLastUploadedSignal.complete()
                        }
                    } finally {
                        byteArrayPool.offer(dataByteArray)
                        requestSemaphore.release()
                    }
                }
            }
        }

        byteArrayPool.clear()
    }

    private fun getReusableByteArray(
        byteArrayPool: ArrayBlockingQueue<ByteArray>,
        inputStream: BufferedInputStream,
        chunkSize: Long,
        isLastChunk: Boolean,
    ): ByteArray {
        val currentChunkSize = if (isLastChunk) inputStream.available() else chunkSize.toInt()
        val reusableByteArray = if (isLastChunk) null else byteArrayPool.poll()

        return reusableByteArray ?: ByteArray(currentChunkSize)
    }

    private suspend inline fun startUploadChunk(
        fileUUID: String,
        chunkIndex: Int,
        isLastChunk: Boolean,
        data: ByteArray,
    ) = coroutineScope {
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
