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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

/**
 * Centralised screen transitions, aligned with Material 3 motion guidelines.
 *
 * Two patterns are exposed and should be picked based on context:
 *
 * 1. **Shared Axis X** — for navigation between sibling destinations (`NavHost`).
 *    Content slides a small distance horizontally while fading. Forward navigation
 *    moves end → start, backward navigation moves start → end. The slide distance
 *    is intentionally small (≈ 30 dp worth) so that even when two consecutive
 *    screens share the same chrome (top app bar / bottom app bar), the motion
 *    reads as a deliberate transition rather than a crossfade through transparency.
 *
 * 2. **Fade Through** — for in-place content swaps inside a screen (`AnimatedContent`,
 *    e.g. empty state ↔ list, or list-detail sub-destination changes).
 *    The outgoing content fades out completely first, *then* the incoming content
 *    fades in. The two never overlap in transparency, which is what removes the
 *    "blinking bars / background bleeding through" issue caused by a naive crossfade
 *    when both states render an identical Scaffold chrome.
 *
 * See: https://m3.material.io/styles/motion/transitions/transition-patterns
 */
object SwissTransferTransition {

    // ---------------------------------------------------------------------------------------------
    // Shared Axis X — for NavHost navigation between sibling destinations.
    // ---------------------------------------------------------------------------------------------

    private const val SHARED_AXIS_DURATION_MS = 300
    // Material spec uses ~30 dp of motion. We approximate it as a fraction of the slot
    // size so it works on every screen size without hard-coding a dp value here.
    private const val SHARED_AXIS_SLIDE_FRACTION = 0.10f

    private val sharedAxisSlideSpec = tween<IntOffset>(
        durationMillis = SHARED_AXIS_DURATION_MS,
        easing = FastOutSlowInEasing,
    )
    private val sharedAxisFadeInSpec = tween<Float>(
        durationMillis = SHARED_AXIS_DURATION_MS,
        easing = LinearOutSlowInEasing,
    )
    private val sharedAxisFadeOutSpec = tween<Float>(
        durationMillis = SHARED_AXIS_DURATION_MS,
        easing = FastOutLinearInEasing,
    )

    /** Forward navigation — incoming screen slides in from the end (right in LTR). */
    val enterTransition: EnterTransition =
        slideInHorizontally(animationSpec = sharedAxisSlideSpec) { fullWidth ->
            (fullWidth * SHARED_AXIS_SLIDE_FRACTION).toInt()
        } + fadeIn(animationSpec = sharedAxisFadeInSpec)

    /** Forward navigation — outgoing screen slides out toward the start (left in LTR). */
    val exitTransition: ExitTransition =
        slideOutHorizontally(animationSpec = sharedAxisSlideSpec) { fullWidth ->
            -(fullWidth * SHARED_AXIS_SLIDE_FRACTION).toInt()
        } + fadeOut(animationSpec = sharedAxisFadeOutSpec)

    /** Back navigation — incoming screen slides in from the start. */
    val popEnterTransition: EnterTransition =
        slideInHorizontally(animationSpec = sharedAxisSlideSpec) { fullWidth ->
            -(fullWidth * SHARED_AXIS_SLIDE_FRACTION).toInt()
        } + fadeIn(animationSpec = sharedAxisFadeInSpec)

    /** Back navigation — outgoing screen slides out toward the end. */
    val popExitTransition: ExitTransition =
        slideOutHorizontally(animationSpec = sharedAxisSlideSpec) { fullWidth ->
            (fullWidth * SHARED_AXIS_SLIDE_FRACTION).toInt()
        } + fadeOut(animationSpec = sharedAxisFadeOutSpec)

    // ---------------------------------------------------------------------------------------------
    // Fade Through — for in-place content swaps inside a single screen (AnimatedContent).
    // The outgoing content fades out first; the incoming content fades in afterwards. They never
    // overlap in transparency, so identical Scaffold chrome (top/bottom bars) does not blink.
    // ---------------------------------------------------------------------------------------------

    private const val FADE_THROUGH_OUT_DURATION_MS = 90
    private const val FADE_THROUGH_IN_DURATION_MS = 210

    /** Fade-through enter — starts only after [fadeThroughExit] has completed. */
    val fadeThroughEnter: EnterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = FADE_THROUGH_IN_DURATION_MS,
            delayMillis = FADE_THROUGH_OUT_DURATION_MS,
            easing = LinearOutSlowInEasing,
        ),
    )

    /** Fade-through exit — short, runs first so the new content appears on a clean container. */
    val fadeThroughExit: ExitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = FADE_THROUGH_OUT_DURATION_MS,
            easing = FastOutLinearInEasing,
        ),
    )
}
