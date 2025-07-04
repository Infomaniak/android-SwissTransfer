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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun BottomStickyButtonScaffold(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    topBar: @Composable () -> Unit,
    topButton: @Composable ((Modifier) -> Unit)? = null,
    bottomButton: @Composable ((Modifier) -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    SinglePaneScaffold(
        modifier = modifier,
        snackbarHost = { snackbarHostState?.let { SnackbarHost(hostState = it) } },
        topBar = topBar,
    ) { contentPaddings ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPaddings),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier.weight(1.0f), content = content)
            DoubleButtonCombo(
                modifier = Modifier.padding(vertical = Dimens.ButtonComboVerticalPadding),
                topButton = topButton,
                bottomButton = bottomButton,
            )
        }
    }
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            BottomStickyButtonScaffold(
                topBar = { BrandTopAppBar() },
                topButton = { modifier -> LargeButton("Top button", onClick = {}, modifier = modifier) },
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    text = "content",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
