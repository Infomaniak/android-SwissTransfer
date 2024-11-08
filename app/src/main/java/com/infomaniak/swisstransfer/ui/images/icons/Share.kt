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

val AppIcons.Share: ImageVector
    get() {

        if (_share != null) return _share!!

        _share = Builder(
            name = "Share",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(1.5f, 11.25f)
                curveTo(1.5f, 12.245f, 1.895f, 13.198f, 2.598f, 13.902f)
                curveTo(3.302f, 14.605f, 4.255f, 15.0f, 5.25f, 15.0f)
                curveTo(6.245f, 15.0f, 7.198f, 14.605f, 7.902f, 13.902f)
                curveTo(8.605f, 13.198f, 9.0f, 12.245f, 9.0f, 11.25f)
                curveTo(9.0f, 10.255f, 8.605f, 9.302f, 7.902f, 8.598f)
                curveTo(7.198f, 7.895f, 6.245f, 7.5f, 5.25f, 7.5f)
                curveTo(4.255f, 7.5f, 3.302f, 7.895f, 2.598f, 8.598f)
                curveTo(1.895f, 9.302f, 1.5f, 10.255f, 1.5f, 11.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(15.0f, 6.0f)
                curveTo(15.0f, 6.995f, 15.395f, 7.948f, 16.098f, 8.652f)
                curveTo(16.802f, 9.355f, 17.755f, 9.75f, 18.75f, 9.75f)
                curveTo(19.745f, 9.75f, 20.698f, 9.355f, 21.402f, 8.652f)
                curveTo(22.105f, 7.948f, 22.5f, 6.995f, 22.5f, 6.0f)
                curveTo(22.5f, 5.005f, 22.105f, 4.052f, 21.402f, 3.348f)
                curveTo(20.698f, 2.645f, 19.745f, 2.25f, 18.75f, 2.25f)
                curveTo(17.755f, 2.25f, 16.802f, 2.645f, 16.098f, 3.348f)
                curveTo(15.395f, 4.052f, 15.0f, 5.005f, 15.0f, 6.0f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(15.0f, 18.0f)
                curveTo(15.0f, 18.995f, 15.395f, 19.948f, 16.098f, 20.652f)
                curveTo(16.802f, 21.355f, 17.755f, 21.75f, 18.75f, 21.75f)
                curveTo(19.745f, 21.75f, 20.698f, 21.355f, 21.402f, 20.652f)
                curveTo(22.105f, 19.948f, 22.5f, 18.995f, 22.5f, 18.0f)
                curveTo(22.5f, 17.005f, 22.105f, 16.052f, 21.402f, 15.348f)
                curveTo(20.698f, 14.645f, 19.745f, 14.25f, 18.75f, 14.25f)
                curveTo(17.755f, 14.25f, 16.802f, 14.645f, 16.098f, 15.348f)
                curveTo(15.395f, 16.052f, 15.0f, 17.005f, 15.0f, 18.0f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(8.746f, 9.89f)
                lineTo(15.254f, 7.359f)
            }
            path(
                stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(8.605f, 12.928f)
                lineTo(15.396f, 16.323f)
            }
        }.build()

        return _share!!
    }

private var _share: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Share,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
