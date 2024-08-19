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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.app.Camera
import com.infomaniak.swisstransfer.ui.icons.app.Folder
import com.infomaniak.swisstransfer.ui.icons.app.PolaroidLandscape
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ImportFilesScreen() {
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(false) }

    BottomStickyButtonScaffold(
        topBar = { SwissTransferTobAppBar() },
        topButton = { modifier ->
            LargeButton(
                modifier = modifier,
                titleRes = R.string.buttonAddFiles,
                imageVector = AppIcons.Add,
                style = ButtonType.TERTIARY,
                onClick = { showUploadSourceChoiceBottomSheet = true },
            )
        },
        bottomButton = { modifier ->
            LargeButton(
                modifier = modifier,
                titleRes = R.string.buttonNext,
                onClick = { /*TODO*/ },
            )
        },
    ) {
        Column {
            UploadSourceChoiceBottomSheet(
                isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
                onDismissRequest = { showUploadSourceChoiceBottomSheet = false },
            )
        }
    }
}

@Composable
private fun UploadSourceChoiceBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onDismissRequest: () -> Unit,
) {
    if (isBottomSheetVisible()) {
        SwissTransferBottomSheet(
            onDismissRequest = onDismissRequest,
            titleRes = R.string.transferUploadSourceChoiceTitle,
            content = {
                Column {
                    BottomSheetItem(AppIcons.Camera, R.string.transferUploadSourceChoiceCamera) { /*TODO*/ }
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium), color = SwissTransferTheme.colors.divider)
                    BottomSheetItem(AppIcons.PolaroidLandscape, R.string.transferUploadSourceChoiceGallery) { /*TODO*/ }
                    HorizontalDivider(Modifier.padding(horizontal = Margin.Medium), color = SwissTransferTheme.colors.divider)
                    BottomSheetItem(AppIcons.Folder, R.string.transferUploadSourceChoiceFiles) { /*TODO*/ }
                }
            },
        )
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun ImportFilesScreenPreview() {
    SwissTransferTheme {
        ImportFilesScreen()
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun ImportChoiceBottomSheetPreview() {
    SwissTransferTheme {
        Surface {
            UploadSourceChoiceBottomSheet({ true }, {})
        }
    }
}
