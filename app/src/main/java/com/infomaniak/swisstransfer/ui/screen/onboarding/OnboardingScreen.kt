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

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.infomaniak.core.compose.basicbutton.BasicButton
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.core.onboarding.OnboardingPage
import com.infomaniak.core.onboarding.OnboardingScaffold
import com.infomaniak.core.onboarding.components.DefaultLottieIllustration
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.HighlightedText
import com.infomaniak.swisstransfer.ui.components.SwissTransferBottomSheet
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.ThemedImage
import com.infomaniak.swisstransfer.ui.images.icons.ArrowRight
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopLeft
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopRight
import com.infomaniak.swisstransfer.ui.screen.onboarding.components.AnimatedOnboardingButton
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLargeWindow
import com.infomaniak.swisstransfer.ui.utils.PreviewSmallWindow
import kotlinx.coroutines.launch
import com.infomaniak.core.R as RCore

@OptIn(ExperimentalMaterial3Api::class)
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
        Page.entries.forEachIndexed { index, page ->
            add(page.toOnboardingPage(isHighlighted, pagerState, index))
        }
    }

    // TODO
    val accounts = remember { mutableStateListOf(Unit, Unit, Unit) }
    val skippedIds = remember { mutableStateListOf<Long>() }

    var showAccountsBottomSheet by rememberSaveable { mutableStateOf(false) }
    // TODO: Does it work?
    val localSkipped by rememberSaveable { derivedStateOf { skippedIds } }

    OnboardingScaffold(
        pagerState = pagerState,
        onboardingPages = onboardingPages,
        bottomContent = { paddingValues ->
            CrossLoginBottomContent(
                Modifier
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues),
                accounts = accounts,
                skippedIds = skippedIds,
                titleColor = SwissTransferTheme.colors.primaryTextColor,
                descriptionColor = SwissTransferTheme.colors.secondaryTextColor,
                isLastPage = { isLastPage },
                onGoToNextPage = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                onLogin = { Log.e("gibran", "OnboardingScreen: Login") },
                onContinueWithSelectedAccounts = { Log.e("gibran", "OnboardingScreen: Continue with selected accounts") },
                onOpenAccountsBottomSheet = { showAccountsBottomSheet = true },
                onCreateAccount = { Log.e("gibran", "OnboardingScreen: New account") }
            )
        },
    )

    if (showAccountsBottomSheet) {
        SwissTransferBottomSheet(onDismissRequest = { showAccountsBottomSheet = false }) {
            CrossLoginListAccounts(
                accounts = accounts,
                skippedIds = skippedIds,
                onAccountClicked = { accountId: Long ->
                    if (accountId in localSkipped) localSkipped -= accountId else localSkipped += accountId
                },
                onAnotherAccountClicked = { Log.e("gibran", "OnboardingScreen: Use another account") },
                onSaveClicked = { skippedIds ->
                    Log.e("gibran", "OnboardingScreen: Save ${skippedIds.count()} accounts")

                },
            )
        }
    }
}

@Composable
fun CrossLoginListAccounts(
    accounts: SnapshotStateList<Unit>,
    skippedIds: SnapshotStateList<Long>,
    onAccountClicked: (Long) -> Unit,
    onAnotherAccountClicked: () -> Int,
    onSaveClicked: (List<Long>) -> Unit
) {
    TODO("Not yet implemented")
}

private const val ANIMATED_BUTTON = "ANIMATED_BUTTON"
private val FAB_SIZE = 64.dp

