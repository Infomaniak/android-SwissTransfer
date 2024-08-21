package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Illu.EnvelopeTilted: ImageVector
    get() {
        if (_envelopeTilted != null) {
            return _envelopeTilted!!
        }
        _envelopeTilted = Builder(
            name = "EnvelopeTilted",
            defaultWidth = 120.0.dp,
            defaultHeight = 80.0.dp,
            viewportWidth = 120.0f,
            viewportHeight = 80.0f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(85.95f, 4.79f)
                lineTo(63.9f, 63.07f)
                lineTo(2.41f, 53.02f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 3.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(80.94f, 5.11f)
                lineTo(5.18f, 48.85f)
                curveTo(2.04f, 50.66f, 1.0f, 54.73f, 2.85f, 57.94f)
                lineToRelative(26.83f, 46.47f)
                curveToRelative(1.85f, 3.21f, 5.9f, 4.34f, 9.03f, 2.53f)
                lineToRelative(75.76f, -43.74f)
                curveToRelative(3.14f, -1.81f, 4.18f, -5.88f, 2.33f, -9.09f)
                lineTo(89.98f, 7.63f)
                curveToRelative(-1.85f, -3.21f, -5.9f, -4.34f, -9.03f, -2.53f)
            }
        }.build()
        return _envelopeTilted!!
    }

private var _envelopeTilted: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Illu.EnvelopeTilted,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
