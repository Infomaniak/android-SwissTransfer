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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import FilesDetailsScreen
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import getOnFileRemoveCallback

@Composable
fun NewTransferFilesDetailsScreen(
    importFilesViewModel: ImportFilesViewModel = hiltViewModel<ImportFilesViewModel>(),
    folderUuid: String? = null,
    navigateToDetails: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    withFileDelete: Boolean,
    navigateBack: (() -> Unit),
) {
    // If we don't have a folderUuid, it means we have to load files from importedFiles in ImportFilesViewModel
    val files by importFilesViewModel.files.collectAsStateWithLifecycle()

    if (files?.isEmpty() == true) navigateBack()

    LaunchedEffect(Unit) {
        importFilesViewModel.loadFiles(folderUuid)
    }

    files?.let {
        NewTransferFilesDetailsScreen(
            files = it,
            navigateToDetails = navigateToDetails,
            withFilesSize = withFilesSize,
            withSpaceLeft = withSpaceLeft,
            onFileRemoved = getOnFileRemoveCallback(importFilesViewModel, withFileDelete),
            navigateBack = navigateBack,
        )
    }
}

@Composable
private fun NewTransferFilesDetailsScreen(
    files: List<FileUi>,
    navigateToDetails: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
    navigateBack: (() -> Unit),
) {
    Scaffold(
        topBar = {
            SwissTransferTopAppBar(navigationMenu = TopAppBarButton.backButton { navigateBack() })
        }
    ) { paddingValues ->
        FilesDetailsScreen(
            paddingValues = paddingValues,
            files = files,
            navigateToDetails = navigateToDetails,
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
                navigateToDetails = { _ -> },
                withFilesSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
                navigateBack = {},
            )
        }
    }
}
