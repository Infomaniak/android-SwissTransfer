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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.isExpired

@Composable
fun TransfersListWithExpiredBottomSheet(
    direction: TransferDirection,
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    getTransfers: () -> List<TransferUi>,
    onDeleteTransfer: (String) -> Unit,
) {

    var expiredTransfer: TransferUi? by rememberSaveable { mutableStateOf(null) }

    TransferItemList(
        direction = direction,
        getSelectedTransferUuid = getSelectedTransferUuid,
        getTransfers = getTransfers,
        onSwiped = onDeleteTransfer,
        onClick = { transfer ->
            if (transfer.isExpired) {
                expiredTransfer = transfer
            } else {
                navigateToDetails(transfer.uuid)
            }
        },
        contentPadding = PaddingValues(Margin.Medium),
    )

    TransferExpiredBottomSheet(
        expiredTransfer = { expiredTransfer },
        onDeleteTransferClicked = { expiredTransfer?.uuid?.let(onDeleteTransfer) },
        closeBottomSheet = { expiredTransfer = null },
    )
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            TransfersListWithExpiredBottomSheet(
                direction = TransferDirection.RECEIVED,
                navigateToDetails = {},
                getSelectedTransferUuid = { null },
                getTransfers = { transfers },
                onDeleteTransfer = {},
            )
        }
    }
}
