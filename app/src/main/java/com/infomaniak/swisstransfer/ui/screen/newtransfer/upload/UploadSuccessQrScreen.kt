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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.DocumentOnDocument
import com.infomaniak.swisstransfer.ui.images.icons.PersonBadgeShare
import com.infomaniak.swisstransfer.ui.images.illus.uploadSuccessQr.UploadSuccessQr
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferType
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun UploadSuccessQrScreen(transferType: TransferType) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        topButton = {
            LargeButton(
                modifier = it,
                style = ButtonType.PRIMARY,
                titleRes = R.string.buttonShare,
                imageVector = AppIcons.PersonBadgeShare,
                onClick = { /* TODO */ },
            )
        },
        bottomButton = {
            LargeButton(
                modifier = it,
                style = ButtonType.SECONDARY,
                titleRes = R.string.buttonFinished,
                onClick = { /* TODO */ },
            )
        },
        content = { Content(transferType) },
    )
}

@Composable
private fun Content(transferType: TransferType) {

    val uploadSuccessTitle = if (transferType == TransferType.LINK) {
        R.string.uploadSuccessLinkTitle
    } else {
        R.string.uploadSuccessQrTitle
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Margin.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            imageVector = AppIllus.UploadSuccessQr.image(),
            contentDescription = null,
        )

        Spacer(Modifier.height(Margin.Huge))

        Text(
            text = stringResource(uploadSuccessTitle),
            style = SwissTransferTheme.typography.h1,
            color = SwissTransferTheme.colors.primaryTextColor,
        )

        Spacer(Modifier.height(Margin.Huge))

        QrCode()

        if (transferType != TransferType.QR_CODE) {
            Spacer(Modifier.height(Margin.Huge))
            Text(
                text = stringResource(R.string.uploadSuccessLinkDescription),
                style = SwissTransferTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
            )
        }
    }

    // TODO: What do we want to do with this button placement?
    LargeButton(
        modifier = Modifier.padding(Margin.Medium),
        style = ButtonType.SECONDARY,
        titleRes = R.string.buttonCopyLink,
        imageVector = AppIcons.DocumentOnDocument,
        onClick = { /* TODO */ },
    )
}

@PreviewAllWindows
@Composable
private fun UploadSuccessQrScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadSuccessQrScreen(transferType = TransferType.LINK)
        }
    }
}
