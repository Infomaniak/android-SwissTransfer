/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.lastTransferDataStore

@Composable
fun UploadSuccessScreen(
    transferType: TransferTypeUi,
    transferUuid: String,
    transferUrl: String,
    dismissCompleteUpload: () -> Unit,
) {
    val context = LocalContext.current

    // We saved the transfer UUID in case the transfer completes when the application
    // is killed, so that we can redirect the user to the UploadSuccessScreen.
    // Now that we are done redirecting, we clear the value stored.
    LaunchedEffect(Unit) {
        context.lastTransferDataStore.edit { it.clear() }
    }

    BackHandler(onBack = dismissCompleteUpload)

    if (transferType == TransferTypeUi.Mail) {
        UploadSuccessEmailScreen(transferUuid, dismissCompleteUpload)
    } else {
        UploadSuccessQrScreen(transferType, transferUrl, dismissCompleteUpload)
    }
}

@PreviewAllWindows
@Composable
private fun UploadSuccessScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadSuccessScreen(
                transferType = TransferTypeUi.QrCode,
                transferUuid = "",
                transferUrl = "https://chk.me/83azQOl",
                dismissCompleteUpload = {},
            )
        }
    }
}
