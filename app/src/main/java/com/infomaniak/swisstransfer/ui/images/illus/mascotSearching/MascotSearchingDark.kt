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
package com.infomaniak.swisstransfer.ui.images.illus.mascotSearching

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

val AppIllus.MascotSearchingDark: ImageVector
    get() {

        if (_mascotSearchingDark != null) return _mascotSearchingDark!!

        _mascotSearchingDark = Builder(
            name = "MascotSearchingDark",
            defaultWidth = 265.0.dp,
            defaultHeight = 157.0.dp,
            viewportWidth = 265.0f,
            viewportHeight = 157.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF3C4F52)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(181.3f, 59.58f)
                lineTo(202.19f, 55.64f)
                lineTo(199.6f, 91.0f)
                lineTo(179.51f, 84.06f)
                curveTo(176.94f, 83.17f, 174.74f, 81.55f, 173.96f, 79.1f)
                curveTo(173.33f, 77.15f, 172.82f, 74.5f, 173.06f, 71.28f)
                curveTo(173.29f, 68.07f, 174.18f, 65.52f, 175.08f, 63.68f)
                curveTo(176.22f, 61.37f, 178.63f, 60.08f, 181.3f, 59.58f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(220.61f, 74.56f)
                curveTo(219.87f, 84.68f, 211.96f, 92.35f, 202.96f, 91.69f)
                curveTo(193.94f, 91.03f, 187.24f, 82.29f, 187.98f, 72.17f)
                curveTo(188.72f, 62.05f, 196.63f, 54.38f, 205.64f, 55.04f)
                curveTo(214.65f, 55.7f, 221.35f, 64.44f, 220.61f, 74.56f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(194.98f, 86.47f)
                curveTo(196.0f, 86.86f, 197.08f, 87.11f, 198.22f, 87.19f)
                curveTo(205.19f, 87.7f, 211.31f, 81.81f, 211.88f, 74.03f)
                curveTo(212.44f, 66.26f, 207.25f, 59.54f, 200.28f, 59.03f)
                curveTo(199.14f, 58.95f, 198.03f, 59.03f, 196.97f, 59.27f)
                curveTo(192.98f, 62.04f, 190.16f, 66.82f, 189.75f, 72.42f)
                curveTo(189.34f, 78.02f, 191.43f, 83.15f, 194.98f, 86.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 136.53f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, 264.08f, 0.0f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, -264.08f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(181.96f, 87.89f)
                lineTo(178.39f, 110.09f)
                lineTo(172.06f, 88.52f)
                curveTo(171.29f, 85.87f, 172.76f, 83.1f, 175.38f, 82.25f)
                curveTo(178.99f, 81.08f, 182.56f, 84.15f, 181.96f, 87.89f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(90.13f, 62.62f)
                curveTo(92.45f, 41.22f, 110.51f, 25.0f, 132.04f, 25.0f)
                curveTo(153.57f, 25.0f, 171.64f, 41.22f, 173.95f, 62.62f)
                lineTo(183.99f, 155.5f)
                lineTo(172.91f, 156.0f)
                curveTo(165.79f, 146.21f, 151.42f, 145.56f, 143.44f, 154.66f)
                lineTo(141.38f, 157.0f)
                horizontalLineTo(122.7f)
                lineTo(120.64f, 154.66f)
                curveTo(112.66f, 145.56f, 98.3f, 146.21f, 91.17f, 156.0f)
                lineTo(80.1f, 155.4f)
                lineTo(90.13f, 62.62f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(183.99f, 155.5f)
                lineTo(173.95f, 62.62f)
                curveTo(171.64f, 41.22f, 153.57f, 25.0f, 132.04f, 25.0f)
                verticalLineTo(25.0f)
                curveTo(110.51f, 25.0f, 92.45f, 41.22f, 90.13f, 62.62f)
                lineTo(80.1f, 155.4f)
                moveTo(91.17f, 156.0f)
                verticalLineTo(156.0f)
                curveTo(98.3f, 146.21f, 112.66f, 145.56f, 120.64f, 154.66f)
                lineTo(122.7f, 157.0f)
                moveTo(141.38f, 157.0f)
                lineTo(143.44f, 154.66f)
                curveTo(151.42f, 145.56f, 165.79f, 146.21f, 172.91f, 156.0f)
                verticalLineTo(156.0f)
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(116.52f, 68.78f)
                lineTo(142.15f, 63.29f)
                lineTo(140.64f, 84.04f)
                lineTo(116.07f, 74.88f)
                curveTo(114.75f, 74.39f, 113.91f, 73.08f, 114.01f, 71.66f)
                curveTo(114.11f, 70.25f, 115.14f, 69.08f, 116.52f, 68.78f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(144.37f, 61.58f)
                lineTo(165.26f, 57.64f)
                lineTo(162.68f, 93.0f)
                lineTo(142.58f, 86.06f)
                curveTo(140.02f, 85.17f, 137.82f, 83.55f, 137.03f, 81.1f)
                curveTo(136.4f, 79.15f, 135.9f, 76.5f, 136.13f, 73.28f)
                curveTo(136.37f, 70.07f, 137.26f, 67.52f, 138.16f, 65.68f)
                curveTo(139.3f, 63.37f, 141.71f, 62.08f, 144.37f, 61.58f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(183.69f, 76.56f)
                curveTo(182.95f, 86.68f, 175.04f, 94.35f, 166.03f, 93.69f)
                curveTo(157.02f, 93.03f, 150.32f, 84.29f, 151.06f, 74.17f)
                curveTo(151.8f, 64.05f, 159.7f, 56.38f, 168.71f, 57.04f)
                curveTo(177.72f, 57.7f, 184.43f, 66.44f, 183.69f, 76.56f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = EvenOdd
            ) {
                moveTo(157.98f, 88.47f)
                curveTo(159.0f, 88.86f, 160.08f, 89.11f, 161.22f, 89.19f)
                curveTo(168.19f, 89.7f, 174.31f, 83.81f, 174.88f, 76.03f)
                curveTo(175.44f, 68.26f, 170.25f, 61.54f, 163.28f, 61.03f)
                curveTo(162.14f, 60.95f, 161.03f, 61.03f, 159.97f, 61.27f)
                curveTo(155.98f, 64.04f, 153.16f, 68.82f, 152.75f, 74.42f)
                curveTo(152.34f, 80.02f, 154.43f, 85.15f, 157.98f, 88.47f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(134.22f, 96.16f)
                lineTo(140.8f, 84.95f)
                curveTo(142.83f, 81.49f, 141.67f, 77.03f, 138.2f, 75.0f)
                curveTo(134.74f, 72.97f, 130.28f, 74.13f, 128.25f, 77.6f)
                lineTo(121.68f, 88.81f)
                curveTo(119.65f, 92.27f, 120.81f, 96.73f, 124.27f, 98.76f)
                curveTo(127.74f, 100.79f, 132.19f, 99.63f, 134.22f, 96.16f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke =
                SolidColor(Color(0xFFDCE4E5)), strokeLineWidth = 1.0f, strokeLineCap =
                Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType =
                NonZero
            ) {
                moveTo(130.55f, 102.44f)
                lineTo(140.8f, 84.95f)
                curveTo(142.83f, 81.49f, 141.67f, 77.03f, 138.2f, 75.0f)
                curveTo(134.74f, 72.97f, 130.28f, 74.13f, 128.25f, 77.6f)
                lineTo(118.0f, 95.08f)
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(93.29f, 14.96f)
                curveTo(94.01f, 16.24f, 93.61f, 17.65f, 93.03f, 18.75f)
                curveTo(92.49f, 19.76f, 92.33f, 20.17f, 92.75f, 20.91f)
                lineTo(92.98f, 21.32f)
                lineTo(91.73f, 22.02f)
                lineTo(91.47f, 21.57f)
                curveTo(90.84f, 20.43f, 91.11f, 19.63f, 91.83f, 18.29f)
                curveTo(92.28f, 17.45f, 92.53f, 16.6f, 92.05f, 15.73f)
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
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
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
                fill = SolidColor(Color(0xFF67DD95)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
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
        }.build()

        return _mascotSearchingDark!!
    }

private var _mascotSearchingDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotSearchingDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
