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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.basics.bottomsheet.dismissGracefully
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.SmallButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.components.dialog.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.components.dialog.SwissTransferAlertDialogDefaults
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Leaf
import com.infomaniak.swisstransfer.ui.screen.main.settings.DownloadLimitOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.EmailLanguageOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingOption
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SingleSelectOptions
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isGuest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransferOptionBottomSheetScaffold(
    onOptionClicked: (SettingOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
    optionEntries: List<SettingOption>,
    title: String,
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

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
        sheetState = state,
        content = {
            SingleSelectOptions(
                items = optionEntries,
                selectedItem = { selectedPosition },
                setSelectedItem = { position ->
                    val selectedValue = optionEntries[position]
                    onOptionClicked(selectedValue)
                    state.dismissGracefully(scope, onDismissRequest = { closeBottomSheet() })
                },
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidityPeriodBottomSheet(
    onOptionClicked: (ValidityPeriodOption) -> Unit,
    closeBottomSheet: () -> Unit,
    initialValue: SettingOption?,
    confirmationButtonTitle: String = stringResource(R.string.buttonConfirm),
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val options = ValidityPeriodOption.entries.sortedBy { it.apiValue.days }
    val isGuest = LocalUser.current.isGuest()
    var isShowingAvailabilityAlert by rememberSaveable { mutableStateOf(false) }

    fun select(option: ValidityPeriodOption) {
        state.dismissGracefully(scope, onDismissRequest = {
            closeBottomSheet()
            MatomoSwissTransfer.trackSettingsLocalValidityPeriodEvent(option.matomoName)
            onOptionClicked(option)
        })
    }

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        sheetState = state,
        title = stringResource(R.string.newTransferValidityPeriodTitle),
        content = {
            RecommendedDurations()
            SingleSelectOptions(
                items = options,
                selectedItem = { options.indexOf(initialValue).takeIf { it >= 0 } },
                setSelectedItem = { position ->
                    val option = options[position]
                    if (option == ValidityPeriodOption.THIRTY && isGuest) {
                        isShowingAvailabilityAlert = true
                    } else {
                        select(option)
                    }
                },
            )
        },
    )

    if (isShowingAvailabilityAlert) {
        SwissTransferAlertDialog(
            title = stringResource(R.string.transferAvailabilityAlertTitle),
            description = stringResource(R.string.transferAvailabilityAlertDescription),
            onDismiss = { isShowingAvailabilityAlert = false },
            negativeButton = {
                SwissTransferAlertDialogDefaults.CancelButton { isShowingAvailabilityAlert = false }
            },
            positiveButton = {
                SmallButton(
                    title = confirmationButtonTitle,
                    style = ButtonType.Tertiary,
                    onClick = {
                        isShowingAvailabilityAlert = false
                        select(ValidityPeriodOption.THIRTY)
                    },
                )
            },
        )
    }
}

@Composable
private fun RecommendedDurations() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(Margin.Medium),
        verticalArrangement = Arrangement.spacedBy(Margin.Small),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Margin.Small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = AppIcons.Leaf,
                contentDescription = null,
                tint = SwissTransferTheme.materialColors.primary,
            )
            Text(
                text = stringResource(R.string.recommendedDurationsTitle),
                style = SwissTransferTheme.typography.bodySmallMedium,
                color = SwissTransferTheme.materialColors.primary,
            )
        }
        Text(
            text = stringResource(R.string.recommendedDurationsDescription).format(),
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.secondaryTextColor,
        )
    }
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
