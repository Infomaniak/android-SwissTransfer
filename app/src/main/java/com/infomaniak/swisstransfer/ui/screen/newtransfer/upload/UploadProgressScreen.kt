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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet.RedCrossPaperPlanes
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.AdHeader
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.Progress
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.workers.UploadWorker.UploadProgressUiState
import com.infomaniak.core2.R as RCore2

@Composable
fun UploadProgressScreen(
    totalSizeInBytes: Long,
    navigateToUploadSuccess: (String) -> Unit,
    navigateToUploadError: () -> Unit,
    closeActivity: () -> Unit,
    uploadProgressViewModel: UploadProgressViewModel = hiltViewModel<UploadProgressViewModel>(),
) {
    val uiState by uploadProgressViewModel.transferProgressUiState.collectAsStateWithLifecycle()

    val adScreenType = rememberSaveable { UploadProgressAdType.entries.random() }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    BackHandler(!showBottomSheet) {
        showBottomSheet = true
    }

    LaunchedEffect(Unit) {
        uploadProgressViewModel.trackUploadProgress()
    }

    HandleProgressState({ uiState }, navigateToUploadSuccess, navigateToUploadError)

    UploadProgressScreen(
        progressState = { uiState },
        totalSizeInBytes = totalSizeInBytes,
        showBottomSheet = GetSetCallbacks(get = { showBottomSheet }, set = { showBottomSheet = it }),
        adScreenType = adScreenType,
        onCancel = {
            uploadProgressViewModel.cancelUpload()
            closeActivity()
        }
    )
}

@Composable
private fun HandleProgressState(
    uiState: () -> UploadProgressUiState,
    navigateToUploadSuccess: (String) -> Unit,
    navigateToUploadError: () -> Unit,
) {
    val currentUiState = uiState()
    LaunchedEffect(uiState()) {
        when (currentUiState) {
            is UploadProgressUiState.Success -> navigateToUploadSuccess(currentUiState.transferUrl)
            is UploadProgressUiState.Error -> navigateToUploadError()
            else -> Unit
        }
    }
}

@Composable
private fun UploadProgressScreen(
    progressState: () -> UploadProgressUiState,
    totalSizeInBytes: Long,
    adScreenType: UploadProgressAdType,
    onCancel: () -> Unit,
    showBottomSheet: GetSetCallbacks<Boolean>,
) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(RCore2.string.buttonCancel, modifier = it, onClick = { showBottomSheet.set(true) })
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AdHeader(adScreenType)

            Spacer(Modifier.height(Margin.Medium))
            Text(stringResource(R.string.uploadProgressIndication))
            Progress(progressState, totalSizeInBytes)
            Spacer(Modifier.height(Margin.Huge))
        }

        if (showBottomSheet.get()) CancelUploadBottomSheet(onCancel = onCancel, closeButtonSheet = { showBottomSheet.set(false) })
    }
}

@Composable
private fun CancelUploadBottomSheet(onCancel: () -> Unit, closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        titleRes = R.string.uploadCancelConfirmBottomSheetTitle,
        imageVector = AppIllus.RedCrossPaperPlanes.image(),
        topButton = {
            LargeButton(
                titleRes = R.string.uploadCancelConfirmBottomSheetCancel,
                modifier = it,
                style = ButtonType.ERROR,
                onClick = onCancel,
            )
        },
        bottomButton = {
            LargeButton(
                titleRes = R.string.uploadCancelConfirmBottomSheetClose,
                modifier = it,
                style = ButtonType.TERTIARY,
                onClick = closeButtonSheet,
            )
        },
        onDismissRequest = closeButtonSheet,
    )
}

@PreviewAllWindows
@Composable
private fun UploadProgressScreenPreview() {
    SwissTransferTheme {
        UploadProgressScreen(
            progressState = { UploadProgressUiState.Progress(44_321_654L) },
            totalSizeInBytes = 76_321_894L,
            adScreenType = UploadProgressAdType.INDEPENDENCE,
            onCancel = {},
            showBottomSheet = GetSetCallbacks(get = { false }, set = { }),
        )
    }
}
