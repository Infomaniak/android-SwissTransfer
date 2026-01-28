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

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.ArrowDownFile
import com.infomaniak.swisstransfer.ui.images.icons.Bell
import com.infomaniak.swisstransfer.ui.images.icons.CircleCross
import com.infomaniak.swisstransfer.ui.images.icons.Clock
import com.infomaniak.swisstransfer.ui.images.icons.PaintbrushPalette
import com.infomaniak.swisstransfer.ui.images.icons.Shield
import com.infomaniak.swisstransfer.ui.images.icons.SpeechBubble
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DELETE_MY_ACCOUNT
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DOWNLOAD_LIMIT
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.EMAIL_LANGUAGE
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.NOTIFICATIONS
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.THEME
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.VALIDITY_PERIOD
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.CHEVRON
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.OPEN_OUTSIDE
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingDivider
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import com.infomaniak.core.common.R as RCore

@Composable
fun SettingsScreen(
    theme: GetSetCallbacks<Theme>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
    onItemClick: (SettingsOptionScreens) -> Unit,
    navigateBack: (() -> Unit),
    getSelectedSetting: () -> SettingsOptionScreens?,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current
    val selectedSetting = getSelectedSetting()

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.Settings) }

    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                stringResource(R.string.settingsTitle),
                navigationIcon = { if (windowAdaptiveInfo.isWindowSmall()) TopAppBarButtons.Back(onClick = navigateBack) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .selectableGroup(),
        ) {
            SettingTitle(R.string.settingsCategoryGeneral)

            if (SDK_INT >= 29) {
                SettingItem(
                    titleRes = R.string.settingsOptionTheme,
                    isSelected = { selectedSetting == THEME },
                    icon = AppIcons.PaintbrushPalette,
                    description = theme.get().getString(),
                    endIcon = CHEVRON,
                    onClick = { onItemClick(THEME) },
                )
            }

            SettingItem(
                titleRes = R.string.settingsOptionNotifications,
                isSelected = { false },
                icon = AppIcons.Bell,
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(NOTIFICATIONS) },
            )
            SettingDivider()

            SettingTitle(R.string.settingsCategoryDefaultSettings)
            SettingItem(
                titleRes = R.string.settingsOptionValidityPeriod,
                isSelected = { selectedSetting == VALIDITY_PERIOD },
                icon = AppIcons.Clock,
                description = validityPeriod.get().getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(VALIDITY_PERIOD) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionDownloadLimit,
                isSelected = { selectedSetting == DOWNLOAD_LIMIT },
                icon = AppIcons.ArrowDownFile,
                description = downloadLimit.get().getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(DOWNLOAD_LIMIT) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionEmailLanguage,
                isSelected = { selectedSetting == EMAIL_LANGUAGE },
                icon = AppIcons.SpeechBubble,
                description = emailLanguage.get().getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(EMAIL_LANGUAGE) },
            )
            SettingDivider()

            SettingTitle(R.string.settingsCategoryDataManagement)
            SettingItem(
                titleRes = RCore.string.trackingManagementTitle,
                isSelected = { selectedSetting?.isDataManagementSetting() == true },
                icon = AppIcons.Shield,
                endIcon = CHEVRON,
                onClick = { onItemClick(DATA_MANAGEMENT) },
            )
            SettingItem(
                titleRes = R.string.settingsDeleteMyAccount,
                isSelected = { false },
                icon = AppIcons.CircleCross,
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(DELETE_MY_ACCOUNT) },
            )
        }
    }
}

@Composable
private fun Theme?.getString(): String {
    return when (this) {
        Theme.SYSTEM -> stringResource(R.string.settingsOptionThemeSystem)
        Theme.DARK -> stringResource(R.string.settingsOptionThemeDark)
        Theme.LIGHT -> stringResource(R.string.settingsOptionThemeLight)
        else -> ""
    }
}

@Composable
private fun ValidityPeriod?.getString(): String {
    return this?.value?.toInt()?.let {
        pluralStringResource(R.plurals.settingsValidityPeriodValue, it, it)
    } ?: ""
}

@Composable
private fun DownloadLimit?.getString() = this?.value?.toString() ?: ""

@Composable
private fun EmailLanguage?.getString(): String {
    return when (this) {
        EmailLanguage.ENGLISH -> stringResource(R.string.settingsEmailLanguageValueEnglish)
        EmailLanguage.FRENCH -> stringResource(R.string.settingsEmailLanguageValueFrench)
        EmailLanguage.GERMAN -> stringResource(R.string.settingsEmailLanguageValueGerman)
        EmailLanguage.ITALIAN -> stringResource(R.string.settingsEmailLanguageValueItalian)
        EmailLanguage.SPANISH -> stringResource(R.string.settingsEmailLanguageValueSpanish)
        else -> ""
    }
}

enum class SettingsOptionScreens {
    SETTINGS,
    THEME, NOTIFICATIONS,
    VALIDITY_PERIOD, DOWNLOAD_LIMIT, EMAIL_LANGUAGE,
    DATA_MANAGEMENT, DATA_MANAGEMENT_MATOMO, DATA_MANAGEMENT_SENTRY,
    DELETE_MY_ACCOUNT;

    fun isDataManagementSetting() = this == DATA_MANAGEMENT || this == DATA_MANAGEMENT_MATOMO || this == DATA_MANAGEMENT_SENTRY
}

@PreviewAllWindows
@Composable
private fun SettingsScreenPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreen(
                theme = GetSetCallbacks(get = { Theme.SYSTEM }, set = {}),
                validityPeriod = GetSetCallbacks(get = { ValidityPeriod.THIRTY }, set = {}),
                downloadLimit = GetSetCallbacks(get = { DownloadLimit.TWO_HUNDRED_FIFTY }, set = {}),
                emailLanguage = GetSetCallbacks(get = { EmailLanguage.ENGLISH }, set = {}),
                onItemClick = {},
                navigateBack = {},
            ) { null }
        }
    }
}
