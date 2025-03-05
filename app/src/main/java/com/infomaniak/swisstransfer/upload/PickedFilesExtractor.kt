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
package com.infomaniak.swisstransfer.upload

import android.net.Uri
import com.infomaniak.swisstransfer.ui.screen.newtransfer.PickedFile
import kotlinx.coroutines.flow.StateFlow

interface PickedFilesExtractor {

    val pickedFilesFlow: StateFlow<List<PickedFile>>
    val isHandlingUrisFlow: StateFlow<Boolean>

    fun addUris(uris: List<Uri>)
    fun removeUris(uris: List<Uri>)
    fun clear()
}
