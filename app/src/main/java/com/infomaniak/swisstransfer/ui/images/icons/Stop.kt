/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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

val AppIcons.Stop: ImageVector
    get() {

        if (_stop != null) return _stop!!

        val viewPortSize = 16.0f
        _stop = Builder(
            name = "Stop",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = viewPortSize,
            viewportHeight = viewPortSize,
        ).apply {

            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                stroke = null,
                pathFillType = NonZero,
            ) {
                val size = 12f
                val edge = (viewPortSize - size) / 2f
                moveTo(x = edge, y = edge)
                horizontalLineToRelative(size)
                verticalLineToRelative(size)
                horizontalLineToRelative(-size)
                verticalLineToRelative(-size)
            }
        }.build()

        return _stop!!
    }

private var _stop: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Stop,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
