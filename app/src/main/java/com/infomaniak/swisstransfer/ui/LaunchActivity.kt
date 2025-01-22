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

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
import com.infomaniak.swisstransfer.ui.utils.hasValidTransferDeeplink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class LaunchActivity : ComponentActivity() {

    @Inject
    lateinit var accountUtils: AccountUtils

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
        val intent = when {
            hasValidTransferDeeplink() -> {
                if (!accountUtils.isUserConnected()) accountUtils.login()
                createDeeplinkIntent()
            }
            isSharingFilesToTheApp() -> createNewTransferSharingFileIntent()
            accountUtils.isUserConnected() -> Intent(this, MainActivity::class.java)
            else -> Intent(this, OnboardingActivity::class.java)
        }

        startActivity(intent)
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
            setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }

    private fun ComponentActivity.isSharingFilesToTheApp(): Boolean {
        return intent?.action == Intent.ACTION_SEND || intent?.action == Intent.ACTION_SEND_MULTIPLE
    }

    companion object {
        private val TAG = LaunchActivity::class.java.simpleName
    }
}
