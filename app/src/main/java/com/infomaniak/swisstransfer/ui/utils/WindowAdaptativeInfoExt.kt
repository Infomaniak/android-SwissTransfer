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
package com.infomaniak.swisstransfer.ui.utils

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Determines if the current window is classified as a large window suitable for tablet devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is large (tablet), `false` otherwise.
 */
fun WindowAdaptiveInfo.isWindowLarge(): Boolean = with(windowSizeClass) {
    return windowWidthSizeClass == WindowWidthSizeClass.EXPANDED && windowHeightSizeClass != WindowHeightSizeClass.COMPACT
}

/**
 * Determines if the current window is classified as a small window suitable for mobile devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is small (mobile), `false` otherwise.
 */
fun WindowAdaptiveInfo.isWindowSmall(): Boolean = !isWindowLarge()
