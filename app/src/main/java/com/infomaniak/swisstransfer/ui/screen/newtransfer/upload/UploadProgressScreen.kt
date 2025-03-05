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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.R
import com.infomaniak.swisstransfer.R.string
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet.RedCrossPaperPlanes
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.AdHeader
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.NetworkUnavailable
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.Progress
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.upload.UploadState

@Composable
fun UploadOngoingScreen(
    progressState: State<UploadState.Ongoing>,
    adScreenType: UploadProgressAdType,
    onCancel: () -> Unit,
    showCancelBottomSheet: GetSetCallbacks<Boolean>,
    showLocationBottomSheet: GetSetCallbacks<Boolean>,
    exitNewTransfer: () -> Unit,
) {
    val progress by progressState
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(stringResource(R.string.buttonCancel), modifier = it, onClick = { showCancelBottomSheet.set(true) })
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AdHeader(adScreenType)

            Spacer(Modifier.height(Margin.Medium))

            Text(text = stringResource(string.uploadProgressIndication), style = SwissTransferTheme.typography.h2)

            Spacer(Modifier.height(Margin.Mini))

            UploadStatus(progress = { progress })

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
                    exitNewTransfer()
                },
            )
        }
    }
}
@Composable
private fun UploadStatus(progress: () -> UploadState.Ongoing) {

    val progressStatus by remember { derivedStateOf { progress().status } }
    val totalSizeInBytes by remember { derivedStateOf { progress().info.totalSize } }

    Crossfade(
        modifier = Modifier.fillMaxWidth(),
        targetState = progressStatus,
        label = "upload progress status",
    ) { status ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            val style = SwissTransferTheme.typography.labelRegular.copy(color = SwissTransferTheme.colors.secondaryTextColor)
            CompositionLocalProvider(value = LocalTextStyle provides style) {
                // NetworkUnavailable is always the biggest item of the three. It's composed here in order for the Box to be
                // constrained to its height. Having a constant height in all ProgressUiState makes the crossfade smoother looking
                NetworkUnavailable(modifier = Modifier.alpha(0f))

                when (status) {
                    UploadState.Ongoing.Status.Initializing -> Text(stringResource(string.transferInitializing))
                    UploadState.Ongoing.Status.InProgress -> Progress(
                        uploadedSize = { progress().uploadedBytes },
                        totalSizeInBytes = totalSizeInBytes
                    )
                    UploadState.Ongoing.Status.WaitingForInternet -> NetworkUnavailable()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CancelUploadBottomSheet(onCancel: () -> Unit, closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        title = stringResource(string.uploadCancelConfirmBottomSheetTitle),
        imageVector = AppIllus.RedCrossPaperPlanes.image(),
        topButton = {
            LargeButton(
                title = stringResource(string.buttonCancelTransfer),
                modifier = it,
                style = ButtonType.Destructive,
                onClick = onCancel,
            )
        },
        bottomButton = {
            LargeButton(
                title = stringResource(string.buttonCloseAndContinue),
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
        title = stringResource(string.sorry),
        description = stringResource(string.restrictedLocation),
        imageVector = AppIllus.MascotSearching.image(),
        bottomButton = {
            LargeButton(
                title = stringResource(string.contentDescriptionButtonClose),
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
        UploadOngoingScreen(
            progressState = rememberUpdatedState(UploadState.Ongoing(
                status = UploadState.Ongoing.Status.InProgress,
                info = UploadState.Info(
                    authorEmail = "",
                    totalSize = 50_000_000L,
                    type = TransferTypeUi.Link
                ),
                uploadedBytes = 44_321_654L,
            )),
            adScreenType = UploadProgressAdType.INDEPENDENCE,
            onCancel = {},
            showCancelBottomSheet = GetSetCallbacks(get = { false }, set = {}),
            showLocationBottomSheet = GetSetCallbacks(get = { false }, set = {}),
            exitNewTransfer = {},
        )
    }
}
