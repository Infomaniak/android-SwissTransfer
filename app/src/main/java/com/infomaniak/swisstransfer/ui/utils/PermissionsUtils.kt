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

import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import com.infomaniak.core.isResumedFlow
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.SmallButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferAlertDialog
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PermissionState?.guardedCallback(action: () -> Unit, onShowDialog: (() -> Unit)): () -> Unit {
    if (this == null) return action

    val scope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    return {
        if (status.isGranted) {
            action()
        } else {
            scope.launch {
                snapshotFlow { status.isGranted }.first { granted ->
                    if (granted) {
                        action()
                    } else if (!status.shouldShowRationale) {
                        onShowDialog.invoke()
                    } else {
                        launchPermissionRequest()
                        val userSelection = getUserSelection(lifecycle)
                        if (userSelection == UserSelection.No) onShowDialog.invoke()
                    }
                    granted
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private suspend fun PermissionState.getUserSelection(lifecycle: Lifecycle): UserSelection {
    val userSelection = lifecycle
        .isResumedFlow()
        .mapNotNull {
            when {
                status.isGranted -> UserSelection.Yes
                !status.shouldShowRationale -> UserSelection.No
                else -> null
            }
        }
        .first()
    return userSelection
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionState?.guardedCallbackWithDialog(action: () -> Unit, @StringRes descriptionRes: Int): () -> Unit {
    if (this == null) return action

    var showDialog by rememberSaveable { mutableStateOf(false) }
    val launchAction = guardedCallback(action, onShowDialog = { showDialog = true })
    val context = LocalContext.current

    if (showDialog) {
        LaunchedEffect(Unit) {
            snapshotFlow { status.isGranted }.first { granted ->
                if (granted) action()
                granted
            }
        }

        DialogRequirePermission(
            onDismiss = { showDialog = false },
            onAuthorize = {
                showDialog = false
                context.openAppNotificationSettings()
            },
            descriptionRes = descriptionRes,
        )
    }

    return launchAction
}

@Composable
private fun DialogRequirePermission(onDismiss: () -> Unit, onAuthorize: () -> Unit, @StringRes descriptionRes: Int) {
    SwissTransferAlertDialog(
        titleRes = R.string.authorizeNotificationTitle,
        descriptionRes = descriptionRes,
        positiveButton = {
            SmallButton(
                style = ButtonType.Tertiary,
                title = stringResource(R.string.authorizeNotificationTitleButton),
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
        DialogRequirePermission(
            onDismiss = {},
            onAuthorize = {},
            descriptionRes = R.string.authorizeDownloadNotificationDescription
        )
    }
}

private enum class UserSelection {
    Yes, No
}
