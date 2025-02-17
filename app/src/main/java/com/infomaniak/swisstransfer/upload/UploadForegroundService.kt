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
@file:OptIn(ExperimentalCoroutinesApi::class)

package com.infomaniak.swisstransfer.upload

import android.app.Notification
import android.content.Intent
import android.net.Uri
import com.infomaniak.core.ForegroundService
import com.infomaniak.core.tryCompletingWhileTrue
import com.infomaniak.core.withPartialWakeLock
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.FileToUploadMetadata
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.upload.UploadSessionRequest
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.invoke
import splitties.intents.serviceWithoutExtrasSpec
import javax.inject.Inject

@AndroidEntryPoint
class UploadForegroundService : ForegroundService(Companion, redeliverIntentIfKilled = true) {

    companion object : ForegroundService.Companion.NoExtras<UploadForegroundService>(
        intentSpec = serviceWithoutExtrasSpec<UploadForegroundService>(),
        notificationId = NotificationsUtils.Ids.LastUpload
    ) {
        val state: StateFlow<UploadState?>
        val pickedFilesFlow: StateFlow<List<PickedFile>>
        val isHandlingPickedFilesFlow: StateFlow<Boolean>

        fun addFiles(uris: List<Uri>) = pickedFilesExtractor.addUris(uris)

        suspend fun startUpload(signal: StartUploadSignal) {
            startUploadChannel.send(signal)
        }

        private val pickedFilesExtractor: PickedFilesExtractor = PickedFilesExtractorImpl().also {
            pickedFilesFlow = it.pickedFilesFlow
            isHandlingPickedFilesFlow = it.isHandlingUrisFlow
        }

        private val startUploadChannel = Channel<StartUploadSignal>()
        private val _state = MutableStateFlow<UploadState?>(value = null).also {
            state = it.asStateFlow()
        }
    }

    @Inject
    internal lateinit var uploadManager: UploadManager

    @Inject
    internal lateinit var sharedApiUrlCreator: SharedApiUrlCreator

    @Inject
    internal lateinit var notificationUtils: NotificationsUtils

    override fun getStartNotification(intent: Intent, isReDelivery: Boolean): Notification {
        TODO("Show 'Transfer draft' 'Continue where you left off'")
    }

    override suspend fun run() = Dispatchers.Default {
        val startSignal = startUploadChannel.receive()
        val uploader: TransferUploader = createUploadSession(
            uploadManager = uploadManager,
            startParams = startSignal,
            pickedFiles = pickedFilesFlow.value,
            attestationHeaderName = startSignal.attestationHeaderName,
            attestationToken = startSignal.attestationHeaderName
        )
        isInternetConnectedFlow.mapLatest { isInternetConnected ->
            if (isInternetConnected.not()) awaitCancellation()
            withPartialWakeLock {
                uploader.uploadAllRemainingWithRetries()
            }
        }.first()
    }

    private suspend fun createUploadSession(
        uploadManager: UploadManager,
        startParams: StartUploadSignal,
        pickedFiles: List<PickedFile>,
        attestationHeaderName: String,
        attestationToken: String,
    ): TransferUploader {
        val request = startParams.request.toUploadSessionRequest(pickedFiles.map { it.toFileUploadMetaData() })
        return isInternetConnectedFlow.tryCompletingWhileTrue {
            //TODO: Run code below with retries.
            val uploadDestination = uploadManager.startUploadSession(
                request = request,
                attestationHeaderName = attestationHeaderName,
                attestationToken = attestationToken
            )
            TransferUploader(
                uploadManager = uploadManager,
                fileChunkSizeManager = fileChunkSizeManager,
                request = request,
                destination = uploadDestination,
                pickedFiles = pickedFiles,
            )
        }
    }

    private val isInternetConnectedFlow: SharedFlow<Boolean> = TODO()
}

private fun PickedFile.toFileUploadMetaData(): FileToUploadMetadata {
    return FileToUploadMetadata(
        name = name,
        size = size,
        mimeType = mimeType
    )
}

private fun StartUploadSignal.Request.toUploadSessionRequest(
    filesMetadata: List<FileToUploadMetadata>
): UploadSessionRequest = UploadSessionRequest(
    validityPeriod = validityPeriod,
    authorEmail = authorEmail,
    authorEmailToken = authorEmailToken,
    password = password,
    message = message,
    sizeOfUpload = filesMetadata.sumOf { it.size },
    downloadCountLimit = downloadCountLimit,
    filesCount = filesMetadata.size,
    languageCode = languageCode,
    filesMetadata = filesMetadata,
    recipientsEmails = recipientsEmails
)

private const val EXPECTED_CHUNK_SIZE = 50L * 1_024 * 1_024 // 50 MB
private const val MAX_CHUNK_COUNT = (FileUtils.MAX_FILES_SIZE / EXPECTED_CHUNK_SIZE).toInt()

private val fileChunkSizeManager = FileChunkSizeManager(
    chunkMinSize = EXPECTED_CHUNK_SIZE,
    chunkMaxSize = EXPECTED_CHUNK_SIZE,
    maxChunkCount = MAX_CHUNK_COUNT,
)
