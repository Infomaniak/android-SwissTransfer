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
import com.infomaniak.multiplatform_swisstransfer.common.utils.DateUtils.SECONDS_IN_A_DAY
import com.infomaniak.swisstransfer.ui.screen.main.transfers.GroupedTransfers
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
import kotlinx.datetime.Clock
import java.util.UUID

class GroupedTransfersPreviewParameterProvider : PreviewParameterProvider<GroupedTransfers> {
    override val values: Sequence<GroupedTransfers> = sequenceOf(transfersPreviewData.groupBySection(now.toLocalDate()))
}

private val now get() = Clock.System.now().epochSeconds

val transfersPreviewData = listOf(
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = now - (0.5f * SECONDS_IN_A_DAY).toLong(),
        expirationDateTimestamp = now + 3 * SECONDS_IN_A_DAY,
        sizeUploaded = 237_866_728L,
        downloadLimit = 250,
        downloadLeft = 123,
        message = "3ème transfert. RAS.",
        password = "my password",
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = now - (0.6f * SECONDS_IN_A_DAY).toLong(),
        expirationDateTimestamp = now + 3 * SECONDS_IN_A_DAY,
        sizeUploaded = 237_866_728L,
        downloadLimit = 250,
        downloadLeft = 123,
        message = null,
        password = "my password",
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = now - 5L * SECONDS_IN_A_DAY,
        expirationDateTimestamp = now + 5L * SECONDS_IN_A_DAY,
        sizeUploaded = 89_723_143L,
        downloadLimit = 20,
        downloadLeft = 0,
        message = null,
        password = null,
        files = filesPreviewData,
    ),
    TransferUi(
        uuid = UUID.randomUUID().toString(),
        createdDateTimestamp = now - 30L * SECONDS_IN_A_DAY,
        expirationDateTimestamp = now - 4L * SECONDS_IN_A_DAY,
        sizeUploaded = 57_689_032L,
        downloadLimit = 1,
        downloadLeft = 1,
        message = "Coucou c'est moi le message de description du transfert.",
        password = "password",
        files = filesPreviewData,
    ),
)
