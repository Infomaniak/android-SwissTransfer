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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.core.common.utils.percent
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils

@Composable
fun Progress(
    processedSize: () -> Long,
    totalSizeInBytes: Long,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Percentage({ processedSize() }, totalSizeInBytes)
        Text(text = " - ")
        UploadedSize { processedSize() }
        Text(text = " / ")
        TotalSize(totalSizeInBytes)
    }
}

/**
 * Text style is provided through [LocalTextStyle] inside [Progress], thanks to [ProvideTextStyle].
 */
@Composable
private fun Percentage(uploadedSizeInBytes: () -> Long, totalSizeInBytes: Long) {
    val percentageNoDecimals by remember { derivedStateOf { percent(uploadedSizeInBytes(), totalSizeInBytes) } }
    Text(text = "${percentageNoDecimals}%")
}

/**
 * Text style is provided through [LocalTextStyle] inside [Progress], thanks to [ProvideTextStyle].
 */
@Composable
private fun UploadedSize(uploadedSizeInBytes: () -> Long) {
    val context = LocalContext.current
    val humanReadableSize by remember(context) {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, uploadedSizeInBytes()) }
    }

    Text(text = humanReadableSize)
}

/**
 * Text style is provided through [LocalTextStyle] inside [Progress], thanks to [ProvideTextStyle].
 */
@Composable
private fun TotalSize(totalSizeInBytes: Long) {
    val context = LocalContext.current
    val humanReadableTotalSize by remember(context) {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, totalSizeInBytes) }
    }

    Text(text = humanReadableTotalSize)
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Progress(
                processedSize = { 73_614L },
                totalSizeInBytes = 3_279_218L,
            )
        }
    }
}
