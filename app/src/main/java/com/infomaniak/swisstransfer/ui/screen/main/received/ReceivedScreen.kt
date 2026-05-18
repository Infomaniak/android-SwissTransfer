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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
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
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.SwissTransferTransition
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItemList
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.previewparameter.GroupedTransfersPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel.TransferUiState
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    hasTransfer: (Boolean) -> Unit,
    onDeleteTransfer: () -> Unit,
    transfersViewModel: TransfersViewModel = hiltViewModel<TransfersViewModel>(),
) {

    val uiState by transfersViewModel.receivedTransfersUiState.collectAsStateWithLifecycle()
    val sentTransfersAreEmpty by transfersViewModel.sentTransfersAreEmpty.collectAsStateWithLifecycle()

    hasTransfer((uiState as? TransferUiState.Success)?.data?.isNotEmpty() == true)

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.Received) }

    ReceivedScreen(
        uiState = { uiState },
        isFirstTransfer = { sentTransfersAreEmpty },
        navigateToDetails = navigateToDetails,
        getSelectedTransferUuid = getSelectedTransferUuid,
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
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    onDeleteTransfer: (String) -> Unit,
) {
    SwissTransferScaffold(
        topBar = {
            if (isWindowLarge()) {
                SwissTransferTopAppBar(title = stringResource(R.string.receivedFilesTitle))
            } else {
                BrandTopAppBar()
            }
        },
        floatingActionButton = {
            if (isWindowSmall()) ReceivedEmptyFab(isMessageVisible = { isFirstTransfer() })
        },
    ) {
        if (uiState() is TransferUiState.Success) {
            ReceivedContent(
                transfers = (uiState() as TransferUiState.Success).data,
                navigateToDetails = navigateToDetails,
                getSelectedTransferUuid = getSelectedTransferUuid,
                onDeleteTransfer = onDeleteTransfer,
            )
        }
    }
}

@Composable
private fun ReceivedContent(
    transfers: GroupedTransfers,
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    onDeleteTransfer: (String) -> Unit,
) {
    AnimatedContent(
        targetState = transfers.isEmpty(),
        transitionSpec = { SwissTransferTransition.enterTransition togetherWith SwissTransferTransition.exitTransition },
    ) { isEmpty ->
        if (isEmpty) {
            val shouldDisplayIcon = isWindowSmall()
            EmptyState(
                content = if (shouldDisplayIcon) {
                    { Image(imageVector = AppIllus.MascotSearching.image(), contentDescription = null) }
                } else null,
                titleRes = R.string.noTransferReceivedTitle,
                descriptionRes = R.string.noTransferReceivedDescription,
            )
        } else {
            TransferItemList(
                modifier = Modifier.fillMaxHeight(),
                direction = TransferDirection.RECEIVED,
                navigateToDetails = navigateToDetails,
                getSelectedTransferUuid = getSelectedTransferUuid,
                getTransfers = { transfers },
                onDeleteTransfer = onDeleteTransfer,
            )
        }
    }
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
                getSelectedTransferUuid = { null },
                onDeleteTransfer = {},
            )
        }
    }
}
