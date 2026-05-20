/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2026 Infomaniak Network SA
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.WindowMetricsCalculator

/**
 * Determines if the current window is classified as a large window suitable for tablet devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is large (tablet), `false` otherwise.
 */
fun isWindowLarge(context: Context): Boolean {
    return context.getCustomWindowWidthClass() == CustomWindowWidthClass.Large &&
            context.getCustomWindowHeightClass() != CustomWindowHeightClass.Compact
}

@Composable
fun isWindowLarge(): Boolean = isWindowLarge(LocalContext.current)

/**
 * Determines if the current window is classified as a medium window suitable for foldable and tablet devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is medium (foldable and tablet), `false` otherwise.
 */
fun isWindowMedium(context: Context): Boolean {
    return context.getCustomWindowWidthClass() == CustomWindowWidthClass.Medium &&
            context.getCustomWindowHeightClass() != CustomWindowHeightClass.Compact
}

@Composable
fun isWindowMedium(): Boolean = isWindowMedium(LocalContext.current)

/**
 * Determines if the current window is classified as a small window suitable for mobile devices.
 *
 * This is typically used to adapt the UI, such as displaying a list-detail layout or using a [NavigationRail]
 * vs a [NavigationBar] for navigation.
 *
 * @return `true` if the window is small (mobile), `false` otherwise.
 */
fun isWindowSmall(context: Context): Boolean {
    return context.getCustomWindowWidthClass() == CustomWindowWidthClass.Small
}

@Composable
fun isWindowSmall(): Boolean = isWindowSmall(LocalContext.current)

private fun Context.getCustomWindowWidthClass(): CustomWindowWidthClass {
    val dpWidth = windowBounds().width().toDpInt()
    require(dpWidth >= 0) { "Width must be positive, received $dpWidth" }

    return when {
        dpWidth >= CustomWindowWidthClass.Large.minWidthDp -> CustomWindowWidthClass.Large
        dpWidth >= CustomWindowWidthClass.Medium.minWidthDp -> CustomWindowWidthClass.Medium
        else -> CustomWindowWidthClass.Small
    }
}

private fun Context.getCustomWindowHeightClass(): CustomWindowHeightClass {
    val dpHeight = windowBounds().height().toDpInt()

    return when {
        dpHeight >= CustomWindowHeightClass.Expanded.minHeightDp -> CustomWindowHeightClass.Expanded
        dpHeight >= CustomWindowHeightClass.Medium.minHeightDp -> CustomWindowHeightClass.Medium
        else -> CustomWindowHeightClass.Compact
    }
}

private fun Context.windowBounds() = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this).bounds

private fun Int.toDpInt(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Custom window width classification based on Material 3 breakpoints.
 * Uses [WindowMetricsCalculator] to read the real window size in dp.
 */
private enum class CustomWindowWidthClass(val minWidthDp: Int) {
    Small(0),
    Medium(600),
    Large(1080),
}

private enum class CustomWindowHeightClass(val minHeightDp: Int) {
    Compact(0),
    Medium(480),
    Expanded(900),
}
