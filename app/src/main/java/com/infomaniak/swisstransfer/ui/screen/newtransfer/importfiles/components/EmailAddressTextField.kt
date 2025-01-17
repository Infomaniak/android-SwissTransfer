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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core2.isValidEmail
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferInputChip
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextFieldDefaults
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun EmailAddressTextField(
    modifier: Modifier = Modifier,
    label: String,
    initialValue: String,
    validatedRecipientsEmails: GetSetCallbacks<Set<String>>,
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) {

    val state = remember(validatedRecipientsEmails) {
        EmailAddressTextFieldState(initialValue, validatedRecipientsEmails, onValueChange)
    }
    val textFieldValue by state::textFieldValue
    val interactionSource = remember { MutableInteractionSource() }

    val cursorColor by animateColorAsState(
        targetValue = if (isError) SwissTransferTheme.materialColors.error else SwissTransferTheme.materialColors.primary,
        label = "CursorColor",
    )

    val keyboardActions = KeyboardActions(onDone = { state.addRecipientAddress() })

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Done,
        showKeyboardOnFocus = true,
    )

    val emailAddressTextFieldModifier = modifier
        .fillMaxWidth()
        .onPreviewKeyEvent(state::onKeyEvent)
        .onFocusChanged { event ->
            if (!event.isFocused) {
                state.unselectChip()
                state.addRecipientAddress()
            }
        }

    BasicTextField(
        modifier = emailAddressTextFieldModifier,
        value = textFieldValue,
        onValueChange = state::updateUiTextValue,
        textStyle = TextStyle(color = SwissTransferTheme.colors.primaryTextColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(cursorColor),
        decorationBox = { innerTextField ->
            EmailAddressDecorationBox(
                text = textFieldValue.text,
                validatedRecipientsEmails = validatedRecipientsEmails,
                selectedChipIndexState = state.selectedChipIndexState,
                innerTextField = innerTextField,
                label = label,
                interactionSource = interactionSource,
                isError = isError,
                supportingText = supportingText,
                textFieldColors = SwissTransferTextFieldDefaults.colors(),
            )
        }
    )
}

private class EmailAddressTextFieldState(
    initialText: String,
    private val validatedRecipientsEmails: GetSetCallbacks<Set<String>>,
    private val onValueChange: (TextFieldValue) -> Unit
) {
    var textFieldValue by mutableStateOf(TextFieldValue(initialText))

    val selectedChipIndexState = mutableIntStateOf(UNSELECTED_CHIP_INDEX)
    var selectedChipIndex by selectedChipIndexState

    fun unselectChip() {
        selectedChipIndex = UNSELECTED_CHIP_INDEX
    }


    fun updateUiTextValue(newValue: TextFieldValue) {
        unselectChip()
        textFieldValue = newValue
        onValueChange(newValue)
    }

    fun addRecipientAddress() {
        val trimmedText = textFieldValue.text.trim()
        if (trimmedText.isValidEmail()) {
            validatedRecipientsEmails.set(validatedRecipientsEmails.get() + trimmedText)
            updateUiTextValue(TextFieldValue())
        }
    }

    fun onKeyEvent(event: KeyEvent) = when {
        event.type != KeyEventType.KeyDown -> false
        event.key == Key.Backspace -> handleBackspace()
        event.isNavigatingLeft() -> handlePreviousNavigation()
        event.isNavigatingRight() -> handleForwardNavigation()
        else -> {
            unselectChip()
            false
        }
    }

    private fun handleForwardNavigation(): Boolean = when {
        selectedChipIndex == UNSELECTED_CHIP_INDEX -> false
        selectedChipIndex < getLastEmailIndex() -> {
            selectedChipIndex++
            true
        }
        else -> {
            // The currently selected chip is the last one, so going right should deselect it and come back to the text
            unselectChip()
            true
        }
    }

    private fun handleBackspace() = if (selectedChipIndex == UNSELECTED_CHIP_INDEX) {
        // If no chip is currently selected, we select the last one
        selectedChipIndex = getLastEmailIndex()
        false
    } else {
        // If any chip is already selected, pressing on backspace deletes it and reset the selection
        validatedRecipientsEmails.get().elementAtOrNull(selectedChipIndex)?.let { email ->
            validatedRecipientsEmails.set(validatedRecipientsEmails.get().minusElement(email))
        }
        unselectChip()
        true
    }

    private fun getLastEmailIndex() = validatedRecipientsEmails.get().toList().lastIndex

    private fun handlePreviousNavigation(): Boolean = when {
        selectedChipIndex == UNSELECTED_CHIP_INDEX && textFieldValue.selection.start == 0 -> {
            // If we go left when the cursor is already at the start of the textField, we select the last chip
            selectedChipIndex = getLastEmailIndex()
            true
        }
        selectedChipIndex != UNSELECTED_CHIP_INDEX -> {
            selectedChipIndex--
            true
        }
        else -> false
    }

    companion object {
        private const val UNSELECTED_CHIP_INDEX = -1
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EmailAddressDecorationBox(
    text: String,
    validatedRecipientsEmails: GetSetCallbacks<Set<String>>,
    selectedChipIndexState: MutableIntState,
    innerTextField: @Composable () -> Unit,
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
                validatedRecipientsEmails = validatedRecipientsEmails,
                selectedChipIndexState = selectedChipIndexState,
                innerTextField = innerTextField,
            )
        },
        enabled = true,
        singleLine = true,
        visualTransformation = if (validatedRecipientsEmails.get().isNotEmpty()) {
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
    validatedRecipientsEmails: GetSetCallbacks<Set<String>>,
    selectedChipIndexState: MutableIntState,
    innerTextField: @Composable () -> Unit,
) {
    var selectedChipIndex by selectedChipIndexState
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        validatedRecipientsEmails.get().forEachIndexed { index, email ->
            SwissTransferInputChip(
                text = email,
                isSelected = { selectedChipIndex == index },
                onClick = { selectedChipIndex = index },
                onDismiss = { validatedRecipientsEmails.set(validatedRecipientsEmails.get().minus(email)) },
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
                    validatedRecipientsEmails = GetSetCallbacks(get = { emails.take(5).toSet() }, set = {}),
                    label = label,
                    initialValue = "test.test@ik.me",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedRecipientsEmails = GetSetCallbacks(get = { emptySet() }, set = {}),
                    label = label,
                    initialValue = "",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedRecipientsEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
                    label = label,
                    initialValue = "test",
                    onValueChange = {},
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedRecipientsEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
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
