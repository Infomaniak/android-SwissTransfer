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
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.FocusInteraction.Focus
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core2.isEmail
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferInputChip
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

const val EMAIL_FIELD_TAG = "EmailAddressTextField"

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EmailAddressTextField(
    modifier: Modifier = Modifier,
    label: String,
    initialValue: String,
    validatedEmails: GetSetCallbacks<Set<String>>,
    onValueChange: (String) -> Unit,
    focusManager: FocusManager,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) {

    var text by rememberSaveable { mutableStateOf(initialValue) }
    var currentFocus: Focus? by remember { mutableStateOf(null) }
    val interactionSource = remember { MutableInteractionSource() }
    val lastChipFocusRequester = remember { FocusRequester() }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val cursorColor by animateColorAsState(
        targetValue = if (isError) {
            SwissTransferTheme.materialColors.error
        } else {
            SwissTransferTheme.materialColors.primary
        },
        label = "CursorColor",
    )

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedLabelColor = SwissTransferTheme.colors.tertiaryTextColor,
        unfocusedSupportingTextColor = SwissTransferTheme.colors.tertiaryTextColor,
        disabledBorderColor = SwissTransferTheme.materialColors.outline,
        unfocusedTrailingIconColor = SwissTransferTheme.colors.iconColor,
        disabledTrailingIconColor = SwissTransferTheme.colors.iconColor,
    )

    fun updateUiTextValue(newValue: String) {
        text = newValue
        onValueChange(newValue)
    }

    BasicTextField(
        value = text,
        onValueChange = ::updateUiTextValue,
        modifier = modifier
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    val newFocus = Focus()
                    if (interactionSource.tryEmit(newFocus)) currentFocus = newFocus
                } else {
                    currentFocus?.let { interactionSource.tryEmit(FocusInteraction.Unfocus(it)) }
                }
            }
            .fillMaxWidth()
            .onPreviewKeyEvent { event ->
                val shouldFocusLastChip = isFocused && validatedEmails.get().isNotEmpty()
                if (event.type == KeyEventType.KeyDown && event.key == Key.Backspace && shouldFocusLastChip) {
                    runCatching {
                        lastChipFocusRequester.requestFocus()
                    }.onFailure {
                        SentryLog.e(EMAIL_FIELD_TAG, "The focusRequested wasn't registered with a non empty Chip list", it)
                    }

                    return@onPreviewKeyEvent true
                }
                false
            },
        textStyle = TextStyle(color = SwissTransferTheme.colors.primaryTextColor),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            showKeyboardOnFocus = true,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                val trimmedText = text.trim()
                if (trimmedText.isEmail()) {
                    validatedEmails.set(validatedEmails.get() + trimmedText)
                    updateUiTextValue("")
                }
            },
        ),
        singleLine = true,
        cursorBrush = SolidColor(cursorColor),
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                        itemVerticalAlignment = Alignment.CenterVertically,
                    ) {
                        validatedEmails.get().forEach { email ->
                            val chipModifier = if (email == validatedEmails.get().lastOrNull()) {
                                Modifier.focusRequester(lastChipFocusRequester)
                            } else {
                                Modifier
                            }
                            SwissTransferInputChip(
                                modifier = chipModifier,
                                text = email,
                                focusManager = focusManager,
                                onDismiss = {
                                    validatedEmails.set(validatedEmails.get().minus(email))
                                    focusManager.moveFocus(FocusDirection.Exit)
                                },
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
    )
}

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
                    focusManager = LocalFocusManager.current,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emptySet() }, set = {}),
                    label = label,
                    initialValue = "",
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
                    label = label,
                    initialValue = "test",
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    validatedEmails = GetSetCallbacks(get = { emails.take(1).toSet() }, set = {}),
                    label = label,
                    initialValue = "test",
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                    isError = true,
                    supportingText = { Text(stringResource(R.string.invalidAddress)) }
                )
            }
        }
    }
}