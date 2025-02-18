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

import androidx.annotation.VisibleForTesting
import androidx.collection.IntList
import androidx.collection.MutableIntList
import androidx.collection.emptyIntList
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.gestures.DragScope
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.isSpecified
import kotlinx.coroutines.coroutineScope

@ExperimentalMaterial3AdaptiveApi
@Stable
class PaneExpansionState(val anchors: List<PaneExpansionAnchor> = emptyList()) : DraggableState {
    private var firstPaneWidthState by mutableIntStateOf(UNSPECIFIED_WIDTH)
    private var firstPanePercentageState by mutableFloatStateOf(Float.NaN)
    private var currentDraggingOffsetState by mutableIntStateOf(UNSPECIFIED_WIDTH)

    var firstPaneWidth: Int
        set(value) {
            firstPanePercentageState = Float.NaN
            currentDraggingOffsetState = UNSPECIFIED_WIDTH
            firstPaneWidthState = value.coerceIn(0, maxExpansionWidth)
        }
        get() = firstPaneWidthState

    var firstPanePercentage: Float
        set(value) {
            require(value in 0f..1f)
            firstPaneWidthState = UNSPECIFIED_WIDTH
            currentDraggingOffsetState = UNSPECIFIED_WIDTH
            firstPanePercentageState = value
        }
        get() = firstPanePercentageState

    internal var currentDraggingOffset
        get() = currentDraggingOffsetState
        private set(value) {
            if (value == currentDraggingOffsetState) return

            val coercedValue = value.coerceIn(0, maxExpansionWidth)
            currentDraggingOffsetState = coercedValue
            currentMeasuredDraggingOffset = coercedValue
        }

    internal var isDragging by mutableStateOf(false)
        private set

    internal var isSettling by mutableStateOf(false)
        private set

    internal val isDraggingOrSettling
        get() = isDragging || isSettling

    @VisibleForTesting
    internal var maxExpansionWidth = 0
        private set

    // Use this field to store the dragging offset decided by measuring instead of dragging to
    // prevent redundant re-composition.
    @VisibleForTesting
    internal var currentMeasuredDraggingOffset = UNSPECIFIED_WIDTH
        private set

    private var anchorPositions: IntList = emptyIntList()

    private val dragScope: DragScope = object : DragScope {
        override fun dragBy(pixels: Float): Unit = dispatchRawDelta(pixels)
    }

    private val dragMutex = MutatorMutex()

    fun isUnspecified(): Boolean =
        firstPaneWidthState == UNSPECIFIED_WIDTH && firstPanePercentage.isNaN() && currentDraggingOffset == UNSPECIFIED_WIDTH

    override fun dispatchRawDelta(delta: Float) {
        if (currentMeasuredDraggingOffset == UNSPECIFIED_WIDTH) return
        currentDraggingOffset = (currentMeasuredDraggingOffset + delta).toInt()
    }

    override suspend fun drag(dragPriority: MutatePriority, block: suspend DragScope.() -> Unit) = coroutineScope {
        isDragging = true
        dragMutex.mutateWith(dragScope, dragPriority, block)
        isDragging = false
    }

    internal fun onMeasured(measuredWidth: Int, density: Density) {
        if (measuredWidth == maxExpansionWidth) return

        maxExpansionWidth = measuredWidth
        if (firstPaneWidth != UNSPECIFIED_WIDTH) firstPaneWidth = firstPaneWidth
        anchorPositions = anchors.toPositions(measuredWidth, density)
    }

    internal fun onExpansionOffsetMeasured(measuredOffset: Int) {
        currentMeasuredDraggingOffset = measuredOffset
    }

    fun List<PaneExpansionAnchor>.toPositions(maxExpansionWidth: Int, density: Density): IntList {
        val anchors = MutableIntList(size)
        forEach { anchor ->
            if (anchor.startOffset.isSpecified) {
                val position = with(density) { anchor.startOffset.toPx() }.toInt().let { if (it < 0) maxExpansionWidth + it else it }
                if (position in 0..maxExpansionWidth) anchors.add(position)
            } else {
                anchors.add(maxExpansionWidth * anchor.percentage / 100)
            }
        }
        anchors.sort()
        return anchors
    }

    companion object {
        const val UNSPECIFIED_WIDTH = -1
    }
}
