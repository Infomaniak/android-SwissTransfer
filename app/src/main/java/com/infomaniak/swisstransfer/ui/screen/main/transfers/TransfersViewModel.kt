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
package com.infomaniak.swisstransfer.ui.screen.main.transfers

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.models.transfer.ContainerApi
import com.infomaniak.multiplatform_swisstransfer.network.models.transfer.FileApi
import com.infomaniak.multiplatform_swisstransfer.network.models.transfer.TransferApi
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransfersViewModel @Inject constructor(
    private val transferManager: TransferManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val sentTransfers = transferManager.getTransfers(TransferDirection.SENT)
        .flowOn(ioDispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = null)

    val receivedTransfers = transferManager.getTransfers(TransferDirection.RECEIVED)
        .flowOn(ioDispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = null)

    val selectedTransferUuids: SnapshotStateMap<String, Boolean> = mutableStateMapOf()

    init {
        //TODO Remove that when we'll have real transfers
        viewModelScope.launch {
            transferManager.removeAllTransfer()
            val files = listOf(
                generateFileApi(fileName = "file1.txt", path = ""),
                generateFileApi(fileName = "file2.txt", path = ""),
                generateFileApi(fileName = "file3_in_folder1.txt", path = "folder1"),
                generateFileApi(fileName = "file3_in_folder2.txt", path = "folder2"),
                generateFileApi(fileName = "file3_in_folder1_folder2.txt", path = "folder1/folder2/folder1")
            )
            val containerApi = generateContainerApi(files)
            transferManager.addDummyTransfer(
                transfer = generateTransferApi(containerApi),
                transferDirection = TransferDirection.RECEIVED
            )
        }
    }
    //checker containerUUid dossier

    private fun generateTransferApi(containerApi: ContainerApi): TransferApi {
        return TransferApi(
            linkUUID = "dummy1",
            containerUUID = "container1",
            downloadCounterCredit = 100,
            createdDateTimestamp = System.currentTimeMillis(),
            expiredDateTimestamp = System.currentTimeMillis(),
            hasBeenDownloadedOneTime = false,
            isMailSent = false,
            downloadHost = "",
            container = containerApi,
        )
    }

    private fun generateContainerApi(files: List<FileApi>): ContainerApi {
        return ContainerApi(
            uuid = "container1",
            duration = 0L,
            createdDateTimestamp = 0L,
            expiredDateTimestamp = 0L,
            numberOfFiles = 2,
            message = null,
            needPassword = false,
            language = "",
            sizeUploaded = 0L,
            deletedDateTimestamp = null,
            swiftVersion = 0,
            downloadLimit = 0,
            source = "ST",
            files = files,
        )
    }

    private fun generateFileApi(fileName: String, path: String): FileApi {
        return FileApi().apply {
            uuid = fileName
            containerUUID = "container1"
            this.fileName = fileName
            fileSizeInBytes = 5000000L
            downloadCounter = 0
            createdDateTimestamp = System.currentTimeMillis()
            expiredDateTimestamp = System.currentTimeMillis()
            eVirus = ""
            deletedDate = null
            mimeType = null
            receivedSizeInBytes = 5000000L
            this.path = path
            thumbnailPath = null
        }
    }

    fun fetchPendingTransfers() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                transferManager.fetchWaitingTransfers()
            }.onFailure {
                SentryLog.e(TAG, "Failure for fetchWaitingTransfers", it)
            }
        }
    }

    fun deleteTransfer(transferUuid: String) {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                transferManager.deleteTransfer(transferUuid)
            }.onFailure {
                SentryLog.e(TAG, "Failure for deleteTransfer", it)
            }
        }
    }

    companion object {
        private val TAG = TransfersViewModel::class.java.simpleName
    }
}
