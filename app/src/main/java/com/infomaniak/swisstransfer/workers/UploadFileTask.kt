/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.workers

import androidx.core.net.toFile
import androidx.core.net.toUri
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadFileSession
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import java.io.BufferedInputStream
import java.io.File
import java.util.concurrent.ArrayBlockingQueue

class UploadFileTask(
    private val uploadManager: UploadManager,
    private val fileChunkSizeManager: FileChunkSizeManager,
) {

    private val mutex = Mutex()

    suspend fun start(
        uploadFileSession: UploadFileSession,
        uploadSession: UploadSession,
        onUploadBytes: suspend (Long) -> Unit,
    ) = coroutineScope {
        SentryLog.i(TAG, "start upload file ${uploadFileSession.localPath}")
        val fileUUID: String = uploadFileSession.remoteUploadFile?.uuid
            ?: throwAndDestroyUpload(uploadSession.uuid, "Remote upload file not found")

        val chunkSize = fileChunkSizeManager.computeChunkSize(fileSize = uploadFileSession.size)
        val totalChunks = fileChunkSizeManager.computeFileChunks(fileSize = uploadFileSession.size, fileChunkSize = chunkSize)
        val parallelChunks = fileChunkSizeManager.computeParallelChunks(fileChunkSize = chunkSize)
        val lastChunkIndex = totalChunks - 1

        val requestSemaphore = Semaphore(parallelChunks)
        val byteArrayPool = ArrayBlockingQueue<ByteArray>(parallelChunks)
        val chunkParentJob = Job()

        SentryLog.d(TAG, "chunkSize:$chunkSize | totalChunks:$totalChunks | parallelChunks:$parallelChunks")

        uploadFileSession.getLocalIoFile(uploadSession.uuid).inputStream().buffered().use { inputStream ->
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

                async(chunkParentJob) {
                    startUploadChunk(uploadSession, fileUUID, chunkIndex, isLastChunk, dataByteArray, onUploadBytes)
                    byteArrayPool.offer(dataByteArray)
                    requestSemaphore.release()
                }
            }
        }

        chunkParentJob.complete()
        chunkParentJob.join()
        byteArrayPool.clear()
    }

    private suspend inline fun UploadFileSession.getLocalIoFile(uploadUuid: String): File {
        return localPath.toUri().toFile().also {
            if (!it.exists()) {
                throwAndDestroyUpload(uploadUuid, "The file ${it.path} doesn't exists")
            }
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
        uploadSession: UploadSession,
        fileUUID: String,
        chunkIndex: Int,
        isLastChunk: Boolean,
        data: ByteArray,
        crossinline onUploadBytes: suspend (Long) -> Unit,
    ) {
        var oldBytesSentTotal = 0L
        uploadManager.uploadChunk(
            uuid = uploadSession.uuid,
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

    private suspend inline fun throwAndDestroyUpload(uploadUuid: String, throwMessage: String): Nothing {
        uploadManager.deleteUploadSession(uploadUuid)
        error(throwMessage)
    }

    companion object {
        private val TAG = UploadFileTask::class.java.simpleName
    }
}