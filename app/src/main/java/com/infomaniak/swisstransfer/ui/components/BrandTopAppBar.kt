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
package com.infomaniak.swisstransfer.ui.components

import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize.Companion.StepBased
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.infomaniak.core.ui.compose.margin.Margin
import com.infomaniak.core.ui.compose.preview.PreviewAllWindows
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus
import com.infomaniak.swisstransfer.ui.images.illus.LogoInfomaniak
import com.infomaniak.swisstransfer.ui.images.illus.LogoSwissTransfer
import com.infomaniak.swisstransfer.ui.screen.main.LocalAnimatedVisibilityScope
import com.infomaniak.swisstransfer.ui.screen.main.LocalCurrentTopBarKey
import com.infomaniak.swisstransfer.ui.screen.main.LocalNavHostAnimatedVisibilityScope
import com.infomaniak.swisstransfer.ui.screen.main.LocalSharedTransitionScope
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
fun BrandTopAppBar() {
    val toolbarTextColor = SwissTransferTheme.colors.toolbarTextColor

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SwissTransferTheme.materialColors.tertiary,
            titleContentColor = SwissTransferTheme.colors.toolbarTextColor,
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(imageVector = AppIllus.LogoInfomaniak, contentDescription = null)
                Spacer(Modifier.width(Margin.Medium))
                VerticalDivider(modifier = Modifier.height(Margin.Large), color = SwissTransferTheme.colors.toolbarTextColor)
                Spacer(Modifier.width(Margin.Medium))
                Image(imageVector = AppIllus.LogoSwissTransfer, contentDescription = null)
                Spacer(Modifier.width(Margin.Mini))
                BasicText(
                    text = stringResource(id = R.string.appName),
                    color = ColorProducer { toolbarTextColor },
                    autoSize = StepBased(minFontSize = 8.sp, stepSize = 0.5.sp, maxFontSize = LocalTextStyle.current.fontSize),
                    maxLines = 1,
                )
            }
        },
        modifier = Modifier.sharedTransitionAppBar(),
    )
}

@Composable
fun Modifier.sharedTransitionAppBar(): Modifier {
    val sharedScope = LocalSharedTransitionScope.current
    val localScope = LocalAnimatedVisibilityScope.current
    val navHostScope = LocalNavHostAnimatedVisibilityScope.current

    if (sharedScope != null) {
        val isNavHostAnimating = navHostScope?.transition?.let { it.currentState != it.targetState } == true
        val activeScope = if (isNavHostAnimating) navHostScope else localScope

        if (activeScope != null) {
            val currentTopBarKey = LocalCurrentTopBarKey.current

            with(sharedScope) {
                return this@sharedTransitionAppBar then Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState(key = currentTopBarKey),
                    animatedVisibilityScope = activeScope,
                )
            }
        }
    }

    return this
}

enum class TopBarKey(key: String) {
    FIRST_TOP_BAR(key = "firstTopBar"), SECOND_TOP_BAR(key = "secondTopBar")
}

@PreviewAllWindows
@Composable
private fun BrandTopAppBarPreview() {
    SwissTransferTheme {
        BrandTopAppBar()
    }
}
