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
fun GenericButton(
    modifier: Modifier = Modifier,
    style: ButtonType = ButtonType.PRIMARY,
    enabled: () -> Boolean = { true },
    showIndeterminateProgress: () -> Boolean = { false },
    progress: (() -> Float)? = null,
    onClick: () -> Unit,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable () -> Unit,
) {
    val isEnabled by remember(progress) { derivedStateOf { enabled() && !showIndeterminateProgress() && progress == null } }
    val buttonColors = style.buttonColors()

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
                val (progressColor, progressModifier) = getProgressSpecs(buttonColors)
                KeepButtonSize(content) {
                    CircularProgressIndicator(modifier = progressModifier, color = progressColor, progress = progress)
                }
            }
            showIndeterminateProgress() -> {
                val (progressColor, progressModifier) = getProgressSpecs(buttonColors)
                KeepButtonSize(content) {
                    CircularProgressIndicator(modifier = progressModifier, color = progressColor)
                }
            }
            else -> content()
        }
    }
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


@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ButtonType.entries.forEach { buttonType ->
                    Row {
                        GenericButton(
                            modifier = Modifier.height(40.dp),
                            style = buttonType,
                            onClick = {},
                            content = { Text("Click me!") },
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        GenericButton(
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
