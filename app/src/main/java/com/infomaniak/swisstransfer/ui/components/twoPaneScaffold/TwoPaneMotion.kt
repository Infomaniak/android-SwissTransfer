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

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.snap
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@ExperimentalMaterial3AdaptiveApi
@Immutable
open class TwoPaneMotion(
    val positionAnimationSpec: FiniteAnimationSpec<IntOffset> = snap(),
    val sizeAnimationSpec: FiniteAnimationSpec<IntSize> = snap(),
    private val firstPaneEnterTransition: EnterTransition = EnterTransition.None,
    private val firstPaneExitTransition: ExitTransition = ExitTransition.None,
    private val secondPaneEnterTransition: EnterTransition = EnterTransition.None,
    private val secondPaneExitTransition: ExitTransition = ExitTransition.None,
) {
    fun enterTransition(role: ThreePaneScaffoldRole, paneOrder: TwoPaneScaffoldHorizontalOrder): EnterTransition {
        // Quick return in case this instance is the NoMotion one.
        if (this === NoMotion) return EnterTransition.None

        return when (paneOrder.indexOf(role)) {
            0 -> firstPaneEnterTransition
            else -> secondPaneEnterTransition
        }
    }

    fun exitTransition(role: ThreePaneScaffoldRole, paneOrder: TwoPaneScaffoldHorizontalOrder): ExitTransition {
        // Quick return in case this instance is the NoMotion one.
        if (this === NoMotion) return ExitTransition.None

        return when (paneOrder.indexOf(role)) {
            0 -> firstPaneExitTransition
            else -> secondPaneExitTransition
        }
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other !is TwoPaneMotion -> false
            this.positionAnimationSpec != other.positionAnimationSpec -> false
            this.sizeAnimationSpec != other.sizeAnimationSpec -> false
            this.firstPaneEnterTransition != other.firstPaneEnterTransition -> false
            this.firstPaneExitTransition != other.firstPaneExitTransition -> false
            this.secondPaneEnterTransition != other.secondPaneEnterTransition -> false
            this.secondPaneExitTransition != other.secondPaneExitTransition -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = positionAnimationSpec.hashCode()
        result = 31 * result + sizeAnimationSpec.hashCode()
        result = 31 * result + firstPaneEnterTransition.hashCode()
        result = 31 * result + firstPaneExitTransition.hashCode()
        result = 31 * result + secondPaneEnterTransition.hashCode()
        result = 31 * result + secondPaneExitTransition.hashCode()
        return result
    }

    companion object {
        /**
         * A ThreePaneMotion with all transitions set to [EnterTransition.None] and
         * [ExitTransition.None].
         */
        val NoMotion = TwoPaneMotion()

        @JvmStatic
        protected fun slideInFromLeft(spacerSize: Int) = slideInHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpec) {
            -it - spacerSize
        }

        @JvmStatic
        protected fun slideInFromLeftDelayed(spacerSize: Int) =
            slideInHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpecDelayed) { -it - spacerSize }

        @JvmStatic
        protected fun slideInFromRight(spacerSize: Int) =
            slideInHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpec) { it + spacerSize }

        @JvmStatic
        protected fun slideInFromRightDelayed(spacerSize: Int) =
            slideInHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpecDelayed) { it + spacerSize }

        @JvmStatic
        protected fun slideOutToLeft(spacerSize: Int) =
            slideOutHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpec) { -it - spacerSize }

        @JvmStatic
        protected fun slideOutToRight(spacerSize: Int) =
            slideOutHorizontally(TwoPaneMotionDefaults.PanePositionAnimationSpec) { it + spacerSize }

        @ExperimentalMaterial3AdaptiveApi
        object TwoPaneScaffoldDefaults {
            const val SECONDARY_PANE_PRIORITY = 1
            const val PRIMARY_PANE_PRIORITY = 2

            /**
             * The negative z-index of hidden panes to make visible panes always show upon hidden panes
             * during pane animations.
             */
            const val HIDDEN_PANE_Z_INDEX = -0.1f
        }
    }
}
