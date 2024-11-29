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

val AppIllus.StorageBoxPileDark: ImageVector
    get() {
        if (_storageBoxPileDark != null) {
            return _storageBoxPileDark!!
        }
        _storageBoxPileDark = Builder(
            name = "StorageBoxPileDark",
            defaultWidth = 289.0.dp,
            defaultHeight = 258.0.dp,
            viewportWidth = 289.0f,
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
                moveTo(97.98f, 94.05f)
                curveToRelative(0.78f, 0.68f, 3.34f, 1.72f, 4.57f, 1.98f)
                curveToRelative(-1.29f, -0.57f, -3.01f, -1.2f, -3.95f, -1.99f)
                curveToRelative(-4.49f, -3.76f, -1.84f, -10.04f, 3.95f, -9.12f)
                curveToRelative(1.06f, 0.17f, 2.86f, 1.02f, 4.31f, 1.73f)
                curveToRelative(-0.97f, -0.69f, -3.81f, -2.05f, -4.84f, -2.28f)
                curveToRelative(-4.83f, -1.07f, -9.7f, 4.74f, -4.04f, 9.68f)
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
                    moveToRelative(97.98f, 94.05f)
                    lineToRelative(0.66f, -0.75f)
                    close()
                    moveTo(102.55f, 96.03f)
                    lineTo(102.34f, 97.01f)
                    lineTo(102.95f, 95.12f)
                    close()
                    moveTo(98.6f, 94.04f)
                    lineToRelative(-0.64f, 0.77f)
                    horizontalLineToRelative(0.0f)
                    close()
                    moveTo(102.56f, 84.92f)
                    lineTo(102.71f, 83.93f)
                    close()
                    moveTo(106.86f, 86.65f)
                    lineTo(106.42f, 87.55f)
                    lineTo(107.44f, 85.84f)
                    close()
                    moveTo(102.02f, 84.37f)
                    lineTo(101.81f, 85.35f)
                    close()
                    moveTo(97.32f, 94.8f)
                    curveToRelative(0.52f, 0.46f, 1.5f, 0.94f, 2.38f, 1.32f)
                    curveToRelative(0.92f, 0.39f, 1.93f, 0.74f, 2.64f, 0.89f)
                    lineToRelative(0.41f, -1.96f)
                    curveToRelative(-0.52f, -0.11f, -1.4f, -0.41f, -2.27f, -0.77f)
                    curveToRelative(-0.9f, -0.38f, -1.59f, -0.76f, -1.85f, -0.98f)
                    close()
                    moveTo(102.95f, 95.12f)
                    curveToRelative(-0.7f, -0.31f, -1.38f, -0.58f, -2.09f, -0.9f)
                    curveToRelative(-0.69f, -0.32f, -1.26f, -0.63f, -1.62f, -0.94f)
                    lineToRelative(-1.28f, 1.53f)
                    curveToRelative(0.58f, 0.48f, 1.34f, 0.88f, 2.07f, 1.22f)
                    curveToRelative(0.71f, 0.33f, 1.53f, 0.66f, 2.12f, 0.92f)
                    close()
                    moveTo(99.24f, 93.28f)
                    curveToRelative(-1.94f, -1.63f, -2.24f, -3.67f, -1.6f, -5.16f)
                    curveToRelative(0.63f, -1.47f, 2.27f, -2.61f, 4.76f, -2.21f)
                    lineToRelative(0.31f, -1.97f)
                    curveToRelative(-3.31f, -0.52f, -5.89f, 1.02f, -6.91f, 3.4f)
                    curveToRelative(-1.01f, 2.37f, -0.4f, 5.34f, 2.15f, 7.48f)
                    close()
                    moveTo(102.4f, 85.91f)
                    curveToRelative(0.4f, 0.06f, 1.02f, 0.28f, 1.77f, 0.59f)
                    curveToRelative(0.74f, 0.31f, 1.53f, 0.69f, 2.24f, 1.05f)
                    lineToRelative(0.89f, -1.79f)
                    arcToRelative(45.0f, 45.0f, 0.0f, false, false, -2.35f, -1.1f)
                    curveToRelative(-0.77f, -0.32f, -1.58f, -0.62f, -2.24f, -0.73f)
                    close()
                    moveTo(107.44f, 85.84f)
                    curveToRelative(-0.56f, -0.4f, -1.58f, -0.94f, -2.55f, -1.4f)
                    curveToRelative(-0.95f, -0.45f, -2.02f, -0.9f, -2.65f, -1.04f)
                    lineToRelative(-0.43f, 1.95f)
                    curveToRelative(0.39f, 0.09f, 1.26f, 0.44f, 2.23f, 0.89f)
                    curveToRelative(0.95f, 0.45f, 1.84f, 0.93f, 2.25f, 1.22f)
                    close()
                    moveTo(102.24f, 83.4f)
                    curveToRelative(-2.85f, -0.63f, -5.71f, 0.76f, -7.04f, 3.06f)
                    arcToRelative(5.65f, 5.65f, 0.0f, false, false, -0.63f, 4.06f)
                    curveToRelative(0.32f, 1.48f, 1.21f, 2.94f, 2.76f, 4.29f)
                    lineToRelative(1.31f, -1.51f)
                    curveToRelative(-1.29f, -1.13f, -1.91f, -2.23f, -2.12f, -3.21f)
                    arcToRelative(3.66f, 3.66f, 0.0f, false, true, 0.41f, -2.64f)
                    curveToRelative(0.9f, -1.57f, 2.9f, -2.55f, 4.87f, -2.11f)
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
                    moveTo(113.15f, 103.25f)
                    curveToRelative(-0.14f, -0.05f, -10.5f, 3.16f, -7.58f, 7.85f)
                    curveToRelative(-0.47f, -2.07f, 0.5f, -4.47f, 7.58f, -7.85f)
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
                    moveToRelative(113.15f, 103.25f)
                    lineToRelative(0.43f, 0.9f)
                    lineToRelative(2.04f, -0.97f)
                    lineToRelative(-2.09f, -0.85f)
                    close()
                    moveTo(105.56f, 111.1f)
                    lineTo(104.71f, 111.63f)
                    lineTo(106.54f, 110.88f)
                    close()
                    moveTo(113.53f, 102.32f)
                    curveToRelative(-0.18f, -0.08f, -0.34f, -0.08f, -0.37f, -0.08f)
                    arcToRelative(1.15f, 1.15f, 0.0f, false, false, -0.24f, 0.02f)
                    quadToRelative(-0.08f, 0.02f, -0.16f, 0.04f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, false, -0.39f, 0.12f)
                    curveToRelative(-0.31f, 0.1f, -0.74f, 0.25f, -1.23f, 0.44f)
                    curveToRelative(-0.98f, 0.38f, -2.26f, 0.95f, -3.45f, 1.68f)
                    curveToRelative(-1.17f, 0.72f, -2.36f, 1.67f, -3.05f, 2.84f)
                    curveToRelative(-0.35f, 0.6f, -0.58f, 1.28f, -0.59f, 2.02f)
                    curveToRelative(-0.01f, 0.75f, 0.22f, 1.5f, 0.67f, 2.22f)
                    lineToRelative(1.7f, -1.05f)
                    curveToRelative(-0.28f, -0.45f, -0.37f, -0.83f, -0.37f, -1.15f)
                    curveToRelative(0.0f, -0.33f, 0.1f, -0.67f, 0.32f, -1.03f)
                    curveToRelative(0.44f, -0.75f, 1.3f, -1.49f, 2.37f, -2.15f)
                    curveToRelative(1.05f, -0.65f, 2.21f, -1.17f, 3.12f, -1.52f)
                    curveToRelative(0.45f, -0.18f, 0.84f, -0.31f, 1.11f, -0.39f)
                    quadToRelative(0.2f, -0.06f, 0.3f, -0.09f)
                    lineToRelative(0.04f, -0.01f)
                    curveToRelative(-0.0f, 0.0f, -0.03f, 0.0f, -0.06f, 0.01f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.11f, 0.0f)
                    curveToRelative(-0.02f, 0.0f, -0.18f, 0.0f, -0.36f, -0.07f)
                    close()
                    moveTo(106.54f, 110.88f)
                    arcToRelative(2.5f, 2.5f, 0.0f, false, true, -0.01f, -1.17f)
                    curveToRelative(0.09f, -0.39f, 0.31f, -0.86f, 0.76f, -1.4f)
                    curveToRelative(0.93f, -1.12f, 2.79f, -2.49f, 6.29f, -4.16f)
                    lineToRelative(-0.86f, -1.8f)
                    curveToRelative(-3.58f, 1.71f, -5.75f, 3.23f, -6.96f, 4.68f)
                    curveToRelative(-0.62f, 0.74f, -1.0f, 1.48f, -1.17f, 2.22f)
                    arcToRelative(4.5f, 4.5f, 0.0f, false, false, 0.01f, 2.08f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(106.44f, 145.42f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(105.94f)
                    verticalLineToRelative(144.92f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2B383B)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(70.46f, 145.42f)
                    verticalLineTo(0.5f)
                    horizontalLineToRelative(105.94f)
                    verticalLineToRelative(144.92f)
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
                    moveTo(155.12f, 46.3f)
                    horizontalLineTo(81.01f)
                    verticalLineTo(68.47f)
                    horizontalLineToRelative(74.11f)
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
                    moveTo(104.53f, 58.89f)
                    curveToRelative(0.0f, -1.56f, -1.09f, -2.24f, -2.12f, -2.24f)
                    curveToRelative(-0.97f, 0.0f, -1.61f, 0.57f, -1.82f, 1.04f)
                    lineToRelative(-1.18f, -0.06f)
                    lineToRelative(0.61f, -5.07f)
                    horizontalLineToRelative(5.41f)
                    verticalLineToRelative(1.1f)
                    horizontalLineToRelative(-4.44f)
                    lineToRelative(-0.32f, 2.68f)
                    curveToRelative(0.49f, -0.48f, 1.25f, -0.71f, 2.03f, -0.71f)
                    curveToRelative(1.65f, 0.0f, 3.09f, 1.17f, 3.09f, 3.25f)
                    curveToRelative(0.0f, 2.16f, -1.6f, 3.27f, -3.35f, 3.27f)
                    curveToRelative(-2.18f, 0.0f, -3.17f, -1.38f, -3.35f, -2.9f)
                    horizontalLineToRelative(1.22f)
                    curveToRelative(0.18f, 1.18f, 0.82f, 1.91f, 2.13f, 1.91f)
                    curveToRelative(1.13f, 0.0f, 2.11f, -0.82f, 2.11f, -2.27f)
                    moveToRelative(6.15f, 3.26f)
                    curveToRelative(-2.51f, 0.0f, -3.66f, -1.9f, -3.66f, -4.89f)
                    curveToRelative(0.0f, -2.97f, 1.16f, -4.87f, 3.66f, -4.87f)
                    reflectiveCurveToRelative(3.66f, 1.9f, 3.66f, 4.87f)
                    curveToRelative(0.0f, 2.99f, -1.16f, 4.89f, -3.66f, 4.89f)
                    moveToRelative(0.0f, -8.77f)
                    curveToRelative(-1.48f, 0.0f, -2.4f, 1.27f, -2.4f, 3.88f)
                    curveToRelative(0.0f, 2.63f, 0.92f, 3.9f, 2.4f, 3.9f)
                    reflectiveCurveToRelative(2.4f, -1.27f, 2.4f, -3.9f)
                    curveToRelative(0.0f, -2.61f, -0.92f, -3.88f, -2.4f, -3.88f)
                    moveToRelative(16.32f, 1.92f)
                    horizontalLineToRelative(-1.29f)
                    curveToRelative(-0.31f, -1.05f, -1.17f, -1.79f, -2.68f, -1.79f)
                    curveToRelative(-1.66f, 0.0f, -2.96f, 1.3f, -2.96f, 3.74f)
                    curveToRelative(0.0f, 2.38f, 1.22f, 3.78f, 2.96f, 3.78f)
                    curveToRelative(1.26f, 0.0f, 2.78f, -0.52f, 2.78f, -2.47f)
                    verticalLineToRelative(-0.25f)
                    horizontalLineToRelative(-2.49f)
                    verticalLineToRelative(-1.14f)
                    horizontalLineToRelative(3.74f)
                    verticalLineToRelative(4.81f)
                    horizontalLineToRelative(-1.04f)
                    lineToRelative(-0.06f, -1.18f)
                    curveToRelative(-0.61f, 0.96f, -1.72f, 1.35f, -2.97f, 1.35f)
                    curveToRelative(-2.72f, 0.0f, -4.26f, -2.03f, -4.26f, -4.9f)
                    curveToRelative(0.0f, -2.92f, 1.73f, -4.86f, 4.38f, -4.86f)
                    curveToRelative(1.95f, 0.0f, 3.51f, 1.09f, 3.9f, 2.91f)
                    moveToRelative(2.18f, -2.74f)
                    horizontalLineToRelative(3.39f)
                    curveToRelative(1.75f, 0.0f, 3.31f, 0.45f, 3.31f, 2.33f)
                    curveToRelative(0.0f, 0.9f, -0.39f, 1.7f, -1.04f, 2.08f)
                    curveToRelative(0.9f, 0.38f, 1.43f, 1.08f, 1.43f, 2.23f)
                    curveToRelative(0.0f, 2.0f, -1.49f, 2.78f, -3.38f, 2.78f)
                    horizontalLineToRelative(-3.72f)
                    close()
                    moveTo(130.5f, 56.59f)
                    horizontalLineToRelative(2.27f)
                    curveToRelative(1.08f, 0.0f, 1.77f, -0.41f, 1.77f, -1.52f)
                    curveToRelative(0.0f, -0.96f, -0.71f, -1.39f, -1.77f, -1.39f)
                    horizontalLineToRelative(-2.27f)
                    close()
                    moveTo(130.5f, 60.86f)
                    horizontalLineToRelative(2.4f)
                    curveToRelative(1.34f, 0.0f, 2.0f, -0.58f, 2.0f, -1.65f)
                    curveToRelative(0.0f, -1.11f, -0.66f, -1.57f, -2.08f, -1.57f)
                    horizontalLineToRelative(-2.33f)
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
                    moveTo(75.86f, 57.21f)
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
                    verticalLineTo(66.0f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    close()
                    moveTo(144.31f, 52.14f)
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
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    horizontalLineToRelative(-1.56f)
                    verticalLineToRelative(-1.22f)
                    horizontalLineToRelative(1.56f)
                    verticalLineToRelative(-1.71f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(54.61f, 244.98f)
                    verticalLineTo(145.14f)
                    horizontalLineToRelative(104.72f)
                    verticalLineToRelative(99.84f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3C4F52)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(18.74f, 244.98f)
                    verticalLineTo(145.14f)
                    horizontalLineToRelative(104.72f)
                    verticalLineToRelative(99.84f)
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
                    moveTo(123.0f, 183.0f)
                    horizontalLineTo(19.0f)
                    verticalLineToRelative(17.0f)
                    horizontalLineToRelative(104.0f)
                    close()
                    moveTo(146.0f, 183.0f)
                    horizontalLineToRelative(-22.0f)
                    verticalLineToRelative(17.0f)
                    horizontalLineToRelative(22.0f)
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
                    moveTo(46.26f, 157.37f)
                    horizontalLineTo(27.23f)
                    verticalLineToRelative(9.16f)
                    horizontalLineToRelative(19.02f)
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
                    moveTo(46.26f, 157.37f)
                    horizontalLineTo(30.01f)
                    verticalLineToRelative(9.16f)
                    horizontalLineToRelative(16.24f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3C4F52)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(146.41f, 135.66f)
                    horizontalLineToRelative(69.04f)
                    verticalLineToRelative(120.83f)
                    horizontalLineToRelative(-69.04f)
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
                    moveTo(210.15f, 151.46f)
                    horizontalLineToRelative(-20.47f)
                    verticalLineToRelative(9.36f)
                    horizontalLineToRelative(20.47f)
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
                    moveTo(210.15f, 151.46f)
                    horizontalLineToRelative(-15.24f)
                    verticalLineToRelative(9.36f)
                    horizontalLineToRelative(15.24f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(215.72f, 135.66f)
                    horizontalLineToRelative(34.75f)
                    verticalLineToRelative(120.83f)
                    horizontalLineToRelative(-34.75f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2B383B)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(7.8f, 203.5f)
                    horizontalLineToRelative(109.89f)
                    verticalLineToRelative(52.99f)
                    horizontalLineTo(7.8f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(0.69f, 224.39f)
                    lineToRelative(6.96f, -20.89f)
                    horizontalLineToRelative(109.84f)
                    lineToRelative(-6.96f, 20.89f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF67DD95)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(117.96f, 203.5f)
                    horizontalLineToRelative(48.61f)
                    verticalLineToRelative(52.99f)
                    horizontalLineToRelative(-48.61f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF014958)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveToRelative(125.11f, 224.39f)
                    lineToRelative(-6.96f, -20.89f)
                    horizontalLineToRelative(48.56f)
                    lineToRelative(6.96f, 20.89f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF2B383B)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(219.15f, 257.09f)
                    verticalLineToRelative(-23.72f)
                    horizontalLineToRelative(69.35f)
                    verticalLineToRelative(23.72f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3C4F52)),
                    stroke = SolidColor(Color(0xFFDCE4E5)),
                    strokeLineWidth = 0.999f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(195.29f, 257.09f)
                    verticalLineToRelative(-23.72f)
                    horizontalLineToRelative(69.35f)
                    verticalLineToRelative(23.72f)
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
