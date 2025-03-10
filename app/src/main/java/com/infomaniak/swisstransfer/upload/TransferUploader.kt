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
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.*
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import splitties.init.appCtx
import java.io.BufferedInputStream
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

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

    /**
     * Returns the transfer UUID once the upload completes successfully.
     */
    suspend fun uploadAllOrThrow(updateState: (UploadState.Ongoing) -> Unit): String {
        //TODO[UL-retry]: To count bytes,
        // consider using AtomicInteger + MutableStateFlow<Unit>, or MutableStateFlow.update { }
        // while making sure we don't exceed the max when there are retries,
        // and properly go back to start when we retry a chunk.
        // Also, how is ktor dealing with that?
        var uploadedBytes = 0L
        uploadFiles(onUploadBytes = { bytesSent ->
            uploadedBytes += bytesSent
            updateState(newUploadState(uploadedBytes))
        })
        val session = NewUploadSession(
            duration = request.validityPeriod,
            authorEmail = request.authorEmail,
            password = request.password,
            message = request.message,
            numberOfDownload = request.downloadCountLimit,
            language = request.languageCode,
            recipientsEmails = request.recipientsEmails,
            files = pickedFiles.mapIndexed { index, pickedFile ->
                pickedFile.toUploadFileSession(
                    uuid = destination.filesUuid[index]
                )
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

        return transferUuid //TODO[UL-retry]: Also retry that if needed, and make sure the backend supports it.
        //TODO[Thumbnails]: Ensure the thumbnails directory is renamed, as done in UploadWorker
    }

    private fun newUploadState(uploadedBytes: Long): UploadState.Ongoing {
        return UploadState.Ongoing(
            uploadedBytes = uploadedBytes,
            status = UploadState.Ongoing.Status.InProgress,
            info = startRequest.info
        )
    }

    private suspend fun uploadFiles(onUploadBytes: suspend (bytesSent: Long) -> Unit) {
        pickedFiles.forEachIndexed { index, pickedFile ->
            //TODO[UL-retry]: Keep track of where we left off last time, and support retries.
            start(
                targetFileUri = pickedFile.uri,
                fileUUID = destination.filesUuid[index],
                uploadFileSession = pickedFile.toUploadFileSession(),
                onUploadBytes = onUploadBytes
            )
        }
    }

    private val contentResolver = appCtx.contentResolver

    private suspend fun start(
        targetFileUri: Uri,
        fileUUID: String,
        uploadFileSession: UploadFileSession,
        onUploadBytes: suspend (Long) -> Unit,
    ) {
        SentryLog.i(TAG, "start upload file ${fileUUID}, with size ${uploadFileSession.size}")

        val chunkConfig = fileChunkSizeManager.computeChunkConfig(fileSize = uploadFileSession.size)
        val chunkSize = chunkConfig.fileChunkSize
        val totalChunks = chunkConfig.totalChunks
        val parallelChunks = chunkConfig.parallelChunks

        SentryLog.d(TAG, "chunkSize:$chunkSize | totalChunks:$totalChunks | parallelChunks:$parallelChunks")

        contentResolver.openInputStream(targetFileUri)!!.buffered().use { inputStream ->
            targetFileUri.getMimeType()?.let { mimeType ->
                thumbnailsLocalStorage.generateThumbnailFor(
                    fileUri = targetFileUri.toString(),
                    fileName = fileUUID,
                    extension = mimeType.substring(mimeType.indexOfLast { it == '/' }),
                )
            }

            uploadChunks(
                fileUUID = fileUUID,
                chunkSize = chunkSize,
                inputStream = inputStream,
                parallelChunks = parallelChunks,
                totalChunks = totalChunks,
                onUploadBytes = onUploadBytes,
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

    private suspend fun uploadChunks(
        fileUUID: String,
        chunkSize: Long,
        inputStream: BufferedInputStream,
        parallelChunks: Int,
        totalChunks: Int,
        onUploadBytes: suspend (Long) -> Unit,
    ) {
        val requestSemaphore = Semaphore(parallelChunks)
        val byteArrayPool = ArrayBlockingQueue<ByteArray>(parallelChunks)

        val completedChunks = AtomicInteger(0)
        val allChunksButLastUploadedSignal: CompletableJob = Job()
        val lastChunkIndex = totalChunks - 1

        coroutineScope {

            for (chunkIndex in 0..lastChunkIndex) {
                requestSemaphore.acquire()
                SentryLog.i(TAG, "start for chunkIndex:$chunkIndex")
                //TODO[UL-retry]: Skip already uploaded chunks

                val isLastChunk = chunkIndex == lastChunkIndex
                val dataByteArray = getReusableByteArray(byteArrayPool, inputStream, chunkSize, isLastChunk)

                val count = inputStream.read(dataByteArray, 0, dataByteArray.size)
                if (count == -1) {
                    requestSemaphore.release()
                    break
                }

                launch {
                    try {
                        // Wait for all the other jobs to complete
                        if (totalChunks > 1 && isLastChunk) {
                            allChunksButLastUploadedSignal.join()
                        }
                        startUploadChunk(
                            fileUUID = fileUUID,
                            chunkIndex = chunkIndex,
                            isLastChunk = isLastChunk,
                            data = dataByteArray,
                            onUploadBytes = onUploadBytes
                        )
                        // Allow the last chunk to start being uploaded
                        if (totalChunks > 1 && completedChunks.incrementAndGet() == lastChunkIndex) {
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

    private val mutex = Mutex()

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
        crossinline onUploadBytes: suspend (Long) -> Unit,
    ) = coroutineScope {
        var oldBytesSentTotal = 0L
        uploadManager.uploadChunk(
            uploadHost = destination.uploadHost,
            remoteContainerUuid = destination.container.uuid,
            fileUUID = fileUUID,
            chunkIndex = chunkIndex,
            isLastChunk = isLastChunk,
            data = data,
            onUpload = { bytesSentTotal, _ ->
                mutex.withLock {
                    onUploadBytes(bytesSentTotal - oldBytesSentTotal)
                    oldBytesSentTotal = bytesSentTotal
                }
            }
        )
    }

    private companion object {
        private val TAG = UploadSession::class.java.simpleName
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
