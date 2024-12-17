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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferType
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Chain
import com.infomaniak.swisstransfer.ui.images.icons.Envelope
import com.infomaniak.swisstransfer.ui.images.icons.QrCode
import com.infomaniak.swisstransfer.ui.images.icons.WifiWave
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import kotlinx.coroutines.launch

@Composable
fun TransferTypeButtons(horizontalPadding: Dp, transferType: GetSetCallbacks<TransferTypeUi>) {

    val coroutineScope = rememberCoroutineScope()

    BringIntoViewRow(
        horizontalPadding = horizontalPadding,
        spaceBy = Margin.Mini,
        items = TransferType.entries.map { it.toTransferTypeUi() },
    ) { transferTypeItem, bringIntoViewRequester ->

        LaunchedEffect(Unit) {
            coroutineScope.launch { if (transferTypeItem == transferType.get()) bringIntoViewRequester.bringIntoView() }
        }

        TransferTypeButton(
            transferType = transferTypeItem,
            isActive = { transferTypeItem == transferType.get() },
            onClick = {
                transferType.set(transferTypeItem)
                coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
            },
        )
    }
}

enum class TransferTypeUi(
    val buttonIcon: ImageVector,
    @StringRes val buttonTextRes: Int,
    @StringRes val titleRes: Int,
    @StringRes @PluralsRes val descriptionRes: Int?,
    val dbValue: TransferType,
) {
    QR_CODE(
        buttonIcon = AppIcons.QrCode,
        buttonTextRes = R.string.transferTypeQrCode,
        titleRes = R.string.uploadSuccessQrTitle,
        descriptionRes = null,
        dbValue = TransferType.QR_CODE,
    ),
    MAIL(
        buttonIcon = AppIcons.Envelope,
        buttonTextRes = R.string.transferTypeEmail,
        titleRes = R.string.uploadSuccessEmailTitle,
        descriptionRes = R.plurals.uploadSuccessEmailDescription,
        dbValue = TransferType.MAIL,
    ),
    LINK(
        buttonIcon = AppIcons.Chain,
        buttonTextRes = R.string.transferTypeLink,
        titleRes = R.string.uploadSuccessLinkTitle,
        descriptionRes = R.string.uploadSuccessLinkDescription,
        dbValue = TransferType.LINK,
    ),
    PROXIMITY(
        buttonIcon = AppIcons.WifiWave,
        buttonTextRes = R.string.transferTypeProximity,
        titleRes = R.string.uploadSuccessLinkTitle,
        descriptionRes = R.string.uploadSuccessLinkDescription,
        dbValue = TransferType.PROXIMITY,
    );

    companion object {
        fun TransferType.toTransferTypeUi() = when (this) {
            TransferType.LINK -> LINK
            TransferType.QR_CODE -> QR_CODE
            TransferType.PROXIMITY -> PROXIMITY
            TransferType.MAIL -> MAIL
        }
    }
}

@PreviewLightAndDark
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferTypeButtons(
                horizontalPadding = Margin.Medium,
                transferType = GetSetCallbacks(get = { TransferTypeUi.QR_CODE }, set = {}),
            )
        }
    }
}
