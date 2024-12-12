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

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core2.FORMAT_DATE_SIMPLE
import com.infomaniak.core2.format
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.Bin
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun TransferExpiredBottomSheet(
    expiredTransfer: () -> TransferUi?,
    onDeleteTransferClicked: () -> Unit,
    closeBottomSheet: () -> Unit,
) {

    val transfer = expiredTransfer() ?: return

    val descriptionText = if (transfer.expiresInDays < 0) {
        stringResource(
            R.string.transferExpiredDateReachedDescription,
            transfer.expirationDateTimestamp.toDateFromSeconds().format(FORMAT_DATE_SIMPLE),
        )
    } else {
        stringResource(
            R.string.transferExpiredLimitReachedDescription,
            transfer.downloadLimit,
        )
    }

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.transferExpiredButton),
                imageVector = AppIcons.Bin,
                onClick = {
                    onDeleteTransferClicked()
                    closeBottomSheet()
                },
            )
        },
        imageVector = AppIllus.MascotDead.image(),
        title = stringResource(R.string.transferExpiredTitle),
        description = descriptionText,
    )
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    SwissTransferTheme {
        Surface {
            TransferExpiredBottomSheet(
                expiredTransfer = { transfers.first() },
                onDeleteTransferClicked = {},
                closeBottomSheet = {},
            )
        }
    }
}
