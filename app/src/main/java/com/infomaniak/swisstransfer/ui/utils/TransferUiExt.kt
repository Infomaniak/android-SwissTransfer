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

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.R

val TransferUi.isExpired: Boolean get() = expiresInDays < 0 || downloadLeft <= 0

@Composable
fun TransferUi.getFormattedExpiry(): String = when (expiresInDays) {
    0 -> stringResource(R.string.expiresToday)
    1 -> stringResource(R.string.expiresTomorrow)
    else -> stringResource(R.string.expiresIn, expiresInDays)
}
