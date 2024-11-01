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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import androidx.annotation.StringRes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SingleSelectOptions
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
private fun TransferOptionBottomSheetScaffold(
    isBottomSheetVisible: () -> Boolean,
    onOptionClicked: (SettingOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialPosition: Int,
    optionEntries: List<SettingOption>,
    @StringRes titleRes: Int,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(initialPosition) }

    if (isBottomSheetVisible()) {
        SwissTransferBottomSheet(
            onDismissRequest = closeBottomSheet,
            titleRes = titleRes,
            content = {
                SingleSelectOptions(
                    items = optionEntries,
                    selectedItem = { selectedItem },
                    setSelectedItem = { position ->
                        selectedItem = position
                        onOptionClicked(optionEntries[position])
                        closeBottomSheet()
                    },
                )
            },
        )
    }
}

@Composable
fun ValidityPeriodBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onOptionClicked: (ValidityPeriodOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialPosition: Int,
) {
    TransferOptionBottomSheetScaffold(
        isBottomSheetVisible = isBottomSheetVisible,
        closeBottomSheet = closeBottomSheet,
        initialPosition = initialPosition,
        titleRes = R.string.settingsOptionValidityPeriod,
        optionEntries = ValidityPeriodOption.entries,
        onOptionClicked = { onOptionClicked(it as ValidityPeriodOption) },
    )
}

@Composable
fun DownloadLimitBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onOptionClicked: (DownloadLimitOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialPosition: Int,
) {
    TransferOptionBottomSheetScaffold(
        isBottomSheetVisible = isBottomSheetVisible,
        closeBottomSheet = closeBottomSheet,
        initialPosition = initialPosition,
        titleRes = R.string.settingsOptionDownloadLimit,
        optionEntries = DownloadLimitOption.entries,
        onOptionClicked = { onOptionClicked(it as DownloadLimitOption) },
    )
}

@Composable
fun PasswordOptionBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onOptionClicked: (PasswordTransferOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialPosition: Int,
) {
    TransferOptionBottomSheetScaffold(
        isBottomSheetVisible = isBottomSheetVisible,
        closeBottomSheet = closeBottomSheet,
        initialPosition = initialPosition,
        titleRes = R.string.settingsOptionPassword,
        optionEntries = PasswordTransferOption.entries,
        onOptionClicked = { onOptionClicked(it as PasswordTransferOption) },
    )
}

@Composable
fun EmailLanguageBottomSheet(
    isBottomSheetVisible: () -> Boolean,
    onOptionClicked: (EmailLanguageOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialPosition: Int,
) {
    TransferOptionBottomSheetScaffold(
        isBottomSheetVisible = isBottomSheetVisible,
        closeBottomSheet = closeBottomSheet,
        initialPosition = initialPosition,
        titleRes = R.string.settingsOptionEmailLanguage,
        optionEntries = EmailLanguageOption.entries,
        onOptionClicked = { onOptionClicked(it as EmailLanguageOption) },
    )
}

@PreviewAllWindows
@Composable
private fun ValidityPeriodOptionBottomSheetPreview() {
    SwissTransferTheme {
        Surface {
            ValidityPeriodBottomSheet(
                isBottomSheetVisible = { true },
                onOptionClicked = {},
                closeBottomSheet = {},
                initialPosition = ValidityPeriodOption.SEVEN.ordinal,
            )
        }
    }
}
