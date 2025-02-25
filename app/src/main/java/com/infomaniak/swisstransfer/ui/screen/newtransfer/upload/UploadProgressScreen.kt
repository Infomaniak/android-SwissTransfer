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
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet.RedCrossPaperPlanes
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferSendManager.SendStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.areTransferDataStillAvailable
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.AdHeader
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.NetworkUnavailable
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.Progress
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.workers.UploadWorker.UploadProgressUiState
import com.infomaniak.core.R as RCore

@Composable
fun UploadProgressScreen(
    totalSizeInBytes: Long,
    navigateToUploadSuccess: (transferUid: String, transferUrl: String) -> Unit,
    navigateToUploadError: () -> Unit,
    navigateToEmailValidation: () -> Unit,
    navigateToAppIntegrityError: () -> Unit,
    navigateBackToImportFiles: () -> Unit,
    closeActivity: () -> Unit,
    uploadProgressViewModel: UploadProgressViewModel = hiltViewModel<UploadProgressViewModel>(),
) {
    val uiState by uploadProgressViewModel.transferProgressUiState.collectAsStateWithLifecycle()
    val isNetworkAvailable by uploadProgressViewModel.isNetworkAvailable.collectAsStateWithLifecycle()
    val sendStatus by uploadProgressViewModel.sendStatus.collectAsStateWithLifecycle()

    val adScreenType = rememberSaveable { UploadProgressAdType.entries.random() }
    var showCancelBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showLocationBottomSheet by rememberSaveable { mutableStateOf(false) }
    val locationCallbacks = GetSetCallbacks(get = { showLocationBottomSheet }, set = { showLocationBottomSheet = it })

    BackHandler(enabled = !showCancelBottomSheet, onBack = { showCancelBottomSheet = true })

    HandleSendStatus(
        sendStatus = { sendStatus },
        navigateToUploadError = { navigateToUploadError() },
        navigateToEmailValidation = { navigateToEmailValidation() },
        navigateToAppIntegrityError = { navigateToAppIntegrityError() },
        resetSendStatus = { uploadProgressViewModel.resetSendStatus() },
        showLocationBottomSheet = locationCallbacks,
    )

    LaunchedEffect(Unit) {
        uploadProgressViewModel.trackUploadProgress()
        uploadProgressViewModel.initializeLastTransfer()
    }

    HandleProgressState({ uiState }, navigateToUploadSuccess, navigateToUploadError, navigateBackToImportFiles, closeActivity)

    UploadProgressScreen(
        progressState = { uiState },
        sendStatus = { sendStatus },
        isNetworkAvailable = { isNetworkAvailable },
        totalSizeInBytes = totalSizeInBytes,
        adScreenType = adScreenType,
        onCancel = { uploadProgressViewModel.cancelUpload(onFailedCancellation = closeActivity) },
        showCancelBottomSheet = GetSetCallbacks(get = { showCancelBottomSheet }, set = { showCancelBottomSheet = it }),
        showLocationBottomSheet = locationCallbacks,
        closeActivity = closeActivity,
    )
}

@Composable
fun HandleSendStatus(
    sendStatus: () -> SendStatus,
    navigateToUploadError: () -> Unit,
    navigateToEmailValidation: () -> Unit,
    navigateToAppIntegrityError: () -> Unit,
    resetSendStatus: () -> Unit,
    showLocationBottomSheet: GetSetCallbacks<Boolean>,
) {
    LaunchedEffect(sendStatus()) {
        when (sendStatus()) {
            is SendStatus.Refused -> {
                navigateToAppIntegrityError()
                resetSendStatus()
            }
            // We are waiting to have access to the network before starting the app integrity and container step, so if we still
            // get a NoNetwork during that step then it's fine to navigate to the error screen and let the user retry manually
            is SendStatus.NoNetwork,
            is SendStatus.Failure -> {
                navigateToUploadError()
                resetSendStatus()
            }
            is SendStatus.RequireEmailValidation -> navigateToEmailValidation()
            is SendStatus.RestrictedLocation -> showLocationBottomSheet.set(true)
            SendStatus.Initial,
            SendStatus.Pending,
            is SendStatus.Success -> Unit
        }
    }
}

