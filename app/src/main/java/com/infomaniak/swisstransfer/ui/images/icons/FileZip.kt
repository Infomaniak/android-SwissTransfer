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

val AppIcons.FileZip: ImageVector
    get() {

        if (_fileZip != null) return _fileZip!!

        _fileZip = Builder(
            name = "FileZip",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 16.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF9f9f9f)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.24f, 6.127f)
                curveTo(6.953f, 5.933f, 6.54f, 5.973f, 6.293f, 6.22f)
                curveTo(6.153f, 6.36f, 6.073f, 6.553f, 6.073f, 6.753f)
                curveTo(6.073f, 6.907f, 6.12f, 7.053f, 6.2f, 7.167f)
                curveTo(6.28f, 7.287f, 6.393f, 7.387f, 6.533f, 7.447f)
                curveTo(6.627f, 7.487f, 6.72f, 7.5f, 6.82f, 7.5f)
                curveTo(6.873f, 7.5f, 6.92f, 7.493f, 6.973f, 7.487f)
                curveTo(7.12f, 7.46f, 7.253f, 7.387f, 7.353f, 7.287f)
                curveTo(7.46f, 7.18f, 7.533f, 7.047f, 7.567f, 6.893f)
                curveTo(7.593f, 6.747f, 7.58f, 6.6f, 7.52f, 6.467f)
                curveTo(7.467f, 6.327f, 7.367f, 6.213f, 7.24f, 6.127f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF9f9f9f)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.24f, 9.127f)
                curveTo(6.953f, 8.933f, 6.54f, 8.973f, 6.293f, 9.22f)
                curveTo(6.153f, 9.36f, 6.073f, 9.553f, 6.073f, 9.753f)
                curveTo(6.073f, 9.907f, 6.12f, 10.053f, 6.2f, 10.167f)
                curveTo(6.28f, 10.287f, 6.393f, 10.387f, 6.533f, 10.447f)
                curveTo(6.627f, 10.487f, 6.72f, 10.5f, 6.82f, 10.5f)
                curveTo(6.873f, 10.5f, 6.92f, 10.493f, 6.973f, 10.487f)
                curveTo(7.12f, 10.46f, 7.253f, 10.387f, 7.353f, 10.287f)
                curveTo(7.46f, 10.18f, 7.533f, 10.047f, 7.567f, 9.893f)
                curveTo(7.593f, 9.747f, 7.58f, 9.6f, 7.52f, 9.467f)
                curveTo(7.467f, 9.327f, 7.367f, 9.213f, 7.24f, 9.127f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF9f9f9f)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.24f, 12.127f)
                curveTo(6.953f, 11.933f, 6.54f, 11.973f, 6.293f, 12.22f)
                curveTo(6.153f, 12.36f, 6.073f, 12.553f, 6.073f, 12.753f)
                curveTo(6.073f, 12.907f, 6.12f, 13.053f, 6.2f, 13.167f)
                curveTo(6.28f, 13.287f, 6.393f, 13.387f, 6.533f, 13.447f)
                curveTo(6.627f, 13.487f, 6.72f, 13.5f, 6.82f, 13.5f)
                curveTo(6.873f, 13.5f, 6.92f, 13.493f, 6.973f, 13.487f)
                curveTo(7.12f, 13.46f, 7.253f, 13.387f, 7.353f, 13.287f)
                curveTo(7.46f, 13.18f, 7.533f, 13.047f, 7.567f, 12.893f)
                curveTo(7.593f, 12.747f, 7.58f, 12.6f, 7.52f, 12.467f)
                curveTo(7.467f, 12.327f, 7.367f, 12.213f, 7.24f, 12.127f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF9f9f9f)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.887f, 2.353f)
                lineTo(12.974f, 0.44f)
                curveTo(12.687f, 0.153f, 12.314f, 0.0f, 11.913f, 0.0f)
                horizontalLineTo(2.16f)
                curveTo(1.76f, 0.0f, 1.38f, 0.153f, 1.1f, 0.44f)
                curveTo(0.813f, 0.72f, 0.66f, 1.1f, 0.66f, 1.5f)
                verticalLineTo(14.5f)
                curveTo(0.66f, 14.893f, 0.82f, 15.28f, 1.1f, 15.56f)
                curveTo(1.38f, 15.847f, 1.76f, 16.0f, 2.16f, 16.0f)
                horizontalLineTo(13.827f)
                curveTo(14.22f, 16.0f, 14.607f, 15.84f, 14.887f, 15.56f)
                curveTo(15.167f, 15.28f, 15.327f, 14.893f, 15.327f, 14.5f)
                verticalLineTo(3.413f)
                curveTo(15.327f, 3.02f, 15.167f, 2.633f, 14.887f, 2.353f)
                close()
                moveTo(7.387f, 1.0f)
                lineTo(7.647f, 3.1f)
                curveTo(7.66f, 3.213f, 7.653f, 3.327f, 7.62f, 3.433f)
                curveTo(7.587f, 3.54f, 7.533f, 3.64f, 7.46f, 3.72f)
                curveTo(7.38f, 3.807f, 7.28f, 3.88f, 7.173f, 3.927f)
                curveTo(6.953f, 4.02f, 6.7f, 4.02f, 6.48f, 3.927f)
                curveTo(6.373f, 3.88f, 6.273f, 3.807f, 6.2f, 3.727f)
                curveTo(6.127f, 3.64f, 6.073f, 3.547f, 6.04f, 3.433f)
                curveTo(6.007f, 3.327f, 5.993f, 3.213f, 6.007f, 3.1f)
                lineTo(6.267f, 1.0f)
                horizontalLineTo(7.387f)
                close()
                moveTo(14.327f, 14.5f)
                curveTo(14.327f, 14.633f, 14.274f, 14.76f, 14.18f, 14.853f)
                curveTo(14.087f, 14.947f, 13.96f, 15.0f, 13.827f, 15.0f)
                horizontalLineTo(2.16f)
                curveTo(2.027f, 15.0f, 1.9f, 14.947f, 1.807f, 14.853f)
                curveTo(1.713f, 14.76f, 1.66f, 14.633f, 1.66f, 14.5f)
                verticalLineTo(1.5f)
                curveTo(1.66f, 1.367f, 1.713f, 1.24f, 1.807f, 1.147f)
                curveTo(1.9f, 1.053f, 2.027f, 1.0f, 2.16f, 1.0f)
                horizontalLineTo(5.26f)
                lineTo(5.013f, 2.973f)
                curveTo(4.987f, 3.227f, 5.007f, 3.487f, 5.08f, 3.733f)
                curveTo(5.16f, 3.98f, 5.28f, 4.2f, 5.46f, 4.4f)
                curveTo(5.633f, 4.587f, 5.847f, 4.74f, 6.08f, 4.847f)
                curveTo(6.32f, 4.947f, 6.567f, 5.0f, 6.827f, 5.0f)
                curveTo(7.087f, 5.0f, 7.333f, 4.947f, 7.573f, 4.84f)
                curveTo(7.807f, 4.733f, 8.02f, 4.587f, 8.2f, 4.387f)
                curveTo(8.367f, 4.193f, 8.5f, 3.967f, 8.567f, 3.727f)
                curveTo(8.64f, 3.487f, 8.667f, 3.227f, 8.633f, 2.973f)
                lineTo(8.393f, 1.0f)
                horizontalLineTo(11.913f)
                curveTo(12.047f, 1.0f, 12.174f, 1.053f, 12.267f, 1.147f)
                lineTo(14.18f, 3.06f)
                curveTo(14.274f, 3.153f, 14.327f, 3.28f, 14.327f, 3.413f)
                verticalLineTo(14.5f)
                close()
            }
        }.build()

        return _fileZip!!
    }

private var _fileZip: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.FileZip,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
