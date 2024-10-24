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

import android.text.format.Formatter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

// TODO: Get the data class from the shared kmp code
data class FileUi(
    val uid: String,
    val fileName: String,
    val fileSizeInBytes: Long,
    val mimeType: String?,
    val uri: String,
)

@Composable
fun FileItem(
    file: FileUi,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean = { false },
    onClick: () -> Unit,
    onRemove: (() -> Unit)? = null,
) {
    FileItemContent(
        content = {
            FilePreview(
                file = file,
                circleColor = SwissTransferTheme.materialColors.surface,
                circleSize = 64.dp,
            )
        },
        onClick = onClick,
        isCheckboxVisible = isCheckboxVisible,
        isChecked = isChecked,
        isRemoveButtonVisible = isRemoveButtonVisible,
        onRemove = onRemove,
        title = file.fileName,
        description = Formatter.formatShortFileSize(LocalContext.current, file.fileSizeInBytes),
    )
}

@Composable
private fun FileItemContent(
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean,
    isRemoveButtonVisible: Boolean,
    onRemove: (() -> Unit)?,
    title: String,
    description: String
) {
    Card(
        onClick = onClick,
        modifier = Modifier.aspectRatio(164 / 152f),
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.background),
        shape = CustomShapes.SMALL,
        border = BorderStroke(width = 1.dp, SwissTransferTheme.materialColors.outlineVariant)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(SwissTransferTheme.materialColors.surfaceContainerHighest)
        ) {
            content()

            if (isCheckboxVisible) {
                Checkbox(
                    checked = isChecked(),
                    onCheckedChange = null,
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(Margin.Small),
                )
            }

            if (isRemoveButtonVisible) CrossCircleButton(onClick = onRemove)
        }

        Column(Modifier.padding(Margin.Mini)) {
            Text(
                text = title,
                style = SwissTransferTheme.typography.bodySmallRegular,
                color = SwissTransferTheme.colors.primaryTextColor,
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
            )
            Text(
                text = description,
                style = SwissTransferTheme.typography.bodySmallRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun FileItemPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(164.dp)
            )
            {
                var isChecked by remember { mutableStateOf(true) }

                val iconFile = files[0]
                FileItem(
                    iconFile,
                    isRemoveButtonVisible = true,
                    isCheckboxVisible = true,
                    isChecked = { isChecked },
                    onClick = { isChecked = !isChecked },
                    onRemove = {},
                )

                Spacer(modifier = Modifier.height(16.dp))

                val imageFile = files[1]
                FileItem(
                    file = imageFile,
                    isRemoveButtonVisible = true,
                    isCheckboxVisible = true,
                    isChecked = { isChecked },
                    onClick = { isChecked = !isChecked },
                    onRemove = {},
                )
            }
        }
    }
}
