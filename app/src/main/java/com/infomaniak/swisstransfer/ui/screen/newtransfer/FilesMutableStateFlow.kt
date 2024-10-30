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

import com.infomaniak.swisstransfer.ui.components.FileUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File

class FilesMutableStateFlow {
    private val files = MutableStateFlow<List<FileUi>>(emptyList())
    private val mutex = Mutex()

    val flow = files.asStateFlow()

    suspend fun addAll(newFiles: List<FileUi>): Unit = mutex.withLock { files.value += newFiles }
    suspend fun add(newFile: FileUi): Unit = mutex.withLock { files.value += newFile }

    suspend fun removeByUid(uid: String): String? = mutex.withLock {
        val files = files.value.toMutableList()

        val index = files.indexOfFirst { it.uid == uid }.takeIf { it != -1 } ?: return null
        val fileToRemove = files.removeAt(index)

        runCatching { File(fileToRemove.localPath).delete() }

        this.files.value = files

        fileToRemove.fileName
    }
}
