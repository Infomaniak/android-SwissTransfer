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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadError.GhostMagnifyingGlassQuestionMark
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.areTransferDataStillAvailable
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.core.R as RCore

@Composable
fun UploadErrorScreen(
    navigateBackToUploadProgress: () -> Unit,
    navigateBackToImportFiles: () -> Unit,
    closeActivity: () -> Unit,
    uploadProgressViewModel: UploadProgressViewModel = hiltViewModel<UploadProgressViewModel>(),
) {

    BackHandler(onBack = {})

    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        topButton = {
            LargeButton(
                modifier = it,
                title = stringResource(RCore.string.buttonRetry),
                onClick = { uploadProgressViewModel.resendLastTransfer(onCompletion = navigateBackToUploadProgress) },
            )
        },
        bottomButton = {
            if (areTransferDataStillAvailable) {
                LargeButton(
                    modifier = it,
                    title = stringResource(R.string.buttonEditTransfer),
                    style = ButtonType.Secondary,
                    onClick = { uploadProgressViewModel.removeAllUploadSession(onCompletion = navigateBackToImportFiles) },
                )
            } else {
                LargeButton(
                    modifier = it,
                    title = stringResource(R.string.buttonCancelTransfer),
                    style = ButtonType.DestructiveText,
                    onClick = { uploadProgressViewModel.removeAllUploadSession(onCompletion = closeActivity) },
                )
            }
        }
    ) {
        EmptyState(
            content = { Image(imageVector = AppIllus.GhostMagnifyingGlassQuestionMark.image(), contentDescription = null) },
            titleRes = R.string.uploadErrorTitle,
            descriptionRes = R.string.uploadErrorDescription,
        )
    }
}

@PreviewAllWindows
@Composable
private fun UploadErrorScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadErrorScreen({}, {}, {})
        }
    }
}
