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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components

import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SmallFileItem
import com.infomaniak.swisstransfer.ui.components.SmallFileTileSize
import com.infomaniak.swisstransfer.ui.components.SwissTransferCard
import com.infomaniak.swisstransfer.ui.components.TextDotText
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.AddThick
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.CanSendStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.findIssueOrNull
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.formatSpaceLeft
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getHumanReadableSize
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import kotlinx.parcelize.Parcelize

@Composable
fun ImportedFilesCard(
    modifier: Modifier = Modifier,
    files: () -> List<FileUi>,
    canSendStatus: () -> CanSendStatus,
    pickFiles: () -> Unit,
    navigateToFilesDetails: () -> Unit,
) {

    SwissTransferCard(
        modifier = modifier,
        onClick = if (files().isNotEmpty()) navigateToFilesDetails else null,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextDotText(
                firstText = { FilesCountText(files().count(), canSendStatus) },
                secondText = { FilesSizeText({ files().sumOf { it.fileSize } }, canSendStatus) },
                modifier = Modifier.padding(start = Margin.Medium),
            )
            Spacer(Modifier.weight(1.0f))
            Icon(
                imageVector = AppIcons.ChevronRightThick,
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
                AddNewFileButton(Modifier.animateItem()) { pickFiles() }
            }

            items(
                items = files().asReversed(),
                key = { TransferLazyRowKey(TransferLazyRowKey.Type.FILE, it.uid) },
            ) { file ->
                SmallFileItem(
                    modifier = Modifier.animateItem(),
                    file = file,
                    smallFileTileSize = SmallFileTileSize.LARGE,
                )
            }
        }
    }
}

@Composable
private fun FilesCountText(fileCount: Int, canSendStatus: () -> CanSendStatus) {
    val maxCountExceeded = canSendStatus().findIssueOrNull<CanSendStatus.Issue.Files.MaxCountExceeded>()
    val string = when (maxCountExceeded) {
        null -> pluralStringResource(R.plurals.filesCount, fileCount, fileCount)
        else -> stringResource(R.string.fileCountOverDisplayOnly, fileCount, maxCountExceeded.maxCount)
    }
    val ttsFriendlyText = maxCountExceeded?.let {
        stringResource(R.string.fileCountOverTtsFriendly, fileCount, maxCountExceeded.maxCount)
    }
    Text(
        text = string,
        modifier = if (ttsFriendlyText == null) Modifier else Modifier.semantics { text = AnnotatedString(ttsFriendlyText) },
        color = if (maxCountExceeded == null) Color.Unspecified else MaterialTheme.colorScheme.error,
    )
}

@Composable
private fun FilesSizeText(filesSize: () -> Long, canSendStatus: () -> CanSendStatus) {
    val context = LocalContext.current
    val humanReadableSize by remember {
        derivedStateOf {
            val usedSpace = filesSize()
            val spaceLeft = (FileUtils.MAX_FILES_SIZE - usedSpace).coerceAtLeast(0)
            getHumanReadableSize(context, spaceLeft)
        }
    }
    val maxSizeExceeded = canSendStatus().findIssueOrNull<CanSendStatus.Issue.Files.MaxSizeExceeded>()
    val string = when (maxSizeExceeded) {
        null -> formatSpaceLeft { humanReadableSize }
        else -> HumanReadableSizeUtils.formatSpaceOver(
            actualSize = maxSizeExceeded.actualSize,
            maxSize = maxSizeExceeded.maxSize,
            ttsFriendly = false,
        )
    }
    val ttsFriendlyText = maxSizeExceeded?.let {
        HumanReadableSizeUtils.formatSpaceOver(
            actualSize = maxSizeExceeded.actualSize,
            maxSize = maxSizeExceeded.maxSize,
            ttsFriendly = true,
        )
    }
    Text(
        text = string,
        modifier = when (ttsFriendlyText) {
            null -> Modifier
            else -> Modifier.semantics { text = AnnotatedString(ttsFriendlyText) }
        },
        color = if (maxSizeExceeded == null) Color.Unspecified else MaterialTheme.colorScheme.error,
    )
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
            imageVector = AppIcons.AddThick,
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

@PreviewLightAndDark
@Composable
private fun ImportedFilesCardPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        ImportedFilesCard(
            modifier = Modifier.padding(Margin.Medium),
            files = { files },
            canSendStatus = { CanSendStatus.Yes },
            pickFiles = {},
            navigateToFilesDetails = {},
        )
    }
}
