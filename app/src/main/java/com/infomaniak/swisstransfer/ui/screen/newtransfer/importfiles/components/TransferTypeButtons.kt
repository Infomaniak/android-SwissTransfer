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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Chain
import com.infomaniak.swisstransfer.ui.images.icons.Envelope
import com.infomaniak.swisstransfer.ui.images.icons.QrCode
import com.infomaniak.swisstransfer.ui.images.icons.WifiWave
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferTypeButtons(transferType: GetSetCallbacks<TransferType>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
    ) {
        for (transferTypeEntry in TransferType.entries) {
            TransferTypeButton(
                transferType = transferTypeEntry,
                isActive = { transferTypeEntry == transferType.get() },
                onClick = { transferType.set(transferTypeEntry) },
            )
        }
    }
}

enum class TransferType(
    val buttonIcon: ImageVector,
    @StringRes val buttonTextRes: Int,
    @StringRes val titleRes: Int,
    @StringRes @PluralsRes val descriptionRes: Int?,
) {
    LINK(
        buttonIcon = AppIcons.Chain,
        buttonTextRes = R.string.transferTypeLink,
        titleRes = R.string.uploadSuccessLinkTitle,
        descriptionRes = R.string.uploadSuccessLinkDescription,
    ),
    QR_CODE(
        buttonIcon = AppIcons.QrCode,
        buttonTextRes = R.string.transferTypeQrCode,
        titleRes = R.string.uploadSuccessQrTitle,
        descriptionRes = null,
    ),
    PROXIMITY(
        buttonIcon = AppIcons.WifiWave,
        buttonTextRes = R.string.transferTypeProximity,
        titleRes = R.string.uploadSuccessLinkTitle,
        descriptionRes = R.string.uploadSuccessLinkDescription,
    ),
    MAIL(
        buttonIcon = AppIcons.Envelope,
        buttonTextRes = R.string.transferTypeEmail,
        titleRes = R.string.uploadSuccessEmailTitle,
        descriptionRes = R.plurals.uploadSuccessEmailDescription,
    ),
}

@PreviewLightAndDark
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferTypeButtons(GetSetCallbacks(get = { TransferType.QR_CODE }, set = {}))
        }
    }
}
