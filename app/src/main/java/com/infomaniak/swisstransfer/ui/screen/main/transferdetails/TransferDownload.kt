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
import com.infomaniak.core2.*
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.invoke
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
    openFile: (Uri) -> Unit,
): Nothing = repeatWhileActive {
    val id: UniqueDownloadId = getDownloadIdFromStorage(
        transferManager = transferManager,
        transferUuid = transfer.uuid,
        fileUid = targetFile?.uid,
    ) ?: getNewDownloadId(
        ui = ui,
        apiUrlCreator = apiUrlCreator,
        transferManager = transferManager,
        userAgent = userAgent,
        transfer = transfer,
        targetFile = targetFile,
    ) ?: return@repeatWhileActive
    autoCancelScope {
        val downloadStatusFlow = downloadManager.downloadStatusFlow(id).stateIn(scope = this)
        raceOf({
            ui.showStatusAndAwaitRemovalRequest(downloadStatusFlow)
        }, {
            awaitFileDeletion(ui, id, downloadStatusFlow)
        }, {
            downloadStatusFlow.collectLatest { status ->
                if (status !is DownloadStatus.Complete) return@collectLatest
                val uri = downloadManager.uriFor(id)
                    ?: return@collectLatest // Shouldn't happen since DownloadStatus is Complete.
                repeatWhileActive {
                    ui.awaitOpenRequest()
                    openFile(uri)
                    delay(.7.seconds) // Prevent unintended doubled actions.
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
}

@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun awaitFileDeletion(
    ui: TransferDownloadUi,
    id: UniqueDownloadId,
    downloadStatusFlow: SharedFlow<DownloadStatus?>
) = downloadStatusFlow.transformLatest { status ->
    if (status == DownloadStatus.Complete) {
        ui.lifecycle.isResumedFlow().transformLatest { isResumed ->
            if (isResumed) {
                val isFileDeleted = downloadManager.doesFileExist(id).not()
                emit(isFileDeleted)
            }
        }.first { deleted -> deleted }
        emit(Unit)
    }
}.first()

private suspend fun getDownloadIdFromStorage(
    transferManager: TransferManager,
    transferUuid: String,
    fileUid: String?,
): UniqueDownloadId? = transferManager.readDownloadManagerId(transferUuid, fileUid)?.let { id ->
    val uri = Dispatchers.IO { downloadManager.getUriForDownloadedFile(id) } ?: return null
    UniqueDownloadId(id)
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
        uniqueDownloadManagerId = newId?.value
    )
    return newId
}

@SuppressLint("SimpleDateFormat")
private val dateFormat = SimpleDateFormat("yyyy-mm-dd_hh:mm:ss")

private fun currentDateString(): String {
    return dateFormat.format(Date())
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
            val dirName = "${currentDateString()}${transfer.uuid}"
            name = "SwissTransfer/$dirName/${targetFile.fileName}"
        }
        transfer.files.size == 1 -> {
            val singleFile = transfer.files.single()
            url = apiUrlCreator.downloadFileUrl(transfer.uuid, singleFile.uid) ?: return null
            name = "SwissTransfer/${singleFile.fileName}"
        }
        else -> {
            url = apiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return null
            val fileName = "${currentDateString()}${transfer.uuid}"
            name = "SwissTransfer/$fileName.zip"
        }
    }
    return DownloadManagerUtils.requestFor(
        url = url,
        name = name,
        userAgent = userAgent,
    )
}
