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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwissTransferAlertDialog(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        Card(shape = RoundedCornerShape(28.dp)) {
            BasicAlertDialogContent(modifier, titleRes, descriptionRes, content)
        }
    }
}

@Composable
fun BasicAlertDialogContent(
    modifier: Modifier,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    additionalContent: @Composable (ColumnScope.() -> Unit)? = null,
) {
    Column(modifier.padding(Margin.Large)) {
        Text(
            stringResource(titleRes),
            style = SwissTransferTheme.typography.bodyMedium,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
        Spacer(Modifier.height(Margin.Large))
        Text(
            stringResource(descriptionRes),
            style = SwissTransferTheme.typography.bodyRegular,
            color = SwissTransferTheme.colors.secondaryTextColor,
        )
        Spacer(Modifier.height(Margin.Large))
        additionalContent?.let { it() }
    }
}

