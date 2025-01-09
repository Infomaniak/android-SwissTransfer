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
import androidx.compose.ui.text.capitalize
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R
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
                SpecificSection.Today.containsTransfer(transfer, today) -> SpecificSection.Today
                SpecificSection.Yesterday.containsTransfer(transfer, today) -> SpecificSection.Yesterday
                SpecificSection.ThisWeek.containsTransfer(transfer, today) -> SpecificSection.ThisWeek
                SpecificSection.LastWeek.containsTransfer(transfer, today) -> SpecificSection.LastWeek
                SpecificSection.ThisMonth.containsTransfer(transfer, today) -> SpecificSection.ThisMonth
                else -> TransferSection.ByMonth(getMonthNameFromEpoch(transfer.createdDateTimestamp))
            }
        }
    }

    sealed class TransferSection(val title: Context.() -> String) {
        data class ByMonth(val monthName: String) : TransferSection({ monthName })
    }

    sealed class SpecificSection(
        @StringRes val titleRes: Int,
        private val contains: (LocalDate, LocalDate) -> Boolean,
    ) : TransferSection(title = { getString(titleRes) }) {

        data object Today : SpecificSection(R.string.transferListSectionToday, { date, today ->
            date.isEqual(today)
        })

        data object Yesterday : SpecificSection(R.string.transferListSectionYesterday, { date, today ->
            val yesterday = today.minusDays(1)
            date.isEqual(yesterday)
        })

        data object ThisWeek : SpecificSection(R.string.transferListSectionThisWeek, { date, today ->
            val startOfWeek = today.firstDayOfWeek()
            val endOfWeek = startOfWeek.plusDays(6)
            date.isAfter(startOfWeek.minusDays(1)) && date.isBefore(endOfWeek.plusDays(1))
        })

        data object LastWeek : SpecificSection(R.string.transferListSectionLastWeek, { date, today ->
            val startOfLastWeek = today.minusWeeks(1).firstDayOfWeek()
            val endOfLastWeek = startOfLastWeek.plusDays(6)
            date.isAfter(startOfLastWeek.minusDays(1)) && date.isBefore(endOfLastWeek.plusDays(1))
        })

        data object ThisMonth : SpecificSection(R.string.transferListSectionThisMonth, { date, today ->
            date.year == today.year && date.month == today.month
        })


        fun containsTransfer(transferUi: TransferUi, today: LocalDate): Boolean {
            return contains(transferUi.createdDateTimestamp.toLocalDate(), today)
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
