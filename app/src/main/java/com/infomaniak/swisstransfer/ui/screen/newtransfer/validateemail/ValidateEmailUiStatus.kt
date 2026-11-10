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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail

import kotlin.time.TimeMark

sealed interface ValidateEmailUiStatus {

    /**
     * @property lastSentCodeExpiration is null when a re-send code request is ongoing.
     */
    data class WaitingForCode(
        val onValidationRequest: (otp: String) -> Unit,
        val onCodeResendRequest: () -> Unit,
        val lastSentCodeExpiration: TimeMark?,
        val lastIssue: Issue?
    ) : ValidateEmailUiStatus

    data object SendingCode : ValidateEmailUiStatus

    sealed interface Issue {
        data object UnknownError : Issue
        data object NoNetwork : Issue
        data object InvalidVerificationCode : Issue
        data object VerificationCodeExpired : Issue
        data object TooManyAttempts : Issue
    }
}
