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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.core2.R as RCore2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwissTransferAlertDialog(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
    isConfirmButtonEnabled: () -> Boolean = { true },
    content: @Composable (ColumnScope.() -> Unit)? = null,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        Card(shape = RoundedCornerShape(Margin.Medium)) {
            BasicAlertDialogContent(
                modifier = modifier,
                titleRes = titleRes,
                descriptionRes = descriptionRes,
                additionalContent = content,
                onDismiss = onDismiss,
                onConfirmation = onConfirmation,
                isConfirmButtonEnabled = isConfirmButtonEnabled,
            )
        }
    }
}

@Composable
private fun BasicAlertDialogContent(
    modifier: Modifier,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    additionalContent: @Composable (ColumnScope.() -> Unit)? = null,
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
    isConfirmButtonEnabled: () -> Boolean = { true },
) {
    Column(modifier.padding(Margin.Large)) {
        TitleAndDescription(titleRes, descriptionRes)
        Spacer(Modifier.height(Margin.Large))
        additionalContent?.let {
            it()
            Spacer(Modifier.height(Margin.Mini))
        }
        ActionButtons(onDismiss, onConfirmation, isConfirmButtonEnabled)
    }
}

@Composable
private fun TitleAndDescription(titleRes: Int, descriptionRes: Int) {
    Text(
        text = stringResource(titleRes),
        style = SwissTransferTheme.typography.bodyMedium,
        color = SwissTransferTheme.colors.primaryTextColor,
    )
    Spacer(Modifier.height(Margin.Large))
    Text(
        text = stringResource(descriptionRes),
        style = SwissTransferTheme.typography.bodyRegular,
        color = SwissTransferTheme.colors.secondaryTextColor,
    )
}

@Composable
private fun ActionButtons(onDismissRequest: () -> Unit, onConfirmation: () -> Unit, isEnabled: () -> Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SmallButton(
            style = ButtonType.TERTIARY,
            title = stringResource(RCore2.string.buttonCancel),
            onClick = onDismissRequest,
        )
        Spacer(Modifier.width(Margin.Micro))
        SmallButton(
            title = stringResource(R.string.buttonConfirm),
            onClick = onConfirmation,
            enabled = isEnabled
        )
    }
}

@Preview
@Composable
private fun PreviewAlertDialog() {
    SwissTransferTheme {
        Surface {
            SwissTransferAlertDialog(
                titleRes = R.string.settingsOptionPassword,
                descriptionRes = R.string.settingsPasswordDescription,
                onDismiss = {},
                onConfirmation = {},
            )
        }
    }
}
