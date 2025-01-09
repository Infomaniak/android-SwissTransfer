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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core2.DownloadManagerUtils
import com.infomaniak.core2.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.DeeplinkException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.DeeplinkException.PasswordNeededDeeplinkException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.DeeplinkException.WrongPasswordDeeplinkException
import com.infomaniak.swisstransfer.di.UserAgent
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferDetailsViewModel @Inject constructor(
    private val transferManager: TransferManager,
    private val fileManager: FileManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    @UserAgent private val userAgent: String,
) : ViewModel() {

    private val _transferUuidFlow = MutableSharedFlow<String>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = _transferUuidFlow
        .flatMapLatest { transferManager.getTransferFlow(it) }
        .map { transfer ->
            when (transfer) {
                null -> TransferDetailsUiState.Delete
                else -> TransferDetailsUiState.Success(transfer)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, TransferDetailsUiState.Loading)

    val checkedFiles: SnapshotStateMap<String, Boolean> = mutableStateMapOf()

    //region Deeplink Password
    private val _isDeeplinkNeedingPassword = MutableStateFlow(false)
    val isDeeplinkNeedingPassword = _isDeeplinkNeedingPassword as StateFlow<Boolean>

    fun resetIsDeeplinkNeedingPassword() {
        _isDeeplinkNeedingPassword.value = false
    }

    private val _isWrongDeeplinkPassword = MutableStateFlow(false)
    val isWrongDeeplinkPassword = _isWrongDeeplinkPassword as StateFlow<Boolean>

    private var _deeplinkPassword by mutableStateOf("")
    var deeplinkPassword = GetSetCallbacks(
        get = { _deeplinkPassword },
        set = { _deeplinkPassword = it },
    )
    //endregion

    private val loadFilesFlow = MutableSharedFlow<String>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val filesInFolder = loadFilesFlow.flatMapLatest { folderUuid ->
        fileManager.getFilesFromTransfer(folderUuid)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun loadTransfer(transferUuid: String) {
        viewModelScope.launch {
            runCatching {
                _isWrongDeeplinkPassword.emit(false)
                val transfer = transferManager.getTransferFlow(transferUuid).first()
                if (transfer == null) {
                    handleTransferDeeplink(transferUuid, _deeplinkPassword)
                    _transferUuidFlow.emit(transferUuid)
                } else {
                    _transferUuidFlow.emit(transferUuid)
                    transferManager.fetchTransfer(transferUuid)
                }
            }.onFailure { exception ->
                when (exception) {
                    is PasswordNeededDeeplinkException -> _isDeeplinkNeedingPassword.emit(true)
                    is WrongPasswordDeeplinkException -> _isWrongDeeplinkPassword.emit(true)
                    else -> SentryLog.e(TAG, "Failure loading a transfer", exception)
                }
            }
        }
    }

    fun loadFiles(folderUuid: String) {
        viewModelScope.launch { loadFilesFlow.emit(folderUuid) }
    }

    fun getTransferUrl(transferUuid: String): String = sharedApiUrlCreator.shareTransferUrl(transferUuid)

    fun startDownloadingAllFiles(transfer: TransferUi) {
        viewModelScope.launch {
            val url = sharedApiUrlCreator.downloadFilesUrl(transfer.uuid) ?: return@launch

            when (transfer.files.size) {
                1 -> {
                    val singleFile = transfer.files.single()
                    DownloadManagerUtils.scheduleDownload(
                        url = sharedApiUrlCreator.downloadFileUrl(transfer.uuid, singleFile.uid) ?: return@launch,
                        name = "SwissTransfer/${singleFile.fileName}",
                        userAgent = userAgent,
                    )
                }
                else -> {
                    DownloadManagerUtils.scheduleDownload(
                        url = url,
                        name = "SwissTransfer/${transfer.uuid}.zip", //TODO: Prepend date (and ensure the iOS app does it too)
                        userAgent = userAgent,
                    )
                }
            }
        }
    }

    private suspend fun handleTransferDeeplink(transferUuid: String, password: String) {
        runCatching {
            transferManager.addTransferByLinkUUID(
                linkUUID = transferUuid,
                password = password,
                transferDirection = TransferDirection.RECEIVED,
            )
            _isWrongDeeplinkPassword.emit(false)
            _isDeeplinkNeedingPassword.emit(false)
        }.onFailure { exception ->
            when (exception) {
                is DeeplinkException -> throw exception
                else -> SentryLog.e(TAG, "An error has occurred when deeplink a transfer", exception)
            }
        }
    }

    sealed class TransferDetailsUiState {

        @Immutable
        data class Success(val transfer: TransferUi) : TransferDetailsUiState()

        @Immutable
        data object Loading : TransferDetailsUiState()

        @Immutable
        data object Delete : TransferDetailsUiState()
    }

    companion object {
        private val TAG = TransferDetailsViewModel::class.java.simpleName
    }
}
