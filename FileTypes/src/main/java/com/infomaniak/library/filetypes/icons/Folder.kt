package com.infomaniak.library.filetypes.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.library.filetypes.FileTypeIcons

internal val FileTypeIcons.Folder: ImageVector
    get() {
        if (_folder != null) {
            return _folder!!
        }
        _folder = Builder(
            name = "Icon",
            defaultWidth = 16.0.dp,
            defaultHeight = 15.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 15.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(0.515f, 0.979f)
                curveTo(0.832f, 0.706f, 1.247f, 0.562f, 1.666f, 0.562f)
                horizontalLineTo(5.135f)
                curveTo(5.555f, 0.562f, 5.969f, 0.706f, 6.286f, 0.979f)
                curveTo(6.605f, 1.255f, 6.802f, 1.647f, 6.802f, 2.074f)
                verticalLineTo(2.562f)
                horizontalLineTo(14.384f)
                curveTo(14.804f, 2.562f, 15.219f, 2.706f, 15.535f, 2.979f)
                curveTo(15.903f, 3.297f, 16.0f, 3.719f, 16.0f, 4.074f)
                verticalLineTo(12.95f)
                curveTo(15.993f, 13.369f, 15.812f, 13.757f, 15.513f, 14.047f)
                curveTo(15.216f, 14.336f, 14.822f, 14.512f, 14.407f, 14.559f)
                curveTo(14.388f, 14.561f, 14.368f, 14.562f, 14.349f, 14.562f)
                lineTo(1.65f, 14.562f)
                curveTo(1.631f, 14.562f, 1.611f, 14.561f, 1.592f, 14.559f)
                curveTo(1.177f, 14.512f, 0.783f, 14.336f, 0.486f, 14.047f)
                curveTo(0.187f, 13.757f, 0.006f, 13.369f, -0.0f, 12.95f)
                lineTo(-0.0f, 12.942f)
                lineTo(-0.0f, 2.074f)
                curveTo(-0.0f, 1.647f, 0.197f, 1.255f, 0.515f, 0.979f)
                close()
                moveTo(1.666f, 1.586f)
                curveTo(1.473f, 1.586f, 1.3f, 1.654f, 1.182f, 1.755f)
                curveTo(1.067f, 1.854f, 1.021f, 1.971f, 1.021f, 2.074f)
                verticalLineTo(12.937f)
                curveTo(1.024f, 13.062f, 1.078f, 13.196f, 1.197f, 13.312f)
                curveTo(1.314f, 13.425f, 1.483f, 13.51f, 1.682f, 13.538f)
                lineTo(14.318f, 13.538f)
                curveTo(14.516f, 13.51f, 14.686f, 13.425f, 14.802f, 13.312f)
                curveTo(14.921f, 13.196f, 14.975f, 13.062f, 14.978f, 12.937f)
                verticalLineTo(4.074f)
                curveTo(14.978f, 3.899f, 14.935f, 3.813f, 14.868f, 3.755f)
                curveTo(14.751f, 3.654f, 14.578f, 3.587f, 14.384f, 3.587f)
                horizontalLineTo(6.291f)
                curveTo(6.009f, 3.587f, 5.78f, 3.357f, 5.78f, 3.074f)
                verticalLineTo(2.074f)
                curveTo(5.78f, 1.971f, 5.734f, 1.854f, 5.619f, 1.755f)
                curveTo(5.502f, 1.654f, 5.329f, 1.586f, 5.135f, 1.586f)
                horizontalLineTo(1.666f)
                close()
            }
        }
            .build()
        return _folder!!
    }

private var _folder: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = FileTypeIcons.Folder, contentDescription = null)
    }
}
