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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItem
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Bin
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

private const val DISMISS_THRESHOLD = 0.5f
private const val MIN_ICON_SCALE = 1.0f
private const val MAX_ICON_SCALE = 1.5f
private const val SWIPED_ELEVATION = 4

@Composable
fun SwipeToDismissComponent(
    contentShape: Shape,
    onSwiped: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val state = rememberSwipeToDismissBoxState(
        positionalThreshold = { totalDistance -> totalDistance * DISMISS_THRESHOLD },
    )

    if (state.currentValue == EndToStart) onSwiped()

    val backgroundColor by animateColorAsState(
        targetValue = if (state.targetValue == EndToStart) {
            SwissTransferTheme.colors.swipeDelete
        } else {
            SwissTransferTheme.colors.swipeDefault
        },
        label = "Background color animation",
    )
    val iconScale by animateFloatAsState(
        targetValue = if (state.targetValue == EndToStart) MAX_ICON_SCALE else MIN_ICON_SCALE,
        label = "Icon scale animation",
    )
    val contentElevation by animateDpAsState(
        targetValue = if (state.dismissDirection == EndToStart) SWIPED_ELEVATION.dp else 0.dp,
        label = "Content elevation animation",
    )

    SwipeToDismissBox(
        state = state,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(contentShape)
                    .background(backgroundColor),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = Margin.Large),
                ) {
                    Icon(
                        imageVector = AppIcons.Bin,
                        tint = SwissTransferTheme.colors.swipeIcon,
                        modifier = Modifier.scale(iconScale),
                        contentDescription = null,
                    )
                }
            }
        }
    ) {
        Surface(
            shape = contentShape,
            shadowElevation = contentElevation,
            content = { content() },
        )
    }
}

@PreviewLightAndDark
@Composable
private fun Preview(@PreviewParameter(TransferUiListPreviewParameter::class) transfers: List<TransferUi>) {
    val contentShape = CustomShapes.SMALL
    SwissTransferTheme {
        Surface {
            SwipeToDismissComponent(contentShape = contentShape) {
                TransferItem(
                    transfer = transfers.first(),
                    shape = contentShape,
                    isSelected = { false },
                    onClick = {},
                )
            }
        }
    }
}
