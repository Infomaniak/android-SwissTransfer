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
import android.text.format.Formatter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.AddThick
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightSmall
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

private const val TOTAL_FILE_SIZE: Long = 50_000_000_000

@Composable
fun ImportFilesScreen(
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
    closeActivity: () -> Unit,
) {
    val transferFiles by newTransferViewModel.transferFiles.collectAsStateWithLifecycle()
    ImportFilesScreen({ transferFiles }, newTransferViewModel::addFiles, closeActivity)
}

@Composable
private fun ImportFilesScreen(
    transferFiles: () -> List<FileUiItem>,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
) {
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(true) }
    val fileCount by remember { derivedStateOf { transferFiles().count() } }
    val totalFileSize by remember { derivedStateOf { transferFiles().sumOf { it.fileSizeInBytes } } }
    val isSendButtonEnabled by remember { derivedStateOf { transferFiles().isNotEmpty() } }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris: List<Uri> ->
        addFiles(uris)
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
            LargeButton(
                modifier = modifier,
                titleRes = R.string.sentTitle, // TODO
                style = ButtonType.PRIMARY,
                enabled = isSendButtonEnabled, // TODO: Worth passing as lambda?
                onClick = { /*TODO*/ },
            )
        },
        content = {
            SwissTransferCard(Modifier.padding(Margin.Medium)) {
                SharpRippleButton(onClick = { /*TODO*/ }) {
                    Text(
                        "${fileCount} files", // TODO
                        modifier = Modifier.padding(start = Margin.Medium),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                    )
                    Text(
                        "•",
                        modifier = Modifier.padding(horizontal = Margin.Small),
                        color = SwissTransferTheme.colors.secondaryTextColor
                    )
                    Text(
                        "${formatSpaceLeft(totalFileSize)} left", // TODO
                        color = SwissTransferTheme.colors.secondaryTextColor
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = AppImages.AppIcons.ChevronRightSmall,
                        contentDescription = null,
                        modifier = Modifier.padding(Margin.Medium),
                        tint = SwissTransferTheme.colors.iconColor
                    )
                }

                LazyRow(
                    Modifier.padding(bottom = Margin.Medium),
                    contentPadding = PaddingValues(Margin.Medium),
                    horizontalArrangement = Arrangement.spacedBy(Margin.Medium)
                ) {
                    item {
                        AddNewFileButton { showUploadSourceChoiceBottomSheet = true }
                    }

                    items(
                        items = transferFiles(),
                        key = { it.uid },
                    ) { file ->
                        SmallFileTile(file, SmallFileTileSize.LARGE) {/*TODO*/ }
                    }
                }
            }

            UploadSourceChoiceBottomSheet(
                isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
                onFilePickerClicked = { filePickerLauncher.launch(arrayOf("*/*")) },
                closeBottomSheet = { showUploadSourceChoiceBottomSheet = false },
            )
        },
    )
}

@Composable
private fun AddNewFileButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(80.dp),
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.surface,
            contentColor = SwissTransferTheme.materialColors.primary,
        ),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = AppImages.AppIcons.AddThick,
            contentDescription = null,
        )
    }
}

@Composable
private fun formatSpaceLeft(usedSpace: Long): String {
    val spaceLeft = TOTAL_FILE_SIZE - usedSpace
    return Formatter.formatShortFileSize(LocalContext.current, spaceLeft)
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun ImportFilesScreenPreview() {
    SwissTransferTheme {
        ImportFilesScreen({
            listOf(
                object : FileUiItem {
                    override val uid = ""
                    override val fileName = "Time-Clock-Circle--Streamline-Ultimate.svg (1).svg"
                    override val fileSizeInBytes = 234567832L
                    override val mimeType = null
                    override val uri = "https://fastly.picsum.photos/id/1/200/300.jpg"
                }
            )
        }, {}, closeActivity = {})
    }
}
