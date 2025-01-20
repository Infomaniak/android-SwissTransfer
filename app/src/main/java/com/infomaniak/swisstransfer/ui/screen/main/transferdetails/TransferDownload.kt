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
import com.infomaniak.core2.*
import com.infomaniak.core2.filetypes.FileType
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.ui.utils.hasPreview
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.systemservices.downloadManager
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
    targetFile = targetFile
).collectLatest { id ->
    autoCancelScope {
        val downloadStatusFlow = downloadManager.downloadStatusFlow(id).stateIn(scope = this)
        raceOf({
            ui.showStatusAndAwaitRemovalRequest(downloadStatusFlow, supportsPreview = targetFile?.hasPreview == true)
        }, {
            awaitFileDeletion(ui.lifecycle, id, downloadStatusFlow)
        }, {
            downloadStatusFlow.collectLatest newStatus@{ status ->
                if (status !is DownloadStatus.Complete) return@newStatus
                val uri = downloadManager.uriFor(id)
                    ?: return@newStatus // Shouldn't happen since DownloadStatus is Complete.
                repeatWhileActive {
                    ui.awaitOpenRequest()
                    openFile(uri)
                    // We can't know for sure how long the target app
                    // will take to react, if any, so we keep
                    // the action disabled for a short time to
                    // show the user their action has
                    // been taken into account, and also to
                    // prevent unintended doubled actions.
                    delay(.7.seconds)
                }
            }
        })
    }
    downloadManager.cancelAndRemove(id)
    transferManager.writeDownloadManagerId(
        transferUUID = transfer.uuid,
        fileUid = targetFile?.uid,
        uniqueDownloadManagerId = null
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
    fileUid = targetFile?.uid
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
    downloadStatusFlow: SharedFlow<DownloadStatus?>
) = downloadStatusFlow.mapLatest { status ->
    when (status) {
        null -> return@mapLatest
        DownloadStatus.Complete -> lifecycle.isResumedFlow().transformLatest { isResumed ->
            if (isResumed) {
                val isFileDeleted = downloadManager.doesFileExist(id).not()
                emit(isFileDeleted)
            }
        }.first { deleted -> deleted }
        else -> awaitCancellation()
    }
}.first()

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
        uniqueDownloadManagerId = newId?.value
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
    userAgent: String
): DownloadManager.Request? {
    val url: String
    val name: String
    when {
        targetFile != null -> {
            url = apiUrlCreator.downloadFileUrl(transfer.uuid, targetFile.uid) ?: return null
            val fileName = DownloadManagerUtils.withoutProblematicCharacters(targetFile.fileName)
            name = "SwissTransfer/$fileName"
        }
        else -> {
            url = apiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return null
            val fileName = currentDateTimeWithSecondsString()
            name = "SwissTransfer/$fileName.zip"
        }
    }
    return DownloadManagerUtils.requestFor(
        url = url,
        nameWithoutProblematicChars = name,
        mimeType = FileType.guessMimeTypeFromFileName(name),
        userAgent = userAgent,
    )
}
