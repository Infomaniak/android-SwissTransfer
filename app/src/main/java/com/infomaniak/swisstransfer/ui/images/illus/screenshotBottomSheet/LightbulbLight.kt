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
package com.infomaniak.swisstransfer.ui.images.illus.screenshotBottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.LightbulbLight: ImageVector
    get() {
        if (_lightbulbLight != null) {
            return _lightbulbLight!!
        }
        _lightbulbLight =
            Builder(
                    name = "LightbulbLight",
                    defaultWidth = 64.0.dp,
                    defaultHeight = 100.0.dp,
                    viewportWidth = 64.0f,
                    viewportHeight = 100.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF014958)),
                        stroke = SolidColor(Color(0xFF014958)),
                        strokeLineWidth = 0.520833f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(37.23f, 95.05f)
                        curveTo(37.1f, 97.66f, 34.94f, 99.74f, 32.29f, 99.74f)
                        curveTo(29.65f, 99.74f, 27.48f, 97.66f, 27.35f, 95.05f)
                        horizontalLineTo(37.23f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF014958)),
                        stroke = SolidColor(Color(0xFF014958)),
                        strokeLineWidth = 0.520833f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(24.48f, 81.51f)
                        horizontalLineTo(40.1f)
                        curveTo(41.69f, 81.51f, 42.97f, 82.79f, 42.97f, 84.38f)
                        curveTo(42.97f, 85.96f, 41.69f, 87.24f, 40.1f, 87.24f)
                        horizontalLineTo(24.48f)
                        curveTo(22.9f, 87.24f, 21.61f, 85.96f, 21.61f, 84.38f)
                        curveTo(21.61f, 82.79f, 22.9f, 81.51f, 24.48f, 81.51f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF014958)),
                        stroke = SolidColor(Color(0xFF014958)),
                        strokeLineWidth = 0.520833f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(24.48f, 88.28f)
                        horizontalLineTo(40.1f)
                        curveTo(41.69f, 88.28f, 42.97f, 89.56f, 42.97f, 91.15f)
                        curveTo(42.97f, 92.73f, 41.69f, 94.01f, 40.1f, 94.01f)
                        horizontalLineTo(24.48f)
                        curveTo(22.9f, 94.01f, 21.61f, 92.73f, 21.61f, 91.15f)
                        curveTo(21.61f, 89.56f, 22.9f, 88.28f, 24.48f, 88.28f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = SolidColor(Color(0xFF014958)),
                        strokeLineWidth = 1.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(26.11f, 22.5f)
                        horizontalLineTo(38.86f)
                        curveTo(47.21f, 22.5f, 53.98f, 29.27f, 53.98f, 37.63f)
                        verticalLineTo(58.31f)
                        curveTo(53.98f, 62.22f, 52.47f, 65.97f, 49.77f, 68.79f)
                        lineTo(44.94f, 73.82f)
                        curveTo(44.02f, 74.77f, 43.51f, 76.05f, 43.51f, 77.37f)
                        curveTo(43.51f, 79.65f, 41.66f, 81.5f, 39.38f, 81.5f)
                        horizontalLineTo(25.59f)
                        curveTo(23.31f, 81.5f, 21.46f, 79.65f, 21.46f, 77.37f)
                        curveTo(21.46f, 76.05f, 20.95f, 74.77f, 20.03f, 73.82f)
                        lineTo(15.2f, 68.79f)
                        curveTo(12.5f, 65.97f, 10.98f, 62.22f, 10.98f, 58.31f)
                        verticalLineTo(37.63f)
                        curveTo(10.98f, 29.27f, 17.76f, 22.5f, 26.11f, 22.5f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFE3F6DC)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(32.29f, 45.83f)
                        moveToRelative(-15.63f, 0.0f)
                        arcToRelative(15.63f, 15.63f, 0.0f, true, true, 31.25f, 0.0f)
                        arcToRelative(15.63f, 15.63f, 0.0f, true, true, -31.25f, 0.0f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF3CB572)),
                        strokeLineWidth = 2.60417f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(36.98f, 81.0f)
                        curveTo(36.98f, 81.0f, 37.07f, 69.66f, 38.23f, 62.5f)
                        curveTo(39.01f, 57.75f, 41.34f, 51.52f, 42.93f, 47.64f)
                        curveTo(43.46f, 46.33f, 42.13f, 44.97f, 40.82f, 45.52f)
                        lineTo(38.83f, 46.34f)
                        curveTo(38.45f, 46.5f, 38.02f, 46.5f, 37.64f, 46.34f)
                        lineTo(32.58f, 44.25f)
                        curveTo(32.2f, 44.09f, 31.77f, 44.09f, 31.39f, 44.25f)
                        lineTo(26.33f, 46.34f)
                        curveTo(25.95f, 46.5f, 25.52f, 46.5f, 25.14f, 46.34f)
                        lineTo(23.15f, 45.52f)
                        curveTo(21.84f, 44.97f, 20.5f, 46.33f, 21.04f, 47.64f)
                        curveTo(22.63f, 51.52f, 24.96f, 57.75f, 25.73f, 62.5f)
                        curveTo(26.9f, 69.66f, 26.98f, 81.0f, 26.98f, 81.0f)
                    }
                    path(
                        fill = SolidColor(Color(0xFF014958)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(0.0f, 11.44f)
                        horizontalLineTo(4.02f)
                        lineTo(9.14f, 27.86f)
                        horizontalLineTo(7.8f)
                        lineTo(0.0f, 11.44f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF014958)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(63.97f, 11.44f)
                        horizontalLineTo(59.95f)
                        lineTo(54.83f, 27.86f)
                        horizontalLineTo(56.17f)
                        lineTo(63.97f, 11.44f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF3CB572)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(33.85f, 0.0f)
                        lineTo(30.21f, 0.0f)
                        lineTo(31.39f, 16.67f)
                        lineTo(32.61f, 16.67f)
                        lineTo(33.85f, 0.0f)
                        close()
                    }
                }
                .build()
        return _lightbulbLight!!
    }

private var _lightbulbLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.LightbulbLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
