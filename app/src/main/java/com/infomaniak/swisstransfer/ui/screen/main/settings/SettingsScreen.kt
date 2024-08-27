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

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.extensions.goToPlayStore
import com.infomaniak.swisstransfer.extensions.openAppNotificationSettings
import com.infomaniak.swisstransfer.extensions.openUrl
import com.infomaniak.swisstransfer.ui.components.BrandTobAppBar
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.*
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.*
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.CHEVRON
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.OPEN_OUTSIDE
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingDivider
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreenWrapper(
    navController: NavHostController? = null,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    isBarNavigation: Boolean = false
) {
    val context = LocalContext.current
    val aboutURL = stringResource(R.string.urlAbout)
    val userReportURL = stringResource(R.string.urlUserReportAndroid)
    TwoPaneScaffold<SettingsOptionScreens>(
        windowAdaptiveInfo,
        listPane = { ListPane(this) },
        detailPane = { DetailPane(this) }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>) {
    val context = LocalContext.current
    val aboutURL = stringResource(R.string.urlAbout)
    val userReportURL = stringResource(R.string.urlUserReportAndroid)

    SettingsScreen(
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
private fun DetailPane(navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>) {
    var lastSelectedScreen by rememberSaveable { mutableStateOf<SettingsOptionScreens?>(null) }

    val destination = navigator.currentDestination?.content ?: lastSelectedScreen
    navigator.currentDestination?.content?.let { lastSelectedScreen = it }

    val navigateBackCallback: () -> Unit = { navigator.navigateBack() }
    val navigateBack: (() -> Unit)? = if (navigator.canNavigateBack()) navigateBackCallback else null

    when (destination) {
        THEME -> SettingsThemeScreen(navigateBack)
        VALIDITY_PERIOD -> SettingsValidityPeriodScreen(navigateBack)
        DOWNLOAD_LIMIT -> SettingsDownloadsLimitScreen(navigateBack)
        EMAIL_LANGUAGE -> SettingsEmailLanguageScreen(navigateBack)
        NOTIFICATIONS,
        DISCOVER_INFOMANIAK,
        SHARE_IDEAS,
        GIVE_FEEDBACK -> Unit
        null -> NoSelectionEmptyState()
    }
}

@Composable
private fun SettingsScreen(onItemClick: (SettingsOptionScreens) -> Unit, getSelectedSetting: () -> SettingsOptionScreens?) {
    val selectedSetting = getSelectedSetting()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .selectableGroup(),
    ) {

        BrandTobAppBar()

        Text(
            modifier = Modifier.padding(horizontal = Margin.Medium, vertical = Margin.Large),
            text = stringResource(R.string.settingsTitle),
            style = SwissTransferTheme.typography.h1,
        )

        SettingTitle(R.string.settingsCategoryGeneral)
        SettingItem(
            titleRes = R.string.settingsOptionTheme,
            isSelected = { selectedSetting == THEME },
            icon = AppIcons.PaintbrushPalette,
            description = "TODO",
            CHEVRON
        ) {
            onItemClick(THEME)
        }
        SettingItem(
            titleRes = R.string.settingsOptionNotifications,
            isSelected = { selectedSetting == NOTIFICATIONS },
            icon = AppIcons.Bell,
            description = "TODO",
            endIcon = OPEN_OUTSIDE,
        ) {
            onItemClick(NOTIFICATIONS)
        }

        SettingDivider()

        SettingTitle(R.string.settingsCategoryDefaultSettings)
        SettingItem(
            titleRes = R.string.settingsOptionValidityPeriod,
            isSelected = { selectedSetting == VALIDITY_PERIOD },
            icon = AppIcons.FileBadgeArrowDown,
            description = "TODO",
            endIcon = CHEVRON,
        ) {
            onItemClick(VALIDITY_PERIOD)
        }
        SettingItem(
            titleRes = R.string.settingsOptionDownloadLimit,
            isSelected = { selectedSetting == DOWNLOAD_LIMIT },
            icon = AppIcons.Clock,
            description = "TODO",
            endIcon = CHEVRON,
        ) {
            onItemClick(DOWNLOAD_LIMIT)
        }
        SettingItem(
            titleRes = R.string.settingsOptionEmailLanguage,
            isSelected = { selectedSetting == EMAIL_LANGUAGE },
            icon = AppIcons.SpeechBubble,
            description = "TODO",
            endIcon = CHEVRON,
        ) {
            onItemClick(EMAIL_LANGUAGE)
        }

        SettingDivider()

        SettingTitle(R.string.settingsCategoryAbout)
        SettingItem(
            R.string.settingsOptionDiscoverInfomaniak,
            { selectedSetting == DISCOVER_INFOMANIAK },
            endIcon = OPEN_OUTSIDE
        ) {
            onItemClick(DISCOVER_INFOMANIAK)
        }
        SettingItem(R.string.settingsOptionShareIdeas, { selectedSetting == SHARE_IDEAS }, endIcon = OPEN_OUTSIDE) {
            onItemClick(SHARE_IDEAS)
        }
        SettingItem(R.string.settingsOptionGiveFeedback, { selectedSetting == GIVE_FEEDBACK }, endIcon = OPEN_OUTSIDE) {
            onItemClick(GIVE_FEEDBACK)
        }
        SettingItem(R.string.version, isSelected = { false }, description = "0.0.1", onClick = null)
    }
}

// Show the detail pane content if selected item is available
@Composable
private fun NoSelectionEmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Select a setting item", color = SwissTransferTheme.colors.secondaryTextColor)
    }
}

enum class SettingsOptionScreens {
    THEME, NOTIFICATIONS,
    VALIDITY_PERIOD, DOWNLOAD_LIMIT, EMAIL_LANGUAGE,
    DISCOVER_INFOMANIAK, SHARE_IDEAS, GIVE_FEEDBACK,
}

@PreviewMobile
@PreviewTablet
@Composable
private fun SettingsScreenPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreenWrapper()
        }
    }
}
