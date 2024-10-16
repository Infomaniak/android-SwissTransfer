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

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Chain
import com.infomaniak.swisstransfer.ui.images.icons.Envelope
import com.infomaniak.swisstransfer.ui.images.icons.QrCode
import com.infomaniak.swisstransfer.ui.images.icons.WifiWave
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferTypeButtons(selectedTransferType: TransferType) {

    val selectedItem by rememberSaveable { mutableStateOf(selectedTransferType) }

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        for (entry in TransferType.entries) {
            TransferTypeButton(
                transferType = entry,
                isActive = { entry == selectedItem },
            )
        }
    }
}

enum class TransferType(val buttonIcon: ImageVector, @StringRes val buttonText: Int, val onClick: () -> Unit = {}) {
    LINK(buttonIcon = AppIcons.Chain, buttonText = R.string.transferTypeLink),
    QR_CODE(buttonIcon = AppIcons.QrCode, buttonText = R.string.transferTypeQrCode),
    PROXIMITY(buttonIcon = AppIcons.WifiWave, buttonText = R.string.transferTypeProximity),
    MAIL(buttonIcon = AppIcons.Envelope, buttonText = R.string.transferTypeEmail),
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferTypeButtons(selectedTransferType = TransferType.QR_CODE)
        }
    }
}
