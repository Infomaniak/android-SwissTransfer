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
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

/**
 * Layout for a [NavigationSuiteScaffold]'s content. This function wraps the [content] and places
 * the [NavigationSuite] component according to the given [layoutType].
 *
 * @param layoutType The current [NavigationSuiteType]. It determines whether
 * the app will use a bottom navigation bar ([NavigationSuiteType.NavigationBar])
 * or a navigation rail ([NavigationSuiteType.NavigationRail]).
 *
 * @param navigationItems The navigation items to be displayed.
 *
 * @param currentDestination The current selected item in the navigation suite. It represents
 * the active navigation item that the user is currently on, which will be highlighted or selected
 * in the navigation UI.
 *
 * @param navigateToSelectedItem A lambda function that handles the navigation logic when a user
 * selects a different item in the navigation suite. It receives the selected [MainNavigation]
 * item and performs the necessary navigation.
 *
 * @param content The content of your screen
 */
@Composable
fun AppNavigationSuiteScaffold(
    layoutType: NavigationSuiteType,
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(color = SwissTransferTheme.materialColors.background) {
        NavigationSuiteScaffoldLayout(
            navigationSuite = {
                when (layoutType) {
                    NavigationSuiteType.None -> Unit
                    NavigationSuiteType.NavigationBar -> {
                        AppNavigationBar(navigationItems, currentDestination, navigateToSelectedItem)
                    }
                    else -> {
                        AppNavigationRail(navigationItems, currentDestination, navigateToSelectedItem)
                    }
                }
            },
            layoutType = layoutType,
        ) {
            Box(
                Modifier.consumeWindowInsets(
                    when (layoutType) {
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
private fun AppNavigationBar(
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    onClick: (MainNavigation) -> Unit,
) {
    NavigationBar(containerColor = SwissTransferTheme.colors.navigationItemBackground) {
        navigationItems.forEach { navigationItem ->
            NavigationBarItem(
                icon = { NavigationIcon(true, navigationItem) },
                label = { NavigationLabel(navigationItem) },
                selected = currentDestination == navigationItem.destination,
                onClick = { onClick(navigationItem.destination) },
            )
        }
    }
}

@Composable
private fun AppNavigationRail(
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    onClick: (MainNavigation) -> Unit,
) {
    NavigationRail(
        header = {
            NewTransferFab(
                modifier = Modifier.padding(vertical = Margin.Medium),
                newTransferFabType = NewTransferFabType.NAVIGATION_RAIL,
            )
        },
        containerColor = SwissTransferTheme.colors.navigationItemBackground
    ) {
        navigationItems.forEach { navigationItem ->
            NavigationRailItem(
                icon = { NavigationIcon(false, navigationItem) },
                label = {},
                selected = currentDestination == navigationItem.destination,
                onClick = { onClick(navigationItem.destination) },
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
