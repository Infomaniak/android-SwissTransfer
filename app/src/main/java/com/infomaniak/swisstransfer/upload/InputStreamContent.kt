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

import android.net.Uri
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.jvm.javaio.toOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.invoke
import kotlinx.io.IOException
import splitties.init.appCtx
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun Uri.toContentChunk(
    offset: Long,
    length: Long,
): OutgoingContent.WriteChannelContent = UriContent(targetFileUri = this, offset = offset, contentLength = length)

private class UriContent(
    private val targetFileUri: Uri,
    private val offset: Long,
    override val contentLength: Long
) : OutgoingContent.WriteChannelContent() {

    override suspend fun writeTo(channel: ByteWriteChannel) = Dispatchers.IO {
        val stream = appCtx.contentResolver.openInputStream(targetFileUri)
            ?: throw IOException("The provider for the following Uri recently crashed: $targetFileUri")
        stream.buffered().use { inputStream ->
            inputStream.skipExactly(numberOfBytes = offset, coroutineContext)
            inputStream.copyToUntil(channel.toOutputStream(), count = contentLength, coroutineContext = coroutineContext)
        }
    }
}

@Throws(java.io.IOException::class)
private fun InputStream.skipExactly(
    numberOfBytes: Long,
    coroutineContext: CoroutineContext = EmptyCoroutineContext
) {
    var n = numberOfBytes
    while (n > 0) {
        coroutineContext.ensureActive()
        val ns: Long = skip(n)
        if (ns > 0 && ns <= n) {
            // adjust number to skip
            n -= ns
        } else if (ns == 0L) { // no bytes skipped
            // read one byte to check for EOS
            if (read() == -1) {
                throw EOFException()
            }
            // one byte read so decrement number to skip
            n--
        } else { // skipped negative or too many bytes
            throw IOException("Unable to skip exactly")
        }
    }
}

/**
 * Copies [count] bytes of this [InputStream] to [out].
 *
 * **Note** It is the caller's responsibility to close both of these resources.
 */
@Throws(java.io.IOException::class)
private fun InputStream.copyToUntil(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    count: Long,
    coroutineContext: CoroutineContext = EmptyCoroutineContext
) {
    var bytesRemaining: Long = count
    val buffer = ByteArray(bufferSize)
    do {
        coroutineContext.ensureActive()
        val bytesToCopy = minOf(bytesRemaining, bufferSize.toLong()).toInt()
        val readBytesCount = read(/* b = */ buffer, /* off = */ 0, /* len = */ bytesToCopy)
        if (readBytesCount == 0) {
            throw IOException("Wanted to read $bytesToCopy bytes but got zero. $bytesRemaining/$count bytes couldn't be copied.")
        } else if (readBytesCount < 0) {
            throw EOFException("$bytesRemaining/$count bytes couldn't be copied.")
        }
        coroutineContext.ensureActive()
        out.write(
            /* b = */ buffer,
            /* off = */ 0,
            /* len = */ readBytesCount
        )
        bytesRemaining -= readBytesCount
    } while (bytesRemaining > 0)
}
