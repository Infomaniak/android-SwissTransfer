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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.roundToInt

private enum class SwipeToDismissAnchors {
    Start,
    HalfStart,
    Center,
    HalfEnd,
    End,
}

@Composable
fun SwipeToDismissComponent(
    callback: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            Log.e("TOTO", "SwipeToDismissComponent - : ${dismissValue.name}")
            val shouldDismiss = dismissValue == SwipeToDismissBoxValue.EndToStart
            // TODO: We should probably add an animation here
            if (shouldDismiss) callback()
            shouldDismiss
        },
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            val color by animateColorAsState(
                if (dismissState.targetValue == SwipeToDismissBoxValue.Settled) Color.LightGray else Color.Red
                // when (dismissState.targetValue) {
                //     SwipeToDismissBoxValue.Settled -> Color.LightGray
                //     SwipeToDismissBoxValue.StartToEnd -> Color.Green
                //     SwipeToDismissBoxValue.EndToStart -> Color.Red
                // }
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
            )
        }
    ) {
        OutlinedCard(shape = RectangleShape) {
            ListItem(
                headlineContent = { Text("Cupcake") },
                supportingContent = { Text("Swipe me left or right!") }
            )
        }
    }

    // BoxWithConstraints {
    //
    //     val dismissThreshold = 0.5f
    //     val minIconScale = 1.0f
    //     val maxIconScale = 1.5f
    //
    //     val containerWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
    //     val state = rememberSaveable(saver = AnchoredDraggableState.Saver()) {
    //         AnchoredDraggableState(
    //             initialValue = SwipeToDismissAnchors.End,
    //         )
    //     }
    //
    //     LaunchedEffect(state.currentValue) {
    //         Log.i("TOTO", "SwipeToDismissComponent - state.currentValue: ${state.currentValue}")
    //         if (state.currentValue == SwipeToDismissAnchors.Start) {
    //             Log.e("TOTO", "SwipeToDismissComponent - START reached !")
    //         }
    //     }
    //
    //     val swipeProgress by remember {
    //         derivedStateOf {
    //             val progress = state.progress(from = SwipeToDismissAnchors.End, to = SwipeToDismissAnchors.Start)
    //             // if (toto > 0.5f) callback()
    //             Log.d("TOTO", "SwipeToDismissComponent | state: ${state.currentValue} | progress: ${progress}")
    //             progress
    //         }
    //     }
    //
    //     // TODO: Red & Gray dark theme background colors need to be handled
    //     val backgroundColor by animateColorAsState(
    //         targetValue = if (swipeProgress > dismissThreshold) SwissTransferTheme.materialColors.error else Color.LightGray,
    //         label = "Background color animation",
    //     )
    //     val iconScale by animateFloatAsState(
    //         targetValue = if (swipeProgress > dismissThreshold) {
    //             maxIconScale
    //         } else {
    //             minIconScale + (swipeProgress * 2.0f * (maxIconScale - minIconScale))
    //         },
    //         label = "Icon scaling animation",
    //     )
    //
    //     SideEffect {
    //         state.updateAnchors(
    //             newAnchors = DraggableAnchors {
    //                 SwipeToDismissAnchors.Start at -containerWidthPx
    //                 // SwipeToDismissAnchors.Center at -(containerWidthPx * dismissThreshold)
    //                 SwipeToDismissAnchors.End at 0.0f
    //             },
    //         )
    //     }
    //
    //     Box(
    //         modifier = Modifier
    //             .clip(shape = CustomShapes.SMALL)
    //             .background(backgroundColor),
    //     ) {
    //         Box(
    //             modifier = Modifier
    //                 .align(Alignment.CenterEnd)
    //                 .padding(end = Margin.Large),
    //         ) {
    //             // TODO: The dark theme icon color needs to be handled
    //             Icon(
    //                 imageVector = Icons.Default.Delete,
    //                 modifier = Modifier.scale(iconScale),
    //                 contentDescription = null,
    //             )
    //         }
    //
    //         Box(
    //             modifier = Modifier
    //                 .offset {
    //                     IntOffset(
    //                         x = state
    //                             .requireOffset()
    //                             .roundToInt(),
    //                         y = 0,
    //                     )
    //                 }
    //                 .anchoredDraggable(
    //                     state = state,
    //                     orientation = Orientation.Horizontal,
    //                     // flingBehavior = AnchoredDraggableDefaults.flingBehavior(
    //                     //     state = state,
    //                     //     positionalThreshold = { it * dismissThreshold },
    //                     // ),
    //                 ),
    //             content = { content() },
    //         )
    //     }
    // }
}

