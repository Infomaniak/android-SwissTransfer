package com.infomaniak.swisstransfer.ui.images.illus.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(49.01f, 135.59f)
                lineToRelative(0.01f, 0.3f)
                lineToRelative(-0.26f, 0.15f)
                lineToRelative(-5.6f, 3.23f)
                arcToRelative(3.32f, 3.32f, 0.0f, false, false, 3.32f, 5.76f)
                lineToRelative(5.6f, -3.23f)
                lineToRelative(0.26f, -0.15f)
                lineToRelative(0.25f, 0.16f)
                arcToRelative(7.76f, 7.76f, 0.0f, true, false, 0.28f, -13.27f)
                lineToRelative(-0.25f, -0.43f)
                lineToRelative(0.25f, 0.43f)
                arcToRelative(7.76f, 7.76f, 0.0f, false, false, -3.88f, 7.05f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3C4F52)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
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
                fill = SolidColor(Color(0xFF152123)),
                stroke = SolidColor(Color(0xFFDCE4E5)),
                strokeLineWidth = 1.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(189.02f, 134.59f)
                lineToRelative(-0.01f, 0.3f)
                lineToRelative(0.26f, 0.15f)
                lineToRelative(5.6f, 3.23f)
                arcToRelative(3.32f, 3.32f, 0.0f, false, true, -3.32f, 5.76f)
                lineToRelative(-5.6f, -3.23f)
                lineToRelative(-0.26f, -0.15f)
                lineToRelative(-0.26f, 0.16f)
                arcToRelative(7.76f, 7.76f, 0.0f, false, true, -10.89f, -10.43f)
                arcToRelative(7.76f, 7.76f, 0.0f, false, true, 10.6f, -2.84f)
                lineToRelative(0.25f, -0.43f)
                lineToRelative(-0.25f, 0.43f)
                arcToRelative(7.76f, 7.76f, 0.0f, false, true, 3.88f, 7.05f)
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
