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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadProgressViewModel @Inject constructor(
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
    private val uploadManager: UploadManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _transferUuidFlow = MutableSharedFlow<String?>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val transferProgressUiState = _transferUuidFlow.flatMapLatest { transferUuid ->
        when (transferUuid) {
            null -> flow { emit(UploadWorker.UploadProgressUiState.Error()) }
            else -> uploadWorkerScheduler.trackUploadProgressFlow(transferUuid).flowOn(ioDispatcher)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UploadWorker.UploadProgressUiState.Default
    )

    fun trackUploadProgress() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                _transferUuidFlow.emit(uploadManager.getLastUpload()?.uuid)
            }.onFailure {
                SentryLog.e(TAG, "Failed to retrieve the last upload", it)
                _transferUuidFlow.emit(null)
            }
        }
    }

    fun cancelUpload() {
        uploadWorkerScheduler.cancelWork()

        viewModelScope.launch(ioDispatcher) {
            uploadManager.getLastUpload()?.let {
                uploadManager.removeUploadSession(it.uuid)
            }
        }
    }

    companion object {
        private val TAG = UploadProgressViewModel::class.java.simpleName
    }
}
