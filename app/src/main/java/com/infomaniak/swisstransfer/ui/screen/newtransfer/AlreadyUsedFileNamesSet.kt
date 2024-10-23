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

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AlreadyUsedFileNamesSet {
    private val alreadyUsedFileNames = mutableSetOf<String>()
    val mutex = Mutex()

    // No need for the mutex because this code is already called inside of the lock
    fun contains(fileName: String): Boolean = alreadyUsedFileNames.contains(fileName)
    fun add(fileName: String): Boolean = alreadyUsedFileNames.add(fileName)

    suspend fun addAll(filesNames: List<String>): Boolean = mutex.withLock { alreadyUsedFileNames.addAll(filesNames) }
    suspend fun remove(filesName: String): Boolean = mutex.withLock { alreadyUsedFileNames.remove(filesName) }
}
