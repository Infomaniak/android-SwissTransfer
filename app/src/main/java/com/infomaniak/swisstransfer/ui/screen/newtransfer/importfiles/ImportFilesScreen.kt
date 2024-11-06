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
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsViewModel
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.SendActionResult
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.*
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils.getHumanReadableSize
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

private const val TOTAL_FILE_SIZE: Long = 50_000_000_000L

@Composable
fun ImportFilesScreen(
    importFilesViewModel: ImportFilesViewModel = hiltViewModel<ImportFilesViewModel>(),
    settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
    closeActivity: () -> Unit,
    navigateToUploadProgress: (transferType: TransferType, totalSize: Long) -> Unit,
) {
    val files by importFilesViewModel.importedFilesDebounced.collectAsStateWithLifecycle()
    val filesToImportCount by importFilesViewModel.filesToImportCount.collectAsStateWithLifecycle()
    val currentSessionFilesCount by importFilesViewModel.currentSessionFilesCount.collectAsStateWithLifecycle()

    val selectedTransferType by importFilesViewModel.selectedTransferType.collectAsStateWithLifecycle()

    val validityPeriodState by importFilesViewModel.selectedValidityPeriodOption.collectAsStateWithLifecycle()
    val downloadLimitState by importFilesViewModel.selectedDownloadLimitOption.collectAsStateWithLifecycle()
    val passwordOptionState by importFilesViewModel.selectedPasswordOption.collectAsStateWithLifecycle()
    val emailLanguageState by importFilesViewModel.selectedLanguageOption.collectAsStateWithLifecycle()

    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)
    val sendActionResult by importFilesViewModel.sendActionResult.collectAsStateWithLifecycle()

    HandleSendActionResult({ sendActionResult }, { selectedTransferType }, navigateToUploadProgress)

    val advancedOptionsCallbacks = importFilesViewModel.getTransferAdvancedOptionCallbacks(
        appSettings, { validityPeriodState }, { downloadLimitState }, { passwordOptionState }, { emailLanguageState },
    )

    if (advancedOptionsCallbacks == null) return

    ImportFilesScreen(
        files = { files },
        filesToImportCount = { filesToImportCount },
        currentSessionFilesCount = { currentSessionFilesCount },
        selectedTransferType = GetSetCallbacks(
            get = { selectedTransferType },
            set = importFilesViewModel::selectTransferType,
        ),
        advancedOptionsCallbacks = advancedOptionsCallbacks,
        removeFileByUid = importFilesViewModel::removeFileByUid,
        addFiles = importFilesViewModel::importFiles,
        closeActivity = closeActivity,
        sendTransfer = importFilesViewModel::sendTransfer,
        initialShowUploadSourceChoiceBottomSheet = true,
    )
}

@Composable
private fun HandleSendActionResult(
    getSendActionResult: () -> SendActionResult?,
    transferType: () -> TransferType,
    navigateToUploadProgress: (transferType: TransferType, totalSize: Long) -> Unit,
) {
    LaunchedEffect(getSendActionResult()) {
        when (val actionResult = getSendActionResult()) {
            is SendActionResult.Success -> navigateToUploadProgress(transferType(), actionResult.totalSize)
            is SendActionResult.Failure -> Unit //TODO: Show error
            else -> Unit
        }
    }
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    selectedTransferType: GetSetCallbacks<TransferType>,
    advancedOptionsCallbacks: AdvancedOptionsCallbacks,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
    initialShowUploadSourceChoiceBottomSheet: Boolean,
    sendTransfer: () -> Unit,
) {
    val context = LocalContext.current
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(initialShowUploadSourceChoiceBottomSheet) }
    var showAdvancedOption by rememberSaveable { mutableStateOf<TransferAdvancedSettingType?>(null) }

    val importedFiles = files()
    val humanReadableSize = remember(importedFiles) {
        val usedSpace = importedFiles.sumOf { it.fileSize }
        val spaceLeft = (TOTAL_FILE_SIZE - usedSpace).coerceAtLeast(0)
        getHumanReadableSize(context, spaceLeft)
    }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris: List<Uri> ->
        addFiles(uris)
    }

    fun closeAdvancedOption() {
        showAdvancedOption = null
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
            SendButton(filesToImportCount, currentSessionFilesCount, files, modifier, sendTransfer)
        },
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
                ImportTextFields(selectedTransferType.get)
                ImportFilesTitle(Modifier.padding(vertical = Margin.Medium), titleRes = R.string.transferTypeTitle)
                TransferTypeButtons(selectedTransferType)
                ImportFilesTitle(Modifier.padding(vertical = Margin.Medium), titleRes = R.string.advancedSettingsTitle)
                TransferAdvancedSettings(
                    advancedSettingsItemsStates = advancedOptionsCallbacks.advancedOptionsStates,
                    onClick = { selectedOption -> showAdvancedOption = selectedOption },
                )
            }

            ValidityPeriodBottomSheet(
                isBottomSheetVisible = { showAdvancedOption == TransferAdvancedSettingType.VALIDITY_DURATION },
                onOptionClicked = { advancedOptionsCallbacks.onAdvancedOptionsValueSelected(it) },
                closeBottomSheet = ::closeAdvancedOption,
                initialValue = advancedOptionsCallbacks.advancedOptionsStates()[0].settingState(),
            )

            DownloadLimitBottomSheet(
                isBottomSheetVisible = { showAdvancedOption == TransferAdvancedSettingType.DOWNLOAD_NUMBER_LIMIT },
                onOptionClicked = { advancedOptionsCallbacks.onAdvancedOptionsValueSelected(it) },
                closeBottomSheet = ::closeAdvancedOption,
                initialValue = advancedOptionsCallbacks.advancedOptionsStates()[1].settingState(),
            )

            PasswordOptionAlertDialog(
                password = advancedOptionsCallbacks.password,
                showPasswordOptionAlert = { showAdvancedOption == TransferAdvancedSettingType.PASSWORD },
                onConfirmation = { passwordOption ->
                    advancedOptionsCallbacks.onAdvancedOptionsValueSelected(passwordOption)
                    closeAdvancedOption()
                },
                onDismiss = ::closeAdvancedOption,
                isPasswordValid = advancedOptionsCallbacks.isPasswordValid,
            )

            EmailLanguageBottomSheet(
                isBottomSheetVisible = { showAdvancedOption == TransferAdvancedSettingType.LANGUAGE },
                onOptionClicked = { advancedOptionsCallbacks.onAdvancedOptionsValueSelected(it) },
                closeBottomSheet = ::closeAdvancedOption,
                initialValue = advancedOptionsCallbacks.advancedOptionsStates()[3].settingState(),
            )

            UploadSourceChoiceBottomSheet(
                isVisible = { showUploadSourceChoiceBottomSheet },
                onFilePickerClicked = { filePickerLauncher.launch(arrayOf("*/*")) },
                closeBottomSheet = { showUploadSourceChoiceBottomSheet = false },
            )
        }
    )
}

