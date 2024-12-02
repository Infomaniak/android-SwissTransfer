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
package com.infomaniak.library.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlin.math.abs

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    inactiveIndicatorColor: Color = Color.LightGray,
    activeIndicatorColor: Color = Color.Green,
    inactiveIndicatorSize: Dp = 8.dp,
    activeIndicatorWidth: Dp = 16.dp,
    indicatorPadding: Dp = 8.dp,
) {
    Row(modifier, horizontalArrangement = Arrangement.Center) {
        repeat(pagerState.pageCount) { index ->
            val (indicatorWidth, indicatorColor: Color) = computeIndicatorProperties(
                index,
                pagerState,
                inactiveIndicatorSize,
                activeIndicatorWidth,
                inactiveIndicatorColor,
                activeIndicatorColor
            )

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(indicatorColor)
                    .size(height = inactiveIndicatorSize, width = indicatorWidth)
            )

            if (index < pagerState.pageCount - 1) Spacer(modifier = Modifier.width(indicatorPadding))
        }
    }
}

@Composable
private fun computeIndicatorProperties(
    index: Int,
    pagerState: PagerState,
    inactiveIndicatorSize: Dp,
    activeIndicatorWidth: Dp,
    inactiveIndicatorColor: Color,
    activeIndicatorColor: Color
): Pair<Dp, Color> {
    val (extendedCurrentPageOffsetFraction, pageVisibilityProgress) = computePageProgresses(index, pagerState)

    val indicatorWidth = lerp(inactiveIndicatorSize, activeIndicatorWidth, pageVisibilityProgress)

    val isTransitioningToSelected = extendedCurrentPageOffsetFraction < 0
    val indicatorColor: Color = when {
        index == pagerState.currentPage && isTransitioningToSelected -> {
            lerp(inactiveIndicatorColor, activeIndicatorColor, pageVisibilityProgress)
        }
        index == pagerState.currentPage + 1 && isTransitioningToSelected -> {
            lerp(inactiveIndicatorColor, activeIndicatorColor, pageVisibilityProgress)
        }
        index <= pagerState.currentPage -> activeIndicatorColor
        else -> inactiveIndicatorColor
    }

    return indicatorWidth to indicatorColor
}

@Composable
private fun computePageProgresses(index: Int, pagerState: PagerState): Pair<Float, Float> {
    // Extended offset fraction of the current page relative to the screen
    // Range: [-1, 1]
    //  0: Page is centered on the screen
    // -1: Page is completely off-screen to the left
    //  1: Page is completely off-screen to the right
    val extendedCurrentPageOffsetFraction = when {
        // Page is one step behind the current page and partially visible on the left
        index == pagerState.currentPage - 1 && pagerState.currentPageOffsetFraction < 0 -> {
            1 + pagerState.currentPageOffsetFraction
        }
        // Current page itself (centered or partially moved)
        index == pagerState.currentPage -> pagerState.currentPageOffsetFraction
        // Page is one step ahead of the current page and partially visible on the right
        index == pagerState.currentPage + 1 && pagerState.currentPageOffsetFraction >= 0 -> {
            -1 + pagerState.currentPageOffsetFraction
        }
        // Completely off-screen
        else -> 1f
    }

    // Progress of the page visibility
    // Range: [0, 1]
    //  0: Page is completely off-screen
    //  1: Page is fully centered on the screen
    val pageVisibilityProgress = 1 - abs(extendedCurrentPageOffsetFraction)

    return extendedCurrentPageOffsetFraction to pageVisibilityProgress
}

@Preview
@Composable
private fun Preview() {
    HorizontalPagerIndicator(pagerState = rememberPagerState { 3 })
}
