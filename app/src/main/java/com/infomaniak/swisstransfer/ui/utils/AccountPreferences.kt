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
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Singleton
class AccountPreferences @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val sharedPreferences = appContext.applicationContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)!!

    private var _currentUserId by sharedValue("currentUserId", NO_USER)
    var currentUserId
        get() = _currentUserId.takeIf { it != NO_USER }
        set(value) {
            _currentUserId = value ?: NO_USER
        }

    //region SharedValues methods to remove
    // TODO: Extend SharedValues when the util class is migrated to Core instead of using these two methods
    private fun sharedValue(key: String, defaultValue: Int): ReadWriteProperty<Any, Int> = with(sharedPreferences) {
        return object : ReadWriteProperty<Any, Int> {
            override fun getValue(thisRef: Any, property: KProperty<*>): Int = getInt(key, defaultValue)
            override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) = transaction { putInt(key, value) }
        }
    }

    private fun SharedPreferences.transaction(block: SharedPreferences.Editor.() -> Unit) {
        with(edit()) {
            block(this)
            apply()
        }
    }
    //endregion

    companion object {
        private const val SHARED_PREFS_NAME = "AccountPreferences"
        private const val NO_USER = -1
    }
}
