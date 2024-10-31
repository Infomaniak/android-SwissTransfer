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

    override suspend fun launchWork(): Result {
        SentryLog.i(TAG, "work launched")
        val uploadSession = uploadManager.initUploadSession(recaptcha = "Recaptcha")!!
        SentryLog.d(TAG, "work started with ${uploadSession.files.count()} files")

        uploadSession.files.forEach { fileSession ->
            uploadFileTask.start(fileSession, uploadSession)
        }

        uploadManager.finishUploadSession(uploadSession.uuid)
        importLocalStorage.removeImportFolder()

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
    }
}
