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

    private val fallback = "bin"

    @Test
    fun nullMimeType_returnsFallback() {
        val result: String? = null
        assertEquals(fallback, result.extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun blankMimeType_returnsFallback() {
        assertEquals(fallback, "  ".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun emptyMimeType_returnsFallback() {
        assertEquals(fallback, "".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun mimeTypeWithoutSlash_returnsFallback() {
        assertEquals(fallback, "png".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun mimeTypeWithSlashButBlankAfter_returnsFallback() {
        assertEquals(fallback, "image/".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun validImagePng_returnsPng() {
        assertEquals("png", "image/png".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun validApplicationJson_returnsJson() {
        assertEquals("json", "application/json".extractExtensionOrFallback(fallback = fallback))
    }

    @Test
    fun validVndType_returnsLastSegment() {
        assertEquals(
            "vnd.android.package-archive",
            "application/vnd.android.package-archive".extractExtensionOrFallback(fallback = fallback)
        )
    }
}
