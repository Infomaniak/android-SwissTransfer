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

val AppIllus.MatomoDark: ImageVector
    get() {

        if (_matomoDark != null) return _matomoDark!!

        _matomoDark = Builder(
            name = "MatomoDark",
            defaultWidth = 181.0.dp,
            defaultHeight = 32.0.dp,
            viewportWidth = 181.0f,
            viewportHeight = 32.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(16.5f, 16.0f)
                    moveToRelative(-16.0f, 0.0f)
                    arcToRelative(16.0f, 16.0f, 0.0f, true, true, 32.0f, 0.0f)
                    arcToRelative(16.0f, 16.0f, 0.0f, true, true, -32.0f, 0.0f)
                }
                path(
                    fill = SolidColor(Color(0xFF95C748)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(28.2f, 17.84f)
                    lineTo(28.14f, 17.74f)
                    curveTo(28.13f, 17.73f, 28.12f, 17.72f, 28.11f, 17.7f)
                    lineTo(23.7f, 11.0f)
                    lineTo(18.01f, 15.23f)
                    lineTo(22.19f, 21.65f)
                    lineTo(22.24f, 21.73f)
                    lineTo(22.27f, 21.76f)
                    curveTo(22.8f, 22.53f, 23.61f, 23.05f, 24.52f, 23.23f)
                    curveTo(25.44f, 23.41f, 26.38f, 23.22f, 27.16f, 22.71f)
                    curveTo(27.94f, 22.19f, 28.48f, 21.4f, 28.68f, 20.48f)
                    curveTo(28.88f, 19.57f, 28.71f, 18.62f, 28.21f, 17.83f)
                }
                path(
                    fill = SolidColor(Color(0xFF35BFC0)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
                ) {
                    moveTo(11.13f, 19.71f)
                    curveTo(11.13f, 19.01f, 10.92f, 18.32f, 10.53f, 17.73f)
                    curveTo(10.14f, 17.14f, 9.58f, 16.69f, 8.93f, 16.42f)
                    curveTo(8.28f, 16.15f, 7.56f, 16.08f, 6.87f, 16.22f)
                    curveTo(6.18f, 16.35f, 5.54f, 16.69f, 5.05f, 17.19f)
                    curveTo(4.55f, 17.69f, 4.21f, 18.32f, 4.07f, 19.01f)
                    curveTo(3.93f, 19.71f, 4.0f, 20.42f, 4.27f, 21.07f)
                    curveTo(4.54f, 21.72f, 5.0f, 22.28f, 5.59f, 22.67f)
                    curveTo(6.17f, 23.06f, 6.86f, 23.27f, 7.56f, 23.27f)
                    curveTo(8.51f, 23.27f, 9.42f, 22.9f, 10.08f, 22.23f)
                    curveTo(10.75f, 21.56f, 11.13f, 20.65f, 11.13f, 19.71f)
                    close()
                }
            }
            path(
                fill = SolidColor(Color(0xFF3253A0)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(24.25f, 12.87f)
                curveTo(24.25f, 12.31f, 24.12f, 11.76f, 23.88f, 11.26f)
                curveTo(23.63f, 10.76f, 23.27f, 10.33f, 22.82f, 10.0f)
                curveTo(22.37f, 9.66f, 21.86f, 9.44f, 21.31f, 9.34f)
                curveTo(20.76f, 9.25f, 20.2f, 9.28f, 19.66f, 9.44f)
                curveTo(19.13f, 9.6f, 18.64f, 9.88f, 18.24f, 10.26f)
                curveTo(17.83f, 10.64f, 17.52f, 11.12f, 17.33f, 11.64f)
                curveTo(17.14f, 12.16f, 17.08f, 12.72f, 17.14f, 13.28f)
                curveTo(17.21f, 13.83f, 17.4f, 14.36f, 17.71f, 14.82f)
                lineTo(14.99f, 11.0f)
                curveTo(14.68f, 10.48f, 14.23f, 10.05f, 13.7f, 9.76f)
                curveTo(13.17f, 9.46f, 12.57f, 9.3f, 11.96f, 9.3f)
                curveTo(11.35f, 9.3f, 10.76f, 9.46f, 10.23f, 9.76f)
                curveTo(9.7f, 10.05f, 9.25f, 10.48f, 8.93f, 11.0f)
                lineTo(4.63f, 17.71f)
                curveTo(4.96f, 17.22f, 5.41f, 16.82f, 5.95f, 16.54f)
                curveTo(6.48f, 16.27f, 7.07f, 16.13f, 7.67f, 16.15f)
                curveTo(8.27f, 16.16f, 8.85f, 16.33f, 9.37f, 16.63f)
                curveTo(9.88f, 16.93f, 10.32f, 17.36f, 10.63f, 17.87f)
                lineTo(13.44f, 21.82f)
                curveTo(13.77f, 22.27f, 14.2f, 22.64f, 14.7f, 22.89f)
                curveTo(15.19f, 23.14f, 15.75f, 23.28f, 16.3f, 23.28f)
                curveTo(16.86f, 23.28f, 17.41f, 23.14f, 17.91f, 22.89f)
                curveTo(18.41f, 22.64f, 18.84f, 22.27f, 19.17f, 21.82f)
                lineTo(19.2f, 21.78f)
                curveTo(19.28f, 21.67f, 19.34f, 21.57f, 19.41f, 21.45f)
                lineTo(23.63f, 14.86f)
                curveTo(24.03f, 14.27f, 24.24f, 13.58f, 24.24f, 12.87f)
                moveTo(18.55f, 15.72f)
                lineTo(18.62f, 15.77f)
                lineTo(18.57f, 15.72f)
                moveTo(22.72f, 15.79f)
                lineTo(22.8f, 15.72f)
                lineTo(22.71f, 15.79f)
                moveTo(4.54f, 17.86f)
                curveTo(4.57f, 17.81f, 4.59f, 17.77f, 4.62f, 17.73f)
                lineTo(4.54f, 17.86f)
                horizontalLineTo(4.54f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF38334)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(24.24f, 12.87f)
                curveTo(24.24f, 12.16f, 24.03f, 11.47f, 23.64f, 10.88f)
                curveTo(23.24f, 10.3f, 22.69f, 9.84f, 22.04f, 9.57f)
                curveTo(21.38f, 9.3f, 20.67f, 9.23f, 19.98f, 9.37f)
                curveTo(19.28f, 9.51f, 18.65f, 9.85f, 18.15f, 10.35f)
                curveTo(17.65f, 10.85f, 17.32f, 11.48f, 17.18f, 12.18f)
                curveTo(17.04f, 12.87f, 17.11f, 13.58f, 17.39f, 14.23f)
                curveTo(17.66f, 14.89f, 18.11f, 15.44f, 18.7f, 15.83f)
                curveTo(19.29f, 16.22f, 19.98f, 16.43f, 20.68f, 16.43f)
                curveTo(21.63f, 16.43f, 22.53f, 16.05f, 23.2f, 15.38f)
                curveTo(23.87f, 14.72f, 24.25f, 13.81f, 24.25f, 12.87f)
            }
            path(
                fill = SolidColor(Color(0xFF3152A0)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(20.68f, 16.43f)
                curveTo(20.09f, 16.43f, 19.51f, 16.28f, 18.99f, 16.0f)
                curveTo(18.47f, 15.72f, 18.03f, 15.32f, 17.71f, 14.82f)
                lineTo(14.99f, 11.0f)
                curveTo(14.67f, 10.48f, 14.23f, 10.05f, 13.7f, 9.76f)
                curveTo(13.17f, 9.46f, 12.57f, 9.3f, 11.96f, 9.3f)
                curveTo(11.35f, 9.3f, 10.75f, 9.46f, 10.22f, 9.76f)
                curveTo(9.69f, 10.05f, 9.25f, 10.48f, 8.93f, 11.0f)
                lineTo(4.61f, 17.71f)
                curveTo(4.95f, 17.22f, 5.4f, 16.82f, 5.93f, 16.54f)
                curveTo(6.46f, 16.27f, 7.06f, 16.13f, 7.65f, 16.15f)
                curveTo(8.25f, 16.16f, 8.84f, 16.33f, 9.35f, 16.63f)
                curveTo(9.87f, 16.93f, 10.3f, 17.36f, 10.61f, 17.87f)
                lineTo(13.44f, 21.82f)
                curveTo(13.77f, 22.27f, 14.2f, 22.64f, 14.7f, 22.89f)
                curveTo(15.2f, 23.14f, 15.75f, 23.28f, 16.31f, 23.28f)
                curveTo(16.86f, 23.28f, 17.42f, 23.14f, 17.91f, 22.89f)
                curveTo(18.41f, 22.64f, 18.84f, 22.27f, 19.17f, 21.82f)
                lineTo(19.2f, 21.77f)
                lineTo(19.41f, 21.47f)
                lineTo(23.63f, 14.88f)
                curveTo(23.31f, 15.36f, 22.87f, 15.75f, 22.35f, 16.02f)
                curveTo(21.84f, 16.29f, 21.26f, 16.43f, 20.68f, 16.43f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(180.11f, 17.84f)
                curveTo(180.11f, 19.64f, 179.58f, 21.39f, 178.58f, 22.89f)
                curveTo(177.58f, 24.38f, 176.16f, 25.54f, 174.51f, 26.23f)
                curveTo(172.85f, 26.92f, 171.02f, 27.1f, 169.26f, 26.75f)
                curveTo(167.5f, 26.4f, 165.88f, 25.53f, 164.61f, 24.26f)
                curveTo(163.34f, 22.99f, 162.48f, 21.37f, 162.13f, 19.61f)
                curveTo(161.77f, 17.85f, 161.96f, 16.03f, 162.64f, 14.37f)
                curveTo(163.33f, 12.71f, 164.49f, 11.29f, 165.99f, 10.29f)
                curveTo(167.48f, 9.29f, 169.24f, 8.76f, 171.03f, 8.76f)
                curveTo(173.44f, 8.76f, 175.75f, 9.72f, 177.45f, 11.42f)
                curveTo(179.15f, 13.12f, 180.11f, 15.43f, 180.11f, 17.84f)
                close()
                moveTo(171.03f, 13.29f)
                curveTo(170.13f, 13.29f, 169.25f, 13.56f, 168.5f, 14.06f)
                curveTo(167.76f, 14.56f, 167.17f, 15.27f, 166.83f, 16.1f)
                curveTo(166.49f, 16.93f, 166.4f, 17.85f, 166.57f, 18.73f)
                curveTo(166.75f, 19.61f, 167.18f, 20.42f, 167.82f, 21.06f)
                curveTo(168.45f, 21.69f, 169.26f, 22.13f, 170.14f, 22.3f)
                curveTo(171.03f, 22.48f, 171.94f, 22.39f, 172.77f, 22.04f)
                curveTo(173.6f, 21.7f, 174.31f, 21.12f, 174.81f, 20.37f)
                curveTo(175.31f, 19.62f, 175.58f, 18.74f, 175.58f, 17.84f)
                curveTo(175.58f, 17.24f, 175.46f, 16.65f, 175.23f, 16.1f)
                curveTo(175.01f, 15.55f, 174.67f, 15.05f, 174.25f, 14.63f)
                curveTo(173.82f, 14.2f, 173.32f, 13.87f, 172.77f, 13.64f)
                curveTo(172.22f, 13.41f, 171.63f, 13.29f, 171.03f, 13.29f)
                close()
                moveTo(132.05f, 17.85f)
                curveTo(132.05f, 19.64f, 131.52f, 21.4f, 130.52f, 22.89f)
                curveTo(129.52f, 24.39f, 128.1f, 25.55f, 126.44f, 26.24f)
                curveTo(124.78f, 26.92f, 122.96f, 27.1f, 121.2f, 26.75f)
                curveTo(119.44f, 26.4f, 117.82f, 25.54f, 116.55f, 24.27f)
                curveTo(115.28f, 23.0f, 114.41f, 21.38f, 114.06f, 19.62f)
                curveTo(113.71f, 17.86f, 113.89f, 16.03f, 114.58f, 14.37f)
                curveTo(115.27f, 12.71f, 116.43f, 11.3f, 117.92f, 10.3f)
                curveTo(119.42f, 9.3f, 121.17f, 8.77f, 122.97f, 8.77f)
                curveTo(125.38f, 8.77f, 127.69f, 9.72f, 129.39f, 11.43f)
                curveTo(131.09f, 13.13f, 132.05f, 15.44f, 132.05f, 17.85f)
                close()
                moveTo(122.97f, 13.3f)
                curveTo(122.07f, 13.3f, 121.19f, 13.57f, 120.44f, 14.07f)
                curveTo(119.69f, 14.57f, 119.11f, 15.28f, 118.77f, 16.11f)
                curveTo(118.42f, 16.94f, 118.33f, 17.85f, 118.51f, 18.73f)
                curveTo(118.68f, 19.62f, 119.12f, 20.43f, 119.75f, 21.06f)
                curveTo(120.39f, 21.7f, 121.2f, 22.13f, 122.08f, 22.31f)
                curveTo(122.96f, 22.48f, 123.88f, 22.39f, 124.71f, 22.05f)
                curveTo(125.54f, 21.71f, 126.25f, 21.12f, 126.75f, 20.37f)
                curveTo(127.25f, 19.63f, 127.52f, 18.75f, 127.52f, 17.85f)
                curveTo(127.52f, 16.64f, 127.04f, 15.48f, 126.18f, 14.63f)
                curveTo(125.33f, 13.78f, 124.18f, 13.3f, 122.97f, 13.3f)
                close()
                moveTo(88.72f, 8.76f)
                curveTo(88.28f, 8.74f, 87.84f, 8.86f, 87.47f, 9.1f)
                curveTo(87.1f, 9.34f, 86.81f, 9.69f, 86.64f, 10.1f)
                curveTo(85.26f, 9.26f, 83.69f, 8.8f, 82.07f, 8.76f)
                curveTo(80.46f, 8.73f, 78.86f, 9.13f, 77.46f, 9.92f)
                curveTo(76.05f, 10.71f, 74.87f, 11.86f, 74.06f, 13.25f)
                curveTo(73.24f, 14.65f, 72.81f, 16.23f, 72.81f, 17.85f)
                curveTo(72.81f, 19.46f, 73.24f, 21.05f, 74.06f, 22.44f)
                curveTo(74.87f, 23.84f, 76.05f, 24.99f, 77.46f, 25.77f)
                curveTo(78.86f, 26.56f, 80.46f, 26.96f, 82.07f, 26.93f)
                curveTo(83.69f, 26.9f, 85.26f, 26.44f, 86.64f, 25.59f)
                curveTo(86.81f, 26.0f, 87.1f, 26.35f, 87.48f, 26.59f)
                curveTo(87.85f, 26.83f, 88.29f, 26.95f, 88.73f, 26.93f)
                curveTo(89.04f, 26.94f, 89.35f, 26.88f, 89.63f, 26.77f)
                curveTo(89.92f, 26.65f, 90.18f, 26.47f, 90.4f, 26.25f)
                curveTo(90.61f, 26.02f, 90.78f, 25.76f, 90.88f, 25.47f)
                curveTo(90.99f, 25.17f, 91.03f, 24.86f, 91.01f, 24.55f)
                verticalLineTo(11.12f)
                curveTo(91.03f, 10.81f, 90.99f, 10.5f, 90.88f, 10.21f)
                curveTo(90.78f, 9.92f, 90.61f, 9.65f, 90.4f, 9.43f)
                curveTo(90.18f, 9.21f, 89.92f, 9.03f, 89.63f, 8.91f)
                curveTo(89.35f, 8.8f, 89.04f, 8.74f, 88.73f, 8.75f)
                moveTo(81.92f, 22.39f)
                curveTo(81.04f, 22.39f, 80.18f, 22.13f, 79.44f, 21.65f)
                curveTo(78.7f, 21.17f, 78.11f, 20.49f, 77.76f, 19.68f)
                curveTo(77.4f, 18.87f, 77.28f, 17.98f, 77.42f, 17.11f)
                curveTo(77.57f, 16.24f, 77.96f, 15.42f, 78.55f, 14.77f)
                curveTo(79.15f, 14.12f, 79.92f, 13.65f, 80.77f, 13.43f)
                curveTo(81.62f, 13.21f, 82.53f, 13.24f, 83.36f, 13.52f)
                curveTo(84.2f, 13.8f, 84.94f, 14.31f, 85.49f, 15.0f)
                curveTo(86.04f, 15.7f, 86.37f, 16.53f, 86.45f, 17.41f)
                verticalLineTo(18.24f)
                curveTo(86.35f, 19.37f, 85.83f, 20.42f, 84.99f, 21.19f)
                curveTo(84.15f, 21.95f, 83.06f, 22.38f, 81.92f, 22.38f)
                moveTo(112.33f, 19.1f)
                curveTo(112.3f, 18.52f, 112.05f, 17.98f, 111.63f, 17.58f)
                curveTo(111.21f, 17.18f, 110.65f, 16.95f, 110.07f, 16.95f)
                curveTo(109.49f, 16.95f, 108.93f, 17.18f, 108.51f, 17.58f)
                curveTo(108.09f, 17.98f, 107.84f, 18.52f, 107.81f, 19.1f)
                curveTo(107.72f, 19.92f, 107.34f, 20.68f, 106.73f, 21.23f)
                curveTo(106.12f, 21.79f, 105.33f, 22.09f, 104.5f, 22.09f)
                curveTo(103.68f, 22.09f, 102.89f, 21.79f, 102.28f, 21.23f)
                curveTo(101.67f, 20.68f, 101.28f, 19.92f, 101.2f, 19.1f)
                verticalLineTo(13.3f)
                horizontalLineTo(106.53f)
                curveTo(107.14f, 13.3f, 107.71f, 13.06f, 108.14f, 12.63f)
                curveTo(108.56f, 12.21f, 108.8f, 11.63f, 108.8f, 11.03f)
                curveTo(108.8f, 10.43f, 108.56f, 9.85f, 108.14f, 9.43f)
                curveTo(107.71f, 9.0f, 107.14f, 8.76f, 106.53f, 8.76f)
                horizontalLineTo(101.19f)
                verticalLineTo(7.22f)
                curveTo(101.16f, 6.64f, 100.91f, 6.09f, 100.49f, 5.69f)
                curveTo(100.07f, 5.29f, 99.51f, 5.07f, 98.93f, 5.07f)
                curveTo(98.35f, 5.07f, 97.79f, 5.29f, 97.37f, 5.69f)
                curveTo(96.95f, 6.09f, 96.7f, 6.64f, 96.67f, 7.22f)
                verticalLineTo(8.78f)
                horizontalLineTo(94.84f)
                curveTo(94.24f, 8.78f, 93.66f, 9.02f, 93.24f, 9.44f)
                curveTo(92.82f, 9.86f, 92.58f, 10.44f, 92.58f, 11.04f)
                curveTo(92.58f, 11.63f, 92.82f, 12.21f, 93.24f, 12.63f)
                curveTo(93.66f, 13.06f, 94.24f, 13.29f, 94.84f, 13.29f)
                horizontalLineTo(96.67f)
                verticalLineTo(19.1f)
                curveTo(96.7f, 21.17f, 97.53f, 23.15f, 98.99f, 24.61f)
                curveTo(100.46f, 26.07f, 102.43f, 26.9f, 104.5f, 26.93f)
                curveTo(106.58f, 26.93f, 108.57f, 26.1f, 110.04f, 24.63f)
                curveTo(111.5f, 23.17f, 112.33f, 21.18f, 112.33f, 19.1f)
                close()
                moveTo(160.38f, 24.65f)
                verticalLineTo(16.49f)
                curveTo(160.37f, 14.95f, 159.89f, 13.45f, 159.01f, 12.17f)
                curveTo(158.14f, 10.9f, 156.91f, 9.92f, 155.48f, 9.34f)
                curveTo(154.05f, 8.77f, 152.48f, 8.63f, 150.97f, 8.95f)
                curveTo(149.46f, 9.27f, 148.07f, 10.02f, 146.99f, 11.12f)
                curveTo(145.91f, 10.02f, 144.53f, 9.27f, 143.02f, 8.95f)
                curveTo(141.51f, 8.63f, 139.94f, 8.77f, 138.51f, 9.35f)
                curveTo(137.07f, 9.93f, 135.84f, 10.92f, 134.97f, 12.19f)
                curveTo(134.1f, 13.47f, 133.63f, 14.97f, 133.62f, 16.51f)
                verticalLineTo(24.65f)
                curveTo(133.64f, 25.23f, 133.89f, 25.78f, 134.32f, 26.18f)
                curveTo(134.74f, 26.58f, 135.29f, 26.8f, 135.88f, 26.8f)
                curveTo(136.46f, 26.8f, 137.01f, 26.58f, 137.43f, 26.18f)
                curveTo(137.86f, 25.78f, 138.11f, 25.23f, 138.13f, 24.65f)
                verticalLineTo(16.59f)
                curveTo(138.21f, 15.76f, 138.59f, 14.99f, 139.2f, 14.44f)
                curveTo(139.82f, 13.88f, 140.61f, 13.57f, 141.44f, 13.57f)
                curveTo(142.27f, 13.57f, 143.07f, 13.88f, 143.68f, 14.44f)
                curveTo(144.29f, 14.99f, 144.67f, 15.76f, 144.75f, 16.59f)
                verticalLineTo(24.58f)
                curveTo(144.75f, 25.19f, 144.99f, 25.77f, 145.41f, 26.21f)
                curveTo(145.84f, 26.65f, 146.42f, 26.9f, 147.03f, 26.92f)
                curveTo(147.63f, 26.9f, 148.2f, 26.64f, 148.61f, 26.2f)
                curveTo(149.02f, 25.76f, 149.25f, 25.18f, 149.24f, 24.58f)
                verticalLineTo(16.59f)
                curveTo(149.32f, 15.76f, 149.7f, 14.99f, 150.31f, 14.44f)
                curveTo(150.92f, 13.88f, 151.72f, 13.57f, 152.55f, 13.57f)
                curveTo(153.38f, 13.57f, 154.17f, 13.88f, 154.79f, 14.44f)
                curveTo(155.4f, 14.99f, 155.78f, 15.76f, 155.85f, 16.59f)
                verticalLineTo(24.65f)
                curveTo(155.85f, 25.25f, 156.09f, 25.83f, 156.51f, 26.25f)
                curveTo(156.94f, 26.67f, 157.51f, 26.91f, 158.11f, 26.91f)
                curveTo(158.71f, 26.91f, 159.28f, 26.67f, 159.71f, 26.25f)
                curveTo(160.13f, 25.83f, 160.37f, 25.25f, 160.37f, 24.65f)
                moveTo(71.27f, 24.65f)
                verticalLineTo(16.48f)
                curveTo(71.25f, 14.94f, 70.77f, 13.44f, 69.9f, 12.16f)
                curveTo(69.02f, 10.89f, 67.79f, 9.91f, 66.36f, 9.33f)
                curveTo(64.93f, 8.76f, 63.36f, 8.63f, 61.85f, 8.94f)
                curveTo(60.34f, 9.26f, 58.95f, 10.02f, 57.87f, 11.12f)
                curveTo(56.79f, 10.02f, 55.41f, 9.27f, 53.9f, 8.95f)
                curveTo(52.39f, 8.63f, 50.82f, 8.77f, 49.39f, 9.35f)
                curveTo(47.96f, 9.92f, 46.73f, 10.91f, 45.86f, 12.19f)
                curveTo(44.99f, 13.46f, 44.52f, 14.97f, 44.5f, 16.51f)
                verticalLineTo(24.65f)
                curveTo(44.5f, 25.25f, 44.74f, 25.82f, 45.16f, 26.24f)
                curveTo(45.58f, 26.67f, 46.16f, 26.91f, 46.76f, 26.91f)
                curveTo(47.36f, 26.91f, 47.93f, 26.67f, 48.35f, 26.24f)
                curveTo(48.78f, 25.82f, 49.02f, 25.25f, 49.02f, 24.65f)
                verticalLineTo(16.58f)
                curveTo(49.02f, 15.7f, 49.37f, 14.86f, 49.99f, 14.23f)
                curveTo(50.61f, 13.61f, 51.45f, 13.26f, 52.34f, 13.26f)
                curveTo(53.22f, 13.26f, 54.06f, 13.61f, 54.68f, 14.23f)
                curveTo(55.31f, 14.86f, 55.66f, 15.7f, 55.66f, 16.58f)
                verticalLineTo(24.57f)
                curveTo(55.66f, 25.18f, 55.9f, 25.76f, 56.32f, 26.2f)
                curveTo(56.75f, 26.64f, 57.33f, 26.9f, 57.94f, 26.92f)
                curveTo(58.54f, 26.89f, 59.11f, 26.63f, 59.52f, 26.19f)
                curveTo(59.94f, 25.75f, 60.16f, 25.17f, 60.15f, 24.57f)
                verticalLineTo(16.58f)
                curveTo(60.24f, 15.76f, 60.62f, 15.0f, 61.23f, 14.45f)
                curveTo(61.84f, 13.9f, 62.63f, 13.59f, 63.46f, 13.59f)
                curveTo(64.28f, 13.59f, 65.07f, 13.9f, 65.68f, 14.45f)
                curveTo(66.29f, 15.0f, 66.68f, 15.76f, 66.76f, 16.58f)
                verticalLineTo(24.65f)
                curveTo(66.76f, 25.25f, 67.0f, 25.82f, 67.42f, 26.24f)
                curveTo(67.85f, 26.67f, 68.42f, 26.91f, 69.02f, 26.91f)
                curveTo(69.62f, 26.91f, 70.19f, 26.67f, 70.61f, 26.24f)
                curveTo(71.04f, 25.82f, 71.28f, 25.25f, 71.28f, 24.65f)
            }
        }.build()

        return _matomoDark!!
    }

private var _matomoDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MatomoDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
