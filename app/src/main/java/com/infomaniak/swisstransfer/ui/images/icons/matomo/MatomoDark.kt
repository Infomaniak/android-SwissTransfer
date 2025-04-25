/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.swisstransfer.ui.images.icons.matomo

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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.MatomoDark: ImageVector
    get() {

        if (_matomoDark != null) return _matomoDark!!

        _matomoDark = Builder(
            name = "MatomoDark",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.0f, 12.0f)
                moveToRelative(-12.0f, 0.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, true, true, 24.0f, 0.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, true, true, -24.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF95C748)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(21.35f, 13.478f)
                lineTo(21.302f, 13.402f)
                curveTo(21.293f, 13.393f, 21.285f, 13.382f, 21.279f, 13.37f)
                lineTo(17.939f, 8.288f)
                lineTo(13.623f, 11.493f)
                lineTo(16.794f, 16.36f)
                lineTo(16.833f, 16.422f)
                lineTo(16.851f, 16.447f)
                curveTo(17.253f, 17.028f, 17.866f, 17.428f, 18.56f, 17.563f)
                curveTo(19.253f, 17.697f, 19.972f, 17.554f, 20.561f, 17.164f)
                curveTo(21.15f, 16.775f, 21.564f, 16.171f, 21.713f, 15.481f)
                curveTo(21.862f, 14.79f, 21.735f, 14.069f, 21.359f, 13.471f)
            }
            path(
                fill = SolidColor(Color(0xFF35BFC0)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.403f, 14.893f)
                curveTo(8.403f, 14.358f, 8.245f, 13.836f, 7.948f, 13.392f)
                curveTo(7.651f, 12.948f, 7.229f, 12.601f, 6.735f, 12.397f)
                curveTo(6.242f, 12.193f, 5.699f, 12.139f, 5.174f, 12.243f)
                curveTo(4.65f, 12.347f, 4.169f, 12.605f, 3.791f, 12.983f)
                curveTo(3.413f, 13.36f, 3.156f, 13.842f, 3.052f, 14.366f)
                curveTo(2.948f, 14.89f, 3.001f, 15.433f, 3.206f, 15.927f)
                curveTo(3.41f, 16.42f, 3.756f, 16.842f, 4.201f, 17.139f)
                curveTo(4.645f, 17.436f, 5.167f, 17.594f, 5.701f, 17.594f)
                curveTo(6.418f, 17.594f, 7.105f, 17.31f, 7.612f, 16.803f)
                curveTo(8.118f, 16.296f, 8.403f, 15.609f, 8.403f, 14.893f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3253A0)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(18.354f, 9.703f)
                curveTo(18.355f, 9.281f, 18.258f, 8.864f, 18.069f, 8.487f)
                curveTo(17.881f, 8.109f, 17.607f, 7.781f, 17.268f, 7.528f)
                curveTo(16.93f, 7.276f, 16.538f, 7.106f, 16.122f, 7.032f)
                curveTo(15.707f, 6.959f, 15.28f, 6.983f, 14.875f, 7.104f)
                curveTo(14.471f, 7.225f, 14.101f, 7.439f, 13.794f, 7.729f)
                curveTo(13.487f, 8.019f, 13.252f, 8.376f, 13.108f, 8.773f)
                curveTo(12.965f, 9.17f, 12.916f, 9.595f, 12.965f, 10.014f)
                curveTo(13.015f, 10.433f, 13.163f, 10.834f, 13.395f, 11.186f)
                lineTo(11.335f, 8.288f)
                curveTo(11.094f, 7.895f, 10.756f, 7.57f, 10.353f, 7.345f)
                curveTo(9.951f, 7.12f, 9.497f, 7.002f, 9.036f, 7.002f)
                curveTo(8.575f, 7.002f, 8.122f, 7.12f, 7.719f, 7.345f)
                curveTo(7.317f, 7.57f, 6.979f, 7.895f, 6.738f, 8.288f)
                lineTo(3.473f, 13.38f)
                curveTo(3.727f, 13.004f, 4.071f, 12.698f, 4.474f, 12.49f)
                curveTo(4.877f, 12.282f, 5.326f, 12.18f, 5.78f, 12.191f)
                curveTo(6.233f, 12.203f, 6.676f, 12.329f, 7.068f, 12.557f)
                curveTo(7.46f, 12.785f, 7.788f, 13.108f, 8.022f, 13.496f)
                lineTo(10.153f, 16.493f)
                curveTo(10.404f, 16.835f, 10.731f, 17.113f, 11.109f, 17.305f)
                curveTo(11.487f, 17.497f, 11.905f, 17.597f, 12.328f, 17.597f)
                curveTo(12.752f, 17.597f, 13.17f, 17.497f, 13.548f, 17.305f)
                curveTo(13.926f, 17.113f, 14.253f, 16.835f, 14.503f, 16.493f)
                lineTo(14.526f, 16.459f)
                curveTo(14.582f, 16.381f, 14.633f, 16.3f, 14.68f, 16.216f)
                lineTo(17.885f, 11.218f)
                curveTo(18.186f, 10.77f, 18.345f, 10.242f, 18.343f, 9.703f)
                moveTo(14.029f, 11.868f)
                lineTo(14.082f, 11.907f)
                lineTo(14.045f, 11.868f)
                moveTo(17.193f, 11.919f)
                lineTo(17.251f, 11.871f)
                lineTo(17.184f, 11.921f)
                moveTo(3.407f, 13.487f)
                curveTo(3.427f, 13.453f, 3.446f, 13.421f, 3.468f, 13.389f)
                lineTo(3.404f, 13.487f)
                horizontalLineTo(3.407f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF38334)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(18.344f, 9.703f)
                curveTo(18.344f, 9.168f, 18.186f, 8.646f, 17.889f, 8.201f)
                curveTo(17.591f, 7.757f, 17.169f, 7.411f, 16.675f, 7.206f)
                curveTo(16.181f, 7.002f, 15.638f, 6.949f, 15.113f, 7.054f)
                curveTo(14.589f, 7.158f, 14.108f, 7.416f, 13.73f, 7.795f)
                curveTo(13.353f, 8.173f, 13.096f, 8.655f, 12.993f, 9.18f)
                curveTo(12.889f, 9.704f, 12.943f, 10.248f, 13.149f, 10.741f)
                curveTo(13.354f, 11.235f, 13.702f, 11.656f, 14.147f, 11.952f)
                curveTo(14.592f, 12.248f, 15.115f, 12.406f, 15.65f, 12.404f)
                curveTo(16.366f, 12.404f, 17.053f, 12.12f, 17.56f, 11.613f)
                curveTo(18.067f, 11.106f, 18.351f, 10.419f, 18.351f, 9.703f)
            }
            path(
                fill = SolidColor(Color(0xFF3152A0)), stroke = null, strokeLineWidth =
                    0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                    4.0f, pathFillType = NonZero
            ) {
                moveTo(15.65f, 12.404f)
                curveTo(15.202f, 12.405f, 14.761f, 12.293f, 14.367f, 12.081f)
                curveTo(13.973f, 11.868f, 13.638f, 11.561f, 13.392f, 11.186f)
                lineTo(11.332f, 8.288f)
                curveTo(11.091f, 7.895f, 10.753f, 7.57f, 10.351f, 7.345f)
                curveTo(9.949f, 7.12f, 9.495f, 7.001f, 9.034f, 7.001f)
                curveTo(8.573f, 7.001f, 8.12f, 7.12f, 7.718f, 7.345f)
                curveTo(7.316f, 7.57f, 6.978f, 7.895f, 6.737f, 8.288f)
                lineTo(3.463f, 13.38f)
                curveTo(3.717f, 13.004f, 4.061f, 12.698f, 4.464f, 12.49f)
                curveTo(4.867f, 12.282f, 5.316f, 12.18f, 5.77f, 12.191f)
                curveTo(6.223f, 12.203f, 6.666f, 12.329f, 7.058f, 12.557f)
                curveTo(7.45f, 12.785f, 7.778f, 13.108f, 8.012f, 13.496f)
                lineTo(10.155f, 16.493f)
                curveTo(10.405f, 16.835f, 10.733f, 17.113f, 11.111f, 17.305f)
                curveTo(11.488f, 17.497f, 11.906f, 17.597f, 12.33f, 17.597f)
                curveTo(12.754f, 17.597f, 13.172f, 17.497f, 13.55f, 17.305f)
                curveTo(13.927f, 17.113f, 14.255f, 16.835f, 14.505f, 16.493f)
                lineTo(14.526f, 16.459f)
                lineTo(14.681f, 16.23f)
                lineTo(17.886f, 11.23f)
                curveTo(17.638f, 11.593f, 17.304f, 11.89f, 16.914f, 12.095f)
                curveTo(16.524f, 12.3f, 16.09f, 12.406f, 15.65f, 12.404f)
                close()
            }
        }.build()

        return _matomoDark!!
    }

private var _matomoDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.MatomoDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
