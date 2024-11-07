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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsScreen
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ReceivedScreenWrapper() {
    TwoPaneScaffold<String>(
        listPane = { ListPane(navigator = this) },
        detailPane = { DetailPane(navigator = this) },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(navigator: ThreePaneScaffoldNavigator<String>) {
    ReceivedScreen(
        navigateToDetails = { transferUuid -> navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, transferUuid) },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(navigator: ThreePaneScaffoldNavigator<String>) {

    val transferUuid = navigator.currentDestination?.content

    if (transferUuid == null) {
        NoSelectionEmptyState()
    } else {
        TransferDetailsScreen(
            transferUuid = transferUuid,
            navigateBack = ScreenWrapperUtils.getBackNavigation(navigator),
        )
    }
}

// Show the detail pane content if selected item is available
@Composable
private fun NoSelectionEmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Select an item", color = SwissTransferTheme.colors.secondaryTextColor)
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ReceivedScreenWrapper()
        }
    }
}
