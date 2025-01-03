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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

open class DataStoreValue<T>(val dataStoreKey: Preferences.Key<T>, val defaultValue: T)

@Composable
fun <T> DataStore<Preferences>.collectAsStateWithLifecycle(preference: DataStoreValue<T>, initialValue: T): State<T> {
    return flowOf(preference).collectAsStateWithLifecycle(initialValue, LocalLifecycleOwner.current)
}

private fun <T> DataStore<Preferences>.flowOf(preference: DataStoreValue<T>): Flow<T> {
    return data.map { it[preference.dataStoreKey] ?: preference.defaultValue }
}

suspend fun <T : Any> DataStore<Preferences>.setValue(preference: DataStoreValue<T>, value: T) {
    edit { it[preference.dataStoreKey] = value }
}

fun <T : Any> DataStore<Preferences>.getValue(preference: DataStoreValue<T>): T {
    return runBlocking { flowOf(preference).first() }
}
