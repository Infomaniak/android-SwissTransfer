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

import android.annotation.SuppressLint
import android.content.Context
import androidx.navigation.NavDestination
import com.infomaniak.matomo.Matomo
import com.infomaniak.swisstransfer.BuildConfig
import org.matomo.sdk.Tracker

object MatomoSwissTransfer : Matomo {

    override val Context.tracker: Tracker get() = buildTracker() // TODO: Fetch appSettings for opt-out
    override val siteId: Int = 24

    // This `SuppressLint` is there so the CI can build
    @SuppressLint("RestrictedApi")
    fun Context.trackDestination(navDestination: NavDestination) = with(navDestination) {
        route?.substringAfter("${BuildConfig.APPLICATION_ID}.ui.navigation.")?.let { path ->
            trackScreen(path)
        }
    }
}
