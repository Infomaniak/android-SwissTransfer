/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

/**
 * Creates and remembers an [ArrowAnimationState] that survives configuration changes.
 */
@Composable
fun rememberArrowAnimationState(): ArrowAnimationState {
    return remember { ArrowAnimationState() }
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
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
) {
    AnimatedArrowCircle(
        state = state,
        direction = ArrowDirection.Down,
        contentDescription = contentDescription,
        modifier = modifier,
        contentColor = contentColor,
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
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
) {
    AnimatedArrowCircle(
        state = state,
        direction = ArrowDirection.Up,
        contentDescription = contentDescription,
        modifier = modifier,
        contentColor = contentColor,
    )
}

/**
 * State class for controlling the arrow down animation.
 * Similar to ModalBottomSheetState, provides a [play] method to trigger animation.
 */
@Stable
class ArrowAnimationState internal constructor() {
    private var isPlaying by mutableStateOf(false)

    internal val animatable = Animatable(0f)

    /**
     * Triggers a single animation cycle.
     * The animation accelerates at start and decelerates at end.
     * If called while animation is playing, it's ignored (debounced).
     */
    suspend fun play() {
        if (isPlaying) return

        isPlaying = true
        try {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = DURATION_MILLIS, easing = FastOutSlowInEasing),
            )

            // Swap arrows for next time
            // Reset to start position instantly (prepare for next animation)
            animatable.snapTo(0f)
        } finally {
            isPlaying = false
        }
    }

    companion object {
        private const val DURATION_MILLIS: Int = 450
    }
}

/**
 * Arrow direction enum defining whether the arrow points up or down.
 */
enum class ArrowDirection {
    Down, Up
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
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
) {
    val strokeWidth = 1.5.dp
    val iconSize = 24.dp
    val animationProgress = state.animatable.value

    // Direction multiplier: 1 for Down (positive Y is down), -1 for Up (positive Y is up)
    val directionMultiplier = if (direction == ArrowDirection.Down) 1f else -1f

    Box(
        modifier = modifier
            .size(iconSize)
            .clip(CircleShape)
            .background(Color.Transparent)
            .then(
                if (contentDescription != null) {
                    Modifier.semantics { this.contentDescription = contentDescription }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        // Circle outline (static)
        Canvas(
            modifier = Modifier
                .size(iconSize)
                .clip(CircleShape),
        ) {
            drawCircle(
                color = contentColor,
                radius = size.minDimension / 2 - 1.dp.toPx(),
                style = Stroke(width = strokeWidth.toPx()),
            )
        }

        // Travel distance: 150% of icon size for clean exit
        val travelDistance = iconSize * 1.5f

        // Calculate offsets based on animation progress.
        // For Down: Arrow A starts center, goes down; Arrow B starts above, goes to center
        // For Up: Arrow A starts center, goes up; Arrow B starts below, goes to center
        val arrowAOffset = animationProgress * travelDistance * directionMultiplier
        val arrowBOffset = -travelDistance * directionMultiplier + (animationProgress * travelDistance * directionMultiplier)

        // Arrow A
        ArrowCanvas(
            offsetY = arrowAOffset,
            contentColor = contentColor,
            direction = direction,
            strokeWidth = strokeWidth,
        )

        // Arrow B
        ArrowCanvas(
            offsetY = arrowBOffset,
            contentColor = contentColor,
            direction = direction,
            strokeWidth = strokeWidth,
        )
    }
}

/**
 * Draws a downward or upward pointing arrow at the specified center position.
 * Based on the original ImageVector path (24x24 canvas):
 * - strokeLineWidth = 1.5f, strokeLineCap = Round
 * - moveTo(12.0f, 16.5f); verticalLineToRelative(-9.0f)
 * - moveTo(12.0f + 3.75f, 16.5f - 3.75f); lineTo(12.0f, 16.5f); lineTo(12.0f - 3.75f, 16.5f - 3.75f)
 * Arrow spans from Y=7.5 to Y=16.5 (height = 9.0), head at 4.5 below/above center, wings at 0.75 above/below center
 *
 * @param direction The direction the arrow should point (Down or Up)
 */
private fun DrawScope.drawArrow(color: Color, strokeWidth: Dp, center: Offset, halfSize: Float, direction: ArrowDirection) {
    val strokeWidthPx = strokeWidth.toPx()

    // Determine y-multiplier based on direction
    // For Down: positive Y goes down (normal)
    // For Up: invert Y movement so arrow points up
    val directionMultiplier = if (direction == ArrowDirection.Down) 1f else -1f

    // Proportions relative to halfSize (since arrow height = 2 * halfSize = 9.0 in original units)
    val stemTopYOffset = -halfSize * 0.9f * directionMultiplier
    val headYOffset = halfSize * 0.9f * directionMultiplier
    val wingsYOffset = halfSize * 0.15f * directionMultiplier
    val wingXOffset = halfSize * 0.8f

    val headX = center.x
    val headY = center.y + headYOffset
    val stemTopY = center.y + stemTopYOffset
    val wingsY = center.y + wingsYOffset

    // Draw arrow stem (vertical line) - from stem top to head
    drawLine(
        color = color,
        start = Offset(headX, stemTopY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidthPx,
        cap = StrokeCap.Round
    )

    // Draw left wing (from left wing outer tip to head)
    drawLine(
        color = color,
        start = Offset(headX - wingXOffset, wingsY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidthPx,
        cap = StrokeCap.Round
    )

    // Draw right wing (from right wing outer tip to head)
    drawLine(
        color = color,
        start = Offset(headX + wingXOffset, wingsY),
        end = Offset(headX, headY),
        strokeWidth = strokeWidthPx,
        cap = StrokeCap.Round
    )
}

@Composable
private fun ArrowCanvas(
    offsetY: Dp,
    contentColor: Color,
    direction: ArrowDirection,
    modifier: Modifier = Modifier,
    strokeWidth: Dp,
) {
    Box(
        modifier = modifier
            .offset(y = offsetY)
            .size(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawArrow(contentColor, strokeWidth, Offset(size.width / 2, size.height / 2), 5.dp.toPx(), direction)
        }
    }
}
