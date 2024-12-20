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

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.network.NetworkAvailability
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.di.MainDispatcher
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferSendManager
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UploadProgressViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
    private val uploadManager: UploadManager,
    private val transferSendManager: TransferSendManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val isNetworkAvailable = NetworkAvailability(appContext).isNetworkAvailable
        .onEach { SentryLog.d("Internet availability", if (it) "Available" else "Unavailable") }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true,
        )

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
        initialValue = UploadWorker.UploadProgressUiState.Default(),
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
            runCatching {
                val uploadSession = uploadManager.getLastUpload()
                if (uploadSession == null) {
                    uploadManager.removeAllUploadSession()
                } else {
                    uploadManager.cancelUploadSession(uploadSession.uuid)
                }
            }.onFailure { exception ->
                SentryLog.e(TAG, "Failure on cancel upload", exception)
            }
        }
    }

    fun removeAllUploadSession(onCompletion: () -> Unit) {
        viewModelScope.launch(ioDispatcher) {
            uploadManager.removeAllUploadSession()
            withContext(mainDispatcher) { onCompletion() }
        }
    }

    fun resendLastTransfer(onCompletion: () -> Unit) {
        viewModelScope.launch(ioDispatcher) {
            transferSendManager.resendLastTransfer()
            withContext(mainDispatcher) { onCompletion() }
        }
    }

    companion object {
        private val TAG = UploadProgressViewModel::class.java.simpleName
    }
}
