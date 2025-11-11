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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.compose.preview.PreviewSmallWindow
import com.infomaniak.core.utils.FORMAT_DATE_FULL
import com.infomaniak.core.utils.format
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.previewparameter.ExpiredTransferStatusUiListPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.TransferError.Expired
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import java.util.Date

@Composable
fun ExpiredTransferContent(transferErrorType: Expired) {
    LaunchedEffect(Unit) {
        MatomoSwissTransfer.trackScreen(
            when (transferErrorType) {
                is Expired.ByDate, Expired.Deleted -> MatomoScreen.DateExpiredTransfer
                is Expired.ByQuota -> MatomoScreen.DownloadQuotasExpiredTransfer
            }
        )
    }

    EmptyState(
        content = { Image(imageVector = AppIllus.MascotDead.image(), contentDescription = null) },
        title = stringResource(R.string.transferExpiredTitle),
        description = when (transferErrorType) {
            is Expired.ByDate -> {
                if (transferErrorType.date != null) {
                    val formatedDate = Date(transferErrorType.date).format(FORMAT_DATE_FULL)
                    stringResource(R.string.transferExpiredDateReachedDescription, formatedDate)
                } else {
                    stringResource(R.string.transferExpiredDescription)
                }
            }
            Expired.Deleted -> stringResource(R.string.transferExpiredDescription)
            is Expired.ByQuota -> {
                if (transferErrorType.downloadLimit != null) {
                    pluralStringResource(
                        R.plurals.transferExpiredLimitReachedDescription,
                        transferErrorType.downloadLimit,
                        transferErrorType.downloadLimit
                    )
                } else {
                    stringResource(R.string.deeplinkTransferExpired)
                }
            }
        },
    )
}


@PreviewSmallWindow
@Composable
private fun Preview(@PreviewParameter(ExpiredTransferStatusUiListPreviewParameterProvider::class) expiredStatus: Expired) {
    SwissTransferTheme {
        Surface {
            ExpiredTransferContent(expiredStatus)
        }
    }
}
