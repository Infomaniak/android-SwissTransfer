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
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.NewTransferFilesDetailsScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.AutoResetTransferDataAvailabilityStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.ImportFilesScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadErrorScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadIntegrityErrorScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadProgressScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadSuccessScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.ValidateUserEmailScreen
import io.sentry.Sentry
import io.sentry.SentryLevel

@Composable
fun NewTransferNavHost(
    navController: NavHostController,
    startDestination: NewTransferNavigation,
    closeActivity: (startMainActivityIfTaskIsEmpty: Boolean) -> Unit,
    closeActivityAndPromptForValidation: () -> Unit,
    cancelUploadNotification: () -> Unit,
) {

    AutoResetTransferDataAvailabilityStatus()

    NavHost(navController, startDestination) {
        composable<ImportFilesDestination> {
            cancelUploadNotification()
            ImportFilesScreen(
                importFilesViewModel = hiltViewModel<ImportFilesViewModel>(it),
                closeActivity = { closeActivity(false) },
                navigateToUploadProgress = { transferType, totalSize, authorEmail ->
                    navController.navigate(UploadProgressDestination(transferType, totalSize, authorEmail))
                },
                navigateToFilesDetails = { navController.navigate(NewTransferFilesDetailsDestination) },
            )
        }
        composable<ValidateUserEmailDestination> {
            val args = it.toRoute<ValidateUserEmailDestination>()
            ValidateUserEmailScreen(
                closeActivity = closeActivityAndPromptForValidation,
                navigateBackToFileImportation = { navController.popBackStack(ImportFilesDestination, false) },
                navigateToUploadInProgress = { totalSize ->
                    navController.navigate(UploadProgressDestination(TransferTypeUi.Mail, totalSize, args.authorEmail))
                },
                emailToValidate = args.authorEmail,
            )
        }
        composable<UploadProgressDestination> {
            val args = it.toRoute<UploadProgressDestination>()
            UploadProgressScreen(
                totalSizeInBytes = args.totalSize,
                navigateToUploadSuccess = { transferUuid, transferUrl ->
                    navController.navigate(UploadSuccessDestination(args.transferType, transferUuid, transferUrl))
                },
                navigateToUploadError = {
                    navController.navigate(UploadErrorDestination(args.transferType, args.totalSize, args.authorEmail))
                },
                navigateToEmailValidation = { navController.navigateToEmailValidation(args) },
                navigateToAppIntegrityError = { navController.navigate(UploadIntegrityErrorDestination) },
                navigateBackToImportFiles = { navController.popBackStack(route = ImportFilesDestination, inclusive = false) },
                // Called instead of `navigateBackToImportFiles()` when the app has been killed while uploading
                closeActivity = { closeActivity(false) },
            )
        }
        composable<UploadSuccessDestination> {
            cancelUploadNotification()
            val args = it.toRoute<UploadSuccessDestination>()
            UploadSuccessScreen(
                transferType = args.transferType,
                transferUuid = args.transferUuid,
                transferUrl = args.transferUrl,
                closeActivity = { closeActivity(true) },
            )
        }
        composable<UploadErrorDestination> {
            cancelUploadNotification()
            val args = it.toRoute<UploadErrorDestination>()
            UploadErrorScreen(
                navigateBackToUploadProgress = {
                    val hasPoppedBack = navController.popBackStack(
                        route = UploadProgressDestination(args.transferType, args.totalSize, args.authorEmail),
                        inclusive = false,
                    )
                    if (!hasPoppedBack) {
                        navController.navigate(UploadProgressDestination(args.transferType, args.totalSize, args.authorEmail))
                        Sentry.captureMessage(
                            "PopBackStack to retry transfer after error has failed",
                            SentryLevel.ERROR,
                        ) { scope ->
                            scope.setExtra("transferType", args.transferType.toString())
                            scope.setExtra("totalSize", args.totalSize.toString())
                        }
                    }
                },
                navigateBackToImportFiles = { navController.popBackStack(route = ImportFilesDestination, inclusive = false) },
                // Called instead of `navigateBackToImportFiles()` when the app has been killed while uploading
                closeActivity = { closeActivity(true) },
            )
        }
        composable<UploadIntegrityErrorDestination> {
            UploadIntegrityErrorScreen(closeActivity = { closeActivity(false) })
        }
        composable<NewTransferFilesDetailsDestination> {
            val backStackEntry = remember(it) { navController.getBackStackEntry(ImportFilesDestination) }
            NewTransferFilesDetailsScreen(
                importFilesViewModel = hiltViewModel<ImportFilesViewModel>(backStackEntry),
                withFilesSize = true,
                withSpaceLeft = true,
                withFileDelete = true,
                navigateBack = { navController.navigateUp() },
            )
        }
    }
}

private fun NavHostController.navigateToEmailValidation(args: UploadProgressDestination) {
    args.authorEmail?.let { userEmail ->
        navigate(ValidateUserEmailDestination(userEmail))
    } ?: run {
        Sentry.captureMessage(
            "Tried navigating to email validation but we received no email to validate",
            SentryLevel.WARNING,
        )
    }
}
