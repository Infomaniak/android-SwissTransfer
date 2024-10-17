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
package com.infomaniak.swisstransfer.ui.images.illus.dataProtection

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
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.DataProtectionDark: ImageVector
    get() {

        if (_dataProtectionDark != null) return _dataProtectionDark!!

        _dataProtectionDark = Builder(
            name = "DataProtectionDark",
            defaultWidth = 101.0.dp,
            defaultHeight = 93.0.dp,
            viewportWidth = 101.0f,
            viewportHeight = 93.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF3C4F52)), stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(71.42f, 48.52f)
                lineTo(71.4f, 48.29f)
                horizontalLineTo(71.17f)
                horizontalLineTo(18.96f)
                horizontalLineTo(18.73f)
                lineTo(18.72f, 48.52f)
                lineTo(16.04f, 91.89f)
                lineTo(16.02f, 92.16f)
                horizontalLineTo(16.29f)
                horizontalLineTo(73.84f)
                horizontalLineTo(74.11f)
                lineTo(74.09f, 91.89f)
                lineTo(71.42f, 48.52f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 0.5f, strokeLineCap = Round, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(43.34f, 61.8f)
                curveTo(43.34f, 65.18f, 44.6f, 68.44f, 45.79f, 71.57f)
                curveTo(46.31f, 72.93f, 46.97f, 74.35f, 47.08f, 75.81f)
                curveTo(47.12f, 76.43f, 47.36f, 77.43f, 47.03f, 78.0f)
                curveTo(46.44f, 79.01f, 43.64f, 78.77f, 42.78f, 78.75f)
                curveTo(40.01f, 78.69f, 37.18f, 78.21f, 34.61f, 77.16f)
                curveTo(34.03f, 76.93f, 32.96f, 76.54f, 33.31f, 75.72f)
                curveTo(33.82f, 74.53f, 35.83f, 73.78f, 36.87f, 73.26f)
                curveTo(40.23f, 71.57f, 43.69f, 70.38f, 47.41f, 69.75f)
                curveTo(49.13f, 69.46f, 50.91f, 69.07f, 52.66f, 69.0f)
                curveTo(52.94f, 68.99f, 53.89f, 68.9f, 53.03f, 69.05f)
                curveTo(48.25f, 69.88f, 43.49f, 70.9f, 38.72f, 71.78f)
                curveTo(36.04f, 72.27f, 33.36f, 72.73f, 30.67f, 73.15f)
                curveTo(29.86f, 73.28f, 27.43f, 73.7f, 28.22f, 73.48f)
                curveTo(30.26f, 72.91f, 32.22f, 72.0f, 34.22f, 71.33f)
                curveTo(39.71f, 69.51f, 45.57f, 68.21f, 51.3f, 67.45f)
                curveTo(54.75f, 66.99f, 58.22f, 66.77f, 61.67f, 66.37f)
                curveTo(62.59f, 66.26f, 63.54f, 66.09f, 64.47f, 66.09f)
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)), stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(16.29f, 13.22f)
                horizontalLineTo(16.02f)
                lineTo(16.04f, 13.49f)
                lineTo(18.72f, 48.56f)
                lineTo(18.73f, 48.79f)
                horizontalLineTo(18.96f)
                horizontalLineTo(71.16f)
                horizontalLineTo(71.39f)
                lineTo(71.41f, 48.56f)
                lineTo(73.29f, 23.93f)
                lineTo(73.3f, 23.82f)
                lineTo(73.22f, 23.74f)
                lineTo(63.58f, 13.3f)
                lineTo(63.51f, 13.22f)
                horizontalLineTo(63.4f)
                horizontalLineTo(16.29f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)), stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(63.86f, 13.71f)
                lineTo(63.5f, 13.32f)
                lineTo(63.43f, 13.85f)
                lineTo(62.08f, 23.88f)
                lineTo(62.05f, 24.16f)
                lineTo(62.33f, 24.16f)
                lineTo(73.05f, 24.17f)
                lineTo(73.62f, 24.17f)
                lineTo(73.23f, 23.75f)
                lineTo(63.86f, 13.71f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(26.73f, 25.52f)
                lineTo(52.7f, 25.52f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 53.77f, 26.59f)
                lineTo(53.77f, 26.59f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 52.7f, 27.66f)
                lineTo(26.73f, 27.66f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 25.66f, 26.59f)
                lineTo(25.66f, 26.59f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 26.73f, 25.52f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(26.73f, 30.87f)
                lineTo(62.6f, 30.87f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 63.67f, 31.94f)
                lineTo(63.67f, 31.94f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 62.6f, 33.01f)
                lineTo(26.73f, 33.01f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 25.66f, 31.94f)
                lineTo(25.66f, 31.94f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 26.73f, 30.87f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(26.73f, 36.23f)
                lineTo(62.6f, 36.23f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 63.67f, 37.3f)
                lineTo(63.67f, 37.3f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 62.6f, 38.37f)
                lineTo(26.73f, 38.37f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 25.66f, 37.3f)
                lineTo(25.66f, 37.3f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 26.73f, 36.23f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(26.73f, 41.58f)
                lineTo(62.6f, 41.58f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 63.67f, 42.65f)
                lineTo(63.67f, 42.65f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 62.6f, 43.72f)
                lineTo(26.73f, 43.72f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 25.66f, 42.65f)
                lineTo(25.66f, 42.65f)
                arcTo(1.07f, 1.07f, 0.0f, false, true, 26.73f, 41.58f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(88.36f, 49.61f)
                lineToRelative(5.45f, 6.19f)
                lineToRelative(-20.06f, 17.66f)
                lineToRelative(-5.45f, -6.19f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(90.11f, 51.59f)
                lineToRelative(1.96f, 2.23f)
                lineToRelative(-20.06f, 17.66f)
                lineToRelative(-1.96f, -2.23f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(93.07f, 45.47f)
                curveTo(94.16f, 44.5f, 95.83f, 44.61f, 96.79f, 45.7f)
                lineTo(98.76f, 47.93f)
                curveTo(99.72f, 49.03f, 99.61f, 50.69f, 98.52f, 51.66f)
                lineTo(94.31f, 55.36f)
                lineTo(88.86f, 49.17f)
                lineTo(93.07f, 45.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF1F1F1)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(71.21f, 73.06f)
                curveTo(71.62f, 72.7f, 72.24f, 72.74f, 72.61f, 73.15f)
                curveTo(72.97f, 73.56f, 72.93f, 74.19f, 72.52f, 74.55f)
                lineTo(71.21f, 73.06f)
                close()
                moveTo(69.77f, 69.93f)
                curveTo(69.41f, 69.52f, 68.78f, 69.48f, 68.37f, 69.84f)
                lineTo(69.79f, 71.45f)
                curveTo(69.79f, 71.45f, 69.79f, 71.45f, 69.79f, 71.45f)
                lineTo(71.21f, 73.06f)
                curveTo(71.62f, 72.7f, 71.66f, 72.08f, 71.3f, 71.67f)
                lineTo(71.19f, 71.54f)
                curveTo(70.83f, 71.14f, 70.22f, 71.09f, 69.81f, 71.43f)
                curveTo(70.2f, 71.07f, 70.23f, 70.46f, 69.88f, 70.06f)
                lineTo(69.77f, 69.93f)
                close()
                moveTo(67.07f, 68.36f)
                curveTo(67.48f, 68.0f, 68.1f, 68.04f, 68.46f, 68.45f)
                curveTo(68.82f, 68.86f, 68.78f, 69.48f, 68.37f, 69.84f)
                lineTo(67.07f, 68.36f)
                close()
                moveTo(67.06f, 68.36f)
                lineTo(72.51f, 74.55f)
                lineTo(63.76f, 77.58f)
                curveTo(63.2f, 77.77f, 62.69f, 77.19f, 62.95f, 76.66f)
                lineTo(67.06f, 68.36f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(64.38f, 73.8f)
                lineTo(66.78f, 76.52f)
                lineTo(63.69f, 77.64f)
                curveTo(63.13f, 77.84f, 62.61f, 77.25f, 62.88f, 76.72f)
                lineTo(64.38f, 73.8f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.71f, 16.3f)
                moveToRelative(-14.4f, 1.81f)
                arcToRelative(14.51f, 14.51f, 127.83f, true, true, 28.79f, -3.62f)
                arcToRelative(14.51f, 14.51f, 127.83f, true, true, -28.79f, 3.62f)
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(15.8f, 9.1f)
                    curveTo(16.05f, 9.07f, 16.28f, 9.25f, 16.31f, 9.49f)
                    lineTo(16.42f, 10.39f)
                    curveTo(16.45f, 10.64f, 16.28f, 10.87f, 16.03f, 10.9f)
                    curveTo(15.78f, 10.93f, 15.55f, 10.76f, 15.52f, 10.51f)
                    lineTo(15.41f, 9.61f)
                    curveTo(15.38f, 9.36f, 15.55f, 9.13f, 15.8f, 9.1f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = EvenOdd
                ) {
                    moveTo(14.89f, 20.03f)
                    curveTo(14.19f, 19.74f, 13.57f, 19.28f, 13.09f, 18.68f)
                    curveTo(12.57f, 18.03f, 12.24f, 17.24f, 12.15f, 16.41f)
                    lineTo(12.15f, 16.41f)
                    curveTo(12.02f, 15.22f, 12.36f, 14.03f, 13.09f, 13.09f)
                    curveTo(13.82f, 12.14f, 14.89f, 11.52f, 16.08f, 11.35f)
                    curveTo(16.08f, 11.35f, 16.09f, 11.35f, 16.09f, 11.35f)
                    curveTo(17.28f, 11.22f, 18.47f, 11.56f, 19.41f, 12.29f)
                    curveTo(20.36f, 13.02f, 20.98f, 14.09f, 21.15f, 15.27f)
                    lineTo(21.15f, 15.28f)
                    curveTo(21.26f, 16.11f, 21.14f, 16.95f, 20.8f, 17.72f)
                    curveTo(20.49f, 18.41f, 20.0f, 19.01f, 19.39f, 19.46f)
                    lineTo(19.69f, 21.86f)
                    curveTo(19.74f, 22.22f, 19.64f, 22.58f, 19.42f, 22.87f)
                    curveTo(19.19f, 23.15f, 18.87f, 23.34f, 18.51f, 23.38f)
                    lineTo(16.71f, 23.61f)
                    curveTo(16.35f, 23.65f, 15.99f, 23.56f, 15.71f, 23.33f)
                    curveTo(15.42f, 23.11f, 15.24f, 22.79f, 15.19f, 22.43f)
                    lineTo(14.89f, 20.03f)
                    close()
                    moveTo(15.81f, 20.07f)
                    lineTo(15.92f, 20.97f)
                    lineTo(18.62f, 20.63f)
                    lineTo(18.51f, 19.73f)
                    lineTo(15.81f, 20.07f)
                    close()
                    moveTo(16.04f, 21.87f)
                    lineTo(16.09f, 22.32f)
                    curveTo(16.11f, 22.44f, 16.17f, 22.54f, 16.26f, 22.62f)
                    curveTo(16.36f, 22.69f, 16.48f, 22.72f, 16.6f, 22.71f)
                    lineTo(18.4f, 22.48f)
                    curveTo(18.52f, 22.47f, 18.63f, 22.41f, 18.7f, 22.31f)
                    curveTo(18.77f, 22.22f, 18.81f, 22.1f, 18.79f, 21.98f)
                    lineTo(18.74f, 21.53f)
                    lineTo(16.04f, 21.87f)
                    close()
                    moveTo(13.81f, 13.64f)
                    curveTo(14.39f, 12.89f, 15.25f, 12.39f, 16.2f, 12.25f)
                    curveTo(17.15f, 12.15f, 18.1f, 12.42f, 18.86f, 13.01f)
                    curveTo(19.61f, 13.6f, 20.11f, 14.45f, 20.25f, 15.4f)
                    curveTo(20.34f, 16.06f, 20.25f, 16.74f, 19.97f, 17.34f)
                    curveTo(19.71f, 17.92f, 19.3f, 18.41f, 18.79f, 18.78f)
                    lineTo(15.3f, 19.22f)
                    curveTo(14.72f, 18.99f, 14.2f, 18.61f, 13.8f, 18.12f)
                    curveTo(13.39f, 17.6f, 13.13f, 16.97f, 13.05f, 16.31f)
                    curveTo(12.95f, 15.36f, 13.22f, 14.4f, 13.81f, 13.64f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(10.3f, 16.19f)
                    curveTo(10.05f, 16.22f, 9.87f, 16.45f, 9.9f, 16.7f)
                    curveTo(9.93f, 16.95f, 10.16f, 17.12f, 10.41f, 17.09f)
                    lineTo(11.31f, 16.98f)
                    curveTo(11.56f, 16.95f, 11.73f, 16.72f, 11.7f, 16.47f)
                    curveTo(11.67f, 16.22f, 11.44f, 16.05f, 11.19f, 16.08f)
                    lineTo(10.3f, 16.19f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(10.77f, 11.7f)
                    curveTo(10.92f, 11.5f, 11.21f, 11.46f, 11.41f, 11.61f)
                    lineTo(12.15f, 12.16f)
                    curveTo(12.35f, 12.31f, 12.39f, 12.59f, 12.24f, 12.79f)
                    curveTo(12.09f, 12.99f, 11.81f, 13.04f, 11.6f, 12.89f)
                    lineTo(10.86f, 12.34f)
                    curveTo(10.66f, 12.19f, 10.62f, 11.9f, 10.77f, 11.7f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(21.99f, 14.72f)
                    curveTo(21.74f, 14.75f, 21.57f, 14.98f, 21.6f, 15.23f)
                    curveTo(21.63f, 15.47f, 21.86f, 15.65f, 22.1f, 15.62f)
                    lineTo(23.0f, 15.51f)
                    curveTo(23.25f, 15.48f, 23.43f, 15.25f, 23.4f, 15.0f)
                    curveTo(23.37f, 14.75f, 23.14f, 14.58f, 22.89f, 14.61f)
                    lineTo(21.99f, 14.72f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(21.32f, 10.38f)
                    curveTo(21.51f, 10.53f, 21.54f, 10.82f, 21.39f, 11.01f)
                    lineTo(20.81f, 11.73f)
                    curveTo(20.65f, 11.92f, 20.36f, 11.95f, 20.17f, 11.8f)
                    curveTo(19.97f, 11.64f, 19.94f, 11.35f, 20.1f, 11.16f)
                    lineTo(20.68f, 10.44f)
                    curveTo(20.84f, 10.25f, 21.12f, 10.22f, 21.32f, 10.38f)
                    close()
                }
            }
        }.build()

        return _dataProtectionDark!!
    }

private var _dataProtectionDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.DataProtectionDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
