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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

private val BOTTOM_SHEET_ITEM_HEIGHT = 56.dp

// Unused for now, but this is still the official component with the appropriate style of a bottom sheet item so this needs to be
// used if we ever create a new bottom sheet
@Composable
fun BottomSheetItem(imageVector: ImageVector, @StringRes titleRes: Int, onClick: () -> Unit) {
    SharpRippleButton(
        modifier = Modifier
            .height(BOTTOM_SHEET_ITEM_HEIGHT)
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Large),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(Dimens.IconSize),
                imageVector = imageVector,
                contentDescription = null,
                tint = SwissTransferTheme.materialColors.primary,
            )
            Spacer(Modifier.width(Margin.Large))
            Text(
                text = stringResource(id = titleRes),
                style = SwissTransferTheme.typography.bodyRegular,
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun ChoiceItemPreview() {
    SwissTransferTheme {
        Surface {
            BottomSheetItem(AppIcons.Add, R.string.appName) {}
        }
    }
}
