package com.infomaniak.swisstransfer.ui.images.illus.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.StorageBoxPileDark: ImageVector
    get() {
        if (_storageBoxPileDark != null) {
            return _storageBoxPileDark!!
        }
        _storageBoxPileDark = Builder(
            name = "StorageBoxPileDark",
            defaultWidth = 287.0.dp,
            defaultHeight = 247.0.dp,
            viewportWidth = 287.0f,
            viewportHeight = 247.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(106.5f, 145.5f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(106.0f)
                    verticalLineToRelative(145.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2b383b)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(70.5f, 145.5f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(106.0f)
                    verticalLineToRelative(145.0f)
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
                    moveTo(155.2f, 46.32f)
                    horizontalLineTo(81.05f)
                    verticalLineToRelative(22.18f)
                    horizontalLineTo(155.2f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(104.57f, 58.91f)
                    curveToRelative(0.0f, -1.56f, -1.09f, -2.24f, -2.12f, -2.24f)
                    curveToRelative(-0.98f, 0.0f, -1.61f, 0.57f, -1.82f, 1.04f)
                    lineToRelative(-1.18f, -0.06f)
                    lineToRelative(0.61f, -5.07f)
                    horizontalLineToRelative(5.41f)
                    verticalLineToRelative(1.11f)
                    horizontalLineToRelative(-4.45f)
                    lineToRelative(-0.32f, 2.68f)
                    curveToRelative(0.49f, -0.48f, 1.25f, -0.71f, 2.03f, -0.71f)
                    curveToRelative(1.65f, 0.0f, 3.09f, 1.17f, 3.09f, 3.25f)
                    curveToRelative(0.0f, 2.16f, -1.6f, 3.28f, -3.35f, 3.28f)
                    curveToRelative(-2.18f, 0.0f, -3.17f, -1.38f, -3.35f, -2.9f)
                    horizontalLineToRelative(1.22f)
                    curveToRelative(0.18f, 1.18f, 0.82f, 1.91f, 2.13f, 1.91f)
                    curveToRelative(1.13f, 0.0f, 2.11f, -0.82f, 2.11f, -2.28f)
                    moveToRelative(6.16f, 3.26f)
                    curveToRelative(-2.51f, 0.0f, -3.67f, -1.9f, -3.67f, -4.89f)
                    curveToRelative(0.0f, -2.98f, 1.16f, -4.88f, 3.67f, -4.88f)
                    reflectiveCurveToRelative(3.67f, 1.9f, 3.67f, 4.88f)
                    curveToRelative(0.0f, 2.99f, -1.16f, 4.89f, -3.67f, 4.89f)
                    moveToRelative(0.0f, -8.77f)
                    curveToRelative(-1.48f, 0.0f, -2.4f, 1.27f, -2.4f, 3.89f)
                    curveToRelative(0.0f, 2.63f, 0.92f, 3.9f, 2.4f, 3.9f)
                    reflectiveCurveToRelative(2.4f, -1.27f, 2.4f, -3.9f)
                    curveToRelative(0.0f, -2.61f, -0.92f, -3.89f, -2.4f, -3.89f)
                    moveToRelative(16.33f, 1.92f)
                    horizontalLineToRelative(-1.29f)
                    curveToRelative(-0.31f, -1.05f, -1.17f, -1.79f, -2.68f, -1.79f)
                    curveToRelative(-1.66f, 0.0f, -2.96f, 1.3f, -2.96f, 3.74f)
                    curveToRelative(0.0f, 2.38f, 1.22f, 3.78f, 2.96f, 3.78f)
                    curveToRelative(1.26f, 0.0f, 2.78f, -0.52f, 2.78f, -2.47f)
                    verticalLineToRelative(-0.25f)
                    horizontalLineToRelative(-2.5f)
                    verticalLineToRelative(-1.14f)
                    horizontalLineToRelative(3.74f)
                    verticalLineToRelative(4.81f)
                    horizontalLineToRelative(-1.04f)
                    lineToRelative(-0.06f, -1.18f)
                    curveToRelative(-0.61f, 0.96f, -1.72f, 1.35f, -2.98f, 1.35f)
                    curveToRelative(-2.72f, 0.0f, -4.26f, -2.03f, -4.26f, -4.9f)
                    curveToRelative(0.0f, -2.92f, 1.73f, -4.86f, 4.38f, -4.86f)
                    curveToRelative(1.95f, 0.0f, 3.51f, 1.09f, 3.9f, 2.91f)
                    moveToRelative(2.18f, -2.74f)
                    horizontalLineToRelative(3.39f)
                    curveToRelative(1.75f, 0.0f, 3.32f, 0.46f, 3.32f, 2.33f)
                    curveToRelative(0.0f, 0.9f, -0.39f, 1.7f, -1.04f, 2.08f)
                    curveToRelative(0.9f, 0.38f, 1.43f, 1.08f, 1.43f, 2.24f)
                    curveToRelative(0.0f, 2.0f, -1.5f, 2.78f, -3.38f, 2.78f)
                    horizontalLineToRelative(-3.72f)
                    close()
                    moveTo(130.56f, 56.61f)
                    horizontalLineToRelative(2.28f)
                    curveToRelative(1.08f, 0.0f, 1.77f, -0.42f, 1.77f, -1.52f)
                    curveToRelative(0.0f, -0.96f, -0.71f, -1.39f, -1.77f, -1.39f)
                    horizontalLineToRelative(-2.28f)
                    close()
                    moveTo(130.56f, 60.89f)
                    horizontalLineToRelative(2.4f)
                    curveToRelative(1.34f, 0.0f, 2.0f, -0.58f, 2.0f, -1.65f)
                    curveToRelative(0.0f, -1.11f, -0.66f, -1.57f, -2.08f, -1.57f)
                    horizontalLineToRelative(-2.33f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(75.9f, 57.24f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineTo(91.22f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    close()
                    moveTo(144.39f, 52.16f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(1.22f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(1.71f)
                    horizontalLineToRelative(15.31f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.57f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.57f)
                    verticalLineToRelative(-1.71f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(54.64f, 245.11f)
                    verticalLineToRelative(-99.89f)
                    horizontalLineTo(159.41f)
                    verticalLineToRelative(99.89f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3c4f52)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(18.75f, 245.11f)
                    verticalLineToRelative(-99.89f)
                    horizontalLineToRelative(104.77f)
                    verticalLineToRelative(99.89f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF152123)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(159.12f, 183.21f)
                    horizontalLineToRelative(-35.06f)
                    verticalLineTo(200.4f)
                    horizontalLineToRelative(35.06f)
                    close()
                    moveTo(123.38f, 183.21f)
                    horizontalLineTo(18.89f)
                    verticalLineTo(200.4f)
                    horizontalLineToRelative(104.49f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(46.28f, 157.45f)
                    horizontalLineTo(27.25f)
                    verticalLineToRelative(9.17f)
                    horizontalLineToRelative(19.03f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF152123)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(46.28f, 157.45f)
                    horizontalLineTo(30.03f)
                    verticalLineToRelative(9.17f)
                    horizontalLineToRelative(16.25f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3c4f52)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(146.82f, 124.5f)
                    horizontalLineToRelative(69.07f)
                    verticalLineToRelative(120.9f)
                    horizontalLineToRelative(-69.07f)
                    verticalLineTo(124.5f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(210.6f, 140.31f)
                    horizontalLineTo(190.12f)
                    verticalLineToRelative(9.37f)
                    horizontalLineToRelative(20.48f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF152123)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(210.6f, 140.31f)
                    horizontalLineToRelative(-15.25f)
                    verticalLineToRelative(9.37f)
                    horizontalLineToRelative(15.25f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(216.17f, 124.5f)
                    horizontalLineToRelative(34.77f)
                    verticalLineToRelative(120.9f)
                    horizontalLineToRelative(-34.77f)
                    verticalLineTo(124.5f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2b383b)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(7.8f, 192.5f)
                    horizontalLineTo(117.75f)
                    verticalLineToRelative(53.01f)
                    horizontalLineTo(7.8f)
                    verticalLineTo(192.5f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3c4f52)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(0.69f, 213.4f)
                    lineTo(7.66f, 192.5f)
                    horizontalLineToRelative(109.9f)
                    lineToRelative(-6.97f, 20.9f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67dd95)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(118.02f, 192.5f)
                    horizontalLineToRelative(48.64f)
                    verticalLineToRelative(53.01f)
                    horizontalLineTo(118.02f)
                    verticalLineTo(192.5f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3c4f52)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(125.18f, 213.4f)
                    lineToRelative(-6.97f, -20.9f)
                    horizontalLineToRelative(48.58f)
                    lineToRelative(6.97f, 20.9f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2b383b)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(216.21f, 245.23f)
                    verticalLineTo(221.5f)
                    horizontalLineToRelative(69.38f)
                    verticalLineToRelative(23.73f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3c4f52)),
                    stroke = SolidColor(Color(0xFFdce4e5)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(195.82f, 245.23f)
                    verticalLineTo(221.5f)
                    horizontalLineToRelative(69.38f)
                    verticalLineToRelative(23.73f)
                    close()
                }
            }
        }.build()
        return _storageBoxPileDark!!
    }

private var _storageBoxPileDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.StorageBoxPileDark
    Box {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(AppImages.previewSize)
                .aspectRatio(imageVector.viewportWidth / imageVector.viewportHeight)
        )
    }
}
