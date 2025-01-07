package com.infomaniak.core2.filetypes.icons

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
import com.infomaniak.core2.filetypes.FileTypeIcons
import com.infomaniak.core2.filetypes.FileTypeIcons.previewSize
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Image: ImageVector
    get() {
        if (_image != null) {
            return _image!!
        }
        _image = Builder(
            name = "Image",
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
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(18.77f, 18.482f)
                lineToRelative(-3.925f, -5.888f)
                arcToRelative(1.01f, 1.01f, 0.0f, false, false, -1.671f, -0.02f)
                lineToRelative(-2.691f, 3.846f)
                lineToRelative(-1.668f, -1.338f)
                arcToRelative(1.01f, 1.01f, 0.0f, false, false, -1.474f, 0.229f)
                lineTo(5.23f, 18.48f)
            }
        }.build()
        return _image!!
    }

private var _image: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Image, contentDescription = null)
    }
}
