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

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.*
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.*
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.*
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private val backBehavior = BackNavigationBehavior.PopUntilContentChange

internal fun Modifier.animatedPane(): Modifier {
    return this.then(AnimatedPaneElement)
}

private object AnimatedPaneElement : ModifierNodeElement<AnimatedPaneNode>() {
    private val inspectorInfo = debugInspectorInfo {
        name = "isPaneComposable"
        value = true
    }

    override fun create(): AnimatedPaneNode {
        return AnimatedPaneNode()
    }

    override fun update(node: AnimatedPaneNode) {}

    override fun InspectorInfo.inspectableProperties() {
        inspectorInfo()
    }

    override fun hashCode(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        return (other is AnimatedPaneElement)
    }
}

private class AnimatedPaneNode : ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?) =
        ((parentData as? PaneScaffoldParentData) ?: PaneScaffoldParentData()).also {
            it.isAnimatedPane = true
        }
}

internal data class PaneScaffoldParentData(
    var preferredWidth: Float? = null,
    var isAnimatedPane: Boolean = false
)

private data class AnimateBoundsElement(
    private val animateFraction: () -> Float,
    private val sizeAnimationSpec: FiniteAnimationSpec<IntSize>,
    private val positionAnimationSpec: FiniteAnimationSpec<IntOffset>,
    private val lookaheadScope: LookaheadScope
) : ModifierNodeElement<AnimateBoundsNode>() {
    private val inspectorInfo = debugInspectorInfo {
        name = "animateBounds"
        properties["animateFraction"] = animateFraction
        properties["sizeAnimationSpec"] = sizeAnimationSpec
        properties["positionAnimationSpec"] = positionAnimationSpec
        properties["lookaheadScope"] = lookaheadScope
    }

    override fun create(): AnimateBoundsNode {
        return AnimateBoundsNode(
            animateFraction,
            sizeAnimationSpec,
            positionAnimationSpec,
            lookaheadScope,
        )
    }

    override fun update(node: AnimateBoundsNode) {
        node.animateFraction = animateFraction
        node.sizeAnimationSpec = sizeAnimationSpec
        node.positionAnimationSpec = positionAnimationSpec
        node.lookaheadScope = lookaheadScope
    }

    override fun InspectorInfo.inspectableProperties() {
        inspectorInfo()
    }
}

private class AnimateBoundsNode(
    var animateFraction: () -> Float,
    sizeAnimationSpec: FiniteAnimationSpec<IntSize>,
    positionAnimationSpec: FiniteAnimationSpec<IntOffset>,
    var lookaheadScope: LookaheadScope,
) : ApproachLayoutModifierNode, Modifier.Node() {
    val sizeTracker = SizeTracker(sizeAnimationSpec)
    val positionTracker = PositionTracker(positionAnimationSpec)

    var sizeAnimationSpec
        set(value) {
            sizeTracker.animationSpec = value
        }
        get() = sizeTracker.animationSpec

    var positionAnimationSpec
        set(value) {
            positionTracker.animationSpec = value
        }
        get() = positionTracker.animationSpec

    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean =
        animateFraction() != 1f

    override fun Placeable.PlacementScope.isPlacementApproachInProgress(
        lookaheadCoordinates: LayoutCoordinates
    ) = animateFraction() != 1f

    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        // When layout changes, the lookahead pass will calculate a new final size for the
        // child modifier. This lookahead size can be used to animate the size
        // change, such that the animation starts from the current size and gradually
        // change towards `lookaheadSize`.
        sizeTracker.updateTargetSize(lookaheadSize)
        val (width, height) = sizeTracker.updateAndGetCurrentSize(animateFraction())
        // Creates a fixed set of constraints using the animated size
        val animatedConstraints = Constraints.fixed(width, height)
        // Measure child/children with animated constraints.
        val placeable = measurable.measure(animatedConstraints)
        return layout(placeable.width, placeable.height) {
            coordinates?.let {
                positionTracker.updateTargetOffset(
                    with(lookaheadScope) {
                        lookaheadScopeCoordinates.localLookaheadPositionOf(it).toIntOffset()
                    }
                )
                placeable.place(
                    with(lookaheadScope) {
                        positionTracker.updateAndGetCurrentOffset(animateFraction()) -
                                lookaheadScopeCoordinates.localPositionOf(it, Offset.Zero).toIntOffset()
                    }
                )
            }
        }
    }
}

private class SizeTracker(var animationSpec: FiniteAnimationSpec<IntSize>) {
    private var originalSize: IntSize = InvalidIntSize
    private var targetSize: IntSize = InvalidIntSize
    private var currentSize = InvalidIntSize
    private lateinit var animation: TargetBasedAnimation<IntSize, AnimationVector2D>

    fun updateTargetSize(newSize: IntSize) {
        if (targetSize == newSize) {
            return
        }
        // TODO(conradchen): Handle the interruption better when the target size changes during
        //                   the animation
        originalSize =
            if (currentSize != InvalidIntSize) {
                currentSize
            } else {
                newSize
            }
        targetSize = newSize
        animation =
            TargetBasedAnimation(animationSpec, IntSize.VectorConverter, originalSize, targetSize)
    }

    fun updateAndGetCurrentSize(fraction: Float): IntSize {
        currentSize = animation.getValueFromNanos((animation.durationNanos * fraction).toLong())
        return currentSize
    }
}

private class PositionTracker(var animationSpec: FiniteAnimationSpec<IntOffset>) {
    private var originalOffset: IntOffset? = null
    private var targetOffset: IntOffset? = null
    private var currentOffset: IntOffset? = null
    private lateinit var animation: TargetBasedAnimation<IntOffset, AnimationVector2D>

