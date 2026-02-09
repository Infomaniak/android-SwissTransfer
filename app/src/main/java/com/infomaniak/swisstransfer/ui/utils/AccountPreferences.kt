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
import com.infomaniak.core.sharedvalues.SharedValues
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountPreferences @Inject constructor(@ApplicationContext private val appContext: Context) : SharedValues {

    override val sharedPreferences = appContext.applicationContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)!!

    /**
     * This used to be currentUserId but in the end, the data of the current user is stored inside of
     * [com.infomaniak.core.auth.PersistedCurrentUserAccountUtils]. The [_currentGuestUserId] will always contain the guest user
     * id if the onboarding is done, even if another real [User] account is connected and currently selected. No other user id has
     * ever been stored here.
     */
    private var _currentGuestUserId by sharedValue("currentUserId", NO_USER)
    var isOnboardingDone
        get() = _currentGuestUserId != NO_USER
        set(value) {
            _currentGuestUserId = if (value) GUEST_USER_ID else NO_USER
        }

    companion object {
        private const val SHARED_PREFS_NAME = "AccountPreferences"
        private const val NO_USER = -1
        const val GUEST_USER_ID = 0
    }
}
