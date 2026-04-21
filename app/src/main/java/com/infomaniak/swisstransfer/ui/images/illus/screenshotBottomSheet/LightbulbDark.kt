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
import com.infomaniak.swisstransfer.ui.images.illus.ghostPointingReport.GhostPointingReportDark

val AppIllus.LightbulbDark: ImageVector
    get() {
        if (_lightbulbDark != null) {
            return _lightbulbDark!!
        }
        _lightbulbDark =
            Builder(
                    name = "LightbulbDark",
                    defaultWidth = 64.0.dp,
                    defaultHeight = 100.0.dp,
                    viewportWidth = 64.0f,
                    viewportHeight = 100.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF2B383B)),
                        stroke = SolidColor(Color(0xFFDCE4E5)),
                        strokeLineWidth = 0.5f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(37.24f, 95.04f)
                        curveTo(37.11f, 97.66f, 34.94f, 99.75f, 32.29f, 99.75f)
                        curveTo(29.63f, 99.75f, 27.47f, 97.66f, 27.34f, 95.04f)
                        horizontalLineTo(37.24f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF2B383B)),
                        stroke = SolidColor(Color(0xFFDCE4E5)),
                        strokeLineWidth = 0.5f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(24.48f, 81.5f)
                        horizontalLineTo(40.1f)
                        curveTo(41.69f, 81.5f, 42.98f, 82.79f, 42.98f, 84.38f)
                        curveTo(42.98f, 85.96f, 41.69f, 87.25f, 40.1f, 87.25f)
                        horizontalLineTo(24.48f)
                        curveTo(22.89f, 87.25f, 21.6f, 85.96f, 21.6f, 84.38f)
                        curveTo(21.6f, 82.79f, 22.89f, 81.5f, 24.48f, 81.5f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF2B383B)),
                        stroke = SolidColor(Color(0xFFDCE4E5)),
                        strokeLineWidth = 0.5f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(24.48f, 88.27f)
                        horizontalLineTo(40.1f)
                        curveTo(41.69f, 88.27f, 42.98f, 89.56f, 42.98f, 91.15f)
                        curveTo(42.98f, 92.73f, 41.69f, 94.02f, 40.1f, 94.02f)
                        horizontalLineTo(24.48f)
                        curveTo(22.89f, 94.02f, 21.6f, 92.73f, 21.6f, 91.15f)
                        curveTo(21.6f, 89.56f, 22.89f, 88.27f, 24.48f, 88.27f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF152123)),
                        stroke = SolidColor(Color(0xFFDCE4E5)),
                        strokeLineWidth = 0.5f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(26.11f, 22.25f)
                        horizontalLineTo(38.86f)
                        curveTo(47.35f, 22.25f, 54.23f, 29.13f, 54.23f, 37.63f)
                        verticalLineTo(58.31f)
                        curveTo(54.23f, 62.28f, 52.7f, 66.1f, 49.95f, 68.96f)
                        lineTo(45.12f, 73.99f)
                        curveTo(44.24f, 74.9f, 43.76f, 76.11f, 43.76f, 77.37f)
                        curveTo(43.76f, 79.79f, 41.8f, 81.75f, 39.38f, 81.75f)
                        horizontalLineTo(25.59f)
                        curveTo(23.17f, 81.75f, 21.21f, 79.79f, 21.21f, 77.37f)
                        curveTo(21.21f, 76.11f, 20.72f, 74.9f, 19.85f, 73.99f)
                        lineTo(15.02f, 68.96f)
                        curveTo(12.27f, 66.1f, 10.73f, 62.28f, 10.73f, 58.31f)
                        verticalLineTo(37.63f)
                        curveTo(10.73f, 29.13f, 17.62f, 22.25f, 26.11f, 22.25f)
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
                        moveTo(32.29f, 45.83f)
                        moveToRelative(-15.63f, 0.0f)
                        arcToRelative(15.63f, 15.63f, 0.0f, true, true, 31.25f, 0.0f)
                        arcToRelative(15.63f, 15.63f, 0.0f, true, true, -31.25f, 0.0f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF67DD95)),
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
                        fill = SolidColor(Color(0xFFffffff)),
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
                        fill = SolidColor(Color(0xFFffffff)),
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
                        fill = SolidColor(Color(0xFF67DD95)),
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
        return _lightbulbDark!!
    }

private var _lightbulbDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.LightbulbDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
