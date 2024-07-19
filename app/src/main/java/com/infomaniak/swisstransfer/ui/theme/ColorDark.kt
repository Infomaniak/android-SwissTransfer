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

import androidx.compose.ui.graphics.Color

// Palette
private const val green_dark = 0xFF014958
private const val green_main = 0xFF67DD95
private const val green_contrast = 0xFFCAF769

private const val dark1 = 0xFF152123
private const val dark2 = 0xFF2B383B
private const val dark3 = 0xFF3C4F52
private const val shark = 0xFF9F9F9F
private const val rabbit = 0xFFF1F1F1

private const val specific1 = 0xFF124426
private const val specific2 = 0xFF334117
private const val specific3 = 0xFF503E0F
private const val specific4 = 0xFFEAC35D

// Roles
val dark_primary = Color(green_main)
val dark_onPrimary = Color(dark1)
val dark_secondaryContainer = Color(dark3)
val dark_onSecondaryContainer = Color(green_main)
val dark_tertiary = Color(green_dark)
val dark_onTertiary = Color(green_main)

val dark_background = Color(dark1)
val dark_surface = dark_background
val dark_onSurface = Color(green_main)
val dark_onSurfaceVariant = Color(rabbit)
val dark_onSurfaceContainerHighest = Color(dark2)

val DarkColors = CustomColors(
    primaryTextColor = Color(rabbit),
    secondaryTextColor = Color(shark),
    navBarBackground = Color(dark2),
    divider = Color(dark3),
)
