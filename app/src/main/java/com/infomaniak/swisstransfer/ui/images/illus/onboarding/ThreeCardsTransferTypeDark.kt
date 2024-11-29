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
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound

val AppIllus.ThreeCardsTransferTypeDark: ImageVector
    get() {
        if (_threeCardsTransferTypeDark != null) {
            return _threeCardsTransferTypeDark!!
        }
        _threeCardsTransferTypeDark = Builder(
            name = "ThreeCardsTransferTypeDark",
            defaultWidth = 296.0.dp,
            defaultHeight = 210.0.dp,
            viewportWidth = 296.0f,
            viewportHeight = 210.0f
        ).apply {
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 2.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(270.05f, 200.24f)
                curveToRelative(7.94f, 1.49f, 16.46f, -2.26f, 20.13f, -13.08f)
                moveTo(278.15f, 206.6f)
                curveToRelative(5.49f, 1.06f, 11.33f, -1.38f, 13.78f, -8.6f)
            }
            path(
                fill = SolidColor(Color(0x00000000)),
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2.0f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(43.28f, 8.95f)
                curveToRelative(-8.35f, -1.06f, -16.97f, 3.37f, -20.11f, 14.84f)
                moveTo(34.47f, 2.83f)
                curveToRelative(-5.77f, -0.76f, -11.69f, 2.14f, -13.79f, 9.79f)
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
                moveTo(148.56f, 49.57f)
                lineTo(227.55f, 28.4f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 231.3f, 30.56f)
                lineTo(270.87f, 178.24f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 268.71f, 181.99f)
                lineTo(189.72f, 203.15f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 185.97f, 200.99f)
                lineTo(146.4f, 53.31f)
                arcTo(3.06f, 3.06f, 120.0f, false, true, 148.56f, 49.57f)
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
                moveTo(164.26f, 66.93f)
                lineTo(222.64f, 51.29f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 224.82f, 52.54f)
                lineTo(242.31f, 117.8f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 241.05f, 119.98f)
                lineTo(182.66f, 135.62f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 180.49f, 134.36f)
                lineTo(163.0f, 69.11f)
                arcTo(1.78f, 1.78f, 120.0f, false, true, 164.26f, 66.93f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(202.65f, 93.45f)
                moveToRelative(-20.61f, 5.52f)
                arcToRelative(21.33f, 21.33f, 120.0f, true, true, 41.21f, -11.04f)
                arcToRelative(21.33f, 21.33f, 120.0f, true, true, -41.21f, 11.04f)
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 0.5f,
                strokeLineCap = strokeCapRound,
                strokeLineJoin = strokeJoinRound,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(192.47f, 89.02f)
                curveToRelative(-1.2f, 0.32f, -1.88f, 1.57f, -1.56f, 2.75f)
                lineToRelative(2.76f, 10.3f)
                curveToRelative(0.32f, 1.18f, 1.52f, 1.92f, 2.73f, 1.6f)
                lineToRelative(16.8f, -4.5f)
                curveToRelative(1.2f, -0.32f, 1.88f, -1.57f, 1.56f, -2.75f)
                lineToRelative(-2.76f, -10.3f)
                curveToRelative(-0.32f, -1.18f, -1.52f, -1.92f, -2.73f, -1.6f)
                close()
                moveTo(195.43f, 101.6f)
                lineTo(192.86f, 91.98f)
                lineTo(202.87f, 96.5f)
                arcToRelative(0.92f, 0.92f, 0.0f, false, false, 1.12f, -0.3f)
                lineToRelative(6.41f, -8.92f)
                lineToRelative(2.58f, 9.62f)
                arcToRelative(0.4f, 0.4f, 0.0f, false, true, -0.27f, 0.5f)
                lineToRelative(-16.8f, 4.5f)
                arcToRelative(0.4f, 0.4f, 0.0f, false, true, -0.48f, -0.3f)
                moveToRelative(-1.4f, -11.1f)
                lineToRelative(14.61f, -3.91f)
                lineToRelative(-5.7f, 7.93f)
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
                moveToRelative(210.69f, 150.77f)
                lineToRelative(-4.44f, -6.95f)
                lineToRelative(2.04f, 7.6f)
                lineToRelative(-1.23f, 0.33f)
                lineToRelative(-2.34f, -8.71f)
                lineToRelative(2.06f, -0.55f)
                lineToRelative(4.17f, 6.66f)
                lineToRelative(0.28f, -7.86f)
                lineToRelative(2.06f, -0.55f)
                lineToRelative(2.34f, 8.72f)
                lineToRelative(-1.23f, 0.33f)
                lineToRelative(-2.04f, -7.6f)
                lineToRelative(-0.37f, 8.24f)
                close()
                moveTo(216.67f, 147.34f)
                curveToRelative(-0.41f, -1.53f, 1.19f, -2.43f, 3.77f, -3.44f)
                lineToRelative(-0.04f, -0.16f)
                curveToRelative(-0.32f, -1.18f, -1.08f, -1.4f, -1.89f, -1.18f)
                curveToRelative(-0.9f, 0.24f, -1.3f, 0.88f, -1.13f, 1.68f)
                lineToRelative(-1.13f, 0.3f)
                curveToRelative(-0.26f, -1.42f, 0.75f, -2.53f, 2.01f, -2.87f)
                curveToRelative(1.78f, -0.48f, 2.81f, 0.16f, 3.29f, 1.98f)
                lineToRelative(0.39f, 1.49f)
                curveToRelative(0.28f, 1.09f, 0.54f, 1.81f, 0.82f, 2.39f)
                lineToRelative(-1.11f, 0.3f)
                arcToRelative(7.0f, 7.0f, 0.0f, false, true, -0.32f, -0.87f)
                curveToRelative(-0.22f, 0.77f, -0.79f, 1.33f, -1.93f, 1.64f)
                curveToRelative(-1.21f, 0.33f, -2.4f, -0.08f, -2.72f, -1.27f)
                moveToRelative(4.08f, -2.6f)
                curveToRelative(-1.93f, 0.74f, -3.11f, 1.36f, -2.88f, 2.25f)
                curveToRelative(0.16f, 0.61f, 0.74f, 0.9f, 1.52f, 0.69f)
                curveToRelative(1.02f, -0.27f, 1.87f, -1.0f, 1.46f, -2.53f)
                close()
                moveTo(222.99f, 138.14f)
                lineTo(223.34f, 139.46f)
                lineTo(222.25f, 139.75f)
                lineTo(221.89f, 138.43f)
                close()
                moveTo(223.59f, 140.38f)
                lineTo(225.32f, 146.85f)
                lineTo(224.23f, 147.14f)
                lineTo(222.5f, 140.68f)
                close()
                moveTo(228.15f, 146.09f)
                lineTo(227.06f, 146.39f)
                lineTo(224.72f, 137.67f)
                lineTo(225.81f, 137.38f)
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
                moveTo(44.72f, 28.4f)
                lineTo(123.71f, 49.57f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 125.87f, 53.31f)
                lineTo(86.3f, 200.99f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 82.56f, 203.15f)
                lineTo(3.56f, 181.99f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 1.4f, 178.24f)
                lineTo(40.97f, 30.56f)
                arcTo(3.06f, 3.06f, 60.0f, false, true, 44.72f, 28.4f)
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
                moveTo(47.91f, 50.83f)
                lineTo(108.01f, 66.93f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 109.27f, 69.11f)
                lineTo(91.78f, 134.36f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 89.61f, 135.62f)
                lineTo(29.5f, 119.52f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 28.25f, 117.34f)
                lineTo(45.73f, 52.09f)
                arcTo(1.78f, 1.78f, 60.0f, false, true, 47.91f, 50.83f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(56.08f, 69.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(55.53f, 72.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(54.98f, 74.07f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(54.42f, 76.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(53.87f, 78.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(53.32f, 80.25f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(58.14f, 70.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(55.38f, 80.81f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(60.2f, 71.06f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(59.1f, 75.18f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(58.55f, 77.24f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(57.44f, 81.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(62.26f, 71.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(61.16f, 75.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(60.61f, 77.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(59.5f, 81.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(64.32f, 72.16f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(61.56f, 82.46f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(66.38f, 72.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(65.83f, 74.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(65.28f, 76.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(64.73f, 78.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(64.18f, 80.96f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(63.62f, 83.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(77.15f, 106.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(76.6f, 108.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(76.05f, 110.56f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(75.5f, 112.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(74.94f, 114.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(74.39f, 116.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(79.21f, 107.0f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(76.45f, 117.3f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(81.27f, 107.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(80.17f, 111.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(79.62f, 113.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.51f, 117.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(83.33f, 108.1f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(82.23f, 112.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(81.68f, 114.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(80.57f, 118.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(85.39f, 108.65f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(82.63f, 118.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(87.46f, 109.2f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(86.9f, 111.27f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(86.35f, 113.33f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(85.8f, 115.39f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(85.25f, 117.45f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(84.69f, 119.51f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(52.18f, 84.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(51.62f, 86.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(51.07f, 88.49f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(50.52f, 90.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(49.42f, 94.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(48.86f, 96.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(48.31f, 98.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(47.21f, 102.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(46.65f, 104.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(46.1f, 107.03f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(45.55f, 109.09f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(54.24f, 84.92f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(53.69f, 86.98f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(50.92f, 97.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(49.82f, 101.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(48.72f, 105.52f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(47.61f, 109.64f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(56.3f, 85.47f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(54.64f, 91.65f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(52.43f, 99.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(51.88f, 101.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(51.33f, 104.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(50.22f, 108.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(49.67f, 110.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(56.15f, 94.26f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(55.05f, 98.38f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(54.49f, 100.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(53.94f, 102.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(53.39f, 104.57f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(52.84f, 106.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(52.28f, 108.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(51.73f, 110.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(60.42f, 86.57f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(59.87f, 88.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(59.31f, 90.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(58.76f, 92.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(56.0f, 103.06f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(55.45f, 105.12f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(54.34f, 109.24f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(60.82f, 93.31f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(60.27f, 95.37f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(58.62f, 101.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(58.06f, 103.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(57.51f, 105.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(55.85f, 111.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(61.23f, 100.04f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(59.57f, 106.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(58.47f, 110.34f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(70.47f, 73.8f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(67.71f, 84.11f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(62.5f, 87.14f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(63.29f, 100.59f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(62.18f, 104.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(61.63f, 106.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(61.08f, 108.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(59.97f, 112.96f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(72.53f, 74.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(71.97f, 76.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(71.42f, 78.48f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(70.87f, 80.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(70.32f, 82.6f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(69.77f, 84.66f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(66.23f, 110.21f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(62.59f, 111.45f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(62.04f, 113.51f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(74.59f, 74.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(74.04f, 76.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(72.38f, 83.15f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(71.83f, 85.21f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(64.56f, 87.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(65.2f, 109.94f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(64.65f, 112.0f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(64.1f, 114.06f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(75.54f, 79.58f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(74.99f, 81.64f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(61.72f, 97.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-1.99f, -0.53f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(68.37f, 106.37f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(66.87f, 103.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(67.81f, 108.43f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(67.26f, 110.49f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(66.71f, 112.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(66.16f, 114.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.71f, 76.01f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(77.6f, 80.13f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(77.05f, 82.19f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(75.95f, 86.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(71.0f, 104.87f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(70.43f, 106.92f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(79.67f, 80.69f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.56f, 84.81f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.01f, 86.87f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(77.47f, 88.94f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.98f, 91.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(76.22f, 101.86f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(64.81f, 103.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(73.59f, 103.35f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(73.04f, 105.41f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(71.93f, 109.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(71.38f, 111.6f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(70.83f, 113.66f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(84.89f, 77.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(84.34f, 79.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(83.79f, 81.79f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(83.23f, 83.85f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(82.68f, 85.91f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(82.13f, 87.97f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(81.03f, 92.09f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(80.47f, 94.15f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(79.37f, 98.28f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.82f, 100.34f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(78.26f, 102.4f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(86.95f, 78.22f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(84.19f, 88.52f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(82.53f, 94.71f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(81.98f, 96.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(81.43f, 98.83f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(80.32f, 102.95f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.01f, 78.77f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(87.91f, 82.89f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(87.36f, 84.96f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(86.25f, 89.08f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(84.59f, 95.26f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(84.04f, 97.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(83.49f, 99.38f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(82.94f, 101.44f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(82.39f, 103.5f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(91.07f, 79.32f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.97f, 83.45f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(89.42f, 85.51f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(88.31f, 89.63f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(87.21f, 93.75f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(85.55f, 99.93f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(93.13f, 79.88f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(90.37f, 90.18f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.27f, 94.3f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(88.71f, 96.36f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(88.16f, 98.42f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(87.61f, 100.48f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(87.06f, 102.54f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(86.51f, 104.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(95.19f, 80.43f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(94.64f, 82.49f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(94.09f, 84.55f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(93.54f, 86.61f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(92.99f, 88.67f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(92.43f, 90.73f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(90.22f, 98.98f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(89.12f, 103.1f)
                lineToRelative(0.55f, -2.06f)
                lineToRelative(-2.06f, -0.55f)
                lineToRelative(-0.55f, 2.06f)
                close()
                moveTo(75.42f, 88.91f)
                lineTo(67.0f, 86.65f)
                curveToRelative(-0.58f, -0.16f, -1.19f, 0.22f, -1.36f, 0.85f)
                lineToRelative(-2.42f, 9.03f)
                curveToRelative(-0.17f, 0.62f, 0.17f, 1.25f, 0.75f, 1.41f)
                lineTo(72.4f, 100.2f)
                curveToRelative(0.58f, 0.16f, 1.19f, -0.22f, 1.36f, -0.85f)
                lineToRelative(2.42f, -9.03f)
                curveToRelative(0.17f, -0.62f, -0.17f, -1.26f, -0.75f, -1.41f)
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
                moveToRelative(54.04f, 147.46f)
                lineToRelative(0.76f, 1.31f)
                lineToRelative(-0.94f, 0.55f)
                lineToRelative(-0.78f, -1.36f)
                curveToRelative(-0.74f, 0.25f, -1.57f, 0.28f, -2.44f, 0.04f)
                curveToRelative(-2.51f, -0.67f, -3.58f, -2.98f, -2.88f, -5.61f)
                curveToRelative(0.7f, -2.63f, 2.78f, -4.09f, 5.29f, -3.41f)
                curveToRelative(2.51f, 0.67f, 3.59f, 2.97f, 2.88f, 5.6f)
                curveToRelative(-0.34f, 1.27f, -1.01f, 2.27f, -1.88f, 2.88f)
                moveToRelative(-3.14f, -0.49f)
                arcToRelative(2.77f, 2.77f, 0.0f, false, false, 1.58f, -0.03f)
                lineToRelative(-0.59f, -1.02f)
                lineToRelative(0.95f, -0.56f)
                lineToRelative(0.59f, 1.03f)
                curveToRelative(0.55f, -0.47f, 0.99f, -1.18f, 1.25f, -2.15f)
                curveToRelative(0.6f, -2.25f, -0.32f, -3.81f, -1.91f, -4.23f)
                curveToRelative(-1.59f, -0.43f, -3.17f, 0.47f, -3.77f, 2.71f)
                reflectiveCurveToRelative(0.32f, 3.82f, 1.9f, 4.25f)
                moveToRelative(9.91f, -0.35f)
                curveToRelative(-0.22f, -0.05f, -0.44f, -0.1f, -0.66f, -0.17f)
                lineToRelative(-1.54f, -0.41f)
                lineToRelative(-0.98f, 3.67f)
                lineToRelative(-1.23f, -0.33f)
                lineToRelative(2.34f, -8.72f)
                lineToRelative(2.77f, 0.74f)
                curveToRelative(2.13f, 0.57f, 3.52f, 1.39f, 2.96f, 3.48f)
                curveToRelative(-0.37f, 1.38f, -1.21f, 1.87f, -2.36f, 1.87f)
                lineToRelative(0.98f, 4.41f)
                lineToRelative(-1.37f, -0.37f)
                close()
                moveTo(59.69f, 142.03f)
                lineTo(58.89f, 145.01f)
                lineTo(60.52f, 145.45f)
                curveToRelative(1.27f, 0.34f, 2.31f, 0.48f, 2.67f, -0.87f)
                reflectiveCurveToRelative(-0.6f, -1.77f, -1.87f, -2.11f)
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
                moveTo(91.0f, 13.5f)
                lineTo(183.0f, 13.5f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 186.5f, 17.0f)
                lineTo(186.5f, 189.0f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 183.0f, 192.5f)
                lineTo(91.0f, 192.5f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 87.5f, 189.0f)
                lineTo(87.5f, 17.0f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 91.0f, 13.5f)
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
                moveTo(103.0f, 37.0f)
                lineTo(171.0f, 37.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 173.0f, 39.0f)
                lineTo(173.0f, 115.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 171.0f, 117.0f)
                lineTo(103.0f, 117.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 101.0f, 115.0f)
                lineTo(101.0f, 39.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 103.0f, 37.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1A1A1A)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(137.0f, 77.0f)
                moveToRelative(-24.0f, 0.0f)
                arcToRelative(24.0f, 24.0f, 0.0f, true, true, 48.0f, 0.0f)
                arcToRelative(24.0f, 24.0f, 0.0f, true, true, -48.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF67DD95)),
                stroke = SolidColor(Color(0xFF67DD95)),
                strokeLineWidth = 0.5f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(137.85f, 79.0f)
                lineToRelative(-0.01f, 0.01f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.13f, 0.73f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, 0.4f, 0.62f)
                curveToRelative(0.22f, 0.15f, 0.49f, 0.17f, 0.73f, 0.13f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, 0.62f, -0.39f)
                curveToRelative(0.83f, -1.14f, 1.26f, -2.51f, 1.05f, -3.89f)
                curveToRelative(-0.11f, -1.38f, -0.85f, -2.64f, -1.89f, -3.57f)
                curveToRelative(-1.05f, -0.94f, -2.41f, -1.47f, -3.79f, -1.37f)
                curveToRelative(-1.38f, 0.0f, -2.74f, 0.64f, -3.68f, 1.58f)
                lineToRelative(-4.82f, 4.82f)
                curveToRelative(-1.05f, 1.05f, -1.58f, 2.42f, -1.58f, 3.89f)
                reflectiveCurveToRelative(0.52f, 2.84f, 1.58f, 3.89f)
                curveToRelative(1.05f, 1.05f, 2.42f, 1.58f, 3.89f, 1.58f)
                curveToRelative(1.57f, 0.0f, 2.94f, -0.52f, 3.99f, -1.58f)
                lineToRelative(0.9f, -0.9f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, 0.0f, -1.46f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, -1.46f, 0.0f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-0.9f, 1.0f)
                curveToRelative(-1.31f, 1.3f, -3.65f, 1.3f, -4.96f, 0.0f)
                curveToRelative(-0.67f, -0.77f, -1.04f, -1.61f, -1.04f, -2.54f)
                curveToRelative(0.0f, -0.94f, 0.38f, -1.88f, 1.03f, -2.54f)
                lineToRelative(4.82f, -4.82f)
                curveToRelative(0.55f, -0.55f, 1.39f, -0.93f, 2.35f, -1.03f)
                curveToRelative(0.84f, 0.0f, 1.66f, 0.28f, 2.3f, 1.11f)
                lineToRelative(0.01f, 0.02f)
                lineToRelative(0.02f, 0.02f)
                curveToRelative(0.66f, 0.56f, 1.13f, 1.41f, 1.22f, 2.25f)
                arcToRelative(3.34f, 3.34f, 0.0f, false, true, -0.65f, 2.43f)
                close()
                moveTo(136.25f, 75.78f)
                lineTo(136.25f, 75.77f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, 0.13f, -0.73f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.4f, -0.62f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.73f, -0.13f)
                arcToRelative(0.98f, 0.98f, 0.0f, false, false, -0.62f, 0.39f)
                curveToRelative(-0.83f, 1.15f, -1.26f, 2.52f, -1.05f, 3.9f)
                curveToRelative(0.21f, 1.36f, 0.83f, 2.61f, 1.88f, 3.56f)
                curveToRelative(0.96f, 0.96f, 2.23f, 1.37f, 3.59f, 1.37f)
                horizontalLineToRelative(0.1f)
                curveToRelative(1.38f, 0.0f, 2.75f, -0.63f, 3.69f, -1.58f)
                lineToRelative(4.82f, -4.82f)
                curveToRelative(1.05f, -1.05f, 1.58f, -2.42f, 1.58f, -3.89f)
                reflectiveCurveToRelative(-0.52f, -2.84f, -1.58f, -3.89f)
                curveToRelative(-1.05f, -1.05f, -2.42f, -1.58f, -3.89f, -1.58f)
                reflectiveCurveToRelative(-2.84f, 0.53f, -3.89f, 1.58f)
                lineToRelative(-0.7f, 0.7f)
                curveToRelative(-0.19f, 0.19f, -0.31f, 0.44f, -0.35f, 0.7f)
                arcToRelative(0.9f, 0.9f, 0.0f, false, false, 0.25f, 0.76f)
                arcToRelative(1.04f, 1.04f, 0.0f, false, false, 1.46f, 0.0f)
                lineToRelative(0.8f, -0.8f)
                curveToRelative(1.31f, -1.31f, 3.65f, -1.31f, 4.96f, -0.01f)
                curveToRelative(0.67f, 0.77f, 1.04f, 1.61f, 1.04f, 2.54f)
                curveToRelative(0.0f, 0.94f, -0.38f, 1.77f, -0.91f, 2.22f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-0.01f, 0.01f)
                lineToRelative(-4.82f, 4.82f)
                curveToRelative(-0.66f, 0.66f, -1.5f, 1.03f, -2.33f, 1.03f)
                curveToRelative(-0.95f, 0.0f, -1.8f, -0.28f, -2.45f, -0.84f)
                curveToRelative(-0.66f, -0.56f, -1.13f, -1.41f, -1.22f, -2.25f)
                arcToRelative(3.34f, 3.34f, 0.0f, false, true, 0.65f, -2.43f)
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
                moveTo(126.9f, 140.77f)
                horizontalLineToRelative(5.36f)
                lineTo(132.27f, 142.0f)
                horizontalLineToRelative(-6.79f)
                verticalLineToRelative(-10.15f)
                horizontalLineToRelative(1.43f)
                close()
                moveTo(134.55f, 131.85f)
                verticalLineToRelative(1.54f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-1.54f)
                close()
                moveTo(134.55f, 134.47f)
                lineTo(134.55f, 142.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-7.53f)
                close()
                moveTo(137.85f, 135.6f)
                curveToRelative(0.43f, -0.71f, 1.16f, -1.26f, 2.44f, -1.26f)
                curveToRelative(1.82f, 0.0f, 2.48f, 1.18f, 2.48f, 2.9f)
                lineTo(142.76f, 142.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-4.38f)
                curveToRelative(0.0f, -1.16f, -0.2f, -2.17f, -1.65f, -2.17f)
                curveToRelative(-1.23f, 0.0f, -1.99f, 0.85f, -1.99f, 2.46f)
                lineTo(137.85f, 142.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-7.53f)
                horizontalLineToRelative(1.27f)
                close()
                moveTo(145.94f, 131.85f)
                verticalLineToRelative(6.01f)
                lineToRelative(3.04f, -3.39f)
                horizontalLineToRelative(1.57f)
                lineToRelative(-2.87f, 3.12f)
                lineToRelative(3.26f, 4.41f)
                horizontalLineToRelative(-1.55f)
                lineToRelative(-2.56f, -3.47f)
                lineToRelative(-0.88f, 0.95f)
                lineTo(145.94f, 142.0f)
                horizontalLineToRelative(-1.27f)
                verticalLineToRelative(-10.15f)
                close()
            }
        }.build()
        return _threeCardsTransferTypeDark!!
    }

private var _threeCardsTransferTypeDark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    val imageVector = AppIllus.ThreeCardsTransferTypeDark
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
