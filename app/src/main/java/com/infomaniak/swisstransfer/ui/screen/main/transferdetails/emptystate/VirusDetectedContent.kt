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

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.EmptyState
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.ghostPointingReport.GhostPointingReport
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun VirusDetectedContent() {
    EmptyState(
        content = { Image(imageVector = AppIllus.GhostPointingReport.image(), contentDescription = null) },
        title = stringResource(R.string.transferVirusDetectedTitle),
        description = stringResource(R.string.transferVirusDetectedDescription),
    )
}


@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            VirusDetectedContent()
        }
    }
}
