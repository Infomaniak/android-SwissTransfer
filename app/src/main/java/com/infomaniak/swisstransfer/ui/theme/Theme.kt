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

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.infomaniak.core.ui.compose.basics.bottomsheet.BottomSheetThemeDefaults
import com.infomaniak.core.ui.compose.basics.bottomsheet.LocalBottomSheetTheme
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.LocalScaffoldTheme
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.ScaffoldThemeDefault
import com.infomaniak.core.ui.compose.theme.LocalIsThemeDarkMode

val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> = staticCompositionLocalOf { CustomColorScheme() }
val LocalWindowAdaptiveInfo = staticCompositionLocalOf<WindowAdaptiveInfo> { error("No WindowAdaptiveInfo provided") }

@Composable
fun SwissTransferTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val customColors = if (isDarkTheme) CustomDarkColorScheme else CustomLightColorScheme
    val scaffoldTheme = ScaffoldThemeDefault.theme(
        singlePaneMaxWidth = Dimens.MaxSinglePaneScreenWidth,
        stackedButtonVerticalPadding = Dimens.ButtonComboVerticalPadding
    )

    val activity = LocalActivity.current
    LaunchedEffect(isDarkTheme) {
        val window = activity?.window ?: return@LaunchedEffect
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightNavigationBars = isDarkTheme.not()
    }
    CompositionLocalProvider(
        LocalTextStyle provides Typography.bodyRegular,
        LocalCustomColorScheme provides customColors,
        LocalWindowAdaptiveInfo provides currentWindowAdaptiveInfo(),
        LocalIsThemeDarkMode provides isDarkTheme,
        LocalScaffoldTheme provides scaffoldTheme,
        // LocalAvatarColors provides avatarColors, // TODO
    ) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme,
            shapes = Shapes,
        ) {
            // Needs both LocalCustomColorScheme and MaterialTheme's colorScheme to be defined by this point
            val bottomSheetTheme = BottomSheetThemeDefaults.theme(
                contentColor = SwissTransferTheme.colors.primaryTextColor,
                titleTextStyle = BottomSheetStyle.TITLE_TEXT_STYLE,
                titleColor = BottomSheetStyle.TITLE_COLOR,
            )

            CompositionLocalProvider(
                LocalBottomSheetTheme provides bottomSheetTheme,
                content = content,
            )
        }
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
    val transferListStroke: Color = Color.Unspecified,
    val highlightedColor: Color = Color.Unspecified,
    val warning: Color = Color.Unspecified,
    val swipeDefault: Color = Color.Unspecified,
    val swipeDelete: Color = Color.Unspecified,
    val swipeIcon: Color = Color.Unspecified,
    val fileStatusButtonBackground: Color = Color.Unspecified,
)

private val Shapes = Shapes(
    extraSmall = CustomShapes.EXTRA_SMALL,
    small = CustomShapes.SMALL,
    medium = CustomShapes.MEDIUM,
    large = CustomShapes.LARGE,
    // extraLarge: uses default values, for the bottom sheet corners
)
