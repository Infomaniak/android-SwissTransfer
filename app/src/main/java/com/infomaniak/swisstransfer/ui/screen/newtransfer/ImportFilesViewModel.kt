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
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.appSettings.AppSettings
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.RemoteUploadFile
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadFileSession
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.utils.mapToList
import com.infomaniak.multiplatform_swisstransfer.data.NewUploadSession
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferType
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportFilesViewModel @Inject constructor(
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
                importationFilesManager.removeLocalCopyFolder()
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

    fun selectTransferType(type: TransferType) {
        _selectedTransferType.value = type
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

    private fun generateNewUploadSession(): NewUploadSession {
        return NewUploadSession(
            duration = "30",
            authorEmail = "",
            password = "",
            message = "sisi test",
            numberOfDownload = 20,
            language = EmailLanguage.ENGLISH,
            recipientsEmails = emptyList(),
            files = importationFilesManager.importedFiles.value.mapToList { fileUi ->
                object : UploadFileSession {
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
    private val _selectedTransferType = MutableStateFlow(TransferType.LINK)
    val selectedTransferType: StateFlow<TransferType> = _selectedTransferType

    sealed class SendActionResult {
        data class Success(val totalSize: Long) : SendActionResult()
        data object Failure : SendActionResult()
    }
    //endregion

    //region Transfer Advanced Options
    private val _selectedValidityPeriodOption = MutableStateFlow<ValidityPeriodOption?>(null)
    val selectedValidityPeriodOption: StateFlow<ValidityPeriodOption?> = _selectedValidityPeriodOption.asStateFlow()

    private val _selectedDownloadLimitOption = MutableStateFlow<DownloadLimitOption?>(null)
    val selectedDownloadLimitOption: StateFlow<DownloadLimitOption?> = _selectedDownloadLimitOption.asStateFlow()

    private val _selectedPasswordOption = MutableStateFlow(PasswordTransferOption.NONE)
    val selectedPasswordOption: StateFlow<PasswordTransferOption?> = _selectedPasswordOption.asStateFlow()

    private val _selectedLanguageOption = MutableStateFlow<EmailLanguageOption?>(null)
    val selectedLanguageOption: StateFlow<EmailLanguageOption?> = _selectedLanguageOption.asStateFlow()

    fun selectTransferValidityPeriod(validityPeriodOption: ValidityPeriodOption) {
        _selectedValidityPeriodOption.value = validityPeriodOption
    }

    fun selectTransferDownloadLimit(downloadLimit: DownloadLimitOption) {
        _selectedDownloadLimitOption.value = downloadLimit
    }

    fun selectTransferPasswordOption(passwordOption: PasswordTransferOption) {
        _selectedPasswordOption.value = passwordOption
    }

    fun selectTransferLanguage(language: EmailLanguageOption) {
        _selectedLanguageOption.value = language
    }

    fun initTransferAdvancedOptionsValues(safeAppSettings: AppSettings) {
        selectTransferValidityPeriod(safeAppSettings.validityPeriod.toAdvancedOption())
        selectTransferDownloadLimit(safeAppSettings.downloadLimit.toAdvancedOption())
        selectTransferLanguage(safeAppSettings.emailLanguage.toAdvancedOption())
    }
    //endregion

    companion object {
        private val TAG = ImportFilesViewModel::class.java.simpleName
        private const val IS_VIEW_MODEL_RESTORED_KEY = "IS_VIEW_MODEL_RESTORED_KEY"
    }
}
