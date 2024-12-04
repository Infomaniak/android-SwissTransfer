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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infomaniak.library.onboarding.OnboardingPage
import com.infomaniak.library.onboarding.OnboardingScaffold
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.HighlightedText
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.*
import com.infomaniak.swisstransfer.ui.screen.onboarding.components.AnimatedOnboardingButton
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
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

    BackHandler(pagerState.currentPage > 0) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    val onboardingPages = buildList {
        Page.entries.forEach { page ->
            add(page.toOnboardingPage(isHighlighted))
        }
    }

    OnboardingScaffold(
        pagerState = pagerState,
        onboardingPages = onboardingPages,
        bottomContent = {
            BottomContent(
                modifier = Modifier
                    .padding(it)
                    .consumeWindowInsets(it),
                isLastPage = { isLastPage },
                startMainActivity = goToMainActivity,
                goToNextPage = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
            )
        },
    )
}

@Composable
private fun Page.toOnboardingPage(isHighlighted: Map<Page, MutableState<Boolean>>) = OnboardingPage(
    background = background.image(),
    illustration = { Illustration(illustration) },
    text = {
        TitleAndDescription(
            page = this,
            isHighlighted = { isHighlighted[this]?.value ?: false }
        )
    }
)

@Composable
private fun Illustration(illustration: ThemedImage) {
    Image(illustration.image(), contentDescription = null)
}

@Composable
private fun TitleAndDescription(page: Page, isHighlighted: () -> Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(page.titleRes),
            style = SwissTransferTheme.typography.specificLight22,
        )

        Spacer(modifier = Modifier.height(Margin.Mini))

        HighlightedText(
            modifier = Modifier.widthIn(max = Dimens.DescriptionWidth),
            templateRes = page.subtitleTemplateRes,
            argumentRes = page.subtitleArgumentRes,
            style = SwissTransferTheme.typography.h1,
            angleDegrees = page.highlightAngleDegree,
            isHighlighted = isHighlighted,
        )
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    isLastPage: () -> Boolean,
    startMainActivity: () -> Unit,
    goToNextPage: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
    ) {
        AnimatedOnboardingButton(
            isExpanded = { isLastPage() },
            onClick = { if (isLastPage()) startMainActivity() else goToNextPage() },
        )
    }
}

private enum class Page(
    val background: ThemedImage,
    val illustration: ThemedImage,
    @StringRes val titleRes: Int,
    @StringRes val subtitleTemplateRes: Int,
    @StringRes val subtitleArgumentRes: Int,
    val highlightAngleDegree: Double,
) {
    STORAGE(
        background = AppIllus.RadialGradientCornerTopRight,
        illustration = AppIllus.StorageBoxPile,
        titleRes = R.string.onboardingStorageTitle,
        subtitleTemplateRes = R.string.onboardingStorageSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingStorageSubtitleArgument,
        highlightAngleDegree = -3.0,
    ),
    EXPIRATION(
        background = AppIllus.RadialGradientCornerTopLeft,
        illustration = AppIllus.ThreeCardsTransferType,
        titleRes = R.string.onboardingExpirationTitle,
        subtitleTemplateRes = R.string.onboardingExpirationSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingExpirationSubtitleArgument,
        highlightAngleDegree = -3.0,
    ),
    PASSWORD(
        background = AppIllus.RadialGradientCornerTopRight,
        illustration = AppIllus.TwoPadlocksIntertwinedStars,
        titleRes = R.string.onboardingPasswordTitle,
        subtitleTemplateRes = R.string.onboardingPasswordSubtitleTemplate,
        subtitleArgumentRes = R.string.onboardingPasswordSubtitleArgument,
        highlightAngleDegree = 3.0,
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
