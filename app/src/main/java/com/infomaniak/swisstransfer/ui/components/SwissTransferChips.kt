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

import android.content.res.Configuration
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SwissTransferSuggestionChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    SuggestionChip(
        modifier = modifier,
        onClick = {},
        label = { ChipLabel(text) },
        enabled = false,
        shape = CustomShapes.ROUNDED,
        colors = SuggestionChipDefaults.suggestionChipColors(
            disabledContainerColor = SwissTransferTheme.colors.emailAddressChipColor,
            disabledLabelColor = SwissTransferTheme.colors.onEmailAddressChipColor
        ),
        border = null,
    )
}

@Composable
fun SwissTransferInputChip(
    modifier: Modifier = Modifier,
    text: String,
    focusManager: FocusManager,
    onDismiss: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var enabled by remember { mutableStateOf(false) }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    InputChip(
        modifier = modifier
            .focusRequester(focusRequester)
            .widthIn(min = Dimens.InputChipMinWidth)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
                if (!isFocused) {
                    enabled = false
                    focusManager.moveFocus(FocusDirection.Exit)
                }
            }
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyUp && event.key == Key.Backspace) {
                    when {
                        enabled -> onDismiss()
                        isFocused -> enabled = true
                    }
                    return@onKeyEvent true
                }
                false
            },
        selected = isFocused,
        onClick = {
            focusRequester.requestFocus()
        },
        label = { ChipLabel(text) },
        shape = CustomShapes.ROUNDED,
        colors = InputChipDefaults.inputChipColors(
            containerColor = SwissTransferTheme.colors.emailAddressChipColor,
            labelColor = SwissTransferTheme.colors.onEmailAddressChipColor,
            selectedLabelColor = SwissTransferTheme.colors.emailAddressChipColor,
            selectedContainerColor = SwissTransferTheme.colors.onEmailAddressChipColor,
            selectedTrailingIconColor = SwissTransferTheme.colors.emailAddressChipColor,
            trailingIconColor = SwissTransferTheme.colors.onEmailAddressChipColor,
        ),
        border = null,
        trailingIcon = {
            IconButton(modifier = Modifier.size(Dimens.IconSize), onClick = onDismiss) {
                Icon(
                    modifier = Modifier.size(Dimens.MicroIconSize),
                    imageVector = AppImages.AppIcons.CrossThick,
                    contentDescription = "Localized description", // TODO
                )
            }
        },
    )
}

@Composable
private fun ChipLabel(text: String) {
    Text(
        text = text,
        style = SwissTransferTheme.typography.bodyRegular,
        maxLines = 1,
        overflow = TextOverflow.MiddleEllipsis,
    )
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun EmailAddressChipPreview() {

    SwissTransferTheme {
        Surface {
            Column(Modifier.padding(Margin.Small)) {
                SwissTransferSuggestionChip(text = LoremIpsum(2).values.joinToString(separator = " "))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini)
                ) {
                    val focusManager = LocalFocusManager.current
                    SwissTransferInputChip(
                        text = LoremIpsum(2).values.joinToString(separator = " "),
                        focusManager = focusManager,
                    ) { }
                    SwissTransferInputChip(
                        text = LoremIpsum(1).values.joinToString(separator = " "),
                        focusManager = focusManager,
                    ) { }
                    SwissTransferInputChip(
                        text = LoremIpsum(4).values.joinToString(separator = " "),
                        focusManager = focusManager,
                    ) { }
                    SwissTransferInputChip(
                        text = LoremIpsum(1).values.joinToString(separator = " "),
                        focusManager = focusManager,
                    ) { }
                }
            }
        }
    }
}
