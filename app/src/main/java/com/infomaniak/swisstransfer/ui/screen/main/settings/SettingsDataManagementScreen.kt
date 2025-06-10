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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.components.SharpRippleButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.icons.matomo.Matomo
import com.infomaniak.swisstransfer.ui.images.icons.sentry.Sentry
import com.infomaniak.swisstransfer.ui.images.illus.dataProtection.DataProtection
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT_MATOMO
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsOptionScreens.DATA_MANAGEMENT_SENTRY
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.CHEVRON
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import com.infomaniak.swisstransfer.ui.utils.openUrl

@Composable
fun SettingsDataManagementScreen(
    navigateBack: (() -> Unit)?,
    onItemClick: (SettingsOptionScreens) -> Unit,
) {

    val context = LocalContext.current
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    SwissTransferScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = com.infomaniak.core.R.string.trackingManagementTitle,
                navigationIcon = { if (windowAdaptiveInfo.isWindowSmall()) TopAppBarButtons.Back(onClick = navigateBack ?: {}) },
            )
        },
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = AppIllus.DataProtection.image(),
                contentDescription = null,
                modifier = Modifier.padding(Margin.Medium),
            )
            Text(
                text = stringResource(com.infomaniak.core.R.string.trackingManagementDescription),
                style = SwissTransferTheme.typography.bodyRegular,
                color = SwissTransferTheme.colors.primaryTextColor,
                modifier = Modifier.padding(Margin.Medium),
            )
            SharpRippleButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { context.openUrl(BuildConfig.GITHUB_REPO_URL) },
            ) {
                Text(
                    text = stringResource(com.infomaniak.core.R.string.applicationSourceCode),
                    style = SwissTransferTheme.typography.bodyMedium,
                    color = SwissTransferTheme.materialColors.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.SettingHorizontalMargin, vertical = Dimens.SettingVerticalMargin),
                )
            }
            SettingItem(
                titleRes = com.infomaniak.core.R.string.trackingMatomoTitle,
                isSelected = { false },
                icon = AppIcons.Matomo.image(),
                endIcon = CHEVRON,
                shouldTintIcon = false,
                onClick = { onItemClick(DATA_MANAGEMENT_MATOMO) },
            )
            SettingItem(
                titleRes = com.infomaniak.core.R.string.trackingSentryTitle,
                isSelected = { false },
                icon = AppIcons.Sentry.image(),
                endIcon = CHEVRON,
                shouldTintIcon = false,
                onClick = { onItemClick(DATA_MANAGEMENT_SENTRY) },
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            SettingsDataManagementScreen({}, {})
        }
    }
}
