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
package com.infomaniak.library.filetypes

import android.webkit.MimeTypeMap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.library.filetypes.icons.*

private val MEDIA_COLOR_LIGHT = Color(0xFF00BCD4)
private val MEDIA_COLOR_DARK = Color(0xFF86DEEA)

enum class FileType(val icon: ImageVector, private val colorLight: Color, private val colorDark: Color) {
    ARCHIVE(icon = FileTypeIcons.Archive, colorLight = Color(0xFF607D8B), colorDark = Color(0xFFA5B7C0)),
    AUDIO(icon = FileTypeIcons.Audio, colorLight = MEDIA_COLOR_LIGHT, colorDark = MEDIA_COLOR_DARK),
    CALENDAR(icon = FileTypeIcons.Calendar, colorLight = Color(0xFF36D0EB), colorDark = Color(0xFFA2E9F6)),
    CODE(icon = FileTypeIcons.Code, colorLight = Color(0xFF673AB7), colorDark = Color(0xFF956DDD)),
    FONT(icon = FileTypeIcons.Font, colorLight = Color(0xFF9C26B0), colorDark = Color(0xFFCD68DE)),
    IMAGE(icon = FileTypeIcons.Image, colorLight = MEDIA_COLOR_LIGHT, colorDark = MEDIA_COLOR_DARK),
    PDF(icon = FileTypeIcons.Pdf, colorLight = Color(0xFFEF5803), colorDark = Color(0xFFFD9459)),
    POINTS(icon = FileTypeIcons.Points, colorLight = Color(0xFFFF9802), colorDark = Color(0xFFFFC166)),
    SPREADSHEET(icon = FileTypeIcons.Sheet, colorLight = Color(0xFF3EBF4D), colorDark = Color(0xFF78D383)),
    TEXT(icon = FileTypeIcons.Text, colorLight = Color(0xFF2196F3), colorDark = Color(0xFF81C4F8)),
    VCARD(icon = FileTypeIcons.Vcard, colorLight = Color(0xFF9D66E1), colorDark = Color(0xFFC8AAEE)),
    VIDEO(icon = FileTypeIcons.Video, colorLight = MEDIA_COLOR_LIGHT, colorDark = MEDIA_COLOR_DARK),

    UNKNOWN(icon = FileTypeIcons.Unknown, colorLight = Color(0xFF85A2B6), colorDark = Color(0xFFAFC2CF));

    fun color(isDark: Boolean): Color {
        return if (isDark) colorDark else colorLight
    }

    companion object {
        // TODO: Move this method into Core2
        fun guessMimeTypeFromFileName(fileName: String): String? {
            return fileName.extractExtension()?.let { extension ->
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            }
        }

        fun guessFromFileName(fileName: String): FileType {
            val mimeType = guessMimeTypeFromFileName(fileName) ?: return UNKNOWN
            return guessFromMimeType(mimeType)
        }

        fun guessFromMimeType(mimeType: String): FileType {
            return FileTypeGuesser.getFileTypeFromMimeType(mimeType)
        }

        private fun String.extractExtension(): String? {
            val fileName = lastIndexOf('/').takeIf { it != -1 }?.let { fileNameStart ->
                substring(fileNameStart + 1)
            } ?: this

            return fileName.substringAfterLast(".").takeIf { it.isNotEmpty() }
        }
    }
}
