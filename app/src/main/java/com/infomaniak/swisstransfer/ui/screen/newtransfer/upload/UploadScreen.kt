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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.upload.UploadState

@Composable
fun UploadScreen(
    navigateBackToPickFiles: () -> Unit,
    exitNewTransfer: () -> Unit,
    viewModel: UploadViewModel = hiltViewModel<UploadViewModel>(),
) {
    val uploadState: UploadState? by viewModel.stateFlow.collectAsStateWithLifecycle()
    val hasPickedFiles by viewModel.hasPickedFiles.collectAsState()

    val adScreenType = rememberSaveable { UploadProgressAdType.entries.random() }
    var showCancelBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showLocationBottomSheet by rememberSaveable { mutableStateOf(false) }
    val locationCallbacks = GetSetCallbacks(get = { showLocationBottomSheet }, set = { showLocationBottomSheet = it })

    BackHandler(
        enabled = !showCancelBottomSheet && uploadState !is UploadState.Complete,
        onBack = {
            when (uploadState) {
                null, is UploadState.Complete -> Unit // Let automatic exit navigation happen
                else -> showCancelBottomSheet = true
            }
        }
    )

    @Composable
    fun exit() {
        if (hasPickedFiles) LaunchedEffect(navigateBackToPickFiles) { navigateBackToPickFiles() }
        else LaunchedEffect(exitNewTransfer) { exitNewTransfer() }
    }

    when (val state = uploadState) {
        null -> {
            exit()
            NoUploadOngoingEmptyState()
        }
        is UploadState.Ongoing -> UploadOngoingScreen(
            progressState = rememberUpdatedState(state),
            adScreenType = adScreenType,
            onCancel = viewModel.abandonUploadRequest,
            showCancelBottomSheet = GetSetCallbacks(get = { showCancelBottomSheet }, set = { showCancelBottomSheet = it }),
            showLocationBottomSheet = locationCallbacks,
            exitNewTransfer = exitNewTransfer
        )
        is UploadState.Retry -> UploadRetryScreen(
            errorState = rememberUpdatedState(state),
            retry = viewModel.retryRequest,
            edit = viewModel.editRequest,
            cancel = viewModel.abandonUploadRequest
        )
        is UploadState.Failure -> UploadFailureScreen(
            failureState = rememberUpdatedState(state),
            cancel = viewModel.abandonUploadRequest
        )
        is UploadState.Complete -> {
            showCancelBottomSheet = false // Ensure we dismiss any pending cancel attempt.
            val uploadSuccessViewModel: UploadSuccessViewModel = hiltViewModel()
            UploadSuccessScreen(
                transferType = state.transferType,
                transferUuid = state.transferUuid,
                transferUrl = state.transferUrl,
                dismissCompleteUpload = { uploadSuccessViewModel.dismissCompleteUpload() }
            )
        }
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

@PreviewAllWindows
@Composable
private fun NoUploadOngoingEmptyStatePreview() {
    SwissTransferTheme { Surface { NoUploadOngoingEmptyState() } }
}
