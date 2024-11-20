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

import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountUtils @Inject constructor(
    private val accountManager: AccountManager,
    private val accountPreferences: AccountPreferences,
) {

    suspend fun init(userId: Int = DEFAULT_USER_ID) {
        accountPreferences.currentUserId = userId
        accountManager.loadUser(userId)
    }

    suspend fun logout(userId: Int) {
        accountManager.removeUser(userId)
        // TODO: Handle logging as the next available connected user or the DEFAULT_USER_ID
        accountPreferences.currentUserId = DEFAULT_USER_ID
    }

    fun isUserConnected(): Boolean = accountPreferences.currentUserId != null

    companion object {
        const val DEFAULT_USER_ID = 0
    }
}
