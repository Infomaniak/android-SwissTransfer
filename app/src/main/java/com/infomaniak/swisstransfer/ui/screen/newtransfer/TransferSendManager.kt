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

import com.infomaniak.core.appintegrity.AppIntegrityManager
import com.infomaniak.core.appintegrity.AppIntegrityManager.Companion.APP_INTEGRITY_MANAGER_TAG
import com.infomaniak.core.appintegrity.exceptions.IntegrityException
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.ContainerErrorsException
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.utils.totalFileSize
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.scopes.ViewModelScoped
import io.sentry.Sentry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

@ViewModelScoped
class TransferSendManager @Inject constructor(
    private val appIntegrityManager: AppIntegrityManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: UploadManager,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
) {

    private val _sendStatus = MutableStateFlow<SendStatus>(SendStatus.Initial)
    val sendStatus = _sendStatus.asStateFlow()

    suspend fun sendLastTransfer() {
        val uploadSession = uploadManager.getLastUpload() ?: run {
            SentryLog.e(TAG, "No last upload found")
            return
        }
        sendTransfer(uploadSession)
    }

    private suspend fun sendTransfer(uploadSession: UploadSession) {
        _sendStatus.value = SendStatus.Pending

        runCatching {
            val attestationToken = getAttestationToken()
            uploadManager.initUploadSession(
                attestationHeaderName = AppIntegrityManager.ATTESTATION_TOKEN_HEADER,
                attestationToken = attestationToken,
            )!! // TODO: Handle ContainerErrorsException here
            uploadWorkerScheduler.scheduleWork(uploadSession.uuid)
            _sendStatus.update {
                SendStatus.Success(uploadSession.totalFileSize())
            }
        }.onFailure { exception ->
            SentryLog.w(TAG, "Exception during sendTransfer", exception)
            val status = when (exception) {
                is NetworkException, is KmpNetworkException -> SendStatus.NoNetwork
                is IntegrityException -> SendStatus.Refused
                is ContainerErrorsException.EmailValidationRequired -> SendStatus.RequireEmailValidation
                else -> {
                    reportToSentry(exception)
                    SendStatus.Failure
                }
            }
            _sendStatus.update { status }
        }
    }

    private fun reportToSentry(exception: Throwable) {
        Sentry.withScope { scope ->
            scope.setTag("exception", exception.message ?: "Unknown message")
            SentryLog.e(TAG, "Failed to start the upload", exception)
        }
    }

    //region App Integrity
    /** Don't forget to handle [IntegrityException] and all the different exceptions that can be thrown at call site*/
    private suspend inline fun getAttestationToken(): String {
        val challenge = appIntegrityManager.getChallenge()

        val appIntegrityToken = appIntegrityManager.requestClassicIntegrityVerdictToken(challenge)
        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "request for app integrity token successful")

        val attestationToken = appIntegrityManager.getApiIntegrityVerdict(
            integrityToken = appIntegrityToken,
            packageName = BuildConfig.APPLICATION_ID,
            targetUrl = sharedApiUrlCreator.createUploadContainerUrl,
        )
        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "Successful API verdict")

        return attestationToken
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
        data object NoNetwork : SendStatus()
        data object RequireEmailValidation : SendStatus()
    }

    companion object {
        private val TAG = TransferSendManager::class.java.simpleName
    }
}
