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
import androidx.core.net.toUri
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferFilesManager @Inject constructor(@ApplicationContext private val appContext: Context) {
    suspend fun getFiles(uris: List<Uri>, isAlreadyUsed: suspend (String) -> Boolean): Set<PickedFile> {
        val currentUsedFileNames = mutableSetOf<String>()

        val files = buildSet {
            uris.forEach { uri ->
                getFile(uri, isAlreadyUsed = { isAlreadyUsed(it) || currentUsedFileNames.contains(it) })?.let { file ->
                    currentUsedFileNames += file.fileName
                    add(file)
                }
            }
        }

        return files
    }

    fun getRestoredFileData(files: Array<File>): List<FileUi> {
        return files.mapNotNull { file ->
            val fileSizeInBytes = runCatching { file.length() }
                .onFailure { Sentry.addBreadcrumb("Caught an exception while restoring imported files: $it") }
                .getOrNull() ?: return@mapNotNull null

            FileUi(
                uid = file.name,
                fileName = file.name,
                fileSizeInBytes = fileSizeInBytes,
                mimeType = null,
                uri = file.toUri().toString(),
            )
        }
    }

    private suspend fun getFile(uri: Uri, isAlreadyUsed: suspend (String) -> Boolean): PickedFile? {
        val contentResolver: ContentResolver = appContext.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        return cursor?.getFileNameAndSize()?.let { (name, size) ->
            val uniqueName = FileNameUtils.postfixExistingFileNames(name, isAlreadyUsed)
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
