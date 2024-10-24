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

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.EyeSmall
import com.infomaniak.swisstransfer.ui.images.icons.EyeCrossedOutSmall
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SwissTransferTextField(
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
    label: String? = null,
    initialValue: String = "",
    maxLineNumber: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: ((String) -> Unit)? = null,
) {

    var shouldShowPassword: Boolean by rememberSaveable { mutableStateOf(false) }

    var text by rememberSaveable { mutableStateOf(initialValue) }

    val isPassword = keyboardType == KeyboardType.Password
    val displayLabel = if (isRequired) label else "$label ${stringResource(R.string.textFieldOptional)}"

    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = displayLabel?.let { { Text(it) } },
        maxLines = maxLineNumber,
        onValueChange = onValueChange ?: { text = Html.escapeHtml(it.trim()) },
        visualTransformation = if (isPassword && !shouldShowPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = if (isPassword) {
            { ShowPasswordButton(shouldShowPassword, onClick = { shouldShowPassword = !shouldShowPassword }) }
        } else {
            null
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@Composable
private fun ShowPasswordButton(shouldShowPassword: Boolean, onClick: () -> Unit) {

    val (contentDescription, icon) = if (shouldShowPassword) {
        "Hide password" to AppIcons.EyeCrossedOutSmall
    } else {
        "Show password" to AppIcons.EyeSmall
    }

    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

@Composable
@Preview
fun Preview() {
    SwissTransferTheme {
        Surface {
            Column {
                SwissTransferTextField(label = stringResource(R.string.transferTitlePlaceholder))
                SwissTransferTextField(isRequired = false, label = stringResource(R.string.transferTitlePlaceholder))
                SwissTransferTextField(
                    keyboardType = KeyboardType.Password,
                    initialValue = "myPassword",
                    label = stringResource(R.string.transferTitlePlaceholder),
                )
                SwissTransferTextField(
                    maxLineNumber = 10,
                    isRequired = false,
                    label = stringResource(R.string.transferTitlePlaceholder),
                )
            }
        }
    }
}
