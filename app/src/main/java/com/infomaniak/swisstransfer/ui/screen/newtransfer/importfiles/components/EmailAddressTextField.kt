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

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.ui.components.SwissTransferInputChip
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmailAddressTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: () -> String,
    emails: List<String>,
    onValueChange: (TextFieldValue) -> Unit,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) {

    var textState by remember { mutableStateOf(TextFieldValue(text())) }
    var isFocused by rememberSaveable { mutableStateOf(false) }
    var isErrorState by rememberSaveable { mutableStateOf(isError) }


    // Dynamic border color based on state (error, success, focus)
    val borderColor by animateColorAsState(
        targetValue = when {
            isErrorState -> SwissTransferTheme.materialColors.error
            isFocused -> SwissTransferTheme.materialColors.primary
            else -> SwissTransferTheme.materialColors.outline
        },
        label = "BorderColor",
    )

    // Dynamic text color
    val textColor by animateColorAsState(
        targetValue = if (isErrorState) SwissTransferTheme.materialColors.error else SwissTransferTheme.colors.primaryTextColor,
        label = "TextColor",
    )
    val labelColor by animateColorAsState(
        targetValue = if (isFocused) {
            SwissTransferTheme.materialColors.primary
        } else {
            SwissTransferTheme.colors.tertiaryTextColor
        },
        label = "TextColor",
    )

    FlowRow(
        modifier = modifier
            .border(
                width = if (isFocused) 2.dp else Dimens.BorderWidth,
                color = borderColor,
                shape = MaterialTheme.shapes.extraSmall,
            )
            .fillMaxWidth()
            .padding(horizontal = Margin.Medium),
        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
    ) {
        emails.forEach {
            SwissTransferInputChip(
                text = it,
                onDismiss = { TODO() }
            )
        }
        Box(
            modifier = Modifier
                .height(56.dp)
                // This minimum width that TextField can have
                // if remaining space in same row is smaller it's moved to next line
                .widthIn(min = 80.dp)
                .weight(1f),
            contentAlignment = Alignment.CenterStart,
        ) {
            BasicTextField(
                value = textState,
                onValueChange = {
                    // TODO replace this and use email regex
                    textState = it
                    isErrorState = textState.text.length in 1..3
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused }
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = textColor),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Row(
                            modifier = Modifier
                                .defaultMinSize(minWidth = 4.dp)
                                .wrapContentWidth(),
                        ) {
                            if (textState.text.isEmpty() && emails.isEmpty()) {
                                Text(maxLines = 1, text = label, color = labelColor)
                            } else {
                                innerTextField()
                            }
                        }
                    }
                },
                // interactionSource = textFieldInteraction,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    showKeyboardOnFocus = true,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                cursorBrush = SolidColor(borderColor),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EmailAddressTextFieldTOTO(
    modifier: Modifier = Modifier,
    label: String,
    text: () -> String,
    emails: List<String>,
    onValueChange: (TextFieldValue) -> Unit,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) {

    val interactionSource = remember { MutableInteractionSource() }
    var textState by remember { mutableStateOf(TextFieldValue(text())) }
    var isFocused by rememberSaveable { mutableStateOf(false) }
    var isErrorState by rememberSaveable { mutableStateOf(isError) }

    // Dynamic border color based on state (error, success, focus)
    val borderColor by animateColorAsState(
        targetValue = when {
            isErrorState -> SwissTransferTheme.materialColors.error
            isFocused -> SwissTransferTheme.materialColors.primary
            else -> SwissTransferTheme.materialColors.outline
        },
        label = "BorderColor",
    )

    val labelColor by animateColorAsState(
        targetValue = if (isFocused) {
            SwissTransferTheme.materialColors.primary
        } else {
            SwissTransferTheme.colors.tertiaryTextColor
        },
        label = "TextColor",
    )

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedLabelColor = SwissTransferTheme.colors.tertiaryTextColor,
        unfocusedSupportingTextColor = SwissTransferTheme.colors.tertiaryTextColor,
        disabledBorderColor = SwissTransferTheme.materialColors.outline,
        unfocusedTrailingIconColor = SwissTransferTheme.colors.iconColor,
        disabledTrailingIconColor = SwissTransferTheme.colors.iconColor,
    )
    val interactions by interactionSource.interactions.collectAsStateWithLifecycle(null)
    val focused = interactionSource.collectIsFocusedAsState()
    Log.e("TOTO", "EmailAddressTextFieldTOTO: $focused / $isFocused / $interactions")

    BasicTextField(
        value = textState,
        onValueChange = {
            Log.e("TOTO", "onvalue change: $focused / $isFocused / $interactions")
            // TODO replace this and use email regex
            textState = it
            isErrorState = textState.text.length in 1..3
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
                    interactionSource.tryEmit(
                    if (focusState.isFocused) {
                        FocusInteraction.Focus()
                    } else {
                        FocusInteraction.Unfocus(FocusInteraction.Focus())
                    }
                )
            }
            .fillMaxWidth(),
        textStyle = TextStyle(color = SwissTransferTheme.colors.primaryTextColor),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            showKeyboardOnFocus = true,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        cursorBrush = SolidColor(borderColor),
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = textState.text,
                innerTextField = {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                        itemVerticalAlignment = Alignment.CenterVertically,
                    ) {
                        emails.forEach {
                            SwissTransferInputChip(
                                text = it,
                                onDismiss = { TODO() }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .widthIn(min = 80.dp)
                                .weight(1f),
                        ) {
                            innerTextField()
                        }
                    }
                },
                enabled = true,
                singleLine = false,
                visualTransformation = VisualTransformation.None,
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
    SwissTransferTheme {
        Surface {
            Column(Modifier.padding(Margin.Medium)) {
                EmailAddressTextFieldTOTO(
                    emails = emails.take(5),
                    label = "Your email address",
                    text = { "test.test@ik.me" },
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                    focusRequester = FocusRequester.Default,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    emails = emptyList(),
                    label = "Your email address",
                    text = { "" },
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                    focusRequester = FocusRequester.Default,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextField(
                    emails = emails.take(1),
                    label = "Your email address",
                    text = { "test" },
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                    focusRequester = FocusRequester.Default,
                )
                Spacer(Modifier.height(Margin.Large))
                EmailAddressTextFieldTOTO(
                    emails = emails.take(1),
                    label = "Your email address",
                    text = { "test" },
                    onValueChange = {},
                    focusManager = LocalFocusManager.current,
                    focusRequester = FocusRequester.Default,
                    isError = true,
                    supportingText = { Text("error") }
                )
            }
        }
    }
}
