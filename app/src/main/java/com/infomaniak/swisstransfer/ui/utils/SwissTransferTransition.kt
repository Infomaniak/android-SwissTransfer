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
package com.infomaniak.swisstransfer.ui.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope?> { null }
val LocalAnimatedPaneVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalNavHostAnimatedVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalCurrentTopBarKey = staticCompositionLocalOf { TopBarKey.SinglePane }

object SwissTransferTransition {
    val enterTransition = fadeIn()
    val exitTransition = fadeOut()
    val transitionSpec = enterTransition togetherWith exitTransition
}

@Composable
fun Modifier.sharedTransitionAppBar(): Modifier {
    val sharedScope = LocalSharedTransitionScope.current ?: return this
    val navHostScope = LocalNavHostAnimatedVisibilityScope.current ?: return this
    val localScope = LocalAnimatedPaneVisibilityScope.current
    val currentTopBarKey = LocalCurrentTopBarKey.current

    val activeScope = if (navHostScope.isAnimating()) navHostScope else localScope

    if (activeScope == null) return this

    return with(sharedScope) {
        this@sharedTransitionAppBar then Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = currentTopBarKey),
            animatedVisibilityScope = activeScope,
        )
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable<T>(deepLinks = deepLinks) { entry ->
        CompositionLocalProvider(LocalNavHostAnimatedVisibilityScope provides this) {
            content(entry)
        }
    }
}

enum class TopBarKey {
    SinglePane, ListPane, DetailPane
}
