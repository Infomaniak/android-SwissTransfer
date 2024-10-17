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
import androidx.compose.ui.res.pluralStringResource
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.OptionScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun SettingsValidityPeriodScreen(
    validityPeriod: ValidityPeriod,
    navigateBack: (() -> Unit)?,
    onValidityPeriodChange: (ValidityPeriod) -> Unit,
) {
    OptionScaffold(
        topAppBarTitleRes = R.string.settingsOptionValidityPeriod,
        optionTitleRes = R.string.settingsValidityPeriodTitle,
        enumEntries = ValidityPeriodOption.entries,
        selectedSettingOptionPosition = validityPeriod.ordinal,
        setSelectedSettingOptionPosition = { position -> onValidityPeriodChange(ValidityPeriod.entries[position]) },
        navigateBack = navigateBack,
    )
}

enum class ValidityPeriodOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector? = null,
    override val imageVectorResId: Int? = null,
) : SettingOption {
    THIRTY({ pluralStringResource(R.plurals.settingsValidityPeriodValue, 30, 30) }),
    FIFTEEN({ pluralStringResource(R.plurals.settingsValidityPeriodValue, 15, 15) }),
    SEVEN({ pluralStringResource(R.plurals.settingsValidityPeriodValue, 7, 7) }),
    ONE({ pluralStringResource(R.plurals.settingsValidityPeriodValue, 1, 1) }),
}

@PreviewAllWindows
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsValidityPeriodScreen(
                validityPeriod = ValidityPeriod.THIRTY,
                navigateBack = {},
                onValidityPeriodChange = {},
            )
        }
    }
}
