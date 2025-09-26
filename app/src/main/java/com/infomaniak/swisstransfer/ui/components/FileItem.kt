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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils

@Composable
fun FileItem(
    modifier: Modifier = Modifier,
    file: FileUi,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean = { false },
    onClick: (() -> Unit)? = null,
    onRemove: (() -> Unit)? = null,
    previewUriForFile: State<String?> = rememberUpdatedState(file.thumbnailPath),
    previewOverlay: @Composable BoxScope.() -> Unit = {}
) {
    FileItemContent(
        modifier = modifier,
        onClick = onClick,
        isCheckboxVisible = isCheckboxVisible,
        isChecked = isChecked,
        isRemoveButtonVisible = isRemoveButtonVisible,
        onRemove = onRemove,
        title = file.fileName,
        description = getDescription(file),
        content = {
            FilePreview(
                file = file,
                previewUri = previewUriForFile.value,
                circleColor = SwissTransferTheme.materialColors.surface,
                circleSize = 64.dp,
                showFileName = false,
            )
            previewOverlay()
        },
    )
}

@Composable
private fun getDescription(file: FileUi): String {
    return if (file.isFolder) {
        ""
    } else {
        HumanReadableSizeUtils.getHumanReadableSize(LocalContext.current, file.fileSize)
    }
}

@Composable
private fun FileItemContent(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean,
    isRemoveButtonVisible: Boolean,
    onRemove: (() -> Unit)?,
    title: String,
    description: String,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        modifier = modifier.then(getCardModifier(onClick)),
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.background),
        shape = CustomShapes.SMALL,
        border = BorderStroke(width = Dimens.BorderWidth, color = SwissTransferTheme.materialColors.outlineVariant),
    ) {
        Box(
            modifier = Modifier
                .weight(1.0f)
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

private fun getCardModifier(onClick: (() -> Unit)? = null): Modifier {
    return Modifier
        .aspectRatio(164.0f / 152.0f)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
}

@PreviewLightAndDark
@Composable
private fun FileItemPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(Margin.Large)
                    .width(164.dp),
            )
            {
                var isChecked by remember { mutableStateOf(true) }

                val iconFile = files[0]
                FileItem(
                    file = iconFile,
                    isRemoveButtonVisible = true,
                    isCheckboxVisible = true,
                    isChecked = { isChecked },
                    onClick = { isChecked = !isChecked },
                    onRemove = {},
                )

                Spacer(Modifier.height(Margin.Medium))

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
