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

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.common.extensions.goToAppStore
import com.infomaniak.core.common.extensions.openUrl
import com.infomaniak.core.network.AUTOLOG_URL
import com.infomaniak.core.network.SUPPORT_URL
import com.infomaniak.core.network.TERMINATE_ACCOUNT_URL
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.core.ui.compose.preview.previewparameter.UserListPreviewParameterProvider
import com.infomaniak.core.webview.ui.WebViewActivity
import com.infomaniak.multiplatform_swisstransfer.common.models.DownloadLimit
import com.infomaniak.multiplatform_swisstransfer.common.models.EmailLanguage
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.ValidityPeriod
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.OnboardingActivity
import com.infomaniak.swisstransfer.ui.OnboardingActivity.Companion.EXTRA_REQUIRED_LOGIN_KEY
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TwoPaneScaffold
import com.infomaniak.swisstransfer.ui.components.safeCurrentContent
import com.infomaniak.swisstransfer.ui.components.selectItem
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.mascotWithMagnifyingGlass.MascotWithMagnifyingGlass
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.ConfigUtils
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import com.infomaniak.swisstransfer.ui.utils.ScreenWrapperUtils
import com.infomaniak.swisstransfer.ui.utils.openAppNotificationSettings
import com.infomaniak.swisstransfer.ui.utils.safeStartActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val EULA_URL = "https://www.swisstransfer.com/?cgu"
private val TERMINATE_ACCOUNT_FULL_URL = "$AUTOLOG_URL/?url=$TERMINATE_ACCOUNT_URL"
private const val URL_REDIRECT_SUCCESSFUL_ACCOUNT_DELETION = "login.infomaniak.com"

