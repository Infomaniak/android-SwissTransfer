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
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.infomaniak.core.crossapplogin.back.BaseCrossAppLoginViewModel.AccountsCheckingState
import com.infomaniak.core.crossapplogin.back.ExternalAccount
import com.infomaniak.core.crossapplogin.front.components.CrossLoginBottomContent
import com.infomaniak.core.crossapplogin.front.components.NoCrossAppLoginAccountsContent
import com.infomaniak.core.crossapplogin.front.previews.AccountsCheckingStatePreviewParameter
import com.infomaniak.core.onboarding.IndicatorStyle
import com.infomaniak.core.onboarding.OnboardingPage
import com.infomaniak.core.onboarding.OnboardingScaffold
import com.infomaniak.core.onboarding.components.OnboardingComponents
import com.infomaniak.core.onboarding.components.OnboardingComponents.DefaultBackground
import com.infomaniak.core.onboarding.components.OnboardingComponents.DefaultLottieIllustration
import com.infomaniak.core.onboarding.components.OnboardingComponents.HighlightedTitleAndDescription
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewLargeWindow
import com.infomaniak.core.ui.compose.preview.PreviewSmallWindow
import com.infomaniak.core.ui.compose.theme.ThemedImage
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopLeft
import com.infomaniak.swisstransfer.ui.images.illus.onboarding.RadialGradientCornerTopRight
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
fun OnboardingScreen(
    shouldDisplayRequiredLogin: Boolean,
    goToMainActivity: () -> Unit,
    accountsCheckingState: () -> AccountsCheckingState,
    skippedIds: () -> Set<Long>,
    areLoginButtonsLoading: () -> Boolean,
    onLoginRequest: (accounts: List<ExternalAccount>) -> Unit,
    onCreateAccount: () -> Unit,
    onSaveSkippedAccounts: (Set<Long>) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val pages: List<Page> = if (shouldDisplayRequiredLogin) RequiredLoginPage.entries else OptionalLoginPage.entries

    val pagerState = rememberPagerState(pageCount = { pages.size })

    val isHighlighted = pages.associateWith { rememberSaveable { mutableStateOf(false) } }

    // Start the highlighting of the text when the associated page is reached in the HorizontalPager
    LaunchedEffect(pagerState.currentPage) {
        val currentPage = pages[pagerState.currentPage]
        isHighlighted[currentPage]?.value = true
    }

    OnboardingScaffold(
        pagerState = pagerState,
        onboardingPages = pages.mapIndexed { index, page -> page.toOnboardingPage(isHighlighted, pagerState, index) },
        bottomContent = { paddingValues ->
            OnboardingComponents.CrossLoginBottomContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues),
                pagerState = pagerState,
                isLoginButtonLoading = areLoginButtonsLoading,
                accountsCheckingState = accountsCheckingState,
                skippedIds = skippedIds,
                onContinueWithSelectedAccounts = { selectedAccounts -> onLoginRequest(selectedAccounts) },
                onUseAnotherAccountClicked = { onLoginRequest(emptyList()) },
                onSaveSkippedAccounts = onSaveSkippedAccounts,
                noCrossAppLoginAccountsContent = if (shouldDisplayRequiredLogin) {
                    NoCrossAppLoginAccountsContent.accountRequired(
                        onLogin = { onLoginRequest(emptyList()) },
                        onCreateAccount = onCreateAccount,
                        isLoginButtonLoading = areLoginButtonsLoading,
                        isSignUpButtonLoading = areLoginButtonsLoading
                    )
                } else {
                    NoCrossAppLoginAccountsContent.accountOptional { goToMainActivity() }
                }
            )
        },
        indicatorStyle = IndicatorStyle(
            inactiveColor = SwissTransferTheme.materialColors.outlineVariant,
            activeColor = SwissTransferTheme.materialColors.primary,
            inactiveSize = 8.dp,
            activeWidth = 16.dp,
            indicatorSpacing = Margin.Mini,
        ),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    )
}

private interface Page {
    @Composable
    fun toOnboardingPage(isHighlighted: Map<Page, MutableState<Boolean>>, pagerState: PagerState, index: Int): OnboardingPage
}

private const val HIGHLIGHT_ANGLE = 3.0

private enum class OptionalLoginPage(
    val background: ThemedImage,
    @RawRes val illustrationRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val subtitleTemplateRes: Int,
    @StringRes val subtitleArgumentRes: Int,
    val highlightAngleDegree: Double,
) : Page {
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
    );

    @Composable
    override fun toOnboardingPage(
        isHighlighted: Map<Page, MutableState<Boolean>>,
        pagerState: PagerState,
        index: Int,
    ) = OnboardingPage(
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
}

private enum class RequiredLoginPage(
    val background: ThemedImage,
    @RawRes val illustrationRes: Int,
    @StringRes val titleRes: Int,
) : Page {
    Login(
        background = AppIllus.RadialGradientCornerTopLeft,
        illustrationRes = R.raw.two_locks_intertwined_stars,
        titleRes = R.string.appName, // TODO
    );

    @Composable
    override fun toOnboardingPage(
        isHighlighted: Map<Page, MutableState<Boolean>>,
        pagerState: PagerState,
        index: Int,
    ) = OnboardingPage(
        background = { DefaultBackground(background.image()) },
        illustration = {
            DefaultLottieIllustration(lottieRawRes = illustrationRes, isCurrentPageVisible = { pagerState.currentPage == index })
        },
        text = { Text(stringResource(titleRes), style = SwissTransferTheme.typography.h1) }
    )
}

@PreviewSmallWindow
@PreviewLargeWindow
@Composable
private fun OnboardingScreenPreview(@PreviewParameter(AccountsCheckingStatePreviewParameter::class) accounts: AccountsCheckingState) {
    SwissTransferTheme {
        Surface {
            OnboardingScreen(
                shouldDisplayRequiredLogin = false,
                goToMainActivity = {},
                accountsCheckingState = { accounts },
                skippedIds = { emptySet() },
                areLoginButtonsLoading = { false },
                onLoginRequest = {},
                onCreateAccount = {},
                onSaveSkippedAccounts = {},
                snackbarHostState = remember { SnackbarHostState() },
            )
        }
    }
}
