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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.infomaniak.core.inappreview.reviewmanagers.InAppReviewManager
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation

@Composable
fun NewTransferScreen(
    startDestination: NewTransferNavigation,
    inAppReviewManager: InAppReviewManager,
    closeActivity: (startMainActivityIfTaskIsEmpty: Boolean) -> Unit,
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
) {
    NewTransferNavHost(
        navController = rememberNavController(),
        startDestination = startDestination,
        inAppReviewManager = inAppReviewManager,
        closeActivity = closeActivity,
        cancelUploadNotification = { newTransferViewModel.cancelUploadNotification() }
    )
}
