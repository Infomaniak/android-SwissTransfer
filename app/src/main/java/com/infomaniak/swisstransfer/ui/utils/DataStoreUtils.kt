/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

open class DataStoreValue<T>(val dataStoreKey: Preferences.Key<T>, val defaultValue: T)

private fun <T> DataStore<Preferences>.flowOf(preference: DataStoreValue<T>): Flow<T> {
    return data.map { it[preference.dataStoreKey] ?: preference.defaultValue }
}

fun <T : Any> DataStore<Preferences>.getValue(preference: DataStoreValue<T>): T = runBlocking { flowOf(preference).first() }

operator fun <T : Any> Preferences.get(preference: DataStoreValue<T>): T = get(preference.dataStoreKey) ?: preference.defaultValue

operator fun <T : Any> MutablePreferences.set(preference: DataStoreValue<T>, value: T) {
    set(preference.dataStoreKey, value)
}