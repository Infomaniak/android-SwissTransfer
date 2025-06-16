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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.sentry.Sentry
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.core.R as RCore

@Composable
fun SettingsDataManagementSentryScreen(
    navigateBack: (() -> Unit)?,
    settingsSentryViewModel: SettingsSentryViewModel = hiltViewModel<SettingsSentryViewModel>()
) {
    val isSentryAuthorized by settingsSentryViewModel.isSentryAuthorized.collectAsStateWithLifecycle()

    SettingsDataManagementSentryScreen(
        navigateBack = navigateBack,
        isSentryAuthorized = { isSentryAuthorized },
        setSentryAuthorization = { settingsSentryViewModel.setSentryAuthorization(it) }
    )
}

@Composable
fun SettingsDataManagementSentryScreen(
    navigateBack: (() -> Unit)?,
    isSentryAuthorized: () -> Boolean,
    setSentryAuthorization: (Boolean) -> Unit,
) {
    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = RCore.string.trackingSentryTitle,
                navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack ?: {}) },
            )
        },
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = AppIllus.Sentry.image(),
                contentDescription = null,
                modifier = Modifier.padding(Margin.Medium),
            )
            Text(
                text = stringResource(RCore.string.trackingSentryDescription),
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
                    checked = isSentryAuthorized(),
                    onCheckedChange = { setSentryAuthorization(it) },
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
            var isSentryAuthorized by remember { mutableStateOf(true) }

            SettingsDataManagementSentryScreen(
                navigateBack = {},
                isSentryAuthorized = { isSentryAuthorized },
                setSentryAuthorization = { isSentryAuthorized = it }
            )
        }
    }
}
