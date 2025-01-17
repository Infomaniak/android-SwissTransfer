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
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

val AppIllus.StorageCardboardBoxPileLight: ImageVector
    get() {
        if (_storageCardboardBoxPileLight != null) {
            return _storageCardboardBoxPileLight!!
        }
        _storageCardboardBoxPileLight = Builder(
            name = "StorageCardboardBoxPileLight",
            defaultWidth = 308.0.dp,
            defaultHeight = 249.0.dp,
            viewportWidth = 308.0f,
            viewportHeight = 249.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(91.5f, 0.5f)
                horizontalLineToRelative(155.0f)
                verticalLineToRelative(156.0f)
                horizontalLineToRelative(-155.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveTo(91.0f, 0.0f)
                horizontalLineToRelative(156.0f)
                verticalLineToRelative(32.0f)
                horizontalLineTo(91.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(82.7f, 25.5f)
                lineToRelative(8.65f, -25.0f)
                horizontalLineToRelative(155.29f)
                lineToRelative(8.65f, 25.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(204.0f, 54.0f)
                horizontalLineToRelative(-70.0f)
                verticalLineToRelative(24.0f)
                horizontalLineToRelative(70.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveTo(155.88f, 67.91f)
                curveToRelative(0.0f, -1.56f, -1.09f, -2.23f, -2.12f, -2.23f)
                curveToRelative(-0.98f, 0.0f, -1.61f, 0.57f, -1.82f, 1.04f)
                lineToRelative(-1.18f, -0.07f)
                lineToRelative(0.61f, -5.07f)
                horizontalLineToRelative(5.41f)
                verticalLineToRelative(1.1f)
                horizontalLineToRelative(-4.44f)
                lineToRelative(-0.32f, 2.68f)
                curveToRelative(0.49f, -0.48f, 1.25f, -0.71f, 2.03f, -0.71f)
                curveToRelative(1.65f, 0.0f, 3.09f, 1.17f, 3.09f, 3.25f)
                curveToRelative(0.0f, 2.16f, -1.6f, 3.27f, -3.35f, 3.27f)
                curveToRelative(-2.18f, 0.0f, -3.17f, -1.38f, -3.35f, -2.9f)
                horizontalLineToRelative(1.22f)
                curveToRelative(0.18f, 1.18f, 0.82f, 1.91f, 2.13f, 1.91f)
                curveToRelative(1.13f, 0.0f, 2.11f, -0.82f, 2.11f, -2.27f)
                moveToRelative(6.15f, 3.26f)
                curveToRelative(-2.51f, 0.0f, -3.66f, -1.9f, -3.66f, -4.89f)
                curveToRelative(0.0f, -2.97f, 1.16f, -4.87f, 3.66f, -4.87f)
                reflectiveCurveToRelative(3.66f, 1.9f, 3.66f, 4.87f)
                curveToRelative(0.0f, 2.99f, -1.16f, 4.89f, -3.66f, 4.89f)
                moveToRelative(0.0f, -8.77f)
                curveToRelative(-1.48f, 0.0f, -2.4f, 1.27f, -2.4f, 3.89f)
                curveToRelative(0.0f, 2.62f, 0.92f, 3.9f, 2.4f, 3.9f)
                reflectiveCurveToRelative(2.4f, -1.27f, 2.4f, -3.9f)
                curveToRelative(0.0f, -2.61f, -0.92f, -3.88f, -2.4f, -3.88f)
                moveToRelative(16.33f, 1.92f)
                horizontalLineToRelative(-1.29f)
                curveToRelative(-0.31f, -1.05f, -1.17f, -1.79f, -2.68f, -1.79f)
                curveToRelative(-1.66f, 0.0f, -2.96f, 1.3f, -2.96f, 3.74f)
                curveToRelative(0.0f, 2.38f, 1.22f, 3.78f, 2.96f, 3.78f)
                curveToRelative(1.26f, 0.0f, 2.78f, -0.52f, 2.78f, -2.47f)
                verticalLineToRelative(-0.25f)
                horizontalLineToRelative(-2.49f)
                verticalLineToRelative(-1.14f)
                horizontalLineToRelative(3.74f)
                lineTo(178.42f, 71.0f)
                horizontalLineToRelative(-1.04f)
                lineToRelative(-0.06f, -1.18f)
                curveToRelative(-0.61f, 0.96f, -1.72f, 1.35f, -2.97f, 1.35f)
                curveToRelative(-2.71f, 0.0f, -4.26f, -2.03f, -4.26f, -4.9f)
                curveToRelative(0.0f, -2.92f, 1.73f, -4.86f, 4.38f, -4.86f)
                curveToRelative(1.95f, 0.0f, 3.51f, 1.09f, 3.9f, 2.91f)
                moveToRelative(2.18f, -2.74f)
                horizontalLineToRelative(3.39f)
                curveToRelative(1.75f, 0.0f, 3.31f, 0.46f, 3.31f, 2.33f)
                curveToRelative(0.0f, 0.9f, -0.39f, 1.7f, -1.04f, 2.08f)
                curveToRelative(0.9f, 0.38f, 1.43f, 1.08f, 1.43f, 2.23f)
                curveToRelative(0.0f, 2.0f, -1.49f, 2.78f, -3.38f, 2.78f)
                horizontalLineToRelative(-3.72f)
                close()
                moveTo(181.86f, 65.61f)
                horizontalLineToRelative(2.27f)
                curveToRelative(1.08f, 0.0f, 1.77f, -0.42f, 1.77f, -1.52f)
                curveToRelative(0.0f, -0.96f, -0.71f, -1.39f, -1.77f, -1.39f)
                horizontalLineToRelative(-2.27f)
                close()
                moveTo(181.86f, 69.88f)
                horizontalLineToRelative(2.4f)
                curveToRelative(1.34f, 0.0f, 2.0f, -0.58f, 2.0f, -1.65f)
                curveToRelative(0.0f, -1.11f, -0.66f, -1.57f, -2.08f, -1.57f)
                horizontalLineToRelative(-2.33f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                pathFillType = NonZero
            ) {
                moveTo(197.0f, 59.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(197.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(197.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(197.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(15.31f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineToRelative(-1.22f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineTo(63.64f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineTo(60.71f)
                horizontalLineToRelative(1.56f)
                verticalLineTo(59.0f)
                close()
                moveTo(126.0f, 72.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(126.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(126.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(1.22f)
                horizontalLineTo(126.0f)
                verticalLineToRelative(1.71f)
                horizontalLineToRelative(15.31f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineToRelative(-1.22f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineTo(76.64f)
                horizontalLineToRelative(1.56f)
                verticalLineToRelative(-1.71f)
                horizontalLineToRelative(-1.56f)
                verticalLineTo(73.71f)
                horizontalLineToRelative(1.56f)
                verticalLineTo(72.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(34.5f, 113.5f)
                horizontalLineToRelative(162.0f)
                verticalLineToRelative(135.0f)
                horizontalLineToRelative(-162.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                pathFillType = NonZero
            ) {
                moveTo(42.0f, 121.0f)
                horizontalLineToRelative(32.0f)
                verticalLineToRelative(18.0f)
                horizontalLineTo(42.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(47.0f, 127.0f)
                lineTo(61.0f, 127.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 62.0f, 128.0f)
                lineTo(62.0f, 128.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 61.0f, 129.0f)
                lineTo(47.0f, 129.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 128.0f)
                lineTo(46.0f, 128.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 47.0f, 127.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                pathFillType = NonZero
            ) {
                moveTo(47.0f, 131.0f)
                lineTo(69.0f, 131.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 70.0f, 132.0f)
                lineTo(70.0f, 132.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 69.0f, 133.0f)
                lineTo(47.0f, 133.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 46.0f, 132.0f)
                lineTo(46.0f, 132.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 47.0f, 131.0f)
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
                moveTo(188.5f, 133.5f)
                horizontalLineToRelative(119.0f)
                verticalLineToRelative(115.0f)
                horizontalLineToRelative(-119.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                pathFillType = NonZero
            ) {
                moveTo(280.0f, 134.0f)
                horizontalLineToRelative(16.0f)
                verticalLineToRelative(32.0f)
                lineToRelative(-4.0f, -4.8f)
                lineToRelative(-4.0f, 4.8f)
                lineToRelative(-4.0f, -4.8f)
                lineToRelative(-4.0f, 4.8f)
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
                moveTo(13.5f, 157.5f)
                horizontalLineToRelative(155.0f)
                verticalLineToRelative(91.0f)
                horizontalLineToRelative(-155.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                pathFillType = EvenOdd
            ) {
                moveTo(140.0f, 229.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(14.0f)
                horizontalLineToRelative(2.0f)
                close()
                moveTo(143.0f, 229.0f)
                verticalLineToRelative(14.0f)
                horizontalLineToRelative(1.0f)
                verticalLineToRelative(-14.0f)
                close()
                moveTo(146.0f, 243.0f)
                horizontalLineToRelative(-1.0f)
                verticalLineToRelative(-14.0f)
                horizontalLineToRelative(1.0f)
                close()
                moveTo(147.0f, 243.0f)
                horizontalLineToRelative(5.0f)
                verticalLineToRelative(-14.0f)
                horizontalLineToRelative(-5.0f)
                close()
                moveTo(154.0f, 243.0f)
                horizontalLineToRelative(-1.0f)
                verticalLineToRelative(-14.0f)
                horizontalLineToRelative(1.0f)
                close()
                moveTo(157.0f, 243.0f)
                horizontalLineToRelative(-1.0f)
                verticalLineToRelative(-14.0f)
                horizontalLineToRelative(1.0f)
                close()
                moveTo(158.0f, 243.0f)
                horizontalLineToRelative(5.0f)
                verticalLineToRelative(-14.0f)
                horizontalLineToRelative(-5.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero
            ) {
                moveTo(13.0f, 157.0f)
                horizontalLineToRelative(156.0f)
                verticalLineToRelative(38.0f)
                horizontalLineTo(13.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(0.74f, 188.5f)
                lineToRelative(12.59f, -31.0f)
                horizontalLineToRelative(155.33f)
                lineToRelative(12.59f, 31.0f)
                close()
            }
        }.build()
        return _storageCardboardBoxPileLight!!
    }

private var _storageCardboardBoxPileLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.StorageCardboardBoxPileLight
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
