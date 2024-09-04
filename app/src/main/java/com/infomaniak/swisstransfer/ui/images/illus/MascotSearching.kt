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

val AppIllus.MascotSearching: ImageVector
    get() {

        if (_mascotSearching != null) return _mascotSearching!!

        _mascotSearching = Builder(
            name = "MascotSearching",
            defaultWidth = 265.0.dp,
            defaultHeight = 157.0.dp,
            viewportWidth = 265.0f,
            viewportHeight = 157.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFffffff)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(171.52f, 85.22f)
                curveTo(170.75f, 82.58f, 172.22f, 79.81f, 174.84f, 78.96f)
                curveTo(178.45f, 77.79f, 182.02f, 80.85f, 181.41f, 84.6f)
                lineTo(177.85f, 106.79f)
                lineTo(171.52f, 85.22f)
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
                moveTo(91.09f, 61.02f)
                curveTo(93.37f, 39.88f, 111.23f, 23.85f, 132.5f, 23.85f)
                curveTo(153.77f, 23.85f, 171.62f, 39.88f, 173.91f, 61.02f)
                lineTo(182.81f, 143.37f)
                lineTo(173.09f, 139.77f)
                curveTo(164.14f, 136.46f, 154.68f, 134.76f, 145.14f, 134.76f)
                horizontalLineTo(132.5f)
                horizontalLineTo(105.53f)
                horizontalLineTo(83.12f)
                lineTo(91.09f, 61.02f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(0.46f, 136.47f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, 264.08f, 0.0f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, -264.08f, 0.0f)
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
                    moveTo(91.09f, 61.02f)
                    curveTo(93.38f, 39.88f, 111.23f, 23.85f, 132.5f, 23.85f)
                    curveTo(153.77f, 23.85f, 171.63f, 39.88f, 173.91f, 61.02f)
                    lineTo(185.74f, 170.49f)
                    lineTo(173.36f, 153.49f)
                    curveTo(166.5f, 144.06f, 152.45f, 144.06f, 145.58f, 153.49f)
                    curveTo(139.12f, 162.36f, 125.88f, 162.36f, 119.42f, 153.49f)
                    curveTo(112.56f, 144.06f, 98.5f, 144.06f, 91.64f, 153.49f)
                    lineTo(79.26f, 170.49f)
                    lineTo(91.09f, 61.02f)
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
                    moveTo(93.75f, 14.96f)
                    curveTo(94.47f, 16.24f, 94.07f, 17.65f, 93.49f, 18.75f)
                    curveTo(92.95f, 19.76f, 92.79f, 20.17f, 93.21f, 20.91f)
                    lineTo(93.44f, 21.32f)
                    lineTo(92.19f, 22.02f)
                    lineTo(91.93f, 21.57f)
                    curveTo(91.3f, 20.43f, 91.57f, 19.63f, 92.29f, 18.29f)
                    curveTo(92.74f, 17.45f, 92.99f, 16.6f, 92.5f, 15.73f)
                    curveTo(92.03f, 14.88f, 90.97f, 14.75f, 89.93f, 15.33f)
                    curveTo(88.42f, 16.17f, 88.52f, 17.49f, 88.93f, 18.35f)
                    lineTo(87.7f, 19.03f)
                    curveTo(86.74f, 17.27f, 87.47f, 15.36f, 89.38f, 14.29f)
                    curveTo(90.91f, 13.44f, 92.84f, 13.33f, 93.75f, 14.96f)
                    close()
                    moveTo(93.66f, 24.95f)
                    lineTo(92.8f, 23.4f)
                    lineTo(94.34f, 22.53f)
                    lineTo(95.21f, 24.08f)
                    lineTo(93.66f, 24.95f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(163.2f, 1.85f)
                    curveTo(163.2f, 2.85f, 162.5f, 3.54f, 161.78f, 4.0f)
                    curveTo(161.14f, 4.41f, 160.9f, 4.6f, 160.9f, 5.18f)
                    lineTo(160.9f, 5.5f)
                    lineTo(159.93f, 5.5f)
                    lineTo(159.93f, 5.14f)
                    curveTo(159.94f, 4.26f, 160.37f, 3.88f, 161.23f, 3.33f)
                    curveTo(161.78f, 2.99f, 162.21f, 2.57f, 162.21f, 1.89f)
                    curveTo(162.21f, 1.24f, 161.63f, 0.81f, 160.82f, 0.8f)
                    curveTo(159.66f, 0.8f, 159.28f, 1.61f, 159.23f, 2.25f)
                    lineTo(158.28f, 2.24f)
                    curveTo(158.3f, 0.89f, 159.37f, 0.0f, 160.85f, 0.01f)
                    curveTo(162.03f, 0.01f, 163.21f, 0.59f, 163.2f, 1.85f)
                    close()
                    moveTo(159.83f, 7.71f)
                    lineTo(159.83f, 6.51f)
                    lineTo(161.03f, 6.51f)
                    lineTo(161.03f, 7.71f)
                    lineTo(159.83f, 7.71f)
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
                    moveTo(179.99f, 15.04f)
                    curveTo(179.36f, 16.81f, 177.67f, 17.6f, 176.12f, 17.96f)
                    curveTo(174.7f, 18.29f, 174.17f, 18.48f, 173.81f, 19.5f)
                    lineTo(173.6f, 20.07f)
                    lineTo(171.88f, 19.46f)
                    lineTo(172.1f, 18.83f)
                    curveTo(172.66f, 17.27f, 173.67f, 16.86f, 175.56f, 16.43f)
                    curveTo(176.74f, 16.16f, 177.77f, 15.69f, 178.2f, 14.49f)
                    curveTo(178.62f, 13.32f, 177.85f, 12.19f, 176.43f, 11.68f)
                    curveTo(174.35f, 10.93f, 173.17f, 12.14f, 172.69f, 13.24f)
                    lineTo(171.0f, 12.64f)
                    curveTo(171.88f, 10.23f, 174.34f, 9.34f, 176.97f, 10.28f)
                    curveTo(179.06f, 11.03f, 180.79f, 12.8f, 179.99f, 15.04f)
                    close()
                    moveTo(170.3f, 23.32f)
                    lineTo(171.07f, 21.19f)
                    lineTo(173.2f, 21.96f)
                    lineTo(172.43f, 24.09f)
                    lineTo(170.3f, 23.32f)
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
                    moveTo(152.82f, 67.81f)
                    curveTo(152.92f, 66.46f, 153.9f, 65.34f, 155.22f, 65.06f)
                    lineTo(179.8f, 59.8f)
                    lineTo(178.35f, 79.66f)
                    lineTo(154.79f, 70.88f)
                    curveTo(153.52f, 70.41f, 152.72f, 69.16f, 152.82f, 67.81f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(176.0f, 62.07f)
                    curveTo(177.09f, 59.86f, 179.4f, 58.63f, 181.95f, 58.15f)
                    lineTo(201.98f, 54.37f)
                    lineTo(199.5f, 88.26f)
                    lineTo(180.24f, 81.61f)
                    curveTo(177.78f, 80.76f, 175.68f, 79.2f, 174.92f, 76.86f)
                    curveTo(174.32f, 74.99f, 173.84f, 72.44f, 174.06f, 69.36f)
                    curveTo(174.29f, 66.28f, 175.13f, 63.83f, 176.0f, 62.07f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(216.4f, 72.46f)
                    curveTo(215.71f, 81.96f, 208.31f, 89.05f, 199.99f, 88.44f)
                    curveTo(191.67f, 87.84f, 185.38f, 79.74f, 186.08f, 70.24f)
                    curveTo(186.77f, 60.74f, 194.17f, 53.65f, 202.49f, 54.25f)
                    curveTo(210.81f, 54.86f, 217.1f, 62.96f, 216.4f, 72.46f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(186.86f, 70.3f)
                    arcToRelative(16.13f, 14.41f, 94.18f, true, false, 28.75f, 2.1f)
                    arcToRelative(16.13f, 14.41f, 94.18f, true, false, -28.75f, -2.1f)
                    close()
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF3CB572)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(183.86f, 70.08f)
                    arcToRelative(13.55f, 12.15f, 94.18f, true, false, 24.24f, 1.77f)
                    arcToRelative(13.55f, 12.15f, 94.18f, true, false, -24.24f, -1.77f)
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
                    moveTo(115.82f, 70.66f)
                    curveTo(115.93f, 69.25f, 116.95f, 68.08f, 118.33f, 67.78f)
                    lineTo(143.97f, 62.29f)
                    lineTo(142.45f, 83.04f)
                    lineTo(117.89f, 73.88f)
                    curveTo(116.56f, 73.39f, 115.72f, 72.08f, 115.82f, 70.66f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(139.97f, 64.68f)
                    curveTo(141.11f, 62.37f, 143.52f, 61.08f, 146.18f, 60.58f)
                    lineTo(167.07f, 56.64f)
                    lineTo(164.49f, 92.0f)
                    lineTo(144.4f, 85.06f)
                    curveTo(141.83f, 84.17f, 139.63f, 82.55f, 138.84f, 80.1f)
                    curveTo(138.22f, 78.15f, 137.71f, 75.5f, 137.95f, 72.28f)
                    curveTo(138.18f, 69.07f, 139.07f, 66.52f, 139.97f, 64.68f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(182.59f, 75.55f)
                    curveTo(181.85f, 85.67f, 173.95f, 93.34f, 164.94f, 92.68f)
                    curveTo(155.93f, 92.02f, 149.22f, 83.28f, 149.96f, 73.16f)
                    curveTo(150.7f, 63.04f, 158.61f, 55.37f, 167.62f, 56.03f)
                    curveTo(176.63f, 56.69f, 183.33f, 65.43f, 182.59f, 75.55f)
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
                    moveTo(182.59f, 75.55f)
                    curveTo(181.85f, 85.67f, 173.95f, 93.34f, 164.94f, 92.68f)
                    curveTo(155.93f, 92.02f, 149.22f, 83.28f, 149.96f, 73.16f)
                    curveTo(150.7f, 63.04f, 158.61f, 55.37f, 167.62f, 56.03f)
                    curveTo(176.63f, 56.69f, 183.33f, 65.43f, 182.59f, 75.55f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(151.31f, 73.26f)
                    arcToRelative(16.81f, 15.01f, 94.18f, true, false, 29.95f, 2.19f)
                    arcToRelative(16.81f, 15.01f, 94.18f, true, false, -29.95f, -2.19f)
                    close()
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF3CB572)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(148.18f, 73.03f)
                    arcToRelative(14.12f, 12.66f, 94.18f, true, false, 25.25f, 1.85f)
                    arcToRelative(14.12f, 12.66f, 94.18f, true, false, -25.25f, -1.85f)
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
                    moveTo(135.84f, 96.5f)
                    lineTo(142.41f, 85.28f)
                    curveTo(144.45f, 81.82f, 143.28f, 77.36f, 139.82f, 75.33f)
                    curveTo(136.35f, 73.3f, 131.9f, 74.46f, 129.87f, 77.93f)
                    lineTo(123.29f, 89.14f)
                    curveTo(121.26f, 92.61f, 122.43f, 97.06f, 125.89f, 99.09f)
                    curveTo(129.35f, 101.13f, 133.81f, 99.96f, 135.84f, 96.5f)
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
                    moveTo(132.16f, 102.77f)
                    lineTo(142.41f, 85.28f)
                    curveTo(144.45f, 81.82f, 143.28f, 77.36f, 139.82f, 75.33f)
                    verticalLineTo(75.33f)
                    curveTo(136.35f, 73.3f, 131.9f, 74.46f, 129.87f, 77.93f)
                    lineTo(119.62f, 95.42f)
                }
            }
        }.build()

        return _mascotSearching!!
    }

private var _mascotSearching: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MascotSearching,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
