/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.main.transfers

import android.content.Context
import androidx.annotation.StringRes
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.UniqueSection.Companion.isIn
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

object TransfersGroupingManager {

    fun List<TransferUi>.groupBySection(today: LocalDate = LocalDate.now()): Map<TransferSection, List<TransferUi>> {
        return groupBy { transfer ->
            when {
                transfer.isIn(UniqueSection.Future, relativeTo = today) -> UniqueSection.Future
                transfer.isIn(UniqueSection.Today, relativeTo = today) -> UniqueSection.Today
                transfer.isIn(UniqueSection.Yesterday, relativeTo = today) -> UniqueSection.Yesterday
                transfer.isIn(UniqueSection.ThisWeek, relativeTo = today) -> UniqueSection.ThisWeek
                transfer.isIn(UniqueSection.LastWeek, relativeTo = today) -> UniqueSection.LastWeek
                transfer.isIn(UniqueSection.ThisMonth, relativeTo = today) -> UniqueSection.ThisMonth
                else -> TransferSection.ByMonth(getMonthNameFromEpoch(transfer.createdDateTimestamp))
            }
        }
    }

    sealed class TransferSection(val title: Context.() -> String) {
        val uid get() = toString()

        data class ByMonth(val monthName: String) : TransferSection({ monthName })
    }

    sealed class UniqueSection(
        @StringRes val titleRes: Int,
        private val contains: (LocalDate, LocalDate) -> Boolean,
    ) : TransferSection(title = { getString(titleRes) }) {

        data object Future : UniqueSection(R.string.transferListSectionFuture, { date, today ->
            date.isAfter(today)
        })

        data object Today : UniqueSection(R.string.transferListSectionToday, { date, today ->
            date.isEqual(today)
        })

        data object Yesterday : UniqueSection(R.string.transferListSectionYesterday, { date, today ->
            val yesterday = today.minusDays(1)
            date.isEqual(yesterday)
        })

        data object ThisWeek : UniqueSection(R.string.transferListSectionThisWeek, { date, today ->
            val startOfWeek = today.firstDayOfWeek()
            val endOfWeek = startOfWeek.plusDays(6)
            date.isAfter(startOfWeek.minusDays(1)) && date.isBefore(endOfWeek.plusDays(1))
        })

        data object LastWeek : UniqueSection(R.string.transferListSectionLastWeek, { date, today ->
            val startOfLastWeek = today.minusWeeks(1).firstDayOfWeek()
            val endOfLastWeek = startOfLastWeek.plusDays(6)
            date.isAfter(startOfLastWeek.minusDays(1)) && date.isBefore(endOfLastWeek.plusDays(1))
        })

        data object ThisMonth : UniqueSection(R.string.transferListSectionThisMonth, { date, today ->
            date.year == today.year && date.month == today.month
        })

        companion object {
            fun TransferUi.isIn(section: UniqueSection, relativeTo: LocalDate): Boolean {
                return section.contains(createdDateTimestamp.toLocalDate(), relativeTo)
            }
        }
    }

    fun Long.toLocalDate(): LocalDate = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDate()

    // TODO: Adapt the first day of the week according to the local
    private fun LocalDate.firstDayOfWeek(): LocalDate = with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    fun getMonthNameFromEpoch(epochSeconds: Long): String {
        val localDate = epochSeconds.toLocalDate()
        return localDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault()).replaceFirstChar { it.uppercase() }
    }
}
