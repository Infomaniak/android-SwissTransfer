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
package com.infomaniak.swisstransfer.ui.images.illus.matomo

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

val AppIllus.MatomoLight: ImageVector
    get() {

        if (_matomoLight != null) return _matomoLight!!

        _matomoLight = Builder(
            name = "MatomoLight",
            defaultWidth = 181.0.dp,
            defaultHeight = 32.0.dp,
            viewportWidth = 181.0f,
            viewportHeight = 32.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF95C748)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(43.11f, 18.68f)
                    lineTo(42.99f, 18.5f)
                    curveTo(42.97f, 18.48f, 42.96f, 18.45f, 42.94f, 18.42f)
                    lineTo(35.19f, 6.14f)
                    lineTo(25.16f, 13.89f)
                    lineTo(32.53f, 25.65f)
                    lineTo(32.62f, 25.8f)
                    lineTo(32.66f, 25.86f)
                    curveTo(33.59f, 27.26f, 35.02f, 28.23f, 36.63f, 28.56f)
                    curveTo(38.24f, 28.88f, 39.91f, 28.53f, 41.27f, 27.59f)
                    curveTo(42.64f, 26.65f, 43.6f, 25.19f, 43.95f, 23.52f)
                    curveTo(44.3f, 21.85f, 44.0f, 20.11f, 43.13f, 18.67f)
                }
                path(
                    fill = SolidColor(Color(0xFF35BFC0)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(13.05f, 22.1f)
                    curveTo(13.05f, 20.81f, 12.68f, 19.55f, 11.99f, 18.48f)
                    curveTo(11.3f, 17.4f, 10.32f, 16.56f, 9.17f, 16.07f)
                    curveTo(8.03f, 15.58f, 6.77f, 15.45f, 5.55f, 15.7f)
                    curveTo(4.33f, 15.95f, 3.21f, 16.57f, 2.34f, 17.49f)
                    curveTo(1.46f, 18.4f, 0.86f, 19.56f, 0.62f, 20.83f)
                    curveTo(0.38f, 22.1f, 0.5f, 23.41f, 0.98f, 24.6f)
                    curveTo(1.45f, 25.79f, 2.26f, 26.81f, 3.29f, 27.53f)
                    curveTo(4.32f, 28.25f, 5.53f, 28.63f, 6.77f, 28.63f)
                    curveTo(8.44f, 28.63f, 10.03f, 27.94f, 11.21f, 26.72f)
                    curveTo(12.39f, 25.5f, 13.05f, 23.83f, 13.05f, 22.1f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3253A0)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(36.16f, 9.56f)
                    curveTo(36.16f, 8.54f, 35.93f, 7.53f, 35.49f, 6.62f)
                    curveTo(35.06f, 5.71f, 34.42f, 4.91f, 33.63f, 4.3f)
                    curveTo(32.85f, 3.69f, 31.94f, 3.28f, 30.97f, 3.1f)
                    curveTo(30.01f, 2.93f, 29.02f, 2.99f, 28.08f, 3.28f)
                    curveTo(27.14f, 3.57f, 26.28f, 4.09f, 25.57f, 4.79f)
                    curveTo(24.85f, 5.49f, 24.31f, 6.35f, 23.98f, 7.31f)
                    curveTo(23.64f, 8.27f, 23.53f, 9.3f, 23.64f, 10.31f)
                    curveTo(23.76f, 11.32f, 24.1f, 12.29f, 24.64f, 13.14f)
                    lineTo(19.86f, 6.14f)
                    curveTo(19.3f, 5.19f, 18.51f, 4.4f, 17.58f, 3.86f)
                    curveTo(16.64f, 3.32f, 15.59f, 3.03f, 14.52f, 3.03f)
                    curveTo(13.45f, 3.03f, 12.4f, 3.32f, 11.46f, 3.86f)
                    curveTo(10.53f, 4.4f, 9.74f, 5.19f, 9.18f, 6.14f)
                    lineTo(1.6f, 18.45f)
                    curveTo(2.19f, 17.54f, 2.99f, 16.8f, 3.93f, 16.3f)
                    curveTo(4.86f, 15.79f, 5.9f, 15.55f, 6.96f, 15.57f)
                    curveTo(8.01f, 15.6f, 9.04f, 15.91f, 9.95f, 16.46f)
                    curveTo(10.86f, 17.01f, 11.62f, 17.79f, 12.16f, 18.73f)
                    lineTo(17.11f, 25.97f)
                    curveTo(17.69f, 26.8f, 18.45f, 27.47f, 19.33f, 27.93f)
                    curveTo(20.21f, 28.4f, 21.18f, 28.64f, 22.16f, 28.64f)
                    curveTo(23.15f, 28.64f, 24.12f, 28.4f, 25.0f, 27.93f)
                    curveTo(25.87f, 27.47f, 26.63f, 26.8f, 27.21f, 25.97f)
                    lineTo(27.27f, 25.89f)
                    curveTo(27.4f, 25.7f, 27.52f, 25.5f, 27.62f, 25.3f)
                    lineTo(35.07f, 13.22f)
                    curveTo(35.76f, 12.14f, 36.14f, 10.86f, 36.13f, 9.56f)
                    moveTo(26.11f, 14.79f)
                    lineTo(26.24f, 14.89f)
                    lineTo(26.15f, 14.79f)
                    moveTo(33.46f, 14.92f)
                    lineTo(33.59f, 14.8f)
                    lineTo(33.44f, 14.92f)
                    moveTo(1.45f, 18.71f)
                    curveTo(1.49f, 18.62f, 1.54f, 18.55f, 1.59f, 18.47f)
                    lineTo(1.44f, 18.71f)
                    horizontalLineTo(1.45f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFF38334)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(36.13f, 9.56f)
                    curveTo(36.13f, 8.27f, 35.76f, 7.0f, 35.07f, 5.93f)
                    curveTo(34.38f, 4.86f, 33.4f, 4.02f, 32.25f, 3.53f)
                    curveTo(31.1f, 3.03f, 29.84f, 2.9f, 28.62f, 3.16f)
                    curveTo(27.41f, 3.41f, 26.29f, 4.03f, 25.41f, 4.95f)
                    curveTo(24.54f, 5.86f, 23.94f, 7.03f, 23.7f, 8.3f)
                    curveTo(23.46f, 9.56f, 23.58f, 10.88f, 24.06f, 12.07f)
                    curveTo(24.54f, 13.26f, 25.35f, 14.28f, 26.38f, 15.0f)
                    curveTo(27.41f, 15.71f, 28.63f, 16.09f, 29.87f, 16.09f)
                    curveTo(31.53f, 16.09f, 33.13f, 15.4f, 34.3f, 14.18f)
                    curveTo(35.48f, 12.95f, 36.14f, 11.29f, 36.14f, 9.56f)
                }
                path(
                    fill = SolidColor(Color(0xFF3152A0)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(29.87f, 16.09f)
                    curveTo(28.83f, 16.09f, 27.81f, 15.82f, 26.89f, 15.31f)
                    curveTo(25.98f, 14.79f, 25.2f, 14.05f, 24.63f, 13.14f)
                    lineTo(19.85f, 6.14f)
                    curveTo(19.29f, 5.19f, 18.5f, 4.4f, 17.57f, 3.86f)
                    curveTo(16.63f, 3.32f, 15.58f, 3.03f, 14.51f, 3.03f)
                    curveTo(13.44f, 3.03f, 12.39f, 3.32f, 11.45f, 3.86f)
                    curveTo(10.52f, 4.4f, 9.74f, 5.19f, 9.18f, 6.14f)
                    lineTo(1.57f, 18.45f)
                    curveTo(2.16f, 17.54f, 2.96f, 16.8f, 3.9f, 16.3f)
                    curveTo(4.83f, 15.79f, 5.88f, 15.55f, 6.93f, 15.57f)
                    curveTo(7.98f, 15.6f, 9.01f, 15.91f, 9.92f, 16.46f)
                    curveTo(10.83f, 17.01f, 11.59f, 17.79f, 12.14f, 18.73f)
                    lineTo(17.11f, 25.97f)
                    curveTo(17.69f, 26.8f, 18.45f, 27.47f, 19.33f, 27.93f)
                    curveTo(20.21f, 28.4f, 21.18f, 28.64f, 22.16f, 28.64f)
                    curveTo(23.15f, 28.64f, 24.12f, 28.4f, 24.99f, 27.93f)
                    curveTo(25.87f, 27.47f, 26.63f, 26.8f, 27.21f, 25.97f)
                    lineTo(27.26f, 25.89f)
                    lineTo(27.62f, 25.33f)
                    lineTo(35.07f, 13.25f)
                    curveTo(34.49f, 14.13f, 33.71f, 14.85f, 32.81f, 15.34f)
                    curveTo(31.9f, 15.84f, 30.89f, 16.09f, 29.87f, 16.09f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3F4445)), stroke = null, strokeLineWidth =
                        0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                        4.0f, pathFillType = NonZero
                ) {
                    moveTo(180.5f, 17.59f)
                    curveTo(180.5f, 19.38f, 179.99f, 21.14f, 179.03f, 22.63f)
                    curveTo(178.07f, 24.13f, 176.71f, 25.29f, 175.11f, 25.98f)
                    curveTo(173.52f, 26.66f, 171.77f, 26.84f, 170.07f, 26.49f)
                    curveTo(168.38f, 26.14f, 166.83f, 25.28f, 165.61f, 24.01f)
                    curveTo(164.39f, 22.74f, 163.56f, 21.12f, 163.22f, 19.36f)
                    curveTo(162.88f, 17.6f, 163.06f, 15.77f, 163.72f, 14.11f)
                    curveTo(164.38f, 12.45f, 165.49f, 11.04f, 166.93f, 10.04f)
                    curveTo(168.36f, 9.04f, 170.05f, 8.51f, 171.78f, 8.51f)
                    curveTo(174.09f, 8.51f, 176.31f, 9.46f, 177.94f, 11.17f)
                    curveTo(179.58f, 12.87f, 180.5f, 15.18f, 180.5f, 17.59f)
                    close()
                    moveTo(171.78f, 13.04f)
                    curveTo(170.91f, 13.04f, 170.07f, 13.31f, 169.35f, 13.81f)
                    curveTo(168.63f, 14.31f, 168.07f, 15.02f, 167.74f, 15.85f)
                    curveTo(167.41f, 16.68f, 167.32f, 17.59f, 167.49f, 18.48f)
                    curveTo(167.66f, 19.36f, 168.07f, 20.17f, 168.69f, 20.8f)
                    curveTo(169.3f, 21.44f, 170.07f, 21.87f, 170.92f, 22.05f)
                    curveTo(171.77f, 22.22f, 172.65f, 22.13f, 173.45f, 21.79f)
                    curveTo(174.25f, 21.45f, 174.93f, 20.86f, 175.41f, 20.11f)
                    curveTo(175.89f, 19.37f, 176.15f, 18.49f, 176.15f, 17.59f)
                    curveTo(176.15f, 16.99f, 176.03f, 16.4f, 175.81f, 15.85f)
                    curveTo(175.59f, 15.3f, 175.27f, 14.79f, 174.87f, 14.37f)
                    curveTo(174.46f, 13.95f, 173.98f, 13.61f, 173.45f, 13.39f)
                    curveTo(172.92f, 13.16f, 172.35f, 13.04f, 171.78f, 13.04f)
                    close()
                    moveTo(134.32f, 17.59f)
                    curveTo(134.32f, 19.39f, 133.81f, 21.14f, 132.85f, 22.64f)
                    curveTo(131.89f, 24.13f, 130.53f, 25.3f, 128.94f, 25.98f)
                    curveTo(127.34f, 26.67f, 125.59f, 26.85f, 123.9f, 26.5f)
                    curveTo(122.21f, 26.15f, 120.65f, 25.28f, 119.43f, 24.01f)
                    curveTo(118.21f, 22.74f, 117.38f, 21.13f, 117.04f, 19.36f)
                    curveTo(116.71f, 17.6f, 116.88f, 15.78f, 117.54f, 14.12f)
                    curveTo(118.2f, 12.46f, 119.32f, 11.04f, 120.75f, 10.04f)
                    curveTo(122.19f, 9.05f, 123.87f, 8.51f, 125.6f, 8.51f)
                    curveTo(127.91f, 8.51f, 130.13f, 9.47f, 131.77f, 11.17f)
                    curveTo(133.4f, 12.88f, 134.32f, 15.19f, 134.32f, 17.59f)
                    close()
                    moveTo(125.6f, 13.05f)
                    curveTo(124.74f, 13.05f, 123.89f, 13.31f, 123.17f, 13.81f)
                    curveTo(122.45f, 14.31f, 121.89f, 15.02f, 121.56f, 15.85f)
                    curveTo(121.23f, 16.68f, 121.14f, 17.6f, 121.31f, 18.48f)
                    curveTo(121.48f, 19.36f, 121.9f, 20.17f, 122.51f, 20.81f)
                    curveTo(123.12f, 21.45f, 123.9f, 21.88f, 124.75f, 22.05f)
                    curveTo(125.6f, 22.23f, 126.47f, 22.14f, 127.27f, 21.8f)
                    curveTo(128.07f, 21.45f, 128.75f, 20.87f, 129.23f, 20.12f)
                    curveTo(129.71f, 19.37f, 129.97f, 18.49f, 129.97f, 17.59f)
                    curveTo(129.97f, 16.39f, 129.51f, 15.23f, 128.69f, 14.38f)
                    curveTo(127.87f, 13.52f, 126.76f, 13.05f, 125.6f, 13.05f)
                    close()
                    moveTo(92.7f, 8.51f)
                    curveTo(92.27f, 8.49f, 91.85f, 8.61f, 91.49f, 8.85f)
                    curveTo(91.14f, 9.09f, 90.86f, 9.44f, 90.7f, 9.85f)
                    curveTo(89.38f, 9.0f, 87.86f, 8.54f, 86.31f, 8.51f)
                    curveTo(84.76f, 8.48f, 83.23f, 8.88f, 81.87f, 9.67f)
                    curveTo(80.52f, 10.46f, 79.39f, 11.61f, 78.61f, 13.0f)
                    curveTo(77.82f, 14.39f, 77.41f, 15.98f, 77.41f, 17.59f)
                    curveTo(77.41f, 19.21f, 77.82f, 20.79f, 78.61f, 22.19f)
                    curveTo(79.39f, 23.58f, 80.52f, 24.73f, 81.87f, 25.52f)
                    curveTo(83.23f, 26.31f, 84.76f, 26.71f, 86.31f, 26.68f)
                    curveTo(87.86f, 26.65f, 89.38f, 26.18f, 90.7f, 25.34f)
                    curveTo(90.86f, 25.75f, 91.14f, 26.1f, 91.5f, 26.33f)
                    curveTo(91.86f, 26.57f, 92.28f, 26.69f, 92.7f, 26.67f)
                    curveTo(93.0f, 26.68f, 93.3f, 26.63f, 93.57f, 26.51f)
                    curveTo(93.85f, 26.39f, 94.1f, 26.22f, 94.31f, 25.99f)
                    curveTo(94.51f, 25.77f, 94.67f, 25.5f, 94.77f, 25.21f)
                    curveTo(94.87f, 24.92f, 94.92f, 24.61f, 94.89f, 24.3f)
                    verticalLineTo(10.87f)
                    curveTo(94.92f, 10.56f, 94.87f, 10.25f, 94.77f, 9.96f)
                    curveTo(94.67f, 9.67f, 94.51f, 9.4f, 94.31f, 9.18f)
                    curveTo(94.1f, 8.95f, 93.85f, 8.78f, 93.57f, 8.66f)
                    curveTo(93.3f, 8.54f, 93.0f, 8.49f, 92.7f, 8.5f)
                    moveTo(86.16f, 22.14f)
                    curveTo(85.32f, 22.14f, 84.49f, 21.88f, 83.78f, 21.4f)
                    curveTo(83.06f, 20.92f, 82.5f, 20.23f, 82.16f, 19.42f)
                    curveTo(81.82f, 18.62f, 81.71f, 17.72f, 81.84f, 16.85f)
                    curveTo(81.98f, 15.98f, 82.36f, 15.17f, 82.93f, 14.52f)
                    curveTo(83.5f, 13.86f, 84.24f, 13.4f, 85.06f, 13.18f)
                    curveTo(85.88f, 12.95f, 86.74f, 12.98f, 87.55f, 13.26f)
                    curveTo(88.35f, 13.54f, 89.06f, 14.06f, 89.59f, 14.75f)
                    curveTo(90.12f, 15.44f, 90.44f, 16.28f, 90.52f, 17.16f)
                    verticalLineTo(17.99f)
                    curveTo(90.42f, 19.12f, 89.92f, 20.17f, 89.11f, 20.93f)
                    curveTo(88.31f, 21.7f, 87.25f, 22.12f, 86.16f, 22.13f)
                    moveTo(115.38f, 18.85f)
                    curveTo(115.35f, 18.27f, 115.11f, 17.72f, 114.7f, 17.32f)
                    curveTo(114.3f, 16.92f, 113.76f, 16.7f, 113.21f, 16.7f)
                    curveTo(112.65f, 16.7f, 112.11f, 16.92f, 111.71f, 17.32f)
                    curveTo(111.3f, 17.72f, 111.06f, 18.27f, 111.03f, 18.85f)
                    curveTo(110.95f, 19.67f, 110.59f, 20.43f, 110.0f, 20.98f)
                    curveTo(109.41f, 21.53f, 108.65f, 21.84f, 107.86f, 21.84f)
                    curveTo(107.07f, 21.84f, 106.31f, 21.53f, 105.72f, 20.98f)
                    curveTo(105.13f, 20.43f, 104.76f, 19.67f, 104.69f, 18.85f)
                    verticalLineTo(13.05f)
                    horizontalLineTo(109.81f)
                    curveTo(110.39f, 13.05f, 110.94f, 12.81f, 111.35f, 12.38f)
                    curveTo(111.76f, 11.95f, 111.99f, 11.38f, 111.99f, 10.78f)
                    curveTo(111.99f, 10.17f, 111.76f, 9.6f, 111.35f, 9.17f)
                    curveTo(110.94f, 8.75f, 110.39f, 8.51f, 109.81f, 8.51f)
                    horizontalLineTo(104.68f)
                    verticalLineTo(6.96f)
                    curveTo(104.65f, 6.38f, 104.41f, 5.84f, 104.0f, 5.44f)
                    curveTo(103.6f, 5.04f, 103.06f, 4.81f, 102.5f, 4.81f)
                    curveTo(101.94f, 4.81f, 101.41f, 5.04f, 101.0f, 5.44f)
                    curveTo(100.6f, 5.84f, 100.36f, 6.38f, 100.33f, 6.96f)
                    verticalLineTo(8.52f)
                    horizontalLineTo(98.57f)
                    curveTo(98.0f, 8.52f, 97.45f, 8.76f, 97.04f, 9.19f)
                    curveTo(96.63f, 9.61f, 96.4f, 10.18f, 96.4f, 10.78f)
                    curveTo(96.4f, 11.38f, 96.63f, 11.95f, 97.04f, 12.38f)
                    curveTo(97.45f, 12.8f, 98.0f, 13.04f, 98.57f, 13.04f)
                    horizontalLineTo(100.33f)
                    verticalLineTo(18.85f)
                    curveTo(100.36f, 20.92f, 101.16f, 22.89f, 102.57f, 24.36f)
                    curveTo(103.97f, 25.82f, 105.87f, 26.65f, 107.86f, 26.67f)
                    curveTo(109.85f, 26.67f, 111.76f, 25.85f, 113.17f, 24.38f)
                    curveTo(114.58f, 22.91f, 115.38f, 20.92f, 115.38f, 18.85f)
                    close()
                    moveTo(161.55f, 24.4f)
                    verticalLineTo(16.24f)
                    curveTo(161.53f, 14.69f, 161.07f, 13.19f, 160.23f, 11.92f)
                    curveTo(159.39f, 10.65f, 158.21f, 9.66f, 156.83f, 9.09f)
                    curveTo(155.46f, 8.51f, 153.95f, 8.38f, 152.5f, 8.69f)
                    curveTo(151.05f, 9.01f, 149.72f, 9.77f, 148.68f, 10.87f)
                    curveTo(147.64f, 9.77f, 146.31f, 9.01f, 144.86f, 8.7f)
                    curveTo(143.41f, 8.38f, 141.9f, 8.52f, 140.53f, 9.1f)
                    curveTo(139.15f, 9.67f, 137.97f, 10.66f, 137.13f, 11.94f)
                    curveTo(136.3f, 13.21f, 135.84f, 14.72f, 135.83f, 16.26f)
                    verticalLineTo(24.4f)
                    curveTo(135.85f, 24.98f, 136.1f, 25.53f, 136.5f, 25.93f)
                    curveTo(136.9f, 26.33f, 137.44f, 26.55f, 138.0f, 26.55f)
                    curveTo(138.56f, 26.55f, 139.09f, 26.33f, 139.5f, 25.93f)
                    curveTo(139.9f, 25.53f, 140.14f, 24.98f, 140.17f, 24.4f)
                    verticalLineTo(16.33f)
                    curveTo(140.24f, 15.51f, 140.61f, 14.74f, 141.2f, 14.18f)
                    curveTo(141.79f, 13.62f, 142.55f, 13.31f, 143.35f, 13.31f)
                    curveTo(144.14f, 13.31f, 144.91f, 13.62f, 145.5f, 14.18f)
                    curveTo(146.09f, 14.74f, 146.45f, 15.51f, 146.52f, 16.33f)
                    verticalLineTo(24.32f)
                    curveTo(146.53f, 24.93f, 146.76f, 25.52f, 147.16f, 25.95f)
                    curveTo(147.57f, 26.39f, 148.13f, 26.65f, 148.71f, 26.67f)
                    curveTo(149.29f, 26.64f, 149.84f, 26.38f, 150.24f, 25.94f)
                    curveTo(150.63f, 25.51f, 150.85f, 24.92f, 150.84f, 24.32f)
                    verticalLineTo(16.33f)
                    curveTo(150.91f, 15.51f, 151.28f, 14.74f, 151.87f, 14.18f)
                    curveTo(152.46f, 13.62f, 153.22f, 13.31f, 154.02f, 13.31f)
                    curveTo(154.81f, 13.31f, 155.58f, 13.62f, 156.17f, 14.18f)
                    curveTo(156.76f, 14.74f, 157.12f, 15.51f, 157.19f, 16.33f)
                    verticalLineTo(24.4f)
                    curveTo(157.19f, 25.0f, 157.42f, 25.57f, 157.83f, 26.0f)
                    curveTo(158.24f, 26.42f, 158.79f, 26.66f, 159.36f, 26.66f)
                    curveTo(159.94f, 26.66f, 160.49f, 26.42f, 160.9f, 26.0f)
                    curveTo(161.3f, 25.57f, 161.53f, 25.0f, 161.53f, 24.4f)
                    moveTo(75.93f, 24.39f)
                    verticalLineTo(16.23f)
                    curveTo(75.91f, 14.68f, 75.45f, 13.18f, 74.61f, 11.91f)
                    curveTo(73.77f, 10.64f, 72.59f, 9.65f, 71.21f, 9.08f)
                    curveTo(69.83f, 8.51f, 68.33f, 8.37f, 66.88f, 8.69f)
                    curveTo(65.42f, 9.01f, 64.1f, 9.77f, 63.06f, 10.87f)
                    curveTo(62.02f, 9.77f, 60.69f, 9.01f, 59.24f, 8.69f)
                    curveTo(57.79f, 8.38f, 56.28f, 8.52f, 54.91f, 9.09f)
                    curveTo(53.53f, 9.67f, 52.35f, 10.66f, 51.52f, 11.93f)
                    curveTo(50.68f, 13.21f, 50.23f, 14.71f, 50.21f, 16.25f)
                    verticalLineTo(24.39f)
                    curveTo(50.21f, 24.99f, 50.44f, 25.57f, 50.85f, 25.99f)
                    curveTo(51.25f, 26.41f, 51.8f, 26.65f, 52.38f, 26.65f)
                    curveTo(52.96f, 26.65f, 53.51f, 26.41f, 53.91f, 25.99f)
                    curveTo(54.32f, 25.57f, 54.55f, 24.99f, 54.55f, 24.39f)
                    verticalLineTo(16.33f)
                    curveTo(54.55f, 15.45f, 54.88f, 14.6f, 55.48f, 13.98f)
                    curveTo(56.08f, 13.36f, 56.89f, 13.01f, 57.74f, 13.01f)
                    curveTo(58.58f, 13.01f, 59.4f, 13.36f, 59.99f, 13.98f)
                    curveTo(60.59f, 14.6f, 60.93f, 15.45f, 60.93f, 16.33f)
                    verticalLineTo(24.31f)
                    curveTo(60.93f, 24.92f, 61.16f, 25.51f, 61.57f, 25.95f)
                    curveTo(61.98f, 26.39f, 62.54f, 26.64f, 63.12f, 26.66f)
                    curveTo(63.7f, 26.64f, 64.25f, 26.37f, 64.65f, 25.94f)
                    curveTo(65.04f, 25.5f, 65.26f, 24.91f, 65.25f, 24.31f)
                    verticalLineTo(16.33f)
                    curveTo(65.33f, 15.51f, 65.7f, 14.75f, 66.28f, 14.2f)
                    curveTo(66.87f, 13.64f, 67.63f, 13.34f, 68.42f, 13.34f)
                    curveTo(69.21f, 13.34f, 69.98f, 13.64f, 70.56f, 14.2f)
                    curveTo(71.15f, 14.75f, 71.52f, 15.51f, 71.6f, 16.33f)
                    verticalLineTo(24.39f)
                    curveTo(71.6f, 24.99f, 71.83f, 25.57f, 72.23f, 25.99f)
                    curveTo(72.64f, 26.41f, 73.19f, 26.65f, 73.77f, 26.65f)
                    curveTo(74.34f, 26.65f, 74.89f, 26.41f, 75.3f, 25.99f)
                    curveTo(75.71f, 25.57f, 75.94f, 24.99f, 75.94f, 24.39f)
                }
            }
        }.build()

        return _matomoLight!!
    }

private var _matomoLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MatomoLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
