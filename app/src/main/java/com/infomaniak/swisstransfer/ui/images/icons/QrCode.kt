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
package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.QrCode: ImageVector
    get() {

        if (_qrCode != null) return _qrCode!!

        _qrCode = Builder(
            name = "QrCode",
            defaultWidth = 24.0.dp,
            defaultHeight = 25.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 25.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(5.25f, 6.033f)
                    horizontalLineToRelative(3.0f)
                    verticalLineToRelative(3.0f)
                    horizontalLineToRelative(-3.0f)
                    close()
                    moveToRelative(0.0f, 10.5f)
                    horizontalLineToRelative(3.0f)
                    verticalLineToRelative(3.0f)
                    horizontalLineToRelative(-3.0f)
                    close()
                    moveToRelative(10.5f, -10.5f)
                    horizontalLineToRelative(3.0f)
                    verticalLineToRelative(3.0f)
                    horizontalLineToRelative(-3.0f)
                    close()
                    moveToRelative(-10.5f, 7.5f)
                    horizontalLineToRelative(6.0f)
                    verticalLineToRelative(1.5f)
                    moveToRelative(3.0f, 0.0f)
                    verticalLineToRelative(4.5f)
                    horizontalLineToRelative(4.5f)
                    verticalLineToRelative(-4.5f)
                    horizontalLineToRelative(-1.5f)
                    moveToRelative(-6.0f, 3.0f)
                    verticalLineToRelative(1.5f)
                    moveToRelative(0.0f, -13.5f)
                    verticalLineToRelative(4.5f)
                    horizontalLineToRelative(1.5f)
                    moveToRelative(3.0f, 1.5f)
                    horizontalLineToRelative(3.0f)
                    moveToRelative(-18.0f, -5.25f)
                    verticalLineToRelative(-3.75f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineTo(6.0f)
                    moveToRelative(12.0f, 0.0f)
                    horizontalLineToRelative(3.75f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, 1.5f)
                    verticalLineToRelative(3.75f)
                    moveToRelative(0.0f, 12.0f)
                    verticalLineToRelative(3.75f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, 1.5f)
                    horizontalLineTo(18.0f)
                    moveToRelative(-12.0f, 0.0f)
                    horizontalLineTo(2.25f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineToRelative(-3.75f)
                }
            }
        }.build()

        return _qrCode!!
    }

private var _qrCode: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.QrCode,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
