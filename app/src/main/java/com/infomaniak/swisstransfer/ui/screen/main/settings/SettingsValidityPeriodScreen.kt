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
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
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
        matomoValue = MatomoScreen.ValidityPeriodSetting,
        setSelectedSettingOptionPosition = { position ->
            val option = ValidityPeriodOption.entries[position]
            MatomoSwissTransfer.trackSettingsGlobalValidityPeriodEvent(option.matomoName)
            onValidityPeriodChange(option.apiValue)
        },
        navigateBack = navigateBack,
    )
}

enum class ValidityPeriodOption(
    override val imageVector: ImageVector? = null,
    override val imageVectorResId: Int? = null,
    val apiValue: ValidityPeriod,
    val matomoName: MatomoName,
) : SettingOption {
    THIRTY(apiValue = ValidityPeriod.THIRTY, matomoName = MatomoName.ThirtyDays),
    FIFTEEN(apiValue = ValidityPeriod.FIFTEEN, matomoName = MatomoName.FifteenDays),
    SEVEN(apiValue = ValidityPeriod.SEVEN, matomoName = MatomoName.SevenDays),
    ONE(apiValue = ValidityPeriod.ONE, matomoName = MatomoName.OneDay);

    override val title: @Composable () -> String = { getValidityPeriodTitle(apiValue) }

    companion object {
        fun ValidityPeriod.toTransferOption() = when (this) {
            ValidityPeriod.THIRTY -> THIRTY
            ValidityPeriod.FIFTEEN -> FIFTEEN
            ValidityPeriod.SEVEN -> SEVEN
            ValidityPeriod.ONE -> ONE
        }
    }
}

@Composable
private fun getValidityPeriodTitle(validityPeriod: ValidityPeriod): String {
    val count = validityPeriod.value.toInt()
    return pluralStringResource(R.plurals.settingsValidityPeriodValue, count, count)
}

@PreviewAllWindows
@Composable
private fun Preview() {
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
