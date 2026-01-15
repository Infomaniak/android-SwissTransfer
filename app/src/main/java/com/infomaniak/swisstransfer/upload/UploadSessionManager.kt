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
@file:OptIn(ExperimentalSplittiesApi::class, ExperimentalCoroutinesApi::class)

package com.infomaniak.swisstransfer.upload

import androidx.compose.runtime.LongState
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.mutableLongStateOf
import androidx.work.WorkManager
import com.infomaniak.core.common.cancellable
import com.infomaniak.core.common.withPartialWakeLock
import com.infomaniak.core.network.NetworkAvailability
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.managers.InMemoryUploadManager
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.multiplatform_swisstransfer.network.exceptions.NetworkException
import com.infomaniak.multiplatform_swisstransfer.utils.FileUtils
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ThumbnailsLocalStorage
import com.infomaniak.swisstransfer.ui.utils.NotificationsUtils
import com.infomaniak.swisstransfer.upload.UploadForegroundService.Companion.removeAllFiles
import com.infomaniak.swisstransfer.upload.UploadForegroundService.Companion.uploadStateFlow
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Uploading.Status
import com.infomaniak.swisstransfer.upload.UploadState.Retry
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.yield
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import javax.inject.Inject
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

class UploadSessionManager @Inject constructor(
    private val uploadManager: InMemoryUploadManager,
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val notificationsUtils: NotificationsUtils,
    private val uploadSessionStarter: UploadSessionStarter,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    private val workManager: WorkManager,
) {

    suspend fun handleNewTransfer(
        startRequestWithTheoreticalSizes: StartUploadRequest,
        uploadState: MutableStateFlow<UploadState?>,
        cancelTransferSignals: Channel<Unit>,
        shouldRetrySignals: Channel<Boolean>,
    ) {
        @Suppress("VariableNeverRead") // Only here for the setter.
        var currentState by uploadState::value

        @Suppress("AssignedValueIsNeverRead") // Updates are sent via uploadState.
        val startRequestWithExactSize: StartUploadRequest = tryCompletingUnlessCancelled(
            waitForCancellationSignal = { cancelTransferSignals.receive() },
            valueIfCancelled = null
        ) {
            repeatWhileActive retryLoop@{
                return@tryCompletingUnlessCancelled runCatching {
                    val checkingProgressState = mutableLongStateOf(0)
                    currentState = UploadState.Ongoing.CheckingFiles(
                        info = startRequestWithTheoreticalSizes.info,
                        progressState = checkingProgressState,
                    )
                    startRequestWithTheoreticalSizes.withExactSizes(
                        progressObserver = { _, _, getTotal ->
                            repeatWhileActive {
                                checkingProgressState.longValue = getTotal()
                                delay(0.1.seconds)
                            }
                        }
                    )
                }.cancellable().getOrElse { t ->
                    SentryLog.e(TAG, "Failed to measure the exact size of a picked file", t)
                    val newState: UploadState = when (t) {
                        is FileSizeExceededException -> UploadState.Failure.SizeExceeded
                        else -> Retry.OtherIssue(info = startRequestWithTheoreticalSizes.info, t = t)
                    }
                    currentState = newState
                    val shouldRetry = shouldRetrySignals.receive() && newState is Retry
                    if (shouldRetry) return@retryLoop else return@tryCompletingUnlessCancelled null
                }
            }
        } ?: run {
            currentState = null
            return
        }

        handleNewTransferWithExactSizes(
            startRequest = startRequestWithExactSize,
            uploadState = uploadState,
            cancelTransferSignals = cancelTransferSignals,
            shouldRetrySignals = shouldRetrySignals
        )
    }

    @Suppress("AssignedValueIsNeverRead") // Updates are sent via uploadState.
    private suspend fun handleNewTransferWithExactSizes(
        startRequest: StartUploadRequest,
        uploadState: MutableStateFlow<UploadState?>,
        cancelTransferSignals: Channel<Unit>,
        shouldRetrySignals: Channel<Boolean>,
    ) {
        @Suppress("VariableNeverRead") // Only here for the setter.
        var currentState by uploadState::value

        suspend fun shouldRetry(): Boolean = shouldRetrySignals.receive()

        currentState = UploadState.Ongoing.CheckingAppIntegrity(startRequest.info)

        val (request, destination) = tryCompletingUnlessCancelled(
            waitForCancellationSignal = { cancelTransferSignals.receive() },
            valueIfCancelled = null
        ) {
            startUploadSession(
                startRequest = startRequest,
                updateState = { currentState = it },
                shouldRetry = ::shouldRetry
            ) ?: return@tryCompletingUnlessCancelled null
        } ?: run {
            currentState = null
            return
        }

        val transferUuid = tryCompletingUnlessCancelled(
            waitForCancellationSignal = { cancelTransferSignals.receive() },
            valueIfCancelled = null
        ) {
            TransferUploader(
                startRequest = startRequest,
                uploadManager = uploadManager,
                transferManager = transferManager,
                fileChunkSizeManager = fileChunkSizeManager,
                request = request,
                destination = destination,
                thumbnailsLocalStorage = thumbnailsLocalStorage,
            ).getUuidFromUploadWithRetries(
                uploadState = uploadState,
                shouldRetry = ::shouldRetry,
            )
        }

        currentState = if (transferUuid != null) {
            val url = sharedApiUrlCreator.shareTransferUrl(transferUuid)
            val transferType = startRequest.info.type

            removeAllFiles()

            notificationsUtils.uploadSucceeded(
                transferType = transferType,
                transferUuid = transferUuid,
                transferUrl = url,
            )

            UploadState.Complete(
                transferType = transferType,
                transferUuid = transferUuid,
                transferUrl = url,
            )
        } else {
            AbandonedTransferCleanupWorker.schedule(workManager, destination.container.uuid).onFailure {
                SentryLog.wtf(TAG, "Failed to schedule AbandonedTransferCleanupWorker!", it)
            }
            null
        }
    }

    /**
     * @return The uuid of the transfer if it completed successfully, null otherwise
     */
    private suspend fun TransferUploader.getUuidFromUploadWithRetries(
        uploadState: MutableStateFlow<UploadState?>,
        shouldRetry: suspend () -> Boolean,
    ): String? {
        repeatWhileActive retryLoop@{
            @Suppress("VariableNeverRead") // Only here for the setter.
            var currentState by uploadState::value

            @Suppress("AssignedValueIsNeverRead") // Updates are sent via uploadState.
            runCatching {
                return tryCompletingWithInternet(
                    withoutInternet = {
                        currentState = uploadStateFlow.filterIsInstance<UploadState.Ongoing.Uploading>()
                            .first()
                            .copy(status = Status.WaitingForInternet)
                        awaitCancellation()
                    },
                ) {
                    withPartialWakeLock(appName = "SwissTransfer", tagSuffix = "upload") {
                        uploadRemainderOrThrow(updateState = { currentState = it })
                    }
                }
            }.cancellable().onFailure { throwable ->
                currentState = when (throwable) {
                    is NetworkException -> Retry.NetworkIssue(startRequest.info)
                    else -> {
                        SentryLog.e(TAG, "Upload FAILURE", throwable)
                        Retry.OtherIssue(startRequest.info, throwable)
                    }
                }

                if (!shouldRetry()) return null
            }
        }
    }

    private suspend fun startUploadSession(
        startRequest: StartUploadRequest,
        updateState: (newState: UploadState?) -> Unit,
        shouldRetry: suspend () -> Boolean,
    ): UploadSessionStarter.Result.Success? = repeatWhileActive {

        val info = startRequest.info
        updateState(UploadState.Ongoing.CheckingAppIntegrity(info))

        val newState: UploadState = when (val result = uploadSessionStarter.tryStarting(startRequest)) {
            is UploadSessionStarter.Result.Success -> return result
            UploadSessionStarter.Result.EmailValidationRequired -> Retry.EmailValidationRequired(info)
            UploadSessionStarter.Result.AppIntegrityIssue -> UploadState.Failure.AppIntegrityIssue
            UploadSessionStarter.Result.NetworkIssue -> Retry.NetworkIssue(info)
            is UploadSessionStarter.Result.OtherIssue -> Retry.OtherIssue(info, result.t)
            UploadSessionStarter.Result.RestrictedLocation -> UploadState.Failure.RestrictedLocation
        }

        updateState(newState)

        // We are protecting against any improper UI implementation, by checking retrying was expected.
        if (shouldRetry() && newState is Retry) {
            return@repeatWhileActive
        } else {
            updateState(null)
            return null
        }
    }

    private suspend fun <R> tryCompletingWithInternet(
        withoutInternet: suspend () -> R,
        block: suspend () -> R
    ): R? {
        return isInternetConnectedFlow.mapLatest { isInternetConnected ->
            if (isInternetConnected) block() else withoutInternet()
        }.first()
    }

    private suspend fun <R> tryCompletingUnlessCancelled(
        waitForCancellationSignal: suspend () -> Unit,
        valueIfCancelled: R,
        block: suspend () -> R
    ): R? = raceOf({
        block()
    }, {
        waitForCancellationSignal()
        valueIfCancelled
    })

    private val isInternetConnectedFlow: SharedFlow<Boolean> = NetworkAvailability(
        context = appCtx
    ).isNetworkAvailable.conflate().distinctUntilChanged().shareIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(),
        replay = 1
    )
}

