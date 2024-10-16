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

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// Palette
private const val green_dark = 0xFF014958
private const val green_main = 0xFF67DD95
private const val green_contrast = 0xFFCAF769

private const val dark1 = 0xFF152123
private const val dark2 = 0xFF2B383B
private const val dark3 = 0xFF3C4F52
private const val shark = 0xFF9F9F9F
private const val polar_bear = 0xFFF5F5F5
private const val rabbit = 0xFFF1F1F1

private const val specific1 = 0xFF124426
private const val specific2 = 0xFF334117
private const val specific3 = 0xFF503E0F
private const val specific4 = 0xFFEAC35D
private const val specific5 = 0xFF49DEFD

// Extra palette
private const val elephant = 0xFF666666
private const val white = 0xFFFFFFFF
private const val black_translucent = 0x80000000

private const val error = 0xFFFC8878

val DarkColorScheme = darkColorScheme(
    primary = Color(green_main),
    onPrimary = Color(dark1),
    secondaryContainer = Color(dark3),
    onSecondaryContainer = Color(green_main),
    tertiary = Color(green_dark),
    onTertiary = Color(green_main),

    background = Color(dark1),
    surface = Color(dark1), // Same value as background
    onSurface = Color(green_main),
    onSurfaceVariant = Color(rabbit),
    surfaceContainerLow = Color(dark2), // Used for bottom sheet backgrounds
    surfaceContainerHighest = Color(dark2),

    outlineVariant = Color(dark3), // Used for divider's color

    error = Color(error),
    // onError: uses default  values
)

val CustomDarkColorScheme = CustomColorScheme(
    primaryTextColor = Color(rabbit),
    secondaryTextColor = Color(shark),
    tertiaryTextColor = Color(elephant),
    toolbarTextColor = Color(white),
    toolbarIconColor = Color(green_contrast),
    iconColor = Color(shark),
    navigationItemBackground = Color(dark2),
    tertiaryButtonBackground = Color(dark2),
    selectedSettingItem = Color(dark2),
    fileItemRemoveButtonBackground = Color(black_translucent),
    transferTypeLinkContainer = Color(specific1),
    transferTypeLinkOnContainer = Color(green_main),
    transferTypeEmailContainer = Color(green_dark),
    transferTypeEmailOnContainer = Color(specific5),
    transferTypeQrContainer = Color(specific2),
    transferTypeQrOnContainer = Color(green_main),
    transferTypeProximityContainer = Color(specific3),
    transferTypeProximityOnContainer = Color(specific4),
    emailAddressChipColor = Color(green_dark),
    onEmailAddressChipColor = Color(green_main),
    qrCodeBackground = Color(polar_bear),
    qrCodeColor = Color(green_dark),
)
