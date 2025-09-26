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

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infomaniak.core.compose.preview.PreviewLightAndDark
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import kotlin.math.cos
import kotlin.math.sin

private val VERTICAL_PADDING @Composable get() = with(LocalDensity.current) { 3.sp.toPx() }
private val HORIZONTAL_PADDING @Composable get() = with(LocalDensity.current) { 10.sp.toPx() }
private const val ROTATION_ANGLE_DEGREE = -3.0

@Composable
fun HighlightedText(
    modifier: Modifier = Modifier,
    templateRes: Int,
    argumentRes: Int,
    style: TextStyle,
    verticalPadding: Float = VERTICAL_PADDING,
    horizontalPadding: Float = HORIZONTAL_PADDING,
    angleDegrees: Double = ROTATION_ANGLE_DEGREE,
    isHighlighted: () -> Boolean,
) {
    val template = stringResource(templateRes)
    val argument = stringResource(argumentRes)
    val text = String.format(template, argument)

    val highlightedColor = SwissTransferTheme.colors.highlightedColor

    var boundingBoxes by remember { mutableStateOf<List<Rect>>(emptyList()) }

    val highlightProgress by animateFloatAsState(
        targetValue = if (isHighlighted()) 1f else 0f,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing,
            delayMillis = 500,
        ),
        label = "Highlighter progress",
    )

    Text(
        text = text,
        style = style,
        textAlign = TextAlign.Center,
        onTextLayout = { layoutResult -> boundingBoxes = layoutResult.getArgumentBoundingBoxes(text, argument) },
        modifier = modifier.drawBehind {
            val highlightedPath = boundingBoxes.transformForHighlightedStyle(
                verticalPadding,
                horizontalPadding,
                angleDegrees,
                highlightProgress,
            )
            drawPath(path = highlightedPath, style = Fill, color = highlightedColor)
        },
    )
}

private fun TextLayoutResult.getArgumentBoundingBoxes(text: String, argument: String): List<Rect> {
    val startIndex = text.indexOf(argument)
    return getBoundingBoxesForRange(start = startIndex, end = startIndex + argument.count() - 1)
}

private fun List<Rect>.transformForHighlightedStyle(
    verticalPadding: Float,
    horizontalPadding: Float,
    angleDegrees: Double,
    @FloatRange(0.0, 1.0) progress: Float,
): Path = Path().apply {
    forEach { boundingBox ->
        addPath(boundingBox.transformForHighlightedStyle(verticalPadding, horizontalPadding, angleDegrees, progress))
    }
}

private fun Rect.transformForHighlightedStyle(
    verticalPadding: Float,
    horizontalPadding: Float,
    angleDegrees: Double,
    @FloatRange(0.0, 1.0) progress: Float,
): Path {
    return getRotatedRectanglePath(
        Rect(
            left = left - horizontalPadding,
            top = top - verticalPadding,
            right = right + horizontalPadding,
            bottom = bottom + verticalPadding,
        ),
        angleDegrees = angleDegrees,
        progress = progress,
    )
}

private fun getRotatedRectanglePath(rect: Rect, angleDegrees: Double, @FloatRange(0.0, 1.0) progress: Float): Path {
    val centerX = rect.left + (rect.width / 2)
    val centerY = rect.top + (rect.height / 2)

    val angleRadians = Math.toRadians(angleDegrees)

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

    val width = rect.right - rect.left
    val right = rect.left + progress * width

    val topLeft = rotatePoint(rect.left, rect.top, centerX, centerY, angleRadians)
    val topRight = rotatePoint(right, rect.top, centerX, centerY, angleRadians)
    val bottomRight = rotatePoint(right, rect.bottom, centerX, centerY, angleRadians)
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
    // No character case or start > end case
    if (end - start <= 0) return emptyList()

    // Single character case
    if (end - start == 1) return listOf(getBoundingBox(0))

    var firstBoundingBoxRect: Rect? = null
    var previousRect: Rect? = null
    val boundingBoxes = mutableListOf<Rect>()

    // More than one character case
    for (index in start..end) {
        val currentRect = getBoundingBox(index)
        val isLastCharacter = index == end

        // If we haven't seen the first character of the line, set it now
        if (firstBoundingBoxRect == null) {
            firstBoundingBoxRect = currentRect
        }

        // Check if we reached the end of the current bounding box
        // (i.e. if we reached a new line or the last character in the range)
        if (previousRect != null && (areOnDifferentLines(previousRect, currentRect) || isLastCharacter)) {
            val lastBoundingBoxRect = if (isLastCharacter) currentRect else previousRect
            boundingBoxes.add(firstBoundingBoxRect.copy(right = lastBoundingBoxRect.right))

            // Start a new line (reset firstBoundingBoxRect to the current character's rect)
            firstBoundingBoxRect = currentRect
        }

        previousRect = currentRect
    }

    return boundingBoxes
}

private fun areOnDifferentLines(previousRect: Rect, currentRect: Rect) = previousRect.bottom != currentRect.bottom

@PreviewLightAndDark
@Preview(locale = "fr")
@Preview(locale = "de")
@Preview(locale = "es")
@Preview(locale = "it")
@Composable
private fun Preview() {
    SwissTransferTheme {
        var isHighlighted by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(Unit) { isHighlighted = true }

        Surface {
            Box(modifier = Modifier.padding(20.dp)) {
                HighlightedText(
                    templateRes = R.string.uploadProgressTitleTemplate,
                    argumentRes = R.string.uploadProgressTitleArgument,
                    style = SwissTransferTheme.typography.bodyMedium,
                    isHighlighted = { isHighlighted }
                )
            }
        }
    }
}
