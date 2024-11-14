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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components

import android.text.format.Formatter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.TextDotText
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownFile
import com.infomaniak.swisstransfer.ui.images.icons.Clock
import com.infomaniak.swisstransfer.ui.images.icons.FileZip
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun TransferInfo(transfer: TransferUi) {

    val filesCount  by remember(transfer) { derivedStateOf { transfer.files.count() } }
    val downloadedCount by remember(transfer) { derivedStateOf { transfer.downloadLimit - transfer.downloadLeft } }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = AppIcons.FileZip,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Spacer(Modifier.width(Margin.Mini))
        TextDotText(
            firstText = { pluralStringResource(R.plurals.filesCount, filesCount, filesCount) },
            secondText = { Formatter.formatShortFileSize(LocalContext.current, transfer.sizeUploaded) },
            color = SwissTransferTheme.colors.primaryTextColor,
        )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = Margin.Medium))

    IconText(
        icon = AppIcons.Clock,
        text = stringResource(R.string.expiresIn, transfer.expiresInDays),
    )

    HorizontalDivider(modifier = Modifier.padding(vertical = Margin.Medium))

    IconText(
        icon = AppIcons.ArrowDownFile,
        text = stringResource(R.string.downloadedTransferLabel, downloadedCount, transfer.downloadLimit),
    )
}

@Composable
private fun IconText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Margin.Medium),
            imageVector = icon,
            tint = SwissTransferTheme.materialColors.primary,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start = Margin.Mini),
            text = text,
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            Column {
                TransferInfo(transfers.first())
            }
        }
    }
}
