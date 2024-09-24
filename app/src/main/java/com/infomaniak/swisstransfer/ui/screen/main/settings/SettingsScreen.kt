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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.appSettings.AppSettings
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.*
import com.infomaniak.swisstransfer.ui.screen.main.components.BrandTopAppBarScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.*
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.CHEVRON
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.OPEN_OUTSIDE
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingDivider
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow

@Composable
fun SettingsScreen(
    appSettings: AppSettings,
    onItemClick: (SettingsOptionScreens) -> Unit,
    getSelectedSetting: () -> SettingsOptionScreens?,
) {
    val selectedSetting = getSelectedSetting()

    BrandTopAppBarScaffold { paddingsValue ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .selectableGroup()
                .padding(paddingsValue),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = Margin.Medium, vertical = Margin.Large),
                text = stringResource(R.string.settingsTitle),
                style = SwissTransferTheme.typography.h1,
            )

            SettingTitle(R.string.settingsCategoryGeneral)
            SettingItem(
                titleRes = R.string.settingsOptionTheme,
                isSelected = { selectedSetting == THEME },
                icon = AppIcons.PaintbrushPalette,
                description = appSettings.theme.getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(THEME) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionNotifications,
                isSelected = { selectedSetting == NOTIFICATIONS },
                icon = AppIcons.Bell,
                description = "TODO",
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(NOTIFICATIONS) },
            )
            SettingDivider()

            SettingTitle(R.string.settingsCategoryDefaultSettings)
            SettingItem(
                titleRes = R.string.settingsOptionValidityPeriod,
                isSelected = { selectedSetting == VALIDITY_PERIOD },
                icon = AppIcons.ArrowDownFile,
                description = appSettings.validityPeriod.getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(VALIDITY_PERIOD) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionDownloadLimit,
                isSelected = { selectedSetting == DOWNLOAD_LIMIT },
                icon = AppIcons.Clock,
                description = appSettings.downloadLimit.getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(DOWNLOAD_LIMIT) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionEmailLanguage,
                isSelected = { selectedSetting == EMAIL_LANGUAGE },
                icon = AppIcons.SpeechBubble,
                description = appSettings.emailLanguage.getString(),
                endIcon = CHEVRON,
                onClick = { onItemClick(EMAIL_LANGUAGE) },
            )
            SettingDivider()

            SettingTitle(R.string.settingsCategoryAbout)
            SettingItem(
                titleRes = R.string.settingsOptionDiscoverInfomaniak,
                isSelected = { selectedSetting == DISCOVER_INFOMANIAK },
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(DISCOVER_INFOMANIAK) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionShareIdeas,
                isSelected = { selectedSetting == SHARE_IDEAS },
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(SHARE_IDEAS) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionGiveFeedback,
                isSelected = { selectedSetting == GIVE_FEEDBACK },
                endIcon = OPEN_OUTSIDE,
                onClick = { onItemClick(GIVE_FEEDBACK) },
            )
            SettingItem(
                titleRes = R.string.version,
                isSelected = { false },
                description = BuildConfig.VERSION_NAME,
                onClick = null,
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
private fun DownloadLimit?.getString() = this?.value ?: ""

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
    THEME, NOTIFICATIONS,
    VALIDITY_PERIOD, DOWNLOAD_LIMIT, EMAIL_LANGUAGE,
    DISCOVER_INFOMANIAK, SHARE_IDEAS, GIVE_FEEDBACK,
}

private class DummyAppSettings(
    override var theme: Theme = Theme.SYSTEM,
    override var downloadLimit: DownloadLimit = DownloadLimit.TWOHUNDREDFIFTY,
    override var emailLanguage: EmailLanguage = EmailLanguage.FRENCH,
    override var validityPeriod: ValidityPeriod = ValidityPeriod.THIRTY,
) : AppSettings

@PreviewSmallWindow
@Composable
private fun SettingsScreenPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreen(appSettings = DummyAppSettings(), onItemClick = {}, getSelectedSetting = { null })
        }
    }
}
