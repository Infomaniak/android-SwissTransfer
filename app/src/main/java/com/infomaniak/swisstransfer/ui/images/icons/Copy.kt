package com.infomaniak.swisstransfer.ui.images.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIcons.Copy: ImageVector
    get() {

        if (_copy != null) return _copy!!

        _copy = Builder(
            name = "Copy",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f, strokeLineCap = strokeCapRound, strokeLineJoin =
                strokeJoinRound, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(7.5f, 6.75f)
                lineTo(7.5f, 2.25f)
                curveTo(7.5f, 1.852f, 7.658f, 1.471f, 7.939f, 1.189f)
                curveTo(8.221f, 0.908f, 8.602f, 0.75f, 9.0f, 0.75f)
                lineTo(16.629f, 0.75f)
                curveTo(17.027f, 0.75f, 17.408f, 0.908f, 17.689f, 1.189f)
                lineTo(20.561f, 4.061f)
                curveTo(20.842f, 4.342f, 21.0f, 4.723f, 21.0f, 5.121f)
                lineTo(21.0f, 15.75f)
                curveTo(21.0f, 16.148f, 20.842f, 16.529f, 20.561f, 16.811f)
                curveTo(20.279f, 17.092f, 19.898f, 17.25f, 19.5f, 17.25f)
                lineTo(16.5f, 17.25f)
            }
            path(
                fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF9f9f9f)),
                strokeLineWidth = 1.5f, strokeLineCap = strokeCapRound, strokeLineJoin =
                strokeJoinRound, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(16.5f, 21.75f)
                curveTo(16.5f, 22.148f, 16.342f, 22.529f, 16.061f, 22.811f)
                curveTo(15.779f, 23.092f, 15.398f, 23.25f, 15.0f, 23.25f)
                lineTo(4.5f, 23.25f)
                curveTo(4.102f, 23.25f, 3.721f, 23.092f, 3.439f, 22.811f)
                curveTo(3.158f, 22.529f, 3.0f, 22.148f, 3.0f, 21.75f)
                lineTo(3.0f, 8.25f)
                curveTo(3.0f, 7.852f, 3.158f, 7.471f, 3.439f, 7.189f)
                curveTo(3.721f, 6.908f, 4.102f, 6.75f, 4.5f, 6.75f)
                lineTo(12.129f, 6.75f)
                curveTo(12.526f, 6.75f, 12.908f, 6.908f, 13.189f, 7.189f)
                lineTo(16.061f, 10.061f)
                curveTo(16.342f, 10.342f, 16.5f, 10.724f, 16.5f, 11.121f)
                lineTo(16.5f, 21.75f)
                close()
            }
        }.build()

        return _copy!!
    }

private var _copy: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIcons.Copy,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize),
        )
    }
}
