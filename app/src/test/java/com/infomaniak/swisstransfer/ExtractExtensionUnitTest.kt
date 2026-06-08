/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer

import com.infomaniak.swisstransfer.ui.utils.extractExtensionOrFallback
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtractExtensionUnitTest {

    @Test
    fun nullMimeType_returnsFallback() {
        val result: String? = null
        assertEquals(FALLBACK, result.extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun blankMimeType_returnsFallback() {
        assertEquals(FALLBACK, "  ".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun emptyMimeType_returnsFallback() {
        assertEquals(FALLBACK, "".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun mimeTypeWithoutSlash_returnsFallback() {
        assertEquals(FALLBACK, "png".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun mimeTypeWithSlashButBlankAfter_returnsFallback() {
        assertEquals(FALLBACK, "image/".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun validImagePng_returnsPng() {
        assertEquals("png", "image/png".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun validApplicationJson_returnsJson() {
        assertEquals("json", "application/json".extractExtensionOrFallback(fallback = FALLBACK))
    }

    @Test
    fun validVndType_returnsLastSegment() {
        assertEquals(
            "vnd.android.package-archive",
            "application/vnd.android.package-archive".extractExtensionOrFallback(fallback = FALLBACK)
        )
    }

    companion object {
        private const val FALLBACK = "bin"
    }
}
