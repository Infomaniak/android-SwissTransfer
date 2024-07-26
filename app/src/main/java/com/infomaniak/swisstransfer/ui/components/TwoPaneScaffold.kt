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

package com.infomaniak.swisstransfer.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowHeightSizeClass

/**
 * A composable function that sets up a List-Detail interface using a three-pane scaffold navigator.
 * This function adapts its layout based on the given window size class and manages the navigation
 * between list and detail panes.
 * @param T the type representing the item displayed in the detail pane. This can be an item id or a whole instance.
 * This type must be storable in a Bundle. If this customization is unneeded, you can pass [Nothing]
 * @param windowAdaptiveInfo An instance of [WindowAdaptiveInfo] that provides information about
 * the current window's size class and adaptive state.
 * @param listPane A composable function that describes the content of the list pane.
 * @param detailPane A composable function that describes the content of the detail pane.
 * @param modifier [Modifier] of the scaffold layout.
 *
 * @sample com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsScreenWrapper
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> TwoPaneScaffold(
    windowAdaptiveInfo: WindowAdaptiveInfo,
    listPane: @Composable ThreePaneScaffoldNavigator<T>.() -> Unit,
    detailPane: @Composable ThreePaneScaffoldNavigator<T>.() -> Unit,
    modifier: Modifier = Modifier,
) {
    val paneScaffoldDirective = calculatePaneScaffoldDirective(windowAdaptiveInfo)
    val maxHorizontalPartitions = when (windowAdaptiveInfo.windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 1
        else -> paneScaffoldDirective.maxHorizontalPartitions
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<T>(
        scaffoldDirective = paneScaffoldDirective.copy(maxHorizontalPartitions)
    )

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                navigator.listPane()
            }
        },
        detailPane = {
            AnimatedPane {
                navigator.detailPane()
            }
        },
        modifier = modifier,
    )
}
