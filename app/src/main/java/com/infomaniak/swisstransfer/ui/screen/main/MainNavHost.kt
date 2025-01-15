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

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.*
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination.Companion.receivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination.Companion.sentDestination
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsScreenWrapper
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersScreenWrapper

@Composable
fun MainNavHost(
    navController: NavHostController,
    currentDestination: MainNavigation,
    deeplinkTransferDirection: TransferDirection?,
) {

    val startDestination = when (deeplinkTransferDirection) {
        TransferDirection.SENT -> SentDestination()
        TransferDirection.RECEIVED -> ReceivedDestination()
        else -> MainNavigation.startDestination
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { if (currentDestination.enableTransition) fadeIn() else EnterTransition.None },
        exitTransition = { if (currentDestination.enableTransition) fadeOut() else ExitTransition.None },
    ) {
        sentDestination {
            val args = it.toRoute<SentDestination>()
            TransfersScreenWrapper(TransferDirection.SENT, transferUuid = args.transferUuid)
        }
        receivedDestination {
            val args = it.toRoute<ReceivedDestination>()
            TransfersScreenWrapper(TransferDirection.RECEIVED, transferUuid = args.transferUuid)
        }
        composable<SettingsDestination> { SettingsScreenWrapper() }
    }
}
