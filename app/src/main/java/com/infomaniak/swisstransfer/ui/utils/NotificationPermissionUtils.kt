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

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

object NotificationPermissionUtils {

    @RequiresApi(33)
    private val permission = Manifest.permission.POST_NOTIFICATIONS

    fun hasNotificationPermission(context: Context): Boolean {
        return when {
            SDK_INT < 33 -> true
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED -> true
            else -> false
        }
    }

    fun askNotificationPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
        if (SDK_INT >= 33) {
            // TODO: Manage with a custom dialog if not handled by the system
            launcher.launch(permission)
        }
    }
}
