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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun FileItemList(
    modifier: Modifier = Modifier,
    files: List<FileUiItem>,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: Boolean,
    isUidChecked: (String) -> Boolean,
    setUidCheckStatus: (String, Boolean) -> Unit,
    onRemoveUid: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(Margin.Medium),
        horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
    ) {
        items(files, key = { it.uid }) { file ->
            FileItem(
                file = file,
                isRemoveButtonVisible = isRemoveButtonVisible,
                isCheckboxVisible = isCheckboxVisible,
                isChecked = { isUidChecked(file.uid) },
                onClick = { if (isCheckboxVisible) setUidCheckStatus(file.uid, !isUidChecked(file.uid)) },
                onRemove = { onRemoveUid(file.uid) },
            )
        }
    }
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun FileItemListPreview() {
    val files = listOf(object : FileUiItem {
        override val fileName: String = "The 5-Step Guide to Not Breaking Your Code.txt"
        override val uid: String = fileName
        override val fileSizeInBytes: Long = 57689032
        override val mimeType: String? = null
        override val uri: String = ""
    }, object : FileUiItem {
        override val fileName: String = "Introduction to Turning It Off and On Again.pptx"
        override val uid: String = fileName
        override val fileSizeInBytes: Long = 89723143
        override val mimeType: String? = null
        override val uri: String = ""
    }, object : FileUiItem {
        override val fileName: String = "Learning to Copy and Paste: A Complete Guide.docx"
        override val uid: String = fileName
        override val fileSizeInBytes: Long = 237866728
        override val mimeType: String? = null
        override val uri: String = ""
    })

    SwissTransferTheme {
        FileItemList(
            files = files,
            isRemoveButtonVisible = false,
            isCheckboxVisible = true,
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = {},
        )
    }
}