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
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.SpecificSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.TransferSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.getMonthNameFromEpoch
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TransferSectionGroupingTest {
    @Test
    fun today_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(today, SpecificSection.Today)
    }

    @Test
    fun yesterday_isGroupedCorrectly() {
        assertEpochsAreGroupedInSection(yesterday, SpecificSection.Yesterday)
    }

    @Test
    fun thisWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisWeek, SpecificSection.ThisWeek)
    }

    @Test
    fun lastWeek_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(lastWeek, SpecificSection.LastWeek)
    }

    @Test
    fun thisMonth_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(thisMonth, SpecificSection.ThisMonth)
    }

    @Test
    fun months_areGroupedCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEpochsAreGroupedInSection(december, decemberSection)
    }

    @Test
    fun futureDates_areGroupedCorrectly() {
        assertEpochsAreGroupedInSection(future, SpecificSection.Future)
    }

    @Test
    fun allSections_areFound() {
        assertEquals(groupedTransferUi.keys.count(), 7)
    }

    @Test
    fun sections_areOrderCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEquals(groupedTransferUi.keys.elementAt(0), SpecificSection.Future)
        assertEquals(groupedTransferUi.keys.elementAt(1), SpecificSection.Today)
        assertEquals(groupedTransferUi.keys.elementAt(2), SpecificSection.Yesterday)
        assertEquals(groupedTransferUi.keys.elementAt(3), SpecificSection.ThisWeek)
        assertEquals(groupedTransferUi.keys.elementAt(4), SpecificSection.LastWeek)
        assertEquals(groupedTransferUi.keys.elementAt(5), SpecificSection.ThisMonth)
        assertEquals(groupedTransferUi.keys.elementAt(6), decemberSection)
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
