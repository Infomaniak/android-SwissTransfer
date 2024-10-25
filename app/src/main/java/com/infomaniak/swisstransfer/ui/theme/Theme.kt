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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val LocalIsDarkMode = staticCompositionLocalOf { false }
val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> = staticCompositionLocalOf { CustomColorScheme() }
val LocalWindowAdaptiveInfo = staticCompositionLocalOf<WindowAdaptiveInfo> { error("No WindowAdaptiveInfo provided") }

@Composable
fun SwissTransferTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val customColors = if (isDarkTheme) CustomDarkColorScheme else CustomLightColorScheme
    CompositionLocalProvider(
        LocalTextStyle provides Typography.bodyRegular,
        LocalCustomColorScheme provides customColors,
        LocalWindowAdaptiveInfo provides currentWindowAdaptiveInfo(),
        LocalIsDarkMode provides isDarkTheme,
    ) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme,
            shapes = Shapes,
            content = content,
        )
    }
}

object SwissTransferTheme {
    val typography = Typography
    val colors: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current
    val materialColors: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme
}

@Immutable
data class CustomColorScheme(
    val primaryTextColor: Color = Color.Unspecified,
    val secondaryTextColor: Color = Color.Unspecified,
    val tertiaryTextColor: Color = Color.Unspecified,
    val toolbarTextColor: Color = Color.Unspecified,
    val toolbarIconColor: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified,
    val navigationItemBackground: Color = Color.Unspecified,
    val tertiaryButtonBackground: Color = Color.Unspecified,
    val selectedSettingItem: Color = Color.Unspecified,
    val fileItemRemoveButtonBackground: Color = Color.Unspecified,
    val transferTypeLinkContainer: Color = Color.Unspecified,
    val transferTypeLinkOnContainer: Color = Color.Unspecified,
    val transferTypeEmailContainer: Color = Color.Unspecified,
    val transferTypeEmailOnContainer: Color = Color.Unspecified,
    val transferTypeQrContainer: Color = Color.Unspecified,
    val transferTypeQrOnContainer: Color = Color.Unspecified,
    val transferTypeProximityContainer: Color = Color.Unspecified,
    val transferTypeProximityOnContainer: Color = Color.Unspecified,
    val emailAddressChipColor: Color = Color.Unspecified,
    val onEmailAddressChipColor: Color = Color.Unspecified,
    val qrCodeDarkPixels: Color = Color.Unspecified,
    val qrCodeLightPixels: Color = Color.Unspecified,
    val transferFilePreviewOverflow: Color = Color.Unspecified,
    val onTransferFilePreviewOverflow: Color = Color.Unspecified,
)

private val Shapes = Shapes(
    extraSmall = CustomShapes.EXTRA_SMALL,
    small = CustomShapes.SMALL,
    medium = CustomShapes.MEDIUM,
    large = CustomShapes.LARGE,
    // extraLarge: uses default values, for the bottom sheet corners
)
