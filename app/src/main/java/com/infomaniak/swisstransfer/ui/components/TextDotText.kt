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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TextDotText(
    firstText: @Composable () -> String,
    secondText: @Composable () -> String,
    modifier: Modifier = Modifier,
    color: Color = SwissTransferTheme.colors.secondaryTextColor,
    style: TextStyle = SwissTransferTheme.typography.bodySmallRegular,
    optionalSecondTextColor: Color? = null,
) {
    Row(modifier) {
        CustomText(text = firstText, style = style, color = color)
        if (secondText().isNotEmpty()) {
            Spacer(Modifier.width(Margin.Mini))
            Text(text = "•", style = style, color = color)
            Spacer(Modifier.width(Margin.Mini))
            CustomText(text = secondText, style = style, color = optionalSecondTextColor ?: color)
        }
    }
}

@Composable
private fun CustomText(text: @Composable () -> String, style: TextStyle, color: Color) {
    Text(text = text(), style = style, color = color)
}
