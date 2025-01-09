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
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.SpecificSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.TransferSection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.getMonthNameFromEpoch
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.groupBySection
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.toLocalDate
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
    fun otherDates_areGroupedCorrectly() {
        val decemberSection = TransferSection.ByMonth(getMonthNameFromEpoch(december.first()))
        assertEpochsAreGroupedInSection(december, decemberSection)

        val februarySection = TransferSection.ByMonth(getMonthNameFromEpoch(february.first()))
        assertEpochsAreGroupedInSection(february, februarySection)
    }

    private fun assertEpochsAreGroupedInSection(epochs: List<Long>, section: TransferSection) {
        val transfers = groupedTransferUi[section]
        assertNotNull(transfers)
        assert(epochs.all { epochSecond -> transfers!!.any { it.createdDateTimestamp == epochSecond } })
    }

    private companion object {
        val december = listOf(
            1733819401L, // 2024-12-10 TUESDAY
            1733905801L, // 2024-12-11 WEDNESDAY
            1733992201L, // 2024-12-12 THURSDAY
            1734078601L, // 2024-12-13 FRIDAY
            1734165001L, // 2024-12-14 SATURDAY
            1734251401L, // 2024-12-15 SUNDAY
            1734337801L, // 2024-12-16 MONDAY
            1734424201L, // 2024-12-17 TUESDAY
            1734510601L, // 2024-12-18 WEDNESDAY
            1734597001L, // 2024-12-19 THURSDAY
            1734683401L, // 2024-12-20 FRIDAY
            1734769801L, // 2024-12-21 SATURDAY
            1734856201L, // 2024-12-22 SUNDAY
            1734942601L, // 2024-12-23 MONDAY
            1735029001L, // 2024-12-24 TUESDAY
            1735115401L, // 2024-12-25 WEDNESDAY
            1735201801L, // 2024-12-26 THURSDAY
            1735288201L, // 2024-12-27 FRIDAY
            1735374601L, // 2024-12-28 SATURDAY
            1735461001L, // 2024-12-29 SUNDAY
        )

        val lastWeek = listOf(
            1735547401L, // 2024-12-30 MONDAY
            1735633801L, // 2024-12-31 TUESDAY
            1735720201L, // 2025-01-01 WEDNESDAY
            1735806601L, // 2025-01-02 THURSDAY
            1735893001L, // 2025-01-03 FRIDAY
            1735979401L, // 2025-01-04 SATURDAY
            1736065801L, // 2025-01-05 SUNDAY
        )

        val thisWeek = listOf(
            1736152201L, // 2025-01-06 MONDAY
            1736238601L, // 2025-01-07 TUESDAY
            1736497801L, // 2025-01-10 FRIDAY
            1736584201L, // 2025-01-11 SATURDAY
            1736670601L, // 2025-01-12 SUNDAY
        )

        val yesterday = listOf(
            1736325001L, // 2025-01-08 WEDNESDAY
        )

        val today = listOf(
            1736411401L, // 2025-01-09 THURSDAY
        )

        val thisMonth = listOf(
            1736757001L, // 2025-01-13 MONDAY
            1736843401L, // 2025-01-14 TUESDAY
            1736929801L, // 2025-01-15 WEDNESDAY
            1737016201L, // 2025-01-16 THURSDAY
            1737102601L, // 2025-01-17 FRIDAY
            1737189001L, // 2025-01-18 SATURDAY
            1737275401L, // 2025-01-19 SUNDAY
            1737361801L, // 2025-01-20 MONDAY
            1737448201L, // 2025-01-21 TUESDAY
            1737534601L, // 2025-01-22 WEDNESDAY
            1737621001L, // 2025-01-23 THURSDAY
            1737707401L, // 2025-01-24 FRIDAY
            1737793801L, // 2025-01-25 SATURDAY
            1737880201L, // 2025-01-26 SUNDAY
            1737966601L, // 2025-01-27 MONDAY
            1738053001L, // 2025-01-28 TUESDAY
            1738139401L, // 2025-01-29 WEDNESDAY
            1738225801L, // 2025-01-30 THURSDAY
            1738312201L, // 2025-01-31 FRIDAY
        )

        val february = listOf(
            1738398601L, // 2025-02-01 SATURDAY
            1738485001L, // 2025-02-02 SUNDAY
            1738571401L, // 2025-02-03 MONDAY
            1738657801L, // 2025-02-04 TUESDAY
            1738744201L, // 2025-02-05 WEDNESDAY
            1738830601L, // 2025-02-06 THURSDAY
            1738917001L, // 2025-02-07 FRIDAY
            1739003401L, // 2025-02-08 SATURDAY
        )

        val groupedTransferUi = listOf(december, lastWeek, thisWeek, yesterday, today, thisMonth, february)
            .flatten()
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
