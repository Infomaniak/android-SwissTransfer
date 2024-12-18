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
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationDestinationUnitTest {

    /**
     * Make sure that NavigationDestination names are not changed without changing the `entries` list, because of minification issue.
     */
    @Test
    fun check_destinations_names_are_correct() {
        val sent = MainNavigation.SentDestination::class.simpleName
        val received = MainNavigation.ReceivedDestination::class.simpleName
        val settings = MainNavigation.SettingsDestination::class.simpleName
        assertEquals(MainNavigation.Companion.destinationsNames.find { it == sent }, sent)
        assertEquals(MainNavigation.Companion.destinationsNames.find { it == received }, received)
        assertEquals(MainNavigation.Companion.destinationsNames.find { it == settings }, settings)
    }
}
