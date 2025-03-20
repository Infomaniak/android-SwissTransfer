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
import com.infomaniak.core.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias GroupedTransfers = Map<TransfersGroupingManager.TransferSection, List<TransferUi>>

@HiltViewModel
class TransfersViewModel @Inject constructor(
    private val transferManager: TransferManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val sentTransfersUiState = transferManager.getTransfers(TransferDirection.SENT)
        .map { TransferUiState.Success(it.groupBySection()) }
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = TransferUiState.Loading)

    val receivedTransfersUiState = transferManager.getTransfers(TransferDirection.RECEIVED)
        .map { TransferUiState.Success(it.groupBySection()) }
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = TransferUiState.Loading)

    val sentTransfersAreEmpty: StateFlow<Boolean> = transferManager.getTransfersCount(TransferDirection.SENT)
        .map { it == 0L }
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = false)

    val selectedTransferUuids: SnapshotStateMap<String, Boolean> = mutableStateMapOf()

    fun fetchPendingTransfers() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                transferManager.fetchWaitingTransfers()
            }.cancellable().onFailure {
                SentryLog.e(TAG, "Failure for fetchWaitingTransfers", it)
            }
        }
    }

    fun deleteTransfer(transferUuid: String) {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                transferManager.deleteTransfer(transferUuid)
            }.cancellable().onFailure {
                SentryLog.e(TAG, "Failure for deleteTransfer", it)
            }
        }
    }

    sealed interface TransferUiState {
        data object Loading : TransferUiState
        data class Success(val data: GroupedTransfers) : TransferUiState
    }

    companion object {
        private val TAG = TransfersViewModel::class.java.simpleName
    }
}
