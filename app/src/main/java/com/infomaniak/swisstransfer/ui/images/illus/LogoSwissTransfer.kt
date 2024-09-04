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
package com.infomaniak.swisstransfer.ui.images.illus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.LogoSwissTransfer: ImageVector
    get() {

        if (_logoSwissTransfer != null) return _logoSwissTransfer!!

        _logoSwissTransfer = Builder(
            name = "LogoSwissTransfer",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(0.0f, 4.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, 4.0f, -4.0f)
                horizontalLineToRelative(16.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, 4.0f, 4.0f)
                verticalLineToRelative(16.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -4.0f, 4.0f)
                horizontalLineTo(4.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -4.0f, -4.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF5AC78A)),
                stroke = null,
                fillAlpha = 0.5f,
                strokeAlpha = 0.5f,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd,
            ) {
                moveTo(8.783f, 4.797f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, 1.04f, -0.761f)
                lineTo(18.67f, 6.73f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, 0.568f, 0.76f)
                verticalLineToRelative(8.686f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, -1.04f, 0.761f)
                lineTo(9.35f, 14.243f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, -0.568f, -0.76f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF4EC483)),
                stroke = null,
                fillAlpha = 0.8f,
                strokeAlpha = 0.8f,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd,
            ) {
                moveTo(6.772f, 6.31f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, 1.041f, -0.761f)
                lineToRelative(8.847f, 2.695f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, 0.567f, 0.76f)
                verticalLineToRelative(8.686f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, -1.04f, 0.761f)
                lineTo(7.34f, 15.756f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, -0.568f, -0.76f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3BB471)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd,
            ) {
                moveTo(4.762f, 7.823f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, 1.04f, -0.761f)
                lineToRelative(8.847f, 2.695f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, 0.568f, 0.76f)
                verticalLineToRelative(8.686f)
                arcToRelative(0.803f, 0.803f, 0.0f, false, true, -1.04f, 0.761f)
                lineTo(5.33f, 17.27f)
                arcToRelative(0.8f, 0.8f, 0.0f, false, true, -0.568f, -0.76f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd,
            ) {
                moveTo(11.131f, 10.806f)
                arcToRelative(0.7f, 0.7f, 0.0f, false, true, 0.179f, 0.232f)
                lineToRelative(1.584f, 5.625f)
                curveToRelative(0.119f, 0.254f, 0.039f, 0.502f, -0.178f, 0.556f)
                arcToRelative(0.4f, 0.4f, 0.0f, false, true, -0.215f, -0.01f)
                lineToRelative(-1.438f, -0.431f)
                lineToRelative(0.068f, -1.972f)
                lineToRelative(-1.3f, 1.628f)
                lineToRelative(-2.672f, -0.827f)
                curveToRelative(-0.247f, -0.074f, -0.448f, -0.335f, -0.448f, -0.583f)
                quadToRelative(0.0f, -0.106f, 0.047f, -0.185f)
                lineToRelative(0.9f, -2.213f)
                curveToRelative(0.11f, -0.19f, 0.379f, -0.2f, 0.6f, -0.024f)
                curveToRelative(0.088f, 0.07f, 0.16f, 0.163f, 0.203f, 0.264f)
                lineToRelative(0.491f, 0.828f)
                lineToRelative(1.572f, -2.892f)
                curveToRelative(0.118f, -0.182f, 0.39f, -0.18f, 0.607f, 0.004f)
            }
        }.build()

        return _logoSwissTransfer!!
    }

private var _logoSwissTransfer: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.LogoSwissTransfer,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
