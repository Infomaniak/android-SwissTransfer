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
package com.infomaniak.swisstransfer.ui.images.illus.screenshotBottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.core.ui.compose.theme.ThemedImage
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

val AppIllus.Lightbulb: ThemedImage
    get() = _lightbulb ?: object : ThemedImage {
        override val light = AppIllus.LightbulbLight
        override val dark = AppIllus.LightbulbDark
    }.also { _lightbulb = it }

private var _lightbulb: ThemedImage? = null

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Box {
                Image(
                    imageVector = AppIllus.Lightbulb.image(),
                    contentDescription = null,
                    modifier = Modifier.size(AppImages.previewSize),
                )
            }
        }
    }
}
