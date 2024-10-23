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

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TransferCountChannel {
    private val channel = Channel<ImportationFilesManager.PickedFile>(capacity = Channel.UNLIMITED)

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count

    // Session resets when reaching 0 files in the queue
    private val _currentSessionTotalUploadedFiles = MutableStateFlow(0)
    val currentSessionTotalUploadedFiles: StateFlow<Int> = _currentSessionTotalUploadedFiles

    private val countMutex = Mutex()

    suspend fun send(element: ImportationFilesManager.PickedFile) {
        countMutex.withLock {
            _count.value += 1
            _currentSessionTotalUploadedFiles.value += 1
        }
        channel.send(element)
    }

    suspend fun consume(process: suspend (ImportationFilesManager.PickedFile) -> Unit) {
        for (element in channel) {
            process(element)
            countMutex.withLock {
                val newValue = _count.value - 1
                _count.value = newValue
                if (newValue == 0) _currentSessionTotalUploadedFiles.value = 0
            }
        }
    }
}
