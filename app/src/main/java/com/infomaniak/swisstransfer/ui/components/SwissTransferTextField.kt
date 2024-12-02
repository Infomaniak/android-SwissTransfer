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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Eye
import com.infomaniak.swisstransfer.ui.images.icons.EyeCrossed
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

/**
 * Wrapper for Material's [OutlinedTextField] that enforce our design needs.
 *
 * By default, this TextField is single lined. You can specify [maxLineNumber] or [minLineNumber] to make it multi-lined
 *
 * If [isPassword] value is true, the [keyboardType] field will be ignored to force [KeyboardType.Password]
 *
 * To set an error message, you need to pass this message as [supportingText] and set [isError] to true
 */
@Composable
fun SwissTransferTextField(
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
    label: String? = null,
    initialValue: String = "",
    minLineNumber: Int = 1,
    maxLineNumber: Int = if (minLineNumber > 1) Int.MAX_VALUE else 1,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isReadOnly: Boolean = false,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    onValueChange: ((String) -> Unit)? = null,
) {

    var shouldShowPassword: Boolean by rememberSaveable { mutableStateOf(false) }

    var text by rememberSaveable { mutableStateOf(initialValue) }

    val displayLabel = if (isRequired) label else "$label ${stringResource(R.string.textFieldOptional)}"
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedLabelColor = SwissTransferTheme.colors.tertiaryTextColor,
        unfocusedSupportingTextColor = SwissTransferTheme.colors.tertiaryTextColor,
        disabledBorderColor = SwissTransferTheme.materialColors.outline,
        unfocusedTrailingIconColor = SwissTransferTheme.colors.iconColor,
        disabledTrailingIconColor = SwissTransferTheme.colors.iconColor,
    )

    OutlinedTextField(
        modifier = modifier,
        readOnly = isReadOnly,
        enabled = !isReadOnly,
        value = text,
        label = displayLabel?.let { { Text(it) } },
        minLines = minLineNumber,
        maxLines = maxLineNumber,
        colors = textFieldColors,
        textStyle = TextStyle(color = SwissTransferTheme.colors.primaryTextColor),
        onValueChange = {
            text = it
            onValueChange?.invoke(it)
        },
        visualTransformation = when {
            isPassword && !shouldShowPassword -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        trailingIcon = when {
            isPassword -> getShowPasswordButton(shouldShowPassword, onClick = { shouldShowPassword = !shouldShowPassword })
            else -> null
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
            autoCorrectEnabled = true,
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions,
        isError = isError,
        supportingText = supportingText,
    )
}

private fun getShowPasswordButton(shouldShowPassword: Boolean, onClick: () -> Unit): @Composable () -> Unit = {
    IconButton(onClick = onClick) {
        val (contentDescription, icon) = if (shouldShowPassword) {
            stringResource(R.string.contentDescriptionButtonHidePassword) to AppIcons.EyeCrossed
        } else {
            stringResource(R.string.contentDescriptionButtonShowPassword) to AppIcons.Eye
        }

        Icon(icon, contentDescription, Modifier.size(Dimens.SmallIconSize))
    }
}

@Composable
@Preview
private fun Preview() {
    val supportingText = "supporting Text"
    val initialValue = "initialValue"

    SwissTransferTheme {
        Surface {
            Column(Modifier.padding(Margin.Medium)) {
                SwissTransferTextField(
                    label = stringResource(R.string.transferMessagePlaceholder),
                    initialValue = "test",
                )
                SwissTransferTextField(
                    keyboardType = KeyboardType.Email,
                    initialValue = "a@a@.com",
                    supportingText = { Text("Invalid address") },
                    isError = true,
                    label = stringResource(R.string.transferRecipientAddressPlaceholder),
                )
                SwissTransferTextField(
                    isRequired = false,
                    label = stringResource(R.string.transferMessagePlaceholder),
                    minLineNumber = 3,
                )
                SwissTransferTextField(
                    maxLineNumber = 10,
                    initialValue = initialValue,
                    isRequired = false,
                    label = stringResource(R.string.transferMessagePlaceholder),
                    supportingText = { Text(supportingText) },
                )
                SwissTransferTextField(
                    maxLineNumber = 10,
                    initialValue = initialValue,
                    isPassword = true,
                    label = stringResource(R.string.settingsOptionPassword),
                    supportingText = { Text(supportingText) },
                )
                SwissTransferTextField(
                    maxLineNumber = 10,
                    initialValue = initialValue,
                    isPassword = true,
                    label = stringResource(R.string.settingsOptionPassword),
                    isError = true,
                    supportingText = { Text("Wrong password") },
                )
            }
        }
    }
}
