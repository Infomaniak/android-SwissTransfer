/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.provider.ContactsContract
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.common.mapSync
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.BottomStickyButtonScaffold
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.core.ui.compose.preview.previewparameter.UserListPreviewParameterProvider
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.FabType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferFab
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextField
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.EyeCrossed
import com.infomaniak.swisstransfer.ui.images.icons.Person
import com.infomaniak.swisstransfer.ui.images.icons.PersonsCircleAdd
import com.infomaniak.swisstransfer.ui.previewparameter.filesPreviewData
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.CanSendStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.EmailAddressTextField
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.ImportedFilesCard
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.PasswordOptionAlertDialog
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferOptionType
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferOptionsTypes
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeButtons
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.isApiV2
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import splitties.toast.longToast

private val HORIZONTAL_PADDING = Margin.Medium

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PickFilesScreen(
    pickFilesViewModel: PickFilesViewModel,
    exitNewTransfer: () -> Unit,
    navigateToUploadProgress: () -> Unit,
    navigateToFilesDetails: () -> Unit,
) {

    val files by pickFilesViewModel.importedFilesDebounced.collectAsStateWithLifecycle()
    val canSendStatus: CanSendStatus by pickFilesViewModel.canSendStatusFlow.collectAsState()

    val selectedTransferType: TransferTypeUi by pickFilesViewModel.selectedTransferTypeFlow.collectAsStateWithLifecycle()

    val validityPeriod: ValidityPeriodOption by pickFilesViewModel.selectedValidityPeriodOption.collectAsStateWithLifecycle()
    val downloadLimit: DownloadLimitOption by pickFilesViewModel.selectedDownloadLimitOption.collectAsStateWithLifecycle()
    val passwordOption: PasswordTransferOption by pickFilesViewModel.selectedPasswordOption.collectAsStateWithLifecycle()
    val emailLanguage: EmailLanguageOption by pickFilesViewModel.selectedLanguageOption.collectAsStateWithLifecycle()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = pickFilesViewModel::importUris,
    )

    val notificationPermissionState: PermissionState? = if (SDK_INT >= 33) {
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else null

    fun pickFiles() {
        try {
            filePickerLauncher.launch(arrayOf("*/*"))
        } catch (e: ActivityNotFoundException) {
            longToast(R.string.startActivityCantHandleAction)
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    val emailTextFieldCallbacks = pickFilesViewModel.getEmailTextFieldCallbacks()

    fun exit() {
        exitNewTransfer()
        UploadForegroundService.removeAllFiles()
    }

    BackHandler { exit() }

    HandleStartupFilePick(pickFilesViewModel.openFilePickerEvent, ::pickFiles)

    LaunchedEffect(Unit) {
        MatomoSwissTransfer.trackScreen(MatomoScreen.NewTransfer)
        UploadForegroundService.uploadStateFlow.mapSync { it != null }.collect { isUploadOngoing ->
            if (isUploadOngoing) {
                navigateToUploadProgress()
            }
        }
    }

    val transferOptionsCallbacks: TransferOptionsCallbacks = pickFilesViewModel.getTransferOptionsCallbacks(
        transferOptionsStates = {
            buildList {
                this += TransferOptionState(
                    transferOptionType = TransferOptionType.VALIDITY_DURATION,
                    settingState = { validityPeriod },
                )
                this += TransferOptionState(
                    transferOptionType = TransferOptionType.DOWNLOAD_NUMBER_LIMIT,
                    settingState = { downloadLimit },
                )
                this += TransferOptionState(
                    transferOptionType = TransferOptionType.PASSWORD,
                    settingState = { passwordOption },
                )

                if (selectedTransferType == TransferTypeUi.Mail) {
                    this += TransferOptionState(
                        transferOptionType = TransferOptionType.LANGUAGE,
                        settingState = { emailLanguage },
                    )
                }
            }
        },
    )

    PickFilesScreen(
        files = { files },
        canSendStatus = { canSendStatus },
        transferTitleState = pickFilesViewModel.transferTitleState,
        emailTextFieldCallbacks = emailTextFieldCallbacks,
        transferMessageCallbacks = pickFilesViewModel.transferMessageCallbacks,
        selectedTransferType = GetSetCallbacks(
            get = { selectedTransferType },
            set = { type ->
                MatomoSwissTransfer.trackTransferTypeEvent(type.dbValue.matomoName)
                pickFilesViewModel.selectTransferType(type)
            },
        ),
        transferOptionsCallbacks = transferOptionsCallbacks,
        pickFiles = ::pickFiles,
        selectContact = pickFilesViewModel::processContactPickerResultUri,
        exitNewTransfer = { exit() },
        onSendButtonClick = {
            MatomoSwissTransfer.trackNewTransferDataEvent(pickFilesViewModel.selectedTransferTypeFlow.value.dbValue.matomoName)
            notificationPermissionState?.launchPermissionRequest()
            // Notification permission is optional, so we don’t wait for the result
            pickFilesViewModel.send()
        },
        isAwaitingSend = { pickFilesViewModel.isReadyToSend() },
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
private fun PickFilesScreen(
    files: () -> List<FileUi>,
    canSendStatus: () -> CanSendStatus,
    transferTitleState: MutableState<String>,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessageCallbacks: GetSetCallbacks<String>,
    selectedTransferType: GetSetCallbacks<TransferTypeUi>,
    transferOptionsCallbacks: TransferOptionsCallbacks,
    pickFiles: () -> Unit,
    selectContact: (Uri, Context) -> Unit,
    exitNewTransfer: () -> Unit,
    isAwaitingSend: () -> Boolean,
    onSendButtonClick: () -> Unit,
    snackbarHostState: SnackbarHostState? = null,
    navigateToFilesDetails: () -> Unit,
) {

    val shouldShowEmailAddressesFields by remember { derivedStateOf { selectedTransferType.get() == TransferTypeUi.Mail } }

    BottomStickyButtonScaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.importFilesScreenTitle,
                actions = { TopAppBarButtons.Close(onClick = { exitNewTransfer() }) }
            )
        },
        topButton = { modifier ->
            SendButton(
                modifier = modifier,
                canSendStatus = canSendStatus,
                expectsClick = isAwaitingSend,
                onClick = onSendButtonClick,
            )
        },
        content = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING)
                SendByOptions(modifier, selectedTransferType)
                FilesToImport(modifier, files, canSendStatus, navigateToFilesDetails, pickFiles)
                Spacer(Modifier.height(Margin.Medium))
                ImportTextFields(
                    horizontalPaddingModifier = modifier,
                    transferTitleState = transferTitleState,
                    emailTextFieldCallbacks = emailTextFieldCallbacks,
                    transferMessageCallbacks = transferMessageCallbacks,
                    shouldShowEmailAddressesFields = { shouldShowEmailAddressesFields },
                    selectContact = selectContact,
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
    canSendStatus: () -> CanSendStatus,
    navigateToFilesDetails: () -> Unit,
    pickFiles: () -> Unit,
) {
    PickFilesTitle(modifier, R.string.myFilesTitle)
    ImportedFilesCard(
        modifier,
        files,
        canSendStatus,
        pickFiles,
        navigateToFilesDetails,
    )
}

@Composable
private fun ImportTextFields(
    horizontalPaddingModifier: Modifier,
    transferTitleState: MutableState<String>,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    transferMessageCallbacks: GetSetCallbacks<String>,
    shouldShowEmailAddressesFields: () -> Boolean,
    selectContact: (Uri, Context) -> Unit,
) {
    val modifier = horizontalPaddingModifier.fillMaxWidth()

    val textFieldSpacing = Margin.Medium
    Column(verticalArrangement = Arrangement.spacedBy(textFieldSpacing)) {
        if (LocalUser.current.isApiV2()) {
            SwissTransferTextField(
                modifier = modifier,
                label = stringResource(R.string.transferTitlePlaceholder),
                isRequired = false,
                maxLineNumber = 1,
                onValueChange = { transferTitleState.value = it },
            )
        }

        EmailAddressesTextFields(
            modifier, emailTextFieldCallbacks, shouldShowEmailAddressesFields, textFieldSpacing, selectContact
        )

        SwissTransferTextField(
            modifier = modifier,
            label = stringResource(R.string.transferMessagePlaceholder),
            isRequired = false,
            minLineNumber = 3,
            capitalization = KeyboardCapitalization.Sentences,
            onValueChange = transferMessageCallbacks.set,
        )
    }
}

@Composable
private fun EmailAddressesTextFields(
    modifier: Modifier,
    emailTextFieldCallbacks: EmailTextFieldCallbacks,
    shouldShowEmailAddressesFields: () -> Boolean,
    textFieldSpacing: Dp,
    selectContact: (Uri, Context) -> Unit,
) = with(emailTextFieldCallbacks) {
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    val pickContact = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val clipData = it.data?.clipData

            if (clipData != null) {

                val length = clipData.itemCount
                val result = mutableListOf<Uri>()
                for (i in 0 until length) {
                    selectContact(clipData.getItemAt(i).uri, context)
                }
            } else {
                val data = it.data?.data ?: return@rememberLauncherForActivityResult
                selectContact(data, context)
            }

        }
    }

    AnimatedVisibility(visible = shouldShowEmailAddressesFields(), modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(textFieldSpacing)) {
            val isAuthorError = checkEmailError(isAuthor = true)
            val isRecipientError = checkEmailError(isAuthor = false)

            SwissTransferTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.transferSenderAddressPlaceholder),
                initialValue = transferAuthorEmail.get(),
                keyboardType = KeyboardType.Email,
                maxLineNumber = 1,
                imeAction = ImeAction.Next,
                isError = isAuthorError,
                isReadOnly = LocalUser.current.isApiV2(),
                supportingText = getEmailError(isAuthorError),
                onValueChange = transferAuthorEmail.set,
            )

            EmailAddressTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.transferRecipientAddressPlaceholder),
                initialValue = recipientEmail.get(),
                validatedRecipientsEmails = validatedRecipientsEmails,
                onValueChange = { recipientEmail.set(it.text) },
                isError = isRecipientError,
                supportingText = getEmailError(isRecipientError),
                trailingIcon =
                    {
                        TrailingButton(
                            AppIcons.PersonsCircleAdd,
                            {
                                try {
                                    val pickContactIntent =
                                        Intent(ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI).apply {
                                            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                                        }
                                    pickContact.launch(pickContactIntent)
                                } catch (_: ActivityNotFoundException) {
                                    coroutine.launch {
                                        longToast("The contact picker isn't available on your version of Android")
                                    }
                                }
                            })
                    },
            )
        }
    }
}

