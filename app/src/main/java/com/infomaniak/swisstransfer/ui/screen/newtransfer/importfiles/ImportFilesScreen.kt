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
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.SendActionResult
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.*
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun ImportFilesScreen(
    importFilesViewModel: ImportFilesViewModel = hiltViewModel<ImportFilesViewModel>(),
    closeActivity: () -> Unit,
    navigateToUploadProgress: (transferType: TransferTypeUi, totalSize: Long) -> Unit,
) {
    val files by importFilesViewModel.importedFilesDebounced.collectAsStateWithLifecycle()
    val filesToImportCount by importFilesViewModel.filesToImportCount.collectAsStateWithLifecycle()
    val currentSessionFilesCount by importFilesViewModel.currentSessionFilesCount.collectAsStateWithLifecycle()

    val selectedTransferType by importFilesViewModel.selectedTransferType.collectAsStateWithLifecycle()

    val validityPeriodState by importFilesViewModel.selectedValidityPeriodOption.collectAsStateWithLifecycle()
    val downloadLimitState by importFilesViewModel.selectedDownloadLimitOption.collectAsStateWithLifecycle()
    val passwordOptionState by importFilesViewModel.selectedPasswordOption.collectAsStateWithLifecycle()
    val emailLanguageState by importFilesViewModel.selectedLanguageOption.collectAsStateWithLifecycle()

    val sendActionResult by importFilesViewModel.sendActionResult.collectAsStateWithLifecycle()

    HandleSendActionResult({ sendActionResult }, { selectedTransferType }, navigateToUploadProgress)

    LaunchedEffect(Unit) { importFilesViewModel.initTransferOptionsValues() }

    val transferOptionsCallbacks = importFilesViewModel.getTransferOptionsCallbacks(
        transferOptionsStates = {
            listOf(
                TransferOptionState(
                    transferOptionType = TransferOptionType.VALIDITY_DURATION,
                    settingState = { validityPeriodState },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.DOWNLOAD_NUMBER_LIMIT,
                    settingState = { downloadLimitState },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.PASSWORD,
                    settingState = { passwordOptionState },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.LANGUAGE,
                    settingState = { emailLanguageState },
                ),
            )
        },
    )

    ImportFilesScreen(
        files = { files },
        filesToImportCount = { filesToImportCount },
        currentSessionFilesCount = { currentSessionFilesCount },
        transferMessage = importFilesViewModel.transferMessage,
        selectedTransferType = GetSetCallbacks(
            get = { selectedTransferType },
            set = importFilesViewModel::selectTransferType,
        ),
        transferOptionsCallbacks = transferOptionsCallbacks,
        removeFileByUid = importFilesViewModel::removeFileByUid,
        addFiles = importFilesViewModel::importFiles,
        closeActivity = closeActivity,
        sendTransfer = importFilesViewModel::sendTransfer,
        shouldStartByPromptingUserForFiles = true,
    )
}

@Composable
private fun HandleSendActionResult(
    getSendActionResult: () -> SendActionResult?,
    transferType: () -> TransferTypeUi,
    navigateToUploadProgress: (transferType: TransferTypeUi, totalSize: Long) -> Unit,
) {
    LaunchedEffect(getSendActionResult()) {
        when (val actionResult = getSendActionResult()) {
            is SendActionResult.Success -> navigateToUploadProgress(transferType(), actionResult.totalSize)
            is SendActionResult.Failure -> Unit // TODO: Show error
            else -> Unit
        }
    }
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    transferMessage: GetSetCallbacks<String>,
    selectedTransferType: GetSetCallbacks<TransferTypeUi>,
    transferOptionsCallbacks: TransferOptionsCallbacks,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
    shouldStartByPromptingUserForFiles: Boolean,
    sendTransfer: () -> Unit,
) {
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
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                FilesToImport(files, removeFileByUid, addFiles, shouldStartByPromptingUserForFiles)
                ImportTextFields(
                    modifier = Modifier
                        .padding(horizontal = Margin.Medium)
                        .fillMaxWidth(),
                    transferMessage = transferMessage,
                    selectedTransferType = selectedTransferType.get,
                )
                SendByOptions(selectedTransferType)
                TransferOptions(transferOptionsCallbacks)
            }
        }
    )
}

@Composable
private fun FilesToImport(
    files: () -> List<FileUi>,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    shouldStartByPromptingUserForFiles: Boolean,
) {
    var shouldShowInitialFilePick by rememberSaveable { mutableStateOf(shouldStartByPromptingUserForFiles) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = addFiles,
    )

    fun pickFiles() {
        shouldShowInitialFilePick = false
        filePickerLauncher.launch(arrayOf("*/*"))
    }

    LaunchedEffect(Unit) { if (shouldShowInitialFilePick) pickFiles() }

    ImportFilesTitle(modifier = Modifier.padding(horizontal = Margin.Medium), titleRes = R.string.myFilesTitle)
    ImportedFilesCard(
        modifier = Modifier.padding(Margin.Medium),
        files = files,
        pickFiles = ::pickFiles,
        removeFileByUid = removeFileByUid,
    )
}

@Composable
private fun ColumnScope.ImportTextFields(
    modifier: Modifier,
    transferMessage: GetSetCallbacks<String>,
    selectedTransferType: () -> TransferTypeUi,
) {
    EmailAddressesTextFields(modifier, selectedTransferType)
    SwissTransferTextField(
        modifier = modifier,
        label = stringResource(R.string.transferMessagePlaceholder),
        isRequired = false,
        minLineNumber = 3,
        onValueChange = transferMessage.set,
    )
}

