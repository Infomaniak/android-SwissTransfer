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

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import com.infomaniak.swisstransfer.ui.utils.ThumbnailsUtils.getLocalThumbnail
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThumbnailsLocalStorage @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val thumbnailsFolder by lazy { File(appContext.filesDir, THUMBNAILS_FOLDER) }
    private val ongoingThumbnailsFolder by lazy {
        File(appContext.filesDir, "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER")
    }

    fun removeOngoingThumbnailsFolder() {
        if (ongoingThumbnailsFolder.exists()) runCatching { ongoingThumbnailsFolder.deleteRecursively() }
    }

    fun getRootUriForTransfer(transferUuid: String) = File(appContext.filesDir, "$THUMBNAILS_FOLDER/$transferUuid").toUri()

    fun renameFileWith(currentFileName: String, newFileName: String) {
        val ongoingTransferFolder = "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER"
        val currentFile = File(appContext.filesDir, "$ongoingTransferFolder/$currentFileName")
        val newFile = File(appContext.filesDir, "$ongoingTransferFolder/$newFileName")
        currentFile.renameTo(newFile)
    }

    fun renameOngoingThumbnailsFolderWith(transferUuid: String) {
        val newFolder = File(appContext.filesDir, "$THUMBNAILS_FOLDER/$transferUuid")
        ongoingThumbnailsFolder.renameTo(newFolder)
    }

    fun getFile(transferUuid: String, fileUuid: String): File {
        return File(appContext.filesDir, "$THUMBNAILS_FOLDER/$transferUuid/$fileUuid")
    }

    fun getOngoingFile(fileUuid: String): File {
        return File(appContext.filesDir, "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER/$fileUuid")
    }

    suspend fun copyUriDataLocally(fileUri: Uri, fileName: String, isOngoingTransfer: Boolean) {
        val fileToCreate = File(getImportFolderOrCreate(isOngoingTransfer), fileName)
        appContext.getLocalThumbnail(fileName, fileUri)?.copyTo(fileToCreate)
    }

    private fun Bitmap.copyTo(outputFile: File): File? {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(outputFile)
            compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            return outputFile
        } catch (e: IOException) {
            return null
        } finally {
            fileOutputStream?.close()
        }
    }

    private fun getImportFolderOrCreate(isOngoingTransfer: Boolean): File {
        val folder = if (isOngoingTransfer) {
            ongoingThumbnailsFolder
        } else {
            thumbnailsFolder
        }
        return folder.apply { if (!exists()) mkdirs() }
    }

    companion object {
        private const val THUMBNAILS_FOLDER = "thumbnails"
        private const val THUMBNAILS_ONGOING_TRANSFER_FOLDER = "ongoing_transfer"
    }
}
