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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.AppImages.previewSize

val AppIllus.ArrowRightCurved: ImageVector
    get() {

        if (_arrowRightCurved != null) return _arrowRightCurved!!

        _arrowRightCurved = Builder(
            name = "Vector",
            defaultWidth = 41.0.dp,
            defaultHeight = 22.0.dp,
            viewportWidth = 41.0f,
            viewportHeight = 22.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(39.104f, 17.308f)
                curveTo(39.104f, 17.308f, 39.952f, 16.737f, 40.295f, 16.351f)
                curveTo(40.638f, 15.965f, 40.307f, 15.016f, 39.901f, 14.778f)
                lineTo(31.943f, 10.124f)
                curveTo(30.798f, 9.456f, 30.595f, 10.749f, 31.457f, 11.359f)
                curveTo(32.999f, 12.45f, 34.542f, 13.542f, 36.086f, 14.637f)
                curveTo(22.854f, 14.844f, 9.508f, 13.346f, 1.624f, 1.069f)
                curveTo(1.054f, 0.179f, -0.328f, 0.44f, 0.389f, 1.554f)
                curveTo(8.754f, 14.583f, 22.808f, 16.496f, 36.952f, 16.305f)
                lineTo(30.776f, 19.87f)
                curveTo(29.953f, 20.345f, 31.212f, 21.869f, 31.904f, 21.47f)
                curveTo(34.543f, 19.946f, 36.464f, 18.832f, 39.104f, 17.308f)
                close()
            }
        }.build()

        return _arrowRightCurved!!
    }

private var _arrowRightCurved: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(
            imageVector = AppIllus.ArrowRightCurved,
            contentDescription = null,
            modifier = Modifier.size(previewSize),
        )
    }
}
