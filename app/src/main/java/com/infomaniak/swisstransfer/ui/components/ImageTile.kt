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

import android.net.Uri
import android.text.format.Formatter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infomaniak.library.filetypes.FileType
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.LocalIsDarkMode
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import com.infomaniak.swisstransfer.ui.utils.fileType
import com.infomaniak.swisstransfer.ui.utils.hasPreview

// TODO: Get the interface from the shared kmp code
interface FileUiItem {
    val uid: String
    val fileName: String
    val fileSizeInBytes: Long
    val mimeType: String?
    val uri: String
}

@Composable
fun ImageTile(
    file: FileUiItem,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean = { false },
    onClick: () -> Unit,
    onRemove: (() -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    ImageTileContent(
        content = {
            var displayPreview by rememberSaveable { mutableStateOf(file.hasPreview) }

            if (displayPreview) {
                FileImage(file.uri.toUri()) { displayPreview = false }
            } else {
                FileIcon(file.fileType)
            }
        },
        onClick = onClick,
        isCheckboxVisible = isCheckboxVisible,
        isChecked = isChecked,
        onCheckedChange = onCheckedChange,
        isRemoveButtonVisible = isRemoveButtonVisible,
        onRemove = onRemove,
        title = file.fileName,
        description = Formatter.formatShortFileSize(LocalContext.current, file.fileSizeInBytes),
    )
}

@Composable
private fun ImageTileContent(
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    isRemoveButtonVisible: Boolean,
    onRemove: (() -> Unit)?,
    title: String,
    description: String
) {
    Card(
        onClick = onClick,
        modifier = Modifier.aspectRatio(164 / 152f),
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.background),
        shape = Shapes.small,
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
                    onCheckedChange = onCheckedChange,
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                )
            }

            if (isRemoveButtonVisible) {
                Button(
                    modifier = Modifier
                        .size(Margin.XXLarge)
                        .padding(12.dp)
                        .align(Alignment.TopEnd),
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = SwissTransferTheme.colors.imageTileRemoveButtonBackground),
                    onClick = onRemove ?: {},
                ) {
                    Icon(
                        modifier = Modifier.size(Margin.Small),
                        imageVector = AppImages.AppIcons.CrossThick,
                        contentDescription = stringResource(R.string.contentDescriptionButtonRemove),
                        tint = Color.White
                    )
                }
            }
        }

        Column(Modifier.padding(Margin.Small)) {
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

@Composable
private fun FileImage(uri: Uri, onError: () -> Unit) {
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
private fun FileIcon(fileType: FileType) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        val surfaceColor = SwissTransferTheme.materialColors.surface
        Canvas(modifier = Modifier.size(64.dp)) {
            drawCircle(color = surfaceColor)
        }

        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = fileType.icon,
            contentDescription = null,
            tint = fileType.color(LocalIsDarkMode.current)
        )
    }
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun ImageTilePreview() {
    SwissTransferTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(164.dp)
            )
            {
                var isChecked by remember { mutableStateOf(true) }


                val iconFile = object : FileUiItem {
                    override val fileName: String = "How to not get fired.pdf"
                    override val uid: String = fileName
                    override val fileSizeInBytes: Long = 10302130
                    override val mimeType: String? = null
                    override val uri: String = ""
                }
                ImageTile(
                    iconFile,
                    isRemoveButtonVisible = true,
                    isCheckboxVisible = true,
                    isChecked = { isChecked },
                    onClick = { isChecked = !isChecked },
                    onRemove = {},
                )

                Spacer(modifier = Modifier.height(16.dp))

                val imageFile = object : FileUiItem {
                    override val fileName: String = "Time-Clock-Circle--Streamline-Ultimate.svg (1).png"
                    override val uid: String = fileName
                    override val fileSizeInBytes: Long = 456782
                    override val mimeType: String? = null
                    override val uri: String = "https://picsum.photos/200/300"
                }
                ImageTile(
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
