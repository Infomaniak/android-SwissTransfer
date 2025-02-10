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

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSession
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.SendButtonStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.*
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.NotificationPermissionUtils
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.totalFileSize
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

private val HORIZONTAL_PADDING = Margin.Medium

/**
 * If the user kills the task while the upload is in progress, we lose the ImportFilesScreen and its information.
 * So, in every places we want to go back to ImportFilesScreen, instead we need to go back to the MainActivity.
 */
var areTransferDataStillAvailable = false
    private set

@Composable
fun AutoResetTransferDataAvailabilityStatus() {
    DisposableEffect(Unit) { onDispose { areTransferDataStillAvailable = false } }
}

@Composable
fun ImportFilesScreen(
    importFilesViewModel: ImportFilesViewModel,
    closeActivity: () -> Unit,
    navigateToUploadProgress: (transferType: TransferTypeUi, totalSize: Long, authorEmail: String?) -> Unit,
    navigateToFilesDetails: () -> Unit,
) {

    areTransferDataStillAvailable = true

    val files by importFilesViewModel.importedFilesDebounced.collectAsStateWithLifecycle()
    val sendButtonStatus by importFilesViewModel.sendButtonStatus.collectAsStateWithLifecycle()

    val selectedTransferType by importFilesViewModel.selectedTransferType.collectAsStateWithLifecycle()

    val validityPeriodState by importFilesViewModel.selectedValidityPeriodOption.collectAsStateWithLifecycle()
    val downloadLimitState by importFilesViewModel.selectedDownloadLimitOption.collectAsStateWithLifecycle()
    val passwordOptionState by importFilesViewModel.selectedPasswordOption.collectAsStateWithLifecycle()
    val emailLanguageState by importFilesViewModel.selectedLanguageOption.collectAsStateWithLifecycle()

    val lastUploadSession by importFilesViewModel.lastUploadSession.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = importFilesViewModel::importFiles,
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        importFilesViewModel.createNewUploadSession()
    }

    fun pickFiles() {
        filePickerLauncher.launch(arrayOf("*/*"))
    }

    val snackbarHostState = remember { SnackbarHostState() }

    val emailTextFieldCallbacks = importFilesViewModel.getEmailTextFieldCallbacks()

    BackHandler { closeActivity() }

    HandleStartupFilePick(importFilesViewModel.openFilePickerEvent, ::pickFiles)

    HandleNavigationToUploadInProgress(
        lastUploadSession = { lastUploadSession },
        navigateToUploadProgress = { totalSize ->
            navigateToUploadProgress(selectedTransferType, totalSize, lastUploadSession?.authorEmail)
        },
        resetSendButtonStatus = importFilesViewModel::resetSendButtonStatus
    )

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

                if (selectedTransferType == TransferTypeUi.Mail) {
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
        sendButtonStatus = { sendButtonStatus },
        emailTextFieldCallbacks = emailTextFieldCallbacks,
        transferMessageCallbacks = importFilesViewModel.transferMessageCallbacks,
        selectedTransferType = GetSetCallbacks(
            get = { selectedTransferType },
            set = importFilesViewModel::selectTransferType,
        ),
        transferOptionsCallbacks = transferOptionsCallbacks,
        pickFiles = ::pickFiles,
        closeActivity = closeActivity,
        onSendButtonClick = {
            importFilesViewModel.startLoadingSendButton()
            if (NotificationPermissionUtils.hasNotificationPermission(context)) {
                importFilesViewModel.createNewUploadSession()
            } else {
                NotificationPermissionUtils.askNotificationPermission(launcher)
            }
        },
        snackbarHostState = snackbarHostState,
        navigateToFilesDetails = navigateToFilesDetails,
    )
}

@Composable
private fun HandleStartupFilePick(openFilePickerEvent: ReceiveChannel<Unit>, pickFiles: () -> Unit) {
    LaunchedEffect(pickFiles) {
        openFilePickerEvent.consumeEach { pickFiles() }
    }
}

