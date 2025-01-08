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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer.toFloat
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialogDefaults
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextField
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.WeightOneSpacer
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks

@Composable
fun PasswordOptionAlertDialog(
    password: GetSetCallbacks<String>,
    closeAlertDialog: () -> Unit,
    onConfirmation: (PasswordTransferOption) -> Unit,
    isPasswordValid: () -> Boolean,
) {

    var isPasswordActivated by rememberSaveable { mutableStateOf(password.get().isNotEmpty()) }
    var lastValidPassword by remember { mutableStateOf(password.get()) }

    fun onDismiss() {
        isPasswordActivated = lastValidPassword.isNotEmpty()
        password.set(lastValidPassword)
        closeAlertDialog()
    }

    fun onConfirmButtonClicked() {
        val passwordOption = if (isPasswordActivated) {
            PasswordTransferOption.ACTIVATED
        } else {
            password.set("")
            PasswordTransferOption.NONE
        }

        lastValidPassword = password.get()
        onConfirmation(passwordOption)
    }

    SwissTransferAlertDialog(
        titleRes = R.string.settingsOptionPassword,
        descriptionRes = R.string.settingsPasswordDescription,
        positiveButton = {
            SwissTransferAlertDialogDefaults.ConfirmButton(
                isEnabled = { !isPasswordActivated || isPasswordValid() },
                onClick = ::onConfirmButtonClicked,
            )
        },
        negativeButton = { SwissTransferAlertDialogDefaults.CancelButton(onClick = ::onDismiss) },
        onDismiss = ::onDismiss,
    ) {
        ActivatePasswordSwitch(isChecked = isPasswordActivated, onCheckedChange = { isPasswordActivated = it })
        Spacer(Modifier.height(Margin.Medium))
        AnimatedPasswordInput(isPasswordActivated, password, isPasswordValid)
    }
}

@Composable
fun DeeplinkPasswordAlertDialog(
    password: GetSetCallbacks<String>,
    closeAlertDialog: () -> Unit,
    onConfirmation: () -> Unit,
    isError: () -> Boolean,
) {

    var isLoading by remember { mutableStateOf(false) }
    var hasPasswordChanged by remember { mutableStateOf(false) }

    val shouldDisplayError = isError() && !hasPasswordChanged

    fun onConfirm() {
        hasPasswordChanged = false
        isLoading = true
        onConfirmation()
    }

    SwissTransferAlertDialog(
        titleRes = R.string.sharePasswordTitle,
        descriptionRes = R.string.deeplinkPasswordDescription,
        onDismiss = closeAlertDialog,
        positiveButton = {
            SwissTransferAlertDialogDefaults.ConfirmButton(
                isEnabled = { !isLoading && password.get().isNotEmpty() },
                onClick = ::onConfirm,
            )
        },
        negativeButton = { SwissTransferAlertDialogDefaults.CancelButton(onClick = closeAlertDialog) },
    ) {
        SwissTransferTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.settingsOptionPassword),
            isPassword = true,
            initialValue = password.get(),
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(onDone = { onConfirm() }),
            isError = shouldDisplayError,
            supportingText = {
                Text(
                    modifier = Modifier.alpha(shouldDisplayError.toFloat()),
                    text = stringResource(R.string.errorIncorrectPassword),
                )
            },
            onValueChange = {
                isLoading = false
                hasPasswordChanged = true
                password.set(it)
            },
        )
    }
}

@Composable
private fun ActivatePasswordSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.settingsPasswordToggleDescription),
            style = SwissTransferTheme.typography.bodyMedium,
            color = SwissTransferTheme.colors.primaryTextColor,
        )
        WeightOneSpacer(minWidth = Margin.Medium)
        Switch(isChecked, onCheckedChange)
    }
}

@Composable
private fun ColumnScope.AnimatedPasswordInput(
    isChecked: Boolean,
    password: GetSetCallbacks<String>,
    isPasswordValid: () -> Boolean
) {

    val isError = !isPasswordValid() && password.get().isNotEmpty()
    AnimatedVisibility(isChecked) {
        SwissTransferTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.settingsOptionPassword),
            isPassword = true,
            initialValue = password.get(),
            imeAction = ImeAction.Done,
            isError = isError,
            supportingText = {
                Text(
                    modifier = Modifier.alpha(isError.toFloat()),
                    text = stringResource(R.string.errorTransferPasswordLength),
                )
            },
            onValueChange = { password.set(it) },
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            PasswordOptionAlertDialog(
                password = GetSetCallbacks(get = { "pass" }, set = {}),
                closeAlertDialog = {},
                onConfirmation = {},
                isPasswordValid = { false },
            )
        }
    }
}
