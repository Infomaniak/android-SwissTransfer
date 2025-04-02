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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer.MatomoScreen
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.AdHeader
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.NetworkUnavailable
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.Progress
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.upload.UploadState
import com.infomaniak.core.R as RCore

@Composable
fun UploadOngoingScreen(
    progressState: State<UploadState.Ongoing>,
    adScreenType: UploadProgressAdType,
    onCancelClick: () -> Unit,
) {

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.UploadProgress) }

    val progress by progressState
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(stringResource(RCore.string.buttonCancel), modifier = it, onClick = onCancelClick)
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

            UploadStatus(progress = { progress })

            Spacer(Modifier.height(Margin.Huge))
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
                    UploadState.Ongoing.Status.Initializing -> Text(stringResource(R.string.transferInitializing))
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

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        UploadOngoingScreen(
            progressState = rememberUpdatedState(
                UploadState.Ongoing(
                    status = UploadState.Ongoing.Status.InProgress,
                    info = UploadState.Info(
                        authorEmail = "",
                        totalSize = 50_000_000L,
                        type = TransferTypeUi.Link,
                    ),
                    uploadedBytes = 44_321_654L,
                )
            ),
            adScreenType = UploadProgressAdType.INDEPENDENCE,
            onCancelClick = {},
        )
    }
}
