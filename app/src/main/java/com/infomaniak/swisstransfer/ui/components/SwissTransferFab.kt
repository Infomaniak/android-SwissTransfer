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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun SwissTransferFab(
    modifier: Modifier = Modifier,
    fabType: FabType = FabType.NORMAL,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier.let { fabType.customSize?.let { size -> it.size(size) } ?: it },
        onClick = onClick,
        containerColor = SwissTransferTheme.materialColors.primary,
        shape = fabType.shape,
        elevation = elevation,
    ) {
        Icon(
            imageVector = AppIcons.Add,
            contentDescription = stringResource(id = R.string.contentDescriptionCreateNewTransferButton),
        )
    }
}

enum class FabType(val shape: CornerBasedShape, val customSize: Dp?) {
    NORMAL(Shapes.medium, null),
    BIG(Shapes.large, 80.dp),
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SwissTransferFabPreview() {
    SwissTransferTheme {
        Row {
            SwissTransferFab(onClick = {})
            Spacer(modifier = Modifier.width(Margin.Large))
            SwissTransferFab(fabType = FabType.BIG, onClick = {})
        }
    }
}
