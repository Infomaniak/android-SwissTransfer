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
package com.infomaniak.swisstransfer.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.multiplatform_swisstransfer.SharedApiUrlCreator
import com.infomaniak.multiplatform_swisstransfer.managers.AppSettingsManager
import com.infomaniak.swisstransfer.ui.navigation.EXTERNAL_NAVIGATION_KEY
import com.infomaniak.swisstransfer.ui.navigation.ExternalNavigation
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import com.infomaniak.swisstransfer.ui.utils.hasValidTransferDeeplink
import com.infomaniak.swisstransfer.upload.UploadForegroundService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class LaunchActivity : ComponentActivity() {

    @Inject
    lateinit var accountUtils: AccountUtils

    @Inject
    lateinit var appSettingsManager: AppSettingsManager

    @Inject
    lateinit var sharedApiUrlCreator: SharedApiUrlCreator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            runCatching {
                startTargetActivity()
            }.onFailure { exception ->
                SentryLog.e(TAG, "Failure for startTargetActivity", exception)
            }
            finish()
        }
    }

    private suspend fun startTargetActivity() {
        val uploadState = UploadForegroundService.uploadStateFlow.value
        val hasPickedFiles = with(UploadForegroundService) {
            pickedFilesFlow.value.isNotEmpty() || isHandlingPickedFilesFlow.value
        }
        val intent = when {
            hasValidTransferDeeplink() -> {
                connectLoggedOutUser()
                createDeeplinkIntent()
            }
            isSharingFilesToTheApp() -> {
                connectLoggedOutUser()
                createNewTransferSharingFileIntent()
            }
            uploadState != null -> {
                connectLoggedOutUser()
                createUploadOngoingIntent()
            }
            hasPickedFiles -> {
                connectLoggedOutUser()
                createPickFilesIntent()
            }
            accountUtils.isUserConnected().not() -> Intent(this, OnboardingActivity::class.java)
            else -> Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
    }

    /**
     * A user will be logged out if the app received an intent before the user ever finished the onboarding. In case of such an
     * intent, we want to handle it correctly but we first need to connect the user seamlessly.
     */
    private suspend fun connectLoggedOutUser() {
        if (!accountUtils.isUserConnected()) accountUtils.login()
    }

    private fun createDeeplinkIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, intent.data, this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun createNewTransferSharingFileIntent(): Intent {
        return Intent(intent).apply {
            setClass(this@LaunchActivity, NewTransferActivity::class.java)
            // We need NewMessageActivity to have its standard launchMode in the Manifest
            // in order for FLAG_ACTIVITY_CLEAR_TOP to kill and recreate NewMessageActivity
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
    }

    private fun createPickFilesIntent(): Intent {
        return Intent(this@LaunchActivity, NewTransferActivity::class.java)
    }

    private fun createUploadOngoingIntent(): Intent {

        return Intent(intent).apply {
            setClass(this@LaunchActivity, NewTransferActivity::class.java)
            putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadOngoing.name)

            // We need NewMessageActivity to have its standard launchMode in the Manifest
            // in order for FLAG_ACTIVITY_CLEAR_TOP to kill and recreate NewMessageActivity
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    private fun ComponentActivity.isSharingFilesToTheApp(): Boolean {
        return intent?.action == Intent.ACTION_SEND || intent?.action == Intent.ACTION_SEND_MULTIPLE
    }

    companion object {
        private val TAG = LaunchActivity::class.java.simpleName
    }
}
