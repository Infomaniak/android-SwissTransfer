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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransferViewModel @Inject constructor(
    private val importationFilesManager: ImportationFilesManager,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            importationFilesManager.importFiles(uris)
        }
    }

    fun removeFileByUid(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            importationFilesManager.removeFileByUid(uid)
        }
    }

    companion object {
        private const val TAG = "File importation"
        const val LOCAL_COPY_FOLDER = "local_copy_folder"
        const val IS_VIEW_MODEL_RESTORED_KEY = "IS_VIEW_MODEL_RESTORED_KEY"
    }
}
