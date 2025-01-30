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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.infomaniak.core.DownloadStatus
import com.infomaniak.core.autoCancelScope
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.core.compose.basics.withForwardTo
import com.infomaniak.core.snackbarMsgResId
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownBar
import com.infomaniak.swisstransfer.ui.images.icons.Checkmark
import com.infomaniak.swisstransfer.ui.images.icons.Stop
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.checkWritePermission
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import splitties.coroutines.raceOf
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import kotlin.time.Duration.Companion.seconds
import com.infomaniak.core.R as RCore

@OptIn(ExperimentalPermissionsApi::class)
class TransferDownloadComposeUi(
    override val lifecycle: Lifecycle,
    private val snackbarHostState: SnackbarHostState,
    private val writeExternalStoragePermissionState: PermissionState? = null,
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
            downloadRequest.isAwaitingCall -> downloadRequest
            openRequest.isAwaitingCall -> openRequest
            removalRequest.isAwaitingCall && downloadStatus is DownloadStatus.Failed -> removalRequest
            else -> fun() {}
            // TODO: If download is in progress, maybe request confirmation and remove?
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
                onClick = { writeExternalStoragePermissionState.checkWritePermission { downloadRequest() } },
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
                onClick = downloadRequest,
                modifier = modifier,
            )
        }
    }

    @Composable
    fun CardCornerButton(modifier: Modifier = Modifier) {
        if (removalRequest.isAwaitingCall) {
            DownloadStatus { btnData, action, _ ->
                if (action == openRequest && supportsPreview) {
                    // Don't overlay the checkmark once the file is openable and has a preview.
                    return@DownloadStatus
                }
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
                onClick = downloadRequest,
                modifier = modifier,
            )
        }
    }

    @Composable
    private fun CardCornerButton(
        icon: ImageVector,
        labelResId: Int,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
    ) = IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        enabled = enabled,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(labelResId),
            modifier = Modifier.size(18.dp),
        )
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
            is DownloadStatus.InProgress -> {
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
            is DownloadStatus.InProgress -> {
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
        progressStatus: State<DownloadStatus.InProgress>,
        modifier: Modifier = Modifier,
    ) {
        val status by progressStatus
        LinearProgressIndicator(
            progress = { status.let { it.downloadedBytes.toFloat() / it.totalSizeInBytes } },
            modifier = modifier,
        )
    }

    @Composable
    private fun DownloadProgress(progressStatus: State<DownloadStatus.InProgress>) {
        val status by progressStatus
        CircularProgressIndicator(
            progress = { status.let { it.downloadedBytes.toFloat() / it.totalSizeInBytes } },
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@PreviewLightDark
@Composable
private fun Preview() = SwissTransferTheme {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val ui = remember { TransferDownloadComposeUi(lifecycle, SnackbarHostState()) }
    LaunchedEffect(Unit) {
        while (coroutineContext.isActive) autoCancelScope {
            val statusFlow = flow {
                emit(DownloadStatus.InProgress(downloadedBytes = 9_345_064L, totalSizeInBytes = 84_234_122L))
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
