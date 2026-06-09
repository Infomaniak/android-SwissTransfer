/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2026 Infomaniak Network SA
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.AnimatedContentDetailPane
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.components.popBackStack
import com.infomaniak.swisstransfer.ui.components.safeCurrentContent
import com.infomaniak.swisstransfer.ui.components.selectItem
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.TransferIdType
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.ReceivedScreen
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.ExistingTransferFilesDetailsScreen
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TransfersScreenWrapper(
    direction: TransferDirection,
    onHideBottomBarChange: (Boolean) -> Unit,
    transferIdType: TransferIdType? = null,
    transfersViewModel: TransfersViewModel = hiltViewModel(),
    deeplinkViewModel: DeeplinkViewModel = hiltViewModel(),
) {
    var hasTransfer: Boolean by rememberSaveable { mutableStateOf(false) }

    TwoPaneScaffold(
        listPane = {
            onHideBottomBarChange(currentDestination?.contentKey != null)

            val isDeepLinkConsumed by deeplinkViewModel.isDeeplinkConsumed.collectAsStateWithLifecycle()

            HandleDeepLink(
                transferIdType = transferIdType,
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
            DetailPane(
                navigator = this,
                hasTransfer = { hasTransfer },
            )
        },
    )
}


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ThreePaneScaffoldNavigator<DestinationContent>.HandleDeepLink(
    transferIdType: TransferIdType?,
    isDeepLinkConsumed: () -> Boolean,
    consumeDeepLink: () -> Unit,
    direction: TransferDirection,
) {
    if (transferIdType != null && !isDeepLinkConsumed()) {
        consumeDeepLink()
        val context = LocalContext.current
        val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

        LaunchedEffect(Unit) {
            navigateToDetails(context, windowAdaptiveInfo, direction, transferIdType)
        }
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
    val scope = rememberCoroutineScope()
    when (direction) {
        TransferDirection.SENT -> SentScreen(
            navigateToDetails = { transferIdType ->
                scope.launch { navigator.navigateToDetails(context, windowAdaptiveInfo, direction, transferIdType) }
            },
            getSelectedTransferIdType = navigator::getSelectedTransferIdType,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
            onDeleteTransfer = { if (isWindowLarge) scope.launch { navigator.popBackStack() } }
        )
        TransferDirection.RECEIVED -> ReceivedScreen(
            navigateToDetails = { transferIdType ->
                scope.launch { navigator.navigateToDetails(context, windowAdaptiveInfo, direction, transferIdType) }
            },
            getSelectedTransferIdType = navigator::getSelectedTransferIdType,
            transfersViewModel = transfersViewModel,
            hasTransfer = updateHasTransfer,
            onDeleteTransfer = { if (isWindowLarge) scope.launch { navigator.popBackStack() } }
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private suspend fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToDetails(
    context: Context,
    windowAdaptiveInfo: WindowAdaptiveInfo,
    direction: TransferDirection,
    transferIdType: TransferIdType,
) {
    val matomoScreen = when (direction) {
        TransferDirection.SENT -> MatomoScreen.SentTransferDetails
        TransferDirection.RECEIVED -> MatomoScreen.ReceivedTransferDetails
    }
    MatomoSwissTransfer.trackScreen(matomoScreen)
    val item = DestinationContent.RootLevel(direction, transferIdType)
    selectItem(context, windowAdaptiveInfo, item)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private suspend fun ThreePaneScaffoldNavigator<DestinationContent>.navigateToFolder(
    direction: TransferDirection,
    transferIdType: TransferIdType,
    folderUuid: String,
) {
    MatomoSwissTransfer.trackScreen(MatomoScreen.TransferDetailsFileList)
    navigateTo(ListDetailPaneScaffoldRole.Detail, DestinationContent.FolderLevel(direction, transferIdType, folderUuid))
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ThreePaneScaffoldNavigator<DestinationContent>.getSelectedTransferIdType(): TransferIdType? {
    return currentDestination?.contentKey?.transferIdType
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    hasTransfer: () -> Boolean,
) {
    val isWindowLarge = LocalWindowAdaptiveInfo.current.isWindowLarge()
    val destinationContent = if (isWindowLarge) navigator.currentDestination?.contentKey else navigator.safeCurrentContent()
    val scope = rememberCoroutineScope()
    val navigateBack: () -> Unit = {
        scope.launch { ScreenWrapperUtils.getBackNavigation(navigator)?.invoke() }
    }

    AnimatedContentDetailPane(destinationContent = destinationContent) { targetDestination ->
        when (targetDestination) {
            null -> {
                NoSelectionEmptyState(hasTransfer())
            }
            is DestinationContent.RootLevel -> {
                TransferDetailsScreen(
                    transferIdType = targetDestination.transferIdType,
                    direction = targetDestination.direction,
                    navigateBack = navigateBack,
                    navigateToFolder = { selectedFolderUuid ->
                        scope.launch {
                            navigator.navigateToFolder(
                                targetDestination.direction,
                                targetDestination.transferIdType,
                                selectedFolderUuid,
                            )
                        }
                    },
                    onDeleteTransfer = { if (isWindowLarge) scope.launch { navigator.popBackStack() } },
                )
            }
            is DestinationContent.FolderLevel -> {
                val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

                ExistingTransferFilesDetails(
                    navigator = navigator,
                    folderUuid = targetDestination.folderUuid,
                    transferDirection = targetDestination.direction,
                    transferIdType = targetDestination.transferIdType,
                    windowAdaptiveInfo = windowAdaptiveInfo,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ExistingTransferFilesDetails(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    folderUuid: String,
    transferDirection: TransferDirection,
    transferIdType: TransferIdType,
    windowAdaptiveInfo: WindowAdaptiveInfo,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    ExistingTransferFilesDetailsScreen(
        folderUuid = folderUuid,
        transferIdType = transferIdType,
        navigateToFolder = { selectedFolderUuid ->
            scope.launch {
                navigator.navigateToFolder(transferDirection, transferIdType, selectedFolderUuid)
            }
        },
        withFilesSize = false,
        withSpaceLeft = false,
        navigateBack = { scope.launch { navigator.popBackStack() } },
        transferDirection = transferDirection,
    ) {
        // Because on phones, if we navigateToDetails, we arrive on the TransferDetailsScreen but
        // the FilesDetailsScreen is displayed again when we press back. We need to first navigateBack again to dismiss all
        // the FilesDetailsScreen's
        scope.launch {
            if (isWindowSmall(context)) navigator.navigateBack()
            navigator.navigateToDetails(context, windowAdaptiveInfo, transferDirection, transferIdType)
        }
    }
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
    val transferIdType: TransferIdType

    @Parcelize
    data class RootLevel(
        override val direction: TransferDirection,
        override val transferIdType: TransferIdType,
    ) : DestinationContent

    @Parcelize
    data class FolderLevel(
        override val direction: TransferDirection,
        override val transferIdType: TransferIdType,
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
                transferIdType = null,
                onHideBottomBarChange = {},
            )
        }
    }
}
