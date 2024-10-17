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

val AppIcons.SentryLight: ImageVector
    get() {

        if (_sentryLight != null) return _sentryLight!!

        _sentryLight = Builder(
            name = "SentryLight",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.922f, 2.012f)
                curveTo(13.784f, 1.752f, 13.595f, 1.523f, 13.366f, 1.338f)
                curveTo(13.136f, 1.154f, 12.872f, 1.018f, 12.589f, 0.939f)
                curveTo(12.305f, 0.86f, 12.009f, 0.839f, 11.717f, 0.878f)
                curveTo(11.426f, 0.917f, 11.145f, 1.016f, 10.893f, 1.167f)
                curveTo(10.553f, 1.372f, 10.273f, 1.664f, 10.082f, 2.012f)
                lineTo(6.921f, 7.718f)
                lineTo(7.727f, 8.201f)
                curveTo(9.971f, 9.582f, 11.846f, 11.488f, 13.19f, 13.754f)
                curveTo(14.533f, 16.02f, 15.306f, 18.579f, 15.441f, 21.211f)
                horizontalLineTo(13.222f)
                curveTo(13.086f, 18.979f, 12.414f, 16.812f, 11.263f, 14.895f)
                curveTo(10.112f, 12.977f, 8.516f, 11.365f, 6.61f, 10.195f)
                lineTo(5.803f, 9.714f)
                lineTo(2.858f, 15.051f)
                lineTo(3.665f, 15.533f)
                curveTo(4.654f, 16.149f, 5.492f, 16.979f, 6.119f, 17.961f)
                curveTo(6.745f, 18.944f, 7.144f, 20.054f, 7.286f, 21.211f)
                horizontalLineTo(2.218f)
                curveTo(2.119f, 21.21f, 2.024f, 21.171f, 1.955f, 21.1f)
                curveTo(1.885f, 21.03f, 1.846f, 20.935f, 1.846f, 20.836f)
                curveTo(1.845f, 20.767f, 1.862f, 20.699f, 1.896f, 20.638f)
                lineTo(3.308f, 18.087f)
                curveTo(2.835f, 17.67f, 2.288f, 17.345f, 1.696f, 17.128f)
                lineTo(0.298f, 19.673f)
                curveTo(0.003f, 20.204f, -0.077f, 20.827f, 0.075f, 21.415f)
                curveTo(0.227f, 22.002f, 0.6f, 22.509f, 1.115f, 22.83f)
                curveTo(1.448f, 23.03f, 1.829f, 23.136f, 2.218f, 23.138f)
                horizontalLineTo(9.193f)
                verticalLineTo(22.176f)
                curveTo(9.202f, 20.668f, 8.861f, 19.179f, 8.196f, 17.826f)
                curveTo(7.532f, 16.472f, 6.563f, 15.291f, 5.364f, 14.376f)
                lineTo(6.479f, 12.372f)
                curveTo(8.013f, 13.501f, 9.259f, 14.977f, 10.115f, 16.679f)
                curveTo(10.97f, 18.382f, 11.411f, 20.262f, 11.4f, 22.167f)
                verticalLineTo(23.132f)
                horizontalLineTo(17.314f)
                verticalLineTo(22.169f)
                curveTo(17.33f, 19.196f, 16.621f, 16.264f, 15.249f, 13.626f)
                curveTo(13.876f, 10.989f, 11.882f, 8.725f, 9.437f, 7.033f)
                lineTo(11.681f, 2.976f)
                curveTo(11.704f, 2.933f, 11.735f, 2.895f, 11.773f, 2.865f)
                curveTo(11.811f, 2.835f, 11.855f, 2.812f, 11.901f, 2.799f)
                curveTo(11.948f, 2.786f, 11.997f, 2.783f, 12.045f, 2.79f)
                curveTo(12.093f, 2.796f, 12.139f, 2.812f, 12.181f, 2.837f)
                curveTo(12.237f, 2.871f, 12.283f, 2.919f, 12.315f, 2.976f)
                lineTo(22.097f, 20.638f)
                curveTo(22.146f, 20.726f, 22.159f, 20.828f, 22.134f, 20.925f)
                curveTo(22.11f, 21.022f, 22.049f, 21.106f, 21.964f, 21.159f)
                curveTo(21.907f, 21.195f, 21.841f, 21.212f, 21.774f, 21.211f)
                horizontalLineTo(19.486f)
                curveTo(19.514f, 21.855f, 19.518f, 22.496f, 19.486f, 23.141f)
                horizontalLineTo(21.782f)
                curveTo(22.383f, 23.128f, 22.954f, 22.878f, 23.369f, 22.444f)
                curveTo(23.785f, 22.011f, 24.012f, 21.43f, 24.0f, 20.829f)
                curveTo(24.0f, 20.426f, 23.898f, 20.028f, 23.703f, 19.675f)
                lineTo(13.922f, 2.012f)
                close()
            }
        }.build()

        return _sentryLight!!
    }

private var _sentryLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.SentryLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
