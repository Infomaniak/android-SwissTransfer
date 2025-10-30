/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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

import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferStatus
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.DownloadQuotaExceededException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.ExpiredDateFetchTransferException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.NotFoundFetchTransferException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.PasswordNeededFetchTransferException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.VirusCheckFetchTransferException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.VirusDetectedFetchTransferException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.FetchTransferException.WrongPasswordFetchTransferException
import com.infomaniak.swisstransfer.di.UserAgent
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.Deleted
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.ExpiredDate
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.ExpiredQuota
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.VirusDetected
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.WaitVirusCheck
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.Success
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import splitties.toast.UnreliableToastApi
import javax.inject.Inject

@HiltViewModel
class TransferDetailsViewModel @Inject constructor(
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    @UserAgent private val userAgent: String,
) : ViewModel() {

    private val _transferUuidFlow = MutableSharedFlow<String>(1)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val transferUuidFlow : Flow<TransferDetailsUiState> = _transferUuidFlow
        .flatMapLatest { transferManager.getTransferFlow(it) }
        .map { transfer ->
            when (transfer?.transferStatus) {
                TransferStatus.READY, TransferStatus.UNKNOWN -> Success(transfer)
                TransferStatus.EXPIRED_DOWNLOAD_QUOTA -> ExpiredQuota(transfer.downloadLimit)
                TransferStatus.EXPIRED_DATE -> ExpiredDate
                TransferStatus.WAIT_VIRUS_CHECK -> WaitVirusCheck
                TransferStatus.VIRUS_DETECTED -> VirusDetected
                null -> Deleted
            }
        }

    private val _statusState = MutableSharedFlow<TransferDetailsUiState>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<TransferDetailsUiState> = merge<TransferDetailsUiState>(transferUuidFlow, _statusState)
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
            }.cancellable().onFailure { exception ->
                when (exception) {
                    is DownloadQuotaExceededException -> _statusState.emit(ExpiredQuota())
                    is ExpiredDateFetchTransferException, is NotFoundFetchTransferException -> _statusState.emit(ExpiredDate)
                    is VirusCheckFetchTransferException -> _statusState.emit(WaitVirusCheck)
                    is VirusDetectedFetchTransferException -> _statusState.emit(VirusDetected)
                    is PasswordNeededFetchTransferException -> _isDeeplinkNeedingPassword.emit(true)
                    is WrongPasswordFetchTransferException -> _isWrongDeeplinkPassword.emit(true)
                    else -> SentryLog.e(TAG, "Failure loading a transfer", exception)
                }
            }
        }
    }

    fun getTransferUrl(transferUuid: String): String = sharedApiUrlCreator.shareTransferUrl(transferUuid)

    suspend fun handleTransferDownload(
        ui: TransferDownloadUi,
        transfer: TransferUi,
        targetFile: FileUi?,
        openFile: suspend (Uri) -> Unit,
        direction: TransferDirection,
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

    fun previewUriForFile(transfer: TransferUi, file: FileUi): Flow<Uri?> {
        return transferManager.previewUriForFile(transfer, file, thumbnailsLocalStorage)
    }

    @OptIn(UnreliableToastApi::class)
    private suspend fun handleTransferDeeplink(transferUuid: String, password: String) {
        runCatching {
            transferManager.addTransferByLinkUUID(
                linkUUID = transferUuid,
                password = password,
                transferDirection = TransferDirection.RECEIVED,
            )
            _isWrongDeeplinkPassword.emit(false)
            _isDeeplinkNeedingPassword.emit(false)
        }.cancellable().onFailure { exception ->
            when (exception) {
                is NotFoundFetchTransferException,
                is ExpiredDateFetchTransferException,
                is PasswordNeededFetchTransferException,
                is WrongPasswordFetchTransferException,
                is DownloadQuotaExceededException -> throw exception
                else -> SentryLog.e(TAG, "An error has occurred when deeplink a transfer", exception)
            }
        }
    }

    sealed interface TransferDetailsUiState {
        @Immutable
        data class Success(val transfer: TransferUi) : TransferDetailsUiState

        @Immutable
        data object Loading : TransferDetailsUiState

        sealed interface ErrorTransferType : TransferDetailsUiState {
            @Immutable
            data object WaitVirusCheck : ErrorTransferType
            @Immutable
            data object VirusDetected : ErrorTransferType

            sealed interface ExpirationTransferType : ErrorTransferType {
                @Immutable
                data object Deleted : ExpirationTransferType

                @Immutable
                data object ExpiredDate : ExpirationTransferType

                @Immutable
                data class ExpiredQuota(val downloadLimit: Int? = null) : ExpirationTransferType
            }
        }
    }

    companion object {
        private val TAG = TransferDetailsViewModel::class.java.simpleName
    }
}
