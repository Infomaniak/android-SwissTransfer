package com.infomaniak.library.filetypes.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.infomaniak.library.filetypes.FileTypeIcons
import com.infomaniak.library.filetypes.FileTypeIcons.previewSize
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

internal val FileTypeIcons.Archive: ImageVector
    get() {
        if (_archive != null) {
            return _archive!!
        }
        _archive = Builder(
            name = "Archive",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                pathFillType = NonZero,
            ) {
                moveTo(9.75f, 15.25f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, -0.75f)
                moveToRelative(0.0f, 0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, false, 0.0f, -0.75f)
                moveToRelative(0.0f, 5.25f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, -0.75f)
                moveToRelative(0.0f, 0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, false, 0.0f, -0.75f)
                moveToRelative(0.0f, -8.25f)
                arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, -0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, 0.75f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF9F9F9F)),
                strokeLineWidth = 1.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.75f, 15.25f)
                arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, -0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, 0.75f)
                close()
                moveToRelative(0.0f, 4.5f)
                arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, -0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, 0.75f)
                close()
                moveToRelative(0.0f, -9.0f)
                arcToRelative(0.375f, 0.375f, 0.0f, true, true, 0.0f, -0.75f)
                arcToRelative(0.375f, 0.375f, 0.0f, false, true, 0.0f, 0.75f)
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
                moveTo(20.885f, 23.257f)
                horizontalLineTo(3.115f)
                arcToRelative(1.68f, 1.68f, 0.0f, false, true, -1.142f, -0.44f)
                arcToRelative(1.45f, 1.45f, 0.0f, false, true, -0.473f, -1.06f)
                verticalLineToRelative(-19.5f)
                curveToRelative(0.0f, -0.398f, 0.17f, -0.78f, 0.473f, -1.061f)
                arcToRelative(1.68f, 1.68f, 0.0f, false, true, 1.142f, -0.44f)
                horizontalLineToRelative(14.678f)
                curveToRelative(0.428f, 0.0f, 0.838f, 0.159f, 1.141f, 0.44f)
                lineToRelative(3.093f, 2.872f)
                curveTo(22.33f, 4.348f, 22.5f, 4.73f, 22.5f, 5.128f)
                verticalLineToRelative(16.629f)
                curveToRelative(0.0f, 0.397f, -0.17f, 0.779f, -0.473f, 1.06f)
                arcToRelative(1.68f, 1.68f, 0.0f, false, true, -1.142f, 0.44f)
            }
            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                pathFillType = NonZero,
            ) {
                moveTo(11.726f, 4.807f)
                arcToRelative(1.95f, 1.95f, 0.0f, false, true, -0.474f, 1.533f)
                arcToRelative(2.04f, 2.04f, 0.0f, false, true, -3.0f, 0.0f)
                arcToRelative(1.95f, 1.95f, 0.0f, false, true, -0.474f, -1.533f)
                lineTo(8.25f, 1.0f)
                horizontalLineToRelative(3.0f)
                close()
            }
        }.build()
        return _archive!!
    }

private var _archive: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(previewSize)) {
        Image(imageVector = FileTypeIcons.Archive, contentDescription = null)
    }
}
