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
package com.infomaniak.swisstransfer

import com.infomaniak.swisstransfer.ui.utils.FileNameUtils.postfixExistingFileNames
import org.junit.Assert.assertEquals
import org.junit.Test

class PostifxedFileNameUnitTest {
    @Test
    fun unusedName_isUnchanged() {
        val inputFileName = "world.txt"
        val newName = postfixExistingFileNames(inputFileName, alreadyUsedFileNames::contains)
        assertEquals(newName, inputFileName)
    }

    @Test
    fun usedName_isPostfixed() {
        val newName = postfixExistingFileNames("hello.txt", alreadyUsedFileNames::contains)
        assertEquals(newName, "hello(1).txt")
    }

    @Test
    fun alreadyPostfixedName_isPostfixedAgain() {
        val newName = postfixExistingFileNames("test(1).txt", alreadyUsedFileNames::contains)
        assertEquals(newName, "test(1)(1).txt")
    }

    @Test
    fun postfixedNameThatCollidesWithAnotherName_isPostfixedUntilNoMoreCollision() {
        val newName = postfixExistingFileNames("test.txt", alreadyUsedFileNames::contains)
        assertEquals(newName, "test(3).txt")
    }

    @Test
    fun unusedEmptyFileName_isUnchanged() {
        assertNewFileNameIsUnchanged(inputFileName = "")
    }

    @Test
    fun usedEmptyFileName_isPostfixed() {
        assertAlreadyExistingFileName(inputFileName = "", expectedFileName = "(1)")
    }

    @Test
    fun unusedNoExtensionFileName_isUnchanged() {
        assertNewFileNameIsUnchanged(inputFileName = "file")
    }

    @Test
    fun usedNoExtensionFileName_isPostfixed() {
        assertAlreadyExistingFileName(inputFileName = "file", expectedFileName = "file(1)")
    }

    @Test
    fun multipleExtensionFileName_isPostfixedBeforeLastOne() {
        assertAlreadyExistingFileName(inputFileName = "file.abc.def.ghi", expectedFileName = "file.abc.def(1).ghi")
    }

    @Test
    fun unusedDotEndingFileName_isUnchanged() {
        assertNewFileNameIsUnchanged(inputFileName = "file.")
    }

    @Test
    fun usedDotEndingFileName_isPostfixed() {
        assertAlreadyExistingFileName(inputFileName = "file.", expectedFileName = "file(1).")
    }

    private fun assertAlreadyExistingFileName(inputFileName: String, expectedFileName: String) {
        val newName = postfixExistingFileNames(inputFileName, setOf(inputFileName)::contains)
        assertEquals(newName, expectedFileName)
    }

    private fun assertNewFileNameIsUnchanged(inputFileName: String) {
        val newName = postfixExistingFileNames(inputFileName, emptySet<String>()::contains)
        assertEquals(newName, inputFileName)
    }

    private companion object {
        // Used for tests that require to check existing filenames among multiple edge case already existing filenames
        val alreadyUsedFileNames = setOf("test.txt", "test(1).txt", "test(2).txt", "hello.txt")
    }
}
