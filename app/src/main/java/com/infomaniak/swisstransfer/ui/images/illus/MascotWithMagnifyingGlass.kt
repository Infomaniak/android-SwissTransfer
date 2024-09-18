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
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.MascotWithMagnifyingGlass: ImageVector
    get() {

        if (_mascotWithMagnifyingGlass != null) return _mascotWithMagnifyingGlass!!

        _mascotWithMagnifyingGlass = Builder(
            name = "MascotWithMagnifyingGlass",
            defaultWidth = 265.0.dp,
            defaultHeight = 134.0.dp,
            viewportWidth = 265.0f,
            viewportHeight = 134.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(0.0f, 113.12f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, 264.08f, 0.0f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, -264.08f, 0.0f)
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
                moveTo(132.04f, 111.41f)
                horizontalLineTo(105.07f)
                horizontalLineTo(82.66f)
                lineTo(90.63f, 37.68f)
                curveTo(92.92f, 16.53f, 110.77f, 0.5f, 132.04f, 0.5f)
                curveTo(153.31f, 0.5f, 171.17f, 16.53f, 173.45f, 37.68f)
                lineTo(182.35f, 120.02f)
                lineTo(172.63f, 116.42f)
                curveTo(163.68f, 113.11f, 154.22f, 111.41f, 144.68f, 111.41f)
                horizontalLineTo(132.04f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFffffff)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(173.45f, 37.68f)
                    lineTo(185.29f, 147.14f)
                    lineTo(172.9f, 130.14f)
                    curveTo(166.04f, 120.71f, 151.99f, 120.71f, 145.12f, 130.14f)
                    curveTo(138.66f, 139.01f, 125.42f, 139.01f, 118.96f, 130.14f)
                    curveTo(112.1f, 120.71f, 98.04f, 120.71f, 91.18f, 130.14f)
                    lineTo(78.8f, 147.14f)
                    lineTo(90.63f, 37.68f)
                    curveTo(92.92f, 16.53f, 110.77f, 0.5f, 132.04f, 0.5f)
                    curveTo(153.31f, 0.5f, 171.17f, 16.53f, 173.45f, 37.68f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(84.5f, 97.15f)
                    lineTo(98.51f, 90.15f)
                    curveTo(102.0f, 88.4f, 103.63f, 84.33f, 102.32f, 80.66f)
                    verticalLineTo(80.66f)
                    curveTo(100.89f, 76.65f, 96.48f, 74.55f, 92.47f, 75.98f)
                    lineTo(86.44f, 78.13f)
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
                    moveTo(151.81f, 60.23f)
                    lineTo(167.91f, 79.22f)
                    curveTo(168.91f, 80.4f, 168.79f, 82.15f, 167.65f, 83.19f)
                    curveTo(166.51f, 84.22f, 164.76f, 84.17f, 163.68f, 83.06f)
                    lineTo(146.35f, 65.18f)
                    curveTo(144.9f, 63.68f, 144.98f, 61.28f, 146.52f, 59.88f)
                    curveTo(148.06f, 58.48f, 150.46f, 58.64f, 151.81f, 60.23f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 0.54f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(138.55f, 51.1f)
                    moveToRelative(-12.78f, 11.59f)
                    arcToRelative(17.25f, 17.25f, 92.79f, true, true, 25.55f, -23.17f)
                    arcToRelative(17.25f, 17.25f, 92.79f, true, true, -25.55f, 23.17f)
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
                    moveTo(138.55f, 51.1f)
                    moveToRelative(-10.38f, 9.42f)
                    arcToRelative(14.02f, 14.01f, 92.79f, true, true, 20.76f, -18.83f)
                    arcToRelative(14.02f, 14.01f, 92.79f, true, true, -20.76f, 18.83f)
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
                    moveTo(132.81f, 46.35f)
                    lineTo(132.81f, 46.35f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 136.81f, 50.35f)
                    lineTo(136.81f, 52.35f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 56.35f)
                    lineTo(132.81f, 56.35f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 128.81f, 52.35f)
                    lineTo(128.81f, 50.35f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 46.35f)
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
                    moveTo(156.08f, 81.62f)
                    curveTo(152.72f, 79.3f, 151.88f, 74.7f, 154.2f, 71.34f)
                    verticalLineTo(71.34f)
                    curveTo(156.52f, 67.97f, 161.12f, 67.13f, 164.48f, 69.45f)
                    lineTo(178.19f, 78.91f)
                    lineTo(169.79f, 91.08f)
                    lineTo(156.08f, 81.62f)
                    close()
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(155.52f, 82.44f)
                    curveTo(151.7f, 79.81f, 150.74f, 74.58f, 153.38f, 70.77f)
                    lineTo(153.38f, 70.77f)
                    curveTo(156.01f, 66.95f, 161.24f, 65.99f, 165.05f, 68.63f)
                    lineTo(163.92f, 70.27f)
                    curveTo(161.01f, 68.27f, 157.03f, 69.0f, 155.02f, 71.9f)
                    curveTo(153.01f, 74.81f, 153.74f, 78.79f, 156.65f, 80.8f)
                    lineTo(155.52f, 82.44f)
                    close()
                    moveTo(178.19f, 78.91f)
                    lineTo(169.79f, 91.08f)
                    lineTo(178.19f, 78.91f)
                    close()
                    moveTo(169.22f, 91.9f)
                    lineTo(155.52f, 82.44f)
                    curveTo(151.7f, 79.81f, 150.74f, 74.58f, 153.38f, 70.77f)
                    lineTo(155.02f, 71.9f)
                    curveTo(153.01f, 74.81f, 153.74f, 78.79f, 156.65f, 80.8f)
                    lineTo(170.36f, 90.26f)
                    lineTo(169.22f, 91.9f)
                    close()
                    moveTo(153.38f, 70.77f)
                    curveTo(156.01f, 66.95f, 161.24f, 65.99f, 165.05f, 68.63f)
                    lineTo(178.76f, 78.08f)
                    lineTo(177.62f, 79.73f)
                    lineTo(163.92f, 70.27f)
                    curveTo(161.01f, 68.27f, 157.03f, 69.0f, 155.02f, 71.9f)
                    lineTo(153.38f, 70.77f)
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
                    moveTo(109.0f, 46.65f)
                    lineTo(109.0f, 46.65f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 113.0f, 50.65f)
                    lineTo(113.0f, 52.65f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 56.65f)
                    lineTo(109.0f, 56.65f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 105.0f, 52.65f)
                    lineTo(105.0f, 50.65f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 46.65f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFAFAFA)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(93.02f, 104.51f)
                    lineTo(92.05f, 62.15f)
                    horizontalLineTo(118.03f)
                    lineTo(117.07f, 104.51f)
                    lineTo(112.14f, 99.79f)
                    lineTo(111.82f, 99.48f)
                    lineTo(111.47f, 99.76f)
                    lineTo(105.04f, 105.01f)
                    lineTo(98.61f, 99.76f)
                    lineTo(98.26f, 99.48f)
                    lineTo(97.95f, 99.79f)
                    lineTo(93.02f, 104.51f)
                    close()
                }
            }
        }.build()

        return _mascotWithMagnifyingGlass!!
    }

private var _mascotWithMagnifyingGlass: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotWithMagnifyingGlass,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
