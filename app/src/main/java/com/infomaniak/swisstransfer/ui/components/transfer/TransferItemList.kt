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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@OptIn(ExperimentalMaterialApi::class)
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

                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        val shouldDismiss =
                            dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart
                        if (shouldDismiss) onSwiped(transfer.uuid) // TODO: We should probably add an animation here
                        shouldDismiss
                    },
                )

                // TODO: the "Using a material import while also using the material3 library"
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        val dismissDirection = dismissState.dismissDirection ?: return@SwipeToDismiss
                        val color by animateColorAsState(
                            targetValue = when (dismissState.targetValue) {
                                DismissValue.Default -> Color.LightGray
                                else -> SwissTransferTheme.materialColors.error
                            },
                            label = "Color animation",
                        )
                        val alignment = when (dismissDirection) {
                            DismissDirection.StartToEnd -> Alignment.CenterStart
                            DismissDirection.EndToStart -> Alignment.CenterEnd
                        }
                        val scale by animateFloatAsState(
                            targetValue = if (dismissState.targetValue == DismissValue.Default) 0.75f else 1.0f,
                            label = "Scale animation",
                        )

                        Card(
                            modifier = Modifier.fillMaxSize(),
                            shape = CustomShapes.SMALL,
                            backgroundColor = color,
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    modifier = Modifier.scale(scale),
                                    contentDescription = null,
                                )
                            }
                        }
                    },
                    dismissContent = {
                        Card(
                            elevation = animateDpAsState(
                                targetValue = if (dismissState.dismissDirection != null) 4.dp else 0.dp,
                                label = "Elevation animation",
                            ).value,
                            shape = CustomShapes.SMALL,
                        ) {
                            TransferItem(
                                transfer = transfer,
                                isSelected = { selectedTransferUuid == transfer.uuid },
                                onClick = { onClick(transfer) },
                            )
                        }
                    },
                )
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
