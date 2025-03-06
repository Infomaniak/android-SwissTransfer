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
@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.upload

import android.app.Notification
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.infomaniak.core.ForegroundService
import com.infomaniak.core.cancellable
import com.infomaniak.core.network.NetworkAvailability
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.withPartialWakeLock
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Status
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import splitties.intents.serviceWithoutExtrasSpec
import splitties.systemservices.notificationManager
import javax.inject.Inject

@AndroidEntryPoint
class UploadForegroundService : ForegroundService(Companion, redeliverIntentIfKilled = true) {

    companion object : ForegroundService.Companion.NoExtras<UploadForegroundService>(
        intentSpec = serviceWithoutExtrasSpec<UploadForegroundService>(),
        notificationId = NotificationsUtils.Ids.OngoingUpload
    ) {
        val uploadStateFlow: StateFlow<UploadState?>
        val pickedFilesFlow: StateFlow<List<PickedFile>>
        val isHandlingPickedFilesFlow: StateFlow<Boolean>

        private val startSignal = Channel<StartUploadRequest>()

        private val shouldRetrySignals = Channel<Boolean>()

        private val pickedFilesExtractor: PickedFilesExtractor = PickedFilesExtractorImpl().also {
            pickedFilesFlow = it.pickedFilesFlow
            isHandlingPickedFilesFlow = it.isHandlingUrisFlow
        }

        private val _state = MutableStateFlow<UploadState?>(value = null).also {
            uploadStateFlow = it.asStateFlow()
        }

        private val scope = CoroutineScope(Dispatchers.Default)

        init {
            keepServiceRunningWhileNeeded()
        }

        fun addFiles(uris: List<Uri>) {
            pickedFilesExtractor.addUris(uris)
            // Give the URI permissions to our app process, to ensure we aren't limited by the receiving Activity lifecycle.
            uris.forEach { uri -> appCtx.grantUriPermission(appCtx.packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
        }

        fun removeFiles(uris: List<Uri>) {
            pickedFilesExtractor.removeUris(uris)
        }

        fun removeAllFiles() {
            pickedFilesExtractor.clear()
        }

        suspend fun commitUploadSession(params: NewTransferParams) {
            check(isHandlingPickedFilesFlow.value.not())
            val pickedFiles = pickedFilesFlow.value
            val totalSize = pickedFiles.sumOf { it.size }
            startSignal.send(
                StartUploadRequest(
                    params = params,
                    files = pickedFiles,
                    info = UploadState.Info(
                        authorEmail = params.authorEmail,
                        totalSize = totalSize,
                        type = params.type
                    )
                )
            )
        }

        fun dismissCompleteUpload() {
            if (uploadStateFlow.value is UploadState.Complete) {
                _state.value = null
                notificationManager.cancel(NotificationsUtils.Ids.LastUpload)
            }
        }

        suspend fun retry() {
            shouldRetrySignals.send(true)
        }

        suspend fun giveUp() {
            shouldRetrySignals.send(false)
        }

        private fun keepServiceRunningWhileNeeded() {
            val needsToKeepFileUris = isHandlingPickedFilesFlow.transformLatest { isHandlingFiles ->
                if (isHandlingFiles) emit(true)
                else emitAll(pickedFilesFlow.map { it.isNotEmpty() })
            }
            val shouldRunFlow = uploadStateFlow.transformLatest { state ->
                when (state) {
                    is UploadState.Complete, null -> emitAll(needsToKeepFileUris)
                    else -> emit(true) // Ongoing upload
                }
            }.distinctUntilChanged()
            scope.launch {
                shouldRunFlow.collectLatest { shouldRun ->
                    if (shouldRun) runUntilCancelled() // Will raise the process priority, ensuring we keep access to the Uris.
                }
            }
        }
    }

    @Inject
    internal lateinit var uploadManager: InMemoryUploadManager

    @Inject
    internal lateinit var sharedApiUrlCreator: SharedApiUrlCreator

    @Inject
    internal lateinit var notificationsUtils: NotificationsUtils

    @Inject
    internal lateinit var uploadSessionStarter: UploadSessionStarter

    override fun getStartNotification(intent: Intent, isReDelivery: Boolean): Notification {
        return notificationsUtils.buildTransferDraftNotification()
    }

    private suspend fun keepNotificationUpToDate(): Notification {
        uploadStateFlow.collect { state ->
            val newNotification = when (state) {
                is UploadState.Complete, null -> {
                    // If we're kept running while the upload state is null or complete,
                    // it should be because we have uris of picked/shared files, i.e. a transfer draft.
                    notificationsUtils.buildTransferDraftNotification()
                }
                is UploadState.Ongoing -> buildOngoingNotification(state)
                is UploadState.Retry -> {
                    notificationsUtils.buildUploadFailedNotification(canRetry = true)
                }
                is UploadState.Failure -> {
                    notificationsUtils.buildUploadFailedNotification(canRetry = true)
                }
            }
            updateNotification(newNotification)
        }
    }

    private fun buildOngoingNotification(state: UploadState.Ongoing): Notification = when (state.status) {
        Status.InProgress, Status.Initializing -> notificationsUtils.buildUploadProgressNotification(
            authorEmail = state.info.authorEmail,
            transferType = state.info.type,
            totalBytes = state.info.totalSize,
            uploadedBytes = state.uploadedBytes,
        )
        //TODO[UL-retry]: Show the progress as above but change the message once we support retries.
        Status.WaitingForInternet -> notificationsUtils.buildUploadFailedNotification(canRetry = true)
    }

    private var currentState by _state::value

    override suspend fun run(): Nothing = Dispatchers.Default {
        launch { keepNotificationUpToDate() }
        repeatWhileActive {
            val startRequest: StartUploadRequest = startSignal.receive()
            currentState = UploadState.Ongoing(
                status = Status.Initializing,
                uploadedBytes = 0L,
                info = startRequest.info
            )

            //TODO[UL-retry]: Once we support resuming the upload:
            // 1. Remove this big `isInternetConnectedFlow` thing and move it to just the uploader part.
            // 2. Remove the loop (repeatWhileActive) below.
            // 3. In the uploader, use a function named `uploadAllRemainingWithRetries` or something.
            val transferUuid = isInternetConnectedFlow.mapLatest { isInternetConnected ->
                if (isInternetConnected.not()) {
                    currentState = uploadStateFlow.filterIsInstance<UploadState.Ongoing>().first().copy(
                        status = Status.WaitingForInternet
                    )
                    awaitCancellation()
                }
                repeatWhileActive retryLoop@{
                    val result = startUploadSession(
                        startRequest = startRequest,
                        updateState = { currentState = it }
                    ) ?: return@mapLatest null
                    val uploader = TransferUploader(
                        uploadManager = uploadManager,
                        fileChunkSizeManager = fileChunkSizeManager,
                        request = result.request,
                        destination = result.destination,
                        startRequest = startRequest,
                    )
                    runCatching {
                        withPartialWakeLock(
                            appName = "SwissTransfer",
                            tagSuffix = "upload"
                        ) {
                            uploader.uploadAllOrThrow(updateState = { currentState = it})
                        }
                    }.cancellable().onFailure { t ->
                        currentState = when (t) {
                            is NetworkException -> UploadState.Retry.NetworkIssue(startRequest.info)
                            else -> {
                                SentryLog.e(TAG, "Upload FAILURE", t)
                                UploadState.Retry.OtherIssue(startRequest.info, t)
                            }
                        }
                        if (shouldRetry()) {
                            return@retryLoop
                        } else {
                            return@mapLatest null
                        }
                    }.onSuccess { uuid -> return@mapLatest uuid }
                }
            }.first()
            currentState = if (transferUuid != null) {
                val url = sharedApiUrlCreator.shareTransferUrl(transferUuid)
                val transferType = startRequest.info.type
                removeAllFiles()
                notificationsUtils.uploadSucceeded(
                    transferType = transferType,
                    transferUuid = transferUuid,
                    transferUrl = url
                )
                UploadState.Complete(
                    transferType = transferType,
                    transferUuid = transferUuid,
                    transferUrl = url
                )
            } else {
                //TODO[UL-cancel]: On give up, schedule a worker to cancel the upload
                null
            }
        }
    }

    private suspend fun shouldRetry(): Boolean = shouldRetrySignals.receive()

    private suspend fun startUploadSession(
        startRequest: StartUploadRequest,
        updateState: (newState: UploadState?) -> Unit
    ): UploadSessionStarter.Result.Success? = repeatWhileActive {

        val info = startRequest.info
        updateState(
            UploadState.Ongoing(
                status = Status.Initializing,
                uploadedBytes = 0L,
                info = info,
            )
        )

        val newState: UploadState = when (val result = uploadSessionStarter.tryStarting(startRequest)) {
            is UploadSessionStarter.Result.Success -> return result
            UploadSessionStarter.Result.EmailValidationRequired -> UploadState.Retry.EmailValidationRequired(info)
            UploadSessionStarter.Result.AppIntegrityIssue -> UploadState.Failure.AppIntegrityIssue
            UploadSessionStarter.Result.NetworkIssue -> UploadState.Retry.NetworkIssue(info)
            is UploadSessionStarter.Result.OtherIssue -> UploadState.Retry.OtherIssue(info, result.t)
            UploadSessionStarter.Result.RestrictedLocation -> UploadState.Failure.RestrictedLocation
        }

        updateState(newState)

        // We are protecting against any improper UI implementation, by checking retrying was expected.
        if (shouldRetry() && newState is UploadState.Retry) {
            return@repeatWhileActive
        } else {
            updateState(null)
            return null
        }
    }

    private val isInternetConnectedFlow: SharedFlow<Boolean> = NetworkAvailability(
        context = appCtx
    ).isNetworkAvailable.conflate().distinctUntilChanged().shareIn(
        scope = lifecycleScope,
        started = SharingStarted.Lazily,
        replay = 1
    )
}

private const val EXPECTED_CHUNK_SIZE = 50L * 1_024 * 1_024 // 50 MB
private const val MAX_CHUNK_COUNT = (FileUtils.MAX_FILES_SIZE / EXPECTED_CHUNK_SIZE).toInt()

private const val TAG = "UploadForegroundService"

private val fileChunkSizeManager = FileChunkSizeManager(
    chunkMinSize = EXPECTED_CHUNK_SIZE,
    chunkMaxSize = EXPECTED_CHUNK_SIZE,
    maxChunkCount = MAX_CHUNK_COUNT,
)
