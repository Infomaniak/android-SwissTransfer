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

import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportLocalStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val importLocalStorage: ImportLocalStorage,
    private val uploadManager: UploadManager,
) : BaseCoroutineWorker(appContext, params) {

    private val fileChunkSizeManager by lazy {
        FileChunkSizeManager(
            chunkMinSize = EXPECTED_CHUNK_SIZE,
            chunkMaxSize = EXPECTED_CHUNK_SIZE,
            maxChunkCount = MAX_CHUNK_COUNT,
        )
    }
    private val uploadFileTask by lazy {
        UploadFileTask(uploadManager, fileChunkSizeManager)
    }

    private var totalUploadedBytes = 0L
    private var lastUpdateTime = 0L

    override suspend fun launchWork(): Result {
        SentryLog.i(TAG, "Work launched")

        if (uploadManager.getUploadsCount() == 0L) {
            SentryLog.w(TAG, "No upload pending")
            return Result.failure()
        }

        val uploadSession = uploadManager.initUploadSession(recaptcha = "Recaptcha")!!
        val totalSize = uploadSession.files.sumOf { it.size }

        SentryLog.d(TAG, "Work started with ${uploadSession.files.count()} files of $totalSize bytes")

        uploadSession.files.forEach { fileSession ->
            uploadFileTask.start(fileSession, uploadSession) { bytesSent ->
                totalUploadedBytes += bytesSent
                emitProgress()
            }
        }

        val transferUuid = uploadManager.finishUploadSession(uploadSession.uuid)
        importLocalStorage.removeImportFolder()

        return Result.success(workDataOf(TRANSFER_UUID_TAG to transferUuid, UPLOADED_BYTES_TAG to totalSize))
    }

    override fun onFinish() {
        SentryLog.i(TAG, "Work finished")
    }

    private suspend fun emitProgress() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUpdateTime > PROGRESS_ELAPSED_TIME) {
            setProgress(workDataOf(UPLOADED_BYTES_TAG to totalUploadedBytes))
            lastUpdateTime = currentTime
        }
    }

    @Singleton
    class Scheduler @Inject constructor(
        private val workManager: WorkManager,
        private val sharedApiUrlCreator: SharedApiUrlCreator,
    ) {

        fun scheduleWork(uploadSessionUuid: String) {
            SentryLog.i(TAG, "Work scheduled uploadSessionUuid:$uploadSessionUuid")
            val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .addTag(uploadSessionUuid)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            workManager.enqueueUniqueWork(TAG, ExistingWorkPolicy.REPLACE, workRequest)
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        fun trackUploadProgressFlow(uploadSessionUuid: String): Flow<UploadProgressUiState> {
            var lastUploadedSize = 0L
            val workQuery = WorkQuery.Builder.fromUniqueWorkNames(listOf(TAG))
                .addTags(listOf(uploadSessionUuid))
                .build()

            return workManager.getWorkInfosFlow(workQuery).mapLatest { workInfoList ->
                val workInfo = workInfoList.firstOrNull() ?: return@mapLatest UploadProgressUiState.Default
                return@mapLatest when (workInfo.state) {
                    WorkInfo.State.RUNNING -> UploadProgressUiState.Progress(
                        uploadedSize = workInfo.progress.getLong(UPLOADED_BYTES_TAG, 0L),
                    ).also { lastUploadedSize = it.uploadedSize }
                    WorkInfo.State.SUCCEEDED -> UploadProgressUiState.Success(
                        uploadedSize = workInfo.outputData.getLong(UPLOADED_BYTES_TAG, 0L),
                        transferLink = workInfo.outputData.getString(TRANSFER_UUID_TAG)
                            ?.let { transferUuid -> sharedApiUrlCreator.shareTransferUrl(transferUuid) }
                            ?: return@mapLatest UploadProgressUiState.Cancelled(lastUploadedSize)
                    )
                    WorkInfo.State.CANCELLED -> UploadProgressUiState.Cancelled(lastUploadedSize)
                    else -> UploadProgressUiState.Default
                }
            }.filterNotNull()
        }

        fun cancelWork() {
            SentryLog.i(TAG, "Work cancelled")
            workManager.cancelUniqueWork(TAG)
        }
    }

    sealed class UploadProgressUiState(open val uploadedSize: Long = 0) {
        data object Default : UploadProgressUiState()

        @Immutable
        data class Progress(override val uploadedSize: Long) : UploadProgressUiState(uploadedSize)

        @Immutable
        data class Success(override val uploadedSize: Long, val transferLink: String) : UploadProgressUiState(uploadedSize)

        @Immutable
        data class Cancelled(override val uploadedSize: Long = 0) : UploadProgressUiState(uploadedSize)
    }

    companion object {
        private const val TAG = "UploadWorker"
        private const val EXPECTED_CHUNK_SIZE = 50L * 1024 * 1024 // 50Mo
        private const val TOTAL_FILE_SIZE = 50L * 1024 * 1024 * 1024  // 50Go
        private const val MAX_CHUNK_COUNT = (TOTAL_FILE_SIZE / EXPECTED_CHUNK_SIZE).toInt()

        private const val UPLOADED_BYTES_TAG = "uploaded_bytes_tag"
        private const val TRANSFER_UUID_TAG = "transfer_result_tag"

        private const val PROGRESS_ELAPSED_TIME = 50
    }
}
