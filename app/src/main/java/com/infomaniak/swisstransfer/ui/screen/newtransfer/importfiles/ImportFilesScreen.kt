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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.*
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.icons.illu.ArrowCurvedDownright
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ImportFilesScreen() {
    var showImportChoiceBottomSheet by remember { mutableStateOf(false) }

    BottomStickyButtonScaffold(
        topBar = { SwissTransferTobAppBar() },
        topButton = { modifier ->
            LargeButton(
                modifier = modifier,
                titleRes = R.string.buttonAddFiles,
                imageVector = AppIcons.Add,
                style = ButtonType.TERTIARY,
                onClick = { showImportChoiceBottomSheet = true },
            )
        },
        bottomButton = { modifier ->
            LargeButton(
                modifier = modifier,
                titleRes = R.string.buttonNext,
                onClick = { /*TODO*/ },
            )
        },
    ) {
        Column {
            ImportChoiceBottomSheet(
                showImportChoiceBottomSheet = { showImportChoiceBottomSheet },
                onDismissRequest = { showImportChoiceBottomSheet = false },
            )
        }
    }
}

@Composable
private fun ImportChoiceBottomSheet(
    showImportChoiceBottomSheet: () -> Boolean,
    onDismissRequest: () -> Unit,
) {
    if (showImportChoiceBottomSheet()) {
        SwissTransferBottomSheet(
            onDismissRequest = onDismissRequest,
            imageVector = AppIcons.Illu.ArrowCurvedDownright,
            titleRes = R.string.appName,
            descriptionRes = R.string.sentEmptyTitle,
            content = {
                Surface(
                    modifier = Modifier.size(200.dp),
                    color = Color.Gray,
                ) {}
            },
            topButton = {
                LargeButton(
                    modifier = it,
                    titleRes = R.string.appName,
                    style = ButtonType.ERROR,
                    onClick = { /*TODO*/ },
                )
            },
            bottomButton = {
                LargeButton(
                    modifier = it,
                    titleRes = R.string.appName,
                    style = ButtonType.TERTIARY,
                    onClick = { /*TODO*/ },
                )
            },
        )
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun ImportFilesScreenPreview() {
    SwissTransferTheme {
        ImportFilesScreen()
    }
}

@PreviewMobile
@PreviewTablet
@Composable
private fun ImportChoiceBottomSheetPreview() {
    SwissTransferTheme {
        Surface {
            ImportChoiceBottomSheet({ true }, {})
        }
    }
}
