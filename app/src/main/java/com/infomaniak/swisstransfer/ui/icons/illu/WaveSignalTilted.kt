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

val AppIcons.Illu.WaveSignalTilted: ImageVector
    get() {
        if (_waveSignalTilted != null) {
            return _waveSignalTilted!!
        }
        _waveSignalTilted = Builder(
            name = "WaveSignalTilted",
            defaultWidth = 120.0.dp,
            defaultHeight = 80.0.dp,
            viewportWidth = 120.0f,
            viewportHeight = 80.0f
        ).apply {
            group {}
            group {
                path(
                    fill = null,
                    stroke = SolidColor(Color(0xFFcf9e1b)),
                    strokeLineWidth = 2.931f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = Round,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(93.93f, 50.5f)
                    arcToRelative(32.97f, 32.97f, 0.0f, false, false, -40.38f, 23.31f)
                    moveToRelative(46.19f, -42.82f)
                    arcToRelative(52.75f, 52.75f, 0.0f, false, false, -64.6f, 37.3f)
                    moveToRelative(66.36f, -56.51f)
                    arcToRelative(72.53f, 72.53f, 0.0f, false, false, -85.65f, 49.45f)
                }
            }
        }.build()
        return _waveSignalTilted!!
    }

private var _waveSignalTilted: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Illu.WaveSignalTilted,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
