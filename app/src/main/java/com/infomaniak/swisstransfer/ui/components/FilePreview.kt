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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.video.VideoFrameDecoder
import com.infomaniak.core2.filetypes.FileType
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.LocalIsDarkMode
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.fileType
import com.infomaniak.swisstransfer.ui.utils.hasPreview

@Composable
fun FilePreview(
    file: FileUi,
    previewUri: String? = file.localPath,
    circleColor: Color,
    circleSize: Dp,
    showFileName: Boolean,
    fileIconContentPadding: PaddingValues = PaddingValues(),
) {
    var shouldDisplayPreview by rememberSaveable(previewUri) { mutableStateOf(file.hasPreview) }

    if (shouldDisplayPreview && previewUri != null) {
        FileThumbnail(previewUri, onError = { shouldDisplayPreview = false })
    } else {
        FileIcon(file.fileType, circleColor, circleSize, file.fileName, showFileName, fileIconContentPadding)
    }
}

private val videoFrameDecoderFactory = VideoFrameDecoder.Factory()

@Composable
private fun FileThumbnail(uri: String, onError: () -> Unit) {
    val context = LocalContext.current
    val imageRequest = remember(uri) {
        ImageRequest.Builder(context)
            .data(uri)
            .decoderFactory(videoFrameDecoderFactory)
            .crossfade(true)
            .build()
    }
    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        onError = { onError() },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun FileIcon(
    fileType: FileType,
    color: Color,
    circleSize: Dp,
    fileName: String,
    showFileName: Boolean,
    iconContentPadding: PaddingValues,
) {
    Box(
        contentAlignment = if (showFileName) Alignment.TopCenter else Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(if (showFileName) iconContentPadding else PaddingValues()),
    ) {
        Canvas(modifier = Modifier.size(circleSize)) {
            drawCircle(color = color)
        }

        Icon(
            modifier = Modifier
                .size(circleSize)
                .padding(circleSize / 4),
            imageVector = fileType.icon,
            contentDescription = null,
            tint = fileType.color(LocalIsDarkMode.current),
        )

        if (showFileName) {
            Text(
                text = fileName,
                style = SwissTransferTheme.typography.labelRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun FileNamePreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface(modifier = Modifier.size(140.dp)) {
            FilePreview(
                file = files.first(),
                circleColor = SwissTransferTheme.materialColors.surfaceContainerHighest,
                circleSize = 40.dp,
                showFileName = true,
                fileIconContentPadding = PaddingValues(8.dp),
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun NoFileNamePreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface(modifier = Modifier.size(140.dp)) {
            FilePreview(
                file = files.first(),
                circleColor = SwissTransferTheme.materialColors.surfaceContainerHighest,
                circleSize = 40.dp,
                showFileName = false,
                fileIconContentPadding = PaddingValues(8.dp),
            )
        }
    }
}
