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
package com.infomaniak.swisstransfer.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScaffold(
    pagerState: PagerState,
    onboardingPages: List<OnboardingPage>,
    bottomContent: @Composable () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding(),
                start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
            )
        ) {
            HorizontalPager(pagerState, modifier = Modifier.weight(1f)) {
                OnboardingPageContent(page = onboardingPages[it], paddingValues.calculateTopPadding())
            }

            HorizontalPagerIndicator(Modifier.padding(vertical = 32.dp), pagerState)

            bottomContent()
        }
    }
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage, calculateTopPadding: Dp) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            page.background,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .padding(top = calculateTopPadding)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            with(page) {
                illustration()
                text()
            }
        }
    }
}

data class OnboardingPage(
    val background: ImageVector,
    val illustration: @Composable () -> Unit,
    val text: @Composable () -> Unit,
)