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

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.core.extensions.goToAppStore
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.components.safeCurrentContent
import com.infomaniak.swisstransfer.ui.components.selectItem
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT_MATOMO
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT_SENTRY
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DISCOVER_INFOMANIAK
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DOWNLOAD_LIMIT
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.EMAIL_LANGUAGE
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.EULA
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.GIVE_FEEDBACK
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.NOTIFICATIONS
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.SHARE_IDEAS
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.THEME
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.VALIDITY_PERIOD
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import com.infomaniak.swisstransfer.ui.utils.openAppNotificationSettings
import com.infomaniak.swisstransfer.ui.utils.openUrl
import kotlinx.coroutines.launch

private const val EULA_URL = "https://www.swisstransfer.com/?cgu"

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
    val userReportURL = stringResource(R.string.urlUserReport)
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    val scope = rememberCoroutineScope()

    SettingsScreen(
        theme = theme,
        validityPeriod = validityPeriod,
        downloadLimit = downloadLimit,
        emailLanguage = emailLanguage,
        onItemClick = { item ->
            when (item) {
                NOTIFICATIONS -> context.openAppNotificationSettings()
                EULA -> context.openUrl(EULA_URL)
                DISCOVER_INFOMANIAK -> context.openUrl(aboutURL)
                SHARE_IDEAS -> context.openUrl(userReportURL)
                GIVE_FEEDBACK -> if (BuildConfig.DEBUG) {
                    // The appended `.debug` to the packageName in debug mode should be removed if we want to test this
                    context.goToAppStore("com.infomaniak.swisstransfer")
                } else {
                    context.goToAppStore()
                }
                else -> {
                    // Navigate to the detail pane with the passed item
                    scope.launch { navigator.selectItem(context, windowAdaptiveInfo, item) }
                }
            }
        },
        getSelectedSetting = { navigator.currentDestination?.contentKey },
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

    val destination = navigator.safeCurrentContent()

    val scope = rememberCoroutineScope()
    val navigateBack: () -> Unit = {
        scope.launch { ScreenWrapperUtils.getBackNavigation(navigator)?.invoke() }
    }

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
            onItemClick = { item ->
                scope.launch { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item) }
            },
        )
        DATA_MANAGEMENT_MATOMO -> SettingsDataManagementMatomoScreen(navigateBack)
        DATA_MANAGEMENT_SENTRY -> SettingsDataManagementSentryScreen(navigateBack)
        NOTIFICATIONS,
        EULA,
        DISCOVER_INFOMANIAK,
        SHARE_IDEAS,
        GIVE_FEEDBACK,
        null -> NoSelectionEmptyState()
    }
}

@Composable
private fun NoSelectionEmptyState() {
    SwissTransferScaffold(
        topBar = { SwissTransferTopAppBar(title = "") }
    ) {
        EmptyState(
            content = { Image(imageVector = AppIllus.MascotWithMagnifyingGlass.image(), contentDescription = null) },
            descriptionRes = R.string.noSettingsSelectedDescription
        )
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
