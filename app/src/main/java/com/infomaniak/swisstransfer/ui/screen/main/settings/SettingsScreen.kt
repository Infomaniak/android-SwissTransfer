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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
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
    Text("Settings screen")
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
