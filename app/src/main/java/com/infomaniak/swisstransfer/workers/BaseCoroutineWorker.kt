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
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import io.sentry.Sentry
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseCoroutineWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    abstract suspend fun launchWork(): Result
    open fun onFinish() = Unit

    final override suspend fun doWork(): Result {
        if (runAttemptCount > MAX_RETRIES) return Result.failure()

        return runCatching {
            launchWork()
        }.getOrElse { exception ->
            SentryLog.w(TAG, "Exception during work", exception)
            when (exception) {
                is CancellationException -> throw exception
                is NetworkException -> Result.retry()
                else -> {
                    Sentry.captureException(exception)
                    Result.failure()
                }
            }
        }.also {
            onFinish()
        }
    }

    companion object {
        private const val MAX_RETRIES = 3
        private val TAG = BaseCoroutineWorker::class.java.simpleName
    }
}
