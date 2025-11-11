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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.emptystate

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.infomaniak.core.compose.bottomstickybuttonscaffolds.BottomStickyButtonScaffold
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.Bin
import com.infomaniak.swisstransfer.ui.previewparameter.TransferStatusUiListPreviewParameterProvider
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.DeletableFromHistory
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel.TransferDetailsUiState.TransferError
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun EmptyStateScreen(
    transferError: TransferError,
    onDeleteTransferClicked: (DeletableFromHistory) -> Unit,
    onCloseClicked: (() -> Unit)? = null,
) {
    BottomStickyButtonScaffold(
        topBar = { SwissTransferTopAppBar(navigationIcon = { onCloseClicked?.let { TopAppBarButtons.Close(onClick = it) } }) },
        modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
        bottomButton = { modifier ->
            if (transferError is DeletableFromHistory) {
                DeleteButton(
                    modifier,
                    onClick = {
                        onDeleteTransferClicked(transferError)
                        onCloseClicked?.invoke()
                    },
                )
            }
        },
    ) {
        when (transferError) {
            is TransferError.Expired -> ExpiredTransferContent(transferError)
            is TransferError.WaitVirusCheck -> VirusCheckContent()
            is TransferError.VirusDetected -> VirusDetectedContent()
        }
    }
}

@Composable
fun DeleteButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    LargeButton(
        modifier = modifier,
        title = stringResource(R.string.transferExpiredButton),
        imageVector = AppImages.AppIcons.Bin,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun PreviewAllState(@PreviewParameter(TransferStatusUiListPreviewParameterProvider::class) transferError: TransferError) {
    SwissTransferTheme {
        Surface {
            EmptyStateScreen(
                transferError = transferError,
                onCloseClicked = {},
                onDeleteTransferClicked = {}
            )
        }
    }
}

@PreviewAllWindows
@Composable
private fun PreviewAllWindows() {
    val transferError: TransferError = TransferError.Expired.ByQuota(downloadLimit = 25)
    SwissTransferTheme {
        Surface {
            EmptyStateScreen(
                transferError = transferError,
                onCloseClicked = {},
                onDeleteTransferClicked = {}
            )
        }
    }
}
