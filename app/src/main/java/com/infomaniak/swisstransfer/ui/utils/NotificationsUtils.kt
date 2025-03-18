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

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.infomaniak.core.notifications.*
import com.infomaniak.core.utils.percent
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.NewTransferActivity
import com.infomaniak.swisstransfer.ui.navigation.EXTERNAL_NAVIGATION_KEY
import com.infomaniak.swisstransfer.ui.navigation.ExternalNavigation
import com.infomaniak.swisstransfer.ui.navigation.TRANSFER_AUTHOR_EMAIL_KEY
import com.infomaniak.swisstransfer.ui.navigation.TRANSFER_TOTAL_SIZE_KEY
import com.infomaniak.swisstransfer.ui.navigation.TRANSFER_TYPE_KEY
import com.infomaniak.swisstransfer.ui.navigation.TRANSFER_URL_KEY
import com.infomaniak.swisstransfer.ui.navigation.TRANSFER_UUID_KEY
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.notificationIconColor
import dagger.hilt.android.qualifiers.ApplicationContext
import splitties.init.appCtx
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
            channelId = ChannelIds.ongoingUploads,
            name = getString(R.string.notificationsUploadChannelName),
            importance = NotificationManager.IMPORTANCE_LOW,
        )
        channelList.add(uploadChannel)

        val transferDraftChannel = buildNotificationChannel(
            channelId = ChannelIds.transferDraft,
            name = getString(R.string.notificationTransferDraftTitle),
            importance = NotificationManager.IMPORTANCE_MIN,
        )
        channelList.add(transferDraftChannel)

        createNotificationChannels(channelList)
    }

    fun buildUploadProgressNotification(
        authorEmail: String,
        transferType: TransferTypeUi,
        totalBytes: Long,
        uploadedBytes: Long,
    ): Notification = buildUploadProgressNotification(
        title = when {
            uploadedBytes > 0L -> {
                val percent = percent(uploadedBytes, totalBytes)
                appContext.getString(R.string.notificationUploadProgressTitle, percent)
            }
            else -> appContext.getString(R.string.uploadProgressIndication)
        },
        description = if (uploadedBytes > 0L) {
            val current = HumanReadableSizeUtils.getHumanReadableSize(appContext, uploadedBytes)
            val total = HumanReadableSizeUtils.getHumanReadableSize(appContext, totalBytes)
            "$current / $total"
        } else {
            null
        },
        authorEmail = authorEmail,
        transferType = transferType,
        totalBytes = totalBytes
    )

    fun uploadSucceeded(
        transferType: TransferTypeUi,
        transferUuid: String,
        transferUrl: String
    ) {
        val descriptionResId = if (transferType == TransferTypeUi.Mail) {
            R.string.notificationUploadSuccessDescriptionMail
        } else {
            R.string.notificationUploadSuccessDescriptionOther
        }
        val contentIntent = Intent(appContext, NewTransferActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadSuccess.name)
            .putExtra(TRANSFER_TYPE_KEY, transferType.name)
            .putExtra(TRANSFER_UUID_KEY, transferUuid)
            .putExtra(TRANSFER_URL_KEY, transferUrl)
        val notification = uploadNotificationBuilder(
            title = appContext.getString(R.string.notificationUploadSuccessTitle),
            description = appContext.getString(descriptionResId),
            intent = contentIntent
        ).setOngoing(false).build()
        notification.post(Ids.LastUpload)
    }

    fun buildUploadFailedNotification(canRetry: Boolean): Notification {
        val contentIntent = Intent(appContext, NewTransferActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadOngoing.name)
        return uploadNotificationBuilder(
            title = appContext.getString(R.string.notificationUploadFailureTitle),
            description = if (canRetry) appContext.getString(R.string.notificationUploadFailureDescription) else null,
            intent = contentIntent
        ).build()
    }

    fun buildTransferDraftNotification(): Notification {
        val contentIntent = Intent(appContext, NewTransferActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return uploadNotificationBuilder(
            channelId = ChannelIds.transferDraft,
            priority = NotificationCompat.PRIORITY_MIN,
            title = appContext.getString(R.string.notificationTransferDraftTitle),
            description = appContext.getString(R.string.notificationTransferDraftDescription),
            intent = contentIntent
        ).build()
    }

    private fun buildUploadProgressNotification(
        title: String,
        description: String? = null,
        authorEmail: String,
        transferType: TransferTypeUi,
        totalBytes: Long
    ): Notification {
        val contentIntent = Intent(appCtx, NewTransferActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadOngoing.name)
            .putExtra(TRANSFER_TYPE_KEY, transferType.name)
            .putExtra(TRANSFER_AUTHOR_EMAIL_KEY, authorEmail)
            .putExtra(TRANSFER_TOTAL_SIZE_KEY, totalBytes)
        return uploadNotificationBuilder(
            intent = contentIntent,
            title = title,
            description = description,
        ).build()
    }

    private fun uploadNotificationBuilder(
        channelId: String = ChannelIds.ongoingUploads,
        priority: Int = NotificationCompat.PRIORITY_LOW,
        intent: Intent,
        title: String,
        description: String? = null,
    ): NotificationCompat.Builder = with(appContext) {
        return@with buildNotification(
            channelId = channelId,
            requestCode = 0, // We don't need it to change since we have no concurrent uploads
            intent = intent,
            icon = defaultSmallIcon,
            iconColor = notificationIconColor,
            title = title,
            description = description,
            onlyAlertOnce = true,
        ).setOngoing(true).setPriority(priority)
    }

    private fun Notification.post(id: Int) {
        notificationManagerCompat.notifyCompat(appContext, id, this)
    }

    fun cancelNotification(notificationId: Int) {
        appContext.cancelNotification(notificationId)
    }

    object Ids {
        const val OngoingUpload = 1
        const val LastUpload = 2
    }

    private object ChannelIds {
        val ongoingUploads = appCtx.getString(R.string.notifications_upload_channel_id)
        val transferDraft = "transfer_draft"
    }

    companion object {
        private val defaultSmallIcon = R.drawable.ic_logo_notification
    }
}
