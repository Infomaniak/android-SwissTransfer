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

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.core.os.BundleCompat
import androidx.lifecycle.lifecycleScope
import com.infomaniak.core.utils.enumValueOfOrNull
import com.infomaniak.swisstransfer.ui.navigation.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferOpenManager
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.infomaniak.swisstransfer.ui.screen.newtransfer.NewTransferOpenManager.Reason as OpenReason

@AndroidEntryPoint
class NewTransferActivity : ComponentActivity() {

    @Inject
    lateinit var newTransferOpenManager: NewTransferOpenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        handleSharedFiles(intent)
        addOnNewIntentListener { newIntent ->
            handleSharedFiles(newIntent)
        }
        setContent {
            SwissTransferTheme {
                NewTransferScreen(
                    startDestination = remember { getStartDestination() },
                    closeActivity = { startMainActivityIfTaskIsEmpty ->
                        finishNewTransferActivity(startMainActivityIfTaskIsEmpty)
                    },
                )
            }
        }
    }

    private fun handleSharedFiles(intent: Intent) {
        val openReason: OpenReason = when (intent.action) {
            Intent.ACTION_SEND -> OpenReason.ExternalShareIncoming(getSingleUri(intent))
            Intent.ACTION_SEND_MULTIPLE -> OpenReason.ExternalShareIncoming(getMultipleUris(intent))
            else -> OpenReason.Other
        }
        lifecycleScope.launch { newTransferOpenManager.setOpenReason(openReason) }
    }

    private fun getSingleUri(intent: Intent): List<Uri> {
        val extras = intent.extras ?: return emptyList()
        val uri = BundleCompat.getParcelable(extras, Intent.EXTRA_STREAM, Uri::class.java)

        return if (uri == null) emptyList() else listOf(uri)
    }

    private fun getMultipleUris(intent: Intent): List<Uri> {
        val extras = intent.extras ?: return emptyList()
        val uris = BundleCompat.getParcelableArrayList(extras, Intent.EXTRA_STREAM, Uri::class.java)

        return uris ?: emptyList()
    }

    private fun finishNewTransferActivity(startMainActivityIfTaskIsEmpty: Boolean) {
        when {
            isTaskRoot && startMainActivityIfTaskIsEmpty -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            isTaskRoot -> finishAndRemoveTask()
            else -> finish()
        }
    }

    private fun getStartDestination(): NewTransferNavigation {

        val externalNavigation = enumValueOfOrNull<ExternalNavigation>(
            value = intent?.extras?.getString(EXTERNAL_NAVIGATION_KEY),
        )

        return when (externalNavigation) {
            ExternalNavigation.UploadOngoing -> {
                NewTransferNavigation.startDestination
            }
            ExternalNavigation.UploadSuccess -> {
                val transferType = enumValueOfOrNull<TransferTypeUi>(intent?.extras?.getString(TRANSFER_TYPE_KEY))
                NewTransferNavigation.UploadSuccessDestination(
                    transferType = transferType!!,
                    transferUuid = intent?.extras?.getString(TRANSFER_UUID_KEY)!!,
                    transferUrl = intent?.extras?.getString(TRANSFER_URL_KEY)!!,
                )
            }
            null -> NewTransferNavigation.startDestination
        }
    }
}
