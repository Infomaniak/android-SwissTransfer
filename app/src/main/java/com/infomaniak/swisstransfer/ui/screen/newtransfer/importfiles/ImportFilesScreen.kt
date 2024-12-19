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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.AppIntegrityResult
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel.SendActionResult
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.*
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

private val HORIZONTAL_PADDING = Margin.Medium

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
    val integrityCheckResult by importFilesViewModel.integrityCheckResult.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    HandleIntegrityCheckResult(
        snackbarHostState = snackbarHostState,
        integrityCheckResult = { integrityCheckResult },
        resetResult = { importFilesViewModel.resetIntegrityCheckResult() },
    )

    HandleSendActionResult(
        snackbarHostState = snackbarHostState,
        getSendActionResult = { sendActionResult },
        transferType = { selectedTransferType },
        navigateToUploadProgress = navigateToUploadProgress,
        resetSendActionResult = importFilesViewModel::resetSendActionResult,
    )


    LaunchedEffect(Unit) { importFilesViewModel.initTransferOptionsValues() }

    val transferOptionsCallbacks = importFilesViewModel.getTransferOptionsCallbacks(
        transferOptionsStates = {
            buildList {
                add(
                    TransferOptionState(
                        transferOptionType = TransferOptionType.VALIDITY_DURATION,
                        settingState = { validityPeriodState },
                    )
                )
                add(
                    TransferOptionState(
                        transferOptionType = TransferOptionType.DOWNLOAD_NUMBER_LIMIT,
                        settingState = { downloadLimitState },
                    )
                )
                add(
                    TransferOptionState(
                        transferOptionType = TransferOptionType.PASSWORD,
                        settingState = { passwordOptionState },
                    )
                )

                if (selectedTransferType == TransferTypeUi.MAIL) {
                    add(
                        TransferOptionState(
                            transferOptionType = TransferOptionType.LANGUAGE,
                            settingState = { emailLanguageState },
                        )
                    )
                }
            }
        },
    )

    ImportFilesScreen(
        files = { files },
        filesToImportCount = { filesToImportCount },
        currentSessionFilesCount = { currentSessionFilesCount },
        emailTextFieldCallbacks = importFilesViewModel.getEmailTextFieldCallbacks(),
        transferMessage = importFilesViewModel.transferMessage,
        selectedTransferType = GetSetCallbacks(
            get = { selectedTransferType },
            set = importFilesViewModel::selectTransferType,
        ),
        transferOptionsCallbacks = transferOptionsCallbacks,
        addFiles = importFilesViewModel::importFiles,
        closeActivity = closeActivity,
        integrityCheckResult = { integrityCheckResult },
        sendTransfer = importFilesViewModel::sendTransfer,
        shouldStartByPromptingUserForFiles = true,
        isTransferStarted = { sendActionResult != SendActionResult.NotStarted },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun HandleSendActionResult(
    snackbarHostState: SnackbarHostState,
    getSendActionResult: () -> SendActionResult?,
    transferType: () -> TransferTypeUi,
    navigateToUploadProgress: (transferType: TransferTypeUi, totalSize: Long) -> Unit,
    resetSendActionResult: () -> Unit,
) {
    val errorMessage = stringResource(R.string.errorUnknown)
    LaunchedEffect(getSendActionResult()) {
        when (val actionResult = getSendActionResult()) {
            is SendActionResult.Success -> navigateToUploadProgress(transferType(), actionResult.totalSize)
            is SendActionResult.Failure -> {
                snackbarHostState.showSnackbar(errorMessage)
                resetSendActionResult()
            }
            else -> Unit
        }
    }
}

@Composable
private fun HandleIntegrityCheckResult(
    snackbarHostState: SnackbarHostState,
    integrityCheckResult: () -> AppIntegrityResult,
    resetResult: () -> Unit,
) {
    val result = integrityCheckResult()
    val errorMessage = stringResource(R.string.errorAppIntegrity)

    LaunchedEffect(result == AppIntegrityResult.Success || result == AppIntegrityResult.Fail) {
        if (integrityCheckResult() == AppIntegrityResult.Fail) { // TODO: Better error management
            snackbarHostState.showSnackbar(errorMessage)
        }
        resetResult()
    }
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessage: GetSetCallbacks<String>,
    selectedTransferType: GetSetCallbacks<TransferTypeUi>,
    transferOptionsCallbacks: TransferOptionsCallbacks,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
    shouldStartByPromptingUserForFiles: Boolean,
    integrityCheckResult: () -> AppIntegrityResult,
    sendTransfer: () -> Unit,
    isTransferStarted: () -> Boolean,
    snackbarHostState: SnackbarHostState? = null,
) {

    val shouldShowEmailAddressesFields by remember { derivedStateOf { selectedTransferType.get() == TransferTypeUi.MAIL } }

    BottomStickyButtonScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.importFilesScreenTitle,
                navigationMenu = null,
                TopAppBarButton.closeButton { closeActivity() },
            )
        },
        topButton = { modifier ->
            SendButton(
                modifier = modifier,
                filesToImportCount = filesToImportCount,
                currentSessionFilesCount = currentSessionFilesCount,
                importedFiles = files,
                shouldShowEmailAddressesFields = { shouldShowEmailAddressesFields },
                isAuthorEmailInvalid = emailTextFieldCallbacks.isAuthorEmailInvalid,
                integrityCheckResult = integrityCheckResult,
                sendTransfer = sendTransfer,
                isTransferStarted = isTransferStarted,
            )
        },
        content = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING)
                SendByOptions(modifier, selectedTransferType)
                FilesToImport(modifier, files, navigateToFileDetails = { /*TODO*/ }, addFiles, shouldStartByPromptingUserForFiles)
                Spacer(Modifier.height(Margin.Medium))
                ImportTextFields(
                    horizontalPaddingModifier = modifier,
                    emailTextFieldCallbacks = emailTextFieldCallbacks,
                    transferMessage = transferMessage,
                    shouldShowEmailAddressesFields = { shouldShowEmailAddressesFields },
                )
                TransferOptions(modifier, transferOptionsCallbacks)
            }
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun FilesToImport(
    modifier: Modifier,
    files: () -> List<FileUi>,
    navigateToFileDetails: () -> Unit,
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

    ImportFilesTitle(modifier, R.string.myFilesTitle)
    ImportedFilesCard(modifier, files, ::pickFiles, navigateToFileDetails)
}

