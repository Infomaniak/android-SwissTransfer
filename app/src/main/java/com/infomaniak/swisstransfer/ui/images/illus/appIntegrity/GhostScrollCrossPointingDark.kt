package com.infomaniak.swisstransfer.ui.images.illus.appIntegrity

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

val AppIllus.GhostScrollCrossPointingDark: ImageVector
    get() {
        if (_ghostScrollCrossPointingDark != null) {
            return _ghostScrollCrossPointingDark!!
        }
        _ghostScrollCrossPointingDark = Builder(
            name = "GhostScrollCrossPointingDark",
            defaultWidth = 153.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 153.0f,
            viewportHeight = 160.0f
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
                moveTo(49.83f, 40.44f)
                curveTo(52.33f, 17.71f, 71.54f, 0.5f, 94.41f, 0.5f)
                curveToRelative(22.91f, 0.0f, 42.13f, 17.26f, 44.59f, 40.03f)
                lineToRelative(12.73f, 117.66f)
                lineToRelative(-13.39f, -18.38f)
                curveToRelative(-7.36f, -10.1f, -22.43f, -10.1f, -29.8f, 0.0f)
                curveToRelative(-6.96f, 9.55f, -21.35f, 9.37f, -28.32f, -0.2f)
                curveToRelative(-7.32f, -10.05f, -22.37f, -10.54f, -30.08f, -0.76f)
                lineToRelative(-12.97f, 16.46f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(30.33f, 154.53f)
                lineToRelative(-12.83f, 4.75f)
                verticalLineTo(82.0f)
                arcToRelative(9.5f, 9.5f, 0.0f, false, true, 9.5f, -9.5f)
                horizontalLineToRelative(48.5f)
                verticalLineToRelative(26.18f)
                horizontalLineToRelative(-4.0f)
                verticalLineToRelative(60.62f)
                lineToRelative(-13.84f, -4.77f)
                lineToRelative(-0.17f, -0.06f)
                lineToRelative(-0.17f, 0.06f)
                lineTo(44.0f, 159.47f)
                lineToRelative(-13.33f, -4.94f)
                lineToRelative(-0.17f, -0.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(33.31f, 107.31f)
                arcToRelative(15.0f, 15.0f, 0.0f, true, true, 21.21f, 21.21f)
                arcToRelative(15.0f, 15.0f, 0.0f, true, true, -21.21f, -21.21f)
            }
            path(
                fill = SolidColor(Color(0xFFFF8500)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(53.11f, 112.26f)
                lineToRelative(-3.54f, -3.54f)
                lineToRelative(-5.66f, 5.66f)
                lineToRelative(-5.66f, -5.66f)
                lineToRelative(-3.54f, 3.54f)
                lineToRelative(5.66f, 5.66f)
                lineToRelative(-5.66f, 5.66f)
                lineToRelative(3.54f, 3.54f)
                lineToRelative(5.66f, -5.66f)
                lineToRelative(5.66f, 5.66f)
                lineToRelative(3.54f, -3.54f)
                lineToRelative(-5.66f, -5.66f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(71.0f, 77.0f)
                arcToRelative(5.0f, 5.0f, 0.0f, false, true, 10.0f, 0.0f)
                verticalLineToRelative(31.0f)
                horizontalLineTo(71.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(46.31f, 97.33f)
                arcTo(7.31f, 7.31f, 0.0f, false, true, 39.0f, 90.02f)
                verticalLineTo(73.0f)
                horizontalLineToRelative(14.62f)
                verticalLineToRelative(17.02f)
                arcToRelative(7.31f, 7.31f, 0.0f, false, true, -7.31f, 7.31f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(39.0f, 73.0f)
                verticalLineToRelative(17.02f)
                arcToRelative(7.31f, 7.31f, 0.0f, false, false, 14.62f, 0.0f)
                verticalLineTo(73.0f)
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
                moveTo(83.21f, 55.73f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, 4.5f, 4.49f)
                verticalLineToRelative(1.8f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, -4.5f, 4.5f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, -4.49f, -4.5f)
                verticalLineToRelative(-1.8f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, 4.49f, -4.49f)
                close()
                moveToRelative(22.47f, 0.0f)
                horizontalLineToRelative(0.01f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, 4.49f, 4.49f)
                verticalLineToRelative(1.8f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, -4.49f, 4.5f)
                horizontalLineToRelative(-0.01f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, -4.49f, -4.5f)
                verticalLineToRelative(-1.8f)
                arcToRelative(4.49f, 4.49f, 0.0f, false, true, 4.49f, -4.49f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFDCE4E5)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(67.41f, 116.03f)
                lineToRelative(49.71f, -15.73f)
                arcToRelative(1.0f, 1.0f, 93.12f, false, true, 1.26f, 0.65f)
                arcToRelative(1.0f, 1.0f, 93.12f, false, true, -0.65f, 1.26f)
                lineToRelative(-49.71f, 15.73f)
                arcToRelative(1.0f, 1.0f, 83.83f, false, true, -1.26f, -0.65f)
                arcToRelative(1.0f, 1.0f, 83.83f, false, true, 0.65f, -1.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B383B)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(114.33f, 103.14f)
                arcToRelative(7.31f, 7.31f, 0.0f, false, true, 1.56f, -10.22f)
                lineTo(134.81f, 79.0f)
                lineToRelative(8.66f, 11.77f)
                lineToRelative(-18.92f, 13.92f)
                arcToRelative(7.31f, 7.31f, 0.0f, false, true, -10.22f, -1.56f)
            }
            path(
                fill = null,
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(134.81f, 79.0f)
                lineToRelative(-18.92f, 13.92f)
                arcToRelative(7.31f, 7.31f, 0.0f, true, false, 8.66f, 11.77f)
                lineToRelative(18.92f, -13.92f)
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
                moveTo(7.79f, 67.36f)
                lineToRelative(-4.52f, -4.38f)
                lineToRelative(-3.13f, -3.47f)
                lineToRelative(1.54f, -1.39f)
                lineToRelative(3.13f, 3.48f)
                lineToRelative(3.88f, 4.95f)
                close()
                moveToRelative(2.57f, 3.78f)
                lineToRelative(-1.64f, -1.82f)
                lineToRelative(1.82f, -1.65f)
                lineToRelative(1.64f, 1.83f)
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
                moveTo(21.18f, 60.63f)
                lineToRelative(-0.75f, -6.59f)
                lineToRelative(-0.22f, -4.93f)
                lineToRelative(2.18f, -0.1f)
                lineToRelative(0.22f, 4.93f)
                lineToRelative(-0.15f, 6.64f)
                close()
                moveToRelative(-0.44f, 4.8f)
                lineToRelative(-0.12f, -2.59f)
                lineToRelative(2.59f, -0.12f)
                lineToRelative(0.12f, 2.59f)
                close()
            }
        }.build()
        return _ghostScrollCrossPointingDark!!
    }

private var _ghostScrollCrossPointingDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box {
        Image(
            imageVector = AppIllus.GhostScrollCrossPointingDark,
            contentDescription = null,
            modifier = Modifier.size(AppImages.previewSize)
        )
    }
}
