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
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun FileItemList(
    modifier: Modifier = Modifier,
    files: List<FileUi>,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: () -> Boolean,
    isUidChecked: (String) -> Boolean,
    setUidCheckStatus: (String, Boolean) -> Unit,
    onRemoveUid: ((String) -> Unit)? = null,
    header: (@Composable LazyGridItemScope.() -> Unit)? = null,
) {
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
            FileItem(
                file = file,
                isRemoveButtonVisible = isRemoveButtonVisible,
                isCheckboxVisible = isCheckboxVisible(),
                isChecked = { isUidChecked(file.uid) },
                onClick = { if (isCheckboxVisible()) setUidCheckStatus(file.uid, !isUidChecked(file.uid)) },
                onRemove = { onRemoveUid?.invoke(file.uid) },
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
            isRemoveButtonVisible = false,
            isCheckboxVisible = { true },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = {},
        )
    }
}
