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
package com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

val AppIllus.MascotWithMagnifyingGlass: ThemedImage
    get() = _mascotWithMagnifyingGlass ?: object : ThemedImage {
        override val light = AppIllus.MascotWithMagnifyingGlassLight
        override val dark = AppIllus.MascotWithMagnifyingGlassDark
    }.also { _mascotWithMagnifyingGlass = it }

private var _mascotWithMagnifyingGlass: ThemedImage? = null

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Box {
            Image(
                imageVector = AppIllus.MascotWithMagnifyingGlass.image(),
                contentDescription = null,
                modifier = Modifier.size(AppImages.previewSize),
            )
        }
    }
}
