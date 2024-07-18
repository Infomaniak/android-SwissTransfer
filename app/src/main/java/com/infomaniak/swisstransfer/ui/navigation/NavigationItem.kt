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

package com.infomaniak.swisstransfer.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationRail
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.swisstransfer.R

/**
 * Enum class representing the different destinations in the app's [BottomAppBar] or [NavigationRail].
 *
 * @property label The resource ID of the string label for the destination.
 * @property icon The icon to be displayed for the destination.
 * @property contentDescription The resource ID of the content description for accessibility.
 */
enum class NavigationItem(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
) {
    SENT(R.string.appName, Icons.AutoMirrored.Filled.Send, R.string.appName),
    RECEIVED(R.string.appName, Icons.Default.Star, R.string.appName),
    SETTINGS(R.string.appName, Icons.Default.Settings, R.string.appName),
}
