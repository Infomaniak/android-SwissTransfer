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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.sentry.SentryLog
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components.CodeVerification
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components.ResendCodeCountDownButton
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.TextUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.safeStartActivity
import kotlinx.coroutines.launch
import com.infomaniak.core2.R as RCore2

private val MAX_LAYOUT_WIDTH = 400.dp

@Composable
fun ValidateUserEmailScreen(
    closeActivity: () -> Unit,
    navigateBack: () -> Unit,
    emailToValidate: String,
    validateUserEmailViewModel: ValidateUserEmailViewModel = hiltViewModel<ValidateUserEmailViewModel>(),
) {
    val isLoading by validateUserEmailViewModel.isLoading.collectAsStateWithLifecycle()
    val isError by validateUserEmailViewModel.isError.collectAsStateWithLifecycle()

    ValidateUserEmailScreen(
        emailToValidate = emailToValidate,
        validateEmailWithOtpCode = { (code, onSuccess, onUnknownError) ->
            validateUserEmailViewModel.validateEmailWithOtpCode(emailToValidate, code, onSuccess, onUnknownError)
        },
        resetErrorState = validateUserEmailViewModel::resetErrorState,
        isLoading = { isLoading },
        isError = { isError },
        navigateBack = navigateBack,
        closeActivity = closeActivity,
        onResendEmailCode = { validateUserEmailViewModel.resendEmailCode(emailToValidate) }
    )
}

@Composable
private fun ValidateUserEmailScreen(
    emailToValidate: String,
    validateEmailWithOtpCode: (ValidateEmailPayload) -> Unit,
    resetErrorState: () -> Unit,
    isLoading: () -> Boolean,
    isError: () -> Boolean,
    navigateBack: () -> Unit,
    closeActivity: () -> Unit,
    onResendEmailCode: () -> Unit,
) {
    var otpCode by rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    BottomStickyButtonScaffold(
        snackbarHostState = snackbarHostState,
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.transferTypeScreenTitle,
                navigationMenu = TopAppBarButton.backButton(navigateBack),
                TopAppBarButton.closeButton(closeActivity)
            )
        },
        topButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.buttonOpenMailApp),
                onClick = context::openMailApp,
                enabled = { !isLoading() })
        },
        bottomButton = { modifier ->
            ResendCodeCountDownButton(modifier, onResendEmailCode, enabled = { !isLoading() })
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

            CodeVerification(
                modifier = Modifier.widthIn(max = MAX_LAYOUT_WIDTH),
                otpCode = { otpCode },
                updateOtpCode = { code, isFilled ->
                    otpCode = code

                    if (isFilled) {
                        validateEmailWithOtpCode(
                            ValidateEmailPayload(
                                otpCode = code,
                                onSuccess = {}, // TODO: Navigate to the next step
                                onUnknownError = {
                                    SentryLog.e(
                                        "Email validation",
                                        "An unknown API error has occurred when validating the OTP code"
                                    )
                                    scope.launch { snackbarHostState.showSnackbar(context.getString(RCore2.string.anErrorHasOccurred)) }
                                },
                            )
                        )
                    } else {
                        resetErrorState()
                    }
                },
                isLoading = isLoading,
                isError = isError,
            )

            Text(
                text = stringResource(R.string.validateMailInfo),
                style = SwissTransferTheme.typography.labelRegular,
                color = SwissTransferTheme.colors.secondaryTextColor,
                textAlign = layoutStyle.textAlign,
            )
        }
    }
}

private fun Context.openMailApp() {
    val intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_EMAIL).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    safeStartActivity(Intent.createChooser(intent, getString(R.string.buttonOpenMailApp)))
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

private data class ValidateEmailPayload(
    val otpCode: String,
    val onSuccess: () -> Unit,
    val onUnknownError: () -> Unit,
)

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            ValidateUserEmailScreen(
                emailToValidate = "example@example.com",
                validateEmailWithOtpCode = {},
                resetErrorState = {},
                isLoading = { false },
                isError = { false },
                navigateBack = {},
                closeActivity = {},
                onResendEmailCode = {},
            )
        }
    }
}
