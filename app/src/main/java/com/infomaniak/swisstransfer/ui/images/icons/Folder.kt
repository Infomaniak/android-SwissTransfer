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
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Folder: ImageVector
    get() {

        if (_folder != null) return _folder!!

        _folder = Builder(
            name = "Folder",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(23.25f, 6.75f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, -1.5f)
                horizontalLineTo(10.5f)
                lineToRelative(-1.8f, -2.4f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.2f, -0.6f)
                horizontalLineTo(2.25f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.5f, 1.5f)
                verticalLineToRelative(16.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, 1.5f, 1.5f)
                horizontalLineToRelative(19.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, false, 1.5f, -1.5f)
                close()
            }
        }.build()

        return _folder!!
    }

private var _folder: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Folder,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
