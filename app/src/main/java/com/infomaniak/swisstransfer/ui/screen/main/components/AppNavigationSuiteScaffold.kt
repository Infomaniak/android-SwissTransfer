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

package com.infomaniak.swisstransfer.ui.screen.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun AppNavigationSuiteScaffold(
    navType: NavigationSuiteType,
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    content: @Composable () -> Unit,
) {
    val isNavigationBar = navType == NavigationSuiteType.NavigationBar

    Surface(color = SwissTransferTheme.materialColors.background) {
        NavigationSuiteScaffoldLayout(
            navigationSuite = {
                if (isNavigationBar) {
                    AppNavigationBar(currentDestination, navigateToSelectedItem)
                } else {
                    AppNavigationRail(currentDestination, navigateToSelectedItem)
                }
            },
            layoutType = navType,
        ) {
            Box(
                Modifier.consumeWindowInsets(
                    when (navType) {
                        NavigationSuiteType.NavigationBar ->
                            NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Bottom)
                        NavigationSuiteType.NavigationRail ->
                            NavigationRailDefaults.windowInsets.only(WindowInsetsSides.Start)
                        else -> WindowInsets(0, 0, 0, 0)
                    }
                )
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AppNavigationBar(currentDestination: MainNavigation, navigateToSelectedItem: (MainNavigation) -> Unit) {
    NavigationBar(containerColor = SwissTransferTheme.colors.navigationItemBackground) {
        com.infomaniak.swisstransfer.ui.navigation.NavigationItem.entries.forEach { navigationItem ->
            NavigationBarItem(
                icon = { NavigationIcon(true, navigationItem) },
                label = { NavigationLabel(navigationItem) },
                selected = currentDestination == navigationItem.destination,
                onClick = { navigateToSelectedItem(navigationItem.destination) },
            )
        }
    }
}

@Composable
private fun AppNavigationRail(currentDestination: MainNavigation, navigateToSelectedItem: (MainNavigation) -> Unit) {
    NavigationRail(
        header = {
            FloatingActionButton(onClick = {}, modifier = Modifier.padding(bottom = Margin.Large)) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        containerColor = SwissTransferTheme.colors.navigationItemBackground
    ) {
        com.infomaniak.swisstransfer.ui.navigation.NavigationItem.entries.forEach { navigationItem ->
            NavigationRailItem(
                icon = { NavigationIcon(false, navigationItem) },
                label = {},
                selected = currentDestination == navigationItem.destination,
                onClick = { navigateToSelectedItem(navigationItem.destination) },
            )
        }
    }
}

@Composable
private fun NavigationIcon(isNavigationBar: Boolean, navigationItem: NavigationItem) {
    val contentDescription = if (isNavigationBar) null else stringResource(navigationItem.label)
    Icon(navigationItem.icon, contentDescription)
}

@Composable
private fun NavigationLabel(navigationItem: NavigationItem) {
    Text(stringResource(navigationItem.label))
}