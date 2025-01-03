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
package com.infomaniak.swisstransfer.ui.screen.main.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.utils.DataManagementPreferences
import com.infomaniak.swisstransfer.ui.utils.dataManagementDataStore
import com.infomaniak.swisstransfer.ui.utils.get
import com.infomaniak.swisstransfer.ui.utils.set
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsMatomoViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    val isMatomoAuthorized = appContext.dataManagementDataStore.data
        .map { it[DataManagementPreferences.IsMatomoAuthorized] }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun setMatomoAuthorization(isAuthorized: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            appContext.dataManagementDataStore.edit {
                it[DataManagementPreferences.IsMatomoAuthorized] = isAuthorized
            }
        }
    }
}
