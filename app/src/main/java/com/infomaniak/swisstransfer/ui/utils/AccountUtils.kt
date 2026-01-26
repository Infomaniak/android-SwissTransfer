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
import android.database.sqlite.SQLiteConstraintException
import com.infomaniak.core.auth.PersistedCurrentUserAccountUtils
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.swisstransfer.ui.MainApplication
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton
import io.sentry.protocol.User as SentryUser

@Singleton
class AccountUtils @Inject constructor(
    private val accountManager: AccountManager,
    private val accountPreferences: AccountPreferences,
    @ApplicationContext context: Context,
) : PersistedCurrentUserAccountUtils(context, MainApplication.userDataCleanableList) {

    suspend fun init() {
        accountPreferences.currentUserId?.let {
            accountManager.loadUser(it)
        }

        Sentry.setUser(SentryUser().apply { id = "-1" })
        currentUserFlow.collect { user ->
            Sentry.setUser(SentryUser().apply {
                id = user?.id?.toString() ?: "-1"
                email = user?.email
            })
        }
    }

    // TODO: Handle guest user login
    suspend fun loginGuestUser() {
        accountPreferences.currentUserId = GUEST_USER_ID
        accountManager.loadUser(GUEST_USER_ID)
    }

    /**
     * @throws SQLiteConstraintException when adding a user with a primary key that already exists
     */
    override suspend fun addUser(user: User) {
        super.addUser(user)
        accountManager.loadUser(user.id)
    }

    override suspend fun removeUser(userId: Int) {
        super.removeUser(userId)
        accountManager.removeUser(userId)

        if (currentUserIdFlow.first() == null) loginGuestUser()
    }

    fun isUserConnected(): Boolean = accountPreferences.currentUserId != null // TODO: Handle guest user login

    companion object {
        private const val GUEST_USER_ID = 0
    }
}
