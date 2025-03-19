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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.basics.CallableState
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.appIntegrity.GhostScrollCrossPointing
import com.infomaniak.swisstransfer.ui.images.illus.mascotSearching.MascotSearching
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.upload.UploadState

@Composable
fun UploadFailureScreen(
    failureState: State<UploadState.Failure>,
    cancel: CallableState<Unit>,
) {
    val failure: UploadState.Failure by failureState
    when (failure) {
        UploadState.Failure.AppIntegrityIssue -> UploadFailureScreen(
            exitNewTransfer = cancel,
            desc = stringResource(R.string.errorAppIntegrity)
        )
        UploadState.Failure.RestrictedLocation -> UploadFailureScreen(
            exitNewTransfer = cancel,
            desc = stringResource(R.string.restrictedLocation)
        )
    }
    if (failure == UploadState.Failure.RestrictedLocation) {
        LocationBottomSheet(closeButtonSheet = cancel)
    }
}

@Composable
private fun UploadFailureScreen(
    exitNewTransfer: () -> Unit,
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
            content = { Image(imageVector = AppIllus.GhostScrollCrossPointing.image(), contentDescription = null) },
            title = stringResource(R.string.uploadErrorTitle) + if (BuildConfig.DEBUG) " Feur" else "",
            description = desc,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationBottomSheet(closeButtonSheet: () -> Unit) {
    SwissTransferBottomSheet(
        title = stringResource(R.string.sorry),
        description = stringResource(R.string.restrictedLocation),
        imageVector = AppIllus.MascotSearching.image(),
        bottomButton = {
            LargeButton(
                title = stringResource(R.string.contentDescriptionButtonClose),
                modifier = it,
                style = ButtonType.Primary,
                onClick = closeButtonSheet,
            )
        },
        onDismissRequest = closeButtonSheet,
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            UploadFailureScreen(exitNewTransfer = {}, stringResource(R.string.errorAppIntegrity))
        }
    }
}
