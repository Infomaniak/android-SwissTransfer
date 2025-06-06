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
package com.infomaniak.swisstransfer.ui.screen.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferDirection
import com.infomaniak.multiplatform_swisstransfer.managers.TransferManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeeplinkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val transferManager: TransferManager,
) : ViewModel() {

    val isDeeplinkConsumed = savedStateHandle.getStateFlow(IS_DEEPLINK_CONSUMED_KEY, false)

    fun consumeDeepLink() {
        if (!isDeeplinkConsumed.value) savedStateHandle[IS_DEEPLINK_CONSUMED_KEY] = true
    }

    suspend fun getDeeplinkTransferDirection(transferUuid: String): TransferDirection? {
        return transferManager.getTransferByUUID(transferUuid)?.direction
    }

    companion object {

        const val SENT_DEEPLINK_SUFFIX = "/sent"

        private const val IS_DEEPLINK_CONSUMED_KEY = "isDeepLinkConsumed"
    }
}
