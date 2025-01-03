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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.screen.main.components.SmallWindowTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.*
import kotlinx.coroutines.launch

@Composable
fun SettingsDataManagementMatomoScreen(navigateBack: (() -> Unit)?) {
    val scope = rememberCoroutineScope()
    val dataStore = LocalContext.current.dataManagementDataStore

    val isMatomoAuthorized by dataStore.collectAsStateWithLifecycle(DataManagementPreferences.IsMatomoAuthorized, false)

    SmallWindowTopAppBarScaffold(
        smallWindowTopAppBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.matomo,
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
                text = stringResource(R.string.settingsMatomoDescription),
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = Modifier.padding(Margin.Medium),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Margin.Medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.settingsAuthorizeTracking),
                    style = SwissTransferTheme.typography.bodyRegular,
                    color = SwissTransferTheme.colors.primaryTextColor,
                )
                Spacer(Modifier.weight(1.0f))
                Switch(
                    checked = isMatomoAuthorized,
                    onCheckedChange = {
                        scope.launch { dataStore.setValue(DataManagementPreferences.IsMatomoAuthorized, it) }
                    },
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
            SettingsDataManagementMatomoScreen {}
        }
    }
}
