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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.beers.Beers
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun UploadSuccessEmailScreen(
    transferUuid: String,
    closeActivity: () -> Unit,
    uploadSuccessViewModel: UploadSuccessViewModel = hiltViewModel<UploadSuccessViewModel>(),
) {
    val recipientsEmails by uploadSuccessViewModel.recipientsEmails.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { uploadSuccessViewModel.fetchTransfer(transferUuid) }

    UploadSuccessEmailScreen({ recipientsEmails }, closeActivity)
}

@Composable
fun UploadSuccessEmailScreen(recipientsEmails: () -> Set<String>, closeActivity: () -> Unit) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.buttonFinished),
                onClick = closeActivity,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Margin.Medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            IllustratedMessageBlock(
                icon = AppIllus.Beers.image(),
                title = TransferTypeUi.Mail.titleRes,
                description = pluralStringResource(TransferTypeUi.Mail.descriptionRes!!, recipientsEmails().count()),
            )

            Spacer(Modifier.height(Margin.Medium))

            EmailsFlowRow(
                emails = recipientsEmails().toList(),
                modifier = Modifier.widthIn(max = 800.dp),
                horizontalArrangement = Arrangement.Center,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun UploadSuccessEmailScreenPreview(@PreviewParameter(EmailsPreviewParameter::class) emails: List<String>) {
    SwissTransferTheme {
        Surface {
            UploadSuccessEmailScreen({ emails.toSet() }, {})
        }
    }
}
