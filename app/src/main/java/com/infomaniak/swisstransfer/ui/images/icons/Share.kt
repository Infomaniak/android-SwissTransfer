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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Share: ImageVector
    get() {

        if (_share != null) return _share!!

        _share = Builder(
            name = "Share",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF9f9f9f)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = EvenOdd
            ) {
                moveTo(19.644f, 16.175f)
                curveTo(19.673f, 16.206f, 19.702f, 16.237f, 19.732f, 16.268f)
                curveTo(20.201f, 16.737f, 20.837f, 17.0f, 21.5f, 17.0f)
                curveTo(22.163f, 17.0f, 22.799f, 16.737f, 23.268f, 16.268f)
                curveTo(23.737f, 15.799f, 24.0f, 15.163f, 24.0f, 14.5f)
                curveTo(24.0f, 13.837f, 23.737f, 13.201f, 23.268f, 12.732f)
                curveTo(22.799f, 12.263f, 22.163f, 12.0f, 21.5f, 12.0f)
                curveTo(20.837f, 12.0f, 20.201f, 12.263f, 19.732f, 12.732f)
                curveTo(19.263f, 13.201f, 19.0f, 13.837f, 19.0f, 14.5f)
                curveTo(19.0f, 14.604f, 19.007f, 14.708f, 19.019f, 14.81f)
                lineTo(16.236f, 16.202f)
                curveTo(15.771f, 15.752f, 15.148f, 15.5f, 14.5f, 15.5f)
                curveTo(13.837f, 15.5f, 13.201f, 15.763f, 12.732f, 16.232f)
                curveTo(12.263f, 16.701f, 12.0f, 17.337f, 12.0f, 18.0f)
                curveTo(12.0f, 18.663f, 12.263f, 19.299f, 12.732f, 19.768f)
                curveTo(13.201f, 20.237f, 13.837f, 20.5f, 14.5f, 20.5f)
                curveTo(15.148f, 20.5f, 15.771f, 20.248f, 16.236f, 19.799f)
                lineTo(19.019f, 21.19f)
                curveTo(19.007f, 21.292f, 19.0f, 21.396f, 19.0f, 21.5f)
                curveTo(19.0f, 22.163f, 19.263f, 22.799f, 19.732f, 23.268f)
                curveTo(20.201f, 23.737f, 20.837f, 24.0f, 21.5f, 24.0f)
                curveTo(22.163f, 24.0f, 22.799f, 23.737f, 23.268f, 23.268f)
                curveTo(23.737f, 22.799f, 24.0f, 22.163f, 24.0f, 21.5f)
                curveTo(24.0f, 20.837f, 23.737f, 20.201f, 23.268f, 19.732f)
                curveTo(22.799f, 19.263f, 22.163f, 19.0f, 21.5f, 19.0f)
                curveTo(20.837f, 19.0f, 20.201f, 19.263f, 19.732f, 19.732f)
                curveTo(19.702f, 19.763f, 19.673f, 19.794f, 19.644f, 19.825f)
                lineTo(16.954f, 18.48f)
                curveTo(16.984f, 18.323f, 17.0f, 18.162f, 17.0f, 18.0f)
                curveTo(17.0f, 17.838f, 16.984f, 17.677f, 16.954f, 17.52f)
                lineTo(19.644f, 16.175f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f, strokeLineCap = strokeCapRound, strokeLineJoin =
                strokeJoinRound, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(9.983f, 19.75f)
                lineTo(0.75f, 19.75f)
                curveTo(0.75f, 17.622f, 1.617f, 15.581f, 3.161f, 14.076f)
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
        }.build()

        return _share!!
    }

private var _share: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Share,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
