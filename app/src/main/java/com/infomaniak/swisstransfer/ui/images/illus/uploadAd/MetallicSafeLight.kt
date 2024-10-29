package com.infomaniak.swisstransfer.ui.images.illus.uploadAd

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
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.MetallicSafeLight: ImageVector
    get() {
        if (_metallicSafeLight != null) {
            return _metallicSafeLight!!
        }
        _metallicSafeLight = Builder(
            name = "MetallicSafeLight",
            defaultWidth = 343.0.dp,
            defaultHeight = 210.0.dp,
            viewportWidth = 343.0f,
            viewportHeight = 210.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(141.0f, 66.5f)
                horizontalLineToRelative(129.0f)
                verticalLineToRelative(143.0f)
                horizontalLineTo(141.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(206.0f, 95.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 2.5f, 2.5f)
                verticalLineToRelative(83.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, 2.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, -2.5f)
                verticalLineToRelative(-83.0f)
                arcTo(2.5f, 2.5f, 0.0f, false, true, 206.0f, 95.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(250.0f, 139.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, 2.5f)
                horizontalLineToRelative(-83.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, -2.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 2.5f, -2.5f)
                horizontalLineToRelative(83.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 2.5f, 2.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(237.11f, 170.11f)
                arcToRelative(2.5f, 2.5f, 112.32f, false, true, -3.53f, 0.0f)
                lineToRelative(-58.69f, -58.69f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 0.0f, -3.53f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 3.53f, 0.0f)
                lineToRelative(58.69f, 58.69f)
                arcToRelative(2.5f, 2.5f, 112.32f, false, true, 0.0f, 3.53f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(174.89f, 170.11f)
                arcToRelative(2.5f, 2.5f, 112.23f, false, true, 0.0f, -3.53f)
                lineToRelative(58.69f, -58.69f)
                arcToRelative(2.5f, 2.5f, 67.84f, false, true, 3.53f, 0.0f)
                arcToRelative(2.5f, 2.5f, 67.84f, false, true, 0.0f, 3.53f)
                lineToRelative(-58.69f, 58.69f)
                arcToRelative(2.5f, 2.5f, 112.23f, false, true, -3.53f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(91.0f, 66.5f)
                horizontalLineToRelative(50.0f)
                verticalLineToRelative(143.0f)
                horizontalLineTo(91.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(172.5f, 139.0f)
                arcToRelative(34.0f, 34.0f, 0.0f, true, true, 68.0f, 0.0f)
                arcToRelative(34.0f, 34.0f, 0.0f, true, true, -68.0f, 0.0f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFFE3F6DC)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(183.0f, 139.0f)
                arcToRelative(23.5f, 23.5f, 0.0f, true, true, 47.0f, 0.0f)
                arcToRelative(23.5f, 23.5f, 0.0f, true, true, -47.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(260.0f, 74.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, 5.0f, 0.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, -5.0f, 0.0f)
                moveToRelative(0.0f, 127.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, 5.0f, 0.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, -5.0f, 0.0f)
                moveTo(147.0f, 74.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, 5.0f, 0.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, -5.0f, 0.0f)
                moveToRelative(0.0f, 127.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, 5.0f, 0.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, true, true, -5.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(138.5f, 111.0f)
                horizontalLineToRelative(5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, 1.0f)
                verticalLineToRelative(5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, 1.0f)
                horizontalLineToRelative(-5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, -1.0f)
                verticalLineToRelative(-5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, -1.0f)
                close()
                moveToRelative(0.0f, 42.0f)
                horizontalLineToRelative(5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, 1.0f)
                verticalLineToRelative(5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, 1.0f)
                horizontalLineToRelative(-5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, -1.0f)
                verticalLineToRelative(-5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, -1.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3CB572)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(86.57f, 38.06f)
                lineToRelative(-3.27f, -6.32f)
                lineToRelative(-3.24f, 1.68f)
                lineToRelative(3.27f, 6.32f)
                lineToRelative(-7.18f, 0.82f)
                lineToRelative(0.48f, 3.52f)
                lineToRelative(7.1f, -0.98f)
                lineToRelative(-1.02f, 7.13f)
                lineToRelative(3.75f, 0.35f)
                lineToRelative(0.83f, -7.23f)
                lineTo(94.0f, 46.68f)
                lineToRelative(1.48f, -3.12f)
                lineToRelative(-6.33f, -3.25f)
                lineToRelative(5.0f, -5.29f)
                lineToRelative(-2.76f, -2.34f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(101.22f, 13.86f)
                lineToRelative(0.97f, -2.28f)
                lineToRelative(-1.17f, -0.5f)
                lineToRelative(-0.97f, 2.28f)
                lineToRelative(-1.82f, -1.74f)
                lineToRelative(-0.84f, 0.91f)
                lineToRelative(1.84f, 1.69f)
                lineToRelative(-2.14f, 1.31f)
                lineToRelative(0.74f, 1.08f)
                lineToRelative(2.13f, -1.38f)
                lineToRelative(0.6f, 2.54f)
                lineToRelative(1.17f, -0.3f)
                lineToRelative(-0.53f, -2.42f)
                lineToRelative(2.53f, 0.17f)
                lineToRelative(0.01f, -1.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(136.65f, 36.15f)
                lineToRelative(-2.33f, -4.49f)
                lineToRelative(-2.3f, 1.19f)
                lineToRelative(2.33f, 4.49f)
                lineToRelative(-5.11f, 0.59f)
                lineToRelative(0.34f, 2.51f)
                lineToRelative(5.05f, -0.7f)
                lineToRelative(-0.73f, 5.07f)
                lineToRelative(2.67f, 0.25f)
                lineToRelative(0.59f, -5.14f)
                lineToRelative(4.77f, 2.37f)
                lineToRelative(1.05f, -2.22f)
                lineToRelative(-4.5f, -2.31f)
                lineToRelative(3.56f, -3.76f)
                lineToRelative(-1.96f, -1.66f)
                close()
            }
        }.build()
        return _metallicSafeLight!!
    }

private var _metallicSafeLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.MetallicSafeLight, contentDescription = null, modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
