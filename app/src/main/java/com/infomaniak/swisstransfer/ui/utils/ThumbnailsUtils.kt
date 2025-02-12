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
package com.infomaniak.swisstransfer.ui.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Size
import android.webkit.MimeTypeMap
import androidx.core.net.toFile
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.filetypes.FileType.Companion.guessFromFileName
import com.infomaniak.core.filetypes.FileType.Companion.guessFromFileUri
import com.infomaniak.core.filetypes.FileType.Companion.guessFromMimeType
import io.sentry.Sentry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.min

object ThumbnailsUtils {

    private const val THUMBNAIL_SIZE = 500

    suspend fun Context.getLocalThumbnail(fileName: String, fileUri: Uri): Bitmap? = withContext(Dispatchers.IO) {
        return@withContext if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            getThumbnailAfterAndroidPie(fileUri, THUMBNAIL_SIZE)
        } else {
            getThumbnailUntilAndroidPie(fileName, fileUri, THUMBNAIL_SIZE)
        }
    }

    private fun Context.getThumbnailAfterAndroidPie(fileUri: Uri, thumbnailSize: Int): Bitmap? {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            val size = Size(thumbnailSize, thumbnailSize)
            try {
                if (fileUri.scheme.equals(ContentResolver.SCHEME_FILE)) {
                    if (guessFromFileUri(fileUri) == FileType.VIDEO) {
                        ThumbnailUtils.createVideoThumbnail(fileUri.toFile(), size, null)
                    } else {
                        ThumbnailUtils.createImageThumbnail(fileUri.toFile(), size, null)
                    }
                } else {
                    contentResolver.loadThumbnail(fileUri, size, null)
                }
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    private fun Context.getThumbnailUntilAndroidPie(fileName: String, fileUri: Uri, thumbnailSize: Int): Bitmap? {
        val isSchemeFile = fileUri.scheme.equals(ContentResolver.SCHEME_FILE)
        val localFile = fileUri.lastPathSegment?.split(":")?.let { list ->
            list.getOrNull(1)?.let { path -> java.io.File(path) }
        }
        val externalRealPath = getExternalRealPath(fileUri, isSchemeFile, localFile)

        return if (isSchemeFile || externalRealPath.isNotBlank()) {
            getBitmapFromPath(fileName, fileUri, thumbnailSize, externalRealPath)
        } else {
            getBitmapFromFileId(fileUri, thumbnailSize)
        }
    }

    private fun Context.getExternalRealPath(fileUri: Uri, isSchemeFile: Boolean, localFile: java.io.File?): String {
        return when {
            !isSchemeFile && localFile?.exists() == true -> {
                Sentry.captureMessage("Uri contains absolute path")
                localFile.absolutePath
            }
            fileUri.authority == "com.android.externalstorage.documents" -> {
                getRealPathFromExternalStorage(this, fileUri)
            }
            else -> ""
        }
    }

    @Suppress("DEPRECATION")
    private fun getRealPathFromExternalStorage(context: Context, uri: Uri): String {
        // ExternalStorageProvider
        val docId = DocumentsContract.getDocumentId(uri)
        val split = docId.split(":").dropLastWhile { it.isEmpty() }.toTypedArray()
        val type = split.first()
        val relativePath = split.getOrNull(1) ?: return ""
        val external = context.externalMediaDirs
        return when {
            "primary".equals(type, true) -> Environment.getExternalStorageDirectory().toString() + "/" + relativePath
            external.size > 1 -> {
                val filePath = external[1].absolutePath
                filePath.substring(0, filePath.indexOf("Android")) + relativePath
            }
            else -> ""
        }
    }

    @Suppress("DEPRECATION")
    private fun getBitmapFromPath(fileName: String, fileUri: Uri, thumbnailSize: Int, externalRealPath: String): Bitmap? {
        val path = externalRealPath.ifBlank { fileUri.path ?: return null }

        return if (guessFromFileName(fileName) == FileType.VIDEO) {
            ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MICRO_KIND)
        } else {
            extractThumbnail(path, thumbnailSize, thumbnailSize)
        }
    }

    @Deprecated(message = "Only for API 28 and below, otherwise use ThumbnailUtils.createImageThumbnail()")
    private fun extractThumbnail(filePath: String, width: Int, height: Int): Bitmap? {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, bitmapOptions)

        val widthScale = bitmapOptions.outWidth.toFloat() / width
        val heightScale = bitmapOptions.outHeight.toFloat() / height
        val scale = min(widthScale, heightScale)
        var sampleSize = 1
        while (sampleSize < scale) {
            sampleSize *= 2
        }
        bitmapOptions.inSampleSize = sampleSize
        bitmapOptions.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(filePath, bitmapOptions)
    }

    @Suppress("DEPRECATION")
    private fun Context.getBitmapFromFileId(fileUri: Uri, thumbnailSize: Int): Bitmap? {
        return try {
            ContentUris.parseId(fileUri)
        } catch (e: Exception) {
            fileUri.lastPathSegment?.split(":")?.let { it.getOrNull(1)?.toLongOrNull() }
        }?.let { fileId ->
            val options = BitmapFactory.Options().apply {
                outWidth = thumbnailSize
                outHeight = thumbnailSize
            }
            if (contentResolver.getType(fileUri)?.contains("video") == true) {
                MediaStore.Video.Thumbnails.getThumbnail(
                    contentResolver,
                    fileId,
                    MediaStore.Video.Thumbnails.MICRO_KIND,
                    options,
                )
            } else {
                MediaStore.Images.Thumbnails.getThumbnail(
                    contentResolver,
                    fileId,
                    MediaStore.Images.Thumbnails.MICRO_KIND,
                    options,
                )
            }
        }
    }
}
