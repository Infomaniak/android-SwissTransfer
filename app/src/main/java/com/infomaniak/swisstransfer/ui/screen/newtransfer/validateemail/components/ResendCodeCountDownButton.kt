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
@file:OptIn(ExperimentalSplittiesApi::class, ExperimentalCoroutinesApi::class)

package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.infomaniak.core.common.isStartedFlow
import com.infomaniak.core.common.time.timeOffFromWholeSeconds
import com.infomaniak.core.ui.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import splitties.coroutines.repeatWhileActive
import splitties.experimental.ExperimentalSplittiesApi
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@Composable
fun ResendCodeCountDownButton(
    resendEmailCode: suspend () -> Unit,
    enabled: () -> Boolean,
    currentCodeExpiration: TimeMark?,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val secondsLeft: Long? by produceState(
        initialValue = null,
        key1 = currentCodeExpiration
    ) {
        if (currentCodeExpiration != null) lifecycle.isStartedFlow().transformLatest { isStarted ->
            if (isStarted.not()) return@transformLatest
            repeatWhileActive {
                val timeUntilExpiration = -currentCodeExpiration.elapsedNow()
                if (timeUntilExpiration.isNegative()) {
                    value = null
                    return@transformLatest emit(Unit)
                } else {
                    value = timeUntilExpiration.inWholeSeconds
                    delay(timeUntilExpiration.timeOffFromWholeSeconds())
                }
            }
        }.first()
    }
    when (val seconds = secondsLeft) {
        null -> LargeButton(
            modifier = modifier,
            title = stringResource(R.string.validateMailResendCode),
            style = ButtonType.Tertiary,
            onClick = { scope.launch { resendEmailCode() } },
            enabled = enabled
        )
        else -> LargeButton(
            modifier = modifier,
            title = stringResource(R.string.validateMailResendCodeTemplate, seconds),
            style = ButtonType.Tertiary,
            onClick = {},
            enabled = { false }
        )
    }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface(Modifier.width(300.dp)) {
            Column {
                ResendCodeCountDownButton(
                    resendEmailCode = {},
                    currentCodeExpiration = TimeSource.Monotonic.markNow() + 13.seconds,
                    enabled = { true }
                )
                ResendCodeCountDownButton(
                    resendEmailCode = {},
                    currentCodeExpiration = TimeSource.Monotonic.markNow() - 1.seconds,
                    enabled = { true }
                )
            }
        }
    }
}
