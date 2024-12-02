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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin

@Composable
fun BottomStickyButtonScaffold(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    topBar: @Composable () -> Unit,
    topButton: @Composable ((Modifier) -> Unit)? = null,
    bottomButton: @Composable ((Modifier) -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        snackbarHost = { snackbarHostState?.let { SnackbarHost(hostState = it) } },
        topBar = topBar,
    ) { contentPaddings ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(contentPaddings)
                .padding(vertical = Margin.Small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = Margin.Small)
                    .weight(1.0f)
                    .widthIn(max = Dimens.MaxSinglePaneScreenWidth),
                content = content,
            )
            DoubleButtonCombo(topButton, bottomButton)
        }
    }
}
