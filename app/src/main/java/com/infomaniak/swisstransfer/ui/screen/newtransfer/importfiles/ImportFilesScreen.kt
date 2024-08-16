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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.BottomStickyButtonScaffold
import com.infomaniak.swisstransfer.ui.components.SwissTransferTobAppBar
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewMobile
import com.infomaniak.swisstransfer.ui.utils.PreviewTablet

@Composable
fun ImportFilesScreen() {
    Scaffold(
        topBar = { SwissTransferTobAppBar() },
    ) { contentPaddings ->
        BottomStickyButtonScaffold(
            modifier = Modifier.padding(contentPaddings),
            topButton = { modifier ->
                LargeButton(
                    modifier = modifier,
                    titleRes = R.string.appName,
                    imageVector = AppIcons.Add,
                    style = ButtonType.TERTIARY,
                    onClick = {},
                )
            },
            bottomButton = { modifier ->
                LargeButton(
                    modifier = modifier,
                    titleRes = R.string.appName,
                    style = ButtonType.PRIMARY,
                    onClick = {},
                )
            },
        ) {
            Column {
                Text("ImportFilesScreen")
            }
        }
    }
}

@Composable
private fun LargeButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    style: ButtonType,
    enabled: Boolean = true,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    Button(
        modifier = modifier.height(56.dp),
        colors = style.buttonColors(),
        shape = Shapes.medium,
        enabled = enabled,
        onClick = onClick,
    ) {
        imageVector?.let {
            Icon(modifier = Modifier.size(Margin.Medium), imageVector = it, contentDescription = null)
            Spacer(modifier = Modifier.width(Margin.Small))
        }
        Text(text = stringResource(id = titleRes))
    }
}

enum class ButtonType(val buttonColors: @Composable () -> ButtonColors) {
    PRIMARY({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.primary,
            contentColor = SwissTransferTheme.materialColors.onPrimary,
        )
    }),
    SECONDARY({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.colors.tertiaryButtonBackground,
            contentColor = SwissTransferTheme.materialColors.primary,
        )
    }),
    TERTIARY({
        ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = SwissTransferTheme.materialColors.primary,
        )
    }),
}

@PreviewMobile
@PreviewTablet
@Composable
private fun ImportFilesScreenPreview() {
    SwissTransferTheme {
        ImportFilesScreen()
    }
}
