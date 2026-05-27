/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025-2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.infomaniak.core.permissionmanager.PermissionType
import com.infomaniak.core.permissionmanager.rememberPermissionManagerState
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferCenterTopAppBar
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.components.transfer.FilesDetailsScreen
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownBar
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.TransferIdType
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.BottomBar
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.BottomBarButton
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.getBottomBarPadding
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.FilesDetailsViewModel
import com.infomaniak.swisstransfer.ui.utils.openFile
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExistingTransferFilesDetailsScreen(
    folderUuid: String,
    transferIdType: TransferIdType,
    navigateToFolder: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    navigateBack: () -> Unit,
    filesDetailsViewModel: FilesDetailsViewModel = hiltViewModel<FilesDetailsViewModel>(),
    close: () -> Unit
) {
    val files by remember(folderUuid) {
        filesDetailsViewModel.filesFlow(folderUuid)
    }.collectAsStateWithLifecycle(initialValue = null)
    val snackbarHostState = remember { SnackbarHostState() }

    var isMultiselectOn by remember { mutableStateOf(false) }
    val checkedFiles = remember { mutableStateMapOf<String, Boolean>() }
    LaunchedEffect(checkedFiles) {
        snapshotFlow { checkedFiles.values.any { it } }
            .collectLatest { hasSelection ->
                if (isMultiselectOn && !hasSelection) {
                    isMultiselectOn = false
                    checkedFiles.clear()
                }
            }
    }

    val topBarTitle = if (isMultiselectOn) {
        val selectedCount = checkedFiles.values.count { it }
        pluralStringResource(R.plurals.multipleSelectionTitle, selectedCount, selectedCount)
    } else {
        ""
    }

    SwissTransferScaffold(
        topBar = {
            if (isMultiselectOn) {
                SwissTransferCenterTopAppBar(
                    title = topBarTitle,
                    navigationIcon = {
                        TextButton(
                            onClick = {
                                isMultiselectOn = false
                                checkedFiles.clear()
                            }
                        ) {
                            Text(text = stringResource(R.string.contentDescriptionButtonClose))
                        }
                    },
                    actions = {
                        val checkedCount = checkedFiles.values.count { it }
                        val textRes = if (checkedCount == files?.size) R.string.settingsOptionNone else R.string.buttonAll
                        TextButton(
                            onClick = {
                                if (checkedCount == files?.size) {
                                    checkedFiles.clear()
                                } else {
                                    files?.forEach { file -> checkedFiles[file.uid] = true }
                                }
                            }
                        ) {
                            Text(text = stringResource(textRes))
                        }
                    },
                )
            } else {
                SwissTransferTopAppBar(
                    title = topBarTitle,
                    navigationIcon = {
                        TopAppBarButtons.Back(onClick = navigateBack)
                    },
                    actions = { TopAppBarButtons.Close(close) },
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        files?.let { fileList ->
            val context = LocalContext.current
            Column(modifier = Modifier.fillMaxSize()) {
                FilesDetailsScreen(
                    modifier = Modifier.weight(1f),
                    snackbarHostState = snackbarHostState,
                    files = fileList,
                    navigateToFolder = navigateToFolder,
                    transferFlow = filesDetailsViewModel.transferFlow(transferIdType),
                    runDownloadUi = { ui, transfer, downloadTarget ->
                        filesDetailsViewModel.handleTransferDownload(
                            ui = ui,
                            transfer = transfer,
                            downloadTarget = downloadTarget,
                            openFile = { uri -> context.openFile(uri) },
                        )
                    },
                    previewUriForFile = { transfer, file -> filesDetailsViewModel.previewUriForFile(transfer, file) },
                    withFileSize = withFilesSize,
                    withSpaceLeft = withSpaceLeft,
                    isNewTransfer = false,
                    isCheckboxVisible = { isMultiselectOn },
                    isUidChecked = { uid -> checkedFiles[uid] ?: false },
                    setUidCheckStatus = { uid, checked -> checkedFiles[uid] = checked },
                    downloadRequestFlow = filesDetailsViewModel.fileDownloadRequests,
                    onLongPress = { uid ->
                        if (!isMultiselectOn) {
                            isMultiselectOn = true
                            checkedFiles.clear()
                            checkedFiles[uid] = true
                        }
                    },
                )

                if (isMultiselectOn) {
                    val selectedUids = checkedFiles.filterValues { it }.keys.toList()
                    val writePermissionManager = rememberPermissionManagerState(PermissionType.WriteExternalStorage)
                    BottomBar(getBottomBarPadding()) {
                        BottomBarButton(
                            icon = AppIcons.ArrowDownBar,
                            labelResId = R.string.buttonDownloadSelected,
                            enabled = selectedUids.isNotEmpty(),
                            onClick = writePermissionManager.dropIfDenied {
                                filesDetailsViewModel.scheduleFileSelectionDownload(transferIdType, selectedUids)
                                checkedFiles.clear()
                                isMultiselectOn = false
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}
