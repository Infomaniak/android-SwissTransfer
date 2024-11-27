package com.infomaniak.library.filetypes.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.library.filetypes.FileTypeIcons
import com.infomaniak.library.filetypes.FileTypeIcons.previewSize
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Points: ImageVector
    get() {
        if (_points != null) {
            return _points!!
        }
        _points = Builder(
            name = "Points",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(22.5f, 21.757f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.503f, 1.5f)
                horizontalLineTo(3.003f)
                arcToRelative(1.505f, 1.505f, 0.0f, false, true, -1.503f, -1.5f)
                verticalLineToRelative(-19.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.503f, -1.5f)
                horizontalLineToRelative(15.033f)
                curveToRelative(0.392f, 0.0f, 0.769f, 0.152f, 1.05f, 0.426f)
                lineToRelative(2.961f, 2.883f)
                arcTo(1.5f, 1.5f, 0.0f, false, true, 22.5f, 5.14f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                pathFillType = NonZero,
            ) {
                moveTo(13.0f, 5.007f)
                verticalLineToRelative(6.0f)
                horizontalLineToRelative(6.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, false, -6.0f, -6.0f)
            }
            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                pathFillType = NonZero,
            ) {
                moveTo(11.0f, 7.007f)
                arcToRelative(6.0f, 6.0f, 0.0f, true, false, 6.0f, 6.0f)
                horizontalLineToRelative(-6.0f)
                close()
            }
        }.build()
        return _points!!
    }

private var _points: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Points, contentDescription = null)
    }
}
