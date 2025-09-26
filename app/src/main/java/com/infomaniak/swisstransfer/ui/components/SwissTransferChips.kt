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

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SwissTransferSuggestionChip(text: String, modifier: Modifier = Modifier) {
    SuggestionChip(
        modifier = modifier,
        onClick = {},
        label = { ChipLabel(text) },
        enabled = false,
        colors = SuggestionChipDefaults.suggestionChipColors(
            disabledContainerColor = SwissTransferTheme.colors.emailAddressChipColor,
            disabledLabelColor = SwissTransferTheme.colors.onEmailAddressChipColor,
        ),
        border = null,
    )
}

@Composable
fun SwissTransferInputChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: () -> Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    InputChip(
        modifier = modifier.widthIn(min = Dimens.InputChipMinWidth),
        selected = isSelected(),
        onClick = onClick,
        label = { ChipLabel(text) },
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
                    contentDescription = stringResource(R.string.contentDescriptionButtonRemoveRecipient, text),
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

@PreviewLightAndDark
@Composable
private fun Preview() {

    fun getLoremText(words: Int) = LoremIpsum(words).values.joinToString(separator = " ")

    SwissTransferTheme {
        Surface {
            Column(Modifier.padding(Margin.Small)) {
                SwissTransferSuggestionChip(text = LoremIpsum(2).values.joinToString(separator = " "))

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(Margin.Mini),
                ) {
                    SwissTransferInputChip(text = getLoremText(2), isSelected = { true }, onClick = {}, onDismiss = { })
                    SwissTransferInputChip(text = getLoremText(1), isSelected = { false }, onClick = {}, onDismiss = { })
                    SwissTransferInputChip(text = getLoremText(4), isSelected = { false }, onClick = {}, onDismiss = { })
                    SwissTransferInputChip(text = getLoremText(1), isSelected = { false }, onClick = {}, onDismiss = { })
                }
            }
        }
    }
}
