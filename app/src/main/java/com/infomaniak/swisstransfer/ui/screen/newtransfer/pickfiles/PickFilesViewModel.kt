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

package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles

import android.net.Uri
import androidx.compose.runtime.*
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.core.compose.basics.collectAsStateIn
import com.infomaniak.core.mapSync
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.tryCompletingWhileTrue
import com.infomaniak.core.utils.isValidEmail
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption.Companion.toTransferOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferOpenManager
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.CanSendStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.CanSendStatus.Issue
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.upload.NewTransferParams
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

@HiltViewModel
class PickFilesViewModel @Inject constructor(
    private val appSettingsManager: AppSettingsManager,
    private val newTransferOpenManager: NewTransferOpenManager,
    private val savedStateHandle: SavedStateHandle,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val canSendStatusFlow: StateFlow<CanSendStatus>

    fun send() = sendRequest()
    fun isReadyToSend() = sendRequest.isAwaitingCall

    val filesDetailsUiState: StateFlow<FilesDetailsUiState>

    val openFilePickerEvent: ReceiveChannel<Unit>

    val importedFilesDebounced: StateFlow<List<FileUi>>

    val selectedTransferTypeFlow = savedStateHandle.getStateFlow(SELECTED_TRANSFER_TYPE, TransferTypeUi.QrCode)

    fun importUris(uris: List<Uri>) {
        UploadForegroundService.addFiles(uris)
    }

    fun removeFileByUri(uriString: String) {
        UploadForegroundService.removeFiles(listOf(uriString.toUri()))
        viewModelScope.launch {
            thumbnailsLocalStorage.removeThumbnailForOngoingTransfer(uriString)
        }
    }

    private val sendRequest = CallableState<Unit>()
    private val _openFilePickerEvent: Channel<Unit> = Channel<Unit>(capacity = CONFLATED).also { openFilePickerEvent = it }

    sealed interface CanSendStatus {
        data object Yes : CanSendStatus
        data class No(val issues: Set<Issue>) : CanSendStatus

        sealed interface Issue {
            data object UploadOngoing : Issue
            sealed interface Files : Issue {
                data object Processing : Files
                data object NonePicked : Files
                data class MaxSizeExceeded(val actualSize: Long, val maxSize: Long) : Files
                data class MaxCountExceeded(val maxCount: Int) : Files
            }

            enum class Email : Issue {
                AuthorUnspecified,
                AuthorInvalid,
                NoValidatedRecipients,
            }
        }
    }

    sealed interface FilesDetailsUiState {
        data object EmptyFiles : FilesDetailsUiState
        data class Success(val files: List<FileUi>) : FilesDetailsUiState
    }

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
        canSendStatusFlow = buildCanSendStatusFlow()

        val pickedFilesFlow = UploadForegroundService.pickedFilesFlow.mapSync { pickedFiles ->
            pickedFiles.map { it.toFileUiModel() }
        }

        filesDetailsUiState = pickedFilesFlow.map { pickedFiles ->
            when {
                pickedFiles.isEmpty() -> FilesDetailsUiState.EmptyFiles
                else -> FilesDetailsUiState.Success(pickedFiles)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = FilesDetailsUiState.Success(emptyList()),
        )

        @OptIn(FlowPreview::class)
        importedFilesDebounced = pickedFilesFlow.debounce(50).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList(),
        )

        viewModelScope.launch { handleSessionStart() }
        viewModelScope.launch(ioDispatcher) {
            if (isFirstViewModelCreation) {
                // Remove old persisted data in case our process was killed, to start with a clean slate.
                removeOldData()

                // Set default values to advanced transfer options. This need to be done here in the `init`,
                // because we only want to do it once. If we come back from a cancelled or edited transfer,
                // we don't want to erase user's choices about advanced transfer options.
                initTransferOptionsValues()

                retrieveLastAuthorEmail()
            }

            val wasFirstViewModelCreation = isFirstViewModelCreation
            launch {
                var isFirstOpen = wasFirstViewModelCreation
                repeatWhileActive {
                    handleOpenReason(isFirstOpen)
                    isFirstOpen = false
                }
            }

            isFirstViewModelCreation = false
        }
    }

    private fun buildCanSendStatusFlow(): StateFlow<CanSendStatus> {
        val transferType by selectedTransferTypeFlow.collectAsStateIn(viewModelScope)
        val isHandlingPickedFiles by UploadForegroundService.isHandlingPickedFilesFlow.collectAsStateIn(viewModelScope)
        val uploadOngoing by UploadForegroundService.uploadStateFlow.mapSync { it != null }.collectAsStateIn(viewModelScope)
        val pickedFilesIssues by pickedFilesIssues().collectAsStateIn(viewModelScope)
        return snapshotFlow {
            val issues = buildSet<Issue> {
                if (uploadOngoing) add(Issue.UploadOngoing)
                if (isHandlingPickedFiles) add(Issue.Files.Processing)
                addAll(pickedFilesIssues)
                if (transferType == TransferTypeUi.Mail) {
                    if (transferAuthorEmail.isEmpty()) add(Issue.Email.AuthorUnspecified)
                    if (isAuthorEmailInvalid) add(Issue.Email.AuthorInvalid)
                    if (validatedRecipientsEmails.isEmpty()) add(Issue.Email.NoValidatedRecipients)
                }
            }
            if (issues.isEmpty()) CanSendStatus.Yes else CanSendStatus.No(issues)
        }.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = CanSendStatus.No(emptySet()))
    }

    private fun pickedFilesIssues(
        maxFilesSize: Long = FileUtils.MAX_FILES_SIZE,
        maxFilesCount: Int = FileUtils.MAX_FILE_COUNT
    ): StateFlow<Set<Issue>> = UploadForegroundService.pickedFilesFlow.mapSync { pickedFiles ->
        if (pickedFiles.isEmpty()) {
            setOf(Issue.Files.NonePicked)
        } else buildSet {
            if (pickedFiles.size > maxFilesCount) add(Issue.Files.MaxCountExceeded(maxFilesCount))
            val totalSize = pickedFiles.sumOf { it.size }
            if (totalSize > maxFilesSize) add(Issue.Files.MaxSizeExceeded(actualSize = totalSize, maxSize = maxFilesSize))
        }
    }

    private suspend fun handleSessionStart() {
        canSendStatusFlow.map { it == CanSendStatus.Yes }.tryCompletingWhileTrue {
            repeatWhileActive {
                sendRequest.awaitOneCall()
                val newTransferParams = extractNewTransferParams()
                appSettingsManager.setLastAuthorEmail(newTransferParams.authorEmail)
                UploadForegroundService.commitUploadSession(newTransferParams)
            }
        }
    }

    private suspend fun removeOldData() {
        thumbnailsLocalStorage.removeOngoingThumbnailsFolder()
    }

    private suspend fun retrieveLastAuthorEmail() {
        appSettingsManager.appSettings.first()?.lastAuthorEmail?.let {
            transferAuthorEmail = it
        }
    }

    private suspend fun handleOpenReason(isFirstOpen: Boolean) {
        when (val reason = newTransferOpenManager.readOpenReason()) {
            is NewTransferOpenManager.Reason.ExternalShareIncoming -> importUris(reason.uris)
            NewTransferOpenManager.Reason.Other -> {
                val noUploadOngoing = UploadForegroundService.uploadStateFlow.value == null
                val hasNoPickedFiles = with(UploadForegroundService) {
                    pickedFilesFlow.value.isEmpty() && !isHandlingPickedFilesFlow.value
                }
                if (noUploadOngoing && hasNoPickedFiles && isFirstOpen) {
                    _openFilePickerEvent.send(Unit)
                }
            }
        }
    }

    private fun extractNewTransferParams() = NewTransferParams(
        validityPeriod = selectedValidityPeriodOption.value.apiValue,
        authorEmail = if (selectedTransferTypeFlow.value == TransferTypeUi.Mail) transferAuthorEmail.trim() else "",
        password = if (selectedPasswordOption.value == PasswordTransferOption.ACTIVATED) transferPassword else NO_PASSWORD,
        message = transferMessage,
        downloadCountLimit = selectedDownloadLimitOption.value.apiValue,
        languageCode = selectedLanguageOption.value.apiValue,
        recipientsEmails = if (selectedTransferTypeFlow.value == TransferTypeUi.Mail) validatedRecipientsEmails else emptySet(),
        type = selectedTransferTypeFlow.value
    )

    //region Transfer Type

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
        private val TAG = PickFilesViewModel::class.java.simpleName

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

fun CanSendStatus.hasIssue(issue: Issue): Boolean = this is CanSendStatus.No && issue in issues

inline fun <reified T : Issue> CanSendStatus.findIssueOrNull(): T? = when (this) {
    is CanSendStatus.Yes -> null
    is CanSendStatus.No -> issues.firstNotNullOfOrNull { it as? T }
}

private fun PickedFile.toFileUiModel(): FileUi {
    val uriString = uri.toString()
    return FileUi(
        uid = uriString,
        fileName = name,
        path = null,
        isFolder = false,
        fileSize = size,
        mimeType = mimeType,
        localPath = null,
        thumbnailPath = uriString
    )
}
