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
package com.infomaniak.swisstransfer.ui.screen.main.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.core.R as RCore

@Composable
fun SettingsDataManagementMatomoScreen(navigateBack: (() -> Unit)?) {

    var isAuthorized by rememberSaveable { mutableStateOf(!MatomoSwissTransfer.tracker.isOptOut) }

    SettingsDataManagementMatomoScreen(
        navigateBack = navigateBack,
        isMatomoAuthorized = { isAuthorized },
        setMatomoAuthorization = {
            isAuthorized = it
            MatomoSwissTransfer.tracker.isOptOut = !it
        }
    )
}

@Composable
fun SettingsDataManagementMatomoScreen(
    navigateBack: (() -> Unit)?,
    isMatomoAuthorized: () -> Boolean,
    setMatomoAuthorization: (Boolean) -> Unit,
) {
    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = RCore.string.trackingMatomoTitle,
                navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack ?: {}) },
            )
        },
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = AppIllus.Matomo.image(),
                contentDescription = null,
                modifier = Modifier.padding(Margin.Medium),
            )
            Text(
                text = stringResource(RCore.string.trackingMatomoDescription),
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = Modifier.padding(Margin.Medium),
            )
            Row(
                modifier = Modifier
                    .widthIn(max = Dimens.MaxSinglePaneScreenWidth)
                    .fillMaxWidth()
                    .padding(Margin.Medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(RCore.string.trackingAuthorizeTracking),
                    style = SwissTransferTheme.typography.bodyRegular,
                    color = SwissTransferTheme.colors.primaryTextColor,
                )
                Spacer(Modifier.weight(1.0f))
                Switch(
                    checked = isMatomoAuthorized(),
                    onCheckedChange = { setMatomoAuthorization(it) },
                )
            }
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            var isMatomoAuthorized by remember { mutableStateOf(true) }

            SettingsDataManagementMatomoScreen(
                navigateBack = {},
                isMatomoAuthorized = { isMatomoAuthorized },
                setMatomoAuthorization = { isMatomoAuthorized = it }
            )
        }
    }
}
