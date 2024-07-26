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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile

@Composable
fun MainScaffold(
    navController: NavHostController,
    currentDestination: MainNavigation,
    windowAdaptiveInfo: WindowAdaptiveInfo,
    content: @Composable () -> Unit = {},
) {

    val showNavigation by remember(currentDestination) {
        derivedStateOf {
            NavigationItem.entries.any { it.destination == currentDestination }
        }
    }

    val navType by remember(showNavigation, windowAdaptiveInfo) {
        derivedStateOf {
            if (showNavigation) {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
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
    Navigation(currentDestination, navController::navigateToSelectedItem, navType, content)
}

@Composable
private fun Navigation(
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    navType: NavigationSuiteType,
    content: @Composable () -> Unit
) {
    val isNavigationBar = navType == NavigationSuiteType.NavigationBar

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            NavigationItem.entries.forEach { navigationItem ->
                item(
                    icon = { NavigationIcon(isNavigationBar, navigationItem) },
                    label = { NavigationLabel(isNavigationBar, navigationItem) },
                    selected = currentDestination == navigationItem.destination,
                    onClick = { navigateToSelectedItem(navigationItem.destination) }
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = SwissTransferTheme.colors.navigationItemBackground,
            navigationRailContainerColor = SwissTransferTheme.colors.navigationItemBackground,
            navigationDrawerContainerColor = SwissTransferTheme.colors.navigationItemBackground,
        ),
        layoutType = navType,
        content = {
            if (navType == NavigationSuiteType.None) {
                content()
            } else {
                Column {
                    Box(modifier = Modifier.weight(1f)) {
                        content()
                    }
                    HorizontalDivider(color = SwissTransferTheme.colors.divider)
                }
            }
        },
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

@PreviewMobile
@Composable
private fun NavigationPreview() {
    SwissTransferTheme {
        Navigation(
            currentDestination = MainNavigation.SentDestination,
            navigateToSelectedItem = {},
            navType = NavigationSuiteType.NavigationBar,
            content = {},
        )
    }
}
