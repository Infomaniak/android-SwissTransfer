/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.components.twoPaneScaffold

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.*
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.*
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.*
import com.infomaniak.swisstransfer.ui.components.twoPaneScaffold.TwoPaneScaffoldHorizontalOrder.Companion.defaultPaneOrder
import com.infomaniak.swisstransfer.ui.theme.LocalWindowAdaptiveInfo
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge
import kotlin.math.roundToInt

/**
 * A composable function that sets up a List-Detail interface using a three-pane scaffold navigator.
 * This function adapts its layout based on the given window size class and manages the navigation
 * between list and detail panes.
 * @param T the type representing the item displayed in the detail pane. This can be an item id or a whole instance.
 * This type must be storable in a Bundle. If this customization is unneeded, you can pass [Nothing]
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
        )
    )

    BackHandler(navigator.canPopBackStack()) { navigator.popBackStack() }

    TwoPaneScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldDirective = navigator.scaffoldDirective,
        scaffoldValue = navigator.scaffoldValue,
        paneOrder = defaultPaneOrder,
        primaryPane = { AnimatedPane { navigator.detailPane() } },
        secondaryPane = { AnimatedPane { navigator.listPane() } },
    )
}

@ExperimentalMaterial3AdaptiveApi
@Composable
private fun TwoPaneScaffold(
    modifier: Modifier,
    scaffoldDirective: PaneScaffoldDirective,
    scaffoldValue: ThreePaneScaffoldValue,
    paneOrder: TwoPaneScaffoldHorizontalOrder,
    paneExpansionState: PaneExpansionState = remember { PaneExpansionState() },
    paneExpansionDragHandle: (@Composable (PaneExpansionState) -> Unit)? = null,
    primaryPane: @Composable TwoPaneScaffoldScope.() -> Unit,
    secondaryPane: @Composable TwoPaneScaffoldScope.() -> Unit,
) {
    val scaffoldState = remember { SeekableTransitionState(scaffoldValue) }
    LaunchedEffect(key1 = scaffoldValue) { scaffoldState.animateTo(scaffoldValue) }

    val layoutDirection = LocalLayoutDirection.current

    val paneHorizontalOrder = remember(paneOrder, layoutDirection) {
        paneOrder.setGoodPaneOrder(layoutDirection)
    }
    val previousScaffoldValue = remember { TwoPaneScaffoldValueHolder(scaffoldState.targetState) }

    val spacerSize = with(LocalDensity.current) { scaffoldDirective.horizontalPartitionSpacerSize.roundToPx() }
    val paneMotion = remember(scaffoldState.targetState, paneHorizontalOrder, spacerSize) {
        val previousValue = previousScaffoldValue.value
        previousScaffoldValue.value = scaffoldState.targetState

        calculateTwoPaneMotion(
            previousScaffoldValue = previousValue,
            currentScaffoldValue = scaffoldState.targetState,
            paneOrder = paneHorizontalOrder,
            spacerSize = spacerSize
        )
    }

    val currentTransition = rememberTransition(scaffoldState)

    LookaheadScope {
        // Create PaneWrappers for each of the panes and map the transitions according to each pane
        // role and order.
        val contents = listOf<@Composable () -> Unit>({
            remember(currentTransition, this@LookaheadScope) {
                TwoPaneScaffoldScopeImpl(
                    ThreePaneScaffoldRole.Primary, currentTransition, scaffoldState, this@LookaheadScope
                )
            }.apply {
                positionAnimationSpec = paneMotion.positionAnimationSpec
                sizeAnimationSpec = paneMotion.sizeAnimationSpec
                enterTransition = paneMotion.enterTransition(ThreePaneScaffoldRole.Primary, paneHorizontalOrder)
                exitTransition = paneMotion.exitTransition(ThreePaneScaffoldRole.Primary, paneHorizontalOrder)
            }.primaryPane()
        }, {
            remember(currentTransition, this@LookaheadScope) {
                TwoPaneScaffoldScopeImpl(ThreePaneScaffoldRole.Secondary, currentTransition, scaffoldState, this@LookaheadScope)
            }.apply {
                positionAnimationSpec = paneMotion.positionAnimationSpec
                sizeAnimationSpec = paneMotion.sizeAnimationSpec
                enterTransition = paneMotion.enterTransition(ThreePaneScaffoldRole.Secondary, paneHorizontalOrder)
                exitTransition = paneMotion.exitTransition(ThreePaneScaffoldRole.Secondary, paneHorizontalOrder)
            }.secondaryPane()
        }, {
            if (paneExpansionDragHandle != null) paneExpansionDragHandle(paneExpansionState)
        })

        val measurePolicy = remember(paneExpansionState) {
            TwoPaneContentMeasurePolicy(scaffoldDirective, scaffoldState.targetState, paneExpansionState, paneHorizontalOrder)
        }.apply {
            this.scaffoldDirective = scaffoldDirective
            this.scaffoldValue = scaffoldState.targetState
            this.paneOrder = paneHorizontalOrder
        }

        Layout(contents = contents, modifier = modifier, measurePolicy = measurePolicy)
    }
}

@ExperimentalMaterial3AdaptiveApi
private fun TwoPaneScaffoldHorizontalOrder.setGoodPaneOrder(layoutDirection: LayoutDirection): TwoPaneScaffoldHorizontalOrder {
    return if (layoutDirection == LayoutDirection.Rtl) TwoPaneScaffoldHorizontalOrder(secondPane, firstPane) else this
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private class TwoPaneScaffoldValueHolder(var value: ThreePaneScaffoldValue)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private class TwoPaneScaffoldScopeImpl(
    override val role: ThreePaneScaffoldRole,
    override val scaffoldStateTransition: Transition<ThreePaneScaffoldValue>,
    private val transitionState: SeekableTransitionState<ThreePaneScaffoldValue>,
    lookaheadScope: LookaheadScope
) : TwoPaneScaffoldScope, LookaheadScope by lookaheadScope, PaneScaffoldScopeImpl() {
    override val scaffoldStateTransitionFraction: Float
        get() = if (transitionState.currentState == transitionState.targetState) 1f else transitionState.fraction

    override var positionAnimationSpec: FiniteAnimationSpec<IntOffset> by mutableStateOf(snap())
    override var sizeAnimationSpec: FiniteAnimationSpec<IntSize> by mutableStateOf(snap())
    override var enterTransition by mutableStateOf(EnterTransition.None)
    override var exitTransition by mutableStateOf(ExitTransition.None)
}

@ExperimentalMaterial3AdaptiveApi
sealed interface TwoPaneScaffoldScope : PaneScaffoldScope, LookaheadScope {
    val role: ThreePaneScaffoldRole
    val scaffoldStateTransition: Transition<ThreePaneScaffoldValue>
    val scaffoldStateTransitionFraction: Float
    val positionAnimationSpec: FiniteAnimationSpec<IntOffset>
    val sizeAnimationSpec: FiniteAnimationSpec<IntSize>
    val enterTransition: EnterTransition
    val exitTransition: ExitTransition
}

sealed interface PaneScaffoldScope {
    fun Modifier.preferredWidth(width: Dp): Modifier
}

private abstract class PaneScaffoldScopeImpl : PaneScaffoldScope {
    override fun Modifier.preferredWidth(width: Dp): Modifier {
        require(width == Dp.Unspecified || width > 0.dp)
        return this.then(PreferredWidthElement(width))
    }
}

private class PreferredWidthElement(
    private val width: Dp,
) : ModifierNodeElement<PreferredWidthNode>() {
    private val inspectorInfo = debugInspectorInfo {
        name = "preferredWidth"
        value = width
    }

    override fun create(): PreferredWidthNode {
        return PreferredWidthNode(width)
    }

    override fun update(node: PreferredWidthNode) {
        node.width = width
    }

    override fun InspectorInfo.inspectableProperties() {
        inspectorInfo()
    }

    override fun hashCode(): Int {
        return width.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? PreferredWidthElement ?: return false
        return width == otherModifier.width
    }
}

private class PreferredWidthNode(var width: Dp) : ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?) =
        ((parentData as? PaneScaffoldParentData) ?: PaneScaffoldParentData()).also {
            it.preferredWidth = with(this) { width.toPx() }
        }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun calculateTwoPaneMotion(
    previousScaffoldValue: ThreePaneScaffoldValue,
    currentScaffoldValue: ThreePaneScaffoldValue,
    paneOrder: TwoPaneScaffoldHorizontalOrder,
    spacerSize: Int
): TwoPaneMotion {
    if (previousScaffoldValue == currentScaffoldValue) return TwoPaneMotion.NoMotion

    val previousExpandedCount = previousScaffoldValue.expandedCount
    val currentExpandedCount = currentScaffoldValue.expandedCount
    if (previousExpandedCount != currentExpandedCount) return TwoPaneMotion.NoMotion

    return when (PaneAdaptedValue.Expanded) {
        previousScaffoldValue[paneOrder.firstPane] -> MovePanesToLeftMotion(spacerSize)
        else -> MovePanesToRightMotion(spacerSize)
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private val ThreePaneScaffoldValue.expandedCount: Int
    get() {
        var count = 0
        if (primary == PaneAdaptedValue.Expanded || secondary == PaneAdaptedValue.Expanded) count++
        return count
    }

@ExperimentalMaterial3AdaptiveApi
@Immutable
private class MovePanesToLeftMotion(private val spacerSize: Int) : TwoPaneMotion(
    TwoPaneMotionDefaults.PanePositionAnimationSpec,
    TwoPaneMotionDefaults.PaneSizeAnimationSpec,
    slideInFromRight(spacerSize),
    slideOutToLeft(spacerSize),
    slideInFromRight(spacerSize),
    slideOutToLeft(spacerSize),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MovePanesToLeftMotion) return false
        if (this.spacerSize != other.spacerSize) return false
        return true
    }

    override fun hashCode(): Int {
        return spacerSize
    }
}

@ExperimentalMaterial3AdaptiveApi
@Immutable
private class MovePanesToRightMotion(private val spacerSize: Int) : TwoPaneMotion(
    TwoPaneMotionDefaults.PanePositionAnimationSpec,
    TwoPaneMotionDefaults.PaneSizeAnimationSpec,
    slideInFromLeft(spacerSize),
    slideOutToRight(spacerSize),
    slideInFromLeft(spacerSize),
    slideOutToRight(spacerSize),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MovePanesToRightMotion) return false
        if (this.spacerSize != other.spacerSize) return false
        return true
    }

    override fun hashCode(): Int {
        return spacerSize
    }
}

@ExperimentalMaterial3AdaptiveApi
@Immutable
private class SwitchLeftTwoPanesMotion(private val spacerSize: Int) : TwoPaneMotion(
    TwoPaneMotionDefaults.PanePositionAnimationSpec,
    TwoPaneMotionDefaults.PaneSizeAnimationSpec,
    slideInFromLeftDelayed(spacerSize),
    slideOutToLeft(spacerSize),
    slideInFromLeftDelayed(spacerSize),
    slideOutToLeft(spacerSize),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SwitchLeftTwoPanesMotion) return false
        if (this.spacerSize != other.spacerSize) return false
        return true
    }

    override fun hashCode(): Int = spacerSize
}

@ExperimentalMaterial3AdaptiveApi
@Immutable
private class SwitchRightTwoPanesMotion(private val spacerSize: Int) : TwoPaneMotion(
    TwoPaneMotionDefaults.PanePositionAnimationSpec,
    TwoPaneMotionDefaults.PaneSizeAnimationSpec,
    EnterTransition.None,
    ExitTransition.None,
    slideInFromRightDelayed(spacerSize),
    slideOutToRight(spacerSize),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SwitchRightTwoPanesMotion) return false
        if (this.spacerSize != other.spacerSize) return false
        return true
    }

    override fun hashCode(): Int = spacerSize
}

class DelayedSpringSpec<T>(
    dampingRatio: Float = Spring.DampingRatioNoBouncy,
    stiffness: Float = Spring.StiffnessMedium,
    private val delayedRatio: Float,
    visibilityThreshold: T? = null
) : FiniteAnimationSpec<T> {
    private val originalSpringSpec = spring(dampingRatio, stiffness, visibilityThreshold)

    override fun <V : AnimationVector> vectorize(converter: TwoWayConverter<T, V>): VectorizedFiniteAnimationSpec<V> {
        return DelayedVectorizedSpringSpec(originalSpringSpec.vectorize(converter), delayedRatio)
    }
}

private class DelayedVectorizedSpringSpec<V : AnimationVector>(
    val originalVectorizedSpringSpec: VectorizedFiniteAnimationSpec<V>,
    val delayedRatio: Float,
) : VectorizedFiniteAnimationSpec<V> {
    var delayedTimeNanos: Long = 0
    var cachedInitialValue: V? = null
    var cachedTargetValue: V? = null
    var cachedInitialVelocity: V? = null
    var cachedOriginalDurationNanos: Long = 0

    override fun getValueFromNanos(
        playTimeNanos: Long,
        initialValue: V,
        targetValue: V,
        initialVelocity: V
    ): V {
        updateDelayedTimeNanosIfNeeded(initialValue, targetValue, initialVelocity)
        return if (playTimeNanos <= delayedTimeNanos) {
            initialValue
        } else {
            originalVectorizedSpringSpec.getValueFromNanos(
                playTimeNanos - delayedTimeNanos,
                initialValue,
                targetValue,
                initialVelocity
            )
        }
    }

    override fun getVelocityFromNanos(
        playTimeNanos: Long,
        initialValue: V,
        targetValue: V,
        initialVelocity: V
    ): V {
        updateDelayedTimeNanosIfNeeded(initialValue, targetValue, initialVelocity)
        return if (playTimeNanos <= delayedTimeNanos) {
            initialVelocity
        } else {
            originalVectorizedSpringSpec.getVelocityFromNanos(
                playTimeNanos - delayedTimeNanos,
                initialValue,
                targetValue,
                initialVelocity
            )
        }
    }

    override fun getDurationNanos(initialValue: V, targetValue: V, initialVelocity: V): Long {
        updateDelayedTimeNanosIfNeeded(initialValue, targetValue, initialVelocity)
        return cachedOriginalDurationNanos + delayedTimeNanos
    }

    private fun updateDelayedTimeNanosIfNeeded(
        initialValue: V,
        targetValue: V,
        initialVelocity: V
    ) {
        if (
            initialValue != cachedInitialValue ||
            targetValue != cachedTargetValue ||
            initialVelocity != cachedInitialVelocity
        ) {
            cachedOriginalDurationNanos =
                originalVectorizedSpringSpec.getDurationNanos(initialValue, targetValue, initialVelocity)
            delayedTimeNanos = (cachedOriginalDurationNanos * delayedRatio).toLong()
        }
    }
}

object TwoPaneMotionDefaults {
    val PanePositionAnimationSpec: SpringSpec<IntOffset> = spring(
        stiffness = Spring.StiffnessMediumLow, visibilityThreshold = IntOffset.VisibilityThreshold
    )

    val PanePositionAnimationSpecDelayed: DelayedSpringSpec<IntOffset> = DelayedSpringSpec(
        stiffness = Spring.StiffnessMediumLow, delayedRatio = 0.1f, visibilityThreshold = IntOffset.VisibilityThreshold
    )

    val PaneSizeAnimationSpec: SpringSpec<IntSize> = spring(
        stiffness = Spring.StiffnessMediumLow, visibilityThreshold = IntSize.VisibilityThreshold
    )
}

@ExperimentalMaterial3AdaptiveApi
@Immutable
class PaneExpansionAnchor
private constructor(val percentage: Int, val startOffset: Dp)

@ExperimentalMaterial3AdaptiveApi
@Composable
fun TwoPaneScaffoldScope.AnimatedPane(modifier: Modifier = Modifier, content: (@Composable AnimatedVisibilityScope.() -> Unit)) {
    val keepShowing =
        scaffoldStateTransition.currentState[role] != PaneAdaptedValue.Hidden && scaffoldStateTransition.targetState[role] != PaneAdaptedValue.Hidden
    val animateFraction = { scaffoldStateTransitionFraction }
    scaffoldStateTransition.AnimatedVisibility(
        visible = { value: ThreePaneScaffoldValue -> value[role] != PaneAdaptedValue.Hidden },
        modifier = modifier
            .then(AnimatedPaneElement)
            .animateBounds(
                animateFraction = animateFraction,
                positionAnimationSpec = positionAnimationSpec,
                sizeAnimationSpec = sizeAnimationSpec,
                lookaheadScope = this,
                enabled = keepShowing
            )
            .then(if (keepShowing) Modifier else Modifier.clipToBounds()),
        enter = enterTransition,
        exit = exitTransition
    ) {
        AnimatedPaneScopeImpl(this).content()
    }
}

private class AnimatedPaneScopeImpl(animatedVisibilityScope: AnimatedVisibilityScope) :
    AnimatedVisibilityScope by animatedVisibilityScope

private object AnimatedPaneElement : ModifierNodeElement<AnimatedPaneNode>() {
    private val inspectorInfo = debugInspectorInfo {
        name = "isPaneComposable"
        value = true
    }

    override fun create(): AnimatedPaneNode = AnimatedPaneNode()
    override fun update(node: AnimatedPaneNode) {}
    override fun InspectorInfo.inspectableProperties() = inspectorInfo()
    override fun hashCode(): Int = 0
    override fun equals(other: Any?): Boolean = (other is AnimatedPaneElement)
}

private class AnimatedPaneNode : ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?) =
        ((parentData as? PaneScaffoldParentData) ?: PaneScaffoldParentData()).also {
            it.isAnimatedPane = true
        }
}

data class PaneScaffoldParentData(var preferredWidth: Float? = null, var isAnimatedPane: Boolean = false)

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
        return AnimateBoundsNode(animateFraction, sizeAnimationSpec, positionAnimationSpec, lookaheadScope)
    }

    override fun update(node: AnimateBoundsNode) {
        node.animateFraction = animateFraction
        node.sizeAnimationSpec = sizeAnimationSpec
        node.positionAnimationSpec = positionAnimationSpec
        node.lookaheadScope = lookaheadScope
    }

    override fun InspectorInfo.inspectableProperties() = inspectorInfo()
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

    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean = animateFraction() != 1f

    override fun Placeable.PlacementScope.isPlacementApproachInProgress(lookaheadCoordinates: LayoutCoordinates): Boolean {
        return animateFraction() != 1f
    }

    override fun ApproachMeasureScope.approachMeasure(measurable: Measurable, constraints: Constraints): MeasureResult {
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
                positionTracker.updateTargetOffset(with(lookaheadScope) {
                    lookaheadScopeCoordinates.localLookaheadPositionOf(it).toIntOffset()
                })
                placeable.place(with(lookaheadScope) {
                    positionTracker.updateAndGetCurrentOffset(animateFraction()) - lookaheadScopeCoordinates.localPositionOf(
                        it,
                        Offset.Zero
                    ).toIntOffset()
                })
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
        if (targetSize == newSize) return

        originalSize = if (currentSize != InvalidIntSize) currentSize else newSize

        targetSize = newSize
        animation = TargetBasedAnimation(animationSpec, IntSize.VectorConverter, originalSize, targetSize)
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
        if (targetOffset == newOffset) return

        originalOffset = if (currentOffset != null) currentOffset else newOffset

        targetOffset = newOffset
        animation = TargetBasedAnimation(animationSpec, IntOffset.VectorConverter, originalOffset!!, targetOffset!!)
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
): Modifier {
    return if (enabled) {
        this.then(AnimateBoundsElement(animateFraction, sizeAnimationSpec, positionAnimationSpec, lookaheadScope))
    } else {
        this
    }
}
