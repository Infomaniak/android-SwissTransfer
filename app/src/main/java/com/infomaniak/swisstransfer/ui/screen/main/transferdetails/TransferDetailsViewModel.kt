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

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferDetailsViewModel @Inject constructor(
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
) : ViewModel() {

    private val _transferUuidFlow = MutableSharedFlow<String>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val transfer = _transferUuidFlow
        .flatMapLatest { transferManager.getTransferFlow(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val checkedFiles: SnapshotStateMap<String, Boolean> = mutableStateMapOf()

    fun loadTransfer(transferUuid: String) {
        viewModelScope.launch {
            _transferUuidFlow.emit(transferUuid)
        }
    }

    fun getTransferUrl(transferUuid: String): String = sharedApiUrlCreator.shareTransferUrl(transferUuid)
}
