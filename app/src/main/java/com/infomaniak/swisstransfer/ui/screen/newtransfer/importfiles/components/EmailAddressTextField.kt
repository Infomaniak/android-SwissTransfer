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

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.CrossThick
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun EmailAddressTextField(modifier: Modifier) {

}

@Composable
fun SwissTransferInputChip(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(false) }

    InputChip(
        modifier = modifier,
        border = null,
        colors = InputChipDefaults.inputChipColors(
            containerColor = SwissTransferTheme.colors.emailAddressChipColor,
            labelColor = SwissTransferTheme.colors.onEmailAddressChipColor,
            selectedLabelColor = SwissTransferTheme.colors.emailAddressChipColor,
            selectedContainerColor = SwissTransferTheme.colors.onEmailAddressChipColor,
            selectedTrailingIconColor = SwissTransferTheme.colors.emailAddressChipColor,
            trailingIconColor = SwissTransferTheme.colors.onEmailAddressChipColor,
        ),
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        label = { Text(text) },
        selected = enabled,
        trailingIcon = {
            IconButton(modifier = Modifier.size(Dimens.IconSize), onClick = {}) {
                Icon(
                    modifier = Modifier.size(Dimens.MiniIconSize),
                    imageVector = AppImages.AppIcons.CrossThick,
                    contentDescription = "Localized description", // TODO
                )
            }
        },
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Row(
                Modifier
                    .padding(Margin.Small)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(Margin.Mini)
            ) {
                SwissTransferInputChip(text = LoremIpsum(2).values.joinToString(separator = " ")) { }
                SwissTransferInputChip(text = LoremIpsum(1).values.joinToString(separator = " ")) { }
                SwissTransferInputChip(text = LoremIpsum(4).values.joinToString(separator = " ")) { }
                SwissTransferInputChip(text = LoremIpsum(1).values.joinToString(separator = " ")) { }
            }
        }
    }
}
