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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FilesDetailsViewModel @Inject constructor() : ViewModel() {

    fun getFilesFromUUID(uuid: String): Flow<List<FileUi>?> {
        // TODO Call the right manager to fetch files from Realm
        val fileUiList = listOf(
            FileUi(
                fileName = "How to not get fired.pdf",
                uid = "How to not get fired.pdf",
                fileSize = 10302130,
                mimeType = null,
                localPath = "",
            ),
            FileUi(
                fileName = "Opening images tutorial.png",
                uid = "Opening images tutorial.png",
                fileSize = 456782,
                mimeType = null,
                localPath = "https://picsum.photos/200/300",
            ),
            FileUi(
                fileName = "The 5 step guide to turning it off and on again.docx",
                uid = "The 5 step guide to turning it off and on again.docx",
                fileSize = 89723143,
                mimeType = null,
                localPath = "",
            ),
        )
        return flow { emit(fileUiList) }.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }
}
