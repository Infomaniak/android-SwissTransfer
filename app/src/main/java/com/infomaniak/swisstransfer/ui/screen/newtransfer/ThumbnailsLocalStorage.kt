/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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

import android.graphics.Bitmap
import android.os.Build.VERSION.SDK_INT
import androidx.core.net.toUri
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.filetypes.FileType.Companion.guessFromFileName
import com.infomaniak.core.thumbnails.ThumbnailsUtils.getLocalThumbnail
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import splitties.init.appCtx
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThumbnailsLocalStorage @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val filesDir = appCtx.filesDir
    private val thumbnailsFolder by lazy { filesDir.resolve(THUMBNAILS_FOLDER) }
    private val ongoingThumbnailsFolder by lazy {
        filesDir.resolve("$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER")
    }

    //region Delete 
    suspend fun removeOngoingThumbnailsFolder() = ioDispatcher {
        if (ongoingThumbnailsFolder.exists()) runCatching { ongoingThumbnailsFolder.deleteRecursively() }
    }

    suspend fun cleanExpiredThumbnails(transfers: List<TransferUi>) = withContext(ioDispatcher) {
        thumbnailsFolder.listFiles()?.forEach { transferFolder ->
            if (transferFolder.name != THUMBNAILS_ONGOING_TRANSFER_FOLDER && transfers.none { it.uuid == transferFolder.name }) {
                transferFolder.deleteRecursively()
            }
        }
    }

    suspend fun removeThumbnailForOngoingTransfer(fileName: String) = ioDispatcher {
        ongoingThumbnailsFolder.listFiles()?.find { fileName.contains(it.name) }?.delete()
    }
    //endregion

    //region Get
    fun getThumbnailsFolderFor(transferUuid: String) = filesDir.resolve("$THUMBNAILS_FOLDER/$transferUuid").toUri()

    fun getThumbnailFor(transferUuid: String, fileUuid: String): File {
        return filesDir.resolve("$THUMBNAILS_FOLDER/$transferUuid/$fileUuid")
    }

    fun getOngoingThumbnailFor(fileUuid: String): File {
        return filesDir.resolve("$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER/$fileUuid")
    }

    private fun getOrCreateThumbnailsFolder(transferUuid: String): File {
        return filesDir.resolve("$THUMBNAILS_FOLDER/$transferUuid").apply { if (!exists()) mkdirs() }
    }

    private fun getOrCreateOngoingThumbnailsFolder() = ongoingThumbnailsFolder.apply { if (!exists()) mkdirs() }
    //endregion

    //region Rename
    fun renameFileWith(currentFileName: String, newFileName: String) {
        val ongoingTransferFolder = "$THUMBNAILS_FOLDER/$THUMBNAILS_ONGOING_TRANSFER_FOLDER"
        val currentFile = filesDir.resolve("$ongoingTransferFolder/$currentFileName")
        val newFile = filesDir.resolve("$ongoingTransferFolder/$newFileName")
        currentFile.renameTo(newFile)
    }

    fun renameOngoingThumbnailsFolderWith(transferUuid: String) {
        val newFolder = filesDir.resolve("$THUMBNAILS_FOLDER/$transferUuid")
        ongoingThumbnailsFolder.renameTo(newFolder)
    }
    //endregion

    //region Copy
    fun generateThumbnailFor(transferUuid: String? = null, fileUri: String, fileName: String, extension: String) {
        val fileToCreate = transferUuid?.let {
            getOrCreateThumbnailsFolder(it).resolve(fileName)
        } ?: getOrCreateOngoingThumbnailsFolder().resolve(fileName)
        val isVideo = guessFromFileName(extension) == FileType.VIDEO
        appCtx.getLocalThumbnail(fileUri.toUri(), isVideo)?.copyTo(fileToCreate)
    }

    @Suppress("DEPRECATION")
    private fun Bitmap.copyTo(outputFile: File): File? {
        val fileOutputStream = runCatching { FileOutputStream(outputFile) }.getOrNull() ?: return null
        return runCatching {
            val compressFormat = if (SDK_INT >= 30) Bitmap.CompressFormat.WEBP_LOSSY else Bitmap.CompressFormat.WEBP
            compress(compressFormat, 100, fileOutputStream)
            return@runCatching outputFile
        }.getOrNull().also { fileOutputStream.close() }
    }
    //endregion

    companion object {
        private const val THUMBNAILS_FOLDER = "thumbnails"
        private const val THUMBNAILS_ONGOING_TRANSFER_FOLDER = "ongoing_transfer"
    }
}
