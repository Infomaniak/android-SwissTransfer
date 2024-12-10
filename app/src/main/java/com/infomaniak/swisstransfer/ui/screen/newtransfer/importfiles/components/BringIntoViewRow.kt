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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times

@Composable
fun <T> BringIntoViewRow(
    horizontalPadding: Dp,
    spaceBy: Dp,
    items: List<T>,
    itemContent: @Composable (T, BringIntoViewRequester) -> Unit,
) {

    // We need to add horizontalPadding to each elements in order to have a pretty bringIntoView() effect
    // Thus we need to subtract 2 times this padding (for right padding of an element and left one of the following for example)
    // minus the wanted spaceBy padding that must stay between elements.
    val computedNegativeSpaceBy = -(2 * horizontalPadding - spaceBy)

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(computedNegativeSpaceBy),
    ) {
        items.forEach {
            val bringIntoViewRequester = remember { BringIntoViewRequester() }

            Box(
                Modifier
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .padding(horizontal = horizontalPadding)
            ) {
                itemContent(it, bringIntoViewRequester)
            }
        }
    }
}
