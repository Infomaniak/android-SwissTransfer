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

val AppIcons.ArrowDownFile: ImageVector
    get() {

        if (_arrowDownFile != null) return _arrowDownFile!!

        _arrowDownFile = Builder(
            name = "ArrowDownFile",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(11.25f, 17.25f)
                    arcToRelative(6.0f, 6.0f, 0.0f, true, false, 12.0f, 0.0f)
                    arcToRelative(6.0f, 6.0f, 0.0f, false, false, -12.0f, 0.0f)
                    moveToRelative(6.0f, -3.0f)
                    verticalLineToRelative(6.0f)
                    moveToRelative(0.0f, 0.0f)
                    lineTo(15.0f, 18.0f)
                    moveToRelative(2.25f, 2.25f)
                    lineTo(19.5f, 18.0f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9f9f9f)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(8.25f, 20.25f)
                    horizontalLineToRelative(-6.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineTo(2.25f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(10.629f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.06f, 0.439f)
                    lineToRelative(2.872f, 2.872f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 0.439f, 1.06f)
                    verticalLineTo(8.25f)
                }
            }
        }.build()

        return _arrowDownFile!!
    }

private var _arrowDownFile: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.ArrowDownFile,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
