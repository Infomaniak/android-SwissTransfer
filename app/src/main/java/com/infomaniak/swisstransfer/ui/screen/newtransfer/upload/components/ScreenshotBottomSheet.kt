/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload.components

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.multiplatform_swisstransfer.common.matomo.MatomoName
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.illus.screenshotBottomSheet.Lightbulb
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.TextUtils
import com.infomaniak.swisstransfer.ui.utils.shareText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenshotBottomSheet(isVisible: () -> Boolean, closeBottomSheet: () -> Unit, transferLink: String) {
    if (!isVisible()) return

    val context = LocalContext.current

    val descriptionTemplate = stringResource(R.string.quickSharingDescription)
    val buttonShareLabel = stringResource(R.string.buttonShare)
    val buttonCopyLinkLabel = stringResource(R.string.buttonCopyLink)

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        topButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.buttonShare),
                onClick = {
                    MatomoSwissTransfer.trackNewTransferEvent(MatomoName.Share)
                    context.shareText(transferLink)
                },
            )
        },
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.understandTitleButton),
                style = ButtonType.Tertiary,
                onClick = closeBottomSheet,
            )
        },
        imageVector = AppImages.AppIllus.Lightbulb.image(),
        title = stringResource(R.string.oneClickSharing),
        annotatedDescription = TextUtils.assembleWithBoldArgument(descriptionTemplate, buttonShareLabel, buttonCopyLinkLabel),
    )
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ScreenshotBottomSheet(
                isVisible = { true },
                closeBottomSheet = {},
                transferLink = "",
            )
        }
    }
}
