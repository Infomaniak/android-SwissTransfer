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

import android.content.Context
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Parcelable
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
import androidx.compose.ui.res.pluralStringResource
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
import kotlinx.parcelize.Parcelize

private const val TOTAL_FILE_SIZE: Long = 50_000_000_000

@Composable
fun ImportFilesScreen(
    newTransferViewModel: NewTransferViewModel = hiltViewModel<NewTransferViewModel>(),
    closeActivity: () -> Unit,
) {
    val files by newTransferViewModel.files.collectAsStateWithLifecycle()
    ImportFilesScreen({ files }, newTransferViewModel::removeFileByUid, newTransferViewModel::addFiles, closeActivity)
}

@Composable
private fun ImportFilesScreen(
    files: () -> List<FileUiItem>,
    removeFileByUid: (uid: String) -> Unit,
    addFiles: (List<Uri>) -> Unit,
    closeActivity: () -> Unit,
) {
    val context = LocalContext.current
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(true) }
    val fileCount by remember { derivedStateOf { files().count() } }
    val formattedSizeWithUnits by remember {
        derivedStateOf {
            val totalFileSize = files().sumOf { it.fileSizeInBytes }
            getFormattedSizeWithUnits(totalFileSize, context)
        }
    }
    val isSendButtonEnabled by remember { derivedStateOf { files().isNotEmpty() } }

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
                titleRes = R.string.transferSendButton,
                style = ButtonType.PRIMARY,
                enabled = { isSendButtonEnabled },
                onClick = { /*TODO*/ },
            )
        },
        content = {
            SwissTransferCard(Modifier.padding(Margin.Medium)) {
                SharpRippleButton(onClick = { /*TODO*/ }) {
                    Text(
                        pluralStringResource(R.plurals.filesCount, fileCount, fileCount),
                        modifier = Modifier.padding(start = Margin.Medium),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
                    )
                    Text(
                        "•",
                        modifier = Modifier.padding(horizontal = Margin.Small),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
                    )
                    Text(
                        formatSpaceLeft(formattedSizeWithUnits),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
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
                    Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(Margin.Medium),
                    horizontalArrangement = Arrangement.spacedBy(Margin.Medium)
                ) {
                    item(key = TransferLazyRowKey(TransferLazyRowKey.Type.ADD_BUTTON)) {
                        AddNewFileButton(Modifier.animateItem()) { showUploadSourceChoiceBottomSheet = true }
                    }

                    items(
                        items = files(),
                        key = { TransferLazyRowKey(TransferLazyRowKey.Type.FILE, it.uid) },
                    ) { file ->
                        SmallFileTile(
                            modifier = Modifier.animateItem(),
                            file = file,
                            smallFileTileSize = SmallFileTileSize.LARGE,
                            onRemove = { removeFileByUid(file.uid) }
                        )
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
private fun AddNewFileButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.size(80.dp),
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

private fun getFormattedSizeWithUnits(usedSpace: Long, context: Context): String {
    val spaceLeft = (TOTAL_FILE_SIZE - usedSpace).coerceAtLeast(0)
    return Formatter.formatShortFileSize(context, spaceLeft)
}

@Composable
private fun formatSpaceLeft(formattedSizeWithUnits: String): String {
    val quantity = getQuantityFromFormattedSizeWithUnits(formattedSizeWithUnits)
    return pluralStringResource(R.plurals.transferSpaceLeft, quantity, formattedSizeWithUnits)
}

@Composable
private fun getQuantityFromFormattedSizeWithUnits(formattedSize: String): Int {
    val sizeParts = formattedSize.split(' ', Typography.nbsp) // Space for languages such as EN and NBSP for languages such as FR

    return if (sizeParts.size == 2) {
        val local = LocalContext.current.resources.configuration.getLocales().get(0)
        val parsedNumber = NumberFormat.getInstance(local).parse(sizeParts[0])
        parsedNumber?.toDouble()?.toInt() ?: 0
    } else {
        0
    }
}

@Parcelize
private data class TransferLazyRowKey(
    val type: Type,
    val fileUid: String? = null
) : Parcelable {
    enum class Type {
        ADD_BUTTON, FILE
    }
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
                    override val uri = ""
                }
            )
        }, {}, {}, closeActivity = {})
    }
}
