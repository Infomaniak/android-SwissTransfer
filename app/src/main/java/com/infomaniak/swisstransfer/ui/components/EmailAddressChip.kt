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
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun EmailAddressChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    SuggestionChip(
        onClick = {},
        label = {
            Text(
                text = text,
                style = SwissTransferTheme.typography.bodyRegular,
                maxLines = 1,
                overflow = TextOverflow.MiddleEllipsis,
            )
        },
        modifier = modifier,
        enabled = false,
        shape = CustomShapes.ROUNDED,
        colors = SuggestionChipDefaults.suggestionChipColors(
            disabledContainerColor = SwissTransferTheme.colors.emailAddressChipColor,
            disabledLabelColor = SwissTransferTheme.colors.onEmailAddressChipColor
        ),
        border = null,
    )
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun EmailAddressChipPreview() {
    SwissTransferTheme {
        Surface {
            EmailAddressChip(text = "test.test@ik.me")
        }
    }
}
