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
@file:Suppress("RemoveRedundantQualifierName") //TODO: Report the issue in YouTrack

package com.infomaniak.swisstransfer.upload

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.hours

@HiltWorker
class AbandonedTransferCleanupWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val uploadManager: InMemoryUploadManager,
) : CoroutineWorker(appContext, params) {

    private object DataKeys {
        const val CONTAINER_UUID = "container_uuid"
        const val REQUEST_UTC_TIMESTAMP_MILLIS = "request_utc_timestamp_millis"
    }

    private fun isBeyondBackedAutoCleanup(): Boolean {
        val requestUtcTimestampMillis = inputData.getLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, 0L)
        val giveUpUtcTimestampMillis = requestUtcTimestampMillis + backendAutoCleanupDelay.inWholeMilliseconds
        return System.currentTimeMillis() >= giveUpUtcTimestampMillis
    }

    override suspend fun doWork(): Result {
        if (isBeyondBackedAutoCleanup()) return Result.failure()

        val containerUuid = inputData.getString(DataKeys.CONTAINER_UUID)!!
        return runCatching {
            uploadManager.cancelUploadSessionByContainerId(containerUuid)
            Result.success()
        }.cancellable().getOrElse { t ->
            when (t) {
                is NetworkException -> Result.retry()
                else -> {
                    SentryLog.e(TAG, "Failed to do the cleanup", t)
                    Result.retry()
                }
            }
        }
    }

    companion object {

        suspend fun schedule(workManager: WorkManager, containerUuid: String): kotlin.Result<Unit> = runCatching {
            val inputData = Data.Builder()
                .putString(DataKeys.CONTAINER_UUID, containerUuid)
                .putLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, System.currentTimeMillis())
                .build()

            workManager.enqueueUniqueWork(
                uniqueWorkName = containerUuid,
                existingWorkPolicy = ExistingWorkPolicy.KEEP,
                request = OneTimeWorkRequestBuilder<AbandonedTransferCleanupWorker>()
                    .setInputData(inputData)
                    .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                    .setBackoffCriteria(backoffPolicy = BackoffPolicy.LINEAR, 10L, TimeUnit.MINUTES)
                    .build()
            ).await()
            Unit // We don't need the fancy completion type Operation.State.SUCCESS from androidx.work
        }.cancellable()

        private val backendAutoCleanupDelay = 24.hours

        private const val TAG = "AbandonedTransferCleanupWorker"
    }
}
