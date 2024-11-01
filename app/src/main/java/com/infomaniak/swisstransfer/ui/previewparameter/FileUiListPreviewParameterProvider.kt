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
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import java.util.UUID

class FileUiListPreviewParameter : PreviewParameterProvider<List<FileUi>> {
    override val values: Sequence<List<FileUi>> = sequenceOf(filesPreviewData)
}

val filesPreviewData = listOf(
    FileUi(
        // Non-preview file (i.e. pdf, txt, etc.)
        fileName = "How to not get fired.pdf",
        uid = "How to not get fired.pdf",
        fileSize = 10_302_130L,
        mimeType = null,
        localPath = "",
    ),
    FileUi(
        // Preview file (i.e. png, jpg, etc.)
        fileName = "Opening images tutorial.png",
        uid = "Opening images tutorial.png",
        fileSize = 456_782L,
        mimeType = null,
        localPath = "https://picsum.photos/200/300",
    ),
    FileUi(
        fileName = "The 5 step guide to turning it off and on again.docx",
        uid = "The 5 step guide to turning it off and on again.docx",
        fileSize = 89_723_143L,
        mimeType = null,
        localPath = "",
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "Learning to Copy and Paste: A Complete Guide.docx",
        fileSize = 237_866_728L,
        mimeType = null,
        localPath = null,
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "Introduction to Turning It Off and On Again.pptx",
        fileSize = 98_723_143L,
        mimeType = null,
        localPath = null,
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "The 5-Step Guide to Not Breaking Your Code.txt",
        fileSize = 57_689_032L,
        mimeType = null,
        localPath = null,
    ),
)
