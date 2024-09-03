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
package com.infomaniak.swisstransfer.ui.icons.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(
            name = "Settings",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF9f9f9f)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(20.17f, 0.0f)
                    curveToRelative(0.423f, 0.0f, 0.766f, 0.343f, 0.766f, 0.766f)
                    verticalLineToRelative(3.396f)
                    arcTo(3.83f, 3.83f, 0.0f, false, true, 24.0f, 7.915f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, -3.064f, 3.752f)
                    verticalLineToRelative(11.567f)
                    arcToRelative(0.766f, 0.766f, 0.0f, false, true, -1.532f, 0.0f)
                    verticalLineTo(11.667f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, -2.99f, -4.5f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, 2.99f, -3.005f)
                    verticalLineTo(0.766f)
                    curveTo(19.404f, 0.343f, 19.747f, 0.0f, 20.17f, 0.0f)
                    moveToRelative(-1.625f, 6.29f)
                    arcToRelative(2.299f, 2.299f, 0.0f, true, true, 3.251f, 3.251f)
                    arcToRelative(2.299f, 2.299f, 0.0f, false, true, -3.25f, -3.25f)
                    moveTo(3.83f, 0.0f)
                    curveToRelative(0.423f, 0.0f, 0.766f, 0.343f, 0.766f, 0.766f)
                    verticalLineToRelative(3.396f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, 0.0f, 7.505f)
                    verticalLineToRelative(11.567f)
                    arcToRelative(0.766f, 0.766f, 0.0f, false, true, -1.532f, 0.0f)
                    verticalLineTo(11.667f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, 0.0f, -7.505f)
                    verticalLineTo(0.766f)
                    curveTo(3.064f, 0.343f, 3.407f, 0.0f, 3.83f, 0.0f)
                    moveTo(2.205f, 6.29f)
                    arcToRelative(2.298f, 2.298f, 0.0f, true, true, 3.25f, 3.25f)
                    arcToRelative(2.298f, 2.298f, 0.0f, false, true, -3.25f, -3.25f)
                    moveTo(12.0f, 0.0f)
                    curveToRelative(0.423f, 0.0f, 0.766f, 0.343f, 0.766f, 0.766f)
                    verticalLineToRelative(11.636f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, 0.0f, 7.505f)
                    verticalLineToRelative(3.327f)
                    arcToRelative(0.766f, 0.766f, 0.0f, false, true, -1.532f, 0.0f)
                    verticalLineToRelative(-3.327f)
                    arcToRelative(3.83f, 3.83f, 0.0f, false, true, 0.0f, -7.505f)
                    verticalLineTo(0.766f)
                    curveTo(11.234f, 0.343f, 11.577f, 0.0f, 12.0f, 0.0f)
                    moveToRelative(-1.277f, 14.244f)
                    arcToRelative(2.3f, 2.3f, 0.0f, false, true, 2.043f, -0.256f)
                    arcToRelative(2.296f, 2.296f, 0.0f, false, true, 0.0f, 4.333f)
                    arcToRelative(2.298f, 2.298f, 0.0f, false, true, -2.043f, -4.077f)
                }
            }
        }.build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Settings,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
