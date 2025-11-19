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
package com.infomaniak.swisstransfer.ui.screen.main.sent

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItemList
import com.infomaniak.swisstransfer.ui.previewparameter.GroupedTransfersPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel.TransferUiState
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun SentScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    transfersViewModel: TransfersViewModel = hiltViewModel<TransfersViewModel>(),
    hasTransfer: (Boolean) -> Unit,
    onDeleteTransfer: () -> Unit,
) {

    val uiState by transfersViewModel.sentTransfersUiState.collectAsStateWithLifecycle()

    hasTransfer((uiState as? TransferUiState.Success)?.data?.isNotEmpty() == true)

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.Sent) }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        transfersViewModel.fetchPendingTransfers()
    }

    SentScreen(
        uiState = { uiState },
        navigateToDetails = navigateToDetails,
        getSelectedTransferUuid = getSelectedTransferUuid,
        onDeleteTransfer = { transferUuid ->
            transfersViewModel.deleteTransfer(transferUuid)
            onDeleteTransfer()
        },
    )
}

@Composable
private fun SentScreen(
    uiState: () -> TransferUiState,
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    onDeleteTransfer: (String) -> Unit,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    SwissTransferScaffold(
        topBar = {
            if (windowAdaptiveInfo.isWindowLarge()) {
                SwissTransferTopAppBar(stringResource(R.string.sentFilesTitle))
            } else {
                BrandTopAppBar()
            }
        },
        floatingActionButton = {
            val hasTransfers = (uiState() as? TransferUiState.Success)?.data?.isNotEmpty() == true
            if (windowAdaptiveInfo.isWindowSmall() && hasTransfers) {
                NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
            }
        },
    ) {
        if (uiState() is TransferUiState.Success) {
            SentContent(
                transfers = (uiState() as TransferUiState.Success).data,
                navigateToDetails = navigateToDetails,
                getSelectedTransferUuid = getSelectedTransferUuid,
                onDeleteTransfer = onDeleteTransfer,
            )
        }
    }
}

@Composable
private fun SentContent(
    transfers: GroupedTransfers,
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    onDeleteTransfer: (String) -> Unit
) {
    if (transfers.isEmpty()) {
        SentEmptyScreen()
    } else {
        TransferItemList(
            modifier = Modifier.fillMaxHeight(),
            direction = TransferDirection.SENT,
            navigateToDetails = navigateToDetails,
            getSelectedTransferUuid = getSelectedTransferUuid,
            getTransfers = { transfers },
            onDeleteTransfer = onDeleteTransfer,
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(GroupedTransfersPreviewParameterProvider::class) transfers: GroupedTransfers) {
    SwissTransferTheme {
        Surface {
            SentScreen(
                uiState = { TransferUiState.Success(transfers) },
                navigateToDetails = {},
                getSelectedTransferUuid = { null },
                onDeleteTransfer = {},
            )
        }
    }
}
