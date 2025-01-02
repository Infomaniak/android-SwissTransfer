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
package com.infomaniak.swisstransfer.ui.images.icons.qrInfomaniak

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.QrInfomaniakLight: ImageVector
    get() {

        if (_qrInfomaniakLight != null) return _qrInfomaniakLight!!

        _qrInfomaniakLight = Builder(
            name = "QrInfomaniakLight",
            defaultWidth = 41.0.dp,
            defaultHeight = 44.0.dp,
            viewportWidth = 41.0f,
            viewportHeight = 44.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)),
                pathFillType = NonZero,
            ) {
                moveTo(36.9f, 0.0f)
                horizontalLineTo(4.1f)
                curveTo(1.835f, 0.0f, -0.0f, 1.97f, -0.0f, 4.4f)
                verticalLineTo(39.6f)
                curveTo(-0.0f, 42.03f, 1.835f, 44.0f, 4.1f, 44.0f)
                horizontalLineTo(36.9f)
                curveTo(39.164f, 44.0f, 41.0f, 42.03f, 41.0f, 39.6f)
                verticalLineTo(4.4f)
                curveTo(41.0f, 1.97f, 39.164f, 0.0f, 36.9f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = EvenOdd,
            ) {
                moveTo(8.552f, 36.637f)
                horizontalLineTo(17.054f)
                verticalLineTo(30.677f)
                lineTo(20.158f, 27.469f)
                lineTo(24.575f, 36.637f)
                horizontalLineTo(33.976f)
                lineTo(25.685f, 21.793f)
                lineTo(33.486f, 13.848f)
                horizontalLineTo(23.267f)
                lineTo(17.054f, 21.386f)
                verticalLineTo(0.0f)
                horizontalLineTo(8.552f)
                verticalLineTo(36.637f)
                close()
            }
        }.build()

        return _qrInfomaniakLight!!
    }

private var _qrInfomaniakLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.QrInfomaniakLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
