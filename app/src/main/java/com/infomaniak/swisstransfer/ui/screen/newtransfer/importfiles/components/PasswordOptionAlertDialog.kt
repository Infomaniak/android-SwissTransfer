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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextField
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.WeightOneSpacer
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun PasswordOptionAlertDialog(
    initialIsChecked: Boolean,
    showPasswordOptionAlert: () -> Boolean,
    closeAlertDialog: () -> Unit,
    onConfirmation: (Boolean) -> Unit,
    setPassword: (String) -> Unit,
    isPasswordValid: () -> Boolean,
) {

    var isChecked by rememberSaveable { mutableStateOf(initialIsChecked) }

    if (showPasswordOptionAlert()) {
        SwissTransferAlertDialog(
            titleRes = R.string.settingsOptionPassword,
            descriptionRes = R.string.settingsPasswordDescription,
            onDismissRequest = {
                isChecked = initialIsChecked
                closeAlertDialog()
            },
            onConfirmation = {
                onConfirmation(isChecked)
                closeAlertDialog()
            },
            shouldEnableConfirmButton = { if (isChecked) isPasswordValid() else true },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.settingsPasswordToggleDescription),
                    style = SwissTransferTheme.typography.bodyMedium,
                    color = SwissTransferTheme.colors.primaryTextColor,
                )
                WeightOneSpacer(minWidth = Margin.Medium)
                Switch(checked = isChecked, onCheckedChange = { isChecked = it })
            }

            AnimatedVisibility(isChecked) {
                Spacer(Modifier.height(Margin.Mini))
                SwissTransferTextField(
                    label = stringResource(R.string.settingsOptionPassword),
                    isPassword = true,
                    errorMessage = { if (isPasswordValid()) null else stringResource(R.string.errorTransferPasswordLength) },
                    onValueChange = { setPassword(it) },
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SwissTransferTheme {
        Surface {
            PasswordOptionAlertDialog(
                initialIsChecked = true,
                showPasswordOptionAlert = { true },
                closeAlertDialog = {},
                onConfirmation = {},
                setPassword = {},
                isPasswordValid = { false },
            )
        }
    }
}
