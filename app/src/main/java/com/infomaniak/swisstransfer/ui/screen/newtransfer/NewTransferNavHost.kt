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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.infomaniak.core.inappreview.reviewmanagers.InAppReviewManager
import com.infomaniak.core.permissionmanager.rationale.RationalePermissionManagerState
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.NewTransferFilesDetailsDestination
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.PickFilesDestination
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.UploadDestination
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.UploadSuccessDestination
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.NewTransferFilesDetailsScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadSuccessScreen
import com.infomaniak.swisstransfer.ui.utils.LocalSharedTransitionScope
import com.infomaniak.swisstransfer.ui.utils.SwissTransferTransition
import com.infomaniak.swisstransfer.ui.utils.animatedComposable

@Composable
fun NewTransferNavHost(
    navController: NavHostController,
    startDestination: NewTransferNavigation,
    inAppReviewManager: InAppReviewManager,
    notificationPermissionManager: RationalePermissionManagerState,
    closeActivity: (startMainActivityIfTaskIsEmpty: Boolean) -> Unit,
    cancelUploadNotification: () -> Unit,
) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this@SharedTransitionLayout) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition = { SwissTransferTransition.enterTransition },
                exitTransition = { SwissTransferTransition.exitTransition },
            ) {
                animatedComposable<PickFilesDestination> {
                    cancelUploadNotification()
                    PickFilesScreen(
                        pickFilesViewModel = hiltViewModel<PickFilesViewModel>(it),
                        notificationPermissionManager = notificationPermissionManager,
                        exitNewTransfer = { closeActivity(true) },
                        navigateToUploadProgress = { navController.navigate(UploadDestination) },
                        navigateToFilesDetails = { navController.navigate(NewTransferFilesDetailsDestination) },
                    )
                }
                animatedComposable<UploadDestination> {
                    UploadScreen(
                        inAppReviewManager = inAppReviewManager,
                        navigateBackToPickFiles = {
                            navController.popBackStack(
                                route = PickFilesDestination,
                                inclusive = false,
                            )
                        },
                        exitNewTransfer = { closeActivity(true) },
                    )
                }
                animatedComposable<UploadSuccessDestination> {
                    // The transfer has succeeded so we decrement the review countdown
                    inAppReviewManager.decrementAppReviewCountdown()

                    val args = it.toRoute<UploadSuccessDestination>()
                    UploadSuccessScreen(
                        transferType = args.transferType,
                        transferUuid = args.transferUuid,
                        transferUrl = args.transferUrl,
                        closeActivity = { closeActivity(true) },
                    )
                }
                animatedComposable<NewTransferFilesDetailsDestination> {
                    val backStackEntry = remember(it) { navController.getBackStackEntry(PickFilesDestination) }
                    NewTransferFilesDetailsScreen(
                        pickFilesViewModel = hiltViewModel<PickFilesViewModel>(backStackEntry),
                        withFilesSize = true,
                        withSpaceLeft = true,
                        withFileDelete = true,
                        navigateBack = { navController.navigateUp() },
                    )
                }
            }
        }
    }
}
