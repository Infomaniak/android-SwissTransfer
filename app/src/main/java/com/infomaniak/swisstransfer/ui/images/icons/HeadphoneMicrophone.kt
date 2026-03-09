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

val AppIcons.HeadphoneMicrophone: ImageVector
    get() {
        if (_headphoneMicrophone != null) {
            return _headphoneMicrophone!!
        }
        _headphoneMicrophone = Builder(
            name = "HeadphoneMicrophone",
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
                        moveTo(4.5f, 18.011f)
                        horizontalLineTo(3.75f)
                        curveTo(2.954f, 18.011f, 2.191f, 17.695f, 1.629f, 17.132f)
                        curveTo(1.066f, 16.57f, 0.75f, 15.807f, 0.75f, 15.011f)
                        verticalLineTo(12.011f)
                        curveTo(0.75f, 11.215f, 1.066f, 10.452f, 1.629f, 9.89f)
                        curveTo(2.191f, 9.327f, 2.954f, 9.011f, 3.75f, 9.011f)
                        horizontalLineTo(4.5f)
                        curveTo(4.914f, 9.011f, 6.0f, 9.347f, 6.0f, 9.761f)
                        verticalLineTo(17.261f)
                        curveTo(6.0f, 17.675f, 4.914f, 18.011f, 4.5f, 18.011f)
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
                        moveTo(20.25f, 18.011f)
                        horizontalLineTo(19.5f)
                        curveTo(19.086f, 18.011f, 18.0f, 17.675f, 18.0f, 17.261f)
                        verticalLineTo(9.761f)
                        curveTo(18.0f, 9.347f, 19.086f, 9.011f, 19.5f, 9.011f)
                        horizontalLineTo(20.25f)
                        curveTo(21.046f, 9.011f, 21.809f, 9.327f, 22.371f, 9.89f)
                        curveTo(22.934f, 10.452f, 23.25f, 11.215f, 23.25f, 12.011f)
                        verticalLineTo(15.011f)
                        curveTo(23.25f, 15.807f, 22.934f, 16.57f, 22.371f, 17.132f)
                        curveTo(21.809f, 17.695f, 21.046f, 18.011f, 20.25f, 18.011f)
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
                        moveTo(3.75f, 9.011f)
                        curveTo(3.75f, 6.823f, 4.619f, 4.725f, 6.166f, 3.177f)
                        curveTo(7.714f, 1.63f, 9.812f, 0.761f, 12.0f, 0.761f)
                        curveTo(14.188f, 0.761f, 16.287f, 1.63f, 17.834f, 3.177f)
                        curveTo(19.381f, 4.725f, 20.25f, 6.823f, 20.25f, 9.011f)
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
                        moveTo(15.0f, 21.761f)
                        horizontalLineTo(17.25f)
                        curveTo(18.046f, 21.761f, 18.809f, 21.445f, 19.371f, 20.882f)
                        curveTo(19.934f, 20.32f, 20.25f, 19.557f, 20.25f, 18.761f)
                        verticalLineTo(18.011f)
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
                        moveTo(13.5f, 23.261f)
                        horizontalLineTo(10.5f)
                        curveTo(10.102f, 23.261f, 9.721f, 23.103f, 9.439f, 22.822f)
                        curveTo(9.158f, 22.54f, 9.0f, 22.159f, 9.0f, 21.761f)
                        curveTo(9.0f, 21.363f, 9.158f, 20.982f, 9.439f, 20.7f)
                        curveTo(9.721f, 20.419f, 10.102f, 20.261f, 10.5f, 20.261f)
                        horizontalLineTo(13.5f)
                        curveTo(13.898f, 20.261f, 14.279f, 20.419f, 14.561f, 20.7f)
                        curveTo(14.842f, 20.982f, 15.0f, 21.363f, 15.0f, 21.761f)
                        curveTo(15.0f, 22.159f, 14.842f, 22.54f, 14.561f, 22.822f)
                        curveTo(14.279f, 23.103f, 13.898f, 23.261f, 13.5f, 23.261f)
                        close()
                    }
                }
            }.build()
        return _headphoneMicrophone!!
    }

private var _headphoneMicrophone: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.HeadphoneMicrophone,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
