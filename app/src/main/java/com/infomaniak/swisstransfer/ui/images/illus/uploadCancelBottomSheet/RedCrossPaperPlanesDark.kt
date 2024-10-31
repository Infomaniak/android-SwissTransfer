package com.infomaniak.swisstransfer.ui.images.illus.uploadCancelBottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

public val AppIllus.RedCrossPaperPlanesDark: ImageVector
    get() {
        if (_redCrossPaperPlanesDark != null) {
            return _redCrossPaperPlanesDark!!
        }
        _redCrossPaperPlanesDark = Builder(
            name = "RedCrossPaperPlanesDark",
            defaultWidth = 168.0.dp,
            defaultHeight = 122.0.dp,
            viewportWidth = 168.0f,
            viewportHeight = 122.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(96.58f, 1.0f)
                horizontalLineToRelative(-24.86f)
                lineTo(46.32f, 26.4f)
                verticalLineToRelative(24.86f)
                lineToRelative(25.4f, 25.4f)
                horizontalLineToRelative(24.86f)
                lineToRelative(25.4f, -25.4f)
                verticalLineToRelative(-24.86f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFC8878)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(95.16f, 5.32f)
                horizontalLineToRelative(-22.02f)
                lineTo(50.64f, 27.82f)
                verticalLineToRelative(22.02f)
                lineTo(73.14f, 72.34f)
                horizontalLineToRelative(22.02f)
                lineToRelative(22.5f, -22.5f)
                verticalLineToRelative(-22.02f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(103.13f, 21.7f)
                arcToRelative(1.2f, 1.2f, 0.0f, true, false, -1.7f, -1.7f)
                lineTo(84.22f, 37.2f)
                lineTo(67.02f, 20.0f)
                arcToRelative(1.2f, 1.2f, 0.0f, true, false, -1.7f, 1.7f)
                lineTo(82.52f, 38.9f)
                lineTo(65.32f, 56.11f)
                arcToRelative(1.2f, 1.2f, 0.0f, true, false, 1.7f, 1.7f)
                lineToRelative(17.2f, -17.2f)
                lineToRelative(17.2f, 17.2f)
                arcToRelative(1.2f, 1.2f, 0.0f, true, false, 1.7f, -1.7f)
                lineTo(85.93f, 38.9f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(84.15f, 113.16f)
                lineTo(65.23f, 94.52f)
                lineTo(84.15f, 63.44f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(84.15f, 113.16f)
                lineToRelative(18.92f, -18.65f)
                lineToRelative(-18.92f, -31.08f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(84.15f, 62.36f)
                lineTo(33.35f, 94.11f)
                lineTo(77.8f, 94.11f)
                close()
                moveTo(84.15f, 62.36f)
                lineTo(90.5f, 94.11f)
                horizontalLineToRelative(44.45f)
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
                moveToRelative(33.35f, 94.11f)
                lineToRelative(-0.21f, -0.34f)
                lineToRelative(-1.18f, 0.74f)
                horizontalLineToRelative(1.39f)
                close()
                moveTo(84.15f, 62.36f)
                lineToRelative(0.21f, -0.34f)
                lineToRelative(-0.21f, -0.13f)
                lineToRelative(-0.21f, 0.13f)
                close()
                moveTo(77.8f, 94.11f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(0.33f)
                lineToRelative(0.06f, -0.32f)
                close()
                moveTo(90.5f, 94.11f)
                lineTo(90.11f, 94.19f)
                lineTo(90.17f, 94.51f)
                horizontalLineToRelative(0.33f)
                close()
                moveTo(134.95f, 94.11f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(1.39f)
                lineToRelative(-1.18f, -0.74f)
                close()
                moveTo(33.56f, 94.45f)
                lineTo(84.36f, 62.7f)
                lineToRelative(-0.42f, -0.68f)
                lineToRelative(-50.8f, 31.75f)
                close()
                moveTo(77.8f, 93.71f)
                lineTo(33.35f, 93.71f)
                verticalLineToRelative(0.8f)
                lineTo(77.8f, 94.51f)
                close()
                moveTo(83.76f, 62.28f)
                lineTo(77.41f, 94.03f)
                lineTo(78.19f, 94.19f)
                lineTo(84.54f, 62.44f)
                close()
                moveTo(83.76f, 62.44f)
                lineTo(90.11f, 94.19f)
                lineTo(90.89f, 94.03f)
                lineTo(84.54f, 62.28f)
                close()
                moveTo(90.5f, 94.51f)
                horizontalLineToRelative(44.45f)
                verticalLineToRelative(-0.8f)
                lineTo(90.5f, 93.71f)
                close()
                moveTo(135.16f, 93.77f)
                lineTo(84.36f, 62.02f)
                lineToRelative(-0.42f, 0.68f)
                lineToRelative(50.8f, 31.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Round,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(50.91f, 103.98f)
                lineTo(33.89f, 121.0f)
                moveTo(60.64f, 102.35f)
                lineToRelative(-4.26f, 4.26f)
                moveToRelative(-4.26f, 4.26f)
                lineToRelative(2.13f, -2.13f)
                moveTo(117.39f, 103.98f)
                lineTo(134.41f, 121.0f)
                moveTo(107.39f, 102.35f)
                lineToRelative(1.72f, 1.72f)
                moveToRelative(6.79f, 6.79f)
                lineToRelative(-5.0f, -5.0f)
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(144.14f, 79.36f)
                lineToRelative(-8.25f, -8.13f)
                lineToRelative(8.25f, -13.55f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(144.14f, 79.36f)
                lineToRelative(8.25f, -8.13f)
                lineToRelative(-8.25f, -13.55f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(144.14f, 57.21f)
                lineToRelative(-22.16f, 13.85f)
                horizontalLineToRelative(19.39f)
                close()
                moveTo(144.14f, 57.21f)
                lineTo(146.91f, 71.06f)
                horizontalLineToRelative(19.39f)
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
                moveToRelative(121.98f, 71.06f)
                lineToRelative(-0.21f, -0.34f)
                lineToRelative(-1.18f, 0.74f)
                horizontalLineToRelative(1.39f)
                close()
                moveTo(144.14f, 57.21f)
                lineTo(144.35f, 56.87f)
                lineTo(144.14f, 56.73f)
                lineTo(143.93f, 56.87f)
                close()
                moveTo(141.37f, 71.06f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(0.33f)
                lineToRelative(0.06f, -0.32f)
                close()
                moveTo(146.91f, 71.06f)
                lineTo(146.52f, 71.13f)
                lineTo(146.58f, 71.46f)
                horizontalLineToRelative(0.33f)
                close()
                moveTo(166.3f, 71.06f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(1.39f)
                lineToRelative(-1.18f, -0.74f)
                close()
                moveTo(122.19f, 71.39f)
                lineTo(144.35f, 57.54f)
                lineTo(143.93f, 56.87f)
                lineTo(121.77f, 70.72f)
                close()
                moveTo(141.37f, 70.65f)
                horizontalLineToRelative(-19.39f)
                verticalLineToRelative(0.8f)
                horizontalLineToRelative(19.39f)
                close()
                moveTo(143.75f, 57.13f)
                lineTo(140.98f, 70.98f)
                lineTo(141.76f, 71.13f)
                lineTo(144.53f, 57.28f)
                close()
                moveTo(143.75f, 57.28f)
                lineTo(146.52f, 71.13f)
                lineTo(147.3f, 70.98f)
                lineTo(144.53f, 57.13f)
                close()
                moveTo(146.91f, 71.46f)
                horizontalLineToRelative(19.39f)
                verticalLineToRelative(-0.8f)
                lineTo(146.91f, 70.65f)
                close()
                moveTo(166.51f, 70.72f)
                lineTo(144.35f, 56.87f)
                lineTo(143.93f, 57.55f)
                lineTo(166.09f, 71.4f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(18.48f, 76.93f)
                lineToRelative(-6.14f, -6.05f)
                lineTo(18.48f, 60.8f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF014958)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.8f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(18.48f, 76.93f)
                lineToRelative(6.14f, -6.05f)
                lineTo(18.48f, 60.8f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF152123)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(18.48f, 60.45f)
                lineTo(2.0f, 70.75f)
                horizontalLineToRelative(14.42f)
                close()
                moveTo(18.48f, 60.45f)
                lineTo(20.54f, 70.75f)
                horizontalLineToRelative(14.42f)
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
                moveToRelative(2.0f, 70.75f)
                lineToRelative(-0.21f, -0.34f)
                lineToRelative(-1.18f, 0.74f)
                lineTo(2.0f, 71.15f)
                close()
                moveTo(18.48f, 60.45f)
                lineToRelative(0.21f, -0.34f)
                lineToRelative(-0.21f, -0.13f)
                lineToRelative(-0.21f, 0.13f)
                close()
                moveTo(16.42f, 70.75f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(0.33f)
                lineToRelative(0.06f, -0.32f)
                close()
                moveTo(20.54f, 70.75f)
                lineTo(20.15f, 70.83f)
                lineTo(20.22f, 71.15f)
                horizontalLineToRelative(0.33f)
                close()
                moveTo(34.97f, 70.75f)
                verticalLineToRelative(0.4f)
                horizontalLineToRelative(1.39f)
                lineToRelative(-1.18f, -0.74f)
                close()
                moveTo(2.21f, 71.09f)
                lineTo(18.7f, 60.79f)
                lineTo(18.27f, 60.11f)
                lineTo(1.79f, 70.41f)
                close()
                moveTo(16.42f, 70.35f)
                lineTo(2.0f, 70.35f)
                verticalLineToRelative(0.8f)
                horizontalLineToRelative(14.42f)
                close()
                moveTo(18.09f, 60.37f)
                lineTo(16.03f, 70.67f)
                lineToRelative(0.79f, 0.16f)
                lineToRelative(2.06f, -10.3f)
                close()
                moveTo(18.09f, 60.53f)
                lineTo(20.15f, 70.83f)
                lineTo(20.94f, 70.67f)
                lineTo(18.88f, 60.37f)
                close()
                moveTo(20.54f, 71.15f)
                horizontalLineToRelative(14.42f)
                verticalLineToRelative(-0.8f)
                lineTo(20.54f, 70.35f)
                close()
                moveTo(35.18f, 70.41f)
                lineTo(18.7f, 60.11f)
                lineToRelative(-0.42f, 0.68f)
                lineToRelative(16.48f, 10.3f)
                close()
            }
        }.build()
        return _redCrossPaperPlanesDark!!
    }

private var _redCrossPaperPlanesDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIllus.RedCrossPaperPlanesDark, contentDescription = null)
    }
}
