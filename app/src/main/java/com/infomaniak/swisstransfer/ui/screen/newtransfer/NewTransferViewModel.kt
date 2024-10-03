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

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.ui.MainApplication
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferFileUtils.getTransferFile
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewTransferViewModel(application: Application) : AndroidViewModel(application) {
    private val context get() = getApplication<MainApplication>() as Context

    val transferFiles = MutableStateFlow<List<TransferFile>>(emptyList())
    val failedTransferFileCount = MutableSharedFlow<Int>()

    fun addFiles(uris: List<Uri>) {
        viewModelScope.launch {
            val alreadyUsedFileNames = buildSet { transferFiles.value.forEach { add(it.fileName) } }

            var failedFileCount = 0
            uris.forEach { uri ->
                context.getTransferFile(uri, alreadyUsedFileNames)?.let { transferFile ->
                    transferFiles.value += transferFile
                } ?: run {
                    failedFileCount++
                }
            }

            failedTransferFileCount.emit(failedFileCount)
        }
    }
}
