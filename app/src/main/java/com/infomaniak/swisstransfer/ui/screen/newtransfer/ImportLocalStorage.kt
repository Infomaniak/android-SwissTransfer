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
import android.net.Uri
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel.Companion.LOCAL_COPY_FOLDER
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImportLocalStorage @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val importFolder by lazy { File(appContext.cacheDir, LOCAL_COPY_FOLDER) }

    fun removeImportFolder() {
        if (importFolder.exists()) runCatching { importFolder.deleteRecursively() }
    }

    fun importFolderExists() = importFolder.exists()

    fun getLocalFiles(): Array<File>? = importFolder.listFiles()

    fun copyUriDataLocally(uri: Uri, fileName: String): File? {
        val file = File(getImportFolderOrCreate(), fileName)

        if (file.exists()) file.delete()
        runCatching { file.createNewFile() }.onFailure { return null }

        runCatching {
            val inputStream = openInputStream(uri) ?: return null
            copyStreams(inputStream, file.outputStream())
        }.onFailure {
            SentryLog.w(TAG, "Caught an exception while copying file to local storage: $it")
            return null
        }

        return file
    }

    private fun openInputStream(uri: Uri): InputStream? {
        return appContext.contentResolver.openInputStream(uri) ?: run {
            SentryLog.w(TAG, "During local copy of the file openInputStream returned null")
            null
        }
    }

    private fun copyStreams(inputStream: InputStream, outputStream: OutputStream): Long {
        return inputStream.use { inputStream ->
            outputStream.use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }

    private fun getImportFolderOrCreate() = importFolder.apply { if (!exists()) mkdirs() }

    companion object {
        private val TAG = "Importation stream copy"
    }
}
