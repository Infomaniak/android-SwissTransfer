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
package com.infomaniak.core2.extensions

import splitties.init.appCtx
import kotlin.LazyThreadSafetyMode.NONE

val appName: String by lazy(NONE) {
    packageManager.getApplicationLabel(applicationInfo).toString()
}

val appVersionName: String by lazy(NONE) {
    packageManager.getPackageInfo(appCtx.packageName, 0).versionName.toString()
}

private val packageManager = appCtx.packageManager
private val applicationInfo by lazy(NONE) { appCtx.applicationInfo }