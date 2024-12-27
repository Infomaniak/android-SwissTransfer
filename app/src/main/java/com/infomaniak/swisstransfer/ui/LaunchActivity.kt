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
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.ui.utils.AccountUtils
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
        if (isValidTransferDeeplink()) {
            createDeeplink()
        } else {
            startActivity(Intent(this@LaunchActivity, chooseTargetActivity()))
        }
    }

    private fun chooseTargetActivity(): Class<out ComponentActivity> = when {
        accountUtils.isUserConnected() -> MainActivity::class
        else -> OnboardingActivity::class
    }.java

    private fun isValidTransferDeeplink(): Boolean {
        val deeplink = intent?.data?.toString()
        return deeplink?.matches(TRANSFER_DEEPLINK_REGEX) == true
    }

    private fun createDeeplink() {
        val deepLinkIntent = Intent(Intent.ACTION_VIEW, intent.data, /*context*/this, MainActivity::class.java)
        startActivity(deepLinkIntent)
    }

    companion object {
        private val TAG = LaunchActivity::class.java.simpleName
        private val TRANSFER_DEEPLINK_REGEX = "https://.+/d/.+".toRegex()
    }
}
