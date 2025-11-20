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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.cancellable
import com.infomaniak.core.ui.compose.basics.CallableState
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.EmailValidationException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

@HiltViewModel
class ValidateUserEmailViewModel @Inject constructor(
    private val uploadManager: InMemoryUploadManager
) : ViewModel() {

    val validationRequests = CallableState<ValidationRequest>()
    val resendEmailReq = CallableState<String>()
    val resetErrorReq = CallableState<Unit>()

    private val _uiState = MutableStateFlow(ValidateEmailUiState.Default)
    val uiState: StateFlow<ValidateEmailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch { run() }
    }

    private fun updateState(newState: ValidateEmailUiState) {
        _uiState.value = newState
    }

    private suspend fun run() {
        var lastIssue: Result.Issue? = null
        repeatWhileActive {
            val request: ValidationRequest = raceOf({
                validationRequests.awaitOneCall()
            }, {
                repeatWhileActive {
                    val email = resendEmailReq.awaitOneCall()
                    uploadManager.resendEmailCode(email)
                }
            }, {
                lastIssue ?: awaitCancellation()
                resetErrorReq.awaitOneCall()
                updateState(ValidateEmailUiState.Default)
                awaitCancellation()
            })
            updateState(ValidateEmailUiState.Loading)
            val result = tryValidating(email = request.email, otpCode = request.otpCode)
            val newState: ValidateEmailUiState = when (result) {
                Result.InvalidVerificationCode -> ValidateEmailUiState.InvalidVerificationCode
                Result.NoNetwork -> ValidateEmailUiState.NoNetwork
                Result.UnknownError -> ValidateEmailUiState.UnknownError
                Result.Success -> awaitCancellation()
            }
            lastIssue = result as? Result.Issue
            updateState(newState)
        }
    }

    enum class ValidateEmailUiState {
        Default, Loading, InvalidVerificationCode, UnknownError, NoNetwork
    }

    data class ValidationRequest(val email: String, val otpCode: String)

    private sealed interface Result {
        data object Success : Result
        sealed interface Issue : Result
        data object UnknownError : Issue
        data object NoNetwork : Issue
        data object InvalidVerificationCode : Issue
    }

    private suspend fun tryValidating(email: String, otpCode: String): Result = runCatching {
        val token = uploadManager.verifyEmailCode(otpCode, email)
        uploadManager.saveAuthorEmailToken(authorEmail = email, emailToken = token.token)
        UploadForegroundService.retry()
        Result.Success
    }.cancellable().getOrElse { t ->
        when (t) {
            is EmailValidationException.InvalidPasswordException -> Result.InvalidVerificationCode
            is NetworkException -> Result.NoNetwork
            else -> Result.UnknownError
        }
    }
}
