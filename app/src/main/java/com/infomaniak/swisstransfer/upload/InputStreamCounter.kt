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
    sizeOfByteArrayToCountBytes: Int = DEFAULT_BUFFER_SIZE // 8kiB only
) {

    private val sharedByteArrayForCountingBytes = ByteArray(sizeOfByteArrayToCountBytes)

    inline fun fileSizeFor(
        uri: Uri,
        onTotalBytesUpdate: (Long) -> Unit,
    ): Long {
        // OpenableColumns.SIZE isn't reliable, so we're counting the bytes from the stream instead.
        return appCtx.contentResolver.openInputStream(uri)!!.buffered(DEFAULT_BUFFER_SIZE).use { stream ->
            var totalBytes = 0L
            while (true) {
                // The skip() method might skip beyond EOF. From FileInputStream's JavaDoc:
                // "This method may skip more bytes than what are remaining in the backing file."
                // (from https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html#skip-long-)
                // That's we're using read instead.
                val skippedBytes = stream.read(sharedByteArrayForCountingBytes)
                if (skippedBytes == -1) break
                totalBytes += skippedBytes
                onTotalBytesUpdate(totalBytes)
            }
            totalBytes
        }
    }
}
