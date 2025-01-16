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
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.TransferSectionWithContains
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.getMonthNameFromEpoch
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.reflect.full.primaryConstructor

class TransferSectionGroupingTest {
    @Test
    fun today_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(today, TransferSectionWithContains.Today)
    }

    @Test
    fun yesterday_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(yesterday, TransferSectionWithContains.Yesterday)
    }

    @Test
    fun thisWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisWeek, TransferSectionWithContains.ThisWeek)
    }

    @Test
    fun lastWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(lastWeek, TransferSectionWithContains.LastWeek)
    }

    @Test
    fun thisMonth_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisMonth, TransferSectionWithContains.ThisMonth)
    }

    @Test
    fun months_areGroupedCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEpochsAreGroupedInSection(december, decemberSection)
    }

    @Test
    fun futureDates_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(future, TransferSectionWithContains.Future)
    }

    @Test
    fun allSections_areFound() {
        assertEquals(groupedTransferUi.keys.count(), 7)
    }

    @Test
    fun sections_areOrderCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEquals(groupedTransferUi.keys.elementAt(0), TransferSectionWithContains.Future)
        assertEquals(groupedTransferUi.keys.elementAt(1), TransferSectionWithContains.Today)
        assertEquals(groupedTransferUi.keys.elementAt(2), TransferSectionWithContains.Yesterday)
        assertEquals(groupedTransferUi.keys.elementAt(3), TransferSectionWithContains.ThisWeek)
        assertEquals(groupedTransferUi.keys.elementAt(4), TransferSectionWithContains.LastWeek)
        assertEquals(groupedTransferUi.keys.elementAt(5), TransferSectionWithContains.ThisMonth)
        assertEquals(groupedTransferUi.keys.elementAt(6), decemberSection)
    }

    @Test
    fun sectionsUids_areUnique() {
        val previousUids = mutableListOf<String>()

        TransferSection::class.sealedSubclasses.forEach { subClass ->
            if (subClass == TransferSectionWithContains::class) return@forEach

            subClass.objectInstance?.let { instance ->
                // Case: data object
                val uid = instance.uid
                assert(uid !in previousUids)

                previousUids.add(uid)
            } ?: run {
                // Case: data class with one parameter
                val constructor = subClass.primaryConstructor
                assertNotNull("This test needs to create instances of this class ${subClass.simpleName}", constructor)
                assertEquals(1, constructor?.parameters?.count())

                val firstInstanceUid = constructor!!.call("aaa").uid
                assert(firstInstanceUid !in previousUids)
                previousUids.add(firstInstanceUid)

                val secondInstanceUid = constructor.call("bbb").uid
                assert(secondInstanceUid !in previousUids)
                previousUids.add(secondInstanceUid)
            }
        }

        TransferSectionWithContains::class.sealedSubclasses.forEach { subClass ->
            // Case: data object
            val instance = subClass.objectInstance
            assertNotNull("This test needs to create instances of this class ${subClass.simpleName}", subClass.objectInstance)

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
