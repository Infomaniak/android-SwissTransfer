/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Organization: ImageVector
    get() {
        if (_organization != null) {
            return _organization!!
        }
        _organization = Builder(
            name = "Organization",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(12.5f, 0f)
                curveTo(12.689f, 0f, 12.863f, 0.107f, 12.947f, 0.276f)
                lineTo(13.947f, 2.276f)
                curveTo(13.982f, 2.346f, 14f, 2.422f, 14f, 2.5f)
                verticalLineTo(15.5f)
                curveTo(14f, 15.776f, 13.776f, 16f, 13.5f, 16f)
                horizontalLineTo(2.5f)
                curveTo(2.224f, 16f, 2f, 15.776f, 2f, 15.5f)
                verticalLineTo(2.5f)
                curveTo(2f, 2.422f, 2.018f, 2.346f, 2.053f, 2.276f)
                lineTo(3.053f, 0.276f)
                curveTo(3.137f, 0.107f, 3.311f, 0f, 3.5f, 0f)
                horizontalLineTo(12.5f)
                close()
                moveTo(3f, 15f)
                horizontalLineTo(6f)
                verticalLineTo(14f)
                curveTo(6f, 13.47f, 6.211f, 12.961f, 6.586f, 12.586f)
                curveTo(6.961f, 12.211f, 7.47f, 12f, 8f, 12f)
                curveTo(8.53f, 12f, 9.039f, 12.211f, 9.414f, 12.586f)
                curveTo(9.789f, 12.961f, 10f, 13.47f, 10f, 14f)
                verticalLineTo(15f)
                horizontalLineTo(13f)
                verticalLineTo(3f)
                horizontalLineTo(3f)
                verticalLineTo(15f)
                close()
                moveTo(8f, 13f)
                curveTo(7.735f, 13f, 7.481f, 13.106f, 7.293f, 13.293f)
                curveTo(7.105f, 13.481f, 7f, 13.735f, 7f, 14f)
                verticalLineTo(15f)
                horizontalLineTo(9f)
                verticalLineTo(14f)
                curveTo(9f, 13.735f, 8.895f, 13.481f, 8.707f, 13.293f)
                curveTo(8.519f, 13.106f, 8.265f, 13f, 8f, 13f)
                close()
                moveTo(4.996f, 9.75f)
                curveTo(5.41f, 9.75f, 5.746f, 10.086f, 5.746f, 10.5f)
                curveTo(5.746f, 10.914f, 5.41f, 11.25f, 4.996f, 11.25f)
                curveTo(4.582f, 11.25f, 4.246f, 10.914f, 4.246f, 10.5f)
                curveTo(4.246f, 10.086f, 4.582f, 9.75f, 4.996f, 9.75f)
                close()
                moveTo(8f, 9.75f)
                curveTo(8.414f, 9.75f, 8.75f, 10.086f, 8.75f, 10.5f)
                curveTo(8.75f, 10.914f, 8.414f, 11.25f, 8f, 11.25f)
                curveTo(7.586f, 11.25f, 7.25f, 10.914f, 7.25f, 10.5f)
                curveTo(7.25f, 10.086f, 7.586f, 9.75f, 8f, 9.75f)
                close()
                moveTo(10.996f, 9.75f)
                curveTo(11.41f, 9.75f, 11.746f, 10.086f, 11.746f, 10.5f)
                curveTo(11.746f, 10.914f, 11.41f, 11.25f, 10.996f, 11.25f)
                curveTo(10.582f, 11.25f, 10.246f, 10.914f, 10.246f, 10.5f)
                curveTo(10.246f, 10.086f, 10.582f, 9.75f, 10.996f, 9.75f)
                close()
                moveTo(4.996f, 6.75f)
                curveTo(5.41f, 6.75f, 5.746f, 7.086f, 5.746f, 7.5f)
                curveTo(5.746f, 7.914f, 5.41f, 8.25f, 4.996f, 8.25f)
                curveTo(4.582f, 8.25f, 4.246f, 7.914f, 4.246f, 7.5f)
                curveTo(4.246f, 7.086f, 4.582f, 6.75f, 4.996f, 6.75f)
                close()
                moveTo(8f, 6.75f)
                curveTo(8.414f, 6.75f, 8.75f, 7.086f, 8.75f, 7.5f)
                curveTo(8.75f, 7.914f, 8.414f, 8.25f, 8f, 8.25f)
                curveTo(7.586f, 8.25f, 7.25f, 7.914f, 7.25f, 7.5f)
                curveTo(7.25f, 7.086f, 7.586f, 6.75f, 8f, 6.75f)
                close()
                moveTo(10.996f, 6.75f)
                curveTo(11.41f, 6.75f, 11.746f, 7.086f, 11.746f, 7.5f)
                curveTo(11.746f, 7.914f, 11.41f, 8.25f, 10.996f, 8.25f)
                curveTo(10.582f, 8.25f, 10.246f, 7.914f, 10.246f, 7.5f)
                curveTo(10.246f, 7.086f, 10.582f, 6.75f, 10.996f, 6.75f)
                close()
                moveTo(4.996f, 3.75f)
                curveTo(5.41f, 3.75f, 5.746f, 4.086f, 5.746f, 4.5f)
                curveTo(5.746f, 4.914f, 5.41f, 5.25f, 4.996f, 5.25f)
                curveTo(4.582f, 5.25f, 4.246f, 4.914f, 4.246f, 4.5f)
                curveTo(4.246f, 4.086f, 4.582f, 3.75f, 4.996f, 3.75f)
                close()
                moveTo(8f, 3.75f)
                curveTo(8.414f, 3.75f, 8.75f, 4.086f, 8.75f, 4.5f)
                curveTo(8.75f, 4.914f, 8.414f, 5.25f, 8f, 5.25f)
                curveTo(7.586f, 5.25f, 7.25f, 4.914f, 7.25f, 4.5f)
                curveTo(7.25f, 4.086f, 7.586f, 3.75f, 8f, 3.75f)
                close()
                moveTo(10.996f, 3.75f)
                curveTo(11.41f, 3.75f, 11.746f, 4.086f, 11.746f, 4.5f)
                curveTo(11.746f, 4.914f, 11.41f, 5.25f, 10.996f, 5.25f)
                curveTo(10.582f, 5.25f, 10.246f, 4.914f, 10.246f, 4.5f)
                curveTo(10.246f, 4.086f, 10.582f, 3.75f, 10.996f, 3.75f)
                close()
                moveTo(3.309f, 2f)
                horizontalLineTo(12.691f)
                lineTo(12.191f, 1f)
                horizontalLineTo(3.809f)
                lineTo(3.309f, 2f)
                close()
            }
        }
            .build()
        return _organization!!
    }

private var _organization: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Organization,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
