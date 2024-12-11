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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.OtpTextField
import com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles.components.OtpTextFieldStyle
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.TextUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val VALID_CHARACTERS = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
private const val OPT_LENGTH = 6

private val MAX_LAYOUT_WIDTH = 400.dp

@Composable
fun ValidateUserEmailScreen(closeActivity: () -> Unit, navigateBack: () -> Unit, emailToValidate: String) {
    BottomStickyButtonScaffold(
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.transferTypeScreenTitle,
                navigationMenu = TopAppBarButton.backButton(navigateBack),
                TopAppBarButton.closeButton(closeActivity)
            )
        },
        topButton = { LargeButton(modifier = it, titleRes = R.string.buttonOpenMailApp, onClick = { }) },
        bottomButton = {
            LargeButton(
                modifier = it,
                titleRes = R.string.validateMailCodeNotReceived,
                style = ButtonType.TERTIARY,
                onClick = {},
            )
        },
    ) {
        val layoutStyle = LayoutStyle.getCurrentLayoutStyle()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(Margin.Medium)
                .then(layoutStyle.columnMaxWidthModifier)
                .align(layoutStyle.verticalAlignment),
            verticalArrangement = Arrangement.spacedBy(Margin.Large),
            horizontalAlignment = layoutStyle.horizontalAlignment,
        ) {
            Text(
                text = stringResource(id = R.string.validateMailTitle),
                style = SwissTransferTheme.typography.h1,
                textAlign = layoutStyle.textAlign,
            )

            Text(
                TextUtils.assembleWithBoldArgument(stringResource(R.string.validateMailDescription), emailToValidate),
                color = SwissTransferTheme.colors.secondaryTextColor,
                textAlign = layoutStyle.textAlign,
            )

            CodeVerification()

            Text(
                text = stringResource(R.string.validateMailInfo),
                style = SwissTransferTheme.typography.labelRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                textAlign = layoutStyle.textAlign,
            )
        }
    }
}

@Composable
private fun ColumnScope.CodeVerification() {
    var otpCode by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(Modifier.align(Alignment.CenterHorizontally)) {
        Box(contentAlignment = Alignment.Center) {
            OtpTextField(
                modifier = Modifier
                    .widthIn(max = MAX_LAYOUT_WIDTH)
                    .fillMaxWidth(),
                otpText = otpCode,
                otpLength = OPT_LENGTH,
                horizontalArrangement = Arrangement.SpaceBetween,
                onOtpTextChange = { text, isFilled ->
                    otpCode = text

                    if (isFilled) {
                        scope.launch {
                            isLoading = true
                            delay(2000)
                            isLoading = false

                            isError = otpCode != "111111"
                        }
                    } else {
                        isError = false
                    }
                },
                isCharacterValid = { it in VALID_CHARACTERS },
                isError = { isError },
                otpTextFieldStyle = OtpTextFieldStyle.default(
                    textStyle = SwissTransferTheme.typography.bodyMedium.copy(color = SwissTransferTheme.colors.secondaryTextColor),
                ),
                isEnabled = { !isLoading }
            )

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier, color = SwissTransferTheme.materialColors.primary)
            }
        }

        Spacer(modifier = Modifier.height(Margin.Micro))

        Text(
            modifier = Modifier.alpha(if (isError) 1f else 0f),
            text = stringResource(R.string.validateMailCodeIncorrectError),
            style = SwissTransferTheme.typography.labelRegular,
            color = SwissTransferTheme.materialColors.error,
        )
    }
}

private enum class LayoutStyle(
    val horizontalAlignment: Alignment.Horizontal,
    val textAlign: TextAlign,
    val verticalAlignment: Alignment,
    val columnMaxWidthModifier: Modifier,
) {
    TopLeft(
        horizontalAlignment = Alignment.Start,
        textAlign = TextAlign.Start,
        verticalAlignment = Alignment.TopCenter,
        columnMaxWidthModifier = Modifier,
    ),
    Centered(
        horizontalAlignment = Alignment.CenterHorizontally,
        textAlign = TextAlign.Center,
        verticalAlignment = Alignment.Center,
        columnMaxWidthModifier = Modifier.widthIn(max = MAX_LAYOUT_WIDTH),
    );

    companion object {
        @Composable
        fun getCurrentLayoutStyle() = if (LocalWindowAdaptiveInfo.current.isWindowLarge()) Centered else TopLeft
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ValidateUserEmailScreen(
                closeActivity = {},
                navigateBack = {},
                emailToValidate = "example@example.com",
            )
        }
    }
}
