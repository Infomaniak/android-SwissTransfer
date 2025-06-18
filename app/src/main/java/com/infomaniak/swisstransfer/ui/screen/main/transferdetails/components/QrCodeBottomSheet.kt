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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.QrCode
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCodeBottomSheet(isVisible: () -> Boolean, transferUrl: String, closeBottomSheet: () -> Unit) {

    if (!isVisible()) return

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.contentDescriptionButtonClose),
                onClick = closeBottomSheet,
            )
        },
        title = stringResource(R.string.shareQrCodeTitle),
        description = stringResource(R.string.shareQrCodeDescription),
    ) {
        Column {
            Spacer(Modifier.height(Margin.Large))
            QrCode(transferUrl)
            Spacer(Modifier.height(Margin.Large))
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            QrCodeBottomSheet(
                isVisible = { true },
                transferUrl = "https://chk.me/83azQOl",
                closeBottomSheet = {},
            )
        }
    }
}
