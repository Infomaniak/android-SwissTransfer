/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.main.received

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ActionsOnlyTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotDead.MascotDead
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun TransferExpiredDownloadCreditScreen(onCloseClicked: (() -> Unit)? = null) {
    SwissTransferScaffold(
        topBar = {
            onCloseClicked?.let {
                ActionsOnlyTopAppBar(actions = { TopAppBarButtons.Close(onClick = it) })
            } ?: EmptyTopAppBar()
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            val paddedModifier = Modifier.padding(horizontal = Margin.Medium)

            Image(
                modifier = paddedModifier,
                imageVector = AppIllus.MascotDead.image(),
                contentDescription = null
            )

            Spacer(Modifier.height(Margin.Large))

            Text(
                text = stringResource(R.string.transferExpiredTitle),
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyMedium,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = paddedModifier,
            )

            Spacer(Modifier.height(Margin.Large))

            Text(
                text = stringResource(R.string.transferExpiredDescription),
                textAlign = TextAlign.Center,
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                modifier = paddedModifier,
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            TransferExpiredDownloadCreditScreen(onCloseClicked = {})
        }
    }
}
