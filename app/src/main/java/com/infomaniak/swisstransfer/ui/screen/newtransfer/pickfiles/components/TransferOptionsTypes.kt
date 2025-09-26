/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewLightAndDark
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
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PasswordTransferOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.TransferOptionState
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun TransferOptionsTypes(
    modifier: Modifier = Modifier,
    transferOptionsStates: () -> List<TransferOptionState>,
    onClick: (TransferOptionType) -> Unit,
) {
    // TODO: Animate the addition or removal when the list of transferOptionsStates changes
    SwissTransferCard(modifier = modifier) {
        transferOptionsStates().forEach {
            val title by remember { derivedStateOf { it.settingState } }
            TransferOptionType(it.transferOptionType, title, onClick = { onClick(it.transferOptionType) })
        }
    }
}

enum class TransferOptionType(val buttonIcon: ImageVector, @StringRes val buttonText: Int) {
    VALIDITY_DURATION(buttonIcon = AppIcons.Clock, buttonText = R.string.settingsOptionValidityPeriod),
    DOWNLOAD_NUMBER_LIMIT(buttonIcon = AppIcons.ArrowDownFile, buttonText = R.string.settingsOptionDownloadLimit),
    PASSWORD(buttonIcon = AppIcons.LockedTextField, buttonText = R.string.settingsOptionPassword),
    LANGUAGE(buttonIcon = AppIcons.SpeechBubble, buttonText = R.string.settingsOptionEmailLanguage),
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            val selectedOptionValues = listOf(
                TransferOptionState(
                    transferOptionType = TransferOptionType.VALIDITY_DURATION,
                    settingState = { ValidityPeriodOption.THIRTY },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.DOWNLOAD_NUMBER_LIMIT,
                    settingState = { DownloadLimitOption.TWO_HUNDRED_FIFTY },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.PASSWORD,
                    settingState = { PasswordTransferOption.NONE },
                ),
                TransferOptionState(
                    transferOptionType = TransferOptionType.LANGUAGE,
                    settingState = { EmailLanguageOption.FRENCH },
                ),
            )

            TransferOptionsTypes(
                modifier = Modifier.padding(Margin.Medium),
                transferOptionsStates = { selectedOptionValues },
                onClick = {},
            )
        }
    }
}
