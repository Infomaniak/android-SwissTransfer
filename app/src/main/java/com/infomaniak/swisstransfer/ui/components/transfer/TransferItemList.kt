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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SmallOrMediumWindowScreenTitle
import com.infomaniak.swisstransfer.ui.components.SwipeToDismissComponent
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.TransferIdType
import com.infomaniak.swisstransfer.ui.previewparameter.GroupedTransfersPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferItemList(
    title: String,
    navigateToDetails: (transferIdType: TransferIdType) -> Unit,
    getSelectedTransferIdType: () -> TransferIdType?,
    getTransfers: () -> GroupedTransfers,
    onDeleteTransfer: (String) -> Unit,
    modifier: Modifier = Modifier,
    emptyState: @Composable () -> Unit,
) {
    AnimatedContent(
        targetState = getTransfers().isEmpty(),
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { isEmpty ->
        if (isEmpty) {
            emptyState()
        } else {
            TransferItemList(
                title = title,
                navigateToDetails = navigateToDetails,
                getSelectedTransferIdType = getSelectedTransferIdType,
                onDeleteTransfer = onDeleteTransfer,
                modifier = modifier,
                getTransfers = getTransfers,
            )
        }
    }
}

@Composable
private fun TransferItemList(
    title: String,
    navigateToDetails: (TransferIdType) -> Unit,
    getSelectedTransferIdType: () -> TransferIdType?,
    getTransfers: () -> GroupedTransfers,
    onDeleteTransfer: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedTransferIdType = getSelectedTransferIdType()
    val itemShape = CustomShapes.SMALL
    // stickyHeader seems to over-remember, causing theme to not be applied.
    // Hoisting it outside of the LazyColumn fixes it.
    val stickyHeaderBackground = SwissTransferTheme.materialColors.background

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Margin.Mini),
        contentPadding = PaddingValues(top = Margin.Large, bottom = Margin.Medium, start = Margin.Medium, end = Margin.Medium),
    ) {

        item { SmallOrMediumWindowScreenTitle(title = title) }

        getTransfers().forEach { (section, transfers) ->
            stickyHeader(key = section.uid) {
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(stickyHeaderBackground)
                        .animateItem()
                ) {
                    // Needed for section.title below, because the context stays the same on configuration changes.
                    // Reading this makes sure composition happens.
                    LocalConfiguration.current

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
                        modifier = Modifier.animateItem(),
                        contentShape = itemShape,
                        onSwiped = { onDeleteTransfer(transfer.uuid) },
                    ) {
                        TransferItem(
                            transfer = transfer,
                            shape = itemShape,
                            isSelected = {
                                when (selectedTransferIdType) {
                                    is TransferIdType.TransferId -> selectedTransferIdType.value == transfer.uuid
                                    is TransferIdType.LinkId -> selectedTransferIdType.value == transfer.linkId
                                    else -> false
                                }
                            },
                            onClick = { navigateToDetails(TransferIdType.TransferId(transfer.uuid)) },
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
                title = stringResource(R.string.sentFilesTitle),
                navigateToDetails = {},
                getSelectedTransferIdType = { null },
                getTransfers = { transfers },
                onDeleteTransfer = {},
                emptyState = {},
            )
        }
    }
}
