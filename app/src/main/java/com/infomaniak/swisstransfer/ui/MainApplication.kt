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
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import com.infomaniak.swisstransfer.ui.utils.DataManagementPreferences.IsSentryAuthorized
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.ui.utils.dataManagementDataStore
import com.infomaniak.swisstransfer.ui.utils.getPreference
import dagger.hilt.android.HiltAndroidApp
import io.sentry.SentryEvent
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid
import io.sentry.android.core.SentryAndroidOptions
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var accountManager: AccountManager

    @Inject
    lateinit var accountUtils: AccountUtils

    @Inject
    lateinit var notificationUtils: NotificationsUtils

    @Inject
    lateinit var transferManager: TransferManager

    @Inject
    lateinit var fileManager: FileManager

    @Inject
    lateinit var thumbnailsLocalStorage: ThumbnailsLocalStorage

    @Inject
    lateinit var globalCoroutineScope: CoroutineScope

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()

        notificationUtils.initNotificationsChannel()

        globalCoroutineScope.launch {
            accountUtils.init()

            withContext(ioDispatcher) {
                transferManager.getTransfers(TransferDirection.SENT).collectLatest(thumbnailsLocalStorage::cleanExpiredThumbnails)
            }

            if (accountUtils.isUserConnected()) transferManager.deleteExpiredTransfers()
        }

        SentryAndroid.init(this) { options: SentryAndroidOptions ->
            // Register the callback as an option
            options.beforeSend = SentryOptions.BeforeSendCallback { event: SentryEvent, _: Any? ->
                val exception = event.throwable
                /**
                 * Reasons to discard Sentry events :
                 * - Application is in Debug mode
                 * - User deactivated Sentry tracking in DataManagement settings
                 * - The exception was a NetworkException or [CancellationException], and we don't want to send them to Sentry
                 */
                when {
                    BuildConfig.DEBUG -> null
                    exception is CancellationException -> null
                    exception is KmpNetworkException -> null
                    exception is NetworkException -> null
                    else -> if (dataManagementDataStore.getPreference(IsSentryAuthorized)) event else null
                }
            }
        }
    }
}
