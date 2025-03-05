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

import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi

sealed interface UploadState {

    /**
     * This is not part of the [UploadState] hierarchy, but is used in its entries,
     * so the UI can display relevant metadata.
     */
    data class Info(
        val authorEmail: String,
        val totalSize: Long,
        val type: TransferTypeUi
    )

    data class Ongoing(
        val uploadedBytes: Long,
        val status: Status,
        val info: Info,
    ) : UploadState {

        sealed interface Status {
            data object Initializing : Status
            data object InProgress : Status
            data object WaitingForInternet : Status
        }
    }

    data class Complete(
        val transferType: TransferTypeUi,
        val transferUuid: String,
        val transferUrl: String,
    ) : UploadState

    sealed interface Retry : UploadState {
        val info: Info
        data class EmailValidationRequired(override val info: Info) : Retry
        data class NetworkIssue(override val info: Info) : Retry
        data class OtherIssue(override val info: Info, val t: Throwable) : Retry
    }

    sealed interface Failure : UploadState {
        data object RestrictedLocation : Failure
        data object FilesDisappeared : Failure
        data object AppIntegrityIssue : Failure
    }
}
