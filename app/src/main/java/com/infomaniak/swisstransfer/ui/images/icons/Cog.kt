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

val AppIcons.Cog: ImageVector
    get() {
        if (_cog != null) {
            return _cog!!
        }
        _cog = Builder(
            name = "Cog",
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
                        moveTo(10.546f, 2.438f)
                        curveTo(10.729f, 2.642f, 10.954f, 2.805f, 11.204f, 2.916f)
                        curveTo(11.455f, 3.028f, 11.726f, 3.085f, 12.0f, 3.085f)
                        curveTo(12.274f, 3.085f, 12.545f, 3.028f, 12.796f, 2.916f)
                        curveTo(13.046f, 2.805f, 13.27f, 2.642f, 13.454f, 2.438f)
                        lineTo(14.4f, 1.4f)
                        curveTo(14.671f, 1.1f, 15.028f, 0.891f, 15.423f, 0.802f)
                        curveTo(15.817f, 0.713f, 16.23f, 0.749f, 16.603f, 0.904f)
                        curveTo(16.977f, 1.058f, 17.294f, 1.325f, 17.51f, 1.667f)
                        curveTo(17.726f, 2.009f, 17.83f, 2.409f, 17.81f, 2.813f)
                        lineTo(17.739f, 4.213f)
                        curveTo(17.725f, 4.486f, 17.769f, 4.759f, 17.867f, 5.014f)
                        curveTo(17.965f, 5.27f, 18.116f, 5.501f, 18.309f, 5.695f)
                        curveTo(18.502f, 5.888f, 18.734f, 6.039f, 18.989f, 6.138f)
                        curveTo(19.244f, 6.236f, 19.517f, 6.28f, 19.79f, 6.267f)
                        lineTo(21.19f, 6.196f)
                        curveTo(21.594f, 6.176f, 21.993f, 6.282f, 22.335f, 6.498f)
                        curveTo(22.676f, 6.714f, 22.942f, 7.031f, 23.097f, 7.404f)
                        curveTo(23.251f, 7.778f, 23.286f, 8.19f, 23.197f, 8.584f)
                        curveTo(23.108f, 8.978f, 22.9f, 9.335f, 22.6f, 9.606f)
                        lineTo(21.558f, 10.546f)
                        curveTo(21.354f, 10.73f, 21.192f, 10.954f, 21.08f, 11.205f)
                        curveTo(20.969f, 11.455f, 20.912f, 11.726f, 20.912f, 12.0f)
                        curveTo(20.912f, 12.275f, 20.969f, 12.546f, 21.08f, 12.796f)
                        curveTo(21.192f, 13.047f, 21.354f, 13.271f, 21.558f, 13.455f)
                        lineTo(22.6f, 14.395f)
                        curveTo(22.9f, 14.666f, 23.109f, 15.024f, 23.198f, 15.418f)
                        curveTo(23.287f, 15.813f, 23.251f, 16.225f, 23.096f, 16.598f)
                        curveTo(22.941f, 16.972f, 22.675f, 17.289f, 22.333f, 17.505f)
                        curveTo(21.991f, 17.721f, 21.591f, 17.826f, 21.187f, 17.805f)
                        lineTo(19.787f, 17.734f)
                        curveTo(19.513f, 17.72f, 19.24f, 17.764f, 18.984f, 17.862f)
                        curveTo(18.728f, 17.96f, 18.496f, 18.111f, 18.302f, 18.305f)
                        curveTo(18.108f, 18.499f, 17.957f, 18.731f, 17.859f, 18.987f)
                        curveTo(17.76f, 19.243f, 17.717f, 19.516f, 17.731f, 19.79f)
                        lineTo(17.802f, 21.19f)
                        curveTo(17.82f, 21.592f, 17.714f, 21.99f, 17.498f, 22.329f)
                        curveTo(17.282f, 22.668f, 16.966f, 22.933f, 16.595f, 23.087f)
                        curveTo(16.223f, 23.241f, 15.813f, 23.277f, 15.421f, 23.19f)
                        curveTo(15.028f, 23.103f, 14.672f, 22.897f, 14.4f, 22.6f)
                        lineTo(13.459f, 21.559f)
                        curveTo(13.275f, 21.356f, 13.051f, 21.193f, 12.8f, 21.082f)
                        curveTo(12.55f, 20.97f, 12.279f, 20.913f, 12.005f, 20.913f)
                        curveTo(11.731f, 20.913f, 11.46f, 20.97f, 11.209f, 21.082f)
                        curveTo(10.959f, 21.193f, 10.735f, 21.356f, 10.551f, 21.559f)
                        lineTo(9.606f, 22.6f)
                        curveTo(9.335f, 22.898f, 8.978f, 23.105f, 8.585f, 23.193f)
                        curveTo(8.192f, 23.281f, 7.781f, 23.246f, 7.408f, 23.092f)
                        curveTo(7.036f, 22.938f, 6.72f, 22.673f, 6.504f, 22.333f)
                        curveTo(6.288f, 21.993f, 6.181f, 21.595f, 6.2f, 21.192f)
                        lineTo(6.272f, 19.792f)
                        curveTo(6.286f, 19.518f, 6.242f, 19.245f, 6.144f, 18.989f)
                        curveTo(6.046f, 18.733f, 5.895f, 18.501f, 5.701f, 18.307f)
                        curveTo(5.507f, 18.113f, 5.275f, 17.962f, 5.019f, 17.864f)
                        curveTo(4.763f, 17.765f, 4.49f, 17.722f, 4.216f, 17.736f)
                        lineTo(2.816f, 17.807f)
                        curveTo(2.412f, 17.828f, 2.012f, 17.724f, 1.67f, 17.508f)
                        curveTo(1.328f, 17.293f, 1.061f, 16.976f, 0.906f, 16.603f)
                        curveTo(0.751f, 16.23f, 0.715f, 15.818f, 0.803f, 15.423f)
                        curveTo(0.892f, 15.029f, 1.1f, 14.671f, 1.4f, 14.4f)
                        lineTo(2.441f, 13.46f)
                        curveTo(2.644f, 13.276f, 2.807f, 13.052f, 2.918f, 12.801f)
                        curveTo(3.03f, 12.551f, 3.087f, 12.28f, 3.087f, 12.005f)
                        curveTo(3.087f, 11.731f, 3.03f, 11.46f, 2.918f, 11.21f)
                        curveTo(2.807f, 10.959f, 2.644f, 10.735f, 2.441f, 10.551f)
                        lineTo(1.4f, 9.606f)
                        curveTo(1.101f, 9.335f, 0.893f, 8.978f, 0.805f, 8.585f)
                        curveTo(0.717f, 8.191f, 0.752f, 7.78f, 0.906f, 7.407f)
                        curveTo(1.06f, 7.035f, 1.326f, 6.719f, 1.666f, 6.503f)
                        curveTo(2.007f, 6.286f, 2.406f, 6.181f, 2.809f, 6.2f)
                        lineTo(4.209f, 6.271f)
                        curveTo(4.483f, 6.285f, 4.757f, 6.242f, 5.014f, 6.144f)
                        curveTo(5.27f, 6.045f, 5.503f, 5.894f, 5.697f, 5.7f)
                        curveTo(5.891f, 5.505f, 6.042f, 5.272f, 6.14f, 5.016f)
                        curveTo(6.238f, 4.759f, 6.282f, 4.485f, 6.267f, 4.211f)
                        lineTo(6.2f, 2.81f)
                        curveTo(6.181f, 2.407f, 6.287f, 2.009f, 6.503f, 1.668f)
                        curveTo(6.719f, 1.328f, 7.035f, 1.062f, 7.407f, 0.908f)
                        curveTo(7.78f, 0.754f, 8.191f, 0.718f, 8.584f, 0.806f)
                        curveTo(8.978f, 0.895f, 9.335f, 1.102f, 9.606f, 1.4f)
                        lineTo(10.546f, 2.438f)
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
                        moveTo(7.5f, 12.001f)
                        curveTo(7.5f, 13.194f, 7.974f, 14.339f, 8.818f, 15.183f)
                        curveTo(9.662f, 16.027f, 10.807f, 16.501f, 12.0f, 16.501f)
                        curveTo(13.193f, 16.501f, 14.338f, 16.027f, 15.182f, 15.183f)
                        curveTo(16.026f, 14.339f, 16.5f, 13.194f, 16.5f, 12.001f)
                        curveTo(16.5f, 10.807f, 16.026f, 9.663f, 15.182f, 8.819f)
                        curveTo(14.338f, 7.975f, 13.193f, 7.501f, 12.0f, 7.501f)
                        curveTo(10.807f, 7.501f, 9.662f, 7.975f, 8.818f, 8.819f)
                        curveTo(7.974f, 9.663f, 7.5f, 10.807f, 7.5f, 12.001f)
                        close()
                    }
                }
            }.build()
        return _cog!!
    }

private var _cog: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Cog,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
