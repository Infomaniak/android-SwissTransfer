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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionState?.guardedCallback(action: () -> Unit): () -> Unit {
    if (this == null) return action
    val isGrantedFlow = remember(this.permission) { snapshotFlow { this.status.isGranted } }
    val actionFired: CompletableJob = remember(this.permission) { Job() }
    LaunchedEffect(this.permission) {
        actionFired.join()
        isGrantedFlow.first { it }
        action()
    }
    return {
        if (status.isGranted) action()
        else {
            launchPermissionRequest()
            actionFired.complete()
        }
    }
}
