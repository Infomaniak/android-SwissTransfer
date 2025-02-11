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
package com.infomaniak.swisstransfer.upload

import android.app.Notification
import android.content.Intent
import android.net.Uri
import com.infomaniak.core.ForegroundService
import com.infomaniak.core.tryCompletingWhileTrue
import com.infomaniak.core.withPartialWakeLock
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import splitties.init.appCtx
import splitties.intents.serviceWithoutExtrasSpec

class UploadForegroundService : ForegroundService(Companion, redeliverIntentIfKilled = true) {

    companion object : ForegroundService.Companion.NoExtras<UploadForegroundService>(
        intentSpec = serviceWithoutExtrasSpec<UploadForegroundService>(),
        notificationId = 0
    ) {
        val urisToImport = MutableStateFlow<List<Uri>>(emptyList())

        val state: StateFlow<UploadState?>

        private val _state = MutableStateFlow<UploadState?>(value = null).also {
            state = it.asStateFlow()
        }
    }

    override fun getStartNotification(intent: Intent, isReDelivery: Boolean): Notification {
        TODO("Show 'Transfer draft' 'Continue where you left off'")
    }

    override suspend fun run() {
        //TODO: Wait for start upload signal.
        //TODO: Get the list of Uris to upload
        val uploadSession = createUploadSession()
        isInternetConnectedFlow.mapLatest { isInternetConnected ->
            if (isInternetConnected.not()) awaitCancellation()
            withPartialWakeLock {
                //TODO: upload, with retries.
            }
        }
    }

    private suspend fun createUploadSession() {
        val uploadManager = EntryPointAccessors.fromApplication<UploadManager>(appCtx)
        TODO("Create the upload container (aka. initUpload), bypassing the Realm db")
        return isInternetConnectedFlow.tryCompletingWhileTrue {
            UploadSession(
                uploadManager = uploadManager,
                fileChunkSizeManager = fileChunkSizeManager
            )
        }
    }

    private val isInternetConnectedFlow: SharedFlow<Boolean> = TODO()
}

private const val EXPECTED_CHUNK_SIZE = 50L * 1_024 * 1_024 // 50 MB
private const val MAX_CHUNK_COUNT = (FileUtils.MAX_FILES_SIZE / EXPECTED_CHUNK_SIZE).toInt()

private val fileChunkSizeManager = FileChunkSizeManager(
    chunkMinSize = EXPECTED_CHUNK_SIZE,
    chunkMaxSize = EXPECTED_CHUNK_SIZE,
    maxChunkCount = MAX_CHUNK_COUNT,
)
