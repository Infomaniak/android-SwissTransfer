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
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import io.sentry.SentryLevel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.sync.withLock
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImportationFilesManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val uploadLocalStorage: UploadLocalStorage,
) {

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

    suspend fun addFiles(uris: List<Uri>) {
        uris.extractPickedFiles().forEach { filesToImport.send(it) }
    }

    suspend fun removeFileByUid(uid: String) {
        _importedFiles.removeByUid(uid)?.also { removedFileName ->
            alreadyUsedFileNames.remove(removedFileName)
        }
    }

    fun removeLocalCopyFolder() = uploadLocalStorage.removeLocalCopyFolder()

    suspend fun restoreAlreadyImportedFiles() {
        if (!uploadLocalStorage.importFolderExists()) return

        val alreadyCopiedFiles = uploadLocalStorage.listImportFiles() ?: return
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
            val copiedFile = uploadLocalStorage.copyFileLocally(fileToImport.uri, fileToImport.fileName)

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

    private suspend fun reportFailedImportation(file: PickedFile) {
        Log.w(TAG, "Failed importation of ${file.uri}");
        _failedFiles.emit(file)
    }

    data class PickedFile(val fileName: String, val fileSizeInBytes: Long, val uri: Uri)

    companion object {
        private const val TAG = "File importation"
    }
}
