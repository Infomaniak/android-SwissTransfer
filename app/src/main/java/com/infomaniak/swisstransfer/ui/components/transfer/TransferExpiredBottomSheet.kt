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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.Bin
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.FORMAT_DATE_SIMPLE
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.format
import java.util.Date

@Composable
fun TransferExpiredBottomSheet(
    isVisible: () -> Boolean,
    expirationDate: () -> Date?,
    downloadsLimit: () -> Int?,
    onDeleteTransferClicked: () -> Unit,
    closeBottomSheet: () -> Unit,
) {

    if (!isVisible()) return

    val date = expirationDate()
    val descriptionText = if (date != null) {
        stringResource(
            R.string.transferExpiredDateReachedDescription,
            date.format(FORMAT_DATE_SIMPLE),
        )
    } else {
        stringResource(R.string.transferExpiredLimitReachedDescription, downloadsLimit()!!)
    }

    SwissTransferBottomSheet(
        modifier = Modifier,
        onDismissRequest = closeBottomSheet,
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = R.string.transferExpiredButton,
                imageVector = AppIcons.Bin,
                onClick = onDeleteTransferClicked,
            )
        },
    ) {
        Column {
            Spacer(modifier = Modifier.height(Margin.Medium))
            EmptyState(
                icon = AppIllus.MascotDead.image(),
                title = R.string.transferExpiredTitle,
                description = descriptionText,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            TransferExpiredBottomSheet(
                isVisible = { true },
                expirationDate = { Date() },
                downloadsLimit = { 42 },
                onDeleteTransferClicked = {},
                closeBottomSheet = {},
            )
        }
    }
}
