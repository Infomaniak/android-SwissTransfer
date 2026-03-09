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
import com.infomaniak.core.auth.PersistedCurrentUserAccountUtils
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.multiplatform_swisstransfer.data.STUser
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.swisstransfer.ui.MainApplication
import dagger.hilt.android.qualifiers.ApplicationContext
import io.sentry.Sentry
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton
import io.sentry.protocol.User as SentryUser

@Singleton
class AccountUtils @Inject constructor(
    private val accountManager: AccountManager,
    @ApplicationContext context: Context,
) : PersistedCurrentUserAccountUtils(context, MainApplication.userDataCleanableList) {

    suspend fun activate(): Nothing {

        combine(currentUserFlow, AccountPreferences().isOnboardingDoneFlow) { user, isOnboardingDone ->
            user to isOnboardingDone
        }.collect { (currentUser, isOnboardingDone) ->
            Sentry.setUser(SentryUser().apply {
                id = currentUser?.id?.toString() ?: "-1"
                email = currentUser?.email
            })

            val stUser: STUser = currentUser.toStUser().takeIf { isOnboardingDone } ?: return@collect

            accountManager.loadUser(stUser)
        }
        awaitCancellation() // Unreachable because currentUserFlow is infinite.
    }

    suspend fun loginGuestUser() {
        accountManager.loadUser(STUser.GuestUser)
    }

    override suspend fun removeUser(userId: Int) {
        super.removeUser(userId)
        accountManager.logoutCurrentUser(newSTUser = userDao.getFirst().toStUser())


        if (currentUserIdFlow.first() == null) loginGuestUser()
    }

    private fun User?.toStUser(): STUser = when (this) {
        null -> STUser.GuestUser
        else -> STUser.AuthUser(id = id.toLong(), token = apiToken.accessToken)
    }

    suspend fun isUserConnected(): Boolean = AccountPreferences().isOnboardingDone
}
