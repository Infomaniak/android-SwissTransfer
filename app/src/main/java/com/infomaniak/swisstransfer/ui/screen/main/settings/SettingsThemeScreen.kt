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

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.CircleBlack
import com.infomaniak.swisstransfer.ui.images.icons.CircleBlackAndWhite
import com.infomaniak.swisstransfer.ui.images.icons.CircleWhite
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.OptionScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile

@Composable
fun SettingsThemeScreen(
    theme: Theme,
    navigateBack: (() -> Unit)?,
    onThemeUpdate: (Theme) -> Unit,
) {
    OptionScaffold(
        topAppBarTitleRes = R.string.settingsOptionTheme,
        optionTitleRes = R.string.settingsThemeTitle,
        enumEntries = ThemeOption.entries,
        selectedSettingOptionPosition = theme.ordinal,
        setSelectedSettingOptionPosition = { position -> onThemeUpdate(Theme.entries[position]) },
        navigateBack = navigateBack,
    )
}

enum class ThemeOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector,
    override val imageVectorResId: Int? = null,
) : SettingOption {
    SYSTEM({ stringResource(R.string.settingsOptionThemeSystem) }, AppIcons.CircleBlackAndWhite),
    LIGHT({ stringResource(R.string.settingsOptionThemeLight) }, AppIcons.CircleWhite),
    DARK({ stringResource(R.string.settingsOptionThemeDark) }, AppIcons.CircleBlack),
}

@PreviewMobile
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsThemeScreen(theme = Theme.SYSTEM, navigateBack = {}, onThemeUpdate = {})
        }
    }
}
