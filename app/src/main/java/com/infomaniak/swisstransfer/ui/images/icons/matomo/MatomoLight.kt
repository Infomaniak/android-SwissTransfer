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

val AppIcons.MatomoLight: ImageVector
    get() {

        if (_matomoLight != null) return _matomoLight!!

        _matomoLight = Builder(
            name = "MatomoLight",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF95C748)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(23.42f, 13.465f)
                lineTo(23.359f, 13.369f)
                curveTo(23.347f, 13.356f, 23.337f, 13.342f, 23.33f, 13.328f)
                lineTo(19.066f, 6.841f)
                lineTo(13.559f, 10.932f)
                lineTo(17.605f, 17.144f)
                lineTo(17.655f, 17.223f)
                lineTo(17.678f, 17.255f)
                curveTo(18.191f, 17.996f, 18.974f, 18.507f, 19.859f, 18.678f)
                curveTo(20.744f, 18.849f, 21.661f, 18.667f, 22.413f, 18.17f)
                curveTo(23.166f, 17.674f, 23.694f, 16.902f, 23.884f, 16.021f)
                curveTo(24.074f, 15.14f, 23.912f, 14.219f, 23.432f, 13.456f)
            }
            path(
                fill = SolidColor(Color(0xFF35BFC0)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(6.896f, 15.271f)
                curveTo(6.896f, 14.589f, 6.694f, 13.922f, 6.315f, 13.355f)
                curveTo(5.936f, 12.788f, 5.397f, 12.346f, 4.767f, 12.085f)
                curveTo(4.137f, 11.825f, 3.444f, 11.756f, 2.775f, 11.889f)
                curveTo(2.106f, 12.022f, 1.492f, 12.351f, 1.01f, 12.833f)
                curveTo(0.528f, 13.315f, 0.199f, 13.929f, 0.066f, 14.598f)
                curveTo(-0.067f, 15.267f, 0.001f, 15.96f, 0.262f, 16.59f)
                curveTo(0.523f, 17.22f, 0.965f, 17.759f, 1.532f, 18.138f)
                curveTo(2.099f, 18.516f, 2.766f, 18.719f, 3.448f, 18.719f)
                curveTo(4.362f, 18.719f, 5.239f, 18.355f, 5.886f, 17.709f)
                curveTo(6.532f, 17.062f, 6.896f, 16.185f, 6.896f, 15.271f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3253A0)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(19.596f, 8.647f)
                curveTo(19.597f, 8.108f, 19.473f, 7.577f, 19.232f, 7.095f)
                curveTo(18.992f, 6.613f, 18.642f, 6.194f, 18.21f, 5.872f)
                curveTo(17.779f, 5.55f, 17.278f, 5.333f, 16.747f, 5.239f)
                curveTo(16.217f, 5.145f, 15.672f, 5.176f, 15.156f, 5.331f)
                curveTo(14.64f, 5.485f, 14.167f, 5.758f, 13.776f, 6.128f)
                curveTo(13.384f, 6.498f, 13.085f, 6.954f, 12.901f, 7.46f)
                curveTo(12.717f, 7.967f, 12.655f, 8.509f, 12.718f, 9.044f)
                curveTo(12.782f, 9.578f, 12.97f, 10.091f, 13.267f, 10.54f)
                lineTo(10.637f, 6.841f)
                curveTo(10.329f, 6.34f, 9.898f, 5.925f, 9.384f, 5.638f)
                curveTo(8.871f, 5.35f, 8.292f, 5.2f, 7.704f, 5.2f)
                curveTo(7.115f, 5.2f, 6.536f, 5.35f, 6.023f, 5.638f)
                curveTo(5.509f, 5.925f, 5.078f, 6.34f, 4.77f, 6.841f)
                lineTo(0.603f, 13.339f)
                curveTo(0.927f, 12.86f, 1.367f, 12.469f, 1.881f, 12.204f)
                curveTo(2.395f, 11.939f, 2.968f, 11.808f, 3.547f, 11.823f)
                curveTo(4.126f, 11.838f, 4.691f, 11.998f, 5.191f, 12.289f)
                curveTo(5.692f, 12.58f, 6.11f, 12.993f, 6.409f, 13.488f)
                lineTo(9.129f, 17.313f)
                curveTo(9.449f, 17.75f, 9.867f, 18.105f, 10.349f, 18.35f)
                curveTo(10.831f, 18.594f, 11.364f, 18.722f, 11.905f, 18.722f)
                curveTo(12.446f, 18.722f, 12.979f, 18.594f, 13.462f, 18.35f)
                curveTo(13.944f, 18.105f, 14.362f, 17.75f, 14.681f, 17.313f)
                lineTo(14.71f, 17.269f)
                curveTo(14.782f, 17.17f, 14.847f, 17.067f, 14.906f, 16.96f)
                lineTo(18.997f, 10.581f)
                curveTo(19.381f, 10.009f, 19.584f, 9.336f, 19.581f, 8.647f)
                moveTo(14.076f, 11.411f)
                lineTo(14.144f, 11.461f)
                lineTo(14.097f, 11.411f)
                moveTo(18.114f, 11.475f)
                lineTo(18.187f, 11.414f)
                lineTo(18.103f, 11.478f)
                moveTo(0.519f, 13.477f)
                curveTo(0.545f, 13.433f, 0.568f, 13.392f, 0.597f, 13.351f)
                lineTo(0.516f, 13.477f)
                horizontalLineTo(0.519f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFF38334)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(19.581f, 8.647f)
                curveTo(19.581f, 7.965f, 19.379f, 7.298f, 19.0f, 6.731f)
                curveTo(18.62f, 6.163f, 18.081f, 5.721f, 17.451f, 5.461f)
                curveTo(16.82f, 5.2f, 16.127f, 5.132f, 15.458f, 5.266f)
                curveTo(14.788f, 5.4f, 14.174f, 5.729f, 13.692f, 6.212f)
                curveTo(13.21f, 6.695f, 12.883f, 7.31f, 12.751f, 7.98f)
                curveTo(12.619f, 8.649f, 12.688f, 9.343f, 12.95f, 9.972f)
                curveTo(13.213f, 10.602f, 13.656f, 11.14f, 14.224f, 11.518f)
                curveTo(14.792f, 11.896f, 15.46f, 12.097f, 16.142f, 12.095f)
                curveTo(17.057f, 12.095f, 17.934f, 11.731f, 18.58f, 11.085f)
                curveTo(19.227f, 10.438f, 19.59f, 9.561f, 19.59f, 8.647f)
            }
            path(
                fill = SolidColor(Color(0xFF3152A0)), stroke = null, strokeLineWidth =
                0.0f, strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter =
                4.0f, pathFillType = NonZero
            ) {
                moveTo(16.143f, 12.095f)
                curveTo(15.571f, 12.095f, 15.009f, 11.953f, 14.506f, 11.682f)
                curveTo(14.003f, 11.41f, 13.575f, 11.018f, 13.262f, 10.54f)
                lineTo(10.632f, 6.841f)
                curveTo(10.325f, 6.34f, 9.894f, 5.925f, 9.38f, 5.638f)
                curveTo(8.867f, 5.35f, 8.288f, 5.199f, 7.7f, 5.199f)
                curveTo(7.112f, 5.199f, 6.533f, 5.35f, 6.02f, 5.638f)
                curveTo(5.506f, 5.925f, 5.075f, 6.34f, 4.768f, 6.841f)
                lineTo(0.59f, 13.339f)
                curveTo(0.913f, 12.86f, 1.353f, 12.469f, 1.867f, 12.204f)
                curveTo(2.382f, 11.939f, 2.955f, 11.808f, 3.533f, 11.823f)
                curveTo(4.112f, 11.838f, 4.677f, 11.998f, 5.178f, 12.289f)
                curveTo(5.678f, 12.58f, 6.097f, 12.993f, 6.395f, 13.488f)
                lineTo(9.13f, 17.313f)
                curveTo(9.45f, 17.75f, 9.867f, 18.105f, 10.35f, 18.35f)
                curveTo(10.832f, 18.594f, 11.365f, 18.722f, 11.906f, 18.722f)
                curveTo(12.447f, 18.722f, 12.98f, 18.594f, 13.463f, 18.35f)
                curveTo(13.945f, 18.105f, 14.363f, 17.75f, 14.682f, 17.313f)
                lineTo(14.708f, 17.269f)
                lineTo(14.907f, 16.977f)
                lineTo(18.998f, 10.596f)
                curveTo(18.68f, 11.06f, 18.254f, 11.439f, 17.757f, 11.7f)
                curveTo(17.259f, 11.962f, 16.705f, 12.097f, 16.143f, 12.095f)
                close()
            }
        }.build()

        return _matomoLight!!
    }

private var _matomoLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.MatomoLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
