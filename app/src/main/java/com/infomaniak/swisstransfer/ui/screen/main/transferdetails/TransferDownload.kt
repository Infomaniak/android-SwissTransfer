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

import android.app.DownloadManager
import com.infomaniak.core2.*
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.systemservices.downloadManager

suspend fun handleTransferDownload(
    ui: TransferDownloadUi,
    transferManager: TransferManager,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String,
    transfer: TransferUi,
): Nothing = repeatWhileActive {
    val id: UniqueDownloadId = getDownloadIdFromStorage(
        transferManager = transferManager,
        transferUuid = transfer.uuid
    ) ?: getNewDownloadId(
        ui = ui,
        apiUrlCreator = apiUrlCreator,
        transferManager = transferManager,
        userAgent = userAgent,
        transfer = transfer
    ) ?: return@repeatWhileActive
    ui.showProgressAndAwaitRemovalRequest(downloadManager.downloadStatusFlow(id))
    downloadManager.cancelAndRemove(id)
}

private suspend fun getDownloadIdFromStorage(
    transferManager: TransferManager,
    transferUuid: String
): UniqueDownloadId? = transferManager.readDownloadManagerId(transferUuid)?.let { UniqueDownloadId(it) }

private suspend fun getNewDownloadId(
    ui: TransferDownloadUi,
    apiUrlCreator: SharedApiUrlCreator,
    transferManager: TransferManager,
    userAgent: String,
    transfer: TransferUi,
): UniqueDownloadId? {
    ui.awaitDownloadRequest()
    val request = buildDownloadRequest(transfer, apiUrlCreator, userAgent) ?: return null
    val newId = downloadManager.startDownloadingFile(request)
    transferManager.writeDownloadManagerId(transfer.uuid, newId?.value)
    return newId
}

private suspend fun buildDownloadRequest(
    transfer: TransferUi,
    apiUrlCreator: SharedApiUrlCreator,
    userAgent: String
): DownloadManager.Request? {
    val url: String
    val name: String
    when (transfer.files.size) {
        1 -> {
            val singleFile = transfer.files.single()
            url = apiUrlCreator.downloadFileUrl(transfer.uuid, singleFile.uid) ?: return null
            name = "SwissTransfer/${singleFile.fileName}"
        }
        else -> {
            url = apiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return null
            name = "SwissTransfer/${transfer.uuid}.zip" //TODO: Prepend date (and ensure the iOS app does it too)
        }
    }
    return DownloadManagerUtils.requestFor(
        url = url,
        name = name,
        userAgent = userAgent,
    )
}
