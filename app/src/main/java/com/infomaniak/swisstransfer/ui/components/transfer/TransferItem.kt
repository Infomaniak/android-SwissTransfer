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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core2.FORMAT_DATE_TITLE
import com.infomaniak.core2.format
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TextDotText
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ChevronRightThick
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.HumanReadableSizeUtils
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.isExpired

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransferItem(
    transfer: TransferUi,
    isSelected: () -> Boolean,
    onClick: () -> Unit,
) {

    val createdDate = transfer.createdDateTimestamp.toDateFromSeconds().format(FORMAT_DATE_TITLE)
    val uploadedSize = HumanReadableSizeUtils.getHumanReadableSize(LocalContext.current, transfer.sizeUploaded)
    val files = transfer.files
    val (expiryText, expiryColor) = if (transfer.isExpired) {
        stringResource(R.string.transferExpired) to SwissTransferTheme.materialColors.error
    } else {
        stringResource(R.string.expiresIn, transfer.expiresInDays) to SwissTransferTheme.colors.secondaryTextColor
    }
    val border = if (isSelected()) {
        BorderStroke(width = Dimens.BorderWidth, color = SwissTransferTheme.colors.transferListStroke)
    } else {
        null
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = SwissTransferTheme.materialColors.surfaceContainerHighest),
        shape = CustomShapes.SMALL,
        border = border,
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

                Spacer(Modifier.height(Margin.Mini))
                TextDotText(
                    firstText = { uploadedSize },
                    secondText = { expiryText },
                    optionalSecondTextColor = expiryColor,
                )

                Spacer(Modifier.height(Margin.Mini))
                ContextualFlowRow(
                    itemCount = files.count(),
                    maxLines = 1,
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                    overflow = ContextualFlowRowOverflow.expandIndicator { TransferFilePreview(remainingFilesCount = totalItemCount - shownItemCount) },
                ) { index ->
                    TransferFilePreview(file = files[index])
                }
            }

            Spacer(Modifier.width(Margin.Medium))
            Icon(
                imageVector = AppIcons.ChevronRightThick,
                contentDescription = null,
                modifier = Modifier.size(Dimens.SmallIconSize),
                tint = SwissTransferTheme.colors.iconColor,
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            TransferItem(
                transfer = transfers.first(),
                isSelected = { false },
                onClick = {},
            )
        }
    }
}
