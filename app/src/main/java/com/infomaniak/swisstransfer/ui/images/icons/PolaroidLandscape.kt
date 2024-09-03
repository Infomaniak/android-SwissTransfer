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
package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.PolaroidLandscape: ImageVector
    get() {

        if (_polaroidLandscape != null) return _polaroidLandscape!!

        _polaroidLandscape = Builder(
            name = "PolaroidLandscape",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(21.75f, 0.75f)
                    horizontalLineTo(2.25f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, 1.5f)
                    verticalLineToRelative(19.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, false, 1.5f, 1.5f)
                    horizontalLineToRelative(19.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, false, 1.5f, -1.5f)
                    verticalLineTo(2.25f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, -1.5f)
                    close()
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(5.25f, 17.25f)
                    lineToRelative(3.462f, -4.616f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 2.261f, -0.161f)
                    lineTo(12.0f, 13.5f)
                    lineToRelative(3.3f, -4.4f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 2.4f, 0.0f)
                    lineToRelative(2.67f, 3.56f)
                    moveTo(6.375f, 8.25f)
                    arcToRelative(1.875f, 1.875f, 0.0f, true, false, 0.0f, -3.75f)
                    arcToRelative(1.875f, 1.875f, 0.0f, false, false, 0.0f, 3.75f)
                    close()
                    moveToRelative(-5.625f, 9.0f)
                    horizontalLineToRelative(22.5f)
                }
            }
        }.build()

        return _polaroidLandscape!!
    }

private var _polaroidLandscape: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.PolaroidLandscape,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
