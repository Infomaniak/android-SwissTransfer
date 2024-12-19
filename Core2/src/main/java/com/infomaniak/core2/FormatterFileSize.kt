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
package com.infomaniak.core2

import android.content.Context
import android.icu.text.DecimalFormat
import android.icu.text.MeasureFormat
import android.icu.text.MeasureFormat.FormatWidth
import android.icu.text.NumberFormat
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import java.math.BigDecimal
import java.util.Locale
import kotlin.math.abs

object FormatterFileSize {

    private const val KIBI_BYTE = 1_024L
    private const val KILO_BYTE = 1_000L

    private const val FLAG_IEC_UNITS = 1 shl 3
    private const val FLAG_SI_UNITS = 1 shl 2
    private const val FLAG_SHORTER = 1 shl 0

    fun Context.formatShortFileSize(bytes: Long, valueOnly: Boolean = false): String {
        return formatFileSize(bytes, FLAG_IEC_UNITS or FLAG_SHORTER, valueOnly)
    }

    private fun Context.formatFileSize(bytes: Long, flags: Int, valueOnly: Boolean): String {

        fun getSuffix(suffixes: MutableList<MeasureUnit>): MeasureUnit? {
            return if (valueOnly) null else suffixes.removeFirstOrNull()
        }

        val suffixes = mutableListOf(
            MeasureUnit.BYTE,
            MeasureUnit.KILOBYTE,
            MeasureUnit.MEGABYTE,
            MeasureUnit.GIGABYTE,
            MeasureUnit.TERABYTE,
        )
        val unit = if (flags and FLAG_IEC_UNITS != 0) KIBI_BYTE else KILO_BYTE
        var multiplier = 1L

        var result = abs(bytes).toFloat()
        val suffixesCount = suffixes.count() - 1
        var suffix = getSuffix(suffixes)

        repeat(suffixesCount) {
            if (result > 900) {
                suffix = getSuffix(suffixes)
                multiplier *= unit
                result /= unit
            }
        }

        val (roundFormat, roundedBytes) = when {
            multiplier == 1L || result >= 100 -> "%.0f" to 0
            result < 1 -> "%.2f" to 2
            result < 10 -> if (flags and FLAG_SHORTER != 0) "%.1f" to 1 else "%.2f" to 2
            else -> if (flags and FLAG_SHORTER != 0) "%.0f" to 0 else "%.2f" to 2 // 10 <= result < 100
        }

        result = abs(result)

        val resultValue = String.format(roundFormat, result)
        val resultValueFormatted = suffix?.let {
            val locale = currentLocale()
            formatMeasureShort(locale, getNumberFormatter(locale, roundedBytes), result, it)
        }

        return resultValueFormatted ?: resultValue
    }

    private fun Context.currentLocale(): Locale = resources.configuration.locales[0]

    private fun getNumberFormatter(locale: Locale, fractionDigits: Int): NumberFormat {
        return NumberFormat.getInstance(locale).apply {
            minimumFractionDigits = fractionDigits
            maximumFractionDigits = fractionDigits
            isGroupingUsed = false
            // We do this only for DecimalFormat, since in the general NumberFormat case,
            // calling setRoundingMode may throw an exception.
            if (this is DecimalFormat) setRoundingMode(BigDecimal.ROUND_HALF_UP)
        }
    }

    private fun formatMeasureShort(locale: Locale, numberFormatter: NumberFormat, value: Float, units: MeasureUnit): String {
        val measureFormatter = MeasureFormat.getInstance(locale, FormatWidth.SHORT, numberFormatter)
        return measureFormatter.format(Measure(value, units))
    }
}
