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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.HideSelectAllTextToolbar
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows


/**
 * @throws IllegalStateException if the first otpText is bigger than the maximum specified number of characters specified by
 * [otpLength] or if it has invalid characters according to [isCharacterValid].
 */
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int,
    horizontalArrangement: Arrangement.Horizontal,
    onOtpTextChange: (String, Boolean) -> Unit,
    isCharacterValid: ((Char) -> Boolean)? = null,
    otpTextFieldStyle: OtpTextFieldStyle = OtpTextFieldStyle.default(),
    isError: () -> Boolean = { false },
    isEnabled: () -> Boolean = { true },
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpLength) {
            error("Otp text value must not have more than otpCount: $otpLength characters")
        } else if (isCharacterValid != null && otpText.any { !isCharacterValid(it) }) {
            error("Otp text value must not have invalid characters based on the isCharacterValid rule")
        }
    }

    var isTextFieldFocused by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
        ProvideTextToolbarWithoutSelectAll {
            BasicTextField(
                modifier = modifier.onFocusChanged { isTextFieldFocused = it.isFocused },
                value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
                onValueChange = { textFieldValue ->
                    val text = isCharacterValid?.let { textFieldValue.text.filter { char -> it(char) } } ?: textFieldValue.text

                    if (text.length <= otpLength) {
                        onOtpTextChange.invoke(text, text.length == otpLength)
                    } else {
                        val truncatedText = text.take(otpLength)
                        if (truncatedText != otpText) {
                            onOtpTextChange.invoke(truncatedText, true)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done,
                ),
                decorationBox = { innerTextField ->
                    // Draw innerTextField but hides it and its text selection handles. This is needed to have the system
                    // copy/paste context menu place correctly on the screen
                    CompositionLocalProvider(
                        LocalTextSelectionColors provides TextSelectionColors(Color.Transparent, Color.Transparent)
                    ) {
                        Box(modifier = Modifier.alpha(0f)) { innerTextField() }
                    }

                    Row(horizontalArrangement = horizontalArrangement) {
                        repeat(otpLength) { index ->
                            CharView(
                                index = index,
                                text = otpText,
                                otpCount = otpLength,
                                isTextFieldFocused = { isTextFieldFocused },
                                isError = isError,
                                otpTextFieldStyle = otpTextFieldStyle,
                            )
                        }
                    }
                },
                enabled = isEnabled(),
            )
        }

        if (!isEnabled()) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(SwissTransferTheme.materialColors.background.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
fun ProvideTextToolbarWithoutSelectAll(content: @Composable () -> Unit) {
    val previousToolbar = LocalTextToolbar.current
    CompositionLocalProvider(LocalTextToolbar provides HideSelectAllTextToolbar(previousToolbar), content)
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    otpCount: Int,
    isTextFieldFocused: () -> Boolean,
    isError: () -> Boolean,
    otpTextFieldStyle: OtpTextFieldStyle,
) = with(otpTextFieldStyle) {
    val content: @Composable BoxScope.() -> Unit = when {
        index >= text.length -> {
            {}
        }
        else -> {
            { Text(text[index].toString(), style = textStyle) }
        }
    }

    val isCharFocused = text.length == index || (text.length == otpCount && index == otpCount - 1)
    val borderThickness = if (isTextFieldFocused() && isCharFocused) activeBorderThickness else inactiveBorderThickness
    val borderColor = when {
        isError() -> errorColor
        isTextFieldFocused() && isCharFocused -> activeColor
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

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(Margin.Medium)) {
                OtpTextField(
                    otpText = "123",
                    otpLength = 6,
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                    onOtpTextChange = { _, _ -> },
                )

                OtpTextField(
                    otpText = "123456",
                    otpLength = 6,
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                    onOtpTextChange = { _, _ -> },
                    isError = { true },
                )
            }
        }
    }
}
