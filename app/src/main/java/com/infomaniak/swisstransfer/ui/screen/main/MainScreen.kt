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

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationDestination.Companion.toDestination
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.toDestination<MainNavigation>() ?: MainNavigation.startDestination
        }
    }

    MainScaffold(navController, currentDestination, windowAdaptiveInfo) {
        MainNavHost(navController, currentDestination, windowAdaptiveInfo)
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun MainScreenPreview() {
    SwissTransferTheme {
        MainScreen()
    }
}
