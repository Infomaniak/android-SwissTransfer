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
package com.infomaniak.swisstransfer.ui

import com.infomaniak.core.matomo.Matomo
import com.infomaniak.swisstransfer.ui.utils.DataManagementPreferences
import com.infomaniak.swisstransfer.ui.utils.dataManagementDataStore
import com.infomaniak.swisstransfer.ui.utils.get
import com.infomaniak.swisstransfer.ui.utils.getPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.matomo.sdk.Tracker
import splitties.init.appCtx

object MatomoSwissTransfer : Matomo {

    enum class MatomoScreen {
        Sent,
        Received,
        Settings,
        NewTransfer,
        NewTransferFileList,
        VerifyMail,
        UploadProgress,
        UploadError,
        UploadSuccess,
        SentTransferDetails,
        ReceivedTransferDetails,
        TransferDetailsFileList,
        ThemeSetting,
        ValidityPeriodSetting,
        DownloadLimitSetting,
        EmailLanguageSetting;

        override fun toString() = "${name}View"
    }

    private val scope = CoroutineScope(Dispatchers.Default)

    override val tracker: Tracker = with(appCtx) {
        buildTracker(shouldOptOut = dataManagementDataStore.getPreference(DataManagementPreferences.IsMatomoAuthorized).not())
    }

    override val siteId: Int = 24

    init {
        scope.launch {
            with(appCtx) {
                dataManagementDataStore.data.collect {
                    tracker.isOptOut = it[DataManagementPreferences.IsMatomoAuthorized].not()
                }
            }
        }
    }

    fun trackScreen(screen: MatomoScreen) {
        trackScreen(path = "/$screen", title = screen.toString())
    }

    fun trackTransferTypeEvent(name: String) {
        trackEvent("transferType", name)
    }
}
