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

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.Bell
import com.infomaniak.swisstransfer.ui.icons.app.SpeechBubble
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreenWrapper(
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    TwoPaneScaffold<Any>( // TODO: Replace Any with item type
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
            if (currentDestination?.content == null) {
                Text("Empty state")
            } else {
                Text("Show selected item")
            }
        }
    )
}

@Composable
private fun SettingsScreen(onItemClick: (Any) -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(horizontal = Margin.Medium, vertical = Margin.Large),
            text = stringResource(R.string.settingsTitle),
            style = SwissTransferTheme.typography.h1,
        )

        SettingTitle(R.string.settingsCategoryGeneral)
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionTheme, AppIcons.Add, "TODO", EndIconType.CHEVRON) {}
        SettingItem(R.string.settingsOptionNotifications, AppIcons.Bell, "TODO", endIcon = EndIconType.OPEN_OUTSIDE) {}

        SettingDivider()

        SettingTitle(R.string.settingsCategoryDefaultSettings)
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionValidityPeriod, AppIcons.Add, "TODO", endIcon = EndIconType.CHEVRON) {}
        // TODO: Use correct icon
        SettingItem(R.string.settingsOptionDownloadLimit, AppIcons.Add, "TODO", endIcon = EndIconType.CHEVRON) {}
        SettingItem(R.string.settingsOptionEmailLanguage, AppIcons.SpeechBubble, "TODO", endIcon = EndIconType.CHEVRON) {}

        SettingDivider()

        SettingTitle(R.string.settingsCategoryAbout)
        SettingItem(R.string.settingsOptionDiscoverInfomaniak, endIcon = EndIconType.OPEN_OUTSIDE) {}
        SettingItem(R.string.settingsOptionShareIdeas, endIcon = EndIconType.OPEN_OUTSIDE) {}
        SettingItem(R.string.settingsOptionGiveFeedback, endIcon = EndIconType.OPEN_OUTSIDE) {}
        SettingItem(R.string.version, description = "0.0.1", onClick = null)
    }
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
