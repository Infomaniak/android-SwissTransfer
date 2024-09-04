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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.Margin

private val WIDTH_LIMIT = 800.dp
private val WIDTH_THRESHOLD = 500.dp

@Composable
fun ColumnScope.DoubleButtonCombo(
    topButton: @Composable ((Modifier) -> Unit)? = null,
    bottomButton: @Composable ((Modifier) -> Unit)? = null
) {
    BoxWithConstraints(
        modifier = Modifier
            .widthIn(max = WIDTH_LIMIT)
            .align(Alignment.CenterHorizontally),
    ) {
        when {
            topButton == null && bottomButton == null -> Unit
            topButton != null && bottomButton != null -> {
                if (maxWidth < WIDTH_THRESHOLD) {
                    VerticallyStackedButtons(topButton, bottomButton)
                } else {
                    HorizontallyStackedButtons(topButton, bottomButton)
                }
            }
            else -> SingleButton(button = topButton ?: bottomButton!!)
        }
    }
}

@Composable
private fun VerticallyStackedButtons(
    topButton: @Composable (Modifier) -> Unit,
    bottomButton: @Composable (Modifier) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        topButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Medium),
        )

        Spacer(modifier = Modifier.height(Margin.Medium))

        bottomButton(
            Modifier
                .fillMaxWidth()
                .padding(start = Margin.Medium, end = Margin.Medium, bottom = Margin.Large),
        )
    }
}

@Composable
private fun HorizontallyStackedButtons(
    topButton: @Composable (Modifier) -> Unit,
    bottomButton: @Composable (Modifier) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Margin.Large, start = Margin.Medium, end = Margin.Medium),
        horizontalArrangement = Arrangement.spacedBy(Margin.Medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        topButton(Modifier.weight(1.0f))
        bottomButton(Modifier.weight(1.0f))
    }
}

@Composable
private fun SingleButton(button: @Composable (Modifier) -> Unit) {
    button(
        Modifier
            .fillMaxWidth()
            .widthIn(WIDTH_LIMIT / 2)
            .padding(bottom = Margin.Large, start = Margin.Medium, end = Margin.Medium),
    )
}
