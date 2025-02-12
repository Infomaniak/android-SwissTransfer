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

import java.io.File
import java.io.InputStream
import java.io.OutputStream

object FileUtils {

    fun copyUriDataLocally(
        fileToCreate: File,
        inputStream: InputStream,
        copiedBytes: ((Int) -> Unit)? = null,
    ): Result<File> = runCatching {
        fileToCreate.also { file ->
            if (file.exists()) file.delete()
            file.createNewFile()
            copyStreams(inputStream, file.outputStream(), copiedBytes)
        }
    }

    private fun copyStreams(inputStream: InputStream, outputStream: OutputStream, copiedBytes: ((Int) -> Unit)?) {
        return inputStream.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytes = input.read(buffer)
                while (bytes >= 0) {
                    output.write(buffer, 0, bytes)
                    copiedBytes?.invoke(bytes)
                    bytes = input.read(buffer)
                }
            }
        }
    }
}
