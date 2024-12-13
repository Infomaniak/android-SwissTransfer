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

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.infomaniak.core2.extensions.parcelableExtra
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun MainScaffold(
    navController: NavHostController,
    currentDestination: MainNavigation,
    largeWindowTopAppBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val navType by rememberNavType(currentDestination)
    MainScaffold(navType, currentDestination, navController::navigateToSelectedItem, largeWindowTopAppBar, content)
}

@Composable
private fun MainScaffold(
    navType: NavigationSuiteType,
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    largeWindowTopAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    Column {
        if (windowAdaptiveInfo.isWindowLarge()) largeWindowTopAppBar()
        AppNavigationSuiteScaffold(navType, NavigationItem.entries, currentDestination, navigateToSelectedItem) {
            if (windowAdaptiveInfo.isWindowSmall()) {
                Column {
                    Box(modifier = Modifier.weight(1.0f)) { content() }
                    HorizontalDivider()
                }
            } else {
                content()
            }
        }
    }
}

@Composable
private fun rememberNavType(
    currentDestination: MainNavigation,
    windowAdaptiveInfo: WindowAdaptiveInfo = LocalWindowAdaptiveInfo.current,
): State<NavigationSuiteType> {

    val showNavigation by remember(currentDestination) {
        derivedStateOf {
            NavigationItem.entries.any { it.destination::class == currentDestination::class }
        }
    }

    return remember(showNavigation, windowAdaptiveInfo) {
        derivedStateOf {
            if (showNavigation) {
                calculateFromAdaptiveInfo(windowAdaptiveInfo)
            } else {
                NavigationSuiteType.None
            }
        }
    }
}

private fun calculateFromAdaptiveInfo(windowAdaptiveInfo: WindowAdaptiveInfo): NavigationSuiteType {
    return if (windowAdaptiveInfo.isWindowLarge()) NavigationSuiteType.NavigationRail else NavigationSuiteType.NavigationBar
}

private fun NavHostController.navigateToSelectedItem(destination: MainNavigation) {
    destination.enableTransition = false
    navigate(destination) {
        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
        // on the back stack as users select items
        val startDestination = this@navigateToSelectedItem.graph.findStartDestination()
        popUpTo(startDestination.id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        val currentBackStackEntry = this@navigateToSelectedItem.currentBackStackEntry
        val hasDeepLink = currentBackStackEntry?.arguments?.parcelableExtra<Intent>(NavController.KEY_DEEP_LINK_INTENT) != null
        val isNavigateToStartDestination = startDestination.route == destination::class.qualifiedName
        restoreState = !hasDeepLink || !isNavigateToStartDestination
    }
}

@PreviewSmallWindow
@Composable
private fun NavigationSmallWindowPreview() {
    SwissTransferTheme {
        MainScaffold(
            currentDestination = MainNavigation.SentDestination,
            navigateToSelectedItem = {},
            navType = NavigationSuiteType.NavigationBar,
            largeWindowTopAppBar = { BrandTopAppBar() },
            content = {},
        )
    }
}

@PreviewLargeWindow
@Composable
private fun NavigationLargeWindowPreview() {
    SwissTransferTheme {
        MainScaffold(
            currentDestination = MainNavigation.SentDestination,
            navigateToSelectedItem = {},
            navType = NavigationSuiteType.NavigationRail,
            largeWindowTopAppBar = { BrandTopAppBar() },
            content = {},
        )
    }
}
