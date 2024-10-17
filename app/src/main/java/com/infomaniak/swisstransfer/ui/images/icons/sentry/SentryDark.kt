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
package com.infomaniak.swisstransfer.ui.images.icons.sentry

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

val AppIcons.SentryDark: ImageVector
    get() {

        if (_sentryDark != null) return _sentryDark!!

        _sentryDark = Builder(
            name = "SentryDark",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.922f, 2.012f)
                curveTo(13.784f, 1.752f, 13.595f, 1.523f, 13.366f, 1.339f)
                curveTo(13.136f, 1.154f, 12.872f, 1.018f, 12.589f, 0.939f)
                curveTo(12.305f, 0.86f, 12.009f, 0.84f, 11.717f, 0.879f)
                curveTo(11.426f, 0.918f, 11.145f, 1.016f, 10.893f, 1.167f)
                curveTo(10.553f, 1.373f, 10.273f, 1.664f, 10.082f, 2.012f)
                lineTo(6.921f, 7.719f)
                lineTo(7.727f, 8.201f)
                curveTo(9.971f, 9.582f, 11.846f, 11.488f, 13.19f, 13.754f)
                curveTo(14.533f, 16.021f, 15.306f, 18.58f, 15.441f, 21.211f)
                horizontalLineTo(13.222f)
                curveTo(13.086f, 18.979f, 12.414f, 16.813f, 11.263f, 14.895f)
                curveTo(10.112f, 12.978f, 8.516f, 11.366f, 6.61f, 10.196f)
                lineTo(5.803f, 9.715f)
                lineTo(2.858f, 15.051f)
                lineTo(3.665f, 15.533f)
                curveTo(4.654f, 16.149f, 5.492f, 16.979f, 6.119f, 17.962f)
                curveTo(6.745f, 18.944f, 7.144f, 20.055f, 7.286f, 21.211f)
                horizontalLineTo(2.218f)
                curveTo(2.119f, 21.211f, 2.024f, 21.171f, 1.955f, 21.101f)
                curveTo(1.885f, 21.031f, 1.846f, 20.935f, 1.846f, 20.837f)
                curveTo(1.845f, 20.767f, 1.862f, 20.699f, 1.896f, 20.639f)
                lineTo(3.308f, 18.088f)
                curveTo(2.835f, 17.671f, 2.288f, 17.346f, 1.696f, 17.129f)
                lineTo(0.298f, 19.674f)
                curveTo(0.003f, 20.204f, -0.077f, 20.827f, 0.075f, 21.415f)
                curveTo(0.227f, 22.003f, 0.6f, 22.509f, 1.115f, 22.83f)
                curveTo(1.448f, 23.031f, 1.829f, 23.137f, 2.218f, 23.138f)
                horizontalLineTo(9.193f)
                verticalLineTo(22.176f)
                curveTo(9.202f, 20.669f, 8.861f, 19.179f, 8.196f, 17.826f)
                curveTo(7.532f, 16.472f, 6.563f, 15.292f, 5.364f, 14.377f)
                lineTo(6.479f, 12.373f)
                curveTo(8.013f, 13.501f, 9.259f, 14.977f, 10.115f, 16.68f)
                curveTo(10.97f, 18.382f, 11.411f, 20.263f, 11.4f, 22.168f)
                verticalLineTo(23.133f)
                horizontalLineTo(17.314f)
                verticalLineTo(22.169f)
                curveTo(17.33f, 19.196f, 16.621f, 16.264f, 15.249f, 13.626f)
                curveTo(13.876f, 10.989f, 11.882f, 8.726f, 9.437f, 7.033f)
                lineTo(11.681f, 2.976f)
                curveTo(11.704f, 2.933f, 11.735f, 2.896f, 11.773f, 2.865f)
                curveTo(11.811f, 2.835f, 11.855f, 2.813f, 11.901f, 2.8f)
                curveTo(11.948f, 2.787f, 11.997f, 2.783f, 12.045f, 2.79f)
                curveTo(12.093f, 2.796f, 12.139f, 2.813f, 12.181f, 2.837f)
                curveTo(12.237f, 2.871f, 12.283f, 2.919f, 12.315f, 2.976f)
                lineTo(22.097f, 20.639f)
                curveTo(22.146f, 20.726f, 22.159f, 20.829f, 22.134f, 20.926f)
                curveTo(22.11f, 21.023f, 22.049f, 21.106f, 21.964f, 21.16f)
                curveTo(21.907f, 21.195f, 21.841f, 21.213f, 21.774f, 21.211f)
                horizontalLineTo(19.486f)
                curveTo(19.514f, 21.856f, 19.518f, 22.497f, 19.486f, 23.141f)
                horizontalLineTo(21.782f)
                curveTo(22.383f, 23.128f, 22.954f, 22.878f, 23.369f, 22.444f)
                curveTo(23.785f, 22.011f, 24.012f, 21.43f, 24.0f, 20.83f)
                curveTo(24.0f, 20.426f, 23.898f, 20.029f, 23.703f, 19.675f)
                lineTo(13.922f, 2.012f)
                close()
            }
        }.build()

        return _sentryDark!!
    }

private var _sentryDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.SentryDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
