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
package com.infomaniak.swisstransfer.ui.screen.onboarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infomaniak.core.compose.margin.Margin
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.components.SwissTransferButton
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.icons.ArrowRight
import com.infomaniak.swisstransfer.ui.theme.Dimens
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import com.infomaniak.core.R as RCore

private const val BUTTON_ANIM_DURATION = 300
private const val BUTTON_ANIM_DELAY = BUTTON_ANIM_DURATION / 2
private const val BUTTON_ANIM_NO_DELAY = 0
private val FAB_SIZE = 64.dp

@Composable
fun AnimatedOnboardingButton(isExpanded: () -> Boolean, onClick: () -> Unit) {
    val buttonWidth by animateDpAsState(
        targetValue = if (isExpanded()) Dimens.SingleButtonMaxWidth else FAB_SIZE,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY),
        label = "Onboarding button width",
    )

    val buttonHeight by animateDpAsState(
        targetValue = if (isExpanded()) Dimens.LargeButtonHeight else FAB_SIZE,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY),
        label = "Onboarding button height",
    )

    val arrowDelay = if (isExpanded()) BUTTON_ANIM_NO_DELAY else BUTTON_ANIM_DELAY
    val arrowVisibility by animateFloatAsState(
        targetValue = if (isExpanded()) 0f else 1f,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY, delayMillis = arrowDelay),
        label = "Onboarding arrow visibility",
    )

    val textDelay = if (isExpanded()) BUTTON_ANIM_DELAY else BUTTON_ANIM_NO_DELAY
    val textVisibility by animateFloatAsState(
        targetValue = if (isExpanded()) 1f else 0f,
        animationSpec = tween(durationMillis = BUTTON_ANIM_DELAY, delayMillis = textDelay),
        label = "Onboarding text visibility",
    )

    SwissTransferButton(
        modifier = Modifier
            .height(buttonHeight)
            .padding(horizontal = Margin.Medium)
            .width(buttonWidth),
        onClick = onClick,
        contentPadding = PaddingValues(),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier.alpha(arrowVisibility),
                imageVector = AppImages.AppIcons.ArrowRight,
                contentDescription = stringResource(RCore.string.buttonNext),
            )
            Text(
                modifier = Modifier.alpha(textVisibility),
                text = stringResource(id = R.string.buttonStart),
                style = SwissTransferTheme.typography.bodyMedium,
            )
        }
    }
}

@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Box(modifier = Modifier.size(width = 400.dp, height = 70.dp), contentAlignment = Alignment.Center) {
            var currentPage by remember { mutableStateOf(false) }

            AnimatedOnboardingButton(
                isExpanded = { currentPage },
                onClick = { currentPage = !currentPage },
            )
        }
    }
}
