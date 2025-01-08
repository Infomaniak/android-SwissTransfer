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
package com.infomaniak.swisstransfer.ui.screen.main.transfers

import FilesDetailsScreen
import android.os.Parcelable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.components.safeCurrentContent
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.ReceivedScreen
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsScreen
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TransfersScreenWrapper(
    direction: TransferDirection,
    transferUuid: String? = null,
    navigateToFilesDetails: (fileUuid: String) -> Unit,
) {
    var hasTransfer: Boolean by rememberSaveable { mutableStateOf(false) }

    TwoPaneScaffold<DestinationContent>(
        listPane = {
            val transfersViewModel = hiltViewModel<TransfersViewModel>()
            val isDeepLinkConsumed by transfersViewModel.isDeepLinkConsumed.collectAsStateWithLifecycle()
            handleDeepLink(
                transferUuid = transferUuid,
                isDeepLinkConsumed = { isDeepLinkConsumed },
                consumeDeepLink = transfersViewModel::consumeDeepLink,
                direction = direction,
            )
            ListPane(
                direction = direction,
                navigator = this,
                updateHasTransfer = { hasTransfer = it },
                transfersViewModel = transfersViewModel
            )
        },
        detailPane = {
            DetailPane(navigator = this, hasTransfer = { hasTransfer })
        },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.handleDeepLink(
    transferUuid: String?,
    isDeepLinkConsumed: () -> Boolean,
    consumeDeepLink: () -> Unit,
    direction: TransferDirection,
) {
    if (transferUuid != null && !isDeepLinkConsumed()) {
        consumeDeepLink()
        navigateToDetails(direction, transferUuid)
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(
    direction: TransferDirection,
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    transfersViewModel: TransfersViewModel,
    updateHasTransfer: (Boolean) -> Unit,
) {
    when (direction) {
        TransferDirection.SENT -> SentScreen(
            navigateToDetails = { transferUuid -> navigator.navigateToDetails(direction, transferUuid) },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
        )
        TransferDirection.RECEIVED -> ReceivedScreen(
            navigateToDetails = { transferUuid -> navigator.navigateToDetails(direction, transferUuid) },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToDetails(
    direction: TransferDirection,
    transferUuid: String,
) {
    navigateTo(ListDetailPaneScaffoldRole.Detail, DestinationContent(direction, transferUuid))
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToFilesDetails(folderUuid: String) {
    navigateTo(ListDetailPaneScaffoldRole.Detail, DestinationContent(folderUuid = folderUuid))
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.getSelectedTransferUuid(): String? {
    return currentDestination?.content?.transferUuid
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    hasTransfer: () -> Boolean,
) {
    val destinationContent = navigator.safeCurrentContent()

    if (destinationContent == null) {
        NoSelectionEmptyState(hasTransfer())
    } else {

        if (destinationContent.folderUuid != null) {
            FilesDetailsScreen(navigator, destinationContent.folderUuid) {
                navigator.navigateToFilesDetails(it)
            }
        } else {
            TransferDetailsScreen(
                transferUuid = destinationContent.transferUuid ?: "", //TODO to modified
                direction = destinationContent.direction ?: TransferDirection.RECEIVED, //TODO to modified
                navigateBack = ScreenWrapperUtils.getBackNavigation(navigator),
                navigateToFilesDetails = {
                    navigator.navigateToFilesDetails(it)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun FilesDetailsScreen(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    folderUuid: String,
    navigateToFilesDetails: (fileUuid: String) -> Unit,
) {
    FilesDetailsScreen(
        navigateToDetails = { folderUuid ->
            navigateToFilesDetails(folderUuid)
        },
        folderUuid = folderUuid,
        navigateBack = {
            navigator.navigateBack()
        },
        close = {
            //closeFilesDetails()
        },
        withFilesSize = false,
        withSpaceLeft = false,
        withFileDelete = false,
    )
}

@Composable
private fun NoSelectionEmptyState(hasTransfers: Boolean) {
    val (titleRes, descriptionRes) = if (hasTransfers) {
        R.string.noTransferSelectedTitle to R.string.noTransferSelectedDescription
    } else {
        null to null
    }

    SwissTransferScaffold(
        topBar = { SwissTransferTopAppBar(title = "") }
    ) {
        EmptyState(
            icon = AppIllus.MascotWithMagnifyingGlass,
            titleRes = titleRes,
            descriptionRes = descriptionRes
        )
    }
}

@Parcelize
private data class DestinationContent(
    val direction: TransferDirection? = null,
    val transferUuid: String? = null,
    val folderUuid: String? = null,
) : Parcelable

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TransfersScreenWrapper(TransferDirection.RECEIVED, transferUuid = null, navigateToFilesDetails = { _ -> })
        }
    }
}
