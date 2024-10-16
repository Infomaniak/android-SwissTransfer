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

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.components.ButtonSize
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferTypeButton(
    transferType: TransferType,
    isActive: () -> Boolean,
) {
    val (borderColor, contentColor) = if (isActive()) {
        SwissTransferTheme.materialColors.primary to SwissTransferTheme.materialColors.primary
    } else {
        SwissTransferTheme.materialColors.outlineVariant to SwissTransferTheme.colors.secondaryTextColor
    }
    Button(
        modifier = Modifier
            .padding(Margin.XSmall)
            .height(ButtonSize.LARGE.height)
            .border(width = 1.dp, color = borderColor, shape = Shapes.small),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = contentColor),
        onClick = transferType.onClick,
        contentPadding = PaddingValues(Margin.Medium),
    ) {
        Icon(modifier = Modifier.size(Margin.Medium), imageVector = transferType.buttonIcon, contentDescription = null)
        Spacer(modifier = Modifier.width(Margin.Small))
        Text(text = stringResource(id = transferType.buttonText), style = SwissTransferTheme.typography.bodyRegular)
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TransferTypeButtonPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                TransferType.entries.forEach { entry ->
                    TransferTypeButton(
                        transferType = entry,
                        isActive = { entry.ordinal == 0 }
                    )
                }
            }
        }
    }
}

