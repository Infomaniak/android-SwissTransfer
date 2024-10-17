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
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.QrInfomaniakDark: ImageVector
    get() {

        if (_qrInfomaniakDark != null) return _qrInfomaniakDark!!

        _qrInfomaniakDark = Builder(
            name = "QrInfomaniakDark",
            defaultWidth = 42.0.dp,
            defaultHeight = 45.0.dp,
            viewportWidth = 42.0f,
            viewportHeight = 45.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFF1F1F1)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(37.12f, 1.0f)
                horizontalLineTo(4.42f)
                curveTo(2.163f, 1.0f, 0.333f, 2.873f, 0.333f, 5.294f)
                verticalLineTo(40.353f)
                curveTo(0.333f, 42.773f, 2.163f, 44.736f, 4.42f, 44.736f)
                horizontalLineTo(37.12f)
                curveTo(39.377f, 44.736f, 41.208f, 42.773f, 41.208f, 40.353f)
                verticalLineTo(5.294f)
                curveTo(41.208f, 2.873f, 39.377f, 1.0f, 37.12f, 1.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd,
            ) {
                moveTo(9.053f, 37.548f)
                horizontalLineTo(17.555f)
                verticalLineTo(31.588f)
                lineTo(20.658f, 28.38f)
                lineTo(25.076f, 37.548f)
                horizontalLineTo(34.477f)
                lineTo(26.186f, 22.704f)
                lineTo(33.986f, 14.759f)
                horizontalLineTo(23.768f)
                lineTo(17.555f, 22.297f)
                verticalLineTo(0.8f)
                horizontalLineTo(9.053f)
                verticalLineTo(37.548f)
                close()
            }
        }.build()

        return _qrInfomaniakDark!!
    }

private var _qrInfomaniakDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.QrInfomaniakDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
