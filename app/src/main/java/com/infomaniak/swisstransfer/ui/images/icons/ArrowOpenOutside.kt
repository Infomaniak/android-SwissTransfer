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

val AppIcons.ArrowOpenOutside: ImageVector
    get() {

        if (_arrowOpenOutside != null) return _arrowOpenOutside!!

        _arrowOpenOutside = Builder(
            name = "ArrowOpenOutside",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 2.0f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(15.0f, 5.2f)
                    verticalLineTo(1.0f)
                    horizontalLineToRelative(-4.2f)
                    moveTo(15.0f, 1.0f)
                    lineToRelative(-9.333f, 9.333f)
                    moveTo(7.533f, 3.8f)
                    horizontalLineToRelative(-5.6f)
                    arcTo(0.933f, 0.933f, 0.0f, false, false, 1.0f, 4.733f)
                    verticalLineToRelative(9.334f)
                    arcTo(0.933f, 0.933f, 0.0f, false, false, 1.933f, 15.0f)
                    horizontalLineToRelative(9.334f)
                    arcToRelative(0.933f, 0.933f, 0.0f, false, false, 0.933f, -0.933f)
                    verticalLineToRelative(-5.6f)
                }
            }
        }.build()

        return _arrowOpenOutside!!
    }

private var _arrowOpenOutside: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.ArrowOpenOutside,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
