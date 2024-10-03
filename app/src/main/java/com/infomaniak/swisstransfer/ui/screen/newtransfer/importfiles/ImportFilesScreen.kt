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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.images.illus.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferFile
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun ImportFilesScreen(
    newTransferViewModel: NewTransferViewModel = viewModel(),
    navigateToTransferTypeScreen: () -> Unit,
    closeActivity: () -> Unit,
) {
    val transferFiles by newTransferViewModel.transferFiles.collectAsStateWithLifecycle()
    ImportFilesScreen({ transferFiles }, newTransferViewModel::addFiles, navigateToTransferTypeScreen, closeActivity)
}

@Composable
private fun ImportFilesScreen(
    transferFiles: () -> List<TransferFile>,
    addFiles: (List<Uri>) -> Unit,
    navigateToTransferTypeScreen: () -> Unit,
    closeActivity: () -> Unit,
) {
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(true) }
    val isNextButtonEnabled by remember { derivedStateOf { transferFiles().isNotEmpty() } }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris: List<Uri> ->
        addFiles(uris)
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
            if (transferFiles().isEmpty()) {
                EmptyState(
                    icon = AppIllus.MascotWithMagnifyingGlass,
                    title = R.string.noFileTitle,
                    description = R.string.noFileDescription,
                )
            } else {
                LazyColumn {
                    items(items = transferFiles(), key = { it.fileName }) { file ->
                        Text(text = "${file.fileName} - ${file.size}")
                    }
                }
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
        ImportFilesScreen({ emptyList() }, {}, navigateToTransferTypeScreen = {}, closeActivity = {})
    }
}
