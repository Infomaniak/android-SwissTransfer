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
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.swisstransfer.di.IoDispatcher
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption.Companion.toAdvancedOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferAdvancedOptionsEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettingsManager: AppSettingsManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val appSettingsFlow = appSettingsManager.appSettings

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

    data class AppSettingsData(
        val validityPeriod: ValidityPeriod,
        val downloadLimit: DownloadLimit,
        val passwordOption: PasswordTransferOption,
        val emailLanguage: EmailLanguage,
    ) {
        fun toAdvancedOptionsList() = listOf<TransferAdvancedOptionsEnum>(
            validityPeriod.toAdvancedOption(),
            downloadLimit.toAdvancedOption(),
            passwordOption,
            emailLanguage.toAdvancedOption(),
        )
    }
}
