/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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

val AppIcons.ArrowCircle: ImageVector
    get() {
        if (_arrowCircle != null) {
            return _arrowCircle!!
        }
        _arrowCircle = Builder(
            name = "ArrowCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.95332f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound
            ) {
                moveTo(5.786f, 7.123f)
                curveTo(6.842f, 5.843f, 8.267f, 4.921f, 9.867f, 4.482f)
                curveTo(11.466f, 4.043f, 13.162f, 4.108f, 14.723f, 4.669f)
                curveTo(16.284f, 5.229f, 17.634f, 6.258f, 18.589f, 7.614f)
                curveTo(19.543f, 8.971f, 20.056f, 10.589f, 20.057f, 12.247f)
                lineTo(20.057f, 14.448f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.95332f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound
            ) {
                moveTo(17.86f, 17.776f)
                curveTo(16.753f, 18.949f, 15.319f, 19.763f, 13.743f, 20.112f)
                curveTo(12.168f, 20.461f, 10.525f, 20.33f, 9.025f, 19.734f)
                curveTo(7.526f, 19.138f, 6.24f, 18.106f, 5.334f, 16.771f)
                curveTo(4.427f, 15.436f, 3.943f, 13.86f, 3.943f, 12.247f)
                lineTo(3.943f, 10.782f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.95332f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound
            ) {
                moveTo(22.987f, 11.515f)
                lineTo(20.057f, 14.444f)
                lineTo(17.128f, 11.515f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.95332f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound
            ) {
                moveTo(1.013f, 13.712f)
                lineTo(3.943f, 10.782f)
                lineTo(6.873f, 13.712f)
            }
        }
            .build()
        return _arrowCircle!!
    }

private var _arrowCircle: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.ArrowCircle,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}

