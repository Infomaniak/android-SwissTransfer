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
import android.provider.OpenableColumns
import com.infomaniak.core.sentry.SentryLog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TransferCountChannel(private val context: Context, private val resetCopiedBytes: () -> Unit) {
    private val channel = Channel<ImportationFilesManager.PickedFile>(capacity = Channel.UNLIMITED)

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    /**
     * A "session" lasts from when the queue count is greater than 0 until it resets to 0 again.
     * This count is used to track and compute the progress of files being imported during the current session.
     */
    private val _currentSessionByteCount: MutableStateFlow<Long> = MutableStateFlow(0)
    val currentSessionTotalByteCount: StateFlow<Long> = _currentSessionByteCount.asStateFlow()

    private val countMutex = Mutex()

    suspend fun send(element: ImportationFilesManager.PickedFile) {
        val fileSize = element.uri.getFileSize(context)

        countMutex.withLock {
            _count.value += 1
            _currentSessionByteCount.update { it + fileSize }
        }
        channel.send(element)
    }

    suspend fun consume(process: suspend (ImportationFilesManager.PickedFile) -> Unit) {
        for (element in channel) {
            process(element)
            countMutex.withLock {
                _count.value -= 1
                if (_count.value == 0) {
                    _currentSessionByteCount.update { 0 }
                    resetCopiedBytes()
                }
            }
        }
    }

    companion object {
        private val TAG = TransferCountChannel::class.java.simpleName

        private fun Uri.getFileSize(context: Context): Long {
            return context.contentResolver.query(this, null, null, null, null)?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                if (sizeIndex != -1 && cursor.moveToFirst()) {
                    cursor.getLong(sizeIndex)
                } else {
                    null
                }
            } ?: run {
                SentryLog.e(TAG, "Could not read file size when importing a new file")
                0
            }
        }
    }
}
