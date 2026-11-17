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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Leaf: ImageVector
    get() {
        if (_leaf != null) return _leaf!!

        _leaf = ImageVector.Builder(
            name = "Leaf",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 18f,
            viewportHeight = 18f,
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF15864E)),
                strokeLineWidth = 1.25f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(0.625f, 16.4583f)
                curveTo(0.625f, 13.9583f, 2.16667f, 11.9917f, 4.85833f, 11.4583f)
                curveTo(6.875f, 11.0583f, 8.95833f, 9.79167f, 9.79167f, 8.95833f)
                moveTo(8.125f, 15.625f)
                curveTo(6.66172f, 15.6294f, 5.25021f, 15.0838f, 4.17043f, 14.0962f)
                curveTo(3.09065f, 13.1086f, 2.42147f, 11.7513f, 2.29561f, 10.2934f)
                curveTo(2.16975f, 8.83556f, 2.59641f, 7.38363f, 3.49096f, 6.22562f)
                curveTo(4.38551f, 5.0676f, 5.68261f, 4.28808f, 7.125f, 4.04167f)
                curveTo(11.875f, 3.125f, 13.125f, 2.69167f, 14.7917f, 0.625f)
                curveTo(15.625f, 2.29167f, 16.4583f, 4.10833f, 16.4583f, 7.29167f)
                curveTo(16.4583f, 11.875f, 12.475f, 15.625f, 8.125f, 15.625f)
                close()
            }
        }.build()
        return _leaf!!
    }

private var _leaf: ImageVector? = null
