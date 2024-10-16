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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmailAddressChip
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadSuccessEmail.UploadSuccessEmail
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun UploadSuccessEmailScreen() {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = R.string.buttonFinished,
                onClick = { /* TODO */ },
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    imageVector = AppIllus.UploadSuccessEmail.image(),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.uploadSuccessEmailTitle),
                    style = SwissTransferTheme.typography.h1,
                    color = SwissTransferTheme.colors.primaryTextColor,
                    modifier = Modifier.padding(top = Margin.XXLarge, start = Margin.Medium, end = Margin.Medium)
                )
                Text(
                    text = stringResource(R.string.uploadSuccessEmailDescription),
                    style = SwissTransferTheme.typography.bodyRegular,
                    textAlign = TextAlign.Center,
                    color = SwissTransferTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .padding(Margin.Medium)
                        .widthIn(max = Dimens.DescriptionWidth),
                )
                EmailAddressChip("test.test@ik.me") // TODO: Use correct email instead of hard-coded value.
            }
        },
    )
}

@PreviewSmallWindow
@Composable
private fun UploadSuccessEmailScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadSuccessEmailScreen()
        }
    }
}
