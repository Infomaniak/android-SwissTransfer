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
import androidx.core.net.toUri
import com.infomaniak.core.filetypes.FileType
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.utils.FileNameUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.sentry.Sentry
import io.sentry.SentryLevel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.invoke
import splitties.toast.UnreliableToastApi
import splitties.toast.longToast
import java.io.File
import java.io.InputStream
import javax.inject.Inject

@ViewModelScoped
class ImportationFilesManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val importLocalStorage: ImportLocalStorage,
    private val thumbnailsLocalStorage: ThumbnailsLocalStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val filesToImportChannel: TransferCountChannel = TransferCountChannel(
        resetCopiedBytes = importLocalStorage::resetCopiedBytes,
    )
    val filesToImportCount: StateFlow<Int> = filesToImportChannel.count
    val currentSessionTotalByteCount: StateFlow<Long> = filesToImportChannel.currentSessionTotalByteCount
    val currentSessionImportedByteCount: StateFlow<Long> by importLocalStorage::copiedBytes

    private val _importedFiles = FilesMutableStateFlow()
    val importedFiles = _importedFiles.flow

    // Importing a file locally can take up time. We can't base the list of already used names on _importedFiles's value because a
    // new import with the same name could occur while the file is still importing. This would lead to a name collision.
    // This list needs to mark a name as "taken" as soon as the file is queued to be imported and until the file is removed from
    // the list of already imported files we listen to in the LazyRow.
    private val alreadyUsedFileNames = AlreadyUsedFileNamesSet()

    suspend fun importFiles(uris: List<Uri>) {
        val pickedFiles = uris.extractPickedFiles()
        filesToImportChannel.setTotalFileSize(pickedFiles.sumOf { it.fileSize })
        pickedFiles.forEach { filesToImportChannel.send(it) }
    }

    suspend fun removeFileByUid(uid: String) {
        _importedFiles.removeByUid(uid)?.also { removedFileName ->
            alreadyUsedFileNames.remove(removedFileName)
        }
    }

    fun removeLocalCopyFolder() = importLocalStorage.removeImportFolder()

    suspend fun restoreAlreadyImportedFiles() {
        if (!importLocalStorage.importFolderExists()) return

        val localFiles = importLocalStorage.getLocalFiles() ?: return
        val restoredFileUi = getRestoredFileUi(localFiles)

        if (localFiles.size != restoredFileUi.size) {
            Sentry.withScope { scope ->
                scope.level = SentryLevel.ERROR
                Sentry.captureMessage("Restoration failure of already imported files after a process kill")
            }
        }

        alreadyUsedFileNames.addAll(restoredFileUi.map { it.fileName })
        _importedFiles.addAll(restoredFileUi)
    }

    suspend fun continuouslyCopyPickedFilesToLocalStorage() {
        filesToImportChannel.consume { fileToImport ->
            SentryLog.i(TAG, "Importing ${fileToImport.uri}")
            val copiedFile = ioDispatcher {
                copyUriDataLocally(fileToImport.uri, fileToImport.fileName).onFailure {
                    reportFailedImportation(fileToImport, it)
                }.getOrNull()
            } ?: return@consume

            thumbnailsLocalStorage.generateThumbnailFor(
                fileUri = fileToImport.uri.toString(),
                fileName = copiedFile.nameWithoutExtension,
                extension = copiedFile.extension,
            )

            SentryLog.i(TAG, "Successfully imported ${fileToImport.uri}")

            // Make sure the file size has to be correct by reading it from local io File
            val fileSizeInBytes = copiedFile.length()
            SentryLog.v(TAG, "File size in bytes: $fileSizeInBytes")

            _importedFiles.add(
                FileUi(
                    uid = fileToImport.fileName,
                    fileName = fileToImport.fileName,
                    isFolder = false,
                    fileSize = fileSizeInBytes,
                    mimeType = FileType.guessMimeTypeFromFileName(fileToImport.fileName),
                    localPath = copiedFile.toUri().toString(),
                    path = null,
                    thumbnailPath = thumbnailsLocalStorage.getOngoingThumbnailFor(copiedFile.nameWithoutExtension).toUri()
                        .toString(),
                ),
            )
        }
    }

    private fun copyUriDataLocally(
        uri: Uri,
        fileName: String
    ): Result<File> = openInputStream(uri).mapCatching { inputStream ->
        return importLocalStorage.copyUriDataLocally(inputStream, fileName)
    }

    private fun openInputStream(uri: Uri): Result<InputStream> = runCatching {
        appContext.contentResolver.openInputStream(uri)
    }.mapCatching {
        it ?: throw NullPointerException("The provider recently crashed")
    }

    private fun getRestoredFileUi(localFiles: Array<File>): List<FileUi> {
        return localFiles.mapNotNull { localFile ->
            val fileSizeInBytes = runCatching { localFile.length() }
                .onFailure { Sentry.addBreadcrumb("Caught an exception while restoring imported files: $it") }
                .getOrNull() ?: return@mapNotNull null

            SentryLog.v(TAG, "Restoring files after activity recreation with file size in bytes: $fileSizeInBytes")

            return@mapNotNull FileUi(
                uid = localFile.name,
                fileName = localFile.name,
                isFolder = false,
                fileSize = fileSizeInBytes,
                mimeType = null,
                localPath = localFile.toUri().toString(),
                path = null,
                thumbnailPath = null,
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
            val uniqueName = alreadyUsedFileNames.addUniqueFileName(computeUniqueFileName = { alreadyUsedStrategy ->
                FileNameUtils.postfixExistingFileNames(
                    fileName = name,
                    isAlreadyUsed = { alreadyUsedStrategy.isAlreadyUsed(it) }
                )
            })

            PickedFile(uniqueName, uri, size)
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

    private suspend fun reportFailedImportation(file: PickedFile, throwable: Throwable) {
        SentryLog.e(TAG, "Failed importation of ${file.uri}", throwable)

        // TODO: Make more precise error messages (especially for the low storage issue).
        val errorMessage = appContext.getString(R.string.cantImportFileX, file.fileName)
        Dispatchers.Main {
            @OptIn(UnreliableToastApi::class)
            longToast(errorMessage) // TODO: Find a better way to show the error in an actionable way.
        }
    }

    data class PickedFile(val fileName: String, val uri: Uri, val fileSize: Long)

    companion object {
        private const val TAG = "File importation"
    }
}
