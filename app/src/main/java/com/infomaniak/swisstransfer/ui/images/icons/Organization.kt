/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons

val AppIcons.Organization: ImageVector
    get() {
        if (_organization != null) {
            return _organization!!
        }
        _organization = Builder(
            name = "Organization",
            defaultWidth = 22.dp,
            defaultHeight = 26.dp,
            viewportWidth = 22f,
            viewportHeight = 26f,
        ).apply {
            // Main Building Path (with cut-out mask area)
            path(
                fill = SolidColor(Color(0xFF85A2B6)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(15.75f, 0f)
                curveTo(16.0335f, 0f, 16.2949f, 0.160563f, 16.4209f, 0.414062f)
                lineTo(17.9209f, 3.41406f)
                curveTo(17.9733f, 3.519f, 18f, 3.63308f, 18f, 3.75f)
                verticalLineTo(17.5898f)
                curveTo(17.9994f, 17.5842f, 17.9959f, 17.5789f, 17.9951f, 17.5732f)
                curveTo(17.9963f, 17.6056f, 17.9982f, 17.6382f, 17.9951f, 17.6709f)
                curveTo(18.0143f, 18.0591f, 17.7318f, 18.4021f, 17.3398f, 18.4512f)
                curveTo(16.9289f, 18.5023f, 16.5542f, 18.2108f, 16.5029f, 17.7998f)
                curveTo(16.4975f, 17.7561f, 16.4959f, 17.7013f, 16.4951f, 17.6797f)
                curveTo(16.4949f, 17.6744f, 16.4942f, 17.6684f, 16.4941f, 17.6631f)
                curveTo(16.4938f, 17.6394f, 16.4954f, 17.6016f, 16.5f, 17.5557f)
                verticalLineTo(17.5195f)
                curveTo(16.4994f, 17.5069f, 16.498f, 17.4942f, 16.498f, 17.4814f)
                verticalLineTo(17.4756f)
                curveTo(16.498f, 17.4625f, 16.4993f, 17.4495f, 16.5f, 17.4365f)
                verticalLineTo(4.5f)
                horizontalLineTo(1.5f)
                verticalLineTo(22.5f)
                horizontalLineTo(6f)
                verticalLineTo(21f)
                curveTo(6f, 20.205f, 6.31641f, 19.4414f, 6.87891f, 18.8789f)
                curveTo(7.44141f, 18.3164f, 8.205f, 18f, 9f, 18f)
                curveTo(9.795f, 18f, 10.5586f, 18.3164f, 11.1211f, 18.8789f)
                curveTo(11.6836f, 19.4414f, 12f, 20.205f, 12f, 21f)
                verticalLineTo(22.4902f)
                curveTo(11.9841f, 22.4905f, 11.9681f, 22.4917f, 11.9521f, 22.4922f)
                curveTo(11.9695f, 22.4937f, 11.9867f, 22.4964f, 12.0039f, 22.499f)
                curveTo(12.0178f, 22.4981f, 12.0318f, 22.4971f, 12.0459f, 22.4971f)
                curveTo(12.4601f, 22.4971f, 12.7959f, 22.8329f, 12.7959f, 23.2471f)
                verticalLineTo(23.2666f)
                curveTo(12.7956f, 23.279f, 12.7944f, 23.2987f, 12.792f, 23.3223f)
                curveTo(12.7869f, 23.3729f, 12.7747f, 23.4396f, 12.7471f, 23.5127f)
                curveTo(12.689f, 23.666f, 12.5949f, 23.7673f, 12.5254f, 23.8252f)
                curveTo(12.4189f, 23.9138f, 12.31f, 23.9515f, 12.3057f, 23.9531f)
                curveTo(12.2438f, 23.9765f, 12.1702f, 23.9956f, 12.0889f, 24.0049f)
                curveTo(12.0205f, 24.0126f, 11.9513f, 24.0117f, 11.9434f, 24.0117f)
                curveTo(11.9044f, 24.0117f, 11.8664f, 24.0067f, 11.8291f, 24.001f)
                curveTo(11.8133f, 24.0005f, 11.7971f, 24.0024f, 11.7812f, 24.001f)
                curveTo(11.7728f, 24.0018f, 11.7655f, 24.0044f, 11.7598f, 24.0049f)
                curveTo(11.7528f, 24.0054f, 11.7453f, 24.0044f, 11.7383f, 24.0049f)
                curveTo(11.7251f, 24.0054f, 11.7157f, 24.0077f, 11.7129f, 24.0078f)
                curveTo(11.6758f, 24.009f, 11.6356f, 24.0104f, 11.6045f, 24.0107f)
                horizontalLineTo(11.501f)
                curveTo(11.497f, 24.0106f, 11.4876f, 24.0101f, 11.4814f, 24.0098f)
                curveTo(11.4741f, 24.0092f, 11.4521f, 24.0044f, 11.4238f, 24f)
                horizontalLineTo(0.75f)
                curveTo(0.336f, 24f, 0f, 23.664f, 0f, 23.25f)
                verticalLineTo(3.75f)
                curveTo(0f, 3.63308f, 0.0266703f, 3.519f, 0.0791016f, 3.41406f)
                lineTo(1.5791f, 0.414062f)
                curveTo(1.7051f, 0.160563f, 1.9665f, 0f, 2.25f, 0f)
                horizontalLineTo(15.75f)
                close()
                moveTo(11.2852f, 23.9316f)
                curveTo(11.2836f, 23.9293f, 11.2818f, 23.9271f, 11.2803f, 23.9248f)
                curveTo(11.2641f, 23.9235f, 11.2484f, 23.9203f, 11.2324f, 23.918f)
                curveTo(11.25f, 23.9221f, 11.2671f, 23.9288f, 11.2852f, 23.9316f)
                close()
                moveTo(10.9961f, 22.6953f)
                curveTo(10.9938f, 22.6979f, 10.992f, 22.701f, 10.9902f, 22.7031f)
                curveTo(10.9935f, 22.6994f, 10.9978f, 22.6951f, 11.002f, 22.6904f)
                curveTo(11.0001f, 22.6924f, 10.9976f, 22.6938f, 10.9961f, 22.6953f)
                close()
                moveTo(9f, 19.5f)
                curveTo(8.6025f, 19.5f, 8.22145f, 19.659f, 7.93945f, 19.9395f)
                curveTo(7.65745f, 20.2215f, 7.5f, 20.6025f, 7.5f, 21f)
                verticalLineTo(22.5f)
                horizontalLineTo(10.5f)
                verticalLineTo(21f)
                curveTo(10.5f, 20.6025f, 10.3425f, 20.2215f, 10.0605f, 19.9395f)
                curveTo(9.77855f, 19.659f, 9.3975f, 19.5f, 9f, 19.5f)
                close()
                moveTo(17.957f, 17.8564f)
                curveTo(17.9586f, 17.852f, 17.9619f, 17.8421f, 17.9658f, 17.8291f)
                curveTo(17.9667f, 17.8263f, 17.9669f, 17.8232f, 17.9678f, 17.8203f)
                curveTo(17.9641f, 17.8322f, 17.9613f, 17.8446f, 17.957f, 17.8564f)
                close()
                moveTo(1.96387f, 3f)
                horizontalLineTo(16.0361f)
                lineTo(15.2861f, 1.5f)
                horizontalLineTo(2.71387f)
                lineTo(1.96387f, 3f)
                close()
            }
            // Circular Arrows Badge Part 1
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(20.2803f, 21.5723f)
                curveTo(20.436f, 21.4663f, 20.6439f, 21.4847f, 20.7822f, 21.626f)
                lineTo(21.874f, 22.7402f)
                curveTo(22.0369f, 22.9071f, 22.0424f, 23.1838f, 21.8867f, 23.3584f)
                curveTo(21.7307f, 23.5329f, 21.4719f, 23.5388f, 21.3086f, 23.3721f)
                lineTo(20.8799f, 22.9346f)
                curveTo(20.8126f, 23.4849f, 20.6211f, 24.0127f, 20.3154f, 24.4727f)
                curveTo(19.9299f, 25.0527f, 19.3848f, 25.4984f, 18.7529f, 25.7549f)
                curveTo(18.1212f, 26.0112f, 17.4293f, 26.0679f, 16.7666f, 25.918f)
                curveTo(16.1038f, 25.768f, 15.4972f, 25.4175f, 15.0273f, 24.9092f)
                curveTo(14.8687f, 24.7373f, 14.8697f, 24.4598f, 15.0303f, 24.29f)
                curveTo(15.191f, 24.1203f, 15.4506f, 24.1231f, 15.6094f, 24.2949f)
                curveTo(15.964f, 24.6784f, 16.426f, 24.9461f, 16.9355f, 25.0615f)
                curveTo(17.4455f, 25.1769f, 17.9784f, 25.1341f, 18.4629f, 24.9375f)
                curveTo(18.9471f, 24.741f, 19.3593f, 24.4015f, 19.6484f, 23.9668f)
                curveTo(19.8402f, 23.6782f, 19.971f, 23.3549f, 20.0381f, 23.0186f)
                lineTo(19.6914f, 23.3721f)
                curveTo(19.528f, 23.5389f, 19.2693f, 23.533f, 19.1133f, 23.3584f)
                curveTo(18.9574f, 23.1836f, 18.9636f, 22.9071f, 19.127f, 22.7402f)
                lineTo(20.2178f, 21.626f)
                lineTo(20.2803f, 21.5723f)
                close()
            }
            // Circular Arrows Badge Part 2
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(16.3535f, 19.2041f)
                curveTo(17.0111f, 18.963f, 17.7252f, 18.9352f, 18.3984f, 19.124f)
                curveTo(19.0717f, 19.3129f, 19.6746f, 19.7099f, 20.123f, 20.2646f)
                curveTo(20.2706f, 20.4474f, 20.2519f, 20.7239f, 20.0811f, 20.8818f)
                curveTo(19.9102f, 21.0397f, 19.6516f, 21.0195f, 19.5039f, 20.8369f)
                curveTo(19.1662f, 20.4192f, 18.708f, 20.1149f, 18.1904f, 19.9697f)
                curveTo(17.6728f, 19.8246f, 17.1229f, 19.8461f, 16.6182f, 20.0312f)
                curveTo(16.1138f, 20.2163f, 15.6807f, 20.5552f, 15.376f, 20.9971f)
                curveTo(15.1086f, 21.3851f, 14.9507f, 21.8376f, 14.916f, 22.3047f)
                lineTo(15.3086f, 21.9043f)
                curveTo(15.472f, 21.7376f, 15.7307f, 21.7443f, 15.8867f, 21.9189f)
                curveTo(16.0425f, 22.0937f, 16.0363f, 22.3703f, 15.873f, 22.5371f)
                lineTo(14.79f, 23.6416f)
                curveTo(14.7506f, 23.6845f, 14.7036f, 23.7186f, 14.6504f, 23.7412f)
                curveTo(14.6481f, 23.7422f, 14.6459f, 23.7432f, 14.6436f, 23.7441f)
                curveTo(14.5989f, 23.7621f, 14.5506f, 23.7734f, 14.5f, 23.7734f)
                curveTo(14.4495f, 23.7734f, 14.4011f, 23.7621f, 14.3564f, 23.7441f)
                curveTo(14.3539f, 23.7431f, 14.3512f, 23.7423f, 14.3486f, 23.7412f)
                curveTo(14.2939f, 23.7178f, 14.2452f, 23.6825f, 14.2051f, 23.6377f)
                lineTo(13.126f, 22.5371f)
                curveTo(12.963f, 22.3702f, 12.9575f, 22.0936f, 13.1133f, 21.9189f)
                curveTo(13.2693f, 21.7443f, 13.528f, 21.7376f, 13.6914f, 21.9043f)
                lineTo(14.0967f, 22.3184f)
                curveTo(14.1304f, 21.657f, 14.3454f, 21.0189f, 14.7178f, 20.4785f)
                curveTo(15.1239f, 19.8893f, 15.6958f, 19.4453f, 16.3535f, 19.2041f)
                close()
            }
            // Windows - Row 3
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(4.49414f, 14.625f)
                curveTo(5.11508f, 14.6251f, 5.61914f, 15.129f, 5.61914f, 15.75f)
                curveTo(5.61914f, 16.371f, 5.11508f, 16.8749f, 4.49414f, 16.875f)
                curveTo(3.87314f, 16.875f, 3.36914f, 16.371f, 3.36914f, 15.75f)
                curveTo(3.36914f, 15.129f, 3.87314f, 14.625f, 4.49414f, 14.625f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(9f, 14.625f)
                curveTo(9.621f, 14.625f, 10.125f, 15.129f, 10.125f, 15.75f)
                curveTo(10.125f, 16.371f, 9.621f, 16.875f, 9f, 16.875f)
                curveTo(8.379f, 16.875f, 7.875f, 16.371f, 7.875f, 15.75f)
                curveTo(7.875f, 15.129f, 8.379f, 14.625f, 9f, 14.625f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(13.4941f, 14.625f)
                curveTo(14.1151f, 14.6251f, 14.6191f, 15.129f, 14.6191f, 15.75f)
                curveTo(14.6191f, 16.371f, 14.1151f, 16.8749f, 13.4941f, 16.875f)
                curveTo(12.8731f, 16.875f, 12.3691f, 16.371f, 12.3691f, 15.75f)
                curveTo(12.3691f, 15.129f, 12.8731f, 14.625f, 13.4941f, 14.625f)
                close()
            }
            // Windows - Row 2
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(4.49414f, 10.125f)
                curveTo(5.11508f, 10.1251f, 5.61914f, 10.629f, 5.61914f, 11.25f)
                curveTo(5.61914f, 11.871f, 5.11508f, 12.3749f, 4.49414f, 12.375f)
                curveTo(3.87314f, 12.375f, 3.36914f, 11.871f, 3.36914f, 11.25f)
                curveTo(3.36914f, 10.629f, 3.87314f, 10.125f, 4.49414f, 10.125f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(9f, 10.125f)
                curveTo(9.621f, 10.125f, 10.125f, 10.629f, 10.125f, 11.25f)
                curveTo(10.125f, 11.871f, 9.621f, 12.375f, 9f, 12.375f)
                curveTo(8.379f, 12.375f, 7.875f, 11.871f, 7.875f, 11.25f)
                curveTo(7.875f, 10.629f, 8.379f, 10.125f, 9f, 10.125f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(13.4941f, 10.125f)
                curveTo(14.1151f, 10.1251f, 14.6191f, 10.629f, 14.6191f, 11.25f)
                curveTo(14.6191f, 11.871f, 14.1151f, 12.3749f, 13.4941f, 12.375f)
                curveTo(12.8731f, 12.375f, 12.3691f, 11.871f, 12.3691f, 11.25f)
                curveTo(12.3691f, 10.629f, 12.8731f, 10.125f, 13.4941f, 10.125f)
                close()
            }
            // Windows - Row 1
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(4.49414f, 5.625f)
                curveTo(5.11508f, 5.62508f, 5.61914f, 6.12905f, 5.61914f, 6.75f)
                curveTo(5.61914f, 7.37095f, 5.11508f, 7.87492f, 4.49414f, 7.875f)
                curveTo(3.87314f, 7.875f, 3.36914f, 7.371f, 3.36914f, 6.75f)
                curveTo(3.36914f, 6.129f, 3.87314f, 5.625f, 4.49414f, 5.625f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(9f, 5.625f)
                curveTo(9.621f, 5.625f, 10.125f, 6.129f, 10.125f, 6.75f)
                curveTo(10.125f, 7.371f, 9.621f, 7.875f, 9f, 7.875f)
                curveTo(8.379f, 7.875f, 7.875f, 7.371f, 7.875f, 6.75f)
                curveTo(7.875f, 6.129f, 8.379f, 5.625f, 9f, 5.625f)
                close()
            }
            path(fill = SolidColor(Color(0xFF85A2B6))) {
                moveTo(13.4941f, 5.625f)
                curveTo(14.1151f, 5.62508f, 14.6191f, 6.12905f, 14.6191f, 6.75f)
                curveTo(14.6191f, 7.37095f, 14.1151f, 7.87492f, 13.4941f, 7.875f)
                curveTo(12.8731f, 7.875f, 12.3691f, 7.371f, 12.3691f, 6.75f)
                curveTo(12.3691f, 6.129f, 12.8731f, 5.625f, 13.4941f, 5.625f)
                close()
            }
        }.build()

        return _organization!!
    }

private var _organization: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Organization,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
