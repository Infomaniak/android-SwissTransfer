/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.compose.bottomstickybuttonscaffold.BottomStickyButtonScaffold
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.core.inappreview.reviewmanagers.InAppReviewManager
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet.RedCrossPaperPlanes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.upload.UploadState

@Composable
fun UploadScreen(
    inAppReviewManager: InAppReviewManager,
    navigateBackToPickFiles: () -> Unit,
    exitNewTransfer: () -> Unit,
    uploadViewModel: UploadViewModel = hiltViewModel<UploadViewModel>(),
) {
    val uploadState: UploadState? by uploadViewModel.stateFlow.collectAsStateWithLifecycle()

    val adScreenType = rememberSaveable { UploadProgressAdType.entries.random() }
    var showCancelBottomSheet by rememberSaveable { mutableStateOf(false) }

    BackHandler(
        enabled = !showCancelBottomSheet && uploadState !is UploadState.Complete,
        onBack = {
            when (uploadState) {
                null, is UploadState.Complete -> Unit // Let automatic exit navigation happen
                is UploadState.Ongoing -> showCancelBottomSheet = true
                is UploadState.Retry -> showCancelBottomSheet = true
                is UploadState.Failure -> uploadViewModel.abandonUploadRequest()
            }
        }
    )

    when (val state = uploadState) {
        null -> {
            val hasPickedFiles by uploadViewModel.hasPickedFiles.collectAsState()
            // Extracting the if/else below to a local composable function causes flickering, so leave it here.
            if (hasPickedFiles) {
                LaunchedEffect(navigateBackToPickFiles) { navigateBackToPickFiles() }
            } else {
                LaunchedEffect(exitNewTransfer) { exitNewTransfer() }
            }

            NoUploadOngoingEmptyState()
        }
        is UploadState.Ongoing -> UploadOngoingScreen(
            progressState = rememberUpdatedState(state),
            adScreenType = adScreenType,
            onCancelClick = { showCancelBottomSheet = true },
        )
        is UploadState.Retry -> UploadRetryScreen(
            errorState = rememberUpdatedState(state),
            retry = uploadViewModel.retryRequest,
            edit = uploadViewModel.editRequest,
        )
        is UploadState.Failure -> UploadFailureScreen(
            failureState = rememberUpdatedState(state),
            cancel = uploadViewModel.abandonUploadRequest
        )
        is UploadState.Complete -> {
            showCancelBottomSheet = false // Ensure we dismiss any pending cancel attempt.
            val uploadSuccessViewModel: UploadSuccessViewModel = hiltViewModel()
            inAppReviewManager.decrementAppReviewCountdown()
            UploadSuccessScreen(
                transferType = state.transferType,
                transferUuid = state.transferUuid,
                transferUrl = state.transferUrl,
                dismissCompleteUpload = { uploadSuccessViewModel.dismissCompleteUpload() }
            )
        }
    }

    if (showCancelBottomSheet) {
        CancelUploadBottomSheet(
            onCancel = {
                showCancelBottomSheet = false
                uploadViewModel.abandonUploadRequest()
            },
            closeButtonSheet = { showCancelBottomSheet = false },
        )
    }
}

@Composable
private fun NoUploadOngoingEmptyState() {
    val snackbarHostState = remember { SnackbarHostState() }
    BottomStickyButtonScaffold(
        snackbarHostState = snackbarHostState,
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                style = ButtonType.Primary,
                title = stringResource(R.string.buttonFinished),
                enabled = { false },
                onClick = {},
            )
        },
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CancelUploadBottomSheet(onCancel: () -> Unit, closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        title = stringResource(R.string.uploadCancelConfirmBottomSheetTitle),
        imageVector = AppIllus.RedCrossPaperPlanes.image(),
        topButton = {
            LargeButton(
                title = stringResource(R.string.buttonCancelTransfer),
                modifier = it,
                style = ButtonType.Destructive,
                onClick = onCancel,
            )
        },
        bottomButton = {
            LargeButton(
                title = stringResource(R.string.buttonCloseAndContinue),
                modifier = it,
                style = ButtonType.Tertiary,
                onClick = closeButtonSheet,
            )
        },
        onDismissRequest = closeButtonSheet,
    )
}

@PreviewAllWindows
@Composable
private fun NoUploadOngoingEmptyStatePreview() {
    SwissTransferTheme { Surface { NoUploadOngoingEmptyState() } }
}
