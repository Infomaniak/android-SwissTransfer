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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.SmallFileItem
import com.infomaniak.swisstransfer.ui.components.SmallFileTileSize
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun TransferFilePreview(
    file: FileUi? = null,
    isFirstItem: Boolean = false,
    remainingFilesCount: Int? = null,
) {
    Row {
        if (!isFirstItem || remainingFilesCount != null) Spacer(modifier = Modifier.width(Margin.Mini))
        if (file != null) {
            // TODO: Temporary code to be able to test the view, while waiting real Transfers data
            // AsyncImage(
            //     model = ImageRequest.Builder(LocalContext.current)
            //         .data(randomTestImageUrl()) // TODO: Use the `fileUi.localPath` (probably?) to generate file's preview instead of this hard-coded value
            //         .crossfade(true)
            //         .build(),
            //     contentDescription = null,
            //     contentScale = ContentScale.Crop,
            //     onError = { }, // TODO ?
            //     modifier = Modifier
            //         .size(Margin.Giant)
            //         .clip(CustomShapes.SMALL)
            //         .background(SwissTransferTheme.colors.transferFilePreviewOverflow),
            // )
            SmallFileItem(file = file, smallFileTileSize = SmallFileTileSize.SMALL)
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(Margin.Giant)
                    .clip(CustomShapes.SMALL)
                    .background(SwissTransferTheme.colors.transferFilePreviewOverflow),
            ) {
                Text(
                    text = "+$remainingFilesCount",
                    color = SwissTransferTheme.colors.onTransferFilePreviewOverflow,
                    style = SwissTransferTheme.typography.bodyRegular,
                )
            }
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Row {
                TransferFilePreview(
                    file = FileUi(
                        fileName = "The 5-Step Guide to Not Breaking Your Code.txt",
                        uid = "The 5-Step Guide to Not Breaking Your Code.txt",
                        fileSize = 57_689_032L,
                        mimeType = null,
                        localPath = "",
                    ),
                    isFirstItem = true,
                )
                TransferFilePreview(
                    file =
                    FileUi(
                        fileName = "Introduction to Turning It Off and On Again.pptx",
                        uid = "Introduction to Turning It Off and On Again.pptx",
                        fileSize = 89_723_143L,
                        mimeType = null,
                        localPath = "",
                    ),
                )
                TransferFilePreview(remainingFilesCount = 42)
            }
        }
    }
}
