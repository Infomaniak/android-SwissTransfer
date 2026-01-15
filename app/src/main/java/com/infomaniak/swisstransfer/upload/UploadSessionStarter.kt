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
package com.infomaniak.swisstransfer.upload

import com.infomaniak.core.appintegrity.AppIntegrityManager
import com.infomaniak.core.appintegrity.AppIntegrityManager.Companion.APP_INTEGRITY_MANAGER_TAG
import com.infomaniak.core.appintegrity.exceptions.IntegrityException
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadDestination
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.ContainerErrorsException
import com.infomaniak.swisstransfer.BuildConfig
import javax.inject.Inject
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

class UploadSessionStarter @Inject constructor(
    private val appIntegrityManager: AppIntegrityManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: InMemoryUploadManager,
) {

    suspend fun tryStarting(request: StartUploadRequest): Result {
        val uploadSessionRequest = request.params.toUploadSessionRequest(
            filesMetadata = request.files.map { it.toFileUploadMetaData() }
        )
        return tryStarting(uploadSessionRequest)
    }

    private suspend fun tryStarting(sessionRequest: UploadSessionRequest): Result = runCatching {
        val destination = uploadManager.startUploadSession(
            request = sessionRequest,
            attestationHeaderName = AppIntegrityManager.ATTESTATION_TOKEN_HEADER,
            generateAttestationToken = { fetchNewAttestationToken() },
        )
        Result.Success(sessionRequest, destination)
    }.cancellable().getOrElse { t ->
        SentryLog.w(TAG, "Throwable while trying to start the upload session", t)
        when (t) {
            is NetworkException, is KmpNetworkException -> Result.NetworkIssue
            is IntegrityException -> Result.AppIntegrityIssue
            is ContainerErrorsException.EmailValidationRequired -> Result.EmailValidationRequired
            is ContainerErrorsException.DomainBlockedException -> Result.RestrictedLocation
            else -> {
                SentryLog.e(TAG, "Unexpected issue while starting the upload session", t)
                Result.OtherIssue(t)
            }
        }
    }

    //region App Integrity
    @Throws(IntegrityException::class)
    private suspend inline fun fetchNewAttestationToken(): String {
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

    sealed interface Result {

        data class Success(
            val request: UploadSessionRequest,
            val destination: UploadDestination
        ) : Result

        data object EmailValidationRequired : Result

        sealed interface Issue : Result
        data object RestrictedLocation : Issue
        data object AppIntegrityIssue : Issue
        data object NetworkIssue : Issue
        data class OtherIssue(val t: Throwable) : Issue
    }
}

private const val TAG = "UploadSessionStarter"
