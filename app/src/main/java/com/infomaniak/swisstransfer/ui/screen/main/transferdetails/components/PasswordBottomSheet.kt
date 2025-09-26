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
package com.infomaniak.swisstransfer.ui.screen.main.transferdetails.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.ButtonType
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.components.SwissTransferTextField
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.DocumentOnDocument
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.copyText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordBottomSheet(isVisible: () -> Boolean, transferPassword: () -> String?, closeBottomSheet: () -> Unit) {

    val password = transferPassword()

    if (!isVisible() || password == null) return

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    SwissTransferBottomSheet(
        onDismissRequest = closeBottomSheet,
        topButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.sharePasswordButton),
                imageVector = AppIcons.DocumentOnDocument,
                onClick = {
                    context.copyText(
                        text = password,
                        showSnackbar = { scope.launch { snackbarHostState.showSnackbar(it) } },
                    )
                    closeBottomSheet()
                },
            )
        },
        bottomButton = {
            LargeButton(
                modifier = it,
                title = stringResource(R.string.contentDescriptionButtonClose),
                style = ButtonType.Tertiary,
                onClick = closeBottomSheet,
            )
        },
        title = stringResource(R.string.sharePasswordTitle),
        description = stringResource(R.string.sharePasswordDescription),
    ) {
        SwissTransferTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.Medium),
            initialValue = password,
            isPassword = true,
            isReadOnly = true,
        )
    }
}

@PreviewAllWindows
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            PasswordBottomSheet(
                isVisible = { true },
                transferPassword = { "toto42" },
                closeBottomSheet = {},
            )
        }
    }
}
