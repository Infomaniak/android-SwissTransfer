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
import com.infomaniak.core.R as RCore

object SwissTransferAlertDialogDefaults {
    @Composable
    fun ConfirmButton(isEnabled: () -> Boolean = { true }, onClick: () -> Unit) {
        SmallButton(
            style = ButtonType.Tertiary,
            title = stringResource(R.string.buttonConfirm),
            enabled = isEnabled,
            onClick = onClick,
        )
    }

    @Composable
    fun CancelButton(onClick: () -> Unit) {
        SmallButton(
            style = ButtonType.Tertiary,
            title = stringResource(RCore.string.buttonCancel),
            onClick = onClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwissTransferAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    positiveButton: @Composable () -> Unit,
    negativeButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
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
                title = title,
                positiveButton = positiveButton,
                negativeButton = negativeButton,
                description = description,
                additionalContent = content,
            )
        }
    }
}

@Composable
private fun BasicAlertDialogContent(
    modifier: Modifier,
    title: String,
    description: String?,
    positiveButton: @Composable () -> Unit,
    negativeButton: @Composable () -> Unit,
    additionalContent: @Composable (ColumnScope.() -> Unit)? = null,
) {
    Column(modifier.padding(Margin.Large)) {
        TitleAndDescription(title, description)
        Spacer(Modifier.height(Margin.Large))
        additionalContent?.let {
            it()
            Spacer(Modifier.height(Margin.Mini))
        }
        ActionButtons(positiveButton, negativeButton)
    }
}

@Composable
private fun TitleAndDescription(title: String, description: String?) {
    Text(
        text = title,
        style = SwissTransferTheme.typography.bodyMedium,
        color = SwissTransferTheme.colors.primaryTextColor,
    )
    description?.let {
        Spacer(Modifier.height(Margin.Large))
        Text(
            text = description,
            style = SwissTransferTheme.typography.bodyRegular,
            color = SwissTransferTheme.colors.secondaryTextColor,
        )
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActionButtons(
    positiveButton: @Composable () -> Unit,
    negativeButton: @Composable () -> Unit,
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        negativeButton()
        positiveButton()
    }
}

@Preview
@Composable
private fun PreviewAlertDialog() {
    SwissTransferTheme {
        Surface {
            SwissTransferAlertDialog(
                title = stringResource(R.string.settingsOptionPassword),
                description = stringResource(R.string.settingsPasswordDescription),
                positiveButton = { SwissTransferAlertDialogDefaults.ConfirmButton { } },
                negativeButton = { SwissTransferAlertDialogDefaults.CancelButton { } },
                onDismiss = {},
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTitleOnlyAlertDialog() {
    SwissTransferTheme {
        Surface {
            SwissTransferAlertDialog(
                title = stringResource(R.string.newTransferConfirmLeavingDialogTitle),
                positiveButton = { SwissTransferAlertDialogDefaults.ConfirmButton { } },
                negativeButton = { SwissTransferAlertDialogDefaults.CancelButton { } },
                onDismiss = {},
            )
        }
    }
}

@Preview
@Composable
private fun WideButtonsPreview() {
    SwissTransferTheme {
        Surface {
            SwissTransferAlertDialog(
                title = stringResource(R.string.settingsOptionPassword),
                description = stringResource(R.string.settingsPasswordDescription),
                positiveButton = { SmallButton("A very looong and wide button", onClick = {}, style = ButtonType.Tertiary) },
                negativeButton = { SwissTransferAlertDialogDefaults.CancelButton { } },
                onDismiss = {},
            )
        }
    }
}
