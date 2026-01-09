/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui

import com.infomaniak.core.auth.CredentialManager
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.auth.room.UserDatabase

class AccountUtils : CredentialManager() {
    override var userDatabase: UserDatabase
        get() = TODO("Not yet implemented")
        set(value) {}
    override var currentUserId: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var currentUser: User?
        get() = TODO("Not yet implemented")
        set(value) {}
}
