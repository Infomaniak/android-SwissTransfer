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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.di.UserAgent
import com.infomaniak.swisstransfer.services.AppDownloadManager
import com.infomaniak.swisstransfer.services.DownloadWorker
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.DownloadTarget
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.TransferIdType
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadUi
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.handleTransferDownload
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.previewUriForFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.ui.utils.isV2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilesDetailsViewModel @Inject constructor(
    private val downloadWorkerScheduler: DownloadWorker.Scheduler,
    private val fileManager: FileManager,
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    private val appDownloadManager: AppDownloadManager,
    @UserAgent private val userAgent: String,
) : ViewModel() {

    private val _fileDownloadRequests = MutableSharedFlow<String>()
    val fileDownloadRequests: SharedFlow<String> = _fileDownloadRequests.asSharedFlow()

    fun filesFlow(folderUuid: String): Flow<List<FileUi>> {
        return fileManager.getFilesFromTransfer(folderUuid)
    }

    fun transferFlow(transferIdType: TransferIdType): Flow<TransferUi> {
        return when (transferIdType) {
            is TransferIdType.LinkId -> transferManager.getTransferByLinkIdFlow(transferIdType.value).filterNotNull()
            is TransferIdType.TransferId -> transferManager.getTransferFlow(transferIdType.value).filterNotNull()
        }
    }

    fun previewUriForFile(transfer: TransferUi, file: FileUi): Flow<Uri?> {
        return transferManager.previewUriForFile(
            transfer = transfer,
            file = file,
            downloadWorkerScheduler = downloadWorkerScheduler,
            thumbnailsLocalStorage = thumbnailsLocalStorage
        )
    }

    suspend fun handleTransferDownload(
        ui: TransferDownloadUi,
        transfer: TransferUi,
        downloadTarget: DownloadTarget,
        openFile: suspend (Uri) -> Unit,
    ): Nothing = handleTransferDownload(
        ui = ui,
        transferManager = transferManager,
        apiUrlCreator = sharedApiUrlCreator,
        appDownloadManager = appDownloadManager,
        downloadWorkerScheduler = downloadWorkerScheduler,
        userAgent = userAgent,
        transfer = transfer,
        downloadTarget = downloadTarget,
        openFile = openFile,
    )

    fun scheduleFileSelectionDownload(transferUuid: String, selectedFileUids: List<String>) {
        viewModelScope.launch {
            val transfer = transferManager.getTransferFlow(transferUuid).first() ?: return@launch
            val selectedFiles = selectedFileUids.mapNotNull { fileManager.getFileUi(it) }
            if (selectedFiles.isEmpty()) return@launch

            when {
                transfer.isV2() && selectedFiles.size > 1 -> downloadWorkerScheduler.scheduleFileSelectionWork(
                    transferId = transfer.uuid,
                    fileIds = selectedFiles.map { it.uid },
                )
                transfer.isV2() -> _fileDownloadRequests.emit(selectedFiles.single().uid)
                else -> selectedFiles.forEach { file -> _fileDownloadRequests.emit(file.uid) }
            }
        }
    }
}
