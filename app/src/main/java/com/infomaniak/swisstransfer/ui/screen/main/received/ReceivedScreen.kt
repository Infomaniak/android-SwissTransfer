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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItemList
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.TransferIdType
import com.infomaniak.swisstransfer.ui.previewparameter.GroupedTransfersPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel.TransferUiState
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferIdType: TransferIdType) -> Unit,
    getSelectedTransferIdType: () -> TransferIdType?,
    hasTransfer: (Boolean) -> Unit,
    onDeleteTransfer: () -> Unit,
    transfersViewModel: TransfersViewModel = hiltViewModel<TransfersViewModel>(),
) {

    val uiState by transfersViewModel.receivedTransfersUiState.collectAsStateWithLifecycle()
    val hasNoTransfers by transfersViewModel.allTransfersAreEmpty.collectAsStateWithLifecycle()

    hasTransfer((uiState as? TransferUiState.Success)?.data?.isNotEmpty() == true)

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.Received) }

    ReceivedScreen(
        uiState = { uiState },
        isFirstTransfer = { hasNoTransfers },
        navigateToDetails = navigateToDetails,
        getSelectedTransferIdType = getSelectedTransferIdType,
        onDeleteTransfer = { transferUuid ->
            transfersViewModel.deleteTransfer(transferUuid)
            onDeleteTransfer()
        },
    )
}

@Composable
private fun ReceivedScreen(
    uiState: () -> TransferUiState,
    isFirstTransfer: () -> Boolean,
    navigateToDetails: (transferIdType: TransferIdType) -> Unit,
    getSelectedTransferIdType: () -> TransferIdType?,
    onDeleteTransfer: (String) -> Unit,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    SwissTransferScaffold(
        topBar = {
            if (windowAdaptiveInfo.isWindowLarge()) {
                SwissTransferTopAppBar(title = stringResource(R.string.receivedFilesTitle))
            } else {
                BrandTopAppBar()
            }
        },
        floatingActionButton = {
            if (isWindowSmall()) ReceivedEmptyFab(isMessageVisible = { isFirstTransfer() })
        },
    ) {
        (uiState() as? TransferUiState.Success)?.let { transferUiStateSuccess ->
            TransferItemList(
                modifier = Modifier.fillMaxHeight(),
                navigateToDetails = navigateToDetails,
                getSelectedTransferIdType = getSelectedTransferIdType,
                getTransfers = { transferUiStateSuccess.data },
                onDeleteTransfer = onDeleteTransfer,
                title = stringResource(R.string.receivedFilesTitle),
                emptyState = { ReceivedEmptyScreen() },
            )
        }
    }
}

@Composable
private fun ReceivedEmptyScreen() {
    val shouldDisplayIcon = isWindowSmall()
    EmptyState(
        content = if (shouldDisplayIcon) {
            { Image(imageVector = AppIllus.MascotSearching.image(), contentDescription = null) }
        } else null,
        titleRes = R.string.noTransferReceivedTitle,
        descriptionRes = R.string.noTransferReceivedDescription,
    )
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(GroupedTransfersPreviewParameterProvider::class) transfers: GroupedTransfers) {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(
                uiState = { TransferUiState.Success(transfers) },
                isFirstTransfer = { true },
                navigateToDetails = {},
                getSelectedTransferIdType = { null },
                onDeleteTransfer = {},
            )
        }
    }
}
