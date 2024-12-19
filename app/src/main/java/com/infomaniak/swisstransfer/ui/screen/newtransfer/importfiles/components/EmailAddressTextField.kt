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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core2.isEmail
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferInputChip
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

private const val UNSELECTED_CHIP_INDEX = -1

@Composable
fun EmailAddressTextField(
    modifier: Modifier = Modifier,
    label: String,
    initialValue: String,
    validatedEmails: GetSetCallbacks<Set<String>>,
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) {

    var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }
    var currentSelectedChip by remember { mutableIntStateOf(UNSELECTED_CHIP_INDEX) }
    val interactionSource = remember { MutableInteractionSource() }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val cursorColor by animateColorAsState(
        targetValue = if (isError) SwissTransferTheme.materialColors.error else SwissTransferTheme.materialColors.primary,
        label = "CursorColor",
    )

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedLabelColor = SwissTransferTheme.colors.tertiaryTextColor,
        unfocusedSupportingTextColor = SwissTransferTheme.colors.tertiaryTextColor,
        disabledBorderColor = SwissTransferTheme.materialColors.outline,
        unfocusedTrailingIconColor = SwissTransferTheme.colors.iconColor,
        disabledTrailingIconColor = SwissTransferTheme.colors.iconColor,
    )

    fun updateUiTextValue(newValue: TextFieldValue) {
        currentSelectedChip = UNSELECTED_CHIP_INDEX
        textFieldValue = newValue
        onValueChange(newValue)
    }

    fun getLastEmailIndex() = validatedEmails.get().toList().lastIndex

    fun handleBackspace() = if (currentSelectedChip == UNSELECTED_CHIP_INDEX) {
        currentSelectedChip = getLastEmailIndex()
        false
    } else {
        validatedEmails.get().elementAtOrNull(currentSelectedChip)?.let { email ->
            validatedEmails.set(validatedEmails.get().minusElement(email))
        }
        currentSelectedChip = UNSELECTED_CHIP_INDEX
        true
    }

    fun onKeyEvent(event: KeyEvent): Boolean = when {
        event.type != KeyEventType.KeyDown -> false
        event.key == Key.Backspace -> handleBackspace()
        event.isNavigatingLeft() -> {
            if (currentSelectedChip == UNSELECTED_CHIP_INDEX && textFieldValue.selection.start == 0) {
                currentSelectedChip = getLastEmailIndex()
                true
            } else if (currentSelectedChip != UNSELECTED_CHIP_INDEX) {
                currentSelectedChip--
                true
            } else {
                false
            }
        }
        event.isNavigatingRight() && currentSelectedChip != UNSELECTED_CHIP_INDEX -> {
            currentSelectedChip++
            true
        }
        else -> {
            currentSelectedChip = UNSELECTED_CHIP_INDEX
            false
        }
    }

    val keyboardActions = KeyboardActions(
        onDone = {
            val trimmedText = textFieldValue.text.trim()
            if (trimmedText.isEmail()) {
                validatedEmails.set(validatedEmails.get() + trimmedText)
                updateUiTextValue(TextFieldValue())
            }
        },
    )

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Done,
        showKeyboardOnFocus = true,
    )

    val emailAddressTextFieldModifier = modifier
        .fillMaxWidth()
        .onPreviewKeyEvent(::onKeyEvent)

    BasicTextField(
        modifier = emailAddressTextFieldModifier,
        value = textFieldValue,
        onValueChange = ::updateUiTextValue,
        textStyle = TextStyle(color = SwissTransferTheme.colors.primaryTextColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(cursorColor),
        decorationBox = { innerTextField ->
            EmailAddressDecorationBox(
                text = textFieldValue.text,
                validatedEmails = validatedEmails,
                currentSelectedChip = GetSetCallbacks(get = { currentSelectedChip }, set = { currentSelectedChip = it }),
                innerTextField = innerTextField,
                isFocused = isFocused,
                label = label,
                interactionSource = interactionSource,
                isError = isError,
                supportingText = supportingText,
                textFieldColors = textFieldColors,
            )
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EmailAddressDecorationBox(
    text: String,
    validatedEmails: GetSetCallbacks<Set<String>>,
    currentSelectedChip: GetSetCallbacks<Int>,
    innerTextField: @Composable () -> Unit,
    isFocused: Boolean,
    label: String,
    interactionSource: MutableInteractionSource,
    isError: Boolean,
    supportingText: @Composable (() -> Unit)?,
    textFieldColors: TextFieldColors,
) {
    OutlinedTextFieldDefaults.DecorationBox(
        value = text,
        innerTextField = {
            EmailChipsAndInnerTextField(
                validatedEmails = validatedEmails,
                currentSelectedChip = currentSelectedChip,
                innerTextField = innerTextField,
            )
        },
        enabled = true,
        singleLine = true,
        visualTransformation = if (validatedEmails.get().isNotEmpty() && !isFocused) {
            // TODO: Remove this hack to make the label always in "above" position when the labelPosition will be
            //  available in the DecorationBox's API
            VisualTransformation { TransformedText(AnnotatedString(label), OffsetMapping.Identity) }
        } else {
            VisualTransformation.None
        },
        interactionSource = interactionSource,
        isError = isError,
        supportingText = supportingText,
        label = { Text(label) },
        colors = textFieldColors,
    ) {
        OutlinedTextFieldDefaults.Container(
            enabled = true,
            isError = isError,
            interactionSource = interactionSource,
            colors = textFieldColors,
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun EmailChipsAndInnerTextField(
    validatedEmails: GetSetCallbacks<Set<String>>,
    currentSelectedChip: GetSetCallbacks<Int>,
    innerTextField: @Composable () -> Unit,
) {

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        validatedEmails.get().forEachIndexed { index, email ->
            SwissTransferInputChip(
                text = email,
                isSelected = { currentSelectedChip.get() == index },
                onClick = { currentSelectedChip.set(index) },
                onDismiss = { validatedEmails.set(validatedEmails.get().minus(email)) },
            )
        }

        Box(
            modifier = Modifier
                .widthIn(min = 80.dp)
                .weight(1f)
        ) {
            innerTextField()
        }
    }
}

private fun KeyEvent.isNavigatingLeft() = key == Key.DirectionLeft || key == Key.NavigatePrevious
private fun KeyEvent.isNavigatingRight() = key == Key.DirectionRight || key == Key.NavigateNext

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(EmailsPreviewParameter::class) emails: List<String>) {
    val label = stringResource(R.string.transferRecipientAddressPlaceholder)

    SwissTransferTheme {
        Surface {
            Column(Modifier.padding(Margin.Medium)) {
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emails.take(5).toSet() }, set = {}),
                    label = label,
                    initialValue = "test.test@ik.me",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emptySet() }, set = {}),
                    label = label,
                    initialValue = "",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
                    label = label,
                    initialValue = "test",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
                    label = label,
                    initialValue = "test",
                    onValueChange = {},
                    isError = true,
                    supportingText = { Text(stringResource(R.string.invalidAddress)) }
                )
            }
        }
    }
}