    fun updateTargetOffset(newOffset: IntOffset) {
        if (targetOffset == newOffset) {
            return
        }
        // TODO(conradchen): Handle the interruption better when the target position changes during
        //                   the animation
        originalOffset =
            if (currentOffset != null) {
                currentOffset
            } else {
                newOffset
            }
        targetOffset = newOffset
        animation =
            TargetBasedAnimation(
                animationSpec,
                IntOffset.VectorConverter,
                originalOffset!!,
                targetOffset!!
            )
    }

    fun updateAndGetCurrentOffset(fraction: Float): IntOffset {
        currentOffset = animation.getValueFromNanos((animation.durationNanos * fraction).toLong())
        return currentOffset!!
    }
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

private val InvalidIntSize = IntSize(-1, -1)

fun Modifier.animateBounds(
    animateFraction: () -> Float,
    sizeAnimationSpec: FiniteAnimationSpec<IntSize>,
    positionAnimationSpec: FiniteAnimationSpec<IntOffset>,
    lookaheadScope: LookaheadScope,
    enabled: Boolean
) =
    if (enabled) {
        this.then(
            AnimateBoundsElement(
                animateFraction,
                sizeAnimationSpec,
                positionAnimationSpec,
                lookaheadScope
            )
        )
    } else {
        this
    }

/**
 * A composable function that sets up a List-Detail interface using a three-pane scaffold navigator.
 * This function adapts its layout based on the given window size class and manages the navigation
 * between list and detail panes.
 * @param T the type representing the item displayed in the detail pane. This can be an item id or a whole instance.
 * This type must be storable in a Bundle. If this customization is unneeded, you can pass [Nothing]
 * @param windowAdaptiveInfo An instance of [WindowAdaptiveInfo] that provides information about
 * the current window's size class and adaptive state.
 * @param listPane A composable function that describes the content of the list pane.
 * @param detailPane A composable function that describes the content of the detail pane.
 * @param modifier [Modifier] of the scaffold layout.
 *
 * @sample com.infomaniak.swisstransfer.ui.screen.main.settings.SettingsScreenWrapper
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> TwoPaneScaffold(
    listPane: @Composable ThreePaneScaffoldNavigator<T>.() -> Unit,
    detailPane: @Composable ThreePaneScaffoldNavigator<T>.() -> Unit,
    modifier: Modifier = Modifier,
) {
    val windowAdaptiveInfo = LocalWindowAdaptiveInfo.current
    val paneScaffoldDirective = calculatePaneScaffoldDirective(windowAdaptiveInfo)
    val maxHorizontalPartitions = if (windowAdaptiveInfo.isWindowLarge()) 2 else 1
    val navigator = rememberListDetailPaneScaffoldNavigator<T>(
        scaffoldDirective = paneScaffoldDirective.copy(
            maxHorizontalPartitions = maxHorizontalPartitions,
            horizontalPartitionSpacerSize = 0.dp,
        ),
        initialDestinationHistory = listOf(
            ThreePaneScaffoldDestinationItem(ListDetailPaneScaffoldRole.List),
        )
    )

    BackHandler(navigator.canPopBackStack()) { navigator.popBackStack() }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            // AnimatedPane {
            //
            // }

            ////////////////////////////////////////////////////////////////////////////////
            val keepShowing = true
            // val animateFraction = if (transitionState.currentState == transitionState.targetState) {
            //     1f
            // } else {
            //     transitionState.fraction
            // }
            val animateFraction = { 1f }
            scaffoldStateTransition.AnimatedVisibility(
                visible = { value: ThreePaneScaffoldValue -> value[role] != PaneAdaptedValue.Hidden },
                modifier =
                modifier
                    .animatedPane()
                    .animateBounds(
                        animateFraction = animateFraction,
                        positionAnimationSpec = positionAnimationSpec,
                        sizeAnimationSpec = sizeAnimationSpec,
                        lookaheadScope = this,
                        enabled = keepShowing
                    )
                    .then(if (keepShowing) Modifier else Modifier.clipToBounds()),
                enter = slideInHorizontally(),
                exit = slideOutHorizontally(),
            ) {
                navigator.listPane()
            }

            ////////////////////////////////////////////////////////////////////////////////
            // scaffoldStateTransition.AnimatedVisibility(
            //     visible = { value: ThreePaneScaffoldValue -> value[role] != PaneAdaptedValue.Hidden },
            //     enter = slideInHorizontally(),
            //     exit = slideOutHorizontally(),
            // ) {
            //     navigator.listPane()
            // }
        },
        detailPane = {
            scaffoldStateTransition.AnimatedVisibility(
                visible = { value: ThreePaneScaffoldValue -> value[role] != PaneAdaptedValue.Hidden },
                enter = slideInHorizontally(initialOffsetX = { it / 2 }),
                exit = slideOutHorizontally(targetOffsetX = { it / 2 }),
            ) {
                navigator.detailPane()
            }
        },
        modifier = modifier,
    )
}

/**
 * Keep the DetailPane's content in memory so that it's always available while navigating back.
 * When leaving the DetailPane, its content becomes `null`, which creates a visual glitch in the back animation.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> ThreePaneScaffoldNavigator<T>.safeCurrentContent(): T? {
    var oldContent by rememberSaveable { mutableStateOf<T?>(null) }

    val newContent = currentDestination?.content ?: oldContent
    currentDestination?.content?.let { oldContent = it }

    return newContent
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.canPopBackStack(): Boolean {
    return canNavigateBack(backBehavior)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.popBackStack(): Boolean {
    return navigateBack(backBehavior)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.selectItem(context: Context, windowAdaptiveInfo: WindowAdaptiveInfo, item: T) {
    if (windowAdaptiveInfo.isWindowLarge(context)) navigateBack()
    navigateTo(ListDetailPaneScaffoldRole.Detail, item)
}
