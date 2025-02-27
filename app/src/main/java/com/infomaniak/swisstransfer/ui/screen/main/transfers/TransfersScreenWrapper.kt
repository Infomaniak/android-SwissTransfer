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

import android.content.Context
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.ReceivedScreen
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.ExistingTransferFilesDetailsScreen
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TransfersScreenWrapper(
    direction: TransferDirection,
    transferUuid: String? = null,
    hideBottomBar: MutableState<Boolean>,
) {
    var hasTransfer: Boolean by rememberSaveable { mutableStateOf(false) }

    TwoPaneScaffold<DestinationContent>(
        listPane = {
            val transfersViewModel = hiltViewModel<TransfersViewModel>()
            val deeplinkViewModel = hiltViewModel<DeeplinkViewModel>()
            hideBottomBar.value = currentDestination?.content != null

            val isDeepLinkConsumed by deeplinkViewModel.isDeeplinkConsumed.collectAsStateWithLifecycle()

            HandleDeepLink(
                transferUuid = transferUuid,
                isDeepLinkConsumed = { isDeepLinkConsumed },
                consumeDeepLink = deeplinkViewModel::consumeDeepLink,
                direction = direction,
            )
            ListPane(
                direction = direction,
                navigator = this,
                updateHasTransfer = { hasTransfer = it },
                transfersViewModel = transfersViewModel,
            )
        },
        detailPane = {
            DetailPane(navigator = this, hasTransfer = { hasTransfer })
        },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ThreePaneScaffoldNavigator<DestinationContent>.HandleDeepLink(
    transferUuid: String?,
    isDeepLinkConsumed: () -> Boolean,
    consumeDeepLink: () -> Unit,
    direction: TransferDirection,
) {
    if (transferUuid != null && !isDeepLinkConsumed()) {
        consumeDeepLink()
        navigateToDetails(LocalContext.current, LocalWindowAdaptiveInfo.current, direction, transferUuid)
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
    val context = LocalContext.current
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current
    val isWindowLarge = windowAdaptiveInfo.isWindowLarge()
    when (direction) {
        TransferDirection.SENT -> SentScreen(
            navigateToDetails = { transferUuid ->
                navigator.navigateToDetails(context, windowAdaptiveInfo, direction, transferUuid)
            },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
            onDeleteTransfer = { if (isWindowLarge) navigator.popBackStack() }
        )
        TransferDirection.RECEIVED -> ReceivedScreen(
            navigateToDetails = { transferUuid ->
                navigator.navigateToDetails(context, windowAdaptiveInfo, direction, transferUuid)
            },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
            onDeleteTransfer = { if (isWindowLarge) navigator.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToDetails(
    context: Context,
    windowAdaptiveInfo: WindowAdaptiveInfo,
    direction: TransferDirection,
    transferUuid: String,
) {
    selectItem(context, windowAdaptiveInfo, DestinationContent.RootLevel(direction, transferUuid))
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToFolder(
    direction: TransferDirection,
    transferUuid: String,
    folderUuid: String,
) {
    navigateTo(ListDetailPaneScaffoldRole.Detail, DestinationContent.FolderLevel(direction, transferUuid, folderUuid))
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
    val isWindowLarge =  LocalWindowAdaptiveInfo.current.isWindowLarge()
    val destinationContent = if (isWindowLarge) navigator.currentDestination?.content else navigator.safeCurrentContent()

    when (destinationContent) {
        null -> {
            NoSelectionEmptyState(hasTransfer())
        }
        is DestinationContent.RootLevel -> {
            TransferDetailsScreen(
                transferUuid = destinationContent.transferUuid,
                direction = destinationContent.direction,
                hasTransfer = hasTransfer,
                navigateBack = ScreenWrapperUtils.getBackNavigation(navigator),
                navigateToFolder = { selectedFolderUuid ->
                    navigator.navigateToFolder(
                        destinationContent.direction,
                        destinationContent.transferUuid,
                        selectedFolderUuid,
                    )
                },
            )
        }
        is DestinationContent.FolderLevel -> {
            val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

            ExistingTransferFilesDetails(
                navigator,
                destinationContent.folderUuid,
                destinationContent.direction,
                destinationContent.transferUuid,
                windowAdaptiveInfo,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ExistingTransferFilesDetails(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    folderUuid: String,
    transferDirection: TransferDirection,
    transferUuid: String,
    windowAdaptiveInfo: WindowAdaptiveInfo,
) {
    val context = LocalContext.current
    ExistingTransferFilesDetailsScreen(
        navigateToFolder = { selectedFolderUuid ->
            navigator.navigateToFolder(
                transferDirection,
                transferUuid,
                selectedFolderUuid,
            )
        },
        transferUuid = transferUuid,
        folderUuid = folderUuid,
        navigateBack = { navigator.popBackStack() },
        close = {
            // Because on phones, if we navigateToDetails, we arrive on the TransferDetailsScreen but
            // the FilesDetailsScreen is displayed again when we press back. We need to first navigateBack again to dismiss all
            // the FilesDetailsScreen's
            if (windowAdaptiveInfo.isWindowSmall(context)) navigator.navigateBack()
            navigator.navigateToDetails(
                context,
                windowAdaptiveInfo,
                transferDirection,
                transferUuid
            )
        },
        withFilesSize = false,
        withSpaceLeft = false,
    )
}

@Composable
fun NoSelectionEmptyState(hasTransfers: Boolean) {
    val (titleRes, descriptionRes) = if (hasTransfers) {
        R.string.noTransferSelectedTitle to R.string.noTransferSelectedDescription
    } else {
        null to null
    }

    SwissTransferScaffold(
        topBar = { SwissTransferTopAppBar(title = "") }
    ) {
        EmptyState(
            content = { Image(imageVector = AppIllus.MascotWithMagnifyingGlass.image(), contentDescription = null) },
            titleRes = titleRes,
            descriptionRes = descriptionRes
        )
    }
}

@Parcelize
private sealed interface DestinationContent : Parcelable {
    val direction: TransferDirection
    val transferUuid: String

    @Parcelize
    data class RootLevel(
        override val direction: TransferDirection,
        override val transferUuid: String,
    ) : DestinationContent

    @Parcelize
    data class FolderLevel(
        override val direction: TransferDirection,
        override val transferUuid: String,
        val folderUuid: String,
    ) : DestinationContent
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TransfersScreenWrapper(
                TransferDirection.RECEIVED,
                transferUuid = null,
                hideBottomBar = remember { mutableStateOf(false) },
            )
        }
    }
}
