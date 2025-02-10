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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadSuccessViewModel @Inject constructor(
    private val transferManager: TransferManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    fun dismissCompleteUpload(): Unit = UploadForegroundService.dismissCompleteUpload()

    private val _transferUuidFlow = MutableSharedFlow<String>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val recipientsEmails = _transferUuidFlow.flatMapLatest { transferUuid ->
        transferManager.getTransferFlow(transferUuid).map { it?.recipientsEmails ?: emptySet() }.flowOn(ioDispatcher)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptySet(),
    )

    fun fetchTransfer(transferUuid: String) {
        viewModelScope.launch(ioDispatcher) { _transferUuidFlow.emit(transferUuid) }
    }
}
