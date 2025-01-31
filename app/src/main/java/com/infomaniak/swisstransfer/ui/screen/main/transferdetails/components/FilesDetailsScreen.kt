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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.FilesDetailsViewModel
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun FilesDetailsScreen(
    filesDetailsViewModel: FilesDetailsViewModel = hiltViewModel<FilesDetailsViewModel>(),
    folderUuid: String,
    navigateToFolder: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    navigateBack: () -> Unit,
    close: (() -> Unit),
) {
    val files by filesDetailsViewModel.filesInFolder.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(folderUuid) {
        filesDetailsViewModel.loadFiles(folderUuid)
    }

    files?.let {
        SwissTransferScaffold(
            topBar = {
                SwissTransferTopAppBar(
                    navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
                    actions = { TopAppBarButtons.Close(close) },
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            FilesDetailsScreen(
                snackbarHostState = snackbarHostState,
                files = it,
                navigateToFolder = navigateToFolder,
                withFileSize = withFilesSize,
                withSpaceLeft = withSpaceLeft,
            )
        }
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
                files = files,
                navigateToFolder = {},
                withFileSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
            )
        }
    }
}
