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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.UploadSourceChoiceBottomSheet

@Composable
fun NewTransferFab(
    modifier: Modifier = Modifier,
    newTransferFabType: NewTransferFabType
) {
    var showUploadSourceChoiceBottomSheet by rememberSaveable { mutableStateOf(false) }

    SwissTransferFab(
        modifier = modifier,
        fabType = newTransferFabType.fabType,
        elevation = newTransferFabType.elevation(),
        onClick = { showUploadSourceChoiceBottomSheet = true },
    )
    UploadSourceChoiceBottomSheet(
        isBottomSheetVisible = { showUploadSourceChoiceBottomSheet },
        onDismissRequest = { showUploadSourceChoiceBottomSheet = false },
    )
}

enum class NewTransferFabType(val fabType: FabType, private val defaultElevation: Dp?) {

    BOTTOM_BAR(FabType.NORMAL, null),
    EMPTY_STATE(FabType.BIG, null),
    NAVIGATION_RAIL(FabType.NORMAL, 0.dp);

    @Composable
    fun elevation(): FloatingActionButtonElevation {
        return if (defaultElevation != null) {
            FloatingActionButtonDefaults.elevation(defaultElevation)
        } else {
            FloatingActionButtonDefaults.elevation()
        }
    }
}
