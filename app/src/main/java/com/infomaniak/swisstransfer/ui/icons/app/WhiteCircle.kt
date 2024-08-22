package com.infomaniak.swisstransfer.ui.icons.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.icons.AppIcons

val AppIcons.WhiteCircle: ImageVector
    get() {
        if (_whiteCircle != null) {
            return _whiteCircle!!
        }
        _whiteCircle = Builder(
            name = "WhiteCircle",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFF5F5F5)),
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 0.3f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.0f, 0.15f)
                arcTo(11.85f, 11.85f, 0.0f, false, true, 23.85f, 12.0f)
                arcTo(11.85f, 11.85f, 0.0f, false, true, 12.0f, 23.85f)
                arcTo(11.85f, 11.85f, 0.0f, false, true, 0.15f, 12.0f)
                arcTo(11.85f, 11.85f, 0.0f, false, true, 12.0f, 0.15f)
                close()
            }
        }.build()
        return _whiteCircle!!
    }

private var _whiteCircle: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.WhiteCircle,
            contentDescription = null,
            modifier = Modifier.size(AppIcons.previewSize)
        )
    }
}
