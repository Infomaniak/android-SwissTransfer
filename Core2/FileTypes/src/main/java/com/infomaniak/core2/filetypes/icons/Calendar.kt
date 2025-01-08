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

internal val FileTypeIcons.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(
            name = "Calendar",
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
                    moveTo(22.044f, 2.913f)
                    horizontalLineTo(1.957f)
                    arcTo(0.957f, 0.957f, 0.0f, false, false, 1.0f, 3.87f)
                    verticalLineToRelative(18.174f)
                    curveTo(1.0f, 22.572f, 1.428f, 23.0f, 1.957f, 23.0f)
                    horizontalLineToRelative(20.087f)
                    arcTo(0.957f, 0.957f, 0.0f, false, false, 23.0f, 22.044f)
                    verticalLineTo(3.87f)
                    arcToRelative(0.956f, 0.956f, 0.0f, false, false, -0.956f, -0.957f)
                    moveTo(5.783f, 1.0f)
                    verticalLineToRelative(4.783f)
                    moveTo(18.217f, 1.0f)
                    verticalLineToRelative(4.783f)
                    moveTo(1.0f, 7.696f)
                    horizontalLineToRelative(22.0f)
                }
            }
        }.build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Calendar, contentDescription = null)
    }
}
