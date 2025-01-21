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

import android.content.Context
import android.content.res.Resources
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.core.layout.WindowWidthSizeClass.Companion.COMPACT
import androidx.window.core.layout.WindowWidthSizeClass.Companion.EXPANDED
import androidx.window.core.layout.WindowWidthSizeClass.Companion.MEDIUM
import androidx.window.layout.WindowMetricsCalculator

/**
 * Determines if the current window is classified as a large window suitable for tablet devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is large (tablet), `false` otherwise.
 */
fun WindowAdaptiveInfo.isWindowLarge(context: Context): Boolean {
    return getCustomWindowClass(context) == EXPANDED && windowSizeClass.windowHeightSizeClass != WindowHeightSizeClass.COMPACT
}

@Composable
fun WindowAdaptiveInfo.isWindowLarge(): Boolean = isWindowLarge(LocalContext.current)

/**
 * Determines if the current window is classified as a small window suitable for mobile devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is small (mobile), `false` otherwise.
 */
fun WindowAdaptiveInfo.isWindowSmall(context:Context): Boolean = !isWindowLarge(context)

@Composable
fun WindowAdaptiveInfo.isWindowSmall(): Boolean = !isWindowLarge()

private fun getCustomWindowClass(context: Context): WindowWidthSizeClass {
    val windowBounds = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(context)
        .bounds
    val dpWidth = windowBounds.width().toDpInt()

    require(dpWidth >= 0) { "Width must be positive, received $dpWidth" }

    return when {
        dpWidth < 600 -> COMPACT
        dpWidth < 1080 -> MEDIUM
        else -> EXPANDED
    }
}

private fun Int.toDpInt(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}
