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

import android.content.Context
import android.icu.text.NumberFormat
import android.text.format.Formatter
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import com.infomaniak.swisstransfer.R

object HumanReadableSizeUtils {
    fun getHumanReadableSize(context: Context, sizeInBytes: Long): String {
        return Formatter.formatShortFileSize(context, sizeInBytes)
    }

    @Composable
    fun formatSpaceLeft(humanReadableSize: () -> String): String {
        val formattedSize = humanReadableSize()
        val quantity = LocalContext.current.getQuantityFromHumanReadableSize(formattedSize)
        return pluralStringResource(R.plurals.transferSpaceLeft, quantity, formattedSize)
    }

    private fun Context.getQuantityFromHumanReadableSize(humanReadableSize: String): Int {
        // Space character for languages such as EN and NBSP character for languages such as FR
        val sizeParts = humanReadableSize.split(' ', Typography.nbsp)

        return if (sizeParts.size == 2) {
            val local = resources.configuration.getLocales().get(0)
            val parsedNumber = NumberFormat.getInstance(local).parse(sizeParts[0])
            parsedNumber?.toDouble()?.toInt() ?: 0
        } else {
            0
        }
    }
}