@Composable
private fun TrailingButton(appIcon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        val (contentDescription, icon) = stringResource(R.string.contentDescriptionButtonHidePassword) to appIcon

        Icon(icon, contentDescription, Modifier.size(Dimens.SmallIconSize))
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
    PickFilesTitle(modifier, R.string.transferTypeTitle)
    TransferTypeButtons(HORIZONTAL_PADDING, selectedTransferType)
}

@Composable
private fun TransferOptions(modifier: Modifier, transferOptionsCallbacks: TransferOptionsCallbacks) {

    var showTransferOption by rememberSaveable { mutableStateOf<TransferOptionType?>(null) }

    fun closeTransferOption() {
        showTransferOption = null
    }

    PickFilesTitle(modifier, R.string.advancedSettingsTitle)
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
private fun PickFilesTitle(modifier: Modifier = Modifier, @StringRes titleRes: Int) {
    Text(
        modifier = modifier.padding(vertical = Margin.Medium),
        style = SwissTransferTheme.typography.bodySmallRegular,
        text = stringResource(titleRes),
    )
}

@Composable
private fun SendButton(
    modifier: Modifier,
    canSendStatus: () -> CanSendStatus,
    expectsClick: () -> Boolean,
    onClick: () -> Unit,
) {
    LargeButton(
        modifier = modifier,
        title = stringResource(R.string.transferSendButton),
        style = ButtonType.Primary,
        showIndeterminateProgress = { canSendStatus().hasIssue(CanSendStatus.Issue.Files.Processing) },
        enabled = expectsClick,
        onClick = onClick,
    )
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
private fun Preview(@PreviewParameter(UserListPreviewParameterProvider::class) users: List<User>) {
    val files = filesPreviewData
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

    CompositionLocalProvider(LocalUser provides users.first()) {
        SwissTransferTheme {
            PickFilesScreen(
                files = { files },
                canSendStatus = { CanSendStatus.Yes },
                transferTitleState = remember { mutableStateOf("") },
                emailTextFieldCallbacks = emailTextFieldCallbacks,
                transferMessageCallbacks = GetSetCallbacks(get = { "" }, set = {}),
                selectedTransferType = GetSetCallbacks(get = { TransferTypeUi.Mail }, set = {}),
                transferOptionsCallbacks = transferOptionsCallbacks,
                pickFiles = {},
                selectContact = { _, _ -> },
                exitNewTransfer = {},
                onSendButtonClick = {},
                isAwaitingSend = { true },
                navigateToFilesDetails = {},
            )
        }
    }
}
