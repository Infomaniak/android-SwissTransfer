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

val AppIcons.SpeechBubble: ImageVector
    get() {

        if (_speechBubble != null) return _speechBubble!!

        _speechBubble = Builder(
            name = "SpeechBubble",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(21.75f, 18.75f)
                    horizontalLineToRelative(-10.5f)
                    lineToRelative(-6.0f, 4.5f)
                    verticalLineToRelative(-4.5f)
                    horizontalLineToRelative(-3.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineToRelative(-15.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(19.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, 1.5f)
                    verticalLineToRelative(15.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, 1.5f)
                    moveTo(5.25f, 7.5f)
                    horizontalLineToRelative(13.5f)
                    moveTo(5.25f, 12.0f)
                    horizontalLineToRelative(10.5f)
                }
            }
        }.build()

        return _speechBubble!!
    }

private var _speechBubble: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.SpeechBubble,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
