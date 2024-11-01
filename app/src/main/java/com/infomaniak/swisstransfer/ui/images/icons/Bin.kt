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

val AppIcons.Bin: ImageVector
    get() {

        if (_bin != null) return _bin!!

        _bin = Builder(
            name = "Bin",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFF7FCFA)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(15.502f, 2.115f)
                horizontalLineTo(11.236f)
                verticalLineTo(1.621f)
                curveTo(11.236f, 1.198f, 11.093f, 0.775f, 10.738f, 0.493f)
                curveTo(10.382f, 0.211f, 10.027f, 0.0f, 9.6f, 0.0f)
                horizontalLineTo(6.471f)
                curveTo(5.973f, 0.0f, 5.618f, 0.211f, 5.333f, 0.493f)
                curveTo(4.978f, 0.775f, 4.836f, 1.198f, 4.836f, 1.621f)
                verticalLineTo(2.185f)
                horizontalLineTo(0.569f)
                curveTo(0.284f, 2.185f, 0.0f, 2.396f, 0.0f, 2.749f)
                curveTo(0.0f, 3.101f, 0.213f, 3.313f, 0.569f, 3.313f)
                horizontalLineTo(1.707f)
                lineTo(2.631f, 14.52f)
                curveTo(2.631f, 14.943f, 2.844f, 15.295f, 3.129f, 15.577f)
                curveTo(3.413f, 15.859f, 3.84f, 16.0f, 4.196f, 16.0f)
                horizontalLineTo(11.804f)
                curveTo(12.231f, 16.0f, 12.587f, 15.859f, 12.871f, 15.577f)
                curveTo(13.156f, 15.295f, 13.369f, 14.943f, 13.369f, 14.52f)
                lineTo(14.293f, 3.313f)
                horizontalLineTo(15.431f)
                curveTo(15.716f, 3.313f, 16.0f, 3.101f, 16.0f, 2.749f)
                curveTo(16.0f, 2.396f, 15.787f, 2.115f, 15.502f, 2.115f)
                close()
                moveTo(5.902f, 1.621f)
                curveTo(5.902f, 1.48f, 5.973f, 1.339f, 6.044f, 1.269f)
                curveTo(6.116f, 1.198f, 6.258f, 1.128f, 6.4f, 1.128f)
                horizontalLineTo(9.6f)
                curveTo(9.742f, 1.128f, 9.884f, 1.198f, 9.956f, 1.269f)
                curveTo(10.027f, 1.339f, 10.169f, 1.48f, 10.169f, 1.621f)
                verticalLineTo(2.185f)
                horizontalLineTo(5.902f)
                verticalLineTo(1.621f)
                close()
                moveTo(12.373f, 14.379f)
                curveTo(12.373f, 14.52f, 12.302f, 14.661f, 12.231f, 14.731f)
                curveTo(12.16f, 14.802f, 12.018f, 14.872f, 11.876f, 14.872f)
                horizontalLineTo(4.196f)
                curveTo(4.053f, 14.872f, 3.911f, 14.802f, 3.84f, 14.731f)
                curveTo(3.769f, 14.661f, 3.698f, 14.52f, 3.698f, 14.379f)
                lineTo(2.773f, 3.172f)
                horizontalLineTo(13.369f)
                lineTo(12.373f, 14.379f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF7FCFA)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(6.471f, 5.85f)
                curveTo(6.116f, 5.85f, 5.902f, 6.062f, 5.902f, 6.414f)
                verticalLineTo(11.7f)
                curveTo(5.902f, 11.982f, 6.116f, 12.194f, 6.471f, 12.194f)
                curveTo(6.827f, 12.194f, 7.04f, 11.982f, 7.04f, 11.63f)
                verticalLineTo(6.414f)
                curveTo(6.969f, 6.062f, 6.756f, 5.85f, 6.471f, 5.85f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF7FCFA)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.6f, 5.85f)
                curveTo(9.316f, 5.85f, 9.031f, 6.062f, 9.031f, 6.414f)
                verticalLineTo(11.7f)
                curveTo(9.031f, 11.982f, 9.244f, 12.264f, 9.6f, 12.264f)
                curveTo(9.956f, 12.264f, 10.169f, 12.053f, 10.169f, 11.7f)
                verticalLineTo(6.414f)
                curveTo(10.169f, 6.062f, 9.956f, 5.85f, 9.6f, 5.85f)
                close()
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
