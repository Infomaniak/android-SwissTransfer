/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025-2026 Infomaniak Network SA
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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.net.Uri
import androidx.lifecycle.Lifecycle
import com.infomaniak.core.common.DownloadStatus
import com.infomaniak.core.common.UniqueDownloadId
import com.infomaniak.core.common.autoCancelScope
import com.infomaniak.core.common.cancelAndRemove
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.common.doesFileExist
import com.infomaniak.core.common.downloadStatusFlow
import com.infomaniak.core.common.isResumedFlow
import com.infomaniak.core.common.startDownloadingFile
import com.infomaniak.core.common.uriFor
import com.infomaniak.core.common.utils.DownloadManagerUtils
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.services.AppDownloadManager
import com.infomaniak.swisstransfer.services.DownloadWorker
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.utils.hasPreview
import com.infomaniak.swisstransfer.ui.utils.isV1
import com.infomaniak.swisstransfer.ui.utils.isV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.invoke
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.systemservices.downloadManager
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.Duration.Companion.seconds

suspend fun handleTransferDownload(
    ui: TransferDownloadUi,
    transferManager: TransferManager,
    apiUrlCreator: SharedApiUrlCreator,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    userAgent: String,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    openFile: suspend (Uri) -> Unit,
    direction: TransferDirection? = null,
): Nothing = if (downloadTarget is DownloadTarget.FileSelection) {
    handleFileSelectionDownloadRequests(
        ui = ui,
        transferManager = transferManager,
        apiUrlCreator = apiUrlCreator,
        downloadWorkerScheduler = downloadWorkerScheduler,
        userAgent = userAgent,
        transfer = transfer,
        downloadTarget = downloadTarget,
    )
} else {
    currentOrNewDownloadManagerId(
        transferManager = transferManager,
        ui = ui,
        apiUrlCreator = apiUrlCreator,
        downloadWorkerScheduler = downloadWorkerScheduler,
        userAgent = userAgent,
        transfer = transfer,
        downloadTarget = downloadTarget,
        direction = direction,
    ).collectLatest { id ->
        handleSingleDownload(
            id = id,
            ui = ui,
            transferManager = transferManager,
            downloadWorkerScheduler = downloadWorkerScheduler,
            transfer = transfer,
            downloadTarget = downloadTarget,
            openFile = openFile,
        )
    }.let { awaitCancellation() /* Should never reach this line. */ }
}

private suspend fun handleSingleDownload(
    id: UniqueDownloadId,
    ui: TransferDownloadUi,
    transferManager: TransferManager,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    openFile: suspend (Uri) -> Unit,
) {
    val needsDownloadWorker = downloadTarget.needsDownloadWorker(transfer)

    autoCancelScope {
        val downloadStatusFlow = createDownloadStatusFlow(
            needsDownloadWorker = needsDownloadWorker,
            downloadWorkerScheduler = downloadWorkerScheduler,
            transfer = transfer,
            downloadTarget = downloadTarget,
            id = id,
        ).stateIn(scope = this)

        raceOf(
            {
                ui.showStatusAndAwaitRemovalRequest(
                    downloadStatusFlow,
                    supportsPreview = downloadTarget.fileOrNull?.hasPreview == true
                )
            },
            { awaitFileDeletion(ui.lifecycle, id, downloadStatusFlow, transfer, downloadTarget) },
            { handleOpenRequests(downloadStatusFlow, id, ui, transfer, downloadTarget, openFile) },
        )
    }

    cancelDownload(
        needsDownloadWorker = needsDownloadWorker,
        downloadWorkerScheduler = downloadWorkerScheduler,
        transfer = transfer,
        downloadTarget = downloadTarget,
        id = id,
    )

    val fileUid = downloadTarget.fileOrNull?.uid
    transferManager.writeDownloadManagerId(
        transfer = transfer,
        fileUid = fileUid,
        uniqueDownloadManagerId = null,
    )
}

private fun DownloadTarget.needsDownloadWorker(transfer: TransferUi): Boolean = when (this) {
    is DownloadTarget.SingleFile -> transfer.isV2() && file.isFolder
    is DownloadTarget.EntireTransfer -> transfer.isV2()
    is DownloadTarget.FileSelection -> error("Handled above")
}

private fun createDownloadStatusFlow(
    needsDownloadWorker: Boolean,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    id: UniqueDownloadId,
): Flow<DownloadStatus?> = when {
    needsDownloadWorker && downloadTarget is DownloadTarget.SingleFile -> {
        downloadWorkerScheduler.downloadStatusFlow(transfer.uuid, downloadTarget.file.uid)
    }
    needsDownloadWorker && downloadTarget is DownloadTarget.EntireTransfer -> {
        downloadWorkerScheduler.downloadStatusFlow(transfer.uuid, null)
    }
    needsDownloadWorker && downloadTarget is DownloadTarget.FileSelection -> error("Handled above")
    else -> downloadManager.downloadStatusFlow(id)
}

