package com.infomaniak.swisstransfer.ui.images.illus.uploadAd

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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.SwissWithFlagDark: ImageVector
    get() {
        if (_swissWithFlagDark != null) {
            return _swissWithFlagDark!!
        }
        _swissWithFlagDark = Builder(
            name = "SwissWithFlagDark",
            defaultWidth = 343.0.dp,
            defaultHeight = 211.0.dp,
            viewportWidth = 343.0f,
            viewportHeight = 211.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(30.0f, 175.41f)
                lineToRelative(27.18f, -8.98f)
                verticalLineToRelative(-9.34f)
                lineToRelative(14.58f, -2.61f)
                lineToRelative(25.2f, -15.0f)
                lineToRelative(-10.62f, -2.52f)
                lineToRelative(10.08f, -5.21f)
                lineToRelative(19.62f, 4.49f)
                lineToRelative(10.44f, -3.95f)
                verticalLineToRelative(-3.5f)
                lineToRelative(15.3f, 1.08f)
                horizontalLineToRelative(23.22f)
                lineToRelative(4.32f, -3.06f)
                lineToRelative(25.02f, 1.26f)
                lineToRelative(2.88f, -3.05f)
                lineToRelative(-12.24f, 0.9f)
                verticalLineToRelative(-5.39f)
                lineTo(198.68f, 119.0f)
                lineToRelative(13.68f, 6.02f)
                lineToRelative(22.14f, -0.99f)
                lineToRelative(28.8f, 13.65f)
                lineToRelative(-10.98f, 5.48f)
                verticalLineToRelative(9.97f)
                lineToRelative(24.48f, 3.06f)
                lineToRelative(1.62f, 3.32f)
                lineToRelative(14.94f, 4.31f)
                lineToRelative(15.12f, -7.64f)
                lineToRelative(4.5f, 0.9f)
                verticalLineToRelative(20.3f)
                lineToRelative(-19.62f, -3.59f)
                lineToRelative(-5.4f, 8.53f)
                lineToRelative(5.4f, 1.71f)
                lineToRelative(-7.02f, 7.73f)
                lineToRelative(-7.92f, -5.66f)
                lineToRelative(-5.04f, 4.49f)
                lineToRelative(-17.64f, -1.35f)
                lineToRelative(-11.34f, -8.27f)
                lineToRelative(-18.9f, 17.34f)
                lineToRelative(-3.42f, 3.95f)
                lineToRelative(3.42f, 7.73f)
                lineToRelative(-10.98f, -2.61f)
                lineToRelative(-3.24f, -9.07f)
                horizontalLineToRelative(-16.92f)
                lineToRelative(-11.16f, -9.07f)
                lineToRelative(3.42f, -5.21f)
                lineToRelative(-5.22f, -1.71f)
                lineToRelative(-20.7f, 9.43f)
                lineToRelative(6.48f, 7.82f)
                lineToRelative(-16.38f, 7.82f)
                lineToRelative(-29.52f, -5.12f)
                lineTo(96.97f, 210.0f)
                lineToRelative(-16.74f, -16.53f)
                lineToRelative(14.58f, -11.14f)
                lineToRelative(-19.44f, -4.94f)
                lineToRelative(-27.18f, 2.61f)
                lineToRelative(-6.48f, 8.18f)
                lineToRelative(5.04f, 4.49f)
                lineTo(30.0f, 195.63f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFEC1D25)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(172.12f, 46.21f)
                horizontalLineToRelative(54.43f)
                verticalLineToRelative(54.43f)
                horizontalLineToRelative(-54.43f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(193.89f, 57.09f)
                horizontalLineToRelative(10.89f)
                verticalLineToRelative(21.77f)
                horizontalLineToRelative(-10.89f)
                verticalLineToRelative(-21.77f)
                moveToRelative(-10.89f, 10.89f)
                horizontalLineToRelative(10.89f)
                verticalLineToRelative(10.89f)
                horizontalLineToRelative(-10.89f)
                close()
                moveTo(204.78f, 67.98f)
                horizontalLineToRelative(10.89f)
                verticalLineToRelative(10.89f)
                horizontalLineToRelative(-10.89f)
                close()
                moveTo(204.78f, 78.86f)
                horizontalLineToRelative(-10.89f)
                lineTo(193.89f, 89.75f)
                horizontalLineToRelative(10.89f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(167.47f, 38.83f)
                arcToRelative(2.81f, 2.81f, 0.0f, true, true, 5.63f, 0.0f)
                lineToRelative(-0.96f, 122.33f)
                arcToRelative(1.85f, 1.85f, 0.0f, false, true, -3.7f, 0.0f)
                close()
            }
        }.build()
        return _swissWithFlagDark!!
    }

private var _swissWithFlagDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.SwissWithFlagDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
