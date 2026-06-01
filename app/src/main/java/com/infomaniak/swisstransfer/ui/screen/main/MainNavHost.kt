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

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.MyAccountDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination.Companion.receivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination.Companion.sentDestination
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountScreenWrapper
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersScreenWrapper
import com.infomaniak.swisstransfer.ui.utils.LocalSharedTransitionScope
import com.infomaniak.swisstransfer.ui.utils.SwissTransferTransition
import com.infomaniak.swisstransfer.ui.utils.animatedComposable

@Composable
fun MainNavHost(
    navController: NavHostController,
    deeplinkTransferDirection: TransferDirection?,
    onHideBottomBarChange: (Boolean) -> Unit,
) {

    val startDestination = when (deeplinkTransferDirection) {
        TransferDirection.SENT -> SentDestination()
        TransferDirection.RECEIVED -> ReceivedDestination()
        else -> MainNavigation.startDestination
    }

    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this@SharedTransitionLayout) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition = { SwissTransferTransition.enterTransition },
                exitTransition = { SwissTransferTransition.exitTransition },
            ) {
                sentDestination {
                    val args = it.toRoute<SentDestination>()
                    TransfersScreenWrapper(
                        direction = TransferDirection.SENT,
                        transferIdType = args.toTransferIdType(),
                        onHideBottomBarChange = onHideBottomBarChange,
                    )
                }
                receivedDestination {
                    val args = it.toRoute<ReceivedDestination>()
                    TransfersScreenWrapper(
                        direction = TransferDirection.RECEIVED,
                        transferIdType = args.toTransferIdType(),
                        onHideBottomBarChange = onHideBottomBarChange,
                    )
                }
                animatedComposable<MyAccountDestination> {
                    MyAccountScreenWrapper()
                }
            }
        }
    }
}