@Composable
private fun HandleProgressState(
    uiState: () -> UploadProgressUiState,
    navigateToUploadSuccess: (String, String) -> Unit,
    navigateToUploadError: () -> Unit,
    navigateBackToImportFiles: () -> Unit,
    closeActivity: () -> Unit,
) {
    val currentUiState = uiState()
    LaunchedEffect(uiState()) {
        when (currentUiState) {
            is UploadProgressUiState.Success -> navigateToUploadSuccess(currentUiState.transferUuid, currentUiState.transferUrl)
            is UploadProgressUiState.Error -> navigateToUploadError()
            is UploadProgressUiState.Cancel -> if (areTransferDataStillAvailable) navigateBackToImportFiles() else closeActivity()
            else -> Unit
        }
    }
}

@Composable
private fun UploadProgressScreen(
    progressState: () -> UploadProgressUiState,
    sendStatus: () -> SendStatus,
    isNetworkAvailable: () -> Boolean,
    totalSizeInBytes: Long,
    adScreenType: UploadProgressAdType,
    onCancel: () -> Unit,
    showCancelBottomSheet: GetSetCallbacks<Boolean>,
    showLocationBottomSheet: GetSetCallbacks<Boolean>,
    closeActivity: () -> Unit,
) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(stringResource(RCore.string.buttonCancel), modifier = it, onClick = { showCancelBottomSheet.set(true) })
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AdHeader(adScreenType)

            Spacer(Modifier.height(Margin.Medium))

            Text(text = stringResource(R.string.uploadProgressIndication), style = SwissTransferTheme.typography.h2)

            Spacer(Modifier.height(Margin.Mini))

            UploadStatus(isNetworkAvailable, progressState, sendStatus, totalSizeInBytes)

            Spacer(Modifier.height(Margin.Huge))
        }

        if (showCancelBottomSheet.get()) {
            CancelUploadBottomSheet(
                onCancel = {
                    showCancelBottomSheet.set(false)
                    onCancel()
                },
                closeButtonSheet = { showCancelBottomSheet.set(false) },
            )
        }

        if (showLocationBottomSheet.get()) {
            LocationBottomSheet(
                closeButtonSheet = {
                    showLocationBottomSheet.set(false)
                    closeActivity()
                },
            )
        }
    }
}

@Composable
private fun UploadStatus(
    isNetworkAvailable: () -> Boolean,
    progressState: () -> UploadProgressUiState,
    sendStatus: () -> SendStatus,
    totalSizeInBytes: Long
) {
    fun getProgressUiState() = when {
        !isNetworkAvailable() -> ProgressUiState.NoNetwork
        sendStatus() is SendStatus.Pending -> ProgressUiState.Initializing
        else -> ProgressUiState.Progress
    }

    var progressUiState by remember { mutableStateOf(getProgressUiState()) }

    LaunchedEffect(isNetworkAvailable(), sendStatus()) {
        progressUiState = getProgressUiState()
    }

    Crossfade(
        modifier = Modifier.fillMaxWidth(),
        targetState = progressUiState,
        label = "upload progress status",
    ) { state ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            val style = SwissTransferTheme.typography.labelRegular.copy(color = SwissTransferTheme.colors.secondaryTextColor)
            CompositionLocalProvider(value = LocalTextStyle provides style) {
                // NetworkUnavailable is always the biggest item of the three. It's composed here in order for the Box to be
                // constrained to its height. Having a constant height in all ProgressUiState makes the crossfade smoother looking
                NetworkUnavailable(modifier = Modifier.alpha(0f))

                when (state) {
                    ProgressUiState.Initializing -> Text(stringResource(R.string.transferInitializing))
                    ProgressUiState.Progress -> Progress(progressState, totalSizeInBytes)
                    ProgressUiState.NoNetwork -> NetworkUnavailable()
                }
            }
        }
    }
}

private enum class ProgressUiState {
    Initializing, Progress, NoNetwork
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationBottomSheet(closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        title = stringResource(R.string.sorry),
        description = stringResource(R.string.restrictedLocation),
        imageVector = AppIllus.MascotSearching.image(),
        bottomButton = {
            LargeButton(
                title = stringResource(R.string.contentDescriptionButtonClose),
                modifier = it,
                style = ButtonType.Primary,
                onClick = closeButtonSheet,
            )
        },
        onDismissRequest = closeButtonSheet,
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        UploadProgressScreen(
            progressState = { UploadProgressUiState.Progress(44_321_654L) },
            sendStatus = { SendStatus.Pending },
            isNetworkAvailable = { true },
            totalSizeInBytes = 76_321_894L,
            adScreenType = UploadProgressAdType.INDEPENDENCE,
            onCancel = {},
            showCancelBottomSheet = GetSetCallbacks(get = { false }, set = {}),
            showLocationBottomSheet = GetSetCallbacks(get = { false }, set = {}),
            closeActivity = {},
        )
    }
}
