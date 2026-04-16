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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.permissionmanager.PermissionManagerState
import com.infomaniak.swisstransfer.R

@Composable
fun ExplainNotificationPermissionDialog(permissionManager: PermissionManagerState) {
    InformationPermissionDialog(
        permissionManager = permissionManager,
        title = stringResource(R.string.notificationUsageTitle),
        description = stringResource(R.string.notificationUsageDescription),
    )
}

@Composable
private fun InformationPermissionDialog(
    permissionManager: PermissionManagerState,
    title: String,
    description: String,
) {
    if (permissionManager.shouldShow) {
        SwissTransferAlertDialog(
            title = title,
            description = description,
            onDismiss = permissionManager::dismissAndAskPermission,
            positiveButton = {
                SwissTransferAlertDialogDefaults.ConfirmButton(
                    onClick = permissionManager::dismissAndAskPermission,
                )
            },
        )
    }
}
