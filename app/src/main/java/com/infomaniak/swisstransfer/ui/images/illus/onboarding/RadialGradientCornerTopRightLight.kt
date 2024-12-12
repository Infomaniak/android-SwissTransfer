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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.RadialGradientCornerTopRightLight: ImageVector
    get() {
        if (_radialGradientCornerTopRightLight != null) {
            return _radialGradientCornerTopRightLight!!
        }
        _radialGradientCornerTopRightLight = Builder(
            name = "RadialGradientCornerTopRightLight",
            defaultWidth = 375.0.dp,
            defaultHeight = 832.0.dp,
            viewportWidth = 375.0f,
            viewportHeight = 832.0f
        ).apply {
            path(
                fill = radialGradient(
                    0.45f to Color(0xFFE3F6DC), 1.0f to Color(0x00E3F6DC), center = Offset(363.92f, 2.41f), radius = 179.03f
                ),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(371.0f, 2.0f)
                moveToRelative(-182.0f, 0.0f)
                arcToRelative(182.0f, 182.0f, 0.0f, true, true, 364.0f, 0.0f)
                arcToRelative(182.0f, 182.0f, 0.0f, true, true, -364.0f, 0.0f)
            }
        }.build()
        return _radialGradientCornerTopRightLight!!
    }

private var _radialGradientCornerTopRightLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.wrapContentHeight()) {
        Image(
            imageVector = AppIllus.RadialGradientCornerTopRightLight,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.wrapContentSize()
        )
    }
}
