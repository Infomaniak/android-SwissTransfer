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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.core.ui.compose.preview.previewparameter.UserListPreviewParameterProvider
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Cog
import com.infomaniak.swisstransfer.ui.images.icons.DoorRectangleArrowRight
import com.infomaniak.swisstransfer.ui.images.icons.HeadphoneMicrophone
import com.infomaniak.swisstransfer.ui.images.icons.Person
import com.infomaniak.swisstransfer.ui.images.icons.PersonCircularArrowsCounterClockwise
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.DiscoverInfomaniak
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.Eula
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.GiveFeedback
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.Login
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.Logout
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.Navigation
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.ShareIdeas
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.Support
import com.infomaniak.swisstransfer.ui.screen.main.settings.MyAccountSetting.SwitchAccount
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.EndIconType.OPEN_OUTSIDE
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingDivider
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingItem
import com.infomaniak.swisstransfer.ui.screen.main.settings.components.SettingTitle
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.core.common.R as RCore

private val AVATAR_SIZE = 80.dp
private val AVATAR_SHAPE = CircleShape

@Composable
fun MyAccountScreen(
    onItemClick: (MyAccountSetting) -> Unit,
    getSelectedSetting: () -> MyAccountSetting?,
) {
    val selectedSetting = getSelectedSetting()
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.MyAccount) }

    SwissTransferScaffold(
        topBar = {
            if (windowAdaptiveInfo.isWindowLarge()) {
                SwissTransferTopAppBar(stringResource(R.string.settingsTitle))
            } else {
                BrandTopAppBar()
            }
        }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Profile(modifier = Modifier.padding(vertical = Margin.Large))
            SettingsItems(selectedSetting, onItemClick)
        }
    }
}

@Composable
private fun Profile(modifier: Modifier = Modifier) {
    AnimatedContent(
        targetState = LocalUser.current,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { user ->
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Margin.Mini),
        ) {
            if (user == null) {
                NoAccountAvatar()
                TitleAndDescription(pluralStringResource(RCore.plurals.myAccount, 1), "")
            } else {
                Avatar(AvatarType.fromUser(user), Modifier.size(AVATAR_SIZE), shape = AVATAR_SHAPE)
                TitleAndDescription(user.displayName.toString(), user.email, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun NoAccountAvatar() {
    Box(
        modifier = Modifier
            .size(AVATAR_SIZE)
            .clip(AVATAR_SHAPE)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = AppIcons.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun TitleAndDescription(title: String, description: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, style = SwissTransferTheme.typography.h1)
        Text(
            text = description,
            style = SwissTransferTheme.typography.bodySmallRegular,
            color = SwissTransferTheme.colors.tertiaryTextColor,
            maxLines = 1,
            overflow = TextOverflow.MiddleEllipsis,
        )
    }
}

@Composable
private fun SettingsItems(
    selectedSetting: MyAccountSetting?,
    onItemClick: (MyAccountSetting) -> Unit,
) {
    Column {
        if (LocalUser.current == null) {
            SettingItem(
                titleRes = R.string.settingsSignIn,
                isSelected = { selectedSetting == Login },
                icon = AppIcons.Person,
                endIcon = EndIconType.CHEVRON,
                onClick = { onItemClick(Login) },
            )
        } else {
            SettingItem(
                titleRes = R.string.settingsSwitchAccount,
                isSelected = { selectedSetting == SwitchAccount },
                icon = AppIcons.PersonCircularArrowsCounterClockwise,
                endIcon = EndIconType.CHEVRON,
                onClick = { onItemClick(SwitchAccount) },
            )
        }

        SettingItem(
            titleRes = R.string.settingsTitle,
            isSelected = { selectedSetting == Navigation.Settings },
            icon = AppIcons.Cog,
            endIcon = EndIconType.CHEVRON,
            onClick = { onItemClick(Navigation.Settings) },
        )

        SettingItem(
            titleRes = R.string.settingsHelpAndSupport,
            isSelected = { selectedSetting == Support },
            icon = AppIcons.HeadphoneMicrophone,
            endIcon = OPEN_OUTSIDE,
            onClick = { onItemClick(Support) },
        )

        AnimatedVisibility(LocalUser.current != null) {
            SettingItem(
                titleRes = R.string.settingsLogOut,
                isSelected = { selectedSetting == Logout },
                icon = AppIcons.DoorRectangleArrowRight,
                onClick = { onItemClick(Logout) },
            )
        }
        SettingDivider()

        SettingTitle(R.string.settingsCategoryAbout)
        SettingItem(
            titleRes = R.string.settingsOptionTermsAndConditions,
            isSelected = { selectedSetting == Eula },
            endIcon = OPEN_OUTSIDE,
            onClick = { onItemClick(Eula) },
        )
        SettingItem(
            titleRes = R.string.settingsOptionDiscoverInfomaniak,
            isSelected = { selectedSetting == DiscoverInfomaniak },
            endIcon = OPEN_OUTSIDE,
            onClick = { onItemClick(DiscoverInfomaniak) },
        )
        SettingItem(
            titleRes = R.string.settingsOptionShareIdeas,
            isSelected = { selectedSetting == ShareIdeas },
            endIcon = OPEN_OUTSIDE,
            onClick = { onItemClick(ShareIdeas) },
        )
        SettingItem(
            titleRes = R.string.settingsOptionGiveFeedback,
            isSelected = { selectedSetting == GiveFeedback },
            endIcon = OPEN_OUTSIDE,
            onClick = { onItemClick(GiveFeedback) },
        )
        SettingItem(
            titleRes = R.string.version,
            isSelected = { false },
            description = BuildConfig.VERSION_NAME,
            onClick = null,
        )
    }
}

@Immutable
sealed class MyAccountSetting(val matomoValue: MatomoName?) {
    data object Login : MyAccountSetting(MatomoName.Login)
    data object SwitchAccount : MyAccountSetting(MatomoName.SwitchUser)
    data object Support : MyAccountSetting(MatomoName.HelpAndSupport)
    data object Logout : MyAccountSetting(MatomoName.Logout)

    data object Eula : MyAccountSetting(MatomoName.TermsAndConditions)
    data object DiscoverInfomaniak : MyAccountSetting(MatomoName.DiscoverInfomaniak)
    data object ShareIdeas : MyAccountSetting(MatomoName.ShareYourIdeas)
    data object GiveFeedback : MyAccountSetting(MatomoName.GiveYourOpinion)

    sealed class Navigation(val destination: SettingsOptionScreens) : MyAccountSetting(null) {
        data object Settings : Navigation(SettingsOptionScreens.SETTINGS)

        companion object {
            val entries = arrayOf(Settings)
        }
    }
}

@PreviewAllWindows
@Composable
private fun SettingsScreenPreview(@PreviewParameter(UserListPreviewParameterProvider::class) users: List<User>) {
    SwissTransferTheme {
        CompositionLocalProvider(LocalUser provides users.first()) {
            Surface(color = MaterialTheme.colorScheme.background) {
                MyAccountScreen(
                    onItemClick = {},
                    getSelectedSetting = { null },
                )
            }
        }
    }
}
