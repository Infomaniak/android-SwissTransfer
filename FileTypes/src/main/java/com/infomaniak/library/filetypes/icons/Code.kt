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

internal val FileTypeIcons.Code: ImageVector
    get() {
        if (_code != null) {
            return _code!!
        }
        _code = Builder(
            name = "Code",
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
                    moveTo(22.5f, 21.76f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, 1.5f)
                    horizontalLineTo(3.0f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, -1.5f, -1.5f)
                    verticalLineTo(2.26f)
                    arcTo(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.76f)
                    horizontalLineToRelative(15.045f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.048f, 0.427f)
                    lineToRelative(2.955f, 2.882f)
                    arcTo(1.5f, 1.5f, 0.0f, false, true, 22.5f, 5.143f)
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
                    moveTo(14.25f, 8.257f)
                    lineToRelative(3.75f, 3.75f)
                    lineToRelative(-3.75f, 3.75f)
                    moveToRelative(-4.5f, -7.5f)
                    lineTo(6.0f, 12.007f)
                    lineToRelative(3.75f, 3.75f)
                }
            }
        }.build()
        return _code!!
    }

private var _code: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = FileTypeIcons.Code, contentDescription = null)
    }
}
