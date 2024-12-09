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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.HighlightedText
import com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.UploadProgressAdType
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun ColumnScope.AdHeader(adScreenType: UploadProgressAdType) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1.0f)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var isHighlighted by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(Unit) { isHighlighted = true }

        Spacer(Modifier.height(Margin.Giant))

        HighlightedText(
            templateRes = R.string.uploadProgressTitleTemplate,
            argumentRes = R.string.uploadProgressTitleArgument,
            style = SwissTransferTheme.typography.bodyMedium,
            isHighlighted = { isHighlighted },
        )

        Spacer(Modifier.height(Margin.Huge))

        Text(
            text = adScreenType.description(),
            textAlign = TextAlign.Center,
            style = SwissTransferTheme.typography.specificLight18,
            modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
        )

        WeightOneSpacer(minHeight = Margin.Medium)

        Image(imageVector = adScreenType.illustration.image(), contentDescription = null)

        WeightOneSpacer(minHeight = Margin.Medium)
    }
}

@PreviewAllWindows
@Composable
private fun AdHeaderPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                AdHeader(UploadProgressAdType.ENERGY)
            }
        }
    }
}
