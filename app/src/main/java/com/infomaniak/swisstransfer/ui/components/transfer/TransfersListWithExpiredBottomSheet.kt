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
package com.infomaniak.swisstransfer.ui.components.transfer

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.previewparameter.transfersPreviewData
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import java.util.Date

@Composable
fun TransfersListWithExpiredBottomSheet(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
) {

    var isExpirySheetVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    var expirationDate: Date? by rememberSaveable { mutableStateOf(null) }
    var downloadsLimit: Int? by rememberSaveable { mutableStateOf(null) }

    TransferItemList(
        modifier = Modifier.padding(Margin.Medium),
        transfers = transfersPreviewData, // TODO: Use real data
        getSelectedTransferUuid = getSelectedTransferUuid,
        onClick = { transfer ->
            when {
                transfer.expiresInDays < 0 -> {
                    isExpirySheetVisible = true
                    expirationDate = Date(transfer.expirationDateTimestamp)
                }
                transfer.downloadLeft == 0 -> {
                    isExpirySheetVisible = true
                    downloadsLimit = transfer.downloadLimit
                }
                else -> {
                    navigateToDetails(transfer.uuid)
                }
            }
        }
    )

    TransferExpiredBottomSheet(
        isVisible = { isExpirySheetVisible },
        expirationDate = { expirationDate },
        downloadsLimit = { downloadsLimit },
        onDeleteTransferClicked = { Log.d("TODO", "Delete expired Transfer") }, // TODO
        closeBottomSheet = {
            isExpirySheetVisible = false
            expirationDate = null
            downloadsLimit = null
        },
    )
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            TransfersListWithExpiredBottomSheet(
                navigateToDetails = {},
                getSelectedTransferUuid = { null },
            )
        }
    }
}
