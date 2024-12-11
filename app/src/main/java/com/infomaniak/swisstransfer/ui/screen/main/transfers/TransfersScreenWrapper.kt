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

import android.os.Parcelable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.components.safeCurrentContent
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.received.ReceivedScreen
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentScreen
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsScreen
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TransfersScreenWrapper(direction: TransferDirection, transferUuid: String? = null) {
    var hasTransfer: Boolean by rememberSaveable { mutableStateOf(false) }
    var isDeepLink = false

    TwoPaneScaffold<DestinationContent>(
        listPane = {
            val transfersViewModel = hiltViewModel<TransfersViewModel>()
            val isDeepLinkConsumed by transfersViewModel.isDeepLinkConsumed.collectAsStateWithLifecycle()
            if (transferUuid != null && !isDeepLinkConsumed) {
                transfersViewModel.consumeDeepLink()
                isDeepLink = true
                navigateToDetails(direction, transferUuid)
            }
            ListPane(direction, navigator = this, hasTransfer = { hasTransfer = it }, transfersViewModel = transfersViewModel)
        },
        detailPane = {
            DetailPane(navigator = this, hasTransfer, isDeepLink)
        },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(
    direction: TransferDirection,
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    transfersViewModel: TransfersViewModel,
    hasTransfer: (Boolean) -> Unit,
) {
    when (direction) {
        TransferDirection.SENT -> SentScreen(
            navigateToDetails = { transferUuid -> navigator.navigateToDetails(direction, transferUuid) },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = hasTransfer,
        )
        TransferDirection.RECEIVED -> ReceivedScreen(
            navigateToDetails = { transferUuid -> navigator.navigateToDetails(direction, transferUuid) },
            getSelectedTransferUuid = navigator::getSelectedTransferUuid,
            transfersViewModel = transfersViewModel,
            hasTransfer = hasTransfer,
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
private fun ThreePaneScaffoldNavigator<DestinationContent>.getSelectedTransferUuid(): String? {
    return currentDestination?.content?.transferUuid
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(
    navigator: ThreePaneScaffoldNavigator<DestinationContent>,
    hasTransfer: Boolean,
    isDeepLink: Boolean,
) {

    val destinationContent = navigator.safeCurrentContent()

    if (destinationContent == null) {
        NoSelectionEmptyState(hasTransfer)
    } else {
        TransferDetailsScreen(
            transferUuid = destinationContent.transferUuid,
            direction = destinationContent.direction,
            isDeepLink = isDeepLink,
            navigateBack = ScreenWrapperUtils.getBackNavigation(navigator),
        )
    }
}

@Composable
private fun NoSelectionEmptyState(hasTransfers: Boolean) {
    val (titleRes, descriptionRes) = if (hasTransfers) {
        R.string.noTransferSelectedTitle to R.string.noTransferSelectedDescription
    } else {
        null to null
    }

    Surface {
        EmptyState(
            icon = AppIllus.MascotWithMagnifyingGlass,
            titleRes = titleRes,
            descriptionRes = descriptionRes
        )
    }
}

@Parcelize
private data class DestinationContent(
    val direction: TransferDirection,
    val transferUuid: String,
) : Parcelable

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TransfersScreenWrapper(TransferDirection.RECEIVED, transferUuid = null)
        }
    }
}
