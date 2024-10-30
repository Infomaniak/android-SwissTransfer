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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferCard
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownFile
import com.infomaniak.swisstransfer.ui.images.icons.Clock
import com.infomaniak.swisstransfer.ui.images.icons.LockedTextField
import com.infomaniak.swisstransfer.ui.images.icons.SpeechBubble
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferAdvancedSettings(
    modifier: Modifier = Modifier,
    states: () -> List<SettingOption?>,
    onClick: (TransferAdvancedSettingType) -> Unit,
) {
    SwissTransferCard(modifier = modifier) {
        TransferAdvancedSettingType.entries.forEach { settingType ->
            val title by remember { derivedStateOf { states()[settingType.ordinal] } }
            TransferAdvancedSetting(settingType, { title }, { onClick(settingType) })
        }
    }
}

enum class TransferAdvancedSettingType(val buttonIcon: ImageVector, @StringRes val buttonText: Int) {
    VALIDITY_DURATION(buttonIcon = AppIcons.Clock, buttonText = R.string.settingsOptionValidityPeriod),
    DOWNLOAD_NUMBER_LIMIT(buttonIcon = AppIcons.ArrowDownFile, buttonText = R.string.settingsOptionDownloadLimit),
    PASSWORD(buttonIcon = AppIcons.LockedTextField, buttonText = R.string.settingsOptionPassword),
    LANGUAGE(buttonIcon = AppIcons.SpeechBubble, buttonText = R.string.settingsOptionEmailLanguage),
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TransferTypeButtonsPreview() {
    SwissTransferTheme {
        Surface {
            val selectedOptionValues = listOf(
                ValidityPeriodOption.THIRTY,
                DownloadLimitOption.TWO_HUNDRED_FIFTY,
                PasswordTransferOption.NONE,
                EmailLanguageOption.FRENCH,
            )

            TransferAdvancedSettings(modifier = Modifier.padding(Margin.Medium), states = { selectedOptionValues }, onClick = {})
        }
    }
}
