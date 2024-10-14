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
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.*
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.*

@Composable
fun SettingsScreenWrapper(
    settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
) {
    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)

    appSettings?.let { safeAppSettings ->
        val theme = GetSetCallbacks(get = { safeAppSettings.theme }, set = { settingsViewModel.setTheme(it) })
        val validityPeriod =
            GetSetCallbacks(get = { safeAppSettings.validityPeriod }, set = { settingsViewModel.setValidityPeriod(it) })
        val downloadLimit =
            GetSetCallbacks(get = { safeAppSettings.downloadLimit }, set = { settingsViewModel.setDownloadLimit(it) })
        val emailLanguage =
            GetSetCallbacks(get = { safeAppSettings.emailLanguage }, set = { settingsViewModel.setEmailLanguage(it) })

        SettingsScreenWrapper(theme, validityPeriod, downloadLimit, emailLanguage)
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreenWrapper(
    theme: GetSetCallbacks<Theme>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
) {
    TwoPaneScaffold<SettingsOptionScreens>(
        listPane = { ListPane(navigator = this, theme, validityPeriod, downloadLimit, emailLanguage) },
        detailPane = { DetailPane(navigator = this, theme, validityPeriod, downloadLimit, emailLanguage) },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
    theme: GetSetCallbacks<Theme>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
) {
    val context = LocalContext.current
    val aboutURL = stringResource(R.string.urlAbout)
    val userReportURL = stringResource(R.string.urlUserReportAndroid)

    SettingsScreen(
        theme,
        validityPeriod,
        downloadLimit,
        emailLanguage,
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
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
    theme: GetSetCallbacks<Theme>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
) {
    var lastSelectedScreen by rememberSaveable { mutableStateOf<SettingsOptionScreens?>(null) }
    val context = LocalContext.current

    val destination = navigator.currentDestination?.content ?: lastSelectedScreen
    navigator.currentDestination?.content?.let { lastSelectedScreen = it }

    val navigateBackCallback: () -> Unit = { navigator.navigateBack() }
    val navigateBack: (() -> Unit)? = if (navigator.canNavigateBack()) navigateBackCallback else null

    // TODO: Update this URL when we know what we want.
    val sourceCodeURL = stringResource(R.string.urlSourceCode)

    when (destination) {
        THEME -> SettingsThemeScreen(
            theme = theme.get(),
            navigateBack = navigateBack,
            onThemeUpdate = { theme.set(it) },
        )
        VALIDITY_PERIOD -> SettingsValidityPeriodScreen(
            validityPeriod = validityPeriod.get(),
            navigateBack = navigateBack,
            onValidityPeriodChange = { validityPeriod.set(it) },
        )
        DOWNLOAD_LIMIT -> SettingsDownloadsLimitScreen(
            downloadLimit = downloadLimit.get(),
            navigateBack = navigateBack,
            onDownloadLimitChange = { downloadLimit.set(it) },
        )
        EMAIL_LANGUAGE -> SettingsEmailLanguageScreen(
            emailLanguage = emailLanguage.get(),
            navigateBack = navigateBack,
            onEmailLanguageChange = { emailLanguage.set(it) },
        )
        DATA_MANAGEMENT -> SettingsDataManagementScreen(
            navigateBack = navigateBack,
            onViewSourceCodeClicked = { context.openUrl(sourceCodeURL) },
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

@PreviewAllWindows
@Composable
private fun SettingsScreenWrapperPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreenWrapper(
                theme = GetSetCallbacks(get = { Theme.SYSTEM }, set = {}),
                validityPeriod = GetSetCallbacks(get = { ValidityPeriod.THIRTY }, set = {}),
                downloadLimit = GetSetCallbacks(get = { DownloadLimit.TWO_HUNDRED_FIFTY }, set = {}),
                emailLanguage = GetSetCallbacks(get = { EmailLanguage.ENGLISH }, set = {}),
            )
        }
    }
}
