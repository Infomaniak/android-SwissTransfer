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

import com.infomaniak.core.auth.CredentialManager
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.auth.room.UserDatabase
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.swisstransfer.ui.MainApplication
import com.infomaniak.swisstransfer.ui.utils.AccountPreferences.Companion.NO_USER
import io.sentry.Sentry
import javax.inject.Inject
import javax.inject.Singleton
import io.sentry.protocol.User as SentryUser

@Singleton
class AccountUtils @Inject constructor(
    private val accountManager: AccountManager,
    private val accountPreferences: AccountPreferences,
) : CredentialManager() {

    override lateinit var userDatabase: UserDatabase

    override var currentUser: User? = null
        set(user) {
            field = user
            Sentry.setUser(SentryUser().apply {
                id = currentUserId.toString()
                email = user?.email
            })
        }

    override var currentUserId: Int = currentUser?.id ?: NO_USER

    suspend fun init() {
        userDatabase = UserDatabase.getDatabase()
        Sentry.setUser(SentryUser().apply { id = currentUserId.toString() })

        accountPreferences.currentUserId?.let {
            accountManager.loadUser(it)
        }
    }

    suspend fun requestCurrentUser(): User? {
        val user = currentUserId.takeIf { it != NO_USER }?.let { getUserById(it) }
        return (user ?: userDatabase.userDao().getFirst()).also { currentUser = it }
    }

    suspend fun loginGuestUser() {
        accountPreferences.currentUserId = GUEST_USER_ID
        accountManager.loadUser(GUEST_USER_ID)
    }

    suspend fun insertUser(user: User) {
        currentUser = user
        val userId = user.id.toLong()
        MainApplication.userDataCleanableList.forEach { it.resetForUser(userId) }
        userDatabase.userDao().insert(user)
        accountManager.loadUser(user.id)
    }

    // TODO: Handle logging as the next available connected user or the DEFAULT_USER_ID
    suspend fun removeUser(user: User) {
        val userId = user.id.toLong()
        MainApplication.userDataCleanableList.forEach { it.resetForUser(userId) }
        userDatabase.userDao().delete(user)
        accountManager.removeUser(user.id)
    }

    fun isUserConnected(): Boolean = accountPreferences.currentUserId != null

    companion object {
        private const val GUEST_USER_ID = 0
    }
}
