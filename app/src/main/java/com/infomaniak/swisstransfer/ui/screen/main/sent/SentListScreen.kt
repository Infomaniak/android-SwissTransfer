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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.components.FileUiItem
import com.infomaniak.swisstransfer.ui.components.ImageTile
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun SentListScreen(transfers: List<Any>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(Margin.Medium),
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(Margin.Medium),
        horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
    ) {
        val imageFile = object : FileUiItem {
            override val fileName: String = "Time-Clock-Circle--Streamline-Ultimate.svg (1).png"
            override val uid: String = fileName
            override val fileSizeInBytes: Long = 456782
            override val mimeType: String? = null
            override val uri: String = "httttttps://picsum.photos/200/300"
        }

        items(3) {
            ImageTile(
                file = imageFile,
                isRemoveButtonVisible = true,
                isCheckboxVisible = true,
                isChecked = { true },
                onClick = {},
                onRemove = {})
        }
    }
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
