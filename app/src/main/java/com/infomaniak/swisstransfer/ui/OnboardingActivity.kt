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
package com.infomaniak.swisstransfer.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.infomaniak.core.auth.UserExistenceChecker
import com.infomaniak.core.auth.models.UserLoginResult
import com.infomaniak.core.auth.utils.LoginFlowController
import com.infomaniak.core.auth.utils.LoginUtils
import com.infomaniak.core.common.observe
import com.infomaniak.core.crossapplogin.back.BaseCrossAppLoginViewModel
import com.infomaniak.core.crossapplogin.back.ExternalAccount
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.core.ui.compose.basics.LockScreenOrientation
import com.infomaniak.lib.login.InfomaniakLogin
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.screen.onboarding.OnboardingScreen
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowSmall
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrossAppLoginViewModel() : BaseCrossAppLoginViewModel(BuildConfig.APPLICATION_ID, BuildConfig.CLIENT_ID)

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {

    @Inject
    lateinit var infomaniakLogin: InfomaniakLogin

    private val crossAppLoginViewModel: CrossAppLoginViewModel by viewModels()

    private var areButtonsLoading by mutableStateOf(false)

    @Inject
    lateinit var accountUtils: AccountUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))

        if (SDK_INT >= 29) window.isNavigationBarContrastEnforced = false

        setupCrossAppLogin()

        val shouldDisplayRequiredLogin = intent.getBooleanExtra(EXTRA_REQUIRED_LOGIN_KEY, false)

        setContent {
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            SwissTransferTheme {
                LockScreenOrientation(isLocked = LocalWindowAdaptiveInfo.current.isWindowSmall())

                val accountsCheckingState by crossAppLoginViewModel.accountsCheckingState.collectAsStateWithLifecycle()
                val skippedIds by crossAppLoginViewModel.skippedAccountIds.collectAsStateWithLifecycle()

                val loginFlowController = LoginUtils.rememberLoginFlowController(
                    infomaniakLogin = infomaniakLogin,
                    userExistenceChecker = accountUtils,
                ) { userLoginResult ->
                    when (userLoginResult) {
                        is UserLoginResult.Success -> {
                            Log.e("gibran", "onCreate - accessToken: ${userLoginResult.user.apiToken.accessToken}")
                        }
                        is UserLoginResult.Failure -> scope.launch { snackbarHostState.showSnackbar(userLoginResult.errorMessage) }
                        null -> Unit
                    }

                    if (userLoginResult !is UserLoginResult.Success) stopLoadingLoginButtons()
                }

                Surface {
                    OnboardingScreen(
                        shouldDisplayRequiredLogin = shouldDisplayRequiredLogin,
                        goToMainActivity = {
                            scope.launch {
                                accountUtils.loginGuestUser()
                                Intent(this@OnboardingActivity, MainActivity::class.java).also(::startActivity)
                                finish()
                            }
                        },
                        accountsCheckingState = { accountsCheckingState },
                        skippedIds = { skippedIds },
                        areLoginButtonsLoading = { areButtonsLoading },
                        onLoginRequest = { accounts ->
                            if (accounts.isEmpty()) {
                                openLoginWebView(loginFlowController)
                            } else {
                                scope.launch { connectSelectedAccounts(accounts, crossAppLoginViewModel, snackbarHostState) }
                            }
                        },
                        onCreateAccount = { /*TODO*/ },
                        onSaveSkippedAccounts = { crossAppLoginViewModel.skippedAccountIds.value = it },
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }

    private fun setupCrossAppLogin() {
        lifecycleScope.launch {
            crossAppLoginViewModel.activateUpdates(this@OnboardingActivity)
        }

        lifecycleScope.launch {
            crossAppLoginViewModel.availableAccounts.observe(this@OnboardingActivity) { accounts ->
                SentryLog.i(TAG, "Got ${accounts.count()} accounts from other apps")
            }
        }
    }

    private fun openLoginWebView(loginFlowController: LoginFlowController) {
        startLoadingLoginButtons()
        loginFlowController.login()
    }

    private suspend fun connectSelectedAccounts(
        accounts: List<ExternalAccount>,
        viewModel: BaseCrossAppLoginViewModel,
        snackbarHostState: SnackbarHostState,
    ) {
        startLoadingLoginButtons()
        val loginResult = viewModel.attemptLogin(selectedAccounts = accounts)
        loginUsers(loginResult, snackbarHostState)
        loginResult.errorMessageIds.forEach { messageResId -> snackbarHostState.showSnackbar(getString(messageResId)) }
    }

    private suspend fun loginUsers(loginResult: BaseCrossAppLoginViewModel.LoginResult, snackbarHostState: SnackbarHostState) {
        val results = LoginUtils.getLoginResultsAfterCrossApp(loginResult.tokens, this, accountUtils)
        val accessTokens = buildList {
            results.forEach { result ->
                when (result) {
                    is UserLoginResult.Success -> add(result.user.apiToken.accessToken)
                    is UserLoginResult.Failure -> snackbarHostState.showSnackbar(result.errorMessage)
                }
            }
        }

        if (accessTokens.isEmpty()) {
            stopLoadingLoginButtons()
        } else {
            Log.e("gibran", "loginUsers - accessTokens: ${accessTokens}")
        }
    }

    private fun startLoadingLoginButtons() {
        areButtonsLoading = true
    }

    private fun stopLoadingLoginButtons() {
        areButtonsLoading = false
    }

    companion object {
        private val TAG = OnboardingActivity::class.java.simpleName

        const val EXTRA_REQUIRED_LOGIN_KEY = "EXTRA_REQUIRED_LOGIN_KEY"
    }
}
