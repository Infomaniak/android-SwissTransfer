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
package com.infomaniak.swisstransfer.ui.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
import java.util.UUID

class GroupedTransfersPreviewParameterProvider : PreviewParameterProvider<GroupedTransfers> {
    override val values: Sequence<GroupedTransfers> = sequenceOf(transfersPreviewData.groupBySection(today.toLocalDate()))
}

private const val today = 1736411401L

val transfersPreviewData = listOf(
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = today - 0.5f.toLong() * 86_400L,
        expirationDateTimestamp = today + 3 * 86_400L,
        sizeUploaded = 237_866_728L,
        downloadLimit = 250,
        downloadLeft = 123,
        message = "3ème transfert. RAS.",
        password = "my password",
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = today - 0.6f.toLong() * 86_400L,
        expirationDateTimestamp = today + 3 * 86_400L,
        sizeUploaded = 237_866_728L,
        downloadLimit = 250,
        downloadLeft = 123,
        message = null,
        password = "my password",
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = today - 5L * 86_400L,
        expirationDateTimestamp = today + 5 * 86_400L,
        sizeUploaded = 89_723_143L,
        downloadLimit = 20,
        downloadLeft = 0,
        message = null,
        password = null,
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = today - 30L * 86_400L,
        expirationDateTimestamp = today - 4 * 86_400L,
        sizeUploaded = 57_689_032L,
        downloadLimit = 1,
        downloadLeft = 1,
        message = "Coucou c'est moi le message de description du transfert.",
        password = "password",
        files = filesPreviewData,
    ),
)
