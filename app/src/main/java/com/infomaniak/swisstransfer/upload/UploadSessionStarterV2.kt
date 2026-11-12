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
import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.UploadV2Manager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.UploadErrorsException
import com.infomaniak.swisstransfer.BuildConfig
import kotlin.uuid.ExperimentalUuidApi
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

class UploadSessionStarterV2(
    private val appIntegrityManager: AppIntegrityManager,
    private val uploadManager: UploadV2Manager,
) : UploadSessionStarter() {

    override suspend fun tryStarting(sessionRequest: UploadSessionRequest): Result = runCatching {
        val transfer = uploadManager.prepareTransfer(
            request = sessionRequest,
            attestationTokenGenerator = { targetUrl -> fetchNewAttestationToken(targetUrl) }
        )
        Result.Success(sessionRequest, transfer)
    }.cancellable().recoverCatching { t ->
        SentryLog.w(TAG, "Throwable while trying to start the upload session", t)
        when (t) {
            is AppIntegrityException -> Result.AppIntegrityIssue(t.showRemediationDialog)
            is UploadErrorsException.InvalidAttestationTokenException -> Result.AppIntegrityIssue(showRemediationDialog = null)
            is UploadErrorsException.EmailValidationRequired -> {
                uploadManager.prepareEmailValidationRequest(sessionRequest.authorEmail)
                Result.EmailValidationRequired
            }
            is UploadErrorsException.CountryBlocked -> Result.RestrictedLocation
            is UploadErrorsException.AccessDenied -> throw t // No special handling for now.
            else -> throw t
        }
    }.getOrElse { t ->
        when (t) {
            is NetworkException, is KmpNetworkException -> Result.NetworkIssue
            else -> {
                SentryLog.e(TAG, "Unexpected issue while starting the upload session", t)
                Result.OtherIssue(t)
            }
        }
    }

    //region App Integrity
    @OptIn(ExperimentalUuidApi::class)
    @Throws(AppIntegrityException::class)
    private suspend inline fun fetchNewAttestationToken(targetUrl: String): String {
        val challenge = appIntegrityManager.getChallenge()

        val attestationToken = appIntegrityManager.requestAttestationToken(
            challenge = challenge,
            packageName = BuildConfig.APPLICATION_ID,
            targetUrl = targetUrl,
        )
        SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "Successful API verdict")

        return attestationToken
    }
    //endregion
}

private const val TAG = "UploadSessionStarterV2"
