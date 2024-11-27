package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.SignalCrossed: ImageVector
    get() {

        if (_signalCrossed != null) return _signalCrossed!!

        _signalCrossed = Builder(
            name = "SignalCrossed",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF9F9F9F)),
                pathFillType = EvenOdd,
            ) {
                moveTo(2.442f, 0.165f)
                curveTo(2.263f, -0.037f, 1.956f, -0.056f, 1.754f, 0.122f)
                curveTo(1.553f, 0.301f, 1.534f, 0.609f, 1.712f, 0.811f)
                lineTo(3.528f, 2.869f)
                curveTo(1.254f, 5.217f, 0.002f, 8.309f, 0.0f, 11.597f)
                curveTo(-0.002f, 14.948f, 1.294f, 18.094f, 3.637f, 20.439f)
                curveTo(3.737f, 20.554f, 3.88f, 20.62f, 4.032f, 20.62f)
                curveTo(4.32f, 20.62f, 4.554f, 20.386f, 4.555f, 20.097f)
                curveTo(4.555f, 19.954f, 4.496f, 19.816f, 4.398f, 19.722f)
                curveTo(2.232f, 17.552f, 1.039f, 14.666f, 1.039f, 11.598f)
                curveTo(1.039f, 8.614f, 2.167f, 5.804f, 4.221f, 3.654f)
                lineTo(6.306f, 6.017f)
                curveTo(4.93f, 7.551f, 4.177f, 9.516f, 4.177f, 11.598f)
                curveTo(4.177f, 13.833f, 5.043f, 15.933f, 6.618f, 17.51f)
                curveTo(6.717f, 17.608f, 6.848f, 17.663f, 6.988f, 17.663f)
                curveTo(7.126f, 17.663f, 7.259f, 17.61f, 7.357f, 17.511f)
                curveTo(7.455f, 17.412f, 7.509f, 17.281f, 7.509f, 17.141f)
                curveTo(7.509f, 17.001f, 7.455f, 16.869f, 7.357f, 16.771f)
                curveTo(5.977f, 15.389f, 5.218f, 13.552f, 5.218f, 11.598f)
                curveTo(5.218f, 9.818f, 5.848f, 8.136f, 7.002f, 6.806f)
                lineTo(9.121f, 9.207f)
                curveTo(8.635f, 9.828f, 8.344f, 10.61f, 8.344f, 11.459f)
                curveTo(8.344f, 13.275f, 9.701f, 14.82f, 11.475f, 15.076f)
                verticalLineTo(23.478f)
                curveTo(11.475f, 23.766f, 11.709f, 24.0f, 11.997f, 24.0f)
                curveTo(12.285f, 24.0f, 12.519f, 23.766f, 12.519f, 23.478f)
                verticalLineTo(15.076f)
                curveTo(13.008f, 15.005f, 13.466f, 14.837f, 13.872f, 14.591f)
                lineTo(21.539f, 23.28f)
                curveTo(21.718f, 23.482f, 22.025f, 23.501f, 22.227f, 23.322f)
                curveTo(22.428f, 23.144f, 22.448f, 22.836f, 22.269f, 22.634f)
                lineTo(20.345f, 20.453f)
                curveTo(22.702f, 18.086f, 24.0f, 14.941f, 24.0f, 11.598f)
                curveTo(24.0f, 8.248f, 22.698f, 5.099f, 20.332f, 2.73f)
                curveTo(20.233f, 2.632f, 20.102f, 2.578f, 19.962f, 2.578f)
                curveTo(19.823f, 2.578f, 19.691f, 2.632f, 19.593f, 2.73f)
                curveTo(19.389f, 2.934f, 19.389f, 3.266f, 19.593f, 3.469f)
                curveTo(21.76f, 5.64f, 22.955f, 8.528f, 22.955f, 11.598f)
                curveTo(22.955f, 14.64f, 21.783f, 17.503f, 19.652f, 19.668f)
                lineTo(17.57f, 17.308f)
                curveTo(19.024f, 15.754f, 19.822f, 13.736f, 19.822f, 11.596f)
                curveTo(19.822f, 9.363f, 18.953f, 7.263f, 17.376f, 5.684f)
                curveTo(17.277f, 5.586f, 17.147f, 5.531f, 17.007f, 5.531f)
                curveTo(16.867f, 5.531f, 16.736f, 5.585f, 16.638f, 5.684f)
                curveTo(16.539f, 5.783f, 16.485f, 5.914f, 16.485f, 6.054f)
                curveTo(16.485f, 6.194f, 16.539f, 6.326f, 16.638f, 6.424f)
                curveTo(18.017f, 7.805f, 18.777f, 9.643f, 18.777f, 11.597f)
                curveTo(18.777f, 13.436f, 18.104f, 15.172f, 16.875f, 16.521f)
                lineTo(14.633f, 13.98f)
                curveTo(15.263f, 13.32f, 15.649f, 12.428f, 15.649f, 11.459f)
                curveTo(15.649f, 9.442f, 14.011f, 7.801f, 11.997f, 7.801f)
                curveTo(11.181f, 7.801f, 10.427f, 8.07f, 9.819f, 8.525f)
                lineTo(2.442f, 0.165f)
                close()
                moveTo(9.828f, 10.008f)
                curveTo(9.55f, 10.423f, 9.388f, 10.922f, 9.388f, 11.459f)
                curveTo(9.388f, 12.9f, 10.558f, 14.072f, 11.997f, 14.072f)
                curveTo(12.418f, 14.072f, 12.816f, 13.971f, 13.168f, 13.793f)
                lineTo(9.828f, 10.008f)
                close()
                moveTo(13.943f, 13.198f)
                lineTo(10.513f, 9.311f)
                curveTo(10.934f, 9.018f, 11.446f, 8.846f, 11.997f, 8.846f)
                curveTo(13.436f, 8.846f, 14.606f, 10.018f, 14.606f, 11.459f)
                curveTo(14.606f, 12.126f, 14.355f, 12.736f, 13.943f, 13.198f)
                close()
            }
        }.build()

        return _signalCrossed!!
    }

private var _signalCrossed: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.SignalCrossed,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
