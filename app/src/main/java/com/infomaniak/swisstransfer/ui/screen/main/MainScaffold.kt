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

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem

@Composable
fun MainScaffold(
    navController: NavHostController,
    currentDestination: MainNavigation,
    content: @Composable () -> Unit = {},
) {
    val adaptiveInfo by rememberUpdatedState(currentWindowAdaptiveInfo())

    val showNavigation by remember(currentDestination) {
        derivedStateOf {
            if (currentDestination == MainNavigation.SettingsDestination) false else
                NavigationItem.entries.any { it.destination == currentDestination }
        }
    }

    val navType by remember(showNavigation, adaptiveInfo) {
        derivedStateOf {
            if (showNavigation) {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
            } else {
                NavigationSuiteType.None
            }
        }
    }

    MainScaffold(navType, currentDestination, navController, content)
}

@Composable
private fun MainScaffold(
    navType: NavigationSuiteType,
    currentDestination: MainNavigation,
    navController: NavHostController,
    content: @Composable () -> Unit,
) {
    val isNavigationBar = navType == NavigationSuiteType.NavigationBar

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            NavigationItem.entries.forEach { navigationItem ->
                item(
                    icon = { NavigationIcon(isNavigationBar, navigationItem) },
                    label = { NavigationLabel(isNavigationBar, navigationItem) },
                    selected = currentDestination == navigationItem.destination,
                    onClick = { navController.navigateToSelectedItem(navigationItem.destination) }
                )
            }
        },
        layoutType = navType,
        content = content,
    )
}

@Composable
private fun NavigationIcon(isNavigationBar: Boolean, navigationItem: NavigationItem) {
    val contentDescription = if (isNavigationBar) null else stringResource(navigationItem.label)
    Icon(navigationItem.icon, contentDescription)
}

@Composable
private fun NavigationLabel(isNavigationBar: Boolean, navigationItem: NavigationItem) {
    if (isNavigationBar) {
        Text(stringResource(navigationItem.label))
    }
}

private fun NavHostController.navigateToSelectedItem(destination: MainNavigation) {
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
