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

import android.Manifest
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel
import com.infomaniak.swisstransfer.ui.screen.main.MainScreen
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsViewModel
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.getDeeplinkTransferUuid
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private val deeplinkViewModel: DeeplinkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)

        askUserForNotificationsPermission()
        
        lifecycleScope.launch {
            val deeplinkUuid = getDeeplinkTransferUuid()
            val transferDirection = deeplinkUuid?.let {
                deeplinkViewModel.getDeeplinkTransferDirection(it) ?: TransferDirection.RECEIVED
            }

            if (transferDirection == TransferDirection.SENT) {
                // Modify the intent to avoid conflict between the `Sent` and `received` deeplinks
                intent.setData((intent.data.toString() + "/sent").toUri())
            }

            setContent {
                val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(null)
                SwissTransferTheme(isDarkTheme = isDarkTheme(getTheme = { appSettings?.theme })) {
                    MainScreen(deeplinkTransferDirection = transferDirection)
                }
            }
        }
    }

    private fun askUserForNotificationsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                callback = {},
            ).launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

@Composable
private fun isDarkTheme(getTheme: () -> Theme?): Boolean {
    return getTheme()?.let {
        if (it == Theme.SYSTEM) isSystemInDarkTheme() else it == Theme.DARK
    } ?: isSystemInDarkTheme()
}
