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

import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.infomaniak.core.cancellable
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.screen.newtransfer.AlreadyUsedFileNamesSet
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import splitties.init.appCtx

class PickedFilesExtractorImpl : PickedFilesExtractor {

    override val isHandlingUrisFlow: StateFlow<Boolean>

    override fun addUris(uris: List<Uri>) {
        incomingActions.trySend(FilePickingAction.Add(uris))
    }

    override fun removeUris(uris: List<Uri>) {
        incomingActions.trySend(FilePickingAction.Removal(uris))
    }

    override fun clear() {
        incomingActions.trySend(FilePickingAction.ClearAll)
    }

    private var isHandlingUris by MutableStateFlow(false).also {
        isHandlingUrisFlow = it.asStateFlow()
    }::value

    private val incomingActions = Channel<FilePickingAction>(capacity = Channel.UNLIMITED)

    override val pickedFilesFlow: StateFlow<List<PickedFile>> = flow {
        val currentPickedFiles = mutableMapOf<Uri, PickedFile>()
        for (action in incomingActions) {
            if (action.isNoOp(currentPickedFiles)) continue
            try {
                isHandlingUris = true
                action.applyTo(currentPickedFiles)
                val pickedFiles = currentPickedFiles.values.withDuplicatedNamesFixed()
                emit(pickedFiles)
            } finally {
                isHandlingUris = false
            }
        }
    }.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )
}

private sealed interface FilePickingAction {
    data class Add(val newUris: List<Uri>) : FilePickingAction
    data class Removal(val urisToRemove: List<Uri>) : FilePickingAction
    data object ClearAll : FilePickingAction
}

private fun FilePickingAction.isNoOp(currentPickedFiles: Map<Uri, PickedFile>): Boolean = when (this) {
    is FilePickingAction.Add -> this.newUris.isEmpty()
    FilePickingAction.ClearAll -> currentPickedFiles.isEmpty()
    is FilePickingAction.Removal -> this.urisToRemove.isEmpty() || currentPickedFiles.isEmpty()
}

private suspend fun FilePickingAction.applyTo(pickedFiles: MutableMap<Uri, PickedFile>) {
    when (this) {
        is FilePickingAction.Add -> {
            val newPickedFiles = coroutineScope {
                newUris.map { uri ->
                    async {
                        val pickedFile = extractPickedFile(uri) ?: return@async null
                        uri to pickedFile
                    }
                }.awaitAll().filterNotNull()
            }
            pickedFiles.putAll(newPickedFiles)
        }
        is FilePickingAction.Removal -> {
            urisToRemove.forEach { pickedFiles.remove(it) }
        }
        is FilePickingAction.ClearAll -> pickedFiles.clear()
    }
}

private suspend fun extractPickedFile(uri: Uri): PickedFile? = runCatching {
    coroutineScope {
        val fileNameAsync = async { fileNameFor(uri) }
        val mimeTypeAsync = async(Dispatchers.IO) {
            appCtx.contentResolver.getType(uri)
                ?: FileType.guessMimeTypeFromFileName(fileNameAsync.await())
                ?: ""
        }
        val fileSize = fileSizeFor(uri)
        PickedFile(
            uri = uri,
            name = fileNameAsync.await(),
            mimeType = mimeTypeAsync.await(),
            size = fileSize
        )
    }
}.cancellable().onFailure { t ->
    SentryLog.e(TAG, "Failed to extract the file size, or name, or mimeType", t)
}.getOrNull()

private suspend fun fileNameFor(uri: Uri): String = Dispatchers.IO {
    runCatching {
        appCtx.contentResolver.query(
            /* uri = */ uri,
            // Not supplying a projection might lead to `NullPointerException` with message "Attempt to get length of null array"
            // being thrown on some devices, despite what is written in the Javadoc, so we provide one.
            /* projection = */ displayNameProjection,
            /* selection = */ null,
            /* selectionArgs = */ null,
            /* sortOrder = */ null
        )?.use { cursor: Cursor ->
            if (cursor.moveToFirst()) {
                when (val nameColumnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)) {
                    -1 -> null
                    else -> cursor.getStringOrNull(nameColumnIndex)
                }
            } else null
        }
    }.onFailure { t -> SentryLog.e(TAG, "Failed to read the display name for uri: $uri", t) }.getOrNull()
} ?: fallbackName

private suspend fun fileSizeFor(uri: Uri): Long = Dispatchers.IO {
    runCatching {
        appCtx.contentResolver.query(
            /* uri = */ uri,
            // Not supplying a projection might lead to `NullPointerException` with message "Attempt to get length of null array"
            // being thrown on some devices, despite what is written in the Javadoc, so we provide one.
            /* projection = */ sizeProjection,
            /* selection = */ null,
            /* selectionArgs = */ null,
            /* sortOrder = */ null
        )?.use { cursor: Cursor ->
            if (cursor.moveToFirst()) {
                when (val sizeColumnIndex = cursor.getColumnIndex(OpenableColumns.SIZE)) {
                    -1 -> -1L
                    else -> cursor.getLongOrNull(sizeColumnIndex)
                }
            } else -1L
        } ?: -1L
    }.onFailure { t ->
        SentryLog.e(TAG, "Failed to read the size for uri: $uri", t)
    }.getOrElse { -1L }
}

private val displayNameProjection = arrayOf(OpenableColumns.DISPLAY_NAME)
private val sizeProjection = arrayOf(OpenableColumns.SIZE)

private const val fallbackName = "untitled"

private const val TAG = "PickedFilesExtractorImpl"

private suspend fun Collection<PickedFile>.withDuplicatedNamesFixed(): List<PickedFile> {
    if (isEmpty()) return emptyList()
    //TODO: Replace AlreadyUsedFileNamesSet with a non-suspend, Mutex-free version.
    // We don't need that since we're processing all picked files sequentially.
    val alreadyUsedFileNames = AlreadyUsedFileNamesSet()
    return map { pickedFile ->
        val uniqueName = alreadyUsedFileNames.addUniqueFileName(computeUniqueFileName = { alreadyUsedStrategy ->
            FileNameUtils.postfixExistingFileNames(
                fileName = pickedFile.name,
                isAlreadyUsed = { name -> alreadyUsedStrategy.isAlreadyUsed(name) }
            )
        })
        pickedFile.copy(name = uniqueName)
    }
}
