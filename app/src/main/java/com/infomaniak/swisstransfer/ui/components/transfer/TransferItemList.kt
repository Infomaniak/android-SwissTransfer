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
package com.infomaniak.swisstransfer.ui.components.transfer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Composable
fun TransferItemList(
    modifier: Modifier = Modifier,
    transfers: List<TransferUi>,
    getSelectedTransferUuid: () -> String?,
    onClick: (TransferUi) -> Unit,
) {

    val selectedTransferUuid = getSelectedTransferUuid()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Margin.Medium),
        contentPadding = PaddingValues(top = Margin.Mini),
    ) {

        item {
            Text(
                text = stringResource(R.string.receivedFilesTitle),
                style = SwissTransferTheme.typography.h1,
            )
        }

        items(
            count = transfers.count(),
            key = { transfers[it].uuid },
            contentType = { transfers[it] },
            itemContent = {
                val transfer = transfers[it]
                TransferItem(
                    transfer = transfer,
                    isSelected = { selectedTransferUuid == transfer.uuid },
                    onClick = { onClick(transfer) },
                )
            },
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {

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

    SwissTransferTheme {
        Surface {
            TransferItemList(
                transfers = transfers,
                getSelectedTransferUuid = { null },
                onClick = {},
            )
        }
    }
}