@Composable
private fun ColumnScope.ImportTextFields(selectedTransferType: () -> TransferType) {

    EmailAddressesTextFields(selectedTransferType)

    SwissTransferTextField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(R.string.transferMessagePlaceholder),
        isRequired = false,
        minLineNumber = 3,
    )
}

@Composable
private fun ColumnScope.EmailAddressesTextFields(selectedTransferType: () -> TransferType) {

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
}

@Composable
private fun SendButton(
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    importedFiles: () -> List<FileUi>,
    modifier: Modifier,
    navigateToUploadProgress: () -> Unit,
) {
    val remainingFilesCount = filesToImportCount()
    val isImporting by remember(remainingFilesCount) { derivedStateOf { remainingFilesCount > 0 } }

    val total = currentSessionFilesCount()
    val importProgress = remember(remainingFilesCount, total) { 1 - (remainingFilesCount.toFloat() / total) }

    val progress: (() -> Float)? = if (isImporting) {
        { importProgress }
    } else {
        null
    }

    LargeButton(
        modifier = modifier,
        titleRes = R.string.transferSendButton,
        style = ButtonType.PRIMARY,
        enabled = { importedFiles().isNotEmpty() && !isImporting },
        progress = progress,
        onClick = navigateToUploadProgress,
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

data class AdvancedOptionsCallbacks(
    val advancedOptionsStates: () -> List<AdvancedOptionsState>,
    val onAdvancedOptionsValueSelected: (SettingOption) -> Unit,
    val password: GetSetCallbacks<String>,
    val isPasswordValid: () -> Boolean,
)

data class AdvancedOptionsState(
    val advancedSettingType: TransferAdvancedSettingType,
    val settingState: () -> SettingOption?,
)

enum class PasswordTransferOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector? = null,
    override val imageVectorResId: Int? = null,
) : SettingOption {
    NONE({ stringResource(R.string.settingsOptionNone) }),
    ACTIVATED({ stringResource(R.string.settingsOptionActivated) }),
}

@PreviewAllWindows
@Composable
private fun ImportFilesScreenPreview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    val advancedOptionsCallbacks = AdvancedOptionsCallbacks(
        advancedOptionsStates = {
            listOf(
                AdvancedOptionsState(
                    advancedSettingType = TransferAdvancedSettingType.VALIDITY_DURATION,
                    settingState = { ValidityPeriodOption.THIRTY },
                ),
                AdvancedOptionsState(
                    advancedSettingType = TransferAdvancedSettingType.DOWNLOAD_NUMBER_LIMIT,
                    settingState = { DownloadLimitOption.TWO_HUNDRED_FIFTY },
                ),
                AdvancedOptionsState(
                    advancedSettingType = TransferAdvancedSettingType.PASSWORD,
                    settingState = { PasswordTransferOption.NONE },
                ),
                AdvancedOptionsState(
                    advancedSettingType = TransferAdvancedSettingType.LANGUAGE,
                    settingState = { EmailLanguageOption.FRENCH },
                ),
            )
        },
        onAdvancedOptionsValueSelected = {},
        password = GetSetCallbacks(get = { "password" }, set = {}),
        isPasswordValid = { true },
    )

    SwissTransferTheme {
        ImportFilesScreen(
            files = { files },
            filesToImportCount = { 0 },
            currentSessionFilesCount = { 0 },
            selectedTransferType = GetSetCallbacks(get = { TransferType.QR_CODE }, set = {}),
            advancedOptionsCallbacks = advancedOptionsCallbacks,
            removeFileByUid = {},
            addFiles = {},
            closeActivity = {},
            initialShowUploadSourceChoiceBottomSheet = false,
            sendTransfer = {},
        )
    }
}
