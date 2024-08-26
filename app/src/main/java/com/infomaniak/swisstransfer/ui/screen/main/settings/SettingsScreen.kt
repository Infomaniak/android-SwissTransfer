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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BrandTobAppBar
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.*
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.*
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.CHEVRON
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.OPEN_OUTSIDE
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingDivider
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile

private fun openUrl(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

private fun goToPlayStore(context: Context) {
    try {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context.packageName}"))
        )
    } catch (_: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}")
            )
        )
    }
}

fun openAppNotificationSettings(context: Context) {
    val packageName = context.packageName
    val appUid = context.applicationInfo.uid
    Intent().apply {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
            else -> {
                action = "Settings.ACTION_APP_NOTIFICATION_SETTINGS"
                putExtra("app_package", packageName)
                putExtra("app_uid", appUid)
            }
        }
    }.also { context.startActivity(it) }
}

@Composable
fun SettingsScreen(onItemClick: (SettingsOptionScreens) -> Unit, getSelectedSetting: () -> SettingsOptionScreens?) {

    val selectedSetting = getSelectedSetting()

    Scaffold(topBar = { BrandTobAppBar() }) { paddingsValue ->
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
                description = "TODO",
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
                description = "TODO",
                endIcon = CHEVRON,
                onClick = { onItemClick(VALIDITY_PERIOD) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionDownloadLimit,
                isSelected = { selectedSetting == DOWNLOAD_LIMIT },
                icon = AppIcons.Clock,
                description = "TODO",
                endIcon = CHEVRON,
                onClick = { onItemClick(DOWNLOAD_LIMIT) },
            )
            SettingItem(
                titleRes = R.string.settingsOptionEmailLanguage,
                isSelected = { selectedSetting == EMAIL_LANGUAGE },
                icon = AppIcons.SpeechBubble,
                description = "TODO",
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

enum class SettingsOptionScreens {
    THEME, NOTIFICATIONS,
    VALIDITY_PERIOD, DOWNLOAD_LIMIT, EMAIL_LANGUAGE,
    DISCOVER_INFOMANIAK, SHARE_IDEAS, GIVE_FEEDBACK,
}

@PreviewMobile
@Composable
private fun SettingsScreenPreview() {
    SwissTransferTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsScreen(onItemClick = {}, getSelectedSetting = { null })
        }
    }
}
