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

package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.Illu.ArrowCurvedDownright: ImageVector
    get() {
        if (_arrowcurveddownright != null) {
            return _arrowcurveddownright!!
        }
        _arrowcurveddownright = Builder(
            name = "Arrowcurveddownright",
            defaultWidth = 37.0.dp,
            defaultHeight = 45.0.dp,
            viewportWidth = 37.0f,
            viewportHeight = 45.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(34.0f, 44.81f)
                reflectiveCurveToRelative(1.352f, -0.094f, 2.001f, -0.31f)
                curveToRelative(0.65f, -0.216f, 0.899f, -1.524f, 0.59f, -2.066f)
                lineToRelative(-6.049f, -10.615f)
                curveToRelative(-0.872f, -1.526f, -1.961f, -0.176f, -1.376f, 1.095f)
                quadToRelative(1.57f, 3.411f, 3.14f, 6.83f)
                curveTo(16.984f, 31.213f, 2.658f, 20.65f, 1.744f, 1.334f)
                curveTo(1.68f, -0.062f, -0.08f, -0.68f, 0.005f, 1.075f)
                curveToRelative(0.967f, 20.496f, 15.83f, 32.004f, 32.19f, 41.158f)
                horizontalLineToRelative(-9.45f)
                curveToRelative(-1.26f, 0.0f, -0.824f, 2.583f, 0.233f, 2.583f)
                curveToRelative(4.04f, 0.0f, 6.982f, -0.005f, 11.022f, -0.005f)
            }
        }.build()
        return _arrowcurveddownright!!
    }

private var _arrowcurveddownright: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(
            imageVector = AppIcons.Illu.ArrowCurvedDownright,
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
    }
}
