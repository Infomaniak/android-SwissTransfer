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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.LogoInfomaniak
import com.infomaniak.swisstransfer.ui.images.illus.LogoSwissTransfer
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BrandTopAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(imageVector = AppIllus.LogoInfomaniak, contentDescription = null)
                Spacer(modifier = Modifier.width(Margin.Medium))
                VerticalDivider(modifier = Modifier.height(Margin.Large), color = SwissTransferTheme.colors.toolbarTextColor)
                Spacer(modifier = Modifier.width(Margin.Medium))
                Image(imageVector = AppIllus.LogoSwissTransfer, contentDescription = null)
                Spacer(modifier = Modifier.width(Margin.Mini))
                Text(text = stringResource(id = R.string.appName), color = SwissTransferTheme.colors.toolbarTextColor)
            }
        },
    )
}

@PreviewAllWindows
@Composable
private fun BrandTopAppBarPreview() {
    SwissTransferTheme {
        BrandTopAppBar()
    }
}
