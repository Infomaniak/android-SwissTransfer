/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.R
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadError.GhostMagnifyingGlassQuestionMark
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.ValidateUserEmailScreen
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.upload.UploadState

@Composable
fun UploadRetryScreen(
    errorState: State<UploadState.Retry>,
    retry: CallableState<Unit>,
    edit: CallableState<Unit>,
) {
    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen("UploadRetryView") }

    when (val error: UploadState.Retry = errorState.value) {
        is UploadState.Retry.EmailValidationRequired -> {
            ValidateUserEmailScreen(
                editTransfer = edit,
                state = error
            )
        }
        is UploadState.Retry.NetworkIssue -> UploadRetryScreen(retry = retry, edit = edit)
        is UploadState.Retry.OtherIssue -> UploadRetryScreen(retry = retry, edit = edit)
    }
}

@Composable
private fun UploadRetryScreen(
    retry: CallableState<Unit>,
    edit: CallableState<Unit>,
) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        topButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.buttonRetry),
                onClick = retry,
            )
        },
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(com.infomaniak.swisstransfer.R.string.buttonEditTransfer),
                style = ButtonType.Secondary,
                onClick = edit,
            )
        }
    ) {
        EmptyState(
            content = { Image(imageVector = AppIllus.GhostMagnifyingGlassQuestionMark.image(), contentDescription = null) },
            titleRes = com.infomaniak.swisstransfer.R.string.uploadErrorTitle,
            descriptionRes = com.infomaniak.swisstransfer.R.string.uploadErrorDescription,
        )
    }
}

@PreviewAllWindows
@Composable
private fun UploadRetryScreenPreview() {
    SwissTransferTheme {
        Surface {
            val info = remember {
                UploadState.Info(
                    authorEmail = "test@infomaniak.com",
                    totalSize = 0L,
                    type = TransferTypeUi.Mail
                )
            }
            UploadRetryScreen(
                errorState = rememberUpdatedState(UploadState.Retry.EmailValidationRequired(info)),
                retry = CallableState<Unit>(),
                edit = CallableState<Unit>(),
            )
        }
    }
}
