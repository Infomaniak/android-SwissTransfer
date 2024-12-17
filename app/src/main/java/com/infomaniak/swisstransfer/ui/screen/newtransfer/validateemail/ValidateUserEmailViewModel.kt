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
import com.infomaniak.multiplatform_swisstransfer.network.models.upload.request.VerifyEmailCodeBody
import com.infomaniak.multiplatform_swisstransfer.network.repositories.UploadRepository
import com.infomaniak.swisstransfer.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ValidateUserEmailViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val uploadManager: UploadManager
) : ViewModel() {

    private val uploadRepository: UploadRepository by lazy { UploadRepository() }

    private val _uiState = MutableStateFlow(ValidateEmailUiState.Default)
    val uiState = _uiState.asStateFlow()

    fun validateEmailWithOtpCode(email: String, otpCode: String, onSuccess: () -> Unit, onUnknownError: () -> Unit) {
        viewModelScope.launch(ioDispatcher) {
            _uiState.value = ValidateEmailUiState.Loading

            runCatching {
                uploadRepository.verifyEmailCode(VerifyEmailCodeBody(otpCode, email))
            }.onFailure {
                when (it) {
                    is EmailValidationException.InvalidPasswordException -> _uiState.value = ValidateEmailUiState.Error
                    else -> onUnknownError()
                }
            }.onSuccess { token ->
                // TODO: Save token for this email in DB

                onSuccess()
                _uiState.value = ValidateEmailUiState.Default
            }
        }
    }

    fun resetErrorState() {
        _uiState.compareAndSet(ValidateEmailUiState.Error, ValidateEmailUiState.Default)
    }

    fun resendEmailCode(email: String) {
        viewModelScope.launch(ioDispatcher) {
            uploadManager.resendEmailCode(email)
        }
    }

    enum class ValidateEmailUiState {
        Default, Loading, Error,
    }
}
