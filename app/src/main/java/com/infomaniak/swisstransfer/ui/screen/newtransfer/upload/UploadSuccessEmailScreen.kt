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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadSuccessEmail.UploadSuccessEmail
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

// TODO: Use correct emails instead of hard-coded values
@Composable
fun UploadSuccessEmailScreen(
    emails: List<String> = listOf(
        "email@example.com",
        "firstname.lastname@example.com",
        "email@subdomain.example.com",
        "firstname+lastname@example.com",
        "email@123.123.123.123",
        "email@[123.123.123.123]",
        "\"email\"@example.com",
        "1234567890@example.com",
        "email@example-one.com",
        "_______@example.com",
        "email@example.name",
        "email@example.museum",
        "email@example.co.jp",
        "firstname-lastname@example.com",
    ),
) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = R.string.buttonFinished,
                onClick = { /* TODO */ },
            )
        },
        content = { Content(emails) },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Content(emails: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Margin.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        IllustratedMessageBlock(
            icon = AppIllus.UploadSuccessEmail.image(),
            title = R.string.uploadSuccessEmailTitle,
            description = pluralStringResource(R.plurals.uploadSuccessEmailDescription, emails.count()),
        )

        Spacer(modifier = Modifier.height(Margin.Medium))

        FlowRow(
            modifier = Modifier.widthIn(max = 800.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            emails.forEach {
                EmailAddressChip(
                    text = it,
                    modifier = Modifier.padding(horizontal = Margin.XSmall),
                )
            }
        }
    }
}

@PreviewAllWindows
@Composable
private fun UploadSuccessEmailScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadSuccessEmailScreen()
        }
    }
}
