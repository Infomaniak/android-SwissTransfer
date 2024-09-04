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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationRail
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownCircle
import com.infomaniak.swisstransfer.ui.images.icons.ArrowUpCircle
import com.infomaniak.swisstransfer.ui.images.icons.Settings
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.*

/**
 * Enum class representing the different destinations in the app's [BottomAppBar] or [NavigationRail].
 *
 * @property label The resource ID of the string label for the destination.
 * @property icon The icon to be displayed for the destination.
 */
enum class NavigationItem(
    @StringRes val label: Int,
    val icon: ImageVector,
    val destination: MainNavigation,
) {
    SENT(R.string.sentTitle, AppIcons.ArrowUpCircle, SentDestination),
    RECEIVED(R.string.receivedTitle, AppIcons.ArrowDownCircle, ReceivedDestination),
    SETTINGS(R.string.settingsTitle, AppIcons.Settings, SettingsDestination),
}
