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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.infomaniak.core.common.DownloadStatus
import com.infomaniak.core.common.DownloadStatus.InProgress
import com.infomaniak.core.common.autoCancelScope
import com.infomaniak.core.common.snackbarMsgResId
import com.infomaniak.core.ui.compose.basics.CallableState
import com.infomaniak.core.ui.compose.basics.withForwardTo
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownBar
import com.infomaniak.swisstransfer.ui.images.icons.Checkmark
import com.infomaniak.swisstransfer.ui.images.icons.Stop
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.guardedCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.isActive
import splitties.coroutines.raceOf
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import kotlin.time.Duration.Companion.seconds
import com.infomaniak.core.common.R as RCore

@OptIn(ExperimentalPermissionsApi::class)
class TransferDownloadComposeUi(
    override val lifecycle: Lifecycle,
    private val snackbarHostState: SnackbarHostState,
    private val writeExternalStoragePermissionState: PermissionState?,
    private val direction: TransferDirection?,
) : TransferDownloadUi {

    private val downloadRequest = CallableState<Unit>()
    private val removalRequest = CallableState<Unit>()
    private val openRequest = CallableState<Unit>()
    private var downloadStatus by mutableStateOf<DownloadStatus?>(null)
    private var supportsPreview by mutableStateOf(false)

    override suspend fun awaitDownloadRequest() = downloadRequest.awaitOneCall()

    override suspend fun showStatusAndAwaitRemovalRequest(
        statusFlow: StateFlow<DownloadStatus?>,
        supportsPreview: Boolean,
    ) {
        this.supportsPreview = supportsPreview
        raceOf(
            { removalRequest.awaitOneCall() },
            {
                @OptIn(ExperimentalCoroutinesApi::class)
                statusFlow.withForwardTo(
                    onNewValue = { downloadStatus = it },
                    defaultValue = null,
                ).transformLatest { status ->
                    if (status is DownloadStatus.Failed) {
                        snackbarHostState.showSnackbar(
                            message = appCtx.getString(status.reason.snackbarMsgResId()),
                            actionLabel = appCtx.getString(android.R.string.ok),
                            withDismissAction = true,
                            duration = SnackbarDuration.Indefinite,
                        )
                        emit(Unit)
                    }
                }.first()
            },
        )
    }

    override suspend fun awaitOpenRequest() {
        openRequest.awaitOneCall()
    }

    val onFileClick: () -> Unit
        get() = when {
            downloadRequest.isAwaitingCall -> {
                direction?.let(::trackTransferEvent)
                downloadRequest
            }
            openRequest.isAwaitingCall -> {
                direction?.let(::trackTransferEvent)
                openRequest
            }
            removalRequest.isAwaitingCall && downloadStatus is DownloadStatus.Failed -> {
                direction?.let(::trackTransferEvent)
                removalRequest
            }
            else -> fun() {}
            // TODO: If download is in progress, maybe request confirmation and remove?
        }

    private fun trackTransferEvent(direction: TransferDirection) {
        MatomoSwissTransfer.trackTransferEvent(direction, MatomoName.ConsultOneFile)
    }

    @Composable
    fun TopAppBarButton() {
        if (removalRequest.isAwaitingCall) {
            DownloadStatus { btnData, action, progressIndicator ->
                Box(contentAlignment = Alignment.Center) {
                    progressIndicator()
                    TopAppBarButton(
                        icon = btnData.icon,
                        contentDescResId = btnData.contentDescResId,
                        enabled = action.isAwaitingCall,
                        onClick = action,
                    )
                }
            }
        } else {
            TopAppBarButtons.Download(
                enabled = downloadRequest.isAwaitingCall,
                onClick = writeExternalStoragePermissionState.guardedCallback { downloadRequest() },
            )
        }
    }

    @Composable
    fun BottomBarItem(modifier: Modifier = Modifier) {
        if (removalRequest.isAwaitingCall) {
            DownloadStatus { btnData, action, progressIndicator ->
                BottomBarButton(
                    icon = btnData.icon,
                    labelResId = btnData.labelResId,
                    onClick = action,
                    enabled = action.isAwaitingCall,
                    modifier = modifier,
                    extraBackgroundContent = progressIndicator,
                )
            }
        } else {
            BottomBarButton(
                icon = ButtonData.download.icon,
                labelResId = ButtonData.download.labelResId,
                enabled = downloadRequest.isAwaitingCall,
                onClick = writeExternalStoragePermissionState.guardedCallback { downloadRequest() },
                modifier = modifier,
            )
        }
    }

    @Composable
    fun CardCornerButton(modifier: Modifier = Modifier) {
        if (removalRequest.isAwaitingCall) {
            DownloadStatus { btnData, action, _ ->
                CardCornerButton(
                    icon = btnData.icon,
                    labelResId = btnData.labelResId,
                    onClick = action,
                    enabled = action.isAwaitingCall,
                    modifier = modifier,
                )
            }
        } else {
            CardCornerButton(
                icon = ButtonData.download.icon,
                labelResId = ButtonData.download.labelResId,
                enabled = downloadRequest.isAwaitingCall,
                onClick = writeExternalStoragePermissionState.guardedCallback { downloadRequest() },
                modifier = modifier,
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CardCornerButton(
        icon: ImageVector,
        labelResId: Int,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides RippleConfiguration(color = Color.White)) {
            IconButton(
                onClick = onClick,
                modifier = modifier.padding(Margin.Micro),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = SwissTransferTheme.colors.fileStatusButtonBackground,
                ),
                enabled = enabled,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(labelResId),
                    modifier = Modifier.size(16.dp),
                )
            }
        }
    }

    @Composable
    fun CardProgressBar(modifier: Modifier) {
        when (val status = downloadStatus) {
            DownloadStatus.Complete -> Unit
            is DownloadStatus.Failed -> {
                LinearProgressIndicator(
                    progress = { 1f },
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.error,
                )
            }
            is InProgress -> {
                DownloadProgressBar(rememberUpdatedState(status), modifier = modifier)
            }
            is DownloadStatus.Paused, DownloadStatus.Pending -> {
                LinearProgressIndicator(modifier = modifier)
            }
            else -> Unit
        }
    }

    @Composable
    private fun DownloadStatus(
        button: @Composable (
            data: ButtonData,
            action: CallableState<Unit>,
            progressIndicator: @Composable BoxScope.() -> Unit,
        ) -> Unit,
    ) {
        when (val status = downloadStatus) {
            DownloadStatus.Complete -> {
                button(ButtonData.downloaded, openRequest) {}
            }
            is DownloadStatus.Failed -> {
                button(ButtonData.failed, removalRequest) {}
            }
            is InProgress -> {
                button(ButtonData.downloading, removalRequest) {
                    DownloadProgress(rememberUpdatedState(status))
                }
            }
            is DownloadStatus.Paused, DownloadStatus.Pending, null -> {
                button(ButtonData.downloading, removalRequest) { CircularProgressIndicator() }
            }
        }
    }

    private class ButtonData(
        val icon: ImageVector,
        @StringRes val labelResId: Int,
        @StringRes val contentDescResId: Int = labelResId,
    ) {
        companion object {
            val download = ButtonData(
                icon = AppImages.AppIcons.ArrowDownBar,
                labelResId = R.string.buttonDownload,
            )
            val downloaded = ButtonData(
                icon = AppImages.AppIcons.Checkmark,
                labelResId = R.string.buttonDownloaded,
            )
            val downloading = ButtonData(
                icon = AppImages.AppIcons.Stop,
                labelResId = R.string.buttonDownloading,
                contentDescResId = RCore.string.buttonCancel,
            )
            val failed = ButtonData(
                icon = AppImages.AppIcons.ArrowDownBar,
                labelResId = RCore.string.errorDownload,
            )
        }
    }

    @Composable
    private fun DownloadProgressBar(
        progressStatus: State<InProgress>,
        modifier: Modifier = Modifier,
    ) {
        val status by progressStatus
        LinearProgressIndicator(progress = status::getProgress, modifier = modifier, drawStopIndicator = {})
    }

    @Composable
    private fun DownloadProgress(progressStatus: State<InProgress>) {
        val status by progressStatus
        CircularProgressIndicator(progress = status::getProgress)
    }
}

private fun InProgress.getProgress(): Float {
    return (if (totalSizeInBytes == 0L) 0 else downloadedBytes.toDouble() / totalSizeInBytes).toFloat()
}

@OptIn(ExperimentalPermissionsApi::class)
@PreviewLightDark
@Composable
private fun Preview() = SwissTransferTheme {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val ui = remember {
        TransferDownloadComposeUi(
            lifecycle = lifecycle,
            snackbarHostState = SnackbarHostState(),
            writeExternalStoragePermissionState = null,
            direction = TransferDirection.SENT
        )
    }
    LaunchedEffect(Unit) {
        while (coroutineContext.isActive) autoCancelScope {
            val statusFlow = flow {
                emit(InProgress(downloadedBytes = 9_345_064L, totalSizeInBytes = 84_234_122L))
                awaitCancellation()
            }.stateIn(scope = this)
            ui.showStatusAndAwaitRemovalRequest(statusFlow, supportsPreview = false)
            delay(0.7.seconds)
        }
    }
    Column {
        ui.TopAppBarButton()
        Spacer(Modifier.weight(1f))
        HorizontalDivider()
        Row(Modifier.height(80.dp)) {
            ui.BottomBarItem()
        }
    }
}
