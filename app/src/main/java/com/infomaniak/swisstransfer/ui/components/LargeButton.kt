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

package com.infomaniak.swisstransfer.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import com.infomaniak.swisstransfer.ui.icons.app.Add
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.Shapes
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

private val LARGE_BUTTON_HEIGHT = 56.dp

@Composable
fun LargeButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    style: ButtonType = ButtonType.PRIMARY,
    enabled: Boolean = true,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    Button(
        modifier = modifier.height(LARGE_BUTTON_HEIGHT),
        colors = style.buttonColors(),
        shape = Shapes.medium,
        enabled = enabled,
        onClick = onClick,
    ) {
        imageVector?.let {
            Icon(modifier = Modifier.size(Margin.Medium), imageVector = it, contentDescription = null)
            Spacer(modifier = Modifier.width(Margin.Small))
        }
        Text(text = stringResource(id = titleRes), style = SwissTransferTheme.typography.bodyMedium)
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
    ERROR({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.error,
            contentColor = SwissTransferTheme.materialColors.onError,
        )
    }),
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun LargeButtonPreview() {
    SwissTransferTheme {
        Column(modifier = Modifier.background(SwissTransferTheme.materialColors.background)) {
            ButtonType.entries.forEach {
                LargeButton(
                    titleRes = R.string.appName,
                    style = it,
                    imageVector = AppIcons.Add,
                    onClick = {},
                )
                Spacer(modifier = Modifier.height(Margin.Small))
            }
        }
    }
}