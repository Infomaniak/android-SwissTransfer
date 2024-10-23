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

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.core.net.toUri
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferViewModel.Companion.LOCAL_COPY_FOLDER
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import io.sentry.SentryLevel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImportationFilesManager @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val filesToImport: TransferCountChannel = TransferCountChannel()
    val filesToImportCount: StateFlow<Int> = filesToImport.count
    val currentSessionTotalUploadedFiles: StateFlow<Int> = filesToImport.currentSessionTotalUploadedFiles

    private val _importedFiles = FilesMutableStateFlow()
    val importedFiles = _importedFiles.flow

    private val _failedFiles = MutableSharedFlow<PickedFile>()
    val failedFiles = _failedFiles.asSharedFlow()

    // Importing a file locally can take up time. We can't base the list of already used names on _importedFiles's value because a
    // new import with the same name could occur while the file is still importing. This would lead to a name collision.
    // This list needs to mark a name as "taken" as soon as the file is queued to be imported and until the file is removed from
    // the list of already imported files we listen to in the LazyRow.
    private val alreadyUsedFileNames = AlreadyUsedFileNamesSet()

    private val importFolder by lazy { File(appContext.cacheDir, LOCAL_COPY_FOLDER) }
    private fun getImportFolderOrCreate() = importFolder.apply { if (!exists()) mkdirs() }

    suspend fun addFiles(uris: List<Uri>) {
        uris.extractPickedFiles().forEach { filesToImport.send(it) }
    }

    suspend fun removeFileByUid(uid: String) {
        _importedFiles.removeByUid(uid)?.also { removedFileName ->
            alreadyUsedFileNames.remove(removedFileName)
        }
    }

    fun removeLocalCopyFolder() {
        if (importFolder.exists()) runCatching { importFolder.deleteRecursively() }
    }

    suspend fun restoreAlreadyImportedFiles() {
        if (!importFolder.exists()) return

        val alreadyCopiedFiles = importFolder.listFiles() ?: return
        val restoredFileData = getRestoredFileData(alreadyCopiedFiles)

        if (alreadyCopiedFiles.size != restoredFileData.size) {
            Sentry.withScope { scope ->
                scope.level = SentryLevel.ERROR
                Sentry.captureMessage("Failure of the restoration of the already imported files after a process kill")
            }
        }

        alreadyUsedFileNames.addAll(restoredFileData.map { it.fileName })
        _importedFiles.addAll(restoredFileData)
    }

    suspend fun continuouslyCopyPickedFilesToLocalStorage() {
        filesToImport.consume { fileToImport ->
            Log.i(TAG, "Importing ${fileToImport.uri}")
            val copiedFile = copyFileLocally(fileToImport.uri, fileToImport.fileName)

            if (copiedFile == null) {
                reportFailedImportation(fileToImport)
                return@consume
            }

            Log.i(TAG, "Successfully imported ${fileToImport.uri}")

            _importedFiles.add(
                FileUi(
                    uid = fileToImport.fileName,
                    fileName = fileToImport.fileName,
                    fileSizeInBytes = fileToImport.fileSizeInBytes,
                    mimeType = null,
                    uri = copiedFile.toUri().toString(),
                )
            )
        }
    }

    private fun getRestoredFileData(files: Array<File>): List<FileUi> {
        return files.mapNotNull { file ->
            val fileSizeInBytes = runCatching { file.length() }
                .onFailure { Sentry.addBreadcrumb("Caught an exception while restoring imported files: $it") }
                .getOrNull() ?: return@mapNotNull null

            FileUi(
                uid = file.name,
                fileName = file.name,
                fileSizeInBytes = fileSizeInBytes,
                mimeType = null,
                uri = file.toUri().toString(),
            )
        }
    }

    private suspend fun List<Uri>.extractPickedFiles(): Set<PickedFile> {
        val files = buildSet {
            this@extractPickedFiles.forEach { uri ->
                extractPickedFile(uri)?.let(::add)
            }
        }

        return files
    }

    private suspend fun extractPickedFile(uri: Uri): PickedFile? {
        val contentResolver: ContentResolver = appContext.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        return cursor?.getFileNameAndSize()?.let { (name, size) ->
            val uniqueName: String

            alreadyUsedFileNames.mutex.withLock {
                uniqueName = FileNameUtils.postfixExistingFileNames(
                    fileName = name,
                    isAlreadyUsed = { alreadyUsedFileNames.contains(it) }
                )

                alreadyUsedFileNames.add(uniqueName)
            }

            PickedFile(uniqueName, size, uri)
        }
    }

    private fun Cursor.getFileNameAndSize(): Pair<String, Long>? = use {
        if (it.moveToFirst()) {
            val displayNameColumnIndex = it.getColumnIndexOrNull(OpenableColumns.DISPLAY_NAME) ?: return null
            val fileName = it.getString(displayNameColumnIndex)

            val fileSizeColumnIndex = it.getColumnIndexOrNull(OpenableColumns.SIZE) ?: return null
            val fileSize = it.getLong(fileSizeColumnIndex)

            fileName to fileSize
        } else {
            null
        }
    }

    private fun Cursor.getColumnIndexOrNull(column: String): Int? {
        return getColumnIndex(column).takeIf { it != -1 }
    }

    private fun copyFileLocally(uri: Uri, fileName: String): File? {
        val file = File(getImportFolderOrCreate(), fileName).apply {
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
        val mutex = Mutex()

        // No need for the mutex because this code is already called inside of the lock
        fun contains(fileName: String): Boolean = alreadyUsedFileNames.contains(fileName)
        fun add(fileName: String): Boolean = alreadyUsedFileNames.add(fileName)

        suspend fun addAll(filesNames: List<String>): Boolean = mutex.withLock { alreadyUsedFileNames.addAll(filesNames) }
        suspend fun remove(filesName: String): Boolean = mutex.withLock { alreadyUsedFileNames.remove(filesName) }
    }

    class FilesMutableStateFlow {
        private val files = MutableStateFlow<List<FileUi>>(emptyList())
        private val mutex = Mutex()

        val flow = files.asStateFlow()

        suspend fun addAll(newFiles: List<FileUi>): Unit = mutex.withLock { files.value += newFiles }
        suspend fun add(newFile: FileUi): Unit = mutex.withLock { files.value += newFile }

        suspend fun removeByUid(uid: String): String? = mutex.withLock {
            val files = files.value.toMutableList()

            val index = files.indexOfFirst { it.uid == uid }.takeIf { it != -1 } ?: return null
            val fileToRemove = files.removeAt(index)

            runCatching { File(fileToRemove.uri).delete() }

            this.files.value = files

            fileToRemove.fileName
        }
    }

    data class PickedFile(val fileName: String, val fileSizeInBytes: Long, val uri: Uri)

    companion object {
        private const val TAG = "File importation"
    }
}
