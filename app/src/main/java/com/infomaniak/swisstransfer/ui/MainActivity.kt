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

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.infomaniak.core.inappreview.reviewmanagers.InAppReviewManager
import com.infomaniak.multiplatform_swisstransfer.common.models.Theme
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ReviewAlertDialog
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel.Companion.SENT_DEEPLINK_SUFFIX
import com.infomaniak.swisstransfer.ui.screen.main.MainScreen
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsViewModel
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.getDeeplinkTransferUuid
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), AppReviewManageable {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private val deeplinkViewModel: DeeplinkViewModel by viewModels()

    @Inject
    lateinit var transferManager: TransferManager

    override val inAppReviewManager by lazy { InAppReviewManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)

        initAppReviewManager()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(State.STARTED) { transferManager.tryUpdatingAllTransfers() }
        }

        lifecycleScope.launch {
            val deeplinkUuid = getDeeplinkTransferUuid()
            val transferDirection = deeplinkUuid?.let {
                // If we don't find the transfer in Realm, it means it's a new received one
                deeplinkViewModel.getDeeplinkTransferDirection(it) ?: TransferDirection.RECEIVED
            }

            if (transferDirection == TransferDirection.SENT) {
                // Modify the intent to avoid conflict between the `Sent` and `Received` deeplinks
                intent.setData((intent.data.toString() + SENT_DEEPLINK_SUFFIX).toUri())
            }

            setContent {
                with(inAppReviewManager) {
                    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(initialValue = null)
                    val shouldDisplayReviewDialog by shouldDisplayReviewDialog.collectAsStateWithLifecycle(initialValue = false)

                    SwissTransferTheme(isDarkTheme = isDarkTheme(getTheme = { appSettings?.theme })) {
                        if (shouldDisplayReviewDialog) {
                            val feedbackUrl = stringResource(R.string.urlUserReport)
                            ReviewAlertDialog(
                                onUserWantsToReview = ::onUserWantsToReview,
                                onUserWantsToGiveFeedback = { onUserWantsToGiveFeedback(feedbackUrl) },
                                onDismiss = ::onUserWantsToDismiss,
                            )
                        }

                        MainScreen(deeplinkTransferDirection = transferDirection)
                    }
                }
            }
        }
    }
}

@Composable
private fun isDarkTheme(getTheme: () -> Theme?): Boolean {
    return getTheme()?.let {
        if (it == Theme.SYSTEM) isSystemInDarkTheme() else it == Theme.DARK
    } ?: isSystemInDarkTheme()
}
