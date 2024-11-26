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

val AppIllus.TwoPadlocksIntertwinedStarsDark: ImageVector
    get() {
        if (_twoPadlocksIntertwinedStarsDark != null) {
            return _twoPadlocksIntertwinedStarsDark!!
        }
        _twoPadlocksIntertwinedStarsDark = Builder(
            name = "TwoPadlocksIntertwinedStarsDark",
            defaultWidth = 240.0.dp,
            defaultHeight = 192.0.dp,
            viewportWidth = 240.0f,
            viewportHeight = 192.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF67DD95)),
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
                stroke = SolidColor(Color(0xFF3C4F52)),
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
                fill = SolidColor(Color(0xFF67DD95)),
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
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 0.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(55.33f, 86.87f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, true, 10.96f, 2.16f)
                lineToRelative(36.56f, 54.5f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, true, -2.16f, 10.96f)
                lineToRelative(-48.43f, 32.48f)
                arcToRelative(7.89f, 7.89f, 0.0f, false, true, -10.96f, -2.16f)
                lineTo(4.75f, 130.31f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, true, 2.16f, -10.96f)
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
                moveTo(52.15f, 142.74f)
                arcToRelative(8.26f, 8.26f, 0.0f, true, false, -4.26f, -6.35f)
                lineToRelative(-5.37f, 3.6f)
                arcToRelative(3.82f, 3.82f, 0.0f, true, false, 4.26f, 6.35f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(52.15f, 142.74f)
                    lineToRelative(0.24f, -0.44f)
                    lineToRelative(-0.27f, -0.14f)
                    lineToRelative(-0.25f, 0.17f)
                    close()
                    moveTo(47.89f, 136.39f)
                    lineTo(48.17f, 136.81f)
                    lineTo(48.42f, 136.64f)
                    lineTo(48.38f, 136.34f)
                    close()
                    moveTo(42.52f, 139.99f)
                    lineTo(42.79f, 140.41f)
                    close()
                    moveTo(60.42f, 141.93f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, -8.04f, 0.37f)
                    lineToRelative(-0.48f, 0.88f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, 9.07f, -0.42f)
                    close()
                    moveTo(62.55f, 131.16f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, -2.12f, 10.77f)
                    lineToRelative(0.56f, 0.83f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, 2.4f, -12.16f)
                    close()
                    moveTo(51.78f, 129.04f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, 10.77f, 2.12f)
                    lineToRelative(0.83f, -0.56f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, -12.16f, -2.39f)
                    close()
                    moveTo(48.38f, 136.34f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, 3.39f, -7.3f)
                    lineToRelative(-0.56f, -0.83f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, -3.83f, 8.24f)
                    close()
                    moveTo(42.79f, 140.41f)
                    lineTo(48.17f, 136.81f)
                    lineTo(47.61f, 135.98f)
                    lineTo(42.24f, 139.58f)
                    close()
                    moveTo(41.89f, 145.02f)
                    arcToRelative(3.32f, 3.32f, 0.0f, false, true, 0.91f, -4.61f)
                    lineToRelative(-0.56f, -0.83f)
                    arcToRelative(4.32f, 4.32f, 0.0f, false, false, -1.18f, 6.0f)
                    close()
                    moveTo(46.5f, 145.93f)
                    arcToRelative(3.32f, 3.32f, 0.0f, false, true, -4.61f, -0.91f)
                    lineToRelative(-0.83f, 0.56f)
                    arcToRelative(4.32f, 4.32f, 0.0f, false, false, 6.0f, 1.18f)
                    close()
                    moveTo(51.87f, 142.33f)
                    lineTo(46.5f, 145.93f)
                    lineTo(47.05f, 146.76f)
                    lineTo(52.43f, 143.16f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3C4F52)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.5f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(232.83f, 117.07f)
                    arcToRelative(7.9f, 7.9f, 0.0f, false, true, 2.86f, 10.79f)
                    lineToRelative(-32.96f, 56.75f)
                    arcToRelative(7.9f, 7.9f, 0.0f, false, true, -10.79f, 2.86f)
                    lineToRelative(-50.43f, -29.29f)
                    arcToRelative(7.9f, 7.9f, 0.0f, false, true, -2.86f, -10.79f)
                    lineToRelative(32.96f, -56.75f)
                    arcToRelative(7.9f, 7.9f, 0.0f, false, true, 10.79f, -2.86f)
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
                    fill = SolidColor(Color(0xFFDCE4E5)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(189.63f, 134.73f)
                    lineToRelative(-0.5f, -0.02f)
                    lineToRelative(-0.01f, 0.3f)
                    lineToRelative(0.26f, 0.15f)
                    close()
                    moveTo(185.81f, 141.35f)
                    lineTo(186.06f, 140.92f)
                    lineTo(185.79f, 140.77f)
                    lineTo(185.54f, 140.93f)
                    close()
                    moveTo(195.23f, 137.97f)
                    lineTo(194.98f, 138.4f)
                    close()
                    moveTo(185.25f, 127.66f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, 3.88f, 7.05f)
                    lineToRelative(1.0f, 0.04f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, -4.38f, -7.96f)
                    close()
                    moveTo(174.65f, 130.5f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, 10.6f, -2.84f)
                    lineToRelative(0.5f, -0.87f)
                    arcTo(8.76f, 8.76f, 0.0f, false, false, 173.78f, 130.0f)
                    close()
                    moveTo(177.49f, 141.1f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, -2.84f, -10.6f)
                    lineToRelative(-0.87f, -0.5f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, 3.21f, 11.97f)
                    close()
                    moveTo(185.54f, 140.93f)
                    arcToRelative(7.76f, 7.76f, 0.0f, false, true, -8.05f, 0.17f)
                    lineToRelative(-0.5f, 0.87f)
                    arcToRelative(8.76f, 8.76f, 0.0f, false, false, 9.08f, -0.19f)
                    close()
                    moveTo(191.66f, 144.15f)
                    lineTo(186.06f, 140.92f)
                    lineTo(185.56f, 141.79f)
                    lineTo(191.16f, 145.02f)
                    close()
                    moveTo(196.2f, 142.94f)
                    arcToRelative(3.32f, 3.32f, 0.0f, false, true, -4.54f, 1.22f)
                    lineToRelative(-0.5f, 0.87f)
                    arcToRelative(4.32f, 4.32f, 0.0f, false, false, 5.91f, -1.58f)
                    close()
                    moveTo(194.98f, 138.4f)
                    arcToRelative(3.32f, 3.32f, 0.0f, false, true, 1.22f, 4.54f)
                    lineToRelative(0.87f, 0.5f)
                    arcToRelative(4.32f, 4.32f, 0.0f, false, false, -1.58f, -5.91f)
                    close()
                    moveTo(189.38f, 135.17f)
                    lineTo(194.98f, 138.4f)
                    lineTo(195.48f, 137.53f)
                    lineTo(189.88f, 134.3f)
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
                    fill = SolidColor(Color(0xFFDCE4E5)),
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
        return _twoPadlocksIntertwinedStarsDark!!
    }

private var _twoPadlocksIntertwinedStarsDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.TwoPadlocksIntertwinedStarsDark
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
