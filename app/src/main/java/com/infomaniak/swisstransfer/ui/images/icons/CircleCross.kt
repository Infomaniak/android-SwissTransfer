package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

val AppIcons.CircleCross: ImageVector
    get() {
        if (_circleCross != null) {
            return _circleCross!!
        }
        _circleCross = Builder(
            name = "CircleCross",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0x00000000)),
                    stroke = SolidColor(Color(0xFF9F9F9F)),
                    strokeLineWidth = 1.5f,
                    strokeLineCap = strokeCapRound,
                    strokeLineJoin = strokeJoinRound,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(0.75f, 11.999f)
                    curveTo(0.75f, 14.983f, 1.935f, 17.844f, 4.045f, 19.954f)
                    curveTo(6.155f, 22.064f, 9.016f, 23.249f, 12.0f, 23.249f)
                    curveTo(14.984f, 23.249f, 17.845f, 22.064f, 19.955f, 19.954f)
                    curveTo(22.065f, 17.844f, 23.25f, 14.983f, 23.25f, 11.999f)
                    curveTo(23.25f, 9.015f, 22.065f, 6.154f, 19.955f, 4.044f)
                    curveTo(17.845f, 1.934f, 14.984f, 0.749f, 12.0f, 0.749f)
                    curveTo(9.016f, 0.749f, 6.155f, 1.934f, 4.045f, 4.044f)
                    curveTo(1.935f, 6.154f, 0.75f, 9.015f, 0.75f, 11.999f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF9F9F9F)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd,
                ) {
                    moveTo(7.411f, 7.403f)
                    curveTo(7.704f, 7.111f, 8.179f, 7.111f, 8.472f, 7.403f)
                    lineTo(12.008f, 10.939f)
                    lineTo(15.543f, 7.404f)
                    curveTo(15.836f, 7.111f, 16.31f, 7.111f, 16.603f, 7.404f)
                    curveTo(16.896f, 7.697f, 16.896f, 8.172f, 16.603f, 8.465f)
                    lineTo(13.068f, 12.0f)
                    lineTo(16.603f, 15.535f)
                    curveTo(16.896f, 15.828f, 16.896f, 16.303f, 16.603f, 16.596f)
                    curveTo(16.31f, 16.889f, 15.836f, 16.889f, 15.543f, 16.596f)
                    lineTo(12.008f, 13.061f)
                    lineTo(8.472f, 16.597f)
                    curveTo(8.179f, 16.889f, 7.704f, 16.889f, 7.411f, 16.597f)
                    curveTo(7.118f, 16.304f, 7.118f, 15.829f, 7.411f, 15.536f)
                    lineTo(10.947f, 12.0f)
                    lineTo(7.411f, 8.464f)
                    curveTo(7.118f, 8.171f, 7.118f, 7.696f, 7.411f, 7.403f)
                    close()
                }
            }
        }.build()
        return _circleCross!!
    }

private var _circleCross: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.CircleCross,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
