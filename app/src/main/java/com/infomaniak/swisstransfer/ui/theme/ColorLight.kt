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

package com.infomaniak.swisstransfer.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// Palette
private const val green_dark = 0xFF014958
private const val green_main = 0xFF3CB572
private const val green_secondary = 0xFFE3F6DC
private const val green_contrast = 0xFFCAF769

private const val orca = 0xFF333333
private const val elephant = 0xFF666666
private const val shark = 0xFF9F9F9F
private const val mouse = 0xFFE0E0E0
private const val polar_bear = 0xFFF5F5F5

private const val specific1 = 0xFFD8F0E3
private const val specific2 = 0xFFCCDBDE
private const val specific3 = 0xFFF5ECD1
private const val specific4 = 0xFFCF9E1B

// Extra palette
private const val on_primary = 0xFFF7FCFA
private const val white = 0xFFFFFFFF

// Roles
val light_primary = Color(green_main)
val light_onPrimary = Color(on_primary)
val light_secondaryContainer = Color(green_secondary)
val light_onSecondaryContainer = Color(green_main)
val light_tertiary = Color(green_dark)
val light_onTertiary = Color(green_contrast)

val light_background = Color(white)
val light_surface = light_background
val light_onSurface = Color(green_main)
val light_onSurfaceVariant = Color(green_dark)
val light_onSurfaceContainerHighest = Color(polar_bear)

val LightColors = CustomColors(
    primaryTextColor = Color(orca),
    secondaryTextColor = Color(elephant),
    navigationItemBackground = light_background,
    divider = Color(mouse),
)

@Immutable
data class CustomColors(
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val navigationItemBackground: Color,
    val divider: Color,
)
