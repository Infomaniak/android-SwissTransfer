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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    background = dark_background,
    surface = dark_surface,
    onSurface = dark_onSurface,
    onSurfaceVariant = dark_onSurfaceVariant,
    surfaceContainerHighest = dark_surfaceContainerHighest,
)

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    background = light_background,
    surface = light_surface,
    onSurface = light_onSurface,
    onSurfaceVariant = light_onSurfaceVariant,
    surfaceContainerHighest = light_surfaceContainerHighest,
)

val LocalCustomTypography = staticCompositionLocalOf { Typography }
val LocalCustomColors: ProvidableCompositionLocal<CustomColors> = staticCompositionLocalOf { CustomColors() }

@Composable
fun SwissTransferTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val customColors = if (darkTheme) DarkColors else LightColors
    CompositionLocalProvider(
        LocalCustomTypography provides Typography,
        LocalTextStyle provides Typography.bodyRegular,
        LocalCustomColors provides customColors,
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            shapes = Shapes,
            content = content
        )
    }
}

object SwissTransferTheme {
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
    val materialColors: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme
}

@Immutable
data class CustomColors(
    val primaryTextColor: Color = Color.Unspecified,
    val secondaryTextColor: Color = Color.Unspecified,
    val navigationItemBackground: Color = Color.Unspecified,
    val divider: Color = Color.Unspecified,
)
