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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun IllustratedMessageBlock(
    content: (@Composable () -> Unit)?,
    title: String?,
    description: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content?.let {
            it()
            Spacer(Modifier.height(Margin.Huge))
        }

        title?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.h1,
            )
        }

        description?.let {
            Spacer(Modifier.height(Margin.Medium))

            Text(
                text = description,
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun IllustratedMessageBlockPreview() {
    SwissTransferTheme {
        Surface {
            IllustratedMessageBlock(
                content = { Image(imageVector = AppIllus.MascotSearching.image(), contentDescription = null) },
                title = stringResource(R.string.noTransferReceivedTitle),
                description = stringResource(R.string.noTransferReceivedDescription),
            )
        }
    }
}
