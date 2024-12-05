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
        uid = "How to not get fired.pdf",
        fileName = "How to not get fired.pdf",
        isFolder = false,
        fileSize = 10_302_130L,
        mimeType = null,
        localPath = "",
        path = "",
    ),
    FileUi(
        // Preview file (i.e. png, jpg, etc.)
        uid = "Opening images tutorial.png",
        fileName = "Opening images tutorial.png",
        isFolder = false,
        fileSize = 456_782L,
        mimeType = null,
        localPath = "https://picsum.photos/200/300",
        path = "",
    ),
    FileUi(
        uid = "The 5 step guide to turning it off and on again.docx",
        fileName = "The 5 step guide to turning it off and on again.docx",
        isFolder = false,
        fileSize = 89_723_143L,
        mimeType = null,
        localPath = "",
        path = "",
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "Learning to Copy and Paste: A Complete Guide.docx",
        isFolder = false,
        fileSize = 237_866_728L,
        mimeType = null,
        localPath = null,
        path = "",
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "Introduction to Turning It Off and On Again.pptx",
        isFolder = false,
        fileSize = 98_723_143L,
        mimeType = null,
        localPath = null,
        path = "",
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "The 5-Step Guide to Not Breaking Your Code.txt",
        isFolder = false,
        fileSize = 57_689_032L,
        mimeType = null,
        localPath = null,
        path = "",
    ),
    FileUi(
        uid = UUID.randomUUID().toString(),
        fileName = "Secret foldert",
        isFolder = true,
        fileSize = 57_689_032L,
        mimeType = null,
        localPath = null,
        path = "",
    ),
)
