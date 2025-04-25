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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.core.mapSync
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import com.infomaniak.swisstransfer.upload.UploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor() : ViewModel() {

    val stateFlow: StateFlow<UploadState?> = UploadForegroundService.uploadStateFlow

    val hasPickedFiles: StateFlow<Boolean> = UploadForegroundService.pickedFilesFlow.mapSync { it.isNotEmpty() }

    val retryRequest = CallableState<Unit>()
    val editRequest = CallableState<Unit>()
    val abandonUploadRequest = CallableState<Unit>()

    init {
        viewModelScope.launch { handleCancellationRequests() }
        viewModelScope.launch {
            UploadForegroundService.uploadStateFlow.collectLatest { uploadState ->
                if (uploadState is UploadState.Retry) handleRetryRequests()
            }
        }
    }

    private suspend fun handleCancellationRequests(): Nothing = repeatWhileActive {
        abandonUploadRequest.awaitOneCall()
        UploadForegroundService.cancelUpload()
    }

    private suspend fun handleRetryRequests(): Nothing = repeatWhileActive {
        val shouldRetry = raceOf(
            { retryRequest.awaitOneCall(); true },
            { editRequest.awaitOneCall(); false },
        )
        when (shouldRetry) {
            true -> UploadForegroundService.retry()
            false -> UploadForegroundService.giveUp()
        }
    }
}
