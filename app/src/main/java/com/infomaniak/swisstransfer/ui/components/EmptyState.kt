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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun EmptyState(
    content: (@Composable () -> Unit)? = null,
    @StringRes titleRes: Int? = null,
    @StringRes descriptionRes: Int? = null,
    modifier: Modifier = Modifier,
) = EmptyState(content, titleRes?.let { stringResource(it) }, descriptionRes?.let { stringResource(it) }, modifier)

@Composable
fun EmptyState(
    content: (@Composable () -> Unit)? = null,
    title: String?,
    description: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        IllustratedMessageBlock(
            content = content,
            title = title,
            description = description,
            modifier = modifier.padding(horizontal = Margin.Medium),
        )
    }
}

@PreviewLightAndDark
@Composable
private fun EmptyStatePreview() {
    SwissTransferTheme {
        Surface {
            EmptyState(
                content = { Image(imageVector = AppIllus.MascotSearching.image(), contentDescription = null) },
                titleRes = R.string.noTransferReceivedTitle,
                descriptionRes = R.string.noTransferReceivedDescription,
            )
        }
    }
}
