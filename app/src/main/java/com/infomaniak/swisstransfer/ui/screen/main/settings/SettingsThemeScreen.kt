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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.MutuallyExclusiveOptions
import com.infomaniak.swisstransfer.ui.components.SettingOption
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun SettingsThemeScreen() {
    Column {
        SettingTitle(titleRes = R.string.appName)
        MutuallyExclusiveOptions(ThemeOption.entries)
    }
}

enum class ThemeOption(override val title: Int, override val icon: ImageVector) : SettingOption {
    SYSTEM(R.string.appName, AppIcons.Add),
    LIGHT(R.string.appName, AppIcons.Add),
    DARK(R.string.appName, AppIcons.Add),
}

@PreviewMobile
@PreviewTablet
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsThemeScreen()
        }
    }
}