@Preview
@Composable
fun DraggableAnchorsSample() {
    var anchors by remember { mutableStateOf(DraggableAnchors<SwipeToDismissAnchors> {}) }
    var offset by rememberSaveable { mutableFloatStateOf(0f) }
    val thumbSize = 16.dp
    val thumbSizePx = with(LocalDensity.current) { thumbSize.toPx() }
    Box(
        Modifier
            .width(100.dp)
            // Our anchors depend on this box's size, so we obtain the size from onSizeChanged and
            // use updateAnchors to let the state know about the new anchors
            .onSizeChanged { layoutSize ->
                anchors = DraggableAnchors {
                    SwipeToDismissAnchors.Start at 0f
                    SwipeToDismissAnchors.End at layoutSize.width - thumbSizePx
                }
            }
            .border(2.dp, Color.Black)
    ) {
        Box(
            Modifier
                .size(thumbSize)
                .offset { IntOffset(x = offset.roundToInt(), y = 0) }
                .draggable(
                    state =
                    rememberDraggableState { delta ->
                        offset =
                            (offset + delta).coerceIn(
                                anchors.minPosition(),
                                anchors.maxPosition()
                            )
                    },
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        val closestAnchor = anchors.positionOf(anchors.closestAnchor(offset)!!)
                        animate(offset, closestAnchor, velocity) { value, _ -> offset = value }
                    }
                )
                .background(Color.Red)
        )
    }
}

@Composable
fun AnchoredDraggableCustomAnchoredSample() {
    @Suppress("unused")
    // Using AnchoredDraggableState's anchoredDrag APIs, we can build a custom animation
    suspend fun <T> AnchoredDraggableState<T>.customAnimation(
        target: T,
        snapAnimationSpec: AnimationSpec<Float>,
        velocity: Float = lastVelocity,
    ) {
        anchoredDrag(target) { latestAnchors, latestTarget ->
            // If the anchors change while this block is suspending, it will get cancelled and
            // restarted with the latest anchors and latest target
            val targetOffset = latestAnchors.positionOf(latestTarget)
            if (!targetOffset.isNaN()) {
                animate(
                    initialValue = offset,
                    initialVelocity = velocity,
                    targetValue = targetOffset,
                    animationSpec = snapAnimationSpec
                ) { value, velocity ->
                    dragTo(value, velocity)
                }
            }
        }
    }
}

@Preview
@Composable
fun AnchoredDraggableLayoutDependentAnchorsSample() {
    val state =
        rememberSaveable(saver = AnchoredDraggableState.Saver()) {
            AnchoredDraggableState(initialValue = SwipeToDismissAnchors.Center)
        }
    val draggableSize = 60.dp
    val draggableSizePx = with(LocalDensity.current) { draggableSize.toPx() }
    Box(
        Modifier
            .fillMaxWidth()
            // Our anchors depend on this box's size, so we obtain the size from onSizeChanged and
            // use updateAnchors to let the state know about the new anchors
            .onSizeChanged { layoutSize ->
                val dragEndPoint = layoutSize.width - draggableSizePx
                state.updateAnchors(
                    DraggableAnchors {
                        SwipeToDismissAnchors.Start at 0f
                        SwipeToDismissAnchors.HalfStart at dragEndPoint * .25f
                        SwipeToDismissAnchors.Center at dragEndPoint * .5f
                        SwipeToDismissAnchors.HalfEnd at dragEndPoint * .75f
                        SwipeToDismissAnchors.End at dragEndPoint
                    }
                )
            }
            .visualizeDraggableAnchors(state, Orientation.Horizontal)
    ) {
        Box(
            Modifier
                .size(draggableSize)
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(), y = 0
                    )
                }
                .anchoredDraggable(state = state, orientation = Orientation.Horizontal)
                .background(Color.Red)
        )
    }
}

@Preview
@Composable
fun AnchoredDraggableWithOverscrollSample() {
    val state =
        rememberSaveable(saver = AnchoredDraggableState.Saver()) {
            AnchoredDraggableState(initialValue = SwipeToDismissAnchors.Center)
        }
    val draggableSize = 80.dp
    val draggableSizePx = with(LocalDensity.current) { draggableSize.toPx() }
    val overscrollEffect = rememberOverscrollEffect()

    Box(
        Modifier
            .fillMaxWidth()
            .onSizeChanged { layoutSize ->
                val dragEndPoint = layoutSize.width - draggableSizePx
                state.updateAnchors(
                    DraggableAnchors {
                        SwipeToDismissAnchors.Start at 0f
                        SwipeToDismissAnchors.Center at dragEndPoint / 2f
                        SwipeToDismissAnchors.End at dragEndPoint
                    }
                )
            }
    ) {
        Box(
            Modifier
                .size(draggableSize)
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(), y = 0
                    )
                }
                // pass the overscrollEffect to AnchoredDraggable
                .anchoredDraggable(
                    state,
                    Orientation.Horizontal,
                    overscrollEffect = overscrollEffect
                )
                .overscroll(overscrollEffect)
                .background(Color.Red)
        )
    }
}

