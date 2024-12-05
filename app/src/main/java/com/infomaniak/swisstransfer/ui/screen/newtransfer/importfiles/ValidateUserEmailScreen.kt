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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

private val VALID_CHARACTERS = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

@Composable
fun ValidateUserEmailScreen() {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            var otp by rememberSaveable { mutableStateOf("123") }
            var isError by rememberSaveable { mutableStateOf(false) }

            OtpTextField(
                otpText = otp,
                otpCount = 6,
                onOtpTextChange = { text, isFilled ->
                    Log.e("gibran", "ValidateUserEmailScreen - text: ${text}, isFilled: $isFilled")
                    otp = text.filter { it in VALID_CHARACTERS }
                    isError = isFilled && otp != "111111"
                },
                isError = { isError },
                otpTextFieldStyle = OtpTextFieldStyle.default(
                    textStyle = SwissTransferTheme.typography.bodyMedium.copy(color = SwissTransferTheme.colors.secondaryTextColor),
                ),
            )

            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                }
            )
        }
    }
}

data class OtpTextFieldStyle(
    val activeColor: Color,
    val inactiveColor: Color,
    val errorColor: Color,
    val activeBorderThickness: Dp,
    val inactiveBorderThickness: Dp,
    val shape: RoundedCornerShape,
    val size: Dp,
    val textStyle: TextStyle,
) {
    companion object {
        @Composable
        fun default(
            activeColor: Color = MaterialTheme.colorScheme.primary,
            inactiveColor: Color = MaterialTheme.colorScheme.outline,
            errorColor: Color = MaterialTheme.colorScheme.error,
            activeBorderThickness: Dp = 2.dp,
            inactiveBorderThickness: Dp = 1.dp,
            shape: RoundedCornerShape = RoundedCornerShape(8.dp),
            size: Dp = 48.dp,
            textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
        ) = OtpTextFieldStyle(
            activeColor = activeColor,
            inactiveColor = inactiveColor,
            errorColor = errorColor,
            activeBorderThickness = activeBorderThickness,
            inactiveBorderThickness = inactiveBorderThickness,
            shape = shape,
            size = size,
            textStyle = textStyle,
        )
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int,
    onOtpTextChange: (String, Boolean) -> Unit,
    isCharacterValid: ((Char) -> Boolean)? = null,
    otpTextFieldStyle: OtpTextFieldStyle,
    isError: () -> Boolean = { false },
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            error("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    var isTextFieldFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier.onFocusChanged { isTextFieldFocused = it.isFocused },
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = { textFieldValue ->
            Log.v("gibran", "OtpTextField - textFieldValue.text: ${textFieldValue.text}")
            val text = isCharacterValid?.let { textFieldValue.text.filter { char -> it(char) } } ?: textFieldValue.text

            if (text.length <= otpCount) {
                onOtpTextChange.invoke(text, text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    if (index > 0) Spacer(modifier = Modifier.width(8.dp))

                    CharView(
                        index = index,
                        text = otpText,
                        isTextFieldFocused = { isTextFieldFocused },
                        isError = isError,
                        otpTextFieldStyle = otpTextFieldStyle,
                    )
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isTextFieldFocused: () -> Boolean,
    isError: () -> Boolean,
    otpTextFieldStyle: OtpTextFieldStyle,
) = with(otpTextFieldStyle) {
    val content: @Composable BoxScope.() -> Unit = when {
        index == text.length -> {
            {}
        }
        index > text.length -> {
            {}
        }
        else -> {
            { Text(text[index].toString(), style = textStyle) }
        }
    }

    val isFirstEmptyChar = text.length == index
    val borderThickness = if (isTextFieldFocused() && isFirstEmptyChar) activeBorderThickness else inactiveBorderThickness
    val borderColor = when {
        isError() -> errorColor
        isTextFieldFocused() && isFirstEmptyChar -> activeColor
        else -> inactiveColor
    }

    Box(
        modifier = Modifier
            .size(size)
            .border(borderThickness, borderColor, shape),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ValidateUserEmailScreen()
        }
    }
}
