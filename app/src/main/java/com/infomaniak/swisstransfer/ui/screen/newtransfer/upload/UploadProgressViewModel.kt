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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.core.network.NetworkAvailability
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.di.MainDispatcher
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferSendManager
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import com.infomaniak.swisstransfer.upload.UploadSessionStarter
import com.infomaniak.swisstransfer.upload.UploadState
import com.infomaniak.swisstransfer.upload.toFileUploadMetaData
import com.infomaniak.swisstransfer.upload.toUploadSessionRequest
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

@HiltViewModel
class UploadProgressViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
    private val uploadManager: UploadManager,
    private val uploadSessionStarter: UploadSessionStarter,
    private val transferSendManager: TransferSendManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val sendStatus by transferSendManager::sendStatus

    val isNetworkAvailable = NetworkAvailability(appContext).isNetworkAvailable
        .onEach { SentryLog.d("Internet availability", if (it) "Available" else "Unavailable") }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true,
        )

    // This stateflow is required in order to start with `initialValue = false`. We need it to block the initialization of the
    // upload while we wait for a network connection. If we start with `initialValue = true`, the initialization will go through
    // because of the initial value.
    private val uploadInitializationIsNetworkAvailable = NetworkAvailability(appContext).isNetworkAvailable
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false,
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

    fun cancelUpload(onFailedCancellation: () -> Unit) {
        uploadWorkerScheduler.cancelWork()

        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val uploadSession = uploadManager.getLastUpload()
                if (uploadSession == null) {
                    uploadManager.removeAllUploadSession()
                } else {
                    // We can cancel during the app integrity check, before any remote container creation. In this case, no need
                    // to cancel any remote upload session.
                    if (uploadSession.remoteContainer != null) uploadManager.cancelUploadSession(uploadSession.uuid)
                }
            }.onFailure { exception ->
                if (exception is CancellationException) throw exception

                SentryLog.e(TAG, "Failure on cancel upload", exception)
                onFailedCancellation()
            }
        }
    }

    fun removeAllUploadSession(onCompletion: () -> Unit) {
        viewModelScope.launch(ioDispatcher) {
            uploadManager.removeAllUploadSession()
            withContext(mainDispatcher) { onCompletion() }
        }
    }

    fun initializeLastTransfer() {
        viewModelScope.launch {
            if (uploadWorkerScheduler.hasAlreadyBeenScheduled()) return@launch

            uploadInitializationIsNetworkAvailable.first { isNetworkAvailable -> isNetworkAvailable }

            SentryLog.i(TAG, "Initializing the upload")
            transferSendManager.sendLastTransfer()
        }
    }

    fun resetSendStatus() {
        transferSendManager.resetSendStatus()
    }

    private val retryButton = CallableState<Unit>()

    init {
        viewModelScope.launch { run() }
    }

    private suspend fun run(): Nothing {
        do {
            val pendingUpload = UploadForegroundService.state.filterIsInstance<UploadState.Pending>().first()
            val uploadSessionRequest = pendingUpload.params.toUploadSessionRequest(
                filesMetadata = pendingUpload.files.map { it.toFileUploadMetaData() }
            )
            val result = uploadSessionStarter.tryStarting(uploadSessionRequest)
            when (result) {
                UploadSessionStarter.Result.EmailValidationRequired -> TODO()
                UploadSessionStarter.Result.AppIntegrityIssue -> TODO()
                UploadSessionStarter.Result.NetworkIssue -> TODO()
                is UploadSessionStarter.Result.OtherIssue -> TODO()
                UploadSessionStarter.Result.RestrictedLocation -> TODO()
                is UploadSessionStarter.Result.Success -> TODO()
            }
            retryButton.awaitOneCall()
        } while (true)
    }

    companion object {
        private val TAG = UploadProgressViewModel::class.java.simpleName
    }
}
