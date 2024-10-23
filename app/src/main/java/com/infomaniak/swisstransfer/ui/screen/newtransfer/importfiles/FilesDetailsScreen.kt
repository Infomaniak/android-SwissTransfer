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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.FilesSize
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun FilesDetailsScreen(
    title: String? = null,
    filesList: () -> List<FileUi>,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
    onCloseClicked: (() -> Unit),
    navigateBack: (() -> Unit),
) {
    val files = filesList()
    Column {
        SwissTransferTopAppBar(
            title = title,
            titleRes = R.string.importFilesScreenTitle,
            navigationMenu = TopAppBarButton.backButton { navigateBack() },
            TopAppBarButton.closeButton { onCloseClicked() },
        )

        FilesSize(files, withSpaceLeft)
        FileItemList(
            modifier = Modifier.padding(horizontal = Margin.Medium),
            files = files,
            isRemoveButtonVisible = onFileRemoved != null,
            isCheckboxVisible = false,
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = { onFileRemoved?.invoke(it) },
        )
    }
}

@PreviewAllWindows
@Composable
private fun FilesDetailsScreenPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            FilesDetailsScreen(
                title = "My album",
                filesList = { files },
                withSpaceLeft = true,
                onFileRemoved = { },
                onCloseClicked = { },
                navigateBack = { },
            )
        }
    }
}
