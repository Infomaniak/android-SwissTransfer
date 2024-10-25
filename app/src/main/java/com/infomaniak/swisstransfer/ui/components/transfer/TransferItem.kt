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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.FORMAT_DATE_TITLE
import com.infomaniak.swisstransfer.ui.utils.format
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransferItem(
    transfer: TransferUi,
    onClick: () -> Unit,
) {

    val date = Date(transfer.createdDateTimestamp).format(FORMAT_DATE_TITLE)
    val size = Formatter.formatShortFileSize(LocalContext.current, transfer.sizeUploaded)
    val expiry = transfer.expiresInDays
    val files = transfer.files
    val itemCount = files.count()

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.surfaceContainerHighest),
        shape = CustomShapes.SMALL,
    ) {
        Row(
            modifier = Modifier.padding(Margin.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                modifier = Modifier.weight(1.0f),
            ) {

                Text(
                    text = date,
                    style = SwissTransferTheme.typography.bodyMedium,
                    color = SwissTransferTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.MiddleEllipsis,
                )

                Spacer(modifier = Modifier.height(Margin.Mini))
                Row {
                    Text(
                        text = size,
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
                    )
                    Text(
                        text = "•",
                        modifier = Modifier.padding(horizontal = Margin.Mini),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
                    )
                    Text(
                        text = stringResource(R.string.expiresIn, expiry),
                        color = SwissTransferTheme.colors.secondaryTextColor,
                        style = SwissTransferTheme.typography.bodySmallRegular,
                    )
                }

                Spacer(modifier = Modifier.height(Margin.Mini))
                ContextualFlowRow(
                    itemCount = itemCount,
                    maxLines = 1,
                    overflow = ContextualFlowRowOverflow.expandIndicator { TransferFilePreview(remainingFilesCount = totalItemCount - shownItemCount) },
                ) { index ->
                    TransferFilePreview(
                        file = files[index],
                        isFirstItem = index == 0,
                    )
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
