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
package com.infomaniak.swisstransfer.ui.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infomaniak.core.auth.models.user.User
import com.infomaniak.core.avatar.components.Avatar
import com.infomaniak.core.avatar.models.AvatarType
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Person
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.MyAccountDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.ReceivedDestination
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation.SentDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.infomaniak.core.common.R as RCore

/**
 * Enum class representing the different destinations in the app's [BottomAppBar] or [NavigationRail].
 *
 * @property label The string label for the destination.
 * @property icon The icon to be displayed for the destination.
 */
enum class NavigationItem(
    val label: @Composable () -> String,
    val icon: @Composable (User?, contentDescription: String?) -> Unit,
    val destination: MainNavigation,
) {
    Sent(
        label = { stringResource(R.string.sentTitle) },
        icon = { _, contentDescription ->
            val scope = rememberCoroutineScope()
            val state = rememberArrowAnimationState()
            AnimatedArrowUpCircle(state = state, modifier = Modifier.clickable(onClick = { scope.launch { state.play() } }))
        },
        destination = SentDestination()
    ),
    Received(
        label = { stringResource(R.string.receivedTitle) },
        icon = { _, contentDescription ->
            val scope = rememberCoroutineScope()
            val state = rememberArrowAnimationState()
            AnimatedArrowDownCircle(state = state, modifier = Modifier.clickable(onClick = { scope.launch { state.play() } }))
        },
        destination = ReceivedDestination()
    ),
    MyAccount(
        label = { pluralStringResource(RCore.plurals.myAccount, 1) },
        icon = { user, contentDescription ->
            Crossfade(user) { user ->
                if (user == null) {
                    Icon(AppIcons.Person, contentDescription)
                } else {
                    Avatar(AvatarType.fromUser(user), modifier = Modifier.size(24.dp))
                }
            }
        },
        destination = MyAccountDestination
    ),
}

/**
 * Arrow direction enum defining whether the arrow points up or down.
 */
enum class ArrowDirection {
    DOWN,
    UP
}

/**
 * Draws a downward or upward pointing arrow at the specified center position.
 * Based on ImageVector path (24x24 canvas):
 * - strokeLineWidth = 1.5f, strokeLineCap = Round
 * - moveTo(12.0f, 16.5f); verticalLineToRelative(-9.0f)
 * - moveTo(12.0f + 3.75f, 16.5f - 3.75f); lineTo(12.0f, 16.5f); lineTo(12.0f - 3.75f, 16.5f - 3.75f)
 * Arrow spans from Y=7.5 to Y=16.5 (height = 9.0), head at 4.5 below/above center, wings at 0.75 above/below center
 *
 * @param direction The direction the arrow should point (DOWN or UP)
 */
