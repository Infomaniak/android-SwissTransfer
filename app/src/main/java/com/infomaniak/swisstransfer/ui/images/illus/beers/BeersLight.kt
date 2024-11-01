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
package com.infomaniak.swisstransfer.ui.images.illus.beers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.AppImages.previewSize

val AppIllus.BeersLight: ImageVector
    get() {

        if (_beersLight != null) return _beersLight!!

        _beersLight = Builder(
            name = "BeersLight",
            defaultWidth = 175.0.dp,
            defaultHeight = 115.0.dp,
            viewportWidth = 175.0f,
            viewportHeight = 115.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 2.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(36.57f, 46.26f)
                curveTo(33.08f, 45.41f, 29.44f, 45.37f, 25.93f, 46.15f)
                curveTo(22.41f, 46.93f, 19.13f, 48.51f, 16.33f, 50.76f)
                curveTo(13.52f, 53.01f, 11.28f, 55.88f, 9.76f, 59.14f)
                curveTo(8.24f, 62.4f, 7.49f, 65.97f, 7.57f, 69.56f)
                curveTo(7.65f, 73.16f, 8.56f, 76.68f, 10.22f, 79.87f)
                curveTo(11.88f, 83.06f, 14.25f, 85.83f, 17.16f, 87.95f)
                curveTo(20.06f, 90.08f, 23.41f, 91.51f, 26.95f, 92.13f)
                curveTo(30.49f, 92.75f, 34.13f, 92.55f, 37.58f, 91.54f)
                lineTo(35.43f, 84.19f)
                curveTo(33.11f, 84.87f, 30.66f, 85.0f, 28.28f, 84.58f)
                curveTo(25.89f, 84.16f, 23.64f, 83.2f, 21.68f, 81.77f)
                curveTo(19.73f, 80.34f, 18.13f, 78.48f, 17.01f, 76.33f)
                curveTo(15.9f, 74.18f, 15.29f, 71.81f, 15.23f, 69.39f)
                curveTo(15.18f, 66.97f, 15.68f, 64.57f, 16.7f, 62.38f)
                curveTo(17.73f, 60.18f, 19.24f, 58.25f, 21.13f, 56.73f)
                curveTo(23.01f, 55.22f, 25.22f, 54.16f, 27.59f, 53.63f)
                curveTo(29.95f, 53.11f, 32.4f, 53.13f, 34.75f, 53.71f)
                lineTo(36.57f, 46.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(85.62f, 25.67f)
                curveTo(86.7f, 25.94f, 87.8f, 25.27f, 88.06f, 24.19f)
                curveTo(88.32f, 23.11f, 87.66f, 22.01f, 86.58f, 21.75f)
                curveTo(85.49f, 21.49f, 84.4f, 22.15f, 84.14f, 23.23f)
                curveTo(83.87f, 24.32f, 84.54f, 25.41f, 85.62f, 25.67f)
                close()
                moveTo(83.78f, 29.62f)
                curveTo(83.44f, 31.0f, 82.05f, 31.85f, 80.67f, 31.51f)
                curveTo(79.28f, 31.17f, 78.43f, 29.78f, 78.77f, 28.4f)
                curveTo(79.11f, 27.01f, 80.51f, 26.16f, 81.89f, 26.5f)
                curveTo(83.27f, 26.84f, 84.12f, 28.24f, 83.78f, 29.62f)
                close()
                moveTo(83.51f, 42.75f)
                curveTo(85.34f, 42.64f, 86.97f, 41.36f, 87.43f, 39.48f)
                curveTo(87.99f, 37.18f, 86.58f, 34.85f, 84.27f, 34.29f)
                curveTo(83.43f, 34.09f, 82.59f, 34.14f, 81.84f, 34.41f)
                curveTo(81.25f, 34.01f, 80.59f, 33.72f, 79.87f, 33.54f)
                curveTo(76.62f, 32.75f, 73.34f, 34.74f, 72.55f, 37.99f)
                curveTo(72.46f, 38.36f, 72.4f, 38.73f, 72.38f, 39.1f)
                curveTo(72.22f, 38.98f, 72.03f, 38.89f, 71.83f, 38.84f)
                curveTo(71.02f, 38.64f, 70.22f, 39.13f, 70.02f, 39.94f)
                curveTo(69.93f, 40.31f, 69.99f, 40.68f, 70.16f, 41.0f)
                curveTo(70.11f, 40.99f, 70.07f, 40.97f, 70.03f, 40.96f)
                curveTo(68.02f, 40.47f, 66.0f, 41.7f, 65.51f, 43.71f)
                curveTo(65.26f, 44.71f, 65.45f, 45.73f, 65.95f, 46.55f)
                curveTo(63.92f, 46.84f, 62.14f, 48.22f, 61.4f, 50.22f)
                curveTo(59.24f, 50.01f, 57.33f, 50.79f, 55.04f, 51.74f)
                curveTo(52.97f, 52.59f, 50.58f, 53.57f, 47.39f, 54.08f)
                curveTo(40.57f, 55.17f, 33.41f, 39.71f, 33.41f, 39.71f)
                lineTo(24.0f, 99.04f)
                curveTo(23.87f, 99.83f, 24.38f, 100.58f, 25.15f, 100.78f)
                lineTo(59.53f, 109.17f)
                curveTo(60.31f, 109.36f, 61.1f, 108.92f, 61.35f, 108.16f)
                lineTo(82.88f, 43.55f)
                curveTo(83.11f, 43.3f, 83.32f, 43.04f, 83.51f, 42.75f)
                close()
                moveTo(87.48f, 32.27f)
                curveTo(87.29f, 33.07f, 86.49f, 33.56f, 85.69f, 33.36f)
                curveTo(84.89f, 33.17f, 84.41f, 32.37f, 84.6f, 31.57f)
                curveTo(84.79f, 30.77f, 85.6f, 30.29f, 86.39f, 30.48f)
                curveTo(87.19f, 30.67f, 87.68f, 31.48f, 87.48f, 32.27f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(31.58f, 81.83f)
                lineTo(29.46f, 96.29f)
                curveTo(29.34f, 97.07f, 29.84f, 97.82f, 30.61f, 98.01f)
                lineTo(32.73f, 98.52f)
                curveTo(33.5f, 98.71f, 34.29f, 98.28f, 34.55f, 97.53f)
                lineTo(39.33f, 83.72f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(43.2f, 84.67f)
                lineTo(41.07f, 99.12f)
                curveTo(40.96f, 99.91f, 41.46f, 100.65f, 42.23f, 100.84f)
                lineTo(44.35f, 101.36f)
                curveTo(45.12f, 101.55f, 45.91f, 101.12f, 46.17f, 100.37f)
                lineTo(50.94f, 86.56f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(54.82f, 87.51f)
                lineTo(52.69f, 101.96f)
                curveTo(52.57f, 102.75f, 53.07f, 103.49f, 53.85f, 103.68f)
                lineTo(55.96f, 104.2f)
                curveTo(56.74f, 104.39f, 57.52f, 103.96f, 57.78f, 103.21f)
                lineTo(62.56f, 89.4f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(36.18f, 58.81f)
                arcToRelative(0.95f, 0.95f, 103.78f, true, false, 1.85f, 0.45f)
                arcToRelative(0.95f, 0.95f, 103.78f, true, false, -1.85f, -0.45f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(57.22f, 62.96f)
                moveToRelative(-1.45f, -0.35f)
                arcToRelative(1.49f, 1.49f, 58.72f, true, true, 2.9f, 0.71f)
                arcToRelative(1.49f, 1.49f, 58.72f, true, true, -2.9f, -0.71f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(59.54f, 68.15f)
                moveToRelative(-0.48f, -0.12f)
                arcToRelative(0.5f, 0.5f, 58.72f, true, true, 0.97f, 0.24f)
                arcToRelative(0.5f, 0.5f, 58.72f, true, true, -0.97f, -0.24f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(39.53f, 76.59f)
                moveToRelative(-0.48f, -0.12f)
                arcToRelative(0.5f, 0.5f, 58.72f, true, true, 0.97f, 0.24f)
                arcToRelative(0.5f, 0.5f, 58.72f, true, true, -0.97f, -0.24f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(45.91f, 71.48f)
                moveToRelative(-0.97f, -0.24f)
                arcToRelative(1.0f, 1.0f, 58.72f, true, true, 1.94f, 0.47f)
                arcToRelative(1.0f, 1.0f, 58.72f, true, true, -1.94f, -0.47f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(58.96f, 84.16f)
                moveToRelative(-0.73f, -0.18f)
                arcToRelative(0.75f, 0.75f, 58.72f, true, true, 1.45f, 0.35f)
                arcToRelative(0.75f, 0.75f, 58.72f, true, true, -1.45f, -0.35f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(73.46f, 60.52f)
                moveToRelative(-0.73f, -0.18f)
                arcToRelative(0.75f, 0.75f, 58.72f, true, true, 1.45f, 0.35f)
                arcToRelative(0.75f, 0.75f, 58.72f, true, true, -1.45f, -0.35f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(36.67f, 30.25f)
                curveTo(35.51f, 29.97f, 34.36f, 30.73f, 34.17f, 31.91f)
                lineTo(23.38f, 98.93f)
                curveTo(23.21f, 99.98f, 23.87f, 100.98f, 24.91f, 101.23f)
                lineTo(59.54f, 109.69f)
                curveTo(60.58f, 109.94f, 61.63f, 109.36f, 61.96f, 108.35f)
                lineTo(83.28f, 43.9f)
                curveTo(83.65f, 42.77f, 82.98f, 41.56f, 81.83f, 41.28f)
                lineTo(36.67f, 30.25f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(22.27f, 99.42f)
                curveTo(22.33f, 98.96f, 22.77f, 98.66f, 23.22f, 98.76f)
                lineTo(62.66f, 108.4f)
                curveTo(63.11f, 108.51f, 63.36f, 108.99f, 63.2f, 109.42f)
                lineTo(62.32f, 111.72f)
                curveTo(62.18f, 112.09f, 61.8f, 112.29f, 61.42f, 112.2f)
                lineTo(22.57f, 102.71f)
                curveTo(22.19f, 102.62f, 21.94f, 102.26f, 21.99f, 101.87f)
                lineTo(22.27f, 99.42f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 2.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(129.77f, 37.24f)
                curveTo(133.05f, 35.77f, 136.63f, 35.07f, 140.22f, 35.21f)
                curveTo(143.81f, 35.34f, 147.33f, 36.3f, 150.49f, 38.01f)
                curveTo(153.65f, 39.72f, 156.38f, 42.13f, 158.46f, 45.07f)
                curveTo(160.55f, 48.0f, 161.92f, 51.37f, 162.49f, 54.92f)
                curveTo(163.06f, 58.47f, 162.81f, 62.11f, 161.75f, 65.54f)
                curveTo(160.69f, 68.98f, 158.85f, 72.13f, 156.38f, 74.74f)
                curveTo(153.91f, 77.35f, 150.87f, 79.36f, 147.5f, 80.61f)
                curveTo(144.13f, 81.86f, 140.52f, 82.32f, 136.94f, 81.95f)
                lineTo(137.73f, 74.33f)
                curveTo(140.13f, 74.58f, 142.57f, 74.27f, 144.84f, 73.43f)
                curveTo(147.1f, 72.59f, 149.15f, 71.23f, 150.81f, 69.48f)
                curveTo(152.48f, 67.72f, 153.71f, 65.6f, 154.43f, 63.28f)
                curveTo(155.14f, 60.97f, 155.31f, 58.53f, 154.93f, 56.14f)
                curveTo(154.54f, 53.75f, 153.62f, 51.48f, 152.21f, 49.5f)
                curveTo(150.82f, 47.53f, 148.98f, 45.9f, 146.85f, 44.75f)
                curveTo(144.72f, 43.6f, 142.35f, 42.95f, 139.93f, 42.86f)
                curveTo(137.51f, 42.77f, 135.11f, 43.24f, 132.9f, 44.23f)
                lineTo(129.77f, 37.24f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(86.44f, 38.22f)
                curveTo(85.8f, 36.51f, 86.59f, 34.58f, 88.28f, 33.83f)
                curveTo(90.04f, 33.04f, 92.1f, 33.83f, 92.89f, 35.58f)
                curveTo(93.06f, 35.96f, 93.15f, 36.35f, 93.18f, 36.73f)
                curveTo(93.33f, 36.55f, 93.52f, 36.39f, 93.75f, 36.29f)
                curveTo(94.5f, 35.95f, 95.39f, 36.29f, 95.72f, 37.04f)
                curveTo(95.88f, 37.39f, 95.89f, 37.77f, 95.78f, 38.11f)
                curveTo(95.82f, 38.09f, 95.86f, 38.07f, 95.9f, 38.06f)
                curveTo(97.79f, 37.21f, 100.0f, 38.06f, 100.84f, 39.94f)
                curveTo(101.26f, 40.89f, 101.26f, 41.91f, 100.92f, 42.81f)
                curveTo(102.97f, 42.73f, 104.97f, 43.77f, 106.06f, 45.6f)
                curveTo(108.15f, 45.01f, 110.16f, 45.44f, 112.59f, 45.95f)
                curveTo(114.78f, 46.41f, 117.31f, 46.95f, 120.54f, 46.87f)
                curveTo(127.44f, 46.72f, 131.7f, 30.22f, 131.7f, 30.22f)
                lineTo(151.65f, 86.88f)
                curveTo(151.91f, 87.64f, 151.56f, 88.47f, 150.82f, 88.8f)
                lineTo(118.53f, 103.25f)
                curveTo(117.79f, 103.58f, 116.93f, 103.29f, 116.55f, 102.59f)
                lineTo(83.14f, 41.86f)
                curveTo(83.14f, 41.86f, 83.66f, 42.21f, 84.53f, 42.77f)
                curveTo(84.45f, 41.05f, 85.14f, 39.37f, 86.44f, 38.22f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(141.09f, 71.32f)
                lineTo(145.78f, 85.16f)
                curveTo(146.04f, 85.91f, 145.68f, 86.73f, 144.96f, 87.06f)
                lineTo(142.97f, 87.95f)
                curveTo(142.24f, 88.27f, 141.39f, 87.99f, 141.0f, 87.3f)
                lineTo(133.81f, 74.58f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(130.17f, 76.21f)
                lineTo(134.87f, 90.04f)
                curveTo(135.13f, 90.8f, 134.77f, 91.62f, 134.04f, 91.94f)
                lineTo(132.05f, 92.83f)
                curveTo(131.33f, 93.16f, 130.48f, 92.88f, 130.09f, 92.19f)
                lineTo(122.9f, 79.46f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(119.26f, 81.09f)
                lineTo(123.96f, 94.93f)
                curveTo(124.21f, 95.68f, 123.85f, 96.5f, 123.13f, 96.83f)
                lineTo(121.14f, 97.72f)
                curveTo(120.42f, 98.04f, 119.56f, 97.76f, 119.17f, 97.07f)
                lineTo(111.98f, 84.35f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(132.41f, 49.5f)
                arcToRelative(0.95f, 0.95f, 65.84f, true, true, -1.74f, 0.78f)
                arcToRelative(0.95f, 0.95f, 65.84f, true, true, 1.74f, -0.78f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(112.47f, 57.39f)
                moveToRelative(1.36f, -0.61f)
                arcToRelative(1.49f, 1.49f, 110.89f, true, false, -2.73f, 1.22f)
                arcToRelative(1.49f, 1.49f, 110.89f, true, false, 2.73f, -1.22f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(111.12f, 62.9f)
                moveToRelative(0.45f, -0.2f)
                arcToRelative(0.5f, 0.5f, 110.89f, true, false, -0.91f, 0.41f)
                arcToRelative(0.5f, 0.5f, 110.89f, true, false, 0.91f, -0.41f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(132.33f, 67.6f)
                moveToRelative(0.45f, -0.2f)
                arcToRelative(0.5f, 0.5f, 110.89f, true, false, -0.91f, 0.41f)
                arcToRelative(0.5f, 0.5f, 110.89f, true, false, 0.91f, -0.41f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(125.13f, 63.73f)
                moveToRelative(0.91f, -0.41f)
                arcToRelative(1.0f, 1.0f, 110.89f, true, false, -1.82f, 0.81f)
                arcToRelative(1.0f, 1.0f, 110.89f, true, false, 1.82f, -0.81f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(114.57f, 78.55f)
                moveToRelative(0.68f, -0.31f)
                arcToRelative(0.75f, 0.75f, 110.89f, true, false, -1.36f, 0.61f)
                arcToRelative(0.75f, 0.75f, 110.89f, true, false, 1.36f, -0.61f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(96.06f, 57.91f)
                moveToRelative(0.68f, -0.31f)
                arcToRelative(0.75f, 0.75f, 110.89f, true, false, -1.36f, 0.61f)
                arcToRelative(0.75f, 0.75f, 110.89f, true, false, 1.36f, -0.61f)
            }
            path(
                stroke = SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(126.79f, 21.5f)
                curveTo(127.87f, 21.02f, 129.14f, 21.56f, 129.54f, 22.68f)
                lineTo(152.24f, 86.66f)
                curveTo(152.6f, 87.66f, 152.12f, 88.77f, 151.15f, 89.2f)
                lineTo(118.61f, 103.76f)
                curveTo(117.64f, 104.2f, 116.5f, 103.82f, 115.99f, 102.88f)
                lineTo(83.4f, 43.33f)
                curveTo(82.83f, 42.29f, 83.27f, 40.98f, 84.36f, 40.49f)
                lineTo(126.79f, 21.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(153.42f, 86.95f)
                curveTo(153.28f, 86.5f, 152.79f, 86.28f, 152.37f, 86.47f)
                lineTo(115.31f, 103.06f)
                curveTo(114.88f, 103.25f, 114.72f, 103.76f, 114.96f, 104.16f)
                lineTo(116.24f, 106.27f)
                curveTo(116.44f, 106.6f, 116.86f, 106.73f, 117.21f, 106.57f)
                lineTo(153.72f, 90.23f)
                curveTo(154.07f, 90.07f, 154.25f, 89.68f, 154.14f, 89.31f)
                lineTo(153.42f, 86.95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(78.16f, 6.88f)
                lineTo(79.59f, 2.35f)
                lineTo(77.27f, 1.61f)
                lineTo(75.83f, 6.14f)
                lineTo(72.05f, 3.14f)
                lineTo(70.61f, 5.03f)
                lineTo(74.43f, 7.92f)
                lineTo(70.58f, 10.8f)
                lineTo(72.19f, 12.74f)
                lineTo(76.0f, 9.72f)
                lineTo(77.59f, 14.46f)
                lineTo(79.77f, 13.68f)
                lineTo(78.31f, 9.15f)
                lineTo(83.18f, 9.02f)
                lineTo(82.98f, 6.61f)
                lineTo(78.16f, 6.88f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(64.0f, 25.58f)
                lineTo(64.5f, 24.0f)
                lineTo(63.69f, 23.74f)
                lineTo(63.19f, 25.32f)
                lineTo(61.87f, 24.28f)
                lineTo(61.37f, 24.93f)
                lineTo(62.7f, 25.94f)
                lineTo(61.36f, 26.94f)
                lineTo(61.92f, 27.62f)
                lineTo(63.25f, 26.56f)
                lineTo(63.8f, 28.22f)
                lineTo(64.56f, 27.94f)
                lineTo(64.05f, 26.37f)
                lineTo(65.75f, 26.32f)
                lineTo(65.68f, 25.48f)
                lineTo(64.0f, 25.58f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(97.28f, 16.51f)
                lineTo(98.03f, 14.15f)
                lineTo(96.82f, 13.77f)
                lineTo(96.07f, 16.12f)
                lineTo(94.11f, 14.56f)
                lineTo(93.36f, 15.54f)
                lineTo(95.34f, 17.05f)
                lineTo(93.34f, 18.55f)
                lineTo(94.18f, 19.55f)
                lineTo(96.16f, 17.98f)
                lineTo(96.99f, 20.44f)
                lineTo(98.12f, 20.04f)
                lineTo(97.36f, 17.69f)
                lineTo(99.89f, 17.62f)
                lineTo(99.79f, 16.37f)
                lineTo(97.28f, 16.51f)
                close()
            }
        }.build()

        return _beersLight!!
    }

private var _beersLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(
            imageVector = AppIllus.BeersLight,
            contentDescription = null,
            modifier = Modifier.size(previewSize),
        )
    }
}