private const val EXPECTED_CHUNK_SIZE = 50L * 1_024 * 1_024 // 50 MB
private const val MAX_CHUNK_COUNT = (FileUtils.MAX_FILES_SIZE / EXPECTED_CHUNK_SIZE).toInt()

private const val TAG = "UploadSessionManager"

private val fileChunkSizeManager = FileChunkSizeManager(
    chunkMinSize = EXPECTED_CHUNK_SIZE,
    chunkMaxSize = EXPECTED_CHUNK_SIZE,
    maxChunkCount = MAX_CHUNK_COUNT,
)

private suspend fun StartUploadRequest.withExactSizes(
    progressObserver: FilesCheckProgressObserver
): StartUploadRequest {
    val pickedFilesWithExactSizes = files.measureSizes(progressObserver = progressObserver)
    return copy(
        files = pickedFilesWithExactSizes,
        info = info.copy(
            totalSize = pickedFilesWithExactSizes.sumOf { it.size }
        )
    )
}

private class FileSizeExceededException(message: String) : Exception(message)

private fun interface FilesCheckProgressObserver {
    suspend fun handleProgression(
        perFileProgress: Map<PickedFile, LongState>,
        theoreticalTotalBytes: Long,
        getCurrentTotalReadBytes: () -> Long
    ): Nothing
}