@Composable
private fun ColumnScope.ImportTextFields(
    horizontalPaddingModifier: Modifier,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessage: GetSetCallbacks<String>,
    shouldShowEmailAddressesFields: () -> Boolean,
) {
    val modifier = horizontalPaddingModifier.fillMaxWidth()
    EmailAddressesTextFields(modifier, emailTextFieldCallbacks, shouldShowEmailAddressesFields)
    SwissTransferTextField(
        modifier = modifier,
        label = stringResource(R.string.transferMessagePlaceholder),
        isRequired = false,
        minLineNumber = 3,
        onValueChange = transferMessage.set,
    )
}

@Composable
private fun ColumnScope.EmailAddressesTextFields(
    modifier: Modifier,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    shouldShowEmailAddressesFields: () -> Boolean,
) = with(emailTextFieldCallbacks) {
    AnimatedVisibility(visible = shouldShowEmailAddressesFields()) {
        Column {
            val isAuthorError = isAuthorEmailInvalid()
            val isRecipientError = isRecipientEmailInvalid()

            SwissTransferTextField(
                modifier = modifier,
                label = stringResource(R.string.transferSenderAddressPlaceholder),
                initialValue = transferAuthorEmail.get(),
                keyboardType = KeyboardType.Email,
                maxLineNumber = 1,
                imeAction = ImeAction.Next,
                isError = isAuthorError,
                supportingText = getEmailError(isAuthorError),
                onValueChange = transferAuthorEmail.set,
            )
            Spacer(Modifier.height(Margin.Medium))
            EmailAddressTextField(
                modifier = modifier,
                label = stringResource(R.string.transferRecipientAddressPlaceholder),
                initialValue = recipientEmail.get(),
                validatedEmails = validatedRecipientsEmails,
                onValueChange = { recipientEmail.set(it.text) },
                isError = isRecipientError,
                supportingText = getEmailError(isRecipientError),
            )
            Spacer(Modifier.height(Margin.Medium))
        }
    }
}

