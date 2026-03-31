/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.Environment
import android.os.StatFs
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkQuery
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.infomaniak.core.common.DownloadStatus
import com.infomaniak.core.common.UniqueDownloadId
import com.infomaniak.core.common.utils.enumValueOfOrNull
import com.infomaniak.core.network.NetworkAvailability
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.ui.utils.displayTitle
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transform
import splitties.systemservices.notificationManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.fetchAndIncrement
import kotlin.coroutines.cancellation.CancellationException

@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val appDownloadManager: AppDownloadManager,
    private val transferManager: TransferManager,
    private val fileManager: FileManager,
    private val notificationsUtils: NotificationsUtils,
) : CoroutineWorker(appContext, params) {

    @OptIn(ExperimentalAtomicApi::class)
    private val downloadBytes = AtomicLong(0L)

    private val foregroundId by lazy { ForegroundIds.newId() }

    override suspend fun doWork(): Result {
        val transferId = inputData.getString(TRANSFER_ID_KEY) ?: run {
            SentryLog.w(TAG, "Finish with failure because transferId is null")
            return Result.failure()
        }
        val folderId = inputData.getString(FOLDER_ID_KEY)

        val transferUi = transferManager.getTransferFlow(transferId).first() ?: run {
            SentryLog.wtf(TAG, "Finish with failure because transferUi is null")
            return Result.failure()
        }

        runCatching {
            when {
                folderId != null -> {
                    val folder = fileManager.getFileUi(folderId) ?: run {
                        SentryLog.wtf(TAG, "Finish with failure because FileUi is null")
                        return Result.failure()
                    }
                    val folderPath = folder.path ?: run {
                        SentryLog.wtf(TAG, "Finish with failure because folderPath is null")
                        return Result.failure()
                    }
                    if (hasAvailableSpace(folder.fileSize).not()) {
                        SentryLog.w(TAG, "Finish with failure because not enough space to download folder")
                        return Result.failure(workDataOf(FAILURE_REASON_KEY to FailureReason.InsufficientAvailableSpace))
                    }
                    val title = folder.fileName
                    setForegroundAsync(getForegroundInfoForDownload(title, bytesSent = 0, totalBytes = 0))

                    appDownloadManager.downloadFolderToPublicDownload(
                        transferUi = transferUi,
                        folder = folder,
                        folderPath = folderPath,
                    ) { bytesSent, contentLength ->
                        setForegroundAsync(getForegroundInfoForDownload(title, bytesSent, totalBytes = contentLength))
                    }

                    val notification = notificationsUtils.buildDownloadNotification(title)
                    notificationManager.notify(uniqueWorkName(transferId, folderId), 1, notification)

                }
                else -> {
                    if (hasAvailableSpace(transferUi.sizeUploaded).not()) {
                        SentryLog.w(TAG, "Finish with failure because not enough space to download transfer")
                        return Result.failure(workDataOf(FAILURE_REASON_KEY to FailureReason.InsufficientAvailableSpace))
                    }
                    val title = transferUi.displayTitle
                    setForegroundAsync(getForegroundInfoForDownload(title, bytesSent = 0, totalBytes = 0))

                    appDownloadManager.downloadTransferToPublicDownload(transferUi) { bytesSent, contentLength ->
                        setForegroundAsync(
                            getForegroundInfoForDownload(title = title, bytesSent = bytesSent, totalBytes = contentLength)
                        )
                    }

                    val notification = notificationsUtils.buildDownloadNotification(title)
                    notificationManager.notify(uniqueWorkName(transferId, null), 1, notification)
                }
            }
        }.getOrElse { exception ->
            return when (exception) {
                is CancellationException, is NetworkException -> Result.retry()
                is AppDownloadManager.FailureException -> Result.failure()
                else -> {
                    SentryLog.e(TAG, "failure", exception)
                    Result.failure()
                }
            }
        }

        return Result.success()
    }

    @OptIn(ExperimentalAtomicApi::class)
    private suspend fun getForegroundInfoForDownload(title: String, bytesSent: Long, totalBytes: Long): ForegroundInfo {
        val downloadedBytes = downloadBytes.addAndFetch(bytesSent)
        val percent = if (totalBytes == 0L) 0 else downloadedBytes * 100 / totalBytes
        setProgress(workDataOf(DOWNLOADED_BYTES_KEY to downloadedBytes, TOTAL_SIZE_IN_BYTES_KEY to totalBytes))
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(
                /* notificationId = */ foregroundId,
                /* notification = */
                notificationsUtils.buildDownloadProgressNotification(title, percent.toInt(), indeterminate = totalBytes == 0L),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            ForegroundInfo(
                /* notificationId = */ foregroundId,
                /* notification = */
                notificationsUtils.buildDownloadProgressNotification(title, percent.toInt(), indeterminate = totalBytes == 0L),
            )
        }
    }

    private fun hasAvailableSpace(requiredBytes: Long): Boolean {
        val path = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> applicationContext.getExternalFilesDir(null)?.path ?: return true
            else -> Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
        }
        val availableBytes = StatFs(path).availableBytes
        // Add a safety margin of 10% extra space
        val requiredWithMargin = (requiredBytes * 110) / 100
        return availableBytes >= requiredWithMargin
    }

    @Singleton
    class Scheduler @Inject constructor(@param:ApplicationContext private val appContext: Context) {
        private val workManager: WorkManager by lazy { WorkManager.getInstance(appContext) }
        private val networkAvailability = NetworkAvailability()
        private val isNetworkAvailableFlow get() = networkAvailability.isNetworkAvailable

        fun scheduleWork(transferId: String, folderId: String?): UniqueDownloadId {
            val uniqueWorkName = uniqueWorkName(transferId, folderId)
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresStorageNotLow(true)
                .build()
            val inputData = workDataOf(TRANSFER_ID_KEY to transferId, FOLDER_ID_KEY to folderId)
            val workRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build()
            workManager.enqueueUniqueWork(uniqueWorkName, ExistingWorkPolicy.REPLACE, workRequest)
            return UniqueDownloadId(999)
        }

        fun downloadStatusFlow(transferId: String, folderId: String?): Flow<DownloadStatus> {
            fun WorkInfo.isPending() = state == WorkInfo.State.ENQUEUED || state == WorkInfo.State.BLOCKED
            val workQuery = WorkQuery.Builder
                .fromUniqueWorkNames(listOf(uniqueWorkName(transferId, folderId)))
                .build()

            return workManager.getWorkInfosFlow(workQuery).transform {
                val workInfo = it.firstOrNull()
                if (workInfo == null) {
                    emit(DownloadStatus.Complete)
                } else if (workInfo.isPending() && isNetworkAvailableFlow.first().not()) {
                    emit(DownloadStatus.Paused(DownloadStatus.Paused.Reason.WaitingForNetwork))
                } else {
                    emit(workInfo.toDownloadStatus())
                }
            }
        }

        fun cancelWork(transferId: String, folderId: String?) {
            workManager.cancelUniqueWork(uniqueWorkName(transferId, folderId))
        }

        private fun WorkInfo.toDownloadStatus(): DownloadStatus {
            val downloadedBytes = progress.getLong(DOWNLOADED_BYTES_KEY, 0)
            val totalBytes = progress.getLong(TOTAL_SIZE_IN_BYTES_KEY, 0)
            return when (state) {
                WorkInfo.State.ENQUEUED,
                WorkInfo.State.BLOCKED -> DownloadStatus.Pending
                WorkInfo.State.RUNNING -> DownloadStatus.InProgress(downloadedBytes, totalBytes)
                WorkInfo.State.SUCCEEDED -> DownloadStatus.Complete
                WorkInfo.State.FAILED -> {
                    val failureValue = outputData.getString(FAILURE_REASON_KEY)
                    val reason = enumValueOfOrNull<FailureReason>(failureValue)?.toDownloadStatus()
                    DownloadStatus.Failed(reason ?: DownloadStatus.Failed.Reason.UnknownError)
                }
                WorkInfo.State.CANCELLED -> DownloadStatus.Failed(DownloadStatus.Failed.Reason.CannotResume)
            }
        }
    }

    @OptIn(ExperimentalAtomicApi::class)
    private object ForegroundIds {
        private val nextId = AtomicInt(1)
        fun newId(): Int = nextId.fetchAndIncrement()
    }

    private enum class FailureReason {
        InsufficientAvailableSpace;

        fun toDownloadStatus() = when (this) {
            InsufficientAvailableSpace -> DownloadStatus.Failed.LocalIssue.InsufficientSpace
        }
    }

    companion object {
        private const val TAG = "DownloadWorker"

        private const val TRANSFER_ID_KEY = "transferId"
        private const val FOLDER_ID_KEY = "filePath"
        private const val DOWNLOADED_BYTES_KEY = "downloaded_bytes_key"
        private const val TOTAL_SIZE_IN_BYTES_KEY = "total_size_in_bytes"
        private const val FAILURE_REASON_KEY = "reason"

        private fun uniqueWorkName(transferId: String, folderId: String?): String {
            return "DownloadWorker_${transferId}_${folderId ?: "all"}"
        }
    }
}