@Throws(FileSizeExceededException::class)
@OptIn(ExperimentalAtomicApi::class)
private suspend fun List<PickedFile>.measureSizes(
    maxSize: Long = FileUtils.MAX_FILES_SIZE,
    progressObserver: FilesCheckProgressObserver = FilesCheckProgressObserver { _, _, _ -> awaitCancellation() },
): List<PickedFile> {
    val pickedFilesWithCountedByteTotals = associateWith { mutableLongStateOf(0) }
    val total = AtomicLong(0)
    return raceOf({
        measureFilesSizesInParallel(
            files = this@measureSizes,
            pickedFilesWithCountedByteTotals = pickedFilesWithCountedByteTotals,
            maxSize = maxSize,
            total = total
        )
    }, {
        progressObserver.handleProgression(
            perFileProgress = pickedFilesWithCountedByteTotals,
            theoreticalTotalBytes = this@measureSizes.sumOf { it.size },
            getCurrentTotalReadBytes = { total.load() }
        )
    })
}

@Throws(FileSizeExceededException::class)
@OptIn(ExperimentalAtomicApi::class)
private suspend fun measureFilesSizesInParallel(
    files: List<PickedFile>,
    pickedFilesWithCountedByteTotals: Map<PickedFile, MutableLongState>,
    maxSize: Long,
    total: AtomicLong
): List<PickedFile> = coroutineScope {
    val counter = InputStreamCounter()
    val updateInterval = ((1.seconds / 60.0) * files.size).coerceAtLeast(1.seconds)
    pickedFilesWithCountedByteTotals.map { (pickedFile, totalBytesState) ->
        async(Dispatchers.IO) {
            var lastYield = TimeSource.Monotonic.markNow()
            val exactSize = counter.fileSizeFor(
                uri = pickedFile.uri,
                onTotalBytesUpdate = { skippedBytes, totalBytes ->
                    if (totalBytes > maxSize) {
                        throw FileSizeExceededException("File exceeded the size limit. Uri: ${pickedFile.uri}")
                    }
                    if (total.addAndFetch(skippedBytes.toLong()) > maxSize) {
                        throw FileSizeExceededException("Files are exceeding the size limit")
                    }
                    if (lastYield.elapsedNow() >= updateInterval) {
                        totalBytesState.longValue = totalBytes
                        lastYield = TimeSource.Monotonic.markNow()
                        yield()
                    } else {
                        ensureActive()
                    }
                }
            )
            pickedFile.copy(size = exactSize)
        }
    }.awaitAll()
}
