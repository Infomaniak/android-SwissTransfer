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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.swisstransfer.ui.utils

import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import splitties.experimental.ExperimentalSplittiesApi
import splitties.preferences.Preferences
import splitties.preferences.SuspendPrefsAccessor

class AccountPreferences private constructor(): Preferences(name = "AccountPreferences") {
    companion object : SuspendPrefsAccessor<AccountPreferences>(::AccountPreferences) {
        private const val GUEST_USER_ID = 0
        private const val NO_USER = -1
    }

    val isOnboardingDoneFlow: Flow<Boolean>
    var isOnboardingDone by boolPref(key = "isOnboardingDone", defaultValue = false).also {
        isOnboardingDoneFlow = it.valueFlow()
    }

    init {
        migrateOldDataIfNeeded()
    }

    private fun migrateOldDataIfNeeded() {
        /**
         * This used to be currentUserId but in the end, the data of the current user is stored inside of
         * [com.infomaniak.core.auth.PersistedCurrentUserAccountUtils].
         * The value behind [legacyCurrentGuestUserIdKey] will always contain the guest user
         * id if the onboarding is done. No other user id value has ever been stored here.
         */
        val legacyCurrentGuestUserIdKey = "currentUserId"
        if (legacyCurrentGuestUserIdKey in prefs) {
            isOnboardingDone = prefs.getInt(legacyCurrentGuestUserIdKey, NO_USER) == GUEST_USER_ID
            prefs.edit(commit = true) { remove(legacyCurrentGuestUserIdKey) }
        }
    }
}
