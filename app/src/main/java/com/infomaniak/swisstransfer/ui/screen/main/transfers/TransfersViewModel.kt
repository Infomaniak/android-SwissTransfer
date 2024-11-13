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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TransfersViewModel @Inject constructor(transferManager: TransferManager) : ViewModel() {

    val sentTransfers = transferManager.getTransfers(TransferDirection.SENT)
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = emptyList())

    val receivedTransfers = transferManager.getTransfers(TransferDirection.RECEIVED)
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = emptyList())

    val selectedTransferUuids: SnapshotStateMap<String, Boolean> = mutableStateMapOf()
}
