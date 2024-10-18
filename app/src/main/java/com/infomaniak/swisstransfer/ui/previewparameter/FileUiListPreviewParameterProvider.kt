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
package com.infomaniak.swisstransfer.ui.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.infomaniak.swisstransfer.ui.components.FileUiItem

class FileUiListPreviewParameter : PreviewParameterProvider<List<FileUiItem>> {
    override val values: Sequence<List<FileUiItem>> = sequenceOf(
        listOf(
            object : FileUiItem { // Non-image file
                override val fileName: String = "How to not get fired.pdf"
                override val uid: String = fileName
                override val fileSizeInBytes: Long = 10302130
                override val mimeType: String? = null
                override val uri: String = ""
            },
            object : FileUiItem { // Image file
                override val fileName: String = "Opening images tutorial.png"
                override val uid: String = fileName
                override val fileSizeInBytes: Long = 456782
                override val mimeType: String? = null
                override val uri: String = "https://picsum.photos/200/300"
            },
            object : FileUiItem {
                override val fileName: String = "The 5 step guide to turning it off and on again.docx"
                override val uid: String = fileName
                override val fileSizeInBytes: Long = 89723143
                override val mimeType: String? = null
                override val uri: String = ""
            },
        )
    )
}
