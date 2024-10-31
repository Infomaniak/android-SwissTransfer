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
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.infomaniak.multiplatform_swisstransfer.SwissTransferInjection
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportLocalStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val importLocalStorage: ImportLocalStorage,
    private val swissTransferInjection: SwissTransferInjection,
) : BaseCoroutineWorker(appContext, params) {

    private val uploadManager inline get() = swissTransferInjection.uploadManager

    private val fileChunkSizeManager by lazy {
        FileChunkSizeManager(
            chunkMinSize = EXPECTED_CHUNK_SIZE,
            chunkMaxSize = EXPECTED_CHUNK_SIZE,
            totalChunks = TOTAL_CHUNKS,
        )
    }
    private val uploadFileTask by lazy {
        UploadFileTask(uploadManager, fileChunkSizeManager)
    }

    private var totalUploadedBytes = 0L

    override suspend fun launchWork(): Result {
        SentryLog.i(TAG, "work launched")

        if (uploadManager.getUploadsCount() == 0L) {
            SentryLog.w(TAG, "No upload pending")
            return Result.failure()
        }

        val uploadSession = uploadManager.initUploadSession(recaptcha = "Recaptcha")!!
        SentryLog.d(TAG, "work started with ${uploadSession.files.count()} files")

        uploadSession.files.forEach { fileSession ->
            uploadFileTask.start(fileSession, uploadSession) { bytesSent ->
                totalUploadedBytes += bytesSent
                setProgress(workDataOf(TOTAL_UPLOADED_BYTES_TAG to totalUploadedBytes))
            }
        }

        uploadManager.finishUploadSession(uploadSession.uuid)
        importLocalStorage.removeImportFolder()

        setProgress(workDataOf(TOTAL_UPLOADED_BYTES_TAG to 100))
        return Result.success()
    }

    override fun onFinish() {
        SentryLog.i(TAG, "work finished")
    }

    @Singleton
    class Scheduler @Inject constructor(private val workManager: WorkManager) {

        fun scheduleWork() {
            SentryLog.i(TAG, "work scheduled")
            val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            workManager.enqueueUniqueWork(TAG, ExistingWorkPolicy.REPLACE, workRequest)
        }

        fun trackUploadProgressFlow(): Flow<MutableList<WorkInfo>> {
            val workQuery = WorkQuery.Builder.fromUniqueWorkNames(listOf(TAG))
                .addStates(listOf(WorkInfo.State.RUNNING))
                .build()
            return workManager.getWorkInfosFlow(workQuery)
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        fun isPendingOrRunningFlow(): Flow<Boolean> {
            val workQuery = WorkQuery.Builder.fromUniqueWorkNames(listOf(TAG))
                .addStates(listOf(WorkInfo.State.RUNNING, WorkInfo.State.ENQUEUED, WorkInfo.State.BLOCKED))
                .build()
            return workManager.getWorkInfosFlow(workQuery).mapLatest { it.isNotEmpty() }
        }

        fun cancelWork() {
            SentryLog.i(TAG, "work cancelled")
            workManager.cancelUniqueWork(TAG)
        }
    }

    companion object {
        private const val TAG = "UploadWorker"
        private const val EXPECTED_CHUNK_SIZE = 50L * 1024 * 1024 // 50Mo
        private const val TOTAL_FILE_SIZE = 50L * 1024 * 1024 * 1024  // 50Go
        private const val TOTAL_CHUNKS = (TOTAL_FILE_SIZE / EXPECTED_CHUNK_SIZE).toInt()

        const val TOTAL_UPLOADED_BYTES_TAG = "totalUploadBytes"
    }
}
