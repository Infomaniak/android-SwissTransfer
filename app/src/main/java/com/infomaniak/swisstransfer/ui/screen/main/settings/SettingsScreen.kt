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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.Bell
import com.infomaniak.swisstransfer.ui.icons.app.SpeechBubble
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
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    TwoPaneScaffold<SettingsOptionScreens>(
        windowAdaptiveInfo,
        listPane = {
            SettingsScreen(
                onItemClick = { item ->
                    // Navigate to the detail pane with the passed item
                    navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                },
            )
        },
        detailPane = {
            // Show the detail pane content if selected item is available
            val destination = currentDestination?.content
            if (destination == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Select a setting item", color = SwissTransferTheme.colors.secondaryTextColor)
                }
            } else {
                when (destination) {
                    THEME -> SettingsThemeScreen()
                    NOTIFICATIONS -> {}
                    VALIDITY_PERIOD -> {}
                    DOWNLOAD_LIMIT -> {}
                    EMAIL_LANGUAGE -> {}
                    DISCOVER_INFOMANIAK -> {}
                    SHARE_IDEAS -> {}
                    GIVE_FEEDBACK -> {}
                }
            }
        }
    )
}

@Composable
private fun SettingsScreen(onItemClick: (SettingsOptionScreens) -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(horizontal = Margin.Medium, vertical = Margin.Large),
            text = stringResource(R.string.settingsTitle),
            style = SwissTransferTheme.typography.h1,
        )

        SettingTitle(R.string.settingsCategoryGeneral)
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionTheme, AppIcons.Add, "TODO", CHEVRON) { onItemClick(THEME) }
        SettingItem(R.string.settingsOptionNotifications, AppIcons.Bell, "TODO", endIcon = OPEN_OUTSIDE) {
            onItemClick(NOTIFICATIONS)
        }

        SettingDivider()

        SettingTitle(R.string.settingsCategoryDefaultSettings)
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionValidityPeriod, AppIcons.Add, "TODO", endIcon = CHEVRON) {
            onItemClick(VALIDITY_PERIOD)
        }
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionDownloadLimit, AppIcons.Add, "TODO", endIcon = CHEVRON) {
            onItemClick(DOWNLOAD_LIMIT)
        }
        SettingItem(R.string.settingsOptionEmailLanguage, AppIcons.SpeechBubble, "TODO", endIcon = CHEVRON) {
            onItemClick(EMAIL_LANGUAGE)
        }

        SettingDivider()

        SettingTitle(R.string.settingsCategoryAbout)
        SettingItem(R.string.settingsOptionDiscoverInfomaniak, endIcon = OPEN_OUTSIDE) { onItemClick(DISCOVER_INFOMANIAK) }
        SettingItem(R.string.settingsOptionShareIdeas, endIcon = OPEN_OUTSIDE) { onItemClick(SHARE_IDEAS) }
        SettingItem(R.string.settingsOptionGiveFeedback, endIcon = OPEN_OUTSIDE) { onItemClick(GIVE_FEEDBACK) }
        SettingItem(R.string.version, description = "0.0.1", onClick = null)
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
