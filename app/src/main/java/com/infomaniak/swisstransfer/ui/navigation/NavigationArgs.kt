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

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 */
sealed class MainNavigation private constructor() : NavigationArgs() {
    data object SentNavArgs : MainNavigation()
    data object ReceivedNavArgs : MainNavigation()
    data class TransferDetailsNavArgs(val transferId: Int) : MainNavigation()

    data object SettingsNavArgs : MainNavigation()
}

/**
 * Sealed class representing the navigation arguments for the new transfer flow.
 */
sealed class NewTransferNavigation private constructor() : NavigationArgs() {
    data object ImportFilesNavArgs : NavigationArgs()
    data object TransferTypeNavArgs : NavigationArgs()
    data object TransferOptionsNavArgs : NavigationArgs()
    data object ValidateUserEmailNavArgs : NavigationArgs()

    data object UploadProgressNavArgs : NavigationArgs()
    data object UploadSuccessNavArgs : NavigationArgs()
}

/**
 * Sealed class representing navigation arguments with a title resource.
 */
sealed class NavigationArgs
