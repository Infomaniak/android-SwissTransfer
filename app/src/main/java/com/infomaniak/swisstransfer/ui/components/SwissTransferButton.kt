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
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

/**
 * Most basic and customizable button component.
 *
 * Only needed for exceptional edge cases where [LargeButton] or [SmallButton] are not enough and we need more control over the
 * button component.
 *
 * Specifying a progress has the priority over specifying showIndeterminateProgress.
 */
@Composable
fun SwissTransferButton(
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.Primary,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable () -> Unit,
) {
    val isProgressing by remember(progress) { derivedStateOf { showIndeterminateProgress() || progress != null } }
    val isEnabled = enabled() && isProgressing.not()

    val buttonColors = style.buttonColors().let { colors -> if (isProgressing) colors.applyEnabledColorsToDisabled() else colors }
    val progressColor = style.loaderColors()

    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = CustomShapes.MEDIUM,
        enabled = isEnabled,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        when {
            progress != null -> {
                KeepButtonSize(content) {
                    CircularProgressIndicator(
                        modifier = getProgressModifier(),
                        color = progressColor.progressColor,
                        trackColor = progressColor.trackColor,
                        progress = progress
                    )
                }
            }
            showIndeterminateProgress() -> {
                KeepButtonSize(content) {
                    CircularProgressIndicator(
                        modifier = getProgressModifier(),
                        color = progressColor.progressColor,
                        trackColor = progressColor.trackColor,
                    )
                }
            }
            else -> content()
        }
    }
}

private fun ButtonColors.applyEnabledColorsToDisabled(): ButtonColors {
    return copy(disabledContainerColor = containerColor, disabledContentColor = contentColor)
}

@Composable
private fun KeepButtonSize(targetSizeContent: @Composable () -> Unit, content: @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.alpha(0f)) {
            targetSizeContent()
        }
        content()
    }
}

@Composable
private fun getProgressModifier(): Modifier {
    val progressModifier = Modifier
        .fillMaxHeight(0.8f)
        .aspectRatio(1f)
    return progressModifier
}

enum class ButtonType(private val colors: @Composable () -> SwissTransferButtonColors) {
    Primary({
        SwissTransferButtonColors(
            containerColor = SwissTransferTheme.materialColors.primary,
            contentColor = SwissTransferTheme.materialColors.onPrimary,
        )
    }),
    Secondary({
        SwissTransferButtonColors(
            containerColor = SwissTransferTheme.colors.tertiaryButtonBackground,
            contentColor = SwissTransferTheme.materialColors.primary,
        )
    }),
    Tertiary({
        SwissTransferButtonColors(
            containerColor = Color.Transparent,
            contentColor = SwissTransferTheme.materialColors.primary,
            disabledContainerColor = Color.Transparent,
        )
    }),
    Destructive({
        SwissTransferButtonColors(
            containerColor = SwissTransferTheme.materialColors.error,
            contentColor = SwissTransferTheme.materialColors.onError,
        )
    }),
    DestructiveText({
        SwissTransferButtonColors(
            containerColor = Color.Transparent,
            contentColor = SwissTransferTheme.materialColors.error,
            disabledContainerColor = Color.Transparent,
            // Alpha based on Material's `FilledButtonTokens.DisabledLabelTextOpacity`
            disabledContentColor = SwissTransferTheme.materialColors.error.copy(alpha = 0.38f),
        )
    });

    @Composable
    fun buttonColors() = colors.invoke().buttonColors()

    @Composable
    fun loaderColors() = colors.invoke().loaderColors()
}

data class SwissTransferButtonColors(
    val containerColor: Color = Color.Unspecified,
    val contentColor: Color = Color.Unspecified,
    val disabledContainerColor: Color = Color.Unspecified,
    val disabledContentColor: Color = Color.Unspecified,
) {
    @Composable
    fun buttonColors(): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    fun loaderColors(): LoaderColors = LoaderColors(
        progressColor = contentColor,
        trackColor = contentColor.copy(alpha = TRACK_COLOR_ALPHA),
    )

    companion object {
        const val TRACK_COLOR_ALPHA = 0.3f
    }
}

data class LoaderColors(val progressColor: Color, val trackColor: Color)

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ButtonType.entries.forEach { buttonType ->
                    Row(horizontalArrangement = Arrangement.spacedBy(Margin.Small)) {
                        SwissTransferButton(
                            modifier = Modifier.height(40.dp),
                            style = buttonType,
                            onClick = {},
                            content = { Text("Click me!") },
                        )
                        SwissTransferButton(
                            modifier = Modifier.height(40.dp),
                            style = buttonType,
                            onClick = {},
                            content = { Text("Click me!") },
                            enabled = { false },
                        )
                        SwissTransferButton(
                            modifier = Modifier.height(40.dp),
                            style = buttonType,
                            onClick = {},
                            progress = { 0.8f },
                            content = { Text("Click me!") },
                        )
                    }
                }
            }
        }
    }
}
