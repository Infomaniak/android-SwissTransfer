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
package com.infomaniak.swisstransfer.upload

import android.net.Uri
import splitties.init.appCtx

internal class InputStreamCounter(
    bufferSize: Int = DEFAULT_BUFFER_SIZE // 8kiB only
) {

    private val sharedByteArrayForCountingBytes = ByteArray(bufferSize)

    /**
     * [android.provider.OpenableColumns.SIZE] is declarative, and could therefore have an inaccurate value,
     * so we're counting the bytes from the stream instead.
     */
    inline fun fileSizeFor(
        uri: Uri,
        onTotalBytesUpdate: (skippedBytes: Int, totalBytes: Long) -> Unit,
    ): Long = appCtx.contentResolver.openInputStream(uri)!!.buffered(sharedByteArrayForCountingBytes.size).use { stream ->
        var totalBytes = 0L
        while (true) {
            // The skip() method might skip beyond EOF. From FileInputStream's JavaDoc:
            // "This method may skip more bytes than what are remaining in the backing file."
            // (from https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html#skip-long-)
            // That's why we're using read instead.
            val skippedBytes = stream.read(sharedByteArrayForCountingBytes)
            if (skippedBytes == -1) break
            totalBytes += skippedBytes
            onTotalBytesUpdate(skippedBytes, totalBytes)
        }
        totalBytes
    }
}
