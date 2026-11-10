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
        val resendCode: suspend () -> Boolean,
        val lastSentCodeExpiration: TimeMark?,
        val lastIssue: DismissableIssue?
    ) : ValidateEmailUiStatus

    data object SendingCode : ValidateEmailUiStatus

    data class DismissableIssue(
        val cause: Cause,
        val dismiss: () -> Unit,
    ) {
        sealed interface Cause {
            data object UnknownError : Cause
            data object NoNetwork : Cause
            data object InvalidVerificationCode : Cause
            data object VerificationCodeExpired : Cause
            data object TooManyAttempts : Cause
        }
    }

}
