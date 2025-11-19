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

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLargeWindow
import com.infomaniak.core.ui.compose.preview.PreviewSmallWindow
import com.infomaniak.core.onboarding.IndicatorStyle
import com.infomaniak.core.onboarding.OnboardingPage
import com.infomaniak.core.onboarding.OnboardingScaffold
import com.infomaniak.core.onboarding.components.OnboardingComponents.DefaultBackground
import com.infomaniak.core.onboarding.components.OnboardingComponents.DefaultLottieIllustration
import com.infomaniak.core.onboarding.components.OnboardingComponents.HighlightedTitleAndDescription
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopLeft
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopRight
import com.infomaniak.swisstransfer.ui.screen.onboarding.components.AnimatedOnboardingButton
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(goToMainActivity: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { Page.entries.size })
    val isLastPage by remember { derivedStateOf { pagerState.currentPage >= pagerState.pageCount - 1 } }
    val coroutineScope = rememberCoroutineScope()

    val isHighlighted = Page.entries.associateWith { rememberSaveable { mutableStateOf(false) } }

    // Start the highlighting of the text when the associated page is reached in the HorizontalPager
    LaunchedEffect(pagerState.currentPage) {
        val currentPage = Page.entries[pagerState.currentPage]
        isHighlighted[currentPage]?.value = true
    }

    OnboardingScaffold(
        pagerState = pagerState,
        onboardingPages = Page.entries.mapIndexed { index, page -> page.toOnboardingPage(isHighlighted, pagerState, index) },
        bottomContent = { paddingValues ->
            BottomContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues),
                isLastPage = { isLastPage },
                goToMainActivity = goToMainActivity,
                goToNextPage = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
            )
        },
        indicatorStyle = IndicatorStyle(
            inactiveColor = SwissTransferTheme.materialColors.outlineVariant,
            activeColor = SwissTransferTheme.materialColors.primary,
            inactiveSize = 8.dp,
            activeWidth = 16.dp,
            indicatorSpacing = Margin.Mini,
        )
    )
}

@Composable
private fun Page.toOnboardingPage(isHighlighted: Map<Page, MutableState<Boolean>>, pagerState: PagerState, index: Int) =
    OnboardingPage(
        background = { DefaultBackground(background.image()) },
        illustration = {
            DefaultLottieIllustration(lottieRawRes = illustrationRes, isCurrentPageVisible = { pagerState.currentPage == index })
        },
        text = {
            HighlightedTitleAndDescription(
                title = stringResource(titleRes),
                textStyle = SwissTransferTheme.typography.specificLight22,
                subtitleTemplate = stringResource(subtitleTemplateRes),
                subtitleArgument = stringResource(subtitleArgumentRes),
                highlightedTextStyle = SwissTransferTheme.typography.h1,
                highlightedAngleDegree = highlightAngleDegree,
                descriptionWidth = Dimens.DescriptionWidth,
                highlightedColor = SwissTransferTheme.colors.highlightedColor,
                isHighlighted = { isHighlighted[this]?.value ?: false }
            )
        }
    )

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    isLastPage: () -> Boolean,
    goToMainActivity: () -> Unit,
    goToNextPage: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
    ) {
        AnimatedOnboardingButton(
            isExpanded = { isLastPage() },
            onClick = { if (isLastPage()) goToMainActivity() else goToNextPage() },
        )
    }
}

private const val HIGHLIGHT_ANGLE = 3.0

private enum class Page(
    val background: ThemedImage,
    @RawRes val illustrationRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val subtitleTemplateRes: Int,
    @StringRes val subtitleArgumentRes: Int,
    val highlightAngleDegree: Double,
) {
    STORAGE(
        background = AppIllus.RadialGradientCornerTopRight,
        illustrationRes = R.raw.storage_cardboard_box_pile,
        titleRes = R.string.onboardingStorageTitle,
        subtitleTemplateRes = R.string.onboardingStorageSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingStorageSubtitleArgument,
        highlightAngleDegree = -HIGHLIGHT_ANGLE,
    ),
    EXPIRATION(
        background = AppIllus.RadialGradientCornerTopLeft,
        illustrationRes = R.raw.three_cards_transfer_type,
        titleRes = R.string.onboardingExpirationTitle,
        subtitleTemplateRes = R.string.onboardingExpirationSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingExpirationSubtitleArgument,
        highlightAngleDegree = -HIGHLIGHT_ANGLE,
    ),
    PASSWORD(
        background = AppIllus.RadialGradientCornerTopRight,
        illustrationRes = R.raw.two_locks_intertwined_stars,
        titleRes = R.string.onboardingPasswordTitle,
        subtitleTemplateRes = R.string.onboardingPasswordSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingPasswordSubtitleArgument,
        highlightAngleDegree = HIGHLIGHT_ANGLE,
    ),
}

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
