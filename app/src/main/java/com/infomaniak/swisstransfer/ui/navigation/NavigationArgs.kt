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

package com.infomaniak.swisstransfer.ui.navigation

import androidx.annotation.StringRes
import com.infomaniak.swisstransfer.R

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 *
 * @property title The resource ID of the title string.
 */
sealed class MainNavigation private constructor(@StringRes title: Int) : NavigationArgs(title) {
    data object SentNavArgs : MainNavigation(R.string.appName)
    data object ReceivedNavArgs : MainNavigation(R.string.appName)
    data class TransferDetailsNavArgs(val transferId: Int) : MainNavigation(R.string.appName)

    data object SettingsNavArgs : MainNavigation(R.string.appName)
}

/**
 * Sealed class representing the navigation arguments for the new transfer flow.
 *
 * @property title The resource ID of the title string.
 */
sealed class NewTransferNavigation private constructor(@StringRes title: Int) : NavigationArgs(title) {
    data object ImportFilesNavArgs : NavigationArgs(R.string.appName)
    data object TransferTypeNavArgs : NavigationArgs(R.string.appName)
    data object TransferOptionsNavArgs : NavigationArgs(R.string.appName)
    data object ValidateUserEmailNavArgs : NavigationArgs(R.string.appName)

    data object UploadProgressNavArgs : NavigationArgs(R.string.appName)
    data object UploadSuccessNavArgs : NavigationArgs(R.string.appName)
}

/**
 * Sealed class representing navigation arguments with a title resource.
 *
 * @property title The resource ID of the title string.
 */
sealed class NavigationArgs protected constructor(@StringRes val title: Int)
