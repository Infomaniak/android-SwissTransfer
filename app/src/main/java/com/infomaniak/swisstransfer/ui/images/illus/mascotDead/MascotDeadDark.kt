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

val AppIllus.MascotDeadDark: ImageVector
    get() {

        if (_mascotDeadDark != null) return _mascotDeadDark!!

        _mascotDeadDark = Builder(
            name = "MascotDeadDark",
            defaultWidth = 185.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 185.0f,
            viewportHeight = 160.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.0f, 145.66f)
                arcToRelative(92.23f, 14.34f, 0.0f, true, false, 184.46f, 0.0f)
                arcToRelative(92.23f, 14.34f, 0.0f, true, false, -184.46f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(56.34f, 52.95f)
                curveTo(58.32f, 34.64f, 73.78f, 20.76f, 92.2f, 20.76f)
                curveTo(110.62f, 20.76f, 126.08f, 34.64f, 128.06f, 52.95f)
                lineTo(138.3f, 147.67f)
                lineTo(127.71f, 133.12f)
                curveTo(121.73f, 124.91f, 109.48f, 124.91f, 103.5f, 133.12f)
                curveTo(97.92f, 140.78f, 86.49f, 140.78f, 80.91f, 133.12f)
                curveTo(74.93f, 124.91f, 62.68f, 124.91f, 56.7f, 133.12f)
                lineTo(46.1f, 147.67f)
                lineTo(56.34f, 52.95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(92.2f, 78.23f)
                moveToRelative(-2.79f, 0.0f)
                arcToRelative(2.79f, 2.79f, 0.0f, true, true, 5.59f, 0.0f)
                arcToRelative(2.79f, 2.79f, 0.0f, true, true, -5.59f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(58.59f, 12.98f)
                curveTo(59.21f, 14.09f, 58.86f, 15.31f, 58.36f, 16.27f)
                curveTo(57.89f, 17.14f, 57.75f, 17.49f, 58.11f, 18.14f)
                lineTo(58.31f, 18.5f)
                lineTo(57.23f, 19.11f)
                lineTo(57.01f, 18.71f)
                curveTo(56.46f, 17.73f, 56.69f, 17.03f, 57.32f, 15.87f)
                curveTo(57.71f, 15.14f, 57.93f, 14.41f, 57.5f, 13.65f)
                curveTo(57.09f, 12.91f, 56.17f, 12.8f, 55.27f, 13.3f)
                curveTo(53.96f, 14.03f, 54.05f, 15.18f, 54.4f, 15.92f)
                lineTo(53.34f, 16.51f)
                curveTo(52.5f, 14.99f, 53.14f, 13.33f, 54.8f, 12.4f)
                curveTo(56.12f, 11.66f, 57.8f, 11.56f, 58.59f, 12.98f)
                close()
                moveTo(58.51f, 21.64f)
                lineTo(57.76f, 20.3f)
                lineTo(59.1f, 19.55f)
                lineTo(59.85f, 20.89f)
                lineTo(58.51f, 21.64f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(118.84f, 1.61f)
                curveTo(118.83f, 2.47f, 118.22f, 3.07f, 117.61f, 3.47f)
                curveTo(117.04f, 3.83f, 116.85f, 3.99f, 116.84f, 4.49f)
                lineTo(116.84f, 4.77f)
                lineTo(116.0f, 4.77f)
                lineTo(116.0f, 4.46f)
                curveTo(116.0f, 3.7f, 116.38f, 3.37f, 117.13f, 2.89f)
                curveTo(117.6f, 2.59f, 117.97f, 2.23f, 117.98f, 1.64f)
                curveTo(117.98f, 1.07f, 117.47f, 0.7f, 116.78f, 0.7f)
                curveTo(115.76f, 0.69f, 115.43f, 1.4f, 115.39f, 1.95f)
                lineTo(114.57f, 1.95f)
                curveTo(114.58f, 0.77f, 115.51f, 0.0f, 116.8f, 0.01f)
                curveTo(117.82f, 0.01f, 118.84f, 0.51f, 118.84f, 1.61f)
                close()
                moveTo(115.91f, 6.69f)
                lineTo(115.92f, 5.65f)
                lineTo(116.96f, 5.65f)
                lineTo(116.95f, 6.69f)
                lineTo(115.91f, 6.69f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(133.4f, 13.05f)
                curveTo(132.85f, 14.58f, 131.39f, 15.27f, 130.04f, 15.59f)
                curveTo(128.82f, 15.87f, 128.36f, 16.03f, 128.04f, 16.92f)
                lineTo(127.86f, 17.42f)
                lineTo(126.37f, 16.88f)
                lineTo(126.56f, 16.33f)
                curveTo(127.05f, 14.98f, 127.92f, 14.62f, 129.56f, 14.25f)
                curveTo(130.59f, 14.02f, 131.48f, 13.61f, 131.85f, 12.57f)
                curveTo(132.21f, 11.56f, 131.55f, 10.57f, 130.31f, 10.13f)
                curveTo(128.51f, 9.48f, 127.48f, 10.53f, 127.07f, 11.49f)
                lineTo(125.6f, 10.96f)
                curveTo(126.37f, 8.88f, 128.5f, 8.1f, 130.78f, 8.92f)
                curveTo(132.6f, 9.57f, 134.1f, 11.11f, 133.4f, 13.05f)
                close()
                moveTo(125.0f, 20.24f)
                lineTo(125.66f, 18.39f)
                lineTo(127.51f, 19.05f)
                lineTo(126.85f, 20.9f)
                lineTo(125.0f, 20.24f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(75.37f, 57.74f)
                lineTo(72.4f, 60.7f)
                lineTo(76.36f, 64.65f)
                lineTo(72.4f, 68.6f)
                lineTo(75.37f, 71.57f)
                lineTo(79.32f, 67.61f)
                lineTo(83.27f, 71.57f)
                lineTo(86.23f, 68.6f)
                lineTo(82.28f, 64.65f)
                lineTo(86.23f, 60.7f)
                lineTo(83.27f, 57.74f)
                lineTo(79.32f, 61.69f)
                lineTo(75.37f, 57.74f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(99.82f, 57.74f)
                lineTo(96.85f, 60.7f)
                lineTo(100.8f, 64.65f)
                lineTo(96.85f, 68.6f)
                lineTo(99.81f, 71.57f)
                lineTo(103.77f, 67.61f)
                lineTo(107.72f, 71.57f)
                lineTo(110.68f, 68.6f)
                lineTo(106.73f, 64.65f)
                lineTo(110.68f, 60.7f)
                lineTo(107.72f, 57.74f)
                lineTo(103.77f, 61.69f)
                lineTo(99.82f, 57.74f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(135.73f, 97.57f)
                lineTo(126.83f, 82.14f)
                lineTo(115.33f, 88.78f)
                lineTo(124.24f, 104.2f)
                curveTo(126.07f, 107.38f, 130.13f, 108.46f, 133.3f, 106.63f)
                curveTo(136.48f, 104.8f, 137.57f, 100.74f, 135.73f, 97.57f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(115.33f, 88.78f)
                lineTo(124.24f, 104.2f)
                curveTo(126.07f, 107.38f, 130.13f, 108.46f, 133.3f, 106.63f)
                verticalLineTo(106.63f)
                curveTo(136.48f, 104.8f, 137.57f, 100.74f, 135.73f, 97.57f)
                lineTo(126.83f, 82.14f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(145.44f, 110.91f)
                curveTo(144.46f, 110.91f, 143.55f, 110.56f, 142.85f, 109.86f)
                curveTo(142.15f, 109.15f, 141.8f, 108.24f, 141.8f, 107.26f)
                curveTo(141.8f, 106.28f, 142.15f, 105.37f, 142.85f, 104.66f)
                lineTo(146.21f, 101.3f)
                curveTo(146.85f, 100.67f, 147.76f, 100.25f, 148.67f, 100.25f)
                curveTo(149.58f, 100.18f, 150.49f, 100.53f, 151.2f, 101.16f)
                curveTo(151.9f, 101.79f, 152.39f, 102.63f, 152.46f, 103.54f)
                curveTo(152.6f, 104.45f, 152.32f, 105.37f, 151.76f, 106.14f)
                curveTo(151.62f, 106.35f, 151.26f, 106.42f, 151.05f, 106.28f)
                curveTo(150.84f, 106.14f, 150.77f, 105.79f, 150.91f, 105.58f)
                curveTo(151.34f, 105.01f, 151.48f, 104.38f, 151.4f, 103.75f)
                curveTo(151.34f, 103.12f, 150.99f, 102.49f, 150.49f, 102.07f)
                curveTo(150.0f, 101.44f, 149.37f, 101.23f, 148.74f, 101.23f)
                curveTo(148.04f, 101.3f, 147.41f, 101.58f, 146.99f, 102.0f)
                lineTo(143.62f, 105.37f)
                curveTo(143.13f, 105.86f, 142.85f, 106.56f, 142.85f, 107.26f)
                curveTo(142.85f, 107.96f, 143.13f, 108.59f, 143.62f, 109.15f)
                curveTo(144.6f, 110.14f, 146.35f, 110.14f, 147.34f, 109.15f)
                lineTo(147.97f, 108.45f)
                curveTo(148.18f, 108.24f, 148.53f, 108.24f, 148.74f, 108.45f)
                curveTo(148.95f, 108.66f, 148.95f, 109.01f, 148.74f, 109.22f)
                lineTo(148.11f, 109.86f)
                curveTo(147.41f, 110.56f, 146.49f, 110.91f, 145.44f, 110.91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(151.62f, 108.45f)
                curveTo(150.7f, 108.45f, 149.86f, 108.17f, 149.23f, 107.54f)
                curveTo(148.53f, 106.91f, 148.11f, 106.07f, 147.97f, 105.15f)
                curveTo(147.83f, 104.24f, 148.11f, 103.33f, 148.67f, 102.56f)
                curveTo(148.81f, 102.35f, 149.16f, 102.28f, 149.37f, 102.42f)
                curveTo(149.58f, 102.56f, 149.65f, 102.91f, 149.51f, 103.12f)
                curveTo(149.09f, 103.68f, 148.95f, 104.31f, 149.02f, 104.94f)
                curveTo(149.09f, 105.58f, 149.44f, 106.21f, 149.93f, 106.63f)
                curveTo(150.42f, 107.05f, 151.05f, 107.26f, 151.76f, 107.26f)
                curveTo(152.39f, 107.26f, 153.02f, 106.98f, 153.51f, 106.49f)
                lineTo(156.88f, 103.12f)
                curveTo(157.3f, 102.77f, 157.58f, 102.14f, 157.58f, 101.44f)
                curveTo(157.58f, 100.74f, 157.3f, 100.11f, 156.81f, 99.54f)
                curveTo(155.82f, 98.56f, 154.07f, 98.56f, 153.09f, 99.54f)
                lineTo(152.53f, 100.11f)
                curveTo(152.32f, 100.32f, 151.97f, 100.32f, 151.76f, 100.11f)
                curveTo(151.55f, 99.89f, 151.62f, 99.54f, 151.83f, 99.33f)
                lineTo(152.32f, 98.84f)
                curveTo(153.02f, 98.14f, 153.93f, 97.79f, 154.91f, 97.79f)
                curveTo(155.9f, 97.79f, 156.81f, 98.14f, 157.51f, 98.84f)
                curveTo(158.21f, 99.54f, 158.56f, 100.46f, 158.56f, 101.44f)
                curveTo(158.56f, 102.42f, 158.21f, 103.33f, 157.51f, 104.03f)
                lineTo(154.14f, 107.4f)
                curveTo(153.51f, 108.03f, 152.6f, 108.45f, 151.69f, 108.45f)
                horizontalLineTo(151.62f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(59.6f, 104.2f)
                lineTo(68.5f, 88.78f)
                lineTo(57.01f, 82.14f)
                lineTo(48.1f, 97.57f)
                curveTo(46.27f, 100.74f, 47.36f, 104.8f, 50.53f, 106.63f)
                curveTo(53.71f, 108.46f, 57.76f, 107.38f, 59.6f, 104.2f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                    SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                    Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                    NonZero
            ) {
                moveTo(57.01f, 82.14f)
                lineTo(48.1f, 97.57f)
                curveTo(46.27f, 100.74f, 47.36f, 104.8f, 50.53f, 106.63f)
                verticalLineTo(106.63f)
                curveTo(53.71f, 108.46f, 57.76f, 107.38f, 59.6f, 104.2f)
                lineTo(68.5f, 88.78f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = EvenOdd
            ) {
                moveTo(24.52f, 102.28f)
                curveTo(24.52f, 100.34f, 26.1f, 98.76f, 28.04f, 98.76f)
                curveTo(29.98f, 98.76f, 31.55f, 100.34f, 31.55f, 102.28f)
                curveTo(31.55f, 104.22f, 29.98f, 105.79f, 28.04f, 105.79f)
                curveTo(26.1f, 105.79f, 24.52f, 104.22f, 24.52f, 102.28f)
                close()
                moveTo(28.04f, 99.85f)
                curveTo(26.69f, 99.85f, 25.6f, 100.93f, 25.6f, 102.28f)
                curveTo(25.6f, 103.62f, 26.69f, 104.71f, 28.04f, 104.71f)
                curveTo(29.38f, 104.71f, 30.47f, 103.62f, 30.47f, 102.28f)
                curveTo(30.47f, 100.93f, 29.38f, 99.85f, 28.04f, 99.85f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(28.58f, 106.91f)
                curveTo(27.76f, 106.81f, 26.94f, 106.94f, 26.19f, 107.29f)
                curveTo(25.45f, 107.64f, 24.82f, 108.19f, 24.38f, 108.89f)
                curveTo(24.04f, 109.43f, 23.82f, 110.03f, 23.74f, 110.66f)
                horizontalLineTo(29.48f)
                curveTo(29.78f, 110.66f, 30.02f, 110.9f, 30.02f, 111.2f)
                curveTo(30.02f, 111.5f, 29.78f, 111.74f, 29.48f, 111.74f)
                horizontalLineTo(23.17f)
                curveTo(23.03f, 111.74f, 22.89f, 111.69f, 22.79f, 111.58f)
                curveTo(22.69f, 111.48f, 22.63f, 111.35f, 22.63f, 111.2f)
                curveTo(22.63f, 110.18f, 22.92f, 109.17f, 23.47f, 108.31f)
                curveTo(24.02f, 107.44f, 24.81f, 106.75f, 25.73f, 106.31f)
                curveTo(26.66f, 105.88f, 27.7f, 105.71f, 28.71f, 105.84f)
                curveTo(29.73f, 105.97f, 30.69f, 106.38f, 31.48f, 107.04f)
                curveTo(31.71f, 107.23f, 31.74f, 107.57f, 31.55f, 107.8f)
                curveTo(31.36f, 108.03f, 31.02f, 108.06f, 30.79f, 107.87f)
                curveTo(30.16f, 107.35f, 29.39f, 107.01f, 28.58f, 106.91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(33.59f, 112.81f)
                curveTo(33.29f, 113.15f, 32.85f, 113.36f, 32.36f, 113.36f)
                curveTo(31.47f, 113.36f, 30.74f, 112.64f, 30.74f, 111.74f)
                curveTo(30.74f, 110.85f, 31.47f, 110.12f, 32.36f, 110.12f)
                curveTo(32.76f, 110.12f, 33.12f, 110.26f, 33.4f, 110.5f)
                curveTo(33.42f, 110.48f, 33.45f, 110.46f, 33.47f, 110.45f)
                lineTo(36.1f, 108.87f)
                curveTo(36.13f, 108.85f, 36.16f, 108.84f, 36.18f, 108.83f)
                curveTo(36.16f, 108.72f, 36.15f, 108.61f, 36.15f, 108.5f)
                curveTo(36.15f, 107.6f, 36.87f, 106.88f, 37.77f, 106.88f)
                curveTo(38.67f, 106.88f, 39.39f, 107.6f, 39.39f, 108.5f)
                curveTo(39.39f, 109.39f, 38.67f, 110.12f, 37.77f, 110.12f)
                curveTo(37.38f, 110.12f, 37.01f, 109.98f, 36.73f, 109.74f)
                curveTo(36.71f, 109.76f, 36.69f, 109.78f, 36.66f, 109.79f)
                lineTo(34.03f, 111.37f)
                curveTo(34.0f, 111.39f, 33.98f, 111.4f, 33.95f, 111.41f)
                curveTo(33.97f, 111.52f, 33.99f, 111.63f, 33.99f, 111.74f)
                curveTo(33.99f, 111.77f, 33.98f, 111.79f, 33.98f, 111.82f)
                curveTo(34.01f, 111.82f, 34.04f, 111.83f, 34.07f, 111.84f)
                lineTo(36.47f, 112.8f)
                curveTo(36.49f, 112.81f, 36.52f, 112.83f, 36.55f, 112.84f)
                curveTo(36.84f, 112.5f, 37.28f, 112.28f, 37.77f, 112.28f)
                curveTo(38.67f, 112.28f, 39.39f, 113.01f, 39.39f, 113.91f)
                curveTo(39.39f, 114.8f, 38.67f, 115.53f, 37.77f, 115.53f)
                curveTo(36.87f, 115.53f, 36.15f, 114.8f, 36.15f, 113.91f)
                curveTo(36.15f, 113.88f, 36.15f, 113.86f, 36.15f, 113.83f)
                curveTo(36.12f, 113.83f, 36.09f, 113.82f, 36.06f, 113.81f)
                lineTo(33.67f, 112.85f)
                curveTo(33.64f, 112.84f, 33.61f, 112.82f, 33.59f, 112.81f)
                close()
            }
        }.build()

        return _mascotDeadDark!!
    }

private var _mascotDeadDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotDeadDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
