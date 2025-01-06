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

import com.infomaniak.core2.appintegrity.AppIntegrityManager
import com.infomaniak.core2.appintegrity.AppIntegrityManager.Companion.APP_INTEGRITY_MANAGER_TAG
import com.infomaniak.core2.appintegrity.exceptions.IntegrityException
import com.infomaniak.core2.appintegrity.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

@ViewModelScoped
class TransferSendManager @Inject constructor(
    private val appIntegrityManager: AppIntegrityManager,
    private val importationFilesManager: ImportationFilesManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: UploadManager,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
) {

    private val _sendStatus = MutableStateFlow<SendStatus>(SendStatus.Initial)
    val sendStatus = _sendStatus.asStateFlow()

    suspend fun sendNewTransfer(newUploadSession: NewUploadSession) {
        runCatching {
            // When clicking the "Send" button, a new session is created.
            // If there is an error before reaching the UploadProgressScreen, we stay in ImportFilesScreen.
            // Every time we'll click the "Send" button again, a new session will be created.
            // So we'll have multiple UploadSession in Realm. We don't want that. We only want the last session.
            // So before creating the new session, we need to remove the previous failed ones.
            uploadManager.removeAllUploadSession()

            val uploadSession = uploadManager.createAndGetUpload(newUploadSession)
            sendTransfer(uploadSession.uuid)
        }.onFailure { exception ->
            if (exception !is NetworkException && exception !is KmpNetworkException) {
                SentryLog.e(TAG, "Failure on sendNewTransfer", exception)
            }
            _sendStatus.update { SendStatus.Failure }
        }
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

        runCatching {
            val attestationToken = getAttestationToken()
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
            if (exception !is NetworkException && exception !is KmpNetworkException) {
                SentryLog.e(TAG, "Failed to start the upload", exception)
            }
            if (exception is IntegrityException) {
                _sendStatus.update { SendStatus.Refused }
            } else {
                _sendStatus.update { SendStatus.Failure }
            }
        }
    }

    //region App Integrity
    /** Handle [IntegrityException] and all the different exceptions that can be thrown */
    private suspend inline fun getAttestationToken(): String {
        val challenge = appIntegrityManager.getChallenge()
        return requestAppIntegrityToken(challenge)
    }

    private suspend fun requestAppIntegrityToken(challenge: String): String {
        val token = appIntegrityManager.requestClassicIntegrityVerdictToken(challenge)
        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "request for app integrity token successful")
        return getApiIntegrityVerdict(appIntegrityManager, token)
    }

    private suspend fun getApiIntegrityVerdict(appIntegrityManager: AppIntegrityManager, appIntegrityToken: String): String {
        val token = appIntegrityManager.getApiIntegrityVerdict(
            integrityToken = appIntegrityToken,
            packageName = BuildConfig.APPLICATION_ID,
            targetUrl = sharedApiUrlCreator.createUploadContainerUrl,
        )

        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "API verdict check $token")

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
