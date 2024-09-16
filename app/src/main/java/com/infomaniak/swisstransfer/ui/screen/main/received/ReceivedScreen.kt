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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BrandTobAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotSearching
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ReceivedScreen(navigateToDetails: (transferId: Int) -> Unit) {
    ReceivedScreen()
}

@Composable
private fun ReceivedScreen() {
    Scaffold(
        topBar = { BrandTobAppBar() },
        floatingActionButton = { ReceivedEmptyFab() },
    ) { contentPadding ->
        EmptyState(
            icon = AppIllus.MascotSearching,
            title = R.string.no_transfer_received_title,
            description = R.string.no_transfer_received_description,
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@PreviewMobile
@Composable
private fun ReceivedScreenMobilePreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen()
        }
    }
}

@PreviewTablet
@Composable
private fun ReceivedScreenTabletPreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen()
        }
    }
}
