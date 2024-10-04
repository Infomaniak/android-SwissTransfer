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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.library.filetypes.FileTypeIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Unknown: ImageVector
    get() {
        if (_unknown != null) {
            return _unknown!!
        }
        _unknown = Builder(
            name = "Unknown",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            group {
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
            }
        }.build()
        return _unknown!!
    }

private var _unknown: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = FileTypeIcons.Unknown, contentDescription = null)
    }
}
