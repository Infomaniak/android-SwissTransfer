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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.TextUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val VALID_CHARACTERS = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
private const val OPT_LENGTH = 6

@Composable
fun ValidateUserEmailScreen() {
    BottomStickyButtonScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.transferTypeScreenTitle,
                navigationMenu = TopAppBarButton.backButton {},
                TopAppBarButton.closeButton {}
            )
        },
        topButton = { LargeButton(modifier = it, titleRes = R.string.buttonOpenMailApp, onClick = { }) },
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = R.string.validateMailCodeNotReceived,
                style = ButtonType.TERTIARY,
                onClick = {},
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(Margin.Medium),
            verticalArrangement = Arrangement.spacedBy(Margin.Large)
        ) {
            Text(stringResource(R.string.validateMailTitle), style = SwissTransferTheme.typography.h1)

            Text(
                TextUtils.assembleWithBoldArgument(
                    stringResource(R.string.validateMailDescription),
                    "example@example.com",
                ),
                color = SwissTransferTheme.colors.secondaryTextColor,
            )

            CodeVerification()

            Text(
                text = stringResource(R.string.validateMailInfo),
                style = SwissTransferTheme.typography.labelRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
            )
        }
    }
}

@Composable
private fun CodeVerification() {
    var otp by rememberSaveable { mutableStateOf("123") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column {
        Box(contentAlignment = Alignment.Center) {
            OtpTextField(
                modifier = Modifier.fillMaxWidth(),
                otpText = otp,
                otpLength = OPT_LENGTH,
                horizontalArrangement = Arrangement.SpaceBetween,
                onOtpTextChange = { text, isFilled ->
                    otp = text

                    if (isFilled) {
                        scope.launch {
                            isLoading = true
                            delay(2000)
                            isLoading = false

                            isError = otp != "111111"
                        }
                    } else {
                        isError = false
                    }
                },
                isCharacterValid = { it in VALID_CHARACTERS },
                isError = { isError },
                otpTextFieldStyle = OtpTextFieldStyle.default(
                    textStyle = SwissTransferTheme.typography.bodyMedium.copy(color = SwissTransferTheme.colors.secondaryTextColor),
                ),
                isEnabled = { !isLoading }
            )

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier, color = SwissTransferTheme.materialColors.primary)
            }
        }

        Spacer(modifier = Modifier.height(Margin.Micro))

        Text(
            modifier = Modifier.alpha(if (isError) 1f else 0f),
            text = stringResource(R.string.validateMailCodeIncorrectError),
            style = SwissTransferTheme.typography.labelRegular,
            color = SwissTransferTheme.materialColors.error,
        )
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
    otpLength: Int,
    horizontalArrangement: Arrangement.Horizontal,
    onOtpTextChange: (String, Boolean) -> Unit,
    isCharacterValid: ((Char) -> Boolean)? = null,
    otpTextFieldStyle: OtpTextFieldStyle,
    isError: () -> Boolean = { false },
    isEnabled: () -> Boolean = { true },
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpLength) {
            error("Otp text value must not have more than otpCount: $otpLength characters")
        }
    }

    var isTextFieldFocused by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
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
            decorationBox = {
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

        if (!isEnabled()) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(SwissTransferTheme.materialColors.background.copy(alpha = 0.8f)) // Change Color.Blue to any color you want
            )
        }
    }

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

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ValidateUserEmailScreen()
        }
    }
}
