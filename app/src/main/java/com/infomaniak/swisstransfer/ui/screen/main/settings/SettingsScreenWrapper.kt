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
package com.infomaniak.swisstransfer.ui.screen.main.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.appSettings.AppSettings
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.*
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.*

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreenWrapper(
    settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
) {
    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)
    appSettings?.let { safeAppSettings ->
        TwoPaneScaffold<SettingsOptionScreens>(
            listPane = { ListPane(this, safeAppSettings) },
            detailPane = { DetailPane(safeAppSettings, settingsViewModel, navigator = this) },
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>, appSettings: AppSettings) {
    val context = LocalContext.current
    val aboutURL = stringResource(R.string.urlAbout)
    val userReportURL = stringResource(R.string.urlUserReportAndroid)

    SettingsScreen(
        appSettings,
        onItemClick = { item ->
            when (item) {
                NOTIFICATIONS -> context.openAppNotificationSettings()
                DISCOVER_INFOMANIAK -> context.openUrl(aboutURL)
                SHARE_IDEAS -> context.openUrl(userReportURL)
                GIVE_FEEDBACK -> context.goToPlayStore()
                else -> {
                    // Navigate to the detail pane with the passed item
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                }
            }
        },
        getSelectedSetting = { navigator.currentDestination?.content },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(
    appSettings: AppSettings,
    settingsViewModel: SettingsViewModel,
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
) {
    var lastSelectedScreen by rememberSaveable { mutableStateOf<SettingsOptionScreens?>(null) }

    val destination = navigator.currentDestination?.content ?: lastSelectedScreen
    navigator.currentDestination?.content?.let { lastSelectedScreen = it }

    val navigateBackCallback: () -> Unit = { navigator.navigateBack() }
    val navigateBack: (() -> Unit)? = if (navigator.canNavigateBack()) navigateBackCallback else null

    when (destination) {
        THEME -> SettingsThemeScreen(
            theme = appSettings.theme,
            navigateBack = navigateBack,
            onThemeUpdate = settingsViewModel::setTheme,
        )
        VALIDITY_PERIOD -> SettingsValidityPeriodScreen(
            validityPeriod = appSettings.validityPeriod,
            navigateBack = navigateBack,
            onValidityPeriodChange = settingsViewModel::setValidityPeriod,
        )
        DOWNLOAD_LIMIT -> SettingsDownloadsLimitScreen(
            downloadLimit = appSettings.downloadLimit,
            navigateBack = navigateBack,
            onDownloadLimitChange = settingsViewModel::setDownloadLimit,
        )
        EMAIL_LANGUAGE -> SettingsEmailLanguageScreen(
            emailLanguage = appSettings.emailLanguage,
            navigateBack = navigateBack,
            onEmailLanguageChange = settingsViewModel::setEmailLanguage,
        )
        NOTIFICATIONS,
        DISCOVER_INFOMANIAK,
        SHARE_IDEAS,
        GIVE_FEEDBACK -> Unit
        null -> NoSelectionEmptyState()
    }
}

// Show the detail pane content if selected item is available
@Composable
private fun NoSelectionEmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Select a setting item", color = SwissTransferTheme.colors.secondaryTextColor)
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun SettingsScreenWrapperPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreenWrapper()
        }
    }
}
