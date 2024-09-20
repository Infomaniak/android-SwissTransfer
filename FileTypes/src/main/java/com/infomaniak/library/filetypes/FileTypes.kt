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
package com.infomaniak.library.filetypes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.library.filetypes.icons.Pdf
import com.infomaniak.library.filetypes.icons.Text

enum class FileTypes(val icon: ImageVector? = null, val color: @Composable (FileTypeColors) -> Color) {
    PDF(icon = FileTypeIcons.Pdf, color = { it.pdf }),
    TEXT(icon = FileTypeIcons.Text, color = { it.text }),
}

@Immutable
data class FileTypeColors(
    val pdf: Color,
    val text: Color,
)

val LightFileTypeColors = FileTypeColors(
    pdf = Color(0xFFEF5803),
    text = Color(0xFF2196F3),
)

val DarkFileTypeColors = FileTypeColors(
    pdf = Color(0xFFFD9459),
    text = Color(0xFF81C4F8),
)
