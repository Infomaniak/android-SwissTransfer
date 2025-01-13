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

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.components.transfer.FilesDetailsScreen
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.FilesDetailsUiState
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun NewTransferFilesDetailsScreen(
    importFilesViewModel: ImportFilesViewModel,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    withFileDelete: Boolean,
    navigateBack: (() -> Unit),
) {
    val uiState by importFilesViewModel.filesDetailsUiState.collectAsStateWithLifecycle()

    when (uiState) {
        is FilesDetailsUiState.EmptyFiles -> navigateBack()
        is FilesDetailsUiState.Success -> {
            NewTransferFilesDetailsScreen(
                files = (uiState as FilesDetailsUiState.Success).files,
                withFilesSize = withFilesSize,
                withSpaceLeft = withSpaceLeft,
                onFileRemoved = getOnFileRemoveCallback(importFilesViewModel, withFileDelete),
                navigateBack = navigateBack,
            )
        }
    }
}

private fun getOnFileRemoveCallback(
    importFilesViewModel: ImportFilesViewModel,
    withFileDelete: Boolean,
): ((String) -> Unit)? {
    return if (withFileDelete) {
        { importFilesViewModel.removeFileByUid(it) }
    } else null
}

@Composable
private fun NewTransferFilesDetailsScreen(
    files: List<FileUi>,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
    navigateBack: (() -> Unit),
) {
    Scaffold(
        topBar = {
            SwissTransferTopAppBar(
                navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
            )
        }
    ) { paddingValues ->
        FilesDetailsScreen(
            paddingValues = paddingValues,
            files = files,
            withFileSize = withFilesSize,
            withSpaceLeft = withSpaceLeft,
            onFileRemoved = onFileRemoved
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
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
