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
package com.infomaniak.swisstransfer.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.infomaniak.core2.notifications.buildNotification
import com.infomaniak.core2.notifications.buildNotificationChannel
import com.infomaniak.core2.notifications.createNotificationChannels
import com.infomaniak.core2.notifications.notifyCompat
import com.infomaniak.swisstransfer.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsUtils @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val notificationManagerCompat: NotificationManagerCompat,
) {

    fun initNotificationsChannel() = with(appContext) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return@with

        val channelList = mutableListOf<NotificationChannel>()

        val uploadChannel = buildNotificationChannel(
            channelId = getString(R.string.notifications_upload_channel_id),
            name = getString(R.string.notificationsUploadChannelName),
            importance = NotificationManager.IMPORTANCE_HIGH,
        )
        channelList.add(uploadChannel)

        createNotificationChannels(channelList)
    }

    fun sendUploadNotification(notificationId: Int, intent: Intent, title: String, description: String? = null) {
        sendNotification(notificationId, buildUploadNotification(notificationId, intent, title, description))
    }

    fun buildUploadNotification(
        requestCode: Int,
        intent: Intent,
        title: String,
        description: String? = null,
    ): NotificationCompat.Builder = with(appContext) {
        return@with buildNotification(
            channelId = getString(R.string.notifications_upload_channel_id),
            requestCode = requestCode,
            intent = intent,
            icon = defaultSmallIcon,
            title = title,
            description = description,
            onlyAlertOnce = true,
        )
    }

    private fun sendNotification(notificationId: Int, builder: NotificationCompat.Builder) {
        notificationManagerCompat.notifyCompat(appContext, notificationId, builder.build())
    }

    companion object {
        private val defaultSmallIcon = R.drawable.ic_logo_notification
    }
}
