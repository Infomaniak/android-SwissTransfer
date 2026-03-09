/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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

import com.infomaniak.core.common.Xor
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.transfers.v2.Transfer
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadDestination
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest

abstract class UploadSessionStarter {

    suspend fun tryStarting(request: StartUploadRequest): Result {
        val uploadSessionRequest = request.params.toUploadSessionRequest(
            filesMetadata = request.files.map { it.toFileUploadMetaData() }
        )
        return tryStarting(uploadSessionRequest)
    }

    protected abstract suspend fun tryStarting(sessionRequest: UploadSessionRequest): Result

    sealed interface Result {

        data class Success(
            val request: UploadSessionRequest,
            val destination: Xor<UploadDestination, Transfer>,
        ) : Result

        data object EmailValidationRequired : Result

        sealed interface Issue : Result
        data object RestrictedLocation : Issue
        data object AppIntegrityIssue : Issue
        data object NetworkIssue : Issue
        data class OtherIssue(val t: Throwable) : Issue
    }
}
