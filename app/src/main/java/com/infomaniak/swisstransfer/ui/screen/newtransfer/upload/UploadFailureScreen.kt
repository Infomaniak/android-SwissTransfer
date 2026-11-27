/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.appintegrity.IntegrityDialogResponse
import com.infomaniak.core.ui.compose.basics.CallableState
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.BottomStickyButtonScaffold
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.core.ui.compose.theme.ThemedImage
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.OnboardingActivity
import com.infomaniak.swisstransfer.ui.OnboardingActivity.Companion.EXTRA_REQUIRED_LOGIN_KEY
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.appIntegrity.GhostScrollCrossPointing
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.upload.UploadState
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import com.infomaniak.core.appintegrity.R as RAppIntegrity
import com.infomaniak.core.common.R as RCore

@Composable
fun UploadFailureScreen(
    failureState: State<UploadState.Failure>,
    cancel: CallableState<Unit>,
) {

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.UploadError) }

    val failure: UploadState.Failure = failureState.value
    when (failure) {
        is UploadState.Failure.AppIntegrityIssue -> AppIntegrityUploadFailureScreen(failure, cancel)
        UploadState.Failure.SizeExceeded -> UploadFailureScreen(
            exitNewTransfer = cancel,
            title = stringResource(R.string.tooLargeFilesTitle),
            desc = stringResource(R.string.tooLargeFilesDescription)
        )
        UploadState.Failure.RestrictedLocation -> UploadFailureScreen(
            exitNewTransfer = cancel,
            illustration = AppIllus.MascotSearching,
            title = stringResource(R.string.sorry),
            desc = stringResource(R.string.restrictedLocation)
        )
    }
}

@Composable
private fun AppIntegrityUploadFailureScreen(
    failure: UploadState.Failure.AppIntegrityIssue,
    cancel: CallableState<Unit>,
) {
    val context = LocalContext.current
    val loginLauncher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) cancel()
    }
    val signIn: (() -> Unit)? = if (LocalUser.current == null) {
        {
            loginLauncher.launch(Intent(context, OnboardingActivity::class.java).putExtra(EXTRA_REQUIRED_LOGIN_KEY, true))
        }
    } else {
        null
    }

    UploadFailureScreen(
        exitNewTransfer = cancel,
        desc = stringResource(RAppIntegrity.string.errorAppIntegrity),
        showRemediationDialog = failure.showRemediationDialog,
        signIn = signIn,
    )
}

@Composable
private fun UploadFailureScreen(
    exitNewTransfer: () -> Unit,
    desc: String,
    illustration: ThemedImage = AppIllus.GhostScrollCrossPointing,
    title: String = stringResource(R.string.uploadErrorTitle) + if (BuildConfig.DEBUG) " Feur" else "",
    showRemediationDialog: (suspend (Activity) -> IntegrityDialogResponse)? = null,
    signIn: (() -> Unit)? = null,
) {
    BackHandler { exitNewTransfer() }

    val activity = LocalActivity.current
    val remediationRequest: CompletableJob = remember(showRemediationDialog, activity) { Job() }

    val canShowRemediationDialog by produceState(initialValue = false, remediationRequest) {
        value = false
        if (showRemediationDialog == null || activity == null) return@produceState
        value = true
        remediationRequest.join()
        value = false
        showRemediationDialog(activity)
    }

    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        topButton = if (canShowRemediationDialog || signIn != null) {
            { modifier ->
                Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Margin.Small)) {
                    if (canShowRemediationDialog) {
                        LargeButton(
                            modifier = Modifier.fillMaxWidth(),
                            style = ButtonType.Secondary,
                            title = stringResource(RCore.string.buttonRetry),
                            onClick = { remediationRequest.complete() },
                        )
                    }
                    if (signIn != null) {
                        LargeButton(
                            modifier = Modifier.fillMaxWidth(),
                            title = stringResource(R.string.settingsSignIn),
                            onClick = signIn,
                        )
                    }
                }
            }
        } else null,
        bottomButton = {
            LargeButton(
                modifier = it,
                style = if (signIn == null) ButtonType.Primary else ButtonType.Secondary,
                title = stringResource(R.string.contentDescriptionButtonClose),
                onClick = { exitNewTransfer() },
            )
        }
    ) {
        EmptyState(
            content = { Image(imageVector = illustration.image(), contentDescription = null) },
            title = title,
            description = if (signIn == null) desc else "$desc\n\n${stringResource(R.string.uploadErrorSignInDescription)}",
        )
    }
}

@PreviewAllWindows
@Composable
private fun AppIntegrityIssuePreview() {
    SwissTransferTheme {
        Surface {
            UploadFailureScreen(exitNewTransfer = {}, desc = stringResource(RAppIntegrity.string.errorAppIntegrity), signIn = {})
        }
    }
}

@PreviewAllWindows
@Composable
private fun RestrictedLocationPreview() {
    SwissTransferTheme {
        Surface {
            UploadFailureScreen(
                exitNewTransfer = {},
                illustration = AppIllus.MascotSearching,
                title = stringResource(R.string.sorry),
                desc = stringResource(R.string.restrictedLocation)
            )
        }
    }
}
