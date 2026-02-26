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

import com.infomaniak.core.appintegrity.exceptions.NetworkException
import com.infomaniak.core.common.Xor
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.UploadV2Manager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.ContainerErrorsException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException as KmpNetworkException

class UploadSessionStarterV2(
    private val uploadManager: UploadV2Manager,
) : UploadSessionStarter() {

    override suspend fun tryStarting(sessionRequest: UploadSessionRequest): Result = runCatching {
        val transfer = uploadManager.prepareTransfer(sessionRequest)
        Result.Success(sessionRequest, Xor.Second(transfer))
    }.cancellable().getOrElse { t ->
        SentryLog.w(TAG, "Throwable while trying to start the upload session", t)
        when (t) {
            is NetworkException, is KmpNetworkException -> Result.NetworkIssue
            // is IntegrityException -> Result.AppIntegrityIssue
            // is ContainerErrorsException.EmailValidationRequired -> Result.EmailValidationRequired
            is ContainerErrorsException.DomainBlockedException -> Result.RestrictedLocation
            else -> {
                SentryLog.e(TAG, "Unexpected issue while starting the upload session", t)
                Result.OtherIssue(t)
            }
        }
    }
}

private const val TAG = "UploadSessionStarterV2"
