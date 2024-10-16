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
package com.infomaniak.swisstransfer.ui.screen.main.received

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotSearching
import com.infomaniak.swisstransfer.ui.screen.main.components.BrandTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferId: Int) -> Unit,
    sentViewModel: SentViewModel = hiltViewModel<SentViewModel>(),
) {
    val transfers by sentViewModel.transfers.collectAsStateWithLifecycle()
    val isTransferChecked = sentViewModel.selectedTransferIds
    val areTransfersEmpty by remember { derivedStateOf { transfers?.isEmpty() == true } }

    ReceivedScreen(
        isFileChecked = { uid -> isTransferChecked[uid] == true },
        setFileCheckStatus = { uid, isChecked -> isTransferChecked[uid] = isChecked },
        areTransfersEmpty = { areTransfersEmpty }
    )
}

@Composable
private fun ReceivedScreen(
    isFileChecked: (String) -> Boolean,
    setFileCheckStatus: (String, Boolean) -> Unit,
    areTransfersEmpty: () -> Boolean,
) {
    BrandTopAppBarScaffold(
        floatingActionButton = { ReceivedEmptyFab(areTransfersEmpty) },
    ) {
        if (areTransfersEmpty()) {
            EmptyState(
                icon = AppIllus.MascotSearching,
                title = R.string.noTransferReceivedTitle,
                description = R.string.noTransferReceivedDescription,
            )
        } else {
            val files = listOf(
                FileUi(
                    fileName = "The 5-Step Guide to Not Breaking Your Code.txt",
                    uid = "The 5-Step Guide to Not Breaking Your Code.txt",
                    fileSizeInBytes = 57689032,
                    mimeType = null,
                    uri = "",
                ),
                FileUi(
                    fileName = "Introduction to Turning It Off and On Again.pptx",
                    uid = "Introduction to Turning It Off and On Again.pptx",
                    fileSizeInBytes = 89723143,
                    mimeType = null,
                    uri = "",
                ),
                FileUi(
                    fileName = "Learning to Copy and Paste: A Complete Guide.docx",
                    uid = "Learning to Copy and Paste: A Complete Guide.docx",
                    fileSizeInBytes = 237866728,
                    mimeType = null,
                    uri = "",
                ),
            )
            FileItemList(
                modifier = Modifier.padding(Margin.Medium),
                files = files,
                isRemoveButtonVisible = false,
                isCheckboxVisible = true,
                isUidChecked = isFileChecked,
                setUidCheckStatus = setFileCheckStatus,
                onRemoveUid = {},
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun ReceivedScreenPreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(areTransfersEmpty = { true }, isFileChecked = { false }, setFileCheckStatus = { _, _ -> })
        }
    }
}
