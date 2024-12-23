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

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.ImportFilesScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.ValidateUserEmailScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadErrorScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadProgressScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadSuccessScreen

@Composable
fun NewTransferNavHost(navController: NavHostController, closeActivity: () -> Unit) {

    NavHost(navController, NewTransferNavigation.startDestination) {
        composable<ImportFilesDestination> {
            ImportFilesScreen(
                closeActivity = closeActivity,
                navigateToUploadProgress = { transferType, totalSize, recipients ->
                    navController.navigate(UploadProgressDestination(transferType, totalSize, recipients))
                },
            )
        }
        composable<ValidateUserEmailDestination> {
            ValidateUserEmailScreen()
        }
        composable<UploadProgressDestination> {
            val args = it.toRoute<UploadProgressDestination>()
            UploadProgressScreen(
                totalSizeInBytes = args.totalSize,
                navigateToUploadSuccess = { transferUrl ->
                    navController.navigate(UploadSuccessDestination(args.transferType, transferUrl, args.recipients))
                },
                navigateToUploadError = { navController.navigate(UploadErrorDestination) },
                navigateBackToImportFiles = { navController.popBackStack(route = ImportFilesDestination, inclusive = false) },
            )
        }
        composable<UploadSuccessDestination> {
            val args = it.toRoute<UploadSuccessDestination>()
            UploadSuccessScreen(
                transferType = args.transferType,
                transferUrl = args.transferUrl,
                recipients = args.recipients,
                closeActivity = closeActivity,
            )
        }
        composable<UploadErrorDestination> {
            UploadErrorScreen(navigateToImportFiles = { navController.navigate(ImportFilesDestination) })
        }
    }
}
