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
package com.infomaniak.swisstransfer.ui.images.illus.uploadSuccessEmail

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

val AppIllus.UploadSuccessEmailLight: ImageVector
    get() {

        if (_uploadSuccessEmailLight != null) return _uploadSuccessEmailLight!!

        _uploadSuccessEmailLight = Builder(
            name = "UploadSuccessEmailLight",
            defaultWidth = 453.0.dp,
            defaultHeight = 194.0.dp,
            viewportWidth = 453.0f,
            viewportHeight = 194.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(431.9f, 55.2f)
                arcToRelative(29.22f, 5.5f, 70.49f, true, false, 10.37f, -3.67f)
                arcToRelative(29.22f, 5.5f, 70.49f, true, false, -10.37f, 3.67f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(20.13f, 75.22f)
                arcToRelative(29.22f, 5.5f, 109.51f, true, true, -10.37f, -3.67f)
                arcToRelative(29.22f, 5.5f, 109.51f, true, true, 10.37f, 3.67f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCF9E1B)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(339.36f, 58.47f)
                curveTo(339.85f, 58.24f, 340.64f, 58.06f, 341.73f, 57.94f)
                curveTo(342.81f, 57.82f, 344.14f, 57.77f, 345.68f, 57.78f)
                curveTo(348.4f, 57.81f, 351.75f, 58.02f, 355.46f, 58.39f)
                curveTo(354.97f, 58.68f, 354.36f, 59.03f, 353.65f, 59.44f)
                curveTo(351.73f, 60.54f, 349.05f, 62.02f, 346.04f, 63.55f)
                curveTo(340.36f, 66.45f, 333.55f, 69.49f, 328.42f, 70.5f)
                curveTo(328.82f, 69.76f, 329.39f, 68.88f, 330.08f, 67.95f)
                curveTo(331.05f, 66.64f, 332.23f, 65.23f, 333.43f, 63.9f)
                curveTo(334.64f, 62.57f, 335.87f, 61.33f, 336.93f, 60.36f)
                curveTo(338.01f, 59.37f, 338.87f, 58.7f, 339.36f, 58.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCF9E1B)), stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(111.2f, 80.47f)
                curveTo(110.71f, 80.24f, 109.92f, 80.06f, 108.83f, 79.94f)
                curveTo(107.75f, 79.82f, 106.42f, 79.77f, 104.88f, 79.78f)
                curveTo(102.16f, 79.81f, 98.81f, 80.02f, 95.1f, 80.39f)
                curveTo(95.59f, 80.68f, 96.2f, 81.03f, 96.91f, 81.44f)
                curveTo(98.83f, 82.54f, 101.51f, 84.02f, 104.52f, 85.55f)
                curveTo(110.2f, 88.45f, 117.0f, 91.49f, 122.14f, 92.5f)
                curveTo(121.74f, 91.76f, 121.17f, 90.88f, 120.48f, 89.95f)
                curveTo(119.51f, 88.64f, 118.33f, 87.23f, 117.13f, 85.9f)
                curveTo(115.92f, 84.57f, 114.69f, 83.33f, 113.63f, 82.36f)
                curveTo(112.55f, 81.37f, 111.68f, 80.7f, 111.2f, 80.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 2.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(144.25f, 78.35f)
                curveTo(138.33f, 76.91f, 132.16f, 76.85f, 126.22f, 78.17f)
                curveTo(120.27f, 79.49f, 114.71f, 82.16f, 109.96f, 85.97f)
                curveTo(105.21f, 89.79f, 101.41f, 94.64f, 98.84f, 100.16f)
                curveTo(96.26f, 105.68f, 95.0f, 111.72f, 95.13f, 117.81f)
                curveTo(95.27f, 123.9f, 96.8f, 129.88f, 99.62f, 135.28f)
                curveTo(102.43f, 140.68f, 106.45f, 145.36f, 111.37f, 148.96f)
                curveTo(116.28f, 152.56f, 121.95f, 154.98f, 127.95f, 156.03f)
                curveTo(133.95f, 157.09f, 140.11f, 156.75f, 145.96f, 155.04f)
                lineTo(142.32f, 142.59f)
                curveTo(138.38f, 143.73f, 134.24f, 143.96f, 130.2f, 143.25f)
                curveTo(126.16f, 142.54f, 122.34f, 140.91f, 119.04f, 138.49f)
                curveTo(115.73f, 136.07f, 113.02f, 132.92f, 111.13f, 129.28f)
                curveTo(109.23f, 125.64f, 108.2f, 121.62f, 108.11f, 117.52f)
                curveTo(108.02f, 113.42f, 108.87f, 109.36f, 110.6f, 105.64f)
                curveTo(112.33f, 101.93f, 114.89f, 98.66f, 118.09f, 96.09f)
                curveTo(121.29f, 93.52f, 125.03f, 91.72f, 129.03f, 90.83f)
                curveTo(133.04f, 89.95f, 137.19f, 89.99f, 141.17f, 90.96f)
                lineTo(144.25f, 78.35f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(227.32f, 43.48f)
                curveTo(229.16f, 43.93f, 231.01f, 42.81f, 231.46f, 40.97f)
                curveTo(231.9f, 39.14f, 230.78f, 37.29f, 228.95f, 36.84f)
                curveTo(227.11f, 36.39f, 225.26f, 37.52f, 224.81f, 39.35f)
                curveTo(224.36f, 41.19f, 225.49f, 43.04f, 227.32f, 43.48f)
                close()
                moveTo(224.21f, 50.17f)
                curveTo(223.64f, 52.51f, 221.27f, 53.94f, 218.93f, 53.37f)
                curveTo(216.59f, 52.8f, 215.15f, 50.44f, 215.73f, 48.09f)
                curveTo(216.3f, 45.75f, 218.66f, 44.32f, 221.0f, 44.89f)
                curveTo(223.35f, 45.46f, 224.78f, 47.82f, 224.21f, 50.17f)
                close()
                moveTo(223.74f, 72.41f)
                curveTo(226.85f, 72.23f, 229.6f, 70.05f, 230.38f, 66.87f)
                curveTo(231.33f, 62.97f, 228.94f, 59.03f, 225.04f, 58.08f)
                curveTo(223.62f, 57.73f, 222.2f, 57.83f, 220.91f, 58.28f)
                curveTo(219.93f, 57.61f, 218.81f, 57.11f, 217.59f, 56.81f)
                curveTo(212.08f, 55.46f, 206.53f, 58.84f, 205.18f, 64.34f)
                curveTo(205.03f, 64.97f, 204.94f, 65.61f, 204.9f, 66.23f)
                curveTo(204.63f, 66.02f, 204.32f, 65.87f, 203.96f, 65.78f)
                curveTo(202.6f, 65.45f, 201.24f, 66.28f, 200.9f, 67.64f)
                curveTo(200.75f, 68.27f, 200.85f, 68.91f, 201.13f, 69.44f)
                curveTo(201.06f, 69.42f, 200.99f, 69.4f, 200.91f, 69.38f)
                curveTo(197.52f, 68.55f, 194.09f, 70.63f, 193.26f, 74.03f)
                curveTo(192.85f, 75.73f, 193.17f, 77.45f, 194.02f, 78.83f)
                curveTo(190.57f, 79.33f, 187.55f, 81.67f, 186.3f, 85.05f)
                curveTo(182.64f, 84.71f, 179.42f, 86.03f, 175.53f, 87.63f)
                curveTo(172.02f, 89.07f, 167.97f, 90.73f, 162.57f, 91.59f)
                curveTo(151.02f, 93.44f, 138.89f, 67.26f, 138.89f, 67.26f)
                lineTo(122.87f, 168.29f)
                curveTo(122.71f, 169.32f, 123.36f, 170.3f, 124.37f, 170.55f)
                lineTo(183.68f, 185.03f)
                curveTo(184.69f, 185.28f, 185.72f, 184.71f, 186.05f, 183.72f)
                lineTo(222.69f, 73.75f)
                curveTo(223.07f, 73.34f, 223.43f, 72.89f, 223.74f, 72.41f)
                close()
                moveTo(230.48f, 54.66f)
                curveTo(230.15f, 56.01f, 228.79f, 56.84f, 227.44f, 56.51f)
                curveTo(226.09f, 56.18f, 225.27f, 54.82f, 225.6f, 53.47f)
                curveTo(225.93f, 52.12f, 227.29f, 51.3f, 228.64f, 51.62f)
                curveTo(229.99f, 51.95f, 230.81f, 53.31f, 230.48f, 54.66f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(135.8f, 138.6f)
                lineTo(132.12f, 163.63f)
                curveTo(131.97f, 164.65f, 132.63f, 165.62f, 133.63f, 165.86f)
                lineTo(138.29f, 167.0f)
                curveTo(139.29f, 167.24f, 140.31f, 166.68f, 140.65f, 165.71f)
                lineTo(148.92f, 141.8f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(155.48f, 143.4f)
                lineTo(151.79f, 168.43f)
                curveTo(151.65f, 169.45f, 152.3f, 170.42f, 153.3f, 170.66f)
                lineTo(157.96f, 171.8f)
                curveTo(158.96f, 172.05f, 159.99f, 171.49f, 160.32f, 170.51f)
                lineTo(168.59f, 146.6f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(175.15f, 148.21f)
                lineTo(171.47f, 173.23f)
                curveTo(171.32f, 174.26f, 171.97f, 175.22f, 172.97f, 175.47f)
                lineTo(177.63f, 176.61f)
                curveTo(178.63f, 176.85f, 179.66f, 176.29f, 179.99f, 175.32f)
                lineTo(188.26f, 151.41f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(143.59f, 99.6f)
                arcToRelative(1.61f, 1.61f, 103.92f, true, false, 3.13f, 0.76f)
                arcToRelative(1.61f, 1.61f, 103.92f, true, false, -3.13f, -0.76f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(179.22f, 106.64f)
                moveToRelative(-2.46f, -0.6f)
                arcToRelative(2.53f, 2.53f, 58.72f, true, true, 4.92f, 1.2f)
                arcToRelative(2.53f, 2.53f, 58.72f, true, true, -4.92f, -1.2f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(183.16f, 115.42f)
                moveToRelative(-0.82f, -0.2f)
                arcToRelative(0.84f, 0.84f, 58.72f, true, true, 1.64f, 0.4f)
                arcToRelative(0.84f, 0.84f, 58.72f, true, true, -1.64f, -0.4f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(149.26f, 129.72f)
                moveToRelative(-0.82f, -0.2f)
                arcToRelative(0.84f, 0.84f, 58.72f, true, true, 1.64f, 0.4f)
                arcToRelative(0.84f, 0.84f, 58.72f, true, true, -1.64f, -0.4f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(160.06f, 121.07f)
                moveToRelative(-1.64f, -0.4f)
                arcToRelative(1.69f, 1.69f, 58.72f, true, true, 3.28f, 0.8f)
                arcToRelative(1.69f, 1.69f, 58.72f, true, true, -3.28f, -0.8f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(182.18f, 142.54f)
                moveToRelative(-1.23f, -0.3f)
                arcToRelative(1.27f, 1.27f, 58.72f, true, true, 2.46f, 0.6f)
                arcToRelative(1.27f, 1.27f, 58.72f, true, true, -2.46f, -0.6f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(206.72f, 102.5f)
                moveToRelative(-1.23f, -0.3f)
                arcToRelative(1.27f, 1.27f, 58.72f, true, true, 2.46f, 0.6f)
                arcToRelative(1.27f, 1.27f, 58.72f, true, true, -2.46f, -0.6f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(143.69f, 51.42f)
                curveTo(142.27f, 51.07f, 140.86f, 52.01f, 140.63f, 53.45f)
                lineTo(122.16f, 168.15f)
                curveTo(121.95f, 169.44f, 122.77f, 170.67f, 124.03f, 170.98f)
                lineTo(183.79f, 185.57f)
                curveTo(185.05f, 185.88f, 186.34f, 185.17f, 186.75f, 183.93f)
                lineTo(223.23f, 73.62f)
                curveTo(223.69f, 72.23f, 222.87f, 70.75f, 221.45f, 70.41f)
                lineTo(143.69f, 51.42f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(120.07f, 168.05f)
                curveTo(120.14f, 167.45f, 120.72f, 167.05f, 121.3f, 167.19f)
                lineTo(188.77f, 183.67f)
                curveTo(189.36f, 183.81f, 189.68f, 184.43f, 189.47f, 185.0f)
                lineTo(187.77f, 189.47f)
                curveTo(187.59f, 189.94f, 187.09f, 190.21f, 186.6f, 190.09f)
                lineTo(120.28f, 173.89f)
                curveTo(119.79f, 173.77f, 119.46f, 173.3f, 119.52f, 172.8f)
                lineTo(120.07f, 168.05f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 2.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(302.1f, 63.06f)
                curveTo(307.65f, 60.58f, 313.71f, 59.4f, 319.8f, 59.63f)
                curveTo(325.88f, 59.85f, 331.83f, 61.48f, 337.19f, 64.37f)
                curveTo(342.55f, 67.27f, 347.17f, 71.36f, 350.7f, 76.33f)
                curveTo(354.22f, 81.29f, 356.56f, 87.0f, 357.52f, 93.02f)
                curveTo(358.48f, 99.03f, 358.05f, 105.19f, 356.26f, 111.01f)
                curveTo(354.46f, 116.83f, 351.35f, 122.16f, 347.17f, 126.58f)
                curveTo(342.99f, 131.01f, 337.84f, 134.41f, 332.13f, 136.53f)
                curveTo(326.42f, 138.65f, 320.3f, 139.43f, 314.24f, 138.8f)
                lineTo(315.57f, 125.89f)
                curveTo(319.65f, 126.31f, 323.77f, 125.79f, 327.61f, 124.36f)
                curveTo(331.46f, 122.94f, 334.92f, 120.65f, 337.74f, 117.67f)
                curveTo(340.55f, 114.69f, 342.65f, 111.1f, 343.86f, 107.18f)
                curveTo(345.07f, 103.26f, 345.36f, 99.12f, 344.71f, 95.07f)
                curveTo(344.06f, 91.03f, 342.48f, 87.18f, 340.11f, 83.84f)
                curveTo(337.74f, 80.49f, 334.63f, 77.74f, 331.02f, 75.79f)
                curveTo(327.42f, 73.84f, 323.41f, 72.75f, 319.31f, 72.6f)
                curveTo(315.21f, 72.44f, 311.14f, 73.23f, 307.4f, 74.91f)
                lineTo(302.1f, 63.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(228.71f, 64.73f)
                curveTo(227.63f, 61.84f, 228.97f, 58.57f, 231.83f, 57.29f)
                curveTo(234.81f, 55.96f, 238.3f, 57.29f, 239.63f, 60.27f)
                curveTo(239.92f, 60.9f, 240.08f, 61.56f, 240.13f, 62.21f)
                curveTo(240.38f, 61.9f, 240.7f, 61.64f, 241.09f, 61.46f)
                curveTo(242.37f, 60.89f, 243.87f, 61.46f, 244.44f, 62.74f)
                curveTo(244.7f, 63.33f, 244.72f, 63.98f, 244.54f, 64.55f)
                curveTo(244.6f, 64.52f, 244.67f, 64.48f, 244.74f, 64.45f)
                curveTo(247.93f, 63.03f, 251.68f, 64.45f, 253.1f, 67.64f)
                curveTo(253.82f, 69.25f, 253.82f, 70.99f, 253.23f, 72.51f)
                curveTo(256.71f, 72.37f, 260.1f, 74.13f, 261.95f, 77.24f)
                curveTo(265.48f, 76.23f, 268.89f, 76.95f, 273.0f, 77.82f)
                curveTo(276.71f, 78.61f, 280.99f, 79.51f, 286.46f, 79.39f)
                curveTo(298.15f, 79.12f, 305.36f, 51.19f, 305.36f, 51.19f)
                lineTo(339.34f, 147.67f)
                curveTo(339.68f, 148.66f, 339.22f, 149.74f, 338.27f, 150.16f)
                lineTo(282.55f, 175.1f)
                curveTo(281.6f, 175.53f, 280.48f, 175.15f, 279.98f, 174.24f)
                lineTo(223.13f, 70.89f)
                curveTo(223.13f, 70.89f, 224.01f, 71.49f, 225.47f, 72.44f)
                curveTo(225.33f, 69.53f, 226.52f, 66.68f, 228.71f, 64.73f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(321.26f, 120.8f)
                lineTo(329.4f, 144.75f)
                curveTo(329.73f, 145.73f, 329.26f, 146.8f, 328.32f, 147.22f)
                lineTo(323.94f, 149.18f)
                curveTo(323.0f, 149.6f, 321.89f, 149.24f, 321.39f, 148.34f)
                lineTo(308.94f, 126.31f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(302.78f, 129.07f)
                lineTo(310.91f, 153.02f)
                curveTo(311.25f, 154.0f, 310.78f, 155.07f, 309.84f, 155.49f)
                lineTo(305.46f, 157.45f)
                curveTo(304.52f, 157.87f, 303.41f, 157.51f, 302.9f, 156.61f)
                lineTo(290.46f, 134.59f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Round, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(284.3f, 137.34f)
                lineTo(292.43f, 161.3f)
                curveTo(292.76f, 162.27f, 292.3f, 163.34f, 291.35f, 163.76f)
                lineTo(286.98f, 165.72f)
                curveTo(286.04f, 166.15f, 284.93f, 165.78f, 284.42f, 164.88f)
                lineTo(271.98f, 142.86f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(306.57f, 83.84f)
                arcToRelative(1.61f, 1.61f, 65.69f, true, true, -2.94f, 1.32f)
                arcToRelative(1.61f, 1.61f, 65.69f, true, true, 2.94f, -1.32f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(272.8f, 97.19f)
                moveToRelative(2.31f, -1.03f)
                arcToRelative(2.53f, 2.53f, 110.89f, true, false, -4.62f, 2.07f)
                arcToRelative(2.53f, 2.53f, 110.89f, true, false, 4.62f, -2.07f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(270.51f, 106.54f)
                moveToRelative(0.77f, -0.34f)
                arcToRelative(0.84f, 0.84f, 110.89f, true, false, -1.54f, 0.69f)
                arcToRelative(0.84f, 0.84f, 110.89f, true, false, 1.54f, -0.69f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(306.43f, 114.5f)
                moveToRelative(0.77f, -0.34f)
                arcToRelative(0.84f, 0.84f, 110.89f, true, false, -1.54f, 0.69f)
                arcToRelative(0.84f, 0.84f, 110.89f, true, false, 1.54f, -0.69f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(294.24f, 107.93f)
                moveToRelative(1.54f, -0.69f)
                arcToRelative(1.69f, 1.69f, 110.89f, true, false, -3.08f, 1.38f)
                arcToRelative(1.69f, 1.69f, 110.89f, true, false, 3.08f, -1.38f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(276.36f, 133.04f)
                moveToRelative(1.16f, -0.52f)
                arcToRelative(1.27f, 1.27f, 110.89f, true, false, -2.31f, 1.03f)
                arcToRelative(1.27f, 1.27f, 110.89f, true, false, 2.31f, -1.03f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(245.0f, 98.08f)
                moveToRelative(1.16f, -0.52f)
                arcToRelative(1.27f, 1.27f, 110.89f, true, false, -2.31f, 1.03f)
                arcToRelative(1.27f, 1.27f, 110.89f, true, false, 2.31f, -1.03f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(297.79f, 36.47f)
                curveTo(299.12f, 35.87f, 300.68f, 36.54f, 301.16f, 37.91f)
                lineTo(340.02f, 147.41f)
                curveTo(340.45f, 148.64f, 339.87f, 149.99f, 338.68f, 150.53f)
                lineTo(282.54f, 175.65f)
                curveTo(281.35f, 176.18f, 279.95f, 175.71f, 279.33f, 174.57f)
                lineTo(223.55f, 72.65f)
                curveTo(222.85f, 71.37f, 223.4f, 69.76f, 224.73f, 69.16f)
                lineTo(297.79f, 36.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(342.05f, 146.93f)
                curveTo(341.87f, 146.35f, 341.23f, 146.06f, 340.68f, 146.31f)
                lineTo(277.29f, 174.68f)
                curveTo(276.74f, 174.93f, 276.53f, 175.6f, 276.84f, 176.11f)
                lineTo(279.33f, 180.21f)
                curveTo(279.59f, 180.64f, 280.13f, 180.81f, 280.59f, 180.6f)
                lineTo(342.9f, 152.71f)
                curveTo(343.36f, 152.51f, 343.59f, 151.99f, 343.45f, 151.51f)
                lineTo(342.05f, 146.93f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(214.68f, 11.65f)
                lineTo(217.12f, 3.97f)
                lineTo(213.18f, 2.72f)
                lineTo(210.75f, 10.4f)
                lineTo(204.34f, 5.31f)
                lineTo(201.9f, 8.51f)
                lineTo(208.37f, 13.41f)
                lineTo(201.85f, 18.3f)
                lineTo(204.57f, 21.58f)
                lineTo(211.03f, 16.45f)
                lineTo(213.73f, 24.48f)
                lineTo(217.41f, 23.17f)
                lineTo(214.95f, 15.5f)
                lineTo(223.19f, 15.27f)
                lineTo(222.85f, 11.19f)
                lineTo(214.68f, 11.65f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(190.71f, 43.32f)
                lineTo(191.55f, 40.65f)
                lineTo(190.18f, 40.21f)
                lineTo(189.34f, 42.88f)
                lineTo(187.1f, 41.11f)
                lineTo(186.26f, 42.23f)
                lineTo(188.51f, 43.93f)
                lineTo(186.24f, 45.63f)
                lineTo(187.19f, 46.77f)
                lineTo(189.43f, 44.99f)
                lineTo(190.37f, 47.79f)
                lineTo(191.66f, 47.33f)
                lineTo(190.8f, 44.66f)
                lineTo(193.67f, 44.58f)
                lineTo(193.55f, 43.16f)
                lineTo(190.71f, 43.32f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(247.07f, 27.96f)
                lineTo(248.34f, 23.97f)
                lineTo(246.29f, 23.32f)
                lineTo(245.02f, 27.31f)
                lineTo(241.69f, 24.66f)
                lineTo(240.43f, 26.33f)
                lineTo(243.79f, 28.87f)
                lineTo(240.4f, 31.41f)
                lineTo(241.82f, 33.11f)
                lineTo(245.17f, 30.45f)
                lineTo(246.57f, 34.63f)
                lineTo(248.49f, 33.94f)
                lineTo(247.21f, 29.96f)
                lineTo(251.49f, 29.84f)
                lineTo(251.32f, 27.72f)
                lineTo(247.07f, 27.96f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCF9E1B)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(319.14f, 79.12f)
                curveTo(318.63f, 76.82f, 319.61f, 73.92f, 323.62f, 71.88f)
                curveTo(324.41f, 71.48f, 325.23f, 71.07f, 326.07f, 70.64f)
                curveTo(330.14f, 68.56f, 334.83f, 66.17f, 339.44f, 64.02f)
                curveTo(344.99f, 61.43f, 350.36f, 59.22f, 354.35f, 58.34f)
                curveTo(356.43f, 58.45f, 359.11f, 58.8f, 361.98f, 59.18f)
                curveTo(363.38f, 59.36f, 364.82f, 59.55f, 366.26f, 59.72f)
                curveTo(370.63f, 60.24f, 375.0f, 60.59f, 377.77f, 59.99f)
                curveTo(378.33f, 59.86f, 379.53f, 59.39f, 381.15f, 58.7f)
                curveTo(382.79f, 58.0f, 384.92f, 57.05f, 387.36f, 55.93f)
                curveTo(392.23f, 53.68f, 398.38f, 50.76f, 404.48f, 47.81f)
                curveTo(416.1f, 42.19f, 427.62f, 36.48f, 429.88f, 35.23f)
                curveTo(430.32f, 35.54f, 430.87f, 36.13f, 431.49f, 37.01f)
                curveTo(432.22f, 38.02f, 433.01f, 39.36f, 433.82f, 40.92f)
                curveTo(435.46f, 44.05f, 437.18f, 48.05f, 438.7f, 52.14f)
                curveTo(440.22f, 56.23f, 441.52f, 60.39f, 442.31f, 63.83f)
                curveTo(442.7f, 65.56f, 442.97f, 67.09f, 443.07f, 68.33f)
                curveTo(443.16f, 69.43f, 443.12f, 70.25f, 442.96f, 70.78f)
                curveTo(431.53f, 73.54f, 422.36f, 75.71f, 415.02f, 77.45f)
                curveTo(409.15f, 78.85f, 404.45f, 79.96f, 400.7f, 80.88f)
                curveTo(396.47f, 81.91f, 393.43f, 82.69f, 391.27f, 83.34f)
                curveTo(389.13f, 83.98f, 387.79f, 84.51f, 387.0f, 85.04f)
                curveTo(386.22f, 85.57f, 385.06f, 86.8f, 383.73f, 88.32f)
                curveTo(382.42f, 89.84f, 380.89f, 91.71f, 379.35f, 93.6f)
                lineTo(379.29f, 93.68f)
                curveTo(377.73f, 95.59f, 376.15f, 97.52f, 374.74f, 99.15f)
                curveTo(373.32f, 100.78f, 372.11f, 102.06f, 371.27f, 102.71f)
                curveTo(370.45f, 103.34f, 368.68f, 104.22f, 366.34f, 105.21f)
                curveTo(364.03f, 106.18f, 361.21f, 107.23f, 358.32f, 108.2f)
                curveTo(355.43f, 109.17f, 352.48f, 110.06f, 349.91f, 110.71f)
                curveTo(347.33f, 111.36f, 345.17f, 111.76f, 343.83f, 111.77f)
                lineTo(343.83f, 111.77f)
                curveTo(340.96f, 111.81f, 339.04f, 110.55f, 338.09f, 109.06f)
                curveTo(337.12f, 107.54f, 337.17f, 105.85f, 338.12f, 104.84f)
                lineTo(338.9f, 104.01f)
                lineTo(337.76f, 104.0f)
                curveTo(334.9f, 103.98f, 332.55f, 102.61f, 331.2f, 100.72f)
                curveTo(329.86f, 98.83f, 329.52f, 96.44f, 330.63f, 94.31f)
                lineTo(330.94f, 93.7f)
                lineTo(330.27f, 93.58f)
                curveTo(326.31f, 92.89f, 324.4f, 91.18f, 323.69f, 89.39f)
                curveTo(322.98f, 87.58f, 323.44f, 85.53f, 324.54f, 84.04f)
                lineTo(325.14f, 83.22f)
                lineTo(324.12f, 83.24f)
                curveTo(321.74f, 83.3f, 319.66f, 81.48f, 319.14f, 79.12f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCF9E1B)), stroke =
                SolidColor(Color(0xFF014958)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(131.43f, 101.12f)
                curveTo(131.93f, 98.82f, 130.95f, 95.92f, 126.94f, 93.88f)
                curveTo(126.15f, 93.48f, 125.33f, 93.07f, 124.49f, 92.64f)
                curveTo(120.42f, 90.56f, 115.72f, 88.17f, 111.12f, 86.02f)
                curveTo(105.57f, 83.43f, 100.2f, 81.22f, 96.21f, 80.34f)
                curveTo(94.13f, 80.45f, 91.45f, 80.8f, 88.58f, 81.18f)
                curveTo(87.18f, 81.36f, 85.74f, 81.55f, 84.3f, 81.72f)
                curveTo(79.93f, 82.25f, 75.56f, 82.59f, 72.79f, 81.99f)
                curveTo(72.23f, 81.86f, 71.03f, 81.39f, 69.41f, 80.7f)
                curveTo(67.77f, 80.0f, 65.64f, 79.05f, 63.2f, 77.93f)
                curveTo(58.33f, 75.68f, 52.18f, 72.76f, 46.08f, 69.81f)
                curveTo(34.46f, 64.19f, 22.94f, 58.48f, 20.68f, 57.23f)
                curveTo(20.24f, 57.54f, 19.69f, 58.13f, 19.07f, 59.01f)
                curveTo(18.34f, 60.02f, 17.55f, 61.36f, 16.74f, 62.92f)
                curveTo(15.1f, 66.05f, 13.38f, 70.05f, 11.86f, 74.14f)
                curveTo(10.34f, 78.23f, 9.04f, 82.39f, 8.25f, 85.83f)
                curveTo(7.86f, 87.56f, 7.59f, 89.09f, 7.49f, 90.33f)
                curveTo(7.4f, 91.43f, 7.44f, 92.25f, 7.6f, 92.78f)
                curveTo(19.03f, 95.54f, 28.2f, 97.71f, 35.54f, 99.45f)
                curveTo(41.41f, 100.85f, 46.11f, 101.96f, 49.85f, 102.88f)
                curveTo(54.09f, 103.91f, 57.13f, 104.69f, 59.29f, 105.34f)
                curveTo(61.43f, 105.98f, 62.77f, 106.51f, 63.56f, 107.04f)
                curveTo(64.34f, 107.57f, 65.5f, 108.8f, 66.82f, 110.32f)
                curveTo(68.14f, 111.84f, 69.67f, 113.71f, 71.21f, 115.6f)
                lineTo(71.27f, 115.68f)
                curveTo(72.83f, 117.59f, 74.41f, 119.52f, 75.82f, 121.15f)
                curveTo(77.24f, 122.78f, 78.45f, 124.06f, 79.29f, 124.71f)
                curveTo(80.11f, 125.34f, 81.88f, 126.22f, 84.22f, 127.21f)
                curveTo(86.53f, 128.18f, 89.35f, 129.23f, 92.24f, 130.2f)
                curveTo(95.13f, 131.17f, 98.07f, 132.06f, 100.65f, 132.71f)
                curveTo(103.23f, 133.36f, 105.39f, 133.76f, 106.73f, 133.77f)
                lineTo(106.73f, 133.77f)
                curveTo(109.6f, 133.81f, 111.52f, 132.55f, 112.47f, 131.06f)
                curveTo(113.44f, 129.54f, 113.39f, 127.85f, 112.44f, 126.84f)
                lineTo(111.66f, 126.01f)
                lineTo(112.8f, 126.0f)
                curveTo(115.66f, 125.98f, 118.01f, 124.61f, 119.36f, 122.72f)
                curveTo(120.7f, 120.83f, 121.04f, 118.43f, 119.93f, 116.31f)
                lineTo(119.61f, 115.7f)
                lineTo(120.29f, 115.58f)
                curveTo(124.25f, 114.89f, 126.16f, 113.18f, 126.87f, 111.39f)
                curveTo(127.58f, 109.58f, 127.12f, 107.53f, 126.02f, 106.04f)
                lineTo(125.42f, 105.22f)
                lineTo(126.44f, 105.25f)
                curveTo(128.82f, 105.3f, 130.9f, 103.48f, 131.43f, 101.12f)
                close()
            }
        }.build()

        return _uploadSuccessEmailLight!!
    }

private var _uploadSuccessEmailLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(
            imageVector = AppIllus.UploadSuccessEmailLight,
            contentDescription = null,
            modifier = Modifier.size(previewSize),
        )
    }
}
