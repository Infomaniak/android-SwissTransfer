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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import android.Manifest
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.infomaniak.core.FORMAT_DATE_FULL
import com.infomaniak.core.format
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownBar
import com.infomaniak.swisstransfer.ui.images.icons.LockedTextField
import com.infomaniak.swisstransfer.ui.images.icons.QrCode
import com.infomaniak.swisstransfer.ui.images.icons.Share
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.Delete
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.Success
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.PasswordBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.QrCodeBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.TransferInfo
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.DeeplinkPasswordAlertDialog
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import com.infomaniak.swisstransfer.ui.utils.openFile
import com.infomaniak.swisstransfer.ui.utils.shareText
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TransferDetailsScreen(
    transferUuid: String,
    direction: TransferDirection,
    navigateBack: (() -> Unit)?,
    transferDetailsViewModel: TransferDetailsViewModel = hiltViewModel<TransferDetailsViewModel>(),
    navigateToFolder: (folderUuid: String) -> Unit,
) {
    val uiState by transferDetailsViewModel.uiState.collectAsStateWithLifecycle()
    val isDeeplinkPasswordNeeded by transferDetailsViewModel.isDeeplinkNeedingPassword.collectAsStateWithLifecycle()
    val isWrongDeeplinkPassword by transferDetailsViewModel.isWrongDeeplinkPassword.collectAsStateWithLifecycle()

    LaunchedEffect(transferUuid) {
        transferDetailsViewModel.loadTransfer(transferUuid)
    }

    val context = LocalContext.current
    when (val state = uiState) {
        is Delete -> navigateBack?.invoke()
        is TransferDetailsViewModel.TransferDetailsUiState.Loading -> {
            SwissTransferScaffold(topBar = { SwissTransferTopAppBar(title = "") }) {}
        }
        is Success -> {
            val success by rememberUpdatedState(state)
            TransferDetailsScreen(
                transferUrl = transferDetailsViewModel.getTransferUrl(transferUuid),
                direction = direction,
                navigateBack = navigateBack,
                getTransfer = { success.transfer },
                runDownloadUi = { ui, transfer, file ->
                    transferDetailsViewModel.handleTransferDownload(
                        ui = ui,
                        transfer = transfer,
                        targetFile = file,
                        openFile = { uri -> context.openFile(uri) },
                    )
                },
                uriForFile = { transfer, file -> transferDetailsViewModel.uriForFile(transfer, file) },
                getCheckedFiles = { transferDetailsViewModel.checkedFiles },
                clearCheckedFiles = { transferDetailsViewModel.checkedFiles.clear() },
                setFileCheckStatus = { fileUid, isChecked ->
                    transferDetailsViewModel.checkedFiles[fileUid] = isChecked
                },
                navigateToFolder = navigateToFolder,
            )
        }
        TransferDetailsViewModel.TransferDetailsUiState.Loading -> Unit
    }

    if (isDeeplinkPasswordNeeded) {
        DeeplinkPasswordAlertDialog(
            password = transferDetailsViewModel.deeplinkPassword,
            closeAlertDialog = {
                transferDetailsViewModel.resetIsDeeplinkNeedingPassword()
                navigateBack?.invoke()
            },
            onConfirmation = { transferDetailsViewModel.loadTransfer(transferUuid) },
            isError = { isWrongDeeplinkPassword },
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun TransferDetailsScreen(
    transferUrl: String,
    direction: TransferDirection,
    navigateBack: (() -> Unit)?,
    getTransfer: () -> TransferUi,
    runDownloadUi: suspend (ui: TransferDownloadUi, transfer: TransferUi, file: FileUi?) -> Nothing,
    uriForFile: (transfer: TransferUi, file: FileUi) -> Flow<Uri?> = { _, _ -> emptyFlow() },
    getCheckedFiles: () -> SnapshotStateMap<String, Boolean>,
    clearCheckedFiles: () -> Unit, // TODO: Unused for now, to be implemented or deleted someday
    setFileCheckStatus: (String, Boolean) -> Unit,
    navigateToFolder: (folderUuid: String) -> Unit,
) {

    val context = LocalContext.current
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current
    val transferRecipients: Set<String> = getTransfer().recipientsEmails

    var isMultiselectOn: Boolean by rememberSaveable { mutableStateOf(false) }
    var showQrCodeBottomSheet: Boolean by rememberSaveable { mutableStateOf(false) }
    var showPasswordBottomSheet: Boolean by rememberSaveable { mutableStateOf(false) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val snackbarHostState = remember { SnackbarHostState() }
    val writeExternalStoragePermissionState: PermissionState? = when {
        SDK_INT > 28 -> null
        else -> rememberPermissionState(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
    val downloadUi = remember(lifecycle) {
        TransferDownloadComposeUi(lifecycle, snackbarHostState, writeExternalStoragePermissionState)
    }
    val transferFlow = remember { snapshotFlow { getTransfer() } }
    LaunchedEffect(Unit) {
        transferFlow.collect { transfer ->
            val singleFileOrNull = transfer.files.singleOrNull()
            runDownloadUi(downloadUi, transfer, singleFileOrNull)
        }
    }

    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                title = getTransfer().createdDateTimestamp.toDateFromSeconds().format(FORMAT_DATE_FULL),
                navigationIcon = { if (windowAdaptiveInfo.isWindowSmall()) TopAppBarButtons.Back(onClick = navigateBack ?: {}) },
                actions = { downloadUi.TopAppBarButton() }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Column {

            FilesList(
                snackbarHostState = snackbarHostState,
                writeExternalStoragePermissionState = writeExternalStoragePermissionState,
                getTransfer = getTransfer,
                transferRecipients = transferRecipients,
                isMultiselectOn = isMultiselectOn,
                getCheckedFiles = getCheckedFiles,
                setFileCheckStatus = setFileCheckStatus,
                navigateToFolder = navigateToFolder,
                transferFlow = transferFlow,
                runDownloadUi = runDownloadUi,
                uriForFile = uriForFile,
            )

            BottomBar(getBottomBarPadding()) {
                val buttonsModifier = Modifier.weight(1f)
                if (isMultiselectOn) {
                    BottomBarButton(
                        icon = AppIcons.ArrowDownBar,
                        labelResId = R.string.buttonDownloadSelected,
                        onClick = {
                            // TODO: Move the multiselect elsewhere, and implement this feature
                            // clearCheckedFiles() // Disabled for now because it's not the correct spot to do this
                            // isMultiselectOn = false // Disabled for now because it's not the correct spot to do this
                        }
                    )
                } else {
                    BottomBarButton(
                        icon = AppIcons.Share,
                        labelResId = R.string.buttonShare,
                        onClick = { context.shareText(transferUrl) },
                        modifier = buttonsModifier,
                    )

                    when (direction) {
                        TransferDirection.SENT -> {
                            BottomBarButton(
                                icon = AppIcons.QrCode,
                                labelResId = R.string.transferTypeQrCode,
                                onClick = { showQrCodeBottomSheet = true },
                                modifier = buttonsModifier,
                            )

                            val shouldShowPassword = getTransfer().password?.isNotEmpty() == true
                            if (shouldShowPassword) {
                                BottomBarButton(
                                    icon = AppIcons.LockedTextField,
                                    labelResId = R.string.settingsOptionPassword,
                                    onClick = { showPasswordBottomSheet = true },
                                    modifier = buttonsModifier,
                                )
                            }
                        }
                        TransferDirection.RECEIVED -> downloadUi.BottomBarItem(modifier = buttonsModifier)
                    }
                }
            }
        }

        QrCodeBottomSheet(
            isVisible = { showQrCodeBottomSheet },
            transferUrl = transferUrl,
            closeBottomSheet = { showQrCodeBottomSheet = false },
        )
        PasswordBottomSheet(
            isVisible = { showPasswordBottomSheet },
            transferPassword = { getTransfer().password },
            closeBottomSheet = { showPasswordBottomSheet = false },
        )
    }
}

@Composable
private fun getBottomBarPadding(): PaddingValues {
    return if (LocalWindowAdaptiveInfo.current.isWindowSmall()) {
        WindowInsets.navigationBars.asPaddingValues()
    } else {
        PaddingValues()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ColumnScope.FilesList(
    snackbarHostState: SnackbarHostState,
    writeExternalStoragePermissionState: PermissionState?,
    getTransfer: () -> TransferUi,
    transferRecipients: Set<String>,
    isMultiselectOn: Boolean,
    getCheckedFiles: () -> SnapshotStateMap<String, Boolean>,
    setFileCheckStatus: (String, Boolean) -> Unit,
    navigateToFolder: ((folderUuid: String) -> Unit)? = null,
    transferFlow: Flow<TransferUi>,
    runDownloadUi: suspend (ui: TransferDownloadUi, transfer: TransferUi, file: FileUi) -> Nothing,
    uriForFile: (transfer: TransferUi, file: FileUi) -> Flow<Uri?> = { _, _ -> emptyFlow() },
) {

    val shouldDisplayRecipients = transferRecipients.isNotEmpty()
    val shouldDisplayMessage = getTransfer().message.isNullOrBlank().not()

    FileItemList(
        modifier = Modifier
            .weight(1.0f)
            .padding(horizontal = Margin.Medium),
        snackbarHostState = snackbarHostState,
        writeExternalStoragePermissionState = writeExternalStoragePermissionState,
        files = getTransfer().files,
        isDownloadButtonVisible = true,
        isRemoveButtonVisible = false,
        isCheckboxVisible = { isMultiselectOn },
        isUidChecked = { fileUid -> getCheckedFiles()[fileUid] ?: false },
        setUidCheckStatus = { fileUid, isChecked -> setFileCheckStatus(fileUid, isChecked) },
        navigateToFolder = { fileUuid ->
            navigateToFolder?.invoke(fileUuid)
        },
        header = {
            Column {
                Spacer(Modifier.height(Margin.Large))
                TransferInfo(getTransfer)
                Spacer(Modifier.height(Margin.Large))
                if (shouldDisplayRecipients) TransferRecipients(transferRecipients)
                if (shouldDisplayRecipients && shouldDisplayMessage) Spacer(Modifier.height(Margin.Mini))
                if (shouldDisplayMessage) TransferMessage(getTransfer().message!!)
                if (shouldDisplayRecipients || shouldDisplayMessage) Spacer(Modifier.height(Margin.Large))
                TransferContentHeader()
            }
        },
        transferFlow = transferFlow,
        runDownloadUi = runDownloadUi,
        uriForFile = uriForFile,
    )
}

@Composable
private fun TransferRecipients(recipients: Set<String>) {
    Text(
        text = pluralStringResource(R.plurals.recipientHeader, recipients.count()),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
    Spacer(Modifier.height(Margin.Mini))
    EmailsFlowRow(recipients.toList())
}

@Composable
private fun TransferMessage(transferMessage: String) {
    Text(
        text = stringResource(R.string.messageHeader),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
    Spacer(Modifier.height(Margin.Medium))
    SwissTransferCard {
        Text(
            text = transferMessage,
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
            modifier = Modifier.padding(all = Margin.Large),
        )
    }
}

@Composable
private fun TransferContentHeader() {
    Text(
        text = stringResource(R.string.transferContentHeader),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
}

@Composable
private fun BottomBar(paddingValues: PaddingValues, content: @Composable (RowScope.() -> Unit)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SwissTransferTheme.colors.navigationItemBackground)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .padding(horizontal = Margin.Micro)
                .heightIn(min = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
        Spacer(Modifier.height(paddingValues.calculateBottomPadding()))
    }
}

@Composable
fun BottomBarButton(
    icon: ImageVector,
    labelResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    extraBackgroundContent: @Composable BoxScope.() -> Unit = {}
) = Button(
    colors = ButtonType.Tertiary.buttonColors(),
    enabled = enabled,
    onClick = { onClick() },
    modifier = modifier,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center) {
            extraBackgroundContent()
            Icon(icon, null)
        }
        Spacer(Modifier.height(Margin.Micro))
        Text(text = stringResource(labelResId), textAlign = TextAlign.Center)
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            TransferDetailsScreen(
                transferUrl = "",
                direction = TransferDirection.SENT,
                navigateBack = null,
                getTransfer = { transfers.first() },
                runDownloadUi = { _, _, _ -> awaitCancellation() },
                getCheckedFiles = { mutableStateMapOf() },
                clearCheckedFiles = {},
                setFileCheckStatus = { _, _ -> },
                navigateToFolder = { _ -> },
            )
        }
    }
}
