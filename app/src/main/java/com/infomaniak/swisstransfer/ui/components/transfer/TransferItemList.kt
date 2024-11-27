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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwipeToDismissComponent
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferItemList(
    modifier: Modifier = Modifier,
    direction: TransferDirection,
    getSelectedTransferUuid: () -> String?,
    getTransfers: () -> List<TransferUi>,
    onSwiped: (String) -> Unit,
    onClick: (TransferUi) -> Unit,
) {

    val selectedTransferUuid = getSelectedTransferUuid()
    val titleRes = when (direction) {
        TransferDirection.SENT -> R.string.sentFilesTitle
        TransferDirection.RECEIVED -> R.string.receivedFilesTitle
    }
    val itemShape = CustomShapes.SMALL

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Margin.Medium),
        contentPadding = PaddingValues(top = Margin.Mini),
    ) {

        item { Text(text = stringResource(titleRes), style = SwissTransferTheme.typography.h1) }

        items(
            count = getTransfers().count(),
            key = { getTransfers()[it].uuid },
            contentType = { getTransfers()[it] },
            itemContent = {
                val transfer = getTransfers()[it]
                SwipeToDismissComponent(
                    contentShape = itemShape,
                    onSwiped = { onSwiped(transfer.uuid) },
                ) {
                    TransferItem(
                        transfer = transfer,
                        shape = itemShape,
                        isSelected = { selectedTransferUuid == transfer.uuid },
                        onClick = { onClick(transfer) },
                    )
                }
            },
        )
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            TransferItemList(
                direction = TransferDirection.SENT,
                getSelectedTransferUuid = { null },
                getTransfers = { transfers },
                onSwiped = {},
                onClick = {},
            )
        }
    }
}
