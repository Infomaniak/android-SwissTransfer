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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

private val VALID_CHARACTERS = '0'..'9'
private const val OPT_LENGTH = 6

@Composable
fun ColumnScope.CodeVerification(
    modifier: Modifier = Modifier,
    otpCode: () -> String,
    updateOtpCode: (String, Boolean) -> Unit,
    isLoading: () -> Boolean,
    isInvalidVerificationCode: () -> Boolean,
) {
    Column(Modifier.align(Alignment.CenterHorizontally)) {
        Box(contentAlignment = Alignment.Center) {
            OtpTextField(
                modifier = modifier.fillMaxWidth(),
                otpText = otpCode(),
                otpLength = OPT_LENGTH,
                horizontalArrangement = Arrangement.SpaceBetween,
                onOtpTextChange = { text, isFilled ->
                    updateOtpCode(text, isFilled)
                },
                isCharacterValid = { it in VALID_CHARACTERS },
                isError = isInvalidVerificationCode,
                otpTextFieldStyle = OtpTextFieldStyle.default(textStyle = computeOtpTextStyle()),
                isEnabled = { !isLoading() }
            )

            if (isLoading()) {
                CircularProgressIndicator(modifier = Modifier, color = SwissTransferTheme.materialColors.primary)
            }
        }

        Spacer(modifier = Modifier.height(Margin.Micro))

        Text(
            modifier = Modifier.alpha(if (isInvalidVerificationCode()) 1f else 0f),
            text = stringResource(R.string.validateMailCodeIncorrectError),
            style = SwissTransferTheme.typography.labelRegular,
            color = SwissTransferTheme.materialColors.error,
        )
    }
}

@Composable
private fun computeOtpTextStyle(): TextStyle {
    return SwissTransferTheme.typography.bodyMedium.copy(color = SwissTransferTheme.colors.secondaryTextColor)
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(Margin.Medium)) {
                CodeVerification(
                    otpCode = { "123" },
                    updateOtpCode = { _, _ -> },
                    isLoading = { false },
                    isInvalidVerificationCode = { false },
                )
                CodeVerification(
                    otpCode = { "123456" },
                    updateOtpCode = { _, _ -> },
                    isLoading = { true },
                    isInvalidVerificationCode = { false },
                )
                CodeVerification(
                    otpCode = { "111111" },
                    updateOtpCode = { _, _ -> },
                    isLoading = { false },
                    isInvalidVerificationCode = { true },
                )
            }
        }
    }
}
