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
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItemList
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.MascotSearching
import com.infomaniak.swisstransfer.ui.screen.main.components.BrandTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.screen.main.received.components.ReceivedEmptyFab
import com.infomaniak.swisstransfer.ui.screen.main.sent.SentViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Composable
fun ReceivedScreen(
    navigateToDetails: (transferId: Int) -> Unit,
    sentViewModel: SentViewModel = hiltViewModel<SentViewModel>(),
) {
    val transfers by sentViewModel.transfers.collectAsStateWithLifecycle()
    val areTransfersEmpty by remember { derivedStateOf { transfers?.isEmpty() == true } }

    ReceivedScreen(areTransfersEmpty = { areTransfersEmpty })
}

@Composable
private fun ReceivedScreen(areTransfersEmpty: () -> Boolean) {
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

            val transfers = listOf(
                TransferUi(
                    uuid = UUID.randomUUID().toString(),
                    createdDateTimestamp = Date().time - 30L * 86_400_000L,
                    expirationDateTimestamp = Calendar.getInstance().apply {
                        time = Date()
                        set(Calendar.DATE, get(Calendar.DATE) + 1)
                    }.time.time,
                    sizeUploaded = 57_689_032L,
                    downloadLimit = 10,
                    downloadLeft = 8,
                    message = "Coucou c'est moi le message de description du transfert.",
                    files = files,
                ),
                TransferUi(
                    uuid = UUID.randomUUID().toString(),
                    createdDateTimestamp = Date().time - 5L * 86_400_000L,
                    expirationDateTimestamp = Calendar.getInstance().apply {
                        time = Date()
                        set(Calendar.DATE, get(Calendar.DATE) + 4)
                    }.time.time,
                    sizeUploaded = 89_723_143L,
                    downloadLimit = 2,
                    downloadLeft = 2,
                    message = null,
                    files = files,
                ),
                TransferUi(
                    uuid = UUID.randomUUID().toString(),
                    createdDateTimestamp = Date().time - 0.5f.toLong() * 86_400_000L,
                    expirationDateTimestamp = Calendar.getInstance().apply {
                        time = Date()
                        set(Calendar.DATE, get(Calendar.DATE) + 7)
                    }.time.time,
                    sizeUploaded = 237_866_728L,
                    downloadLimit = 420_069,
                    downloadLeft = 402_690,
                    message = "3ème transfert. RAS.",
                    files = files,
                ),
            )

            TransferItemList(
                modifier = Modifier.padding(Margin.Medium),
                transfers = transfers,
                onClick = { /* TODO */ }
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun ReceivedScreenPreview() {
    SwissTransferTheme {
        Surface {
            ReceivedScreen(areTransfersEmpty = { true })
        }
    }
}
