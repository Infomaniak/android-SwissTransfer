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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
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
    @StringRes titleRes: Int,
    modifier: Modifier,
    buttonSize: ButtonSize,
    style: ButtonType,
    enabled: () -> Boolean,
    showIndeterminateProgress: () -> Boolean,
    progress: (() -> Float)?,
    onClick: () -> Unit,
    imageVector: ImageVector?,
) {
    val isEnabled by remember(progress) { derivedStateOf { enabled() && !showIndeterminateProgress() && progress == null } }
    val buttonColors = style.buttonColors()

    Button(
        modifier = modifier.height(buttonSize.height),
        colors = buttonColors,
        shape = CustomShapes.medium,
        enabled = isEnabled,
        onClick = onClick,
    ) {
        when {
            progress != null -> {
                val (progressColor, progressModifier) = getProgressSpecs(buttonColors)
                KeepButtonSize(imageVector, titleRes) {
                    CircularProgressIndicator(modifier = progressModifier, color = progressColor, progress = progress)
                }
            }
            showIndeterminateProgress() -> {
                val (progressColor, progressModifier) = getProgressSpecs(buttonColors)
                KeepButtonSize(imageVector, titleRes) {
                    CircularProgressIndicator(modifier = progressModifier, color = progressColor)
                }
            }
            else -> {
                ButtonTextContent(imageVector, titleRes)
            }
        }
    }
}

@Composable
fun KeepButtonSize(imageVector: ImageVector?, titleRes: Int, content: @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.alpha(0f)) {
            ButtonTextContent(imageVector, titleRes)
        }
        content()
    }
}

@Composable
private fun ButtonTextContent(imageVector: ImageVector?, titleRes: Int) {
    imageVector?.let {
        Icon(modifier = Modifier.size(Margin.Medium), imageVector = it, contentDescription = null)
        Spacer(modifier = Modifier.width(Margin.Small))
    }
    Text(text = stringResource(id = titleRes), style = SwissTransferTheme.typography.bodyMedium)
}

@Composable
private fun getProgressSpecs(buttonColors: ButtonColors): Pair<Color, Modifier> {
    val progressColor = buttonColors.disabledContentColor
    val progressModifier = Modifier
        .fillMaxHeight(0.8f)
        .aspectRatio(1f)
    return Pair(progressColor, progressModifier)
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
                        Spacer(modifier = Modifier.width(Margin.Small))
                        LargeButton(
                            titleRes = R.string.appName,
                            style = it,
                            progress = { 0.3f },
                            onClick = {},
                            imageVector = AppIcons.Add
                        )

                        Spacer(modifier = Modifier.width(Margin.Small))

                        SmallButton(titleRes = R.string.appName, style = it, imageVector = AppIcons.Add, onClick = {})
                        Spacer(modifier = Modifier.width(Margin.Small))
                        SmallButton(
                            titleRes = R.string.appName,
                            style = it,
                            progress = { 0.3f },
                            imageVector = AppIcons.Add,
                            onClick = {}
                        )
                    }
                    Spacer(modifier = Modifier.height(Margin.Medium))
                }
            }
        }
    }
}
