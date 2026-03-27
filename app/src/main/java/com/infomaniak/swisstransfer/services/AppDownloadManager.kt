/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.services

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.infomaniak.core.network.networking.HttpClient
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.FileManager
import com.infomaniak.swisstransfer.ui.utils.displayTitle
import createHttpClient
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.accept
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.ContentType
import io.ktor.utils.io.readRemaining
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.readByteArray
import splitties.init.appCtx
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

class AppDownloadManager @Inject constructor(
    @param:ApplicationContext private val appContext: Context,
    private val fileManager: FileManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
) {

    suspend fun downloadFolderToPublicDownload(
        transferUi: TransferUi,
        folder: FileUi,
        folderPath: String,
        onDownload: suspend (bytesSent: Long, contentLength: Long) -> Unit,
    ) {
        fileManager.getFilesUnderPath(transferUi.uuid, folderPath).forEach { fileUi ->
            val downloadUrl = sharedApiUrlCreator.downloadFileUrl(transferUi, fileUi.uid)
            if (downloadUrl == null) {
                SentryLog.wtf(TAG, "Finish with failure because downloadUrl is null")
                throw FailureException()
            }

            var previousBytesSentTotal = 0L
            downloadToPublicDownload(downloadUrl, transferUi, fileUi) { bytesSentTotal, contentLength ->
                val bytesSent = bytesSentTotal - previousBytesSentTotal
                onDownload(bytesSent, folder.fileSize)
                previousBytesSentTotal = bytesSentTotal
            }
        }
    }

    suspend fun downloadTransferToPublicDownload(
        transferUi: TransferUi,
        onDownload: suspend (bytesSent: Long, contentLength: Long) -> Unit,
    ) {
        fileManager.getTransferFilesOnly(transferUi.uuid).forEach { fileUi ->
            val downloadUrl = sharedApiUrlCreator.downloadFileUrl(transferUi, fileUi.uid)
            if (downloadUrl == null) {
                SentryLog.wtf(TAG, "Finish with failure because downloadUrl is null")
                throw FailureException()
            }

            var previousBytesSentTotal = 0L
            downloadToPublicDownload(downloadUrl, transferUi, fileUi) { bytesSentTotal, contentLength ->
                val bytesSent = bytesSentTotal - previousBytesSentTotal
                onDownload(bytesSent, transferUi.sizeUploaded)
                previousBytesSentTotal = bytesSentTotal
            }
        }
    }

    private suspend fun downloadToPublicDownload(
        downloadUrl: String,
        transferUi: TransferUi,
        fileUi: FileUi,
        onDownload: suspend (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    ) {
        val mimeType = fileUi.mimeType ?: "application/octet-stream"
        val path = transferUi.computeDownloadPathWith(fileUi)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            downloadToPublicDownloads(
                url = downloadUrl,
                fileUi = fileUi,
                path = path,
                mimeType = mimeType,
                onDownload = onDownload,
            )
        } else {
            downloadToPublicDownloadsUnderApi29(
                url = downloadUrl,
                fileName = fileUi.fileName,
                path = path,
                onDownload = onDownload,
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private suspend fun downloadToPublicDownloads(
        url: String,
        fileUi: FileUi,
        path: String,
        mimeType: String,
        onDownload: suspend (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    ): Uri? = withContext(Dispatchers.IO) {
        val resolver = appContext.contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, fileUi.fileName)
            // put(MediaStore.Downloads.MIME_TYPE, mimeType)
            put(MediaStore.Downloads.IS_PENDING, 1)
            put(MediaStore.Downloads.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}/$path")
        }

        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        val itemUri = resolver.insert(collection, contentValues) ?: return@withContext null

        return@withContext runCatching {
            resolver.openFileDescriptor(itemUri, "rwt")?.use { pfd ->
                FileOutputStream(pfd.fileDescriptor).use { outputStream ->
                    downloadFile(url, outputStream, onDownload)
                }
            } ?: throw IllegalStateException("OutputStream not available")

            contentValues.clear()
            contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
            resolver.update(itemUri, contentValues, null, null)

            return@runCatching itemUri
        }.onFailure {
            resolver.delete(itemUri, null, null)
            throw it
        }.getOrNull()
    }


    private suspend fun downloadToPublicDownloadsUnderApi29(
        url: String,
        fileName: String,
        path: String,
        onDownload: suspend (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    ) = withContext(Dispatchers.IO) {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, "$path/$fileName").also {
            if (it.parentFile?.exists() == false) it.parentFile?.mkdirs()
            if (it.exists()) it.delete()
        }
        runCatching {
            file.outputStream().use { outputStream ->
                downloadFile(url, outputStream, onDownload)
            }
        }.onFailure {
            file.delete()
            throw it
        }
    }

    private suspend fun downloadFile(
        url: String,
        output: OutputStream,
        onDownload: suspend (bytesSentTotal: Long, contentLength: Long?) -> Unit,
    ) {
        createHttpClient(HttpClient.okHttpClient).prepareGet(url) {
            accept(ContentType.Any)
            onDownload { bytesSentTotal, contentLength ->
                onDownload(bytesSentTotal, contentLength)
            }
        }.execute { response ->
            val channel = response.bodyAsChannel()
            while (!channel.isClosedForRead) {
                val packet = channel.readRemaining(DEFAULT_BUFFER_SIZE)
                while (!packet.exhausted()) output.write(packet.readByteArray())
            }
            output.flush()
        }
    }

    class FailureException : IllegalStateException()

    companion object {
        private val TAG = AppDownloadManager::javaClass.name
        private const val DEFAULT_BUFFER_SIZE: Long = 8 * 1024
        const val ROOT_FOLDER_NAME = "SwissTransfer"

        fun TransferUi.computeDownloadPathWith(fileUi: FileUi): String {
            return "$ROOT_FOLDER_NAME/${this.displayTitle}${fileUi.path?.substringBeforeLast("/") ?: ""}"
        }

        suspend fun uriFor(transferUi: TransferUi, fileUi: FileUi): Uri? = withContext(Dispatchers.IO) {
            return@withContext when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> uriForAboveApi29(transferUi, fileUi)
                else -> uriForUnderApi29(transferUi, fileUi)
            }
        }

        suspend fun doesFileExists(transferUi: TransferUi, fileUi: FileUi): Boolean {
            return uriFor(transferUi, fileUi) != null
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun uriForAboveApi29(transferUi: TransferUi, fileUi: FileUi): Uri? {
            val contentUri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            val relativePath = "${Environment.DIRECTORY_DOWNLOADS}/${transferUi.computeDownloadPathWith(fileUi)}"
            val selection = "${MediaStore.DownloadColumns.RELATIVE_PATH}=? AND ${MediaStore.DownloadColumns.DISPLAY_NAME}=?" +
                    " AND ${MediaStore.DownloadColumns.IS_PENDING}=0"
            val selectionArgs = arrayOf(relativePath, fileUi.fileName)

            return appCtx.contentResolver.query(contentUri, null, selection, selectionArgs, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.DownloadColumns._ID)
                    val id = cursor.getLong(idColumn)
                    ContentUris.withAppendedId(contentUri, id)
                } else {
                    null
                }
            }
        }

        private fun uriForUnderApi29(transferUi: TransferUi, fileUi: FileUi): Uri? {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val path = "${ROOT_FOLDER_NAME}/${transferUi.computeDownloadPathWith(fileUi)}"
            val file = File(downloadsDir, "$path/${fileUi.fileName}")
            return if (file.exists()) Uri.fromFile(file) else null
        }
    }
}
