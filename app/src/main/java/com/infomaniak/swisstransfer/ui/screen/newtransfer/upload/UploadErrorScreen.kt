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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.upload

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.illus.uploadError.GhostMagnifyingGlassQuestionMark
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.core2.R as RCore2

@Composable
fun UploadErrorScreen(navigateToImportFiles: () -> Unit) {
    BottomStickyButtonScaffold(
        topBar = { BrandTopAppBar() },
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = RCore2.string.buttonRetry,
                onClick = navigateToImportFiles
            )
        }
    ) {
        EmptyState(
            icon = AppImages.AppIllus.GhostMagnifyingGlassQuestionMark.image(),
            titleRes = R.string.uploadErrorTitle,
            descriptionRes = R.string.uploadErrorDescription,
        )
    }
}

@PreviewAllWindows
@Composable
private fun UploadErrorScreenPreview() {
    SwissTransferTheme {
        Surface {
            UploadErrorScreen({})
        }
    }
}