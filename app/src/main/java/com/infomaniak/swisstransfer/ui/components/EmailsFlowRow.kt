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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.previewparameter.EmailsPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmailsFlowRow(
    emails: List<String>,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
    ) {
        emails.forEach {
            SwissTransferSuggestionChip(
                text = it,
                modifier = Modifier.padding(horizontal = Margin.Micro),
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(EmailsPreviewParameter::class) emails: List<String>) {
    SwissTransferTheme {
        Surface {
            EmailsFlowRow(emails)
        }
    }
}
