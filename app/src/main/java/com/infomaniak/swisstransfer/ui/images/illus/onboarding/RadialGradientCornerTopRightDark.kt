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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.RadialGradientCornerTopRightDark: ImageVector
    get() {
        if (_radialGradientCornerTopRightDark != null) {
            return _radialGradientCornerTopRightDark!!
        }
        _radialGradientCornerTopRightDark = Builder(
            name = "RadialGradientCornerTopRightDark",
            defaultWidth = 375.0.dp,
            defaultHeight = 832.0.dp,
            viewportWidth = 375.0f,
            viewportHeight = 832.0f
        ).apply {
            group {
                path(
                    fill = radialGradient(
                        0.0f to Color(0xFF67DD95), 1.0f to Color(0x0067DD95), center = Offset(375.0f, -0.0f), radius = 234.0f
                    ),
                    stroke = null,
                    fillAlpha = 0.6f,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(375.0f, 0.0f)
                    moveToRelative(-251.0f, 0.0f)
                    arcToRelative(251.0f, 251.0f, 0.0f, true, true, 502.0f, 0.0f)
                    arcToRelative(251.0f, 251.0f, 0.0f, true, true, -502.0f, 0.0f)
                }
            }
        }.build()
        return _radialGradientCornerTopRightDark!!
    }

private var _radialGradientCornerTopRightDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.wrapContentHeight()) {
        Image(
            imageVector = AppIllus.RadialGradientCornerTopRightDark,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.wrapContentSize()
        )
    }
}
