/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025-2026 Infomaniak Network SA
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
import com.infomaniak.core.appintegrity.exceptions.AppIntegrityException
import com.infomaniak.core.appintegrity.isRecoverable
import com.infomaniak.core.common.Xor
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.ContainerErrorsException
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

class UploadSessionStarterV1(
    private val appIntegrityManager: AppIntegrityManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val uploadManager: InMemoryUploadManager,
) : UploadSessionStarter() {

    override suspend fun tryStarting(sessionRequest: UploadSessionRequest): Result = runCatching {
        val destination = uploadManager.startUploadSession(
            request = sessionRequest,
            attestationHeaderName = AppIntegrityManager.ATTESTATION_TOKEN_HEADER,
            generateAttestationToken = { fetchNewAttestationToken() },
        )
        Result.Success(sessionRequest, Xor.First(destination))
    }.cancellable().getOrElse { t ->
        SentryLog.w(TAG, "Throwable while trying to start the upload session", t)
        when (t) {
            is com.infomaniak.core.appintegrity.exceptions.NetworkException, is KmpNetworkException -> Result.NetworkIssue
            is AppIntegrityException -> when {
                // Remediable through Play's Integrity dialog (fixed automatically or by guiding the user).
                t.showRemediationDialog != null -> Result.AppIntegrityRemediable(t.showRemediationDialog!!)
                // Not dialog-remediable, but transient/recoverable (network, Google server down, etc.): let the user retry.
                t.issue.isRecoverable() -> Result.AppIntegrityRecoverable
                else -> Result.AppIntegrityIssue
            }
            is ContainerErrorsException.EmailValidationRequired -> Result.EmailValidationRequired
            is ContainerErrorsException.DomainBlockedException -> Result.RestrictedLocation
            else -> {
                SentryLog.e(TAG, "Unexpected issue while starting the upload session", t)
                Result.OtherIssue(t)
            }
        }
    }

    //region App Integrity
    @Throws(AppIntegrityException::class)
    private suspend fun fetchNewAttestationToken(): String {
        val challenge = appIntegrityManager.getChallenge()
        val attestationToken = appIntegrityManager.requestAttestationToken(
            challenge = challenge,
            packageName = BuildConfig.APPLICATION_ID,
            targetUrl = sharedApiUrlCreator.createUploadContainerUrl,
        )
        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "Successful attestation token")
        return attestationToken
    }
    //endregion
}

private const val TAG = "UploadSessionStarter"
