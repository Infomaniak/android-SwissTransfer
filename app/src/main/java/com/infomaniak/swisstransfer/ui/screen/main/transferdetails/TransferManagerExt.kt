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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails

import android.net.Uri
import com.infomaniak.core.DownloadStatus
import com.infomaniak.core.downloadStatusFlow
import com.infomaniak.core.uriFor
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.transformLatest
import splitties.systemservices.downloadManager

@OptIn(ExperimentalCoroutinesApi::class)
fun TransferManager.uriForFile(transfer: TransferUi, file: FileUi): Flow<Uri?> = downloadManagerId(
    transferManager = this,
    transferUuid = transfer.uuid,
    fileUid = file.uid,
).transformLatest { uniqueDownloadId ->
    if (uniqueDownloadId == null) {
        emit(null)
        return@transformLatest
    }
    val uriFlow = downloadManager.downloadStatusFlow(uniqueDownloadId).transformLatest { status ->
        if (status !is DownloadStatus.Complete) {
            emit(null)
            awaitCancellation()
        }
        val uri = downloadManager.uriFor(uniqueDownloadId)
        emit(uri)
    }
    emitAll(uriFlow)
}.distinctUntilChanged()
