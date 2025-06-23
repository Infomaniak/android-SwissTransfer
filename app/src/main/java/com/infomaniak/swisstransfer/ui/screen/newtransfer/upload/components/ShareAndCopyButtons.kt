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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.DocumentOnDocument
import com.infomaniak.swisstransfer.ui.images.icons.PersonBadgeShare
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.swisstransfer.ui.utils.copyText
import com.infomaniak.swisstransfer.ui.utils.shareText
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.ShareAndCopyButtons(modifier: Modifier = Modifier, transferLink: String, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Row(
        modifier
            .widthIn(max = Dimens.SingleButtonMaxWidth)
            .fillMaxWidth()
            .padding(horizontal = Margin.Medium)
            .align(Alignment.CenterHorizontally),
    ) {
        ShareCopyButton(
            textRes = R.string.buttonShare,
            icon = AppImages.AppIcons.PersonBadgeShare,
            onClick = { context.shareText(transferLink) },
        )

        Spacer(Modifier.width(Margin.Medium))

        ShareCopyButton(
            textRes = R.string.buttonCopyLink,
            icon = AppImages.AppIcons.DocumentOnDocument,
            onClick = {
                context.copyText(
                    text = transferLink,
                    showSnackbar = { scope.launch { snackbarHostState.showSnackbar(it) } },
                )
            },
        )
    }
}

@Composable
private fun RowScope.ShareCopyButton(icon: ImageVector, @StringRes textRes: Int, onClick: () -> Unit) {
    Button(
        modifier = Modifier.weight(1.0f),
        shape = CustomShapes.MEDIUM,
        colors = ButtonType.Secondary.buttonColors(),
        contentPadding = PaddingValues(vertical = Margin.Medium),
        onClick = onClick,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(Dimens.SmallIconSize))
            Spacer(Modifier.height(Margin.Micro))
            Text(text = stringResource(textRes))
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column {
                val snackbarHostState = remember { SnackbarHostState() }
                ShareAndCopyButtons(
                    transferLink = "https://example.com",
                    snackbarHostState = snackbarHostState,
                )
            }
        }
    }
}
