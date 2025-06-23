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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.net.Uri
import androidx.lifecycle.Lifecycle
import com.infomaniak.core.DownloadStatus
import com.infomaniak.core.UniqueDownloadId
import com.infomaniak.core.autoCancelScope
import com.infomaniak.core.cancelAndRemove
import com.infomaniak.core.cancellable
import com.infomaniak.core.doesFileExist
import com.infomaniak.core.downloadStatusFlow
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.isResumedFlow
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.startDownloadingFile
import com.infomaniak.core.uriFor
import com.infomaniak.core.utils.DownloadManagerUtils
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.ui.utils.hasPreview
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
    userAgent: String,
    transfer: TransferUi,
    targetFile: FileUi?,
    openFile: suspend (Uri) -> Unit,
): Nothing = currentOrNewDownloadManagerId(
    transferManager = transferManager,
    ui = ui,
    apiUrlCreator = apiUrlCreator,
    userAgent = userAgent,
    transfer = transfer,
    targetFile = targetFile,
).collectLatest { id ->
    autoCancelScope {
        val downloadStatusFlow = downloadManager.downloadStatusFlow(id).stateIn(scope = this)
        raceOf(
            { ui.showStatusAndAwaitRemovalRequest(downloadStatusFlow, supportsPreview = targetFile?.hasPreview == true) },
            { awaitFileDeletion(ui.lifecycle, id, downloadStatusFlow) },
            { handleOpenRequests(downloadStatusFlow, id, ui, openFile) },
        )
    }
    downloadManager.cancelAndRemove(id)
    transferManager.writeDownloadManagerId(
        transferUUID = transfer.uuid,
        fileUid = targetFile?.uid,
        uniqueDownloadManagerId = null,
    )
}.let { awaitCancellation() /* Should never reach this line. */ }

fun downloadManagerId(
    transferManager: TransferManager,
    transferUuid: String,
    fileUid: String?,
): Flow<UniqueDownloadId?> = transferManager.downloadManagerIdFor(
    transferUUID = transferUuid,
    fileUid = fileUid,
).map { UniqueDownloadId(it ?: return@map null) }

private suspend fun handleOpenRequests(
    downloadStatusFlow: StateFlow<DownloadStatus?>,
    id: UniqueDownloadId,
    ui: TransferDownloadUi,
    openFile: suspend (Uri) -> Unit,
) = downloadStatusFlow.collectLatest { status ->
    if (status !is DownloadStatus.Complete) return@collectLatest
    val uri = downloadManager.uriFor(id) ?: return@collectLatest // Shouldn't happen since DownloadStatus is Complete.
    repeatWhileActive {
        ui.awaitOpenRequest()
        openFile(uri)
        // We can't know for sure how long the target app will take to react, if any, so we keep the action disabled for a short
        // time to show the user their action has been taken into account, and also to prevent unintended doubled actions.
        delay(0.7.seconds)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun currentOrNewDownloadManagerId(
    transferManager: TransferManager,
    ui: TransferDownloadUi,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String,
    transfer: TransferUi,
    targetFile: FileUi?,
): Flow<UniqueDownloadId> = downloadManagerId(
    transferManager = transferManager,
    transferUuid = transfer.uuid,
    fileUid = targetFile?.uid,
).mapLatest { id ->
    id ?: repeatWhileActive {
        return@mapLatest getNewDownloadId(
            ui = ui,
            apiUrlCreator = apiUrlCreator,
            transferManager = transferManager,
            userAgent = userAgent,
            transfer = transfer,
            targetFile = targetFile,
        ) ?: return@repeatWhileActive
    }
}.distinctUntilChanged()

@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun awaitFileDeletion(
    lifecycle: Lifecycle,
    id: UniqueDownloadId,
    downloadStatusFlow: SharedFlow<DownloadStatus?>,
) = downloadStatusFlow.mapLatest { status ->
    when (status) {
        null -> return@mapLatest
        DownloadStatus.Complete -> lifecycle.isResumedFlow().transformLatest { isResumed ->
            if (isResumed) emit(isFileDeleted(id))
        }.first { deleted -> deleted }
        else -> awaitCancellation()
    }
}.first()

private suspend fun isFileDeleted(id: UniqueDownloadId): Boolean {
    repeat(2) { attemptIndex ->
        runCatching {
            return downloadManager.doesFileExist(id).not()
        }.cancellable().onFailure { throwable ->
            when (throwable) {
                is SecurityException, is IOException -> {
                    // SecurityException can be thrown when the file is deleted, sometimes.
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
    transferManager: TransferManager,
    userAgent: String,
    transfer: TransferUi,
    targetFile: FileUi?,
): UniqueDownloadId? {
    ui.awaitDownloadRequest()
    val request = buildDownloadRequest(transfer, targetFile, apiUrlCreator, userAgent) ?: return null
    val newId = downloadManager.startDownloadingFile(request)
    transferManager.writeDownloadManagerId(
        transferUUID = transfer.uuid,
        fileUid = targetFile?.uid,
        uniqueDownloadManagerId = newId?.value,
    )
    return newId
}

/**
 * Seconds are important to avoid overwrites if the user downloads from multiple
 * transfers within the same minute.
 * We don't expect the user to trigger download from multiple different
 * transfers within a second because of the time it takes to navigate back and forth.
 */
@SuppressLint("SimpleDateFormat")
private val dateFormatWithSeconds = SimpleDateFormat("yyyy-mm-dd_hhmmss")

private fun currentDateTimeWithSecondsString(): String {
    return dateFormatWithSeconds.format(Date())
}

private suspend fun buildDownloadRequest(
    transfer: TransferUi,
    targetFile: FileUi?,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String,
): DownloadManager.Request? {
    val url: String
    val name: String
    when {
        targetFile != null -> {
            url = apiUrlCreator.downloadFileUrl(transfer.uuid, targetFile.uid) ?: return null
            val fileName = DownloadManagerUtils.withoutProblematicCharacters(targetFile.fileName)
            name = "SwissTransfer/$fileName${if (targetFile.isFolder) ".zip" else ""}"
        }
        else -> {
            url = apiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return null
            val fileName = currentDateTimeWithSecondsString()
            name = "SwissTransfer/$fileName.zip"
        }
    }
    return runCatching {
        DownloadManagerUtils.requestFor(
            url = url,
            nameWithoutProblematicChars = name,
            mimeType = Dispatchers.IO { FileType.guessMimeTypeFromFileName(name) },
            userAgent = userAgent,
        )
    }.cancellable().onFailure {
        // Unlikely to happen since mitigation in requestFor, but we don't want to crash the app if it happens.
        SentryLog.wtf(TAG, "Failed to create the DownloadManager request", it)
        // We don't show the error, and we let the user try again.
    }.getOrNull()
}

private const val TAG = "TransferDownload"
