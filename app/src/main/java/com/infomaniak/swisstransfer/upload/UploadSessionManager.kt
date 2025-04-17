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
import androidx.compose.runtime.mutableLongStateOf
import com.infomaniak.core.cancellable
import com.infomaniak.core.network.NetworkAvailability
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.withPartialWakeLock
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
import com.infomaniak.swisstransfer.upload.UploadState.Ongoing.Status
import com.infomaniak.swisstransfer.upload.UploadState.Retry
import com.infomaniak.swisstransfer.workers.FileChunkSizeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.yield
import splitties.coroutines.raceOf
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import javax.inject.Inject
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.experimental.ExperimentalTypeInference
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource
import kotlin.time.measureTime

class UploadSessionManager @Inject constructor(
    private val uploadManager: InMemoryUploadManager,
    private val transferManager: TransferManager,
    private val sharedApiUrlCreator: SharedApiUrlCreator,
    private val notificationsUtils: NotificationsUtils,
    private val uploadSessionStarter: UploadSessionStarter,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
) {

    suspend fun handleNewTransferWithApproximateSize(
        startRequest: StartUploadRequest,
        uploadState: MutableStateFlow<UploadState?>,
        cancelTransferSignals: Channel<Unit>,
        shouldRetrySignals: Channel<Boolean>,
    ) {
        var currentState by uploadState::value

        val startRequestWithExactSize: StartUploadRequest = tryCompletingUnlessCancelled(
            waitForCancellationSignal = { cancelTransferSignals.receive() },
            valueIfCancelled = null
        ) {
            repeatWhileActive retryLoop@{
                return@tryCompletingUnlessCancelled runCatching {
                    currentState = UploadState.Ongoing(
                        status = Status.Initializing.CheckingFiles,
                        uploadedBytes = 0L,
                        info = startRequest.info
                    )
                    startRequest.withExactSizes()
                }.cancellable().getOrElse { t ->
                    SentryLog.e(TAG, "Failed to measure the exact size of a picked file", t)
                    val newState: UploadState = when (t) {
                        is FileSizeExceededException -> UploadState.Failure.SizeExceeded
                        else -> Retry.OtherIssue(info = startRequest.info, t = t)
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

    private suspend fun handleNewTransferWithExactSizes(
        startRequest: StartUploadRequest,
        uploadState: MutableStateFlow<UploadState?>,
        cancelTransferSignals: Channel<Unit>,
        shouldRetrySignals: Channel<Boolean>,
    ) {
        var currentState by uploadState::value

        suspend fun shouldRetry(): Boolean = shouldRetrySignals.receive()

        currentState = UploadState.Ongoing(
            status = Status.Initializing,
            uploadedBytes = 0L,
            info = startRequest.info,
        )

        val transferUuid = tryCompletingUnlessCancelled(
            waitForCancellationSignal = { cancelTransferSignals.receive() },
            valueIfCancelled = null
        ) {
            val (request, destination) = startUploadSession(
                startRequest = startRequest,
                updateState = { currentState = it },
                shouldRetry = ::shouldRetry
            ) ?: return@tryCompletingUnlessCancelled null

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
            //TODO[UL-cancel]: On give up, schedule a worker to cancel the upload
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
            var currentState by uploadState::value

            runCatching {
                return tryCompletingWithInternet(
                    withoutInternet = {
                        currentState = uploadStateFlow.filterIsInstance<UploadState.Ongoing>()
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
        updateState(
            UploadState.Ongoing(
                status = Status.Initializing,
                uploadedBytes = 0L,
                info = info,
            )
        )

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

private suspend fun StartUploadRequest.withExactSizes(): StartUploadRequest {
    val pickedFilesWithExactSizes = measureSizes(files)
    return copy(
        files = pickedFilesWithExactSizes,
        info = info.copy(
            totalSize = pickedFilesWithExactSizes.sumOf { it.size }
        )
    )
}

private class FileSizeExceededException(message: String) : Exception(message)

@Throws(FileSizeExceededException::class)
@OptIn(ExperimentalAtomicApi::class)
private suspend fun measureSizes(
    files: List<PickedFile>,
    maxSize: Long = FileUtils.MAX_FILES_SIZE,
    handleTotalsProgression: suspend (Map<PickedFile, LongState>) -> Nothing = { awaitCancellation() },
): List<PickedFile> {
    val pickedFilesWithCountedByteTotals = files.associateWith { mutableLongStateOf(0) }
    return raceOf({
        val counter = InputStreamCounter()
        val total = AtomicLong(0)
        val updateInterval = ((1.seconds / 60.0) * files.size).coerceAtLeast(1.seconds)
        val start = TimeSource.Monotonic.markNow()
        pickedFilesWithCountedByteTotals.map { (pickedFile, totalBytesState) ->
            async(Dispatchers.IO) {
                var lastYield = TimeSource.Monotonic.markNow()
                var totalTimeYielding = Duration.ZERO
                var totalTimeUpdatingAtomicLong = Duration.ZERO
                var totalTimeUpdatingMutableLongState = Duration.ZERO
                val exactSize: Long
                val totalTimeRunning = measureTime {
                    exactSize = counter.fileSizeFor(
                        uri = pickedFile.uri,
                        onTotalBytesUpdate = { skippedBytes, totalBytes ->
                            if (totalBytes >= maxSize) {
                                throw FileSizeExceededException("File exceeded the size limit. Uri: ${pickedFile.uri}")
                            }
                            totalTimeUpdatingAtomicLong += measureTime {
                                if (total.addAndFetch(skippedBytes.toLong()) >= maxSize) {
                                    throw FileSizeExceededException("Files are exceeding the size limit")
                                }
                            }
                            if (lastYield.elapsedNow() >= updateInterval) {
                                totalTimeUpdatingMutableLongState += measureTime { totalBytesState.longValue = totalBytes }
                                lastYield = TimeSource.Monotonic.markNow()
                                totalTimeYielding += measureTime { yield() }
                            } else {
                                ensureActive()
                            }
                        }
                    )
                }
                pickedFile.copy(size = exactSize) to TotalTimes(
                    totalTimeRunning = totalTimeRunning,
                    yielding = totalTimeYielding,
                    updatingAtomicLong = totalTimeUpdatingAtomicLong,
                    updatingMutableLongState = totalTimeUpdatingMutableLongState
                )
            }
        }.awaitAll().let { list ->
            val totalTimes = TotalTimes(
                totalTimeRunning = list.sumOfDuration { it.second.totalTimeRunning },
                yielding = list.sumOfDuration { it.second.yielding },
                updatingAtomicLong = list.sumOfDuration { it.second.updatingAtomicLong },
                updatingMutableLongState = list.sumOfDuration { it.second.updatingMutableLongState },
            )
            val executionDuration = start.elapsedNow()
            SentryLog.i(TAG, "Actual execution duration: $executionDuration | total thread times: $totalTimes")
            list.map { (pickedFileWithExactSize, _) -> pickedFileWithExactSize }
        }
    }, {
        handleTotalsProgression(pickedFilesWithCountedByteTotals)
    })
}

private data class TotalTimes(
    val totalTimeRunning: Duration,
    val yielding: Duration,
    val updatingAtomicLong: Duration,
    val updatingMutableLongState: Duration,
)

@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfDuration")
inline fun <T> Iterable<T>.sumOfDuration(selector: (T) -> Duration): Duration {
    var sum: Duration = Duration.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
