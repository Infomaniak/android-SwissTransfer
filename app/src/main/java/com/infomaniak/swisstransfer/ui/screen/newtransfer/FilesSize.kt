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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.formatSpaceLeft
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getFilesSize
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getSpaceLeft

@Composable
fun FilesSize(files: List<FileUi>, withSpaceLeft: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = Margin.Medium)
    ) {
        val filesCount = files.count()
        val filesSize = LocalContext.current.getFilesSize(files)
        Text(
            pluralStringResource(R.plurals.filesCount, filesCount, filesCount),
            modifier = Modifier
                .padding(start = Margin.Medium)
                .wrapContentWidth(),
            color = SwissTransferTheme.colors.secondaryTextColor,
            style = SwissTransferTheme.typography.bodySmallRegular,
        )
        Text(
            "•",
            modifier = Modifier.padding(horizontal = Margin.Small),
            color = SwissTransferTheme.colors.secondaryTextColor,
            style = SwissTransferTheme.typography.bodySmallRegular,
        )
        Text(
            filesSize,
            color = SwissTransferTheme.colors.secondaryTextColor,
            style = SwissTransferTheme.typography.bodySmallRegular,
        )
        if (withSpaceLeft) {
            val context = LocalContext.current
            val spaceLeft = formatSpaceLeft { context.getSpaceLeft(files) }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(horizontal = Margin.Medium),
                text = spaceLeft,
                color = SwissTransferTheme.colors.secondaryTextColor,
                style = SwissTransferTheme.typography.bodySmallRegular,
            )
        }
    }
}

@Preview
@Composable
fun FileSizePreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            FilesSize(files, withSpaceLeft = true)
        }
    }
}