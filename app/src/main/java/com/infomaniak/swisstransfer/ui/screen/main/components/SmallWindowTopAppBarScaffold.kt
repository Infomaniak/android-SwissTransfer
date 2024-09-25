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
package com.infomaniak.swisstransfer.ui.screen.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun SmallWindowTopAppBarScaffold(
    smallWindowTopAppBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    Scaffold(
        topBar = { if (windowAdaptiveInfo.isWindowSmall()) smallWindowTopAppBar() },
        floatingActionButton = floatingActionButton,
    ) { contentPadding ->
        val paddingValues = if (windowAdaptiveInfo.isWindowSmall()) {
            contentPadding
        } else {
            PaddingValues(all = 0.dp)
        }
        content(paddingValues)
    }
}
