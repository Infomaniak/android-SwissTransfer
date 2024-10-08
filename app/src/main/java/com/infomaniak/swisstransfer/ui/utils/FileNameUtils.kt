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

    fun postfixExistingFileNames(fileName: String, existingFileNames: Set<String>): String {
        return if (fileName in existingFileNames) {
            val postfixedFileName = PostfixedFileName.fromFileName(fileName)

            while (postfixedFileName.fullName() in existingFileNames) {
                postfixedFileName.incrementPostfix()
            }

            postfixedFileName.fullName()
        } else {
            fileName
        }
    }

    private data class PostfixedFileName(
        private val start: String,
        private var postfixNumber: Int,
        private val end: String,
        private val extension: String,
    ) {
        fun incrementPostfix() {
            postfixNumber += 1
        }

        fun fullName(): String = "$start$postfixNumber$end$extension"

        companion object {
            fun fromFileName(fileName: String): PostfixedFileName {
                val (name, ext) = splitNameAndExtension(fileName)

                return PostfixedFileName(
                    start = "$name(",
                    postfixNumber = 1,
                    end = ")",
                    extension = ext,
                )
            }

            private fun splitNameAndExtension(fileName: String): Pair<String, String> {
                val dotIndex = fileName.lastIndexOf('.')

                // If there's no dot or it's the first/last character, return the whole name and an empty extension
                return if (dotIndex == -1) {
                    fileName to ""
                } else {
                    fileName.substring(0, dotIndex) to fileName.substring(dotIndex)
                }
            }
        }
    }
}
