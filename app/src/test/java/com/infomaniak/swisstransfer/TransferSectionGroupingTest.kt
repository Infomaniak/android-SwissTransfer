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

import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.december
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.future
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.lastWeek
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.thisMonth
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.thisWeek
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.today
import com.infomaniak.swisstransfer.dataset.TransferSectionGroupingData.yesterday
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.TransferSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.UniqueSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.getMonthNameFromEpoch
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TransferSectionGroupingTest {
    @Test
    fun today_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(today, UniqueSection.Today)
    }

    @Test
    fun yesterday_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(yesterday, UniqueSection.Yesterday)
    }

    @Test
    fun thisWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisWeek, UniqueSection.ThisWeek)
    }

    @Test
    fun lastWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(lastWeek, UniqueSection.LastWeek)
    }

    @Test
    fun thisMonth_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisMonth, UniqueSection.ThisMonth)
    }

    @Test
    fun months_areGroupedCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEpochsAreGroupedInSection(december, decemberSection)
    }

    @Test
    fun futureDates_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(future, UniqueSection.Future)
    }

    @Test
    fun allSections_areFound() {
        assertEquals(groupedTransferUi.keys.count(), 7)
    }

    @Test
    fun sections_areOrderCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEquals(groupedTransferUi.keys.elementAt(0), UniqueSection.Future)
        assertEquals(groupedTransferUi.keys.elementAt(1), UniqueSection.Today)
        assertEquals(groupedTransferUi.keys.elementAt(2), UniqueSection.Yesterday)
        assertEquals(groupedTransferUi.keys.elementAt(3), UniqueSection.ThisWeek)
        assertEquals(groupedTransferUi.keys.elementAt(4), UniqueSection.LastWeek)
        assertEquals(groupedTransferUi.keys.elementAt(5), UniqueSection.ThisMonth)
        assertEquals(groupedTransferUi.keys.elementAt(6), decemberSection)
    }

    @Test
    fun sectionsUids_areUnique() {
        val previousUids = mutableListOf<String>()

        TransferSection::class.sealedSubclasses.forEach { subClass ->
            if (subClass == UniqueSection::class) return@forEach

            subClass.objectInstance?.let { instance ->
                // Case: data object
                val uid = instance.uid
                assert(uid !in previousUids)

                previousUids.add(uid)
            } ?: run {
                // Case: data class with one parameter
                val oneParameterConstructor = subClass
                    .constructors
                    .firstOrNull { it.parameters.count() == 1 }

                assertNotNull("This test need to create instances of this class ${subClass.simpleName}", oneParameterConstructor)

                val firstInstanceUid = oneParameterConstructor!!.call("aaa").uid
                assert(firstInstanceUid !in previousUids)
                previousUids.add(firstInstanceUid)

                val secondInstanceUid = oneParameterConstructor.call("bbb").uid
                assert(secondInstanceUid !in previousUids)
                previousUids.add(secondInstanceUid)
            }
        }

        UniqueSection::class.sealedSubclasses.forEach { subClass ->
            // Case: data object
            val instance = subClass.objectInstance
            assertNotNull("This test need to create instances of this class ${subClass.simpleName}", subClass.objectInstance)

            val uid = instance!!.uid
            assert(uid !in previousUids)

            previousUids.add(uid)
        }
    }

    private fun assertEpochsAreGroupedInSection(epochs: List<Long>, section: TransferSection) {
        val transfers = groupedTransferUi[section]
        assertNotNull(transfers)
        assert(epochs.all { epochSecond -> transfers!!.any { it.createdDateTimestamp == epochSecond } })
    }

    private companion object {
        val groupedTransferUi = listOf(december, lastWeek, thisMonth, thisWeek, yesterday, today, future)
            .flatten()
            .sortedDescending()
            .map { day -> day.toTransferUi() }
            .groupBySection(today = today.single().toLocalDate())

        fun Long.toTransferUi(): TransferUi {
            return TransferUi(
                uuid = "",
                createdDateTimestamp = this,
                expirationDateTimestamp = 0,
                sizeUploaded = 0,
                downloadLimit = 0,
                downloadLeft = 0,
                message = "",
                password = "",
                files = emptyList(),
            )
        }
    }
}
