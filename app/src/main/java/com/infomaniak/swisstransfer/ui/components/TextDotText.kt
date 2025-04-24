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
@file:OptIn(ExperimentalTypeInference::class)

package com.infomaniak.swisstransfer.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlin.experimental.ExperimentalTypeInference

@Composable
fun TextDotText(
    firstText: @Composable () -> Unit,
    secondText: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier,
    color: Color = SwissTransferTheme.colors.secondaryTextColor,
    style: TextStyle = SwissTransferTheme.typography.bodySmallRegular,
) = Row(modifier) {
    ProvideTextStyle(style.copy(color = color)) {
        firstText()
        secondText?.let { secondText ->
            Spacer(Modifier.width(Margin.Mini))
            Text(text = "•", modifier = Modifier.semantics { hideFromAccessibility() })
            Spacer(Modifier.width(Margin.Mini))
            secondText()
        }
    }
}
