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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
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
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    BackHandler(enabled = !showBottomSheet, onBack = { showBottomSheet = true })

    HandleSendStatus(
        snackbarHostState = snackbarHostState,
        sendStatus = { sendStatus },
        navigateToUploadError = { navigateToUploadError() },
        navigateToEmailValidation = { navigateToEmailValidation() },
        navigateToAppIntegrityError = { navigateToAppIntegrityError() },
        resetSendStatus = { uploadProgressViewModel.resetSendStatus() }
    )

    LaunchedEffect(Unit) {
        uploadProgressViewModel.trackUploadProgress()
        uploadProgressViewModel.initNewTransfer()
    }

    HandleProgressState({ uiState }, navigateToUploadSuccess, navigateToUploadError, navigateBackToImportFiles, closeActivity)

    UploadProgressScreen(
        progressState = { uiState },
        sendStatus = { sendStatus },
        isNetworkAvailable = { isNetworkAvailable },
        totalSizeInBytes = totalSizeInBytes,
        showBottomSheet = GetSetCallbacks(get = { showBottomSheet }, set = { showBottomSheet = it }),
        adScreenType = adScreenType,
        onCancel = { uploadProgressViewModel.cancelUpload(onFailedCancellation = closeActivity) }
    )
}

@Composable
fun HandleSendStatus(
    snackbarHostState: SnackbarHostState,
    sendStatus: () -> SendStatus,
    navigateToUploadError: () -> Unit,
    navigateToEmailValidation: () -> Unit,
    navigateToAppIntegrityError: () -> Unit,
    resetSendStatus: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(sendStatus()) {
        when (val actionResult = sendStatus()) {
            is SendStatus.Success -> {
                // TODO: Did we still need to reset?
                // // If the user cancels the transfer while in UploadProgress, we're gonna popBackStack to ImportFiles.
                // // If we don't reset the ImportFiles state machine, we'll automatically navigate-back to UploadProgress again.
                // // So, before leaving ImportFiles to go to UploadProgress, we need to reset the ImportFiles state machine.
                // resetSendActionResult()

                // TODO: Do we need to check for this?
                // navigateToUploadProgress(actionResult.totalSize)
            }
            is SendStatus.Refused -> {
                navigateToAppIntegrityError()
                resetSendStatus()
            }
            // We are waiting to have access to the network before starting the app integrity and container step, so if we still
            // get a NoNetwork during that step then it's fine to navigate to the error screen and let the user retry manually
            is SendStatus.NoNetwork,
            is SendStatus.Failure -> {
                navigateToUploadError()
                resetSendStatus() // Needed apparently
            }
            is SendStatus.RequireEmailValidation -> {
                navigateToEmailValidation()
                // resetSendActionResult() // Not needed apparently
            }
            else -> Unit
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
    showBottomSheet: GetSetCallbacks<Boolean>,
) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(stringResource(RCore.string.buttonCancel), modifier = it, onClick = { showBottomSheet.set(true) })
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

        if (showBottomSheet.get()) {
            CancelUploadBottomSheet(
                onCancel = {
                    showBottomSheet.set(false)
                    onCancel()
                },
                closeButtonSheet = { showBottomSheet.set(false) },
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
    ) { progressUiState ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            val style = SwissTransferTheme.typography.labelRegular.copy(color = SwissTransferTheme.colors.secondaryTextColor)
            CompositionLocalProvider(value = LocalTextStyle provides style) {
                // NetworkUnavailable is always the biggest item of the three. It's composed here in order for the Box to be
                // constrained to its height. Having a constant height in all ProgressUiState makes the crossfade smoother looking
                NetworkUnavailable(modifier = Modifier.alpha(0f))

                when (progressUiState) {
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
            showBottomSheet = GetSetCallbacks(get = { false }, set = {}),
        )
    }
}