object CrossLoginBottomContentDefaults {
    val buttonShape = RoundedCornerShape(16.dp)
    val primaryButtonHeight = 56.dp
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CrossLoginBottomContent(
    modifier: Modifier = Modifier,
    accounts: SnapshotStateList<Unit>, // TODO: Use cross app login accounts
    skippedIds: SnapshotStateList<Long>,
    titleColor: Color, // TODO: Extract once we have a design system and shared color tokens
    descriptionColor: Color, // TODO: Extract once we have a design system and shared color tokens
    isLastPage: () -> Boolean,
    onGoToNextPage: () -> Unit,
    onLogin: () -> Unit,
    onContinueWithSelectedAccounts: () -> Unit,
    // Bottom sheet is not shared through the CrossLoginBottomContent because each app has its own style of bottom sheet defined
    // at the app's level
    onOpenAccountsBottomSheet: () -> Unit,
    onCreateAccount: () -> Unit,
    nextButtonShape: Shape = CrossLoginBottomContentDefaults.buttonShape,
    primaryButtonShape: Shape = CrossLoginBottomContentDefaults.buttonShape,
    primaryButtonHeight: Dp = CrossLoginBottomContentDefaults.primaryButtonHeight,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
    ) {
        SharedTransitionLayout {
            AnimatedContent(
                isLastPage()
            ) { isLastPage ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isLastPage && accounts.isNotEmpty()) {
                        CrossLoginSelectAccounts(onOpenAccountsBottomSheet, titleColor, accounts, skippedIds, descriptionColor)
                    }

                    if (isLastPage) {
                        ButtonExpanded(
                            text = if (accounts.isEmpty()) "Login" else "Continue with this account",
                            shape = primaryButtonShape,
                            modifier = Modifier
                                .sharedElement(
                                    rememberSharedContentState(key = ANIMATED_BUTTON),
                                    animatedVisibilityScope = this@AnimatedContent
                                )
                                .height(primaryButtonHeight),
                            onClick = if (accounts.isEmpty()) onLogin else onContinueWithSelectedAccounts,
                        )
                    } else {
                        ButtonNext(
                            onClick = onGoToNextPage,
                            shape = nextButtonShape,
                            modifier = Modifier.sharedElement(
                                rememberSharedContentState(key = ANIMATED_BUTTON),
                                animatedVisibilityScope = this@AnimatedContent
                            )
                        )
                    }

                    if (isLastPage && accounts.isEmpty()) {
                        Button(
                            modifier = Modifier.height(56.dp),
                            onClick = onCreateAccount,
                            colors = ButtonDefaults.textButtonColors()
                        ) { Text("Create an account") }
                    }
                }
            }
        }
    }
}

@Composable
private fun CrossLoginSelectAccounts(
    onOpenAccountsBottomSheet: () -> Unit,
    titleColor: Color,
    accounts: SnapshotStateList<Unit>,
    skippedIds: SnapshotStateList<Long>,
    descriptionColor: Color
) {
    Button(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onOpenAccountsBottomSheet,
        colors = ButtonDefaults.textButtonColors(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            Text("CROSS LOGIN APP", color = titleColor)
            Text("${accounts.count()} / ${skippedIds.count()}", color = descriptionColor)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ButtonNext(onClick: () -> Unit, shape: Shape, modifier: Modifier = Modifier) {
    val buttonWidth = FAB_SIZE
    val buttonHeight = FAB_SIZE

    BasicButton(
        modifier = modifier
            .height(buttonHeight)
            .padding(horizontal = Margin.Medium)
            .width(buttonWidth),
        onClick = onClick,
        shape = shape,
        contentPadding = PaddingValues(),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = AppImages.AppIcons.ArrowRight,
                contentDescription = stringResource(RCore.string.buttonNext),
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ButtonExpanded(text: String, shape: Shape, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var visibility by rememberSaveable { mutableFloatStateOf(0f) }

    val textVisibility by animateFloatAsState(
        targetValue = visibility,
        animationSpec = tween(),
        label = "Onboarding expanded button text visibility",
    )

    LaunchedEffect(Unit) { visibility = 1f }

    BasicButton(
        modifier = modifier
            .padding(horizontal = Margin.Medium)
            .width(400.dp),
        onClick = onClick,
        shape = shape,
        contentPadding = PaddingValues(),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = SwissTransferTheme.typography.bodyMedium,
                modifier = Modifier.graphicsLayer { alpha = textVisibility }
            )
        }
    }
}

@Composable
private fun Page.toOnboardingPage(isHighlighted: Map<Page, MutableState<Boolean>>, pagerState: PagerState, index: Int) =
    OnboardingPage(
        background = background.image(),
        illustration = {
            DefaultLottieIllustration(LottieCompositionSpec.RawRes(illustrationRes), { pagerState.currentPage == index })
        },
        text = {
            TitleAndDescription(
                page = this,
                isHighlighted = { isHighlighted[this]?.value ?: false }
            )
        }
    )

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

@OptIn(ExperimentalSharedTransitionApi::class)
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
        SharedTransitionLayout {
            AnimatedContent(
                isLastPage()
            ) { isLastPage ->
                Column {
                    AnimatedOnboardingButton(
                        isExpanded = isLastPage,
                        onClick = { if (isLastPage()) goToMainActivity() else goToNextPage() },
                        modifier = Modifier.sharedElement(
                            rememberSharedContentState(key = ANIMATED_BUTTON),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                    )

                    if (isLastPage) Button(onClick = {}) { Text("Hello") }
                }
            }
        }
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
