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
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.ImportFilesScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferOptionsScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferTypeScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.ValidateUserEmailScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadProgressScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadSuccessScreen

@Composable
fun NewTransferNavHost(navController: NavHostController) {

    NavHost(navController, NewTransferNavigation.startDestination) {
        composable<ImportFilesDestination> {
            ImportFilesScreen(navigateToTransferTypeScreen = { navController.navigate(TransferTypeDestination) })
        }
        composable<TransferTypeDestination> {
            TransferTypeScreen()
        }
        composable<TransferOptionsDestination> {
            TransferOptionsScreen()
        }
        composable<ValidateUserEmailDestination> {
            ValidateUserEmailScreen()
        }
        composable<UploadProgressDestination> {
            UploadProgressScreen()
        }
        composable<UploadSuccessDestination> {
            UploadSuccessScreen()
        }
    }

}
