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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
            actionIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
            navigationIconContentColor = SwissTransferTheme.colors.toolbarIconColor,
        ),
        title = { },
    )
}
