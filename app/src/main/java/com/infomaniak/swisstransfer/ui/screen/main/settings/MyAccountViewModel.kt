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
package com.infomaniak.swisstransfer.ui.screen.main.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.multiplatform_swisstransfer.database.models.OrganizationAccount
import com.infomaniak.multiplatform_swisstransfer.managers.AccountManager
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val appSettingsManager: AppSettingsManager,
    private val accountUtils: AccountUtils,
    private val accountManager: AccountManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val appSettingsFlow = appSettingsManager.appSettings
    val users = accountUtils.users

    val selectedOrganization: StateFlow<OrganizationAccount?> = accountManager.selectedOrganizationAccount()
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = null)

    val organizations: StateFlow<List<OrganizationAccount>> = accountManager.currentUserFlow
        .map { user -> user?.let { accountManager.organizationAccountsForUser(it.id) } ?: emptyList() }
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = emptyList())

    fun switchToOrganization(organizationAccountId: Long) = viewModelScope.launch(ioDispatcher) {
        accountManager.switchToOrganization(organizationAccountId)
    }

    fun setTheme(theme: Theme) = viewModelScope.launch(ioDispatcher) {
        appSettingsManager.setTheme(theme)
    }

    fun setValidityPeriod(validityPeriod: ValidityPeriod) = viewModelScope.launch(ioDispatcher) {
        appSettingsManager.setValidityPeriod(validityPeriod)
    }

    fun setDownloadLimit(downloadLimit: DownloadLimit) = viewModelScope.launch(ioDispatcher) {
        appSettingsManager.setDownloadLimit(downloadLimit)
    }

    fun setEmailLanguage(emailLanguage: EmailLanguage) = viewModelScope.launch(ioDispatcher) {
        appSettingsManager.setEmailLanguage(emailLanguage)
    }

    fun disconnectCurrentUser() {
        viewModelScope.launch {
            accountUtils.currentUserIdFlow.first()?.let { accountUtils.removeUser(it) }
        }
    }

    fun switchUser(userId: Int) {
        viewModelScope.launch {
            accountUtils.switchUser(userId)
        }
    }
}
