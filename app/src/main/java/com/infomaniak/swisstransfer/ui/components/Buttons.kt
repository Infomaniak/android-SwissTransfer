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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

/**
 * Specifying a progress has the priority over specifying showIndeterminateProgress
 */
@Composable
fun LargeButton(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.PRIMARY,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    CoreButton(
        titleRes,
        modifier,
        ButtonSize.LARGE,
        style,
        enabled,
        showIndeterminateProgress,
        progress,
        onClick,
        imageVector,
    )
}

/**
 * Specifying a progress has the priority over specifying showIndeterminateProgress
 */
@Composable
fun SmallButton(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.PRIMARY,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    CoreButton(
        titleRes,
        modifier,
        ButtonSize.SMALL,
        style,
        enabled,
        showIndeterminateProgress,
        progress,
        onClick,
        imageVector,
    )
}

@Composable
private fun CoreButton(
    titleRes: Int,
    modifier: Modifier,
    buttonSize: ButtonSize,
    style: ButtonType,
    enabled: () -> Boolean,
    showIndeterminateProgress: () -> Boolean,
    progress: (() -> Float)?,
    onClick: () -> Unit,
    imageVector: ImageVector?,
) {
    SwissTransferButton(
        modifier.height(buttonSize.height),
        style,
        enabled,
        showIndeterminateProgress,
        progress,
        onClick,
    ) {
        ButtonTextContent(imageVector, titleRes)
    }
}

@Composable
private fun ButtonTextContent(imageVector: ImageVector?, titleRes: Int) {
    imageVector?.let {
        Icon(modifier = Modifier.size(Dimens.SmallIconSize), imageVector = it, contentDescription = null)
        Spacer(Modifier.width(Margin.Mini))
    }
    Text(text = stringResource(id = titleRes), style = SwissTransferTheme.typography.bodyMedium)
}

private enum class ButtonSize(val height: Dp) {
    LARGE(Dimens.LargeButtonHeight),
    SMALL(40.dp),
}

@Preview(name = "Light", widthDp = 800)
@Preview(name = "Dark", widthDp = 800, uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun LargeButtonPreview() {
    SwissTransferTheme {
        Surface {
            Column {
                ButtonType.entries.forEach {
                    Row {
                        LargeButton(titleRes = R.string.appName, style = it, onClick = {}, imageVector = AppIcons.Add)
                        Spacer(Modifier.width(Margin.Mini))
                        LargeButton(
                            titleRes = R.string.appName,
                            style = it,
                            progress = { 0.3f },
                            onClick = {},
                            imageVector = AppIcons.Add
                        )

                        Spacer(Modifier.width(Margin.Mini))

                        SmallButton(titleRes = R.string.appName, style = it, imageVector = AppIcons.Add, onClick = {})
                        Spacer(Modifier.width(Margin.Mini))
                        SmallButton(
                            titleRes = R.string.appName,
                            style = it,
                            progress = { 0.3f },
                            imageVector = AppIcons.Add,
                            onClick = {}
                        )
                    }
                    Spacer(Modifier.height(Margin.Medium))
                }
            }
        }
    }
}
