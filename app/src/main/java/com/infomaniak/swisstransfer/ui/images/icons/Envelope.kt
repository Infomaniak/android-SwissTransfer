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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Envelope: ImageVector
    get() {

        if (_envelope != null) return _envelope!!

        _envelope = Builder(
            name = "Envelope",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(1.22f, 5.695f)
                curveToRelative(-0.3f, 0.282f, -0.47f, 0.663f, -0.47f, 1.061f)
                verticalLineToRelative(12.0f)
                curveToRelative(0.0f, 0.398f, 0.17f, 0.78f, 0.47f, 1.06f)
                curveToRelative(0.302f, 0.282f, 0.71f, 0.44f, 1.137f, 0.44f)
                horizontalLineToRelative(19.286f)
                curveToRelative(0.426f, 0.0f, 0.835f, -0.158f, 1.136f, -0.44f)
                curveToRelative(0.302f, -0.28f, 0.471f, -0.662f, 0.471f, -1.06f)
                verticalLineToRelative(-12.0f)
                curveToRelative(0.0f, -0.398f, -0.17f, -0.78f, -0.47f, -1.06f)
                moveToRelative(-21.56f, 0.0f)
                curveToRelative(0.302f, -0.282f, 0.71f, -0.44f, 1.137f, -0.44f)
                horizontalLineToRelative(19.286f)
                curveToRelative(0.426f, 0.0f, 0.835f, 0.158f, 1.136f, 0.44f)
                moveToRelative(-21.558f, 0.0f)
                lineToRelative(8.618f, 6.374f)
                arcTo(3.7f, 3.7f, 0.0f, false, false, 12.0f, 12.756f)
                arcToRelative(3.7f, 3.7f, 0.0f, false, false, 2.161f, -0.686f)
                lineToRelative(8.618f, -6.375f)
            }
        }.build()

        return _envelope!!
    }

private var _envelope: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Envelope,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
