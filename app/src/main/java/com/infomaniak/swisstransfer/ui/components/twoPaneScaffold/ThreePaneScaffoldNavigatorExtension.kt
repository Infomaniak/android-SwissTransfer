/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.components.twoPaneScaffold

import android.content.Context
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.infomaniak.swisstransfer.ui.utils.isWindowLarge

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private val backBehavior = BackNavigationBehavior.PopUntilContentChange

/**
 * Keep the DetailPane's content in memory so that it's always available while navigating back.
 * When leaving the DetailPane, its content becomes `null`, which creates a visual glitch in the back animation.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> ThreePaneScaffoldNavigator<T>.safeCurrentContent(): T? {
    var oldContent by rememberSaveable { mutableStateOf<T?>(null) }

    val newContent = currentDestination?.content ?: oldContent
    currentDestination?.content?.let { oldContent = it }

    return newContent
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.canPopBackStack(): Boolean {
    return canNavigateBack(backBehavior)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.popBackStack(): Boolean {
    return navigateBack(backBehavior)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun <T> ThreePaneScaffoldNavigator<T>.selectItem(context: Context, windowAdaptiveInfo: WindowAdaptiveInfo, item: T) {
    if (windowAdaptiveInfo.isWindowLarge(context)) navigateBack()
    navigateTo(ListDetailPaneScaffoldRole.Detail, item)
}
