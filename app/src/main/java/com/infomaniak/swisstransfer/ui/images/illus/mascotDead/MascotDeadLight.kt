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
package com.infomaniak.swisstransfer.ui.images.illus.mascotDead

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

val AppIllus.MascotDeadLight: ImageVector
    get() {

        if (_mascotDeadLight != null) return _mascotDeadLight!!

        _mascotDeadLight = Builder(
            name = "MascotDeadLight",
            defaultWidth = 185.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 185.0f,
            viewportHeight = 160.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFE3F6DC)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.27f, 145.66f)
                arcToRelative(92.23f, 14.34f, 0.0f, true, false, 184.46f, 0.0f)
                arcToRelative(92.23f, 14.34f, 0.0f, true, false, -184.46f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(56.61f, 52.95f)
                curveTo(58.59f, 34.64f, 74.05f, 20.76f, 92.47f, 20.76f)
                curveTo(110.89f, 20.76f, 126.35f, 34.64f, 128.33f, 52.95f)
                lineTo(138.57f, 147.67f)
                lineTo(127.97f, 133.12f)
                curveTo(122.0f, 124.91f, 109.75f, 124.91f, 103.77f, 133.12f)
                curveTo(98.19f, 140.78f, 86.76f, 140.78f, 81.18f, 133.12f)
                curveTo(75.2f, 124.91f, 62.95f, 124.91f, 56.97f, 133.12f)
                lineTo(46.37f, 147.67f)
                lineTo(56.61f, 52.95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(92.47f, 78.23f)
                moveToRelative(-2.79f, 0.0f)
                arcToRelative(2.79f, 2.79f, 0.0f, true, true, 5.59f, 0.0f)
                arcToRelative(2.79f, 2.79f, 0.0f, true, true, -5.59f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(58.86f, 12.98f)
                curveTo(59.48f, 14.09f, 59.13f, 15.31f, 58.62f, 16.27f)
                curveTo(58.16f, 17.14f, 58.02f, 17.49f, 58.38f, 18.14f)
                lineTo(58.58f, 18.5f)
                lineTo(57.5f, 19.11f)
                lineTo(57.28f, 18.71f)
                curveTo(56.73f, 17.73f, 56.96f, 17.03f, 57.59f, 15.87f)
                curveTo(57.98f, 15.14f, 58.2f, 14.41f, 57.77f, 13.65f)
                curveTo(57.36f, 12.91f, 56.44f, 12.8f, 55.54f, 13.3f)
                curveTo(54.23f, 14.03f, 54.32f, 15.18f, 54.67f, 15.92f)
                lineTo(53.61f, 16.51f)
                curveTo(52.77f, 14.99f, 53.41f, 13.33f, 55.07f, 12.4f)
                curveTo(56.39f, 11.66f, 58.07f, 11.56f, 58.86f, 12.98f)
                close()
                moveTo(58.78f, 21.64f)
                lineTo(58.03f, 20.3f)
                lineTo(59.37f, 19.55f)
                lineTo(60.12f, 20.89f)
                lineTo(58.78f, 21.64f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(119.11f, 1.61f)
                curveTo(119.1f, 2.47f, 118.5f, 3.07f, 117.88f, 3.47f)
                curveTo(117.32f, 3.83f, 117.11f, 3.99f, 117.11f, 4.49f)
                lineTo(117.11f, 4.77f)
                lineTo(116.27f, 4.77f)
                lineTo(116.27f, 4.46f)
                curveTo(116.27f, 3.7f, 116.65f, 3.37f, 117.4f, 2.89f)
                curveTo(117.87f, 2.59f, 118.24f, 2.23f, 118.25f, 1.64f)
                curveTo(118.25f, 1.07f, 117.74f, 0.7f, 117.04f, 0.7f)
                curveTo(116.03f, 0.69f, 115.7f, 1.4f, 115.67f, 1.95f)
                lineTo(114.84f, 1.95f)
                curveTo(114.85f, 0.77f, 115.78f, 0.0f, 117.07f, 0.01f)
                curveTo(118.09f, 0.01f, 119.11f, 0.51f, 119.11f, 1.61f)
                close()
                moveTo(116.18f, 6.69f)
                lineTo(116.19f, 5.65f)
                lineTo(117.23f, 5.65f)
                lineTo(117.22f, 6.69f)
                lineTo(116.18f, 6.69f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(133.67f, 13.05f)
                curveTo(133.12f, 14.58f, 131.66f, 15.27f, 130.31f, 15.59f)
                curveTo(129.08f, 15.87f, 128.63f, 16.03f, 128.31f, 16.92f)
                lineTo(128.13f, 17.42f)
                lineTo(126.64f, 16.88f)
                lineTo(126.83f, 16.33f)
                curveTo(127.32f, 14.98f, 128.19f, 14.62f, 129.83f, 14.25f)
                curveTo(130.85f, 14.02f, 131.74f, 13.61f, 132.12f, 12.57f)
                curveTo(132.48f, 11.56f, 131.82f, 10.57f, 130.58f, 10.13f)
                curveTo(128.78f, 9.48f, 127.75f, 10.53f, 127.34f, 11.49f)
                lineTo(125.87f, 10.96f)
                curveTo(126.64f, 8.88f, 128.77f, 8.1f, 131.05f, 8.92f)
                curveTo(132.87f, 9.57f, 134.37f, 11.11f, 133.67f, 13.05f)
                close()
                moveTo(125.27f, 20.24f)
                lineTo(125.93f, 18.39f)
                lineTo(127.78f, 19.05f)
                lineTo(127.12f, 20.9f)
                lineTo(125.27f, 20.24f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(75.64f, 57.74f)
                lineTo(72.67f, 60.7f)
                lineTo(76.63f, 64.65f)
                lineTo(72.67f, 68.6f)
                lineTo(75.64f, 71.57f)
                lineTo(79.59f, 67.61f)
                lineTo(83.54f, 71.57f)
                lineTo(86.5f, 68.6f)
                lineTo(82.55f, 64.65f)
                lineTo(86.5f, 60.7f)
                lineTo(83.54f, 57.74f)
                lineTo(79.59f, 61.69f)
                lineTo(75.64f, 57.74f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(100.08f, 57.74f)
                lineTo(97.12f, 60.7f)
                lineTo(101.07f, 64.65f)
                lineTo(97.12f, 68.6f)
                lineTo(100.08f, 71.57f)
                lineTo(104.04f, 67.61f)
                lineTo(107.99f, 71.57f)
                lineTo(110.95f, 68.6f)
                lineTo(107.0f, 64.65f)
                lineTo(110.95f, 60.7f)
                lineTo(107.99f, 57.74f)
                lineTo(104.04f, 61.69f)
                lineTo(100.08f, 57.74f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(136.0f, 97.57f)
                lineTo(127.1f, 82.14f)
                lineTo(115.6f, 88.78f)
                lineTo(124.51f, 104.2f)
                curveTo(126.34f, 107.38f, 130.4f, 108.46f, 133.57f, 106.63f)
                curveTo(136.75f, 104.8f, 137.83f, 100.74f, 136.0f, 97.57f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(115.6f, 88.78f)
                lineTo(124.51f, 104.2f)
                curveTo(126.34f, 107.38f, 130.4f, 108.46f, 133.57f, 106.63f)
                verticalLineTo(106.63f)
                curveTo(136.75f, 104.8f, 137.83f, 100.74f, 136.0f, 97.57f)
                lineTo(127.1f, 82.14f)
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(145.71f, 110.91f)
                curveTo(144.73f, 110.91f, 143.82f, 110.56f, 143.12f, 109.86f)
                curveTo(142.42f, 109.15f, 142.07f, 108.24f, 142.07f, 107.26f)
                curveTo(142.07f, 106.28f, 142.42f, 105.37f, 143.12f, 104.67f)
                lineTo(146.48f, 101.3f)
                curveTo(147.12f, 100.67f, 148.03f, 100.25f, 148.94f, 100.25f)
                curveTo(149.85f, 100.18f, 150.76f, 100.53f, 151.46f, 101.16f)
                curveTo(152.17f, 101.79f, 152.66f, 102.63f, 152.73f, 103.54f)
                curveTo(152.87f, 104.46f, 152.59f, 105.37f, 152.03f, 106.14f)
                curveTo(151.88f, 106.35f, 151.54f, 106.42f, 151.32f, 106.28f)
                curveTo(151.11f, 106.14f, 151.04f, 105.79f, 151.18f, 105.58f)
                curveTo(151.6f, 105.02f, 151.74f, 104.38f, 151.68f, 103.75f)
                curveTo(151.6f, 103.12f, 151.25f, 102.49f, 150.76f, 102.07f)
                curveTo(150.27f, 101.44f, 149.64f, 101.23f, 149.01f, 101.23f)
                curveTo(148.31f, 101.3f, 147.68f, 101.58f, 147.26f, 102.0f)
                lineTo(143.89f, 105.37f)
                curveTo(143.4f, 105.86f, 143.12f, 106.56f, 143.12f, 107.26f)
                curveTo(143.12f, 107.96f, 143.4f, 108.59f, 143.89f, 109.15f)
                curveTo(144.87f, 110.14f, 146.63f, 110.14f, 147.61f, 109.15f)
                lineTo(148.24f, 108.45f)
                curveTo(148.45f, 108.24f, 148.8f, 108.24f, 149.01f, 108.45f)
                curveTo(149.22f, 108.66f, 149.22f, 109.01f, 149.01f, 109.22f)
                lineTo(148.38f, 109.86f)
                curveTo(147.68f, 110.56f, 146.76f, 110.91f, 145.71f, 110.91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(151.88f, 108.45f)
                curveTo(150.97f, 108.45f, 150.13f, 108.17f, 149.5f, 107.54f)
                curveTo(148.8f, 106.91f, 148.38f, 106.07f, 148.24f, 105.16f)
                curveTo(148.1f, 104.24f, 148.38f, 103.33f, 148.94f, 102.56f)
                curveTo(149.08f, 102.35f, 149.43f, 102.28f, 149.64f, 102.42f)
                curveTo(149.85f, 102.56f, 149.92f, 102.91f, 149.78f, 103.12f)
                curveTo(149.36f, 103.68f, 149.22f, 104.31f, 149.29f, 104.95f)
                curveTo(149.36f, 105.58f, 149.71f, 106.21f, 150.2f, 106.63f)
                curveTo(150.69f, 107.05f, 151.32f, 107.26f, 152.03f, 107.26f)
                curveTo(152.66f, 107.26f, 153.29f, 106.98f, 153.78f, 106.49f)
                lineTo(157.15f, 103.12f)
                curveTo(157.57f, 102.77f, 157.85f, 102.14f, 157.85f, 101.44f)
                curveTo(157.85f, 100.74f, 157.57f, 100.11f, 157.08f, 99.54f)
                curveTo(156.09f, 98.56f, 154.34f, 98.56f, 153.36f, 99.54f)
                lineTo(152.8f, 100.11f)
                curveTo(152.59f, 100.32f, 152.24f, 100.32f, 152.03f, 100.11f)
                curveTo(151.82f, 99.9f, 151.88f, 99.54f, 152.1f, 99.33f)
                lineTo(152.59f, 98.84f)
                curveTo(153.29f, 98.14f, 154.2f, 97.79f, 155.18f, 97.79f)
                curveTo(156.16f, 97.79f, 157.08f, 98.14f, 157.78f, 98.84f)
                curveTo(158.48f, 99.54f, 158.83f, 100.46f, 158.83f, 101.44f)
                curveTo(158.83f, 102.42f, 158.48f, 103.33f, 157.78f, 104.03f)
                lineTo(154.41f, 107.4f)
                curveTo(153.78f, 108.03f, 152.87f, 108.45f, 151.96f, 108.45f)
                horizontalLineTo(151.88f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(59.87f, 104.2f)
                lineTo(68.77f, 88.78f)
                lineTo(57.28f, 82.14f)
                lineTo(48.37f, 97.57f)
                curveTo(46.54f, 100.74f, 47.63f, 104.8f, 50.8f, 106.63f)
                curveTo(53.98f, 108.46f, 58.03f, 107.38f, 59.87f, 104.2f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(57.28f, 82.14f)
                lineTo(48.37f, 97.57f)
                curveTo(46.54f, 100.74f, 47.63f, 104.8f, 50.8f, 106.63f)
                verticalLineTo(106.63f)
                curveTo(53.98f, 108.46f, 58.03f, 107.38f, 59.87f, 104.2f)
                lineTo(68.77f, 88.78f)
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(24.79f, 102.28f)
                curveTo(24.79f, 100.34f, 26.36f, 98.76f, 28.3f, 98.76f)
                curveTo(30.25f, 98.76f, 31.82f, 100.34f, 31.82f, 102.28f)
                curveTo(31.82f, 104.22f, 30.25f, 105.79f, 28.3f, 105.79f)
                curveTo(26.36f, 105.79f, 24.79f, 104.22f, 24.79f, 102.28f)
                close()
                moveTo(28.3f, 99.85f)
                curveTo(26.96f, 99.85f, 25.87f, 100.93f, 25.87f, 102.28f)
                curveTo(25.87f, 103.62f, 26.96f, 104.71f, 28.3f, 104.71f)
                curveTo(29.65f, 104.71f, 30.74f, 103.62f, 30.74f, 102.28f)
                curveTo(30.74f, 100.93f, 29.65f, 99.85f, 28.3f, 99.85f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(28.85f, 106.91f)
                curveTo(28.03f, 106.81f, 27.2f, 106.94f, 26.46f, 107.29f)
                curveTo(25.72f, 107.64f, 25.09f, 108.19f, 24.65f, 108.89f)
                curveTo(24.31f, 109.42f, 24.09f, 110.03f, 24.01f, 110.66f)
                horizontalLineTo(29.75f)
                curveTo(30.05f, 110.66f, 30.29f, 110.9f, 30.29f, 111.2f)
                curveTo(30.29f, 111.5f, 30.05f, 111.74f, 29.75f, 111.74f)
                horizontalLineTo(23.44f)
                curveTo(23.29f, 111.74f, 23.16f, 111.68f, 23.05f, 111.58f)
                curveTo(22.95f, 111.48f, 22.9f, 111.35f, 22.9f, 111.2f)
                curveTo(22.9f, 110.18f, 23.19f, 109.17f, 23.74f, 108.31f)
                curveTo(24.29f, 107.44f, 25.07f, 106.75f, 26.0f, 106.31f)
                curveTo(26.93f, 105.87f, 27.96f, 105.71f, 28.98f, 105.84f)
                curveTo(30.0f, 105.97f, 30.96f, 106.38f, 31.75f, 107.04f)
                curveTo(31.98f, 107.23f, 32.01f, 107.57f, 31.82f, 107.8f)
                curveTo(31.63f, 108.03f, 31.29f, 108.06f, 31.06f, 107.87f)
                curveTo(30.43f, 107.35f, 29.66f, 107.01f, 28.85f, 106.91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(33.85f, 112.81f)
                curveTo(33.56f, 113.15f, 33.12f, 113.36f, 32.63f, 113.36f)
                curveTo(31.73f, 113.36f, 31.01f, 112.64f, 31.01f, 111.74f)
                curveTo(31.01f, 110.85f, 31.73f, 110.12f, 32.63f, 110.12f)
                curveTo(33.03f, 110.12f, 33.39f, 110.26f, 33.67f, 110.5f)
                curveTo(33.69f, 110.48f, 33.71f, 110.46f, 33.74f, 110.45f)
                lineTo(36.37f, 108.87f)
                curveTo(36.4f, 108.85f, 36.42f, 108.84f, 36.45f, 108.83f)
                curveTo(36.43f, 108.72f, 36.42f, 108.61f, 36.42f, 108.5f)
                curveTo(36.42f, 107.6f, 37.14f, 106.88f, 38.04f, 106.88f)
                curveTo(38.93f, 106.88f, 39.66f, 107.6f, 39.66f, 108.5f)
                curveTo(39.66f, 109.39f, 38.93f, 110.12f, 38.04f, 110.12f)
                curveTo(37.64f, 110.12f, 37.28f, 109.98f, 37.0f, 109.74f)
                curveTo(36.98f, 109.76f, 36.95f, 109.78f, 36.93f, 109.79f)
                lineTo(34.3f, 111.37f)
                curveTo(34.27f, 111.39f, 34.25f, 111.4f, 34.22f, 111.41f)
                curveTo(34.24f, 111.52f, 34.25f, 111.63f, 34.25f, 111.74f)
                curveTo(34.25f, 111.77f, 34.25f, 111.79f, 34.25f, 111.82f)
                curveTo(34.28f, 111.82f, 34.31f, 111.83f, 34.34f, 111.84f)
                lineTo(36.73f, 112.8f)
                curveTo(36.76f, 112.81f, 36.79f, 112.83f, 36.81f, 112.84f)
                curveTo(37.11f, 112.5f, 37.55f, 112.28f, 38.04f, 112.28f)
                curveTo(38.93f, 112.28f, 39.66f, 113.01f, 39.66f, 113.9f)
                curveTo(39.66f, 114.8f, 38.93f, 115.53f, 38.04f, 115.53f)
                curveTo(37.14f, 115.53f, 36.42f, 114.8f, 36.42f, 113.9f)
                curveTo(36.42f, 113.88f, 36.42f, 113.86f, 36.42f, 113.83f)
                curveTo(36.39f, 113.82f, 36.36f, 113.82f, 36.33f, 113.81f)
                lineTo(33.94f, 112.85f)
                curveTo(33.91f, 112.83f, 33.88f, 112.82f, 33.85f, 112.81f)
                close()
            }
        }.build()

        return _mascotDeadLight!!
    }

private var _mascotDeadLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotDeadLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
