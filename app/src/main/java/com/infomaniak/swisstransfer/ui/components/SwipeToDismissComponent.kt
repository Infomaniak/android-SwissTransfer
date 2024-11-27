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

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.ui.components.transfer.TransferItem
import com.infomaniak.swisstransfer.ui.previewparameter.TransferUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.theme.CustomShapes
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark

@Composable
fun SwipeToDismissComponent(
    contentShape: Shape,
    onSwiped: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    // Configuration
    val dismissThreshold = 0.5f
    val defaultColor = Color.LightGray
    val swipedColor = SwissTransferTheme.materialColors.error
    val minIconScale = 1.0f
    val maxIconScale = 1.5f
    val swipedElevation = 4.dp

    val state = rememberSwipeToDismissBoxState(
        // TODO: This block of code is commented for now, while we investigate the multiple triggers of dismiss state
        // confirmValueChange = { value ->
        //     val shouldDismiss = value == SwipeToDismissBoxValue.EndToStart
        //     // TODO: This check triggers twice, why ?? :(
        //     if (shouldDismiss) {
        //         Log.i("TOTO", "SwipeToDismiss state : $value")
        //         // TODO: We should probably add an animation here
        //         onSwiped()
        //     } else {
        //         Log.d("TOTO", "SwipeToDismiss state : $value")
        //     }
        //     shouldDismiss
        // },
        positionalThreshold = { totalDistance -> totalDistance * dismissThreshold },
    )

    // TODO: Sometimes, this check triggers several times in a row, why ?
    if (state.currentValue == SwipeToDismissBoxValue.EndToStart) {
        Log.i("TOTO", "SwipeToDismiss state : EndToStart")
        // TODO: We should probably add an animation here
        onSwiped()
    }

    // TODO: Red & Gray dark theme background colors need to be handled
    val backgroundColor by animateColorAsState(
        targetValue = if (state.targetValue == SwipeToDismissBoxValue.Settled) defaultColor else swipedColor,
        label = "Background color animation",
    )
    val iconScale by animateFloatAsState(
        targetValue = if (state.targetValue == SwipeToDismissBoxValue.Settled) minIconScale else maxIconScale,
        label = "Icon scale animation",
    )
    val contentElevation by animateDpAsState(
        targetValue = if (state.dismissDirection == SwipeToDismissBoxValue.EndToStart) swipedElevation else 0.dp,
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
                    // TODO: The dark theme icon color needs to be handled
                    Icon(
                        imageVector = Icons.Default.Delete,
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
