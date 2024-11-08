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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewLightAndDark
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HighlighterText(
    templateRes: Int,
    argumentRes: Int,
    style: TextStyle,
    verticalPadding: Float = 2f, // TODO
    horizontalPadding: Float = 24f, // TODO
    angleDegrees: Float = -3f, // TODO
) {
    val template = stringResource(templateRes)
    val argument = stringResource(argumentRes)
    val text = String.format(template, argument)

    val highlighterColor = SwissTransferTheme.colors.highlighterColor

    var highlighterPath by remember { mutableStateOf<Path?>(null) }

    Text(
        text = text,
        style = style,
        onTextLayout = { layoutResult ->
            val boundingBoxes = layoutResult.getArgumentBoundingBoxes(text, argument)
            highlighterPath = boundingBoxes.transformForHighlighterStyle(verticalPadding, horizontalPadding, angleDegrees)
        },
        modifier = Modifier.drawBehind {
            highlighterPath?.let {
                drawPath(it, style = Fill, color = highlighterColor)
            }
        }
    )
}

private fun TextLayoutResult.getArgumentBoundingBoxes(text: String, argument: String): List<Rect> {
    val startIndex = text.indexOf(argument)
    return getBoundingBoxesForRange(start = startIndex, end = startIndex + argument.count())
}

private fun List<Rect>.transformForHighlighterStyle(
    verticalPadding: Float,
    horizontalPadding: Float,
    angleDegrees: Float,
): Path = Path().apply {
    forEach { boundingBox ->
        addPath(boundingBox.transformForHighlighterStyle(verticalPadding, horizontalPadding, angleDegrees))
    }
}

private fun Rect.transformForHighlighterStyle(verticalPadding: Float, horizontalPadding: Float, angleDegrees: Float): Path {
    return getRotatedRectanglePath(
        Rect(
            left = left - horizontalPadding,
            top = top - verticalPadding,
            right = right + horizontalPadding,
            bottom = bottom + verticalPadding,
        ),
        angleDegrees = angleDegrees,
    )
}

private fun getRotatedRectanglePath(rect: Rect, angleDegrees: Float): Path {
    val centerX = rect.left + (rect.width / 2)
    val centerY = rect.top + (rect.height / 2)

    val angleRadians = Math.toRadians(angleDegrees.toDouble())

    // Function to rotate a point around the center
    fun rotatePoint(x: Float, y: Float, centerX: Float, centerY: Float, angleRadians: Double): Offset {
        val dx = x - centerX
        val dy = y - centerY
        val cosAngle = cos(angleRadians)
        val sinAngle = sin(angleRadians)
        val newX = (dx * cosAngle - dy * sinAngle + centerX).toFloat()
        val newY = (dx * sinAngle + dy * cosAngle + centerY).toFloat()
        return Offset(newX, newY)
    }

    val topLeft = rotatePoint(rect.left, rect.top, centerX, centerY, angleRadians)
    val topRight = rotatePoint(rect.right, rect.top, centerX, centerY, angleRadians)
    val bottomRight = rotatePoint(rect.right, rect.bottom, centerX, centerY, angleRadians)
    val bottomLeft = rotatePoint(rect.left, rect.bottom, centerX, centerY, angleRadians)

    return Path().apply {
        moveTo(topLeft.x, topLeft.y)
        lineTo(topRight.x, topRight.y)
        lineTo(bottomRight.x, bottomRight.y)
        lineTo(bottomLeft.x, bottomLeft.y)
        close()
    }
}

private fun TextLayoutResult.getBoundingBoxesForRange(start: Int, end: Int): List<Rect> {
    var previousRect: Rect? = null
    var firstLineCharRect: Rect? = null
    val boundingBoxes = mutableListOf<Rect>()

    for (index in start..end) {
        val rect = getBoundingBox(index)
        val isLastRect = index == end

        // Single char case
        if (isLastRect && firstLineCharRect == null) {
            firstLineCharRect = rect
            previousRect = rect
        }

        // `rect.right` is zero for the last space in each line
        // Might be an issue: https://issuetracker.google.com/issues/197146630
        if (!isLastRect && rect.right == 0f) continue

        if (firstLineCharRect == null) {
            firstLineCharRect = rect
        } else if (previousRect != null && (previousRect.bottom != rect.bottom || isLastRect)) {
            boundingBoxes.add(
                firstLineCharRect.copy(right = previousRect.right)
            )
            firstLineCharRect = rect
        }
        previousRect = rect
    }
    return boundingBoxes
}


@PreviewLightAndDark
@Composable
private fun Preview() {
    SwissTransferTheme {
        Surface {
            Box(modifier = Modifier.padding(20.dp)) {
                HighlighterText(
                    templateRes = R.string.uploadProgressTitleTemplate,
                    argumentRes = R.string.uploadProgressTitleArgument,
                    style = SwissTransferTheme.typography.bodyMedium
                )
            }
        }
    }
}
