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
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.HighlightedText
import com.infomaniak.swisstransfer.ui.components.SwissTransferButton
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.icons.ArrowRight
import com.infomaniak.swisstransfer.ui.images.illus.matomo.Matomo
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.StorageBoxPile
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.ThreeCardsTransferType
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.TwoPadlocksIntertwinedStars
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
            background = AppIllus.Matomo.image(),
            illustration = { Illustration(AppIllus.StorageBoxPile) },
            text = {
                TitleAndDescription(
                    R.string.onboardingStorageTitle,
                    R.string.onboardingStorageSubtitleTemplate,
                    R.string.onboardingStorageSubtitleArgument,
                )
            }
        ),
        OnboardingPage(
            background = AppIllus.Matomo.image(),
            illustration = { Illustration(AppIllus.ThreeCardsTransferType) },
            text = {
                TitleAndDescription(
                    R.string.onboardingExpirationTitle,
                    R.string.onboardingExpirationSubtitleTemplate,
                    R.string.onboardingExpirationSubtitleArgument,
                )
            }
        ),
        OnboardingPage(
            background = AppIllus.Matomo.image(),
            illustration = { Illustration(AppIllus.TwoPadlocksIntertwinedStars) },
            text = {
                TitleAndDescription(
                    R.string.onboardingPasswordTitle,
                    R.string.onboardingPasswordSubtitleTemplate,
                    R.string.onboardingPasswordSubtitleArgument,
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
private fun Illustration(illustration: ThemedImage) {
    Image(illustration.image(), contentDescription = null)
}

@Composable
private fun TitleAndDescription(
    @StringRes titleRes: Int,
    @StringRes subtitleTemplateRes: Int,
    @StringRes subtitleArgumentRes: Int,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(textAlign = TextAlign.Center, text = stringResource(titleRes), style = SwissTransferTheme.typography.specificLight22)

        Spacer(modifier = Modifier.height(Margin.Mini))

        HighlightedText(
            modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
            templateRes = subtitleTemplateRes,
            argumentRes = subtitleArgumentRes,
            style = SwissTransferTheme.typography.h1,
        )
    }
}

private const val BUTTON_ANIM_DURATION = 300
private const val BUTTON_ANIM_DELAY = BUTTON_ANIM_DURATION / 2
private const val BUTTON_ANIM_NO_DELAY = 0
private val FAB_SIZE = 64.dp

@Composable
private fun BottomContent(
    pagerState: PagerState,
    pageCount: Int,
    startMainActivity: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage >= pageCount - 1

    val buttonWidth by animateDpAsState(
        targetValue = if (isLastPage) Dimens.SingleButtonMaxWidth else FAB_SIZE,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY),
        label = "Onboarding button width",
    )

    val buttonHeight by animateDpAsState(
        targetValue = if (isLastPage) Dimens.LargeButtonHeight else FAB_SIZE,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY),
        label = "Onboarding button height",
    )

    val arrowDelay = if (isLastPage) BUTTON_ANIM_NO_DELAY else BUTTON_ANIM_DELAY
    val arrowVisibility by animateFloatAsState(
        targetValue = if (isLastPage) 0f else 1f,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY, delayMillis = arrowDelay),
        label = "Onboarding arrow visibility",
    )

    val textDelay = if (isLastPage) BUTTON_ANIM_DELAY else BUTTON_ANIM_NO_DELAY
    val textVisibility by animateFloatAsState(
        targetValue = if (isLastPage) 1f else 0f,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY, delayMillis = textDelay),
        label = "Onboarding text visibility",
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp),
    ) {
        SwissTransferButton(
            modifier = Modifier
                .height(buttonHeight)
                .padding(horizontal = Margin.Medium)
                .width(buttonWidth),
            onClick = {
                if (isLastPage) {
                    startMainActivity()
                } else {
                    coroutineScope.launch(Dispatchers.IO) { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            contentPadding = PaddingValues(),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    modifier = Modifier.alpha(arrowVisibility),
                    imageVector = AppImages.AppIcons.ArrowRight,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.alpha(textVisibility),
                    text = stringResource(id = R.string.appName),
                    style = SwissTransferTheme.typography.bodyMedium
                )
            }
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
            modifier = Modifier
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
