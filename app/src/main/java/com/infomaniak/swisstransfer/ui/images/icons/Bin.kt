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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Bin: ImageVector
    get() {

        if (_bin != null) return _bin!!

        _bin = Builder(
            name = "Bin",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(20.609f, 4.826f)
                lineTo(18.875f, 21.287f)
                curveTo(18.826f, 21.757f, 18.604f, 22.192f, 18.253f, 22.508f)
                curveTo(17.902f, 22.825f, 17.447f, 23.0f, 16.974f, 23.0f)
                horizontalLineTo(7.026f)
                curveTo(6.553f, 23.0f, 6.097f, 22.825f, 5.746f, 22.509f)
                curveTo(5.395f, 22.192f, 5.173f, 21.757f, 5.124f, 21.287f)
                lineTo(3.391f, 4.826f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(1.0f, 4.826f)
                horizontalLineTo(23.0f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(7.696f, 4.826f)
                verticalLineTo(1.957f)
                curveTo(7.696f, 1.703f, 7.796f, 1.46f, 7.976f, 1.28f)
                curveTo(8.155f, 1.101f, 8.399f, 1.0f, 8.652f, 1.0f)
                horizontalLineTo(15.348f)
                curveTo(15.601f, 1.0f, 15.845f, 1.101f, 16.024f, 1.28f)
                curveTo(16.204f, 1.46f, 16.304f, 1.703f, 16.304f, 1.957f)
                verticalLineTo(4.826f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(10.0f, 9.0f)
                verticalLineTo(17.8f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(14.0f, 9.0f)
                verticalLineTo(17.8f)
            }
        }.build()

        return _bin!!
    }

private var _bin: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Bin,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
