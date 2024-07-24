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

import kotlinx.serialization.Serializable

/**
 * Sealed class representing the navigation arguments for the main navigation flow.
 */
@Serializable
sealed class MainNavigation : NavigationDestination() {
    @Serializable
    data object SentDestination : MainNavigation()
    @Serializable
    data object ReceivedDestination : MainNavigation()
    @Serializable
    data class TransferDetailsDestination(val transferId: Int) : MainNavigation()

    @Serializable
    data object SettingsDestination : MainNavigation()
}

/**
 * Sealed class representing the navigation arguments for the new transfer flow.
 */
@Serializable
sealed class NewTransferNavigation : NavigationDestination() {
    @Serializable
    data object ImportFilesDestination : NewTransferNavigation()
    @Serializable
    data object TransferTypeDestination : NewTransferNavigation()
    @Serializable
    data object TransferOptionsDestination : NewTransferNavigation()
    @Serializable
    data object ValidateUserEmailDestination : NewTransferNavigation()

    @Serializable
    data object UploadProgressDestination : NewTransferNavigation()
    @Serializable
    data object UploadSuccessDestination : NewTransferNavigation()
}

/**
 * Sealed class representing navigation arguments with a title resource.
 */
@Serializable
sealed class NavigationDestination {
}