private fun DrawScope.drawArrow(
    color: Color,
    center: Offset,
    halfSize: Float,
    direction: ArrowDirection = ArrowDirection.DOWN
) {
    val strokeWidth = 1.5.dp.toPx()

    // Determine y-multiplier based on direction
    // For DOWN: positive Y goes down (normal)
    // For UP: invert Y movement so arrow points up
    val yMultiplier = if (direction == ArrowDirection.DOWN) 1f else -1f

    // Proportions relative to halfSize (since arrow height = 2 * halfSize = 9.0 in original units)
    // In ImageVector: stem top at Y=7.5 (4.5 above center), head at Y=16.5 (4.5 below center)
    // Wings at Y=12.75 (0.75 above center), wing X offset = 3.75
    val stemTopYOffset = -halfSize * 0.9f * yMultiplier  // 4.5/5.0 = 0.9 of halfSize
    val headYOffset = halfSize * 0.9f * yMultiplier  // Head is below/above center depending on direction
    val wingsYOffset = halfSize * 0.15f * yMultiplier  // Move wings closer to arrow tip
    val wingXOffset = halfSize * 0.8f  // 3.75/5.0 = 0.75 (X is same for both directions)

    val headX = center.x
    val headY = center.y + headYOffset
    val stemTopY = center.y + stemTopYOffset
    val wingsY = center.y + wingsYOffset

    // Draw arrow stem (vertical line) - from stem top to head
    drawLine(
        color = color,
        start = Offset(headX, stemTopY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    // Draw left wing (from left wing to head)
    drawLine(
        color = color,
        start = Offset(headX - wingXOffset, wingsY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    // Draw right wing (from right wing to head)
    drawLine(
        color = color,
        start = Offset(headX + wingXOffset, wingsY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

/**
 * State class for controlling the arrow down animation.
 * Similar to ModalBottomSheetState, provides a [play] method to trigger animation.
 */
@Stable
class ArrowAnimationState internal constructor(
    private val scope: CoroutineScope
) {
    private var _isPlaying by mutableStateOf(false)

    /**
     * Whether an animation is currently in progress.
     */
    val isPlaying: Boolean get() = _isPlaying

    internal val animatable = Animatable(0f)
    internal var activeArrow by mutableIntStateOf(0)

    /**
     * Triggers a single animation cycle.
     * The animation accelerates at start and decelerates at end.
     * If called while animation is playing, it's ignored (debounced).
     *
     * @param durationMillis Duration of the animation in milliseconds (default: 400ms)
     */
    suspend fun play(durationMillis: Int = 400) {
        if (_isPlaying) return

        _isPlaying = true
        try {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = durationMillis,
                    easing = FastOutSlowInEasing
                )
            )

            // Swap arrows for next time
            activeArrow = 1 - activeArrow
            // Reset to start position instantly (prepare for next animation)
            animatable.snapTo(0f)
        } finally {
            _isPlaying = false
        }
    }
}

/**
 * Creates and remembers an [ArrowAnimationState] that survives configuration changes.
 */
@Composable
fun rememberArrowAnimationState(): ArrowAnimationState {
    val scope = rememberCoroutineScope()
    return remember { ArrowAnimationState(scope = scope) }
}

/**
 * Base animated arrow circle icon that supports both up and down directions.
 *
 * Animation behavior:
 * - Circle remains static
 * - One arrow slides in the direction of the arrow and exits the circle
 * - A second arrow slides in from the opposite direction and settles at center
 * - Arrows are masked by the circular boundary
 *
 * @param state The animation state controlling the animation
 * @param direction The direction the arrow should point
 * @param modifier Modifier for the component
 * @param contentColor Color of the circle and arrows
 */
@Composable
private fun AnimatedArrowCircle(
    state: ArrowAnimationState,
    direction: ArrowDirection,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current
) {
    val animationProgress = state.animatable.value
    val activeArrow = state.activeArrow

    // Direction multiplier: 1 for DOWN (positive Y is down), -1 for UP (positive Y is up)
    val directionMultiplier = if (direction == ArrowDirection.DOWN) 1f else -1f

    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        // Circle outline (static)
        Canvas(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        ) {
            drawCircle(
                color = contentColor,
                radius = size.minDimension / 2 - 1.dp.toPx(),
                style = Stroke(width = 1.5.dp.toPx())
            )
        }

        // Calculate offsets based on animation progress
        // Travel distance: 150% of icon size for clean exit (24dp * 1.5 = 36dp)
        val travelDistancePx = with(LocalDensity.current) { 36.dp.toPx() }

        // Arrow offset calculation - adapts based on direction
        // For DOWN: Arrow A starts center, goes down; Arrow B starts above, goes to center
        // For UP: Arrow A starts center, goes up; Arrow B starts below, goes to center
        val arrowAOffsetPx = when (activeArrow) {
            0 -> animationProgress * travelDistancePx * directionMultiplier  // Starts center, goes in direction
            else -> -travelDistancePx * directionMultiplier + (animationProgress * travelDistancePx * directionMultiplier)  // Starts opposite, goes to center
        }

        val arrowBOffsetPx = when (activeArrow) {
            0 -> -travelDistancePx * directionMultiplier + (animationProgress * travelDistancePx * directionMultiplier)  // Starts opposite, goes to center
            else -> animationProgress * travelDistancePx * directionMultiplier  // Starts center, goes in direction
        }

        // Arrow A
        ArrowCanvas(
            offsetY = with(LocalDensity.current) { arrowAOffsetPx.toDp() },
            contentColor = contentColor,
            direction = direction,
            modifier = Modifier.graphicsLayer { alpha = if (activeArrow == 0 || animationProgress > 0f) 1f else 0f }
        )

        // Arrow B
        ArrowCanvas(
            offsetY = with(LocalDensity.current) { arrowBOffsetPx.toDp() },
            contentColor = contentColor,
            direction = direction,
            modifier = Modifier.graphicsLayer { alpha = if (activeArrow == 1 || animationProgress > 0f) 1f else 0f }
        )
    }
}

/**
 * Animated downward pointing arrow circle icon.
 *
 * @param state The animation state controlling the animation
 * @param modifier Modifier for the component
 * @param contentColor Color of the circle and arrows
 */
@Composable
fun AnimatedArrowDownCircle(
    state: ArrowAnimationState,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current
) {
    AnimatedArrowCircle(
        state = state,
        direction = ArrowDirection.DOWN,
        modifier = modifier,
        contentColor = contentColor
    )
}

/**
 * Animated upward pointing arrow circle icon.
 *
 * @param state The animation state controlling the animation
 * @param modifier Modifier for the component
 * @param contentColor Color of the circle and arrows
 */
@Composable
fun AnimatedArrowUpCircle(
    state: ArrowAnimationState,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current
) {
    AnimatedArrowCircle(
        state = state,
        direction = ArrowDirection.UP,
        modifier = modifier,
        contentColor = contentColor
    )
}

@Composable
private fun ArrowCanvas(
    offsetY: Dp,
    contentColor: Color,
    direction: ArrowDirection,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .offset(y = offsetY)
            .size(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawArrow(contentColor, Offset(size.width / 2, size.height / 2), 5.dp.toPx(), direction)
        }
    }
}
