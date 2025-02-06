/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024 Infomaniak Network SA
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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.EmailValidationException
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferSendManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ValidateUserEmailViewModel @Inject constructor(
    private val uploadManager: UploadManager,
    private val transferSendManager: TransferSendManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ValidateEmailUiState.Default)
    val uiState = _uiState.asStateFlow()

    val sendStatus by transferSendManager::sendStatus

    fun validateEmailWithOtpCode(email: String, otpCode: String) {
        viewModelScope.launch {
            _uiState.value = ValidateEmailUiState.Loading

            runCatching {
                uploadManager.verifyEmailCode(otpCode, email)
            }.onFailure {
                _uiState.value = when (it) {
                    is EmailValidationException.InvalidPasswordException -> ValidateEmailUiState.InvalidVerificationCode
                    is NetworkException -> ValidateEmailUiState.NoNetwork
                    else -> ValidateEmailUiState.UnknownError
                }
            }.onSuccess { token ->
                uploadManager.updateAuthorEmailToken(email, token.token)
                transferSendManager.sendLastTransfer()
            }
        }
    }

    fun resetErrorState() {
        _uiState.compareAndSet(ValidateEmailUiState.InvalidVerificationCode, ValidateEmailUiState.Default)
    }

    fun resendEmailCode(email: String) {
        viewModelScope.launch {
            uploadManager.resendEmailCode(email)
        }
    }

    suspend fun removeAllUploadSession() {
        uploadManager.removeAllUploadSession()
    }

    enum class ValidateEmailUiState {
        Default, Loading, InvalidVerificationCode, UnknownError, NoNetwork
    }
}
