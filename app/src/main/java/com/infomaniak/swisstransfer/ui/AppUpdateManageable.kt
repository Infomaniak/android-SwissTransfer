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
package com.infomaniak.swisstransfer.ui

import android.content.Context
import com.infomaniak.core.inappupdate.updatemanagers.InAppUpdateManager
import com.infomaniak.swisstransfer.ui.utils.showToast
import com.infomaniak.core.inappupdate.R as RInAppUpdate

interface AppUpdateManageable {

    val inAppUpdateRequiredManager: InAppUpdateManager
    // val inAppUpdateNotRequiredManager: InAppUpdateManager

    fun Context.initAppUpdateRequiredManager() = inAppUpdateRequiredManager.init(
        mustRequireImmediateUpdate = true,
        onInstallFailure = {
            showToast(RInAppUpdate.string.errorUpdateInstall)
        }
    )

    // TODO: Do not use twice the same object.
    // fun initAppUpdateNotRequiredManager() = inAppUpdateNotRequiredManager.init(
    //     mustRequireImmediateUpdate = false,
    // )
}
