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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.ui.components.FileUi
import com.infomaniak.swisstransfer.ui.screen.newtransfer.TransferFilesManager.PickedFile
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewTransferViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val transferFilesManager: TransferFilesManager,
) : ViewModel() {

    private val _files = MutableStateFlow<List<FileUi>>(emptyList())
    val files: StateFlow<List<FileUi>> = _files

    private val _failedFiles = MutableSharedFlow<PickedFile>()
    val failedFiles: SharedFlow<PickedFile> = _failedFiles

    private val filesToImport: Channel<PickedFile> = Channel(capacity = Channel.UNLIMITED)

    private val filesMutationMutex = Mutex()

    init {
        removeLocalCopyFolder() // Remove old imported files in case it would've crashed or similar to start with a clean slate
        observeFilesToImport()
    }

    fun addFiles(uris: List<Uri>) {
        viewModelScope.launch(Dispatchers.IO) {
            val alreadyUsedFileNames = buildSet { files.value.forEach { add(it.fileName) } }
            val newFiles = transferFilesManager.getFiles(uris, alreadyUsedFileNames)

            newFiles.forEach(filesToImport::trySend)
        }
    }

    fun removeFileByUid(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filesMutationMutex.withLock {
                val files = _files.value.toMutableList()

                val index = files.indexOfFirst { it.uid == uid }.takeIf { it != -1 } ?: return@withLock
                val fileToRemove = files.removeAt(index)

                runCatching { File(fileToRemove.uri).delete() }

                _files.value = files
            }
        }
    }

    private fun removeLocalCopyFolder() {
        runCatching { appContext.createLocalCopyFolderFile().deleteRecursively() }
    }

    private fun observeFilesToImport() {
        viewModelScope.launch(Dispatchers.IO) {
            for (fileToImport in filesToImport) {
                Log.i(TAG, "Importing ${fileToImport.uri}")
                val copiedFile = copyFileLocally(fileToImport.uri)

                if (copiedFile == null) {
                    reportFailedImportation(fileToImport)
                    continue
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
    }

    private fun copyFileLocally(uri: Uri): File? {
        val file = File(appContext.localCopyFolder, uri.hashCode().toString()).apply {
            if (exists()) delete()
            createNewFile()

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

    companion object {
        private const val TAG = "File importation"
        const val LOCAL_COPY_FOLDER = "local_copy_folder"
        inline val Context.localCopyFolder get() = createLocalCopyFolderFile().apply { if (!exists()) mkdirs() }

        fun Context.createLocalCopyFolderFile() = File(cacheDir, LOCAL_COPY_FOLDER)
    }
}
