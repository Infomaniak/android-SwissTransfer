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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavType
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun SentScreen(
    navigateToDetails: (transferId: Int) -> Unit,
) {
    val viewmodel = viewModel<SentViewModel>()
    SentScreen(
        isEmpty = viewmodel.transfers.isEmpty(),
        navType = LocalNavType.current,
    )
}

@Composable
private fun SentScreen(isEmpty: Boolean, navType: NavigationSuiteType) {
    if (isEmpty) {
        EmptyScreen()
    } else {
        TransferScreen(navType)
    }
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val maxWidth = 300.dp
        Text(
            modifier = Modifier.widthIn(max = maxWidth),
            text = stringResource(id = R.string.sentEmptyTitle),
            style = SwissTransferTheme.typography.specificMedium32,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .padding(top = Margin.Medium),
            text = stringResource(id = R.string.firstTransferDescription),
            style = SwissTransferTheme.typography.bodyRegular
        )
        NewTransferFab(
            modifier = Modifier.padding(top = Margin.ExtraLarge),
            newTransferFabType = NewTransferFabType.EMPTY_STATE,
        )
    }
}

@Composable
private fun TransferScreen(
    navType: NavigationSuiteType,
) {
    Scaffold(
        floatingActionButton = {
            if (navType == NavigationSuiteType.NavigationBar) NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
        }
    ) { contentPadding ->
        Text(
            text = "Sent screen",
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@PreviewMobile
@Composable
private fun SentScreenMobilePreview() {
    SwissTransferTheme {
        Surface {
            SentScreen(
                isEmpty = true,
                navType = NavigationSuiteType.NavigationBar,
            )
        }
    }
}

@PreviewTablet
@Composable
private fun SentScreenTabletPreview() {
    SwissTransferTheme {
        Surface {
            SentScreen(
                isEmpty = true,
                navType = NavigationSuiteType.NavigationRail,
            )
        }
    }
}
