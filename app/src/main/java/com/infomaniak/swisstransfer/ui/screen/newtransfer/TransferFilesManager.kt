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

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferFilesManager @Inject constructor(@ApplicationContext private val appContext: Context) {
    fun getFiles(uris: List<Uri>, alreadyUsedFileNames: Set<String>): MutableSet<PickedFile> {
        val currentUsedFileNames = alreadyUsedFileNames.toMutableSet()
        val files = mutableSetOf<PickedFile>()

        uris.forEach { uri ->
            getFile(uri, currentUsedFileNames)?.let { file ->
                currentUsedFileNames += file.fileName
                files += file
            }
        }

        return files
    }

    private fun getFile(uri: Uri, alreadyUsedFileNames: Set<String>): PickedFile? {
        val contentResolver: ContentResolver = appContext.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        return cursor?.getFileNameAndSize()?.let { (name, size) ->
            val uniqueName = FileNameUtils.postfixExistingFileNames(name, alreadyUsedFileNames)
            PickedFile(uniqueName, size, uri)
        }
    }

    private fun Cursor.getFileNameAndSize(): Pair<String, Long>? = use {
        if (it.moveToFirst()) {
            val displayNameColumnIndex = it.getColumnIndexOrNull(OpenableColumns.DISPLAY_NAME) ?: return null
            val fileName = it.getString(displayNameColumnIndex)

            val fileSizeColumnIndex = it.getColumnIndexOrNull(OpenableColumns.SIZE) ?: return null
            val fileSize = it.getLong(fileSizeColumnIndex)

            fileName to fileSize
        } else {
            null
        }
    }

    private fun Cursor.getColumnIndexOrNull(column: String): Int? {
        return getColumnIndex(column).takeIf { it != -1 }
    }

    data class PickedFile(val fileName: String, val fileSizeInBytes: Long, val uri: Uri)
}
