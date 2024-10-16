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
package com.infomaniak.swisstransfer.ui.screen.main.sent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.components.FileUiItem
import com.infomaniak.swisstransfer.ui.components.FileTileList
import com.infomaniak.swisstransfer.ui.components.FileItem
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun SentListScreen(transfers: List<Any>) {
    val transfers = listOf(object : FileUiItem {
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
    FileTileList(
        modifier = Modifier.padding(Margin.Medium),
        files = transfers,
        isRemoveButtonVisible = true,
        isCheckboxVisible = false,
        isUidChecked = { false },
        setUidCheckStatus = { _, _ -> },
        onRemoveUid = {},
    )
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun SentListScreenPreview() {
    SwissTransferTheme {
        Surface {
            SentListScreen(transfers = listOf(Unit))
        }
    }
}
