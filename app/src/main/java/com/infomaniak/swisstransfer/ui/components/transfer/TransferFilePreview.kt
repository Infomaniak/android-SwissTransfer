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
package com.infomaniak.swisstransfer.ui.components.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.SmallFileItem
import com.infomaniak.swisstransfer.ui.components.SmallFileTileSize
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferFilePreview(file: FileUi? = null, remainingFilesCount: Int? = null) {
    Row {
        if (file == null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(Margin.Giant)
                    .clip(CustomShapes.SMALL)
                    .background(SwissTransferTheme.colors.transferFilePreviewOverflow),
            ) {
                Text(
                    text = "+$remainingFilesCount",
                    style = SwissTransferTheme.typography.bodyRegular,
                    color = SwissTransferTheme.colors.onTransferFilePreviewOverflow,
                )
            }
        } else {
            SmallFileItem(file = file, smallFileTileSize = SmallFileTileSize.SMALL)
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            Card(
                shape = CustomShapes.NONE,
                colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.surfaceContainerHighest),
            ) {
                Row(
                    modifier = Modifier.padding(Margin.Mini),
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                ) {
                    TransferFilePreview(file = files[0])
                    TransferFilePreview(file = files[1])
                    TransferFilePreview(remainingFilesCount = 42)
                }
            }
        }
    }
}
