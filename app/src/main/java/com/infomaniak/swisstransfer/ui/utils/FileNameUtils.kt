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
package com.infomaniak.swisstransfer.ui.utils

object FileNameUtils {
    private val ALREADY_POSTFIXED_REGEX = Regex("""(.*\()(\d)(\))$""")

    fun postfixExistingFileNames(wholeFileName: String, existingFileNames: Set<String>): String {
        return if (wholeFileName in existingFileNames) {
            val (name, ext) = splitNameAndExtension(wholeFileName)

            var (nameStart, namePostfixNumber, nameEnd) = ALREADY_POSTFIXED_REGEX.find(name)?.let {
                Triple(it.groupValues[1], (it.groupValues[2]).toInt(), it.groupValues[3])
            } ?: Triple("$name(", 0, ")")

            fun incrementFileNamePostfix() = incrementFileNamePostfix(nameStart, namePostfixNumber, nameEnd, ext)

            var newName = incrementFileNamePostfix()
            while (newName in existingFileNames) {
                namePostfixNumber += 1
                newName = incrementFileNamePostfix()
            }

            newName
        } else {
            wholeFileName
        }
    }

    private fun incrementFileNamePostfix(
        nameStart: String,
        previousPostfixNumber: Int,
        nameEnd: String,
        ext: String,
    ) = "$nameStart${(previousPostfixNumber + 1)}$nameEnd$ext"

    private fun splitNameAndExtension(fileName: String): Pair<String, String> {
        val dotIndex = fileName.lastIndexOf('.')

        // If there's no dot or it's the first/last character, return the whole name and an empty extension
        return if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length - 1) {
            fileName to ""
        } else {
            fileName.substring(0, dotIndex) to fileName.substring(dotIndex)
        }
    }
}
