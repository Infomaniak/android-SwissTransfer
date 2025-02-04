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
package com.infomaniak.swisstransfer.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadComposeUi
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadUi
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.guardedCallback
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FileItemList(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    writeExternalStoragePermissionState: PermissionState? = null,
    files: List<FileUi>,
    isDownloadButtonVisible: Boolean,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: () -> Boolean,
    isUidChecked: (String) -> Boolean,
    setUidCheckStatus: (String, Boolean) -> Unit,
    onRemoveUid: ((String) -> Unit)? = null,
    navigateToFolder: ((uid: String) -> Unit)? = null,
    header: (@Composable LazyGridItemScope.() -> Unit)? = null,
    transferFlow: Flow<TransferUi> = emptyFlow(),
    runDownloadUi: suspend (ui: TransferDownloadUi, transfer: TransferUi, file: FileUi) -> Unit = { _, _, _ ->
        awaitCancellation()
    },
    uriForFile: (transfer: TransferUi, file: FileUi) -> Flow<Uri?> = { _, _ -> emptyFlow() },
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(Margin.Medium),
        horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
        contentPadding = PaddingValues(bottom = Margin.Mini),
    ) {

        header?.let {
            item(
                span = { GridItemSpan(maxLineSpan) },
                content = it,
            )
        }

        items(files, key = { it.uid }) { file ->

            val downloadUi: TransferDownloadComposeUi = remember(lifecycle) {
                TransferDownloadComposeUi(lifecycle, snackbarHostState, writeExternalStoragePermissionState)
            }

            LaunchedEffect(Unit) { transferFlow.collect { transfer -> runDownloadUi(downloadUi, transfer, file) } }

            FileItem(
                modifier = Modifier.animateItem(),
                file = file,
                isRemoveButtonVisible = isRemoveButtonVisible,
                isCheckboxVisible = isCheckboxVisible(),
                isChecked = { isUidChecked(file.uid) },
                onClick = when {
                    isCheckboxVisible() -> fun() { setUidCheckStatus(file.uid, !isUidChecked(file.uid)) }
                    file.isFolder -> fun() { navigateToFolder?.invoke(file.uid) }
                    else -> writeExternalStoragePermissionState.guardedCallback { downloadUi.onFileClick() }
                },
                uriForFile = produceState(file.localPath) {
                    transferFlow.collectLatest { transfer ->
                        uriForFile(transfer, file).collect { uri: Uri? ->
                            value = uri.toString()
                        }
                    }
                },
                onRemove = { onRemoveUid?.invoke(file.uid) },
                previewOverlay = {
                    if (isDownloadButtonVisible) {
                        downloadUi.CardCornerButton(Modifier.align(Alignment.TopEnd))
                        downloadUi.CardProgressBar(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth(),
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@PreviewLightAndDark
@Composable
private fun FileItemListPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        FileItemList(
            snackbarHostState = remember { SnackbarHostState() },
            files = files,
            isRemoveButtonVisible = false,
            isDownloadButtonVisible = false,
            isCheckboxVisible = { true },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = {},
        )
    }
}
