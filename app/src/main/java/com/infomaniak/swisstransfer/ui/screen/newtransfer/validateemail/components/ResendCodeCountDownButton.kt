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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private const val RESEND_EMAIL_CODE_COOLDOWN = 30

@Composable
fun ResendCodeCountDownButton(modifier: Modifier = Modifier, onResendEmailCode: () -> Unit, enabled: () -> Boolean) {
    var timeLeft by rememberSaveable { mutableIntStateOf(0) }
    var isRunning by rememberSaveable { mutableStateOf(false) }

    fun startTimer() {
        timeLeft = RESEND_EMAIL_CODE_COOLDOWN
        isRunning = true
    }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1.seconds)
                timeLeft--
            }
            isRunning = false
        }
    }

    if (timeLeft == 0) {
        LargeButton(
            modifier = modifier,
            title = stringResource(R.string.validateMailResendCode),
            style = ButtonType.TERTIARY,
            onClick = {
                startTimer()
                onResendEmailCode()
            },
            enabled = enabled
        )
    } else {
        LargeButton(
            modifier = modifier,
            title = stringResource(R.string.validateMailResendCodeTemplate, timeLeft),
            style = ButtonType.TERTIARY,
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
            ResendCodeCountDownButton(
                onResendEmailCode = {},
                enabled = { true }
            )
        }
    }
}