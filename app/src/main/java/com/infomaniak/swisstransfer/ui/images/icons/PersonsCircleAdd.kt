package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.PersonsCircleAdd: ImageVector
    get() {
        if (_personsCircleAdd != null) {
            return _personsCircleAdd!!
        }
        _personsCircleAdd = Builder(
            name = "PersonsCircleAdd",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF9F9F9F)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(18.0f, 12.0f)
                    curveTo(19.591f, 12.0f, 21.117f, 12.633f, 22.242f, 13.758f)
                    curveTo(23.367f, 14.883f, 24.0f, 16.409f, 24.0f, 18.0f)
                    curveTo(24.0f, 19.591f, 23.367f, 21.117f, 22.242f, 22.242f)
                    curveTo(21.117f, 23.367f, 19.591f, 24.0f, 18.0f, 24.0f)
                    curveTo(16.409f, 24.0f, 14.883f, 23.367f, 13.758f, 22.242f)
                    curveTo(12.633f, 21.117f, 12.0f, 19.591f, 12.0f, 18.0f)
                    curveTo(12.0f, 16.409f, 12.633f, 14.883f, 13.758f, 13.758f)
                    curveTo(14.883f, 12.633f, 16.409f, 12.0f, 18.0f, 12.0f)
                    close()
                    moveTo(18.0f, 14.0f)
                    curveTo(17.586f, 14.0f, 17.25f, 14.336f, 17.25f, 14.75f)
                    verticalLineTo(17.25f)
                    horizontalLineTo(14.75f)
                    curveTo(14.336f, 17.25f, 14.0f, 17.586f, 14.0f, 18.0f)
                    curveTo(14.0f, 18.414f, 14.336f, 18.75f, 14.75f, 18.75f)
                    horizontalLineTo(17.25f)
                    verticalLineTo(21.25f)
                    curveTo(17.25f, 21.664f, 17.586f, 22.0f, 18.0f, 22.0f)
                    curveTo(18.414f, 22.0f, 18.75f, 21.664f, 18.75f, 21.25f)
                    verticalLineTo(18.75f)
                    horizontalLineTo(21.25f)
                    curveTo(21.664f, 18.75f, 22.0f, 18.414f, 22.0f, 18.0f)
                    curveTo(22.0f, 17.586f, 21.664f, 17.25f, 21.25f, 17.25f)
                    horizontalLineTo(18.75f)
                    verticalLineTo(14.75f)
                    curveTo(18.75f, 14.336f, 18.414f, 14.0f, 18.0f, 14.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(13.071f, 10.047f)
                    curveTo(13.58f, 9.824f, 14.134f, 9.705f, 14.7f, 9.705f)
                    curveTo(15.601f, 9.705f, 16.47f, 10.004f, 17.175f, 10.548f)
                    moveTo(2.85f, 4.046f)
                    curveTo(2.85f, 4.92f, 3.197f, 5.758f, 3.816f, 6.377f)
                    curveTo(4.435f, 6.995f, 5.274f, 7.342f, 6.15f, 7.342f)
                    curveTo(7.025f, 7.342f, 7.864f, 6.995f, 8.483f, 6.377f)
                    curveTo(9.102f, 5.758f, 9.449f, 4.92f, 9.449f, 4.046f)
                    curveTo(9.449f, 3.172f, 9.102f, 2.333f, 8.483f, 1.715f)
                    curveTo(7.864f, 1.097f, 7.025f, 0.75f, 6.15f, 0.75f)
                    curveTo(5.274f, 0.75f, 4.435f, 1.097f, 3.816f, 1.715f)
                    curveTo(3.197f, 2.333f, 2.85f, 3.172f, 2.85f, 4.046f)
                    close()
                    moveTo(12.225f, 6.216f)
                    curveTo(12.225f, 6.872f, 12.486f, 7.501f, 12.95f, 7.964f)
                    curveTo(13.414f, 8.428f, 14.044f, 8.688f, 14.7f, 8.688f)
                    curveTo(15.356f, 8.688f, 15.986f, 8.428f, 16.45f, 7.964f)
                    curveTo(16.914f, 7.501f, 17.175f, 6.872f, 17.175f, 6.216f)
                    curveTo(17.175f, 5.561f, 16.914f, 4.932f, 16.45f, 4.468f)
                    curveTo(15.986f, 4.005f, 15.356f, 3.744f, 14.7f, 3.744f)
                    curveTo(14.044f, 3.744f, 13.414f, 4.005f, 12.95f, 4.468f)
                    curveTo(12.486f, 4.932f, 12.225f, 5.561f, 12.225f, 6.216f)
                    close()
                    moveTo(0.75f, 13.75f)
                    curveTo(0.75f, 12.32f, 1.319f, 10.948f, 2.332f, 9.936f)
                    curveTo(3.344f, 8.925f, 4.718f, 8.357f, 6.15f, 8.357f)
                    curveTo(7.582f, 8.357f, 8.955f, 8.925f, 9.968f, 9.936f)
                    curveTo(10.98f, 10.948f, 11.549f, 12.32f, 11.549f, 13.75f)
                    horizontalLineTo(0.75f)
                    close()
                }
            }
        }.build()
        return _personsCircleAdd!!
    }

private var _personsCircleAdd: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.PersonsCircleAdd,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
