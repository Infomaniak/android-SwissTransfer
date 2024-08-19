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
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Camera
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun BottomSheetItem(imageVector: ImageVector, @StringRes titleRes: Int, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.textButtonColors(contentColor = SwissTransferTheme.colors.primaryTextColor),
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = CustomShapes.None,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(Margin.Large),
                imageVector = imageVector,
                contentDescription = null,
                tint = SwissTransferTheme.materialColors.primary
            )
            Spacer(modifier = Modifier.width(Margin.Large))
            Text(
                text = stringResource(id = titleRes),
                style = SwissTransferTheme.typography.bodyRegular,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ChoiceItemPreview() {
    SwissTransferTheme {
        Surface {
            BottomSheetItem(AppIcons.Camera, R.string.transferUploadSourceChoiceCamera) {}
        }
    }
}
