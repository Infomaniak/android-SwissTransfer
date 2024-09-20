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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun EmptyState(
    icon: ImageVector,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(imageVector = icon, contentDescription = null)

        Text(
            text = stringResource(title),
            style = SwissTransferTheme.typography.specificMedium22,
            modifier = Modifier.padding(PaddingValues(top = Margin.XLarge)),
        )

        Text(
            text = stringResource(description),
            textAlign = TextAlign.Center,
            style = SwissTransferTheme.typography.bodyRegular,
            color = SwissTransferTheme.colors.secondaryTextColor,
            modifier = Modifier
                .widthIn(max = Dimens.DescriptionWidth)
                .padding(PaddingValues(top = Margin.Medium)),
        )
    }
}
