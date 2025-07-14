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
package com.infomaniak.swisstransfer.ui.components.transfer

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDownloadUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.components.FilesSize
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    snackbarHostState: SnackbarHostState,
    files: List<FileUi>,
    navigateToFolder: ((String) -> Unit)? = null,
    transferFlow: Flow<TransferUi> = emptyFlow(),
    runDownloadUi: suspend (
        ui: TransferDownloadUi,
        transfer: TransferUi,
        file: FileUi
    ) -> Nothing = { _, _, _ -> awaitCancellation() },
    previewUriForFile: (transfer: TransferUi, file: FileUi) -> Flow<Uri?> = { _, _ -> emptyFlow() },
    withFileSize: Boolean,
    withSpaceLeft: Boolean,
    isDownloadButtonVisible: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
    direction: TransferDirection? = null,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = Margin.Medium)
    ) {
        FilesSize(files, withFilesSize = withFileSize, withSpaceLeft)
        FileItemList(
            snackbarHostState = snackbarHostState,
            files = files,
            isDownloadButtonVisible = isDownloadButtonVisible,
            isRemoveButtonVisible = onFileRemoved != null,
            isCheckboxVisible = { false },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = {
                MatomoSwissTransfer.trackNewTransferEvent(MatomoName.DeleteFile)
                onFileRemoved?.invoke(it)
            },
            navigateToFolder = { navigateToFolder?.invoke(it) },
            transferFlow = transferFlow,
            runDownloadUi = runDownloadUi,
            previewUriForFile = previewUriForFile,
            direction = direction,
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            FilesDetailsScreen(
                paddingValues = PaddingValues(0.dp),
                snackbarHostState = remember { SnackbarHostState() },
                isDownloadButtonVisible = false,
                files = files,
                navigateToFolder = {},
                transferFlow = emptyFlow(),
                runDownloadUi = { _, _, _ -> awaitCancellation() },
                previewUriForFile = { _, _ -> emptyFlow() },
                withFileSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
                direction = TransferDirection.SENT
            )
        }
    }
}
