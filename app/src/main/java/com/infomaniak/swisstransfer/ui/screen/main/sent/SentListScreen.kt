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
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun SentListScreen(transfers: List<Any>) {
    val transfers = listOf(
        FileUi(
            fileName = "The 5-Step Guide to Not Breaking Your Code.txt",
            uid = "The 5-Step Guide to Not Breaking Your Code.txt",
            fileSizeInBytes = 57689032,
            mimeType = null,
            uri = "",
        ),
        FileUi(
            fileName = "Introduction to Turning It Off and On Again.pptx",
            uid = "Introduction to Turning It Off and On Again.pptx",
            fileSizeInBytes = 89723143,
            mimeType = null,
            uri = "",
        ),
        FileUi(
            fileName = "Learning to Copy and Paste: A Complete Guide.docx",
            uid = "Learning to Copy and Paste: A Complete Guide.docx",
            fileSizeInBytes = 237866728,
            mimeType = null,
            uri = "",
        ),
    )
    FileItemList(
        modifier = Modifier.padding(Margin.Medium),
        files = transfers,
        isRemoveButtonVisible = true,
        isCheckboxVisible = false,
        isUidChecked = { false },
        setUidCheckStatus = { _, _ -> },
        onRemoveUid = {},
    )
}

@PreviewAllWindows
@Composable
private fun SentListScreenPreview() {
    SwissTransferTheme {
        Surface {
            SentListScreen(transfers = listOf(Unit))
        }
    }
}
