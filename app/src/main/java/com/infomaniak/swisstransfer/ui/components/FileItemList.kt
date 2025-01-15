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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadComposeUi
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadUi
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FileItemList(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    files: List<FileUi>,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: () -> Boolean,
    isUidChecked: (String) -> Boolean,
    setUidCheckStatus: (String, Boolean) -> Unit,
    onRemoveUid: ((String) -> Unit)? = null,
    navigateToFolder: ((uid: String) -> Unit)? = null,
    header: (@Composable LazyGridItemScope.() -> Unit)? = null,
    transferFlow: Flow<TransferUi> = emptyFlow(),
    runDownloadUi: suspend (
        ui: TransferDownloadUi,
        transfer: TransferUi,
        file: FileUi
    ) -> Nothing = { _, _, _ -> awaitCancellation() }
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
            val downloadUi: TransferDownloadComposeUi = remember(lifecycle) { TransferDownloadComposeUi(lifecycle, snackbarHostState) }
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
                    else -> downloadUi.onFileClick
                },
                onRemove = { onRemoveUid?.invoke(file.uid) },
                previewOverlay = {
                    downloadUi.CardCornerButton(Modifier.align(Alignment.TopEnd))
                    downloadUi.CardProgressBar(Modifier.align(Alignment.BottomStart).fillMaxWidth())
                }
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun FileItemListPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        FileItemList(
            files = files,
            snackbarHostState = remember { SnackbarHostState() },
            isRemoveButtonVisible = false,
            isCheckboxVisible = { true },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = {},
        )
    }
}
