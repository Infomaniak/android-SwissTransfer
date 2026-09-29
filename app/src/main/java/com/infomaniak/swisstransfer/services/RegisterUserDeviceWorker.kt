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
package com.infomaniak.swisstransfer.services

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.infomaniak.core.common.isChannelEnabled
import com.infomaniak.core.common.isChannelEnabledFlow
import com.infomaniak.core.notifications.registration.AbstractNotificationsRegistrationWorker
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.twofactorauth.back.notifications.TwoFactorAuthNotifications
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import splitties.systemservices.notificationManager

@HiltWorker
class RegisterUserDeviceWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val accountUtils: AccountUtils,
) : AbstractNotificationsRegistrationWorker(appContext, params) {

    override suspend fun getConnectedHttpClient(userId: Int): OkHttpClient = accountUtils.getHttpClient(userId = userId)

    override suspend fun currentTopicsForUser(userId: Int): List<String> {
        val isEnabled = notificationManager.isChannelEnabled(TwoFactorAuthNotifications.CHANNEL_ID)
        return getNotificationTopics(isEnabled)
    }

    companion object {
        val notificationTopicsFlow: Flow<List<String>> = notificationManager
            .isChannelEnabledFlow(TwoFactorAuthNotifications.CHANNEL_ID)
            .map(::getNotificationTopics)
    }
}

private fun getNotificationTopics(canShow2faNotifications: Boolean?): List<String> {
    return when (canShow2faNotifications) {
        true -> listOf(TwoFactorAuthNotifications.TOPIC)
        false -> emptyList()
        null -> {
            SentryLog.wtf(TAG, "The channel was created too late, or was deleted by the app!")
            emptyList()
        }
    }
}

private const val TAG = "RegisterUserDeviceWorker"
