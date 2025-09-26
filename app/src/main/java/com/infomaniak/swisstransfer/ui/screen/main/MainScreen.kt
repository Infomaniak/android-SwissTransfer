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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.Companion.toMainDestination
import com.infomaniak.swisstransfer.ui.screen.main.components.MainScaffold
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun MainScreen(deeplinkTransferDirection: TransferDirection? = null) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val hideBottomBar = rememberSaveable { mutableStateOf(false) }

    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.toMainDestination() ?: MainNavigation.startDestination }
    }

    MainScaffold(
        navController = navController,
        currentDestination = currentDestination,
        hideBottomBar = hideBottomBar,
        content = { MainNavHost(navController, deeplinkTransferDirection, hideBottomBar) },
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        MainScreen()
    }
}
