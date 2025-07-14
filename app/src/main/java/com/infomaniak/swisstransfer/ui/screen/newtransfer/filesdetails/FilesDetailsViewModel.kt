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
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.di.UserAgent
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadUi
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.handleTransferDownload
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.previewUriForFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
class FilesDetailsViewModel @Inject constructor(
    private val fileManager: FileManager,
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    @UserAgent private val userAgent: String,
) : ViewModel() {

    fun filesFlow(folderUuid: String): Flow<List<FileUi>> {
        return fileManager.getFilesFromTransfer(folderUuid)
    }

    fun transferFlow(transferUuid: String): Flow<TransferUi> = transferManager.getTransferFlow(transferUuid).filterNotNull()

    fun previewUriForFile(transfer: TransferUi, file: FileUi): Flow<Uri?> {
        return transferManager.previewUriForFile(transfer = transfer, file = file, thumbnailsLocalStorage)
    }

    suspend fun handleTransferDownload(
        ui: TransferDownloadUi,
        transfer: TransferUi,
        targetFile: FileUi?,
        openFile: suspend (Uri) -> Unit,
        direction: TransferDirection?
    ): Nothing = handleTransferDownload(
        ui = ui,
        transferManager = transferManager,
        apiUrlCreator = sharedApiUrlCreator,
        userAgent = userAgent,
        transfer = transfer,
        targetFile = targetFile,
        openFile = openFile,
        direction = direction,
    )
}
