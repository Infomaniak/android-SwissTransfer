/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.components.transfer.FilesDetailsScreen
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.newtransfer.filesdetails.FilesDetailsViewModel
import com.infomaniak.swisstransfer.ui.utils.openFile

@Composable
fun ExistingTransferFilesDetailsScreen(
    filesDetailsViewModel: FilesDetailsViewModel = hiltViewModel<FilesDetailsViewModel>(),
    folderUuid: String,
    transferUuid: String,
    navigateToFolder: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    navigateBack: () -> Unit,
    close: () -> Unit,
) {
    val files by remember(folderUuid) {
        filesDetailsViewModel.filesFlow(folderUuid)
    }.collectAsStateWithLifecycle(initialValue = null)
    val snackbarHostState = remember { SnackbarHostState() }

    files?.let {
        SwissTransferScaffold(
            topBar = {
                SwissTransferTopAppBar(
                    navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
                    actions = { TopAppBarButtons.Close(close) },
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            val context = LocalContext.current
            FilesDetailsScreen(
                snackbarHostState = snackbarHostState,
                files = it,
                navigateToFolder = navigateToFolder,
                transferFlow = filesDetailsViewModel.transferFlow(transferUuid),
                runDownloadUi = { ui, transfer, file ->
                    filesDetailsViewModel.handleTransferDownload(
                        ui = ui,
                        transfer = transfer,
                        targetFile = file,
                        openFile = { uri -> context.openFile(uri) },
                        direction = null
                    )
                },
                previewUriForFile = { transfer, file -> filesDetailsViewModel.previewUriForFile(transfer, file) },
                withFileSize = withFilesSize,
                withSpaceLeft = withSpaceLeft,
                isDownloadButtonVisible = true,
                direction = null
            )
        }
    }
}
