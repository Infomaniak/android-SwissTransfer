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

import android.net.Uri
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadDestination
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadFileSession
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import splitties.init.appCtx
import java.io.BufferedInputStream
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

class TransferUploader(
    private val uploadManager: UploadManager,
    private val fileChunkSizeManager: FileChunkSizeManager,
    private val destination: UploadDestination,
) {

    companion object {
        private val TAG = UploadSession::class.java.simpleName
    }

    suspend fun uploadAllWithRetries() {
        TODO()
        uploadManager.finalizeUploadSession(TODO()) //TODO: Also retry that if needed, and make sure the backend supports it.
    }

    private suspend fun uploadFile(sessionUuid: String) {

    }

    private val contentResolver = appCtx.contentResolver

    private suspend fun start(
        targetFileUri: Uri,
        fileUUID: String,
        uploadFileSession: UploadFileSession,
        uploadSessionUuid: String,
        onUploadBytes: suspend (Long) -> Unit,
    ) {

        val chunkSize = fileChunkSizeManager.computeChunkSize(fileSize = uploadFileSession.size)
        val totalChunks = fileChunkSizeManager.computeFileChunks(fileSize = uploadFileSession.size, fileChunkSize = chunkSize)
        val parallelChunks = fileChunkSizeManager.computeParallelChunks(fileChunkSize = chunkSize)

        SentryLog.d(TAG, "chunkSize:$chunkSize | totalChunks:$totalChunks | parallelChunks:$parallelChunks")

        contentResolver.openInputStream(targetFileUri)!!.buffered().use { inputStream ->
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
                // TODO: Skip already uploaded chunks

                val isLastChunk = chunkIndex == lastChunkIndex
                val dataByteArray = getReusableByteArray(byteArrayPool, inputStream, chunkSize, isLastChunk)

                val count = inputStream.read(dataByteArray, 0, dataByteArray.size)
                if (count == -1) {
                    requestSemaphore.release()
                    break
                }

                launch {
                    try {
                        if (totalChunks > 1) {
                            waitForOthersIfLastChunk(
                                isLastChunk = isLastChunk,
                                allChunksButLastUploadedSignal = allChunksButLastUploadedSignal,
                                completedChunks = completedChunks,
                                lastChunkIndex = lastChunkIndex
                            )
                        }
                        startUploadChunk(
                            fileUUID = fileUUID,
                            chunkIndex = chunkIndex,
                            isLastChunk = isLastChunk,
                            isRetry = false,
                            data = dataByteArray,
                            onUploadBytes = onUploadBytes
                        )
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

    private suspend fun waitForOthersIfLastChunk(
        isLastChunk: Boolean,
        allChunksButLastUploadedSignal: CompletableJob,
        completedChunks: AtomicInteger,
        lastChunkIndex: Int,
    ) {
        if (isLastChunk) {
            allChunksButLastUploadedSignal.join() // Wait for all the other jobs to complete
        } else {
            if (completedChunks.incrementAndGet() == lastChunkIndex) allChunksButLastUploadedSignal.complete()
        }
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
        isRetry: Boolean,
        data: ByteArray,
        crossinline onUploadBytes: suspend (Long) -> Unit,
    ) = coroutineScope {
        var oldBytesSentTotal = 0L
        uploadManager.uploadChunk(
            uploadHost = destination.uploadHost,
            remoteContainerUuid = destination.containerUuid,
            fileUUID = fileUUID,
            chunkIndex = chunkIndex,
            isLastChunk = isLastChunk,
            isRetry = isRetry,
            data = data,
            onUpload = { bytesSentTotal, _ ->
                ensureActive() // Cancel when this chunk is resumed while parent scope is cancelled
                mutex.withLock {
                    onUploadBytes(bytesSentTotal - oldBytesSentTotal)
                    oldBytesSentTotal = bytesSentTotal
                }
            }
        )
    }
}
