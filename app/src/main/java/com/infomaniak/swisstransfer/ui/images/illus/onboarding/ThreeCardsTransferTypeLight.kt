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
package com.infomaniak.swisstransfer.ui.images.illus.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIllus.ThreeCardsTransferTypeLight: ImageVector
    get() {
        if (_threeCardsTransferTypeLight != null) {
            return _threeCardsTransferTypeLight!!
        }
        _threeCardsTransferTypeLight = Builder(
            name = "ThreeCardsTransferTypeLight",
            defaultWidth = 375.0.dp,
            defaultHeight = 258.0.dp,
            viewportWidth = 375.0f,
            viewportHeight = 258.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 2.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(310.05f, 224.24f)
                curveToRelative(7.94f, 1.49f, 16.46f, -2.26f, 20.13f, -13.08f)
                moveTo(318.15f, 230.6f)
                curveToRelative(5.49f, 1.06f, 11.33f, -1.38f, 13.78f, -8.6f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 2.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(83.28f, 32.95f)
                curveToRelative(-8.35f, -1.06f, -16.97f, 3.37f, -20.11f, 14.84f)
                moveTo(74.47f, 26.83f)
                curveToRelative(-5.77f, -0.76f, -11.69f, 2.14f, -13.79f, 9.79f)
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(188.56f, 73.57f)
                lineTo(267.55f, 52.4f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 271.3f, 54.56f)
                lineTo(310.87f, 202.24f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 308.71f, 205.98f)
                lineTo(229.72f, 227.15f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 225.97f, 224.99f)
                lineTo(186.4f, 77.31f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 188.56f, 73.57f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(204.26f, 90.93f)
                lineTo(262.64f, 75.29f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 264.82f, 76.54f)
                lineTo(282.31f, 141.8f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 281.05f, 143.97f)
                lineTo(222.66f, 159.62f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 220.49f, 158.36f)
                lineTo(203.0f, 93.11f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 204.26f, 90.93f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = NonZero
            ) {
                moveTo(242.65f, 117.45f)
                moveToRelative(-20.61f, 5.52f)
                arcToRelative(21.33f, 21.33f, 120.0f, true, true, 41.21f, -11.04f)
                arcToRelative(21.33f, 21.33f, 120.0f, true, true, -41.21f, 11.04f)
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 0.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(232.47f, 113.02f)
                curveToRelative(-1.2f, 0.32f, -1.88f, 1.57f, -1.56f, 2.75f)
                lineToRelative(2.76f, 10.3f)
                curveToRelative(0.32f, 1.18f, 1.52f, 1.92f, 2.73f, 1.6f)
                lineToRelative(16.8f, -4.5f)
                curveToRelative(1.2f, -0.32f, 1.88f, -1.57f, 1.56f, -2.75f)
                lineToRelative(-2.76f, -10.3f)
                curveToRelative(-0.32f, -1.18f, -1.52f, -1.92f, -2.73f, -1.6f)
                close()
                moveTo(235.43f, 125.6f)
                lineTo(232.86f, 115.98f)
                lineTo(242.87f, 120.5f)
                arcToRelative(0.92f, 0.92f, 0.0f, false, false, 1.12f, -0.3f)
                lineToRelative(6.41f, -8.92f)
                lineToRelative(2.58f, 9.62f)
                arcToRelative(0.4f, 0.4f, 0.0f, false, true, -0.27f, 0.5f)
                lineToRelative(-16.8f, 4.5f)
                arcToRelative(0.4f, 0.4f, 0.0f, false, true, -0.48f, -0.3f)
                moveToRelative(-1.4f, -11.1f)
                lineToRelative(14.61f, -3.91f)
                lineToRelative(-5.7f, 7.93f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(250.69f, 174.77f)
                lineToRelative(-4.44f, -6.95f)
                lineToRelative(2.04f, 7.6f)
                lineToRelative(-1.23f, 0.33f)
                lineToRelative(-2.34f, -8.72f)
                lineToRelative(2.06f, -0.55f)
                lineToRelative(4.17f, 6.66f)
                lineToRelative(0.28f, -7.86f)
                lineToRelative(2.06f, -0.55f)
                lineToRelative(2.34f, 8.72f)
                lineToRelative(-1.23f, 0.33f)
                lineToRelative(-2.04f, -7.6f)
                lineToRelative(-0.37f, 8.24f)
                close()
                moveTo(256.67f, 171.34f)
                curveToRelative(-0.41f, -1.53f, 1.19f, -2.43f, 3.77f, -3.44f)
                lineToRelative(-0.04f, -0.16f)
                curveToRelative(-0.32f, -1.18f, -1.08f, -1.4f, -1.89f, -1.18f)
                curveToRelative(-0.9f, 0.24f, -1.3f, 0.88f, -1.13f, 1.68f)
                lineToRelative(-1.13f, 0.3f)
                curveToRelative(-0.26f, -1.42f, 0.75f, -2.53f, 2.01f, -2.87f)
                curveToRelative(1.78f, -0.48f, 2.81f, 0.16f, 3.29f, 1.98f)
                lineToRelative(0.39f, 1.49f)
                curveToRelative(0.28f, 1.09f, 0.54f, 1.81f, 0.82f, 2.39f)
                lineToRelative(-1.11f, 0.3f)
                arcToRelative(7.0f, 7.0f, 0.0f, false, true, -0.32f, -0.87f)
                curveToRelative(-0.22f, 0.77f, -0.79f, 1.33f, -1.93f, 1.64f)
                curveToRelative(-1.21f, 0.32f, -2.4f, -0.08f, -2.72f, -1.27f)
                moveToRelative(4.08f, -2.6f)
                curveToRelative(-1.93f, 0.74f, -3.11f, 1.36f, -2.88f, 2.25f)
                curveToRelative(0.16f, 0.61f, 0.74f, 0.9f, 1.52f, 0.69f)
                curveToRelative(1.02f, -0.27f, 1.87f, -1.0f, 1.46f, -2.53f)
                close()
                moveTo(262.99f, 162.13f)
                lineTo(263.34f, 163.46f)
                lineTo(262.25f, 163.75f)
                lineTo(261.89f, 162.43f)
                close()
                moveTo(263.59f, 164.38f)
                lineTo(265.32f, 170.85f)
                lineTo(264.23f, 171.14f)
                lineTo(262.49f, 164.68f)
                close()
                moveTo(268.15f, 170.09f)
                lineTo(267.06f, 170.38f)
                lineTo(264.72f, 161.67f)
                lineTo(265.82f, 161.38f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(84.72f, 52.4f)
                lineTo(163.71f, 73.57f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 165.87f, 77.31f)
                lineTo(126.3f, 224.99f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 122.56f, 227.15f)
                lineTo(43.56f, 205.99f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 41.4f, 202.24f)
                lineTo(80.97f, 54.56f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 84.72f, 52.4f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(87.91f, 74.83f)
                lineTo(148.01f, 90.93f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 149.27f, 93.11f)
                lineTo(131.78f, 158.36f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 129.61f, 159.62f)
                lineTo(69.5f, 143.52f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 68.25f, 141.34f)
                lineTo(85.73f, 76.08f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 87.91f, 74.83f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(96.08f, 93.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(95.53f, 96.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(94.98f, 98.07f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.43f, 100.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(93.87f, 102.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(93.32f, 104.25f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(98.14f, 94.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(95.38f, 104.81f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(100.2f, 95.05f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(99.1f, 99.17f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(98.55f, 101.23f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(97.44f, 105.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(102.26f, 95.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(101.16f, 99.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(100.61f, 101.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(99.5f, 105.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(104.32f, 96.16f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(101.56f, 106.46f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(106.38f, 96.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(105.83f, 98.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(105.28f, 100.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(104.73f, 102.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(104.18f, 104.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(103.62f, 107.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(117.15f, 130.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(116.6f, 132.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(116.05f, 134.56f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(115.5f, 136.62f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(114.94f, 138.68f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(114.39f, 140.74f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(119.21f, 130.99f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(116.45f, 141.29f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(121.27f, 131.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(120.17f, 135.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(119.62f, 137.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.51f, 141.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(123.33f, 132.1f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(122.23f, 136.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(121.68f, 138.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(120.57f, 142.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(125.39f, 132.65f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(122.63f, 142.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(127.46f, 133.2f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(126.9f, 135.26f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(126.35f, 137.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(125.8f, 139.38f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(125.25f, 141.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(124.69f, 143.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(92.18f, 108.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(91.62f, 110.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(91.07f, 112.48f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(90.52f, 114.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.42f, 118.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(88.86f, 120.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(88.31f, 122.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(87.21f, 126.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(86.65f, 128.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(86.1f, 131.03f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(85.55f, 133.09f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.24f, 108.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(93.69f, 110.98f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(90.93f, 121.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.82f, 125.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(88.72f, 129.52f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(87.61f, 133.64f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(96.3f, 109.47f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.64f, 115.65f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(92.43f, 123.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(91.88f, 125.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(91.33f, 128.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(90.22f, 132.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(89.67f, 134.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(96.15f, 118.26f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(95.05f, 122.38f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.49f, 124.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(93.94f, 126.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(93.39f, 128.56f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(92.84f, 130.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(92.29f, 132.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(91.73f, 134.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(100.42f, 110.57f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(99.87f, 112.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(99.32f, 114.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(98.76f, 116.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(96.0f, 127.06f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(95.45f, 129.12f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.34f, 133.24f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(100.82f, 117.31f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(100.27f, 119.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(98.61f, 125.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(98.06f, 127.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(97.51f, 129.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(95.85f, 135.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(101.23f, 124.04f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(99.57f, 130.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(98.47f, 134.34f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(110.47f, 97.8f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(107.71f, 108.11f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(102.5f, 111.14f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(103.29f, 124.59f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(102.18f, 128.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(101.63f, 130.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(101.08f, 132.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(99.97f, 136.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(112.53f, 98.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(111.97f, 100.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(111.42f, 102.48f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(110.87f, 104.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(110.32f, 106.6f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(109.77f, 108.66f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(106.23f, 134.21f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(102.59f, 135.45f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(102.04f, 137.51f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(114.59f, 98.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(114.04f, 100.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(112.38f, 107.15f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(111.83f, 109.21f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(104.56f, 111.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(105.2f, 133.94f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(104.65f, 136.0f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(104.1f, 138.06f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(115.54f, 103.58f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(114.99f, 105.64f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(101.72f, 121.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-1.98f, -0.53f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(108.37f, 130.37f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(106.87f, 127.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(107.81f, 132.43f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(107.26f, 134.49f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(106.71f, 136.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(106.16f, 138.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.71f, 100.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(117.6f, 104.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(117.05f, 106.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(115.95f, 110.31f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(111.0f, 128.87f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(110.43f, 130.92f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(119.67f, 104.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.56f, 108.81f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(118.01f, 110.87f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(117.47f, 112.94f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.98f, 115.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(116.22f, 125.86f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(104.81f, 127.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(113.59f, 127.35f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(113.04f, 129.41f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(111.93f, 133.53f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(111.38f, 135.59f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(110.83f, 137.65f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(124.89f, 101.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(124.34f, 103.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(123.79f, 105.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(123.23f, 107.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(122.68f, 109.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(122.13f, 111.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(121.03f, 116.09f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(120.47f, 118.15f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(119.37f, 122.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.82f, 124.34f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(118.26f, 126.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(126.95f, 102.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(124.19f, 112.52f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(122.53f, 118.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(121.98f, 120.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(121.43f, 122.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(120.32f, 126.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(129.01f, 102.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(127.91f, 106.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(127.36f, 108.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(126.25f, 113.08f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(124.59f, 119.26f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(124.04f, 121.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(123.49f, 123.38f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(122.94f, 125.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(122.39f, 127.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(131.07f, 103.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(129.97f, 107.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(129.42f, 109.51f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(128.31f, 113.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(127.21f, 117.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(125.55f, 123.93f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(133.13f, 103.88f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(130.37f, 114.18f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(129.27f, 118.3f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(128.71f, 120.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(128.16f, 122.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(127.61f, 124.48f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(127.06f, 126.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(126.51f, 128.6f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(135.19f, 104.43f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(134.64f, 106.49f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(134.09f, 108.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveToRelative(133.54f, 110.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(132.99f, 112.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(132.43f, 114.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(130.22f, 122.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(129.12f, 127.1f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(115.42f, 112.91f)
                lineToRelative(-8.42f, -2.26f)
                curveToRelative(-0.58f, -0.16f, -1.19f, 0.22f, -1.36f, 0.85f)
                lineToRelative(-2.42f, 9.03f)
                curveToRelative(-0.17f, 0.62f, 0.17f, 1.25f, 0.75f, 1.41f)
                lineToRelative(8.42f, 2.26f)
                curveToRelative(0.58f, 0.16f, 1.19f, -0.22f, 1.36f, -0.85f)
                lineToRelative(2.42f, -9.03f)
                curveToRelative(0.17f, -0.62f, -0.17f, -1.25f, -0.75f, -1.41f)
                moveTo(94.04f, 171.46f)
                lineToRelative(0.76f, 1.31f)
                lineToRelative(-0.94f, 0.55f)
                lineToRelative(-0.78f, -1.36f)
                curveToRelative(-0.74f, 0.25f, -1.57f, 0.28f, -2.45f, 0.04f)
                curveToRelative(-2.51f, -0.67f, -3.58f, -2.98f, -2.88f, -5.61f)
                curveToRelative(0.7f, -2.63f, 2.78f, -4.09f, 5.29f, -3.41f)
                curveToRelative(2.51f, 0.67f, 3.59f, 2.97f, 2.88f, 5.6f)
                curveToRelative(-0.34f, 1.27f, -1.01f, 2.27f, -1.88f, 2.88f)
                moveToRelative(-3.14f, -0.49f)
                arcToRelative(2.77f, 2.77f, 0.0f, false, false, 1.58f, -0.03f)
                lineToRelative(-0.59f, -1.02f)
                lineToRelative(0.95f, -0.56f)
                lineToRelative(0.59f, 1.03f)
                curveToRelative(0.55f, -0.47f, 0.99f, -1.18f, 1.25f, -2.15f)
                curveToRelative(0.6f, -2.25f, -0.32f, -3.81f, -1.9f, -4.23f)
                reflectiveCurveToRelative(-3.17f, 0.47f, -3.77f, 2.71f)
                reflectiveCurveToRelative(0.32f, 3.82f, 1.9f, 4.25f)
                moveToRelative(9.91f, -0.35f)
                arcToRelative(13.0f, 13.0f, 0.0f, false, true, -0.66f, -0.17f)
                lineToRelative(-1.54f, -0.41f)
                lineToRelative(-0.98f, 3.67f)
                lineToRelative(-1.23f, -0.33f)
                lineToRelative(2.34f, -8.72f)
                lineToRelative(2.77f, 0.74f)
                curveToRelative(2.13f, 0.57f, 3.52f, 1.39f, 2.96f, 3.48f)
                curveToRelative(-0.37f, 1.38f, -1.21f, 1.87f, -2.36f, 1.87f)
                lineToRelative(0.98f, 4.41f)
                lineToRelative(-1.37f, -0.37f)
                close()
                moveTo(99.69f, 166.03f)
                lineTo(98.89f, 169.01f)
                lineTo(100.53f, 169.45f)
                curveToRelative(1.27f, 0.34f, 2.31f, 0.48f, 2.67f, -0.87f)
                reflectiveCurveToRelative(-0.6f, -1.77f, -1.87f, -2.11f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(131.0f, 37.5f)
                lineTo(223.0f, 37.5f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 226.5f, 41.0f)
                lineTo(226.5f, 213.0f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 223.0f, 216.5f)
                lineTo(131.0f, 216.5f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 127.5f, 213.0f)
                lineTo(127.5f, 41.0f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 131.0f, 37.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(143.0f, 61.0f)
                lineTo(211.0f, 61.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 213.0f, 63.0f)
                lineTo(213.0f, 139.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 211.0f, 141.0f)
                lineTo(143.0f, 141.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 141.0f, 139.0f)
                lineTo(141.0f, 63.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 143.0f, 61.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = NonZero
            ) {
                moveTo(177.0f, 101.0f)
                moveToRelative(-24.0f, 0.0f)
                arcToRelative(24.0f, 24.0f, 0.0f, true, true, 48.0f, 0.0f)
                arcToRelative(24.0f, 24.0f, 0.0f, true, true, -48.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 0.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(177.85f, 103.0f)
                lineToRelative(-0.01f, 0.01f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.13f, 0.73f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, 0.4f, 0.62f)
                curveToRelative(0.22f, 0.14f, 0.49f, 0.17f, 0.73f, 0.13f)
                arcToRelative(0.97f, 0.97f, 0.0f, false, false, 0.62f, -0.39f)
                curveToRelative(0.83f, -1.14f, 1.26f, -2.51f, 1.05f, -3.89f)
                curveToRelative(-0.11f, -1.38f, -0.85f, -2.64f, -1.89f, -3.57f)
                curveToRelative(-1.05f, -0.94f, -2.41f, -1.47f, -3.79f, -1.37f)
                curveToRelative(-1.38f, 0.0f, -2.74f, 0.64f, -3.68f, 1.58f)
                lineToRelative(-4.82f, 4.82f)
                curveToRelative(-1.05f, 1.05f, -1.58f, 2.42f, -1.58f, 3.89f)
                reflectiveCurveToRelative(0.52f, 2.84f, 1.58f, 3.89f)
                curveToRelative(1.05f, 1.05f, 2.42f, 1.58f, 3.89f, 1.58f)
                curveToRelative(1.57f, 0.0f, 2.94f, -0.52f, 3.99f, -1.58f)
                lineToRelative(0.9f, -0.9f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, 0.0f, -1.46f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, -1.46f, 0.0f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-0.9f, 1.0f)
                curveToRelative(-1.31f, 1.3f, -3.65f, 1.3f, -4.96f, 0.0f)
                curveToRelative(-0.67f, -0.77f, -1.04f, -1.61f, -1.04f, -2.54f)
                curveToRelative(0.0f, -0.94f, 0.38f, -1.88f, 1.03f, -2.53f)
                lineToRelative(4.82f, -4.82f)
                curveToRelative(0.55f, -0.55f, 1.39f, -0.93f, 2.35f, -1.03f)
                curveToRelative(0.84f, 0.0f, 1.66f, 0.28f, 2.3f, 1.11f)
                lineToRelative(0.01f, 0.02f)
                lineToRelative(0.02f, 0.02f)
                curveToRelative(0.66f, 0.56f, 1.13f, 1.41f, 1.22f, 2.25f)
                arcToRelative(3.33f, 3.33f, 0.0f, false, true, -0.65f, 2.43f)
                close()
                moveTo(176.25f, 99.78f)
                lineTo(176.25f, 99.77f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, 0.13f, -0.73f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.4f, -0.62f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.73f, -0.13f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.62f, 0.39f)
                curveToRelative(-0.83f, 1.15f, -1.26f, 2.52f, -1.05f, 3.9f)
                curveToRelative(0.21f, 1.36f, 0.83f, 2.61f, 1.88f, 3.56f)
                curveToRelative(0.96f, 0.96f, 2.23f, 1.37f, 3.59f, 1.37f)
                horizontalLineToRelative(0.1f)
                curveToRelative(1.38f, 0.0f, 2.75f, -0.63f, 3.69f, -1.58f)
                lineToRelative(4.82f, -4.82f)
                curveToRelative(1.05f, -1.05f, 1.58f, -2.42f, 1.58f, -3.89f)
                reflectiveCurveToRelative(-0.52f, -2.84f, -1.58f, -3.89f)
                curveToRelative(-1.05f, -1.05f, -2.42f, -1.58f, -3.89f, -1.58f)
                reflectiveCurveToRelative(-2.84f, 0.52f, -3.89f, 1.58f)
                lineToRelative(-0.7f, 0.7f)
                curveToRelative(-0.19f, 0.19f, -0.31f, 0.44f, -0.35f, 0.7f)
                arcToRelative(0.9f, 0.9f, 0.0f, false, false, 0.25f, 0.76f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, 1.46f, 0.0f)
                lineToRelative(0.8f, -0.8f)
                curveToRelative(1.31f, -1.31f, 3.65f, -1.31f, 4.96f, -0.01f)
                curveToRelative(0.67f, 0.77f, 1.04f, 1.61f, 1.04f, 2.54f)
                curveToRelative(0.0f, 0.94f, -0.38f, 1.77f, -0.91f, 2.22f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-4.82f, 4.82f)
                curveToRelative(-0.66f, 0.66f, -1.5f, 1.03f, -2.33f, 1.03f)
                curveToRelative(-0.95f, 0.0f, -1.8f, -0.28f, -2.45f, -0.84f)
                curveToRelative(-0.66f, -0.56f, -1.13f, -1.41f, -1.22f, -2.25f)
                arcToRelative(3.34f, 3.34f, 0.0f, false, true, 0.65f, -2.43f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = NonZero
            ) {
                moveTo(166.9f, 164.77f)
                horizontalLineToRelative(5.36f)
                lineTo(172.27f, 166.0f)
                horizontalLineToRelative(-6.79f)
                verticalLineToRelative(-10.15f)
                horizontalLineToRelative(1.43f)
                close()
                moveTo(174.55f, 155.85f)
                verticalLineToRelative(1.54f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-1.54f)
                close()
                moveTo(174.55f, 158.47f)
                lineTo(174.55f, 166.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-7.53f)
                close()
                moveTo(177.85f, 159.6f)
                curveToRelative(0.43f, -0.71f, 1.16f, -1.26f, 2.44f, -1.26f)
                curveToRelative(1.82f, 0.0f, 2.48f, 1.18f, 2.48f, 2.9f)
                lineTo(182.76f, 166.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-4.38f)
                curveToRelative(0.0f, -1.16f, -0.2f, -2.17f, -1.65f, -2.17f)
                curveToRelative(-1.23f, 0.0f, -1.99f, 0.85f, -1.99f, 2.46f)
                lineTo(177.85f, 166.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-7.53f)
                horizontalLineToRelative(1.27f)
                close()
                moveTo(185.94f, 155.85f)
                verticalLineToRelative(6.01f)
                lineToRelative(3.04f, -3.39f)
                horizontalLineToRelative(1.57f)
                lineToRelative(-2.87f, 3.12f)
                lineToRelative(3.26f, 4.41f)
                horizontalLineToRelative(-1.55f)
                lineToRelative(-2.56f, -3.47f)
                lineToRelative(-0.88f, 0.95f)
                lineTo(185.94f, 166.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-10.15f)
                close()
            }
        }.build()
        return _threeCardsTransferTypeLight!!
    }

private var _threeCardsTransferTypeLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.ThreeCardsTransferTypeLight
    Box {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(AppImages.previewSize)
                .aspectRatio(imageVector.viewportWidth / imageVector.viewportHeight)
        )
    }
}
