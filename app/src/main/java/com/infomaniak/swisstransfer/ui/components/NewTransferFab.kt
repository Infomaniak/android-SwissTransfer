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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.NewTransferActivity
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.launchActivity

@Composable
fun NewTransferFab(
    modifier: Modifier = Modifier,
    newTransferFabType: NewTransferFabType
) {
    val context = LocalContext.current

    SwissTransferFab(
        modifier = modifier,
        fabType = newTransferFabType.fabType,
        elevation = newTransferFabType.elevation(),
        onClick = { context.launchActivity(NewTransferActivity::class) },
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

@PreviewLightAndDark
@Composable
private fun NewTransferFabPreview() {
    SwissTransferTheme {
        Surface {
            Row {
                NewTransferFab(newTransferFabType = NewTransferFabType.BOTTOM_BAR)
                Spacer(Modifier.width(Margin.Large))
                NewTransferFab(newTransferFabType = NewTransferFabType.EMPTY_STATE)
                Spacer(Modifier.width(Margin.Large))
                NewTransferFab(newTransferFabType = NewTransferFabType.NAVIGATION_RAIL)
            }
        }
    }
}
