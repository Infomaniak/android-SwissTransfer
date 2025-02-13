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
import android.os.Build
import androidx.core.net.toUri
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.filetypes.FileType.Companion.guessFromFileName
import com.infomaniak.core.thumbnails.ThumbnailsUtils.getLocalThumbnail
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThumbnailsLocalStorage @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val thumbnailsFolder by lazy { File(appContext.filesDir, THUMBNAILS_FOLDER) }
    private val ongoingThumbnailsFolder by lazy {
        File(appContext.filesDir, "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER")
    }

    //region Delete 
    fun removeOngoingThumbnailsFolder() {
        if (ongoingThumbnailsFolder.exists()) runCatching { ongoingThumbnailsFolder.deleteRecursively() }
    }

    suspend fun cleanExpiredThumbnails(transfers: List<TransferUi>) = withContext(ioDispatcher) {
        thumbnailsFolder.listFiles()?.forEach { file ->
            if (file.name != THUMBNAILS_ONGOING_TRANSFER_FOLDER && transfers.none { it.uuid == file.name }) {
                file.deleteRecursively()
            }
        }
    }

    fun removeThumbnailForOngoingTransfer(fileName: String) {
        ongoingThumbnailsFolder.listFiles()?.find { fileName.contains(it.name) }?.delete()
    }
    //endregion

    //region Get
    fun getThumbnailsFolderFor(transferUuid: String) = File(appContext.filesDir, "$THUMBNAILS_FOLDER/$transferUuid").toUri()

    fun getThumbnailFor(transferUuid: String, fileUuid: String): File {
        return File(appContext.filesDir, "$THUMBNAILS_FOLDER/$transferUuid/$fileUuid")
    }

    fun getOngoingThumbnailFor(fileUuid: String): File {
        return File(appContext.filesDir, "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER/$fileUuid")
    }

    private fun getOrCreateOngoingThumbnailsFolder() = ongoingThumbnailsFolder.apply { if (!exists()) mkdirs() }
    //endregion

    //region Rename
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
    //endregion

    //region Copy
    fun generateThumbnailFor(fileUri: String, fileName: String, extension: String) {
        val fileToCreate = File(getOrCreateOngoingThumbnailsFolder(), fileName)
        val isVideo = guessFromFileName(extension) == FileType.VIDEO
        appContext.getLocalThumbnail(fileUri.toUri(), isVideo)?.copyTo(fileToCreate)
    }

    @Suppress("DEPRECATION")
    private fun Bitmap.copyTo(outputFile: File): File? {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(outputFile)

            val compressFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            }
            compress(compressFormat, 100, fileOutputStream)
            return outputFile
        } catch (e: IOException) {
            return null
        } finally {
            fileOutputStream?.close()
        }
    }
    //endregion

    companion object {
        private const val THUMBNAILS_FOLDER = "thumbnails"
        private const val THUMBNAILS_ONGOING_TRANSFER_FOLDER = "ongoing_transfer"
    }
}
