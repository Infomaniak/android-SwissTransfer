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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BrandTobAppBar
import com.infomaniak.swisstransfer.ui.components.NewTransferFab
import com.infomaniak.swisstransfer.ui.components.NewTransferFabType
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.ArrowDownRightCurved
import com.infomaniak.swisstransfer.ui.images.illus.MascotSearching
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavType
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ReceivedScreen(navigateToDetails: (transferId: Int) -> Unit) {
    SwissTransferTheme {
        ReceivedScreen(navType = LocalNavType.current)
    }
}

@Composable
private fun ReceivedScreen(navType: NavigationSuiteType) {
    Scaffold(
        topBar = { BrandTobAppBar() },
        floatingActionButton = {
            if (navType == NavigationSuiteType.NavigationBar) NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
        },
    ) { contentPadding ->

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(imageVector = AppIllus.MascotSearching, contentDescription = "")

                Text(
                    text = LocalContext.current.getString(R.string.no_transfer_received_title),
                    style = SwissTransferTheme.typography.specificMedium22,
                    modifier = Modifier.padding(PaddingValues(top = Margin.XLarge))
                )

                Text(
                    text = LocalContext.current.getString(R.string.no_transfer_received_description),
                    textAlign = TextAlign.Center,
                    style = SwissTransferTheme.typography.bodyRegular,
                    modifier = Modifier
                        .widthIn(max = 200.dp)
                        .padding(PaddingValues(top = Margin.Medium)),
                )
            }

            if (navType == NavigationSuiteType.NavigationBar) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .wrapContentSize()
                        .padding(PaddingValues(bottom = Margin.XLarge, end = 90.dp)),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.firstTransferDescription),
                        style = SwissTransferTheme.typography.bodyRegular,
                        modifier = Modifier.padding(PaddingValues(top = Margin.XLarge)),
                    )

                    Icon(
                        imageVector = AppIllus.ArrowDownRightCurved,
                        contentDescription = "",
                        modifier = Modifier.rotate(-30.0f),
                    )
                }
            }
        }
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
