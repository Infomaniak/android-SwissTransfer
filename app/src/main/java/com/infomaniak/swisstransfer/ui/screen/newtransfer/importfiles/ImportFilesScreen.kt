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

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.illus.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun ImportFilesScreen(navigateToTransferTypeScreen: () -> Unit, closeActivity: () -> Unit) {

    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(true) }

    var fileNames by rememberSaveable { mutableStateOf(listOf<String>()) }
    val isNextButtonEnabled by remember { derivedStateOf { fileNames.isNotEmpty() } }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris: List<Uri> ->
        fileNames = uris.map { uri ->
            uri.lastPathSegment?.split("/")?.last() ?: "Unknown file"
        }
    }

    BottomStickyButtonScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.importFilesScreenTitle,
                navigationMenu = null,
                TopAppBarButton.closeButton { closeActivity() },
            )
        },
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
                enabled = isNextButtonEnabled,
                onClick = { navigateToTransferTypeScreen() },
            )
        },
        content = {
            if (fileNames.isNotEmpty()) {
                LazyColumn {
                    items(fileNames) { fileName ->
                        Text(text = fileName, modifier = Modifier.padding(8.dp))
                    }
                }
            } else {
                EmptyState(
                    icon = AppIllus.MascotWithMagnifyingGlass,
                    title = R.string.noFileTitle,
                    description = R.string.noFileDescription,
                )
            }

            UploadSourceChoiceBottomSheet(
                isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
                onFilePickerClicked = { filePickerLauncher.launch(arrayOf("*/*")) },
                closeBottomSheet = { showUploadSourceChoiceBottomSheet = false },
            )
        },
    )
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun ImportFilesScreenPreview() {
    SwissTransferTheme {
        ImportFilesScreen({}, {})
    }
}
