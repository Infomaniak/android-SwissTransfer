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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import android.net.Uri
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.RemoteUploadFile
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadFileSession
import com.infomaniak.multiplatform_swisstransfer.common.utils.mapToList
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferOptionState
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferOptionsCallbacks
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportFilesViewModel @Inject constructor(
    private val appSettingsManager: AppSettingsManager,
    private val savedStateHandle: SavedStateHandle,
    private val importationFilesManager: ImportationFilesManager,
    private val uploadManager: UploadManager,
    private val uploadWorkerScheduler: UploadWorker.Scheduler,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _sendActionResult = MutableStateFlow<SendActionResult?>(null)
    val sendActionResult = _sendActionResult.asStateFlow()

    @OptIn(FlowPreview::class)
    val importedFilesDebounced = importationFilesManager.importedFiles
        .debounce(50)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList(),
        )

    val failedFiles = importationFilesManager.failedFiles
    val filesToImportCount = importationFilesManager.filesToImportCount
    val currentSessionFilesCount = importationFilesManager.currentSessionFilesCount

    private var isFirstViewModelCreation: Boolean
        get() = savedStateHandle.get<Boolean>(IS_VIEW_MODEL_RESTORED_KEY) ?: true
        set(value) {
            savedStateHandle[IS_VIEW_MODEL_RESTORED_KEY] = value
        }

    init {
        viewModelScope.launch(ioDispatcher) {
            if (isFirstViewModelCreation) {
                isFirstViewModelCreation = false
                // Remove old imported files in case it would've crashed or similar to start with a clean slate. This is required
                // for already imported files restoration to not pick up old files in some extreme cases.
                removeOldData()
            } else {
                importationFilesManager.restoreAlreadyImportedFiles()
            }

            importationFilesManager.continuouslyCopyPickedFilesToLocalStorage()
        }
    }

    fun importFiles(uris: List<Uri>) {
        viewModelScope.launch(ioDispatcher) {
            importationFilesManager.importFiles(uris)
        }
    }

    fun removeFileByUid(uid: String) {
        viewModelScope.launch(ioDispatcher) {
            importationFilesManager.removeFileByUid(uid)
        }
    }

    fun sendTransfer() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val uuid = uploadManager.createAndGetUpload(generateNewUploadSession()).uuid
                uploadWorkerScheduler.scheduleWork(uuid)
                _sendActionResult.update {
                    val totalSize = importationFilesManager.importedFiles.value.sumOf { it.fileSize }
                    SendActionResult.Success(totalSize)
                }
            }.onFailure { exception ->
                SentryLog.e(TAG, "Failed to start the upload", exception)
                _sendActionResult.update { SendActionResult.Failure }
            }
        }
    }

    private suspend fun removeOldData() {
        importationFilesManager.removeLocalCopyFolder()
        uploadManager.removeAllUploadSession()
    }

    private fun generateNewUploadSession(): NewUploadSession {
        return NewUploadSession(
            duration = selectedValidityPeriodOption.value.apiValue,
            authorEmail = "",
            password = if (selectedPasswordOption.value == PasswordTransferOption.ACTIVATED) transferPassword else NO_PASSWORD,
            message = _transferMessage,
            numberOfDownload = selectedDownloadLimitOption.value.apiValue,
            language = selectedLanguageOption.value.apiValue,
            recipientsEmails = emptyList(),
            files = importationFilesManager.importedFiles.value.mapToList { fileUi ->
                object : UploadFileSession {
                    override val path: String? = null
                    override val localPath: String = fileUi.localPath ?: ""
                    override val mimeType: String = fileUi.mimeType ?: ""
                    override val name: String = fileUi.fileName
                    override val remoteUploadFile: RemoteUploadFile? = null
                    override val size: Long = fileUi.fileSize
                }
            }
        )
    }

    //region Transfer Type
    val selectedTransferType = savedStateHandle.getStateFlow(SELECTED_TRANSFER_TYPE, TransferTypeUi.QR_CODE)

    fun selectTransferType(type: TransferTypeUi) {
        savedStateHandle[SELECTED_TRANSFER_TYPE] = type
        viewModelScope.launch(ioDispatcher) { appSettingsManager.setLastTransferType(type.dbValue) }
    }
    //endregion

    //region Transfer Options
    val selectedValidityPeriodOption = savedStateHandle.getStateFlow(
        key = SELECTED_VALIDITY_PERIOD_KEY,
        initialValue = ValidityPeriodOption.THIRTY
    )

    val selectedDownloadLimitOption = savedStateHandle.getStateFlow(
        key = SELECTED_DOWNLOAD_LIMIT_KEY,
        initialValue = DownloadLimitOption.TWO_HUNDRED_FIFTY,
    )

    val selectedPasswordOption = savedStateHandle.getStateFlow(
        key = SELECTED_PASSWORD_OPTION_KEY,
        initialValue = PasswordTransferOption.NONE,
    )

    val selectedLanguageOption = savedStateHandle.getStateFlow(
        key = SELECTED_LANGUAGE_KEY,
        initialValue = EmailLanguageOption.FRENCH,
    )

    private fun selectTransferValidityPeriod(validityPeriodOption: ValidityPeriodOption) {
        savedStateHandle[SELECTED_VALIDITY_PERIOD_KEY] = validityPeriodOption
    }

    private fun selectTransferDownloadLimit(downloadLimit: DownloadLimitOption) {
        savedStateHandle[SELECTED_DOWNLOAD_LIMIT_KEY] = downloadLimit
    }

    private fun selectTransferPasswordOption(passwordOption: PasswordTransferOption) {
        savedStateHandle[SELECTED_PASSWORD_OPTION_KEY] = passwordOption
    }

    private fun selectTransferLanguage(language: EmailLanguageOption) {
        savedStateHandle[SELECTED_LANGUAGE_KEY] = language
    }

    fun initTransferOptionsValues() {
        viewModelScope.launch(ioDispatcher) {
            appSettingsManager.getAppSettings()?.let {
                selectTransferValidityPeriod(it.validityPeriod.toTransferOption())
                selectTransferDownloadLimit(it.downloadLimit.toTransferOption())
                selectTransferLanguage(it.emailLanguage.toTransferOption())
                selectTransferType(it.lastTransferType.toTransferTypeUi())
            } ?: run {
                SentryLog.e(TAG, "AppSettings is null in ImportFilesScreen, it should not happened")
                selectTransferValidityPeriod(ValidityPeriodOption.THIRTY)
                selectTransferDownloadLimit(DownloadLimitOption.TWO_HUNDRED_FIFTY)
                selectTransferLanguage(EmailLanguageOption.FRENCH)
            }
        }
    }

    fun getTransferOptionsCallbacks(transferOptionsStates: () -> List<TransferOptionState>): TransferOptionsCallbacks {
        return TransferOptionsCallbacks(
            transferOptionsStates = transferOptionsStates,
            onTransferOptionValueSelected = ::onTransferOptionValueSelected,
            password = GetSetCallbacks(
                get = { transferPassword },
                set = { transferPassword = it },
            ),
            isPasswordValid = { isPasswordValid },
        )
    }

    private fun onTransferOptionValueSelected(option: SettingOption) {
        when (option) {
            is ValidityPeriodOption -> selectTransferValidityPeriod(option)
            is DownloadLimitOption -> selectTransferDownloadLimit(option)
            is PasswordTransferOption -> selectTransferPasswordOption(option)
            is EmailLanguageOption -> selectTransferLanguage(option)
        }
    }
    //endregion

    //region Password
    private var transferPassword by mutableStateOf("")

    private val isPasswordValid by derivedStateOf { transferPassword.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH }
    //endregion

    //region Transfer Message
    private var _transferMessage by mutableStateOf("")
    val transferMessage = GetSetCallbacks(get = { _transferMessage }, set = { _transferMessage = it })
    //endregion

    sealed class SendActionResult {
        data class Success(val totalSize: Long) : SendActionResult()
        data object Failure : SendActionResult()
    }

    companion object {
        private val TAG = ImportFilesViewModel::class.java.simpleName

        private const val PASSWORD_MIN_LENGTH = 6
        private const val PASSWORD_MAX_LENGTH = 25

        private const val IS_VIEW_MODEL_RESTORED_KEY = "IS_VIEW_MODEL_RESTORED_KEY"
        private const val SELECTED_TRANSFER_TYPE = "SELECTED_TRANSFER_TYPE"
        private const val SELECTED_VALIDITY_PERIOD_KEY = "SELECTED_VALIDITY_PERIOD_KEY"
        private const val SELECTED_DOWNLOAD_LIMIT_KEY = "SELECTED_DOWNLOAD_LIMIT_KEY"
        private const val SELECTED_PASSWORD_OPTION_KEY = "SELECTED_PASSWORD_OPTION_KEY"
        private const val SELECTED_LANGUAGE_KEY = "SELECTED_TRANSFER_LANGUAGE_KEY"

        private const val NO_PASSWORD = ""
    }
}
