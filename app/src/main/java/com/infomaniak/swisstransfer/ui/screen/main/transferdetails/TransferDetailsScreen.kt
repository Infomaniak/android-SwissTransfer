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

import android.text.format.Formatter
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.*
import com.infomaniak.swisstransfer.ui.screen.main.components.SmallWindowTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.FORMAT_DATE_FULL
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.format
import com.infomaniak.swisstransfer.ui.utils.shareText
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Composable
fun TransferDetailsScreen(
    transferUuid: String,
    navigateBack: (() -> Unit)?,
    transferDetailsViewModel: TransferDetailsViewModel = hiltViewModel<TransferDetailsViewModel>(),
) {

    val transferFiles = listOf(
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "The 5-Step Guide to Not Breaking Your Code (1).txt",
            fileSize = 57_689_032L,
            mimeType = null,
            localPath = null,
        ),
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "Introduction to Turning It Off and On Again (1).pptx",
            fileSize = 89_723_143L,
            mimeType = null,
            localPath = null,
        ),
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "Learning to Copy and Paste: A Complete Guide (1).docx",
            fileSize = 237_866_728L,
            mimeType = null,
            localPath = null,
        ),
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "The 5-Step Guide to Not Breaking Your Code (2).txt",
            fileSize = 57_689_032L,
            mimeType = null,
            localPath = null,
        ),
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "Introduction to Turning It Off and On Again (2).pptx",
            fileSize = 89_723_143L,
            mimeType = null,
            localPath = null,
        ),
        FileUi(
            uid = UUID.randomUUID().toString(),
            fileName = "Learning to Copy and Paste: A Complete Guide (2).docx",
            fileSize = 237_866_728L,
            mimeType = null,
            localPath = null,
        ),
    )

    val transfer = TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = Date().time - 30L * 86_400_000L,
        expirationDateTimestamp = Calendar.getInstance().apply {
            time = Date()
            set(Calendar.DATE, get(Calendar.DATE) + 3)
        }.time.time,
        sizeUploaded = 57_689_032L,
        downloadLimit = 250,
        downloadLeft = 42,
        message = "Coucou c'est moi le message de description du transfert.",
        files = transferFiles,
    )

    val transferSenderEmail: String? = "john.smith@ik.me" // TODO
    val transferLink = "https://chk.me/83azQOl" // TODO

    var isMultiselectOn: Boolean by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    SmallWindowTopAppBarScaffold(
        smallWindowTopAppBar = {
            SwissTransferTopAppBar(
                title = Date(transfer.createdDateTimestamp).format(FORMAT_DATE_FULL),
                navigationMenu = TopAppBarButton.backButton(navigateBack ?: {}),
            )
        },
        floatingActionButton = {},
    ) {
        Column {

            FileItemList(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(horizontal = Margin.Medium),
                files = transferFiles, // TODO
                isRemoveButtonVisible = false,
                isCheckboxVisible = { isMultiselectOn },
                isUidChecked = { fileUid -> transferDetailsViewModel.checkedFiles[fileUid] ?: false },
                setUidCheckStatus = { fileUid, isChecked -> transferDetailsViewModel.checkedFiles[fileUid] = isChecked },
            ) {
                Column {
                    Spacer(modifier = Modifier.height(Margin.Large))
                    TransferInfos(transfer)
                    TransferMessage(transfer, transferSenderEmail)
                    Spacer(modifier = Modifier.height(Margin.Large))
                    TransferContentHeader()
                }
            }

            BottomBar(
                isMultiselectOn = { isMultiselectOn },
                onClick = { item ->
                    when (item) {
                        BottomBarItem.SHARE -> context.shareText(transferLink)
                        BottomBarItem.DOWNLOAD -> {
                            // TODO: Move the multiselect elsewhere, and implement this feature
                            isMultiselectOn = true
                        }
                        BottomBarItem.MULTISELECT_DOWNLOAD -> {
                            // TODO: Move the multiselect elsewhere, and implement this feature
                            transferDetailsViewModel.checkedFiles.clear()
                            isMultiselectOn = false
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun TransferInfos(transfer: TransferUi) {

    val filesCount = transfer.files.count()
    val downloadedCount = transfer.downloadLimit - transfer.downloadLeft

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = AppIcons.FileZip,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start = Margin.Mini),
            text = pluralStringResource(R.plurals.filesCount, filesCount, filesCount),
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
        Text(
            text = "•",
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
            modifier = Modifier.padding(horizontal = Margin.Mini),
        )
        Text(
            text = Formatter.formatShortFileSize(LocalContext.current, transfer.sizeUploaded),
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = Margin.Medium))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Margin.Medium),
            imageVector = AppIcons.Clock,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start = Margin.Mini),
            text = stringResource(R.string.expiresIn, transfer.expiresInDays),
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = Margin.Medium))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Margin.Medium),
            imageVector = AppIcons.ArrowDownFile,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start = Margin.Mini),
            text = stringResource(R.string.downloadedTransferLabel, downloadedCount, transfer.downloadLimit),
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
    }
}

@Composable
private fun TransferMessage(transfer: TransferUi, transferSenderEmail: String?) {

    if (transferSenderEmail == null && transfer.message == null) return

    Spacer(modifier = Modifier.height(Margin.Large))

    Text(
        text = stringResource(R.string.messageHeader),
        style = SwissTransferTheme.typography.bodySmallRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )

    SwissTransferCard(modifier = Modifier.padding(top = Margin.Medium)) {

        transferSenderEmail?.let {
            Row(
                modifier = Modifier.padding(all = Margin.Large),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.fromHeader),
                    style = SwissTransferTheme.typography.bodySmallRegular,
                    color = SwissTransferTheme.colors.primaryTextColor,
                )
                EmailAddressChip(
                    text = it,
                    modifier = Modifier.padding(start = Margin.Mini),
                )
            }
        }

        if (transferSenderEmail != null && transfer.message != null) HorizontalDivider()

        transfer.message?.let {
            Text(
                text = it,
                modifier = Modifier.padding(all = Margin.Large),
                style = SwissTransferTheme.typography.bodySmallRegular,
                color = SwissTransferTheme.colors.primaryTextColor,
            )
        }
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
    isMultiselectOn: () -> Boolean,
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
                Spacer(modifier = Modifier.width(Margin.Medium))
                BottomBarButton(BottomBarItem.DOWNLOAD, onClick)
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
            Spacer(modifier = Modifier.height(Margin.Micro))
            Text(stringResource(item.label))
        }
    }
}

private enum class BottomBarItem(@StringRes val label: Int, val icon: ImageVector) {
    SHARE(R.string.buttonShare, AppIcons.Share),
    DOWNLOAD(R.string.buttonDownload, AppIcons.ArrowDownBar),
    MULTISELECT_DOWNLOAD(R.string.buttonDownloadSelected, AppIcons.ArrowDownBar),
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            TransferDetailsScreen("", {})
        }
    }
}