private suspend fun cancelDownload(
    needsDownloadWorker: Boolean,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    id: UniqueDownloadId,
) {
    if (needsDownloadWorker) {
        when (downloadTarget) {
            is DownloadTarget.SingleFile -> downloadWorkerScheduler.cancelWork(transfer.uuid, downloadTarget.file.uid)
            is DownloadTarget.EntireTransfer -> downloadWorkerScheduler.cancelWork(transfer.uuid, null)
            is DownloadTarget.FileSelection -> error("Handled above")
        }
    } else {
        downloadManager.cancelAndRemove(id)
    }
}

private suspend fun handleFileSelectionDownloadRequests(
    ui: TransferDownloadUi,
    transferManager: TransferManager,
    apiUrlCreator: SharedApiUrlCreator,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    userAgent: String,
    transfer: TransferUi,
    downloadTarget: DownloadTarget.FileSelection,
): Nothing = repeatWhileActive {
    ui.awaitDownloadRequest()
    downloadSelectedFiles(
        transfer = transfer,
        files = downloadTarget.files,
        downloadWorkerScheduler = downloadWorkerScheduler,
        transferManager = transferManager,
        apiUrlCreator = apiUrlCreator,
        userAgent = userAgent,
    )
}

fun downloadManagerId(
    transferManager: TransferManager,
    transfer: TransferUi,
    fileUid: String?,
): Flow<UniqueDownloadId?> = transferManager.downloadManagerIdFor(
    transfer = transfer,
    fileUid = fileUid,
).map { UniqueDownloadId(it ?: return@map null) }

