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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.FilesSize
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun FilesDetailsScreen(
    importFilesViewModel: ImportFilesViewModel = hiltViewModel<ImportFilesViewModel>(),
    folderUuid: String? = null,
    navigateToDetails: (String) -> Unit,
    withFileSize: Boolean,
    withSpaceLeft: Boolean,
    withFileDelete: Boolean,
    onCloseClicked: (() -> Unit),
    navigateBack: (() -> Unit),
) {
    // If we don't have a folderUuid, it means we have to load files from importedFiles in ImportFilesViewModel
    val files by importFilesViewModel.getFiles(folderUuid).collectAsStateWithLifecycle(emptyList())

    if (files.isEmpty() == true) navigateBack()

    FilesDetailsScreen(
        files = files,
        navigateToDetails = navigateToDetails,
        withFileSize = withFileSize,
        withSpaceLeft = withSpaceLeft,
        onFileRemoved = getOnFileRemoveCallback(withFileDelete, importFilesViewModel),
        onCloseClicked = onCloseClicked,
        navigateBack = navigateBack,
    )
}

private fun getOnFileRemoveCallback(
    withFileDelete: Boolean,
    importFilesViewModel: ImportFilesViewModel,
): ((String) -> Unit)? {
    return if (withFileDelete) {
        { importFilesViewModel.removeFileByUid(it) }
    } else null
}

@Composable
private fun FilesDetailsScreen(
    title: String = "",
    files: List<FileUi>,
    navigateToDetails: (String) -> Unit,
    withFileSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
    onCloseClicked: (() -> Unit),
    navigateBack: (() -> Unit),
) {
    Column {
        SwissTransferTopAppBar(
            title = title,
            navigationMenu = TopAppBarButton.backButton { navigateBack() },
            TopAppBarButton.closeButton { onCloseClicked() },
        )

        FilesSize(files, withFileSize = withFileSize, withSpaceLeft)
        FileItemList(
            modifier = Modifier.padding(horizontal = Margin.Medium),
            files = files,
            isRemoveButtonVisible = onFileRemoved != null,
            isCheckboxVisible = { false },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = { onFileRemoved?.invoke(it) },
            onClick = {
                //TODO Check here if the clicked file is a folder before navigating
                navigateToDetails(it)
            }
        )
    }
}

@PreviewAllWindows
@Composable
private fun FilesDetailsScreenPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            FilesDetailsScreen(
                title = "Title",
                files = files,
                navigateToDetails = { _ -> },
                withFileSize = true,
                withSpaceLeft = true,
                onCloseClicked = {},
                onFileRemoved = {},
                navigateBack = {},
            )
        }
    }
}
