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
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.CircleBlackAndWhite: ImageVector
    get() {

        if (_circleBlackAndWhite != null) return _circleBlackAndWhite!!

        _circleBlackAndWhite = Builder(
            name = "CircleBlackAndWhite",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = NonZero,
            ) {
                moveTo(12.0f, 0.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, true, 12.0f, 12.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, true, -12.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 0.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 0.0f)
                close()
            }
            group(clipPathData = PathData {
                moveTo(12.0f, 0.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, true, 12.0f, 12.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, true, -12.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 0.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 0.0f)
                close()
            }) {
                path(
                    fill = SolidColor(Color(0xFFF5F5F5)),
                    pathFillType = NonZero,
                ) {
                    moveTo(-4.5f, 27.5f)
                    lineTo(27.0f, -2.5f)
                    lineToRelative(-30.5f, -1.0f)
                    close()
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 0.3f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 0.15f)
                    arcTo(11.85f, 11.85f, 0.0f, false, true, 23.85f, 12.0f)
                    arcTo(11.85f, 11.85f, 0.0f, false, true, 12.0f, 23.85f)
                    arcTo(11.85f, 11.85f, 0.0f, false, true, 0.15f, 12.0f)
                    arcTo(11.85f, 11.85f, 0.0f, false, true, 12.0f, 0.15f)
                    close()
                }
            }
        }.build()

        return _circleBlackAndWhite!!
    }

private var _circleBlackAndWhite: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.CircleBlackAndWhite,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
