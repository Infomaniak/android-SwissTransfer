/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.infomaniak.swisstransfer.ui.components.transfer

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun SwipeToDismissComponent(
    shape: Shape,
    callback: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val defaultColor = Color.LightGray
    val swipedColor = SwissTransferTheme.materialColors.error
    val minIconScale = 1.0f
    val maxIconScale = 1.5f
    val swipedElevation = 4.dp

    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            Log.d("TOTO", "SwipeToDismissComponent - : ${value.name}")
            val shouldDismiss = value == SwipeToDismissBoxValue.EndToStart
            // TODO: We should probably add an animation here
            if (shouldDismiss) {
                // TODO: This check triggers twice, why ?? :(
                Log.e("TOTO", "SwipeToDismissComponent - DISMISS GOGOGO")
                callback()
            }
            shouldDismiss
        },
    )

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
                    .clip(shape)
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
            shape = shape,
            shadowElevation = contentElevation,
            content = { content() },
        )
    }
}
