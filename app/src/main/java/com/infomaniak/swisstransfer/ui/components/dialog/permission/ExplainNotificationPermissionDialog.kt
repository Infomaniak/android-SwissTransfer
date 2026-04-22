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
package com.infomaniak.swisstransfer.ui.components.dialog.permission

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun ExplainNotificationPermissionDialog(onDismiss: () -> Unit) {
    InformationPermissionDialog(
        title = stringResource(R.string.notificationUsageTitle),
        description = stringResource(R.string.notificationUsageDescription),
        onDismiss = onDismiss,
    )
}

@PreviewLightAndDark
@Composable
private fun ExplainNotificationPermissionDialogPreview() {
    SwissTransferTheme {
        ExplainNotificationPermissionDialog(onDismiss = { })
    }
}
