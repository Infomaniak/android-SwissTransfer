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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import android.content.res.Configuration
import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.AddThick
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.formatSpaceLeft
import kotlinx.parcelize.Parcelize

@Composable
fun ImportedFilesCard(
    modifier: Modifier = Modifier,
    files: () -> List<FileUi>,
    humanReadableSize: () -> String,
    showUploadSourceChoiceBottomSheet: () -> Unit,
    removeFileByUid: (uid: String) -> Unit,
) {
    SwissTransferCard(modifier) {
        SharpRippleButton(onClick = { /* TODO */ }) {
            TextDotText(
                firstText = {
                    val fileCount = files().count()
                    pluralStringResource(R.plurals.filesCount, fileCount, fileCount)
                },
                secondText = { formatSpaceLeft(humanReadableSize) },
                modifier = Modifier.padding(start = Margin.Medium),
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = AppImages.AppIcons.ChevronRightThick,
                contentDescription = null,
                modifier = Modifier.padding(Margin.Medium),
                tint = SwissTransferTheme.colors.iconColor,
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = Margin.Medium, end = Margin.Medium, bottom = Margin.Medium),
            horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
        ) {
            item(key = TransferLazyRowKey(TransferLazyRowKey.Type.ADD_BUTTON)) {
                AddNewFileButton(Modifier.animateItem()) { showUploadSourceChoiceBottomSheet() }
            }

            items(
                items = files().asReversed(),
                key = { TransferLazyRowKey(TransferLazyRowKey.Type.FILE, it.uid) },
            ) { file ->
                SmallFileItem(
                    modifier = Modifier.animateItem(),
                    file = file,
                    smallFileTileSize = SmallFileTileSize.LARGE,
                    onRemove = { removeFileByUid(file.uid) },
                )
            }
        }
    }
}

@Composable
private fun AddNewFileButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.size(80.dp),
        shape = CustomShapes.MEDIUM,
        colors = ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.surface,
            contentColor = SwissTransferTheme.materialColors.primary,
        ),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = AppImages.AppIcons.AddThick,
            contentDescription = null,
        )
    }
}

@Parcelize
private data class TransferLazyRowKey(
    val type: Type,
    val fileUid: String? = null,
) : Parcelable {
    enum class Type {
        ADD_BUTTON, FILE
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ImportedFilesCardPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        ImportedFilesCard(
            modifier = Modifier.padding(Margin.Medium),
            files = { files },
            humanReadableSize = { "20 GB" },
            showUploadSourceChoiceBottomSheet = {},
            removeFileByUid = {},
        )
    }
}
