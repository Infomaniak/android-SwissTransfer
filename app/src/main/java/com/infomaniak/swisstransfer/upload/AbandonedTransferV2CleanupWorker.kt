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
@file:Suppress("RemoveRedundantQualifierName") // TODO: Report the issue in YouTrack

package com.infomaniak.swisstransfer.upload

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.await
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.multiplatform_swisstransfer.managers.UploadV2Manager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.hours

@HiltWorker
class AbandonedTransferV2CleanupWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val uploadManager: UploadV2Manager,
    private val accountManager: AccountManager,
) : CoroutineWorker(appContext, params) {

    private object DataKeys {
        const val TRANSFER_ID = "transfer_id"
        const val USER_ID = "user_id"
        const val FAILED = "failed"
        const val REQUEST_UTC_TIMESTAMP_MILLIS = "request_utc_timestamp_millis"
    }

    private fun isBeyondBackedAutoCleanup(): Boolean { //TODO[ST-v2]: Duplicates the one in AbandonedTransferCleanupWorker. Fix it
        val requestUtcTimestampMillis = inputData.getLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, 0L)
        val giveUpUtcTimestampMillis = requestUtcTimestampMillis + backendAutoCleanupDelay.inWholeMilliseconds
        return System.currentTimeMillis() >= giveUpUtcTimestampMillis
    }

    override suspend fun doWork(): Result {
        if (isBeyondBackedAutoCleanup()) return Result.failure()
        if (accountManager.currentUser?.id != inputData.getLong(key = DataKeys.USER_ID, defaultValue = -1)) {
            SentryLog.e(TAG, "Couldn't complete the abandoner transfer cleanup because the user id changed in the meantime.")
            return Result.failure()
        }

        val transferId = inputData.getString(DataKeys.TRANSFER_ID)!!
        val failed = inputData.getBoolean(DataKeys.FAILED, defaultValue = false)
        return runCatching {
            uploadManager.cancelTransfer(transferId = transferId, failed = failed)
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

        suspend fun schedule(
            workManager: WorkManager,
            userId: Long,
            transferId: String,
            failed: Boolean
        ): kotlin.Result<Unit> = runCatching {
            val inputData = Data.Builder()
                .putLong(DataKeys.USER_ID, userId)
                .putString(DataKeys.TRANSFER_ID, transferId)
                .putBoolean(DataKeys.FAILED, failed)
                .putLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, System.currentTimeMillis())
                .build()

            workManager.enqueueUniqueWork(
                uniqueWorkName = transferId,
                existingWorkPolicy = ExistingWorkPolicy.KEEP,
                request = OneTimeWorkRequestBuilder<AbandonedTransferV2CleanupWorker>()
                    .setInputData(inputData)
                    .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                    .setBackoffCriteria(backoffPolicy = BackoffPolicy.LINEAR, 10L, TimeUnit.MINUTES)
                    .build()
            ).await()
            Unit // We don't need the fancy completion type Operation.State.SUCCESS from androidx.work
        }.cancellable()

        private val backendAutoCleanupDelay = 24.hours

        private const val TAG = "AbandonedTransferV2CleanupWorker"
    }
}
