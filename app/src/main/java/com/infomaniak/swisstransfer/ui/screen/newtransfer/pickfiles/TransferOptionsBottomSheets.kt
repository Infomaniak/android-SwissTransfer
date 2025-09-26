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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SingleSelectOptions
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransferOptionBottomSheetScaffold(
    onOptionClicked: (SettingOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
    optionEntries: List<SettingOption>,
    title: String,
) {

    val selectedPosition = when (initialValue) {
        is ValidityPeriodOption -> initialValue.ordinal
        is DownloadLimitOption -> initialValue.ordinal
        is PasswordTransferOption -> initialValue.ordinal
        is EmailLanguageOption -> initialValue.ordinal
        else -> 0
    }

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        title = title,
        content = {
            SingleSelectOptions(
                items = optionEntries,
                selectedItem = { selectedPosition },
                setSelectedItem = { position ->
                    val selectedValue = optionEntries[position]
                    onOptionClicked(selectedValue)
                    closeBottomSheet()
                },
            )
        },
    )
}

@Composable
fun ValidityPeriodBottomSheet(
    onOptionClicked: (ValidityPeriodOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
) {
    TransferOptionBottomSheetScaffold(
        closeBottomSheet = closeBottomSheet,
        initialValue = initialValue,
        title = stringResource(R.string.settingsOptionValidityPeriod),
        optionEntries = ValidityPeriodOption.entries,
        onOptionClicked = {
            val option = it as ValidityPeriodOption
            MatomoSwissTransfer.trackSettingsLocalValidityPeriodEvent(option.matomoName)
            onOptionClicked(option)
        },
    )
}

@Composable
fun DownloadLimitBottomSheet(
    onOptionClicked: (DownloadLimitOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
) {
    TransferOptionBottomSheetScaffold(
        closeBottomSheet = closeBottomSheet,
        initialValue = initialValue,
        title = stringResource(R.string.settingsOptionDownloadLimit),
        optionEntries = DownloadLimitOption.entries,
        onOptionClicked = {
            val option = it as DownloadLimitOption
            MatomoSwissTransfer.trackSettingsLocalDownloadLimitEvent(option.matomoName)
            onOptionClicked(option)
        },
    )
}

@Composable
fun EmailLanguageBottomSheet(
    onOptionClicked: (EmailLanguageOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
) {
    TransferOptionBottomSheetScaffold(
        closeBottomSheet = closeBottomSheet,
        initialValue = initialValue,
        title = stringResource(R.string.settingsOptionEmailLanguage),
        optionEntries = EmailLanguageOption.entries,
        onOptionClicked = {
            val option = it as EmailLanguageOption
            MatomoSwissTransfer.trackSettingsLocalEmailLanguageEvent(option.apiValue.value)
            onOptionClicked(option)
        },
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ValidityPeriodBottomSheet(
                onOptionClicked = {},
                closeBottomSheet = {},
                initialValue = ValidityPeriodOption.SEVEN,
            )
        }
    }
}
