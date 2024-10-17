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

import android.content.Context
import android.net.Uri
import android.text.format.Formatter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

private const val TOTAL_FILE_SIZE: Long = 50_000_000_000L

@Composable
fun ImportFilesScreen(
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
    closeActivity: () -> Unit,
) {
    val files by newTransferViewModel.filesDebounced.collectAsStateWithLifecycle()
    val filesToImportCount by newTransferViewModel.filesToImportCount.collectAsStateWithLifecycle()
    ImportFilesScreen(
        files = { files },
        filesToImportCount = { filesToImportCount },
        removeFileByUid = newTransferViewModel::removeFileByUid,
        addFiles = newTransferViewModel::addFiles,
        closeActivity = closeActivity
    )
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    filesToImportCount: () -> Int,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
) {
    val context = LocalContext.current
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(true) }
    val formattedSizeWithUnits by remember {
        derivedStateOf {
            val totalFileSize = files().sumOf { it.fileSizeInBytes }
            getFormattedSizeWithUnits(totalFileSize, context)
        }
    }
    val isSendButtonEnabled by remember { derivedStateOf { files().isNotEmpty() && filesToImportCount() == 0 } }

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
            // TODO: Animate
            LargeButton(
                modifier = modifier,
                titleRes = R.string.transferSendButton,
                style = ButtonType.PRIMARY,
                showIndeterminateProgress = { !isSendButtonEnabled },
                onClick = { /*TODO*/ },
            )
        },
        content = {
            SelectedFilesCard(
                modifier = Modifier.padding(Margin.Medium),
                files = files,
                formattedSizeWithUnits = { formattedSizeWithUnits },
                showUploadSourceChoiceBottomSheet = { showUploadSourceChoiceBottomSheet = true },
                removeFileByUid = removeFileByUid,
            )

            UploadSourceChoiceBottomSheet(
                isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
                onFilePickerClicked = { filePickerLauncher.launch(arrayOf("*/*")) },
                closeBottomSheet = { showUploadSourceChoiceBottomSheet = false },
            )
        },
    )
}

private fun getFormattedSizeWithUnits(usedSpace: Long, context: Context): String {
    val spaceLeft = (TOTAL_FILE_SIZE - usedSpace).coerceAtLeast(0)
    return Formatter.formatShortFileSize(context, spaceLeft)
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun ImportFilesScreenPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        ImportFilesScreen({ files }, { 0 }, {}, {}, closeActivity = {})
    }
}
