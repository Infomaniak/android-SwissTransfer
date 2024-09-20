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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow


@Composable
fun ImageTile(
    title: String,
    description: String,
    isRemoveButtonVisible: Boolean,
    isCheckboxVisible: Boolean,
    isChecked: () -> Boolean = { false },
    onClick: () -> Unit,
    onRemove: (() -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(164 / 152f),
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://fastly.picsum.photos/id/1/200/300.jpg?hmac=jH5bDkLr6Tgy3oAg5khKCHeunZMHq0ehBZr6vGifPLY")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

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

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun ImageTilePreview() {
    SwissTransferTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .size(164.dp)
            )
            {
                var isChecked by remember { mutableStateOf(true) }
                ImageTile(
                    title = "Time-Clock-Circle--Streamline-Ultimate.svg (1).svg",
                    description = "1.4 Mo",
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
