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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import android.util.Log
import com.infomaniak.core2.appintegrity.AppIntegrityManager
import com.infomaniak.core2.appintegrity.AppIntegrityManager.Companion.APP_INTEGRITY_MANAGER_TAG
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.AppIntegrityResult
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.SendActionResult
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class TransferSendManager @Inject constructor(
    private val appIntegrityManager: AppIntegrityManager,
    private val importationFilesManager: ImportationFilesManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: UploadManager,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
) {

    // TODO: Merge these two ui states in a single one for the whole flow of logic
    private val _sendActionResult = MutableStateFlow<SendActionResult?>(SendActionResult.NotStarted)
    val sendActionResult = _sendActionResult.asStateFlow()

    private val _integrityCheckResult = MutableStateFlow(AppIntegrityResult.Idle)
    val integrityCheckResult = _integrityCheckResult.asStateFlow()

    suspend fun sendTransfer(newUploadSession: NewUploadSession) {
        _integrityCheckResult.value = AppIntegrityResult.Ongoing

        withIntegrityToken(
            onSuccess = { attestationToken ->
                sendTransfer(newUploadSession, attestationToken)
            },
            onRefused = {
                _integrityCheckResult.value = AppIntegrityResult.Fail
            },
            onFailure = { exception ->
                if (exception !is CancellationException) {
                    SentryLog.e(TAG, "Integrity token received an exception", exception)
                } else {
                    SentryLog.i(TAG, "Integrity token received an exception", exception)
                }
                _sendActionResult.update { SendActionResult.Failure }
            }
        )
    }

    private suspend fun sendTransfer(newUploadSession: NewUploadSession, attestationToken: String) {
        _integrityCheckResult.value = AppIntegrityResult.Success
        _sendActionResult.update { SendActionResult.Pending }

        runCatching {
            val uuid = uploadManager.createAndGetUpload(newUploadSession).uuid
            uploadManager.initUploadSession(
                attestationHeaderName = AppIntegrityManager.ATTESTATION_TOKEN_HEADER,
                attestationToken = attestationToken,
            )!! // TODO: Handle ContainerErrorsException here
            uploadWorkerScheduler.scheduleWork(uuid)
            _sendActionResult.update {
                val totalSize = importationFilesManager.importedFiles.value.sumOf { it.fileSize }
                SendActionResult.Success(totalSize)
            }
        }.onFailure { exception ->
            SentryLog.e(TAG, "Failed to start the upload", exception)
            _sendActionResult.update { SendActionResult.Failure }
        }
    }

    //region App Integrity
    private suspend inline fun withIntegrityToken(
        onSuccess: (attestationToken: String) -> Unit,
        onRefused: () -> Unit = {},
        onFailure: (exception: Throwable) -> Unit = {},
    ) {
        runCatching {
            var attestationToken: String? = null

            coroutineScope {
                appIntegrityManager.getChallenge(
                    onSuccess = { launch { attestationToken = requestAppIntegrityToken(appIntegrityManager) } },
                    onFailure = {},
                )
            }

            attestationToken?.let(onSuccess) ?: onRefused()
        }.onFailure {
            onFailure.invoke(it)
        }
    }

    private suspend fun requestAppIntegrityToken(appIntegrityManager: AppIntegrityManager): String? {
        var attestationToken: String? = null

        coroutineScope {
            appIntegrityManager.requestClassicIntegrityVerdictToken(
                onSuccess = { token ->
                    SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "request for app integrity token successful $token")
                    launch { attestationToken = getApiIntegrityVerdict(appIntegrityManager, token) }
                },
                onFailure = {},
            )
        }

        return attestationToken
    }

    private suspend fun getApiIntegrityVerdict(appIntegrityManager: AppIntegrityManager, appIntegrityToken: String): String? {
        var token: String? = null

        appIntegrityManager.getApiIntegrityVerdict(
            integrityToken = appIntegrityToken,
            packageName = BuildConfig.APPLICATION_ID,
            targetUrl = sharedApiUrlCreator.createUploadContainerUrl,
            onSuccess = { attestationToken ->
                SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "Api verdict check")
                Log.i(APP_INTEGRITY_MANAGER_TAG, "getApiIntegrityVerdict: $attestationToken")
                token = attestationToken
            },
            onFailure = {},
        )

        return token
    }

    fun resetIntegrityCheckResult() {
        _integrityCheckResult.value = AppIntegrityResult.Idle
    }
    //endregion

    fun resetSendActionResult() {
        _sendActionResult.value = SendActionResult.NotStarted
    }

    companion object {
        private val TAG = TransferSendManager::class.java.simpleName
    }
}