@Composable
private fun getEmailError(isAuthorError: Boolean): @Composable (() -> Unit)? {
    val supportingText: @Composable (() -> Unit)? = if (isAuthorError) {
        { Text(stringResource(R.string.invalidAddress)) }
    } else {
        null
    }

    return supportingText
}

@Composable
private fun SendByOptions(modifier: Modifier, selectedTransferType: GetSetCallbacks<TransferTypeUi>) {
    ImportFilesTitle(modifier, R.string.transferTypeTitle)
    TransferTypeButtons(HORIZONTAL_PADDING, selectedTransferType)
}

@Composable
private fun TransferOptions(modifier: Modifier, transferOptionsCallbacks: TransferOptionsCallbacks) {

    var showTransferOption by rememberSaveable { mutableStateOf<TransferOptionType?>(null) }

    fun closeTransferOption() {
        showTransferOption = null
    }

    ImportFilesTitle(modifier, R.string.advancedSettingsTitle)
    TransferOptionsTypes(
        modifier = modifier,
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
        modifier = modifier.padding(vertical = Margin.Medium),
        style = SwissTransferTheme.typography.bodySmallRegular,
        text = stringResource(titleRes),
    )
}

@Composable
private fun SendButton(
    modifier: Modifier,
    filesToImportCount: () -> Int,
    currentSessionFilesCount: () -> Int,
    importedFiles: () -> List<FileUi>,
    shouldShowEmailAddressesFields: () -> Boolean,
    isAuthorEmailInvalid: () -> Boolean,
    integrityCheckResult: () -> AppIntegrityResult,
    sendTransfer: () -> Unit,
    isTransferStarted: () -> Boolean,
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

    val isSenderEmailCorrect by remember {
        derivedStateOf { !shouldShowEmailAddressesFields() || !isAuthorEmailInvalid() }
    }

    LargeButton(
        modifier = modifier,
        title = stringResource(R.string.transferSendButton),
        style = ButtonType.PRIMARY,
        showIndeterminateProgress = { integrityCheckResult() == AppIntegrityResult.Ongoing || isTransferStarted() },
        enabled = { importedFiles().isNotEmpty() && !isImporting && isSenderEmailCorrect && !isTransferStarted() },
        progress = progress,
        onClick = { sendTransfer() },
    )
}

data class EmailTextFieldCallbacks(
    val transferAuthorEmail: GetSetCallbacks<String>,
    val isAuthorEmailInvalid: () -> Boolean,
    val recipientEmail: GetSetCallbacks<String>,
    val isRecipientEmailInvalid: () -> Boolean,
    val validatedRecipientsEmails: GetSetCallbacks<Set<String>>,
)

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

    val emailTextFieldCallbacks = EmailTextFieldCallbacks(
        transferAuthorEmail = GetSetCallbacks(get = { "" }, set = {}),
        isAuthorEmailInvalid = { false },
        recipientEmail = GetSetCallbacks(get = { "test.test@ik me" }, set = {}),
        isRecipientEmailInvalid = { true },
        validatedRecipientsEmails = GetSetCallbacks(get = { setOf("test.test@ik.me") }, set = {}),
    )

    SwissTransferTheme {
        ImportFilesScreen(
            files = { files },
            filesToImportCount = { 0 },
            currentSessionFilesCount = { 0 },
            emailTextFieldCallbacks = emailTextFieldCallbacks,
            transferMessage = GetSetCallbacks(get = { "" }, set = {}),
            selectedTransferType = GetSetCallbacks(get = { TransferTypeUi.MAIL }, set = {}),
            transferOptionsCallbacks = transferOptionsCallbacks,
            addFiles = {},
            closeActivity = {},
            shouldStartByPromptingUserForFiles = false,
            integrityCheckResult = { AppIntegrityResult.Idle },
            sendTransfer = {},
            isTransferStarted = { false },
        )
    }
}
