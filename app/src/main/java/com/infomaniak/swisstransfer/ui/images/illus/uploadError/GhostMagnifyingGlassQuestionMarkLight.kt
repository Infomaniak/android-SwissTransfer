package com.infomaniak.swisstransfer.ui.images.illus.uploadError

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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.GhostMagnifyingGlassQuestionMarkLight: ImageVector
    get() {
        if (_ghostMagnifyingGlassQuestionMarkLight != null) {
            return _ghostMagnifyingGlassQuestionMarkLight!!
        }
        _ghostMagnifyingGlassQuestionMarkLight = Builder(
            name = "GhostMagnifyingGlassQuestionMarkLight",
            defaultWidth = 265.0.dp,
            defaultHeight = 157.0.dp,
            viewportWidth = 265.0f,
            viewportHeight = 157.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.0f, 136.47f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, 264.08f, 0.0f)
                arcToRelative(132.04f, 20.53f, 0.0f, true, false, -264.08f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(90.63f, 61.02f)
                curveToRelative(2.29f, -21.15f, 20.14f, -37.17f, 41.41f, -37.17f)
                curveToRelative(21.27f, 0.0f, 39.13f, 16.03f, 41.41f, 37.17f)
                lineToRelative(8.9f, 82.35f)
                lineToRelative(-9.73f, -3.6f)
                arcToRelative(80.5f, 80.5f, 0.0f, false, false, -27.94f, -5.01f)
                horizontalLineTo(82.66f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(90.63f, 61.02f)
                    curveToRelative(2.29f, -21.15f, 20.14f, -37.17f, 41.41f, -37.17f)
                    reflectiveCurveToRelative(39.13f, 16.03f, 41.41f, 37.17f)
                    lineToRelative(11.83f, 109.46f)
                    lineToRelative(-12.38f, -17.0f)
                    curveToRelative(-6.86f, -9.42f, -20.92f, -9.42f, -27.78f, 0.0f)
                    curveToRelative(-6.46f, 8.88f, -19.7f, 8.88f, -26.16f, 0.0f)
                    curveToRelative(-6.86f, -9.42f, -20.92f, -9.42f, -27.78f, 0.0f)
                    lineToRelative(-12.38f, 17.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF8500)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(93.29f, 14.96f)
                    curveToRelative(0.72f, 1.28f, 0.32f, 2.69f, -0.27f, 3.79f)
                    curveToRelative(-0.53f, 1.01f, -0.69f, 1.41f, -0.28f, 2.15f)
                    lineToRelative(0.23f, 0.42f)
                    lineToRelative(-1.25f, 0.7f)
                    lineToRelative(-0.26f, -0.46f)
                    curveToRelative(-0.63f, -1.13f, -0.36f, -1.94f, 0.36f, -3.28f)
                    curveToRelative(0.45f, -0.84f, 0.7f, -1.68f, 0.21f, -2.56f)
                    curveToRelative(-0.47f, -0.85f, -1.54f, -0.98f, -2.58f, -0.4f)
                    curveToRelative(-1.51f, 0.84f, -1.41f, 2.17f, -1.0f, 3.02f)
                    lineToRelative(-1.23f, 0.69f)
                    curveToRelative(-0.96f, -1.76f, -0.23f, -3.67f, 1.68f, -4.74f)
                    curveToRelative(1.52f, -0.85f, 3.46f, -0.96f, 4.37f, 0.67f)
                    moveToRelative(-0.09f, 9.99f)
                    lineToRelative(-0.87f, -1.55f)
                    lineToRelative(1.55f, -0.87f)
                    lineToRelative(0.87f, 1.55f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFAA4C)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(162.74f, 1.85f)
                    curveToRelative(-0.0f, 0.99f, -0.7f, 1.69f, -1.42f, 2.14f)
                    curveToRelative(-0.65f, 0.41f, -0.88f, 0.6f, -0.88f, 1.18f)
                    lineToRelative(-0.0f, 0.32f)
                    lineToRelative(-0.97f, -0.0f)
                    lineToRelative(0.0f, -0.35f)
                    curveToRelative(0.0f, -0.88f, 0.43f, -1.26f, 1.3f, -1.81f)
                    curveToRelative(0.54f, -0.34f, 0.97f, -0.76f, 0.98f, -1.44f)
                    curveToRelative(0.0f, -0.66f, -0.58f, -1.09f, -1.38f, -1.09f)
                    curveToRelative(-1.17f, -0.0f, -1.55f, 0.81f, -1.59f, 1.44f)
                    lineToRelative(-0.95f, -0.0f)
                    curveToRelative(0.01f, -1.36f, 1.08f, -2.24f, 2.57f, -2.23f)
                    curveToRelative(1.18f, 0.0f, 2.36f, 0.58f, 2.35f, 1.85f)
                    moveToRelative(-3.37f, 5.85f)
                    lineToRelative(0.0f, -1.2f)
                    lineToRelative(1.2f, 0.0f)
                    lineToRelative(-0.0f, 1.2f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF8500)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(179.53f, 15.05f)
                    curveToRelative(-0.63f, 1.76f, -2.32f, 2.56f, -3.87f, 2.92f)
                    curveToRelative(-1.41f, 0.33f, -1.94f, 0.52f, -2.31f, 1.54f)
                    lineToRelative(-0.21f, 0.57f)
                    lineToRelative(-1.72f, -0.62f)
                    lineToRelative(0.23f, -0.63f)
                    curveToRelative(0.56f, -1.56f, 1.56f, -1.97f, 3.45f, -2.4f)
                    curveToRelative(1.18f, -0.27f, 2.21f, -0.74f, 2.64f, -1.94f)
                    curveToRelative(0.42f, -1.17f, -0.35f, -2.3f, -1.77f, -2.81f)
                    curveToRelative(-2.08f, -0.75f, -3.26f, 0.46f, -3.74f, 1.57f)
                    lineToRelative(-1.69f, -0.61f)
                    curveToRelative(0.88f, -2.4f, 3.34f, -3.3f, 5.97f, -2.36f)
                    curveToRelative(2.09f, 0.75f, 3.82f, 2.52f, 3.02f, 4.76f)
                    moveToRelative(-9.68f, 8.28f)
                    lineToRelative(0.76f, -2.13f)
                    lineToRelative(2.13f, 0.76f)
                    lineToRelative(-0.76f, 2.13f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(109.0f, 70.0f)
                    lineTo(109.0f, 70.0f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 113.0f, 74.0f)
                    lineTo(113.0f, 76.0f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 80.0f)
                    lineTo(109.0f, 80.0f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 105.0f, 76.0f)
                    lineTo(105.0f, 74.0f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 109.0f, 70.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(146.35f, 88.53f)
                    arcToRelative(3.69f, 3.69f, 0.0f, true, true, 5.46f, -4.95f)
                    lineToRelative(16.11f, 18.99f)
                    arcToRelative(2.86f, 2.86f, 0.0f, false, true, -4.23f, 3.84f)
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
                    moveTo(138.55f, 74.45f)
                    moveToRelative(-12.98f, 11.77f)
                    arcToRelative(17.52f, 17.52f, 92.79f, true, true, 25.95f, -23.54f)
                    arcToRelative(17.52f, 17.52f, 92.79f, true, true, -25.95f, 23.54f)
                }
                path(
                    fill = SolidColor(Color(0xFFFF9802)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(138.55f, 74.45f)
                    moveToRelative(-10.38f, 9.42f)
                    arcToRelative(14.02f, 14.01f, 92.79f, true, true, 20.76f, -18.83f)
                    arcToRelative(14.02f, 14.01f, 92.79f, true, true, -20.76f, 18.83f)
                }
                path(
                    fill = SolidColor(Color(0xFFFFC166)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(132.81f, 69.7f)
                    lineTo(132.81f, 69.7f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 136.81f, 73.7f)
                    lineTo(136.81f, 75.7f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 79.7f)
                    lineTo(132.81f, 79.7f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 128.81f, 75.7f)
                    lineTo(128.81f, 73.7f)
                    arcTo(4.0f, 4.0f, 0.0f, false, true, 132.81f, 69.7f)
                    close()
                }
                path(
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(84.5f, 120.5f)
                    lineToRelative(14.01f, -7.0f)
                    arcToRelative(7.71f, 7.71f, 0.0f, false, false, -6.04f, -14.17f)
                    lineToRelative(-6.03f, 2.15f)
                }
                path(
                    fill = SolidColor(Color(0xFFFAFAFA)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(93.01f, 127.86f)
                    lineTo(92.05f, 85.5f)
                    horizontalLineToRelative(25.98f)
                    lineToRelative(-0.96f, 42.35f)
                    lineToRelative(-4.93f, -4.72f)
                    lineToRelative(-0.32f, -0.31f)
                    lineToRelative(-0.34f, 0.28f)
                    lineToRelative(-6.43f, 5.24f)
                    lineToRelative(-6.43f, -5.24f)
                    lineToRelative(-0.34f, -0.28f)
                    lineToRelative(-0.32f, 0.31f)
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
                    moveTo(157.98f, 98.35f)
                    arcToRelative(7.31f, 7.31f, 0.0f, false, true, 10.33f, -0.37f)
                    lineToRelative(9.99f, 9.3f)
                    lineToRelative(-9.96f, 10.7f)
                    lineToRelative(-9.99f, -9.3f)
                    arcToRelative(7.31f, 7.31f, 0.0f, false, true, -0.37f, -10.33f)
                }
                path(
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(178.3f, 107.28f)
                    lineToRelative(-9.99f, -9.3f)
                    arcToRelative(7.31f, 7.31f, 0.0f, false, false, -9.96f, 10.7f)
                    lineToRelative(9.99f, 9.3f)
                }
            }
        }.build()
        return _ghostMagnifyingGlassQuestionMarkLight!!
    }

private var _ghostMagnifyingGlassQuestionMarkLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.GhostMagnifyingGlassQuestionMarkLight,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
