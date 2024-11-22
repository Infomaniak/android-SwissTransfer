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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.components.SwissTransferFab
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.illus.ArrowRightCurved
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.MetallicSafe
import com.infomaniak.swisstransfer.ui.images.illus.uploadAd.SwissWithFlag
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(goToMainActivity: () -> Unit) {
    val onboardingPages = listOf(
        OnboardingPage(
            background = AppImages.AppIllus.Matomo.image(),
            illustration = {
                Image(AppImages.AppIllus.MetallicSafe.image(), contentDescription = null)
            },
            text = {
                TitleAndDescription(
                    "Salut",
                    "C'est cool comme app je vous conseil de l'essayer vous verrez que c'est toute une expérience"
                )
            }
        ),
        OnboardingPage(
            background = AppImages.AppIllus.Matomo.image(),
            illustration = {
                Image(AppImages.AppIllus.SwissWithFlag.image(), contentDescription = null)
            },
            text = {
                TitleAndDescription(
                    "Salut",
                    "C'est cool comme app je vous conseil de l'essayer vous verrez que c'est toute une expérience"
                )
            }
        ),
        OnboardingPage(
            background = AppImages.AppIllus.Matomo.image(),
            illustration = {
                Image(AppImages.AppIllus.MetallicSafe.image(), contentDescription = null)
            },
            text = {
                TitleAndDescription(
                    "Salut",
                    "C'est cool comme app je vous conseil de l'essayer vous verrez que c'est toute une expérience"
                )
            }
        ),
    )

    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()

    BackHandler(pagerState.currentPage > 0) {
        coroutineScope.launch(Dispatchers.IO) {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    OnboardingScaffold(
        pagerState = pagerState,
        onboardingPages = onboardingPages,
        bottomContent = { BottomContent(pagerState, onboardingPages.size, goToMainActivity) },
    )
}

@Composable
private fun TitleAndDescription(title: String, description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(textAlign = TextAlign.Center, text = title, style = SwissTransferTheme.typography.h1)
        Text(
            modifier = Modifier
                .widthIn(max = Dimens.DescriptionWidth)
                .padding(Margin.Medium),
            textAlign = TextAlign.Center,
            text = description,
            style = SwissTransferTheme.typography.specificLight22
        )
    }
}

@Composable
private fun BottomContent(
    pagerState: PagerState,
    pageCount: Int,
    startMainActivity: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage >= pageCount - 1

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp),
    ) {
        when {
            isLastPage -> LargeButton(R.string.appName, onClick = startMainActivity)
            else -> SwissTransferFab(
                icon = AppImages.AppIllus.ArrowRightCurved,
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            )
        }
    }
}

@Composable
private fun OnboardingScaffold(
    pagerState: PagerState,
    onboardingPages: List<OnboardingPage>,
    bottomContent: @Composable () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            HorizontalPager(pagerState, modifier = Modifier.weight(1f)) {
                OnboardingPageContent(page = onboardingPages[it])
            }

            HorizontalPagerIndicator(Modifier.padding(vertical = 32.dp), pagerState)

            bottomContent()
        }
    }
}

@Composable
fun ColumnScope.HorizontalPagerIndicator(modifier: Modifier = Modifier, pagerState: PagerState) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(page.background, contentDescription = null, modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier.fillMaxHeight(),
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

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun OnboardingScreenPreview() {
    SwissTransferTheme {
        Surface {
            OnboardingScreen {}
        }
    }
}
