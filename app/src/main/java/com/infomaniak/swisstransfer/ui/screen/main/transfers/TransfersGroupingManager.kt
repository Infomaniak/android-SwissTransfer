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
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager.SortedTransfers
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.main.transfers.TransfersGroupingManager.TransferSectionWithContains.Companion.isIn
import java.time.*
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

object TransfersGroupingManager {

    fun SortedTransfers.groupBySection(): Map<TransferSection, List<TransferUi>> {
        return validTransfers.groupBySection() + expiredTransfers.groupBy { TransferSectionWithContains.Expired }
    }

    fun List<TransferUi>.groupBySection(today: LocalDate = LocalDate.now()): Map<TransferSection, List<TransferUi>> {
        return groupBy { transfer ->
            when {
                transfer.isIn(TransferSectionWithContains.Future, relativeTo = today) -> TransferSectionWithContains.Future
                transfer.isIn(TransferSectionWithContains.Today, relativeTo = today) -> TransferSectionWithContains.Today
                transfer.isIn(TransferSectionWithContains.Yesterday, relativeTo = today) -> TransferSectionWithContains.Yesterday
                transfer.isIn(TransferSectionWithContains.ThisWeek, relativeTo = today) -> TransferSectionWithContains.ThisWeek
                transfer.isIn(TransferSectionWithContains.LastWeek, relativeTo = today) -> TransferSectionWithContains.LastWeek
                transfer.isIn(TransferSectionWithContains.ThisMonth, relativeTo = today) -> TransferSectionWithContains.ThisMonth
                else -> {
                    val transferCreatedLocalDate = transfer.createdDateTimestamp.toLocalDate()
                    TransferSection.ByMonth(transferCreatedLocalDate.year, transferCreatedLocalDate.month.value)
                }
            }
        }
    }

    sealed class TransferSection(val title: Context.() -> String) {
        val uid get() = toString()

        data class ByMonth(private val year: Int, private val month: Int) : TransferSection({ getMonthNameFromEpoch(month) })
    }

    sealed class TransferSectionWithContains(
        @StringRes val titleRes: Int,
        private val contains: (date: LocalDate, today: LocalDate) -> Boolean,
    ) : TransferSection(title = { getString(titleRes) }) {

        data object Future : TransferSectionWithContains(R.string.transferListSectionFuture, { date, today ->
            date.isAfter(today)
        })

        data object Today : TransferSectionWithContains(R.string.transferListSectionToday, { date, today ->
            date.isEqual(today)
        })

        data object Yesterday : TransferSectionWithContains(R.string.transferListSectionYesterday, { date, today ->
            val yesterday = today.minusDays(1)
            date.isEqual(yesterday)
        })

        data object ThisWeek : TransferSectionWithContains(R.string.transferListSectionThisWeek, { date, today ->
            val startOfWeek = today.firstDayOfWeek()
            val endOfWeek = startOfWeek.plusDays(6)
            date.isAfter(startOfWeek.minusDays(1)) && date.isBefore(endOfWeek.plusDays(1))
        })

        data object LastWeek : TransferSectionWithContains(R.string.transferListSectionLastWeek, { date, today ->
            val startOfLastWeek = today.minusWeeks(1).firstDayOfWeek()
            val endOfLastWeek = startOfLastWeek.plusDays(6)
            date.isAfter(startOfLastWeek.minusDays(1)) && date.isBefore(endOfLastWeek.plusDays(1))
        })

        data object ThisMonth : TransferSectionWithContains(R.string.transferListSectionThisMonth, { date, today ->
            date.year == today.year && date.month == today.month
        })

        data object Expired : TransferSectionWithContains(R.string.expired, { _, _ -> true })

        companion object {
            fun TransferUi.isIn(section: TransferSectionWithContains, relativeTo: LocalDate): Boolean {
                return section.contains(createdDateTimestamp.toLocalDate(), relativeTo)
            }
        }
    }

    fun Long.toLocalDate(): LocalDate = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDate()

    // TODO: Adapt the first day of the week according to the local
    private fun LocalDate.firstDayOfWeek(): LocalDate = with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    fun getMonthNameFromEpoch(month: Int): String {
        return Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault()).replaceFirstChar { it.uppercase() }
    }
}
