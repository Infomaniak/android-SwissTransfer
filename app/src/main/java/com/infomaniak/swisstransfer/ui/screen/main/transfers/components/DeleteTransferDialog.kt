/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.main.transfers.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.SmallButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialogDefaults
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun DeleteTransferDialog(
    closeAlertDialog: () -> Unit,
    onConfirmation: () -> Unit,
) {

    SwissTransferAlertDialog(
        title = stringResource(R.string.deleteThisTransferTitle),
        description = stringResource(R.string.deleteThisTransferDescription),
        onDismiss = closeAlertDialog,
        positiveButton = {
            SmallButton(
                stringResource(R.string.buttonDeleteYes),
                onClick = { onConfirmation() },
                style = ButtonType.Destructive,
            )
        },
        negativeButton = { SwissTransferAlertDialogDefaults.CancelButton(onClick = closeAlertDialog) },
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            DeleteTransferDialog(
                closeAlertDialog = {},
                onConfirmation = {},
            )
        }
    }
}
