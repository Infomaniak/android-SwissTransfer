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
package com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass

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

val AppIllus.MascotWithMagnifyingGlassLight: ImageVector
    get() {

        if (_mascotWithMagnifyingGlassLight != null) return _mascotWithMagnifyingGlassLight!!

        _mascotWithMagnifyingGlassLight = Builder(
            name = "MascotWithMagnifyingGlassLight",
            defaultWidth = 265.0.dp,
            defaultHeight = 157.0.dp,
            viewportWidth = 265.0f,
            viewportHeight = 157.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.0f, 136.47f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, 264.08f, 0.0f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, -264.08f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(90.03f, 62.62f)
                curveTo(92.34f, 41.22f, 110.41f, 25.0f, 131.94f, 25.0f)
                curveTo(153.46f, 25.0f, 171.53f, 41.22f, 173.85f, 62.62f)
                lineTo(183.89f, 155.5f)
                lineTo(172.81f, 156.0f)
                curveTo(165.68f, 146.21f, 151.32f, 145.56f, 143.34f, 154.66f)
                lineTo(141.28f, 157.0f)
                horizontalLineTo(122.6f)
                lineTo(120.54f, 154.66f)
                curveTo(112.56f, 145.56f, 98.19f, 146.21f, 91.07f, 156.0f)
                lineTo(80.0f, 155.4f)
                lineTo(90.03f, 62.62f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(183.89f, 155.5f)
                lineTo(173.85f, 62.62f)
                curveTo(171.53f, 41.22f, 153.46f, 25.0f, 131.94f, 25.0f)
                verticalLineTo(25.0f)
                curveTo(110.41f, 25.0f, 92.34f, 41.22f, 90.03f, 62.62f)
                lineTo(80.0f, 155.4f)
                moveTo(91.07f, 156.0f)
                verticalLineTo(156.0f)
                curveTo(98.19f, 146.21f, 112.56f, 145.56f, 120.54f, 154.66f)
                lineTo(122.6f, 157.0f)
                moveTo(141.28f, 157.0f)
                lineTo(143.34f, 154.66f)
                curveTo(151.32f, 145.56f, 165.68f, 146.21f, 172.81f, 156.0f)
                verticalLineTo(156.0f)
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(93.29f, 14.96f)
                curveTo(94.01f, 16.24f, 93.61f, 17.65f, 93.03f, 18.75f)
                curveTo(92.49f, 19.76f, 92.33f, 20.17f, 92.75f, 20.91f)
                lineTo(92.98f, 21.32f)
                lineTo(91.73f, 22.02f)
                lineTo(91.47f, 21.57f)
                curveTo(90.84f, 20.43f, 91.11f, 19.63f, 91.83f, 18.29f)
                curveTo(92.28f, 17.45f, 92.53f, 16.6f, 92.04f, 15.73f)
                curveTo(91.57f, 14.88f, 90.51f, 14.75f, 89.47f, 15.33f)
                curveTo(87.96f, 16.17f, 88.06f, 17.49f, 88.47f, 18.35f)
                lineTo(87.24f, 19.03f)
                curveTo(86.28f, 17.27f, 87.01f, 15.36f, 88.92f, 14.29f)
                curveTo(90.45f, 13.44f, 92.38f, 13.33f, 93.29f, 14.96f)
                close()
                moveTo(93.2f, 24.95f)
                lineTo(92.34f, 23.4f)
                lineTo(93.89f, 22.53f)
                lineTo(94.75f, 24.08f)
                lineTo(93.2f, 24.95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFAA4C)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(162.74f, 1.85f)
                curveTo(162.74f, 2.85f, 162.04f, 3.54f, 161.32f, 4.0f)
                curveTo(160.68f, 4.41f, 160.45f, 4.6f, 160.44f, 5.18f)
                lineTo(160.44f, 5.5f)
                lineTo(159.47f, 5.5f)
                lineTo(159.47f, 5.14f)
                curveTo(159.48f, 4.26f, 159.91f, 3.88f, 160.77f, 3.33f)
                curveTo(161.32f, 2.99f, 161.75f, 2.57f, 161.75f, 1.89f)
                curveTo(161.75f, 1.24f, 161.17f, 0.81f, 160.37f, 0.8f)
                curveTo(159.2f, 0.8f, 158.82f, 1.61f, 158.77f, 2.25f)
                lineTo(157.82f, 2.24f)
                curveTo(157.84f, 0.89f, 158.91f, 0.0f, 160.39f, 0.01f)
                curveTo(161.57f, 0.01f, 162.75f, 0.59f, 162.74f, 1.85f)
                close()
                moveTo(159.37f, 7.71f)
                lineTo(159.38f, 6.51f)
                lineTo(160.57f, 6.51f)
                lineTo(160.57f, 7.71f)
                lineTo(159.37f, 7.71f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(179.53f, 15.05f)
                curveTo(178.9f, 16.81f, 177.21f, 17.6f, 175.66f, 17.97f)
                curveTo(174.24f, 18.29f, 173.71f, 18.48f, 173.35f, 19.5f)
                lineTo(173.14f, 20.08f)
                lineTo(171.42f, 19.46f)
                lineTo(171.65f, 18.83f)
                curveTo(172.21f, 17.27f, 173.21f, 16.86f, 175.1f, 16.43f)
                curveTo(176.28f, 16.16f, 177.31f, 15.69f, 177.74f, 14.49f)
                curveTo(178.16f, 13.32f, 177.4f, 12.19f, 175.97f, 11.68f)
                curveTo(173.89f, 10.93f, 172.71f, 12.14f, 172.23f, 13.24f)
                lineTo(170.54f, 12.64f)
                curveTo(171.42f, 10.23f, 173.88f, 9.34f, 176.51f, 10.28f)
                curveTo(178.6f, 11.03f, 180.33f, 12.8f, 179.53f, 15.05f)
                close()
                moveTo(169.84f, 23.33f)
                lineTo(170.61f, 21.19f)
                lineTo(172.74f, 21.96f)
                lineTo(171.98f, 24.09f)
                lineTo(169.84f, 23.33f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(109.0f, 70.0f)
                lineTo(109.0f, 70.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 113.0f, 74.0f)
                lineTo(113.0f, 76.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 80.0f)
                lineTo(109.0f, 80.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 105.0f, 76.0f)
                lineTo(105.0f, 74.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 70.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(146.35f, 88.53f)
                curveTo(144.9f, 87.03f, 144.98f, 84.63f, 146.52f, 83.23f)
                curveTo(148.06f, 81.83f, 150.46f, 81.99f, 151.81f, 83.58f)
                lineTo(167.91f, 102.57f)
                curveTo(168.91f, 103.75f, 168.79f, 105.5f, 167.65f, 106.54f)
                curveTo(166.51f, 107.57f, 164.76f, 107.51f, 163.68f, 106.41f)
                lineTo(146.35f, 88.53f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(138.55f, 74.45f)
                moveToRelative(-12.98f, 11.77f)
                arcToRelative(17.52f, 17.52f, 92.79f, true, true, 25.95f, -23.54f)
                arcToRelative(17.52f, 17.52f, 92.79f, true, true, -25.95f, 23.54f)
            }
            path(
                fill = SolidColor(Color(0xFFFF9802)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(138.55f, 74.45f)
                moveToRelative(-10.38f, 9.42f)
                arcToRelative(14.02f, 14.01f, 92.79f, true, true, 20.76f, -18.83f)
                arcToRelative(14.02f, 14.01f, 92.79f, true, true, -20.76f, 18.83f)
            }
            path(
                fill = SolidColor(Color(0xFFFFC166)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(132.81f, 69.7f)
                lineTo(132.81f, 69.7f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 136.81f, 73.7f)
                lineTo(136.81f, 75.7f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 79.7f)
                lineTo(132.81f, 79.7f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 128.81f, 75.7f)
                lineTo(128.81f, 73.7f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 69.7f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(84.5f, 120.5f)
                lineTo(98.51f, 113.5f)
                curveTo(102.0f, 111.75f, 103.63f, 107.68f, 102.32f, 104.01f)
                verticalLineTo(104.01f)
                curveTo(100.89f, 100.0f, 96.48f, 97.9f, 92.47f, 99.33f)
                lineTo(86.44f, 101.48f)
            }
            path(
                fill = SolidColor(Color(0xFFFAFAFA)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(93.02f, 127.86f)
                lineTo(92.05f, 85.5f)
                horizontalLineTo(118.03f)
                lineTo(117.07f, 127.86f)
                lineTo(112.14f, 123.14f)
                lineTo(111.82f, 122.83f)
                lineTo(111.47f, 123.11f)
                lineTo(105.04f, 128.35f)
                lineTo(98.61f, 123.11f)
                lineTo(98.26f, 122.83f)
                lineTo(97.95f, 123.14f)
                lineTo(93.02f, 127.86f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(157.98f, 98.35f)
                curveTo(160.73f, 95.39f, 165.36f, 95.23f, 168.31f, 97.98f)
                lineTo(178.3f, 107.28f)
                lineTo(168.34f, 117.98f)
                lineTo(158.35f, 108.68f)
                curveTo(155.4f, 105.93f, 155.23f, 101.3f, 157.98f, 98.35f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(178.3f, 107.28f)
                lineTo(168.31f, 97.98f)
                curveTo(165.36f, 95.23f, 160.73f, 95.39f, 157.98f, 98.35f)
                verticalLineTo(98.35f)
                curveTo(155.23f, 101.3f, 155.4f, 105.93f, 158.35f, 108.68f)
                lineTo(168.34f, 117.98f)
            }
        }.build()

        return _mascotWithMagnifyingGlassLight!!
    }

private var _mascotWithMagnifyingGlassLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotWithMagnifyingGlassLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
