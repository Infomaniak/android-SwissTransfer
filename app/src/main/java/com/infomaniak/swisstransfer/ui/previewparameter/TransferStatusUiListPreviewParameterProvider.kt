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
package com.infomaniak.swisstransfer.ui.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.Deleted
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.ExpiredDate
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.ExpirationTransferType.ExpiredQuota
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.VirusDetected
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.ErrorTransferType.WaitVirusCheck

class TransferStatusUiListPreviewParameterProvider : PreviewParameterProvider<ErrorTransferType> {
    override val values: Sequence<ErrorTransferType> = transferStatusUiPreviewData
}

private val transferStatusUiPreviewData = sequenceOf(
    ExpiredDate,
    ExpiredQuota(),
    ExpiredQuota(downloadLimit = 25),
    WaitVirusCheck,
    VirusDetected
)

class ExpiredTransferStatusUiListPreviewParameterProvider : PreviewParameterProvider<ExpirationTransferType> {
    override val values: Sequence<ExpirationTransferType> = expiredTransferStatusUiPreviewData
}

private val expiredTransferStatusUiPreviewData = sequenceOf(
    ExpiredDate,
    ExpiredQuota(),
    ExpiredQuota(downloadLimit = 25),
    Deleted
)
