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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferType
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

private val SPACE_AROUND_BUTTONS = 20.dp
private val COLUMN_MIN_WIDTH = 150.dp

@Composable
fun TransferTypeButtons(items: List<TransferType>, navigateToTransfer: (TransferType) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = SPACE_AROUND_BUTTONS)
            .wrapContentHeight(),
        userScrollEnabled = false,
        columns = GridCells.Adaptive(COLUMN_MIN_WIDTH),
        verticalArrangement = Arrangement.spacedBy(SPACE_AROUND_BUTTONS),
        horizontalArrangement = Arrangement.spacedBy(SPACE_AROUND_BUTTONS),
    ) {
        items(items = items, key = { it.titleRes }) { item ->
            TransferTypeButton(
                modifier = Modifier.aspectRatio(0.87f),
                item = item,
                onClick = { navigateToTransfer(item) }
            )
        }
    }
}

@Composable
private fun TransferTypeButton(modifier: Modifier = Modifier, item: TransferType, onClick: () -> Unit) {
    Button(
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.fillMaxSize(),
        colors = ButtonDefaults.buttonColors(containerColor = item.background(), contentColor = item.foreground()),
        shape = ShapeDefaults.Medium,
        onClick = onClick,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxHeight(0.80f)
                    .align(Alignment.BottomCenter),
                text = stringResource(id = item.titleRes),
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyMedium,
            )

            Icon(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .align(Alignment.BottomCenter),
                imageVector = item.icon,
                contentDescription = null,
            )
        }
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
                        modifier = Modifier
                            .size(160.dp, 200.dp)
                            .padding(20.dp),
                        item = entry,
                    ) {}
                }
            }
        }
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            TransferTypeButtons(TransferType.entries) {}
        }
    }
}
