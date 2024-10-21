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
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadSuccessEmail.UploadSuccessEmail
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

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
        content = { Content() },
    )
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        IllustratedMessageBlock(
            icon = AppIllus.UploadSuccessEmail.image(),
            title = R.string.uploadSuccessEmailTitle,
            description = R.string.uploadSuccessEmailDescription,
        )

        Spacer(Modifier.height(Margin.Small))

        EmailAddressChip("test.test@ik.me") // TODO: Use correct email instead of hard-coded value.
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
