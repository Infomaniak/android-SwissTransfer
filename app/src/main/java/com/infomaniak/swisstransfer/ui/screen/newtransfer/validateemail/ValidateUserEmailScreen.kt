/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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

import android.content.ClipDescription
import android.content.ClipboardManager
import androidx.activity.compose.BackHandler
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import com.infomaniak.core.sentry.SentryLog
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer
import com.infomaniak.swisstransfer.ui.MatomoSwissTransfer.MatomoScreen
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.ValidateUserEmailViewModel.ValidateEmailUiState
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.ValidateUserEmailViewModel.ValidationRequest
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components.CodeVerification
import com.infomaniak.swisstransfer.ui.screen.newtransfer.validateemail.components.ResendCodeCountDownButton
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows
import com.infomaniak.swisstransfer.ui.utils.TextUtils
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import com.infomaniak.swisstransfer.ui.utils.openMailApp
import com.infomaniak.swisstransfer.upload.UploadState
import kotlinx.coroutines.launch

private val MAX_LAYOUT_WIDTH = 400.dp

@Composable
fun ValidateUserEmailScreen(
    editTransfer: () -> Unit,
    state: UploadState.Retry.EmailValidationRequired,
    validateUserEmailViewModel: ValidateUserEmailViewModel = hiltViewModel(),
) {

    val emailToValidate = state.info.authorEmail
    val uiState by validateUserEmailViewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by remember { derivedStateOf { uiState == ValidateEmailUiState.Loading } }
    val isInvalidVerificationCode by remember { derivedStateOf { uiState == ValidateEmailUiState.InvalidVerificationCode } }

    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var otpCode by rememberSaveable { mutableStateOf("") }
    var isFirstResumed by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) { MatomoSwissTransfer.trackScreen(MatomoScreen.VerifyMail) }

    val lifecycleState = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value

    LaunchedEffect(lifecycleState) {

        if (lifecycleState != Lifecycle.State.RESUMED) return@LaunchedEffect
        if (isFirstResumed) {
            isFirstResumed = false
            return@LaunchedEffect
        }

        val clipBoardManager = context.getSystemService(ClipboardManager::class.java)

        val isTextPlain = clipBoardManager?.primaryClipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) == true
        if (!isTextPlain) return@LaunchedEffect

        val clipBoardContent = clipBoardManager.getFirstTextPlain() ?: return@LaunchedEffect
        if (Regex("[0-9]{6}").matches(clipBoardContent)) {
            otpCode = clipBoardContent
            validateUserEmailViewModel.validationRequests(ValidationRequest(emailToValidate, otpCode))
        }
    }

    BackHandler { editTransfer() }

    HandleUnknownValidationError({ uiState }, snackbarHostState)

    ValidateUserEmailScreen(
        emailToValidate = emailToValidate,
        validateEmailWithOtpCode = { code ->
            validateUserEmailViewModel.validationRequests(ValidationRequest(emailToValidate, code))
        },
        resetErrorState = validateUserEmailViewModel.resetErrorReq,
        isLoading = { isLoading },
        isInvalidVerificationCode = { isInvalidVerificationCode },
        snackbarHostState = snackbarHostState,
        navigateBack = editTransfer,
        onResendEmailCode = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    context.getString(R.string.validateMailCodeResentFeedback, emailToValidate)
                )
            }
            validateUserEmailViewModel.resendEmailReq(emailToValidate)
        },
        otpCode = { otpCode },
        updateOtpCode = { code ->
            otpCode = code
        }
    )
}

@Composable
fun HandleUnknownValidationError(uiState: () -> ValidateEmailUiState, snackbarHostState: SnackbarHostState) {
    val context = LocalContext.current

    LaunchedEffect(uiState(), context) {
        when (uiState()) {
            ValidateEmailUiState.UnknownError -> {
                SentryLog.e("Email validation", "An unknown API error has occurred when validating the OTP code")
                snackbarHostState.showSnackbar(context.getString(R.string.validateMailUnknownError))
            }
            ValidateEmailUiState.NoNetwork -> {
                snackbarHostState.showSnackbar(context.getString(R.string.networkUnavailable))
            }
            ValidateEmailUiState.Default,
            ValidateEmailUiState.Loading,
            ValidateEmailUiState.InvalidVerificationCode -> Unit
        }
    }
}

@Composable
private fun ValidateUserEmailScreen(
    emailToValidate: String,
    validateEmailWithOtpCode: (String) -> Unit,
    resetErrorState: () -> Unit,
    isLoading: () -> Boolean,
    isInvalidVerificationCode: () -> Boolean,
    snackbarHostState: SnackbarHostState,
    navigateBack: () -> Unit,
    onResendEmailCode: () -> Unit,
    otpCode: () -> String,
    updateOtpCode: (String) -> Unit,
) {

    val context = LocalContext.current

    BottomStickyButtonScaffold(
        snackbarHostState = snackbarHostState,
        topBar = {
            SwissTransferTopAppBar(
                titleRes = R.string.transferTypeScreenTitle,
                navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
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
                text = stringResource(R.string.validateMailTitle),
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
                otpCode = otpCode,
                updateOtpCode = { code, isFilled ->
                    updateOtpCode(code)

                    if (isFilled) {
                        validateEmailWithOtpCode(code)
                    } else {
                        resetErrorState()
                    }
                },
                isLoading = isLoading,
                isInvalidVerificationCode = isInvalidVerificationCode,
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
        fun getCurrentLayoutStyle() = if (LocalWindowAdaptiveInfo.current.isWindowLarge()) {
            Centered
        } else {
            TopLeft
        }
    }
}

private fun ClipboardManager.getFirstTextPlain(): String? {
    val countItemInClipboard = primaryClip?.itemCount ?: return null

    (0..countItemInClipboard).forEach { item ->
        if (primaryClipDescription?.getMimeType(item) == ClipDescription.MIMETYPE_TEXT_PLAIN) {
            val text = primaryClip?.getItemAt(item)?.text.toString()
            if (text.isNotBlank()) return text
        }
    }

    return null
}

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
                isInvalidVerificationCode = { false },
                snackbarHostState = remember { SnackbarHostState() },
                navigateBack = {},
                onResendEmailCode = {},
                otpCode = { "" },
                updateOtpCode = {},
            )
        }
    }
}
