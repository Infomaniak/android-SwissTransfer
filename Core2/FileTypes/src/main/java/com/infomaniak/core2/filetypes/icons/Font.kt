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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.core2.filetypes.FileTypeIcons
import com.infomaniak.core2.filetypes.FileTypeIcons.previewSize
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Font: ImageVector
    get() {
        if (_font != null) {
            return _font!!
        }
        _font = Builder(
            name = "Font",
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
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, 1.5f)
                    horizontalLineTo(3.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineToRelative(-19.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    horizontalLineToRelative(15.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.047f, 0.426f)
                    lineToRelative(3.0f, 2.883f)
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
                    moveTo(8.0f, 9.007f)
                    verticalLineToRelative(-2.0f)
                    horizontalLineToRelative(8.0f)
                    verticalLineToRelative(2.0f)
                    moveToRelative(-4.0f, -2.0f)
                    verticalLineToRelative(10.0f)
                    moveToRelative(-2.0f, 0.0f)
                    horizontalLineToRelative(4.0f)
                }
            }
        }.build()
        return _font!!
    }

private var _font: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Font, contentDescription = null)
    }
}
