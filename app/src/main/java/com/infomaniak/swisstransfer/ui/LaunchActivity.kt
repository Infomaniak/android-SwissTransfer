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
import com.infomaniak.multiplatform_swisstransfer.managers.UploadManager
import com.infomaniak.swisstransfer.ui.navigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.TransferTypeUi.Companion.toTransferTypeUi
import com.infomaniak.swisstransfer.ui.utils.*
import com.infomaniak.swisstransfer.workers.UploadWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class LaunchActivity : ComponentActivity() {

    @Inject
    lateinit var accountUtils: AccountUtils

    @Inject
    lateinit var uploadWorkerScheduler: UploadWorker.Scheduler

    @Inject
    lateinit var appSettingsManager: AppSettingsManager

    @Inject
    lateinit var uploadManager: UploadManager

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
        val intent = when {
            hasValidTransferDeeplink() -> {
                connectLoggedOutUser()
                createDeeplinkIntent()
            }
            isSharingFilesToTheApp() -> {
                connectLoggedOutUser()
                createNewTransferSharingFileIntent()
            }
            isAlreadyUploading() -> {
                connectLoggedOutUser()
                createUploadInProgressIntent()
            }
            hasBeenSuccessful() -> {
                connectLoggedOutUser()
                createUploadSuccessfulIntent()
            }
            accountUtils.isUserConnected() -> Intent(this, MainActivity::class.java)
            else -> Intent(this, OnboardingActivity::class.java)
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

    private fun createUploadInProgressIntent(): Intent {
        val transferType = appSettingsManager.getAppSettings()!!.lastTransferType.toTransferTypeUi()
        val uploadSession = runBlocking { uploadManager.getLastUpload() }

        return Intent(intent).apply {
            setClass(this@LaunchActivity, NewTransferActivity::class.java)
            putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadProgress.name)
            putExtra(TRANSFER_TYPE_KEY, transferType.name)
            putExtra(TRANSFER_TOTAL_SIZE_KEY, uploadSession?.totalFileSize())

            // We need NewMessageActivity to have its standard launchMode in the Manifest
            // in order for FLAG_ACTIVITY_CLEAR_TOP to kill and recreate NewMessageActivity
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    private fun createUploadSuccessfulIntent(): Intent {
        val transferType = appSettingsManager.getAppSettings()!!.lastTransferType.toTransferTypeUi()
        val lastTransferUuid =
            applicationContext.lastTransferDataStore.getPreference(LastTransferPreferences.lastTransferUuid)
        val lastTransferUrl = sharedApiUrlCreator.shareTransferUrl(lastTransferUuid)

        return Intent(intent).apply {
            setClass(this@LaunchActivity, NewTransferActivity::class.java)
            putExtra(EXTERNAL_NAVIGATION_KEY, ExternalNavigation.UploadSuccess.name)
            putExtra(TRANSFER_TYPE_KEY, transferType.name)
            putExtra(TRANSFER_UUID_KEY, lastTransferUuid)
            putExtra(TRANSFER_URL_KEY, lastTransferUrl)

            // We need NewMessageActivity to have its standard launchMode in the Manifest
            // in order for FLAG_ACTIVITY_CLEAR_TOP to kill and recreate NewMessageActivity
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    private fun ComponentActivity.isSharingFilesToTheApp(): Boolean {
        return intent?.action == Intent.ACTION_SEND || intent?.action == Intent.ACTION_SEND_MULTIPLE
    }

    private fun isAlreadyUploading() = runBlocking { uploadWorkerScheduler.hasAlreadyBeenScheduled() }

    private fun hasBeenSuccessful(): Boolean {
        val hasBeenSuccessful = runBlocking { uploadWorkerScheduler.hasBeenSuccessful() }
        val lastTransferUuid = applicationContext.lastTransferDataStore.getPreference(LastTransferPreferences.lastTransferUuid)
        return hasBeenSuccessful && lastTransferUuid.isNotEmpty()
    }

    companion object {
        private val TAG = LaunchActivity::class.java.simpleName
    }
}
