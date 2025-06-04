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

import androidx.activity.ComponentActivity

private val TRANSFER_DEEPLINK_REGEX = "https://.+/d/([^?]+)(?:\\?delete=(.+))?".toRegex()

fun ComponentActivity.hasValidTransferDeeplink(): Boolean {
    val deeplink = intent?.data?.toString()
    return deeplink?.matches(TRANSFER_DEEPLINK_REGEX) == true
}

fun ComponentActivity.getDeeplinkTransferData(): DeeplinkTransferData? {
    return intent?.data?.let { deeplinkUri ->
        val deeplinkGroupValues = TRANSFER_DEEPLINK_REGEX.find(deeplinkUri.toString())?.groupValues
        val transferUuid = deeplinkGroupValues?.get(1)
        val deleteToken = deeplinkGroupValues?.get(2)
        DeeplinkTransferData(uuid = transferUuid, deleteToken = deleteToken)
    }
}

data class DeeplinkTransferData(val uuid: String?, val deleteToken: String?)