@Composable
fun HandleNavigationToUploadInProgress(
    lastUploadSession: () -> UploadSession?,
    navigateToUploadProgress: (Long) -> Unit,
    resetSendButtonStatus: () -> Unit,
) {
    LaunchedEffect(lastUploadSession()) {
        val uploadSession = lastUploadSession() ?: return@LaunchedEffect

        resetSendButtonStatus()
        navigateToUploadProgress(uploadSession.totalFileSize())
    }
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUi>,
    sendButtonStatus: () -> SendButtonStatus,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessageCallbacks: GetSetCallbacks<String>,
    selectedTransferType: GetSetCallbacks<TransferTypeUi>,
    transferOptionsCallbacks: TransferOptionsCallbacks,
    pickFiles: () -> Unit,
    closeActivity: () -> Unit,
    onSendButtonClick: () -> Unit,
    snackbarHostState: SnackbarHostState? = null,
    navigateToFilesDetails: () -> Unit,
) {

    val shouldShowEmailAddressesFields by remember { derivedStateOf { selectedTransferType.get() == TransferTypeUi.Mail } }
    val areEmailsCorrects by remember {
        derivedStateOf {
            with(emailTextFieldCallbacks) {
                val areAuthorAndRecipientsCorrects = transferAuthorEmail.get().isNotEmpty() && !isAuthorEmailInvalid()
                        && validatedRecipientsEmails.get().isNotEmpty()
                !shouldShowEmailAddressesFields || areAuthorAndRecipientsCorrects
            }
        }
    }

    BottomStickyButtonScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.importFilesScreenTitle,
                actions = { TopAppBarButtons.Close(onClick = { closeActivity() }) }
            )
        },
        topButton = { modifier ->
            SendButton(
                modifier = modifier,
                sendButtonStatus = sendButtonStatus,
                importedFiles = files,
                areEmailsCorrects = { areEmailsCorrects },
                onClick = onSendButtonClick,
            )
        },
        content = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING)
                SendByOptions(modifier, selectedTransferType)
                FilesToImport(modifier, files, navigateToFilesDetails, pickFiles)
                Spacer(Modifier.height(Margin.Medium))
                ImportTextFields(
                    horizontalPaddingModifier = modifier,
                    emailTextFieldCallbacks = emailTextFieldCallbacks,
                    transferMessageCallbacks = transferMessageCallbacks,
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
    navigateToFilesDetails: () -> Unit,
    pickFiles: () -> Unit,
) {
    ImportFilesTitle(modifier, R.string.myFilesTitle)
    ImportedFilesCard(
        modifier,
        files,
        pickFiles,
        navigateToFilesDetails,
    )
}

@Composable
private fun ColumnScope.ImportTextFields(
    horizontalPaddingModifier: Modifier,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessageCallbacks: GetSetCallbacks<String>,
    shouldShowEmailAddressesFields: () -> Boolean,
) {
    val modifier = horizontalPaddingModifier.fillMaxWidth()
    EmailAddressesTextFields(modifier, emailTextFieldCallbacks, shouldShowEmailAddressesFields)
    SwissTransferTextField(
        modifier = modifier,
        label = stringResource(R.string.transferMessagePlaceholder),
        isRequired = false,
        minLineNumber = 3,
        onValueChange = transferMessageCallbacks.set,
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
            val isAuthorError = checkEmailError(isAuthor = true)
            val isRecipientError = checkEmailError(isAuthor = false)

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
                validatedRecipientsEmails = validatedRecipientsEmails,
                onValueChange = { recipientEmail.set(it.text) },
                isError = isRecipientError,
                supportingText = getEmailError(isRecipientError),
            )
            Spacer(Modifier.height(Margin.Medium))
        }
    }
}

@Composable
private fun getEmailError(isError: Boolean): @Composable (() -> Unit)? {
    val supportingText: @Composable (() -> Unit)? = if (isError) {
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
    sendButtonStatus: () -> SendButtonStatus,
    importedFiles: () -> List<FileUi>,
    areEmailsCorrects: () -> Boolean,
    onClick: () -> Unit,
) {
    fun isImporting() = sendButtonStatus() is SendButtonStatus.Progress

    val status = sendButtonStatus()
    val progress: (() -> Float)? = if (status is SendButtonStatus.Progress) fun() = status.progress else null

    LargeButton(
        modifier = modifier,
        title = stringResource(R.string.transferSendButton),
        style = ButtonType.Primary,
        showIndeterminateProgress = { sendButtonStatus() == SendButtonStatus.Loading },
        enabled = { importedFiles().isNotEmpty() && !isImporting() && areEmailsCorrects() && sendButtonStatus().canEnableButton() },
        progress = progress,
        onClick = { if (sendButtonStatus() !is SendButtonStatus.Unclickable) onClick() },
    )
}

private fun SendButtonStatus.canEnableButton(): Boolean = when (this) {
    is SendButtonStatus.Unclickable,
    is SendButtonStatus.Clickable,
    is SendButtonStatus.Progress -> true
    SendButtonStatus.Loading -> false
}

enum class SendButtonStatus {
    Available, Loading
}

data class EmailTextFieldCallbacks(
    val transferAuthorEmail: GetSetCallbacks<String>,
    val isAuthorEmailInvalid: () -> Boolean,
    val recipientEmail: GetSetCallbacks<String>,
    val isRecipientEmailInvalid: () -> Boolean,
    val validatedRecipientsEmails: GetSetCallbacks<Set<String>>
) {

    fun checkEmailError(isAuthor: Boolean): Boolean {
        return if (isAuthor) {
            isAuthorEmailInvalid() && transferAuthorEmail.get().isNotEmpty()
        } else {
            isRecipientEmailInvalid() && recipientEmail.get().isNotEmpty()
        }
    }
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
            sendButtonStatus = { SendButtonStatus.Clickable },
            emailTextFieldCallbacks = emailTextFieldCallbacks,
            transferMessageCallbacks = GetSetCallbacks(get = { "" }, set = {}),
            selectedTransferType = GetSetCallbacks(get = { TransferTypeUi.Mail }, set = {}),
            transferOptionsCallbacks = transferOptionsCallbacks,
            pickFiles = {},
            closeActivity = {},
            onSendButtonClick = {},
            navigateToFilesDetails = {},
        )
    }
}
