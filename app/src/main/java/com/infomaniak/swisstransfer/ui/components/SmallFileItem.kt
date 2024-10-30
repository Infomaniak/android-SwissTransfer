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

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SmallFileItem(
    modifier: Modifier = Modifier,
    file: FileUi,
    smallFileTileSize: SmallFileTileSize,
    onRemove: (() -> Unit)? = null,
) {
    Box(
        modifier
            .size(smallFileTileSize.size)
            .clip(smallFileTileSize.shape)
            .background(SwissTransferTheme.materialColors.surface),
    ) {
        FilePreview(
            file = file,
            circleColor = SwissTransferTheme.materialColors.surfaceContainerHighest,
            circleSize = smallFileTileSize.iconCircleSize(),
        )

        onRemove?.let { CrossCircleButton(onClick = it, size = 40.dp) }
    }
}

enum class SmallFileTileSize(val size: Dp, val shape: Shape) {
    SMALL(48.dp, CustomShapes.SMALL),
    LARGE(80.dp, CustomShapes.MEDIUM);

    fun iconCircleSize(): Dp = 2f / 3 * size
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SmallFileItemPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface(color = SwissTransferTheme.materialColors.surfaceContainerHighest) {
            Column(Modifier.padding(16.dp)) {
                val file = files.first()

                SmallFileItem(
                    file = file,
                    smallFileTileSize = SmallFileTileSize.LARGE,
                    onRemove = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                SmallFileItem(
                    file = file,
                    smallFileTileSize = SmallFileTileSize.SMALL,
                )
            }
        }
    }
}
