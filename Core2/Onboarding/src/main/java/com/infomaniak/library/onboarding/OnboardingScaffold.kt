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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScaffold(
    pagerState: PagerState,
    onboardingPages: List<OnboardingPage>,
    bottomContent: @Composable (PaddingValues) -> Unit,
    indicatorStyle: IndicatorStyle,
) {
    Scaffold { paddingValues ->
        Column {
            val startPadding = paddingValues.calculateStartPadding(LocalLayoutDirection.current)
            val endPadding = paddingValues.calculateEndPadding(LocalLayoutDirection.current)

            HorizontalPager(pagerState, modifier = Modifier.weight(1f)) { pageIndex ->
                OnboardingPageContent(
                    page = onboardingPages[pageIndex],
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding(),
                        start = startPadding,
                        end = endPadding,
                    ),
                )
            }

            HorizontalPagerIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(PaddingValues(start = startPadding, end = endPadding)),
                pagerState = pagerState,
                indicatorStyle = indicatorStyle,
            )

            bottomContent(
                PaddingValues(
                    bottom = paddingValues.calculateBottomPadding(),
                    start = startPadding,
                    end = endPadding,
                )
            )
        }
    }
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage, contentPadding: PaddingValues) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = page.background,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
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

@Preview
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun Preview() {
    val imageVector = Builder(
        defaultWidth = 24.0.dp,
        defaultHeight = 24.0.dp,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).build()

    val onboardingPage = OnboardingPage(
        background = imageVector,
        illustration = {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.LightGray)
            ) {
                Text("Illustration")
            }
        },
        text = { Text("Long description text of the onboarding page") }
    )

    OnboardingScaffold(
        pagerState = rememberPagerState { 3 },
        onboardingPages = listOf(onboardingPage, onboardingPage, onboardingPage),
        bottomContent = {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                Text("Bottom content")
            }
        },
        indicatorStyle = IndicatorStyle(
            inactiveColor = Color.LightGray,
            activeColor = Color.DarkGray,
            inactiveSize = 8.dp,
            activeWidth = 16.dp,
            indicatorSpacing = 8.dp,
        )
    )
}
