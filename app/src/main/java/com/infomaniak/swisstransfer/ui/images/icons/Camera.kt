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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Camera: ImageVector
    get() {

        if (_camera != null) return _camera!!

        _camera = Builder(
            name = "Camera",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.469f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(2.698f, 7.115f)
                lineToRelative(0.027f, -0.98f)
                arcToRelative(0.995f, 0.995f, 0.0f, false, true, 0.952f, -0.979f)
                horizontalLineTo(4.6f)
                arcToRelative(1.046f, 1.046f, 0.0f, false, true, 1.034f, 0.98f)
                verticalLineToRelative(0.978f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.469f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(19.96f, 7.115f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, true, -0.886f, -0.565f)
                curveToRelative(-0.531f, -1.137f, -1.101f, -2.373f, -2.178f, -2.373f)
                horizontalLineTo(12.0f)
                curveToRelative(-0.912f, 0.0f, -1.37f, 0.817f, -2.644f, 2.538f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, true, -0.783f, 0.4f)
                horizontalLineTo(2.698f)
                curveToRelative(-1.469f, 0.0f, -1.959f, 0.664f, -1.959f, 1.687f)
                verticalLineToRelative(10.171f)
                curveToRelative(0.0f, 1.022f, 0.49f, 1.85f, 2.014f, 1.85f)
                horizontalLineToRelative(18.494f)
                curveToRelative(1.524f, 0.0f, 2.014f, -0.828f, 2.014f, -1.85f)
                verticalLineTo(8.802f)
                curveToRelative(0.0f, -1.023f, -0.49f, -1.687f, -2.014f, -1.687f)
                close()
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.469f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(14.448f, 18.865f)
                arcToRelative(5.386f, 5.386f, 0.0f, true, false, 0.0f, -10.771f)
                arcToRelative(5.386f, 5.386f, 0.0f, false, false, 0.0f, 10.77f)
                close()
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.469f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(14.448f, 16.417f)
                arcToRelative(2.937f, 2.937f, 0.0f, true, false, 0.0f, -5.875f)
                arcToRelative(2.937f, 2.937f, 0.0f, false, false, 0.0f, 5.875f)
                close()
                moveTo(4.167f, 12.01f)
                arcToRelative(1.469f, 1.469f, 0.0f, true, false, 0.0f, -2.937f)
                arcToRelative(1.469f, 1.469f, 0.0f, false, false, 0.0f, 2.937f)
                close()
            }
        }.build()

        return _camera!!
    }

private var _camera: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Camera,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
