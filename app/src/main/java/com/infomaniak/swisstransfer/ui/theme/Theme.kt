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
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val LocalCustomTypography = staticCompositionLocalOf { Typography }
val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> = staticCompositionLocalOf { CustomColorScheme() }

@Composable
fun SwissTransferTheme(
    darkTheme: Boolean = isDarkTheme(),
    content: @Composable () -> Unit,
) {
    val customColors = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme
    CompositionLocalProvider(
        LocalCustomTypography provides Typography,
        LocalTextStyle provides Typography.bodyRegular,
        LocalCustomColorScheme provides customColors,
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun isDarkTheme(): Boolean {
    // rememberMutableStateOf
    // TODO check in realm. If system, isSystemDark, otherwise,
    return isSystemInDarkTheme()
}

object SwissTransferTheme {
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
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
    val iconColor: Color = Color.Unspecified,
    val navigationItemBackground: Color = Color.Unspecified,
    val tertiaryButtonBackground: Color = Color.Unspecified,
    val selectedSettingItem: Color = Color.Unspecified,
)
