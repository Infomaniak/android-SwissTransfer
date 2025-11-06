/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.images.illus.ghostPointingReport

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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.GhostPointingReportDark: ImageVector
    get() {
        if (_ghostPointingReportDark != null) {
            return _ghostPointingReportDark!!
        }
        _ghostPointingReportDark = Builder(
            name = "GhostPointingReportDark",
            defaultWidth = 153.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 153.0f,
            viewportHeight = 160.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(94.41f, 0.5f)
                curveTo(117.31f, 0.5f, 136.54f, 17.76f, 139.0f, 40.53f)
                lineTo(151.73f, 158.19f)
                lineTo(138.34f, 139.81f)
                curveTo(130.98f, 129.71f, 115.9f, 129.71f, 108.54f, 139.81f)
                curveTo(101.58f, 149.36f, 87.19f, 149.18f, 80.22f, 139.62f)
                curveTo(72.89f, 129.57f, 57.84f, 129.07f, 50.13f, 138.86f)
                lineTo(37.16f, 155.32f)
                lineTo(49.82f, 40.44f)
                curveTo(52.33f, 17.71f, 71.54f, 0.5f, 94.41f, 0.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(27.0f, 72.5f)
                horizontalLineTo(75.5f)
                verticalLineTo(98.68f)
                horizontalLineTo(71.5f)
                verticalLineTo(159.3f)
                lineTo(57.66f, 154.53f)
                lineTo(57.49f, 154.47f)
                lineTo(57.33f, 154.53f)
                lineTo(44.0f, 159.47f)
                lineTo(30.67f, 154.53f)
                lineTo(30.5f, 154.47f)
                lineTo(30.33f, 154.53f)
                lineTo(17.5f, 159.28f)
                verticalLineTo(82.0f)
                curveTo(17.5f, 76.75f, 21.75f, 72.5f, 27.0f, 72.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(71.0f, 77.0f)
                curveTo(71.0f, 74.24f, 73.24f, 72.0f, 76.0f, 72.0f)
                curveTo(78.76f, 72.0f, 81.0f, 74.24f, 81.0f, 77.0f)
                verticalLineTo(108.0f)
                horizontalLineTo(71.0f)
                verticalLineTo(77.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 81.0f)
                lineTo(55.0f, 81.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 56.0f, 82.0f)
                lineTo(56.0f, 82.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 55.0f, 83.0f)
                lineTo(26.0f, 83.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 82.0f)
                lineTo(25.0f, 82.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 81.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 109.0f)
                lineTo(41.0f, 109.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 42.0f, 110.0f)
                lineTo(42.0f, 110.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 41.0f, 111.0f)
                lineTo(26.0f, 111.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 110.0f)
                lineTo(25.0f, 110.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 109.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(45.0f, 109.0f)
                lineTo(63.0f, 109.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 110.0f)
                lineTo(64.0f, 110.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 111.0f)
                lineTo(45.0f, 111.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 44.0f, 110.0f)
                lineTo(44.0f, 110.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 45.0f, 109.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 104.0f)
                lineTo(45.0f, 104.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 105.0f)
                lineTo(46.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 45.0f, 106.0f)
                lineTo(26.0f, 106.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 105.0f)
                lineTo(25.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 104.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(49.0f, 104.0f)
                lineTo(58.0f, 104.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 59.0f, 105.0f)
                lineTo(59.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 58.0f, 106.0f)
                lineTo(49.0f, 106.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 48.0f, 105.0f)
                lineTo(48.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 49.0f, 104.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(62.0f, 104.0f)
                lineTo(63.0f, 104.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 105.0f)
                lineTo(64.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 106.0f)
                lineTo(62.0f, 106.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 61.0f, 105.0f)
                lineTo(61.0f, 105.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 62.0f, 104.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 98.0f)
                lineTo(38.0f, 98.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 39.0f, 99.0f)
                lineTo(39.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 38.0f, 100.0f)
                lineTo(26.0f, 100.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 99.0f)
                lineTo(25.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 98.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(42.0f, 98.0f)
                lineTo(46.0f, 98.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 47.0f, 99.0f)
                lineTo(47.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 100.0f)
                lineTo(42.0f, 100.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 41.0f, 99.0f)
                lineTo(41.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 42.0f, 98.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(50.0f, 98.0f)
                lineTo(63.0f, 98.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 99.0f)
                lineTo(64.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 100.0f)
                lineTo(50.0f, 100.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 49.0f, 99.0f)
                lineTo(49.0f, 99.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 50.0f, 98.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 93.0f)
                lineTo(35.0f, 93.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 36.0f, 94.0f)
                lineTo(36.0f, 94.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 35.0f, 95.0f)
                lineTo(26.0f, 95.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 94.0f)
                lineTo(25.0f, 94.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 93.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(39.0f, 93.0f)
                lineTo(63.0f, 93.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 94.0f)
                lineTo(64.0f, 94.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 95.0f)
                lineTo(39.0f, 95.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 38.0f, 94.0f)
                lineTo(38.0f, 94.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 39.0f, 93.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 130.0f)
                lineTo(41.0f, 130.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 42.0f, 131.0f)
                lineTo(42.0f, 131.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 41.0f, 132.0f)
                lineTo(26.0f, 132.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 131.0f)
                lineTo(25.0f, 131.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 130.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 119.0f)
                lineTo(45.0f, 119.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 120.0f)
                lineTo(46.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 45.0f, 121.0f)
                lineTo(26.0f, 121.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 120.0f)
                lineTo(25.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 119.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(49.0f, 119.0f)
                lineTo(58.0f, 119.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 59.0f, 120.0f)
                lineTo(59.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 58.0f, 121.0f)
                lineTo(49.0f, 121.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 48.0f, 120.0f)
                lineTo(48.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 49.0f, 119.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(62.0f, 119.0f)
                lineTo(63.0f, 119.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 120.0f)
                lineTo(64.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 121.0f)
                lineTo(62.0f, 121.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 61.0f, 120.0f)
                lineTo(61.0f, 120.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 62.0f, 119.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 125.0f)
                lineTo(38.0f, 125.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 39.0f, 126.0f)
                lineTo(39.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 38.0f, 127.0f)
                lineTo(26.0f, 127.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 126.0f)
                lineTo(25.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 125.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(42.0f, 125.0f)
                lineTo(46.0f, 125.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 47.0f, 126.0f)
                lineTo(47.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 127.0f)
                lineTo(42.0f, 127.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 41.0f, 126.0f)
                lineTo(41.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 42.0f, 125.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(50.0f, 125.0f)
                lineTo(63.0f, 125.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 126.0f)
                lineTo(64.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 127.0f)
                lineTo(50.0f, 127.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 49.0f, 126.0f)
                lineTo(49.0f, 126.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 50.0f, 125.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(26.0f, 114.0f)
                lineTo(35.0f, 114.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 36.0f, 115.0f)
                lineTo(36.0f, 115.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 35.0f, 116.0f)
                lineTo(26.0f, 116.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 25.0f, 115.0f)
                lineTo(25.0f, 115.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 26.0f, 114.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF666666)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(39.0f, 114.0f)
                lineTo(63.0f, 114.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 64.0f, 115.0f)
                lineTo(64.0f, 115.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 63.0f, 116.0f)
                lineTo(39.0f, 116.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 38.0f, 115.0f)
                lineTo(38.0f, 115.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 39.0f, 114.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(46.31f, 97.33f)
                curveTo(42.27f, 97.33f, 39.0f, 94.06f, 39.0f, 90.02f)
                lineTo(39.0f, 73.0f)
                lineTo(53.62f, 73.0f)
                lineTo(53.62f, 90.02f)
                curveTo(53.62f, 94.06f, 50.35f, 97.33f, 46.31f, 97.33f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(39.0f, 73.0f)
                lineTo(39.0f, 90.02f)
                curveTo(39.0f, 94.06f, 42.27f, 97.33f, 46.31f, 97.33f)
                curveTo(50.35f, 97.33f, 53.62f, 94.06f, 53.62f, 90.02f)
                lineTo(53.62f, 73.0f)
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
                moveTo(83.21f, 55.73f)
                lineTo(83.21f, 55.73f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 87.71f, 60.22f)
                lineTo(87.71f, 62.02f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 83.21f, 66.52f)
                lineTo(83.21f, 66.52f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 78.72f, 62.02f)
                lineTo(78.72f, 60.22f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 83.21f, 55.73f)
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
                moveTo(105.69f, 55.73f)
                lineTo(105.69f, 55.73f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 110.18f, 60.22f)
                lineTo(110.18f, 62.02f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 105.69f, 66.52f)
                lineTo(105.69f, 66.52f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 101.19f, 62.02f)
                lineTo(101.19f, 60.22f)
                arcTo(4.49f, 4.49f, 0.0f, false, true, 105.69f, 55.73f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(67.41f, 116.03f)
                lineTo(117.12f, 100.3f)
                arcTo(1.0f, 1.0f, 102.64f, false, true, 118.38f, 100.95f)
                lineTo(118.38f, 100.95f)
                arcTo(1.0f, 1.0f, 102.64f, false, true, 117.73f, 102.21f)
                lineTo(68.02f, 117.94f)
                arcTo(1.0f, 1.0f, 126.14f, false, true, 66.76f, 117.29f)
                lineTo(66.76f, 117.29f)
                arcTo(1.0f, 1.0f, 126.14f, false, true, 67.41f, 116.03f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(114.33f, 103.14f)
                curveTo(111.94f, 99.89f, 112.64f, 95.31f, 115.89f, 92.92f)
                lineTo(134.81f, 79.0f)
                lineTo(143.47f, 90.77f)
                lineTo(124.55f, 104.69f)
                curveTo(121.3f, 107.09f, 116.72f, 106.39f, 114.33f, 103.14f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(134.81f, 79.0f)
                lineTo(115.89f, 92.92f)
                curveTo(112.64f, 95.31f, 111.94f, 99.89f, 114.33f, 103.14f)
                curveTo(116.72f, 106.39f, 121.3f, 107.09f, 124.55f, 104.69f)
                lineTo(143.47f, 90.77f)
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(7.79f, 67.36f)
                lineTo(3.27f, 62.99f)
                lineTo(0.14f, 59.51f)
                lineTo(1.68f, 58.13f)
                lineTo(4.81f, 61.6f)
                lineTo(8.69f, 66.55f)
                lineTo(7.79f, 67.36f)
                close()
                moveTo(10.36f, 71.14f)
                lineTo(8.72f, 69.32f)
                lineTo(10.54f, 67.67f)
                lineTo(12.18f, 69.5f)
                lineTo(10.36f, 71.14f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFAA4C)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(21.18f, 60.63f)
                lineTo(20.43f, 54.04f)
                lineTo(20.2f, 49.11f)
                lineTo(22.38f, 49.01f)
                lineTo(22.61f, 53.94f)
                lineTo(22.46f, 60.57f)
                lineTo(21.18f, 60.63f)
                close()
                moveTo(20.74f, 65.43f)
                lineTo(20.62f, 62.84f)
                lineTo(23.21f, 62.72f)
                lineTo(23.33f, 65.31f)
                lineTo(20.74f, 65.43f)
                close()
            }
        }.build()
        return _ghostPointingReportDark!!
    }

private var _ghostPointingReportDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.GhostPointingReportDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