private suspend fun handleOpenRequests(
    downloadStatusFlow: StateFlow<DownloadStatus?>,
    id: UniqueDownloadId,
    ui: TransferDownloadUi,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    openFile: suspend (Uri) -> Unit,
) = downloadStatusFlow.collectLatest { status ->
    if (status !is DownloadStatus.Complete) return@collectLatest
    val uri = when {
        transfer.isV1() -> downloadManager.uriFor(id)
        downloadTarget is DownloadTarget.SingleFile -> {
            downloadManager.uriFor(id) ?: AppDownloadManager.uriFor(transfer, downloadTarget.file)
        }
        else -> null
    } ?: return@collectLatest
    repeatWhileActive {
        ui.awaitOpenRequest()
        openFile(uri)
        delay(0.7.seconds)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun currentOrNewDownloadManagerId(
    transferManager: TransferManager,
    ui: TransferDownloadUi,
    apiUrlCreator: SharedApiUrlCreator,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    userAgent: String,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    direction: TransferDirection?,
): Flow<UniqueDownloadId> {
    require(downloadTarget !is DownloadTarget.FileSelection) {
        "FileSelection downloads are handled without a global download id"
    }
    return downloadManagerId(
        transferManager = transferManager,
        transfer = transfer,
        fileUid = downloadTarget.fileOrNull?.uid,
    ).mapLatest { id ->
        id ?: repeatWhileActive {
            return@mapLatest getNewDownloadId(
                ui = ui,
                apiUrlCreator = apiUrlCreator,
                downloadWorkerScheduler = downloadWorkerScheduler,
                transferManager = transferManager,
                userAgent = userAgent,
                transfer = transfer,
                downloadTarget = downloadTarget,
                direction = direction,
            ) ?: return@repeatWhileActive
        }
    }.let {
        val isSingleNonFolderFile = downloadTarget is DownloadTarget.SingleFile && !downloadTarget.file.isFolder
        if (transfer.isV1() || isSingleNonFolderFile) it.distinctUntilChanged() else it
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun awaitFileDeletion(
    lifecycle: Lifecycle,
    id: UniqueDownloadId,
    downloadStatusFlow: SharedFlow<DownloadStatus?>,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
) = downloadStatusFlow.mapLatest { status ->
    when (status) {
        null -> return@mapLatest
        DownloadStatus.Complete -> lifecycle.isResumedFlow().transformLatest { isResumed ->
            if (isResumed) emit(isFileDeleted(id, transfer, downloadTarget))
        }.first { deleted -> deleted }
        else -> awaitCancellation()
    }
}.first()

private suspend fun isFileDeleted(id: UniqueDownloadId, transfer: TransferUi, downloadTarget: DownloadTarget): Boolean {
    repeat(2) { attemptIndex ->
        runCatching {
            return when {
                transfer.isV1() -> downloadManager.doesFileExist(id).not()
                downloadTarget is DownloadTarget.SingleFile -> {
                    downloadManager.doesFileExist(id).not() && AppDownloadManager.doesFileExist(transfer, downloadTarget.file)
                        .not()
                }
                else -> true
            }
        }.cancellable().onFailure { throwable ->
            when (throwable) {
                is SecurityException, is IOException -> {
                    if (attemptIndex > 0) {
                        SentryLog.e(TAG, "Unable to check if the downloaded file exists", throwable)
                    }
                }
                else -> throw throwable
            }
        }
        delay(.5.seconds) // Give a chance for the deletion to complete if it was ongoing.
    }
    return true // Don't assume the file still exists if we couldn't read it
}

private suspend fun getNewDownloadId(
    ui: TransferDownloadUi,
    apiUrlCreator: SharedApiUrlCreator,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    transferManager: TransferManager,
    userAgent: String,
    transfer: TransferUi,
    downloadTarget: DownloadTarget,
    direction: TransferDirection?,
): UniqueDownloadId? {
    ui.awaitDownloadRequest()
    val newId = when {
        downloadTarget is DownloadTarget.FileSelection -> error("FileSelection is handled separately")
        downloadTarget is DownloadTarget.SingleFile && transfer.isV2() && downloadTarget.file.isFolder -> {
            downloadWorkerScheduler.scheduleWork(
                transferId = transfer.uuid,
                folderId = downloadTarget.file.uid,
            )
        }
        downloadTarget is DownloadTarget.EntireTransfer && transfer.isV2() -> {
            downloadWorkerScheduler.scheduleWork(
                transferId = transfer.uuid,
                folderId = null,
            )
        }
        downloadTarget is DownloadTarget.SingleFile -> {
            val request = buildDownloadRequest(transfer, downloadTarget.file, apiUrlCreator, userAgent, direction)
                ?: return null
            downloadManager.startDownloadingFile(request)
        }
        downloadTarget is DownloadTarget.EntireTransfer -> {
            val request = buildDownloadRequest(transfer, null, apiUrlCreator, userAgent, direction)
                ?: return null
            downloadManager.startDownloadingFile(request)
        }
        else -> error("Unreachable")
    }

    val fileUid = downloadTarget.fileOrNull?.uid
    transferManager.writeDownloadManagerId(
        transfer = transfer,
        fileUid = fileUid,
        uniqueDownloadManagerId = newId?.value,
    )
    return newId
}

suspend fun downloadSelectedFiles(
    transfer: TransferUi,
    files: List<FileUi>,
    downloadWorkerScheduler: DownloadWorker.Scheduler,
    transferManager: TransferManager,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String,
) {
    files.forEach { file ->
        if (transfer.isV2() && file.isFolder) {
            val newId = downloadWorkerScheduler.scheduleWork(
                transferId = transfer.uuid,
                folderId = file.uid,
            )
            transferManager.writeDownloadManagerId(
                transfer = transfer,
                fileUid = file.uid,
                uniqueDownloadManagerId = newId.value,
            )
            return@forEach
        }

        val request = buildDownloadRequest(transfer, file, apiUrlCreator, userAgent, null)
            ?: return@forEach
        val newId = downloadManager.startDownloadingFile(request)
        transferManager.writeDownloadManagerId(
            transfer = transfer,
            fileUid = file.uid,
            uniqueDownloadManagerId = newId?.value,
        )
    }
}

@SuppressLint("SimpleDateFormat")
private val dateFormatWithSeconds = SimpleDateFormat("yyyy-MM-dd_HHmmss")

private fun currentDateTimeWithSecondsString(): String {
    return dateFormatWithSeconds.format(Date())
}

internal suspend fun buildDownloadRequest(
    transfer: TransferUi,
    targetFile: FileUi?,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String,
    direction: TransferDirection?
): DownloadManager.Request? {

    val url: String
    val name: String

    direction?.let { MatomoSwissTransfer.trackTransferEvent(it, MatomoName.DownloadTransfer) }

    when {
        targetFile != null -> {
            url = apiUrlCreator.downloadFileUrl(transfer, targetFile.uid) ?: return null
            val fileName = DownloadManagerUtils.withoutProblematicCharacters(targetFile.fileName)
            name = "SwissTransfer/$fileName${if (targetFile.isFolder) ".zip" else ""}"
        }
        transfer.apiSource == TransferUi.ApiSource.V1 -> {
            url = apiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return null
            val fileName = currentDateTimeWithSecondsString()
            name = "SwissTransfer/$fileName.zip"
        }
        else -> return null
    }

    val extraHeaders = buildSet {
        if (transfer.apiSource == TransferUi.ApiSource.V2) {
            val password = transfer.password ?: return@buildSet
            add("Transfer-Password" to password)
        }
    }

    return runCatching {
        DownloadManagerUtils.requestFor(
            url = url,
            nameWithoutProblematicChars = name,
            mimeType = Dispatchers.IO { FileType.guessMimeTypeFromFileName(name) },
            userAgent = userAgent,
            extraHeaders = extraHeaders,
        )
    }.cancellable().onFailure {
        // Unlikely to happen since mitigation in requestFor, but we don't want to crash the app if it happens.
        SentryLog.wtf(TAG, "Failed to create the DownloadManager request", it)
        // We don't show the error, and we let the user try again.
    }.getOrNull()
}

private const val TAG = "TransferDownload"
