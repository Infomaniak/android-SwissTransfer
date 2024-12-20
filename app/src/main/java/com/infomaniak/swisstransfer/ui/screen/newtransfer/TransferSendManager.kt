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
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.scopes.ViewModelScoped
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

    // TODO: Merge these two UI states in a single one for the whole flow of logic
    private val _sendStatus = MutableStateFlow<SendStatus>(SendStatus.Initial)
    val sendStatus = _sendStatus.asStateFlow()

    suspend fun sendNewTransfer(newUploadSession: NewUploadSession) {
        val uploadSession = uploadManager.createAndGetUpload(newUploadSession)
        sendTransfer(uploadSession.uuid)
    }

    suspend fun resendLastTransfer() {
        val uploadSessionUuid = uploadManager.getLastUpload()?.uuid ?: run {
            SentryLog.e(TAG, "No last upload found")
            return
        }
        sendTransfer(uploadSessionUuid)
    }

    private suspend fun sendTransfer(uploadSessionUuid: String) {
        _sendStatus.value = SendStatus.Pending

        withIntegrityToken(
            onSuccess = { attestationToken ->
                runCatching {
                    uploadManager.initUploadSession(
                        attestationHeaderName = AppIntegrityManager.ATTESTATION_TOKEN_HEADER,
                        attestationToken = attestationToken,
                    )!! // TODO: Handle ContainerErrorsException here
                    uploadWorkerScheduler.scheduleWork(uploadSessionUuid)
                    _sendStatus.update {
                        val totalSize = importationFilesManager.importedFiles.value.sumOf { it.fileSize }
                        SendStatus.Success(totalSize)
                    }
                }.onFailure { exception ->
                    SentryLog.e(TAG, "Failed to start the upload", exception)
                    _sendStatus.update { SendStatus.Failure }
                }
            },
            onRefused = { _sendStatus.update { SendStatus.Refused } },
        )
    }

    //region App Integrity
    private suspend inline fun withIntegrityToken(onSuccess: (attestationToken: String) -> Unit, onRefused: () -> Unit = {}) {
        var attestationToken: String? = null

        coroutineScope {
            appIntegrityManager.getChallenge(
                onSuccess = { launch { attestationToken = requestAppIntegrityToken(appIntegrityManager) } },
                onFailure = {},
            )
        }

        attestationToken?.let(onSuccess) ?: onRefused()
    }

    private suspend fun requestAppIntegrityToken(appIntegrityManager: AppIntegrityManager): String? {
        var attestationToken: String? = null

        coroutineScope {
            appIntegrityManager.requestClassicIntegrityVerdictToken(
                onSuccess = { token ->
                    SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "request for app integrity token successful")
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
                SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "API verdict check")
                Log.i(APP_INTEGRITY_MANAGER_TAG, "getApiIntegrityVerdict: $attestationToken")
                token = attestationToken
            },
            onFailure = {},
        )

        return token
    }
    //endregion

    fun resetSendStatus() {
        _sendStatus.value = SendStatus.Initial
    }

    sealed class SendStatus {
        data object Initial : SendStatus()
        data object Pending : SendStatus()
        data class Success(val totalSize: Long) : SendStatus()
        data object Refused : SendStatus()
        data object Failure : SendStatus()
    }

    companion object {
        private val TAG = TransferSendManager::class.java.simpleName
    }
}
