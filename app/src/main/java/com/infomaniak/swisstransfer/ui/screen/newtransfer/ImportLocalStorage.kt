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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImportLocalStorage @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val importFolder by lazy { File(appContext.filesDir, IMPORTED_FILES) }

    private val _copiedBytes = MutableStateFlow<Long>(0)
    val copiedBytes: StateFlow<Long> = _copiedBytes.asStateFlow()

    /**
     * This method is used to reset the copied bytes the transfer count session is reset
     */
    fun resetCopiedBytes() {
        _copiedBytes.update { 0 }
    }

    fun removeImportFolder() {
        if (importFolder.exists()) runCatching { importFolder.deleteRecursively() }
    }

    fun importFolderExists() = importFolder.exists()

    fun getLocalFiles(): Array<File>? = importFolder.listFiles()

    fun copyUriDataLocally(inputStream: InputStream, fileName: String): Result<File> = runCatching {
        File(getImportFolderOrCreate(), fileName).also { file ->
            if (file.exists()) file.delete()
            file.createNewFile()
            copyStreams(inputStream, file.outputStream())
        }
    }

    private fun copyStreams(inputStream: InputStream, outputStream: OutputStream) {
        return inputStream.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytes = input.read(buffer)
                while (bytes >= 0) {
                    output.write(buffer, 0, bytes)
                    _copiedBytes.update { it + bytes }
                    bytes = input.read(buffer)
                }
            }
        }
    }

    private fun getImportFolderOrCreate() = importFolder.apply { if (!exists()) mkdirs() }

    companion object {
        const val TAG = "Importation stream copy"
        private const val IMPORTED_FILES = "imported_files"
    }
}
