package com.infomaniak.swisstransfer.ui.icons.illu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

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
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 2.982f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = Round,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(86.13f, 4.46f)
                    lineTo(64.07f, 62.74f)
                    lineTo(2.57f, 52.69f)
                }
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 2.982f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = Round,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(81.12f, 4.77f)
                    lineTo(5.34f, 48.52f)
                    curveToRelative(-3.14f, 1.81f, -4.18f, 5.88f, -2.33f, 9.09f)
                    lineToRelative(26.83f, 46.48f)
                    curveToRelative(1.85f, 3.21f, 5.9f, 4.34f, 9.04f, 2.53f)
                    lineToRelative(75.78f, -43.75f)
                    curveToRelative(3.14f, -1.81f, 4.18f, -5.88f, 2.33f, -9.09f)
                    lineTo(90.16f, 7.3f)
                    curveToRelative(-1.85f, -3.21f, -5.9f, -4.34f, -9.04f, -2.53f)
                    close()
                }
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
