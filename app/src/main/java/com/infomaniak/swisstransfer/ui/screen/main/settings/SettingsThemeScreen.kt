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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferTobAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.BlackAndWhiteCircle
import com.infomaniak.swisstransfer.ui.images.icons.BlackCircle
import com.infomaniak.swisstransfer.ui.images.icons.WhiteCircle
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SingleSelectOptions
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile

@Composable
fun SettingsThemeScreen(navigateBack: (() -> Unit)?) {
    Scaffold(topBar = {
        val canDisplayBackButton = navigateBack?.let { TopAppBarButton.backButton(navigateBack) }
        SwissTransferTobAppBar(R.string.settingsOptionTheme, navigationMenu = canDisplayBackButton)
    }) { paddingsValue ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingsValue),
        ) {
            SettingTitle(titleRes = R.string.settingsThemeTitle)

            var selectedItem by rememberSaveable { mutableIntStateOf(0) } // TODO: Use DataStore or Realm
            SingleSelectOptions(ThemeOption.entries, { selectedItem }, { selectedItem = it })
        }
    }
}

enum class ThemeOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector,
    override val imageVectorResId: Int? = null,
) : SettingOption {
    SYSTEM({ stringResource(R.string.settingsOptionThemeSystem) }, AppIcons.BlackAndWhiteCircle),
    LIGHT({ stringResource(R.string.settingsOptionThemeLight) }, AppIcons.WhiteCircle),
    DARK({ stringResource(R.string.settingsOptionThemeDark) }, AppIcons.BlackCircle),
}

@PreviewMobile
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsThemeScreen {}
        }
    }
}
