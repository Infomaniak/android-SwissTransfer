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
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.components.FilesSize
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    snackbarHostState: SnackbarHostState,
    files: List<FileUi>,
    navigateToFolder: ((String) -> Unit)? = null,
    withFileSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
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
            isDownloadButtonVisible = false,
            isRemoveButtonVisible = onFileRemoved != null,
            isCheckboxVisible = { false },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = { onFileRemoved?.invoke(it) },
            navigateToFolder = { navigateToFolder?.invoke(it) }
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
                files = files,
                navigateToFolder = {},
                withFileSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
            )
        }
    }
}