@Composable
private fun ColumnScope.EmailAddressesTextFields(modifier: Modifier, selectedTransferType: () -> TransferTypeUi) {

    val shouldShowEmailAddressesFields by remember { derivedStateOf { selectedTransferType() == TransferTypeUi.MAIL } }

    AnimatedVisibility(visible = shouldShowEmailAddressesFields) {
        Column {
            SwissTransferTextField(
                modifier = modifier,
                label = stringResource(R.string.transferSenderAddressPlaceholder),
                onValueChange = { /* TODO */ },
            )
            Spacer(Modifier.height(Margin.Medium))
            SwissTransferTextField(
                modifier = modifier,
                label = stringResource(R.string.transferRecipientAddressPlaceholder),
                onValueChange = { /* TODO */ },
            )
            Spacer(Modifier.height(Margin.Medium))
        }
    }
}

@Composable
private fun SendByOptions(selectedTransferType: GetSetCallbacks<TransferTypeUi>) {
    ImportFilesTitle(Modifier.padding(Margin.Medium), titleRes = R.string.transferTypeTitle)
    TransferTypeButtons(selectedTransferType)
}

@Composable
private fun TransferOptions(transferOptionsCallbacks: TransferOptionsCallbacks) {

    var showTransferOption by rememberSaveable { mutableStateOf<TransferOptionType?>(null) }

    fun closeTransferOption() {
        showTransferOption = null
    }

    ImportFilesTitle(Modifier.padding(Margin.Medium), titleRes = R.string.advancedSettingsTitle)
    TransferOptionsTypes(
        modifier = Modifier.padding(horizontal = Margin.Medium),
        transferOptionsStates = transferOptionsCallbacks.transferOptionsStates,
        onClick = { selectedOptionType -> showTransferOption = selectedOptionType },
    )
    TransferOptionsDialogs({ showTransferOption }, transferOptionsCallbacks, ::closeTransferOption)
}

@Composable
private fun TransferOptionsDialogs(
    selectedOptionType: () -> TransferOptionType?,
    transferOptionsCallbacks: TransferOptionsCallbacks,
    closeTransferOption: () -> Unit,
) {
    when (selectedOptionType()) {
        TransferOptionType.VALIDITY_DURATION -> ValidityPeriodBottomSheet(
            onOptionClicked = { transferOptionsCallbacks.onTransferOptionValueSelected(it) },
            closeBottomSheet = closeTransferOption,
            initialValue = transferOptionsCallbacks.transferOptionsStates()[0].settingState(),
        )
        TransferOptionType.DOWNLOAD_NUMBER_LIMIT -> DownloadLimitBottomSheet(
            onOptionClicked = { transferOptionsCallbacks.onTransferOptionValueSelected(it) },
            closeBottomSheet = closeTransferOption,
            initialValue = transferOptionsCallbacks.transferOptionsStates()[1].settingState(),
        )
        TransferOptionType.PASSWORD -> PasswordOptionAlertDialog(
            password = transferOptionsCallbacks.password,
            onConfirmation = { passwordOption ->
                transferOptionsCallbacks.onTransferOptionValueSelected(passwordOption)
                closeTransferOption()
            },
            closeAlertDialog = closeTransferOption,
            isPasswordValid = transferOptionsCallbacks.isPasswordValid,
        )
        TransferOptionType.LANGUAGE -> EmailLanguageBottomSheet(
            onOptionClicked = { transferOptionsCallbacks.onTransferOptionValueSelected(it) },
            closeBottomSheet = closeTransferOption,
            initialValue = transferOptionsCallbacks.transferOptionsStates()[3].settingState(),
        )
        null -> Unit
    }
}

@Composable
private fun ImportFilesTitle(modifier: Modifier = Modifier, @StringRes titleRes: Int) {
    Text(
        modifier = modifier,
        style = SwissTransferTheme.typography.bodySmallRegular,
        text = stringResource(titleRes),
    )
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

data class TransferOptionsCallbacks(
    val transferOptionsStates: () -> List<TransferOptionState>,
    val onTransferOptionValueSelected: (SettingOption) -> Unit,
    val password: GetSetCallbacks<String>,
    val isPasswordValid: () -> Boolean,
)

data class TransferOptionState(
    val transferOptionType: TransferOptionType,
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
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    val transferOptionsCallbacks = TransferOptionsCallbacks(
        transferOptionsStates = {
            listOf(
                TransferOptionState(
                    transferOptionType = TransferOptionType.VALIDITY_DURATION,
                    settingState = { ValidityPeriodOption.THIRTY },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.DOWNLOAD_NUMBER_LIMIT,
                    settingState = { DownloadLimitOption.TWO_HUNDRED_FIFTY },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.PASSWORD,
                    settingState = { PasswordTransferOption.NONE },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.LANGUAGE,
                    settingState = { EmailLanguageOption.FRENCH },
                ),
            )
        },
        onTransferOptionValueSelected = {},
        password = GetSetCallbacks(get = { "password" }, set = {}),
        isPasswordValid = { true },
    )

    SwissTransferTheme {
        ImportFilesScreen(
            files = { files },
            filesToImportCount = { 0 },
            currentSessionFilesCount = { 0 },
            transferMessage = GetSetCallbacks(get = { "" }, set = {}),
            selectedTransferType = GetSetCallbacks(get = { TransferTypeUi.QR_CODE }, set = {}),
            transferOptionsCallbacks = transferOptionsCallbacks,
            removeFileByUid = {},
            addFiles = {},
            closeActivity = {},
            shouldStartByPromptingUserForFiles = false,
            sendTransfer = {},
        )
    }
}
