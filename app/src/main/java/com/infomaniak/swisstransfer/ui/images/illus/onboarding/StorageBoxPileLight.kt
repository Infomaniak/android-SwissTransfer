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

val AppIllus.StorageBoxPileLight: ImageVector
    get() {
        if (_storageBoxPileLight != null) {
            return _storageBoxPileLight!!
        }
        _storageBoxPileLight = Builder(
            name = "StorageBoxPileLight",
            defaultWidth = 290.0.dp,
            defaultHeight = 258.0.dp,
            viewportWidth = 290.0f,
            viewportHeight = 258.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFA98116)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(98.03f, 94.1f)
                curveToRelative(0.78f, 0.68f, 3.34f, 1.72f, 4.57f, 1.98f)
                curveToRelative(-1.3f, -0.57f, -3.01f, -1.2f, -3.95f, -1.99f)
                curveToRelative(-4.49f, -3.77f, -1.84f, -10.05f, 3.96f, -9.13f)
                curveToRelative(1.06f, 0.17f, 2.86f, 1.02f, 4.31f, 1.73f)
                curveToRelative(-0.97f, -0.69f, -3.82f, -2.05f, -4.84f, -2.28f)
                curveToRelative(-4.83f, -1.07f, -9.71f, 4.74f, -4.05f, 9.68f)
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(98.03f, 94.1f)
                    lineToRelative(0.66f, -0.75f)
                    close()
                    moveTo(102.6f, 96.08f)
                    lineTo(102.4f, 97.06f)
                    lineTo(103.01f, 95.17f)
                    close()
                    moveTo(98.65f, 94.1f)
                    lineTo(98.01f, 94.86f)
                    lineTo(98.01f, 94.86f)
                    close()
                    moveTo(102.61f, 84.97f)
                    lineTo(102.77f, 83.98f)
                    close()
                    moveTo(106.92f, 86.7f)
                    lineTo(106.48f, 87.6f)
                    lineTo(107.5f, 85.88f)
                    close()
                    moveTo(102.08f, 84.42f)
                    lineTo(101.86f, 85.39f)
                    horizontalLineToRelative(0.0f)
                    close()
                    moveTo(97.37f, 94.86f)
                    curveToRelative(0.52f, 0.46f, 1.5f, 0.94f, 2.39f, 1.32f)
                    curveToRelative(0.92f, 0.39f, 1.93f, 0.74f, 2.64f, 0.89f)
                    lineToRelative(0.41f, -1.96f)
                    curveToRelative(-0.52f, -0.11f, -1.4f, -0.41f, -2.27f, -0.77f)
                    curveToRelative(-0.9f, -0.38f, -1.59f, -0.76f, -1.85f, -0.98f)
                    close()
                    moveTo(103.01f, 95.17f)
                    curveToRelative(-0.7f, -0.31f, -1.38f, -0.58f, -2.09f, -0.9f)
                    curveToRelative(-0.69f, -0.32f, -1.26f, -0.63f, -1.62f, -0.94f)
                    lineToRelative(-1.28f, 1.53f)
                    curveToRelative(0.58f, 0.48f, 1.34f, 0.88f, 2.07f, 1.22f)
                    curveToRelative(0.71f, 0.33f, 1.53f, 0.66f, 2.13f, 0.92f)
                    close()
                    moveTo(99.3f, 93.33f)
                    curveToRelative(-1.94f, -1.63f, -2.24f, -3.68f, -1.6f, -5.16f)
                    curveToRelative(0.63f, -1.47f, 2.28f, -2.61f, 4.76f, -2.21f)
                    lineToRelative(0.31f, -1.98f)
                    curveToRelative(-3.31f, -0.52f, -5.89f, 1.02f, -6.91f, 3.4f)
                    curveToRelative(-1.02f, 2.37f, -0.4f, 5.34f, 2.15f, 7.48f)
                    close()
                    moveTo(102.45f, 85.95f)
                    curveToRelative(0.4f, 0.06f, 1.02f, 0.27f, 1.78f, 0.59f)
                    curveToRelative(0.74f, 0.31f, 1.53f, 0.69f, 2.25f, 1.05f)
                    lineToRelative(0.89f, -1.79f)
                    arcToRelative(45.0f, 45.0f, 0.0f, false, false, -2.36f, -1.1f)
                    curveToRelative(-0.77f, -0.32f, -1.58f, -0.62f, -2.24f, -0.73f)
                    close()
                    moveTo(107.5f, 85.88f)
                    curveToRelative(-0.56f, -0.4f, -1.58f, -0.94f, -2.55f, -1.4f)
                    curveToRelative(-0.95f, -0.45f, -2.02f, -0.9f, -2.66f, -1.04f)
                    lineToRelative(-0.43f, 1.95f)
                    curveToRelative(0.39f, 0.09f, 1.26f, 0.44f, 2.23f, 0.89f)
                    curveToRelative(0.95f, 0.45f, 1.84f, 0.93f, 2.25f, 1.22f)
                    close()
                    moveTo(102.29f, 83.44f)
                    curveToRelative(-2.86f, -0.63f, -5.72f, 0.76f, -7.04f, 3.06f)
                    arcToRelative(5.66f, 5.66f, 0.0f, false, false, -0.63f, 4.06f)
                    curveToRelative(0.32f, 1.48f, 1.21f, 2.95f, 2.76f, 4.29f)
                    lineToRelative(1.32f, -1.51f)
                    curveToRelative(-1.29f, -1.13f, -1.91f, -2.24f, -2.12f, -3.21f)
                    arcToRelative(3.66f, 3.66f, 0.0f, false, true, 0.41f, -2.64f)
                    curveToRelative(0.9f, -1.57f, 2.9f, -2.55f, 4.88f, -2.11f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFA98116)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd
                ) {
                    moveTo(113.21f, 103.3f)
                    curveToRelative(-0.14f, -0.05f, -10.5f, 3.16f, -7.59f, 7.86f)
                    curveToRelative(-0.47f, -2.07f, 0.5f, -4.47f, 7.59f, -7.86f)
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(113.21f, 103.3f)
                    lineToRelative(0.43f, 0.9f)
                    lineToRelative(2.04f, -0.97f)
                    lineToRelative(-2.09f, -0.85f)
                    close()
                    moveTo(105.62f, 111.16f)
                    lineTo(104.77f, 111.69f)
                    lineTo(106.6f, 110.94f)
                    close()
                    moveTo(113.59f, 102.38f)
                    curveToRelative(-0.18f, -0.08f, -0.34f, -0.08f, -0.37f, -0.08f)
                    arcToRelative(1.15f, 1.15f, 0.0f, false, false, -0.24f, 0.02f)
                    arcToRelative(2.0f, 2.0f, 0.0f, false, false, -0.16f, 0.04f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, false, -0.39f, 0.12f)
                    curveToRelative(-0.31f, 0.1f, -0.74f, 0.25f, -1.23f, 0.44f)
                    curveToRelative(-0.98f, 0.38f, -2.26f, 0.95f, -3.45f, 1.68f)
                    curveToRelative(-1.17f, 0.72f, -2.36f, 1.67f, -3.05f, 2.84f)
                    curveToRelative(-0.35f, 0.6f, -0.58f, 1.28f, -0.59f, 2.03f)
                    curveToRelative(-0.01f, 0.75f, 0.22f, 1.5f, 0.67f, 2.22f)
                    lineToRelative(1.7f, -1.05f)
                    curveToRelative(-0.28f, -0.45f, -0.37f, -0.83f, -0.37f, -1.15f)
                    curveToRelative(0.0f, -0.33f, 0.1f, -0.67f, 0.32f, -1.03f)
                    curveToRelative(0.44f, -0.75f, 1.3f, -1.49f, 2.38f, -2.15f)
                    curveToRelative(1.05f, -0.65f, 2.21f, -1.17f, 3.12f, -1.52f)
                    arcToRelative(19.0f, 19.0f, 0.0f, false, true, 1.11f, -0.39f)
                    quadToRelative(0.2f, -0.06f, 0.3f, -0.09f)
                    lineToRelative(0.04f, -0.01f)
                    curveToRelative(-0.0f, 0.0f, -0.03f, 0.0f, -0.06f, 0.01f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.11f, 0.0f)
                    curveToRelative(-0.02f, -0.0f, -0.18f, 0.0f, -0.36f, -0.07f)
                    close()
                    moveTo(106.6f, 110.93f)
                    arcToRelative(2.5f, 2.5f, 0.0f, false, true, -0.01f, -1.17f)
                    curveToRelative(0.09f, -0.39f, 0.31f, -0.86f, 0.76f, -1.4f)
                    curveToRelative(0.93f, -1.12f, 2.79f, -2.49f, 6.29f, -4.16f)
                    lineToRelative(-0.86f, -1.8f)
                    curveToRelative(-3.58f, 1.71f, -5.76f, 3.23f, -6.97f, 4.68f)
                    curveToRelative(-0.62f, 0.74f, -1.0f, 1.48f, -1.17f, 2.22f)
                    arcToRelative(4.5f, 4.5f, 0.0f, false, false, 0.01f, 2.08f)
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
                    moveTo(106.5f, 145.5f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(106.0f)
                    verticalLineToRelative(145.0f)
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
                    moveTo(70.5f, 145.5f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(106.0f)
                    verticalLineToRelative(145.0f)
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
                    moveTo(155.2f, 46.32f)
                    horizontalLineTo(81.06f)
                    verticalLineToRelative(22.18f)
                    horizontalLineTo(155.2f)
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
                    fill = SolidColor(Color(0xFF3CB572)),
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
                    fill = SolidColor(Color(0xFF3CB572)),
                    stroke = SolidColor(Color(0xFF000000)),
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
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF000000)),
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
                    fill = SolidColor(Color(0xFFE3F6DC)),
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
                    fill = SolidColor(Color(0xFF3CB572)),
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
                    fill = SolidColor(Color(0xFF014958)),
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
                    fill = SolidColor(Color(0xFFFFFFFF)),
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(146.49f, 135.73f)
                    horizontalLineToRelative(69.07f)
                    verticalLineToRelative(120.9f)
                    horizontalLineToRelative(-69.07f)
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
                    moveTo(210.26f, 151.54f)
                    horizontalLineToRelative(-20.48f)
                    verticalLineToRelative(9.37f)
                    horizontalLineToRelative(20.48f)
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
                    moveTo(210.26f, 151.54f)
                    horizontalLineToRelative(-15.25f)
                    verticalLineToRelative(9.37f)
                    horizontalLineToRelative(15.25f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3CB572)),
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(215.83f, 135.73f)
                    horizontalLineToRelative(34.77f)
                    verticalLineToRelative(120.9f)
                    horizontalLineToRelative(-34.77f)
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
                    moveTo(7.8f, 203.61f)
                    horizontalLineTo(117.75f)
                    verticalLineToRelative(53.01f)
                    horizontalLineTo(7.8f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(0.69f, 224.51f)
                    lineToRelative(6.97f, -20.9f)
                    horizontalLineToRelative(109.9f)
                    lineToRelative(-6.97f, 20.9f)
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
                    moveTo(118.02f, 203.61f)
                    horizontalLineToRelative(48.64f)
                    verticalLineToRelative(53.01f)
                    horizontalLineTo(118.02f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF014958)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(125.18f, 224.51f)
                    lineToRelative(-6.97f, -20.9f)
                    horizontalLineToRelative(48.58f)
                    lineToRelative(6.97f, 20.9f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(219.27f, 257.23f)
                    verticalLineTo(233.5f)
                    horizontalLineToRelative(69.38f)
                    verticalLineToRelative(23.73f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE3F6DC)),
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(195.39f, 257.23f)
                    verticalLineTo(233.5f)
                    horizontalLineToRelative(69.38f)
                    verticalLineToRelative(23.73f)
                    close()
                }
            }
        }.build()
        return _storageBoxPileLight!!
    }

private var _storageBoxPileLight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.StorageBoxPileLight
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
