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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SharpRippleButton(
    modifier: Modifier = Modifier,
    isSelected: () -> Boolean = { false },
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val colors = if (isSelected()) {
        ButtonDefaults.textButtonColors(
            contentColor = SwissTransferTheme.colors.primaryTextColor,
            containerColor = SwissTransferTheme.colors.selectedSettingItem,
        )
    } else {
        ButtonDefaults.textButtonColors(contentColor = SwissTransferTheme.colors.primaryTextColor)
    }
    Button(
        modifier = modifier.selectable(
            selected = isSelected(),
            onClick = onClick,
        ),
        colors = colors,
        shape = CustomShapes.NONE,
        onClick = onClick,
        content = content,
        contentPadding = contentPadding,
    )
}
