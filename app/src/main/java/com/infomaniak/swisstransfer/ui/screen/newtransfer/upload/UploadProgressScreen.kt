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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
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
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.*
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Uploading.Status
import com.infomaniak.core.R as RCore

@Composable
fun UploadOngoingScreen(
    progressState: State<Ongoing>,
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun UploadStatus(progress: () -> Ongoing) {
    updateTransition(
        targetState = progress(),
        label = "ongoing upload state"
    ).Crossfade(
        modifier = Modifier.fillMaxWidth().animateContentSize(),
        contentKey = { it::class },
    ) { state ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            val style = SwissTransferTheme.typography.labelRegular.copy(color = SwissTransferTheme.colors.secondaryTextColor)
            CompositionLocalProvider(value = LocalTextStyle provides style) {
                // The block below prevents small height changes for the containing Box and its host layout.
                with(LocalDensity.current) {
                    val linesCount = 3
                    val lineHeight = style.fontSize.toDp()
                    Spacer(Modifier.height(lineHeight * linesCount))
                }

                when (state) {
                    is CheckingFiles -> Text(stringResource(R.string.checkingFiles))
                    is CheckingAppIntegrity -> Text(stringResource(R.string.transferInitializing))
                    is Uploading -> UploadProgress(progress)
                }
            }
        }
    }
}

@Composable
private fun UploadProgress(state: () -> Ongoing) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val status by remember { derivedStateOf { (state() as? Uploading)?.status } }
    val totalSize by remember { derivedStateOf { state().info.totalSize } }
    val uploadedBytes by remember { derivedStateOf { (state() as? Uploading)?.uploadedBytes ?: 0L } }
    when (status) {
        null, Status.InProgress -> Unit
        Status.WaitingForInternet -> NetworkUnavailable()
    }
    Progress(
        uploadedSize = { uploadedBytes },
        totalSizeInBytes = totalSize
    )
}

@PreviewAllWindows
@Composable
private fun UploadingPreview() {
    SwissTransferTheme {
        UploadOngoingScreen(
            progressState = rememberUpdatedState(
                Uploading(
                    status = Status.InProgress,
                    info = TransferInfo(
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

@PreviewAllWindows
@Composable
private fun WaitingForInternetPreview() {
    SwissTransferTheme {
        UploadOngoingScreen(
            progressState = rememberUpdatedState(
                Uploading(
                    status = Status.WaitingForInternet,
                    info = TransferInfo(
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
