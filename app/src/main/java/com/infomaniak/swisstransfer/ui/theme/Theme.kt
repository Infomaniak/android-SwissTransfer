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
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsViewModel

val LocalCustomTypography = staticCompositionLocalOf { Typography }
val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> = staticCompositionLocalOf { CustomColorScheme() }
val LocalWindowAdaptiveInfo = staticCompositionLocalOf<WindowAdaptiveInfo> { error("No WindowAdaptiveInfo provided") }

@Composable
fun SwissTransferTheme(
    settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
    darkTheme: Boolean = isDarkTheme(settingsViewModel),
    content: @Composable () -> Unit,
) {
    val customColors = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme
    CompositionLocalProvider(
        LocalCustomTypography provides Typography,
        LocalTextStyle provides Typography.bodyRegular,
        LocalCustomColorScheme provides customColors,
        LocalWindowAdaptiveInfo provides currentWindowAdaptiveInfo(),
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            shapes = Shapes,
            content = content,
        )
    }
}

@Composable
fun isDarkTheme(settingsViewModel: SettingsViewModel): Boolean {
    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)
    return appSettings?.let {
        if (it.theme == Theme.SYSTEM) isSystemInDarkTheme() else it.theme == Theme.DARK
    } ?: isSystemInDarkTheme()
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
    val toolbarTextColor: Color = Color.Unspecified,
    val toolbarIconColor: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified,
    val navigationItemBackground: Color = Color.Unspecified,
    val tertiaryButtonBackground: Color = Color.Unspecified,
    val selectedSettingItem: Color = Color.Unspecified,
    val transferTypeLinkContainer: Color = Color.Unspecified,
    val transferTypeLinkOnContainer: Color = Color.Unspecified,
    val transferTypeEmailContainer: Color = Color.Unspecified,
    val transferTypeEmailOnContainer: Color = Color.Unspecified,
    val transferTypeQrContainer: Color = Color.Unspecified,
    val transferTypeQrOnContainer: Color = Color.Unspecified,
    val transferTypeProximityContainer: Color = Color.Unspecified,
    val transferTypeProximityOnContainer: Color = Color.Unspecified,
)
