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

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.runtime.Immutable

@ExperimentalMaterial3AdaptiveApi
@Immutable
class TwoPaneScaffoldHorizontalOrder(
    val firstPane: ThreePaneScaffoldRole, val secondPane: ThreePaneScaffoldRole
) : PaneScaffoldHorizontalOrder<ThreePaneScaffoldRole> {
    init {
        require(firstPane != secondPane)
    }

    override val size = 2

    override fun indexOf(role: ThreePaneScaffoldRole) = when (role) {
        firstPane -> 0
        secondPane -> 1
        else -> -1
    }

    override fun forEach(action: (ThreePaneScaffoldRole) -> Unit) {
        action(firstPane)
        action(secondPane)
    }

    override fun forEachIndexed(action: (Int, ThreePaneScaffoldRole) -> Unit) {
        action(0, firstPane)
        action(1, secondPane)
    }

    override fun forEachIndexedReversed(action: (Int, ThreePaneScaffoldRole) -> Unit) {
        action(1, secondPane)
        action(0, firstPane)
    }

    override fun equals(other: Any?): Boolean {
        return when {
            other === this -> true
            other !is TwoPaneScaffoldHorizontalOrder -> false
            firstPane != other.firstPane -> false
            secondPane != other.secondPane -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = firstPane.hashCode()
        result = 31 * result + secondPane.hashCode()
        return result
    }

    companion object {
        val defaultPaneOrder = TwoPaneScaffoldHorizontalOrder(ThreePaneScaffoldRole.Secondary, ThreePaneScaffoldRole.Primary)
    }
}

@ExperimentalMaterial3AdaptiveApi
interface PaneScaffoldHorizontalOrder<T> {
    val size: Int

    fun indexOf(role: T): Int
    fun forEach(action: (T) -> Unit)
    fun forEachIndexed(action: (Int, T) -> Unit)
    fun forEachIndexedReversed(action: (Int, T) -> Unit)
}
