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

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.transfer.TransfersListWithExpiredBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersViewModel
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import java.util.Date

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    transfersViewModel: TransfersViewModel = hiltViewModel<TransfersViewModel>(),
    hasTransfer: (Boolean) -> Unit,
) {

    val transfers by transfersViewModel.receivedTransfers.collectAsStateWithLifecycle()
    val isLoading by remember { derivedStateOf { transfers == null } }

    hasTransfer(transfers?.isNotEmpty() == true)

    if (!isLoading) {
        ReceivedScreen(
            navigateToDetails = navigateToDetails,
            getSelectedTransferUuid = getSelectedTransferUuid,
            getTransfers = { transfers!! },
            onDeleteTransfer = transfersViewModel::deleteTransfer,
        )
    }
}

@Composable
private fun ReceivedScreen(
    navigateToDetails: (transferUuid: String) -> Unit,
    getSelectedTransferUuid: () -> String?,
    getTransfers: () -> List<TransferUi>,
    onDeleteTransfer: (String) -> Unit,
) {

    val areTransfersEmpty by remember { derivedStateOf { getTransfers().isEmpty() } }
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    SwissTransferScaffold(
        topBar = {
            val title = stringResource(R.string.receivedFilesTitle)
            if (windowAdaptiveInfo.isWindowLarge()) SwissTransferTopAppBar(title) else BrandTopAppBar()
        },
        floatingActionButton = {
            if (windowAdaptiveInfo.isWindowSmall()) ReceivedEmptyFab { areTransfersEmpty }
        },
    ) {
        if (areTransfersEmpty) {
            val shouldDisplayIcon = LocalWindowAdaptiveInfo.current.isWindowSmall()
            EmptyState(
                icon = if (shouldDisplayIcon) AppIllus.MascotWithMagnifyingGlass else null,
                titleRes = R.string.noTransferReceivedTitle,
                descriptionRes = R.string.noTransferReceivedDescription,
            )
        } else {
            TransfersListWithExpiredBottomSheet(
                direction = TransferDirection.RECEIVED,
                navigateToDetails = navigateToDetails,
                getSelectedTransferUuid = getSelectedTransferUuid,
                getTransfers = getTransfers,
                onDeleteTransfer = onDeleteTransfer,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(
                navigateToDetails = {},
                getSelectedTransferUuid = { null },
                getTransfers = { transfers },
                onDeleteTransfer = {},
            )
        }
    }
}
