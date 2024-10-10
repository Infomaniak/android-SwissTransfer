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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infomaniak.library.filetypes.FileType
import com.infomaniak.swisstransfer.ui.theme.LocalIsDarkMode
import com.infomaniak.swisstransfer.ui.utils.fileType
import com.infomaniak.swisstransfer.ui.utils.hasPreview

@Composable
fun FilePreview(
    file: FileUiItem,
    circleColor: Color,
    circleSize: Dp
) {
    var displayPreview by rememberSaveable { mutableStateOf(file.hasPreview) }

    if (displayPreview) {
        FileThumbnail(file.uri, onError = { displayPreview = false })
    } else {
        FileIcon(file.fileType, circleColor, circleSize)
    }
}

@Composable
private fun FileThumbnail(uri: String, onError: () -> Unit) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        onError = { onError() },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun FileIcon(fileType: FileType, color: Color, circleSize: Dp) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.size(circleSize)) {
            drawCircle(color = color)
        }

        Icon(
            modifier = Modifier.size(circleSize / 2),
            imageVector = fileType.icon,
            contentDescription = null,
            tint = fileType.color(LocalIsDarkMode.current)
        )
    }
}
