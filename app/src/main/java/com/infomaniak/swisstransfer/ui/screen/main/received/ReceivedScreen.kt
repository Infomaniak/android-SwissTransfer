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
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavType
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ReceivedScreen(navigateToDetails: (transferId: Int) -> Unit) {
    ReceivedScreen(navType = LocalNavType.current)
}

@Composable
private fun ReceivedScreen(navType: NavigationSuiteType) {
    Scaffold(
        floatingActionButton = {
            if (navType == NavigationSuiteType.NavigationBar) NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
        }
    ) { contentPadding ->
        Text(
            text = "Received screen",
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@PreviewMobile
@Composable
private fun ReceivedScreenMobilePreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(navType = NavigationSuiteType.NavigationBar)
        }
    }
}

@PreviewTablet
@Composable
private fun ReceivedScreenTabletPreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(navType = NavigationSuiteType.NavigationRail)
        }
    }
}
