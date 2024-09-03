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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferTobAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.illu.ChainTilted
import com.infomaniak.swisstransfer.ui.icons.illu.EnvelopeTilted
import com.infomaniak.swisstransfer.ui.icons.illu.QrCodeTilted
import com.infomaniak.swisstransfer.ui.icons.illu.WaveSignalTilted
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeButtons
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun TransferTypeScreen(navigateToTransfer: (TransferType) -> Unit, popBack: () -> Unit) {
    Scaffold(topBar = {
        SwissTransferTobAppBar(
            titleRes = R.string.transferTypeScreenTitle,
            navigationMenu = TopAppBarButton.backButton(popBack),
            TopAppBarButton.closeButton { /*TODO*/ }
        )
    }) { contentPaddings ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPaddings)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Margin.Large, horizontal = Margin.Medium),
                text = stringResource(R.string.transferTypeTitle),
                style = SwissTransferTheme.typography.h1,
                textAlign = TextAlign.Center,
            )

            TransferTypeButtons(TransferType.entries, navigateToTransfer)
        }
    }
}

enum class TransferType(
    @StringRes val titleRes: Int,
    val background: @Composable () -> Color,
    val foreground: @Composable () -> Color,
    val icon: ImageVector,
) {
    LINK(
        R.string.transferTypeLink,
        { SwissTransferTheme.colors.transferTypeLinkContainer },
        { SwissTransferTheme.colors.transferTypeLinkOnContainer },
        AppIcons.Illu.ChainTilted
    ),
    EMAIL(
        R.string.transferTypeEmail,
        { SwissTransferTheme.colors.transferTypeEmailContainer },
        { SwissTransferTheme.colors.transferTypeEmailOnContainer },
        AppIcons.Illu.EnvelopeTilted
    ),
    QR_CODE(
        R.string.transferTypeQrCode,
        { SwissTransferTheme.colors.transferTypeQrContainer },
        { SwissTransferTheme.colors.transferTypeQrOnContainer },
        AppIcons.Illu.QrCodeTilted
    ),
    PROXIMITY(
        R.string.transferTypeProximity,
        { SwissTransferTheme.colors.transferTypeProximityContainer },
        { SwissTransferTheme.colors.transferTypeProximityOnContainer },
        AppIcons.Illu.WaveSignalTilted
    ),
}

@PreviewMobile
@PreviewTablet
@Composable
private fun TransferTypeScreenPreview() {
    SwissTransferTheme {
        TransferTypeScreen({}) {}
    }
}
