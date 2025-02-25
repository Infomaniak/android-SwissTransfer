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
package com.infomaniak.swisstransfer.ui.screen.newtransfer

import androidx.annotation.FloatRange

sealed interface SendButtonStatus {
    data object Clickable : SendButtonStatus
    // This status usually only lasts for a split seconds if the user doesn't pick multiple hundred of files. So this prevents the
    // button from being clicked but doesn't show a jarring blinking button to the user.
    data object Unclickable : SendButtonStatus
    data object Loading : SendButtonStatus
    data class Progress(@FloatRange(0.0, 1.0) val progress: Float) : SendButtonStatus
}
