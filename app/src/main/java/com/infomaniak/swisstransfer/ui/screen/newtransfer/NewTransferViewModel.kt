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
import kotlinx.coroutines.flow.*

class NewTransferViewModel(application: Application) : AndroidViewModel(application) {
    private val context get() = getApplication<MainApplication>() as Context

    private val _fileUris = MutableStateFlow<List<Uri>>(emptyList())
    val fileUris: StateFlow<List<Uri>> = _fileUris

    private val _transferFiles: Flow<List<TransferFile>> = _fileUris
        .map { uris -> uris.map { getFileNameAndSize(it) } }

    val transferFiles: StateFlow<List<TransferFile>> = _transferFiles
        .map { files -> files.filter { file -> file.isSuccessfullyImported } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    val failedTransferFileCount: StateFlow<Int> = _transferFiles.map { files -> files.count { !it.isSuccessfullyImported } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0
        )

    data class TransferFile(private val _metadata: Metadata?, val uri: Uri) {
        val isSuccessfullyImported = _metadata != null
        val metadata = _metadata!!

        data class Metadata(val fileName: String, val size: Long)
    }


    private fun getFileNameAndSize(uri: Uri): TransferFile {
        var fileName: String? = null
        var fileSize: Long? = null

        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        val isSuccess = cursor?.use {
            if (it.moveToFirst()) {
                val displayNameColumnIndex = it.getColumnIndexOrNull(OpenableColumns.DISPLAY_NAME) ?: return@use false
                fileName = it.getString(displayNameColumnIndex)

                val fileSizeColumnIndex = it.getColumnIndexOrNull(OpenableColumns.SIZE) ?: return@use false
                fileSize = it.getLong(fileSizeColumnIndex)

                return@use true
            } else {
                return@use false
            }
        } ?: false

        return if (isSuccess) TransferFile(TransferFile.Metadata(fileName!!, fileSize!!), uri) else TransferFile(null, uri)
    }

    private fun Cursor.getColumnIndexOrNull(column: String): Int? {
        return getColumnIndex(column).takeIf { it != -1 }
    }

    fun addFiles(uris: List<Uri>) {
        _fileUris.value += uris
    }
}
