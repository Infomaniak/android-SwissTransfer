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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferFilesManager.PickedFile
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import io.sentry.SentryLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewTransferViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val transferFilesManager: TransferFilesManager,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Importing a file locally can take up time. We can't base the list of already used names on _files because a new import with
    // the same name could occur while the file is not finished importing yet.
    // This list needs to mark a name a "taken" as soon as the file is queued to be imported and until the file is removed from
    // the list of already imported files we listen to in the LazyRow.
    private val alreadyUsedFileNames = AlreadyUsedFileNamesSet()
    private val _files = MutableStateFlow<List<FileUi>>(emptyList())
    private val files: StateFlow<List<FileUi>> = _files
    @OptIn(FlowPreview::class)
    val filesDebounced = files
        .debounce(50)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList(),
        )

    private val _failedFiles = MutableSharedFlow<PickedFile>()
    val failedFiles: SharedFlow<PickedFile> = _failedFiles

    private val filesToImport: TransferCountChannel = TransferCountChannel()
    val filesToImportCount: StateFlow<Int> = filesToImport.count
    val currentSessionTotalUploadedFiles: StateFlow<Int> = filesToImport.currentSessionTotalUploadedFiles

    private val filesMutationMutex = Mutex()

    private val localCopyFolderFile by lazy { File(appContext.cacheDir, LOCAL_COPY_FOLDER) }
    private val localCopyFolder get() = localCopyFolderFile.apply { if (!exists()) mkdirs() }

    private var isFirstViewModelCreation: Boolean
        get() = savedStateHandle.get<Boolean>(IS_VIEW_MODEL_RESTORED_KEY) ?: true
        set(value) {
            savedStateHandle[IS_VIEW_MODEL_RESTORED_KEY] = value
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFirstViewModelCreation) {
                isFirstViewModelCreation = false
                // Remove old imported files in case it would've crashed or similar to start with a clean slate. This is required for
                // already imported files restoration to not pick up old files in some extreme cases.
                removeLocalCopyFolder()
            } else {
                restoreAlreadyImportedFiles()
            }

            observeFilesToImport()
        }
    }

    fun addFiles(uris: List<Uri>) {
        viewModelScope.launch(Dispatchers.IO) {
            val newFiles = transferFilesManager.getFiles(uris, isAlreadyUsed = { alreadyUsedFileNames.contains(it) })

            alreadyUsedFileNames.addAll(newFiles.map { it.fileName })

            newFiles.forEach { filesToImport.send(it) }
        }
    }

    fun removeFileByUid(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var fileName: String? = null

            filesMutationMutex.withLock {
                val files = _files.value.toMutableList()

                val index = files.indexOfFirst { it.uid == uid }.takeIf { it != -1 } ?: return@withLock
                val fileToRemove = files.removeAt(index)
                fileName = fileToRemove.fileName

                runCatching { File(fileToRemove.uri).delete() }

                _files.value = files
            }

            fileName?.let {
                alreadyUsedFileNames.remove(it)
            }
        }
    }

    private suspend fun restoreAlreadyImportedFiles() {
        if (!localCopyFolderFile.exists()) return

        val alreadyCopiedFiles = localCopyFolderFile.listFiles() ?: return
        val restoredFileData = transferFilesManager.getRestoredFileData(alreadyCopiedFiles)

        if (alreadyCopiedFiles.size != restoredFileData.size) {
            Sentry.withScope { scope ->
                scope.level = SentryLevel.ERROR
                Sentry.captureMessage("Failure of the restoration of the already imported files after a process kill")
            }
        }

        alreadyUsedFileNames.addAll(restoredFileData.map { it.fileName })

        filesMutationMutex.withLock { _files.value += restoredFileData }
    }

    private fun removeLocalCopyFolder() {
        if (localCopyFolderFile.exists()) runCatching { localCopyFolderFile.deleteRecursively() }
    }

    private suspend fun observeFilesToImport() {
        filesToImport.consume { fileToImport ->
            Log.i(TAG, "Importing ${fileToImport.uri}")
            val copiedFile = copyFileLocally(fileToImport.uri, fileToImport.fileName)

            if (copiedFile == null) {
                reportFailedImportation(fileToImport)
                return@consume
            }

            Log.i(TAG, "Successfully imported ${fileToImport.uri}")

            filesMutationMutex.withLock {
                _files.value += FileUi(
                    uid = fileToImport.fileName,
                    fileName = fileToImport.fileName,
                    fileSizeInBytes = fileToImport.fileSizeInBytes,
                    mimeType = null,
                    uri = copiedFile.toUri().toString(),
                )
            }
        }
    }

    private fun copyFileLocally(uri: Uri, fileName: String): File? {
        val file = File(localCopyFolder, fileName).apply {
            if (exists()) delete()
            runCatching { createNewFile() }.onFailure { return null }

            runCatching {
                val inputStream = appContext.contentResolver.openInputStream(uri) ?: return null

                inputStream.use { inputStream ->
                    outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }.onFailure {
                return null
            }
        }

        return file
    }

    private suspend fun reportFailedImportation(file: PickedFile) {
        Log.w(TAG, "Failed importation of ${file.uri}");
        _failedFiles.emit(file)
    }

    private class TransferCountChannel {
        private val channel = Channel<PickedFile>(capacity = Channel.UNLIMITED)

        private val _count = MutableStateFlow(0)
        val count: StateFlow<Int> = _count

        // Session resets when reaching 0 files in the queue
        private val _currentSessionTotalUploadedFiles = MutableStateFlow(0)
        val currentSessionTotalUploadedFiles: StateFlow<Int> = _currentSessionTotalUploadedFiles

        private val countMutex = Mutex()

        suspend fun send(element: PickedFile) {
            countMutex.withLock {
                _count.value += 1
                _currentSessionTotalUploadedFiles.value += 1
            }
            channel.send(element)
        }

        suspend fun consume(process: suspend (PickedFile) -> Unit) {
            for (element in channel) {
                process(element)
                countMutex.withLock {
                    val newValue = _count.value - 1
                    _count.value = newValue
                    if (newValue == 0) _currentSessionTotalUploadedFiles.value = 0
                }
            }
        }
    }

    class AlreadyUsedFileNamesSet {
        private val alreadyUsedFileNames = mutableSetOf<String>()
        private val mutex = Mutex()

        suspend fun contains(fileName: String): Boolean = mutex.withLock { alreadyUsedFileNames.contains(fileName) }
        suspend fun addAll(filesNames: List<String>): Boolean = mutex.withLock { alreadyUsedFileNames.addAll(filesNames) }
        suspend fun remove(filesName: String): Boolean = mutex.withLock { alreadyUsedFileNames.remove(filesName) }
    }

    companion object {
        private const val TAG = "File importation"
        const val LOCAL_COPY_FOLDER = "local_copy_folder"
        const val IS_VIEW_MODEL_RESTORED_KEY = "IS_VIEW_MODEL_RESTORED_KEY"
    }
}
