package com.infomaniak.swisstransfer.ui.images.illus.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.swisstransfer.ui.images.AppImages
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIllus

val AppIllus.TwoPadlocksIntertwinedStarsLight: ImageVector
    get() {
        if (_twoPadlocksIntertwinedStarsLight != null) {
            return _twoPadlocksIntertwinedStarsLight!!
        }
        _twoPadlocksIntertwinedStarsLight = Builder(
            name = "TwoPadlocksIntertwinedStarsLight",
            defaultWidth = 240.0.dp,
            defaultHeight = 192.0.dp,
            viewportWidth = 240.0f,
            viewportHeight = 192.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF3CB572)),
                strokeLineWidth = 9.78f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(124.45f, 89.52f)
                lineTo(124.45f, 89.52f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 116.99f, 127.39f)
                lineTo(90.87f, 144.91f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 53.0f, 137.45f)
                lineTo(53.0f, 137.45f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 60.46f, 99.58f)
                lineTo(86.58f, 82.06f)
                arcTo(27.3f, 27.3f, 56.26f, false, true, 124.45f, 89.52f)
                close()
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF014958)),
                strokeLineWidth = 9.776f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(183.29f, 139.5f)
                lineTo(183.29f, 139.5f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 145.94f, 149.26f)
                lineTo(118.8f, 133.36f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 109.04f, 96.02f)
                lineTo(109.04f, 96.02f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 146.39f, 86.26f)
                lineTo(173.53f, 102.15f)
                arcTo(27.3f, 27.3f, 120.47f, false, true, 183.29f, 139.5f)
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
                moveToRelative(117.85f, 121.01f)
                lineToRelative(4.43f, 8.51f)
                lineToRelative(-16.53f, 11.3f)
                lineToRelative(-5.41f, -8.06f)
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
                moveTo(55.47f, 87.07f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 10.61f, 2.09f)
                lineToRelative(36.56f, 54.5f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -2.09f, 10.61f)
                lineToRelative(-48.43f, 32.49f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, -10.61f, -2.09f)
                lineTo(4.95f, 130.17f)
                arcToRelative(7.65f, 7.65f, 0.0f, false, true, 2.09f, -10.61f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFE3F6DC)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(52.15f, 142.74f)
                arcToRelative(8.26f, 8.26f, 0.0f, true, false, -4.26f, -6.35f)
                lineToRelative(-5.37f, 3.6f)
                arcToRelative(3.82f, 3.82f, 0.0f, true, false, 4.26f, 6.35f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(52.15f, 142.74f)
                    lineToRelative(0.48f, -0.88f)
                    lineToRelative(-0.53f, -0.29f)
                    lineToRelative(-0.5f, 0.34f)
                    close()
                    moveTo(47.89f, 136.39f)
                    lineTo(48.44f, 137.22f)
                    lineTo(48.95f, 136.88f)
                    lineTo(48.88f, 136.28f)
                    close()
                    moveTo(60.15f, 141.51f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, -7.52f, 0.35f)
                    lineToRelative(-0.95f, 1.76f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, 9.59f, -0.44f)
                    close()
                    moveTo(62.13f, 131.44f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, -1.99f, 10.08f)
                    lineToRelative(1.11f, 1.66f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, 2.53f, -12.85f)
                    close()
                    moveTo(52.06f, 129.45f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, 10.08f, 1.99f)
                    lineToRelative(1.66f, -1.11f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, -12.85f, -2.53f)
                    close()
                    moveTo(48.88f, 136.28f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, 3.17f, -6.83f)
                    lineToRelative(-1.11f, -1.66f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, -4.05f, 8.71f)
                    close()
                    moveTo(43.07f, 140.82f)
                    lineTo(48.44f, 137.22f)
                    lineTo(47.33f, 135.56f)
                    lineTo(41.96f, 139.16f)
                    close()
                    moveTo(42.3f, 144.74f)
                    arcToRelative(2.82f, 2.82f, 0.0f, false, true, 0.77f, -3.92f)
                    lineToRelative(-1.11f, -1.66f)
                    arcToRelative(4.82f, 4.82f, 0.0f, false, false, -1.32f, 6.69f)
                    close()
                    moveTo(46.22f, 145.51f)
                    arcToRelative(2.82f, 2.82f, 0.0f, false, true, -3.92f, -0.77f)
                    lineToRelative(-1.66f, 1.11f)
                    arcToRelative(4.82f, 4.82f, 0.0f, false, false, 6.69f, 1.32f)
                    close()
                    moveTo(51.59f, 141.91f)
                    lineTo(46.22f, 145.51f)
                    lineTo(47.33f, 147.17f)
                    lineTo(52.7f, 143.57f)
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
                    moveTo(232.7f, 117.28f)
                    arcToRelative(7.65f, 7.65f, 0.0f, false, true, 2.77f, 10.45f)
                    lineToRelative(-32.96f, 56.75f)
                    arcToRelative(7.65f, 7.65f, 0.0f, false, true, -10.45f, 2.77f)
                    lineToRelative(-50.43f, -29.29f)
                    arcToRelative(7.65f, 7.65f, 0.0f, false, true, -2.77f, -10.45f)
                    lineToRelative(32.96f, -56.75f)
                    arcToRelative(7.65f, 7.65f, 0.0f, false, true, 10.45f, -2.77f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(189.63f, 134.73f)
                    arcToRelative(8.26f, 8.26f, 0.0f, false, false, -4.13f, -7.51f)
                    arcToRelative(8.26f, 8.26f, 0.0f, false, false, -11.29f, 3.02f)
                    arcToRelative(8.26f, 8.26f, 0.0f, false, false, 3.02f, 11.29f)
                    arcToRelative(8.26f, 8.26f, 0.0f, false, false, 8.56f, -0.18f)
                    lineToRelative(5.6f, 3.23f)
                    arcToRelative(3.82f, 3.82f, 0.0f, true, false, 3.82f, -6.62f)
                    close()
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(189.63f, 134.73f)
                    lineToRelative(-1.0f, -0.04f)
                    lineToRelative(-0.03f, 0.61f)
                    lineToRelative(0.52f, 0.3f)
                    close()
                    moveTo(185.81f, 141.35f)
                    lineTo(186.31f, 140.49f)
                    lineTo(185.78f, 140.19f)
                    lineTo(185.27f, 140.51f)
                    close()
                    moveTo(195.23f, 137.97f)
                    lineTo(194.73f, 138.83f)
                    close()
                    moveTo(185.0f, 128.09f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, 3.63f, 6.6f)
                    lineToRelative(2.0f, 0.08f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, -4.62f, -8.41f)
                    close()
                    moveTo(175.08f, 130.75f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, 9.92f, -2.66f)
                    lineToRelative(1.0f, -1.73f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, -12.65f, 3.39f)
                    close()
                    moveTo(177.74f, 140.67f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, -2.66f, -9.92f)
                    lineToRelative(-1.73f, -1.0f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, 3.39f, 12.65f)
                    close()
                    moveTo(185.27f, 140.51f)
                    arcToRelative(7.26f, 7.26f, 0.0f, false, true, -7.53f, 0.16f)
                    lineToRelative(-1.0f, 1.73f)
                    arcToRelative(9.26f, 9.26f, 0.0f, false, false, 9.6f, -0.2f)
                    close()
                    moveTo(191.91f, 143.72f)
                    lineTo(186.31f, 140.49f)
                    lineTo(185.31f, 142.22f)
                    lineTo(190.91f, 145.45f)
                    close()
                    moveTo(195.76f, 142.69f)
                    arcToRelative(2.82f, 2.82f, 0.0f, false, true, -3.86f, 1.03f)
                    lineToRelative(-1.0f, 1.73f)
                    arcToRelative(4.82f, 4.82f, 0.0f, false, false, 6.59f, -1.76f)
                    close()
                    moveTo(194.73f, 138.83f)
                    arcToRelative(2.82f, 2.82f, 0.0f, false, true, 1.03f, 3.86f)
                    lineToRelative(1.73f, 1.0f)
                    arcToRelative(4.82f, 4.82f, 0.0f, false, false, -1.77f, -6.59f)
                    close()
                    moveTo(189.13f, 135.6f)
                    lineTo(194.73f, 138.83f)
                    lineTo(195.73f, 137.1f)
                    lineTo(190.13f, 133.87f)
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
                    moveToRelative(109.05f, 14.08f)
                    lineToRelative(2.95f, -9.28f)
                    lineToRelative(-4.76f, -1.51f)
                    lineToRelative(-2.95f, 9.28f)
                    lineToRelative(-7.75f, -6.15f)
                    lineToRelative(-2.95f, 3.87f)
                    lineToRelative(7.82f, 5.92f)
                    lineToRelative(-7.89f, 5.91f)
                    lineToRelative(3.29f, 3.96f)
                    lineToRelative(7.8f, -6.19f)
                    lineToRelative(3.27f, 9.71f)
                    lineToRelative(4.45f, -1.59f)
                    lineToRelative(-2.98f, -9.27f)
                    lineToRelative(9.96f, -0.27f)
                    lineToRelative(-0.41f, -4.94f)
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
                    moveToRelative(80.06f, 52.37f)
                    lineToRelative(1.02f, -3.23f)
                    lineToRelative(-1.65f, -0.52f)
                    lineToRelative(-1.03f, 3.23f)
                    lineToRelative(-2.7f, -2.14f)
                    lineToRelative(-1.02f, 1.35f)
                    lineToRelative(2.72f, 2.06f)
                    lineToRelative(-2.74f, 2.06f)
                    lineToRelative(1.15f, 1.38f)
                    lineToRelative(2.72f, -2.16f)
                    lineToRelative(1.14f, 3.38f)
                    lineToRelative(1.55f, -0.55f)
                    lineToRelative(-1.04f, -3.23f)
                    lineToRelative(3.46f, -0.09f)
                    lineToRelative(-0.14f, -1.72f)
                    close()
                    moveTo(148.2f, 33.79f)
                    lineToRelative(1.53f, -4.82f)
                    lineToRelative(-2.47f, -0.79f)
                    lineToRelative(-1.53f, 4.82f)
                    lineToRelative(-4.03f, -3.2f)
                    lineToRelative(-1.53f, 2.01f)
                    lineToRelative(4.06f, 3.07f)
                    lineToRelative(-4.1f, 3.07f)
                    lineToRelative(1.71f, 2.06f)
                    lineToRelative(4.05f, -3.22f)
                    lineToRelative(1.7f, 5.05f)
                    lineToRelative(2.31f, -0.83f)
                    lineToRelative(-1.55f, -4.82f)
                    lineToRelative(5.18f, -0.14f)
                    lineToRelative(-0.21f, -2.57f)
                    close()
                }
            }
        }.build()
        return _twoPadlocksIntertwinedStarsLight!!
    }

private var _twoPadlocksIntertwinedStarsLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.TwoPadlocksIntertwinedStarsLight
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
