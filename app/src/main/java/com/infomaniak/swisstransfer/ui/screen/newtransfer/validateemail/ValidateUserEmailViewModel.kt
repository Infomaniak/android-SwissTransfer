/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
@file:OptIn(ExperimentalSplittiesApi::class, ExperimentalCoroutinesApi::class)

package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.common.dynamicLazyMapOfSharedFlow
import com.infomaniak.core.common.flowForKey
import com.infomaniak.core.common.toTimeMark
import com.infomaniak.core.common.waitForValue
import com.infomaniak.multiplatform_swisstransfer.data.EmailValidation
import com.infomaniak.multiplatform_swisstransfer.managers.UploadV2Manager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.EmailValidationException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.ValidateEmailUiStatus.DismissableIssue
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformLatest
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant
import kotlin.time.TimeMark

@HiltViewModel
class ValidateUserEmailViewModel @Inject constructor(
    private val uploadManager: UploadV2Manager
) : ViewModel() {

    fun validationStatus(email: String): Flow<ValidateEmailUiStatus> = validationStatuses.flowForKey(email)

    val validationStatuses = viewModelScope.dynamicLazyMapOfSharedFlow(
        cacheManager = { _, _ -> delay(5.seconds) }
    ) { email: String ->
        validationStatusFor(email = email)
    }

    private fun EmailValidation.lastSentCodeExpiration(): TimeMark? {
        val expirationSeconds = lastSentCodeExpirationTimestampSeconds ?: return null
        return Instant.fromEpochSeconds(expirationSeconds).toTimeMark()
    }

    private fun validationStatusFor(email: String): Flow<ValidateEmailUiStatus>  {
        var lastIssue: DismissableIssue? = null
        val issueDismissals = Channel<Unit>()
        return uploadManager.emailValidationStatus(email = email).transformLatest { validationStatus ->
            if (validationStatus == null) {
                return@transformLatest // Should not happen since the email validation request shall be created before.
            }
            val lastSentCodeExpiration = validationStatus.lastSentCodeExpiration()
                ?: return@transformLatest // Already validated. Should exit.

            repeatWhileActive {
                val otpAsync = CompletableDeferred<String>()
                val codeResendRequests = Channel<CompletableDeferred<Boolean>>()
                val waitingForCode = ValidateEmailUiStatus.WaitingForCode(
                    onValidationRequest = otpAsync::complete,
                    resendCode = { waitForValue { codeResendRequests.trySend(it) } },
                    lastSentCodeExpiration = lastSentCodeExpiration,
                    lastIssue = lastIssue
                )
                emit(waitingForCode)
                val action: UserAction = raceOf(
                    { UserAction.ValidateWithOtp(otpAsync.await()) },
                    {
                        var sentCompletable: CompletableDeferred<Boolean>
                        while (true) {
                            sentCompletable = codeResendRequests.receive() // It's UI responsibility to respect the timer.
                            if (lastSentCodeExpiration.hasPassedNow()) break // Last code expired, accept re-sending user request.
                        }
                        UserAction.ResendCode(onSent = { sentCompletable.complete(it) })
                    },
                    {
                        issueDismissals.receive()
                        UserAction.DismissLastIssue
                    }
                )
                when (action) {
                    is UserAction.ResendCode -> return@repeatWhileActive run {
                        runCatching {
                            lastIssue = null
                            emit(waitingForCode.copy(lastSentCodeExpiration = null, resendCode = { false }, lastIssue = null))
                            val alreadyValidated = !uploadManager.resendValidationEmail(validationId = validationStatus.id)
                            if (alreadyValidated) return@transformLatest
                            action.onSent(true)
                        }.onFailure {
                            action.onSent(false)
                        }.cancellable().onFailure {
                            lastIssue = DismissableIssue(
                                cause = it.toEmailValidationIssueCause(),
                                dismiss = { issueDismissals.trySend(Unit) }
                            )
                        }
                    }
                    UserAction.DismissLastIssue -> return@repeatWhileActive run { lastIssue = null }
                    is UserAction.ValidateWithOtp -> Unit
                }
                emit(ValidateEmailUiStatus.SendingCode)
                lastIssue = DismissableIssue(
                    cause = tryValidating(email = email, otpCode = action.otpCode) ?: return@transformLatest,
                    dismiss = { issueDismissals.trySend(Unit) }
                )
            }
        }
    }

    private sealed interface UserAction {
        data class ValidateWithOtp(val otpCode: String) : UserAction
        data class ResendCode(val onSent: (succeeded: Boolean) -> Unit) : UserAction
        data object DismissLastIssue : UserAction
    }

    private suspend fun tryValidating(email: String, otpCode: String): DismissableIssue.Cause? = runCatching {
        uploadManager.validateEmailWithCode(email = email, code = otpCode)
        UploadForegroundService.retry()
        null
    }.cancellable().getOrElse { t -> t.toEmailValidationIssueCause() }

    private fun Throwable.toEmailValidationIssueCause(): DismissableIssue.Cause = when (this) {
        is EmailValidationException.InvalidCode -> DismissableIssue.Cause.InvalidVerificationCode
        is EmailValidationException.CodeExpired -> DismissableIssue.Cause.VerificationCodeExpired
        is EmailValidationException.TooManyAttempts -> DismissableIssue.Cause.TooManyAttempts
        is NetworkException -> DismissableIssue.Cause.NoNetwork
        else -> DismissableIssue.Cause.UnknownError
    }
}
