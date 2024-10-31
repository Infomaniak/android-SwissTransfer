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

import android.text.format.Formatter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.infomaniak.core2.FORMAT_DATE_SIMPLE
import com.infomaniak.core2.FORMAT_DATE_TITLE
import com.infomaniak.core2.format
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TextDotText
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import java.util.Calendar
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransferItem(transfer: TransferUi, onClick: () -> Unit) {

    val createdDate = Date(transfer.createdDateTimestamp).format(FORMAT_DATE_TITLE)
    val expirationDate = Date(transfer.expirationDateTimestamp)
    val remainingDays = transfer.expiresInDays
    val remainingDownloads = transfer.downloadLeft
    val uploadedSize = Formatter.formatShortFileSize(LocalContext.current, transfer.sizeUploaded)
    val files = transfer.files
    val filesCount = files.count()
    val (expiryText, expiryColor) = when {
        remainingDays < 0 -> {
            stringResource(R.string.expiredThe, expirationDate.format(FORMAT_DATE_SIMPLE)) to
                    SwissTransferTheme.materialColors.error
        }
        remainingDownloads == 0 -> {
            "Transfert expiré (TODO)" to SwissTransferTheme.materialColors.error
        }
        else -> {
            stringResource(R.string.expiresIn, remainingDays) to SwissTransferTheme.colors.secondaryTextColor
        }
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.surfaceContainerHighest),
        shape = CustomShapes.SMALL,
    ) {
        Row(
            modifier = Modifier.padding(Margin.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(modifier = Modifier.weight(1.0f)) {

                Text(
                    text = createdDate,
                    style = SwissTransferTheme.typography.bodyMedium,
                    color = SwissTransferTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.MiddleEllipsis,
                )

                Spacer(modifier = Modifier.height(Margin.Mini))
                TextDotText(
                    firstText = { uploadedSize },
                    secondText = { expiryText },
                    optionalSecondTextColor = expiryColor,
                )

                Spacer(modifier = Modifier.height(Margin.Mini))
                ContextualFlowRow(
                    itemCount = filesCount,
                    maxLines = 1,
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                    overflow = ContextualFlowRowOverflow.expandIndicator { TransferFilePreview(remainingFilesCount = totalItemCount - shownItemCount) },
                ) { index ->
                    TransferFilePreview(file = files[index])
                }
            }

            Spacer(modifier = Modifier.width(Margin.Medium))
            Icon(
                imageVector = AppIcons.ChevronRightThick,
                contentDescription = null,
                modifier = Modifier.size(Margin.Medium),
                tint = SwissTransferTheme.colors.iconColor,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            TransferItem(
                transfer = TransferUi(
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
                    files = listOf(
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
                    ),
                ),
                onClick = {},
            )
        }
    }
}
