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
import com.infomaniak.core2.isValidEmail
import com.infomaniak.core2.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.RemoteUploadFile
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadFileSession
import com.infomaniak.multiplatform_swisstransfer.common.utils.mapToList
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.EmailTextFieldCallbacks
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferOptionState
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferOptionsCallbacks
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportFilesViewModel @Inject constructor(
    private val appSettingsManager: AppSettingsManager,
    private val savedStateHandle: SavedStateHandle,
    private val importationFilesManager: ImportationFilesManager,
    private val fileManager: FileManager,
    private val uploadManager: UploadManager,
    private val transferSendManager: TransferSendManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val sendStatus by transferSendManager::sendStatus

    @OptIn(FlowPreview::class)
    val importedFilesDebounced = importationFilesManager.importedFiles
        .debounce(50)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList(),
        )

    val failedFiles = importationFilesManager.failedFiles // TODO ? (unused)
    val filesToImportCount = importationFilesManager.filesToImportCount
    val currentSessionFilesCount = importationFilesManager.currentSessionFilesCount

    //region Transfer Author Email
    private var transferAuthorEmail by mutableStateOf("")
    private val isAuthorEmailInvalid by derivedStateOf { !transferAuthorEmail.isValidEmail() }
    //endregion

    //region Recipient Email
    private var recipientEmail by mutableStateOf("")
    private val isRecipientEmailInvalid by derivedStateOf { !recipientEmail.isValidEmail() }
    private var validatedRecipientsEmails by mutableStateOf<Set<String>>(emptySet())
    //endregion

    //region Transfer Message
    private var transferMessage by mutableStateOf("")
    val transferMessageCallbacks = GetSetCallbacks(get = { transferMessage }, set = { transferMessage = it })
    //endregion

    //region Password
    private var transferPassword by mutableStateOf("")
    private val isPasswordValid by derivedStateOf { transferPassword.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH }
    //endregion

    private var isFirstViewModelCreation: Boolean
        get() = savedStateHandle[IS_VIEW_MODEL_RESTORED_KEY] ?: true
        set(value) {
            savedStateHandle[IS_VIEW_MODEL_RESTORED_KEY] = value
        }

    init {
        viewModelScope.launch(ioDispatcher) {
            if (isFirstViewModelCreation) {
                isFirstViewModelCreation = false

                // Remove old imported files in case it would've crashed (or similar) to start with a clean slate.
                // This is required for already imported files restoration to not pick up old files in some extreme cases.
                removeOldData()

                // Set default values to advanced transfer options. This need to be done here in the `init`,
                // because we only want to do it once. If we come back from a cancelled or edited transfer,
                // we don't want to erase user's choices about advanced transfer options.
                initTransferOptionsValues()

            } else {
                importationFilesManager.restoreAlreadyImportedFiles()
            }

            importationFilesManager.continuouslyCopyPickedFilesToLocalStorage()
        }
    }

    fun getFiles(folderUuid: String?): Flow<List<FileUi>> {
        return if (folderUuid == null) {
            importationFilesManager.importedFiles
        } else {
            fileManager.getFilesFromTransfer(folderUuid)
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
            transferSendManager.sendNewTransfer(generateNewUploadSession())
        }
    }

    fun resetSendActionResult() {
        transferSendManager.resetSendStatus()
    }

    private suspend fun removeOldData() {
        importationFilesManager.removeLocalCopyFolder()
        uploadManager.removeAllUploadSession()
    }

    private suspend fun generateNewUploadSession(): NewUploadSession {
        return uploadManager.generateNewUploadSession(
            duration = selectedValidityPeriodOption.value.apiValue,
            authorEmail = if (selectedTransferType.value == TransferTypeUi.Mail) transferAuthorEmail.trim() else "",
            password = if (selectedPasswordOption.value == PasswordTransferOption.ACTIVATED) transferPassword else NO_PASSWORD,
            message = transferMessage,
            numberOfDownload = selectedDownloadLimitOption.value.apiValue,
            language = selectedLanguageOption.value.apiValue,
            recipientsEmails = if (selectedTransferType.value == TransferTypeUi.Mail) validatedRecipientsEmails else emptySet(),
            files = importationFilesManager.importedFiles.value.mapToList { fileUi ->
                object : UploadFileSession {
                    override val path: String? = null
                    override val localPath: String = fileUi.localPath ?: ""
                    override val mimeType: String = fileUi.mimeType ?: ""
                    override val name: String = fileUi.fileName
                    override val remoteUploadFile: RemoteUploadFile? = null
                    override val size: Long = fileUi.fileSize
                }
            },
        )
    }

    //region Transfer Type
    val selectedTransferType = savedStateHandle.getStateFlow(SELECTED_TRANSFER_TYPE, TransferTypeUi.QrCode)

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

    fun getEmailTextFieldCallbacks(): EmailTextFieldCallbacks {
        return EmailTextFieldCallbacks(
            transferAuthorEmail = GetSetCallbacks(get = { transferAuthorEmail }, set = { transferAuthorEmail = it }),
            isAuthorEmailInvalid = { isAuthorEmailInvalid },
            recipientEmail = GetSetCallbacks(get = { recipientEmail }, set = { recipientEmail = it }),
            isRecipientEmailInvalid = { isRecipientEmailInvalid },
            validatedRecipientsEmails = GetSetCallbacks(
                get = { validatedRecipientsEmails },
                set = { validatedRecipientsEmails = it },
            ),
        )
    }

    private fun initTransferOptionsValues() {
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
