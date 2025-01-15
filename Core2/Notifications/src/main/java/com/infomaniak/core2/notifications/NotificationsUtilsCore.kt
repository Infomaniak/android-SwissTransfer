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
package com.infomaniak.core2.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.infomaniak.core2.extensions.hasPermissions

@RequiresApi(Build.VERSION_CODES.O)
fun buildNotificationChannel(
    channelId: String,
    name: String,
    importance: Int,
    description: String? = null,
    groupId: String? = null,
): NotificationChannel {
    return NotificationChannel(channelId, name, importance).apply {
        description?.let { this.description = it }
        groupId?.let { group = it }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.createNotificationChannels(
    channelList: List<NotificationChannel>,
    groupList: List<NotificationChannelGroup>? = null,
) {
    (getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager).apply {
        groupList?.let { createNotificationChannelGroups(it) }
        createNotificationChannels(channelList)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.deleteNotificationChannels(channelList: List<String>) {
    (getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager).apply {
        channelList.forEach { deleteNotificationChannel(it) }
    }
}

fun Context.buildNotification(
    channelId: String,
    requestCode: Int,
    intent: Intent,
    icon: Int,
    title: String,
    description: String? = null,
): NotificationCompat.Builder {
    val pendingIntentFlags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    return NotificationCompat.Builder(this, channelId).apply {
        setTicker(title)
        setContentTitle(title)
        description?.let { setStyle(NotificationCompat.BigTextStyle().bigText(it)) }
        setSmallIcon(icon)
        setAutoCancel(true)
        setContentIntent(PendingIntent.getActivity(this@buildNotification, requestCode, intent, pendingIntentFlags))
    }
}

@SuppressLint("MissingPermission")
fun NotificationManagerCompat.notifyCompat(context: Context, notificationId: Int, build: Notification) {
    if (
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
        context.hasPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
    ) {
        notify(notificationId, build)
    }
}

fun Context.cancelNotification(notificationId: Int) {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(notificationId)
}
