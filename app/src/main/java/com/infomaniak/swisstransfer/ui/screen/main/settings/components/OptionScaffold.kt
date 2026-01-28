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
package com.infomaniak.swisstransfer.ui.screen.main.settings.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.ThemeOption
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall

@Composable
fun OptionScaffold(
    @StringRes topAppBarTitleRes: Int,
    @StringRes optionTitleRes: Int,
    enumEntries: List<SettingOption>,
    selectedSettingOptionPosition: Int,
    matomoValue: MatomoScreen,
    setSelectedSettingOptionPosition: (Int) -> Unit,
    navigateBack: (() -> Unit),
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(matomoValue) }

    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = topAppBarTitleRes,
                navigationIcon = { if (windowAdaptiveInfo.isWindowSmall()) TopAppBarButtons.Back(onClick = navigateBack) },
            )
        },
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            OptionTitle(titleRes = optionTitleRes)

            var selectedItem by rememberSaveable { mutableIntStateOf(selectedSettingOptionPosition) }
            SingleSelectOptions(
                items = enumEntries,
                selectedItem = { selectedItem },
                setSelectedItem = { position ->
                    selectedItem = position
                    setSelectedSettingOptionPosition(position)
                },
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        OptionScaffold(
            topAppBarTitleRes = R.string.settingsOptionTheme,
            optionTitleRes = R.string.settingsThemeTitle,
            enumEntries = ThemeOption.entries,
            selectedSettingOptionPosition = 0,
            matomoValue = MatomoScreen.Sent,
            setSelectedSettingOptionPosition = {},
            navigateBack = {},
        )
    }
}
