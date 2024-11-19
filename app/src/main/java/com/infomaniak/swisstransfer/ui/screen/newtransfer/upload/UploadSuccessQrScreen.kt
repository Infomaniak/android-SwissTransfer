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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.beers.Beers
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.ShareAndCopyButtons
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun UploadSuccessQrScreen(transferType: TransferTypeUi, transferUrl: String, closeActivity: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    BottomStickyButtonScaffold(
        snackbarHostState = snackbarHostState,
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                style = ButtonType.PRIMARY,
                titleRes = R.string.buttonFinished,
                onClick = closeActivity,
            )
        },
    ) {
        Column {
            SuccessMessage(transferType, transferUrl)

            ShareAndCopyButtons(
                modifier = Modifier.padding(bottom = Margin.Medium, top = Margin.Mini),
                transferLink = transferUrl,
                snackbarHostState = snackbarHostState,
            )
        }
    }
}

@Composable
private fun ColumnScope.SuccessMessage(transferType: TransferTypeUi, transferUrl: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1.0f)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Margin.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(Modifier.height(Margin.Medium))

        Image(imageVector = AppIllus.Beers.image(), contentDescription = null)

        Spacer(Modifier.height(Margin.Huge))

        Text(
            text = stringResource(transferType.titleRes),
            style = SwissTransferTheme.typography.h1,
            color = SwissTransferTheme.colors.primaryTextColor,
        )

        Spacer(Modifier.height(Margin.Huge))

        QrCode(transferUrl)

        transferType.descriptionRes?.let { descriptionRes ->
            Spacer(Modifier.height(Margin.Huge))
            Text(
                text = stringResource(descriptionRes),
                style = SwissTransferTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun UploadSuccessQrScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadSuccessQrScreen(
                transferType = TransferTypeUi.QR_CODE,
                transferUrl = "https://chk.me/83azQOl",
                closeActivity = {}
            )
        }
    }
}
