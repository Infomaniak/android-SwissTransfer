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

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.utils.FORMAT_HOUR_MINUTES
import com.infomaniak.core.utils.endOfTheDay
import com.infomaniak.core.utils.format
import com.infomaniak.core.utils.startOfTheDay
import com.infomaniak.core.utils.tomorrow
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.multiplatform_swisstransfer.common.utils.DateUtils.SECONDS_IN_A_DAY
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlinx.datetime.Clock

enum class ExpiryStatus(@StringRes val stringId: Int) {
    EXPIRED(R.string.expired), // Has expired
    EXPIRES_TODAY(R.string.expiresAt), // Will expire today
    EXPIRES_TOMORROW(R.string.expiresTomorrow), // Will expire tomorrow
    NOT_EXPIRED(R.string.expiresIn); // Will expire someday after tomorrow

    @Composable
    fun getText(transfer: TransferUi): String {
        val expiry = transfer.expirationDateTimestamp.toDateFromSeconds()
        return when (this) {
            EXPIRED -> stringResource(stringId)
            EXPIRES_TODAY -> stringResource(stringId, expiry.format(FORMAT_HOUR_MINUTES))
            EXPIRES_TOMORROW -> stringResource(stringId)
            NOT_EXPIRED -> stringResource(stringId, transfer.daysBeforeExpiry)
        }
    }
}

fun TransferUi.expiryStatus(): ExpiryStatus {
    val expiry = expirationDateTimestamp.toDateFromSeconds()
    val now = Clock.System.now().epochSeconds.toDateFromSeconds()
    val tomorrow = now.tomorrow()
    return when {
        isExpired -> ExpiryStatus.EXPIRED
        expiry in now..now.endOfTheDay() -> ExpiryStatus.EXPIRES_TODAY
        expiry in tomorrow.startOfTheDay()..tomorrow.endOfTheDay() -> ExpiryStatus.EXPIRES_TOMORROW
        else -> ExpiryStatus.NOT_EXPIRED
    }
}

val TransferUi.daysBeforeExpiry: Int
    get() {

        fun Long.epochSecondsToDays(): Int = (toDateFromSeconds().startOfTheDay().time / 1_000L / SECONDS_IN_A_DAY).toInt()

        val expiry = expirationDateTimestamp.epochSecondsToDays()
        val now = Clock.System.now().epochSeconds.epochSecondsToDays()

        return expiry - now
    }

val TransferUi.isExpired: Boolean
    get() {
        val expiry = expirationDateTimestamp.toDateFromSeconds()
        val now = Clock.System.now().epochSeconds.toDateFromSeconds()
        return expiry.before(now) || downloadLeft <= 0
    }

@Composable
fun TransferUi.getFormattedExpiry(): Pair<String, Color> {
    val text = expiryStatus().getText(transfer = this)
    val color = if (isExpired) SwissTransferTheme.materialColors.error else SwissTransferTheme.colors.secondaryTextColor
    return text to color
}

@Composable
fun TransferUi.getWholeDate(): String {
    val expiry = expirationDateTimestamp.toDateFromSeconds()
    return stringResource(
        R.string.expiresOn,
        expiry.format(),
        expiry.format(FORMAT_HOUR_MINUTES),
    )
}
