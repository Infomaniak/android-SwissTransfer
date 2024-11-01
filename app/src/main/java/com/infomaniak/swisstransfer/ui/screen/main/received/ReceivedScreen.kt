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
package com.infomaniak.swisstransfer.ui.screen.main.received

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.transfer.TransferExpiredBottomSheet
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItemList
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotSearching
import com.infomaniak.swisstransfer.ui.previewparameter.transfersPreviewData
import com.infomaniak.swisstransfer.ui.screen.main.components.BrandTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import java.util.Date

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    sentViewModel: SentViewModel = hiltViewModel<SentViewModel>(),
) {
    val transfers by sentViewModel.transfers.collectAsStateWithLifecycle()
    val areTransfersEmpty by remember { derivedStateOf { transfers?.isEmpty() == true } }

    ReceivedScreen(
        navigateToDetails = navigateToDetails,
        getSelectedTransferUuid = getSelectedTransferUuid,
        areTransfersEmpty = { areTransfersEmpty },
    )
}

@Composable
private fun ReceivedScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    areTransfersEmpty: () -> Boolean,
) {

    var isVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    var expirationDate: Date? by rememberSaveable { mutableStateOf(null) }
    var downloadsLimit: Int? by rememberSaveable { mutableStateOf(null) }

    BrandTopAppBarScaffold(
        floatingActionButton = { ReceivedEmptyFab(areTransfersEmpty) },
    ) {
        if (areTransfersEmpty()) {
            EmptyState(
                icon = AppIllus.MascotSearching,
                title = R.string.noTransferReceivedTitle,
                description = stringResource(R.string.noTransferReceivedDescription),
            )
        } else {
            TransferItemList(
                modifier = Modifier.padding(Margin.Medium),
                transfers = transfersPreviewData, // TODO: Use real data
                getSelectedTransferUuid = getSelectedTransferUuid,
                onClick = { transfer ->
                    when {
                        transfer.expiresInDays < 0 -> {
                            isVisible = true
                            expirationDate = Date(transfer.expirationDateTimestamp)
                        }
                        transfer.downloadLeft == 0 -> {
                            isVisible = true
                            downloadsLimit = transfer.downloadLimit
                        }
                        else -> {
                            navigateToDetails(transfer.uuid)
                        }
                    }
                }
            )

            TransferExpiredBottomSheet(
                isVisible = { isVisible },
                expirationDate = { expirationDate },
                downloadsLimit = { downloadsLimit },
                onDeleteTransferClicked = {
                    Log.d("TODO", "Delete expired Transfer")
                    // TODO
                },
                closeBottomSheet = {
                    isVisible = false
                    expirationDate = null
                    downloadsLimit = null
                },
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(
                navigateToDetails = {},
                getSelectedTransferUuid = { null },
                areTransfersEmpty = { true },
            )
        }
    }
}
