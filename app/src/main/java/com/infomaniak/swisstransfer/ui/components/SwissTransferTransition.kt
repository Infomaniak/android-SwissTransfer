/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope?> { null }
val LocalAnimatedPaneVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalNavHostAnimatedVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalCurrentTopBarKey = staticCompositionLocalOf { TopBarKey.FIRST }

object SwissTransferTransition {
    val enterTransition = fadeIn()
    val exitTransition = fadeOut()
}

@Composable
fun Modifier.sharedTransitionAppBar(): Modifier {
    val sharedScope = LocalSharedTransitionScope.current
    val localScope = LocalAnimatedPaneVisibilityScope.current
    val navHostScope = LocalNavHostAnimatedVisibilityScope.current
    val currentTopBarKey = LocalCurrentTopBarKey.current

    val activeScope = remember(navHostScope, localScope) {
        val isNavHostAnimating = navHostScope?.transition?.let { it.currentState != it.targetState } == true
        if (isNavHostAnimating) navHostScope else localScope
    }

    if (activeScope == null || sharedScope == null) return this

    return with(sharedScope) {
        this@sharedTransitionAppBar then Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = currentTopBarKey),
            animatedVisibilityScope = activeScope,
        )
    }
}

enum class TopBarKey {
    FIRST, SECOND, THIRD
}
