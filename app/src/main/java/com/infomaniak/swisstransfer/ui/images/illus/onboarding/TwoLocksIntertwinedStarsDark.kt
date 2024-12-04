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

val AppIllus.TwoLocksIntertwinedStarsDark: ImageVector
    get() {
        if (_twoLocksIntertwinedStarsDark != null) {
            return _twoLocksIntertwinedStarsDark!!
        }
        _twoLocksIntertwinedStarsDark = Builder(
            name = "TwoLocksIntertwinedStarsDark",
            defaultWidth = 375.0.dp,
            defaultHeight = 258.0.dp,
            viewportWidth = 375.0f,
            viewportHeight = 258.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 9.78f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(194.45f, 121.52f)
                lineTo(194.45f, 121.52f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 186.99f, 159.39f)
                lineTo(160.87f, 176.91f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 123.0f, 169.45f)
                lineTo(123.0f, 169.45f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 130.46f, 131.58f)
                lineTo(156.58f, 114.06f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 194.45f, 121.52f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF3C4F52)),
                strokeLineWidth = 9.776f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(253.29f, 171.5f)
                lineTo(253.29f, 171.5f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 215.94f, 181.26f)
                lineTo(188.8f, 165.36f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 179.04f, 128.02f)
                lineTo(179.04f, 128.02f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 216.39f, 118.26f)
                lineTo(243.53f, 134.15f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 253.29f, 171.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                pathFillType = NonZero
            ) {
                moveToRelative(187.85f, 153.01f)
                lineToRelative(4.43f, 8.51f)
                lineToRelative(-16.53f, 11.3f)
                lineToRelative(-5.41f, -8.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(125.47f, 119.07f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 10.61f, 2.09f)
                lineToRelative(36.56f, 54.5f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -2.09f, 10.61f)
                lineToRelative(-48.43f, 32.49f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -10.61f, -2.09f)
                lineToRelative(-36.56f, -54.5f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 2.09f, -10.61f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(117.45f, 167.59f)
                lineToRelative(0.01f, 0.3f)
                lineToRelative(-0.26f, 0.15f)
                lineToRelative(-5.53f, 3.23f)
                curveToRelative(-1.57f, 0.92f, -2.11f, 2.95f, -1.2f, 4.54f)
                arcToRelative(3.25f, 3.25f, 0.0f, false, false, 4.47f, 1.22f)
                lineToRelative(5.53f, -3.23f)
                lineToRelative(0.26f, -0.16f)
                lineToRelative(0.26f, 0.17f)
                arcToRelative(7.56f, 7.56f, 0.0f, false, false, 7.93f, 0.17f)
                curveToRelative(3.66f, -2.14f, 4.92f, -6.89f, 2.8f, -10.61f)
                curveToRelative(-2.12f, -3.71f, -6.79f, -4.98f, -10.45f, -2.84f)
                lineToRelative(-0.25f, -0.43f)
                lineToRelative(0.25f, 0.43f)
                curveToRelative(-2.55f, 1.49f, -3.94f, 4.26f, -3.82f, 7.05f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(302.7f, 149.28f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 2.77f, 10.45f)
                lineToRelative(-32.96f, 56.75f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -10.45f, 2.77f)
                lineToRelative(-50.43f, -29.29f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -2.77f, -10.45f)
                lineToRelative(32.96f, -56.75f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 10.45f, -2.77f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(255.61f, 166.59f)
                lineToRelative(-0.01f, 0.3f)
                lineToRelative(0.26f, 0.15f)
                lineToRelative(5.53f, 3.23f)
                curveToRelative(1.57f, 0.92f, 2.11f, 2.95f, 1.2f, 4.54f)
                arcToRelative(3.25f, 3.25f, 0.0f, false, true, -4.47f, 1.22f)
                lineToRelative(-5.53f, -3.23f)
                lineToRelative(-0.26f, -0.16f)
                lineToRelative(-0.26f, 0.17f)
                arcToRelative(7.56f, 7.56f, 0.0f, false, true, -7.93f, 0.17f)
                curveToRelative(-3.66f, -2.14f, -4.92f, -6.89f, -2.8f, -10.61f)
                curveToRelative(2.12f, -3.71f, 6.79f, -4.98f, 10.45f, -2.84f)
                lineToRelative(0.25f, -0.43f)
                lineToRelative(-0.25f, 0.43f)
                curveToRelative(2.55f, 1.49f, 3.94f, 4.26f, 3.82f, 7.05f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                pathFillType = NonZero
            ) {
                moveToRelative(179.05f, 46.08f)
                lineToRelative(2.95f, -9.28f)
                lineToRelative(-4.76f, -1.51f)
                lineToRelative(-2.95f, 9.28f)
                lineToRelative(-7.75f, -6.15f)
                lineToRelative(-2.95f, 3.87f)
                lineToRelative(7.82f, 5.92f)
                lineToRelative(-7.89f, 5.91f)
                lineToRelative(3.29f, 3.96f)
                lineToRelative(7.8f, -6.19f)
                lineToRelative(3.27f, 9.71f)
                lineToRelative(4.45f, -1.59f)
                lineToRelative(-2.98f, -9.27f)
                lineToRelative(9.96f, -0.27f)
                lineToRelative(-0.41f, -4.94f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)),
                pathFillType = NonZero
            ) {
                moveToRelative(150.06f, 84.37f)
                lineToRelative(1.02f, -3.23f)
                lineToRelative(-1.65f, -0.52f)
                lineToRelative(-1.03f, 3.23f)
                lineToRelative(-2.7f, -2.14f)
                lineToRelative(-1.02f, 1.35f)
                lineToRelative(2.72f, 2.06f)
                lineToRelative(-2.74f, 2.06f)
                lineToRelative(1.15f, 1.38f)
                lineToRelative(2.72f, -2.16f)
                lineToRelative(1.14f, 3.38f)
                lineToRelative(1.55f, -0.55f)
                lineToRelative(-1.04f, -3.23f)
                lineToRelative(3.47f, -0.09f)
                lineToRelative(-0.14f, -1.72f)
                close()
                moveTo(218.2f, 65.79f)
                lineToRelative(1.53f, -4.82f)
                lineToRelative(-2.47f, -0.79f)
                lineToRelative(-1.53f, 4.82f)
                lineToRelative(-4.03f, -3.2f)
                lineToRelative(-1.53f, 2.01f)
                lineToRelative(4.06f, 3.08f)
                lineToRelative(-4.1f, 3.07f)
                lineToRelative(1.71f, 2.06f)
                lineToRelative(4.05f, -3.22f)
                lineToRelative(1.7f, 5.05f)
                lineToRelative(2.31f, -0.83f)
                lineToRelative(-1.55f, -4.82f)
                lineToRelative(5.18f, -0.14f)
                lineToRelative(-0.21f, -2.57f)
                close()
            }
        }.build()
        return _twoLocksIntertwinedStarsDark!!
    }

private var _twoLocksIntertwinedStarsDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.TwoLocksIntertwinedStarsDark
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
