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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.workers.UploadWorker
import java.util.Locale

@Composable
fun Progress(
    progressState: () -> UploadWorker.UploadProgressUiState,
    totalSizeInBytes: Long,
) {
    Row {
        Percentage({ progressState().uploadedSize }, totalSizeInBytes)
        Text(" - ")
        UploadedSize({ progressState().uploadedSize })
        Text(" / ")
        TotalSize(totalSizeInBytes)
    }
}
@Composable
private fun Percentage(uploadedSizeInBytes: () -> Long, totalSizeInBytes: Long) {
    val percentageNoDecimals by remember {
        derivedStateOf {
            val percentage = (uploadedSizeInBytes().toFloat() / totalSizeInBytes)
            String.format(Locale.getDefault(), "%d", (percentage * 100).toInt())
        }
    }

    Text("$percentageNoDecimals%")
}
@Composable
private fun UploadedSize(uploadedSizeInBytes: () -> Long) {
    val context = LocalContext.current
    val humanReadableSize by remember {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, uploadedSizeInBytes()) }
    }

    Text(humanReadableSize)
}
@Composable
private fun TotalSize(totalSizeInBytes: Long) {
    val context = LocalContext.current
    val humanReadableTotalSize by remember {
        derivedStateOf { HumanReadableSizeUtils.getHumanReadableSize(context, totalSizeInBytes) }
    }

    Text(humanReadableTotalSize)
}
