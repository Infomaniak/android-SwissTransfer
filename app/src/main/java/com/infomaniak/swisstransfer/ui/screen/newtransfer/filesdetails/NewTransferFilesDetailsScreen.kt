/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.SinglePaneScaffold
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.components.transfer.FilesDetailsScreen
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.FilesDetailsUiState
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun NewTransferFilesDetailsScreen(
    pickFilesViewModel: PickFilesViewModel,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    withFileDelete: Boolean,
    navigateBack: (() -> Unit),
) {
    val uiState by pickFilesViewModel.filesDetailsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.NewTransferFileList) }
    LaunchedEffect(uiState) {
        if (uiState is FilesDetailsUiState.EmptyFiles) navigateBack()
    }

    NewTransferFilesDetailsScreen(
        files = (uiState as? FilesDetailsUiState.Success)?.files?.asReversed() ?: emptyList(),
        withFilesSize = withFilesSize,
        withSpaceLeft = withSpaceLeft,
        onFileRemoved = getOnFileRemoveCallback(pickFilesViewModel, withFileDelete),
        navigateBack = navigateBack,
    )
}

private fun getOnFileRemoveCallback(
    pickFilesViewModel: PickFilesViewModel,
    withFileDelete: Boolean,
): ((String) -> Unit)? {
    return if (withFileDelete) {
        { pickFilesViewModel.removeFileByUri(it) }
    } else null
}

@Composable
private fun NewTransferFilesDetailsScreen(
    files: List<FileUi>,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uriString: String) -> Unit)? = null,
    navigateBack: (() -> Unit),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    SinglePaneScaffold(
        topBar = {
            SwissTransferTopAppBar(
                navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        FilesDetailsScreen(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            files = files,
            withFileSize = withFilesSize,
            withSpaceLeft = withSpaceLeft,
            isDownloadButtonVisible = false,
            onFileRemoved = onFileRemoved,
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        SwissTransferScaffold {
            Surface {
                NewTransferFilesDetailsScreen(
                    files = files,
                    withFilesSize = true,
                    withSpaceLeft = true,
                    onFileRemoved = {},
                    navigateBack = {},
                )
            }
        }
    }
}
