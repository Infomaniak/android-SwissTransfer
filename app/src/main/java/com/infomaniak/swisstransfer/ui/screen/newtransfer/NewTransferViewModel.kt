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

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.ui.MainApplication
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils.postfixExistingFileNames
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NewTransferViewModel(application: Application) : AndroidViewModel(application) {
    private val context get() = getApplication<MainApplication>() as Context

    // TODO: replay = 1 not needed for now because addFiles() cannot be called before we start listening to the sharedflow in the
    //  viewmodel
    private val addedFilesUri = MutableSharedFlow<List<Uri>>(replay = 1)
    private val allTransferFiles = MutableStateFlow<List<TransferFile>>(emptyList())

    val successfulTransferFiles: StateFlow<List<TransferFile>> = allTransferFiles
        .mapLatest { files -> files.filter { file -> file.isSuccessfullyImported } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val alreadyUsedFileNames: StateFlow<Set<String>> = successfulTransferFiles
        .mapLatest { files ->
            buildSet {
                files.forEach { add(it.metadata.fileName) }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptySet(),
        )

    val failedTransferFileCount: StateFlow<Int> =
        allTransferFiles.mapLatest { files -> files.count { !it.isSuccessfullyImported } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily, // TODO: Decide of the SharingStarted strategy once this value is used
                initialValue = 0
            )

    init {
        viewModelScope.launch {
            addedFilesUri.collect { uris ->
                uris.forEach { uri ->
                    val transferFile = getFileNameAndSize(uri)
                    allTransferFiles.value += transferFile
                }
            }
        }
    }

    fun addFiles(uris: List<Uri>) {
        viewModelScope.launch {
            addedFilesUri.emit(uris)
        }
    }

    private fun getFileNameAndSize(uri: Uri): TransferFile {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        return TransferFile(getFileMetadata(cursor), uri)
    }

    private fun getFileMetadata(cursor: Cursor?): TransferFile.Metadata? {
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameColumnIndex = it.getColumnIndexOrNull(OpenableColumns.DISPLAY_NAME) ?: return null
                val fileName = it.getString(displayNameColumnIndex)

                val fileSizeColumnIndex = it.getColumnIndexOrNull(OpenableColumns.SIZE) ?: return null
                val fileSize = it.getLong(fileSizeColumnIndex)

                val customName = postfixExistingFileNames(fileName, alreadyUsedFileNames.value)
                return TransferFile.Metadata(customName, fileSize)
            } else {
                return null
            }
        } ?: return null
    }

    private fun Cursor.getColumnIndexOrNull(column: String): Int? {
        return getColumnIndex(column).takeIf { it != -1 }
    }

    data class TransferFile(private val _metadata: Metadata?, val uri: Uri) {
        val isSuccessfullyImported = _metadata != null
        val metadata = _metadata!!

        data class Metadata(val fileName: String, val size: Long)
    }
}
