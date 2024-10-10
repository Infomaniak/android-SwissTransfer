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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.ui.components.FileUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransferViewModel @Inject constructor(private val transferFilesManager: TransferFilesManager) : ViewModel() {

    private val _files = MutableStateFlow<List<FileUiItem>>(emptyList())
    val files: StateFlow<List<FileUiItem>> = _files

    private val _failedFileCount = MutableSharedFlow<Int>()
    val failedFileCount: SharedFlow<Int> = _failedFileCount

    fun addFiles(uris: List<Uri>) {
        viewModelScope.launch {
            val alreadyUsedFileNames = buildSet { files.value.forEach { add(it.fileName) } }

            val newFiles = transferFilesManager.getFiles(uris, alreadyUsedFileNames)

            _files.value += newFiles
            _failedFileCount.emit(uris.count() - newFiles.count())
        }
    }
}