@Composable
fun MyAccountScreenWrapper(
    myAccountViewModel: MyAccountViewModel = hiltViewModel<MyAccountViewModel>(),
) {
    val appSettings by myAccountViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)
    val users by myAccountViewModel.users.collectAsStateWithLifecycle(emptyList())

    appSettings?.let { safeAppSettings ->
        val theme = GetSetCallbacks(get = { safeAppSettings.theme }, set = { myAccountViewModel.setTheme(it) })
        val validityPeriod =
            GetSetCallbacks(get = { safeAppSettings.validityPeriod }, set = { myAccountViewModel.setValidityPeriod(it) })
        val downloadLimit =
            GetSetCallbacks(get = { safeAppSettings.downloadLimit }, set = { myAccountViewModel.setDownloadLimit(it) })
        val emailLanguage =
            GetSetCallbacks(get = { safeAppSettings.emailLanguage }, set = { myAccountViewModel.setEmailLanguage(it) })

        MyAccountScreenWrapper(
            theme = theme,
            users = { users },
            validityPeriod = validityPeriod,
            downloadLimit = downloadLimit,
            emailLanguage = emailLanguage,
            onDisconnectCurrentUser = myAccountViewModel::disconnectCurrentUser,
            onSwitchUser = myAccountViewModel::switchUser,
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MyAccountScreenWrapper(
    theme: GetSetCallbacks<Theme>,
    users: () -> List<User>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
    onDisconnectCurrentUser: () -> Unit,
    onSwitchUser: (userId: Int) -> Unit,
) {
    TwoPaneScaffold<SettingsOptionScreens>(
        listPane = { ListPane(navigator = this, users, onDisconnectCurrentUser, onSwitchUser) },
        detailPane = {
            DetailPane(
                navigator = this,
                theme = theme,
                validityPeriod = validityPeriod,
                downloadLimit = downloadLimit,
                emailLanguage = emailLanguage,
                onDisconnectCurrentUser = onDisconnectCurrentUser
            )
        },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListPane(
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
    users: () -> List<User>,
    onDisconnectCurrentUser: () -> Unit,
    onSwitchUser: (userId: Int) -> Unit,
) {
    val context = LocalContext.current
    val aboutURL = stringResource(R.string.urlAbout)
    val userReportURL = stringResource(R.string.urlUserReport)
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current

    val scope = rememberCoroutineScope()

    MyAccountScreen(
        users = users,
        onAction = { action ->
            when (action) {
                MyAccountSettingAction.Login -> {
                    val intent = Intent(context, OnboardingActivity::class.java).apply {
                        putExtra(EXTRA_REQUIRED_LOGIN_KEY, true)
                    }
                    context.safeStartActivity(intent)
                }
                MyAccountSettingAction.Support -> context.openUrl(SUPPORT_URL)
                MyAccountSettingAction.Logout -> onDisconnectCurrentUser()
                MyAccountSettingAction.Eula -> context.openUrl(EULA_URL)
                MyAccountSettingAction.DiscoverInfomaniak -> context.openUrl(aboutURL)
                MyAccountSettingAction.ShareIdeas -> context.openUrl(userReportURL)
                MyAccountSettingAction.GiveFeedback -> if (BuildConfig.DEBUG) {
                    context.goToAppStore(ConfigUtils.safePackage)
                } else {
                    context.goToAppStore()
                }
                is MyAccountSettingAction.SwitchAccount -> onSwitchUser(action.userId)
                is MyAccountSettingAction.Navigation -> {
                    // Navigate to the detail pane with the passed action
                    scope.launch { navigator.selectItem(context, windowAdaptiveInfo, action.destination) }
                }
            }
        },
        getSelectedSetting = { MyAccountSettingAction.Navigation.entries.firstOrNull { it.destination == navigator.currentDestination?.contentKey } },
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun DetailPane(
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
    theme: GetSetCallbacks<Theme>,
    validityPeriod: GetSetCallbacks<ValidityPeriod>,
    downloadLimit: GetSetCallbacks<DownloadLimit>,
    emailLanguage: GetSetCallbacks<EmailLanguage>,
    onDisconnectCurrentUser: () -> Unit,
) {

    val destination = navigator.safeCurrentContent()

    val scope = rememberCoroutineScope()
    val navigateBack: () -> Unit = {
        scope.launch { ScreenWrapperUtils.getBackNavigation(navigator)?.invoke() }
    }

    val accountDeletionActivityResultLauncher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) onDisconnectCurrentUser()
    }

    when (destination) {
        SettingsOptionScreens.Root -> {
            val user = LocalUser.current
            val context = LocalContext.current

            SettingsScreen(
                theme = theme,
                isAccountDeletable = { user != null },
                validityPeriod = validityPeriod,
                downloadLimit = downloadLimit,
                emailLanguage = emailLanguage,
                onItemClick = { item ->
                    handleSettingsItemClick(item, context, user, accountDeletionActivityResultLauncher, scope, navigator)
                },
                navigateBack = navigateBack,
                getSelectedSetting = { navigator.currentDestination?.contentKey },
            )
        }
        SettingsOptionScreens.Theme -> SettingsThemeScreen(
            theme = theme.get(),
            navigateBack = navigateBack,
            onThemeUpdate = { theme.set(it) },
        )
        SettingsOptionScreens.ValidityPeriod -> SettingsValidityPeriodScreen(
            validityPeriod = validityPeriod.get(),
            navigateBack = navigateBack,
            onValidityPeriodChange = { validityPeriod.set(it) },
        )
        SettingsOptionScreens.DownloadLimit -> SettingsDownloadsLimitScreen(
            downloadLimit = downloadLimit.get(),
            navigateBack = navigateBack,
            onDownloadLimitChange = { downloadLimit.set(it) },
        )
        SettingsOptionScreens.EmailLanguage -> SettingsEmailLanguageScreen(
            emailLanguage = emailLanguage.get(),
            navigateBack = navigateBack,
            onEmailLanguageChange = { emailLanguage.set(it) },
        )
        SettingsOptionScreens.DataManagement.Root -> SettingsDataManagementScreen(
            navigateBack = navigateBack,
            onItemClick = { item ->
                scope.launch { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item) }
            },
        )
        SettingsOptionScreens.DataManagement.Matomo -> SettingsDataManagementMatomoScreen(navigateBack)
        SettingsOptionScreens.DataManagement.Sentry -> SettingsDataManagementSentryScreen(navigateBack)
        SettingsOptionScreens.Notifications,
        SettingsOptionScreens.DeleteMyAccount,
        null -> NoSelectionEmptyState()
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun handleSettingsItemClick(
    item: SettingsOptionScreens,
    context: Context,
    user: User?,
    accountDeletionActivityResultLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    scope: CoroutineScope,
    navigator: ThreePaneScaffoldNavigator<SettingsOptionScreens>,
) {
    when (item) {
        SettingsOptionScreens.DeleteMyAccount -> {
            WebViewActivity.startActivity(
                context = context,
                url = TERMINATE_ACCOUNT_FULL_URL,
                headers = mapOf("Authorization" to "Bearer ${user?.apiToken?.accessToken}"),
                urlToQuit = URL_REDIRECT_SUCCESSFUL_ACCOUNT_DELETION,
                activityResultLauncher = accountDeletionActivityResultLauncher,
            )
        }
        SettingsOptionScreens.Notifications -> context.openAppNotificationSettings()
        else -> scope.launch { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item) }
    }
}

@Composable
private fun NoSelectionEmptyState() {
    SwissTransferScaffold(
        topBar = { SwissTransferTopAppBar(title = "") }
    ) {
        EmptyState(
            content = { Image(imageVector = AppIllus.MascotWithMagnifyingGlass.image(), contentDescription = null) },
            descriptionRes = R.string.noSettingsSelectedDescription
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(UserListPreviewParameterProvider::class) users: List<User>) {
    SwissTransferTheme {
        CompositionLocalProvider(LocalUser provides users.first()) {
            Surface(color = MaterialTheme.colorScheme.background) {
                MyAccountScreenWrapper(
                    theme = GetSetCallbacks(get = { Theme.SYSTEM }, set = {}),
                    users = { users },
                    validityPeriod = GetSetCallbacks(get = { ValidityPeriod.THIRTY }, set = {}),
                    downloadLimit = GetSetCallbacks(get = { DownloadLimit.TWO_HUNDRED_FIFTY }, set = {}),
                    emailLanguage = GetSetCallbacks(get = { EmailLanguage.ENGLISH }, set = {}),
                    onDisconnectCurrentUser = {},
                    onSwitchUser = {},
                )
            }
        }
    }
}
