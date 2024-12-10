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

import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.Companion.toMainDestination
import com.infomaniak.swisstransfer.ui.screen.main.components.MainScaffold
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun MainScreen(isTransferDeeplink: Boolean = false) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.toMainDestination() ?: MainNavigation.startDestination }
    }

    var lastStartDestination: MainNavigation by remember { mutableStateOf(MainNavigation.startDestination) }

    MainScaffold(
        navController = navController,
        currentDestination = currentDestination,
        windowTopAppBar = { isWindowLarge ->
            // This is temporary to fix an issue with the animation when displaying the FilesDetailsScreen
            if (isWindowLarge) {
                if (currentDestination is MainNavigation.FilesDetailsDestination) {
                    SwissTransferTopAppBar(
                        navigationMenu = TopAppBarButton.backButton { navController.popBackStack() },
                        actionMenus = arrayOf(TopAppBarButton.closeButton {
                            goBackToStartScreen(navController, lastStartDestination)
                        }),
                    )
                } else {
                    BrandTopAppBar()
                }
            }
        },
        content = {
            MainNavHost(
                navController = navController,
                currentDestination = currentDestination,
                isWindowSmall = LocalWindowAdaptiveInfo.current.isWindowSmall(),
                onStartDestinationChanged = { lastStartDestination = it },
                closeFilesDetails = { goBackToStartScreen(navController, lastStartDestination) },
				isTransferDeepink = isTransferDeeplink,
            )
        },
    )
}

private fun goBackToStartScreen(navController: NavController, lastStartDestination: MainNavigation) {
    navController.popBackStack(
        route = lastStartDestination,
        inclusive = false,
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        MainScreen()
    }
}
