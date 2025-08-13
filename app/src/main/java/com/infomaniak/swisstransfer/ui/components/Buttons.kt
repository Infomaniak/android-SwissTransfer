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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.core.compose.basicbutton.BasicButton
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

/**
 * Specifying a progress has the priority over specifying showIndeterminateProgress
 */
@Composable
fun LargeButton(
    title: String,
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.Primary,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    CoreButton(
        title,
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
    title: String,
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.Primary,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    imageVector: ImageVector? = null,
) {
    CoreButton(
        title,
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
    title: String,
    modifier: Modifier,
    buttonSize: ButtonSize,
    style: ButtonType,
    enabled: () -> Boolean,
    showIndeterminateProgress: () -> Boolean,
    progress: (() -> Float)?,
    onClick: () -> Unit,
    imageVector: ImageVector?,
) {
    BasicButton(
        onClick = onClick,
        modifier = modifier.height(buttonSize.height),
        shape = CustomShapes.MEDIUM,
        colors = style.colors(),
        enabled = enabled,
        showIndeterminateProgress = showIndeterminateProgress,
        progress = progress,
        contentPadding = buttonSize.contentPadding,
    ) {
        ButtonTextContent(imageVector, title)
    }
}

@Composable
private fun ButtonTextContent(imageVector: ImageVector?, title: String) {
    imageVector?.let {
        Icon(modifier = Modifier.size(Dimens.SmallIconSize), imageVector = it, contentDescription = null)
        Spacer(Modifier.width(Margin.Mini))
    }
    Text(text = title, style = SwissTransferTheme.typography.bodyMedium)
}

private enum class ButtonSize(val height: Dp, val contentPadding: PaddingValues) {
    LARGE(Dimens.LargeButtonHeight, ButtonDefaults.ContentPadding),
    SMALL(40.dp, PaddingValues(horizontal = Margin.Medium)),
}

enum class ButtonType(val colors: @Composable () -> ButtonColors) {
    Primary({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.primary,
            contentColor = SwissTransferTheme.materialColors.onPrimary,
        )
    }),
    Secondary({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.colors.tertiaryButtonBackground,
            contentColor = SwissTransferTheme.materialColors.primary,
        )
    }),
    Tertiary({
        ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = SwissTransferTheme.materialColors.primary,
            disabledContainerColor = Color.Transparent,
        )
    }),
    Destructive({
        ButtonDefaults.buttonColors(
            containerColor = SwissTransferTheme.materialColors.error,
            contentColor = SwissTransferTheme.materialColors.onError,
        )
    }),
    DestructiveText({
        ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = SwissTransferTheme.materialColors.error,
            disabledContainerColor = Color.Transparent,
            // Alpha based on Material's `FilledButtonTokens.DisabledLabelTextOpacity`
            disabledContentColor = SwissTransferTheme.materialColors.error.copy(alpha = 0.38f),
        )
    });

    @Composable
    fun buttonColors() = colors.invoke()
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
                        LargeButton(title = "SwissTransfer", style = it, onClick = {}, imageVector = AppIcons.Add)
                        Spacer(Modifier.width(Margin.Mini))
                        LargeButton(
                            title = "SwissTransfer",
                            style = it,
                            progress = { 0.3f },
                            onClick = {},
                            imageVector = AppIcons.Add
                        )

                        Spacer(Modifier.width(Margin.Mini))

                        SmallButton(title = "SwissTransfer", style = it, imageVector = AppIcons.Add, onClick = {})
                        Spacer(Modifier.width(Margin.Mini))
                        SmallButton(
                            title = "SwissTransfer",
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
