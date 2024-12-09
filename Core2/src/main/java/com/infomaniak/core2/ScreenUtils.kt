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
package com.infomaniak.core2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import kotlin.math.min

private const val MIN_HEIGHT_FOR_LANDSCAPE = 4

@SuppressLint("SourceLockedOrientationActivity")
fun Activity.lockOrientationForSmallScreens() {
    val (screenHeightInches, screenWidthInches) = with(resources.displayMetrics) {
        (heightPixels / ydpi) to (widthPixels / xdpi)
    }

    val aspectRatio = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_LONG_MASK
    val isLongScreen = aspectRatio == Configuration.SCREENLAYOUT_LONG_YES

    val isScreenTooSmall = isLongScreen && min(screenHeightInches, screenWidthInches) < MIN_HEIGHT_FOR_LANDSCAPE

    if (isScreenTooSmall) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}
