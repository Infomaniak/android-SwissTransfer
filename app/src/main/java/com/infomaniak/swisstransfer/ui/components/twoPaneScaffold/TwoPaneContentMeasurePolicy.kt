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
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.MultiContentMeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.roundToIntRect
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import com.infomaniak.swisstransfer.ui.components.twoPaneScaffold.PaneExpansionState.Companion.UNSPECIFIED_WIDTH
import com.infomaniak.swisstransfer.ui.components.twoPaneScaffold.TwoPaneMotion.Companion.TwoPaneScaffoldDefaults
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class TwoPaneContentMeasurePolicy(
    scaffoldDirective: PaneScaffoldDirective,
    scaffoldValue: ThreePaneScaffoldValue,
    val paneExpansionState: PaneExpansionState,
    paneOrder: TwoPaneScaffoldHorizontalOrder,
) : MultiContentMeasurePolicy {
    var scaffoldDirective by mutableStateOf(scaffoldDirective)
    var scaffoldValue by mutableStateOf(scaffoldValue)
    var paneOrder by mutableStateOf(paneOrder)

    /**
     * Data class that is used to store the position and width of an expanded pane to be reused when
     * the pane is being hidden.
     */
    data class PanePlacement(var positionX: Int = 0, var measuredWidth: Int = 0)

    private val placementsCache = mapOf(
        ThreePaneScaffoldRole.Primary to PanePlacement(),
        ThreePaneScaffoldRole.Secondary to PanePlacement(),
        ThreePaneScaffoldRole.Tertiary to PanePlacement()
    )

    override fun MeasureScope.measure(
        measurables: List<List<Measurable>>, constraints: Constraints
    ): MeasureResult {
        val primaryMeasurable = measurables[0]
        val secondaryMeasurable = measurables[1]
        return layout(constraints.maxWidth, constraints.maxHeight) {
            if (coordinates == null) {
                return@layout
            }
            val visiblePanes = getPanesMeasurable(
                paneOrder = paneOrder,
                primaryMeasurable = primaryMeasurable,
                scaffoldValue = scaffoldValue,
                secondaryMeasurable = secondaryMeasurable
            ) {
                it != PaneAdaptedValue.Hidden
            }

            val hiddenPanes = getPanesMeasurable(
                paneOrder = paneOrder,
                primaryMeasurable = primaryMeasurable,
                scaffoldValue = scaffoldValue,
                secondaryMeasurable = secondaryMeasurable
            ) {
                it == PaneAdaptedValue.Hidden
            }

            val verticalSpacerSize = scaffoldDirective.horizontalPartitionSpacerSize.roundToPx()
            val outerBounds = IntRect(0, 0, constraints.maxWidth, constraints.maxHeight)
            if (!isLookingAhead) {
                paneExpansionState.onMeasured(outerBounds.width, this@measure)
            }

            if (!paneExpansionState.isUnspecified() && visiblePanes.size == 2) {
                // Pane expansion should override everything
                if (paneExpansionState.currentDraggingOffset != UNSPECIFIED_WIDTH) {
                    // Respect the user dragging result if there's any
                    val halfSpacerSize = verticalSpacerSize / 2
                    if (paneExpansionState.currentDraggingOffset <= halfSpacerSize) {
                        val bounds = if (paneExpansionState.isDraggingOrSettling) {
                            outerBounds.copy(
                                left = paneExpansionState.currentDraggingOffset * 2 + outerBounds.left
                            )
                        } else {
                            outerBounds
                        }
                        measureAndPlacePaneWithLocalBounds(bounds, visiblePanes[1], isLookingAhead)
                    } else if (paneExpansionState.currentDraggingOffset >= outerBounds.width - halfSpacerSize) {
                        val bounds = if (paneExpansionState.isDraggingOrSettling) {
                            outerBounds.copy(
                                right = paneExpansionState.currentDraggingOffset * 2 - outerBounds.right
                            )
                        } else {
                            outerBounds
                        }
                        measureAndPlacePaneWithLocalBounds(bounds, visiblePanes[0], isLookingAhead)
                    } else {
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds.copy(
                                right = paneExpansionState.currentDraggingOffset - halfSpacerSize
                            ), visiblePanes[0], isLookingAhead
                        )
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds.copy(
                                left = paneExpansionState.currentDraggingOffset + halfSpacerSize
                            ), visiblePanes[1], isLookingAhead
                        )
                    }
                } else { // Pane expansion settings from non-dragging results
                    val availableWidth = constraints.maxWidth
                    if (paneExpansionState.firstPaneWidth == 0 || paneExpansionState.firstPanePercentage == 0f) {
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds, visiblePanes[1], isLookingAhead
                        )
                    } else if (paneExpansionState.firstPaneWidth >= availableWidth - verticalSpacerSize || paneExpansionState.firstPanePercentage >= 1f) {
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds, visiblePanes[0], isLookingAhead
                        )
                    } else {
                        val firstPaneWidth = if (paneExpansionState.firstPaneWidth != UNSPECIFIED_WIDTH) {
                            paneExpansionState.firstPaneWidth
                        } else {
                            (paneExpansionState.firstPanePercentage * (availableWidth - verticalSpacerSize)).toInt()
                        }
                        val firstPaneRight = outerBounds.left + firstPaneWidth
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds.copy(right = firstPaneRight), visiblePanes[0], isLookingAhead
                        )
                        measureAndPlacePaneWithLocalBounds(
                            outerBounds.copy(left = firstPaneRight + verticalSpacerSize), visiblePanes[1], isLookingAhead
                        )
                    }
                }
            } else if (scaffoldDirective.excludedBounds.isNotEmpty()) {
                val layoutBounds = coordinates!!.boundsInWindow()
                val layoutPhysicalPartitions = mutableListOf<Rect>()
                var actualLeft = layoutBounds.left
                var actualRight = layoutBounds.right
                val actualTop = layoutBounds.top
                val actualBottom = layoutBounds.bottom
                // Assume hinge bounds are sorted from left to right, non-overlapped.
                @Suppress("ListIterator") scaffoldDirective.excludedBounds.forEach { hingeBound ->
                    if (hingeBound.left <= actualLeft) {
                        // The hinge is at the left of the layout, adjust the left edge of
                        // the current partition to the actual displayable bounds.
                        actualLeft = max(actualLeft, hingeBound.right)
                    } else if (hingeBound.right >= actualRight) {
                        // The hinge is right at the right of the layout and there's no more
                        // room for more partitions, adjust the right edge of the current
                        // partition to the actual displayable bounds.
                        actualRight = min(hingeBound.left, actualRight)
                        return@forEach
                    } else {
                        // The hinge is inside the layout, add the current partition to the list
                        // and move the left edge of the next partition to the right of the
                        // hinge.
                        layoutPhysicalPartitions.add(
                            Rect(actualLeft, actualTop, hingeBound.left, actualBottom)
                        )
                        actualLeft = max(hingeBound.right, hingeBound.left + verticalSpacerSize)
                    }
                }
                if (actualLeft < actualRight) {
                    // The last partition
                    layoutPhysicalPartitions.add(
                        Rect(actualLeft, actualTop, actualRight, actualBottom)
                    )
                }
                if (layoutPhysicalPartitions.isEmpty()) {
                    // Display nothing
                } else if (layoutPhysicalPartitions.size == 1) {
                    measureAndPlacePanes(
                        layoutPhysicalPartitions[0], verticalSpacerSize, visiblePanes, isLookingAhead
                    )
                } else if (layoutPhysicalPartitions.size < visiblePanes.size) {
                    // Note that the only possible situation is we have only two physical partitions
                    // but three expanded panes to show. In this case fit two panes in the larger
                    // partition.
                    if (layoutPhysicalPartitions[0].width > layoutPhysicalPartitions[1].width) {
                        measureAndPlacePanes(
                            layoutPhysicalPartitions[0], verticalSpacerSize, visiblePanes.subList(0, 2), isLookingAhead
                        )
                        measureAndPlacePane(
                            layoutPhysicalPartitions[1], visiblePanes[2], isLookingAhead
                        )
                    } else {
                        measureAndPlacePane(
                            layoutPhysicalPartitions[0], visiblePanes[0], isLookingAhead
                        )
                        measureAndPlacePanes(
                            layoutPhysicalPartitions[1], verticalSpacerSize, visiblePanes.subList(1, 3), isLookingAhead
                        )
                    }
                } else {
                    // Layout each visible pane in a physical partition
                    visiblePanes.fastForEachIndexed { index, paneMeasurable ->
                        measureAndPlacePane(
                            layoutPhysicalPartitions[index], paneMeasurable, isLookingAhead
                        )
                    }
                }
            } else {
                measureAndPlacePanesWithLocalBounds(
                    outerBounds, verticalSpacerSize, visiblePanes, isLookingAhead
                )
            }

            if (!isLookingAhead) {
                paneExpansionState.onExpansionOffsetMeasured(UNSPECIFIED_WIDTH)
            }

            // Place the hidden panes to ensure a proper motion at the AnimatedVisibility,
            // otherwise the pane will be gone immediately when it's hidden.
            // The placement is done using the outerBounds, as the placementsCache holds
            // absolute position values.
            placeHiddenPanes(outerBounds.top, outerBounds.height, hiddenPanes)
        }
    }

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    private fun MeasureScope.getPanesMeasurable(
        paneOrder: TwoPaneScaffoldHorizontalOrder,
        primaryMeasurable: List<Measurable>,
        scaffoldValue: ThreePaneScaffoldValue,
        secondaryMeasurable: List<Measurable>,
        predicate: (PaneAdaptedValue) -> Boolean
    ): List<PaneMeasurable> {
        return buildList {
            paneOrder.forEach { role ->
                if (predicate(scaffoldValue[role])) {
                    when (role) {
                        ThreePaneScaffoldRole.Primary -> {
                            createPaneMeasurableIfNeeded(
                                primaryMeasurable,
                                TwoPaneScaffoldDefaults.PRIMARY_PANE_PRIORITY,
                                role,
                                scaffoldDirective.defaultPanePreferredWidth.roundToPx()
                            )
                        }
                        ThreePaneScaffoldRole.Secondary -> {
                            createPaneMeasurableIfNeeded(
                                secondaryMeasurable,
                                TwoPaneScaffoldDefaults.SECONDARY_PANE_PRIORITY,
                                role,
                                scaffoldDirective.defaultPanePreferredWidth.roundToPx()
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun MutableList<PaneMeasurable>.createPaneMeasurableIfNeeded(
        measurable: List<Measurable>, priority: Int, role: ThreePaneScaffoldRole, defaultPreferredWidth: Int
    ) {
        if (measurable.isNotEmpty()) add(PaneMeasurable(measurable[0], priority, role, defaultPreferredWidth))
    }

    private fun Placeable.PlacementScope.measureAndPlacePane(
        partitionBounds: Rect, measurable: PaneMeasurable, isLookingAhead: Boolean
    ) = measureAndPlacePaneWithLocalBounds(getLocalBounds(partitionBounds), measurable, isLookingAhead)

    private fun Placeable.PlacementScope.measureAndPlacePaneWithLocalBounds(
        localBounds: IntRect, measurable: PaneMeasurable, isLookingAhead: Boolean
    ) {
        with(measurable) {
            measureAndPlace(
                localBounds.width,
                localBounds.height,
                localBounds.left,
                localBounds.top,
                if (isLookingAhead) placementsCache else null
            )
        }
    }

    private fun Placeable.PlacementScope.measureAndPlacePanes(
        partitionBounds: Rect, spacerSize: Int, measurable: List<PaneMeasurable>, isLookingAhead: Boolean
    ) {
        measureAndPlacePanesWithLocalBounds(
            getLocalBounds(partitionBounds), spacerSize, measurable, isLookingAhead
        )
    }

    private fun Placeable.PlacementScope.measureAndPlacePanesWithLocalBounds(
        partitionBounds: IntRect, spacerSize: Int, measurable: List<PaneMeasurable>, isLookingAhead: Boolean
    ) {
        if (measurable.isEmpty()) {
            return
        }
        val allocatableWidth = partitionBounds.width - (measurable.size - 1) * spacerSize
        val totalPreferredWidth = measurable.sumOf { it.measuringWidth }
        if (allocatableWidth > totalPreferredWidth) {
            // Allocate the remaining space to the pane with the highest priority.
            measurable.maxBy { it.priority }.measuringWidth += allocatableWidth - totalPreferredWidth
        } else if (allocatableWidth < totalPreferredWidth) {
            // Scale down all panes to fit in the available space.
            val scale = allocatableWidth.toFloat() / totalPreferredWidth
            measurable.fastForEach { it.measuringWidth = (it.measuringWidth * scale).toInt() }
        }
        var positionX = partitionBounds.left
        measurable.fastForEach {
            with(it) {
                measureAndPlace(
                    it.measuringWidth,
                    partitionBounds.height,
                    positionX,
                    partitionBounds.top,
                    if (isLookingAhead) placementsCache else null
                )
            }
            positionX += it.measuredWidth + spacerSize
        }
    }

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    private fun Placeable.PlacementScope.placeHiddenPanes(
        partitionTop: Int, partitionHeight: Int, measurable: List<PaneMeasurable>
    ) {
        // When panes are being hidden, apply each pane's width and position from the cache to
        // maintain the those before it's hidden by the AnimatedVisibility.
        measurable.fastForEach {
            if (!it.isAnimatedPane) {
                // When panes are not animated, we don't need to measure and place them.
                return
            }
            val cachedPanePlacement = placementsCache[it.role]!!
            with(it) {
                measureAndPlace(
                    cachedPanePlacement.measuredWidth,
                    partitionHeight,
                    cachedPanePlacement.positionX,
                    partitionTop,
                    null,
                    TwoPaneScaffoldDefaults.HIDDEN_PANE_Z_INDEX
                )
            }
        }
    }

    private fun Placeable.PlacementScope.getLocalBounds(bounds: Rect): IntRect {
        return bounds.translate(coordinates!!.windowToLocal(Offset.Zero)).roundToIntRect()
    }

    companion object {
        private class PaneMeasurable(
            val measurable: Measurable, val priority: Int, val role: ThreePaneScaffoldRole, defaultPreferredWidth: Int
        ) {
            private val data = ((measurable.parentData as? PaneScaffoldParentData) ?: PaneScaffoldParentData())

            var measuringWidth = if (data.preferredWidth == null || data.preferredWidth!!.isNaN()) {
                defaultPreferredWidth
            } else {
                data.preferredWidth!!.toInt()
            }

            val isAnimatedPane = data.isAnimatedPane

            var measuredWidth = 0
                private set

            var measuredHeight = 0
                private set

            var placedPositionX = 0
                private set

            var placedPositionY = 0
                private set

            var measuredAndPlaced = false
                private set

            fun Placeable.PlacementScope.measureAndPlace(
                width: Int,
                height: Int,
                positionX: Int,
                positionY: Int,
                placementsCache: Map<ThreePaneScaffoldRole, PanePlacement>?,
                zIndex: Float = 0f
            ) {
                measuredWidth = width
                measuredHeight = height
                placedPositionX = positionX
                placedPositionY = positionY
                measurable.measure(Constraints.fixed(width, height)).place(positionX, positionY, zIndex)
                measuredAndPlaced = true

                // Cache the values to be used when this measurable's role is being hidden.
                // See placeHiddenPanes.
                if (placementsCache != null) {
                    val cachedPanePlacement = placementsCache[role]!!
                    cachedPanePlacement.measuredWidth = width
                    cachedPanePlacement.positionX = positionX
                }
            }
        }
    }
}
