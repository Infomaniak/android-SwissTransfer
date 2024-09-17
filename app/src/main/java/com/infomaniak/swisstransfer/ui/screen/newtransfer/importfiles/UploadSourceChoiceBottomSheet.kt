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
import androidx.compose.ui.platform.LocalContext
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.NewTransferActivity
import com.infomaniak.swisstransfer.ui.components.BottomSheetItem
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Camera
import com.infomaniak.swisstransfer.ui.images.icons.Folder
import com.infomaniak.swisstransfer.ui.images.icons.PolaroidLandscape
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet
import com.infomaniak.swisstransfer.ui.utils.launchActivity

@Composable
fun UploadSourceChoiceBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current

    if (isBottomSheetVisible()) {
        SwissTransferBottomSheet(
            onDismissRequest = onDismissRequest,
            titleRes = R.string.transferUploadSourceChoiceTitle,
            content = {
                Column {
                    BottomSheetItem(AppIcons.Camera, R.string.transferUploadSourceChoiceCamera) {

                        // TODO: Remove this, it's only here to be able to navigate to the
                        //  NewTransferActivity, since we don't have the FilePicker for now.
                        context.launchActivity(NewTransferActivity::class)

                        /* TODO */
                        onDismissRequest()
                    }
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
                    BottomSheetItem(AppIcons.PolaroidLandscape, R.string.transferUploadSourceChoiceGallery) {
                        /* TODO */
                        onDismissRequest()
                    }
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium))
                    BottomSheetItem(AppIcons.Folder, R.string.transferUploadSourceChoiceFiles) {
                        /* TODO */
                        onDismissRequest()
                    }
                }
            },
        )
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun UploadSourceChoiceBottomSheetPreview() {
    SwissTransferTheme {
        Surface {
            UploadSourceChoiceBottomSheet(isBottomSheetVisible = { true }, onDismissRequest = {})
        }
    }
}
