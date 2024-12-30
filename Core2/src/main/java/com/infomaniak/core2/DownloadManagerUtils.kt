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
package com.infomaniak.core2

import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.database.Cursor
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import com.infomaniak.core2.extensions.appName
import com.infomaniak.core2.extensions.appVersionName
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import splitties.systemservices.downloadManager
import splitties.toast.UnreliableToastApi
import splitties.toast.toast

object DownloadManagerUtils {

    private val regexInvalidSystemChar = Regex("[\\\\/:*?\"<>|\\x7F]|[\\x00-\\x1f]")

    private val scope = CoroutineScope(Dispatchers.Default)

    fun scheduleDownload(
        url: String,
        name: String,
        userAgent: String,
        extraHeaders: Iterable<Pair<String, String>> = emptySet(),
    ) {
        val request = requestFor(
            url = url,
            name = name,
            userAgent = userAgent,
            extraHeaders = extraHeaders
        )
        val downloadReference = downloadManager.enqueue(request)
        handleDownloadManagerErrors(downloadManager, downloadReference)
    }

    fun downloadFile(
        url: String,
        name: String,
        userAgent: String,
        extraHeaders: Iterable<Pair<String, String>> = emptySet(),
    ): Flow<DownloadStatus> {
        val request = requestFor(
            url = url,
            name = name,
            userAgent = userAgent,
            extraHeaders = extraHeaders
        )
        return downloadManager.downloadFile(request)
    }

    fun requestFor(
        url: String,
        name: String,
        userAgent: String,
        extraHeaders: Iterable<Pair<String, String>> = emptySet(),
    ): Request = Request(Uri.parse(url)).also { r ->
        r.setAllowedNetworkTypes(Request.NETWORK_WIFI or Request.NETWORK_MOBILE)
        val formattedName = name.replace(regexInvalidSystemChar, "_").replace("%", "_").let {
            // fix IllegalArgumentException only on Android 10 if multi dot
            if (SDK_INT == 29) it.replace(Regex("\\.{2,}"), ".") else it
        }
        r.setTitle(formattedName)
        r.setDescription(appName)
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        r.addHeaders(userAgent, extraHeaders)

        r.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    }

    private fun Request.addHeaders(userAgent: String, extraHeaders: Iterable<Pair<String, String>>) {
        addRequestHeader("Accept-Encoding", "gzip")
        addRequestHeader("App-Version", "Android $appVersionName")
        addRequestHeader("User-Agent", userAgent)
        extraHeaders.forEach { (key, value) -> addRequestHeader(key, value) }
    }

    private fun handleDownloadManagerErrors(downloadManager: DownloadManager, downloadReference: Long) {
        scope.launch {
            delay(1_000L) // We wait a little to make sure we have the errors from the query
            DownloadManager.Query().also { query ->
                query.setFilterById(downloadReference)
                Dispatchers.IO {
                    downloadManager.query(query).use {
                        if (it.moveToFirst()) showErrorIfAny(it)
                    }
                }
            }
        }
    }

    @OptIn(UnreliableToastApi::class)
    private suspend fun showErrorIfAny(cursor: Cursor) {
        val status: Int
        val reason: Int
        withContext(Dispatchers.IO) {
            status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
            reason = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON))
        }
        if (status == DownloadManager.STATUS_FAILED) Dispatchers.Main {
            when (reason) {
                DownloadManager.ERROR_INSUFFICIENT_SPACE -> toast(R.string.errorDownloadInsufficientSpace)
                else -> toast(R.string.errorDownload)
            }
        }
    }
}
