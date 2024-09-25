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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.screen.main.components.BrandTobAppBarScaffold
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun SentScreen(
    navigateToDetails: (transferId: Int) -> Unit,
    sentViewModel: SentViewModel = hiltViewModel<SentViewModel>(),
) {
    val transfers by sentViewModel.transfers.collectAsStateWithLifecycle()
    SentScreen(transfers)
}

@Composable
private fun SentScreen(transfers: List<Any>?) {

    if (transfers == null) return
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()

    BrandTobAppBarScaffold(
        floatingActionButton = {
            if (windowAdaptiveInfo.isWindowSmall() && transfers.isNotEmpty()) {
                NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
            }
        },
    ) { contentPadding ->
        val modifier = Modifier.padding(contentPadding)
        if (transfers.isEmpty()) {
            SentEmptyScreen(modifier)
        } else {
            SentListScreen(modifier, transfers)
        }
    }

}

@PreviewMobile
@Composable
private fun SentScreenMobilePreview() {
    SwissTransferTheme {
        Surface {
            SentScreen(transfers = emptyList())
        }
    }
}

@PreviewTablet
@Composable
private fun SentScreenTabletPreview() {
    SwissTransferTheme {
        Surface {
            SentScreen(transfers = emptyList())
        }
    }
}
