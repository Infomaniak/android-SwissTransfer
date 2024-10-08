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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.PaintbrushPalette: ImageVector
    get() {

        if (_paintbrushPalette != null) return _paintbrushPalette!!

        _paintbrushPalette = Builder(
            name = "PaintbrushPalette",
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
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(10.644f, 17.865f)
                    curveToRelative(1.589f, -1.9f, 0.1f, -4.338f, 1.513f, -5.981f)
                    arcToRelative(2.759f, 2.759f, 0.0f, true, true, 4.18f, 3.6f)
                    arcToRelative(6.5f, 6.5f, 0.0f, false, true, -5.693f, 2.38f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(16.62f, 12.277f)
                    lineToRelative(6.232f, -8.253f)
                    arcTo(2.037f, 2.037f, 0.0f, true, false, 19.4f, 1.88f)
                    lineTo(14.871f, 11.0f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(13.5f, 5.225f)
                    horizontalLineTo(3.75f)
                    arcToRelative(3.0f, 3.0f, 0.0f, false, false, -3.0f, 3.0f)
                    verticalLineToRelative(12.0f)
                    arcToRelative(3.0f, 3.0f, 0.0f, false, false, 3.0f, 3.0f)
                    horizontalLineToRelative(12.0f)
                    arcToRelative(3.0f, 3.0f, 0.0f, false, false, 3.0f, -3.0f)
                    verticalLineToRelative(-1.5f)
                }
            }
        }.build()

        return _paintbrushPalette!!
    }

private var _paintbrushPalette: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.PaintbrushPalette,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
