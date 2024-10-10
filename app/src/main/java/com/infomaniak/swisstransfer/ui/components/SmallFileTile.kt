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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import com.infomaniak.swisstransfer.ui.utils.fileType
import com.infomaniak.swisstransfer.ui.utils.hasPreview

@Composable
fun SmallFileTile(file: FileUiItem, smallFileTileSize: SmallFileTileSize, onRemove: (() -> Unit)? = null) {
    var displayPreview by rememberSaveable { mutableStateOf(file.hasPreview) }

    Box(
        Modifier
            .size(smallFileTileSize.size)
            .clip(smallFileTileSize.shape)
            .background(SwissTransferTheme.materialColors.surface),
    ) {
        if (displayPreview) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(file.uri)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onError = { displayPreview = false },
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            FileIcon(
                file.fileType,
                color = SwissTransferTheme.materialColors.surfaceContainerHighest,
                circleSize = smallFileTileSize.iconCircleSize(),
            )
        }

        onRemove?.let { CrossCircleButton(onClick = it, size = 40.dp) }
    }
}

enum class SmallFileTileSize(val size: Dp, val shape: Shape) {
    SMALL(48.dp, Shapes.small),
    LARGE(80.dp, Shapes.medium);

    fun iconCircleSize(): Dp = 2f / 3 * size
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun SmallFileTilePreview() {
    SwissTransferTheme {
        Surface(color = SwissTransferTheme.materialColors.surfaceContainerHighest) {
            Column(Modifier.padding(16.dp)) {
                SmallFileTile(
                    object : FileUiItem {
                        override val fileName: String = "How to not get fired.pdf"
                        override val uid: String = fileName
                        override val fileSizeInBytes: Long = 10302130
                        override val mimeType: String? = null
                        override val uri: String = ""
                    },
                    SmallFileTileSize.LARGE,
                    {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                SmallFileTile(
                    object : FileUiItem {
                        override val fileName: String = "How to not get fired.pdf"
                        override val uid: String = fileName
                        override val fileSizeInBytes: Long = 10302130
                        override val mimeType: String? = null
                        override val uri: String = ""
                    },
                    SmallFileTileSize.SMALL,
                )
            }
        }
    }
}
