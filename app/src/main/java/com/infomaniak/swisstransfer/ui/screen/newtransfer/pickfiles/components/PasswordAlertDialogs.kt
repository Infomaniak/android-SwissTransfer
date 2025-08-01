/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer.toFloat
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialogDefaults
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextField
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components.WeightOneSpacer
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks

@Composable
fun DeeplinkPasswordAlertDialog(
    password: GetSetCallbacks<String>,
    closeAlertDialog: () -> Unit,
    onConfirmation: () -> Unit,
    isError: () -> Boolean,
) {

    var isLoading by remember { mutableStateOf(false) }
    var hasPasswordChanged by remember { mutableStateOf(false) }
    val isConfirmButtonEnabled by remember { derivedStateOf { !isLoading && password.get().isNotEmpty() } }

    val shouldDisplayError = isError() && !hasPasswordChanged

    fun onConfirm() {
        hasPasswordChanged = false
        isLoading = true
        onConfirmation()
    }

    SwissTransferAlertDialog(
        title = stringResource(R.string.sharePasswordTitle),
        description = stringResource(R.string.deeplinkPasswordDescription),
        onDismiss = closeAlertDialog,
        positiveButton = {
            SwissTransferAlertDialogDefaults.ConfirmButton(
                isEnabled = { isConfirmButtonEnabled },
                onClick = ::onConfirm,
            )
        },
        negativeButton = { SwissTransferAlertDialogDefaults.CancelButton(onClick = closeAlertDialog) },
    ) {
        PasswordTextField(
            password = password,
            isError = shouldDisplayError,
            errorTextId = R.string.errorIncorrectPassword,
            onDone = { if (isConfirmButtonEnabled) onConfirm() },
            onValueChange = {
                isLoading = false
                hasPasswordChanged = true
                password.set(it)
            },
        )
    }
}

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
        title = stringResource(R.string.settingsOptionPassword),
        description = stringResource(R.string.settingsPasswordDescription),
        positiveButton = {
            SwissTransferAlertDialogDefaults.ConfirmButton(
                isEnabled = { !isPasswordActivated || isPasswordValid() },
                onClick = ::onConfirmButtonClicked,
            )
        },
        negativeButton = { SwissTransferAlertDialogDefaults.CancelButton(onClick = ::onDismiss) },
        onDismiss = ::onDismiss,
    ) {
        ActivatePasswordSwitch(
            isChecked = isPasswordActivated,
            onCheckedChange = {
                MatomoSwissTransfer.trackSettingsLocalPasswordEvent(MatomoName.TogglePassword)
                isPasswordActivated = it
            },
        )
        Spacer(Modifier.height(Margin.Medium))
        AnimatedPasswordTextField(isPasswordActivated, password, isPasswordValid, ::onConfirmButtonClicked)
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
private fun ColumnScope.AnimatedPasswordTextField(
    isChecked: Boolean,
    password: GetSetCallbacks<String>,
    isPasswordValid: () -> Boolean,
    onDone: () -> Unit,
) {

    val isError = !isPasswordValid() && password.get().isNotEmpty()
    AnimatedVisibility(isChecked) {
        PasswordTextField(
            password = password,
            isError = isError,
            errorTextId = R.string.errorTransferPasswordLength,
            onDone = { if (isPasswordValid()) onDone() },
            onValueChange = { password.set(it) },
        )
    }
}

@Composable
private fun PasswordTextField(
    password: GetSetCallbacks<String>,
    isError: Boolean,
    @StringRes errorTextId: Int,
    onDone: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    SwissTransferTextField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(R.string.settingsOptionPassword),
        isPassword = true,
        initialValue = password.get(),
        imeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = { onDone() }),
        isError = isError,
        supportingText = {
            Text(
                modifier = Modifier.alpha(isError.toFloat()),
                text = stringResource(errorTextId),
            )
        },
        onValueChange = onValueChange,
    )
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
