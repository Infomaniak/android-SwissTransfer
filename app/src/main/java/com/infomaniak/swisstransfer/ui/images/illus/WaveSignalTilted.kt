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
package com.infomaniak.swisstransfer.ui.images.illus

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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIllus.WaveSignalTilted: ImageVector
    get() {

        if (_waveSignalTilted != null) return _waveSignalTilted!!

        _waveSignalTilted = Builder(
            name = "WaveSignalTilted",
            defaultWidth = 120.0.dp,
            defaultHeight = 80.0.dp,
            viewportWidth = 120.0f,
            viewportHeight = 80.0f,
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFFCF9E1B)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(93.93f, 50.48f)
                arcToRelative(32.97f, 32.97f, 0.0f, false, false, -40.38f, 23.31f)
                moveToRelative(46.19f, -42.81f)
                arcToRelative(52.75f, 52.75f, 0.0f, false, false, -64.6f, 37.3f)
                moveToRelative(66.36f, -56.51f)
                arcToRelative(72.53f, 72.53f, 0.0f, false, false, -85.65f, 49.45f)
            }
        }.build()

        return _waveSignalTilted!!
    }

private var _waveSignalTilted: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.WaveSignalTilted,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
