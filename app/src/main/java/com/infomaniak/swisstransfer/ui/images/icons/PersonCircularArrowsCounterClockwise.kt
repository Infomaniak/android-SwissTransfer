package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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

val AppIcons.PersonCircularArrowsCounterClockwise: ImageVector
    get() {
        if (_personCircularArrowsCounterClockwise != null) {
            return _personCircularArrowsCounterClockwise!!
        }
        _personCircularArrowsCounterClockwise = Builder(
            name = "PersonCircularArrowsCounterClockwise",
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
                        moveTo(9.983f, 19.75f)
                        horizontalLineTo(0.75f)
                        curveTo(0.75f, 17.622f, 1.617f, 15.58f, 3.161f, 14.076f)
                        curveTo(4.706f, 12.571f, 6.8f, 11.725f, 8.983f, 11.725f)
                        curveTo(10.829f, 11.725f, 12.61f, 12.329f, 14.05f, 13.425f)
                        moveTo(3.917f, 5.817f)
                        curveTo(3.917f, 7.16f, 4.45f, 8.449f, 5.401f, 9.399f)
                        curveTo(6.351f, 10.349f, 7.64f, 10.883f, 8.983f, 10.883f)
                        curveTo(10.327f, 10.883f, 11.616f, 10.349f, 12.566f, 9.399f)
                        curveTo(13.516f, 8.449f, 14.05f, 7.16f, 14.05f, 5.817f)
                        curveTo(14.05f, 4.473f, 13.516f, 3.184f, 12.566f, 2.234f)
                        curveTo(11.616f, 1.284f, 10.327f, 0.75f, 8.983f, 0.75f)
                        curveTo(7.64f, 0.75f, 6.351f, 1.284f, 5.401f, 2.234f)
                        curveTo(4.45f, 3.184f, 3.917f, 4.473f, 3.917f, 5.817f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF9F9F9F)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(21.361f, 18.31f)
                        curveTo(21.654f, 18.018f, 22.129f, 18.018f, 22.422f, 18.31f)
                        lineTo(23.78f, 19.668f)
                        curveTo(24.073f, 19.961f, 24.073f, 20.435f, 23.78f, 20.728f)
                        curveTo(23.487f, 21.021f, 23.013f, 21.021f, 22.72f, 20.728f)
                        lineTo(22.525f, 20.534f)
                        curveTo(22.401f, 21.068f, 22.179f, 21.578f, 21.867f, 22.036f)
                        curveTo(21.363f, 22.778f, 20.647f, 23.352f, 19.813f, 23.684f)
                        curveTo(18.978f, 24.014f, 18.064f, 24.087f, 17.188f, 23.893f)
                        curveTo(16.311f, 23.699f, 15.513f, 23.247f, 14.896f, 22.595f)
                        curveTo(14.612f, 22.294f, 14.626f, 21.819f, 14.927f, 21.535f)
                        curveTo(15.228f, 21.251f, 15.703f, 21.263f, 15.987f, 21.564f)
                        curveTo(16.397f, 21.999f, 16.928f, 22.3f, 17.512f, 22.43f)
                        curveTo(18.095f, 22.559f, 18.704f, 22.509f, 19.26f, 22.289f)
                        curveTo(19.815f, 22.069f, 20.292f, 21.687f, 20.627f, 21.193f)
                        curveTo(20.692f, 21.098f, 20.751f, 21.0f, 20.804f, 20.898f)
                        curveTo(20.535f, 21.001f, 20.219f, 20.945f, 20.003f, 20.728f)
                        curveTo(19.71f, 20.435f, 19.711f, 19.961f, 20.004f, 19.668f)
                        lineTo(21.361f, 18.31f)
                        close()
                        moveTo(16.327f, 14.764f)
                        curveTo(17.196f, 14.452f, 18.139f, 14.416f, 19.029f, 14.66f)
                        curveTo(19.919f, 14.904f, 20.712f, 15.417f, 21.3f, 16.128f)
                        curveTo(21.564f, 16.447f, 21.518f, 16.92f, 21.199f, 17.184f)
                        curveTo(20.88f, 17.447f, 20.407f, 17.402f, 20.144f, 17.083f)
                        curveTo(19.753f, 16.61f, 19.225f, 16.269f, 18.633f, 16.106f)
                        curveTo(18.04f, 15.944f, 17.411f, 15.968f, 16.833f, 16.176f)
                        curveTo(16.255f, 16.383f, 15.755f, 16.764f, 15.401f, 17.265f)
                        curveTo(15.232f, 17.506f, 15.1f, 17.768f, 15.009f, 18.044f)
                        curveTo(15.303f, 17.817f, 15.727f, 17.84f, 15.996f, 18.109f)
                        curveTo(16.289f, 18.402f, 16.289f, 18.877f, 15.996f, 19.17f)
                        lineTo(14.664f, 20.5f)
                        curveTo(14.527f, 20.652f, 14.328f, 20.747f, 14.107f, 20.747f)
                        curveTo(13.887f, 20.747f, 13.689f, 20.652f, 13.552f, 20.5f)
                        lineTo(12.22f, 19.17f)
                        curveTo(11.927f, 18.877f, 11.927f, 18.402f, 12.22f, 18.109f)
                        curveTo(12.512f, 17.816f, 12.987f, 17.816f, 13.28f, 18.108f)
                        lineTo(13.418f, 18.247f)
                        curveTo(13.528f, 17.586f, 13.786f, 16.954f, 14.176f, 16.401f)
                        curveTo(14.707f, 15.647f, 15.459f, 15.075f, 16.327f, 14.764f)
                        close()
                    }
                }
            }.build()
        return _personCircularArrowsCounterClockwise!!
    }

private var _personCircularArrowsCounterClockwise: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.PersonCircularArrowsCounterClockwise,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
