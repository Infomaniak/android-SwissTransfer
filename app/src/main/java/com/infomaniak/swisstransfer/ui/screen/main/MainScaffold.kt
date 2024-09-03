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
package com.infomaniak.swisstransfer.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.screen.main.components.AppNavigationSuiteScaffold
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

val LocalNavType = staticCompositionLocalOf { NavigationSuiteType.None }

@Composable
fun MainScaffold(
    navController: NavHostController,
    currentDestination: MainNavigation,
    windowAdaptiveInfo: WindowAdaptiveInfo,
    content: @Composable () -> Unit = {},
) {

    val navType by rememberNavType(currentDestination, windowAdaptiveInfo)

    CompositionLocalProvider(LocalNavType provides navType) {
        MainScaffold(navType, currentDestination, navController::navigateToSelectedItem, content)
    }
}

@Composable
private fun MainScaffold(
    navType: NavigationSuiteType,
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    content: @Composable () -> Unit,
) {
    AppNavigationSuiteScaffold(navType, NavigationItem.entries, currentDestination, navigateToSelectedItem) {
        if (navType == NavigationSuiteType.None) {
            content()
        } else {
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    content()
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun rememberNavType(
    currentDestination: MainNavigation,
    windowAdaptiveInfo: WindowAdaptiveInfo,
): State<NavigationSuiteType> {

    val showNavigation by remember(currentDestination) {
        derivedStateOf {
            NavigationItem.entries.any { it.destination == currentDestination }
        }
    }

    return remember(showNavigation, windowAdaptiveInfo) {
        derivedStateOf {
            if (showNavigation) {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
            } else {
                NavigationSuiteType.None
            }
        }
    }
}

private fun NavHostController.navigateToSelectedItem(destination: MainNavigation) {
    destination.enableTransition = false
    navigate(destination) {
        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(this@navigateToSelectedItem.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
}

@PreviewMobile
@Composable
private fun NavigationPreviewMobile() {
    SwissTransferTheme {
        MainScaffold(
            currentDestination = MainNavigation.SentDestination,
            navigateToSelectedItem = {},
            navType = NavigationSuiteType.NavigationBar,
            content = {},
        )
    }
}

@PreviewTablet
@Composable
private fun NavigationPreviewTablet() {
    SwissTransferTheme {
        MainScaffold(
            currentDestination = MainNavigation.SentDestination,
            navigateToSelectedItem = {},
            navType = NavigationSuiteType.NavigationRail,
            content = {},
        )
    }
}
