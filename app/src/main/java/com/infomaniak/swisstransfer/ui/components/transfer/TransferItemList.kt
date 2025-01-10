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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SmallWindowScreenTitle
import com.infomaniak.swisstransfer.ui.components.SwipeToDismissComponent
import com.infomaniak.swisstransfer.ui.previewparameter.GroupedTransfersPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferItemList(
    direction: TransferDirection,
    getSelectedTransferUuid: () -> String?,
    getTransfers: () -> GroupedTransfers,
    onSwiped: (String) -> Unit,
    onClick: (TransferUi) -> Unit,
    contentPadding: PaddingValues = PaddingValues(),
) {

    val selectedTransferUuid = getSelectedTransferUuid()
    val itemShape = CustomShapes.SMALL
    val titleRes = when (direction) {
        TransferDirection.SENT -> R.string.sentFilesTitle
        TransferDirection.RECEIVED -> R.string.receivedFilesTitle
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Margin.Mini),
        contentPadding = contentPadding,
    ) {

        item { SmallWindowScreenTitle(title = stringResource(titleRes)) }

        getTransfers().forEach { (section, transfers) ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(SwissTransferTheme.materialColors.background)
                ) {
                    Text(
                        section.title(LocalContext.current),
                        style = SwissTransferTheme.typography.bodySmallRegular,
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        modifier = Modifier.padding(vertical = Margin.Mini)
                    )
                }
            }

            items(
                count = transfers.count(),
                key = { transfers[it].uuid },
                contentType = { transfers[it] },
                itemContent = {
                    val transfer = transfers[it]
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
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(GroupedTransfersPreviewParameterProvider::class) transfers: GroupedTransfers) {
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
