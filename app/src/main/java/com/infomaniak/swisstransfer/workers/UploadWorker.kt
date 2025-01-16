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
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.compose.runtime.Immutable
import androidx.hilt.work.HiltWorker
import androidx.work.*
import androidx.work.WorkInfo.State
import com.infomaniak.core2.percent
import com.infomaniak.core2.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.NewTransferActivity
import com.infomaniak.swisstransfer.ui.navigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportLocalStorage
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val appSettingsManager: AppSettingsManager,
    private val importLocalStorage: ImportLocalStorage,
    private val notificationsUtils: NotificationsUtils,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: UploadManager,
) : BaseCoroutineWorker(appContext, params) {

    private val fileChunkSizeManager by lazy {
        FileChunkSizeManager(
            chunkMinSize = EXPECTED_CHUNK_SIZE,
            chunkMaxSize = EXPECTED_CHUNK_SIZE,
            maxChunkCount = MAX_CHUNK_COUNT,
        )
    }

    private val uploadFileTask by lazy {
        UploadFileTask(uploadManager, fileChunkSizeManager)
    }

    private var uploadedBytes = 0L
    private var lastUpdateTime = 0L

    private val serviceNotificationId = id.hashCode()
    private val transferType by lazy { appSettingsManager.getAppSettings()!!.lastTransferType.toTransferTypeUi() }
    private val uploadSessionJob: CompletableDeferred<UploadSession> = CompletableDeferred()

    override suspend fun launchWork(): Result {
        SentryLog.i(TAG, "Work launched")

        if (uploadManager.getUploadsCount() == 0L) {
            SentryLog.w(TAG, "No upload pending")
            return Result.failure()
        }

        val uploadSession = uploadManager.getLastUpload() ?: return Result.failure()
        uploadSessionJob.complete(uploadSession)

        val totalSize = uploadSession.files.sumOf { it.size }

        SentryLog.d(TAG, "Work started with ${uploadSession.files.count()} files of $totalSize bytes")

        return runCatching {

            uploadSession.files.forEach { fileSession ->
                uploadFileTask.start(fileSession, uploadSession) { bytesSent ->
                    uploadedBytes += bytesSent
                    emitProgress(totalSize)
                }
            }

            val transferUuid = uploadManager.finishUploadSession(uploadSession.uuid)
            importLocalStorage.removeImportFolder()

            displaySuccessNotification(transferUuid)

            Result.success(
                workDataOf(
                    UPLOADED_BYTES_TAG to totalSize,
                    TRANSFER_TYPE_TAG to transferType.name,
                    TRANSFER_UUID_TAG to transferUuid,
                )
            )
        }.getOrElse { exception ->
            when (exception) {
                is NetworkException -> Result.retry()
                is CancellationException -> Result.failure()
                else -> Result.failure()
            }
        }
    }

    override fun onFinish() {
        SentryLog.i(TAG, "Work finished")
    }

    private suspend fun emitProgress(totalSize: Long) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUpdateTime > PROGRESS_ELAPSED_TIME) {
            setProgress(workDataOf(UPLOADED_BYTES_TAG to uploadedBytes))
            lastUpdateTime = currentTime
            displayProgressNotification(totalSize)
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {

        val uploadSession = uploadSessionJob.await()
        val totalSize = uploadSession.files.sumOf { it.size }
        val builder = notificationsUtils.buildUploadNotification(
            requestCode = serviceNotificationId,
            intent = createProgressNotificationIntent(totalSize),
            title = applicationContext.getString(R.string.uploadProgressIndication),
        )

        return ForegroundInfo(serviceNotificationId, builder.build())
    }

    private suspend fun displayProgressNotification(totalSize: Long) {

        val percent = percent(uploadedBytes, totalSize)
        val current = HumanReadableSizeUtils.getHumanReadableSize(applicationContext, uploadedBytes)
        val total = HumanReadableSizeUtils.getHumanReadableSize(applicationContext, totalSize)

        val builder = notificationsUtils.buildUploadNotification(
            requestCode = serviceNotificationId,
            intent = createProgressNotificationIntent(totalSize),
            title = applicationContext.getString(R.string.notificationUploadProgressTitle, percent),
            description = "$current / $total",
        )

        val foregroundInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(serviceNotificationId, builder.build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(serviceNotificationId, builder.build())
        }

        setForeground(foregroundInfo)
    }

    private fun createProgressNotificationIntent(totalSize: Long): Intent {
        return Intent(applicationContext, NewTransferActivity::class.java)
            .putExtra(NOTIFICATION_NAVIGATION_KEY, NotificationNavigation.UploadProgress.name)
            .putExtra(TRANSFER_TYPE_KEY, transferType.name)
            .putExtra(TRANSFER_TOTAL_SIZE_KEY, totalSize)
    }

    private fun displaySuccessNotification(transferUuid: String) {

        val transferUrl = sharedApiUrlCreator.shareTransferUrl(transferUuid)
        val descriptionResId = if (transferType == TransferTypeUi.Mail) {
            R.string.notificationUploadSuccessDescriptionMail
        } else {
            R.string.notificationUploadSuccessDescriptionOther
        }

        notificationsUtils.sendUploadNotification(
            notificationId = NOTIFICATION_ID,
            intent = Intent(applicationContext, NewTransferActivity::class.java)
                .putExtra(NOTIFICATION_NAVIGATION_KEY, NotificationNavigation.UploadSuccess.name)
                .putExtra(TRANSFER_TYPE_KEY, transferType.name)
                .putExtra(TRANSFER_UUID_KEY, transferUuid)
                .putExtra(TRANSFER_URL_KEY, transferUrl),
            title = applicationContext.getString(R.string.notificationUploadSuccessTitle),
            description = applicationContext.getString(descriptionResId),
        )
    }

    @Singleton
    class Scheduler @Inject constructor(
        private val workManager: WorkManager,
        private val sharedApiUrlCreator: SharedApiUrlCreator,
    ) {

        fun scheduleWork(uploadSessionUuid: String) {
            SentryLog.i(TAG, "Work scheduled uploadSessionUuid:$uploadSessionUuid")
            val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .addTag(uploadSessionUuid)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            workManager.enqueueUniqueWork(TAG, ExistingWorkPolicy.REPLACE, workRequest)
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        fun trackUploadProgressFlow(uploadSessionUuid: String): Flow<UploadProgressUiState> {
            var lastUploadedSize = 0L
            val workQuery = WorkQuery.Builder.fromUniqueWorkNames(listOf(TAG))
                .addTags(listOf(uploadSessionUuid))
                .build()

            return workManager.getWorkInfosFlow(workQuery).mapLatest { workInfoList ->
                val workInfo = workInfoList.firstOrNull() ?: return@mapLatest UploadProgressUiState.Default()
                return@mapLatest when (workInfo.state) {
                    State.RUNNING -> UploadProgressUiState.Progress(workInfo.progress).also { lastUploadedSize = it.uploadedSize }
                    State.SUCCEEDED -> UploadProgressUiState.Success.create(workInfo.outputData, sharedApiUrlCreator)
                    State.FAILED -> UploadProgressUiState.Error(lastUploadedSize)
                    State.CANCELLED -> UploadProgressUiState.Cancel()
                    else -> UploadProgressUiState.Default(lastUploadedSize)
                } ?: UploadProgressUiState.Error(lastUploadedSize)
            }.filterNotNull()
        }

        fun cancelWork() {
            SentryLog.i(TAG, "Work cancelled")
            workManager.cancelUniqueWork(TAG)
        }
    }

    sealed class UploadProgressUiState(open val uploadedSize: Long) {
        @Immutable
        data class Default(override val uploadedSize: Long = 0L) : UploadProgressUiState(uploadedSize)

        @Immutable
        data class Progress(override val uploadedSize: Long) : UploadProgressUiState(uploadedSize) {
            internal constructor(progressData: Data) : this(progressData.getLong(UPLOADED_BYTES_TAG, 0L))
        }

        @Immutable
        data class Success(
            override val uploadedSize: Long,
            val transferType: String,
            val transferUuid: String,
            val transferUrl: String,
        ) : UploadProgressUiState(uploadedSize) {
            companion object {
                fun create(outputData: Data, sharedApiUrlCreator: SharedApiUrlCreator): Success? {
                    val uuid = outputData.getString(TRANSFER_UUID_TAG) ?: return null
                    return Success(
                        uploadedSize = outputData.getLong(UPLOADED_BYTES_TAG, 0L),
                        transferType = outputData.getString(TRANSFER_TYPE_TAG) ?: return null,
                        transferUuid = uuid,
                        transferUrl = sharedApiUrlCreator.shareTransferUrl(uuid),
                    )
                }
            }
        }

        @Immutable
        data class Error(override val uploadedSize: Long = 0L) : UploadProgressUiState(uploadedSize)

        @Immutable
        data class Cancel(override val uploadedSize: Long = 0L) : UploadProgressUiState(uploadedSize)
    }

    companion object {
        private const val TAG = "UploadWorker"
        private const val EXPECTED_CHUNK_SIZE = 50L * 1_024 * 1_024 // 50 MB
        private const val MAX_CHUNK_COUNT = (FileUtils.MAX_FILES_SIZE / EXPECTED_CHUNK_SIZE).toInt()

        private const val UPLOADED_BYTES_TAG = "uploaded_bytes_tag"
        private const val TRANSFER_TYPE_TAG = "transfer_type_tag"
        private const val TRANSFER_UUID_TAG = "transfer_uuid_tag"

        private const val PROGRESS_ELAPSED_TIME = 50

        private const val NOTIFICATION_ID = 42_1337_666 // ID used for the Success & Failure notifications
    }
}
