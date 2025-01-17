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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.SmallButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun NewTransferScreen(
    startDestination: NewTransferNavigation,
    closeActivity: (startMainActivityIfTaskIsEmpty: Boolean) -> Unit,
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
) {

    val navController = rememberNavController()
    var displayConfirmationDialog by rememberSaveable { mutableStateOf(false) }

    NewTransferNavHost(
        navController = navController,
        startDestination = startDestination,
        closeActivity = closeActivity,
        closeActivityAndPromptForValidation = { displayConfirmationDialog = true },
        cancelFailureNotification = { newTransferViewModel.cancelFailureNotification() }
    )

    if (displayConfirmationDialog) {
        ConfirmLeavingDialog(
            onLeave = { closeActivity(false) },
            onCancel = { displayConfirmationDialog = false },
        )
    }
}

@Composable
fun ConfirmLeavingDialog(onLeave: () -> Unit, onCancel: () -> Unit) {
    SwissTransferAlertDialog(
        titleRes = R.string.newTransferConfirmLeavingDialogTitle,
        descriptionRes = R.string.newTransferLeavingDialogDescription,
        positiveButton = {
            SmallButton(
                title = stringResource(R.string.newTransferLeavingDialogPositiveButton),
                style = ButtonType.DestructiveText,
                onClick = onLeave,
            )
        },
        negativeButton = {
            SmallButton(
                title = stringResource(R.string.newTransferLeavingDialogNegativeButton),
                style = ButtonType.Tertiary,
                onClick = onCancel,
            )
        },
        onDismiss = onCancel,
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        ConfirmLeavingDialog({}, {})
    }
}
