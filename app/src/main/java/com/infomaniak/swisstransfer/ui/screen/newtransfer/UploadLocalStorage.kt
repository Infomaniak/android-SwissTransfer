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
import androidx.core.net.toUri
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel.Companion.LOCAL_COPY_FOLDER
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadLocalStorage @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val importFolder by lazy { File(appContext.cacheDir, LOCAL_COPY_FOLDER) }
    private fun getImportFolderOrCreate() = importFolder.apply { if (!exists()) mkdirs() }

    fun removeLocalCopyFolder() {
        if (importFolder.exists()) runCatching { importFolder.deleteRecursively() }
    }

    fun importFolderExists() = importFolder.exists()

    fun listImportFiles(): Array<File>? {
        return importFolder.listFiles()
    }

    fun copyFileLocally(uri: Uri, fileName: String): File? {
        val file = File(getImportFolderOrCreate(), fileName).apply {
            if (exists()) delete()
            runCatching { createNewFile() }.onFailure { return null }

            runCatching {
                val inputStream = appContext.contentResolver.openInputStream(uri) ?: return null

                inputStream.use { inputStream ->
                    outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }.onFailure {
                return null
            }
        }

        return file
    }
}
