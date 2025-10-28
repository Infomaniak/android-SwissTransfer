/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.emptystate

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.previewparameter.ExpiredTransferStatusUiListPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun ExpiredTransferContent(state: ExpirationTransferType) {
    EmptyState(
        content = { Image(imageVector = AppIllus.MascotDead.image(), contentDescription = null) },
        title = stringResource(R.string.transferExpiredTitle),
        description = stringResource(
            when (state) {
                is ExpirationTransferType.ExpiredDate, ExpirationTransferType.Deleted -> R.string.transferExpiredDescription
                is ExpirationTransferType.ExpiredQuota -> R.string.deeplinkTransferExpired
            }
        ),
    )
}


@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(ExpiredTransferStatusUiListPreviewParameterProvider::class) expiredStatus: ExpirationTransferType) {
    SwissTransferTheme {
        Surface {
            ExpiredTransferContent(expiredStatus)
        }
    }
}
