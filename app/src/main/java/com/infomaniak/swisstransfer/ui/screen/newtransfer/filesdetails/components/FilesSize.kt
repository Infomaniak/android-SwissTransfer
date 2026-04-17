/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TextDotText
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.formatSpaceLeft
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getSpaceLeft

@Composable
fun FilesSize(filesCount: Int, filesSizeInBytes: Long, withFilesSize: Boolean, withSpaceLeft: Boolean) {
    Row(modifier = Modifier.padding(vertical = Margin.Medium)) {
        val filesInfo = getFilesInfo(filesCount = filesCount, filesSizeInBytes = filesSizeInBytes, withFilesSize = withFilesSize)
        TextDotText(
            firstText = { Text(filesInfo.filesCountText) },
            secondText = { Text(filesInfo.filesSizeText) },
        )
        Spacer(modifier = Modifier.weight(1.0f))
        if (withSpaceLeft) {
            Spacer(modifier = Modifier.width(Margin.Medium))
            Text(
                text = filesInfo.spaceLeftText,
                color = SwissTransferTheme.colors.secondaryTextColor,
                style = SwissTransferTheme.typography.bodySmallRegular,
            )
        }
    }
}

@Composable
private fun getFilesInfo(filesCount: Int, filesSizeInBytes: Long, withFilesSize: Boolean): FileInfo {
    val filesCountText = pluralStringResource(id = R.plurals.filesCount, count = filesCount, filesCount)

    val context = LocalContext.current
    val fileSizeText: String = if (withFilesSize) {
        HumanReadableSizeUtils.getHumanReadableSize(context = context, fileSizeInBytes = filesSizeInBytes)
    } else {
        ""
    }

    val spaceLeftText = formatSpaceLeft { context.getSpaceLeft(fileSizeInBytes = filesSizeInBytes) }

    return FileInfo(filesCountText, fileSizeText, spaceLeftText)
}

private data class FileInfo(val filesCountText: String, val filesSizeText: String, val spaceLeftText: String)

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun FileSizePreview() {
    SwissTransferTheme {
        Surface {
            Column {
                FilesSize(filesCount = 1, filesSizeInBytes = 2000, withFilesSize = false, withSpaceLeft = true)
                FilesSize(filesCount = 1, filesSizeInBytes = 2000, withFilesSize = true, withSpaceLeft = true)
                FilesSize(filesCount = 1, filesSizeInBytes = 2000, withFilesSize = true, withSpaceLeft = false)
                FilesSize(filesCount = 1, filesSizeInBytes = 2000, withFilesSize = false, withSpaceLeft = false)
            }
        }
    }
}
