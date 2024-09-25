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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomSheetItem
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Camera
import com.infomaniak.swisstransfer.ui.images.icons.Folder
import com.infomaniak.swisstransfer.ui.images.icons.PolaroidLandscape
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun UploadSourceChoiceBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onDismissRequest: () -> Unit,
) {
    if (isBottomSheetVisible()) {
        SwissTransferBottomSheet(
            onDismissRequest = onDismissRequest,
            titleRes = R.string.transferUploadSourceChoiceTitle,
            content = {
                Column {
                    BottomSheetItem(
                        imageVector = AppIcons.Camera,
                        titleRes = R.string.transferUploadSourceChoiceCamera,
                        onClick = { /* TODO */ },
                    )
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
                    BottomSheetItem(
                        imageVector = AppIcons.PolaroidLandscape,
                        titleRes = R.string.transferUploadSourceChoiceGallery,
                        onClick = { /* TODO */ },
                    )
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
                    BottomSheetItem(
                        imageVector = AppIcons.Folder,
                        titleRes = R.string.transferUploadSourceChoiceFiles,
                        onClick = { /* TODO */ },
                    )
                }
            },
        )
    }
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun UploadSourceChoiceBottomSheetPreview() {
    SwissTransferTheme {
        Surface {
            UploadSourceChoiceBottomSheet(isBottomSheetVisible = { true }, onDismissRequest = {})
        }
    }
}
