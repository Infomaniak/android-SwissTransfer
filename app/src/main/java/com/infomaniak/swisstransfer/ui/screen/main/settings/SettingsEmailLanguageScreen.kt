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
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.OptionScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.TransferAdvancedOptionsEnum
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun SettingsEmailLanguageScreen(
    emailLanguage: EmailLanguage,
    navigateBack: (() -> Unit)?,
    onEmailLanguageChange: (EmailLanguage) -> Unit,
) {
    OptionScaffold(
        topAppBarTitleRes = R.string.settingsOptionEmailLanguage,
        optionTitleRes = R.string.settingsEmailLanguageTitle,
        enumEntries = EmailLanguageOption.entries,
        selectedSettingOptionPosition = emailLanguage.ordinal,
        setSelectedSettingOptionPosition = { position -> onEmailLanguageChange(EmailLanguage.entries[position]) },
        navigateBack = navigateBack,
    )
}

enum class EmailLanguageOption(
    override val title: @Composable () -> String,
    override val imageVector: ImageVector? = null,
    override val imageVectorResId: Int? = null,
) : SettingOption, TransferAdvancedOptionsEnum {
    ENGLISH({ stringResource(R.string.settingsEmailLanguageValueEnglish) }, imageVectorResId = R.drawable.flag_gb),
    FRENCH({ stringResource(R.string.settingsEmailLanguageValueFrench) }, imageVectorResId = R.drawable.flag_fr),
    GERMAN({ stringResource(R.string.settingsEmailLanguageValueGerman) }, imageVectorResId = R.drawable.flag_ge),
    ITALIAN({ stringResource(R.string.settingsEmailLanguageValueItalian) }, imageVectorResId = R.drawable.flag_it),
    SPANISH({ stringResource(R.string.settingsEmailLanguageValueSpanish) }, imageVectorResId = R.drawable.flag_es);

    companion object {
        fun EmailLanguage.toAdvancedOption() = when (this) {
            EmailLanguage.ENGLISH -> ENGLISH
            EmailLanguage.FRENCH -> FRENCH
            EmailLanguage.GERMAN -> GERMAN
            EmailLanguage.ITALIAN -> ITALIAN
            EmailLanguage.SPANISH -> SPANISH
        }
    }
}

@PreviewAllWindows
@Composable
private fun SettingsThemeScreenPreview() {
    SwissTransferTheme {
        Surface {
            SettingsEmailLanguageScreen(
                emailLanguage = EmailLanguage.FRENCH,
                navigateBack = {},
                onEmailLanguageChange = {},
            )
        }
    }
}
