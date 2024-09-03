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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.illu.ArrowCurvedDownright
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavType
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun SentScreen(
    navigateToDetails: (transferId: Int) -> Unit,
    sentViewModel: SentViewModel = hiltViewModel<SentViewModel>(),
) {
    val transfers by sentViewModel.transfers.collectAsStateWithLifecycle()
    SentScreen(
        transfers = transfers,
        navType = LocalNavType.current,
    )
}

@Composable
private fun SentScreen(transfers: List<Any>?, navType: NavigationSuiteType) {
    if (transfers == null) return

    if (transfers.isEmpty()) {
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
        Spacer(modifier = Modifier.height(Margin.Medium))
        Text(
            modifier = Modifier.widthIn(max = maxWidth),
            text = stringResource(id = R.string.firstTransferDescription),
            style = SwissTransferTheme.typography.bodyRegular,
        )
        Spacer(modifier = Modifier.height(Margin.Medium))
        ConstraintLayout {
            val (icon, fab) = createRefs()

            Icon(
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(anchor = parent.top)
                        end.linkTo(anchor = fab.start, margin = Margin.Small)
                    },
                imageVector = AppIcons.Illu.ArrowCurvedDownright,
                contentDescription = null,
            )
            NewTransferFab(
                modifier = Modifier
                    .constrainAs(fab) {
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        top.linkTo(anchor = parent.top, margin = Margin.Large)
                    },
                newTransferFabType = NewTransferFabType.EMPTY_STATE,
            )
        }
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
private fun SentScreenAMobilePreview() {
    SwissTransferTheme {
        Surface {
            SentScreen(
                transfers = emptyList(),
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
                transfers = emptyList(),
                navType = NavigationSuiteType.NavigationRail,
            )
        }
    }
}
