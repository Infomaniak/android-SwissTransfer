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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.infomaniak.core.inappreview.reviewmanagers.InAppReviewManager
import com.infomaniak.core.inappupdate.updatemanagers.InAppUpdateManager
import com.infomaniak.core.inappupdate.updaterequired.ui.UpdateRequiredScreen
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.data.DeepLinkType
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.ReviewAlertDialog
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel
import com.infomaniak.swisstransfer.ui.screen.main.DeeplinkViewModel.Companion.SENT_DEEPLINK_SUFFIX
import com.infomaniak.swisstransfer.ui.screen.main.MainScreen
import com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsViewModel
import com.infomaniak.swisstransfer.ui.screen.main.transfers.components.DeleteTransferDialog
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.getDeeplinkTransferData
import com.infomaniak.swisstransfer.ui.utils.isDarkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.infomaniak.core.inappupdate.R as RInAppUpdate

@AndroidEntryPoint
class MainActivity : ComponentActivity(), AppReviewManageable, AppUpdateManageable {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private val deeplinkViewModel: DeeplinkViewModel by viewModels()

    @Inject
    lateinit var transferManager: TransferManager

    override val inAppReviewManager by lazy { InAppReviewManager(this) }
    override val inAppUpdateManager by lazy { InAppUpdateManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)

        initAppReviewManager()
        initAppUpdateManager()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(State.STARTED) { transferManager.tryUpdatingAllTransfers() }
        }

        lifecycleScope.launch {
            val deeplinkTransferData = getDeeplinkTransferData()
            val transferDirection = deeplinkTransferData?.uuid?.let {
                // If we don't find the transfer in Realm, it means it's a new received one
                deeplinkViewModel.getDeeplinkTransferDirection(it) ?: TransferDirection.RECEIVED
            }

            val deepLinkTypeFromURL = DeepLinkType.fromURL(intent.data.toString())
            when (deepLinkTypeFromURL){
                is DeepLinkType.DeleteTransfer -> {
                    // Modify the intent to avoid opening the transfer when we want to delete it via deeplink
                    intent.setData(null)
                }
                is DeepLinkType.OpenTransfer -> {
                    // Modify the intent to avoid conflict between the `Sent` and `Received` deeplinks
                    intent.setData((intent.data.toString() + SENT_DEEPLINK_SUFFIX).toUri())
                }
                else -> {}
            }

            setContent {
                with(inAppReviewManager) {
                    val appSettings by settingsViewModel.appSettingsFlow.collectAsStateWithLifecycle(initialValue = null)
                    val shouldDisplayReviewDialog by shouldDisplayReviewDialog.collectAsStateWithLifecycle(initialValue = false)
                    var shouldDisplayDeleteDialog by remember {
                        mutableStateOf(deepLinkTypeFromURL is DeepLinkType.DeleteTransfer && deeplinkTransferData.uuid != null)
                    }
                    val shouldDisplayUpdateRequiredScreen by inAppUpdateManager.shouldDisplayUpdateRequiredScreen.collectAsStateWithLifecycle(
                        initialValue = false
                    )

                    SwissTransferTheme(isDarkTheme = isDarkTheme(getTheme = { appSettings?.theme })) {
                        if (shouldDisplayReviewDialog && shouldDisplayUpdateRequiredScreen.not()) {
                            val feedbackUrl = stringResource(R.string.urlUserReport)
                            ReviewAlertDialog(
                                onUserWantsToReview = ::onUserWantsToReview,
                                onUserWantsToGiveFeedback = { onUserWantsToGiveFeedback(feedbackUrl) },
                                onDismiss = ::onUserWantsToDismiss,
                            )
                        }

                        if (shouldDisplayDeleteDialog && shouldDisplayUpdateRequiredScreen.not()) {

                            fun dismissDeleteDialog() {
                                shouldDisplayDeleteDialog = false
                            }

                            DeleteTransferDialog(
                                closeAlertDialog = ::dismissDeleteDialog,
                                onConfirmation = {
                                    lifecycleScope.launch {
                                        transferManager.deleteTransfer(
                                            deeplinkTransferData?.uuid!!,
                                            deeplinkTransferData.deleteToken!!,
                                        )
                                    }
                                    dismissDeleteDialog()
                                },
                            )
                        }

                        if (shouldDisplayUpdateRequiredScreen) {
                            UpdateRequiredScreen(
                                illustration = painterResource(R.drawable.illu_update_required),
                                titleTextStyle = SwissTransferTheme.typography.h1,
                                descriptionTextStyle = SwissTransferTheme.typography.bodyMedium,
                                installUpdateButton = {
                                    LargeButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        title = stringResource(RInAppUpdate.string.buttonUpdate),
                                        style = ButtonType.Primary,
                                        onClick = { inAppUpdateManager.requireUpdate() },
                                    )
                                }
                            )
                        } else {
                            MainScreen(deeplinkTransferDirection = transferDirection)
                        }
                    }
                }
            }
        }
    }
}
