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
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.ImportedFilesCard
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferAdvancedSettings
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferType
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeButtons
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getHumanReadableSize
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

private const val TOTAL_FILE_SIZE: Long = 50_000_000_000L

@Composable
fun ImportFilesScreen(
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
    closeActivity: () -> Unit,
) {
    val files by newTransferViewModel.importedFilesDebounced.collectAsStateWithLifecycle()
    val filesToImportCount by newTransferViewModel.filesToImportCount.collectAsStateWithLifecycle()
    val currentSessionFilesCount by newTransferViewModel.currentSessionFilesCount.collectAsStateWithLifecycle()
    val selectedTransferType by newTransferViewModel.selectedTransferType.collectAsStateWithLifecycle()

    ImportFilesScreen(
        files = { files },
        filesToImportCount = { filesToImportCount },
        currentSessionFilesCount = { currentSessionFilesCount },
        selectedTransferType = { selectedTransferType },
        onTransferTypeClicked = newTransferViewModel::selectTransferType,
        removeFileByUid = newTransferViewModel::removeFileByUid,
        addFiles = newTransferViewModel::importFiles,
        closeActivity = closeActivity,
        initialShowUploadSourceChoiceBottomSheet = true,
    )
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    selectedTransferType: () -> TransferType,
    onTransferTypeClicked: (TransferType) -> Unit,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
    initialShowUploadSourceChoiceBottomSheet: Boolean,
) {
    val context = LocalContext.current
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(initialShowUploadSourceChoiceBottomSheet) }

    val importedFiles = files()
    val humanReadableSize = remember(importedFiles) {
        val usedSpace = importedFiles.sumOf { it.fileSize }
        val spaceLeft = (TOTAL_FILE_SIZE - usedSpace).coerceAtLeast(0)
        getHumanReadableSize(context, spaceLeft)
    }

    val count = filesToImportCount()
    val isImporting by remember(count) { derivedStateOf { count > 0 } }

    val total = currentSessionFilesCount()
    val importProgress = remember(count, total) { 1 - (count.toFloat() / total) }

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
        topButton = { modifier -> SendButton({ isImporting }, { importProgress }, modifier) },
        content = {
            Column(
                modifier = Modifier
                    .padding(horizontal = Margin.Medium, vertical = Margin.Large)
                    .verticalScroll(rememberScrollState()),
            ) {
                ImportFilesTitle(titleRes = R.string.myFilesTitle)
                ImportedFilesCard(
                    modifier = Modifier.padding(vertical = Margin.Medium),
                    files = files,
                    humanReadableSize = { humanReadableSize },
                    showUploadSourceChoiceBottomSheet = { showUploadSourceChoiceBottomSheet = true },
                    removeFileByUid = removeFileByUid,
                )
                ImportTextFields(selectedTransferType)
                ImportFilesTitle(Modifier.padding(vertical = Margin.Medium), titleRes = R.string.transferTypeTitle)
                TransferTypeButtons(selectedTransferType, onTransferTypeClicked)
                ImportFilesTitle(Modifier.padding(vertical = Margin.Medium), titleRes = R.string.advancedSettingsTitle)
                TransferAdvancedSettings(
                    states = {
                        listOf(
                            ValidityPeriodOption.THIRTY,
                            DownloadLimitOption.ONE,
                            PasswordTransferOption.NONE,
                            EmailLanguageOption.GERMAN,
                        )
                    },
                    onClick = {},
                )
            }

            UploadSourceChoiceBottomSheet(
                isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
                onFilePickerClicked = { filePickerLauncher.launch(arrayOf("*/*")) },
                closeBottomSheet = { showUploadSourceChoiceBottomSheet = false },
            )
        }
    )
}

@Composable
private fun ImportTextFields(selectedTransferType: () -> TransferType) {

    val shouldShowEmailAddressesFields by remember { derivedStateOf { selectedTransferType() == TransferType.MAIL } }

    AnimatedVisibility(visible = shouldShowEmailAddressesFields) {
        Column {
            SwissTransferTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.transferSenderAddressPlaceholder),
            )
            Spacer(Modifier.size(Margin.Medium))
            SwissTransferTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.transferRecipientAddressPlaceholder),
            )
            Spacer(Modifier.size(Margin.Medium))
        }
    }

    SwissTransferTextField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(R.string.transferMessagePlaceholder),
        isRequired = false,
        minLineNumber = 3,
    )
}

@Composable
private fun SendButton(isImporting: () -> Boolean, importProgress: () -> Float, modifier: Modifier) {
    val progress: (() -> Float)? = if (isImporting()) importProgress else null

    LargeButton(
        modifier = modifier,
        titleRes = R.string.transferSendButton,
        style = ButtonType.PRIMARY,
        progress = progress,
        onClick = { /*TODO*/ },
    )
}

@Composable
private fun ImportFilesTitle(modifier: Modifier = Modifier, @StringRes titleRes: Int) {
    Text(
        modifier = modifier,
        style = SwissTransferTheme.typography.bodySmallRegular,
        text = stringResource(titleRes),
    )
}

enum class PasswordTransferOption(override val title: @Composable () -> String) : TransferAdvancedOptionsEnum {
    NONE({ stringResource(R.string.settingsOptionNone) }),
    ACTIVATED({ stringResource(R.string.settingsOptionActivated) }),
}

interface TransferAdvancedOptionsEnum {
    val title: @Composable () -> String
}

@PreviewAllWindows
@Composable
private fun ImportFilesScreenPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        ImportFilesScreen(
            files = { files },
            filesToImportCount = { 0 },
            currentSessionFilesCount = { 0 },
            selectedTransferType = { TransferType.QR_CODE },
            onTransferTypeClicked = {},
            removeFileByUid = {},
            addFiles = {},
            closeActivity = {},
            initialShowUploadSourceChoiceBottomSheet = false,
        )
    }
}
