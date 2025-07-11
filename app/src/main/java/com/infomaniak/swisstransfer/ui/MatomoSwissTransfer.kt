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
import com.infomaniak.core.matomo.Matomo.TrackerAction
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoCategory
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import org.matomo.sdk.Tracker

object MatomoSwissTransfer : Matomo {

    override val siteId: Int = 24
    override val tracker: Tracker by lazy(::buildTracker)

    fun trackScreen(screen: MatomoScreen) {
        trackScreen(path = "/$screen", title = screen.toString())
    }

    fun trackEvent(
        category: MatomoCategory,
        name: MatomoName,
        action: TrackerAction = TrackerAction.CLICK,
        value: Float? = null
    ) {
        trackEvent(category.categoryName, name.eventName, action, value)
    }

    fun trackTransferTypeEvent(name: String) {
        trackEvent(MatomoCategory.TransferType.categoryName, name)
    }

    fun trackAppUpdateEvent(name: MatomoName) {
        trackEvent(MatomoCategory.AppUpdate, name)
    }

    fun trackNewTransferDataEvent(name: String) {
        trackEvent(MatomoCategory.NewTransferData.categoryName, name)
    }

    fun trackNewTransferEvent(name: MatomoName) {
        trackEvent(MatomoCategory.NewTransfer, name)
    }

    fun trackSettingsGlobalEmailLanguageEvent(name: String) {
        trackEvent(MatomoCategory.SettingsGlobalEmailLanguage.categoryName, name)
    }

    fun trackSettingsLocalEmailLanguageEvent(name: String) {
        trackEvent(MatomoCategory.SettingsLocalEmailLanguage.categoryName, name)
    }

    fun trackSettingsGlobalValidityPeriodEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsGlobalValidityPeriod, name)
    }


    fun trackSettingsLocalValidityPeriodEvent(name: MatomoName?) {
        trackEvent(MatomoCategory.SettingsLocalValidityPeriod, name!!)
    }

    fun trackSettingsGlobalDownloadLimitEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsGlobalDownloadLimit, name)
    }

    fun trackSettingsLocalDownloadLimitEvent(name: MatomoName?) {
        trackEvent(MatomoCategory.SettingsLocalDownloadLimit, name!!)
    }

    fun trackSettingsLocalPasswordEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsLocalPassword, name)
    }

    fun trackSettingsGlobalThemeEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsLocalValidityPeriod, name)
    }

    fun trackSentTransferEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsGlobalDownloadLimit, name)
    }

    fun trackReceivedTransferEvent(name: MatomoName) {
        trackEvent(MatomoCategory.SettingsLocalValidityPeriod, name)
    }
}
