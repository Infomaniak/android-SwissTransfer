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

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalLookaheadAnimationVisualDebugApi
import androidx.compose.animation.LookaheadAnimationVisualDebugging
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.components.SwissTransferTransition
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.MyAccountDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination.Companion.receivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination.Companion.sentDestination
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountScreenWrapper
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersScreenWrapper

@OptIn(ExperimentalLookaheadAnimationVisualDebugApi::class)
@SuppressLint("DisallowLookaheadAnimationVisualDebug")
@Composable
fun MainNavHost(
    navController: NavHostController,
    deeplinkTransferDirection: TransferDirection?,
    hideBottomBar: MutableState<Boolean>,
) {

    val startDestination = when (deeplinkTransferDirection) {
        TransferDirection.SENT -> SentDestination()
        TransferDirection.RECEIVED -> ReceivedDestination()
        else -> MainNavigation.startDestination
    }

    LookaheadAnimationVisualDebugging(
        overlayColor = Color(0x4AE91E63),
        isEnabled = true,
        multipleMatchesColor = Color.Green,
        isShowKeyLabelEnabled = false,
        unmatchedElementColor = Color.Red,
    ) {
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
                        CompositionLocalProvider(
                            LocalAnimatedVisibilityScope provides this@sentDestination,
                            LocalNavHostAnimatedVisibilityScope provides this@sentDestination
                        ) {
                            TransfersScreenWrapper(
                                TransferDirection.SENT,
                                transferUuid = args.transferUuid,
                                hideBottomBar = hideBottomBar
                            )
                        }
                    }
                    receivedDestination {
                        val args = it.toRoute<ReceivedDestination>()
                        CompositionLocalProvider(
                            LocalAnimatedVisibilityScope provides this@receivedDestination,
                            LocalNavHostAnimatedVisibilityScope provides this@receivedDestination
                        ) {
                            TransfersScreenWrapper(
                                direction = TransferDirection.RECEIVED,
                                transferUuid = args.transferUuid,
                                isApiV2Deeplink = args.isApiV2,
                                hideBottomBar = hideBottomBar,
                            )
                        }
                    }
                    composable<MyAccountDestination> {
                        CompositionLocalProvider(
                            LocalAnimatedVisibilityScope provides this@composable,
                            LocalNavHostAnimatedVisibilityScope provides this@composable
                        ) {
                            MyAccountScreenWrapper()
                        }
                    }
                }
            }
        }
    }
}

val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope?> { null }
val LocalAnimatedVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalNavHostAnimatedVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
