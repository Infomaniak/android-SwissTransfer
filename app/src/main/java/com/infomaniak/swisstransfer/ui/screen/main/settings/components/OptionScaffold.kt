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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.infomaniak.swisstransfer.ui.components.SwissTransferTobAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavType

@Composable
fun OptionScaffold(
    @StringRes topAppBarTitleRes: Int,
    @StringRes optionTitleRes: Int,
    enumEntries: List<SettingOption>,
    selectedSettingOptionPosition: Int,
    setSelectedSettingOptionPosition: (Int) -> Unit,
    navigateBack: (() -> Unit)? = null,
) {
    Scaffold(topBar = {
        val backNavigationMenu = if (LocalNavType.current == NavigationSuiteType.NavigationBar) {
            TopAppBarButton.backButton(navigateBack ?: {})
        } else {
            null
        }
        SwissTransferTobAppBar(topAppBarTitleRes, navigationMenu = backNavigationMenu)
    }) { paddingsValue ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingsValue),
        ) {
            OptionTitle(titleRes = optionTitleRes)

            var selectedItem by rememberSaveable { mutableIntStateOf(selectedSettingOptionPosition) }
            SingleSelectOptions(enumEntries, { selectedItem }, { position ->
                selectedItem = position
                setSelectedSettingOptionPosition(position)
            })
        }
    }
}