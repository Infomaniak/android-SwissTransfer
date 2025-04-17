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
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.await
import com.infomaniak.core.cancellable
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.hours

class AbandonedTransferCleanupWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {

        suspend fun WorkManager.schedule(containerUuid: String): kotlin.Result<Unit> = runCatching {
            val currentTimeMillis = System.currentTimeMillis()
            val inputData = Data.Builder().putLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, currentTimeMillis).build()
            enqueueUniqueWork(
                uniqueWorkName = containerUuid,
                existingWorkPolicy = ExistingWorkPolicy.KEEP,
                request = OneTimeWorkRequestBuilder<AbandonedTransferCleanupWorker>()
                    .setInputData(inputData)
                    .setBackoffCriteria(backoffPolicy = BackoffPolicy.LINEAR, 10L, TimeUnit.MINUTES)
                    .build()
            ).await()
            Unit
        }.cancellable()

        private val backendAutoCleanupDelay = 24.hours
    }

    private object DataKeys {
        const val REQUEST_UTC_TIMESTAMP_MILLIS = "request_utc_timestamp_millis"
    }

    private fun isBeyondBackedAutoCleanup(): Boolean {
        val requestUtcTimestampMillis = inputData.getLong(DataKeys.REQUEST_UTC_TIMESTAMP_MILLIS, 0L)
        val giveUpUtcTimestampMillis = requestUtcTimestampMillis + backendAutoCleanupDelay.inWholeMilliseconds
        return System.currentTimeMillis() >= giveUpUtcTimestampMillis
    }

    override suspend fun doWork(): Result {
        if (isBeyondBackedAutoCleanup()) return Result.failure()
        TODO("Do the cleanup")
    }
}
