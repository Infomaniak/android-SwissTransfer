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
package com.infomaniak.swisstransfer.ui.utils

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.*
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.SmallButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first

// faire POC
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionState?.guardedCallback(action: () -> Unit): () -> Unit {
    if (this == null) return action

    val isGrantedFlow = remember(permission) { snapshotFlow { status.isGranted } }
    val actionFired: CompletableJob = remember(permission) { Job() }
    val shouldShowDialog = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(permission) {
        actionFired.join()
        isGrantedFlow.first { it }
        action()
    }

    if (status is PermissionStatus.Denied && !status.shouldShowRationale && shouldShowDialog.value) {
        DialogRequirePermission(
            onDismiss = { shouldShowDialog.value = false },
            onAuthorize = { context.openAppNotificationSettings() },
        )
    }

    return {
        if (status.isGranted) {
            action()
        } else {
            if (status is PermissionStatus.Denied && status.shouldShowRationale) { // verif shouldShowRationale
                launchPermissionRequest()
            } else {
                shouldShowDialog.value = true
            }
            actionFired.complete()
        }
    }
}

@Composable
fun DialogRequirePermission(onDismiss: () -> Unit, onAuthorize: () -> Unit) {
    SwissTransferAlertDialog(
        titleRes = R.string.authorizeNotificationTitle,
        descriptionRes = R.string.authorizeNotificationDescription,
        positiveButton = {
            SmallButton(
                style = ButtonType.Tertiary,
                title = stringResource(R.string.authorizeNotificationTitleButton),
                enabled = { true },
                onClick = onAuthorize,
            )
        },
        negativeButton = {},
        onDismiss = onDismiss,
        content = {}
    )
}

@Preview
@Composable
fun PreviewDialogRequirePermission() {
    SwissTransferTheme {
        DialogRequirePermission(onDismiss = {}, onAuthorize = {})
    }
}
