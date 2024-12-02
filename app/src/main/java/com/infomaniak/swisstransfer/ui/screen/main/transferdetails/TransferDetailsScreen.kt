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

import androidx.annotation.StringRes
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core2.FORMAT_DATE_FULL
import com.infomaniak.core2.format
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
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
import com.infomaniak.swisstransfer.ui.screen.main.components.SmallWindowTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.*
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.PasswordBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.QrCodeBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components.TransferInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.shareText

@Composable
fun TransferDetailsScreen(
    transferUuid: String,
    direction: TransferDirection,
    navigateBack: (() -> Unit)?,
    transferDetailsViewModel: TransferDetailsViewModel = hiltViewModel<TransferDetailsViewModel>(),
) {
    val uiState by transferDetailsViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(transferUuid) {
        transferDetailsViewModel.loadTransfer(transferUuid)
    }

    if (uiState is Delete) {
        navigateBack?.invoke()
    } else if (uiState is Success) {
        TransferDetailsScreen(
            transferUrl = transferDetailsViewModel.getTransferUrl(transferUuid),
            direction = direction,
            navigateBack = navigateBack,
            getTransfer = { (uiState as Success).transfer },
            getCheckedFiles = { transferDetailsViewModel.checkedFiles },
            clearCheckedFiles = { transferDetailsViewModel.checkedFiles.clear() },
            setFileCheckStatus = { fileUid, isChecked ->
                transferDetailsViewModel.checkedFiles[fileUid] = isChecked
            },
        )
    }
}

@Composable
private fun TransferDetailsScreen(
    transferUrl: String,
    direction: TransferDirection,
    navigateBack: (() -> Unit)?,
    getTransfer: () -> TransferUi,
    getCheckedFiles: () -> SnapshotStateMap<String, Boolean>,
    clearCheckedFiles: () -> Unit,
    setFileCheckStatus: (String, Boolean) -> Unit,
) {

    val context = LocalContext.current
    val transferRecipients: List<String> = emptyList() // TODO: Use real data

    var isMultiselectOn: Boolean by rememberSaveable { mutableStateOf(false) }
    var showQrCodeBottomSheet: Boolean by rememberSaveable { mutableStateOf(false) }
    var showPasswordBottomSheet: Boolean by rememberSaveable { mutableStateOf(false) }

    SmallWindowTopAppBarScaffold(
        smallWindowTopAppBar = {
            SwissTransferTopAppBar(
                title = getTransfer().createdDateTimestamp.toDateFromSeconds().format(FORMAT_DATE_FULL),
                navigationMenu = TopAppBarButton.backButton(navigateBack ?: {}),
                TopAppBarButton.downloadButton { /* TODO */ },
            )
        },
        floatingActionButton = {},
    ) {
        Column {

            FilesList(getTransfer, transferRecipients, isMultiselectOn, getCheckedFiles, setFileCheckStatus)

            BottomBar(
                direction = direction,
                isMultiselectOn = { isMultiselectOn },
                shouldShowPassword = { getTransfer().password?.isNotEmpty() == true },
                onClick = { item ->
                    when (item) {
                        BottomBarItem.SHARE -> context.shareText(transferUrl)
                        BottomBarItem.QR_CODE -> showQrCodeBottomSheet = true
                        BottomBarItem.PASSWORD -> showPasswordBottomSheet = true
                        BottomBarItem.DOWNLOAD -> {
                            // TODO: Move the multiselect elsewhere, and implement this feature
                            isMultiselectOn = true
                        }
                        BottomBarItem.MULTISELECT_DOWNLOAD -> {
                            // TODO: Move the multiselect elsewhere, and implement this feature
                            clearCheckedFiles()
                            isMultiselectOn = false
                        }
                    }
                },
            )
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
private fun ColumnScope.FilesList(
    getTransfer: () -> TransferUi,
    transferRecipients: List<String>,
    isMultiselectOn: Boolean,
    getCheckedFiles: () -> SnapshotStateMap<String, Boolean>,
    setFileCheckStatus: (String, Boolean) -> Unit,
) {

    val shouldDisplayRecipients = transferRecipients.isNotEmpty()
    val shouldDisplayMessage = getTransfer().message != null

    FileItemList(
        modifier = Modifier
            .weight(1.0f)
            .padding(horizontal = Margin.Medium),
        files = getTransfer().files,
        isRemoveButtonVisible = false,
        isCheckboxVisible = { isMultiselectOn },
        isUidChecked = { fileUid -> getCheckedFiles()[fileUid] ?: false },
        setUidCheckStatus = { fileUid, isChecked -> setFileCheckStatus(fileUid, isChecked) },
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
    )
}

@Composable
private fun TransferRecipients(recipients: List<String>) {
    Text(
        text = pluralStringResource(R.plurals.recipientHeader, recipients.count()),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
    Spacer(Modifier.height(Margin.Mini))
    EmailsFlowRow(recipients)
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
private fun BottomBar(
    direction: TransferDirection,
    isMultiselectOn: () -> Boolean,
    shouldShowPassword: () -> Boolean,
    onClick: (BottomBarItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .height(80.dp)
            .background(SwissTransferTheme.colors.navigationItemBackground),
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier.padding(horizontal = Margin.Medium),
        ) {
            if (isMultiselectOn()) {
                BottomBarButton(BottomBarItem.MULTISELECT_DOWNLOAD, onClick)
            } else {
                BottomBarButton(BottomBarItem.SHARE, onClick)

                when (direction) {
                    TransferDirection.SENT -> {
                        Spacer(Modifier.width(Margin.Medium))
                        BottomBarButton(BottomBarItem.QR_CODE, onClick)

                        if (shouldShowPassword()) {
                            Spacer(Modifier.width(Margin.Medium))
                            BottomBarButton(BottomBarItem.PASSWORD, onClick)
                        }
                    }
                    TransferDirection.RECEIVED -> {
                        Spacer(Modifier.width(Margin.Medium))
                        BottomBarButton(BottomBarItem.DOWNLOAD, onClick)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.BottomBarButton(item: BottomBarItem, onClick: (BottomBarItem) -> Unit) {
    Button(
        modifier = Modifier.weight(1.0f),
        colors = ButtonType.TERTIARY.buttonColors(),
        onClick = { onClick(item) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(item.icon, null)
            Spacer(Modifier.height(Margin.Micro))
            Text(text = stringResource(item.label))
        }
    }
}

private enum class BottomBarItem(@StringRes val label: Int, val icon: ImageVector) {
    SHARE(R.string.buttonShare, AppIcons.Share),
    QR_CODE(R.string.transferTypeQrCode, AppIcons.QrCode),
    PASSWORD(R.string.settingsOptionPassword, AppIcons.LockedTextField),
    DOWNLOAD(R.string.buttonDownload, AppIcons.ArrowDownBar),
    MULTISELECT_DOWNLOAD(R.string.buttonDownloadSelected, AppIcons.ArrowDownBar),
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
                getCheckedFiles = { mutableStateMapOf() },
                clearCheckedFiles = {},
                setFileCheckStatus = { _, _ -> },
            )
        }
    }
}
