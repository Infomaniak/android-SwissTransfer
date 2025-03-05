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
package com.infomaniak.swisstransfer

import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NewTransferNavigation
import org.junit.Assert.assertNotNull
import org.junit.Test

class NavigationDestinationUnitTest {

    /**
     * Make sure that MainNavigationDestination names are not changed without changing the `entries` list,
     * because of minification issue.
     */
    @Test
    fun check_main_destinations_names_are_correct() {
        val destinationsNames = MainNavigation.Companion.mainDestinationsNames
        assertNotNull(destinationsNames.find { it == MainNavigation.SentDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == MainNavigation.ReceivedDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == MainNavigation.SettingsDestination::class.simpleName })
    }

    /**
     * Make sure that NewTransferNavigationDestination names are not changed without changing the `entries` list,
     * because of minification issue.
     */
    @Test
    fun check_new_transfer_destinations_names_are_correct() {
        val destinationsNames = NewTransferNavigation.Companion.newTransferDestinationsNames
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.ImportFilesDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.ValidateUserEmailDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.UploadProgressDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.UploadSuccessDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.UploadIntegrityErrorDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.UploadIntegrityErrorDestination::class.simpleName })
        assertNotNull(destinationsNames.find { it == NewTransferNavigation.NewTransferFilesDetailsDestination::class.simpleName })
    }
}