@Preview
@Composable
fun AnchoredDraggableProgressSample() {
    val state =
        rememberSaveable(saver = AnchoredDraggableState.Saver()) {
            AnchoredDraggableState(initialValue = SwipeToDismissAnchors.Center)
        }
    val draggableSize = 60.dp
    val draggableSizePx = with(LocalDensity.current) { draggableSize.toPx() }
    Column(
        Modifier
            .fillMaxWidth()
            // Our anchors depend on this box's size, so we obtain the size from onSizeChanged and
            // use updateAnchors to let the state know about the new anchors
            .onSizeChanged { layoutSize ->
                val dragEndPoint = layoutSize.width - draggableSizePx
                state.updateAnchors(
                    DraggableAnchors {
                        SwipeToDismissAnchors.Start at 0f
                        SwipeToDismissAnchors.Center at dragEndPoint * .5f
                        SwipeToDismissAnchors.End at dragEndPoint
                    }
                )
            }
    ) {
        // Read progress in a snapshot-backed context to receive updates. This could be e.g. a
        //  derived state, snapshotFlow or other snapshot-aware context like the graphicsLayer
        //  block.
        val centerToStartProgress by remember {
            derivedStateOf {
                state.progress(
                    from = SwipeToDismissAnchors.Center,
                    to = SwipeToDismissAnchors.Start,
                )
            }
        }
        val centerToEndProgress by remember {
            derivedStateOf {
                state.progress(
                    from = SwipeToDismissAnchors.Center,
                    to = SwipeToDismissAnchors.End,
                )
            }
        }
        Box {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(draggableSize)
                    .graphicsLayer { alpha = max(centerToStartProgress, centerToEndProgress) }
                    .background(Color.Black)
            )
            Box(
                Modifier
                    .size(draggableSize)
                    .offset {
                        IntOffset(
                            x = state
                                .requireOffset()
                                .roundToInt(), y = 0
                        )
                    }
                    .anchoredDraggable(state, Orientation.Horizontal)
                    .background(Color.Red)
            )
        }
    }
}

@Preview
@Composable
fun AnchoredDraggableDynamicAnchorsSample() {
    val open = "Open"
    val closed = "Closed"

    @Composable
    fun DrawerLayout(
        state: AnchoredDraggableState<String>,
        activePositions: List<String> = listOf(open, closed),
        modifier: Modifier = Modifier,
        drawerContent: @Composable () -> Unit,
        content: @Composable () -> Unit
    ) {
        Box(modifier) {
            Box(Modifier.anchoredDraggable(state, Orientation.Horizontal)) { content() }
            Box(
                Modifier
                    .onSizeChanged { measuredSize ->
                        state.updateAnchors(
                            DraggableAnchors {
                                if (closed in activePositions) {
                                    closed at -measuredSize.width.toFloat()
                                }
                                if (open in activePositions) {
                                    open at 0f
                                }
                            }
                        )
                    }
                    .offset {
                        IntOffset(
                            x = state
                                .requireOffset()
                                .roundToInt(), y = 0
                        )
                    }
            ) {
                drawerContent()
            }
        }
    }

    val state =
        rememberSaveable(saver = AnchoredDraggableState.Saver()) {
            AnchoredDraggableState(initialValue = closed)
        }
    val activePositions = remember { mutableStateListOf(open, closed) }
    DrawerLayout(
        state,
        activePositions,
        drawerContent = {
            Button(
                onClick = {
                    if (closed in activePositions) {
                        activePositions.remove(closed)
                    } else {
                        activePositions.add(closed)
                    }
                }
            ) {
                val text =
                    if (closed in activePositions) {
                        "Click to disallow closing drawer"
                    } else {
                        "Click to allow closing"
                    }
                Text(text)
            }
        },
    ) {
        Text("Swipe to expand Drawer")
    }
}

/**
 * A [Modifier] that visualizes the anchors attached to an [AnchoredDraggableState] as lines along
 * the cross axis of the layout (start to end for [Orientation.Vertical], top to end for
 * [Orientation.Horizontal]). This is useful to debug components with a complex set of anchors, or
 * for AnchoredDraggable development.
 *
 * @param state The state whose anchors to visualize
 * @param orientation The orientation of the [anchoredDraggable]
 * @param lineColor The color of the visualization lines
 * @param lineStrokeWidth The stroke width of the visualization lines
 * @param linePathEffect The path effect used to draw the visualization lines
 */
private fun Modifier.visualizeDraggableAnchors(
    state: AnchoredDraggableState<*>,
    orientation: Orientation,
    lineColor: Color = Color.Black,
    lineStrokeWidth: Float = 10f,
    linePathEffect: PathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 30f))
) = drawWithContent {
    drawContent()
    state.anchors.forEach { _, position ->
        val startOffset =
            Offset(
                x = if (orientation == Orientation.Horizontal) position else 0f,
                y = if (orientation == Orientation.Vertical) position else 0f
            )
        val endOffset =
            Offset(
                x = if (orientation == Orientation.Horizontal) startOffset.x else size.height,
                y = if (orientation == Orientation.Vertical) startOffset.y else size.width
            )
        drawLine(
            color = lineColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineStrokeWidth,
            pathEffect = linePathEffect
        )
    }
}
