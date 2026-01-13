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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.basics.CallableState
import com.infomaniak.core.ui.compose.bottomstickybuttonscaffolds.BottomStickyButtonScaffold
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoScreen
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.core.ui.compose.theme.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.appIntegrity.GhostScrollCrossPointing
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.upload.UploadState
import com.infomaniak.core.appintegrity.R as RAppIntegrity

@Composable
fun UploadFailureScreen(
    failureState: State<UploadState.Failure>,
    cancel: CallableState<Unit>,
) {

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.UploadError) }

    val failure: UploadState.Failure by failureState
    when (failure) {
        UploadState.Failure.AppIntegrityIssue -> UploadFailureScreen(
            exitNewTransfer = cancel,
            desc = stringResource(RAppIntegrity.string.errorAppIntegrity)
        )
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
private fun UploadFailureScreen(
    exitNewTransfer: () -> Unit,
    illustration: ThemedImage = AppIllus.GhostScrollCrossPointing,
    title: String = stringResource(R.string.uploadErrorTitle) + if (BuildConfig.DEBUG) " Feur" else "",
    desc: String
) {
    BackHandler { exitNewTransfer() }

    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.contentDescriptionButtonClose),
                onClick = { exitNewTransfer() },
            )
        }
    ) {
        EmptyState(
            content = { Image(imageVector = illustration.image(), contentDescription = null) },
            title = title,
            description = desc,
        )
    }
}

@PreviewAllWindows
@Composable
private fun AppIntegrityIssuePreview() {
    SwissTransferTheme {
        Surface {
            UploadFailureScreen(exitNewTransfer = {}, desc = stringResource(RAppIntegrity.string.errorAppIntegrity))
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
