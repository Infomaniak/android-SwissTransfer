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
package com.infomaniak.swisstransfer.ui

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import com.infomaniak.swisstransfer.ui.utils.getExpiredTransfers
import dagger.hilt.android.HiltAndroidApp
import io.sentry.SentryEvent
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid
import io.sentry.android.core.SentryAndroidOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var accountManager: AccountManager

    @Inject
    lateinit var accountUtils: AccountUtils

    @Inject
    lateinit var transferManager: TransferManager

    @Inject
    lateinit var globalCoroutineScope: CoroutineScope

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()

        globalCoroutineScope.launch {
            accountUtils.init()
            collectTransfers()
        }

        SentryAndroid.init(this) { options: SentryAndroidOptions ->
            // Register the callback as an option
            options.beforeSend = SentryOptions.BeforeSendCallback { event: SentryEvent?, _: Any? ->
                // If the application is in debug mode, discard the events
                if (BuildConfig.DEBUG) null else event
            }
        }
    }

    private suspend fun collectTransfers() {
        transferManager.getTransfers(TransferDirection.SENT).collect(::deleteExpiredTransfers)
        transferManager.getTransfers(TransferDirection.RECEIVED).collect(::deleteExpiredTransfers)
    }

    private suspend fun deleteExpiredTransfers(transfers: List<TransferUi>) {
        deleteTransfers(transfers.getExpiredTransfers(since = 15))
    }

    private suspend fun deleteTransfers(transfers: List<TransferUi>) {
        transfers.forEach { transferManager.deleteTransfer(it.uuid) }
    }
}
