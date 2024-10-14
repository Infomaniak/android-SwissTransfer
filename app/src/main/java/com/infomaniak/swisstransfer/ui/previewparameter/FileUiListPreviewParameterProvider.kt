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
            FileUiItem(
                fileName = "How to not get fired.pdf",
                uid = "How to not get fired.pdf",
                fileSizeInBytes = 10302130,
                mimeType = null,
                uri = "",
            ),
            FileUiItem(
                fileName = "Opening images tutorial.png",
                uid = "Opening images tutorial.png",
                fileSizeInBytes = 456782,
                mimeType = null,
                uri = "https://picsum.photos/200/300",
            ),
            FileUiItem(
                fileName = "The 5 step guide to turning it off and on again.docx",
                uid = "The 5 step guide to turning it off and on again.docx",
                fileSizeInBytes = 89723143,
                mimeType = null,
                uri = "",
            ),
        )
    )
}
