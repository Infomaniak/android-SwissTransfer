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
import com.infomaniak.library.filetypes.FileTypeIcons.previewSize
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Audio: ImageVector
    get() {
        if (_audio != null) {
            return _audio!!
        }
        _audio = Builder(
            name = "Audio",
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
                    fill = SolidColor(Color(0xFF9F9F9F)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(5.0f, 16.708f)
                    curveToRelative(0.0f, 0.61f, 0.246f, 1.194f, 0.683f, 1.625f)
                    arcToRelative(2.35f, 2.35f, 0.0f, false, false, 1.65f, 0.674f)
                    curveToRelative(0.62f, 0.0f, 1.213f, -0.243f, 1.65f, -0.674f)
                    arcToRelative(2.28f, 2.28f, 0.0f, false, false, 0.684f, -1.625f)
                    curveToRelative(0.0f, -0.61f, -0.246f, -1.195f, -0.684f, -1.626f)
                    arcToRelative(2.35f, 2.35f, 0.0f, false, false, -1.65f, -0.673f)
                    curveToRelative(-0.619f, 0.0f, -1.212f, 0.242f, -1.65f, 0.673f)
                    arcTo(2.28f, 2.28f, 0.0f, false, false, 5.0f, 16.708f)
                    moveToRelative(9.333f, -2.758f)
                    curveToRelative(0.0f, 0.609f, 0.246f, 1.194f, 0.684f, 1.625f)
                    arcToRelative(2.35f, 2.35f, 0.0f, false, false, 1.65f, 0.673f)
                    curveToRelative(0.618f, 0.0f, 1.212f, -0.242f, 1.65f, -0.673f)
                    arcTo(2.28f, 2.28f, 0.0f, false, false, 19.0f, 13.949f)
                    curveToRelative(0.0f, -0.61f, -0.246f, -1.194f, -0.683f, -1.625f)
                    arcToRelative(2.35f, 2.35f, 0.0f, false, false, -1.65f, -0.674f)
                    curveToRelative(-0.62f, 0.0f, -1.213f, 0.242f, -1.65f, 0.674f)
                    arcToRelative(2.28f, 2.28f, 0.0f, false, false, -0.684f, 1.625f)
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
                    moveTo(9.667f, 16.708f)
                    verticalLineToRelative(-8.33f)
                    curveToRelative(0.0f, -0.386f, 0.123f, -0.762f, 0.352f, -1.075f)
                    curveToRelative(0.23f, -0.313f, 0.553f, -0.548f, 0.925f, -0.67f)
                    lineToRelative(5.6f, -1.532f)
                    arcToRelative(1.9f, 1.9f, 0.0f, false, true, 1.68f, 0.253f)
                    curveToRelative(0.24f, 0.17f, 0.436f, 0.394f, 0.57f, 0.654f)
                    curveTo(18.93f, 6.267f, 19.0f, 6.554f, 19.0f, 6.845f)
                    verticalLineToRelative(7.104f)
                }
            }
        }.build()
        return _audio!!
    }

private var _audio: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Audio, contentDescription = null)
    }
}
