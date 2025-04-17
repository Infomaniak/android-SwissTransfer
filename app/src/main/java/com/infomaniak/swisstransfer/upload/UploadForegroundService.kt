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
import android.os.SystemClock
import com.infomaniak.core.ForegroundService
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.coroutines.selects.select
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import splitties.intents.serviceWithoutExtrasSpec
import splitties.systemservices.notificationManager
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class UploadForegroundService : ForegroundService(Companion, redeliverIntentIfKilled = false) {

    companion object : ForegroundService.Companion.NoExtras<UploadForegroundService>(
        intentSpec = serviceWithoutExtrasSpec<UploadForegroundService>(),
        notificationId = NotificationsUtils.Ids.OngoingUpload
    ) {
        val uploadStateFlow: StateFlow<UploadState?>
        val pickedFilesFlow: StateFlow<List<PickedFile>>
        val isHandlingPickedFilesFlow: StateFlow<Boolean>

        private val startSignal = Channel<StartUploadRequest>()

        private val cancelTransferSignals = Channel<Unit>()
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

        suspend fun cancelUpload() {
            cancelTransferSignals.send(Unit)
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
    internal lateinit var notificationsUtils: NotificationsUtils

    @Inject
    internal lateinit var uploadSessionManager: UploadSessionManager

    override fun getStartNotification(intent: Intent, isReDelivery: Boolean): Notification {
        return notificationsUtils.buildTransferDraftNotification()
    }

    private suspend fun keepNotificationUpToDate() {
        uploadStateFlow.rateLimit(minInterval = 1.seconds / 5).collect { state ->
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
                    notificationsUtils.buildUploadFailedNotification(canRetry = false)
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

    override suspend fun run(): Nothing = Dispatchers.Default {
        launch { keepNotificationUpToDate() }
        repeatWhileActive {
            val startRequest: StartUploadRequest = startSignal.receive()
            uploadSessionManager.handleNewTransfer(
                startRequest = startRequest,
                uploadState = _state,
                cancelTransferSignals = cancelTransferSignals,
                shouldRetrySignals = shouldRetrySignals,
            )
        }
    }
}

private fun <T> Flow<T>.rateLimit(minInterval: Duration): Flow<T> = channelFlow {
    var lastEmitElapsedNanos = 0L
    collectLatest { newValue ->
        val nanosSinceLastEmit = SystemClock.elapsedRealtimeNanos() - lastEmitElapsedNanos
        val timeSinceLastEmit = nanosSinceLastEmit.nanoseconds
        val timeToWait = minInterval - timeSinceLastEmit.coerceAtMost(minInterval)
        delay(timeToWait)
        // We use `sendAtomic` instead of just `send` to ensure `lastEmitElapsedNanos` is updated
        // if the value was successfully sent while cancellation was happening.
        // That ensures we count every value being sent, and respect the desired rate limiting.
        // FYI, as its documentation states, `send` can deliver the value to the receiver,
        // and throw a `CancellationException` instead of returning (if coroutine's `Job` gets cancelled).
        sendAtomic(newValue)
        lastEmitElapsedNanos = SystemClock.elapsedRealtimeNanos()
    }
}.buffer(Channel.RENDEZVOUS)


/**
 * [SendChannel.send] can throw a [CancellationException] after the value was sent,
 * which might not be desirable if we want to factor-in whether the value was actually sent.
 *
 * That's why this atomic version exists.
 * It supports cancellation, but if the value is sent, it will return instead, even if it's
 * happening concurrently to cancellation.
 *
 * See [this issue](https://github.com/Kotlin/kotlinx.coroutines/issues/4414) for more details.
 */
private suspend fun <T> SendChannel<T>.sendAtomic(element: T) {
    trySelectAtomically<Unit?>(onCancellation = { null }) {
        onSend(element) {}
    } ?: currentCoroutineContext().ensureActive()
}

/**
 * Always return if a select clause from [builder] was selected,
 * while allowing cancellation, if it happened strictly before one
 * of these clauses could be selected.
 */
private suspend inline fun <R> trySelectAtomically(
    crossinline onCancellation: suspend () -> R,
    crossinline builder: SelectBuilder<R>.() -> Unit
): R? {
    // We connect `cancellationSignal` to the current job, so it can be used as
    // a secondary select clause below, even though cancellation is blocked
    // using `withContext(NonCancellable)`.
    // The atomic behavior of `select` allows us to get the desired behavior.
    val cancellationSignal = Job(parent = currentCoroutineContext().job)
    try {
        return withContext(NonCancellable) {
            select {
                builder() // We need to be biased towards this/these clause(s), so it comes first.
                cancellationSignal.onJoin { onCancellation() }
            }
        }
    } finally {
        cancellationSignal.cancel() // The `builder()` clause could throw, so we need this in the finally block.
    }
}
